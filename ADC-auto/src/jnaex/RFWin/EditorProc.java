package jnaex.RFWin;

import daima.KeyboardHandler;
import daima.MouseHandler;
import daima.WindowHandler;
import jnaex.RFWin.Editor.*;
import jnaex.RFWin.Editor.RF7.EditorRF7MW;
import jnaex.RFWin.SearchPresets.MSWindowsGeneral;
import jnaex.RFWin.SearchPresets.RF;
import jnaex.RFWin.SearchPresets.RF7;
import jnaex.RFWin.SyncWindow.SyncD;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import daima.DElement;
import jnaex.RFWin.User.RFUser;

/**
 * Created by Evgenia on 19-Jun-17.
 *
 */
public class EditorProc extends Proc{

    /**
     * Default name for safenote to test existence
     */
    public static String nameSNDefault = "nameSN";


    /**
     * Default path for RF7 Editor
     */
    private static String pathRF7Editor = "C:/Program Files (x86)/Siber Systems/AI RoboForm/";

    public static void setPathRF7Editor(String s){
        pathRF7Editor = s;
    }
    /**
     * Default name for RF7 Editor
     */
    private static String nameRF7Editor = "identities.exe";
    /**
     * Default path for RF8 Editor
     */
    private static String pathRF8Editor = "C:/Program Files (x86)/Siber Systems/AI RoboForm/";
    public static void setPathRF8Editor(String s){
        pathRF8Editor = s;
    }
    /**
     * Default name for RF8 Editor
     */
    private static String nameRF8Editor = "identities.exe";
    private static String nameRF8TBI = "robotaskbaricon.exe";
    /**
     * RF application shell (used for Installer)
     */
    static Process shell;
    /**
     * Run specified RF7 Editor
     */
    static void runRF7Editor() throws IOException {
        setLogBlockPrefix("runRF7Editor");
        ProcessBuilder pb = new ProcessBuilder(pathRF7Editor + nameRF7Editor);
        shell = pb.start();

        try{
            DElement eW = gL(null, RF7.editorW,2);
            eW.setForeground();
        } catch (Exception e) {
            logE("RF7 Editor window was not found");
        }
    }
    /**
     * Run specified RF8 Editor
     */
    public static void runRF8Editor() throws IOException{
        log("Checking whether editor windows are opened or not");
        setLogBlockPrefix("runRF8Editor");
        try{
            DElement g = gL(null,"Editor window",1,"c",EditorD.editorW.cName);
            g.setFocus();
            g.setForeground();
            log("Editor window was found, exiting");
            closeRF8EditorSoft();
            setLogBlockPrefix("runRF8Editor-II");
            sleepy(3);
        }catch (Exception e){
            log("Going further");
        }
        ProcessBuilder pb = new ProcessBuilder(pathRF8Editor + nameRF8Editor);
        shell = pb.start();
        log("Checking whether extra editor windows are opened or not");
        try{
            DElement g = DElement.gimMePN(null,"Editor window",1,1,"c",EditorD.editorW.cName);
            g.setFocus();
            g.setForeground();
            log("Editor window was found, exiting");
            closeRF8EditorSoft();
            sleepy(3);
        }catch (Exception e){
            log("Going further");
        }
    }
    private static String logString = "EditorProc";
    private static String logPrefixBlock = "EdProc" + Proc.logSeparator;
    public static void log(String s){ //normal log
        Proc.log(logPrefixBlock + logString + Proc.logSeparator + s);
    }
    public static void logE(String s){ //error log
        Proc.logE(logPrefixBlock + logString + Proc.logSeparator + s);
    }
    public static void logW(String s){
        Proc.logW(logPrefixBlock + logString + Proc.logSeparator + s);
    }

//    private static void sleepy(double s){
//        Proc.sleepy(s);
//    }
//    private static DElement gimMe(DElement _dElement, ElemD _elemD, String _name) throws Exception{
//        logW("Deprecated method. Use gL instead");
//        return Proc.gimMe(_dElement,_elemD,_name);
//    }
//    private static DElement gimMeP(DElement _dElement, ElemD _nED, String _name, int _attempts, String _paramT, String _paramF) throws Exception{
//        logW("Deprecated method. Use gL instead");
//        return Proc.gimMeP(_dElement,_nED,_name,_attempts,_paramT,_paramF);
//    }
//    private static DElement gimMeP(DElement _dElement,String _string, String _name, int _attempts, String _paramT, String _paramF) throws Exception{
//        logW("Deprecated method. Use gL instead");
//        return Proc.gimMeP(_dElement,_string,_name,_attempts,_paramT,_paramF);
//    }
//    private static List<DElement> gimMePL(DElement _dElement, ElemD _elemD, String _name, String _paramT, String _paramF) throws Exception{
//        logW("Get off ElemD asap please :-)");
//        return Proc.gimMePL(_dElement,_elemD,_name,_paramT,_paramF);
//    }
//    private static void doubleClick(DElement _webElement) throws Exception{
//        Proc.doubleClick(_webElement);
//    }
//    private static void clickItemFromContextMenu(String _itemName) throws Exception{
//        Proc.clickItemFromContextMenu(_itemName);
//    }
//    private static void offsetClick(DElement _webElement, int _offsetX) throws Exception{
//        Proc.offsetClick(_webElement,_offsetX);
//    }
//    private static void offsetClick(DElement _webElement, int _offsetX, int _offsetY) throws Exception{
//        Proc.offsetClick(_webElement,_offsetX,_offsetY);
//    }
//    private static void offsetClick(DElement _webElement, int _offsetX, String _keys) throws Exception{
//        Proc.offsetClick(_webElement,_offsetX,_keys);
//    }


        /**
        * Close opened RF7 Editor soft way
        */
    static void closeRF7EditorSoft() throws Exception{
        logString = "Close RF7 Editor (soft way)";
        setLogBlockPrefix("closeRF7EditorSoft");
        log("Closing the Editor");
        //DElement ssW = gimMeP(null,EditorRF7MW.defMainW,"Editor window",1,"","na");
        DElement ssW = gL(null, new SP ("RF7 window", "c", "RfEditor"), 2 );

        try {
            //DElement tWrk = gimMeP(ssW,EditorRF7MW.titleBar,"Title bar",1,"","cno");
            DElement tWrk = gL(ssW,"Title bar",1,"l","title bar");
            //DElement wrk = gimMeP(tWrk,EditorRF7MW.btnTBClose,"Close button",1,"","ca");
            DElement wrk = gL(tWrk, new SP ("RF7 window close button", "n", "Close"), 2 );
            sleepy(1);
            wrk.click();
            sleepy(1);
            log("Verifying whether Editor window was closed or not:");
            //gimMeP(null,EditorRF7MW.defMainW,"Editor window",2,"d","na");
            gL(null, new SP ("RF7 window ", "cd", "RfEditor", ""), 2 );

            log("Editor window has been closed successfully");
        }
        catch(Exception e)
        {
            logE("Could not locate title bar and perform evil things");
        }

    }
    public static void clickTBIBrowser(String _window) throws Exception{
        setLogBlockPrefix("clickTBIBrowser");
        try{
            log("Clicking TBI > Browsers >" + _window);
            //DElement rFTBI = gimMeP(null,new ElemD("","#32768"),"Context menu",2,"","na");
            DElement rFTBI = gL(null,MSWindowsGeneral.contextW,2);

//            rFTBI = ApplicationProc.calculateParent(rFTBI,"{}[8]");
            rFTBI = ApplicationProc.calculateParent(rFTBI,"{}[5]");
            rFTBI.click();
            sleepy(1.4);
            //rFTBI = gimMeP(null,"","Submenu",2,"","ca");
            rFTBI = gL(null,MSWindowsGeneral.emptyElement,1);

            /*  //there are texts for windows
            //rFTBI = gimMeP(rFTBI,_window,"Icq item",2,"","ca");
            rFTBI = gL(rFTBI,new SP(_window,"n",_window),2);

            rFTBI.click();
            /*/
            //*  //No texts for windows
            log("Getting all browser strings");
            Vector<DElement> allMenuItems = gVL(rFTBI,MSWindowsGeneral.emptyElement,1);
            int cnt = allMenuItems.size();

            log("There are " + cnt + " browser strings, including inactive first one");
            allMenuItems.get(1).click();

            for (int z = 2; z < cnt; z++){
                log("Perform #"+z+" browser click");
                openTBI();
                setLogBlockPrefix("clickTBIBrowser-II");
                rFTBI = gL(null,MSWindowsGeneral.contextW,2);
                rFTBI = ApplicationProc.calculateParent(rFTBI,"{}[5]");
                rFTBI.click();
                sleepy(0.4);
                rFTBI = gL(null,MSWindowsGeneral.emptyElement,1);
                allMenuItems = gVL(rFTBI,MSWindowsGeneral.emptyElement,1);
                allMenuItems.get(z).click();
            }
            //*/
            sleepy(4);

        } catch (Exception e){
            logW("Exception catcher for debugging purposes");
            throw e;
        }
    }
    /**
     * Looking in taskbar for RF TBI, then clicks on it to evoke context menu
     */
    public static DElement openTBI() throws Exception{
        log("Open TBI");
        setLogBlockPrefix("openTBI");
        DElement wrk;
        try{
            log("Looking if TBI is pinned.");
            DElement rFTBI;
            try{
                rFTBI = ApplicationProc.calculateParent(null,"{}[0]{}[2]{}[0]"); //- for horizontal
            }catch (Exception e){
                rFTBI = ApplicationProc.calculateParent(null,"{}[0]{}[1]{}[0]"); //- for vertical
            }


            //rFTBI = gimMeP(rFTBI,new ElemD("User Promoted Notification Area","ToolbarWindow32"),"Taskbar",1,"","a");
            rFTBI = gL(rFTBI, new SP ("Taskbar", "nc", "User Promoted Notification Area", "ToolbarWindow32"), 2 );

            //rFTBI = gimMeP(rFTBI,"RoboForm: fills forms, manages passwords","TBI",1,"x","ca");
            rFTBI = gL(rFTBI, new SP ("RoboForm: fills forms, manages passwords", "n", "TBI"), 2);
            wrk = rFTBI;
        }catch (Exception e){
            log("Pinned TBI was not found. Looking for concealed TBI.");
            DElement rFTBI;
            //
            rFTBI = gL(null,MSWindowsGeneral.taskbarP,1);
            //

            try{
                //rFTBI = ApplicationProc.calculateParent(null,"{}[1]{}[3]"); //- for horizontal
                rFTBI = ApplicationProc.calculateParent(rFTBI,"{}[2]"); //- for horizontal

                log("Horizontal TBI has been found OK");
            } catch (Exception e1){
                log("Horizontal TBI finding failed. Finding Vertical instead");
//                rFTBI = ApplicationProc.calculateParent(null,"{}[1]{}[2]"); //-for vertical
                rFTBI = ApplicationProc.calculateParent(rFTBI,"{}[1]"); //-for vertical
                log("Vertical TBI has been found");
            }


            rFTBI = gL(rFTBI,"Chevron button",2,"nc", "Notification Chevron", "Button");
            rFTBI.click();
            sleepy(0.5);
            try{
                log("Working with taskbar panel");
                //rFTBI = gimMeP(null,new ElemD("","NotifyIconOverflowWindow"),"Notification Overflow",2,"","na");
                rFTBI = gL(null, new SP("Notification Overflow","c","NotifyIconOverflowWindow"),2);

                log("Working OK");
            }catch (Exception e1){
                try{
                    log("Way II");
                    rFTBI = gL(null,MSWindowsGeneral.taskbarP,1);
                    //rFTBI = ApplicationProc.calculateParent(null,"{}[0]{}[2]"); //- for horizontal
                    rFTBI = ApplicationProc.calculateParent(rFTBI,"{}[2]");

                    log("Way II OK");
                } catch (Exception e2){
                    log("Way III");
                    rFTBI = gL(null,MSWindowsGeneral.taskbarP,1);
                    //rFTBI = ApplicationProc.calculateParent(null,"{}[0]{}[1]"); //-for vertical
                    rFTBI = ApplicationProc.calculateParent(rFTBI,"{}[1]");

                    log("Way III OK");
                }
                log("WayA begin");
                //rFTBI = gimMeP(rFTBI,new ElemD("Notification Chevron", "Button"),"Chevron button",2,"","a");
                rFTBI = gL(rFTBI,"Chevron button",2,"nc", "Notification Chevron", "Button");

                rFTBI.click();
                sleepy(0.5);
                log("WayA cont");
                //rFTBI = gimMeP(null,new ElemD("","NotifyIconOverflowWindow"),"Notification Overflow",2,"","na");
                rFTBI = gL(null, new SP("Overflow Window","c","NotifyIconOverflowWindow"),2);

                log("WayA OK");
            }

            log("Overflow notification area");
            //DElement tRFTBI = gimMeP(rFTBI,"Overflow Notification Area","Notification Area",2,"","ca");
            DElement tRFTBI = gL(rFTBI, new SP("Notification Area", "n", "Overflow Notification Area"),2);

            log("Searching TBI");
            //rFTBI = gimMeP(tRFTBI,"RoboForm: fills forms, manages passwords","TBI",2,"x","ca");
            rFTBI = gL(tRFTBI,new SP("TBI","n","RoboForm: fills forms, manages passwords"),2);

            rFTBI.click();
            sleepy(0.5);
            log("Searching TBI again");
            //rFTBI = gimMeP(tRFTBI,"RoboForm: fills forms, manages passwords","TBI",2,"x","ca");
            rFTBI = gL(tRFTBI,new SP("TBI","n","RoboForm: fills forms, manages passwords"),2);

            wrk = rFTBI;
            log("Searching TBI has finished");
        }
        if (wrk == null){
            throw new Exception("TBI icon was not found.");
        }
        //wrk.click();
        log("Procedure 'Open TBI' has been completed");
        return wrk;
    }

