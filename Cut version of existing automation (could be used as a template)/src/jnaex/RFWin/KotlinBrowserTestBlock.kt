package jnaex.RFWin

import jnaex.RFWin.Extension.EdgeTestContext
import jnaex.RFWin.Extension.Kotlin.performLogin
import jnaex.RFWin.Extension.Kotlin.prepareBrowser
import jnaex.RFWin.Extension.Kotlin.verifyPage
import jnaex.RFWin.Extension.TestContext
import jnaex.RFWin.Kotlin.Enums.Browsers
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

/**
 * Created by Autotester on 10/9/2018.
 */
fun <T : TestContext> performLoginTest(_browser : Browsers,_testContext : T) : Boolean{
    Proc.setLogBlockPrefix("Prepare browser")
    when (_browser){
        Browsers.EDGE -> prepareBrowser()
        else -> throw Exception("Incorrect browser for Preparation: $_browser")
    }
    Proc.setLogBlockPrefix("Perform Login")
    when (_browser){
        Browsers.EDGE -> performLogin(_testContext as EdgeTestContext)
        else -> throw Exception("Incorrect browser for Login: $_browser")
    }
    Proc.setLogBlockPrefix("Verification")
    return when (_browser){
        Browsers.EDGE -> verifyPage(_testContext as EdgeTestContext)
        else -> throw Exception("Incorrect browser for Verification: $_browser")
    }
}

private val nonStrictNameMarker = '<'
private val operatorBraces = '>'
private val strictURLMarker = '!'
private val nameOpenMarker = '{'
private val nameCloseMarker = '}'
private val ltnameOpenMarker = '['
private val ltnameCloseMarker = ']'
private val preActionRequiredMarker = '1'
private val preTwoActionsRequiredMarker = '2'
private val deepSearchMarker = '~'
private val separator = ';'

