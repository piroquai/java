package testLogger;

/* INSTALL Sardine (e.g. adding library from Maven: com.github.lookfirst:sardine:5.8)*/
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import com.sun.xml.internal.fastinfoset.util.CharArray;
import jnaex.Run.RunAllTests;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import java.util.Vector;

import static jnaex.RFWin.Proc.logW;

/**
 * Created by Autotester on 10/11/2017.
 */
public class TestLogger {
    private static String logPath = "Log/";
    private static String reportPath = "report/";

    private static Vector<TestSet> testSetVector;
    private static Date startDate;
    private static Date endDate;
    private static long totalTimeElapsed;
    private static int nPassed;
    private static int nFailed;
    private static int nTotal;
    private static String testVersion;

    //email reports:
    private static String reportEmailFromName = "rfwintest@yahoo.com";
    public static void setReportEmailFromName(String s){
        reportEmailFromName = s;
    }

    private static String reportEmailFromPassword = "dtR_Njh3";
    public static void setReportEmailFromPassword(String s){
        reportEmailFromPassword = s;
    }

    private static String reportEmailSMTPServer = "smtp.mail.yahoo.com";
    public static void setReportEmailSMTPServer(String s){
        reportEmailSMTPServer = s;
    }

    private static String reportEmailSMTPPort = "25";
    public static void setReportEmailSMTPPort(String s){
        reportEmailSMTPPort = s;
    }

    private static Vector<String> emailList = new Vector<>();
    private static final String[] emailListDefault = {"agolov@siber.com","dendav@siber.com","evtitova@siber.com"};
    public static void setEmailList(Vector<String> _emails){
        emailList.clear();
        emailList.addAll(_emails);
    }

    private static String pathRFCmd = "C:/Users/Autotester/Desktop/";
    public static void setPathRFCmd(String s){
        pathRFCmd = s;
    }
    public static String getPathRFCmd(){
        return pathRFCmd;
    }
    private static String nameRFCmd = "rf-cmd.exe";
    public static void setNameRFCmd(String s){
        nameRFCmd = s;
    }
    public static String getNameRFCmd(){
        return nameRFCmd;
    }


