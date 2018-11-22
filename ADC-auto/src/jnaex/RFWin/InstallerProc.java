package jnaex.RFWin;

import daima.DElement;
import jnaex.RFWin.SearchPresets.MSStore;
import jnaex.RFWin.SearchPresets.MSWindowsGeneral;
import jnaex.RFWin.SearchPresets.RF;
import jnaex.RFWin.SearchPresets.RFMarkers;
import jnaex.RFWin.SyncWindow.InstallSetSyncD;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

/**
 * Created by Autotester on 4/4/2018.
 */
public class InstallerProc extends Proc {
    /**
     * Default path to Siber Systems cleaner
     */
    private static final String pathSiberCleanerDefault = "C:/Users/AutoTester/Desktop/";
    /**
     * Default name for Siber Systems cleaner
     */
    private static final String nameSiberCleanerDefault = "clearRF.bat";

    /**
     * RF uninstaller path
     */
    private static String pathUninstaller = "C:/Users/AutoTester/Desktop/";
    public static void setPathUninstaller(String s){
        pathUninstaller = s;
    }
    /**
     * RF uninstaller name
     */
    private static String nameUninstaller = "rfwipeout.exe";
    public static void setNameUninstaller(String s) {nameUninstaller = s;}
    /**
     * RF uninstaller additional key - wipe all
     */
    private static final String addParamUninstAA = " aa";
    /**
     * RF uninstaller additional key - wipe all and license info
     */
    private static final String addParamUninstAL = " al";

    /**
     * Registy rfo-test-server injector path
     */
    private static String pathRFOTSInjector = "C:/Users/AutoTester/Desktop/";
    public static void setPathRFOTSInjector(String s){
        pathRFOTSInjector = s;
    }
    /**
     * Registry rfo-test-server injector name
     */
    private static final String nameRFOTSInjector = "setRFOTSon.bat";

    /**
     * RF8 Installer path
     */
    private static String pathRF8Installer = "C:/Users/AutoTester/Desktop/";
    public static void setPathRF8Installer(String s){
        pathRF8Installer = s;
    }
    /**
     * RF8 Installer name
     */
    private static String nameRF8Installer = "RoboForm-v8-Setup.exe";
    public static void setNameRF8Installer(String s){
        nameRF8Installer = s;
    }
    /**
     * RF8 Official Installer name
     */
    private static String nameRF8InstallerOfficial = "8-3-9-5-official-RoboForm-Setup.exe";
    public static void setNameRF8InstallerOfficial(String s){
        nameRF8InstallerOfficial = s;
    }
    /**
     * RF8 Additional parameter - AutoUpdate (on automatic RF update)
     */
    private static final String addParamRF8AutoUpdate = " /autoupdate";


    /**
     * Run specified RF8 Installer
     */
    private static void runRF8Installer() throws IOException {
        setLogBlockPrefix("runRF8Installer");
        ProcessBuilder pb = new ProcessBuilder(pathRF8Installer + nameRF8Installer);
        EditorProc.shell = pb.start();
    }
    /**
     * Run official RF8 Installer
     */
    private static void runRF8InstallerOfficial() throws  IOException{
        setLogBlockPrefix("runRF8InstallerOfficial");
        ProcessBuilder pb = new ProcessBuilder(pathRF8Installer + nameRF8InstallerOfficial);
        EditorProc.shell = pb.start();
    }
    /**
     * Run specified RF8 Installer in AutoUpdate mode
     */
    public static void runRF8InstallerAutoUpdate() throws IOException{
        ProcessBuilder pb = new ProcessBuilder(pathRF8Installer + nameRF8Installer, addParamRF8AutoUpdate);
        EditorProc.shell = pb.start();
    }
    /**
     * Run specified uninstaller in wipe all mode
     */
    public static void runRFUninstallerAA() throws IOException{
        setLogBlockPrefix("runRFUninstallerAA");
        ProcessBuilder pb = new ProcessBuilder(pathUninstaller + nameUninstaller, addParamUninstAA);
        EditorProc.shell = pb.start();
    }

