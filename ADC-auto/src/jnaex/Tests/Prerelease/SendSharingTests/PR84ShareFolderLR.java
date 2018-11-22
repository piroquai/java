package jnaex.Tests.Prerelease.SendSharingTests;

import jnaex.RFWin.EditorProc;
import jnaex.RFWin.Proc;
import jnaex.RFWin.SSTestBlock;
import jnaex.RFWin.User.UserProc;
import testLogger.ITest;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * Created by  Evgenia on 04-Aug-17.
 */
public class PR84ShareFolderLR implements ITest{
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
        Proc.setGLP(" PR84ShareFolderLR");
        Proc.setLogScenarioPrefix("PR84");
        log(" PR84ShareFolderLR");
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
//            String S_email = SSTestBlock.emailSend;
//            String S_password = SSTestBlock.passwSend;
            //SSTestBlock.emailSend = CommonTestBlock.getRF8SSEmail();
            //SSTestBlock.passwSend = CommonTestBlock.getRF8SSPassword();
//            Date d = new Date();
//            SimpleDateFormat fd = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
            //String R_email = CommonTestBlock.emailSSRF8Prefix + fd.format(d) + "_LRreceive" + CommonTestBlock.emailSSRF8Suffix;
//            String R_email = "evtitova+aut2017_12_07_10_26_36_LRreceive@siber.com";
//            String R_password = "qwerty";
//            SSTestBlock.emailLReceive = R_email;
//            SSTestBlock.passwLReceive = R_password;

            Proc.setLogStepPrefix("I");
            EditorProc.runRF8Editor();
            sleepy(2);
            //EditorProc.createNewAccount(R_email, R_password);
            sleepy(2);
            //EditorProc.changeAccount(S_email, S_password);
            sleepy(2);
            //EditorProc.handleSync();
            Proc.setLogStepPrefix("II");
            SSTestBlock.shareFolder(UserProc.getUserCustom(5), true, SSTestBlock.nameSFolder, "Login only");
            sleepy(2);
            Proc.setLogStepPrefix("III");
            EditorProc.manualsyncRF();
            sleepy(1);
            Proc.setLogStepPrefix("IV");
            EditorProc.handleSync(); // operates with sync window
            sleepy(3);
            //SSTestBlock.roleConformationSender(SSTestBlock.nameSFolder, "Limited");
            sleepy(3);
            Proc.setLogStepPrefix("V");
            EditorProc.closeRF8EditorSoft();

            Proc.setLogBlockPrefix("");
            log("Test was completed without fatal exceptions");
        }
        catch (Exception e){
            Proc.setLogStepPrefix("ERROR");
            logE(e.getMessage());
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
            ITest.uninstallIfError(Proc.preinstallRF);
        }

        buf = Proc.getBuf();
    }
}
