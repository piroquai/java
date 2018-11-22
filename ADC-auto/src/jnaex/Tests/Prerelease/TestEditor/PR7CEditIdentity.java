package jnaex.Tests.Prerelease.TestEditor;

import jnaex.RFWin.EditorProc;
import jnaex.RFWin.Proc;
import testLogger.ITest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

/**
 * Prerelease Test Plan
 * 7. Editor
 * C. Edit Identity (+Create new Instances)
 */
public class PR7CEditIdentity implements ITest {
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
        Proc.setGLP("PR7CEditIdentity");
        Proc.setLogScenarioPrefix("PR7C");
        log("Editor Edit Identity (+new Instances)");
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

            String identityFilename = "identity1.txt";
            String identityName = Proc.getItemFileProperty(identityFilename,"ITEM_NAME");

            Proc.setLogStepPrefix("I");
            EditorProc.runRF8Editor();

            //            ProcessBuilder pro = new ProcessBuilder("C://Users//Autotester//juy.bat");
//            try {
//                Process shell = pro.start();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            sleepy(2);

            sleepy(1);
            Proc.setLogStepPrefix("II");
            EditorProc.ensureMinimalEditorWidth(900);

            Proc.setLogStepPrefix("III");
            if (!(EditorProc.createNewIdentity(identityName)))
            {
                throw new Exception("Identity with the name " + identityName + " already exists.");
            }

            Proc.setLogStepPrefix("IV");
            EditorProc.fillIdentity(identityFilename);
            sleepy(5);
            Proc.setLogStepPrefix("V");
            EditorProc.manualsyncRF();
            sleepy(5);
            Proc.setLogStepPrefix("VI");
            EditorProc.handleSync();

/*            sleepy(3);
            CommonTestBlock.uninstallRF(driver);
            sleepy(5);
            CommonTestBlock.basicInstall(driver);
            sleepy(5);
            CommonTestBlock.syncSetupRF8Current(driver);
            sleepy(5);
            //EditorProc.checkItemContents(driver,EditorD.safenoteBtn.name,snName,"~Edit~[2]",snText);
            sleepy(5);


*/  //          if (Proc.preinstallRF){
//                CommonTestBlock.uninstallRF(driver);
//                sleepy(2.3);
//            }

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
