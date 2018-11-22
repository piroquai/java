package jnaex.RFWin;

import daima.DElement;
import jnaex.RFWin.SearchPresets.RF;
import jnaex.RFWin.SearchPresets.RF7;
import jnaex.RFWin.SearchPresets.RFMarkers;
import jnaex.RFWin.User.RFUser;

import java.util.Vector;

/**
 * Created by Autotester on 4/4/2018.
 */
public class TBIProc extends Proc {
    /**
     * No MP upgrade scenario handling - enter new MP part
     * @throws Exception
     */
    public static void syncSetupRF7DesktopToRF8UpgradeNoMP(RFUser _user) throws Exception{
        lgP = "SyncSetupRF7DesktopToRF8UpgradeNoMP";
        setLogBlockPrefix("syncSetupRF7DesktopToRF8UpgradeNoMP");
        log("Starting procedure");
        try{
            gL(null,RF.supremeWFast,10);
//            gL(null,RF.supremeWFast,"",10,"D");
        } catch (Exception e) {
//            log("OK - window is here");
            log("Not OK - window is not here");
        }

//        DElement supW = gL(null,RF.supremeWFast,"U",2,"");
//
//        DElement ssW = gL(supW,RF.syncSetupW,4);

        DElement ssW = getSyncSetupWindow();

        DElement wrk = gL(ssW,RF.nextB,1);

        wrk.click();
        sleepy(1);
        log("Entering new MP");

        //wrk = gL(ssW,"Edit MP 1",1,"nc",SetupSyncWindow.editNoMPCreateMP1.name,"Edit");
        wrk = gL(ssW,RF.chooseMPE,1);

        wrk.setEditValue(_user.getPassword()); //there were no MP before, so enter RF8 pwd
        sleepy(0.3);
        //wrk = gL(ssW,"Edit MP 2",1,"nc",SetupSyncWindow.editNoMPCreateMP2.name,"Edit");
        wrk = gL(ssW,RF.retypeMPE,1);

        wrk.setEditValue(_user.getPassword());
        sleepy(0.2);
        log("Going further");
        wrk = gL(ssW,RF.nextB,1);

        wrk.click();
    }
    private static void wfSyncSetupWindowToDisappear() throws Exception{
        setLogBlockPrefix("wfSyncSetupWindowToDisappear");
        int timeoutIC = 5;
        int ct = 0; //counter
        while (ct < timeoutIC){
            sleepy(4);
            log("Locating sync setup window. Attempt #" + (ct + 1));
            try{
                DElement sw = gL(null,RF.supremeWFast,"U",1,"");
                gL(sw,RF.syncSetupW,1);
            }
            catch(Exception e){
                break;
            }
            //
            ct++;
            if (ct == timeoutIC){
                logE("Sync setup window is still shown, exiting.");
                throw new Exception("Sync setup window was not closed in time");
            }
        }

    }

    private static DElement getSyncSetupWindow() throws Exception{
        log("Getting sync setup window");
        try{
            gL(null,RF.supremeWFast,"",10,"DU","300");
        } catch (Exception e){
            log("SSW#1 did not disappear, continuing..");
        }
        try{
            DElement sw = gL(null,RF.supremeWFast,"U",2,"");
            return gL(sw,RF.syncSetupW,4);
        } catch (Exception e){
            return gL(null,RF.syncSetupW,4);
        }
    }

