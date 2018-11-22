package jnaex.Tests.Prerelease.TestEditor;

import jnaex.RFWin.*;
import jnaex.RFWin.Editor.EditorD;
import jnaex.RFWin.User.RFUser;
import jnaex.RFWin.User.UserProc;
import testLogger.ITest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

/**
 *  Prerelease Test Plan
 *  7. Editor
 *  5. Create Application
 */
public class PR75CreateApplication implements ITest{
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
        Proc.setGLP("PR75CreateApplication");
        Proc.setLogScenarioPrefix("PR75");
        log("Editor Create Application");
        try{
            ITest.preinstall(Proc.preinstallRF);
//            if (Proc.preinstallRF){
//                CommonTestBlock.uninstallRF();
//                sleepy(2);
//
//                CommonTestBlock.basicInstall();
//                sleepy(4.7);
//                CommonTestBlock.setupSyncNew();
//                sleepy(6.7);
//            }
            RFUser testUser = UserProc.getUser();

            String applicationName = "Test Application";
            String appLogin = "qwertyqwerty";
            String appPassword = "asdfghasdfgh";
            log("Stage I Restart TBI");
            Proc.setLogStepPrefix("I");
            EditorProc.restartTBI();
            sleepy(3.2);
            try{
                log("Stage Ia Turn RF On");
                Proc.setLogStepPrefix("II");
                EditorProc.turnRFOn();
                sleepy(0.3);
                log("Stage Ib Login using current password");
                Proc.setLogStepPrefix("III");
                EditorProc.loginPR(testUser);
                sleepy(1.2);
            } catch (Exception e){
                log("Editor was not turned off");
            }
            log("Stage II Login to TBI");
            Proc.setLogStepPrefix("IV");
            EditorProc.loginToTBI(testUser);
            log("Stage III Launch target application");
            Proc.setLogStepPrefix("V");
            ApplicationProc.launchAppLT();
            log("Stage IV Kill target application");
            Proc.setLogStepPrefix("VI");
            ApplicationProc.killAppLT();
            log("Stage V Launch target application");
            Proc.setLogStepPrefix("VII");
            ApplicationProc.launchAppLT();
            sleepy(3.5);
            log("Stage VI Handle bad application conditions");
            Proc.setLogStepPrefix("VIII");
            ApplicationProc.handleBadAppLT();
            sleepy(5.5);
            log("Stage VII Click Save button");
            Proc.setLogStepPrefix("IX");
            ApplicationProc.clickSave();
            sleepy(3.5);
            log("Stage VIII Trigger login AutoSave");
            Proc.setLogStepPrefix("X");
            ApplicationProc.performLoginSave(appLogin,appPassword);
            sleepy(3.5);
            log("Stage IX Handle AutoSave");
            Proc.setLogStepPrefix("XI");
            ApplicationProc.handleAutoSave(applicationName);
            sleepy(3.5);
            log("Stage X Kill target application");
            Proc.setLogStepPrefix("XII");
            ApplicationProc.killAppLT();
            sleepy(3.5);
            log("Stage XI Run RF8 Editor");
            Proc.setLogStepPrefix("XIII");
            EditorProc.runRF8Editor();
            sleepy(0.5);
            log("Stage XII Check item existence");
            Proc.setLogStepPrefix("XIV");
            EditorProc.checkItemExistence(EditorD.applicationsBtn.name,applicationName);
            sleepy(2);
            log("Stage XIII Kill TBI to restart it properly");
            Proc.setLogStepPrefix("XV");
            EditorProc.killTBI();
            sleepy(2);
            log("Stage XIV Turn RF On");
            Proc.setLogStepPrefix("XVI");
            EditorProc.turnRFOn();
            sleepy(0.3);
            log("Stage XV Login using current password");
            Proc.setLogStepPrefix("XVII");
            EditorProc.loginPR(testUser);
            sleepy(1.2);
            log("Stage XVI Closing Editor");
            Proc.setLogStepPrefix("XVIII");
            EditorProc.closeRF8EditorSoft();
            sleepy(1.2);


            if (Proc.preinstallRF){
                Proc.setLogStepPrefix("XIX");
                InstallerProc.uninstallRF();
                sleepy(2.3);
            }

            Proc.setLogBlockPrefix("");
            log("Test was completed without fatal exceptions");
        }
        catch (Exception e){
            Proc.setLogStepPrefix("ERROR");
            logE(e.getMessage());
            ApplicationProc.killAppLT();
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
            ITest.uninstallIfError(Proc.preinstallRF);
        }

        buf = Proc.getBuf();
    }


}