    private static Process shellTBI;
    public static void loginToTBI(RFUser _user) throws Exception{
        logString = "Login to TBI";
        setLogBlockPrefix("loginToTBI");
        log("Starting procedure");
        openTBI();
        setLogBlockPrefix("loginToTBI-II");
        //DElement wrk = gimMeP(null,new ElemD("","#32768"),"Context menu",2,"","na");
        //DElement wrk = gL(null, "Context menu",2,"c","#32768");
        DElement wrk = gL(null, MSWindowsGeneral.contextW,2);

        log("Calculating parent");
        wrk = ApplicationProc.calculateParent(wrk,"{}[0]");
        wrk.click();
        sleepy(2);
        //ElemD logWin = new ElemD("RoboForm Master Password","#32770");
        DElement tWrk;
        try{
            //tWrk = gimMeP(null,logWin,"Enter MP window",1,"","a");
            tWrk = gL(null, "Enter MP window",1,"nc","RoboForm Master Password","#32770");

            //wrk = gimMeP(tWrk,new ElemD("Master Password","Edit"),"Password field",1,"","a");
            wrk = gL(tWrk, "Password field",1,"","nc","Master Password","Edit");

            wrk.click();
            sleepy(0.5);
            wrk.setEditValue(_user.getPassword());
            //wrk = gimMe(tWrk,new ElemD("Login","Button","1"),"Login button");
            wrk = gL(tWrk, "Log In button",1,"nca","Log In","Button","1");

            wrk.click();
            sleepy(1.5);
            //gimMeP(null,logWin,"Enter MP window",1,"d","a");
            gL(null,"Enter MP Window",1,"Dnc","RoboForm Master Password","#32770");

        }catch (Exception e){
            try{
                log("What if RF was logged in already?");
                openTBI();
                setLogBlockPrefix("loginToTBI-III");
                //DElement wrk = gimMeP(null,new ElemD("","#32768"),"Context menu",2,"","na");
                //DElement wrk = gL(null, "Context menu",2,"c","#32768");
                wrk = gL(null, MSWindowsGeneral.contextW,2);

                log("Calculating parent");
                wrk = ApplicationProc.calculateParent(wrk,"{}[0]");
                wrk.click();
                sleepy(2);

                //tWrk = gimMeP(null,logWin,"Enter MP window",1,"","a");
                tWrk = gL(null, "Enter MP window",1,"nc","RoboForm Master Password","#32770");

                //wrk = gimMeP(tWrk,new ElemD("Master Password","Edit"),"Password field",1,"","a");
                wrk = gL(tWrk, "Password field",1,"","nc","Master Password","Edit");

                wrk.click();
                sleepy(0.5);
                wrk.setEditValue(_user.getPassword());
                //wrk = gimMe(tWrk,new ElemD("Login","Button","1"),"Login button");
                wrk = gL(tWrk, "Log In button",1,"nca","Log In","Button","1");

                wrk.click();
                sleepy(1.5);
                //gimMeP(null,logWin,"Enter MP window",1,"d","a");
                gL(null,"Enter MP Window",1,"Dnc","RoboForm Master Password","#32770");
            } catch (Exception e1){
                log("Some shit happened, looking for Editor instead.");

                //tWrk = gimMeP(null,EditorD.editorW,"Editor window",1,"","na");
                tWrk = gL(null, "Editor window",1,"c",EditorD.editorW.cName);

                //wrk = gimMeP(tWrk,new ElemD("","Edit"),"Password field",1,"","na");
                wrk = gL(tWrk,"Password field",1,"c","Edit");

                wrk.click();
                sleepy(0.5);
                wrk.setEditValue(_user.getPassword());
                //wrk = gimMeP(tWrk,new ElemD("Login","Button"),"Login button",1,"","a");
                wrk = gL(tWrk,"Log In button",1,"nc","Log In","Button");

                wrk.click();
                sleepy(1.5);
            }

        }
        log("Procedure complete");
    }
    public static void killTBI(){
        logString = "Kill TBI";
        setLogBlockPrefix("killTBI");
        log("Starting procedure");
        int step = 0;
        try{
            openTBI();
            setLogBlockPrefix("killTBI-II");
            DElement wrk = gL(null,RF.ContextM,2);
            log("TBI has been found, closing");
            wrk = ApplicationProc.calculateParent(wrk,"{}[18]");
            wrk.click();
            sleepy(2);
            log("Handling possible warning message");
            step++;
            wrk = gL(null,RF.WarningD,1);
            step++;
            wrk = gL(wrk,RF.OkBtn,1);
            wrk.click();
        } catch (Exception e){
            switch (step) {
                case 0:
                    log("TBI was not found");
                    break;
                case 1:
                    log("Warning message was not found");
                    break;
                case 2:
                    logE("Button to dismiss warning was not found");
                    break;
            }
        }

        log("Procedure complete");
    }
    public static void restartTBI() throws Exception{
        logString = "Restart TBI";
        setLogBlockPrefix("restartTBI");
        log("Starting procedure");
        killTBI();
        setLogBlockPrefix("restartTBI-II");
        logString = "Restart TBI";
        sleepy(8);
        log("Starting TBI");
        ProcessBuilder pb = new ProcessBuilder(pathRF8Editor + nameRF8TBI);
        shellTBI = pb.start();
        sleepy(10);
        log("Procedure complete");
    }

    /**
     * If TBI is down and Editor shows "RoboForm is turn off", turns RF on
     * @throws Exception if something goes wrong
     */
    public static void turnRFOn() throws Exception{
        logString = "Turn RF On";
        setLogBlockPrefix("turnRFOn");
        log("Starting procedure");
        DElement wrk = gL(null,RF.editorW,1);
        //wrk = gimMeP(wrk,new ElemD("Turn ON","Button"),"'Turn ON' button",1,"","a");
        wrk = gL(wrk, new SP ("'Turn ON' button", "nc", "Turn ON", "Button"), 2);
        wrk.click();
        sleepy(5);
        log("Procedure complete");
    }

    /**
     * Edit _targetName safenote. If _append = true, then string will be added. Otherwise, all text will be erased.
     * @param _targetName target safenote name
     * @param _text text string
     * @param _append if false, text will be overwritten
     * @throws Exception if something bad happens
     */
    public static void editSafenote(String _targetName, String _text, Boolean _append) throws Exception{
        logString = "Edit Safenote";
        setLogBlockPrefix("editSafenote");
        log("Starting procedure");
        runRF8Editor();
        setLogBlockPrefix("editSafenote-II");
        sleepy(0.5);
        //DElement eW = gimMeP(null,EditorD.editorW,"Editor window",2,"","na");
        DElement eW = gL(null, RF.editorW, 2);
        checkItemExistence(EditorD.safenoteBtn.name,_targetName);
        setLogBlockPrefix("editSafenote-III");

        log("Moving to edit field");
        //DElement wrk = gimMePL(eW,new ElemD("","Edit"),"Editor field","e","oan").get(1);
        DElement wrk = gL(eW, new SP ("Editor field", "ce", "Edit", ""), 2);
        wrk.click();

        log("Applying _append==" + _append.toString());
        if (_append){
            //Append is quite bad due to Winium =)
            KeyboardHandler.sendKeysToElement(wrk,true,"^{END}");
            KeyboardHandler.sendKeysHere(_text);
//            wrk.sendKeys(wrk.getText() + _text);
        }
        else
        {
            wrk.setEditValue(_text);
//            wrk.sendKeys(_text);
        }
        sleepy(2);
        log("Press Save button");
        //wrk = gimMeP(eW,"Save","Save button",1,"e","ok");
        wrk = gL(eW, new SP ("Save button", "ne", "Save", ""), 2);
        wrk.click();
        sleepy(5);
        closeRF8EditorSoft();
        setLogBlockPrefix("editSafenote-IV");
        log("Procedure complete");
    }

    public static void checkItemContents(String _category, String _item,String _fieldScript, String _value) throws Exception{
        logString = "Check Item Contents";
        setLogBlockPrefix("checkItemContents");
        log("Checking item contents with the following parameters:");
        log("Cat.: " + _category + " Field script: " + _fieldScript);
        log("Value: " + _value);
        runRF8Editor();
        setLogBlockPrefix("checkItemContents-II");
        //DElement eW = gimMeP(null,EditorD.editorW,"Editor window",2,"","na");
        DElement eW = gL(null, RF.editorW, 2);
        navEditorTo(eW,_category);
        setLogBlockPrefix("checkItemContents-III");
        sleepy(0.5);
        searchFile(_item);
        setLogBlockPrefix("checkItemContents-IV");
        sleepy(0.5);
        DElement tgtF = ApplicationProc.calculateParent(eW,_fieldScript);

        if (!tgtF.getEditText().equals(_value)){
        //if (!tgtF.getText().equals(_value)){
            throw new Exception("Values are different");
        }
        log("Item's contents are OK");
    }
    /**
     * Close opened RF8 Editor soft way
     */
    public static void closeRF8EditorSoft() throws Exception{
        //logString = "Close RF8 Editor (soft way)";
        setLogBlockPrefix("closeRF8EditorSoft");
        log("Closing the Editor");

        try {
            //DElement ssW = gimMeP(null,EditorD.editorW,"Editor window",2,"","na");
            DElement ssW = gL(null, RF.editorW, 2);

            ssW.setForeground();
            sleepy (1);
//            DElement tWrk = gimMeP(ssW,EditorD.titleBar,"Title bar",1,"","onc");
            DElement tWrk = gL(ssW,"Title bar",1,"l","title bar");
            //DElement wrk = gimMeP(tWrk,EditorD.btnTBClose,"Close button",1,"","ca");
            DElement wrk = gL(tWrk, new SP ("Close button", "n", "Close"), 2);
            sleepy(1);
            wrk.click();
            sleepy(1);
            log("Verifying whether Editor window was closed or not:");
            //gimMeP(null,EditorD.editorW,"Editor window",2,"d","na");
            gL(null, RF.editorW, 2);
            log("Editor window has been closed successfully");
        }
        catch(Exception e)
        {
            logE("Could not locate title bar and perform evil things");
        }
//
//        log("Looking for possible sync setup windows");
//        boolean found = false;
//        try {
//            ssW = gimMeP(null, SyncD.syncW, "Sync RF data folder", 2, "", "ca");
//            found = true;
//        } catch (Exception e) {
//            try{
//                ssW = gimMeP(null, SyncD.syncWS, "Sync setttings", 2, "", "ca");
//                found = true;
//            } catch (Exception e1){
//                found = false;
//            }
//        }
//        if (found){
//            logW("Redundant bad sync setup window found. Closing");
//            WindowHandler.setForegroundWindow(ssW);
//            sleepy(1);
//            DElement closeOk;
//            try{
//                closeOk = gimMeP(ssW, SyncD.closeBUn, "close button", 2, "", "ca");
//            } catch (Exception q){
//                closeOk = gimMeP(ssW, SyncD.OkB, "Ok button", 2, "", "ca");
//            }
//            closeOk.click();
//            sleepy(1);
//        } else {
//            log("No redundant sync setup windows exist");
//        }

    }

    private static String nameSafeNote = "";
    static private Vector namesS = new Vector();
    static private int numNames = 0;

    /**This function creates file for send / sharing operations. File  safeNOte*/

