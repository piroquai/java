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
public class C11Rename_Send_Until_Receive implements ITest{
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
        Proc.setGLP(" C11 Rename Send file, until R receive"); //Place global log prefix
        Proc.setLogScenarioPrefix("C11");
        log(" C11 Rename Send file, until R receive"); //Place test description
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
            Proc.setLogStepPrefix("II");
            EditorProc.changeAccount(testUser);//отправитель
            sleepy(2);
            Proc.setLogStepPrefix("III");
            EditorProc.handleSync();
            String nameSN = "SNforSending11"; //создаем файл
            Proc.setLogStepPrefix("IV");
            EditorProc.createSafenoteRF8(nameSN);

            //send to account
            if (RunRename.custom) {
                Proc.setLogStepPrefix("Va");
                SSTestBlock.sendFile(UserProc.getUserCustom(3), false, nameSN);//получатель

            } else {
                Proc.setLogStepPrefix("Vb");
                SSTestBlock.sendFile(UserProc.getUser(1), false, nameSN);//получатель
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
            String newnameSN = Integer.toString(rnd.nextInt(50000));
            Proc.setLogStepPrefix("VIII");
            RenameTestBlock.renameFile(nameSN, newnameSN);

            //change account
            if (RunRename.custom) {
                Proc.setLogStepPrefix("IXa");
                EditorProc.changeAccount(UserProc.getUserCustom(3));

            } else {
                Proc.setLogStepPrefix("IXb");
                EditorProc.changeAccount(UserProc.getUser(1));
            }
            sleepy(2);
            String  newName = EditorProc.getNewName(nameSN, testUser, "send");
            //accept send file
            Proc.setLogStepPrefix("X");
            EditorProc.handleSync(nameSN, testUser, 2, true);
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
