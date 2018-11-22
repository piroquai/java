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
 * R-receiver
 * 1. S Owner rename shared folder
 *  *
 */
public class C31Rename_by_Owner_Sender implements ITest{
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
        Proc.setGLP(" C31 Rename Shared folder, by Owner Sender"); //Place global log prefix
        Proc.setLogScenarioPrefix("C31");
        log(" Rename Shared folder C.3.1"); //Place test description
        try {
            ITest.preinstall(Proc.preinstallRF);
            RFUser testUser = null;
            if (RunRename.custom) {
                testUser = UserProc.getUserCustom(7);//

            } else {
                testUser = UserProc.getUser();//
            }
            testUser.clearData();
            Proc.setLogStepPrefix("I");
            EditorProc.runRF8Editor();
            sleepy(2);
            EditorProc.removeAllItems();
            sleepy(2);
            Random random = new Random();
            int num1 = random.nextInt(1000);
            int num2 = random.nextInt(1000);
//            String SFolderName = num1 + "SFolder" + num2;
            String SFolderName = num1 + SSTestBlock.sharedFolderDefaultIdentificatior + num2;

            SSTestBlock.nameSFolder = SFolderName;
            Proc.setLogStepPrefix("II");
            EditorProc.createSFolder(SFolderName);
            sleepy(2);
            Proc.setLogStepPrefix("III");
            EditorProc.manualsyncRF();
            sleepy(1);
            Proc.setLogStepPrefix("IV");
            EditorProc.handleSync(); // operates with sync window
            sleepy(3);
            //share folder to 3 users : owner, regular, limited
            UserProc.getUser(0).clearData();//
            UserProc.getUser(1).clearData();//owner
            UserProc.getUser(2).clearData();//regular
            UserProc.getUser(3).clearData();//limited
            //owner
            if (RunRename.custom) {
                Proc.setLogStepPrefix("Va");
                SSTestBlock.shareFolder(UserProc.getUserCustom(3), true, SSTestBlock.nameSFolder, "Full control");

            } else {
                Proc.setLogStepPrefix("Vb");
                SSTestBlock.shareFolder(UserProc.getUser(1), true, SSTestBlock.nameSFolder, "Full control");
            }
            sleepy(2);
            Proc.setLogStepPrefix("VI");
            EditorProc.manualsyncRF();
            sleepy(1);
            Proc.setLogStepPrefix("VII");
            EditorProc.handleSync();
            //regular
            if (RunRename.custom) {
                Proc.setLogStepPrefix("VIIIa");
                SSTestBlock.shareFolder(UserProc.getUserCustom(4), true, SSTestBlock.nameSFolder, "Read and write");

            } else {
                Proc.setLogStepPrefix("VIIIb");
                SSTestBlock.shareFolder(UserProc.getUser(2), true, SSTestBlock.nameSFolder, "Read and write");
            }
            sleepy(2);
            Proc.setLogStepPrefix("IX");
            EditorProc.manualsyncRF();
            sleepy(1);
            Proc.setLogStepPrefix("X");
            EditorProc.handleSync();
            //limited
            if (RunRename.custom) {
                Proc.setLogStepPrefix("XIa");
                SSTestBlock.shareFolder(UserProc.getUserCustom(5), true, SSTestBlock.nameSFolder, "Login only");

            } else {
                Proc.setLogStepPrefix("XIb");
                SSTestBlock.shareFolder(UserProc.getUser(3), true, SSTestBlock.nameSFolder, "Login only");
            }
            sleepy(2);
            Proc.setLogStepPrefix("XII");
            EditorProc.manualsyncRF();
            sleepy(1);
            Proc.setLogStepPrefix("XIII");
            EditorProc.handleSync();
            //////
            //rename folder untill they receive
            Random rnd = new Random(System.currentTimeMillis());
            RenameTestBlock.nameSharedfolder = Integer.toString(rnd.nextInt(50000));
            Proc.setLogStepPrefix("XIV");
            RenameTestBlock.renameFile(SFolderName, RenameTestBlock.nameSharedfolder);

            //change account on owner
            if (RunRename.custom) {
                Proc.setLogStepPrefix("XVa");
                EditorProc.changeAccount(UserProc.getUserCustom(3));

            } else {
                Proc.setLogStepPrefix("XVb");
                EditorProc.changeAccount(UserProc.getUser(1));
            }
            sleepy(2);
            //accept shared folder
            Proc.setLogStepPrefix("XVI");
            EditorProc.handleSync(RenameTestBlock.nameSharedfolder, testUser, 2, true);
            sleepy(2);
            //EditorProc.searchFile(RenameTestBlock.nameSharedfolder);
            sleepy(3);
            Proc.setLogStepPrefix("XVII");
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
