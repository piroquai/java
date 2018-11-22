package jnaex.Tests.Prerelease.Extension.Firefox;

import jnaex.RFWin.*;
import jnaex.RFWin.Extension.FFProc;
import jnaex.RFWin.User.UserProc;
import testLogger.ITest;

import java.util.Vector;

/**
 * Prerelease Test Plan
 * 2. Extension installation
 * 3. FF:
 * -----
 * I    Install RF8, remove all browser extensions during installation.
 * II   Go to Options > Browsers & Apps.
 * III  Check Mozilla Firefox extension.
 * IV   Check that extension is installed successfully in Mozilla Firefox.
 * E:   Correct version of the extension is installed in Mozilla Firefox. [Usually FF extension has previous release version due to the signing process]
 *
 * V    Check that extensions are missing in all other browsers except IE.
 * E.   Extension are missing for Google Chrome and Opera.
 */
public class PR23InstFF implements ITest {
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
        Proc.setGLP("PR23FF");

        Proc.setLogScenarioPrefix("PR32FF");
        log("Extension installation - FF");
        try{
            Proc.setLogStepPrefix("0.1");
            log("0.1 Uninstall Current RF");
            InstallerProc.uninstallRF();
            sleepy(4.1);
            Proc.setLogStepPrefix("0.2");
            log("0.2 Remove RF add-on from FF");
            FFProc.removeRFFFAddOnIfPresent();
            sleepy(4.2);
            Proc.setLogStepPrefix("Ia");
            log("Ia Install RF8 removing all browser extensions");
            InstallerProc.advancedInstall(false,null,null,"fco");
            sleepy(4.3);
            Proc.setLogStepPrefix("Ib");
            log("Ib Sync setup with existing user");
            TBIProc.setupSyncNew(UserProc.getUser(),true);
            sleepy(4.4);
            Proc.setLogStepPrefix("Ic");
            log("Ic FF notification should not appear:");
            if (FFProc.isFFInstallInfoDisplayed()) {
                throw new Exception("FF add-on post-install info appears");
            }
            Proc.setLogStepPrefix("Id");
            log("Id closing browser");
            Proc.closeDefaultBrowser();
            sleepy(1.5);
            Proc.setLogStepPrefix("IIa");
            log("II,III Go to Options > Browsers & Apps. Install FF ext.");
            FFProc.navigateToRFBrAppOptions();
            sleepy(1);
            Proc.setLogStepPrefix("IIb");
            FFProc.clickRFOptFFExtInstButton(true);
            sleepy(1);
            Proc.setLogStepPrefix("IIIa");
            InstallerProc.handleFFPostInstallArtifacts(Proc.gL(null, "RF Options", 1, "n", "RoboForm Options"));
            sleepy(1);
            Proc.setLogStepPrefix("IIIb");
            Proc.closeFFBrowser();
            sleepy(2);
            Proc.setLogStepPrefix("IV");
            log("IV Check that extension is installed successfully in Mozilla Firefox");
            FFProc.checkThatFFExtensionIsInstalled();
            sleepy(2);
            Proc.setLogStepPrefix("V");
            log("V Check installations in other browsers");


            EditorProc.runRF8Editor();
            sleepy(1.2);
            Proc.setLogStepPrefix("VI");
            EditorProc.logoff();
            sleepy(1.2);
            Proc.setLogStepPrefix("VII");
            EditorProc.loginPR(UserProc.getUser());
            sleepy(1.2);
            Proc.setLogStepPrefix("VIII");
            EditorProc.closeRF8EditorSoft();
            sleepy(1.2);

            if (Proc.preinstallRF){
                Proc.setLogStepPrefix("IX");
                InstallerProc.uninstallRF();
                sleepy(2.3);
            }

            Proc.setLogBlockPrefix("");
            log("Test was completed without fatal exceptions");
        }
        catch (Exception e){
            Proc.setLogStepPrefix("ERROR");
            logE(e.getMessage());
            ITest.uninstallIfError(Proc.preinstallRF);
        }

        buf = Proc.getBuf();
    }
}