    public static void startLogging(){
        testSetVector = new Vector<>();
        startDate = new Date();
        totalTimeElapsed = 0;
        nPassed = 0;
        nFailed = 0;
        nTotal = 0;
        if (emailList.isEmpty()){
            for (String s : emailListDefault){
                emailList.add(s);
            }
        }
        if (!RunAllTests.isOfficial()){
            testVersion = getTestVersion();
        }
    }
    public static String getTestVersion(){
        try {
            //run console
            ProcessBuilder pb = new ProcessBuilder(pathRFCmd + nameRFCmd, "-v");
            BufferedReader reader = new BufferedReader(new InputStreamReader(pb.start().getInputStream()));
            return reader.readLine();
        } catch (Exception e) {
            logW("Error trying to get version info: " + e.getMessage());
            return null;
        }
    }
    public static void addTestSet(TestSet _testSet){
        testSetVector.add(_testSet);
    }
    public static void stopLogging(){
        endDate = new Date();
    }
    public static String prepareFilename(String s){
        String q;
        char[] qw = s.toCharArray();
        int i = 0;
        for (Character w : qw){
            if (w.equals(' ')) {
                qw[i] = '_';
            }
            if (w.equals(':')) {
                qw[i] = '_';
            }
            i++;
        }

        q = String.valueOf(qw);
        return q;
    }
    public static void writeLogs() throws Exception{
        Vector<String> logg = new Vector<>();
        logg.add("Test summary for tests started from " + startDate.toString());
        logg.add("=================================================");

        for (TestSet ts : testSetVector) {
            nPassed += ts.getNSuccessful();
            nFailed += ts.getNFailed();
            nTotal += ts.getTotal();
            totalTimeElapsed += ts.getTotalTime();
            logg.addAll(ts.getResults());
        }
        logg.add("=================================================");
        logg.add("Total passed: " + nPassed);
        logg.add("Total failed: " + nFailed);
        logg.add("Total tests: " + nTotal);
        logg.add("Total time elapsed: " + TestSet.getTimeString(totalTimeElapsed));
        logg.add("=================================================");
        if (nFailed > 0) {
            logg.add("==============LOGS FOR FAILED TESTS==============");
            for (TestSet ts : testSetVector) {
                logg.addAll(ts.getDebugInfoVector());
            }
        }
        SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd-HH-mm");

        String filename = "REPORT_" + fd.format(startDate) + "_tests.txt";
        File file = new File(logPath + filename);

        FileWriter writer = new FileWriter(file, true);

        for (int i = 0; i < logg.size(); ++i) {
            writer.write(logg.elementAt(i) + "\n");
        }
        writer.close();
        System.out.println("Results are saved to file: " + logPath + filename);
    }
    public static void writeLogsHTML() throws Exception{
        Vector<String> logh = new Vector<>();
        logh.add("<hmtl>");
        logh.add("<head>");
        logh.add("  <meta charset=\"utf-8\" />");
        if (RunAllTests.isOfficial()){
            logh.add("  <title>Test summary for tests started from " + startDate.toString() + "</title>");
        } else {
            logh.add("  <title>Test summary for tests of " + testVersion + " started from " + startDate.toString() + "</title>");
        }
        logh.add("  <style>");
        logh.add("    .greencolor {");
        logh.add("      color: green;");
        logh.add("    }");
        logh.add("    .orangecolor {");
        logh.add("      color: orange;");
        logh.add("    }");
        logh.add("    .redcolor {");
        logh.add("      color: red;");
        logh.add("    }");
        logh.add("  </style>");
        logh.add("</head>");
        logh.add("<body>");
        Vector<String> logg = new Vector<>();
        logg.add("Test summary for tests started from " + startDate.toString());
        if (!RunAllTests.isOfficial()){
            logg.add("Target internal version: " + testVersion);
        }
        logg.add("=================================================");

        for (TestSet ts : testSetVector) {
            nPassed += ts.getNSuccessful();
            nFailed += ts.getNFailed();
            nTotal += ts.getTotal();
            totalTimeElapsed += ts.getTotalTime();
            logg.addAll(ts.getHTMLResults());
        }
        logg.add("=================================================");
        logg.add("Total passed: " + nPassed);
        logg.add("Total failed: " + nFailed);
        logg.add("Total tests: " + nTotal);
        logg.add("Total time for test execution: " + TestSet.getTimeString(totalTimeElapsed));
        logg.add("Total time elapsed: " + TestSet.getTimeString((endDate.getTime() - startDate.getTime()) / 1000));
        logg.add("=================================================");
        Vector<String> loggE = new Vector<>();
        if (nFailed > 0) {
            loggE.add("==============LOGS FOR FAILED TESTS==============");
            for (TestSet ts : testSetVector) {
                loggE.addAll(ts.getHTMLDebugInfoVector());
            }
        }
        Vector<String> loge = new Vector<>();
        loge.add("</body>");
        loge.add("</html>");
        Vector<String> totalHTML = new Vector<>();
        totalHTML.addAll(logh);
        for (String s : logg) {
            totalHTML.add(s + "<br>");
        }
        for (String s : loggE) {
            totalHTML.add(s + "<br>");
        }
        totalHTML.addAll(loge);

        SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd-HH-mm");

        String filename;
        if (RunAllTests.isOfficial()){
            filename = "REPORT_" + fd.format(startDate) + ".html";
        } else {
            filename = "Build_" + testVersion + "_REPORT_" + fd.format(startDate) + ".html";
            filename = prepareFilename(filename);
        }
        File file = new File(reportPath + filename);

        FileWriter writer = new FileWriter(file, true);

        for (int i = 0; i < totalHTML.size(); ++i) {
            writer.write(totalHTML.elementAt(i) + "\n");
        }
        writer.close();
        System.out.println("Results are saved to file: " + reportPath + filename);

        System.out.println("Sending results to WebDAV server");
        try{
            Sardine sardine  = SardineFactory.begin();
            sardine.put("http://test:1@192.168.1.214/webdav/rf-win-test-results/" + filename,file,"text/html");
            System.out.println("Results are sent successfully");
//            sendHTMLReportLinkToEmail(logg,filename,"agolov@siber.com","evtitova@siber.com","dendav@siber.com");
            sendHTMLReportLinkToEmail(logg,filename);
        } catch (Exception e){
            System.out.println("Result sending error:" + e.getMessage());
        }

    }
    private static void sendHTMLReportLinkToEmail(Vector<String> _reports, String _reportName){
//        private static void sendHTMLReportLinkToEmail(Vector<String> _reports, String _reportName, String..._emails){
        //String to = _emails[0];
//        String from = "p-ray@yandex.ru";
//        String host = "smtp.yandex.ru";
//        final String username = "rfwintest@gmail.com";
//        final String username = "rfwintest@yahoo.com";
//        final String password = "dtR_Njh3";
//        Properties properties = System.getProperties();
//        properties.setProperty("mail.user","p-ray");
//        properties.setProperty("mail.password","YTH_5gf5_YTH_5gf5_");
//        properties.setProperty("mail.smtp.host", host);
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.auth","true");
//        props.put("mail.smtp.host","smtp.gmail.com");
//        props.put("mail.smtp.port","587");
//        props.put("mail.smtp.host","smtp.mail.yahoo.com");
        props.put("mail.smtp.host",reportEmailSMTPServer);
//        props.put("mail.smtp.port","25");
        props.put("mail.smtp.port",reportEmailSMTPPort);

        //Session session = Session.getDefaultInstance(properties);
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(reportEmailFromName,reportEmailFromPassword);
                }
                });

        try{
            System.out.println("Sending results via email");
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(reportEmailFromName));
//            Address[] addresses = new Address[_emails.length];
//            for (int i = 0; i < _emails.length; i++) {
//                addresses[i] = new InternetAddress(_emails[i]);
//            }
            Address[] addresses = new Address[emailList.size()];
            for (int i = 0; i < emailList.size(); i++) {
                addresses[i] = new InternetAddress(emailList.get(i));
            }

            //message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setRecipients(Message.RecipientType.TO,addresses);
            message.setSubject("Test report: " + startDate.toString());

            if (RunAllTests.isOfficial()){
                message.setText("Result: " + nPassed + " of " + nTotal + " PASSED; more info at: http://test:1@192.168.1.214/rf-win-test-results/" + _reportName);
            } else {
                message.setText("Build: " + testVersion + " Result: " + nPassed + " of " + nTotal + " PASSED; more info at: http://test:1@192.168.1.214/rf-win-test-results/" + _reportName);
            }


            Transport.send(message);
            System.out.println("Results are sent successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