    public static Boolean createSafenoteRF8(String _name) throws Exception{
        logString = "Create Safenote (RF8)";
        setLogBlockPrefix("createSafenoteRF8");
        nameSafeNote = _name;
        namesS.add(_name);
        numNames +=1;
        Boolean res;
        log("Running Editor");
        EditorProc.runRF8Editor();
        setLogBlockPrefix("createSafenoteRF8-II");
        log("Attaching to Editor window");
        sleepy(5);
        //DElement eW = gimMeP(null,EditorD.editorW,"Editor window",1,"","na");
        DElement eW = gL(null,RF.editorW,1);
        DElement wrk = navEditorTo(eW,EditorD.safenoteBtn.name);
        log("Clicking Add new Safenote button");
        offsetClick(wrk,55,0);
        sleepy(2);
        //System.out.println(_name);
        log("Entering the following name: " + _name);
//        DElement tWrk = gimMe(eW,CreateNewSafenote.mainW,"New Safenote dialog");
//        wrk = gimMe(tWrk,CreateNewSafenote.editN,"Safenote name");
//        wrk.setEditValue(_name);
//        sleepy(0.3);
//        wrk = gimMe(tWrk,CreateNewSafenote.btnOK,"OK Button");
//        wrk.click();
        DElement tWrk = gL(eW,"New Safenote dialog",2,"nc",CreateNewSafenote.mainW.name,CreateNewSafenote.mainW.cName);
        wrk = gL(tWrk, "Safenote name", 2,"nc",CreateNewSafenote.editN.name, CreateNewSafenote.editN.cName);
        wrk.setEditValue(_name);
        sleepy(0.3);
//        wrk = gL(tWrk,"OK button",2,"nca",CreateNewSafenote.btnOK.name,
//                CreateNewSafenote.btnOK.cName, CreateNewSafenote.btnOK.autId);
        wrk = gL(tWrk,RF.okB,2);
        wrk.click();
        sleepy(0.3);

        log("Verifying whether warning message exists or not");
        try{
            //tWrk = gimMe(eW,CreateNewSafenote.mainW,"New Safenote dialog");
            tWrk = gL(eW,"New Safenote dlg",2,"nc",CreateNewSafenote.mainW.name,CreateNewSafenote.mainW.cName);
            //DElement ttWrk = gimMe(tWrk,CreateNewSafenoteWDlg.mainW,"Safenote Warning dialog");
            DElement ttWrk = gL(tWrk,RF.warningRFW,2);

            //wrk = gimMe(ttWrk,CreateNewSafenoteWDlg.btnCancel,"Cancel button - warning dlg");
            wrk = gL(ttWrk,RF.cancelB,2);
            wrk.click();
            sleepy(0.5);
            //wrk = gimMe(tWrk,CreateNewSafenote.btnCancel,"Cancel button - create dlg");
            wrk = gL(tWrk,RF.cancelB,2);
            wrk.click();

            res = false;
        }
        catch(Exception e){
            log("Safenote created successfully");
            res = true;
        }

        //EditorProc.closeRF8EditorSoft(_webDriver);

        return res;
    }
    public static Boolean createNewIdentity(String _name) throws Exception{
        logString = "Create Identity (RF8)";
        setLogBlockPrefix("createNewIdentity");
        Boolean res;
        log("Attaching to Editor window");
        sleepy(5);
        DElement eW = gL(null,RF.editorW,1);

        DElement wrk = navEditorTo(eW,EditorD.identityBtn.name);
        setLogBlockPrefix("createNewIdentity-II");
        log("Clicking Add new Identity button");
        offsetClick(wrk,55,0);
        sleepy(2);
        log("Entering the following name: " + _name);
        DElement tWrk = gimMe(eW,CreateNewIdentity.mainW,"New Identity dialog");
        wrk = gimMe(tWrk,CreateNewIdentity.editN,"Identity name");
        wrk.setEditValue(_name);
        sleepy(0.3);
        wrk = gimMe(tWrk,CreateNewIdentity.btnOK,"OK Button");
        wrk.click();

        log("Verifying whether error message exists or not");
        try{
            tWrk = gimMe(eW,CreateNewIdentityEDlg.mainW,"RoboForm Error message");
            wrk = gimMe(tWrk,CreateNewIdentityEDlg.btnOK,"'OK' button");
            wrk.click();
            sleepy(0.5);
            log("Identity was not created");
            res = false;
        }
        catch(Exception e){
            log("Identity was created successfully");
            res = true;
        }

        return res;
    }
    public static Boolean createNewContact(String _name) throws Exception{
        logString = "Create Contact (RF8)";
        setLogBlockPrefix("createNewContact");
        Boolean res = false;
        if (!(_name.isEmpty())){
            log("Attaching to Editor window");
            sleepy(5);
            DElement eW = gimMeP(null,EditorD.editorW,"Editor window",1,"","na");

            DElement wrk = navEditorTo(eW,EditorD.contactBtn.name);
            setLogBlockPrefix("createNewContact-II");
            log("Clicking Add new Contact button");
            offsetClick(wrk,55,0);
            sleepy(2);
            log("Entering the following name: " + _name);
            DElement tWrk = gimMe(eW,CreateNewContact.mainW,"New Contact dialog");
            wrk = gimMe(tWrk,CreateNewContact.editN,"Contact name");
            sleepy(0.3);
            wrk.setEditValue(_name);
            sleepy(0.3);
            wrk = gimMe(tWrk,CreateNewContact.btnOK,"OK Button");
            wrk.click();

            log("Verifying whether error message exists or not");
            try{
                tWrk = gimMe(eW,CreateNewContactEDlg.mainW,"RoboForm Error message");
                wrk = gimMe(tWrk,CreateNewContactEDlg.btnOK,"'OK' button");
                wrk.click();
                sleepy(0.5);
                log("Contact was not created");
                res = false;
            }
            catch(Exception e){
                log("Contact was created successfully");
                res = true;
            }

        }
        else {
            logE("The _name is empty");
        }
        return res;
    }

    /**
     * Makes two category switches in Editor window
     * @param _first first target category name
     * @param _second second target category name
     * @throws Exception is something goes wrong
     */
    public static void slyCleaningMove(String _first, String _second) throws Exception{
        logString = "Sly move";
        setLogBlockPrefix("slyCleaningMove");
        log("Starting procedure");
        DElement eW = gimMeP(null,EditorD.editorW,"Editor window",1,"","na");

        navEditorTo(eW,_first);
        navEditorTo(eW,_second);
    }

    /**
     * Navigate editor to the specified category
     * @param _editor Editor window handle
     * @param _whereTo Name of the category to navigate to
     * @throws Exception If something goes wrong
     * @return Button handle
     */
    private static DElement navEditorTo(DElement _editor, String _whereTo) throws Exception{
//        DElement wrk = gimMe(_editor,new ElemD(_whereTo),"'" + _whereTo + "' button");
        setLogBlockPrefix("navEditorTo");
        DElement wrk = gL(_editor,"'" + _whereTo + "' button",1,"n",_whereTo);
        wrk.click();
        sleepy(2);
        return wrk;
    }
    public static void checkItemExistence(String _category, String _itemName) throws Exception{
        setLogBlockPrefix("checkItemExistence");
        log("Check item existence");
        sleepy(1);
//        DElement eW = null;
//        try{
            //eW = gL(null,"Editor window",1,"c","RfEditor");
            DElement eW = gL(null,RF.editorW,1);
//        } catch (Exception e) {
//            eW = gL(null,"Editor window - DEBUG", 1, "!c", "RfEditor");
//        }
        navEditorTo(eW,_category);
        searchFile(_itemName);
    }

    /** RF--Sync---Sync----
      * with some changes*/
    public static void manualsyncRF () throws Exception

    {
        logString = "Sync RF (RF8)  RF--Sync---Sync---";
        setLogBlockPrefix("manualsyncRF");
        log("Attaching to Editor window");
        sleepy(5);
        //DElement ssW = gimMeP(null,EditorD.editorW,"Editor window",1,"","na");
        DElement ssW = gL(null, RF.editorW, 2);
        //DElement wrk = gimMe(ssW,EditorD.grandRFB,"RoboForm button");
        DElement wrk = gL(ssW, new SP ("Grand RF button", "nc", "RoboForm", ""), 2);
        wrk.click();
        sleepy(2);
//        DElement context = gimMeP(null,new ElemD("Context","#32768"),"Context menu",2,"","na");
        DElement context = gL(null,"Context menu",2,"l","menu");
        //DElement tmp = gimMeP(context,"Sync","Sync",1,"","ca");
        DElement tmp = gL(context, new SP ("Sync", "n", "Sync"), 2);
        tmp.click();
        //context = gL(null,"Sync Context menu",2,"l","menu");
        //tmp = gimMeP(context,"Sync Now","Sync RF",1,"","ca");
//        tmp = gimMeP(tmp,"Sync Now","Sync Now",1,"","ca");
        context = gL(ssW,"Sync menu",2,"n","Sync");
        //tmp = gimMeP(context,"Sync Now","Sync Now",1,"","ca");
        tmp = gL(context, new SP ("Sync now", "n", "Sync Now"), 2);
//        tmp = gL(context,"Sync Now",1,"nd!","Sync Now");
        tmp.click();
        log("Procedure complete");
    }
    public static void syncSettingsRF (Boolean change, RFUser _user) throws Exception
    {
        logString = "Sync Settings RF (RF8)  RF--Sync---Sync Settings---";
        setLogBlockPrefix("syncSettingsRF");
        log("Attaching to Editor window");
        sleepy(5);
        //DElement ssW = gimMeP(null,EditorD.editorW,"Editor window",1,"","na");
        DElement ssW = gL(null,RF.editorW,1);
        //DElement wrk = gimMe(ssW,EditorD.grandRFB,"RoboForm button");
        DElement wrk = gL(ssW,RF.roboformB,2);
        wrk.click();
        sleepy(2);
        //DElement context = gimMeP(null,new ElemD("Context","#32768"),"Context menu",2,"","na");
        DElement context = gL(null,RF.contextM,2);
        //DElement tmp = gimMeP(context,"Sync","Sync",1,"","ca");
        DElement tmp = gL(context,"Sync",1,"n","Sync");
        tmp.click();

        //context = gimMeP(ssW,new ElemD("Sync","#32768"),"Sync Context menu",2,"","na");
        context = gL(ssW,RF.syncContextM,2);
        //tmp = gimMeP(context,"Settings...","Sync Settings RF",1,"","ca");
        tmp = gL(context,"Sync Settings RF",1,"n","Settings...");
        tmp.click();
        log("Procedure complete");
        if (!change)
        {
            try {
                //DElement mainW = gimMeP(null, SyncD.syncWS, "RF Sync Settings", 1, "", "ca");
                DElement mainW = gL(null,RF.syncSettingsW,1);
                //DElement nextB = gimMeP(mainW, SyncD.nextBtn, "next button", 1, "", "nc");
                DElement nextB = gL(mainW,RF.nextB,1);
                nextB.click();
            }
            catch(Exception e) {
                log("Window RF SEttings is broken");
            }
        }
        else
        {
            try {
                //DElement mainW = gimMeP(null, SyncD.syncWS, "RF Sync Settings", 1, "", "ca");
                DElement mainW = gL(null,RF.syncSettingsW,1);
                //DElement changeA = gimMeP(mainW, SyncD.selectD, "change acc", 1, "", "ca");
                DElement changeA = gL(mainW,"Change acc",1,"n","Select a different RoboForm account");
                changeA.click();

                DElement loginex = gL(mainW,RF.logInWithExistingAccB,1);
                loginex.click();
                sleepy(1);

                //DElement field = gimMe(mainW, ChangeAccount.emailField, "change account email field");
                DElement field = gL(mainW,RF.emailUserIDE,1);
                field.setEditValue(_user.getEmail());
//                field = gimMe(mainW, ChangeAccount.nextB1, "email is entered");
//                field.click();
//                field = gimMe(mainW, ChangeAccount.passwordField, "passsword field");
                field = gL(mainW,RF.MPE,1);
                field.setEditValue(_user.getPassword());
                //field = gimMe(mainW, ChangeAccount.nextB2, "password is entered");
                field = gL(mainW,RF.nextB,1);
                field.click();
            }
            catch (Exception e) {
                log("Window RF Settings is broken");
            }
        }
    }
    public static void syncSettingsRF (Boolean change) throws Exception
    {
        syncSettingsRF (false, null);
    }

    /**
     * RF > Sync > Turn On Sync (made for RF7, should work for RF8 as well)
     */
    public static void turnOnSync() throws Exception{
        logString = "Turn On Sync";
        setLogBlockPrefix("turnOnSync");
        log("Attaching to Editor window");
        sleepy(5);
        DElement ssW = gimMeP(null,EditorD.editorW,"Editor window",2,"","na");

        DElement wrk = gimMe(ssW,EditorD.grandRFB,"RoboForm button");
        wrk.click();
        sleepy(2);
        wrk = gimMeP(null,new ElemD("Context","#32768"),"Context menu",2,"","na");
        wrk = gimMeP(wrk,"Sync","Sync tree item",2,"","ca");
        wrk.click();
        sleepy(0.5);
        //wrk = gimMeP(wrk,"Turn On Sync","Turn On Sync tree item",2,"","ca");
        wrk = gL(ssW,"Sync menu",1,"n","Sync");
        wrk = gL(wrk,"Turn On Sync",1,"n","Turn On Sync");
        wrk.click();
        sleepy(0.5);
        log("Procedure complete");
    }


    private static int numFolders = 0;
    private static String name = "";
    public static void createFolders(int _count, String _name) throws Exception {
        logString = "Create folders";
        setLogBlockPrefix("createFolders");
        numFolders = _count;
        name = _name;

        log("Starting procedure");
        try {
            DElement ssW = gimMeP(null,EditorD.editorW,"Editor window",2,"","na");
            for (int i = 0; i < numFolders; ++i) {
                System.out.println(i);
                DElement rfE = gimMe(ssW, EditorD.createFolder, "Create Folder button");
                rfE.click();
                System.out.println(i);
                //rfE = gimMe(rfE, CreateNewFolder.mainW, "Open create folder window");
                DElement sharwin = gimMeP(null,"RoboForm","RoboForm",1,"","ca");
                System.out.println(i);
                rfE = gimMeP(sharwin,new ElemD("","Edit"),"Edit",1,"","na");
                //rfE = gimMe(sharwin, CreateNewFolder.editN, "Input folder name");
                rfE.setEditValue(_name);
                System.out.println(i);
                rfE = gimMe(sharwin, CreateNewFolder.btnOK, "OK button");
                rfE.click();
                System.out.println(i);

                try {
                    DElement err = gimMe(ssW, CreateNewFolderError.mainW, "Name already exists");
                    DElement ttWrk = gimMe(err, CreateNewFolderError.btnOK, "OK button");
                    ttWrk.click();
                } catch (Exception e) {
                    log("Folder created successfully");
                }
            }
        }
        catch (Exception e){
            logE("Procedure failed: " + e.getMessage());
            throw e;
        }

    }

    public static void logoff() throws Exception
    {
        setLogBlockPrefix("logoff");
        log("Starting logoff");
        DElement ssW = gimMeP(null,EditorD.editorW,"Editor window",1,"","an");
        DElement rfE = gimMe(ssW, EditorD.logoffBtn, "logoff button");
        try {
            rfE.click();
        }
        catch (Exception e){
            logE("Procedure failed: " + e.getMessage());
            throw e;
        }

        log("Logoff is finished successfully");
    }