    /**
     * Sync setup - enter MP on existing data (RF8, was a part of syncSetupRF7DesktopToRF8Upgrade
     */
    public static void syncSetupRF8RIEnterMP(RFUser _user) throws Exception{
        lgP = "RF8 Sync Setup (reinstall)"+ Proc.logSeparator+"Enter MP";
        setLogBlockPrefix("syncSetupRF8RIEnterMP");
        log("Starting procedure");
        log("Attaching to sync setup window");

        try {
            gL(null,RF.supremeWFast,10);
//            gL(null,RF.supremeWFast,"",10,"D");
        } catch (Exception e) {
//            log("Setup window did not disappear, continuing...");
            log("Setup window did not appear, continuing...");
        }

        DElement ssW = getSyncSetupWindow();

        //
        log("Enter MP");
//        DElement wrk = gL(ssW,"Enter MP field",1,"nc", SetupSyncWindow.editMPField.name,"Edit");
        DElement wrk = gL(ssW,RF.syncSetupEnterMPE,"a",1,"");

        wrk.click();
        wrk.setEditValue(_user.getPassword());
        sleepy(0.3);
        log("Moving forward");
        wrk = gL(ssW,RF.nextB,1);

        wrk.click();
        sleepy(3);
    }

    /**
     * Overloading for Setup RF8 sync on RF7 Desktop to RF8 Upgrade (default param keepOffline = True)
     */
    public static void syncSetupRF7DesktopToRF8Upgrade(RFUser _user) throws Exception {
        syncSetupRF7DesktopToRF8Upgrade(_user,true);
    }

    /**
     * Sync setup RF7 to RF8 Don not sync dialog handling
     * @param _ssW sync setup window handle
     * @param _keepOffline whether we should keep account in offline mode
     * @throws Exception if something is bad
     */
    public static void syncSetupRF7ToRF8DNSHandling(DElement _ssW, Boolean _keepOffline) throws Exception{
        setLogBlockPrefix("syncSetupRF7ToRF8DNSHandling");
        log("Begin syncSetupRF7ToRF8DNSHandling");
//        log("Do not sync my RF data handling");
        DElement wrk;
        if (!_keepOffline){ //TODO: need to test future behavior for this case
//            wrk = gL(_ssW,"DoNotSync checkbox",1,"nc",SetupSyncWindow.chbDNS.name,"Button");
            wrk = gL(_ssW,RF.dnsChB,1);
            wrk.click();
            sleepy(0.4);
        }
        log("Going further:");
        wrk = gL(_ssW,RF.nextB,1);

        wrk.click();
        sleepy(2);
        log("End syncSetupRF7ToRF8DNSHandling");
    }
    /**
     * Setup RF8 sync on RF7 EW to RF8 Upgrade
     */
    public static void syncSetupRF7EWToRF8Upgrade(RFUser _user) throws Exception{
        lgP = "RF8 Sync Setup" + Proc.logSeparator + "RF7 Everywhere to RF8 Upgrade";
        setLogBlockPrefix("syncSetupRF7EWToRF8Upgrade");
        log("Starting procedure");
        log("Attaching to sync setup window");
//        DElement ssW = gimMe(null,InstallSetSyncD.mainW,"Sync setup window [Main]",2);
//        ssW = gimMe(ssW,InstallSetSyncD.syncSetupW,"Sync setup window",3);
        DElement ssW = getSyncSetupWindow();
        // click Next
        DElement wrk = gL(ssW,RF.nextB,2);

        wrk.click();
        log("Waiting for sync to begin");
        try{
            gL(ssW,RF.stopB,3);
        }catch(Exception e){
            log("Maybe sync was too fast");
        }
        log("Waiting for sync to complete");
        gL(ssW,RF.stopB,"",5,"Dh","true");
        sleepy(2);
        wrk = gL(ssW,RF.nextB,2);
        wrk.click();
        sleepy(3);
        wrk = gL(ssW,RF.nextB,2);
        wrk.click();
        log("Waiting for sync to begin");
        try{
            gL(ssW,RF.stopB,3);
        }catch(Exception e){
            log("Maybe sync was too fast");
        }
        log("Waiting for sync to complete");
        gL(ssW,RF.stopB,"",5,"Dh","true");

        sleepy(2);
        log("Procedure complete");
    }
    /**
     * Setup RF8 sync on RF7 Desktop to RF8 Upgrade
     * @param _keepOffline if this option is False, then checkbox "Do not Sync my RF data" will be unchecked
     * @throws Exception if something goes wrong :-)
     */
    public static void syncSetupRF7DesktopToRF8Upgrade(RFUser _user, Boolean _keepOffline) throws Exception{
        lgP = "RF8 Sync Setup" + Proc.logSeparator + "RF7 Desktop to RF8 Upgrade";
        setLogBlockPrefix("syncSetupRF7DesktopToRF8Upgrade");
        log("Starting procedure");
        log("Attaching to sync setup window");
        //DElement ssW = gimMe(null,InstallSetSyncD.mainW,"Sync setup window [Main]",2);
//        DElement ssW = gL(null,"Sync setup window [Main]",3,"nc",InstallSetSyncD.mainW.name,"#32770");
//
//        //ssW = gimMe(ssW,InstallSetSyncD.syncSetupW,"Sync setup window",3);
//        ssW = gL(ssW,"Setup sync window",4,"nc",InstallSetSyncD.syncSetupW.name,"#32770");
        DElement ssW = getSyncSetupWindow();

        DElement wrk;
        syncSetupRF7ToRF8DNSHandling(ssW,_keepOffline);
        setLogBlockPrefix("syncSetupRF7DesktopToRF8Upgrade-II");
        log("Simple create free account page handling");

        wrk = gL(ssW,RF.createNewAccB,1);
        wrk.click();
        sleepy(2);

        //wrk = gL(ssW,"Create free account email field",1,"nc",SetupSyncWindow.editEmailCrFr78.name,"Edit");
        wrk = gL(ssW,RF.emailE,1);

        wrk.click();

        sleepy(0.3);
        log("Entering email");
        wrk.setEditValue(_user.getEmail());
        sleepy(0.3);
        log("Clicking Next");
        wrk = gL(ssW,RF.nextB,1);

        wrk.click();
        sleepy(3);
        gL(ssW, RFMarkers.accSuccCreated,10);

        log("Account created");
        gL(ssW,RF.nextB,1);

        sleepy(1);
        wrk.click();
        sleepy(5);
        wfSyncSetupWindowToDisappear();
        setLogBlockPrefix("syncSetupRF7DesktopToRF8Upgrade-III");
        log("Procedure complete");
        InstallerProc.processInstallationEnd();

    }