    /**
     * <p>Inject rfo-test-server registry entry</p>
     */
    public static void injectRFOTestServer() throws Exception{
        setLogBlockPrefix("injectRFOTestServer");
        ProcessBuilder pb = new ProcessBuilder(pathRFOTSInjector + nameRFOTSInjector);
        pb.start();
    }

    /**
     * Uninstall RF using rfwipeout and clearRF.bat
     */
    public static void uninstallRF(){
        lgP = "Uninstall RF";
        setLogBlockPrefix("uninstallRF");
        log("Starting procedure");
        try
        {
            log("Checking for error window");
            //DElement instW = gimMe(null,ErrorDlg.mainW,"Error dialog window");
            //DElement instW = gL(null,"Error dlg window",2,"nc",ErrorDlg.mainW.name,ErrorDlg.mainW.cName);
            DElement instW = gL(null,RF.uninstallerW,2);
            log("Error window still persists, closing");
            //WindowHandler.destroyWindow(instW);
//            instW = gL(instW,"Title bar",1,"l","title bar");
//            instW = gL(instW,"Close button",1,"n","Close");
            instW = gL(instW,RF.titleBar,1);
            instW = gL(instW,RF.closeB,"lo",1,"");
            instW.click();
            sleepy(1);

        } catch (Exception e){
            log("Error window was closed");
        }
        try{
            log("Looking for error windows");
            //Vector<DElement> ut = DElement.gimMePV(null,"Error dlg",2,"Nc","Error","#32770");
            Vector<DElement> ut = gVL(null,RF.errorDialogW,2);
            log("Error windows have been found");
            for (DElement h : ut) {
                //DElement but = gL(h,"OK button",1,"nc","OK","Button");
                DElement but = gL(h,RF.okB,"lo",1,"c","Button");
                but.click();
                sleepy(1);
                try{
                    h.click();
                    log("Window still persists");
                    //DElement instW = gL(h,"Title bar",1,"l","title bar");
                    DElement instW = gL(h,RF.titleBar,1);
                    //instW = gL(instW,"Close button",1,"n","Close");
                    instW = gL(instW,RF.closeB,"lo",1,"");
                    instW.click();
                    sleepy(1);
                } catch (Exception e){
                    log("Window was closed successfully unless 'Window still persists' log message is above");
                }
            }
            log("Error windows have been dealt with");
        } catch (Exception e) {
            log("Error windows handling ended");
        }
        sleepy(10);
        try{
            log("Looking for info window");
            DElement win = gL(null,RF.infoW,4);
            log("Info window is found, handling");
            win = gL(win,RF.titleBar,2);
            win = gL(win,RF.closeB,2);
            win.click();
            sleepy(5);
            log("Searching for Settings window");
            win = gL(null, MSWindowsGeneral.settingsMainW,5);
            log("Window found, sleep for 10 seconds in hope this window to be loaded");
            win.setForeground();
            sleepy(10);
            win.setForeground();
            log("Closing Settings window without uninstalling Edge");
            win = gL(win,MSWindowsGeneral.settingsHeader,2);
            win = gL(win,MSWindowsGeneral.settingsCloseB,2);
            win.click();
            sleepy(2);
        } catch (Exception e){
            log("Info windows handling finished or not found");
        }
        try{
            log("Looking for installer windows");
            //Vector<DElement> ut = DElement.gimMePV(null,"Installer window",2,"N","Install RoboForm");
            Vector<DElement> ut = gVL(null,RF.installerW,2);
            log("Installer windows have been found");
            for (DElement h : ut) {
                //DElement titleBar = gL(h,"Title bar",1,"l","title bar");
                DElement titleBar = gL(h,RF.titleBar,1);
                //DElement closeBtn = gL(titleBar,"Close btn",1,"n","Close");
                DElement closeBtn = gL(titleBar,RF.closeB,"lo",1,"");
                closeBtn.click();
                sleepy(1);
                //DElement warn = gL(h,"Warning message",2,"Nc","Warning","#32770");
                DElement warn = gL(h,RF.warningDialogW,2);
                //DElement yesBtn = gL(warn,"Yes button",1,"nc","Yes","Button");
                DElement yesBtn = gL(warn,RF.yesB,1);
                yesBtn.click();
                sleepy(1);
            }
            log("Installer windows have been dealt with");
        } catch (Exception e) {
            log("Installer windows handling ended");
        }
        try{
            runRFUninstallerAA();
            setLogBlockPrefix("uninstallRF-II");
            log("Connecting to installer window");
            //DElement instW = gimMe(null,UninstallRFGD.mainW,"Installer window",5);
//            DElement instW = gL(null,"Installer window",10,"nc",
//                    UninstallRFGD.mainW.name,UninstallRFGD.mainW.cName);
            DElement instW = gL(null,RF.uninstallerMainW,10);
            instW.setForeground();

            //DElement wrk = gimMe(instW,UninstallRFGD.raCB,"'Remove all' checkbox",2);
            DElement wrk = gL(instW,RF.removeAllChB,2);

            wrk.click();

            sleepy(1);
            //wrk = gimMe(instW,UninstallRFGD.btnOK,"'OK' button");
            try {
                wrk = gL(instW,RF.okB,3);
            } catch (Exception e){
                wrk = gL(instW,RF.okBm,3);
            }

            log("OK button has been found, clicking");
            wrk.click();
            sleepy(5);
            log("Checking whether confirmation dialog is displayed or not");

            try{
                //instW = gimMe(null,ConfirmationDlg.mainW,"Confirmation dialog");
                //instW = gL(null,"Confirmation dialog",2,"nc",ConfirmationDlg.mainW.name,ConfirmationDlg.mainW.cName);
                instW = gL(null,RF.confirmationInstallerW,2);
            }catch (Exception e){
                instW = null;
            }

            if (instW != null){
                log("Confirmation dialog was found, handling");
                //wrk = gimMe(instW,ConfirmationDlg.btnYes,"'Yes' button");
                wrk = gL(instW,RF.yesB,3);

                log("'Yes' button is found, clicking");
                wrk.click();
                sleepy(1);
            }
        }
        catch(Exception e){
            log("Exception is risen: " + e.getMessage());
        }
        try{
            sleepy(7);
            log("Checking whether error dialog is displayed or not");
            DElement instW;
            try{
//                instW = gimMe(null,ErrorDlg.mainW,"Error dialog window");
                //instW = gL(null,"Error dlg window",2,"nc",ErrorDlg.mainW.name,ErrorDlg.mainW.cName);
                instW = gL(null,RF.uninstallerW,2);
            }catch(Exception e){

                instW = null;
            }
            //
            if (instW != null){
                log("Error dialog is found, handling");
                //DElement wrk = gimMe(instW,ErrorDlg.btnOK,"'OK' button");
                DElement wrk = gL(instW,RF.okB,2);

                log("OK button is found, clicking");
                wrk.click();
                try
                {
                    log("Checking for error window");
                    //instW = gimMe(null,ErrorDlg.mainW,"Error dialog window");
                    instW = gL(null,RF.uninstallerW,2);
                    log("Error window still persists, closing");
                    //WindowHandler.destroyWindow(instW);
                    //instW = gL(instW,"Title bar",1,"l","title bar");
                    instW = gL(instW,RF.titleBar,1);
                    //instW = gL(instW,"Close button",1,"n","Close");
                    instW = gL(instW,RF.closeB,"lo",1,"");
                    instW.click();
                    sleepy(1);

                } catch (Exception e){
                    log("Error window was closed");
                }
                sleepy(5);
            }

        }
        catch(Exception e){
            log("Exception is risen: " + e.getMessage());
        }
        sleepy(10);
        try{
            log("Looking for info window");
            DElement win = gL(null,RF.infoW,4);
            log("Info window is found, handling");
            win = gL(win,RF.titleBar,2);
            win = gL(win,RF.closeB,2);
            win.click();
            sleepy(5);
            log("Searching for Settings window");
            win = gL(null, MSWindowsGeneral.settingsMainW,5);
            log("Window found, sleep for 10 seconds in hope this window to be loaded");
            win.setForeground();
            sleepy(10);
            win.setForeground();
            log("Closing Settings window without uninstalling Edge");
            win = gL(win,MSWindowsGeneral.settingsHeader,2);
            win = gL(win,MSWindowsGeneral.settingsCloseB,2);
            win.click();
            sleepy(2);
        } catch (Exception e){
            log("Info windows handling finished or not found");
        }
        try{
            log("Killing all existing Siber files");
            killSiber();
            setLogBlockPrefix("uninstallRF-III");
        }catch (Exception e){
            log("Exception is risen " + e.getMessage());
        }
        try{
            log("Injecting registry key");
            injectRFOTestServer();
            setLogBlockPrefix("uninstallRF_IV");
            sleepy(1);
            log("Registry key injected OK");
        } catch (Exception e) {
            logW("Registry key was not injected successfully");
        }

        Proc.closeDefaultBrowser();
    }

