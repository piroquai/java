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
public class PR87SharingFile implements ITest {
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
        Proc.setGLP(" PR87 Sharing file"); //Place global log prefix
        Proc.setLogScenarioPrefix("PR87");
        log("PR87 Sharing file"); //Place test description
        try {
            ITest.preinstall(Proc.preinstallRF);
            RFUser testUser = UserProc.getUser();

//            SSTestBlock.emailSend = testUser.getEmail();
//            SSTestBlock.passwSend = testUser.getPassword();
//            String S_email = SSTestBlock.emailSend;
//            String S_password = SSTestBlock.passwSend;

//            Date d = new Date();
//            SimpleDateFormat fd = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
            //String R_email = CommonTestBlock.emailSSRF8Prefix + fd.format(d) + "_FileReceive" + CommonTestBlock.emailSSRF8Suffix;
            //String R_password = "qwerty";
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
            //for accepting


            String _nameSN = "NewSN1";
            SSTestBlock.nameShareFileA = _nameSN;
            Proc.setLogStepPrefix("IV");
            EditorProc.createSafenoteRF8(_nameSN);
            Proc.setLogStepPrefix("V");
            SSTestBlock.shareFile(UserProc.getUserCustom(3), false, _nameSN);
            //for rejecting
//            R_email = SSTestBlock.emailRReceive;
//            R_password = SSTestBlock.passwRReceive;
            _nameSN = "NewSN2";
            SSTestBlock.nameShareFileR = _nameSN;
            Proc.setLogStepPrefix("VI");
            EditorProc.createSafenoteRF8(_nameSN);
            Proc.setLogStepPrefix("VII");
            SSTestBlock.shareFile(UserProc.getUserCustom(4), false, _nameSN);
            sleepy(2);
            Proc.setLogStepPrefix("VIII");
            EditorProc.manualsyncRF();
            sleepy(1);
            Proc.setLogStepPrefix("IX");
            EditorProc.handleSync();
            sleepy(3);
            Proc.setLogStepPrefix("X");
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