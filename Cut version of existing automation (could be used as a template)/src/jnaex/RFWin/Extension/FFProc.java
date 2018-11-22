package jnaex.RFWin.Extension;

import daima.DElement;
import jnaex.RFWin.*;

/**
 * Created by Autotester on 11/1/2017.
 */
public class FFProc {
    private static DElement gL(DElement _parent, String _descr, int _attempts, String _XattrList, String..._Xattrs) throws Exception{
        return Proc.gL(_parent,_descr,_attempts,_XattrList,_Xattrs);
    }
//    private static void setGP(String globalPrefix){
//        Proc.setGLP(globalPrefix);
//    }
    private static void log(String logString){
        Proc.log(logString);
    }
    private static void logW(String warnLogString){
        Proc.logW(warnLogString);
    }
    private static void logE(String errLogString){
        Proc.logE(errLogString);
    }
    private static void setLP(String localPrefix){
        Proc.lgP = localPrefix;
    }
    private static void sleepy(double sec){
        Proc.sleepy(5);
    }
    public static void launchFFBrowser() throws Exception{
        Proc.setLogBlockPrefix("launchFFBrowser");
        log("Launching Firefox");
        sleepy(1);
        sleepy(5);
    }
    public static void removeRFFFAddOnIfPresent() throws Exception{
        int success = 0; // 0 - no RF present, 1 - OK, 2 - failed
        Proc.setLogBlockPrefix("removeRFFFAddOnIfPresent");
        setLP("RemoveRFFF");
        log("Beginning procedure");
        launchFFBrowser();
        Proc.setLogBlockPrefix("removeRFFFAddOnIfPresent-II");
        log("Locating browser");
        DElement ff = gL(null,"FF window",4,"Nl","Mozilla Firefox","window");

        ff.setForeground();
        log("Decadance has begun :-)");
        DElement ffMenu = DElement.gimMeP(ff,"FF Menu",1,"n","Navigation Toolbar");
        DElement wrk = DElement.gimMeP(ffMenu,"FF button",1,"n","Firefox");
        wrk.click();
        sleepy(2);
        ffMenu = DElement.gimMeP(ff,"Group",1,"l","group");
        wrk = DElement.gimMeP(ffMenu,"Add-ons",1,"n","Add-ons");
        wrk.click();

        log("Go to Add-ons tab");
        DElement tgtTab;
        try{
            wrk = gL(ff, "Group",1,"l","group");
            wrk = DElement.gimMePN(wrk,"Second empty name",1,1,"n","");
            wrk = gL(wrk,"First empty #2",1,"n","");
            tgtTab = gL(wrk,"Add-ons Manager window",1,"nl","Add-ons Manager","window");
        } catch (Exception e) {
            wrk = DElement.gimMePN(ff, "Group",1, 1,"l","group");
            wrk = DElement.gimMePN(wrk,"Second empty name",1,1,"n","");
            wrk = gL(wrk,"First empty #2",1,"n","");
            tgtTab = gL(wrk,"Add-ons Manager window",1,"nl","Add-ons Manager","window");
        }

        log("Set Extensions list");
        wrk = gL(tgtTab,"Nav List",1,"l","list");
        wrk = gL(wrk, "Nav Ext LI",1,"n","Extensions");
        wrk = gL(wrk,"Ext text",1,"n","Extensions");
        wrk = gL(wrk,"Ext edit",1,"n","Extensions");
        wrk.click();
        sleepy(2);

        log("Locate RF and disable if present");
        wrk = DElement.gimMePN(tgtTab,"Nav List II", 1, 1, "l", "list");
        try {
            log("Check whether RF is present");
            wrk = gL(wrk,"RF link",1,"N","RoboForm Password");
            log("RF is present, continue");
            success = 1;
        } catch (Exception e) {
            log("No RF present");
        }
        if (success > 0) {
            try{
                log("Trying to remove: locating Remove button");
                wrk = gL(wrk,"Remove btn",1,"n","Remove");
                log("Remove button found");
            } catch (Exception e) {
                logW("Remove button not found; locating Disable button");
                try {
                    wrk = gL(wrk, "Disable btn", 1, "n", "Disable");
                    log("Disable button found");
                } catch (Exception e1){
                    logE("Neither Remove, nor Disable button have found: " + e1.getMessage());
                    success = 2;
                }
            }
            if (success == 1){
                log("Pressing the button");
                wrk.click();
            }

        }
        log("Closing FF browser");
        Proc.setLogBlockPrefix("removeRFFFAddOnIfPresent-III");
        sleepy(1);
        if (success == 0) {
            log("Result: No FF was installed");
        } else {
            if (success == 1) {
                log("Result: FF was found and removed or disabled");
            } else {
                logW("Result: FF was found but was not removed or disabled");
            }
        }
        log("Procedure complete");

    }

