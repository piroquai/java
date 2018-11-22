package jnaex.RFWin;

import java.io.IOException;
import java.util.List;

import daima.DElement;
import jnaex.RFWin.SearchPresets.RF;

public class ApplicationProc extends Proc{
    private static String logString = "Application Proc";
    private static String logPrefixBlock = "AppProc" + Proc.logSeparator;
    public static void log(String s){ //normal log
        Proc.log(logPrefixBlock + logString + Proc.logSeparator + s);
    }
    public static void logE(String s){ //error log
        Proc.logE(logPrefixBlock + logString + Proc.logSeparator + s);
    }
    public static void logW(String s){
        Proc.logW(logPrefixBlock + logString + Proc.logSeparator + s);
    }

    private static String appLTPath="C:/Users/Autotester/AppData/Roaming/ICQ/bin/";

    /*
        script string handle path moves handler to a series of window jumps to element deep inside application window
        Usage: {subwindow name}[counter of the same subwindows (0 for the first)]
               ~{subwindow classname}[counter of the same subwindows (0 for the first)]
     */
    public static void setAppLTPath(String s){
        appLTPath = s;
    }
    private static String appLTExec="icq.exe";
    public static void setAppLTExec(String s){
        appLTExec = s;
    }
    private static String appLTExecWinName="ICQ";
    public static void setAppLTExecWinName(String s){
        appLTExecWinName = s;
    }
    private static String appLTExecWinCName="Qt5QWindowIcon";
    public static void setAppLTExecWinCName(String s){
        appLTExecWinCName = s;
    }
    private static String appLTBadMarkerPath="{}[1]{}[2]{}[1]{}[2]{}[1]";
    public static void setAppLTBadMarkerPath(String s){
        appLTBadMarkerPath = s;
    }
    private static String appLTBadMarkerName="Enter phone marker";
    public static void setAppLTBadMarkerName(String s){
        appLTBadMarkerName = s;
    }
    private static String appLTBadHandlePath="{}[1]{}[2]{}[1]{}[3]";
    public static void setAppLTBadHandlePath(String s){
        appLTBadHandlePath = s;
    }
    private static String appLTBadHandleName="StartWindowChangeLoginType";
    public static void setAppLTBadHandleName(String s){
        appLTBadHandleName = s;
    }
    private static String appLTGoodMarkerPath="{}[2]{}[2]{}[1]{}[2]{}[1]";
    public static void setAppLTGoodMarkerPath(String s){
        appLTGoodMarkerPath = s;
    }
    private static String appLTGoodMarkerName="Enter UIN or Email";
    public static void setAppLTGoodMarkerName(String s){
        appLTGoodMarkerName = s;
    }
    private static String appLTLoginFieldPath="{}[2]{}[2]{}[1]{}[2]{}[1]{}[2]{}[1]{}[1]";
    public static void setAppLTLoginFieldPath(String s){
        appLTLoginFieldPath = s;
    }
    private static String appLTLoginFieldName="StartWindowUinField";
    public static void setAppLTLoginFieldName(String s){
        appLTLoginFieldName = s;
    }
    private static String appLTPasswordFieldPath="{}[2]{}[2]{}[1]{}[2]{}[1]{}[2]{}[1]{}[1]";
    public static void setAppLTPasswordFieldPath(String s){
        appLTPasswordFieldPath = s;
    }
    private static String appLTPasswordFieldName="StartWindowPasswordField";
    public static void setAppLTPasswordFieldName(String s){
        appLTPasswordFieldName = s;
    }
    private static String appLTSubmitPath="{}[2]{}[2]{}[1]{}[2]{}[1]{}[3]";
    public static void setAppLTSubmitPath(String s){
        appLTSubmitPath = s;
    }
    private static String appLTSubmitName="StartWindowLoginButton";
    public static void setAppLTSubmitName(String s){
        appLTSubmitName = s;
    }
    private static String appLTBadAdditionalHandlePath="{}[1]{}[2]{}[1]{}[2]{}[1]{}[2]{}[1]{}[1]";
    public static void setAppLTBadAdditionalHandlePath(String s){
        appLTBadAdditionalHandlePath = s;
    }
    private static String appLTBadAdditionalHandleName="StartWindowPasswordField";
    public static void setAppLTBadAdditionalHandleName(String s){
        appLTBadAdditionalHandleName = s;
    }
    private static String appLTCloseButtonPath = "{}[1]{}[1]{}[4]";
    public static void setAppLTCloseButtonPath(String s) {
        appLTCloseButtonPath = s;
    }

