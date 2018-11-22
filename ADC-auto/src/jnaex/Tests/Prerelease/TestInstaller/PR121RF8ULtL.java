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
 * Created by Autotester on 7/18/2017.
 * Prerelease Test Plan:
 * 1. Installation
 * 2. Update
 * 2.1. Local to local
 *
 * 1. Install recent official RF7 in Desktop mode.
 * 2. Create some data.
 * 3. Upgrade to recent official RF8. Leave the sync turned off. Enter a new valid email.
 * 4. Install relevant RF8.
 * 5. Check on server that onefile data does not exist (on RFO [later] and on other device).
 * E. RF is installed successfully, onefile data does not exist on server.
 */
public class PR121RF8ULtL implements ITest{
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
        Proc.setBuf(buf);
        Proc.setGLP("PR121LtL");
        Proc.setLogScenarioPrefix("PR121");
        log("RF8 Update local (through RF7 official) to local");
        try{
            UserProc.addNewCustomUser(Servers.USSTAGING,DataTypeStates.RF7);
            log("I Get RF7 user");
            RFUser user = UserProc.getRF7User();
            log("II Clear data for the user");
            user.clearData();
            log("III Basic install RF7");
            Proc.setLogStepPrefix("I");
            InstallerProcRF7.basicInstallRF7(true);
            sleepy(2.7);
            log("IV Setup sync of RF7 desktop");
            Proc.setLogStepPrefix("II");
            TBIProcRF7.setupSyncRF7Desktop(user);
            sleepy(2.7);
            //creating safenote to check data transfer
            log("V Create Safenote");
            Proc.setLogStepPrefix("III");
            EditorProcRF7.CreateSnCRF7();
            sleepy(2.7);
            log("VI Upgrade to RF8");
            Proc.setLogStepPrefix("IV");
            InstallerProc.upgradeRF7RF8(true);
            sleepy(8.7);
            log("VII Sync setup script RF7 to RF8 (local to local, desktop)");
            Proc.setLogStepPrefix("V");
            TBIProc.syncSetupScriptRF7RF8LtLD(user);
            log("VIII Setting actual DataTypeState");
            user.setDataTypeState(DataTypeStates.RF7CONVERTED);
            sleepy(4.7);
            log("IX Basic install > RF8");
            Proc.setLogStepPrefix("VI");
            InstallerProc.basicInstall();
            sleepy(4.7);
            log("X Sync setup script RF7 to RF8 upgrade (local to local)");
            Proc.setLogStepPrefix("VII");
            TBIProc.syncSetupScriptRF7ToRF8ULtL(user);
            sleepy(4.2);
//            CommonTestBlock.verifySnCRF8();
//            sleepy(4.2);
            log("XI Uninstall RF");
            Proc.setLogStepPrefix("VIII");
            InstallerProc.uninstallRF();
            sleepy(4.7);
            //reinstall RF, setup sync, turn it OFF
            log("XII Install RF8");
            Proc.setLogStepPrefix("IX");
            InstallerProc.basicInstall();
            sleepy(4.7);
            log("XIII Sync setup (current user RF8)");
            Proc.setLogStepPrefix("X");
            TBIProc.syncSetupRF8Current(user);
            sleepy(4.4);
            //check whether it is able to create a safenote or not
            log("XIV Verify created Safenote");
            Proc.setLogStepPrefix("XI");
            EditorProc.verifySnCRF8();
            sleepy(4.2);
            //uninstalling
            log("XV Uninstall RF");
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
