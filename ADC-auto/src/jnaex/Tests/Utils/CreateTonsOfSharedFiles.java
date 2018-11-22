package jnaex.Tests.Utils;

import jnaex.RFWin.EditorProc;
import jnaex.RFWin.Proc;
import jnaex.RFWin.SSTestBlock;
import jnaex.RFWin.User.UserProc;
import testLogger.ITest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * Created by Autotester on 12/13/2017.
 */
public class CreateTonsOfSharedFiles implements ITest{
    private void sleepy(double s) {  //in seconds
        Proc.sleepy(s);
    }

    private void log(String s) { //normal log
        Proc.log(s);
    }

    private void logE(String s) { //error log
        Proc.logE(s);
    }

    private static final int numberOfFilesToShare = 150;
    @Override
    public void performTest(Vector<String> buf) {
        Proc.setBuf(buf);
        Proc.setGLP(" Sharing " + numberOfFilesToShare + " files"); //Place global log prefix
        Proc.setLogScenarioPrefix("SharTons");
        log("Util test. Sharing " + numberOfFilesToShare + " files"); //Place test description
        try {
            ITest.preinstall(Proc.preinstallRF);

//        SSTestBlock.emailSend = CommonTestBlock.getRF8SSEmail();
//        SSTestBlock.passwSend = CommonTestBlock.getRF8SSPassword();
//        SSTestBlock.emailSend = "agolov+fmlc-ccreg@siber.com";
//        SSTestBlock.passwSend = "qwerty123456";

//        String S_email = SSTestBlock.emailSend;
//        String S_password = SSTestBlock.passwSend;

//        Date d = new Date();
//        SimpleDateFormat fd = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");

        //String R_email = CommonTestBlock.emailSSRF8Prefix + fd.format(d) + "_FileReceive" + CommonTestBlock.emailSSRF8Suffix;
        //String R_password = "qwerty";

//        String R_email = "agolov+d171213-1@siber.com";
//            String R_email = SSTestBlock.emailOReceive;
//        String R_password = SSTestBlock.passwOReceive;

            Proc.setLogStepPrefix("I");
            EditorProc.runRF8Editor();
            sleepy(2);
        //EditorProc.createNewAccount(R_email, R_password);
//        sleepy(2);
//        EditorProc.changeAccount(S_email, S_password);
//        sleepy(2);
//        EditorProc.handleSync();
        //for accepting

            Proc.setLogStepPrefix("II");
            for (int i = 1; i <= numberOfFilesToShare; i++){
                String _nameSN = "NewSN" + i;
                SSTestBlock.nameShareFileA = _nameSN;
                EditorProc.createSafenoteRF8(_nameSN);
                SSTestBlock.shareFile(UserProc.getUserCustom(6), false, _nameSN);
            }
            sleepy(2);
            Proc.setLogStepPrefix("III");
            EditorProc.manualsyncRF();
            sleepy(1);
            Proc.setLogStepPrefix("IV");
            EditorProc.handleSync();
            sleepy(3);
            Proc.setLogStepPrefix("V");
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