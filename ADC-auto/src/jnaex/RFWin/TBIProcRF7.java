package jnaex.RFWin;

import daima.DElement;
import jnaex.RFWin.InstallWindow.RF7.InstallRF7GD;
import jnaex.RFWin.SearchPresets.RF;
import jnaex.RFWin.SearchPresets.RF7;
import jnaex.RFWin.SearchPresets.RF7Markers;
import jnaex.RFWin.SyncWindow.SyncD;
import jnaex.RFWin.User.RFUser;

import java.util.List;

/**
 * Created by Autotester on 4/4/2018.
 */
public class TBIProcRF7 extends Proc{
    /**
     * syncSetupRF7EWCreateNew default override
     */
    public static void syncSetupRF7EWCreateNew(RFUser _user) throws Exception {
        syncSetupRF7EWCreateNew(_user, true);
    }

    /**
     * Sync setup RF7 - create new EW account - created for PR113RF7RF8LtLSrvExs
     */
    public static void syncSetupRF7EWCreateNew(RFUser _user, Boolean _fromInstaller) throws Exception{
        lgP = "syncSetupRF7EWCreateNew";
        setLogBlockPrefix("syncSetupRF7EWCreateNew");
        log("User ID: " + _user.getUserID());
        log("Password: " + _user.getPassword());
        log("User Name: " + _user.getPassword());
        log("Email: " + _user.getEmail());

        log("Attaching to sync setup window");

        DElement wrk;
        DElement ssW;
        List<DElement> wrkL;
        if (_fromInstaller){
            try{
                gL(null,"Supreme window",10,"ncD", InstallRF7GD.supremeW.name,"#32770");
            } catch (Exception e) {

            }
            DElement supW = gL(null,"Supreme window",2,"nc",InstallRF7GD.supremeW.name, InstallRF7GD.supremeW.cName);

            ssW = gL(supW,"Sync setup window",2,"nc",InstallRF7GD.mainW.name, InstallRF7GD.mainW.cName);

            //
            log("Window is attached, make sure that this is correct window");
            gL(ssW,"Marker",2,"n",InstallRF7GD.mrkEW.name);
            log("Getting radiobutton list (Desktop/EW switch)");
            wrkL = gVL(ssW,"Rb fam",1,"c",InstallRF7GD.rbFam.cName);
            //first item is for Everywhere
            //second item is for Desktop
            try{
                wrk = wrkL.get(0);
            }
            catch (Exception e){
                throw new Exception("Getting correct radiobutton by index problem");
            }
            log("Verifying that clicking that will not crash us");
            if (wrk == null){
                throw new Exception("Cannot find the first radiobutton");
            }

            sleepy(1);
            log("Clicking radiobutton - switching to Everywhere mode");
            wrk.click();

            wrk = gL(ssW,"Next button",1,"nc",InstallRF7GD.nextB.name,InstallRF7GD.nextB.cName);

            wrk.click();
            sleepy(5);

            wrk = gL(ssW, "Create new account radiobutton",1,"nc",InstallRF7GD.rbNew.name,InstallRF7GD.rbNew.cName);
            wrk.click();
            sleepy(1);
        }
        else
        {
            ssW = gL(null,"Sync settings window",1,"nc", SyncD.syncWS.name,SyncD.syncWS.cName);
        }

        wrk = gL(ssW,"UserID edit",1,"nc",InstallRF7GD.editUserID.name,InstallRF7GD.editUserID.cName);

        wrk.click();
        wrk.setEditValue(_user.getUserID());
        sleepy(0.5);

        log("Getting list of password fields");
        wrkL = gVL(ssW,"Fields",1,"nc",InstallRF7GD.editAccountPassword.name,"Edit");

        log("Entering passwords in field family");
        try{
            log("Account Password field");
            wrk = wrkL.get(0);
            wrk.click();
            wrk.setEditValue(_user.getPassword());
            sleepy(0.5);
            log("Confirm Password field");
            wrk = wrkL.get(1);
            wrk.click();
            wrk.setEditValue(_user.getPassword());
        } catch (Exception e) {
            logE("Exception caught.");
            throw e;
        }
        sleepy(0.5);

        wrk = gL(ssW,"Your Name field",1,"nc",InstallRF7GD.editYourName.name,InstallRF7GD.editYourName.cName);

        wrk.click();
        wrk.setEditValue(_user.getName());
        sleepy(0.5);

        wrk = gL(ssW,"Email field",1,"nc",InstallRF7GD.editEmail.name,InstallRF7GD.editEmail.cName);

        wrk.click();
        wrk.setEditValue(_user.getEmail());
        sleepy(0.5);

        wrk = gL(ssW,"Next button",1,"nc",InstallRF7GD.nextBtnEW.name, InstallRF7GD.nextBtnEW.cName);

        wrk.click();
        sleepy(1);

        //MP windows handling
        syncSetupRF7NewMP(ssW, _user);
        setLogBlockPrefix("syncSetupRF7EWCreateNew-II");
        sleepy(3); //should be enough to account creation to complete

        //waiting and verifying that sync is not in progress
        //gL(ssW,"Sync in progress button",5,"Donc","true",InstallRF7GD.finishBtnSS.name,InstallRF7GD.finishBtnSS.cName);
        gL(ssW,RF7.finishB,"",5,"D");

        log("Account creation seems to be complete");
        //wrk = gL(ssW,"Next button",5,"ncaeo",InstallRF7GD.nextBtnEWEndNew.name,InstallRF7GD.nextBtnEWEndNew.cName,InstallRF7GD.nextBtnEWEndNew.autId,"true","false");
        wrk = gL(ssW,RF7.nextB,5);

        log("Button is found, clicking");
        wrk.click();
        log("Waiting for sync to complete");
        sleepy(3);
        gL(ssW,"Stop button",10,"Dhnc","true",InstallRF7GD.stopBtnSE.name,InstallRF7GD.stopBtnSE.cName);

        log("Procedure complete");
    }

