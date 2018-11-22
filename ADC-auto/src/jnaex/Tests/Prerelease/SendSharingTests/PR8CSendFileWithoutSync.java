package jnaex.Tests.Prerelease.SendSharingTests;

import jnaex.RFWin.EditorProc;
import jnaex.RFWin.Proc;
import jnaex.RFWin.SSTestBlock;
import jnaex.RFWin.User.RFUser;
import jnaex.RFWin.User.UserProc;
import testLogger.ITest;

import java.util.Vector;

/**
 * Created by Autotester on 8/30/2017.
 */
public class PR8CSendFileWithoutSync implements ITest{
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
        Proc.setGLP("  PR8C Send file without sync"); //Place global log prefix
        Proc.setLogScenarioPrefix("PR8C");
        log(" PR8C Send file without sync"); //Place test description
        try {
           // ITest.preinstall(Proc.preinstallRF);

//            String S_email = "evtitova+tester14@siber.com";
//            String S_password = "tester14";

//            String R_email = SSTestBlock.emailOReceive;
//            String R_password = SSTestBlock.passwOReceive;

            Proc.setLogStepPrefix("I");
            EditorProc.runRF8Editor();
            sleepy(2);
            //EditorProc.createNewAccount(R_email, R_password);
            sleepy(2);
            Proc.setLogStepPrefix("II");
            EditorProc.changeAccount(UserProc.getUserCustom(0));
            sleepy(2);
            Proc.setLogStepPrefix("III");
            EditorProc.handleSync();
            String _nameSN = "SNforSending1";
            log("I Create safenote");
            Proc.setLogStepPrefix("IV");
            EditorProc.createSafenoteRF8(_nameSN);
            //to account with sync
            log("II Send file");
            Proc.setLogStepPrefix("V");
            SSTestBlock.sendFile(UserProc.getUserCustom(3), false, _nameSN);
            sleepy(2);
            log("III Manual sync");
            Proc.setLogStepPrefix("VI");
            EditorProc.manualsyncRF();
            sleepy(1);
            log("IV Handle sync");
            Proc.setLogStepPrefix("VII");
            EditorProc.handleSync();
            sleepy(3);
            //to account without sync
            log("V Send file");
            Proc.setLogStepPrefix("VIII");
            SSTestBlock.sendFile(UserProc.getUserCustom(1), false, _nameSN);
            sleepy(2);
            log("VI Manual sync");
            Proc.setLogStepPrefix("IX");
            EditorProc.manualsyncRF();
            sleepy(1);
            log("VII Handle sync");
            Proc.setLogStepPrefix("X");
            EditorProc.handleSync();
            sleepy(3);
            log("VIII Close Editor");
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
