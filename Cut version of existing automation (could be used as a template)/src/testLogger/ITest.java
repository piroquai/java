package testLogger;

import jnaex.RFWin.Proc;
import jnaex.RFWin.User.UserProc;

import java.util.Vector;

import static jnaex.RFWin.Proc.log;
import static jnaex.RFWin.Proc.logE;
import static jnaex.Run.RunnerMother.sleepy;

/**
 * Created by Autotester on 10/17/2017.
 */
public interface ITest {
    void performTest(Vector<String> buf);
    static void preinstall(boolean shouldDo) throws Exception{
        if (shouldDo){
            Proc.setLogStepPrefix("Pre-install");
            log("0.1. PRE-INSTALL: uninstall current");
            sleepy(2);
            log("0.2. PRE-INSTALL: basic install");
            sleepy(4.7);
            log("0.3. PRE-INSTALL: Setup sync existing");
            UserProc.getUser().clearData();
            sleepy(6.7);
        }
    }
    static void uninstallIfError(boolean shouldDo){
        if (shouldDo){
            Proc.setLogStepPrefix("Uninstall due to the error");
            log("Cleaning");
            try {
                logE("Cleaning was successful");
            }
            catch(Exception ex){
                logE("Cleaning was not successful: " + ex.getMessage());
            }
        }
    }
}
