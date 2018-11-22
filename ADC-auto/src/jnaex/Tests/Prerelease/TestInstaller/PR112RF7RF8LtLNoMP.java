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
 * 1.2. Local to local (No MP entered)
 *
 * 1. Install RF7 in Desktop mode, click cancel on create MP.
 * 2. Install RF8, convert data, enter correct email, create MP.
 * 3. Check that onefile data do not exist on server (on RFO and on other device).
 * E. RF is installed successfully, onefile data do not exist on server.
*/
public class PR112RF7RF8LtLNoMP implements ITest{
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
        Proc.setGLP("PR112LtLNoMP");
        Proc.setLogScenarioPrefix("PR112");
        log("RF7 to RF8 local to local (No MP entered)");
        try{
            UserProc.addNewCustomUser(Servers.USSTAGING,DataTypeStates.RF7);
            RFUser testUser = UserProc.getUser(UserProc.addNewCustomUser(Servers.USSTAGING, DataTypeStates.RF7));

            Proc.setLogStepPrefix("I");
            InstallerProcRF7.basicInstallRF7();
            sleepy(2.7);
            Proc.setLogStepPrefix("II");
            TBIProcRF7.setupSyncRF7DesktopNoMP();
            sleepy(2.7);
            //creating safenote to check data transfer
            Proc.setLogStepPrefix("III");
            EditorProcRF7.CreateSnCRF7(true);
            sleepy(2.7);
            Proc.setLogStepPrefix("IV");
            InstallerProc.upgradeRF7RF8();
            sleepy(4.7);
            Proc.setLogStepPrefix("V");
            TBIProc.syncSetupScriptRF7RF8LtLNoMP(testUser);
            Proc.setLogStepPrefix("VI");
            testUser.setDataTypeState(DataTypeStates.RF7CONVERTED);
            sleepy(4.7);
            Proc.setLogStepPrefix("VII");
            InstallerProc.uninstallRF();
            sleepy(4.7);
//            reinstall RF, setup sync, turn it OFF
            Proc.setLogStepPrefix("VIII");
            InstallerProc.basicInstall();
            sleepy(4.7);
            Proc.setLogStepPrefix("IX");
            TBIProc.syncSetupRF8Current(testUser);
            sleepy(4.4);
            Proc.setLogStepPrefix("X");
            EditorProc.syncTurnOff();
            sleepy(4.3);
            //check whether it is able to create a safenote or not
            Proc.setLogStepPrefix("XI");
            EditorProc.verifySnCRF8();
            sleepy(4.2);
            //uninstalling
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