package jnaex.Tests.Prerelease.TestEditor;

import jnaex.RFWin.BrowserTestBlock;
import jnaex.RFWin.Editor.EditorD;
import jnaex.RFWin.EditorProc;
import jnaex.RFWin.Proc;
import jnaex.RFWin.User.UserProc;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import testLogger.ITest;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Vector;

/**
 * Created by  Evgenia on 03-Aug-17.
 *  Prerelease Test Plan
 *  7. Editor
 *  3. Create Login
 */
public class PR73CreateLoginAuto implements ITest {
    public void sleepy(double s){
            Proc.sleepy(s);
        }//in seconds
    public void log(String s){ //normal log
            Proc.log(s);
        }
    public void logE(String s){ //error log
            Proc.logE(s);
        }



    private static String extPath = "C:/Users/Evgenia/Desktop/ForTests/";
    public static void setExtPath(String s){
            extPath = s;
        }
    private static String extName = "rf-chrome.crx";
    public static void setExtName(String s){
            extName = s;
        }


    @Override
    public void performTest(Vector<String> buf){
        Proc.setBuf(buf);
        Proc.setGLP("PR73CreateLogin");
        Proc.setLogScenarioPrefix("PR73");
        log("Editor Create Login (from AutoSave)");
        try {
            /** ONLY FOR CHROME NOW*/
            ITest.preinstall(Proc.preinstallRF);
//            if (Proc.preinstallRF){
//                CommonTestBlock.uninstallRF();
//                sleepy(2);
//
//                CommonTestBlock.basicInstall();
//                sleepy(4.7);
//                CommonTestBlock.setupSyncNew();
//                sleepy(6.7);
//            }
            Proc.setLogStepPrefix("I");
            ChromeOptions options = new ChromeOptions();
            options.addExtensions(new File(extPath + extName));
            WebDriver driverBrowser = new ChromeDriver(options);

            BrowserTestBlock.openBrowser(driverBrowser, "chrome");
            //String loginName = "loginName";
            //generate loginName:
            long _date = new Date().getTime();
            long h = (_date) / (60 * 60);
            String hA = Long.toString(h);
            long m = ((_date) / 60) % 60;
            String m1 = Long.toString(m / 10);
            String m2 = Long.toString(m % 10);
            long s = (_date) % 60;
            String s1 = Long.toString(s / 10);
            String s2 = Long.toString(s % 10);
            String loginName = "loginName" + hA + "_" + m1 + m2 + "_" + s1 + s2;


            //BrowserTestBlock.autoCreateLogin(loginName, "tester46");
            Proc.setLogStepPrefix("II");
            BrowserTestBlock.autoCreateLogin(loginName, UserProc.getUser());
            Proc.setLogStepPrefix("III");
            BrowserTestBlock.closeBrowser(driverBrowser,"chrome");
            Proc.setLogStepPrefix("IV");
            EditorProc.runRF8Editor();
            sleepy(0.5);
            Proc.setLogStepPrefix("V");
            EditorProc.checkItemExistence(EditorD.loginsBtn.name,loginName);
            sleepy(0.5);
        }
        catch (Exception ex){
            Proc.setLogStepPrefix("ERROR");
            logE("Unable to start process:" + ex.getMessage());
            ITest.uninstallIfError(Proc.preinstallRF);
        }
        buf = Proc.getBuf();
    }
}




