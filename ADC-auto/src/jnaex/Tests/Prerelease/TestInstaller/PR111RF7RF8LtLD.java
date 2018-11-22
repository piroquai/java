package jnaex.Tests.Prerelease.TestInstaller;

import jnaex.RFWin.*;
import jnaex.RFWin.User.DataTypeStates;
import jnaex.RFWin.User.RFUser;
import jnaex.RFWin.User.Servers;
import jnaex.RFWin.User.UserProc;
import testLogger.ITest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;


/**
 * Created by Anton Golovchenko on 6/5/2017.
 * Prerelease Test Plan:
 * 1. Installation
 * 1. Upgrade
 * 1.1. Local to local (Desktop)
 *
 * 1. Install RF7 in Desktop mode (no server data exist).
 * 2. Install RF8, convert data, enter correct email.
 * 3. Check that onefile data does not exist on server (on RFO and on other device).
 * E. RF is installed successfully, onefile data does not exist on server.
 */
public class PR111RF7RF8LtLD implements ITest{
    private void sleepy(double s){  //in seconds
        Proc.sleepy(s);
    }
    private void log(String s){ //normal log
        Proc.log(s);
    }
    private void logE(String s){ //error log
        Proc.logE(s);
    }

    @Override
    public void performTest(Vector<String> buf){
        try{
            Proc.setBuf(buf);
            Proc.setGLP("PR111LtLD");
            Proc.setLogScenarioPrefix("PR111");
            log("RF7 to RF8 local to local (Desktop)");
            UserProc.addNewCustomUser(Servers.USSTAGING,DataTypeStates.RF7);
            RFUser testUser = UserProc.getUser(UserProc.addNewCustomUser(Servers.USSTAGING, DataTypeStates.RF7));

            log("I Uninstall RF");
            Proc.setLogStepPrefix("I");
            InstallerProc.uninstallRF();
            sleepy(2.5);
            log("II Basic install RF7");
            Proc.setLogStepPrefix("II");
            InstallerProcRF7.basicInstallRF7();
            sleepy(2.7);
            log("III Setup sync RF7 Desktop");
            Proc.setLogStepPrefix("III");
            TBIProcRF7.setupSyncRF7Desktop(testUser);
            sleepy(2.7);
            //creating safenote to check data transfer
            log("IV Create SN RF7");
            Proc.setLogStepPrefix("IV");
            EditorProcRF7.CreateSnCRF7();
            sleepy(2.7);
            log("V Upgrade RF7 to RF8");
            Proc.setLogStepPrefix("V");
            InstallerProc.upgradeRF7RF8();
            sleepy(4.7);
            log("VI Sync setup script RF7 RF8 Local to local Desktop");
            Proc.setLogStepPrefix("VI");
            TBIProc.syncSetupScriptRF7RF8LtLD(testUser);
            testUser.setDataTypeState(DataTypeStates.RF7CONVERTED);
            sleepy(4.7);
            log("VII Uninstall RF");
            Proc.setLogStepPrefix("VII");
            InstallerProc.uninstallRF();
            sleepy(4.7);
            //reinstall RF, setup sync, turn it OFF
            log("VIII Basic install RF8");
            Proc.setLogStepPrefix("VIII");
            InstallerProc.basicInstall();
            sleepy(4.7);
            log("IX Sync setup RF8 current user");
            Proc.setLogStepPrefix("IX");
            TBIProc.syncSetupRF8Current(testUser);
            sleepy(4.4);
            log("X Turn sync Off");
            Proc.setLogStepPrefix("X");
            EditorProc.syncTurnOff();
            sleepy(4.3);
            //check whether it is able to create a safenote or not
            log("XI Verify SN RF8");
            Proc.setLogStepPrefix("XI");
            EditorProc.verifySnCRF8();
            sleepy(4.2);
            //uninstalling
            log("XII Uninstall RF");
            Proc.setLogStepPrefix("XII");
            InstallerProc.uninstallRF();

            Proc.setLogBlockPrefix("");
            log("Test was completed without fatal exceptions");
        }
        catch (Exception e){
            Proc.setLogStepPrefix("ERROR");
            logE(e.getMessage());
            ITest.uninstallIfError(true);
//            log("Cleaning");
//            try {
//                CommonTestBlock.uninstallRF();
//                logE("Cleaning was successful");
//            }
//            catch(Exception ex){
//                logE("Cleaning was not successful: " + ex.getMessage());
//            }
        }
        buf = Proc.getBuf();
    }
}