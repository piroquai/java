package testLogger

import com.github.sardine.SardineFactory
import com.sun.jndi.cosnaming.IiopUrl
import jnaex.RFWin.logW
import jnaex.Run.RunAllTests
import org.openqa.selenium.remote.server.Session
import java.io.BufferedReader
import java.io.File
import java.io.FileWriter
import java.io.InputStreamReader
import java.net.PasswordAuthentication
import java.text.SimpleDateFormat
import java.util.*
import javax.mail.*

import javax.mail.Session.getInstance
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

/**
 * Created by Autotester on 9/17/2018.
 */
private val logPath = "Log/"
private val reportPath = "report/"

private var testSetMList: MutableList<KotlinTestSet>? = null
private var startDate: Date? = null
private var endDate: Date? = null
private var totalTimeElapsed: Long = 0
private var nPassed: Int = 0
private var nFailed: Int = 0
private var nTotal: Int = 0
private var testVersion: String? = null

var currentTest : Test? = null
var currentKotlinTest : KotlinTest? = null

fun logToTest(s : String){
    if (currentTest == null){
        println("NOTIFICATION: test paratemer is null. Here is the log string:")
        println(s)
    } else {
        currentTest!!.writeDebugInfo(s)
    }
}
fun logToKotlinTest(s : String){
    if (currentKotlinTest == null){
        print("NOTIFICATION: kotlinTest parameter is null::")
        println(s)
    } else {
        currentKotlinTest!!.writeDebugInfo(s)
    }
}

//email reports:
private var reportEmailFromName = "rfwintest@yahoo.com"
fun setReportEmailFromName(s: String) {
    reportEmailFromName = s
}

private var reportEmailFromPassword = "dtR_Njh3"
fun setReportEmailFromPassword(s: String) {
    reportEmailFromPassword = s
}

private var reportEmailSMTPServer = "smtp.mail.yahoo.com"
fun setReportEmailSMTPServer(s: String) {
    reportEmailSMTPServer = s
}

private var reportEmailSMTPPort = "25"
fun setReportEmailSMTPPort(s: String) {
    reportEmailSMTPPort = s
}

private var emailList : MutableList<String>? = null
private val emailListDefault = listOf("ponushenko@siber.com")
fun setEmailList(_emails: List<String>) {
    emailList = _emails.toMutableList()
}

private var pathRFCmd = "C:/Users/Autotester/Desktop/"
fun setPathRFCmd(s: String) {
    pathRFCmd = s
}
fun getPathRFCmd(): String {
    return pathRFCmd
}

private var nameRFCmd = "rf-cmd.exe"
fun setNameRFCmd(s: String) {
    nameRFCmd = s
}
fun getNameRFCmd(): String {
    return nameRFCmd
}

fun startLogging() {
    testSetMList = null
    startDate = Date()
    totalTimeElapsed = 0
    nPassed = 0
    nFailed = 0
    nTotal = 0
    if (emailList == null) {
        setEmailList(emailListDefault)
    }
    if (!RunAllTests.isOfficial()) {
        testVersion = getTestVersion()
    }
}

fun getTestVersion(): String? {
    try {
        //run console
        val pb = ProcessBuilder(pathRFCmd + nameRFCmd, "-v")
        val reader = BufferedReader(InputStreamReader(pb.start().inputStream))
        return reader.readLine()
    } catch (e: Exception) {
        logW("Error trying to get version info: " + e.message)
        return null
    }

}

fun addTestSet(_testSet: KotlinTestSet) {
    if (testSetMList == null){
        testSetMList = mutableListOf(_testSet)
    } else {
        testSetMList!!.add(_testSet)
    }
}

fun stopLogging() {
    endDate = Date()
}

fun prepareFilename(s: String): String {
    val q: String
    val qw = s.toCharArray()
    var i = 0
    val trC = setOf(' ',':')
    for (w in qw) {
        if (w in trC) {
            qw[i] = '_'
        }
        i++
    }

    q = String(qw)
    return q
}

