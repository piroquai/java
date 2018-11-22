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
 * 3. Clear
 * 3.2. Install with not converted multifile account
 *
 * 0. To get fresh multifile account, install RF7, setup sync, create some data and sync.
 * 1. Install RF8 with multifile account.
 * 2. Convert account using correct email.
 * 3. Check on server that onefile data exists (on RFO [later] and on other device).
 * E. RF is installed successfully, onefile data exists on server.
 */
public class PR132NotConverted implements ITest{

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
        Proc.setGLP("PR132NotConv");
        Proc.setLogScenarioPrefix("PR132");
        log("1.3.3.2 Install with not converted multifile account");
        try{
            RFUser testUser = UserProc.getUser(UserProc.addNewCustomUser(Servers.USSTAGING, DataTypeStates.RF7));
            log("I Basic install RF7");
            Proc.setLogStepPrefix("I");
            InstallerProcRF7.basicInstallRF7();
            sleepy(2.7);
            log("II Sync setup RF7 EW");
            Proc.setLogStepPrefix("II");
            TBIProcRF7.syncSetupRF7EWCreateNew(testUser);
            sleepy(2.7);
            //creating safenote to check data transfer
            log("III Create Sn RF7");
            Proc.setLogStepPrefix("III");
            EditorProcRF7.CreateSnCRF7(false);
            sleepy(2.7);
            log("IV Sync RF7");
            Proc.setLogStepPrefix("IV");
            TBIProcRF7.syncRF7();
            sleepy(3);
            log("V Uninstall RF");
            Proc.setLogStepPrefix("V");
            InstallerProc.uninstallRF();
            sleepy(4.7);
            //reinstall RF, setup sync, turn it OFF
            log("VI Basic install RF8");
            Proc.setLogStepPrefix("VI");
            InstallerProc.basicInstall();
            sleepy(4.7);
            log("VII Sync setup RF8 with RF7 cred");
            Proc.setLogStepPrefix("VII");
            TBIProc.syncSetupRF8withRF7cred(testUser); //last two actions are redundant here but fine
            sleepy(4.4);
            log("VIII Sync setup RF8 with RF7 cred Decrypt data");
            Proc.setLogStepPrefix("VIII");
            TBIProc.syncSetupRF8WithRF7CredDecryptData(testUser);
            sleepy(3.5);
            //check whether it is able to create a safenote or not
            log("IX Verify Sn RF8");
            Proc.setLogStepPrefix("IX");
            EditorProc.verifySnCRF8(false);//negative since the data should exist
            sleepy(4.2);
            //uninstalling
            log("X Uninstall RF");
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
