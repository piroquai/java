package jnaex.Tests.Utils;

import daima.DElement;
import daima.KeyboardHandler;
import jnaex.RFWin.ApplicationProc;
import jnaex.RFWin.Proc;
import jnaex.RFWin.SP;
import jnaex.RFWin.SearchPresets.MozillaFF;
import jnaex.RFWin.SearchPresets.RFO;
import jnaex.RFWin.User.DataTypeStates;
import jnaex.RFWin.User.Servers;
import jnaex.RFWin.User.UserProc;
import testLogger.ITest;

import java.util.Vector;

/**
 * Created by Autotester on 2/6/2018.
 */
public class AddUsersOnRFOAdmin implements ITest {
    private static String tgtN = "LC Admin2";

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

    private static final int numberOfUsersToAdd = 1000;

    @Override
    public void performTest(Vector<String> buf) {
        Proc.setBuf(buf);
        Proc.setLogScenarioPrefix("AddURFOA");

        try {
            ITest.preinstall(Proc.preinstallRF);
            Proc.setGLP("AUORFOA");

            //initial state: RFO is opened in admin interface (FF)

            Proc.setLogStepPrefix("I");
            int res = 0;
//            DElement mozFFW = DElement.gimMeP(null,"FF Window",2,"N","Mozilla Firefox");
            DElement mozFFW = Proc.gL(null, MozillaFF.FFW,2);

            mozFFW.setForeground();
            do {
                res++;
                sleepy(0.2);
                //generate new user name and email
                int z = UserProc.addNewCustomUser(Servers.USSTAGING, DataTypeStates.RF8);
                //locating Create User button
                DElement tmp = Proc.gL(mozFFW,MozillaFF.G,2);
                tmp = ApplicationProc.calculateParent(tmp,"{}[0]{}[0]");
                tmp = Proc.gL(tmp, RFO.rfDoc,2);
                tmp = ApplicationProc.calculateParent(tmp,"{}[0]{}[4]{}[0]{}[0]{}[0]");
                tmp = Proc.gL(tmp,RFO.createUser,2);
                tmp = ApplicationProc.calculateParent(tmp,"{}[0]{}[0]");
                tmp = Proc.gL(tmp,RFO.createUser,2);
                tmp.click();
                sleepy(1);

                //locating Name field
                tmp = Proc.gL(mozFFW,MozillaFF.G,2);
                tmp = ApplicationProc.calculateParent(tmp,"{}[0]{}[0]");
                tmp = Proc.gL(tmp, RFO.rfDoc,2);
                tmp = ApplicationProc.calculateParent(tmp,"{}[0]{Create user}[0]{}[0]{}[0]{}[1]");
                DElement tmp1 = ApplicationProc.calculateParent(tmp,"{}[0]{}[1]{}[0]{}[0]");
                tmp = ApplicationProc.calculateParent(tmp1,"{}[0]{}[0]");
                tmp = Proc.gL(tmp,RFO.name,2);
                tmp.click();
                sleepy(0.4);
                KeyboardHandler.sendKeysHereSlow(UserProc.getUser(z).getName());
                sleepy(0.4);

                //locating Email field
//                tmp = ApplicationProc.calculateParent(tmp1,"{}[1]{}[0]{}[0]");
                tmp = ApplicationProc.calculateParent(tmp1,"{}[1]{}[0]");
                tmp = Proc.gL(tmp,RFO.email,2);
                tmp.click();
                sleepy(0.4);
                KeyboardHandler.sendKeysHereSlow(UserProc.getUser(z).getEmail());
                sleepy(0.4);

                //locating submit button
                tmp = Proc.gL(mozFFW,MozillaFF.G,2);
                tmp = ApplicationProc.calculateParent(tmp,"{}[0]{}[0]");
                tmp = Proc.gL(tmp, RFO.rfDoc,2);
                tmp = ApplicationProc.calculateParent(tmp,"{}[0]{Create user}[0]{}[0]{}[0]{}[2]");
                tmp = Proc.gL(tmp,RFO.OKB,2);
                tmp.click();

                sleepy(5);
                //Pressing close on dialog
                tmp = Proc.gL(mozFFW,MozillaFF.G,2);
                tmp = ApplicationProc.calculateParent(tmp,"{}[0]{}[0]");
                tmp = Proc.gL(tmp, RFO.rfDoc,2);
                tmp = ApplicationProc.calculateParent(tmp,"{}[0]{}[0]{}[0]{}[2]");
                tmp = Proc.gL(tmp,RFO.closeB,2);
                sleepy(0.4);
                tmp.click();
                sleepy(10);
                log("User number " + res + " has been added successfully");



/*                DElement tmp = DElement.gimMeP(mozFFW,"group",1,"l","group");
                tmp = DElement.gimMeP(tmp,"Smth-1",1,"n","");
                tmp = DElement.gimMeP(tmp,"Smth0",1,"n","");
                tmp = DElement.gimMeP(tmp, "doc",1,"N","RoboForm Everywhere");
                DElement tmpX = DElement.gimMeP(tmp,"Smth",1,"n","");
                try{
                    tmp = DElement.gimMePN(tmpX,"Smth-",1,3,"n","");
                    DElement xt = DElement.gimMeP(tmp,"Sm1",1,"n","");
                    xt = DElement.gimMeP(xt,"Sm2",1,"n","");
                    xt = DElement.gimMeP(xt,"Sm3",1,"n","");
                    DElement.gimMeP(xt,"Active",1,"N","Active Users");
                } catch (Exception e) {
                    tmp = DElement.gimMePN(tmpX,"Smth-",1,4,"n","");
                    DElement xt = DElement.gimMeP(tmp,"Sm1",1,"n","");
                    xt = DElement.gimMeP(xt,"Sm2",1,"n","");
                    xt = DElement.gimMeP(xt,"Sm3",1,"n","");
                    DElement.gimMeP(xt,"Active",1,"N","Active Users");
                }

                tmp = DElement.gimMeP(tmp,"Smth1",1,"n","");
                tmp = DElement.gimMeP(tmp,"Smth2",1,"n","");
                tmp = DElement.gimMeP(tmp,"Smth3",1,"n","");
                tmp = DElement.gimMeP(tmp,"SmthT",1,"n","");
                tmp = DElement.gimMeP(tmp,"LCA1",1,"dN",tgtN);
                tmp = DElement.gimMeP(tmp,"LCA2",1,"dN",tgtN);
                tmp.click();
                sleepy(0.5);
                tmp = DElement.gimMeP(tmpX,"Edit user",1,"n","Edit user");
                tmp = DElement.gimMeP(tmp,"Smth1",1,"n","");
                tmp = DElement.gimMeP(tmp,"Smth2",1,"n","");
                tmp = DElement.gimMePN(tmp,"Smth3",1,1,"n","");
                tmp = DElement.gimMeP(tmp,"Smth4",1,"n","");
                tmp = DElement.gimMeP(tmp,"Smth5",1,"n","");
                tmp = DElement.gimMeP(tmp,"Smth6",1,"n","");
                tmp = DElement.gimMeP(tmp,"Smth7",1,"n","");
                tmp = DElement.gimMePN(tmp,"Smth8",1,2,"n","");
                DElement tmpY = DElement.gimMeP(tmp,"Smth9",1,"n","");
                tmp = DElement.gimMeP(tmpY,"SmthA",1,"n","");
                tmp = DElement.gimMeP(tmp,"More settings",1,"N","More settings");
                tmp.click();
                sleepy(0.5);
                tmp = DElement.gimMePN(tmpY,"SmthB",1,1,"n","");
                tmp = DElement.gimMePN(tmp,"SmthC",1,1,"n","");
                tmp = DElement.gimMeP(tmp,"Reset MP",1,"n","Reset Master Password");
                tmp.click();
                sleepy(0.5);
//                tmp = DElement.gimMeP(tmpX,"SmthD",1,"n","");
//                DElement tmpU = DElement.gimMeP(tmp,"SmthE",1,"n","");
//                //debug - begin
//                sleepy(3);
//                tmp = DElement.gimMePN(tmpU,"SmthF1",1,0,"n","");
//                tmp.click();
//                sleepy(2);
//                tmp = DElement.gimMePN(tmpU,"SmthF1",1,1,"n","");
//                tmp.click();
//                sleepy(2);
//                //debug - end
//                tmp = DElement.gimMePN(tmpU,"SmthF",1,2,"n","");
                //
                tmp = DElement.gimMeP(mozFFW,"group",1,"l","group");
                tmp = DElement.gimMeP(tmp,"Smt1",1,"n","");
                tmp = DElement.gimMeP(tmp,"Smt2",1,"n","");
                tmp = DElement.gimMeP(tmp, "doc",1,"N","RoboForm Everywhere");
                tmp = DElement.gimMeP(tmp,"Smt3",1,"n","");
                tmp = DElement.gimMeP(tmp,"Smt4",1,"n","");
                tmp = DElement.gimMeP(tmp,"Smt5",1,"n","");
                tmp = DElement.gimMePN(tmp,"Smt6",1,2,"n","");

                //
                tmp = DElement.gimMeP(tmp,"Yes button",1,"n","Yes");
                tmp.click();
                sleepy(2.5);
                tmp = DElement.gimMeP(mozFFW,"group",1,"l","group");
                tmp = DElement.gimMeP(tmp,"Smt1",1,"n","");
                tmp = DElement.gimMeP(tmp,"Smt2",1,"n","");
                tmp = DElement.gimMeP(tmp, "doc",1,"N","RoboForm Everywhere");
                tmp = DElement.gimMeP(tmp,"Smt3",1,"n","");
                tmp = DElement.gimMeP(tmp,"SmthG",1,"n","");
                DElement tmpZ = DElement.gimMeP(tmp,"SmthH",1,"n","");
                tmp = DElement.gimMePN(tmpZ,"SmthI",1,1,"n","");
                try{
                    DElement.gimMeP(tmp,"Marker",1,"N","Reset link has been successfully");
                    cont = true;
                } catch (Exception e){
                    cont = false;
                }
                if (cont){
                    tmp = DElement.gimMePN(tmpZ,"SmthJ",1,2,"n","");
                    DElement closeBtn = DElement.gimMeP(tmp,"Close button",1,"n","Close");
                    closeBtn.click();
                    sleepy(0.3);
                }
*/            } while ((res < numberOfUsersToAdd));

 /*           if (cont && res >= maxAttempts) {
                logE("Server was not blocked at the following number of attempts: " + maxAttempts);
            } else {
                if (!cont) {
                    log("Server has blocked this IP after " + (res - 1) + " attempts.");
                }
                else
                {
                    logE("Misfortune happened. Number of attempts: " + (res - 1));
                }
            }





            //tmp = DElement.gimMeP(tmp,"Act",2,"N","Active Users 8");

*/
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