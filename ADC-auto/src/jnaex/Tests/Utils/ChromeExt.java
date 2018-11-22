package jnaex.Tests.Utils;

import daima.DElement;
import jnaex.RFWin.BrowserTestBlock;
import jnaex.RFWin.Editor.EditorD;
import jnaex.RFWin.EditorProc;
import jnaex.RFWin.Proc;
import jnaex.RFWin.SearchPresets.Chrome;
import jnaex.RFWin.User.RFUser;
import jnaex.RFWin.User.UserProc;
import jnaex.Run.RunRename;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import testLogger.ITest;

import java.io.File;
import java.util.Date;
import java.util.Vector;

/**
 * Created by  Evgenia on 02-Jul-18.
 */
public class ChromeExt implements ITest {
    public void sleepy(double s){
        Proc.sleepy(s);
    }//in seconds
    public void log(String s){ //normal log
        Proc.log(s);
    }
    public void logE(String s){ //error log
        Proc.logE(s);
    }



    private static String extPath = "C:/Users/Autotester/Desktop/";
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
        Proc.setGLP("Staff chrome test");
        log("Chrome extension elements clicking");
        try {
            /** ONLY FOR CHROME NOW*/
            ITest.preinstall(Proc.preinstallRF);
//

            //clicking on RF Extension button
            DElement popUp;
            String [] allPopUp = Chrome.popUpElements;
            ChromeOptions options = new ChromeOptions();
            options.addExtensions(new File(extPath + extName));
            WebDriver driverBrowser = new ChromeDriver(options);
            driverBrowser.navigate().to("https://mail.ru/");
            sleepy(4);
            popUp = BrowserTestBlock.clickRfButtonChrome();

            RFUser testUser = null;
            testUser = UserProc.getUser();//
            testUser.clearData();


            for (int i = 0; i < allPopUp.length; ++i) {

                log (allPopUp[i]);

                BrowserTestBlock.clickOnPopUP(popUp, allPopUp[i], testUser.getPassword());
                sleepy(5);
                //driverBrowser.quit();
                sleepy(5);
                //driverBrowser = null;
                ///options = null;
            }
            sleepy(0.5);
            driverBrowser.quit();
        }
        catch (Exception ex){
            logE("Unable to start process:" + ex.getMessage());
            ITest.uninstallIfError(Proc.preinstallRF);
        }
        buf = Proc.getBuf();
    }

}