    /**
     * Brave Sync setup - on installing RF8 after upgrade from RF7
     */
    public static void syncSetupScriptRF7ToRF8ULtL(RFUser _user) throws Exception{
        syncSetupRF8RIEnterMP(_user);
        setLogBlockPrefix("syncSetupScriptRF7ToRF8ULtL");
        log("Waiting for Install RF window to disappear");
        try{
            //DElement ssW = gimMe(null, InstallSetSyncD.mainW,"Setup main window",1);
            DElement ssW = gL(null,RF.supremeWFast,"U",1,"");
            try{
                //gimMeP(ssW,InstallSetSyncD.syncSetupW,"Sync setup window",3,"d","o");
                gL(ssW,RF.syncSetupW,"",3,"D");
            }
            catch (Exception e){
                throw new Exception("");
            }
        }
        catch(Exception e){
            if (e.getMessage().isEmpty()){
                throw new Exception("Sync setup window was still present");
            }
            log("Setup window is hidden");
        }
        sleepy(3);
        InstallerProc.processInstallationEnd();
    }
    /**
     * Sync setup - PR115RF7RF8StS script
     */
    public static void syncSetupScriptRF7ToRF8StS(RFUser _user) throws Exception {
        syncSetupRF8RIEnterMP(_user);
        syncSetupRF7EWToRF8Upgrade(_user);
    }
    /**
     * Sync setup - PR114RF7RF8LtS script
     */
    public static void syncSetupScriptRF7RF8LtS(RFUser _user) throws Exception{
        syncSetupRF8RIEnterMP(_user);
        syncSetupRF7DesktopToRF8Upgrade(_user,false);
    }
    /**
     * Sync setup - PR111RF7RF8LtLD script
     */
    public static void syncSetupScriptRF7RF8LtLD(RFUser _user) throws Exception{
        syncSetupRF8RIEnterMP(_user);//setting RF7 MP here as it was set up in RF7; use default existing value
        syncSetupRF7DesktopToRF8Upgrade(_user);
    }
    /**
     * Sync setup - PR113RF7RF8LtLSrvExs script
     */
    public static void syncSetupScriptRF7RF8LtLSrvExs(RFUser _user) throws Exception{
        syncSetupRF8RIEnterMP(_user);
        syncSetupRF7RF8AccLoaded(true);
    }
    /**
     *  Sync setup - PR112RF7RF8LtLNoMP script
     */
    public static void syncSetupScriptRF7RF8LtLNoMP(RFUser _user) throws Exception{
        syncSetupRF7DesktopToRF8UpgradeNoMP(_user);//setting RF7 MP here as it was set up in RF7; use default existing value
        syncSetupRF7DesktopToRF8Upgrade(_user);
    }