    /**
     * Execute script to eradicate all data in Program Files
     */
    public static void killSiber() throws IOException{
        setLogBlockPrefix("killSiber");
        ProcessBuilder pb = new ProcessBuilder(pathSiberCleanerDefault + nameSiberCleanerDefault);
        EditorProc.shell = pb.start();
    }

    /**
     * for basic install - runs and returns installer window
     * this was separated from basicInstall because it can also be used in other installations
     * @param _official true - for official builds
     * @return installer window handle
     * @throws Exception if something goes wrong
     */
    private static DElement runAndGetInstaller(Boolean _official) throws Exception{
        setLogBlockPrefix("runAndGetInstaller");
        if (_official){
            runRF8InstallerOfficial();
            setLogBlockPrefix("runAndGetInstaller-IIa");
        }
        else
        {
            runRF8Installer();
            setLogBlockPrefix("runAndGetInstaller-IIb");
        }
        sleepy(8);

        log("Attaching to window");

        DElement rFI1 = null;
//        String tgtStr = targetVersionStr;
//        if (_official)
//        {
//            tgtStr = targetVersionStrOfficial;
//        }

        try{
//            rFI1 = gimMeP(null, InstallGD.mainW.name + tgtStr,"Installer window",3,"","ca");
//            rFI1 = gL(null, "Installer window",4,"N",InstallGD.mainW.name);
            rFI1 = gL(null,RF.installerW,4);
        }
        catch (Exception e){
        }
        return rFI1;
    }


