package jnaex.Tests.Prerelease.TestEditor;

import jnaex.RFWin.EditorProc;
import jnaex.RFWin.InstallerProc;
import jnaex.RFWin.Proc;
import jnaex.RFWin.User.UserProc;
import testLogger.ITest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

/**
 * Created by Autotester on 7/27/2017.
 * Prerelease Test Plan
 * 7. Editor
 * 1. Logoff Login
 */
public class PR71LogoffLogin implements ITest{
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
        Proc.setGLP("PR71LogoffLogin");
        Proc.setLogScenarioPrefix("PR71");
        log("Editor Logoff-Login");
        try{
            ITest.preinstall(Proc.preinstallRF);
//            if (Proc.preinstallRF){
//                CommonTestBlock.uninstallRF();
//                sleepy(2);
//                CommonTestBlock.basicInstall();
//                sleepy(4.7);
//                CommonTestBlock.setupSyncNew();
//                sleepy(6.7);
//            }

            Proc.setLogStepPrefix("I");
            EditorProc.runRF8Editor();
            sleepy(1.2);
            Proc.setLogStepPrefix("II");
            EditorProc.logoff();
            sleepy(1.2);
            Proc.setLogStepPrefix("III");
            EditorProc.loginPR(UserProc.getUser());
            sleepy(1.2);
            Proc.setLogStepPrefix("IV");
            EditorProc.closeRF8EditorSoft();
            sleepy(1.2);

            if (Proc.preinstallRF){
                Proc.setLogStepPrefix("V");
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
        }

        buf = Proc.getBuf();
    }
}