    /**
     * Sync setup RF7 to RF8 if account is OK and loaded
     */
    public static void syncSetupRF7RF8AccLoaded(Boolean _keepOffline) throws Exception{
        lgP = "RF8 Sync Setup"+ logSeparator+"Account loaded";
        sleepy(3);
        setLogBlockPrefix("syncSetupRF7RF8AccLoaded");
        log("Starting procedure");
        log("Attaching to sync setup window");
        DElement ssW = getSyncSetupWindow();

        syncSetupRF7ToRF8DNSHandling(ssW,_keepOffline);
        setLogBlockPrefix("syncSetupRF7RF8AccLoaded-II");
        sleepy(1);

        DElement wrk = gL(ssW, RF.nextB,2);
        wrk.click();
        log("Waiting for Stop button");
        try{
            //too bored to make another element, it is incorrect though, but we are at where we are
            gL(ssW,RF.stopB,"",3,"e","true");
            log("Waiting for Stop button to disappear");
            log("If 'Maybe Stop btn was too fast' message appears, then something bad is happened with Stop button closure");
            gL(ssW,RF.stopB,"",5,"De","true");
        }
        catch(Exception e){
            log("Maybe Stop button was too fast? Skipping");
        }
        sleepy(2);
        log("Keep current email, go further");
        wrk = gL(ssW,RF.nextB,2);
        log("Clicking");
        wrk.click();
        //here may be some errors
        log("Waiting for another Next button to finish");
        wrk = gL(ssW,RF.nextB,2);
        log("Clicking");
        wrk.click();
        log("Waiting for Stop button");
        try{
            //too bored to make another element, it is incorrect though, but we are at where we are
            gL(ssW,RF.stopB,"",3,"e","true");
            log("Waiting for Stop button to disappear");
            log("If 'Maybe Stop btn was too fast' message appears, then something bad is happened with Stop button closure");
            gL(ssW,RF.stopB,"",3,"De","true");
        }catch (Exception e){
            log("Maybe Stop button was too fast? Skipping");
        }
        sleepy(4);
        InstallerProc.processInstallationEnd();
        setLogBlockPrefix("syncSetupRF7RF8AccLoaded-III");

        log("Waiting for Install window to close completely");
        //gimMeP(null,InstallSetSyncD.mainW,"Setup main window",3,"d","");
        gL(null,RF.supremeWFast,"U",8,"D");
        log("That's all, folks!");
        sleepy(5);

    }

