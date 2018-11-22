package jnaex.Tests.Prerelease.SendSharingTests;

import jnaex.RFWin.EditorProc;
import jnaex.RFWin.Proc;
import jnaex.RFWin.SSTestBlock;
import jnaex.RFWin.User.RFUser;
import jnaex.RFWin.User.UserProc;
import testLogger.ITest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * Created by Autotester on 8/30/2017.
 */
public class PR8ASendFileSync implements ITest{
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
        Proc.setGLP(" PR8A Send file with Sync"); //Place global log prefix
        Proc.setLogScenarioPrefix("PR8A");
        log(" PR8A Send file with Sync"); //Place test description
        try {
            ITest.preinstall(Proc.preinstallRF);

            RFUser testUser = UserProc.getUser();
//            String S_email = testUser.getEmail();
//            String S_password = testUser.getPassword();
//
//            String R_email = SSTestBlock.emailOReceive;
//            String R_password = SSTestBlock.passwOReceive;

            Proc.setLogStepPrefix("I");
            EditorProc.runRF8Editor();
            sleepy(2);
            //EditorProc.createNewAccount(R_email, R_password);
            sleepy(2);
            Proc.setLogStepPrefix("II");
            EditorProc.changeAccount(testUser);
            sleepy(2);
            Proc.setLogStepPrefix("III");
            EditorProc.handleSync();
            String _nameSN = "SNforSending1";
            SSTestBlock.nameSendFile = _nameSN;
            Proc.setLogStepPrefix("IV");
            EditorProc.createSafenoteRF8(_nameSN);
            //to account with sync
            Proc.setLogStepPrefix("V");
            SSTestBlock.sendFile(UserProc.getUserCustom(3), false, _nameSN);
            sleepy(2);
            Proc.setLogStepPrefix("VI");
            EditorProc.manualsyncRF();
            sleepy(1);
            Proc.setLogStepPrefix("VII");
            EditorProc.handleSync();
            sleepy(3);
            //to account without sync
            Proc.setLogStepPrefix("VIII");
            SSTestBlock.sendFile(UserProc.getUserCustom(2), false, _nameSN);

            sleepy(2);
            Proc.setLogStepPrefix("IX");
            EditorProc.manualsyncRF();
            sleepy(1);
            Proc.setLogStepPrefix("X");
            EditorProc.handleSync();
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
