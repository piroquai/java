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
 * Created by Autotester on 7/17/2017.
 * Prerelease Test Plan:
 * 1. Installation
 * 1. Upgrade
 * 1.5. Sync to sync
 *
 * 1. Install RF7, setup sync.
 * 2. Create some data, sync.
 * 3. Install RF8, convert data, enter correct email.
 * 4. Check on server that onefile data exists (on RFO [later] and on other device).
 * E. RF is installed successfully, onefile data exists on server.
 */
public class PR115RF7RF8StS implements ITest{
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
        Proc.setGLP("PR115StS");
        Proc.setLogScenarioPrefix("PR115");
        log("RF7 to RF8 sync to sync");
        try{
//            UserProc.addNewCustomUser(Servers.USSTAGING,DataTypeStates.RF7);
            RFUser testUser = UserProc.getUser(UserProc.addNewCustomUser(Servers.USSTAGING, DataTypeStates.RF7));
            Proc.setLogStepPrefix("I");
            InstallerProcRF7.basicInstallRF7();
            sleepy(2.7);
            Proc.setLogStepPrefix("II");
            TBIProcRF7.syncSetupRF7EWCreateNew(testUser);
            sleepy(2.7);
            //creating safenote to check data transfer
            Proc.setLogStepPrefix("III");
            EditorProcRF7.CreateSnCRF7(false);
            sleepy(2.7);
            Proc.setLogStepPrefix("IV");
            InstallerProc.upgradeRF7RF8();
            sleepy(4.7);

            Proc.setLogStepPrefix("V");
            TBIProc.syncSetupScriptRF7ToRF8StS(testUser);
            testUser.setDataTypeState(DataTypeStates.RF7CONVERTED);
            sleepy(4.7);

            Proc.setLogStepPrefix("VI");
            InstallerProc.uninstallRF();
            sleepy(4.7);
            //reinstall RF, setup sync, turn it OFF
            Proc.setLogStepPrefix("VII");
            InstallerProc.basicInstall();
            sleepy(4.7);
            Proc.setLogStepPrefix("VIII");
            TBIProc.syncSetupRF8withRF7cred(testUser);
            sleepy(4.4);
            //check whether it is able to create a safenote or not
            Proc.setLogStepPrefix("IX");
            EditorProc.verifySnCRF8(false);//negative since the data should exist
            sleepy(4.2);
            //uninstalling
            Proc.setLogStepPrefix("X");
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
