package jnaex.Tests.Prerelease.SendSharingTests;

import jnaex.RFWin.EditorProc;
import jnaex.RFWin.Proc;
import jnaex.RFWin.SSTestBlock;
import jnaex.RFWin.User.RFUser;
import jnaex.RFWin.User.UserProc;
import testLogger.ITest;

import java.util.Date;
import java.util.Vector;

/**
 * Created by Autotester on 8/30/2017.
 */
public class PR88AcceptSharedFile implements ITest {
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
        Proc.setGLP(" PR88 Accept shared file"); //Place global log prefix
        Proc.setLogScenarioPrefix("PR88");
        log(" PR88 Accept shared file"); //Place test description
        try {
            ITest.preinstall(Proc.preinstallRF);

            RFUser testUser = UserProc.getUser();
//            String S_email = testUser.getEmail();
//            String S_password = testUser.getPassword();

//            String R_email = SSTestBlock.emailOReceive;
//            String R_password = SSTestBlock.passwOReceive;
            Proc.setLogStepPrefix("I");
            EditorProc.runRF8Editor();
            sleepy(2);
            Proc.setLogStepPrefix("II");
            EditorProc.changeAccount(UserProc.getUserCustom(3));
            sleepy(2);
            String  newName = EditorProc.getNewName(SSTestBlock.nameShareFileA, testUser, "share");
            Proc.setLogStepPrefix("III");
            EditorProc.handleSync(newName, testUser, 2, true);
            sleepy(2);
            Proc.setLogStepPrefix("IV");
            EditorProc.searchFile(newName);
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
