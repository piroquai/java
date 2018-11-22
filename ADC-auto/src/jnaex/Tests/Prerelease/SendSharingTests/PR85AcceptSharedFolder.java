package jnaex.Tests.Prerelease.SendSharingTests;

import jnaex.RFWin.EditorProc;
import jnaex.RFWin.Proc;
import jnaex.RFWin.SSTestBlock;
import jnaex.RFWin.User.RFUser;
import jnaex.RFWin.User.UserProc;
import testLogger.ITest;


import java.util.Vector;

/**
 * Created by  Evgenia on 07-Aug-17.
 */
public class PR85AcceptSharedFolder implements ITest {

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
        Proc.setGLP(" PR85AcceptSharedFolder");
        Proc.setLogScenarioPrefix("PR85");
        log(" Folder was with OWNER rights");
        try{
            ITest.preinstall(Proc.preinstallRF);
            RFUser testUser = UserProc.getUser();

//            String S_email = testUser.getEmail();
//            String S_password = testUser.getPassword();
//            Date d = new Date();
//            String R_email = SSTestBlock.emailOReceive;
//            String R_password = SSTestBlock.passwOReceive;
            Proc.setLogStepPrefix("I");
            EditorProc.runRF8Editor();
            sleepy(2);
            Proc.setLogStepPrefix("II");
            EditorProc.changeAccount(UserProc.getUserCustom(3));
            sleepy(2);
            Proc.setLogStepPrefix("III");
            EditorProc.handleSync(SSTestBlock.nameSFolder, testUser, 2, true);
            sleepy(2);
            Proc.setLogStepPrefix("IV");
            EditorProc.searchFile(SSTestBlock.nameSFolder);
            sleepy(3);
            //SSTestBlock.roleConformationReceiver(SSTestBlock.nameSFolder, "Owner");
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
        }

        buf = Proc.getBuf();
    }
}
