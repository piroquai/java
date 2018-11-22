package jnaex.Tests.Utils;

import daima.DElement;
import jnaex.RFWin.BrowserTestBlock;
import jnaex.RFWin.EditorProc;
import jnaex.RFWin.Extension.EdgeProc;
import jnaex.RFWin.Proc;
import jnaex.RFWin.User.UserProc;
import testLogger.ITest;

import java.util.Vector;


/**
 * Created by Autotester on 4/12/2018.
 */
public class EdgeTop100 implements ITest {
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

        Proc.setGLP(" Edge top-100"); //Place global log prefix
        Proc.setLogScenarioPrefix("EdgeT100");
        log("Begin test"); //Place test description
        try {
            //EdgeProc.assertSite(true,"https://online.roboform.com/site/signup-enterprise");
            Proc.setLogStepPrefix("I");
            EdgeProc.performTop100Test(BrowserTestBlock.getTestList(BrowserTestBlock.top100Common));

            Proc.setLogBlockPrefix("");
            log("Test completed");
        } catch (Exception e) {
            logE(e.getMessage());
        }
        buf = Proc.getBuf();
    }
}