    /**
     * login for PR71LogoffLogin
     * @param _user RFUser
     * @throws Exception if something bad happens
     */
    public static void loginPR(RFUser _user) throws Exception{
        setLogBlockPrefix("loginPR");
        log("Starting login with password: " + _user.getPassword());
        DElement eW = gimMeP(null, EditorD.editorW, "Editor window",1,"","an");
        DElement wrk = gimMeP(eW,EditorD.editLoginPwd,"Password field",2,"","n");
        wrk.click();
        sleepy(0.1);
        wrk.setEditValue(_user.getPassword());
        sleepy(0.4);
        wrk = gimMeP(eW,EditorD.btnLogin,"Login button",1,"","a");
        wrk.click();
        sleepy(4);
        log("Finishing procedure");
    }
    public static void openRFOptions() throws Exception{
        setLogBlockPrefix("openRFOptions");
        log("Opening RF Options: starting");
        DElement ssW = gL(null,RF.editorW,2);
        ssW.setForeground();
        sleepy(1);
        DElement wrk = gL(ssW, RF.roboformB,2);
        wrk.click();
        sleepy(1);
        clickItemFromContextMenu("Options...");
        log("Opening RF Options: complete");
    }

    public static void performChangeAccount() throws Exception{
        setLogBlockPrefix("performChangeAccount");
        log("Going to Options");
        openRFOptions();
        setLogBlockPrefix("performChangeAccount-II");
        DElement tmp = gL(null,RF.optionsW,2);
        log("Going to Account&Data");
        tmp = gL(tmp,RF.optionsSectionsSI,2);
        tmp = gL(tmp,RF.optionsAccountDataSectionT,2);
        tmp.click();
        sleepy(2);

        tmp = gL(null,RF.optionsW,2);
        log("Clicking Change Account");
        tmp = gNL(tmp,RF.optionsSectionsSI,2,1);
        tmp = gL(tmp,RF.changeAccountT,2);
        tmp.click();

    }

    public static void createNewAccount(RFUser _user) throws Exception{
        //logString = "Create New Account From LOGOFF Window"; //do not actual anymore
        logString = "Create New Account";
        setLogBlockPrefix("createNewAccount");
        log("Starting procedure");

         //logoff();
        performChangeAccount();
        setLogBlockPrefix("createNewAccount-II");


//        sleepy(3);
//        //DElement ssW = gimMeP(null,EditorD.editorW,"Editor window",1,"","na");
//        //DElement ssW = gL(null,"Editor window",1,"c",EditorD.editorW.cName);
//        DElement ssW = gL(null,RF.editorW,1);

        //DElement rfE = gL(ssW, "Change account",2,"No","Change Account","f");
//        DElement rfE = gL(ssW,RF.changeAccE,2);
//        rfE.click();

//        DElement sw = gimMeP(null, SyncD.syncWS,"Sync setup window [Main]", 2, "", "ca");
        //DElement sw = gL(null,"Sync setup window [Main]",5,"n",SyncD.syncWS.name);
        DElement sw = gL(null,RF.syncRFDFW2,5);

        //DElement CNA = gimMeP(sw, new ElemD("Create New RoboForm account", "", ""),"Sync setup window", 2, "", "ca");
        //DElement CNA = gL(sw, "Create new RF account",2,"n","Create New Account");
        DElement CNA = gL(sw,RF.createNewAccB,2);

        CNA.click();

        log("Proceeding with Sync setup window.");

        //DElement wrk = gimMe(sw, InstallSetSyncD.emailF,"Email field");
        //DElement wrk = gL(sw,"Email field",1,"nc",InstallSetSyncD.emailF.name,InstallSetSyncD.emailF.cName);
        DElement wrk = gL(sw,RF.emailE,1);

        wrk.click();
        wrk.setEditValue(_user.getEmail());
        sleepy(0.5);
//        wrk = gimMe(sw,InstallSetSyncD.masterPwdF,"Master pwd field");
//        wrk = gL(sw,"Master pwd field",1,"nc",InstallSetSyncD.masterPwdF.name,InstallSetSyncD.masterPwdF.cName);
//
//        wrk.click();
//        wrk.setEditValue(_user.getPassword());
//        sleepy(0.5);


        //
        // Vector<DElement> wrkL = gVL(sw,"MP field",1,"nco",InstallSetSyncD.masterPwdF.name,InstallSetSyncD.masterPwdF.cName,"false");
        Vector<DElement> wrkL = gVL(sw,RF.MPE,1);
        for (DElement z : wrkL){
            z.click();
            z.setEditValue(_user.getPassword());
            sleepy(0.5);
        }
//        wrk = gimMe(sw,InstallSetSyncD.nextBNasty7,"Next button");
        //wrk = gL(sw,"Next button",1,"nc",InstallSetSyncD.nextBNasty7.name,InstallSetSyncD.nextBNasty7.cName);
//        wrk = gL(sw,RF.nextB,1);

//        wrk.click();
//        sleepy(5);
//        log("Seeking for disabled Next button to appear");
        //gimMeP(sw,InstallSetSyncD.nextBNasty0,"Next button",5,"","eo");
        //gL(sw,"Next button",5,"nceo",InstallSetSyncD.nextBNasty0.name,InstallSetSyncD.nextBNasty0.cName,"false","false");
//        gL(sw,RF.nextB,"",5,"e","false");

        //wrk = gimMe(sw,InstallSetSyncD.masterPwdF,"Confirm MP field");
//        wrk = gL(sw,"Confirm MP field",1,"nc",InstallSetSyncD.masterPwdF.name,InstallSetSyncD.masterPwdF.cName);
//
//        wrk.click();
//        wrk.setEditValue(_user.getPassword());
//        sleepy(1);

        //wrk = gimMe(sw,InstallSetSyncD.nextBNasty0,"Next button");
        //wrk = gL(sw,"Next button",1,"nc",InstallSetSyncD.nextBNasty0.name,InstallSetSyncD.nextBNasty0.cName);
        wrk = gL(sw,RF.nextB,1);

        wrk.click();
        sleepy(6);
        //wrk = gimMe(sw,InstallSetSyncD.nextBNasty2,"Next button");
        //wrk = gL(sw,"Next button",1,"nc",InstallSetSyncD.nextBNasty2.name,InstallSetSyncD.nextBNasty2.cName);
        wrk = gL(sw,RF.nextB,1);

        wrk.click();

        sleepy(1);
        //gimMeP(sw,SyncD.stopB,"Stop button",5,"ed","oa");
        //gL(sw,"Stop button",5,"ncDeo",SyncD.stopB.name,SyncD.stopB.cName,"true","false");
        gL(sw,RF.stopB,"",5,"De","true");

        log("Procedure completed");
        //wrk = gimMeP(sw,SyncD.OkB,"Close window",5,"","oa");
        //wrk = gL(sw,"Close window",5,"nco",SyncD.OkB.name,SyncD.OkB.cName,"false");
        wrk = gL(sw,RF.okB,5);

        wrk.click();
        log("Procedure completed");

    }

    /**
     * Supposed to remove all shared folders from the current account in current Editor window
     * Editor window is supposed to be opened and brought to front
     */
    public static void removeAllItems() throws Exception{
        setLogBlockPrefix("removeAllItems");
        DElement rfE = gL(null,RF.editorW,2);

        DElement tree = gL(rfE,RF.editorTree,2);

        DElement itemToDelete;
        boolean finish;

        do {
            finish = true;
            try{
                itemToDelete = gVL(tree,RF.TI,1).get(1);
                log("Attempting to delete the item with the following name: " + itemToDelete.getName());
                itemToDelete.contextClick();

                finish = false;
                try{
                    clickItemFromContextMenu("Delete");
                } catch (Exception e){
                    logW("Unable to perform delete: no such context menu item - Delete");
                    return;
                }

                //what if it is a regular file
                try{
                    DElement tmp = gL(rfE,RF.roboformQuestionDlgW,2);
                    log("That is a regular file or folder");
                    tmp = gL(tmp,RF.okB,2);
                    tmp.click();
                } catch (Exception e){
                    log("That is not a regular file or folder");
                    try{
                        DElement mainW = gL(null,RF.paneLT,2);//not truly paneLT, but the same
                        DElement tmp = gL(mainW,RF.deleteB,1);
                        tmp.click();
                        sleepy(3);
                        log("Waiting for warning window");
                        tmp = gL(mainW, RF.warningDialogRFW,1);
                        tmp = gL(tmp, RF.okB,1);
                        tmp.click();
                    } catch (Exception e1){
                        logE("Something bad had happened, proceeding emergency alert");
                        KeyboardHandler.sendKeysHere(true,"{ESC}{ESC}{ESC}{ESC}{ESC}");
                        sleepy(1);
                        return;
                    }
                }
                sleepy(5);
            } catch (Exception e){
                itemToDelete = null;
            }
        } while (!finish);
        log("Procedure finished");
    }
    /** accept / reject file / folder and finish sync (close sync window)
     * @param accrej  2--accept 1 ---reject 3----combination
     * @param flag true is expect accept/reject
     * @param _filename - name of reject file
     * @throws Exception if something goes wrong
     */
    public static void handleSync(String _filename, RFUser _user, int accrej, Boolean flag) throws Exception
    {
        logString = "Sync handling";
        setLogBlockPrefix("handleSync");
        Boolean isSyncW = false; /* sync RF data folder window*/
        Boolean isSyncWS = false; /* sync settings window*/
        log("Starting procedure");
        try {
            DElement ssW;
            try {
                //ssW = gimMeP(null, SyncD.syncW, "Sync RF data folder", 2, "", "ca");
                ssW = gL(null, RF.syncRFDFW,2);
                isSyncW = true;
            } catch (Exception e) {
                try{
                    ssW = gL(null,RF.syncRFDFW2,2);
                    isSyncW = true;
                } catch (Exception e1){
                    //ssW = gimMeP(null, SyncD.syncWS, "Sync setttings", 2, "", "ca");
                    ssW = gL(null, RF.syncSettingsW,2);
                    isSyncWS = true;
                }
            }
            WindowHandler.setForegroundWindow(ssW);
            DElement ar;
            DElement btn;
            if (flag) {
                sleepy(5);
                if (accrej != 0) {
                    if (accrej == 2) /* accept file*/ {
                        //btn = gimMeP(ssW, SyncD.acceptB, "Accept files", 2, "", "");
                        btn = gL(ssW,RF.acceptB,2);
                        btn.click();
                        sleepy(10);
                        try{
                            btn.click();
                            sleepy(10);
                        } catch (Exception e){

                        }
                    }
                    if (accrej == 1) /* reject button*/ {
                        //btn = gimMeP(ssW, SyncD.rejectB, "Reject button", 2, "", "");
                        btn = gL(ssW,RF.rejectB,2);
                        btn.click();
                        sleepy(10);
                    }
                    if (accrej == 3) {
                        //ar = gimMeP(ssW, SyncD.dataFieldWS, "files", 2, "", "");
                        ar = gL(ssW,"files",2,"ca",SyncD.dataFieldWS.cName,SyncD.dataFieldWS.autId);
                        String filename = "'" + _filename + "'" + " " + "from" + " " + _user.getEmail();

                        //ar = gimMeP(ar,filename,filename,1,"","ca");
                        ar = gL(ar,filename,1,"n",filename);

                        ar.click();
                        //btn = gimMeP(ssW, SyncD.acceptB, "Accept files", 2, "", "");
                        btn = gL(ssW,RF.acceptB,2);
                        btn.click();
                        sleepy(5);
                        //ar = gimMeP(ssW, SyncD.dataFieldWS, "files", 2, "", "");
                        ar = gL(ssW,"files",2,"ca",SyncD.dataFieldWS.cName,SyncD.dataFieldWS.autId);
                        ar = gL(ar,filename,1,"n",filename);
                        ar.click();
                        //btn = gimMeP(ssW, SyncD.rejectB, "Reject button", 2, "", "");
                        btn = gL(ssW,RF.rejectB,2);
                        btn.click();
                        sleepy(10);

                     }
                }
            }
            sleepy(8.7);
            /* trial licence window appears?*/
            try {
                DElement tmp = gL(null,"Feat.",1,"N","Everywhere Feature");
                tmp.setFocus();
                tmp.setForeground();
                DElement titlebar = gL(tmp,"Title bar",1,"l","title bar");
                DElement close = gL(titlebar,"Close",1,"n","Close");
                close.click();
                log("Window with warning about trial was closed");
            } catch (Exception e) {
                log("Window with warning about trial did not appear");
            }

            /* close the window, if it is OK*/
            DElement closeOk;
            try {
                if (isSyncW) {
                    //closeOk = gimMeP(ssW, SyncD.closeBUn, "close button", 2, "", "ca");
                    closeOk = gL(ssW,RF.closeB,2);
                    closeOk.click();
                }
                if (isSyncWS) {
                    //closeOk = gimMeP(ssW, SyncD.OkB, "Ok button", 2, "", "ca");
                    closeOk = gL(ssW,RF.okB,2);
                    closeOk.click();
                }
            }
            catch (Exception e) {
                log("May be there is no license");
                /*no license*/
                try {
                    closeOk = gL(ssW, "close button toolbar", 2, "l", "title bar");
                    //closeOk = gimMeP(closeOk, new ElemD("Close","",""), "close button ", 2, "", "ca");
                    closeOk = gL(closeOk,RF.closeB,2);
                    closeOk.click();
                }
                catch (Exception b) {
                    log("ups");
                }
            }

        } catch (Exception e) {
            logE("Procedure failed: " + e.getMessage());
            throw e;
        }
    }

    /** Generate new item name if original was sent or shared
     * @param _filename original filename
     * @param _user target RFUser
     * @param operation_type 2--sharing 1--sens 0---simple name
     * @throws Exception if something goes wrong
     */
    public static String getNewName(String _filename, RFUser _user, String operation_type) throws  Exception
    {
        String filename;
        log("Starting getting new name");
        try {

            if (operation_type.equals("share")) /* sharing operation*/
            {
                filename = _user.getEmail() + "'s " +_filename;
            }
            else if (operation_type.equals("send")) /* send operation*/
            {
                filename = _filename + " (from " + _user.getEmail() +")";
            }
            else
                filename = _filename;

        } catch (Exception e) {
            logE("Procedure failed: " + e.getMessage());
            throw e;
        }
        return filename;
    }

