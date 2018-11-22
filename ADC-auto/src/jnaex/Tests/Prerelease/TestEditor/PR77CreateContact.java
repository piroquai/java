package jnaex.Tests.Prerelease.TestEditor;

import jnaex.RFWin.Editor.EditorD;
import jnaex.RFWin.EditorProc;
import jnaex.RFWin.InstallerProc;
import jnaex.RFWin.Proc;
import testLogger.ITest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

/**
 *  Prerelease Test Plan
 *  7. Editor
 *  7. Create Contact
 */

public class PR77CreateContact implements ITest{
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
        Proc.setGLP("PR77CreateContact");
        Proc.setLogScenarioPrefix("PR77");
        log("Editor Create Contact");
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

            String contactName = "Test Contact";
            Proc.setLogStepPrefix("I");
            EditorProc.runRF8Editor();
            sleepy(1.2);
            Proc.setLogStepPrefix("II");
            if (!(EditorProc.createNewContact(contactName)))
            {
                throw new Exception("Contact with the name " + contactName + " already exists.");
            }
            //to refresh contact list - BEGIN
            Proc.setLogStepPrefix("III");
            EditorProc.slyCleaningMove( EditorD.safenoteBtn.name, EditorD.contactBtn.name);
            sleepy(1.2);
            Proc.setLogStepPrefix("IV");
            EditorProc.closeRF8EditorSoft();
            sleepy(1.2);
            Proc.setLogStepPrefix("V");
            EditorProc.runRF8Editor();
            sleepy(1.2);
            Proc.setLogStepPrefix("VI");
            EditorProc.slyCleaningMove(EditorD.safenoteBtn.name, EditorD.contactBtn.name);
            sleepy(1.2);
            //to refresh contact list - END
            Proc.setLogStepPrefix("VII");
            if ((EditorProc.createNewContact(contactName)))
            {
                throw new Exception("Contact with the name " + contactName + " did not exist.");
            }
            Proc.setLogStepPrefix("VIII");
            EditorProc.checkItemExistence(EditorD.contactBtn.name,contactName);

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