    public static void eradicateSetupErrorsRF8RF7Downgrade() throws Exception{
        lgP = "RF8 to RF7 errors eradicator";
        log("Starting procedure");
        Boolean wasErrors = false;
        DElement wrk1;
        DElement wrk2;
        try {
            log("Seeking for RF is currently disabled error window");
            wrk1 = gimMeP(null, "RoboForm Error", "RF disabled message", 1, "", "ca");
            wasErrors = true;
            wrk2 = gimMeP(wrk1, "OK","OK button",2,"","ca");
            wrk2.click();
            sleepy(1);
        } catch (Exception e){
            log("RF is currently disabled message did not appear");
        }
        try{
            log("Seeking for Reboot required notification");
            wrk1 = gimMeP(null,"RoboForm Setup", "RF setup req message",1,"","ca");
            wrk2 = gimMeP(wrk1,"No","No button",1,"","ca");
            wasErrors = true;
            wrk2.click();
            sleepy(1);
        }catch (Exception e){
            log("Reboot required notification did not appear");
        }
        if (wasErrors){
            log("There were some errors, turning Editor ON");
            try{
                //wrk1 = gimMeP(null,EditorRF7MW.defMainW,"Main Editor window",2,"","na");
                wrk1 = gL(null, RF7.editorW,2);
                //wrk2 = gimMeP(wrk1,new ElemD("Turn ON","Button"),"Turn ON button",2,"k","oa");
                wrk2 = gL(wrk1,RF7.turnOnB,"",2,"h","true");
                wrk2.click();
                sleepy(0.4);
                try {
                    log("Seeking for RF is currently disabled error window");
                    //wrk1 = gimMeP(null, "RoboForm Error", "RF disabled message", 1, "", "ca");
                    wrk1 = gL(null, RF7.errorMsg,1);
                    //wrk2 = gimMeP(wrk1, "OK","OK button",2,"","ca");
                    wrk2 = gL(wrk1,RF7.okB,2);
                    wrk2.click();
                    sleepy(1);
                } catch (Exception e){
                    log("RF is currently disabled message did not appear");
                }
            }
            catch(Exception e){
                log("Turning failed");
            }
        }

        log("Procedure complete");
    }


    public static void setupSyncNew(RFUser _user, boolean leaveArtifacts) throws Exception{
        lgP = "SetupSyncNew";
        setLogBlockPrefix("setupSyncNew");
        log("Starting procedure");

        DElement sw = getSyncSetupWindow();

        DElement crNew = gL(sw, RF.createNewAccB,1);
        crNew.click();
        sleepy(2);

        //DElement wrk = gL(sw,"Email field",2,"nc", "Email:","Edit");
        DElement wrk = gL(sw,RF.emailE,2);

        wrk.click();
        wrk.setEditValue(_user.getEmail());
        sleepy(0.5);

        //Vector<DElement> wrkL = gVL(sw,"Master pwd fields",1,"nlo","Master Password:","edit","false");
        Vector<DElement> wrkL = gVL(sw,RF.MPE,1);

//        wrk.click();
//        wrk.setEditValue(_user.getPassword());
//        sleepy(0.5);
        for (DElement z : wrkL) {
            z.click();
            z.setEditValue(_user.getPassword());
            sleepy(0.5);
        }

        //wrk = gL(sw, "Next button",1,"nlo","Next","button","false");
        wrk = gL(sw,RF.nextB,1);

        wrk.click();
        sleepy(5);

        log("Checking successful account creation marker message");
        //gL(sw,"Account created marker",3,"nlo","Your RoboForm account was successfully created","text","false");
        gL(sw,RFMarkers.accSuccCreated,5);

        //wrk = gL(sw, "Next button",1,"nlo","Next","button","false");
        wrk = gL(sw,RF.nextB,1);

        wrk.click();
        sleepy(6);
        //gimMeP(sw,SyncD.stopB,"Stop button",5,"ed","oa");
        gL(sw,RF.stopB,"",5,"eD","true");

        log("Waiting for setup to finish");
        if (!leaveArtifacts){
            InstallerProc.processInstallationEnd();
            setLogBlockPrefix("setupSyncNew-II");
        }
        log("Procedure completed");

    }

    public static void setupSyncNew(RFUser _user) throws Exception{
        setupSyncNew(_user,false);
    }


