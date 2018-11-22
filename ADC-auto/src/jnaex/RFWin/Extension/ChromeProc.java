package jnaex.RFWin.Extension;

import daima.DElement;
import jnaex.RFWin.BrowserTestBlock;
import jnaex.RFWin.Proc;

import java.util.Vector;

/**
 * Created by Autotester on 11/1/2017.
 */
public class ChromeProc {
    //NS suffix = non-Selenium (JNA-based)
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
    // NS suffix means non-Selenium handling
    public static void runChromeNS() throws Exception{
        log("Launching Chrome");
        sleepy(1);
        ProcessBuilder pb = new ProcessBuilder(BrowserTestBlock.pathChrome + BrowserTestBlock.nameChrome);
        pb.start();
        sleepy(5);
    }
    private static DElement getChromeWindow() throws Exception{
        boolean windowFound = false;
        Vector<DElement> potential = DElement.gimMePV(null,"Windows",1,"l","window");
        DElement res = null;
        int i = 0;
        while ((i < potential.size())&&(!windowFound)) {
            try {
                res = potential.get(i);
                gL(res,"Window #" + i, 1, "n", "Google Chrome");
                windowFound = true;
            } catch (Exception e) {
                i++;
            }
        }
        if (!windowFound) {
            throw new Exception("Chrome window not found");
        } else {
            return res;
        }
    }
    public static boolean isRFExtensionInstalledNS() throws Exception{
        setLP("isRFExtensionInstalledChrome");
        boolean installed = false;
        runChromeNS();
        sleepy(10);
        log("Checking already installed extension");
        DElement chr = getChromeWindow();
        log("Check RF extension installation prompt");
//        try{
//            gL(chr,"Install prompt",1,"dN","New extension added (RoboForm");
//        } catch (Exception e) {
//
//        }
        chr = gL(chr,"Nav I", 1, "n", "Google Chrome");
        DElement wrk = DElement.gimMePN(chr,"Nav II", 1, 1, "n","");
        wrk = DElement.gimMePN(wrk, "Nav III", 1, 1, "n", "");
        wrk = gL(wrk,"Nav IV",1,"ln","tool bar","main");
        try{
            wrk = gL(wrk,"Ext group",1, "n","Extensions");
            gL(wrk,"RF ext",1,"N","RoboForm");
            installed = true;
        } catch (Exception e) {
            log("RF is not installed");
        }
        if (!installed) {
            log("Checking RF installation prompt");
        }

        return installed;
    }

}