    /**
     * <p>Override for basicInstall where _official = false</p>
     */
    public static void basicInstall() throws Exception{
        basicInstall(false);
    }
    /**
     * <p>Basic installation procedure</p>
     */
    public static void basicInstall(Boolean _official) throws Exception{
        closeDefaultBrowser();
        setLogBlockPrefix("basicInstall");
        lgP = "Basic Install RF8";
        log("Starting procedure");

        DElement rFI1 = runAndGetInstaller(_official);
        setLogBlockPrefix("basicInstall-II");

        log("Assigning to Install button");

        DElement instB;
//        try{
//            instB = gimMeP(rFI1,new ElemD("","","5000"),"Install button 1",1,"","nco");
//        }catch (Exception e){
//            log("Seems like wrong button is gonna be pressed. Pressing another one [5000]:");
//            instB = gimMeP(rFI1, InstallGD.installB5,"Install button 2",1,"","nco");
//        }
        //instB = gL(rFI1,"Install button",1,"nlo","Install","button","false");
        instB = gL(rFI1,RF.installB,1);

        log("Clicking Install button");
        instB.click();
        sleepy(5);

        log("Checking existence of closing application window");
        //if (checkExistenceN(rFI1,InstallGD.closeAppHeaderT.name)){
        //if (checkExistenceN(rFI1,RF.closeAppHeaderT.name)){
        if (checkExistenceN(rFI1,RFMarkers.closingApplications.name)){
            //sleepy(1);
            log("Window containing 'closing application' text exists");
            log("Assigning to marker text for closing applications");
            try{
                log("Checking whether marker text is displayed or not");
                //gimMeP(rFI1,InstallGD.closeAppHeaderT,"Marker text",1,"","o");
                gL(rFI1, RFMarkers.closingApplications,1);
                log("Marker text exists. Clicking Install again");
                try{
                    instB = gL(rFI1,RF.installB,1);
                    instB.click();
                    sleepy(8);
                } catch (Exception e){
                    logW("instB button could not be clicked");
                }
                //
            }
            catch(Exception e){
                log("Marker text does not exist");
            }
        }
        sleepy(8);
    }


