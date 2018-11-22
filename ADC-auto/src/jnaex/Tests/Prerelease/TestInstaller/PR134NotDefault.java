package jnaex.Tests.Prerelease.TestInstaller;

import jnaex.RFWin.InstallerProc;
import jnaex.RFWin.Proc;
import jnaex.RFWin.TBIProc;
import jnaex.RFWin.User.DataTypeStates;
import jnaex.RFWin.User.RFUser;
import jnaex.RFWin.User.Servers;
import jnaex.RFWin.User.UserProc;
import testLogger.ITest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

/**
 * Created by Autotester on 7/27/2017.
 * Prerelease Test Plan:
 * 1. Installation
 * 3. Clear
 * 3.4. Not in the default directory
 *
 * 1. Start installing RF8, click Show advanced options.
 * 2. Change default directories for program files and user data, proceed with the installation.
 * 3. Check that program files and user data are saved in selected directories.
 * E. Program files and user data are saved in selected directories.
 *
 * 4. Check that default directories do not contain RF data.
 * E. No RF data and files in default directories.
*/
public class PR134NotDefault implements ITest{
    private void sleepy(double s){  //in seconds
        Proc.sleepy(s);
    }
    private void log(String s){ //normal log
        Proc.log(s);
    }
    private void logE(String s){ //error log
        Proc.logE(s);
    }

    private static String testFolder = "C:\\Program Files\\Adata";

    @Override
    public void performTest(Vector<String> buf){
        Proc.setBuf(buf);
        Proc.setGLP("PR134NotDefault");
        Proc.setLogScenarioPrefix("PR134");
        log("1.3.3.4 Not in the default directory");
        try{
            RFUser testUser = UserProc.getUser(UserProc.addNewCustomUser(Servers.USSTAGING, DataTypeStates.RF8));
            //Start installing
            //Select Advanced options
            //Default dir
            Proc.setLogStepPrefix("I");
            InstallerProc.advancedInstall(false,null,testFolder,null);
            sleepy(4.7);
            //Proceed
            Proc.setLogStepPrefix("II");
            TBIProc.setupSyncNew(testUser);
            sleepy(4.5);
            //Check that saved
            //Check default directories
            //Uninstall
            Proc.setLogStepPrefix("III");
            InstallerProc.uninstallRF();

            Proc.setLogBlockPrefix("");
            log("Test was completed without fatal exceptions");
        }
        catch (Exception e){
            Proc.setLogStepPrefix("ERROR");
            logE(e.getMessage());
            ITest.uninstallIfError(true);
        }
        buf = Proc.getBuf();
    }

}