    /**
     * check whether post-install RF for FF add-on installation window appears or not.
     * @return true if displayed, false if not
     */
    public static boolean isFFInstallInfoDisplayed(){
        boolean isDisplayed = false;
        Proc.setLogBlockPrefix("isFFInstallInfoDisplayed");
        setLP("isFFInstallInfoDisplayed");
        try{
            Proc.setLogBlockPrefix("isFFInstallInfoDisplayed-II");
            isDisplayed = true;
            log("FF add-on installation notification is displayed");
        } catch (Exception e) {
            log("FF add-on installation notification is not displayed");
        }
        return isDisplayed;
    }
    public static void navigateToRFBrAppOptions() throws Exception{
        Proc.setLogBlockPrefix("navigateToRFBrAppOptions");
        setLP("NavigateToRFBrAppOptions");
        log("Starting procedure");
        Proc.setLogBlockPrefix("navigateToRFBrAppOptions-II");
        sleepy(2);
        log("Going to Options");
        DElement eW = gL(null,"Editor window",1,"Nl","RoboForm Editor","window");
        DElement wrk = gL(eW,"Grand RFB",1,"n","RoboForm");
        wrk.click();
        sleepy(2);
        Proc.clickItemFromContextMenu("Options...");
        sleepy(2);
        log("Going to Browsers and Apps");
        DElement opt = gL(null, "RF Options", 1, "n", "RoboForm Options");
        DElement elList = DElement.gimMePN(opt,"Category list",1,1,"l","custom");
        wrk = gL(elList,"Browsers and Apps",1,"N","Browsers");
        wrk.click();
        sleepy(1);

        log("Procedure complete");
    }
    public static void clickRFOptFFExtInstButton(boolean _install) throws Exception{
        Proc.setLogBlockPrefix("clickRFOptFFExtInstButton");
        String state = "Uninstall";
        if (_install) {
            state = "Install";
        }
        setLP("ClickRFOptFFExtInstButton[InitState=" + state + "]");
        log("Starting procedure");
        DElement opt = gL(null, "RF Options", 1, "n", "RoboForm Options");
        DElement elList = DElement.gimMePN(opt,"Right pane list",1,2,"l","custom");
        DElement wrk = gL(elList,"Mozilla button",1,"c","Button");

        if (!(wrk.getName().equals(state))) {
            throw new Exception("Unexpected state: Expected: " + state + " Actual: " + wrk.getName());
        }
        log("Clicking " + state);
        wrk.click();

        log("Procedure complete");
    }
    public static void checkThatFFExtensionIsInstalledFF() throws Exception{
        Proc.setLogBlockPrefix("checkThatFFExtensionIsInstalledFF");
        log("Check FF side");
        launchFFBrowser();
        Proc.setLogBlockPrefix("checkThatFFExtensionIsInstalledFF-II");
        log("Locating browser");
        DElement ff = gL(null,"FF window",4,"Nl","Mozilla Firefox","window");
        DElement wrk = gL(ff,"Nav toolbar",1,"nl","Navigation Toolbar","tool bar");
        gL(wrk,"RF add-on",1,"lN","button","RoboForm");
        log("RF add-on is installed to FF");
    }
    public static void checkThatFFExtensionIsInstalledRF() throws Exception{
        Proc.setLogBlockPrefix("checkThatFFExtensionIsInstalledRF");
        log("Check RF side");
        Proc.setLogBlockPrefix("checkThatFFExtensionIsInstalledRF-II");
        navigateToRFBrAppOptions();
        Proc.setLogBlockPrefix("checkThatFFExtensionIsInstalledRF-III");
        setLP("CheckFFAddOnInstalled");
        DElement opt = gL(null, "RF Options", 1, "n", "RoboForm Options");
        DElement elList = DElement.gimMePN(opt,"Right pane list",1,2,"l","custom");
        DElement wrk = gL(elList,"Mozilla button",1,"c","Button");
        if (wrk.getName().equals("Install")){
            throw new Exception("FF add-on is not installed on RF side");
        }
        log("FF add-on is installed on RF side");
    }
    public static void checkThatFFExtensionIsInstalled() throws Exception{
        Proc.setLogBlockPrefix("checkThatFFExtensionIsInstalled");
        setLP("CheckFFAddOnInstalled");
        log("Starting procedure");
        checkThatFFExtensionIsInstalledFF();
        Proc.setLogBlockPrefix("checkThatFFExtensionIsInstalled-II");
        checkThatFFExtensionIsInstalledRF();
        Proc.setLogBlockPrefix("checkThatFFExtensionIsInstalled-III");
        log("Procedure complete");
    }
}
