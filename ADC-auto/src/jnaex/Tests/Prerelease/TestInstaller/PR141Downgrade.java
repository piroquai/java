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
 * Created by Autotester on 7/20/2017.
 * Prerelease Test Plan:
 * 1. Installation
 * 4. Downgrade
 * 4.1. Sync to sync
 *
 * 1. Install RF8, setup sync.
 * 2. Install RF7, setup sync.
 * E. Installation is finished successfully
 */
public class PR141Downgrade implements ITest{
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
        Proc.setGLP("PR141Dwngrd");
        Proc.setLogScenarioPrefix("PR141");
        log("RF8 Downgrade to RF7");
        try{
            RFUser testUser = UserProc.getUser(UserProc.addNewCustomUser(Servers.USSTAGING, DataTypeStates.RF8));
            Proc.setLogStepPrefix("I");
            InstallerProc.basicInstall();
            sleepy(4.7);
            //setup sync
            Proc.setLogStepPrefix("II");
            TBIProc.setupSyncNew(testUser);
            sleepy(4.7);
            //
            Proc.setLogStepPrefix("III");
            InstallerProcRF7.basicInstallRF7(true);
            sleepy(20); //can be artifacts otherwise
            Proc.setLogStepPrefix("IV");
            EditorProc.runRF8Editor();
            sleepy(2.7);
            //TO=DO: verify that there is no bad windows
//            CommonTestBlock.eradicateSetupErrorsRF8RF7Downgrade(driver);
//            sleepy(1.8);
            Proc.setLogStepPrefix("V");
            EditorProc.turnOnSync();
            sleepy(2.7);
            RFUser testUserN = UserProc.getUser(UserProc.addNewCustomUser(Servers.USSTAGING,DataTypeStates.RF7));
            Proc.setLogStepPrefix("VI");
            TBIProcRF7.syncSetupRF7EWCreateNew(testUserN, false);//new user from Editor
            //uninstalling
            Proc.setLogStepPrefix("VII");
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