    /**
     * install RF8 with advanced options
     * for uncheckItems string:
     *  - IE can't be unchecked, therefore nothing for it
     *  - FF - to uncheck Mozilla Firefox, use 'F' symbol there
     *  - Chrome - to uncheck Google Chrome, use 'C' symbol there
     *  - Opera - to uncheck Opera, use 'O' symbol there
     *  - Apps - to uncheck "Fill and Save Forms in Windows Applications, use 'A' symbol there
     *  - Install for all users (next screen) - to uncheck that, use 'U' symbol there
     *  Note that this will only perform single click. If element was disabled this procedure will emit logW,
     *  but will continue successfully
     * @param _official true for official installer
     * @param _userDataPath path to user data (or null if skip)
     * @param _programDataPath path to program data (or null if skip)
     * @param _uncheckItems string to uncheck items (show description)
     * @throws Exception if something goes wrong
     */
    public static void advancedInstall(Boolean _official, String _userDataPath,
                                       String _programDataPath, String _uncheckItems) throws Exception{
        lgP = "Advanced Install RF8";
        setLogBlockPrefix("advancedInstall");
        log("Starting procedure");

        DElement rFI1 = runAndGetInstaller(_official);

        //DElement wrk = gimMe(rFI1,InstallGD.advancedComboB,"Show adv opt cb",2);
        DElement wrk = gL(rFI1,RF.showAdvancedOptionsChB,2);
        wrk.click();
        sleepy(0.3);

        //wrk = gimMe(rFI1, InstallGD.nextB,"Next button");
        wrk = gL(rFI1,RF.nextB,3);
        wrk.click();
        sleepy(1);

        //List<DElement> chbList = gimMePL(rFI1,InstallGD.nextB,"Checkboxes and buttons list","","nao");
        Vector<DElement> chbList = gVL(rFI1,RF.B,1);

        Boolean hasUncheckItems = true;
        if (_uncheckItems == null){
            hasUncheckItems = false;
        }

        if ((hasUncheckItems)&&(_uncheckItems.toUpperCase().contains("F"))){
            if (chbList.get(1).getIsEnabled()){
                log("Unchecking Mozilla Firefox");
                chbList.get(1).click();
                sleepy(0.2);
            }
            else
            {
                logW("Mozilla Firefox is disabled");
            }
        }
        if ((hasUncheckItems)&&(_uncheckItems.toUpperCase().contains("C"))){
            if (chbList.get(2).getIsEnabled()){
                log("Unchecking Google Chrome");
                chbList.get(2).click();
                sleepy(0.2);
            }
            else
            {
                logW("Google Chrome is disabled");
            }
        }
        if ((hasUncheckItems)&&(_uncheckItems.toUpperCase().contains("O"))){
            if (chbList.get(3).getIsEnabled()){
                log("Unchecking Opera");
                chbList.get(3).click();
                sleepy(0.2);
            }
            else
            {
                logW("Opera is disabled");
            }
        }
        if ((hasUncheckItems)&&(_uncheckItems.toUpperCase().contains("A"))){
            if (chbList.get(4).getIsEnabled()){
                log("Unchecking Fill and Save for Applications");
                chbList.get(4).click();
                sleepy(0.3);
            }
            else
            {
                logW("Fick!");
                throw new Exception("Fill and Save for Applications is disabled [INVESTIGATE]");
            }
        }

        log("Proceeding forward");
        //wrk = gimMe(rFI1,InstallGD.nextBNasty2,"Next button");
        wrk = gL(rFI1,RF.nextB,3);
        wrk.click();

        if (_userDataPath != null) {
            if (!(_userDataPath.isEmpty())){
                //TODO: handle user data different locations when available
            }
        }
        if (_programDataPath != null) {
            if (!(_programDataPath.isEmpty())) {
                log("Changing program data path");
                //wrk = gimMe(rFI1,InstallGD.changeB,"Change button");
                wrk = gL(rFI1,RF.changeB,3);
                wrk.click();

                //warning message appears
                //wrk = gimMe(rFI1,InstallGD.wrnDlg,"Warning window",2);
                wrk = gL(rFI1,RF.warningDialogRFW,2);
                //wrk = gimMeP(wrk,"Yes","Yes button",1,"","ca");
                wrk = gL(wrk,RF.yesB,"c",1,"");
                wrk.click();
                sleepy(0.4);

                //Browse for folder dialog appears
                //DElement tW = gimMe(rFI1,InstallGD.brwsDlg,"Browsing dialog",2);
                DElement tW = gL(rFI1,RF.browseForFolderW,2);
                //wrk = gimMeP(tW,InstallGD.folderE,"Folder edit",1,"","a");
                wrk = gL(tW, RF.folderE,1);
                wrk.click();
                wrk.click();

                wrk.setEditValue(_programDataPath);
                sleepy(0.2);
                //wrk = gimMe(tW,InstallGD.btnOK1,"OK Button");
                wrk = gL(tW,RF.okB,2);
                wrk.click();
                sleepy(0.2);
            }
        }

        if ((hasUncheckItems)&&(_uncheckItems.toUpperCase().contains("U"))){
            //wrk = gimMeP(rFI1,"Install RoboForm for all Windows users","All users checkbox",1,"","ca");
            wrk = gL(rFI1,RF.installRFForAllUsersChB,1);
            if (wrk.getIsEnabled()){
                log("Unchecking All users checkbox");
                wrk.click();
                sleepy(0.3);
            }
            else
            {
                logW("Fick!");
                throw new Exception("All users checkbox is disabled [INVESTIGATE]");
            }
        }

        log("Proceeding with installation");
        //wrk = gimMeP(rFI1,InstallGD.btnInstall4,"Install button",1,"","a");
        wrk = gL(rFI1,RF.installB,1);
        wrk.click();
        sleepy(10);

        log("Maybe Closing applications dialog appears (may be not)");
        Boolean handleClosing = false;
        try{
            //wrk = gimMeP(rFI1,InstallGD.closeAppHeaderT,"Closing marker",2,"","oac");
            gL(rFI1,RFMarkers.closingApplications,2);
            handleClosing = true;
        }catch(Exception e){
            log("There were no closing text, proceeding");
        }

        if (handleClosing){
            //wrk = gimMe(rFI1,InstallGD.installB5,"Install button");
            wrk = gL(rFI1,RF.installB,1);
            wrk.click();
            //wrk = gimMeP(rFI1,InstallGD.closeAppHeaderT,"Closing marker",5,"d","oac");
            gL(rFI1,RFMarkers.closingApplications,"",5,"D");
        }

        sleepy(10);
        log("Procedure complete");
    }
    /**
     * <p>Override for upgradeRF7RF8 where _official = false</p>
     */
    public static void upgradeRF7RF8() throws Exception{
        upgradeRF7RF8(false);
    }
    /**
     * <p>RF8 Installer - upgrade from RF7 to RF8</p>
     */
    public static void upgradeRF7RF8(Boolean _official) throws Exception{
        lgP = "Upgrade from RF7 to RF8";
        setLogBlockPrefix("upgradeRF7RF8");
        log("Starting procedure");
        if (_official){
            runRF8InstallerOfficial();
            setLogBlockPrefix("upgradeRF7RF8-IIa");
        }
        else
        {
            runRF8Installer();
            setLogBlockPrefix("upgradeRF7RF8-IIb");
        }
        sleepy(8);

        log("Attaching to window");
        DElement rFI1 = null;
//        String tgtStr = targetVersionStr;
//        if (_official){
//            tgtStr = targetVersionStrOfficial;
//        }
        try{
            //rFI1 = gimMeP(null, UpgradeRF7RF8.mainW.name + tgtStr,"Installer window",3,"","ca");
            //rFI1 = gL(null,"Installer window",3,"n",UpgradeRF7RF8.mainW.name + tgtStr);
            rFI1 = gL(null,RF.installerW,3);
        }
        catch (Exception e){
//            log("Maybe installer name has changed.. attempting to find in another way");
//            List<DElement> wEL = gimMePL(null,UpgradeRF7RF8.mainW,"Installer window","","nao");
//
//            if (wEL != null){
//                for (DElement iter : wEL){
//                    String elName = iter.getName();
//                    if (elName.contains(InstallGD.mainW.name)){
//                        rFI1 = iter;
//                        break;
//                    }
//                }
                if (rFI1 == null){
                    throw new Exception("Installer window was not found");
                }
//            }
        }

        //DElement wrk = gimMe(rFI1,UpgradeRF7RF8.nextBtn,"Next button");
        //DElement wrk = gL(rFI1,"Next button",1,"nlo","Next","button","false");
        DElement wrk = gL(rFI1,RF.nextB,1);

        wrk.click();
        sleepy(1);
        //wrk = gimMe(rFI1,UpgradeRF7RF8.installBtn,"Install button");
        //wrk = gL(rFI1,"Install button",1,"nlo","Install","button","false");
        wrk = gL(rFI1,RF.installB,1);
        wrk.click();

        sleepy(5);
        log("Checking existence of closing application window");
        //if (checkExistenceN(rFI1,InstallGD.closeAppHeaderT.name)){
        //if (checkExistenceN(rFI1,RF.closeAppHeaderT.name))
        if (checkExistenceN(rFI1,RFMarkers.closingApplications.name)){
            log("Window containing 'closing application' text exists");
            log("Assigning to marker text for closing applications");

            DElement mrkT;
            try{
                //mrkT = gimMe(rFI1,InstallGD.closeAppHeaderT,"Close application header");
                mrkT = gL(rFI1,RFMarkers.closingApplications,1);
            } catch (Exception e){
                mrkT = null;
            }
            //
            if (mrkT != null){
                sleepy(1);
                log("Checking whether marker text is displayed or not");
                try{
                    boolean tS = mrkT.getIsOffscreen();
                    if (!(tS)){
                        log("Marker text exists. Clicking Install again");
                        sleepy(8);
                        wrk.click();
                    }
                } catch (Exception e){
                    log("Marker has disappeared");
                }
            }
            else
            {
                log("Marker is null");
            }
        }

        sleepy(10);

    }


