package jnaex.Tests.Prerelease.StaffTests;

import jnaex.RFWin.BrowserTestBlock;
import jnaex.RFWin.Proc;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import daima.DElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by  Evgenia on 01-Aug-17.
 */
public class CreateLB {

        public static void sleepy(double s){  //in seconds
            Proc.sleepy(s);
        }
        public static void log(String s){ //normal log
            Proc.log(s);
        }
        public static void logE(String s){ //error log
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

        public static Process shell;
        public static String pathFirefox = "C:/Program Files (x86)/Mozilla Firefox/";
        public static String nameFirefox = "firefox.exe";
        public static void main(String[] args , Vector<String> buf) throws IOException, MalformedURLException {

            Proc.setBuf(buf);
            Proc.setLogScenarioPrefix("CreateLB");
            log("Create LB");
            try {
                /** ONLY FOR CHROME NOW*/
                //ChromeOptions options = new ChromeOptions();
                //options.addExtensions(new File(extPath + extName));
                //WebDriver driverBrowser = new ChromeDriver(options);

                /**Firefox start*/


                Proc.setLogStepPrefix("I");
                DesiredCapabilities cap = new DesiredCapabilities();
                WebDriver driver1 = new RemoteWebDriver(new URL("http://localhost:9999"), cap);
                sleepy(5);
                ProcessBuilder pb = new ProcessBuilder(pathFirefox + nameFirefox);
                shell = pb.start();
                sleepy(5);

                Proc.setLogStepPrefix("II");
                BrowserTestBlock.openBrowser(driver1,"firefox");
                sleepy(5);
                Proc.setLogStepPrefix("III");
                BrowserTestBlock.manualCreateLB("name", "tester46",true, "firefox");

                Proc.setLogStepPrefix("IV");
                BrowserTestBlock.closeBrowser(driver1, "firefox");

                /**FOR CHROME*/
                /**BrowserTestBlock.openBrowser(Browser);
                DesiredCapabilities cap = new DesiredCapabilities();
                WebDriver driver1 = new RemoteWebDriver(new URL("http://localhost:9999"), cap);
                String loginName = "loginName";
                BrowserTestBlock.autoCreateLogin(1, loginName, "tester46");
                BrowserTestBlock.closeBrowser(1);*/
            }
            catch (Exception ex){
                logE("Unable to start process:" + ex.getMessage());
            }
            buf = Proc.getBuf();
        }
    }