    private static Process shell;
    public static void launchAppLT(){
        setLogBlockPrefix("launchAppLT");
        ProcessBuilder pb = new ProcessBuilder(appLTPath + appLTExec, "");
        try {
            shell = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void killAppLT(){
        setLogBlockPrefix("killAppLT");
        try{
            shell.destroy();
            DElement aW = gL(null,appLTExecWinName,3,"NC",appLTExecWinName,appLTExecWinCName);
            DElement clB = calculateParent(aW,appLTCloseButtonPath);
            clB.click();
            sleepy(1);
        } catch (Exception e) {
            logW("Application closure exception:");
            logW(e.getStackTrace().toString());
        }
    }

    /**
     * cut the string before specific char (cuts char too)
     * # String s = "abra kadabra";
     * # String res = cutBeforeChar(s,"r");
     * >> res: "a kadabra";
     * @param _string string to process
     * @param _char string (represents 1 char) to look for
     * @return cut string
     */
    private static String cutBeforeChar(String _string, String _char){
        int st = _string.indexOf(_char) + 1;
        int end = _string.length();
        if (end - st > 0){
            char[] buf = new char[end - st];
            _string.getChars(st,end,buf,0);
            return new String(buf);
        }
        else{
            return "";
        }

    }
    public static DElement calculateParent(DElement _dElement,String _lookingScript) throws Exception {
        return calculateParent(1,_dElement,_lookingScript);
    }

    public static DElement calculateParent(int _attempts, DElement _dElement,String _lookingScript) throws Exception{
        DElement res = null;
        String s = _lookingScript;
        log("Calculate parent: script is: " + _lookingScript + "   attempts: " + _attempts);
        String nm;
        int ind;
        Boolean cntC; // continue calculation
        int i = 0;
        do{
            cntC = false;
            if ((s.indexOf("{") == 0)||(s.indexOf("~") == 0)){
                String symStart = "{";
                String symStop = "}";
                if (s.indexOf("~") == 0){
                    symStart = "~";
                    symStop = "~";
                }
                cntC = true;

                int st;
                if (symStart.equals("~")){
                    st = 0;
                    s = cutBeforeChar(s,symStop);
                }
                else{
                    st = s.indexOf(symStart) + 1;
                }
                int end = s.indexOf(symStop);
                if (end - st > 0){
                    char[] buf = new char[end - st];
                    s.getChars(st,end,buf,0);
                    nm = new String(buf);
                }
                else
                {
                    nm = "";
                }
                s = cutBeforeChar(s,symStop);
                if (s.indexOf("[") == 0){
                    int st1 = s.indexOf("[") + 1;
                    int end1 = s.indexOf("]");
                    if (end1 - st1 > 0){
                        char[] buf1 = new char[end1-st1];
                        s.getChars(st1,end1,buf1,0);
                        try{
                            ind = Integer.parseInt(new String(buf1));
                        }
                        catch (Exception e){
                            throw new Exception(e.getMessage());
                        }

                        if (symStart.equals("~")){
                            if (ind > 0){
                                List<DElement> juy;
                                if (i > 0){
                                    //juy = gimMePL(res,new ElemD("",nm),nm,"x","na");
                                    juy = gVL(res,new SP(nm,"c",nm),_attempts);

                                }
                                else
                                {
                                    //juy = gimMePL(_dElement,new ElemD("",nm),nm,"x","na");
                                    juy = gVL(_dElement, new SP(nm,"c",nm),_attempts);

                                }

                                res = juy.get(ind);
                            }
                            else
                            {
                                if (i > 0){
                                    //res = gimMeP(res,new ElemD("",nm),nm,1,"x","na");
                                    res = gL(res,new SP(nm,"c",nm),_attempts);

                                }
                                else{
                                    //res = gimMeP(_dElement,new ElemD("",nm),nm,1,"x","na");
                                    res = gL(_dElement, new SP(nm,"c",nm),_attempts);

                                }

                            }
                        }
                        else
                        {
                            if (ind > 0){
                                List<DElement> juy;
                                if (i > 0){
                                    //juy = gimMePL(res,new ElemD(nm),nm,"x","ca");
                                    juy = gVL(res, new SP(nm,"n",nm),_attempts);

                                }
                                else
                                {
                                    //juy = gimMePL(_dElement,new ElemD(nm),nm,"x","ca");
                                    juy = gVL(_dElement,new SP(nm,"n",nm),_attempts);

                                }

                                res = juy.get(ind);
                            }
                            else
                            {
                                if (i > 0){
                                    //res = gimMeP(res,nm,nm,1,"x","ca");
                                    res = gL(res,new SP(nm,"n",nm),_attempts);

                                }
                                else{
                                    //res = gimMeP(_dElement,nm,nm,1,"x","ca");
                                    res = gL(_dElement,new SP(nm,"n",nm),_attempts);

                                }

                            }
                        }
                        s = cutBeforeChar(s,"]");
                        i++;
                    }

                }
                else{
                    throw new Exception("Parsing error. The rest is: " + s);
                }
            }
        }while ((cntC)&&(!(s.isEmpty())));
        return res;
    }
//    private static final Boolean needAdditionalHandle = true;
    private static final Boolean needAdditionalHandle = false;
    public static void handleBadAppLT() throws Exception{
        logString = "Handle possible bad condition";
        setLogBlockPrefix("handleBadAppLT");
        log("Starting procedure");
        //DElement aW = gimMeP(null,new ElemD(appLTExecWinName,appLTExecWinCName),appLTExecWinName,2,"","a");
        DElement aW = gL(null, new SP("Main application window","nc",appLTExecWinName,appLTExecWinCName),2);

        try{
            log("Looking for terms and conditions prompt");
            DElement tWrk = calculateParent(aW,"{}[1]{}[0]{}[0]{}[0]{I AGREE}[0]");
            log("Terms and conditions prompt found, let's agree");
            tWrk.click();
        } catch (Exception e){
            logW("Terms and conditions prompt was not found or handling error");
        }


        try{
            DElement tWrk = calculateParent(aW,appLTBadMarkerPath);
            //gimMeP(tWrk,appLTBadMarkerName,"Bad marker",1,"","ca");
            calculateParent(tWrk,appLTBadMarkerName);

            //DElement wrk = gimMeP(tWrk,new ElemD(appLTBadMarkerName),"Bad marker",1,"","ca");
            tWrk = calculateParent(aW,appLTBadHandlePath);
            //DElement wrk = gimMeP(tWrk,appLTBadHandleName,"Bad handler",1,"","ca");
            DElement wrk = calculateParent(tWrk,appLTBadHandleName);

            sleepy(0.5);
            log("Clicking on handler");
            wrk.click();
            sleepy(4);
            tWrk = calculateParent(aW,appLTGoodMarkerPath);
            //gimMeP(tWrk,appLTGoodMarkerName,"Good marker",1,"","ca");
            calculateParent(tWrk,appLTGoodMarkerName);

//            if (needAdditionalHandle){
//                tWrk = calculateParent(aW,appLTBadAdditionalHandlePath);
//                wrk = gimMeP(tWrk, appLTBadAdditionalHandleName,"Additional bad handler",1,"","ca");
//                wrk.click();
//                sleepy(4);
//            }
        } catch (Exception e){
            log("App is OK");
        }
        log("Procedure complete");
    }
    public static void clickSave() throws Exception{
        logString = "Click save";
        setLogBlockPrefix("clickSave");
        log("Starting procedure");
        //DElement aW = gimMeP(null,new ElemD(appLTExecWinName,appLTExecWinCName),appLTExecWinName,2,"","a");
        DElement aW = gL(null,new SP("Main application window","nc",appLTExecWinName,appLTExecWinCName),2);

        log("Substage I");
        DElement wrk;
        if (needAdditionalHandle){
            try{
                DElement tWrk = calculateParent(aW,appLTBadAdditionalHandlePath);
                wrk = gimMeP(tWrk, appLTBadAdditionalHandleName,"Additional bad handler",1,"","ca");
                wrk.click();
            } catch (Exception e){
                logW("Additional handle error");
            }
            sleepy(4);
        }
        log("Substage II");
        try {
            //DElement tWrk = gimMeP(aW,"","pane",2,"","ca");
            DElement tWrk = gL(aW,RF.paneLT,2);

            //wrk = gimMeP(tWrk, "Save", "Save button", 2, "", "ca");
            wrk = gL(tWrk,RF.saveT,2);

            wrk.click();
        } catch (Exception e){
            logW("Lower toolbar was not attached automatically. Performing hard attach.");
            log("Substage II-a");
            EditorProc.openTBI();
            setLogBlockPrefix("clickSave-II");
            //EditorProc.clickTBIBrowser(_webDriver,"TestLoginApplication: TestLoginApplication");
            log("Substage II-b");
            EditorProc.clickTBIBrowser("icq: ICQ");
            setLogBlockPrefix("clickSave-III");
            //tWrk = gimMeP(null,new ElemD("","#32770"),"pane",2,"x","na");
            log("Substage II-c");
            aW.setForeground();
            sleepy(1);
            wrk = gL(aW,RF.paneLT,2);

            //wrk = gimMeP(tWrk, "Save", "Save button", 2, "", "ca");
//            wrk = gL(wrk, RF.saveT,2);
//
//            wrk.click();
            log("Substage II-d");
            for (DElement z : gVL(wrk,RF.saveT,1)){
                try{
                    z.click();
                } catch (Exception ex){
                    //do nothing
                }

            }

//            try{
//                log("Clicking once again");
//                sleepy(1);
//                wrk = gL(aW,RF.paneLT,2);
//                wrk = gL(wrk, RF.saveT,2);
//                wrk.click();
//                log("Clicking complete");
//            } catch (Exception e1){
//                log("Hurray! Clicking failed!");
//            }
        }

        sleepy(4);
        log("Procedure complete");
    }
    public static void performLoginSave(String _name, String _password) throws Exception{
        logString = "Perform save login";
        setLogBlockPrefix("performLoginSave");
        log("Starting procedure");
        //DElement aW = gimMeP(null,new ElemD(appLTExecWinName,appLTExecWinCName),appLTExecWinName,2,"","a");
        DElement aW = gL(null, new SP("Application main window","nc",appLTExecWinName,appLTExecWinCName),2);

        log("Calculate parent for appLTLoginFieldPath");
        DElement tWrk = calculateParent(aW,appLTLoginFieldPath);
        //DElement wrk = gimMeP(tWrk,appLTLoginFieldName,"Login field",1,"","ca");
        log("Calculate parent for appLTLoginFieldName");
        DElement wrk = calculateParent(tWrk,appLTLoginFieldName);

        //tWrk.click();
        wrk.click();
        sleepy(1);
        wrk.setEditValue(_name);
        //sendKeysWr(tWrk,_name);
        sleepy(0.5);
        log("Calculate parent for appLTPasswordFieldPath");
        tWrk = calculateParent(aW,appLTPasswordFieldPath);
        //wrk = gimMeP(tWrk,appLTPasswordFieldName,"Password field", 1,"","ca");
        log("Calculate parent for appLTPasswordFieldName");
        wrk = calculateParent(tWrk,appLTPasswordFieldName);

        //tWrk.click();
        wrk.click();
        sleepy(1);
        //sendKeysWr(tWrk,_password);
        wrk.setEditValue(_password);
        //sendKeysWr(wrk,_password);
        sleepy(0.5);
        log("Calculate parent for appLTSubmitPath");
        tWrk = calculateParent(aW,appLTSubmitPath);
        //wrk = gimMeP(tWrk,appLTSubmitName,"Submit button",1,"","ca");
        log("Calculate parent for appLTSubmitName");
        wrk = calculateParent(tWrk,appLTSubmitName);

        //tWrk.click();
        wrk.click();
        sleepy(5);
        log("Procedure complete");
    }
    public static void handleAutoSave(String _name) throws Exception{
        logString = "Handle AutoSave window";
        setLogBlockPrefix("handleAutoSave");
        log("Starting procedure");
        DElement aSW = gL(null,RF.autoSave,2);
        DElement wrk = gL(aSW, RF.nameF,1);
        wrk.click();
        sleepy(1);
//        KeyboardHandler.sendKeysToElement(wrk,true,"^a");
//        sendKeysWr(wrk,"^a");
        sleepy(0.2);
        wrk.setEditValue(_name);
        sleepy(1);
        wrk = gimMeP(aSW,"Save","Save button",1,"e","ca");
        wrk.click();
        log("Handling 'Your login has been saved' window");
        try{
            aSW = gimMeP(null,new ElemD("","#32770"),"AutoSave dialog",2,"e","ao");
            wrk = gimMeP(aSW,"Close","Close button",1,"","ca");
            wrk.click();
        } catch (Exception e){
            log("Window was closed before handling processed :-)");
        }
        sleepy(2);
        log("Procedure complete");
    }


}
