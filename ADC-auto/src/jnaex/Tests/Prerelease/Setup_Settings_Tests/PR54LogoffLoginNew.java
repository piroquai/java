package jnaex.Tests.Prerelease.Setup_Settings_Tests;

import jnaex.RFWin.EditorProc;
import jnaex.RFWin.Proc;
import jnaex.RFWin.SetupSTestBlock;
import jnaex.RFWin.User.DataTypeStates;
import jnaex.RFWin.User.RFUser;
import jnaex.RFWin.User.Servers;
import jnaex.RFWin.User.UserProc;
import testLogger.ITest;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * Created by  Evgenia on 15-Sep-17.
 */
public class PR54LogoffLoginNew implements ITest {

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
        Proc.setGLP("5.4 Setup (settings)");
        Proc.setLogScenarioPrefix("PR54");
        log("Logoff and login to the new account");
        try{
            ITest.preinstall(Proc.preinstallRF);
//            if (Proc.preinstallRF){
//                CommonTestBlock.uninstallRF();
//                sleepy(2);
//                CommonTestBlock.basicInstall();
//                sleepy(4.7);
//                CommonTestBlock.setupSyncNew();
//                sleepy(6.7);
//            }
            log("I Run Editor");
            Proc.setLogStepPrefix("I");
            EditorProc.runRF8Editor();
            sleepy(1);
//            Date d = new Date();
//            SimpleDateFormat fd = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
//            SetupSTestBlock.pr5new3Email = CommonTestBlock.emailSSRF8Prefix + fd.format(d) + "new" + CommonTestBlock.emailSSRF8Suffix;
//            SetupSTestBlock.pr5new3Password = "qwerty";
            sleepy(2);
            RFUser testuser = UserProc.getUser(UserProc.addNewCustomUser(Servers.USSTAGING, DataTypeStates.RF8));
            log("II Create new account");
            Proc.setLogStepPrefix("II");
            EditorProc.createNewAccount(testuser);
            sleepy(3);
            log("III Close Editor");
            Proc.setLogStepPrefix("III");
            EditorProc.closeRF8EditorSoft();
            sleepy(4.7);

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