    /**
     * Handling Enter MP window on Desktop setup and for setting up MP at the end of EW setup
     * @param _syncW sync setup window
     */
    private static void syncSetupRF7NewMP(DElement _syncW, RFUser _user) throws Exception{
        lgP = "setupSyncRF7NewMP";
        setLogBlockPrefix("setupSyncRF7NewMP");
        log("Starting setting up MP");
        //DElement wrk = gimMe(_syncW,InstallRF7GD.editMP1,"First MP window");
        //DElement wrk = gL(_syncW,"First MP Window",1,"c","Edit");
        DElement wrk = gL(_syncW,RF7.MP1E,1);

        wrk.click();
        sleepy(0.3);
        wrk.setEditValue(_user.getPassword());
        sleepy(0.3);
        //wrk = gimMe(_syncW,InstallRF7GD.editMP2,"Second MP Window");
        //wrk = DElement.gimMePN(_syncW,"Second MP window",1,1,"c","Edit");
        wrk = gL(_syncW,RF7.MP2E,1);

        wrk.click();
        sleepy(0.3);
        wrk.setEditValue(_user.getPassword());
        sleepy(0.3);
        log("Going further");
        //wrk = gimMe(_syncW,InstallRF7GD.nextBtnDesktop,"Next button");
        wrk = gL(_syncW,RF7.nextB,3);
        wrk.click();
    }
    /**
     * RF7 sync setup : Desktop
     */
    public static void setupSyncRF7Desktop(RFUser _user) throws Exception{
        lgP = "RF7 Sync Setup" + logSeparator + "Desktop";
        setLogBlockPrefix("setupSyncRF7Desktop");
        log("Starting procedure");
        log("Quick check to disappear and re-appear of the supW");
        try {
            gL(null,"SupremeW",10,"DncU",InstallRF7GD.supremeW.name, InstallRF7GD.supremeW.cName,"1000");
        } catch (Exception e) {
            log("Setup window did not disappear, continuing...");
        }
        DElement supW = gimMe(null,InstallRF7GD.supremeW,"Supreme window");
        supW.setForeground();
        log("Attaching to sync setup window");

        DElement ssW = gL(supW,"Sync setup window",4,"NcU","Install RoboForm","#32770","6000");
        log("Window is attached, make sure that this is correct window");

        gL(ssW,"Marker text",1,"N","Everywhere");
        log("Getting radiobutton list");

        sleepy(1);
        DElement wrk = DElement.gimMePN(ssW,"Radiobutton",1,1,"c",InstallRF7GD.rbFam.cName);
        wrk.click();
        log("Clicking radiobutton - switching to Desktop mode");

        wrk = gimMe(ssW,InstallRF7GD.nextB,"Next button");
        wrk.click();
        sleepy(5);

        syncSetupRF7NewMP(ssW, _user); //use existing password for Desktop RF7
    }

