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
 * Created by Autotester on 6/22/2017.
 * Prerelease Test Plan:
 * 1. Installation
 * 1. Upgrade
 * 1.3. Local to local (server data exist)
 *
 * 1. Install RF7, setup sync.
 * 2. Create some data, sync.
 * 3. Check on RFO that multifile data exists. //hard to do
 * 4. Turn the sync off.
 * 5. Install RF8, convert data, enter correct email (from Step 1).
 * 6. Check on server that onefile data does not exist (on RFO and on other device) [mulfifile data is erased on upgrade].
 * E. RF is installed successfully, onefile data does not exist on server.
 */
public class PR113RF7RF8LtLSrvExs implements ITest{
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
        Proc.setGLP("PR113LtLSrvExs");
        Proc.setLogScenarioPrefix("PR113");
        log("RF7 to RF8 local to local (server data exist)");
        try{
            UserProc.addNewCustomUser(Servers.USSTAGING,DataTypeStates.RF7);
            RFUser testUser = UserProc.getUser(UserProc.addNewCustomUser(Servers.USSTAGING, DataTypeStates.RF7));

            Proc.setLogStepPrefix("I");
            InstallerProc.uninstallRF();
            sleepy(3);
            Proc.setLogStepPrefix("II");
            InstallerProcRF7.basicInstallRF7();
            sleepy(2.7);
            Proc.setLogStepPrefix("III");
            TBIProcRF7.syncSetupRF7EWCreateNew(testUser);
            sleepy(2.7);
            //creating safenote to check data transfer
            Proc.setLogStepPrefix("IV");
            EditorProcRF7.CreateSnCRF7(false);
            sleepy(2.7);
            //sync and turn the sync off
            Proc.setLogStepPrefix("V");
            TBIProcRF7.syncRF7();
            sleepy(4.7);
            Proc.setLogStepPrefix("VI");
            EditorProcRF7.syncTurnOffRF7();
            sleepy(4.7);

            Proc.setLogStepPrefix("VII");
            InstallerProc.upgradeRF7RF8();
            sleepy(4.7);
            Proc.setLogStepPrefix("VIII");
            TBIProc.syncSetupScriptRF7RF8LtLSrvExs(testUser);
            testUser.setDataTypeState(DataTypeStates.RF7CONVERTED);
            sleepy(4.7);
            Proc.setLogStepPrefix("IX");
            EditorProc.verifySnCRF8(false);
            sleepy(4.7);
            Proc.setLogStepPrefix("X");
            InstallerProc.uninstallRF();
            sleepy(4.7);
            //reinstall RF, setup sync, turn it OFF
            Proc.setLogStepPrefix("XI");
            InstallerProc.basicInstall();
            sleepy(4.7);
            Proc.setLogStepPrefix("XII");
            TBIProc.syncSetupRF8withRF7cred(testUser);
            sleepy(4.4);
            Proc.setLogStepPrefix("XIII");
            EditorProc.syncTurnOff();
            sleepy(4.3);
            //check whether it is able to create a safenote or not
            Proc.setLogStepPrefix("XIV");
            EditorProc.verifySnCRF8();//positive since the data should be erased
            sleepy(4.2);
            //uninstalling
            Proc.setLogStepPrefix("XV");
            InstallerProc.uninstallRF();

            Proc.setLogBlockPrefix("");
            log("Test was completed without fatal exceptions");
        }
        catch (Exception e){
            Proc.setLogStepPrefix("ERROR");
            logE(e.getMessage());
            /*
            log("Cleaning");
            try {
                DesiredCapabilities cap = new DesiredCapabilities();
                WebDriver driver = new RemoteWebDriver(new URL("http://localhost:9999"), cap);
                CommonTestBlock.uninstallRF(driver);
                logE("Cleaning was successful");
            }
            catch(Exception ex){
                logE("Cleaning was not successful: " + ex.getMessage());
            }*/
            ITest.uninstallIfError(true);

        }
        buf = Proc.getBuf();
    }

}
