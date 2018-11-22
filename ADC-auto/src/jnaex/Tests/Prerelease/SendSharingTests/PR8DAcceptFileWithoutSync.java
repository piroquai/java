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
 * Created by  Evgenia on 01-Dec-17.
 */
public class PR8DAcceptFileWithoutSync implements ITest {
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
        Proc.setGLP(" PR8D Accept send file without sync"); //Place global log prefix
        Proc.setLogScenarioPrefix("PR8D");
        log(" PR8D Accept send file without sync"); //Place test description
        try {
            //ITest.preinstall(Proc.preinstallRF);

//            String S_email = "evtitova+tester14@siber.com";
//            String S_password = "tester14";

//            String R_email = "evtitova+tester46@siber.com";
//            String R_password = "tester46";
            log("I Run Editor");
            Proc.setLogStepPrefix("I");
            EditorProc.runRF8Editor();
            sleepy(2);
//            EditorProc.changeAccount(new RFUser(R_email, R_password));
            log("I Change account");
            Proc.setLogStepPrefix("II");
            EditorProc.changeAccount(UserProc.getUserCustom(1));
            sleepy(2);
            String  newName = EditorProc.getNewName(SSTestBlock.nameSendFile, UserProc.getUserCustom(0), "send");
            log("III Handling sync (newName = " + newName + ")");
            Proc.setLogStepPrefix("III");
            EditorProc.handleSync(newName, UserProc.getUserCustom(0), 2, true);
            sleepy(2);
            log("IV Performing search file");
            Proc.setLogStepPrefix("IV");
            EditorProc.searchFile(newName);
            sleepy(3);
            log("V Closing Editor");
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
