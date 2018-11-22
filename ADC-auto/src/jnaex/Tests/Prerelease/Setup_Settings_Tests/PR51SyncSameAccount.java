package jnaex.Tests.Prerelease.Setup_Settings_Tests;

import jnaex.RFWin.EditorProc;
import jnaex.RFWin.Proc;
import testLogger.ITest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

/**
 * Created by  Evgenia on 15-Sep-17.
 */
public class PR51SyncSameAccount implements ITest {

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
        Proc.setGLP("5.1 Setup (settings)");
        Proc.setLogScenarioPrefix("PR51");
        log("Sync with the same account");
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
            log("II Sync settings RF (w/o changes)");
            Proc.setLogStepPrefix("II");
            EditorProc.syncSettingsRF(false);
            sleepy(1);
            log("III Handle sync");
            Proc.setLogStepPrefix("III");
            EditorProc.handleSync();
            sleepy(4.7);
            log("IV Close Editor softly");
            Proc.setLogStepPrefix("IV");
            EditorProc.closeRF8EditorSoft();
            sleepy(1);

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
