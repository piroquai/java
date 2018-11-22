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
 * 2.2. Sync to sync
 *
 * 1. Install recent official RF8, setup sync.
 * 2. Create some data, sync.
 * 3. Install relevant RF8.
 * 4. Check on server that onefile data exists (on RFO [later] and on other device).
 * E. RF is installed successfully, onefile data exists on server.
 */
public class PR122RF8UStS implements ITest{
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
        Proc.setGLP("PR122StS");
        Proc.setLogScenarioPrefix("PR122");
        log("RF8 Update sync to sync");
        try{
            log("I Basic install (official RF)");
            Proc.setLogStepPrefix("I");
            InstallerProc.basicInstall(true);
            sleepy(4.7);
            //setup sync
            log("II Generate new RF8 user");
            int i = Proc.generateNewRF8User();
            RFUser user = UserProc.getUser(i);
            log("III Sync setup for the new user");
            Proc.setLogStepPrefix("II");
            TBIProc.setupSyncNew(user);
            sleepy(4.7);
            //
            log("IV Run Editor");
            Proc.setLogStepPrefix("III");
            EditorProc.runRF8Editor();
            sleepy(2);
            log("V Create a Safenote");
            Proc.setLogStepPrefix("IV");
            EditorProc.createSafenoteRF8(EditorProc.nameSNDefault);
            sleepy(2);
            log("VI Perform manual sync");
            Proc.setLogStepPrefix("V");
            EditorProc.manualsyncRF();
            sleepy(1);
            log("VII Handle sync");
            Proc.setLogStepPrefix("VI");
            EditorProc.handleSync(); // operates with sync window
            sleepy(3);

            log("VIII Uninstall RF");
            Proc.setLogStepPrefix("VII");
            InstallerProc.uninstallRF();
            sleepy(4.7);
            //reinstall RF, setup sync, turn it OFF
            log("IX Basic install of current RF");
            Proc.setLogStepPrefix("VIII");
            InstallerProc.basicInstall();
            sleepy(4.7);
            log("X Sync setup using current RF8 credentials");
            Proc.setLogStepPrefix("IX");
            TBIProc.syncSetupRF8Current(user);
            sleepy(4.4);
            //check whether it is able to create a safenote or not
            log("XI Verify created Safenote");
            Proc.setLogStepPrefix("X");
            EditorProc.verifySnCRF8(false);//negative since the data should exist
            sleepy(4.2);
            //uninstalling
            log("XII Uninstall RF");
            Proc.setLogStepPrefix("XI");
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
