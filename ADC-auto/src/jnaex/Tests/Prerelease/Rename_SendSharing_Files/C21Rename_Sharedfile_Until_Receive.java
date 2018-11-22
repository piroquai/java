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
 * 1. S rename until R receive
 *  * After receiving file, there are 2 different files independent of each other
 */
public class C21Rename_Sharedfile_Until_Receive implements ITest{
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
        Proc.setGLP(" C21 Rename Shared file, until R receive"); //Place global log prefix
        Proc.setLogScenarioPrefix("C21");
        log(" Rename Shared file C.2.1"); //Place test description
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
            sleepy(2);
            Proc.setLogStepPrefix("IIa");
            EditorProc.changeAccount(testUser);//отправитель
            sleepy(2);
            Proc.setLogStepPrefix("III");
            EditorProc.handleSync();
            Proc.setLogStepPrefix("IIIb");
            EditorProc.removeAllItems();
            String _nameSN = "SNforSharing21"; //создаем файл
            Proc.setLogStepPrefix("IV");
            EditorProc.createSafenoteRF8(_nameSN);

            //send to account
            if (RunRename.custom) {
                Proc.setLogStepPrefix("Va");
                SSTestBlock.shareFile(UserProc.getUserCustom(3), false, _nameSN);//получатель

            } else {
                Proc.setLogStepPrefix("Vb");
                SSTestBlock.shareFile(UserProc.getUser(1), false, _nameSN);//получатель
            }
            sleepy(2);
            Proc.setLogStepPrefix("VI");
            EditorProc.manualsyncRF();
            sleepy(1);
            Proc.setLogStepPrefix("VII");
            EditorProc.handleSync();
            sleepy(3);

            //rename file untill R receive
            Random rnd = new Random(System.currentTimeMillis());
            String newname = Integer.toString(rnd.nextInt(50000));
            Proc.setLogStepPrefix("VIII");
            RenameTestBlock.renameFile(_nameSN, newname);
            sleepy(5);
            //change account
            if (RunRename.custom) {
                Proc.setLogStepPrefix("IXa");
                EditorProc.changeAccount(UserProc.getUserCustom(3));

            } else {
                Proc.setLogStepPrefix("IXb");
                EditorProc.changeAccount(UserProc.getUser(1));
            }
            sleepy(2);
            String  newName = EditorProc.getNewName(_nameSN, testUser, "share");
            //accept send file
            Proc.setLogStepPrefix("X");
            EditorProc.handleSync(_nameSN, testUser, 2, true);
            sleepy(2);
            //EditorProc.searchFile(newName);
            sleepy(3);
            Proc.setLogStepPrefix("XI");
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
