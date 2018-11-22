package jnaex.Tests.Prerelease.TestEditor;

import jnaex.RFWin.*;
import jnaex.RFWin.User.RFUser;
import jnaex.RFWin.User.UserProc;
import testLogger.ITest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

/**
  *  Prerelease Test Plan
  *  7. Editor
  *  2. Change Account after Logoff
  */
public class PR72ChangeAccAfterLogoff implements ITest {
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
        Proc.setGLP("PR72ChangeAccAfLogoff");
        Proc.setLogScenarioPrefix("PR72");
        log("Editor Change Account after Logoff");
        try{
            Proc.setLogStepPrefix("I");
            InstallerProc.uninstallRF();
            sleepy(4.7);
            Proc.setLogStepPrefix("II");
            InstallerProc.basicInstall();
            sleepy(4.7);
            UserProc.getUser(0).clearData();
            UserProc.getUser(1).clearData();
            Proc.setLogStepPrefix("III");
            TBIProc.syncSetupRF8Current(UserProc.getUser(0));
            sleepy(4.7);
//            Proc.copyRF8UserToBuffer();
//            sleepy(0.5);
//            CommonTestBlock.uninstallRF();
//            sleepy(4.5);
//            CommonTestBlock.basicInstall();
//            sleepy(4.5);
//            CommonTestBlock.setupSyncNew();
//            sleepy(4.5);


            Proc.setLogStepPrefix("IV");
            EditorProc.runRF8Editor();
            sleepy(1.2);

            Proc.setLogStepPrefix("V");
            EditorProc.changeAccount(UserProc.getUser(1));
            sleepy(1.2);
            Proc.setLogStepPrefix("VI");
            EditorProc.handleSync();
            sleepy(1.1);

            Proc.setLogStepPrefix("VII");
            EditorProc.closeRF8EditorSoft();
            sleepy(1.2);

            if (Proc.preinstallRF){
                Proc.setLogStepPrefix("VIII");
                InstallerProc.uninstallRF();
                sleepy(2.3);
            }

            Proc.setLogBlockPrefix("");
            log("Test was completed without fatal exceptions");
        }
        catch (Exception e){
            Proc.setLogStepPrefix("ERROR");
            logE(e.getMessage());
            ITest.uninstallIfError(Proc.preinstallRF);
//            if (Proc.preinstallRF){
//                log("Cleaning");
//                try {
//                    CommonTestBlock.uninstallRF();
//                    logE("Cleaning was successful");
//                }
//                catch(Exception ex){
//                    logE("Cleaning was not successful: " + ex.getMessage());
//                }
//            }
        }

        buf = Proc.getBuf();
    }

}
