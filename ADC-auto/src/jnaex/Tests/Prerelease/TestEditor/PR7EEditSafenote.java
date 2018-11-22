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
 * Prerelease Test Plan
 * 7. Editor
 * E. Edit Safenote
 */
public class PR7EEditSafenote implements ITest {
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
        Proc.setGLP("PR7EEditSafenote");
        Proc.setLogScenarioPrefix("PR7E");
        log("Editor Edit Safenote");
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

            String snName = "tgtSafenote";
            String snText = "tgtText";
            Proc.setLogStepPrefix("I");
            EditorProc.runRF8Editor();
            try{
                Proc.setLogStepPrefix("IIa");
                EditorProc.checkItemExistence(EditorD.safenoteBtn.name,snName);
            }
            catch (Exception e){
                Proc.setLogStepPrefix("IIb");
                EditorProc.createSafenoteRF8(snName);
            }
            Proc.setLogStepPrefix("III");
            EditorProc.editSafenote(snName,snText,false);
            sleepy(3);
//            EditorProc.manualsyncRF();
//            sleepy(5);
//            EditorProc.handleSync();
//            sleepy(3);
            Proc.setLogStepPrefix("IV");
            InstallerProc.uninstallRF();
            sleepy(5);
            Proc.setLogStepPrefix("V");
            InstallerProc.basicInstall();
            sleepy(5);
            Proc.setLogStepPrefix("VI");
            TBIProc.syncSetupRF8Current(UserProc.getUser());
            sleepy(5);
            Proc.setLogStepPrefix("VII");
            EditorProc.checkItemContents(EditorD.safenoteBtn.name,snName,"~Edit~[1]",snText);
            sleepy(5);


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