    public static void handleSync() throws Exception{
        handleSync("", null, 0, false);
    }

    public static void changeAccount(RFUser _user) throws Exception
    {
        logString = "Change account";
        setLogBlockPrefix("changeAccount");
        log("Starting procedure");
        try {
//            //DElement ssW = gimMeP(null,EditorD.editorW,"Editor window",1,"","an");
//            DElement ssW = gL(null, RF.editorW,1);
//            //DElement rfE = gimMe(ssW, EditorD.logoffBtn, "logoff button");
//            DElement rfE = gL(ssW,RF.logoutB,1);
//            rfE.click();
//            sleepy(4);
//
//            sleepy(2);
//            //rfE = gL(ssW, "Change account",4,"No","Change Account","f");
//            rfE = gL(ssW,RF.changeAccE,4);
//            rfE.click();
//            sleepy(2);
            performChangeAccount();
            setLogBlockPrefix("changeAccount-II");

            //rfE = gimMeP(null,"RoboForm Sync Settings","RF Sync settings",2,"","ca");
            //rfE = gL(null,"RF Sync settings",2,"n","RoboForm Sync Settings");
            //rfE = gL(null,RF.syncSettingsW,1);
            DElement rfE = gL(null,RF.syncRFDFW2,3);
            rfE.setForeground();
            sleepy(1);
            DElement wrk = gL(rfE,RF.logInWithExistingAccB,2);
            wrk.click();
            sleepy(2);
            //DElement field = gimMe(rfE,ChangeAccount.emailField, "change account email field");
            DElement field = gL(rfE,RF.emailUserIDE,1);
            field.setEditValue(_user.getEmail());
            sleepy(1);
//            field = gimMe(rfE, ChangeAccount.nextB1, "email is entered");
//            field.click();
            //field = gimMe(rfE, ChangeAccount.passwordField, "password field");
            field = gL(rfE,RF.MPE,1);
            field.setEditValue(_user.getPassword());
//            field = gimMe(rfE, ChangeAccount.nextB2, "password is entered");
            field = gL(rfE, RF.nextB, 1);
            field.click();

            /* trial licence window appears?*/
            try {
                DElement tmp = gL(null,"Feat.",1,"n","RoboForm Everywhere Feature");
                DElement close = gL(tmp,"Close",1,"n","Close");
                close.click();
                log("Window with warning about trial was closed");
            } catch (Exception e) {
                log("Window with warning about trial does not appeared");
            }

            /* close the window, if it is OK*/
//            field = gimMeP(rfE,SyncD.OkB,"Close window",5,"","oa");
//            field.click();
        }
        catch (Exception e){
            logE("Procedure failed: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Search for an item by its name
     * @param _name Name of the item
     * @param _shouldExist True if item should exist
     * @throws Exception If something bad happens or result was not expected
     */
    public static void searchFile(String _name, Boolean _shouldExist) throws Exception{
        setLogBlockPrefix("searchFile");
        log("Starting searching for the following item: " + _name + " shouldExist=" + _shouldExist);
        Boolean bad = false;
        try{
            //DElement eW = gimMeP(null,EditorD.editorW,"Editor window",1,"","an");
            DElement eW = gL(null,RF.editorW,1);
            //DElement tree = gimMeP(eW,EditorD.treeRF, "RF tree with files", 2, "", "nc");
            DElement tree = gL(eW,"tree",1,"l","tree");
            //DElement elem = gimMeP(tree, _name, _name, 1, "", "ca");
            DElement elem = gL(tree,_name,3,"n",_name);
            //elem.click();
            elem.click();
            sleepy(2);
            KeyboardHandler.sendKeysHere(true,"{ESC}"); //to remove possible rename state
            if (_shouldExist){
                log("SUCCESS: Item exists");
            }
            else
            {
                bad = true;
            }
        } catch (Exception e){
            if (!_shouldExist){
                log("SUCCESS: Item does not exist");
            }
            else
            {
                logE("Procedure failed: Item does not exist: " + e.getMessage());
                throw e;
            }
        }
        if (bad){
            logE("Procedure failed: Item exists.");
            throw new Exception("Failure: Item '" + _name + "' exists");
        }

    }

    public static void searchFile(String _name) throws Exception // true - accepted  false -- rej
    {
        searchFile(_name, true);
    }

    public static void createSFolder(String _name) throws Exception
    {
        logString = "Create shared folder";
        setLogBlockPrefix("createSFolder");
        name = _name;
        log("Starting procedure");
        try {
            DElement ssW = gL(null,RF.editorW,1);
            DElement rfE = gimMe(ssW, EditorD.createSFolder, "More button button");
            rfE.click();
            DElement shar = gimMeP(null,new ElemD("Context","#32768"),"Context menu",2,"","na");
            DElement tmp = gimMeP(shar,"Create shared folder...","Create shared folder",1,"","ca");
            tmp.click();
            sleepy(1);
            //temp patch - begin
            ssW.clickRECTCenter();
            sleepy(1);
            //temp patch - end
            KeyboardHandler.sendKeysHere(_name);
            sleepy(0.5);
            KeyboardHandler.sendKeysHere(true,"{ENTER}");
            sleepy(3);
            //because sometimes it is very very slow
            /*rfE = gimMeP(null, CreateNewSFolder.mainW, "create folder window", 2, "", "a");

            DElement name = gimMeP(rfE, CreateNewSFolder.editN, "folder name", 2, "", "ca");
            name.sendKeys(_name);

            name = gimMeP(rfE, CreateNewSFolder.btnCreate, "Click Create button",2,"", "ca");
            name.click();*/

            try {
                DElement err = gimMeP(null, CreateNewSFolderError.mainW, "you already have one shared folder", 2, "", "ca");
                err.click();
                sleepy(4);
                DElement ttWrk = gimMeP(err, CreateNewSFolderError.btnOK, "OK button", 2, "", "na");
                ttWrk.click();
                sleepy(4);
                err = gimMeP(rfE, CreateNewSFolder.btnCancel, "Click Cancel button",2,"", "ca");
                err.click();
                log("Folder was not created, beecse already exists");
            } catch (Exception e) {
                log("Folder created successfully");
            }
        }
        catch (Exception e){
            logE("Procedure failed: " + e.getMessage());
            throw e;
        }
    }



    public static void fillIdentity(String _identityFilename) throws Exception{
        logString = "EdFillIdentity";
        setLogBlockPrefix("fillIdentity");
        log("Starting procedure with following parameters:");
        log("_identityFilename = " + _identityFilename);
        String identityName = Proc.getItemFileProperty(_identityFilename,"ITEM_NAME");
        String identityCountry = Proc.getItemFileProperty(_identityFilename,"COUNTRY");
        String itemClass = Proc.getItemFileProperty(_identityFilename,"ITEM_CLASS");
        checkItemExistence("Identities",identityName);
        setLogBlockPrefix("fillIdentity-II");
        DElement eW = gL(null,RF.editorW,1);
        DElement tree = gL(eW,RF.editorTree,2);
        DElement elem = gL(tree, new SP ("Identity name", "n", identityName), 1);
        try
        {
            doubleClick(elem);
        } catch (Exception e){
            logW("Double-click has failed O_o");
        }
        sleepy(1);
        fillSectionFromJSON(itemClass,"Person",_identityFilename,eW,tree,identityCountry);
        fillSectionFromJSON(itemClass,"Business",_identityFilename,eW,tree,identityCountry);
        fillSectionFromJSON(itemClass,"Passport",_identityFilename,eW,tree,identityCountry);
        fillSectionFromJSON(itemClass,"Address",_identityFilename,eW,tree,identityCountry);
        fillSectionFromJSON(itemClass,"Credit Card",_identityFilename,eW,tree,identityCountry,"CREDIT_CARD",
                            "Credit Card","Credit Card",
                            "Credit Card");
        fillSectionFromJSON(itemClass,"Bank Account",_identityFilename,eW,tree,identityCountry, "BANK_ACCOUNT",
                            "Bank Account","Bank Account",
                            "Bank Account");
        fillSectionFromJSON(itemClass,"Car",_identityFilename,eW,tree,identityCountry);
        //fillSectionFromJSON(itemClass,"Authentication",_identityFilename,eW,tree,identityCountry);
        fillSectionFromJSON(itemClass,"Custom",_identityFilename,eW,tree,identityCountry);
    }
    /**
     * Works for only single contact person now
     * !Check that contact does exist before the rest
     * @param _contactFilename Contact JSON file name to fill from
     * @throws Exception if something bad had happened
     */
    public static void fillContact(String _contactFilename) throws Exception{
        logString = "EdFillContact";
        setLogBlockPrefix("fillContact");
        log("Starting procedure with following parameters:");
        log("_contactFileName = " + _contactFilename);
//        log("Filling default Person");
        String contactName = Proc.getItemFileProperty(_contactFilename,"ITEM_NAME");
//        JsonObject wrk = getJSONInstance(_contactFileName,"PERSON_DEFAULT");
        String contactCountry = Proc.getItemFileProperty(_contactFilename,"COUNTRY");
        String itemClass = Proc.getItemFileProperty(_contactFilename,"ITEM_CLASS");
        //navigate to person
        checkItemExistence("Contacts",contactName);
        setLogBlockPrefix("fillContact-II");
        DElement eW = gL(null, RF.editorW,1);
        DElement tree = gL(eW,RF.editorTree,2);
        DElement elem = gL(tree, new SP (contactName, "n", contactName),1);
//        elem.sendKeys(Keys.RIGHT);
//        sleepy(3);
        try
        {
//            gimMeP(tree, "Person", "Person", 1, "xo", "ca");
  //          log("Person is hidden, unfolding");
            doubleClick(elem);
        } catch (Exception e){
    //        log("Person is already displayed");
        }
//        DElement elem;
        //eanny winium - krivo rabotaet s klavoy, prihoditsya izvratschat'sya

        fillSectionFromJSON(itemClass,"Person",_contactFilename,eW,tree,contactCountry);
        fillSectionFromJSON(itemClass,"Business",_contactFilename,eW,tree,contactCountry);
        fillSectionFromJSON(itemClass,"Address",_contactFilename,eW,tree,contactCountry);
    }
    private static void fillObjJSON(int _sectionType, JsonObject wrk, DElement _editorWindow,String _country, String _itemClass) throws Exception{
        switch (_sectionType){
            case 1:{
                fillPersonWithJSONObject(wrk,_editorWindow,_country, _itemClass);
                break;
            }
            case 2:{
                fillBusinessWithJSONObject(wrk,_editorWindow,_country);
                break;
            }
            case 3:{
                fillAddressWithJSONObject(wrk, _editorWindow, _country);
                break;
            }
            case 4:{
                fillPassportWithJSONObject(wrk,_editorWindow,_country);
                break;
            }
            case 5:{
                fillCreditCardWithJSONObject(wrk,_editorWindow,_country);
                break;
            }
            case 6:{
                fillBankAccountWithJSONObject(wrk,_editorWindow,_country);
                break;
            }
            case 7:{
                fillCarWithJSONObject(wrk,_editorWindow,_country);
                break;
            }
            case 8:{
                fillAuthenticationWithJSONObject(wrk,_editorWindow,_country);
                break;
            }
            case 9:{
                fillCustomWithJSONObject(wrk,_editorWindow,_country);
                break;
            }
        }
    }
    private static void fillSectionFromJSON(String _itemClass, String _section, String _tgtFilename, DElement _editorWindow,
                                            DElement _editorTree, String _country) throws Exception {
        fillSectionFromJSON(_itemClass,_section,_tgtFilename,_editorWindow,_editorTree,_country,_section.toUpperCase(),
                _section,_section,_section);
    }
    private static void fillSectionFromJSON(String _itemClass,String _section, String _tgtFilename, DElement _editorWindow,
                                            DElement _editorTree, String _country, String _sectionFileDescription,
                                            String _sectionEditorDescription, String _sectionInterfaceDescription,
                                            String _sectionPopupDescription) throws Exception{
        log("Starting fillSectionFromJSON with following parameters:");
        log("# _section: " + _section + "     _tgtFilename: " + _tgtFilename);
        log("# _editorWindow: " + _editorWindow.toString());
        log("# _editorTree: " + _editorTree.toString());
        log("# _country: " + _country + "     _sectionFileDescription: " + _sectionFileDescription);
        log("# _sectionEditorDescription: " + _sectionEditorDescription + "     _sectionInterfaceDescription: " + _sectionInterfaceDescription);
        log("# _sectionPopupDescription: " + _sectionPopupDescription);
        int sectionType = 0;
        if (_section.equals("Person")){
            sectionType = 1;
        }
        if (_section.equals("Business")){
            sectionType = 2;
        }
        if (_section.equals("Address")){
            sectionType = 3;
        }
        if (_section.equals("Passport")){
            sectionType = 4;
        }
        if (_section.equals("Credit Card")){
            sectionType = 5;
        }
        if (_section.equals("Bank Account")){
            sectionType = 6;
        }
        if (_section.equals("Car")){
            sectionType = 7;
        }
        if (_section.equals("Authentication")){
            sectionType = 8;
        }
        if (_section.equals("Custom")){
            sectionType = 9;
        }

        if (sectionType == 0){
            throw new Exception("Section handling error - no such section: " + _section);
        }
        //navigate to section
        JsonObject wrk = getJSONInstance(_tgtFilename,_sectionFileDescription + "_DEFAULT");
        if (wrk != null){
            log("Filling default " + _section);
            DElement elem = gL(_editorTree, new SP (_sectionEditorDescription, "n", _sectionEditorDescription), 1);
            elem.click();
            sleepy(2);
            fillObjJSON(sectionType,wrk,_editorWindow,_country,_itemClass);
            //new addresses
            String cnt = Proc.getItemFileProperty(_tgtFilename,_sectionFileDescription + "_COUNT");
            if (!(cnt.equals(""))){
                int totnum = Integer.parseInt(cnt);
                for (int i = 2; i <= totnum; i++){
                    log("Filling " + _section + " #" + i);
                    wrk = getJSONInstance(_tgtFilename,_sectionFileDescription + "_" + Integer.toString(i));
                    String txt = _sectionInterfaceDescription;
                    if (i == 2) {
                        txt = "New " + txt;
                    }
                    Boolean makeNew = false;
                    if (wrk.has("SUBITEM_CLONE")){
                        if (wrk.get("SUBITEM_CLONE").getAsString().toUpperCase().equals("YES")){
                            String t = wrk.get("SUBITEM_CLONE_TARGET").getAsString();
                            if (i > 2) {
                                DElement hero = gL(_editorTree, new SP (t, "n", t),2);
                                hero.click();
                                sleepy(2);
                            }
                            DElement newButton = gL(_editorWindow, new SP (txt, "nle", txt,"split button","t"),1);
                            newButton.click();
                            clickItemFromContextMenu("Clone");
                        }
                        else {
                            makeNew = true;
                        }
                    }
                    else{
                        makeNew = true;
                    }
                    if (makeNew){
                        DElement newButton = gL(_editorWindow, new SP (txt, "nle", txt,"split button","t"),1);

                        newButton.click();
                        clickItemFromContextMenu("New...");
                    }
                    DElement tmpW = gL(_editorWindow,new SP("RoboForm", "c","#32770"),2);
                    DElement tmp = gimMeP(tmpW, new ElemD("New '" + _sectionPopupDescription + "' name","Edit"),txt + " edit",1,"","na");
                    tmp.click();
                    tmp.setEditValue(wrk.get("SUBITEM_NAME").getAsString());
                    log("Pressing OK");
                    tmp = gL(tmpW, new SP("OK","n", "OK"),1 );
                    tmp.click();
                    sleepy(2);
                    Boolean bad = false;
                    try {
                        DElement errW = gimMeP(_editorWindow,new ElemD("New " + _sectionPopupDescription,"#32770"),"New " + _sectionPopupDescription + " dialog",1,"e","a");
                        logE("Error message has appeared");
                        bad = true;
                        errW = gimMeP(errW,new ElemD("OK","Button","2"),"OK Button",1,"e","a");
                        errW.click();
                    } catch (Exception e) {
                        log(_section + "s #" + i + " was created successfully");
                    }
                    if (bad){
                        throw new Exception(_section + " #" + i + " already exists");
                    }

                    fillObjJSON(sectionType,wrk,_editorWindow,_country,_itemClass);
                }
            }
        }

    }
    /**
     * Filling right pane containing Person with Person data stored in JSON
     * @param _o JSON object with Person data
     * @param _editorW Editor's window
     * @param _country Since this would be probably country-related thing...
     */
    private static void fillPersonWithJSONObject(JsonObject _o, DElement _editorW, String _country, String _itemClass) throws Exception{
        log("Starting filling Person with JSON object");
        DElement rightPane = getRightPane(_editorW);

        //USA
/*        if (_country.equals("United States")){

        }
*/
        Boolean fillCompleteOK = true;
        DElement wrk;
        String className = "Title";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane,new ElemD("Title","ComboBox"),className,1,"","a");
//            wrk.click();
//            wrk = gimMeP(wrk,"Title","List",1,"","ca");
//            try{
//                wrk = gimMeP(wrk,t,t,2,"","ca");
//                wrk.click();
//            } catch (Exception e) {
//                logW("Field " + className + " was not filled successfully");
//                fillCompleteOK = false;
//            }
            Proc.setCBVAdd(_editorW,wrk,t,10,0);
        }
//        if (_o.has("Name_Last") || _o.has("Name_Mi")){
//            wrk = gimMeP(rightPane, new ElemD("", "Edit"), className, 1, "", "na");
//        }
        className = "Name_Last";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Last Name", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);

        }
        className = "Name_Mi";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Middle Initial", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);

        }
        className = "Name_First";
        if (_o.has(className)) {
            String t = _o.get(className).getAsString();
            wrk = gL(rightPane, new SP(className, "nc","Name", "Edit"), 1);
            wrk.click();
            wrk.setEditValue(t);
        }

