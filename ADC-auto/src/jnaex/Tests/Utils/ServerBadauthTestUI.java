package jnaex.Tests.Utils;

import daima.DElement;
import daima.KeyboardHandler;
import daima.MouseHandler;
import daima.WindowHandler;
import jnaex.RFWin.EditorProc;
import jnaex.RFWin.Proc;
import testLogger.ITest;

import java.security.Key;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Autotester on 2/6/2018.
 */
public class ServerBadauthTestUI implements ITest {

    private final String lcor = "agolov+test-s-1fa1@siber.com";
    private final String pcor = "qwerty123456";

    private final int booster = 20; //integer; 1 = normal

    private void sleepy(double s) {  //in seconds
        Proc.sleepy(s);
    }

    private void log(String s) { //normal log
        Proc.log(s);
    }
    private void log(String s, int att) {
        Proc.log("Attempt #" + (att + 1) + ": " + s);
    }

    private void logE(String s) { //error log
        Proc.logE(s);
    }

    private static final int maxAttempts = 150;
    @Override
    public void performTest(Vector<String> buf) {
        Proc.setBuf(buf);

        try {
            ITest.preinstall(Proc.preinstallRF);
            Proc.setGLP("SBATUI");
            Proc.setLogScenarioPrefix("ServBaAu");

            //initial state: RF is installed

            int res = 0;
            boolean cont = true;
            Proc.setLogStepPrefix("I");

            while (cont && res < maxAttempts)
            {
                log("I Run Editor",res);
                EditorProc.runRF8Editor();
                sleepy(2);
                log("II Log Out",res);
                EditorProc.logoff();
                sleepy(3);
                // run editor
//                log("III Run Editor",res);
//                EditorProc.runRF8Editor();
//                sleepy(2);
                // click Change account
                log("III Evoking Change account sync setup window",res);
                DElement rfEditor = DElement.gimMeP(null,"RF Editor",1,"n","RoboForm Editor");
                DElement chAcc = DElement.gimMeP(rfEditor,"Change account",2,"nloe","Change Account", "edit","f","t");
                chAcc.click();
                sleepy(1);
                DElement ssW = DElement.gimMeP(null,"RF SS", 1, "n", "RoboForm Sync Settings");

                DElement edW;
                DElement nextB;
                for (int i = 0; i < booster; i++){
                    //enter random acc cred
                    log("IV Entering incorrect credentials #" + (i + 1),res);
                    edW = DElement.gimMeP(ssW, "Email edit", 2, "N", "Email");
                    edW.click();

                    Random random = new Random();
                    int nam = random.nextInt(1000000) + 1000000;
                    KeyboardHandler.sendKeysHere(true,"^a{DEL}");
                    KeyboardHandler.sendKeysHere(Integer.toString(nam));

                    nextB = DElement.gimMeP(ssW, "Next button",2,"nl","Next","button");
                    nextB.click();
                    sleepy(1);

                    edW = DElement.gimMeP(ssW,"Password edit",2,"Nl","Enter password","edit");
                    edW.click();
                    nam = random.nextInt(1000000) + 1000000;
                    KeyboardHandler.sendKeysHere(Integer.toString(nam));

                    nextB = DElement.gimMeP(ssW, "Next button",2,"nl","Next","button");
                    nextB.click();
                    sleepy(3);

                    //enter correct acc cred instead, increase, on error - quit
                    DElement backB = DElement.gimMeP(ssW, "Back button",2,"nloe","Back", "button", "f", "t");
                    backB.click();
                    sleepy(1);
                }


                edW = DElement.gimMeP(ssW, "Email edit", 2, "N", "Email");
                edW.click();

                KeyboardHandler.sendKeysHere(true,"^a{DEL}");
                KeyboardHandler.sendKeysHere(lcor);

                nextB = DElement.gimMeP(ssW, "Next button",2,"nl","Next","button");
                nextB.click();
                sleepy(1);

                edW = DElement.gimMeP(ssW,"Password edit",2,"Nl","Enter password","edit");
                edW.click();

                KeyboardHandler.sendKeysHere(pcor);

                nextB = DElement.gimMeP(ssW, "Next button",2,"nl","Next","button");
                nextB.click();
                sleepy(8);

                log("V Checking sync result",res);
                DElement ewfW = null;
                try {
                    //check whether Everywhere feature exists or not
                    ewfW = DElement.gimMeP(null,"Everywhere Feature window",4,"no","RoboForm Everywhere Feature", "f");
                    log("Sync OK");
                    res++;
                } catch (Exception e){
                    log("Sync FAILED");
                    cont = false;
                }

                if (!cont) {
                    break;
                }

                log("VI Closing Everywhere Feature window");
                DElement tlb = DElement.gimMeP(ewfW, "Title bar",2,"l","title bar");
                DElement closeB = DElement.gimMeP(tlb,"Close button",2,"nl","Close","button");
                closeB.click();

                sleepy(1);
                log("VII Closure verification");
                DElement.gimMeP(null,"Everywhere Feature window",2,"Dno","RoboForm Everywhere Feature", "f");

                log("VIII Closing Sync setup window");
                ssW.setForeground();
                closeB = DElement.gimMeP(ssW,"OK button", 2,"nl","Ok","button");
                closeB.click();
                sleepy(2);

            }
            int resm = res * booster;
            int resh = res * booster + (booster - 1);
            log("Test result: " + resm + "-" + resh + " successful attempts of correct account before IP blocking");

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