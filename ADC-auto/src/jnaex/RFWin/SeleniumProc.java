package jnaex.RFWin;

import jnaex.RFWin.ElemD;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.Vector;

/**
 * Created by Autotester on 10/2/2017.
 */
public class SeleniumProc {
    /**
     * <p>Grand experiment to make some kind of universal taker and handler with different parameters</p>
     * <p>Parameters are:</p>
     * <p>o - IsOffscreen</p>
     * <p>e - IsEnabled</p>
     * <p>k - HasKeyboardFocus</p>
     * <p>Also specifying 'd' in _paramT section will make it feel in versus way: element SHOULD NOT be found for success</p>
     * <p>Specifying slash (/) in _paramT section will make xpath begin with // (to find more elements)</p>
     * <p>Specifying 'x' in _paramT section will use strict By.xpath search without trying to use other locators</p>
     * <p>Another exclude flags:</p>
     * <p>n - exclude name</p>
     * <p>c - exclude classname</p>
     * <p>a - exclude automationID</p>
     * <p>This procedure looks for Name/ClassName/AutomationId by default and has attempts counter</p>
     * @param _webElement parent WebElement too look at
     * @param _elemD target element description to look for
     * @param _name name of the element to desribe it in logs
     * @param _attempts number of attempts
     * @param _paramT parameters which are checked in TRUE state
     * @param _paramF parameters which are checked in FALSE state
     * @return returns WebElement or null (in case of 'd'). Will throw exception on fault
     * @throws Exception that describes that element was not found using given parameters
     */
    public static WebElement gimMeP(SearchContext _webElement, ElemD _elemD, String _name, int _attempts, String _paramT, String _paramF) throws Exception{
        Proc.lgP = "gimMeP";
        log("Locating " + _name);
        WebElement res;

        String xpathStart = "*[";
        String xpathEnd = "]";
        int isOffscreenF = 0; //oO
        int isEnabledF = 0; //eE
        int hasKBFocusF = 0; //kK
        Boolean removeName = false;
        Boolean removeCName = false;
        Boolean removeAID = false;
        Boolean changeXPS = false; //change xpath starting point
        Boolean forceXPathUsage = false;

        if(_paramT.toUpperCase().contains("X")){
            forceXPathUsage = true;
        }
        if(_paramT.toUpperCase().contains("/")){
            changeXPS = true;
        }
        if (_paramF.toUpperCase().contains("N")){
            removeName = true;
        }
        if (_paramF.toUpperCase().contains("C")){
            removeCName = true;
        }
        if (_paramF.toUpperCase().contains("A")){
            removeAID = true;
        }
        if (_paramT.toUpperCase().contains("O")){
            isOffscreenF = 1;
        }
        if (_paramT.toUpperCase().contains("E")){
            isEnabledF = 1;
        }
        if (_paramT.toUpperCase().contains("K")){
            hasKBFocusF = 1;
        }
        if (_paramF.toUpperCase().contains("O")){
            if (isOffscreenF == 1){
                cl();
                throw new Exception("IsOffscreen flag was specified twice");
            }
            isOffscreenF = 2;
        }
        if (_paramF.toUpperCase().contains("E")){
            if (isEnabledF == 1){
                cl();
                throw new Exception("IsEnabled flag was specified twice");
            }
            isEnabledF = 2;
        }
        if (_paramF.toUpperCase().contains("K")){
            if (hasKBFocusF == 1){
                cl();
                throw new Exception("HasKeyboardFocus flag was specified twice");
            }
            hasKBFocusF = 2;
        }

        //what we should flags; 0 - initial (do nothing), 1 - true, 2 - false
        Boolean shouldDisappearF = false; //dD

        //setting flags:
        if (_paramT.toUpperCase().contains("D")){
            shouldDisappearF = true;
        }

        Boolean useXPath = false;
        if (forceXPathUsage){
            useXPath = true;
        }
        if (!useXPath){
            if ((isEnabledF > 0) || (isOffscreenF > 0) || (hasKBFocusF > 0) || (!removeAID) ||
                    ((removeCName)==(removeName))){
                useXPath = true;
            }
        }

        By hh = null;
        if (useXPath){
            if (changeXPS){
                xpathStart = "//*[";
            }
            String xpathTotal = xpathStart +
                    parseXPath(_elemD, removeName,removeCName,removeAID,isOffscreenF,isEnabledF,hasKBFocusF) + xpathEnd;
            log("Using XPath locator; xpathTotal: " + xpathTotal);
            hh = By.xpath(xpathTotal);
        }
        else
        {
            if (removeCName){
                log("Using find by name: "+_elemD.name);
                hh = By.name(_elemD.name);
            }
            if (removeName) {
                log("Using find by classname: "+_elemD.cName);
                hh = By.className(_elemD.cName);
            }
            if (hh == null){
                cl();
                throw new Exception ("By locator type was not defined properly");
            }
        }


        int i = 0;
        Boolean notFnd;

        //looking
        do{
            i++;
            log("Attempt #" + i + " of " + _attempts);
            try{
                res = _webElement.findElement(hh);
                notFnd = false;
                if (shouldDisappearF){
                    sleepy(5);
                }
            }
            catch (Exception e){
                res = null;
                notFnd = true;
            }
        }
        while ((notFnd != shouldDisappearF)&&(i < _attempts));
        if ((res == null) != (shouldDisappearF)){
            cl();
            throw new Exception("Incorrect behavior: Actual element appearance is: " + shouldDisappearF.toString());
        }
        if (shouldDisappearF){
            log(_name + " cannot be not found: success");
        }
        else
        {
            log(_name + " was found successfully");
        }
        cl();
        return res;
    }
    private static void cl(){
        Proc.lgP = "";
    }

