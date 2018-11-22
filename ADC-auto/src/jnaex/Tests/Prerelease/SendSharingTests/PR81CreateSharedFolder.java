package jnaex.Tests.Prerelease.SendSharingTests;

import jnaex.RFWin.EditorProc;
import jnaex.RFWin.Proc;
import jnaex.RFWin.SSTestBlock;
import jnaex.RFWin.User.UserProc;
import testLogger.ITest;

import java.util.Random;
import java.util.Vector;

/**
 * Created by Evgenia on 26-Jul-17.
 *
 * 1. Create a shared folder from More > Create shared folder.
 * 2. Enter correct name.
 * 3. Try to create a second shared folder.
 * E. Only one shared folder can be created.
 */

public class PR81CreateSharedFolder implements ITest {
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
        Proc.setGLP(" PR81CreateSharedFolder");
        Proc.setLogScenarioPrefix("PR81");
        log("RF8CreateSharedFolder");
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
//            RFUser testUser = UserProc.getUser();
//            SSTestBlock.emailSend = testUser.getEmail();
//            SSTestBlock.passwSend = testUser.getPassword();
            Proc.setLogStepPrefix("I");
            EditorProc.runRF8Editor();
            sleepy(2);
            Random random = new Random();
            int num1 = random.nextInt(1000);
            int num2 = random.nextInt(1000);
//            String SFolderName = num1 + "SFolder" + num2;
            String SFolderName = num1 + SSTestBlock.sharedFolderDefaultIdentificatior + num2;

            SSTestBlock.nameSFolder = SFolderName;
            Proc.setLogStepPrefix("IIa");
            UserProc.getUser().clearData();
            EditorProc.removeAllItems();
            Proc.setLogStepPrefix("IIb");
            EditorProc.createSFolder(SFolderName );
            sleepy(2);
            Proc.setLogStepPrefix("III");
            EditorProc.manualsyncRF();
            sleepy(1);
            Proc.setLogStepPrefix("IV");
            EditorProc.handleSync(); // operates with sync window
            sleepy(3);
            Proc.setLogStepPrefix("V");
            EditorProc.closeRF8EditorSoft();

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
