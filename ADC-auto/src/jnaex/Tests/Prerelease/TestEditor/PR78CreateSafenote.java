package jnaex.Tests.Prerelease.TestEditor;
/**
 *  Prerelease Test Plan
 *  7. Editor
 *  8. Create Safenote
 */


import jnaex.RFWin.Editor.EditorD;
import jnaex.RFWin.EditorProc;
import jnaex.RFWin.InstallerProc;
import jnaex.RFWin.Proc;
import testLogger.ITest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

public class PR78CreateSafenote implements ITest{
    private static void sleepy(double s){  //in seconds
        Proc.sleepy(s);
    }
    private static void log(String s){ //normal log
        Proc.log(s);
    }
    private static void logE(String s){ //error log
        Proc.logE(s);
    }

    @Override
    public void performTest(Vector<String> buf){
        Proc.setBuf(buf);
        Proc.setGLP("PR78CreateSafenote");
        Proc.setLogScenarioPrefix("PR78");
        log("Editor Create Safenote");
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

            String safenoteName = "Test Safenote";
            Proc.setLogStepPrefix("I");
            EditorProc.runRF8Editor();
            sleepy(1.2);
            Proc.setLogStepPrefix("II");
            EditorProc.createSafenoteRF8(safenoteName);
            sleepy(2);
            //to refresh safenotes list - BEGIN
            Proc.setLogStepPrefix("III");
            EditorProc.slyCleaningMove(EditorD.contactBtn.name, EditorD.safenoteBtn.name);
            sleepy(1.2);
            Proc.setLogStepPrefix("IV");
            EditorProc.closeRF8EditorSoft();
            sleepy(1.2);
            Proc.setLogStepPrefix("V");
            EditorProc.runRF8Editor();
            sleepy(1.2);
            Proc.setLogStepPrefix("VI");
            EditorProc.slyCleaningMove(EditorD.identityBtn.name, EditorD.safenoteBtn.name);
            sleepy(1.2);
            //to refresh safenotes list - END
            Proc.setLogStepPrefix("VII");
            if ((EditorProc.createSafenoteRF8(safenoteName)))
            {
                throw new Exception("Safenote with the name " + safenoteName + " did not exist.");
            }
            Proc.setLogStepPrefix("VIII");
            EditorProc.checkItemExistence(EditorD.safenoteBtn.name,safenoteName);

            Proc.setLogStepPrefix("IX");
            EditorProc.closeRF8EditorSoft();
            sleepy(1.2);

            if (Proc.preinstallRF){
                Proc.setLogStepPrefix("X");
                InstallerProc.uninstallRF();
                sleepy(2.3);
            }

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