    public static void log(String s){ //normal log
        Proc.log(s);
    }
    public static void logE(String s){ //error log
        Proc.logE(s);
    }
    public static void logW(String s){ //warning log
        Proc.logW(s);
    }

    public static void sleepy(double s){  //in seconds
        Proc.lgP = "Sleeping";
        double multiplierFastMachine = 0.9;
        try {
            log("Waiting for " + s * multiplierFastMachine + " seconds.");
            Thread.sleep(Math.round(s * multiplierFastMachine * 1000));                 //milliseconds
        } catch(InterruptedException ex) {
            logE("Interrupted exception appeared");
            Thread.currentThread().interrupt();
        }
        cl();
    }
    private static String parseXPath(ElemD _elemD, Boolean _removeName, Boolean _removeCName, Boolean _removeAID,
                                     int _isOffscreenF, int _isEnabledF, int _hasKBFocusF) throws Exception{

        String xpathArgs = "";
        Boolean wasItems = false;

        if (!_removeName){
            xpathArgs += "(@Name='" + _elemD.name + "')";
            wasItems = true;
        }
        if (!_removeCName){
            if (wasItems){
                xpathArgs += " and ";
            }
            xpathArgs += "(@ClassName='" + _elemD.cName + "')";
            wasItems = true;
        }
        if (!_removeAID){
            if (wasItems){
                xpathArgs += " and ";
            }
            xpathArgs += "(@AutomationId='" + _elemD.autId + "')";
            wasItems = true;
        }
        if (_isOffscreenF > 0){
            if (wasItems){
                xpathArgs += " and ";
            }
            xpathArgs += "(@IsOffscreen='";
            if (_isOffscreenF == 1){
                xpathArgs += "True";
            }
            else
            {
                xpathArgs += "False";
            }
            xpathArgs += "')";
            wasItems = true;
        }
        if (_isEnabledF > 0){
            if (wasItems){
                xpathArgs += " and ";
            }
            xpathArgs += "(@IsEnabled='";
            if (_isEnabledF == 1){
                xpathArgs += "True";
            }
            else
            {
                xpathArgs += "False";
            }
            xpathArgs += "')";
            wasItems = true;
        }
        if (_hasKBFocusF > 0){
            if (wasItems){
                xpathArgs += " and ";
            }
            xpathArgs += "(@HasKeyboardFocus='";
            if (_hasKBFocusF == 1){
                xpathArgs += "True";
            }
            else
            {
                xpathArgs += "False";
            }
            xpathArgs += "')";
        }

        return xpathArgs;
    }

}