fun writeLogs() {
    var logg : MutableList<String> = mutableListOf()
    logg.add("Test summary for tests started from " + startDate.toString())
    logg.add("=================================================")

    if (testSetMList == null) throw Exception("testSetMList is null")

    for (ts in testSetMList!!) {
        nPassed += ts.getNSuccessful()
        nFailed += ts.getNFailed()
        nTotal += ts.getTotal()
        totalTimeElapsed += ts.getTotalTime()
        logg.addAll(ts.getResults())
    }
    logg.add("=================================================")
    logg.add("Total passed: $nPassed")
    logg.add("Total failed: $nFailed")
    logg.add("Total tests: $nTotal")
    logg.add("Total time elapsed: " + getTimeString(totalTimeElapsed))
    logg.add("=================================================")
    if (nFailed > 0) {
        logg.add("==============LOGS FOR FAILED TESTS==============")
        for (ts in testSetMList!!) {
            logg.addAll(ts.getDebugInfoList())
        }
    }
    val fd = SimpleDateFormat("yyyy-MM-dd-HH-mm")

    val filename = "REPORT_" + fd.format(startDate) + "_tests.txt"
    val file = File(logPath + filename)

    val writer = FileWriter(file, true)

    for (i in logg) {
        writer.write(i + "\n")
    }
    writer.close()
    println("Results are saved to file: $logPath$filename")
}
///////
fun writeLogsHTML() {
    try{
        if (testSetMList == null) throw Exception("testSetMList is null")
        if (startDate == null) throw Exception("startDate is null")
        if (endDate == null) throw Exception("endDate is null")
    } catch (e : Exception){
        logW("Cannot send test results. Reason is: $e")
        return
    }

    val logh = mutableListOf<String>()
    logh.add("<hmtl>")
    logh.add("<head>")
    logh.add("  <meta charset=\"utf-8\" />")
    if (RunAllTests.isOfficial()) {
        logh.add("  <title>Test summary for tests started from $startDate</title>")
    } else {
        logh.add("  <title>Test summary for tests of $testVersion started from $startDate</title>")
    }
    logh.add("  <style>")
    logh.add("    .greencolor {")
    logh.add("      color: green;")
    logh.add("    }")
    logh.add("    .orangecolor {")
    logh.add("      color: orange;")
    logh.add("    }")
    logh.add("    .redcolor {")
    logh.add("      color: red;")
    logh.add("    }")
    logh.add("  </style>")
    logh.add("</head>")
    logh.add("<body>")
    val logg = mutableListOf<String>()
    logg.add("Test summary for tests started from $startDate.")
    if (!RunAllTests.isOfficial()) {
        logg.add("Target internal version: $testVersion")
    }
    logg.add("=================================================")

    for (ts in testSetMList!!) {
        nPassed += ts.getNSuccessful()
        nFailed += ts.getNFailed()
        nTotal += ts.getTotal()
        totalTimeElapsed += ts.getTotalTime()
        logg.addAll(ts.getHTMLResults())
    }
    logg.add("=================================================")
    logg.add("Total passed: $nPassed")
    logg.add("Total failed: $nFailed")
    logg.add("Total tests: $nTotal")
    logg.add("Total time for test execution: ${getTimeString(totalTimeElapsed)}")
    logg.add("Total time elapsed: ${getTimeString((endDate!!.time - startDate!!.time) / 1000)}")
    logg.add("=================================================")
    val loggE = mutableListOf<String>()
    if (nFailed > 0) {
        loggE.add("==============LOGS FOR FAILED TESTS==============")
        for (ts in testSetMList!!) {
            loggE.addAll(ts.getHTMLDebugInfoList())
        }
    }
    val loge = mutableListOf<String>()
    loge.add("</body>")
    loge.add("</html>")
    val totalHTML = mutableListOf<String>()
    totalHTML.addAll(logh)
    for (s in logg) {
        totalHTML.add("$s<br>")
    }
    for (s in loggE) {
        totalHTML.add("$s<br>")
    }
    totalHTML.addAll(loge)

    val fd = SimpleDateFormat("yyyy-MM-dd-HH-mm")

    var filename: String
    if (RunAllTests.isOfficial()) {
        filename = "REPORT_${fd.format(startDate)}.html"
    } else {
        filename = "Build_${testVersion}_REPORT_${fd.format(startDate)}.html"
        filename = prepareFilename(filename)
    }
    val file = File(reportPath + filename)

    val writer = FileWriter(file, true)

    for (i in totalHTML.indices) {
        writer.write(totalHTML.elementAt(i) + "\n")
    }
    writer.close()
    println("Results are saved to file: $reportPath$filename")

    println("Sending results to WebDAV server")
    try {
        val sardine = SardineFactory.begin()
        sardine.put("http://test:1@192.168.1.214/webdav/rf-win-test-results/$filename", file, "text/html")
        println("Results are sent successfully")
        //            sendHTMLReportLinkToEmail(logg,filename,"agolov@siber.com","evtitova@siber.com","dendav@siber.com");
        sendHTMLReportLinkToEmail(logg, filename)
    } catch (e: Exception) {
        println("Result sending error:" + e.message)
    }

}