    /**
     * <p>Sets the sync up (RF8) using given credentials</p>
     * @param _user RFUser user
     * @throws Exception If something goes wrong
     */
    private static void setupSyncExisting(RFUser _user) throws Exception {
        lgP = "SetupSyncExisting";
        setLogBlockPrefix("setupSyncExisting");

        DElement sw = getSyncSetupWindow();

        sw.setForeground();
        sleepy(0.2);

        sw.setForeground();
        sleepy(0.2);
        //
        log("Proceeding with Sync setup window.");
        log("Switching to Login to Existing account mode");

        if (!checkExistenceN(sw,RF.logInWithExistingAccB.name)){
            throw new Exception(RF.logInWithExistingAccB.name + " trigger was not found.");
        }

        sleepy(1);

        DElement tr = gL(sw,RF.logInWithExistingAccB,1);
        tr.click();
        sleepy(1);
        log("Switching to enter email field");

        if (!checkExistenceN(sw,RF.emailUserIDE.name)){
            throw new Exception(RF.emailUserIDE.name + " field was not found.");
        }

        DElement ef = gL(sw,RF.emailUserIDE,1);

        log("Sending existing email keys");
        ef.setEditValue(_user.getEmail());
        sleepy(0.5);
        log("Going further. Locating 'Next' button.");

        sleepy(0.5);

        try{
            DElement field = gL(sw,RF.MPE,1);
            log("Accessed to some element, entering keys");
            field.setEditValue(_user.getPassword());
            sleepy(0.5);
        }catch (Exception e){
            throw new Exception("Unable to locate 'Enter pwd' field or enter keys. See detailed logs.");
        }
        log("Going further. Locating 'Next' button.");

        DElement nextB = gL(sw,RF.nextB,2);
        log("Clicking 'Next' button");
        nextB.click();
        sleepy(0.5);
        log("And now, if we wait for some time, setup will be finished.");
        sleepy(6);
        log("Checking whether sync is in progress or not");
        while (true){
            try{
                log("Locating 'Downloading user data' text");
                gL(sw,RFMarkers.syncDownloading,1);
                log("Downloading is in progress, waiting");
                sleepy(3);
            }catch (Exception e){
                log("Downloading is not in progress.");
                break;
            }
        }
        sleepy(5);
        while (true){
            try{
                log("Locating Stop button");
                gL(sw,RF.stopB,1);
                //
                log("Sync is in progress, waiting");
                sleepy(3);
            }catch (Exception e){
                log("Sync is not in progress.");
                break;
            }
        }
        sleepy(3);
        log("Sync setup is over");

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

        InstallerProc.processInstallationEnd();
    }

    /**
     * <p>Setup sync using current system credentials for current account</p>
     * @throws Exception exception rethrowing
     */
    public static void syncSetupRF8Current(RFUser _user) throws Exception{
        setupSyncExisting(_user);
    }
    public static void syncSetupRF8withRF7cred(RFUser _user) throws Exception{
        setupSyncExisting(_user);
    }

    /**
     * sync setup RF8 with RF7 cred - decrypt old data
     */
    public static void syncSetupRF8WithRF7CredDecryptData(RFUser _user) throws Exception{
        setLogBlockPrefix("syncSetupRF8WithRF7CredDecryptData");
        DElement sw = getSyncSetupWindow();

        //DElement wrk = gL(sw,"MP for Contact Info field",2,"nc",InstallSetSyncD.editRF7DataPassword.name,"Edit");
        DElement wrk = gL(sw,RF.editMultifileDataPassword,2);

        wrk.click();
        wrk.setEditValue(_user.getPassword());
        wrk = gL(sw,RF.nextB,1);

        wrk.click();
        sleepy(5);
        wrk = gL(sw,RF.nextB,1);

        wrk.click();
        sleepy(10);
        wrk = gL(sw,RF.nextB,1);

        wrk.click();
        sleepy(5);

        InstallerProc.processInstallationEnd();
    }

}