        className = "Suffix";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gL(rightPane,new SP( className, "nc","Name Suffix","ComboBox"),1);
//            wrk.click();
//            wrk = gimMeP(wrk,"Name Suffix","List",1,"","ca");
//            try{
//                wrk = gimMeP(wrk,t,t,2,"","ca");
//                wrk.click();
//            } catch (Exception e) {
//                logW("Field " + className + " was not filled successfully");
//                fillCompleteOK = false;
//            }
            Proc.setCBVAdd(_editorW,wrk,t,10,0);
        }
        className = "Job_Title";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Job Title", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);

        }
        className = "Phone_x";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Phone", "Edit"), className, 1, "", "a");
            wrk.click();
            KeyboardHandler.sendKeysHere(true,"{TAB 3}");
            KeyboardHandler.sendKeysHere(t);
//            sendKeysWr(wrk, "{TAB}{TAB}{TAB}" + t);
        }
        className = "Phone";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Phone", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Home_Tel_x";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Home Tel", "Edit"), className, 1, "", "a");
            wrk.click();
            KeyboardHandler.sendKeysHere(true,"{TAB 3}");
            KeyboardHandler.sendKeysHere(t);
//            sendKeysWr(wrk, "{TAB}{TAB}{TAB}" + t);

        }
        className = "Home_Tel";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Home Tel", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);

        }
        className = "Work_Tel_x";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Work Tel", "Edit"), className, 1, "", "a");
            wrk.click();
            KeyboardHandler.sendKeysHere(true,"{TAB 3}");
            KeyboardHandler.sendKeysHere(t);
//            sendKeysWr(wrk, "{TAB}{TAB}{TAB}" + t);
        }
        className = "Work_Tel";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Work Tel", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Cell_Tel";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Cell Tel", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);

        }
        className = "Pager";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Pager", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Fax";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Fax", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }


        //move vertical scrollbar to the bottom
        int edWid = _editorW.getWindowSize().width;
        int edHei = _editorW.getWindowSize().height;

        offsetClick(_editorW,edWid / 2 - 10, edHei / 2 - 10);
        for (int i = 0; i < 29; i++){
            MouseHandler.clickLeft();
        }

        className = "Email";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Email", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Yahoo_ID";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Yahoo ID", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);

        }
        className = "MSN_ID";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("MSN ID", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);

        }
        className = "AOL_Name";
        if (_o.has(className)) {
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("AOL Name", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);

        }
        className = "ICQ_No";
        if (_o.has(className)) {
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("ICQ No", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);

        }

        className = "Skype_ID";
        if (_o.has(className)) {
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Skype ID", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);

        }
        className = "Sex";
        if (_o.has(className)) {
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane,new ElemD("Sex","ComboBox"),className,1,"","a");
            wrk.click();
            wrk = gimMeP(wrk,"Sex","List",1,"","ca");
            try{
                wrk = gimMeP(wrk,t,t,1,"","ca");
                wrk.click();
            } catch (Exception e) {
                logW("Field " + className + " was not filled successfully");
                fillCompleteOK = false;
            }
        }
        className = "Age";
        if (_o.has(className)) {
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Age", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);

        }
        className = "Birth_Date_mm";
        if (_o.has(className)) {
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane,new ElemD("Birth Date","ComboBox"),className,1,"","a");
            String[] arr = {"01","02","03","04","05","06","07","08","09","10","11","12"};
            Vector<String> ct = new Vector<>(Arrays.asList(arr));

            //fillCompleteOK = listDeal(_editorW,wrk,t,0,ct,null);
            wrk.setRFComboboxValue(t,10,0);
        }
        className = "Birth_Date_dd";
        if (_o.has(className)) {
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane,new ElemD("/","ComboBox"),className,1,"","a");
            String[] arr = {"01","02","03","04","05","06","07","08","09"};
            Vector<String> ct = new Vector<>(Arrays.asList(arr));
            for (int i = 10; i <= 31; i++ ){
                ct.add(Integer.toString(i));
            }
            //fillCompleteOK = listDeal(_editorW,wrk,t,0,ct,null);
            wrk.setRFComboboxValue(t,10,0);
        }
        className = "Birth_Date_yyyy";
        if (_o.has(className)) {
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("/", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        if (_itemClass.equals("Identity")){
            className = "Birth_Place";
            if (_o.has(className)) {
                String t = _o.get(className).getAsString();
                wrk = gimMeP(rightPane, new ElemD("Birth Place", "Edit"), className, 1, "", "a");
                wrk.click();
                wrk.setEditValue(t);
            }
            className = "Income";
            if (_o.has(className)) {
                String t = _o.get(className).getAsString();
                wrk = gimMeP(rightPane, new ElemD("Income", "Edit"), className, 1, "", "a");
                wrk.click();
                wrk.setEditValue(t);
            }
            className = "Soc_Sec_No";
            if (_o.has(className)) {
                String t = _o.get(className).getAsString();
                wrk = gimMeP(rightPane, new ElemD("Soc Sec No", "Edit"), className, 1, "", "a");
                wrk.click();
                wrk.setEditValue(t);
            }
            className = "Driver_License_State";
            if (_o.has(className)){
                String t = _o.get(className).getAsString();
                wrk = gimMeP(rightPane,new ElemD("Driver License","ComboBox"),className,1,"","a");
                //Vector<String> zips = new Vector<>(Arrays.asList(getUSAStateList()));
                //fillCompleteOK = listDeal(_editorW,wrk,t,0,zips,null);
                wrk.setRFComboboxValue(t,10,0);
            }
            className = "Driver_License_Number";
            if (_o.has(className)) {
                String t = _o.get(className).getAsString();
                wrk = gimMeP(rightPane, new ElemD("Driver License Number", "Edit"), className, 1, "", "a");
                wrk.click();
                wrk.setEditValue(t);
            }
            className = "Driver_License_Expires_mm";
            if (_o.has(className)) {
                String t = _o.get(className).getAsString();
                wrk = gimMeP(rightPane,new ElemD("Expires","ComboBox"),className,1,"","a");
                String[] arr = {"01","02","03","04","05","06","07","08","09","10","11","12"};
                Vector<String> ct = new Vector<>(Arrays.asList(arr));

                //fillCompleteOK = listDeal(_editorW,wrk,t,0,ct,null);
                wrk.setRFComboboxValue(t,10,0);

            }
            className = "Driver_License_Expires_dd";
            if (_o.has(className)) {
                String t = _o.get(className).getAsString();
                List<DElement> wrkL = gimMePL(rightPane,new ElemD("/","ComboBox"),className + " list","","a");
                wrk = wrkL.get(1);
                String[] arr = {"01","02","03","04","05","06","07","08","09"};
                Vector<String> ct = new Vector<>(Arrays.asList(arr));
                for (int i = 10; i <= 31; i++ ){
                    ct.add(Integer.toString(i));
                }
                //fillCompleteOK = listDeal(_editorW,wrk,t,0,ct,null);
                wrk.setRFComboboxValue(t,10,0);
            }
            className = "Driver_License_Expires_yyyy";
            if (_o.has(className)) {
                String t = _o.get(className).getAsString();
                List<DElement> wrkL = gimMePL(rightPane, new ElemD("/", "Edit"), className + " list",  "", "a");
                wrk = wrkL.get(1);
                wrk.click();
                wrk.setEditValue(t);
            }
        }
        className = "Note";
        if (_o.has(className)) {
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Note", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);

        }

        if (fillCompleteOK) {
            log("All filled OK");
        } else {
            throw new Exception("Some fields were not filled. See warnings.");
        }
        log("Finishing filling Person with JSON object");
    }

    /**
     * Filling right pane containing Business with Business data stored in JSON
     * @param _o JSON object with Business data
     * @param _editorW Editor's window
     * @param _country Since this would be probably country-related thing...
     */
    private static void fillBusinessWithJSONObject(JsonObject _o, DElement _editorW, String _country) throws Exception{
        log("Starting filling Business with JSON object");
        DElement rightPane = getRightPane(_editorW);

        //USA
/*        if (_country.equals("United States")){

        }
*/
        Boolean fillCompleteOK = true;
        DElement wrk;

        String className = "Company_Name";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Company Name", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Department";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Department", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Toll_Free_Phone";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Toll Free Phone", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Web_Site";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Web Site", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Business_Type";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            //!
            wrk = gimMeP(rightPane,new ElemD("Business Type","ComboBox"),className,1,"","a");
//            wrk.click();
//            wrk = gimMeP(wrk,"Business Type","List",1,"","ca");
//            try{
//                wrk = gimMeP(wrk,t,t,1,"","ca");
//                wrk.click();
//            } catch (Exception e) {
//                logW("Field " + className + " was not filled successfully");
//                fillCompleteOK = false;
//            }
            Proc.setCBVAdd(_editorW,wrk,t,10,0);
        }
        className = "Employer_ID";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Employer Id", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Stock_Symbol";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Stock Symbol", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Note";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Note", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        if (fillCompleteOK) {
            log("All filled OK");
        } else {
            throw new Exception("Some fields were not filled. See warnings.");
        }
        log("Finishing filling Business with JSON object");
    }

    /**
     * Filling right pane containing Address with Address data stored in JSON
     * @param _o JSON object with Address data
     * @param _editorW Editor's window
     * @param _country Since this would be probably country-related thing...
     */
    private static void fillAddressWithJSONObject(JsonObject _o, DElement _editorW, String _country) throws Exception{
        log("Starting filling Address with JSON object");
        DElement rightPane = getRightPane(_editorW);

        //USA
/*        if (_country.equals("United States")){

        }
*/
        Boolean fillCompleteOK = true;
        DElement wrk;

        String className = "Address_Line_1";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Address Line 1", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Address_Line_2";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Address Line 2", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "City";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("City", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "State_Zip";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane,new ElemD("State Zip","ComboBox"),className,1,"","a");
            Vector<String> zips = new Vector<>(Arrays.asList(getUSAStateList()));
            //fillCompleteOK = listDeal(_editorW,wrk,t,0,zips,null);
            wrk.setRFComboboxValue(t,10,0);

        }
        className = "Zip_Code_2";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Zip Or PostCode", "Edit"), className, 1, "", "a");
            wrk.click();
            KeyboardHandler.sendKeysToElement(wrk,true,"{TAB}");
            KeyboardHandler.sendKeysHere(t);
