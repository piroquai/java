package jnaex.Tests.Prerelease.TestEditor;

import jnaex.RFWin.BrowserTestBlock;
import jnaex.RFWin.Editor.EditorD;
import jnaex.RFWin.EditorProc;
import jnaex.RFWin.Proc;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import testLogger.ITest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

/**
 * Created by  Evgenia on 03-Aug-17.
 *  Prerelease Test Plan
 *  7. Editor
 *  4. Create Bookmark
 */
public class PR74CreateBookmark implements ITest {

    public void sleepy(double s){  //in seconds
        Proc.sleepy(s);
    }
    public void log(String s){ //normal log
        Proc.log(s);
    }
    public void logE(String s){ //error log
        Proc.logE(s);
    }


    @Override
    public void performTest(Vector<String> buf){

        Proc.setBuf(buf);
        Proc.setGLP("PR74CreateBookmark");
        Proc.setLogScenarioPrefix("PR74");
        log("Editor Create Bookmark");
        try {
            ITest.preinstall(Proc.preinstallRF);
//            if (Proc.preinstallRF){
//                log("0.1. PRE-INSTALL: uninstall current");
//                CommonTestBlock.uninstallRF();
//                sleepy(2);
//                log("0.2. PRE-INSTALL: basic install");
//                CommonTestBlock.basicInstall();
//                sleepy(4.7);
//                log("0.3. PRE-INSTALL: Setup sync new");
//                CommonTestBlock.setupSyncNew();
//                sleepy(6.7);
//            }

            //Install RF FF plugin
            log("1. Install RF add-on for FF");
            Proc.setLogStepPrefix("I");
            BrowserTestBlock.installRFaddonFF();

            /**Firefox start*/

//            DesiredCapabilities cap = new DesiredCapabilities();
//            WebDriver driver1 = new RemoteWebDriver(new URL("http://localhost:9999"), cap);
            sleepy(5);
            log("2. Starting FF");
            Proc.setLogStepPrefix("II");
            ProcessBuilder pb = new ProcessBuilder(BrowserTestBlock.pathFirefox + BrowserTestBlock.nameFirefox);
            pb.start();
            sleepy(5);

            log("3. Open browser");
            Proc.setLogStepPrefix("III");
            BrowserTestBlock.openBrowser("firefox");
            sleepy(5);
            String bookmarkName = "name";
            log("4. Manual create LB");
            Proc.setLogStepPrefix("IV");
            BrowserTestBlock.manualCreateLB(bookmarkName, "tester46",true, "firefox");

            log("5. Close browser");
            Proc.setLogStepPrefix("V");
            Proc.closeFFBrowser();

            log("6. Run RF8 Editor");
            Proc.setLogStepPrefix("VI");
            EditorProc.runRF8Editor();
            sleepy(0.5);
            log("7. Check item existence");
            Proc.setLogStepPrefix("VII");
            EditorProc.checkItemExistence(EditorD.bookmarksBtn.name,bookmarkName);
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