    public static void handleFFPostInstallArtifacts() throws Exception {
        handleFFPostInstallArtifacts(null);
    }
    public static DElement getFFPostInstallRFNotification() throws Exception{
        return getFFPostInstallRFNotification(null);
    }
    public static DElement getFFPostInstallRFNotification(DElement parent) throws Exception{
        Proc.setLogBlockPrefix("getFFPostInstallRFNotification");
        DElement erW;
        if (parent == null) {
            //erW = gL(null, "RF Setup dialog", 1, "nc","RoboForm Setup","#32770");
            erW = gL(null,RF.supremeWFast,1);
        } else {
            erW = parent;
        }
        return gL(erW,"Error window",2,"nc","RoboForm Information","#32770");
    }
    public static void handleEdgePostInstallArtifacts() throws Exception{
        handleEdgePostInstallArtifacts(false, false);
    }

    /**
     * Closes Edge post-install artifacts
     * If shouldAppear == true, then raises exception if artifacts did not appear
     * If affectedByFF == true && shouldAppear == true then check that small notification is NOT shown.
     */
    public static void handleEdgePostInstallArtifacts(boolean shouldAppear, boolean affectedByFF) throws Exception{
        setLogBlockPrefix("handleEdgePostInstallArtifacts");
        sleepy(2);
        try {
            DElement storeWindow = gL(null, MSStore.mainW,1);
            storeWindow.setForeground();
            sleepy(1);
            DElement q = gL(storeWindow,MSStore.subW,1);
            q = gL(q,MSStore.closeB,1);
            q.click();
            sleepy(2);
        } catch (Exception e){
            if (shouldAppear){
                throw e;
            }
        }

        //TODO: improve notification window detection to be more selective
//        try{
//            log("Killing notification");
//            DElement erW = gL(null,"Notification window",1,"l","window");
//            DElement wrk = gL(erW, "Title bar", 1, "l", "title bar");
//            wrk = gL(wrk,"Close button",1,"n","Close");
//            wrk.click();
//            log("Notification is killed");
//        } catch (Exception e){
//            if (shouldAppear && !(affectedByFF)){
//                throw e;
//            }
//            logW("Killing notification error");
//        }

    }
    public static void handleFFPostInstallArtifacts(DElement parent) throws Exception{
        setLogBlockPrefix("handleFFPostInstallArtifacts");
        sleepy(5);
        boolean wasSetup = false;
        try {
            DElement erW = getFFPostInstallRFNotification(parent);
            wasSetup = true;
            erW.setForeground();
            sleepy(0.5);
            //DElement wrk = gL(erW, "OK Button", 2, "nc", "OK","Button");
            DElement wrk = gL(erW, RF.okB, 2);
            wrk.click();
            //
            //gL(null,"Sync setup window#1",25,"DncU", InstallSetSyncD.mainW.name,InstallSetSyncD.mainW.cName,"5000");
            gL(null,RF.supremeWFast,"",25,"DU","5000");
            //
            sleepy(2);
            try{
                log("Killing notification");
                erW = gL(null,"Notification window",1,"l","window");
                erW.setForeground();
                sleepy(0.5);
                wrk = gL(erW, "Title bar", 1, "l", "title bar");
                wrk = gL(wrk,"Close button",1,"n","Close");
                wrk.click();
                log("Notification is killed");
            } catch (Exception e){
                logW("Killing notification error");
            }
        } catch (Exception e) {
            if (!wasSetup){
                log("RF Setup dialog is not opened. Continue with tests.");
            } else {
                logE("Error while handling setup");
                throw e;
            }
        }

    }