//            sendKeysWr(wrk, "{TAB}" + t);
        }
        className = "Zip_Code_1";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Zip Or PostCode", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "County";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("County", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Note";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Note", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        if (fillCompleteOK) {
            log("All filled OK");
        } else {
            throw new Exception("Some fields were not filled. See warnings.");
        }
        log("Finishing filling Address with JSON object");
    }
    /**
     * Filling right pane containing Passport with Passport data stored in JSON
     * @param _o JSON object with Passport data
     * @param _editorW Editor's window
     * @param _country Since this would be probably country-related thing...
     */
    private static void fillPassportWithJSONObject(JsonObject _o, DElement _editorW, String _country) throws Exception{
        log("Starting filling Passport with JSON object");
        DElement rightPane = getRightPane(_editorW);

        //USA
/*        if (_country.equals("United States")){

        }
*/
        DElement wrk;

        String className = "Passport_Number";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Passport Number", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Passport_Issue_Date";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Passport Issue Date", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Passport_Expiration_Date";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Passport Expiration Date", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Note";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Note", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        log("Finishing filling Passport with JSON object");
    }
    /**
     * Filling right pane containing Credit Card with Person data stored in JSON
     * @param _o JSON object with Credit Card data
     * @param _editorW Editor's window
     * @param _country Since this would be probably country-related thing...
     */
    private static void fillCreditCardWithJSONObject(JsonObject _o, DElement _editorW, String _country) throws Exception{
        log("Starting filling Credit Card with JSON object");
        DElement rightPane = getRightPane(_editorW);

        //USA
/*        if (_country.equals("United States")){

        }
*/
        Boolean fillCompleteOK = true;
        DElement wrk;

        String className = "Card_Type";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            DElement cbb = gimMeP(rightPane,new ElemD("Card Type","ComboBox"),className,1,"","a");
            String[] arr = {"Visa","Master Card","American Express","Diners Club","Discover",
                            "JCB","Carte Bancaire","Carte Blanche","Carte Bleue",
                            "Delta","Solo","Switch","Maestro","UATP"};
            Vector<String> ct = new Vector<>(Arrays.asList(arr));

            //fillCompleteOK = listDeal(_editorW,cbb,t,2,ct,null);
            //cbb.setRFComboboxValue(t,10,0);
            Proc.setCBVAdd(_editorW,cbb,t,10,0);

        }
        className = "Card_Number";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Card Number", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Validation_Code";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Validation Code", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Card_Expires_mm";
        if (_o.has(className)) {
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane,new ElemD("Card Expires","ComboBox"),className,1,"","a");
            String[] arr = {"01","02","03","04","05","06","07","08","09","10","11","12"};
            Vector<String> ct = new Vector<>(Arrays.asList(arr));

            //fillCompleteOK = listDeal(_editorW,wrk,t,0,ct,null);
            wrk.setRFComboboxValue(t,10,0);

        }
        className = "Card_Expires_yyyy";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane,new ElemD("/","ComboBox"),className,1,"","a");
            int start = 1999;
            int end = 2050;
            Vector<String> years = new Vector<>();
            for (int i = start; i <= end; i++) {
                years.add(Integer.toString(i));
            }
            //fillCompleteOK = listDeal(_editorW,wrk,t,0,years,null);
            wrk.setRFComboboxValue(t,10,0);

        }
        className = "Valid_From_mm";
        if (_o.has(className)) {
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane,new ElemD("Valid From","ComboBox"),className,1,"","a");
            String[] arr = {"01","02","03","04","05","06","07","08","09","10","11","12"};
            Vector<String> ct = new Vector<>(Arrays.asList(arr));

            //fillCompleteOK = listDeal(_editorW,wrk,t,0,ct,null);
            wrk.setRFComboboxValue(t,10,0);

        }
        className = "Valid_From_yyyy";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            List<DElement> wrkL = gimMePL(rightPane,new ElemD("/","ComboBox"),className + " list","","a");
            wrk = wrkL.get(1);
            int start = 1990;
            int end = 2017;
            Vector<String> years = new Vector<>();
            for (int i = start; i <= end; i++) {
                years.add(Integer.toString(i));
            }
            //fillCompleteOK = listDeal(_editorW,wrk,t,0,years,null);
            wrk.setRFComboboxValue(t,10,0);

        }
        className = "Card_User_Name";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Card User Name", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Issuing_Bank";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Issuing Bank", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Cust_Svc_Phone";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Cust Svc Phone", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Intl_Svc_Phone";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Intl Svc Phone", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "PIN_Number";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("PIN Number", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        //move vertical scrollbar to the bottom
        int edWid = _editorW.getWindowSize().width;
        int edHei = _editorW.getWindowSize().height;
        offsetClick(_editorW,edWid / 2 - 10, edHei / 2 - 10);
        for (int i = 0; i < 11; i++){
            MouseHandler.clickLeft();
        }

        className = "Credit_Limit";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Credit Limit", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Interest_Rate";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Interest Rate", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }

        className = "Note";
        if (_o.has(className)) {
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Note", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);

        }

        if (fillCompleteOK) {
            log("All filled OK");
        } else {
            throw new Exception("Some fields were not filled. See warnings.");
        }
        log("Finishing filling Credit Card with JSON object");
    }

    /**
     * Perform listDeal with standard calculated offset parameters
     * This procedure lists combobox up, then selects desired value
     * Combobox should not be opened
     * @param _editorW Editor DElement
     * @param _combobox Target combobox DElement
     * @param _text Target item text to find in combobox
     * @param _shrt If 0, there will be no Unlisted items; if 1, there are and have short names (Unl), if 2, there are and have full names (Unlisted)
     * @param _items Vector with String items (excluding Unlisted things)
     * @param _addedItems Vector with String items which were added already (for _shrt == 1 or 2)
     *
     * @return true is all was fine
     * @throws Exception if something went critically wrong
     */
    private static boolean listDeal(DElement _editorW,DElement _combobox, String _text,
                                    int _shrt, Vector<String> _items, Vector<String> _addedItems) throws Exception{
        _combobox.click();
        //calculate _offsetX
        int offsetX = _combobox.getWindowSize().width / 2 - 7;
        //calculate _offsetYUp
        int offsetYUp = -_combobox.getWindowSize().height / 2 + 34;
        //calculate _offsetYDown
        int offsetYDown = _combobox.getWindowSize().height / 2 - 7;
        return listDeal(_editorW,_combobox,_text,_shrt,_items,offsetX,offsetYDown,offsetYUp,_addedItems);
    }

    private static boolean listDeal(DElement _editorW,DElement _combobox, String _text, int _shrt,
                                    Vector<String> _items, int _offsetX, int _offsetYDown,
                                    int _offsetYUp, Vector<String> _addedItems) throws Exception
    {
        boolean fillCompleteOK = true;

        DElement wrk = gimMeP(_combobox,_combobox.getName(),"List",1,"","ca");
        try{
            if (_text.isEmpty()){
                throw new Exception(_combobox.getName() + " property value is empty");
            }
            String unlistedItem = "(Unlisted)";
            String deleteUnlistedItem = "(Delete)";
            if (_shrt == 1) {
                unlistedItem = "(Unl)";
                deleteUnlistedItem = "(Del)";
            }

            if ((_text.equals(unlistedItem) || _text.equals(deleteUnlistedItem))&&(_shrt != 0)){
                throw new Exception("Incorrect " + _combobox.getName() + " property value: " + _text);
            }
            Vector<String> fullStack = _items;
            if (_shrt != 0){
                fullStack.add(unlistedItem);
                if (_addedItems != null) {
                    fullStack.addAll(_addedItems);
                    fullStack.add(deleteUnlistedItem);
                }
            }
            if (fullStack.contains(_text)){
                //reset card state
                offsetClick(_combobox,_offsetX,_offsetYUp);
                //continue
                int ind = fullStack.indexOf(_text);
                if (ind > 9) {
                    offsetClick(_combobox,_offsetX,_offsetYUp);
                    for (int i = 1; i <= ind - 8; i++){
                        offsetClick(_combobox,_offsetX,_offsetYUp);
                        sleepy(0.6);
                    }
                }
                wrk = gimMeP(wrk,_text,_text,1,"","ca");
                wrk.click();
            }
            else{
                if (_shrt == 0) {
                    throw new Exception("Incorrect value: " + _text);
                }
                int ind = fullStack.indexOf(unlistedItem);
                offsetClick(_combobox,_offsetX,_offsetYDown);
                for (int i = 1; i <= ind - 8; i++){
                    offsetClick(_combobox,_offsetX,_offsetYDown);
                    sleepy(0.6);
                }
                DElement hwrk = gimMeP(wrk,unlistedItem,unlistedItem,1,"","ca");
                hwrk.click();
                sleepy(2);
                hwrk = gimMeP(_editorW,new ElemD("RoboForm","#32770"),"New Item dialog",2,"","a");
                DElement hhwrk = gimMeP(hwrk,new ElemD("Enter new value here:","Edit"),"Edit",1,"","a");
                hhwrk.click();
                hhwrk.setEditValue(_text);
                hhwrk = gimMeP(hwrk, new ElemD("OK","Button","1"),"OK Button",1,"","a");
                hhwrk.click();
            }
        } catch (Exception e) {
            logW("Field " + _combobox.getName() + " was not filled successfully");
            fillCompleteOK = false;
        }

        return fillCompleteOK;
    }
    /**
     * Filling right pane containing Bank Account with Person data stored in JSON
     * @param _o JSON object with Bank Account data
     * @param _editorW Editor's window
     * @param _country Since this would be probably country-related thing...
     */
    private static void fillBankAccountWithJSONObject(JsonObject _o, DElement _editorW, String _country) throws Exception{
        log("Starting filling Bank Account with JSON object");
        DElement rightPane = getRightPane(_editorW);

        //USA
/*        if (_country.equals("United States")){

        }
*/
        Boolean fillCompleteOK = true;
        DElement wrk;

        String className = "Bank_Name";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Bank Name", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
            wrk.setEditValue(t);
        }
        className = "Account_Number";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Account Number", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Account_Type";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane,new ElemD("Account Type","ComboBox"),className,1,"","a");
