package jnaex.Tests.Prerelease.Setup_Settings_Tests;

import jnaex.RFWin.*;
import jnaex.RFWin.User.UserProc;
import testLogger.ITest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * Created by  Evgenia on 15-Sep-17.
 */
public class PR52SyncDiffrentWithLocal implements ITest {
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
        Proc.setGLP("5.2 Setup (settings)");
        Proc.setLogScenarioPrefix("PR52");
        log("Sync with the diff account");
        try{
//            if (Proc.preinstallRF){
//                CommonTestBlock.uninstallRF();
//                sleepy(2);
//                CommonTestBlock.basicInstall();
//                sleepy(4.7);
//                CommonTestBlock.setupSyncNew();
//                sleepy(6.7);
//            }
            ITest.preinstall(Proc.preinstallRF);
            log("I Run Editor");
            Proc.setLogStepPrefix("I");
            EditorProc.runRF8Editor();
            sleepy(2);
//            Date d = new Date();
//            SimpleDateFormat fd = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
//            SetupSTestBlock.pr5new2Email = CommonTestBlock.emailSSRF8Prefix + fd.format(d) + "new" + CommonTestBlock.emailSSRF8Suffix;
//            SetupSTestBlock.pr5new2Password = "qwerty";


//            sleepy(2);
//            EditorProc.createNewAccount(SetupSTestBlock.pr5new2Email, SetupSTestBlock.pr5new2Password);
//            sleepy(2);
            //String _e = "evtitova+tester84@siber.com";
            //String _p = "tester84";
//            EditorProc.changeAccount(Proc.getRF8SSEmail(), Proc.getRF8SSPassword());
            //EditorProc.changeAccount(_e, _p);
            sleepy(2);
//            log("II Handle sync");
//            EditorProc.handleSync();
            Random random = new Random();
            int num1 = random.nextInt(1000);
            int num2 = random.nextInt(1000);
            String st = num1 + "note" + num2;
            log("III Create Safenote RF8");
            Proc.setLogStepPrefix("II");
            EditorProc.createSafenoteRF8(st);

            log("IV Sync settings RF");
            Proc.setLogStepPrefix("III");
            EditorProc.syncSettingsRF(true, UserProc.getUser(1));
            sleepy(1);
            log("V Handle sync");
            Proc.setLogStepPrefix("IV");
            EditorProc.handleSync();
            sleepy(4.7);
            log("VI Search file");
            Proc.setLogStepPrefix("V");
            EditorProc.searchFile("testnote", false);
            sleepy(1);
            log("VII Close RF8 Editor soft");
            Proc.setLogStepPrefix("VI");
            EditorProc.closeRF8EditorSoft();
            sleepy(1);

            Proc.setLogBlockPrefix("");
            log("Test was completed without fatal exceptions");
        }
        catch (Exception e){
            Proc.setLogStepPrefix("ERROR");
            logE(e.getMessage());
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