    public static void syncRF7() throws Exception{
        lgP = "Manual sync (RF7)";
        setLogBlockPrefix("syncRF7");
        log("Starting procedure");
        EditorProc.runRF7Editor();
        sleepy(2);
        log("Locating RF button");
        //DElement rfEW = gimMeP(null,EditorRF7MW.defMainW,"Editor window",2,"","na");
        DElement rfEW = gL(null, RF7.editorW,2);
        sleepy(1);
        //DElement wrk = gimMe(rfEW,EditorRF7MW.grandRFB,"Grand RoboForm button",2);
        DElement wrk = gL(rfEW,RF7.grandRFB,2);
        log("Clicking");
        wrk.click();

        log("Clicking Sync");
        sleepy(1);
        offsetClick(wrk,10,85);

        log("Clicking Sync RoboForm");
        sleepy(1);

        offsetClick(wrk,210,85);

        log("Connecting to Sync window");
        //DElement sW = gimMe(null, RF7SyncD.syncW,"Sync window",2);
        DElement sW = gL(null,RF7.syncW,2);
        sleepy(0.5);
        try{
            log("Contacting the Stop button");
//        gimMeP(sW,RF7SyncD.stopB,"Stop button",4,"k","ao");
            gL(sW,RF7.stopB,"",4,"h","true");
            log("Waiting for Stop button to disappear");
            //gimMeP(sW,RF7SyncD.stopB,"Stop button",10,"dk","ao");
            gL(sW,RF7.stopB,"",10,"Dh","true");
            sleepy(1);
        } catch (Exception e){
            log("Seems like sync was too fast. It's OK.");
        }
        //wrk = gimMe(sW,RF7SyncD.closeBUn,"Close button",2);
        wrk = gL(sW,RF7.closeB,2);
        log("Clicking");
        wrk.click();
        sleepy(2);
        log("Procedure complete");
    }

    /**
     * RF7 sync setup : Desktop
     */
    public static void setupSyncRF7DesktopNoMP() throws Exception{
        lgP = "RF7 Sync Setup: Desktop (no MP)";
        setLogBlockPrefix("setupSyncRF7DesktopNoMP");
        log("Starting procedure");

        log("Attaching to sync setup window");
        try{
            //gL(null,"Supreme window",10,"Dnc",InstallRF7GD.supremeW.name,"#32770");
            gL(null,RF7.supremeW,"",10,"D");
        } catch (Exception e){
            log("OK - Window was not closed");
        }
        //DElement supW = gL(null,"Supreme window",1,"nc",InstallRF7GD.supremeW.name,"#32770");
        DElement supW = gL(null,RF7.supremeW,1);

        //DElement ssW = gL(supW,"Main window",1,"nc",InstallRF7GD.mainW.name,"#32770");
        DElement ssW = gL(supW,RF7.mainW,1);

        log("Window is attached, make sure that this is correct window");
        //gL(ssW,"Marker",1,"n",InstallRF7GD.mrkEW.name);
        gL(ssW, RF7Markers.mrkEW,1);


        log("Getting radiobutton list");
        //DElement wrk = DElement.gimMePN(ssW,"Desktop edit",1,1,"c","Button");
        DElement wrk = gNL(ssW,RF7.radioBF,"no",1,1,"");

        sleepy(1);
        log("Clicking radiobutton - switching to Desktop mode");
        wrk.click();

        //wrk = gL(ssW,"Next button",1,"nc",InstallRF7GD.nextB.name,"Button");
        wrk = gL(ssW,RF7.nextB,1);

        wrk.click();
        sleepy(5);

        log("Closing sync setup window");

        //ssW = gL(supW,"Main window",1,"nc",InstallRF7GD.mainW.name,"#32770");
        ssW = gL(supW,RF7.mainW,1);

        try {
            DElement tWrk = gL(ssW,"Title bar",1,"l","title bar");
//            wrk = gL(tWrk,"Close button",1,"n",InstallRF7GD.btnTBClose.name);
            wrk = gL(tWrk,RF7.closeTBB,1);

            sleepy(1);
            wrk.click();
            sleepy(1);
            log("Verifying whether Sync setup window was closed or not:");
            //gL(null,"Sync setup window",3,"Dnc",InstallRF7GD.mainW.name,"#32770");
            gL(null,RF7.mainW,"",3,"D");

            log("Sync setup window has been closed successfully");
        }
        catch(Exception e)
        {
            logE("Could not locate title bar and perform evil things");
        }


    }


}