private fun sendHTMLReportLinkToEmail(_reports: List<String>, _reportName: String) {
    val props = Properties()
    props["mail.smtp.starttls.enable"] = "true"
    props["mail.smtp.auth"] = "true"
    props["mail.smtp.host"] = reportEmailSMTPServer
    props["mail.smtp.port"] = reportEmailSMTPPort

    val session = javax.mail.Session.getInstance(props,
            object : javax.mail.Authenticator() {
                override fun getPasswordAuthentication(): javax.mail.PasswordAuthentication {
                    return PasswordAuthentication(reportEmailFromName, reportEmailFromPassword)
                }
            })

    if (emailList == null) throw Exception("emailList is null")
    emailList = mutableListOf("ponushenko@siber.com")
    try {
        println("Sending results via email")
        val message = MimeMessage(session)
        message.setFrom(InternetAddress(reportEmailFromName))
        val addresses = arrayOfNulls<javax.mail.Address>(emailList!!.size)
        for (i in emailList!!.indices) {
            addresses[i] = InternetAddress(emailList!![i])
        }

        message.setRecipients(Message.RecipientType.TO, addresses)
        message.subject = "Test report: $startDate (Kotlin test engine)"

//        if (RunAllTests.isOfficial()) {
//            message.setText("Result: $nPassed of $nTotal PASSED; more info at: http://test:1@192.168.1.214/rf-win-test-results/$_reportName")
//        } else {
//            message.setText("Build: $testVersion Result: $nPassed of $nTotal PASSED; more info at: http://test:1@192.168.1.214/rf-win-test-results/$_reportName")
//        }

        val lis = mutableListOf(when (RunAllTests.isOfficial()){
            true -> "Result: $nPassed of $nTotal PASSED; more info at: http://test:1@192.168.1.214/rf-win-test-results/$_reportName"
            else -> "Build: $testVersion Result: $nPassed of $nTotal PASSED; more info at: http://test:1@192.168.1.214/rf-win-test-results/$_reportName"
        })
//        if (RunAllTests.isOfficial()) {
//            message.setText("Result: $nPassed of $nTotal PASSED; more info at: http://test:1@192.168.1.214/rf-win-test-results/$_reportName")
//        } else {
//            message.setText("Build: $testVersion Result: $nPassed of $nTotal PASSED; more info at: http://test:1@192.168.1.214/rf-win-test-results/$_reportName")
//        }

        lis.addAll(_reports)
        val liss = mutableListOf<String>()
        for (t in lis){
            liss.add("$t\n")
        }
        message.setContent(liss.toString(),"text/plain")

        Transport.send(message)
        println("Results are sent successfully")
    } catch (e: Exception) {
        e.printStackTrace()
    }

}