/**
 * Parsing testData file to fill up the vector of LSURLP objects.
 * syntax of testData which is processing is as follows:
 * %Login_name;%Successful_URL;%Marker_SP1;%Marker_SP2;...;Marker_SPN
 * where ; is separator (relies on the expression above) and
 * where %Marker_SPx can contain sequences:
 * - {%name}
 * - [%localized_type_name]
 * - ~ - perform deep search
 * conditions above can be mixed but please write them once since extra occurrences would be ignored
 * example:
 * yahoo;http://yahoo.com;{Enter UserID};~{RoboForm}[window]
 *
 * new:
 * ;<name>;<successfulURL>;{name strict}[ltname]~notDeep<name not strict ;;URL: ! - non-strict, 1 - need additional action (the first ;{}[] after the URL) ;;2 - need two additional actions> - nested SPs
 * ;; e.g. dummy;2https://dummy.org/;{Log In}[link];>{Log In}[list item];{Log In}[link]>;{Here I am}[text]
 * ;; clear cookies and restart browser (Edge) after each test
 *
 * new:
 * ;<id_int_unique>;<name>;<successfulURL>;{name strict}[ltname]~notDeep<name not strict ;;URL: ! - non-strict, 1 - need additional action (the first ;{}[] after the URL) ;;2 - need two additional actions> - nested SPs
 *
 * @param _filename testData file
 * @return vector of LSURLP object (so will be prepared for test)
 * @throws Exception if something goes wrong
</name></successfulURL></name></id_int_unique></name></successfulURL></name> */
fun getTestListEdge(_filename: String): List<EdgeTestContext> {
    Proc.lgP = "getTestList"
    val fstream = FileInputStream(_filename)
    val br = BufferedReader(InputStreamReader(fstream))

    var testContext: String
    val res = mutableListOf<EdgeTestContext>()

    testContext = br.readLine()
    while (testContext.isNotEmpty()) {
        //ignore unicode character is present
        if (testContext[0] == '\uFEFF') {
            testContext = Proc.getStringToTheRightOfTheChar(testContext, '\uFEFF')
        }
        if (testContext[0] != separator) {
            val testID = Integer.parseInt(Proc.getStringToTheLeftOfTheChar(testContext, separator))
            testContext = Proc.getStringToTheRightOfTheChar(testContext, separator)
            val res1 = Proc.getStringToTheLeftOfTheChar(testContext, separator)
            val res2: String
            var strictURL = true
            var resM = mutableListOf<SP>()
            var wrk = Proc.getStringToTheRightOfTheChar(testContext, separator)
            //                boolean hasMarkers = false;
            var preActionRequired = false
            val preActionItemToClick = mutableListOf<SP>()
            var preActionRequired2 = false
            val preActionItemToClick2 = mutableListOf<SP>()

            //                boolean hasPreActions = false;

            if (wrk[0] == strictURLMarker) {
                strictURL = false
                wrk = Proc.getStringToTheRightOfTheChar(wrk, strictURLMarker)
            }
            if (wrk[0] == preActionRequiredMarker) {
                preActionRequired = true
                wrk = Proc.getStringToTheRightOfTheChar(wrk, preActionRequiredMarker)
            }
            if (wrk[0] == preTwoActionsRequiredMarker) {
                preActionRequired = true
                preActionRequired2 = true
                wrk = Proc.getStringToTheRightOfTheChar(wrk, preTwoActionsRequiredMarker)
            }
            if (wrk.contains("" + separator)) {
                res2 = Proc.getStringToTheLeftOfTheChar(wrk, separator)
                wrk = Proc.getStringToTheRightOfTheChar(wrk, separator)
            } else {
                res2 = wrk
            }
            //split actions
            if (preActionRequired || preActionRequired2) {
                if (wrk[0] == operatorBraces) {
                    var tempStr = Proc.getStringToTheLeftOfTheChar(Proc.getStringToTheRightOfTheChar(wrk, operatorBraces), operatorBraces)
                    if (wrk.contains("" + separator)) {
                        wrk = Proc.getStringToTheRightOfTheChar(Proc.getStringToTheRightOfTheChar(Proc.getStringToTheRightOfTheChar(wrk, operatorBraces), operatorBraces), separator)
                    } else {
                        wrk = ""
                    }
                    while (tempStr.contains("" + separator)) {
                        tempStr = Proc.getStringToTheRightOfTheChar(tempStr, separator)
                    }
                } else {
                    if (wrk.contains("" + separator)) {
                        wrk = Proc.getStringToTheRightOfTheChar(wrk, separator)
                    } else {
                    }
                }
                if (preActionRequired2) {
                    if (wrk[0] == operatorBraces) {
                        var tempStr = Proc.getStringToTheLeftOfTheChar(Proc.getStringToTheRightOfTheChar(wrk, operatorBraces), operatorBraces)
                        if (wrk.contains("" + separator)) {
                            wrk = Proc.getStringToTheRightOfTheChar(Proc.getStringToTheRightOfTheChar(Proc.getStringToTheRightOfTheChar(wrk, operatorBraces), operatorBraces), separator)
                        } else {
                            wrk = ""
                        }
                        while (tempStr.contains("" + separator)) {
                            tempStr = Proc.getStringToTheRightOfTheChar(tempStr, separator)
                        }
                    } else {
                        if (wrk.contains("" + separator)) {
                            wrk = Proc.getStringToTheRightOfTheChar(wrk, separator)
                        } else {
                        }
                    }
                }
            }

            if (wrk.contains("" + separator)) {
                do {
                    var tmp = wrk
                    if (tmp.contains("" + separator)) {
                        tmp = Proc.getStringToTheLeftOfTheChar(tmp, separator)
                    }
                    wrk = Proc.getStringToTheRightOfTheChar(wrk, separator)
                } while (wrk.contains("" + separator))

            }
            if (resM.isEmpty()) {
                resM = mutableListOf()
            }
            res.add(EdgeTestContext(testID, res1, res2, strictURL, resM, preActionRequired, preActionItemToClick, preActionRequired2, preActionItemToClick2))
        }
        try{
            testContext = br.readLine()
        } catch (e : Exception){
            testContext = ""
        }
    }
    return res
}