    public static void processInstallationEnd() throws Exception{
        setLogBlockPrefix("processInstallationEnd");
        log("Processing artifacts and closing browser");
        sleepy(4);
        handleBrowserExtensionSetupWindow();
        setLogBlockPrefix("processInstallationEnd-II");
        sleepy(4);
        handleEdgePostInstallArtifacts();
        setLogBlockPrefix("processInstallationEnd-III");
        sleepy(4);
        handleFFPostInstallArtifacts();
        setLogBlockPrefix("processInstallationEnd-IV");
        sleepy(4);
        closeDefaultBrowser();
    }
    public static void handleBrowserExtensionSetupWindow() throws Exception{
        lgP = "Handle browser extension setup window";
        setLogBlockPrefix("handleBrowserExtensionSetupWindow");
        log("Starting procedure");
        //TODO: handle different browser extension checking
        boolean proceedWithHandle = false;
        DElement wrk = null;
        try{
            wrk = gL(null, RF.supremeWFast,4);
            proceedWithHandle = true;
        } catch (Exception e){
            log("Setup window was not found");
        }
        if (!proceedWithHandle) {
            return;
        }
        proceedWithHandle = false;
        try{
            wrk = gL(wrk,RF.browserExtensionSetupW,3);
            proceedWithHandle = true;
        } catch (Exception e){
            log("Browser Extension Setup window did not appear. There is no need to handle anything here");
        }
        if (!proceedWithHandle){
            return;
        }
        log("Browser Extension Setup window has been found. Proceeding.");
        DElement nextB = gL(wrk,RF.nextB,1);
        nextB.click();
        log("Procedure complete");
    }

}
