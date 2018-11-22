package jnaex.Tests.Utils;

import jnaex.RFWin.EditorProc;
import jnaex.RFWin.Proc;
import jnaex.RFWin.SSTestBlock;
import testLogger.ITest;

import java.util.Vector;

/**
 * Created by Autotester on 7/19/2018.
 */
public class CleanCurrentRFAccount implements ITest{
    private void sleepy(double s) {  //in seconds
        Proc.sleepy(s);
    }

    private void log(String s) { //normal log
        Proc.log(s);
    }

    private void logE(String s) { //error log
        Proc.logE(s);
    }

    private static final int numberOfFilesToShare = 150;
    @Override
    public void performTest(Vector<String> buf) {
        Proc.setBuf(buf);
        Proc.setLogScenarioPrefix("CleanCurrentRFAccount");

        try {
            ITest.preinstall(Proc.preinstallRF);

            Proc.setLogStepPrefix("I");
            sleepy(2);
            EditorProc.runRF8Editor();
            Proc.setLogStepPrefix("II");
            sleepy(2);
            EditorProc.removeAllItems();
            sleepy(2);
            Proc.setLogBlockPrefix("");
            log("Test was completed without fatal exceptions");
        } catch (Exception e) {
            Proc.setLogStepPrefix("ERROR");
            logE(e.getMessage());
            ITest.uninstallIfError(Proc.preinstallRF);
        }
        buf = Proc.getBuf();
    }
}
