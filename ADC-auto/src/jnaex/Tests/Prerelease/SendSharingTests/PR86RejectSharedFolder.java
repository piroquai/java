package jnaex.Tests.Prerelease.SendSharingTests;

import jnaex.RFWin.EditorProc;
import jnaex.RFWin.Proc;
import jnaex.RFWin.SSTestBlock;
import jnaex.RFWin.User.RFUser;
import jnaex.RFWin.User.UserProc;
import testLogger.ITest;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * Created by  Evgenia on 07-Aug-17.
 */
public class PR86RejectSharedFolder implements ITest{

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
        Proc.setGLP(" PR86RejectSharedFolder");
        Proc.setLogScenarioPrefix("PR86");
        log(" Folder was with REGULAR rights");
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

            RFUser testUser = UserProc.getUser();

//            String S_email = testUser.getEmail();
//            String S_password = testUser.getPassword();
//            Date d = new Date();
//            SimpleDateFormat fd = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
//            String R_email = SSTestBlock.emailRReceive;
//            String R_password = SSTestBlock.passwRReceive;
            Proc.setLogStepPrefix("I");
            EditorProc.runRF8Editor();
            sleepy(2);
            Proc.setLogStepPrefix("II");
            EditorProc.changeAccount(UserProc.getUserCustom(4));
            sleepy(2);
            Proc.setLogStepPrefix("III");
            EditorProc.handleSync(SSTestBlock.nameSFolder, testUser, 1, true);
            sleepy(2);
            Proc.setLogStepPrefix("IV");
            EditorProc.searchFile(SSTestBlock.nameSFolder, false);
            sleepy(3);
            //SSTestBlock.roleConformationReceiver(SSTestBlock.nameSFolder, "Regular");
            sleepy(3);
            Proc.setLogStepPrefix("V");
            EditorProc.closeRF8EditorSoft();

            Proc.setLogBlockPrefix("");
            log("Test was completed without fatal exceptions");
        }
        catch (Exception e){
            Proc.setLogStepPrefix("ERROR");
            logE(e.getMessage());
            ITest.uninstallIfError(Proc.preinstallRF);
//            log("Cleaning");
//            try {
//                CommonTestBlock.uninstallRF();
//                logE("Cleaning was successful");
//            }
//            catch(Exception ex){
//                logE("Cleaning was not successful: " + ex.getMessage());
//            }
        }

        buf = Proc.getBuf();
    }
}
