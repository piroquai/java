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
public class C22Rename_Sharedfile_After_Receive implements ITest{
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
        Proc.setGLP(" C22 Rename shared file after R receive"); //Place global log prefix
        Proc.setLogScenarioPrefix("C22");
        log(" Rename Share file C.2.2"); //Place test description
        try {
            ITest.preinstall(Proc.preinstallRF);

            RFUser testUser = null;
            if (RunRename.custom) {
                testUser = UserProc.getUserCustom(7);//

            } else {
                testUser = UserProc.getUser();//
            }
//
            Proc.setLogStepPrefix("I");
            EditorProc.runRF8Editor();
            sleepy(2);
            sleepy(2);
            Proc.setLogStepPrefix("II");
            EditorProc.changeAccount(testUser);//отправитель
            sleepy(2);
            Proc.setLogStepPrefix("III");
            EditorProc.handleSync();
            Random rnd = new Random(System.currentTimeMillis());
            String oldname = "SNforSharing22"; //создаем файл
            Proc.setLogStepPrefix("IV");
            EditorProc.createSafenoteRF8(oldname);

            //share to account
            if (RunRename.custom) {
                Proc.setLogStepPrefix("Va");
                SSTestBlock.shareFile(UserProc.getUserCustom(3), false, oldname);//получатель

            } else {
                Proc.setLogStepPrefix("Vb");
                SSTestBlock.shareFile(UserProc.getUser(1), false, oldname);//получатель
            }
            sleepy(2);
            Proc.setLogStepPrefix("VI");
            EditorProc.manualsyncRF();
            sleepy(1);
            Proc.setLogStepPrefix("VII");
            EditorProc.handleSync();
            sleepy(3);

            //change account
            if (RunRename.custom) {
                Proc.setLogStepPrefix("VIIIa");
                EditorProc.changeAccount(UserProc.getUserCustom(3));

            } else {
                Proc.setLogStepPrefix("VIIIb");
                EditorProc.changeAccount(UserProc.getUser(1));
            }
            sleepy(2);
            String  newName = EditorProc.getNewName(oldname, testUser, "share");
            //accept shared file
            Proc.setLogStepPrefix("IX");
            EditorProc.handleSync(oldname, testUser, 2, true);
            sleepy(2);

            //change acount to sender
            Proc.setLogStepPrefix("X");
            EditorProc.changeAccount(testUser);
            sleepy(2);
            Proc.setLogStepPrefix("XI");
            EditorProc.handleSync();
            sleepy(2);


            //rename file after R receive
            String newname = Integer.toString(rnd.nextInt(50000));
            Proc.setLogStepPrefix("XII");
            RenameTestBlock.renameFile(oldname, newname);
            sleepy(5);
            //change account to Receiver
            if (RunRename.custom) {
                Proc.setLogStepPrefix("XIIIa");
                EditorProc.changeAccount(UserProc.getUserCustom(3));
            } else {
                Proc.setLogStepPrefix("XIIIb");
                EditorProc.changeAccount(UserProc.getUser(1));

            }
            sleepy(2);
            Proc.setLogStepPrefix("XIV");
            EditorProc.handleSync();
            sleepy(2);
            //EditorProc.searchFile(newName);
            sleepy(3);
            Proc.setLogStepPrefix("XV");
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
