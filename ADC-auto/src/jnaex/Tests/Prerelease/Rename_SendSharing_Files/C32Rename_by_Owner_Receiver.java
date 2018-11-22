package jnaex.Tests.Prerelease.Rename_SendSharing_Files;

import jnaex.RFWin.EditorProc;
import jnaex.RFWin.Proc;
import jnaex.RFWin.RenameTestBlock;
import jnaex.RFWin.SSTestBlock;
import jnaex.RFWin.User.RFUser;
import jnaex.RFWin.User.UserProc;
import jnaex.Run.RunRename;
import testLogger.ITest;

import java.util.Random;
import java.util.Vector;

/**
 * Created by  Evgenia on 21-Jun-18.
 * S-sender
 * R-receiver *
 * 2. S rename after R receive *
 * After receiving file, there are 2 different files independent of each other
 */
public class C32Rename_by_Owner_Receiver implements ITest{
    private void sleepy(double s) {  //in seconds
        Proc.sleepy(s);
    }

    private void log(String s) { //normal log
        Proc.log(s);
    }

    private void logE(String s) { //error log
        Proc.logE(s);
    }

    @Override
    public void performTest(Vector<String> buf) {
        Proc.setBuf(buf);
        Proc.setGLP(" C32 Rename Shared folder by Owner Receiver"); //Place global log prefix
        Proc.setLogScenarioPrefix("C32");
        log(" Rename Shared folder C.3.2"); //Place test description
        try {
            ITest.preinstall(Proc.preinstallRF);
            RFUser testUser = null;
            if (RunRename.custom) {
                testUser = UserProc.getUserCustom(7);//

            } else {
                testUser = UserProc.getUser();//
            }
            Proc.setLogStepPrefix("I");
            EditorProc.runRF8Editor();
            sleepy(2);
            Random random = new Random();
            int num1 = random.nextInt(1000);
            int num2 = random.nextInt(1000);
            RenameTestBlock.newnameSharedfolder = num1 + "SFolderNEW" + num2;
            sleepy(2);
            //rename folder by owner receiver
            Proc.setLogStepPrefix("II");
            RenameTestBlock.renameFile(RenameTestBlock.nameSharedfolder, RenameTestBlock.newnameSharedfolder);
            sleepy(2);
            Proc.setLogStepPrefix("III");
            EditorProc.manualsyncRF();
            sleepy(1);
            Proc.setLogStepPrefix("IV");
            EditorProc.handleSync();
            //////

            //change account on owner sender
            Proc.setLogStepPrefix("V");
            EditorProc.changeAccount(testUser);
            sleepy(2);
            //EditorProc.searchFile(RenameTestBlock.newnameSharedfolder);
            sleepy(3);
            Proc.setLogStepPrefix("VI");
            EditorProc.closeRF8EditorSoft();
            Proc.setLogBlockPrefix("");
            log("Test was completed without fatal exceptions");

        } catch (Exception e) {
            Proc.setLogStepPrefix("ERROR");
            logE(e.getMessage());
            ITest.uninstallIfError(Proc.preinstallRF);
        }
        buf = Proc.getBuf();
    }
}