//            wrk.click();
//            wrk = gimMeP(wrk,"Account Type","List",1,"","ca");
//            try{
//                if (t.isEmpty()){
//                    throw new Exception("Account_Type property value is empty");
//                }
//                String unlistedItem = "(Unlisted)";
//                String deleteUnlistedItem = "(Delete)";
//
//                if (t.equals(unlistedItem) || t.equals(deleteUnlistedItem)){
//                    throw new Exception("Incorrect Card_Type property value: " + t);
//
//                }
//                String[] arr = {"Checking","Savings","Money Manager",unlistedItem};
//                Vector<String> ct = new Vector<>(Arrays.asList(arr));
//                if (ct.contains(t)){
//                    int ind = ct.indexOf(t);
//                    if (ind > 9) {
//                        offsetClick(wrk,69,196);
//                        for (int i = 1; i <= ind - 8; i++){
//                            offsetClick(wrk,69,196);
//                            sleepy(0.6);
//                        }
//                    }
//                    wrk = gimMeP(wrk,t,t,1,"","ca");
//                    wrk.click();
//                }
//                else{
//                    int ind = ct.indexOf(unlistedItem);
//                    offsetClick(wrk,69,198);
//                    for (int i = 1; i <= ind - 8; i++){
//                        offsetClick(wrk,69,196);
//                        sleepy(0.6);
//                    }
//                    DElement hwrk = gimMeP(wrk,unlistedItem,unlistedItem,1,"","ca");
//                    hwrk.click();
//                    sleepy(2);
//                    hwrk = gimMeP(_editorW,new ElemD("RoboForm","#32770"),"New Item dialog",2,"","a");
//                    DElement hhwrk = gimMeP(hwrk,new ElemD("Enter new value here:","Edit"),"Edit",1,"","a");
//                    hhwrk.click();
//                    hhwrk.setEditValue(t);
//                    hhwrk = gimMeP(hwrk, new ElemD("OK","Button","1"),"OK Button",1,"","a");
//                    hhwrk.click();
//                }
//            } catch (Exception e) {
//                logW("Field " + className + " was not filled successfully");
//                fillCompleteOK = false;
//            }
            Proc.setCBVAdd(_editorW,wrk,t,10,0);
        }
        className = "Routing_Number";
        if (_o.has(className)) {
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Routing Number", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Bank_Branch";
        if (_o.has(className)) {
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Bank Branch", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Bank_Phone";
        if (_o.has(className)) {
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Bank Phone", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Bank_Address";
        if (_o.has(className)) {
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Bank Address", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "SWIFT";
        if (_o.has(className)) {
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("SWIFT", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Interest_Rate";
        if (_o.has(className)) {
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Interest Rate", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Account_Owner";
        if (_o.has(className)) {
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Account Owner", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Bank_PIN_Code";
        if (_o.has(className)) {
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Bank PIN Code", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }

        className = "Note";
        if (_o.has(className)) {
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Note", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);

        }

        if (fillCompleteOK) {
            log("All filled OK");
        } else {
            throw new Exception("Some fields were not filled. See warnings.");
        }
        log("Finishing filling Bank Account with JSON object");
    }
    private static String[] getUSAStateList(){
        return new String[] {"AK","AL","AR","AZ","CA","CO","CT","DC","DE","FL","GA","HI","IA","ID","IL",
                "IN","KS","KY","LA","MA","MD","ME","MI","MN","MO","MS","MT","NC","ND","NE",
                "NH","NJ","NM","NV","NY","OH","OK","OR","PA","RI","SC","SD","TN","TX","UT",
                "VA","VT","WA","WI","WV","WY","-Mil-","AA","AE","AP","-Etc-","AS","GU","MP",
                "PR","PW","VI"};
    }
    /**
     * Filling right pane containing Car with Address data stored in JSON
     * @param _o JSON object with Car data
     * @param _editorW Editor's window
     * @param _country Since this would be probably country-related thing...
     */
    private static void fillCarWithJSONObject(JsonObject _o, DElement _editorW, String _country) throws Exception{
        log("Starting filling Car with JSON object");
        DElement rightPane = getRightPane(_editorW);

        //USA
/*        if (_country.equals("United States")){

        }
*/
        Boolean fillCompleteOK = true;
        DElement wrk;

        String className = "Plate_Number";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Plate", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Plate_State";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane,new ElemD("Car Plate State","ComboBox"),className,1,"","a");
            Vector<String> zips = new Vector<>(Arrays.asList(getUSAStateList()));
            //fillCompleteOK = listDeal(_editorW,wrk,t,0,zips,null);
            wrk.setRFComboboxValue(t,10,0);

        }
        className = "Make";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Make", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Model";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Model", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Year";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Year", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "VIN";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("VIN", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Note";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Note", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        if (fillCompleteOK) {
            log("All filled OK");
        } else {
            throw new Exception("Some fields were not filled. See warnings.");
        }
        log("Finishing filling Car with JSON object");
    }
    /**
     * Filling right pane containing Authentication with Address data stored in JSON
     * @param _o JSON object with Authentication data
     * @param _editorW Editor's window
     * @param _country Since this would be probably country-related thing...
     */
    private static void fillAuthenticationWithJSONObject(JsonObject _o, DElement _editorW, String _country) throws Exception{
        log("Starting filling Authentication with JSON object");
        DElement rightPane = getRightPane(_editorW);

        //USA
/*        if (_country.equals("United States")){

        }*/

        DElement wrk;

        String className = "Favorite_User_ID";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Favorite User ID", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Favorite_Password";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Favorite Password", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Password_Question";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Password Question", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Password_Answer";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Password Answer", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        className = "Note";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk = gimMeP(rightPane, new ElemD("Note", "Edit"), className, 1, "", "a");
            wrk.click();
            wrk.setEditValue(t);
        }
        log("Finishing filling Authentication with JSON object");
    }
    /**
     * Filling right pane containing Custom with Address data stored in JSON
     * @param _o JSON object with Custom data
     * @param _editorW Editor's window
     * @param _country Since this would be probably country-related thing...
     */
    private static void fillCustomWithJSONObject(JsonObject _o, DElement _editorW, String _country) throws Exception{
        log("Starting filling Custom with JSON object");
        DElement rightPane = getRightPane(_editorW);

        //USA
/*        if (_country.equals("United States")){

        }
*/

        DElement wrk = gimMeP(rightPane, new ElemD("Other Matching Strings", "Edit"), "First field", 1, "", "a");

        String className = "Other_Matching_Strings_5";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk.click();
            KeyboardHandler.sendKeysToElement(wrk,true,"{TAB 14}");
            KeyboardHandler.sendKeysHere(t);
//            sendKeysWr(wrk, "{TAB 14}" + t);
        }
        className = "Field_Value_5";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk.click();
            KeyboardHandler.sendKeysToElement(wrk,true,"{TAB 13}");
            KeyboardHandler.sendKeysHere(t);
//            sendKeysWr(wrk, "{TAB 13}" + t);
        }
        className = "Field_Name_5";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk.click();
            KeyboardHandler.sendKeysToElement(wrk,true,"{TAB 12}");
            KeyboardHandler.sendKeysHere(t);
//            sendKeysWr(wrk, "{TAB 12}" + t);
        }
        className = "Other_Matching_Strings_4";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk.click();
            KeyboardHandler.sendKeysToElement(wrk,true,"{TAB 11}");
            KeyboardHandler.sendKeysHere(t);
//            sendKeysWr(wrk, "{TAB 11}" + t);
        }
        className = "Field_Value_4";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk.click();
            KeyboardHandler.sendKeysToElement(wrk,true,"{TAB 10}");
            KeyboardHandler.sendKeysHere(t);
//            sendKeysWr(wrk, "{TAB 10}" + t);
        }
        className = "Field_Name_4";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk.click();
            KeyboardHandler.sendKeysToElement(wrk,true,"{TAB 9}");
            KeyboardHandler.sendKeysHere(t);
            //sendKeysWr(wrk, "{TAB 9}" + t);
        }
        className = "Other_Matching_Strings_3";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk.click();
            KeyboardHandler.sendKeysToElement(wrk,true,"{TAB 8}");
            KeyboardHandler.sendKeysHere(t);
            //sendKeysWr(wrk, "{TAB 8}" + t);
        }
        className = "Field_Value_3";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk.click();
            KeyboardHandler.sendKeysToElement(wrk,true,"{TAB 7}");
            KeyboardHandler.sendKeysHere(t);
            //sendKeysWr(wrk, "{TAB 7}" + t);
        }
        className = "Field_Name_3";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk.click();
            KeyboardHandler.sendKeysToElement(wrk,true,"{TAB 6}");
            KeyboardHandler.sendKeysHere(t);
//            sendKeysWr(wrk, "{TAB 6}" + t);
        }
        className = "Other_Matching_Strings_2";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk.click();
            KeyboardHandler.sendKeysToElement(wrk,true,"{TAB 5}");
            KeyboardHandler.sendKeysHere(t);
//            sendKeysWr(wrk, "{TAB 5}" + t);
        }
        className = "Field_Value_2";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk.click();
            KeyboardHandler.sendKeysToElement(wrk,true,"{TAB 4}");
            KeyboardHandler.sendKeysHere(t);
//            sendKeysWr(wrk, "{TAB 4}" + t);
        }
        className = "Field_Name_2";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk.click();
            KeyboardHandler.sendKeysToElement(wrk,true,"{TAB 3}");
            KeyboardHandler.sendKeysHere(t);
//            sendKeysWr(wrk, "{TAB 3}" + t);
        }
        className = "Other_Matching_Strings_1";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk.click();
            KeyboardHandler.sendKeysToElement(wrk,true,"{TAB 2}");
            KeyboardHandler.sendKeysHere(t);
            //sendKeysWr(wrk, "{TAB 2}" + t);
        }
        className = "Field_Value_1";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk.click();
            KeyboardHandler.sendKeysToElement(wrk,true,"{TAB}");
            KeyboardHandler.sendKeysHere(t);
//            sendKeysWr(wrk, "{TAB}" + t);
        }
        className = "Field_Name_1";
        if (_o.has(className)){
            String t = _o.get(className).getAsString();
            wrk.click();
            wrk.setEditValue(t);
        }
        log("Finishing filling Custom with JSON object");
    }

    public static JsonObject getJSONInstance(String _fileName, String _className){
        File iniF = new File(Proc.itemTemplatesPath + _fileName);
        JsonObject retO = null;
        try
        {
            JsonParser parser = new JsonParser();
            JsonObject o = (JsonObject) parser.parse(new FileReader(iniF));

            if (o.has(_className)) {
                retO = o.get(_className).getAsJsonObject();
            } else {
                throw new Exception("Cannot find _classname=" + _className + " at " + iniF.getAbsoluteFile());
            }

        } catch (Exception e) {
            logE("JSON parser error: " + e.getMessage());
        }
        return retO;
    }

    /**
     * Returns Right pane of Editor (where different fields are allocated)
     * This is made to separate method because finding RP is not obvious and may be changed in the future,
     * but this method is used in several places.
     * @param _editor DElement for Editor window
     * @return DElement of right pane
     * @throws Exception is something goes bad
     */
    private static DElement getRightPane(DElement _editor) throws Exception{
        //DElement res = gimMeP(eW, EditorD.rightPane,"Right pane",2,"","n");
        return gimMeP(_editor,"","Right pane",2,"","ca");
    }
    public static void ensureMinimalEditorWidth(int _minWidth) throws Exception{
        DElement eW = null;
        try{
            eW = gL(null,"Editor",1,"c",EditorD.editorW.cName);
        } catch (Exception e){
            sleepy(5);
            try{
                eW = gL(null,"Editor",1,"c",EditorD.editorW.cName);
            } catch (Exception e1) {
                eW = gL(null,"Editor",1,"!c",EditorD.editorW.cName);
            }

        }
        if (eW.getWindowSize().width < _minWidth) {
            int curWPx = WindowHandler.getWindowRECT(eW).left;
            int curWPy = WindowHandler.getWindowRECT(eW).top;
            int curHei = eW.getWindowSize().height;
            WindowHandler.changeWindowPosition(eW,curWPx,curWPy,_minWidth,curHei);
        }
//        if (eW.getWindowSize().width < _minWidth) {
//            log("Enlarging Editor window size");
//
//            Actions builder = new Actions(_webDriver);
//            Action enter = builder
//                    .moveToElement(eW)
//                    .moveByOffset(eW.getSize().width / 2 - 5, eW.getSize().height / 2 - 100)
//                    .build();
//            enter.perform();
//            sleepy(2);
//            //sendGPCommandToDriver(true,_webDriver,"/session/AwesomeSession/buttondown");
//            //sendGPCommandToDriver(true,_webDriver,"/session");
//            String offsetX = Integer.toString(_minWidth - eW.getSize().width);
//            String offsetY = Integer.toString(0);
//            String newX = Integer.toString(eW.getRect().x + _minWidth);
//            Proc.autoIt3ScriptGenerator("HoldAndMove",curX + " " + curY + " " + newX + " " + curY);
//            Proc.autoIt3ScriptGenerator("HoldAndMoveOffset",offsetX + " " + offsetY,"Home - RoboForm Editor");
//            ProcessBuilder p = new ProcessBuilder("C://Users//Autotester//gov.bat");
//            Process shell = p.start();
//            sleepy(2);
//            enter = builder
//                    .moveByOffset(_minWidth - eW.getSize().width,0)
//                    .build();
//            enter.perform();
            //sendGPCommandToDriver(true,_webDriver,"/session/AwesomeSession/buttonup");

    }

    /**
     * Turn off sync for RF8
     * OBSOLETE OPTION: Turn Off was removed from Sync menu
     * @throws Exception if something goes wrong
     */
    public static void syncTurnOff() throws Exception{
        logString = "Turn sync off";
        setLogBlockPrefix("syncTurnOff");
        logW("Obsolete: Starting procedure");
/*        EditorProc.runRF8Editor();
        sleepy(2);
        log("Clicking RF button");
        //WebElement RfEW = sibAttachDriverCN(_driver, EditorD.editorW.cName);
        WebElement RfEW = gimMeP(_webDriver,EditorD.editorW,"Editor window",2,"","na");
        //
        sleepy(1);

        WebElement wrk = RfEW;
        //WebElement RFM = sibAttachToN(wrk, EditorD.grandRFB.name);
        WebElement RFM = gimMe(wrk,EditorD.grandRFB,"Grand RF button");
        //
        sleepy(1);
        Actions builder = new Actions(_webDriver);
        RFM.click();

        log("Clicking Sync");
        sleepy(1);
        Action enter = builder
                .moveToElement(RFM)
                .moveByOffset(10, 75)
                .click()
                .build();
        enter.perform();

        log("Clicking Turn off sync");
        sleepy(1);
        enter = builder
                .moveByOffset(200, 185)
                .click()
                .build();
        enter.perform();

        log("Turn sync off confirmation dialog handling");
        WebElement tWrk = gimMe(RfEW,ConfirmTurnSyncOffDlg.mainW,"Confirm dialog");
        wrk = gimMe(tWrk,ConfirmTurnSyncOffDlg.btnYes,"Yes button");
        wrk.click();
        sleepy(1);

        EditorProc.closeRF8EditorSoft(_webDriver);
  */      log("Procedure complete");
    }
    /**
     * <p>Verifies whether it is enable to create specified safenote or not</p>
     * @param positive (default True) if False, it is expected that the specified safenote already exists
     * @throws Exception some possible exception
     */
    public static void verifySnCRF8(Boolean positive) throws Exception{
        //runRF8Editor();
        setLogBlockPrefix("verifySnCRF8");
        Boolean toEv = createSafenoteRF8(nameSNDefault);
        logString = "VerifySnCRF8";
        if (toEv == positive){

            if (positive){
                log("Safenote was created - as expected");
            }
            else
            {
                log("Safenote was not created - as expected");
            }
        }
        else
        {
            if (positive){
                throw new Exception(logString + "Safenote was not created, it is unexpected");
            }
            else
            {
                throw new Exception(logString + "Safenote was created, it is unexpected");
            }
        }

    }
    /**
     * <p>Default positive=true procedure for verifySnCRF8</p>
     * @throws Exception exception
     */
    public static void verifySnCRF8() throws Exception{
        verifySnCRF8(true);
    }
}
