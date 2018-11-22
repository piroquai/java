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
 * D. Edit Contact (+Create new Instances)
 */
public class PR7DEditContact implements ITest{
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
        Proc.setGLP("PR7DEditContact");
        Proc.setLogScenarioPrefix("PR7D");
        log("Editor Edit Contact (+new Instances)");
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


            String contactFilename = "contact1.txt";
            String contactName = Proc.getItemFileProperty(contactFilename,"ITEM_NAME");

            Proc.setLogStepPrefix("I");
            EditorProc.runRF8Editor();
            Proc.setLogStepPrefix("II");
            if (!(EditorProc.createNewContact(contactName)))
            {
                throw new Exception("Contact with the name " + contactName + " already exists.");
            }

            Proc.setLogStepPrefix("III");
            EditorProc.fillContact(contactFilename);
            sleepy(5);
            Proc.setLogStepPrefix("IV");
            EditorProc.manualsyncRF();
            sleepy(5);
            Proc.setLogStepPrefix("V");
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


*/ //            if (Proc.preinstallRF){
//                CommonTestBlock.uninstallRF(driver);
//                sleepy(2.3);
//            }

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
//                    DesiredCapabilities cap = new DesiredCapabilities();
//                    WebDriver driver = new RemoteWebDriver(new URL("http://localhost:9999"), cap);
//                    CommonTestBlock.uninstallRF(driver);
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
