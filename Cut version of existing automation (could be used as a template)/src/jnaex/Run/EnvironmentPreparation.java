package jnaex.Run;

import java.net.MalformedURLException;
import java.util.Vector;
import jnaex.RFWin.*;
import jnaex.RFWin.User.UserProc;

public class EnvironmentPreparation {
    private static void sleepy(double s){  //in seconds
        Proc.sleepy(s);
    }
    private static void log(String s){ //normal log
        Proc.log(s);
    }
    private static void logE(String s){ //error log
        Proc.logE(s);
    }

    public static void prepare(String[] args, Vector<String> buf) throws Exception {
        Proc.setBuf(buf);
//        Proc.setGLP("Prepare environment");
        Proc.setLogScenarioPrefix("Prepare environment");
        log("Starting preparation");
        try{

            log("Environment has been prepared successfully");
        }
        catch (Exception e){
            logE(e.getMessage());
            throw e;
//            log("Cleaning");
//            try {
//                DesiredCapabilities cap = new DesiredCapabilities();
//                WebDriver driver = new RemoteWebDriver(new URL("http://localhost:9999"), cap);
//                CommonTestBlock.uninstallRF(driver);
//                logE("Cleaning was successful");
//            }
//            catch(Exception ex){
//                logE("Cleaning was not successful: " + ex.getMessage());
//            }
        }

        buf = Proc.getBuf();
    }

    public static void clean(String[] args, Vector<String> buf) throws MalformedURLException {
        Proc.setBuf(buf);
        Proc.setGLP("Clean environment");
        Proc.setLogScenarioPrefix("Clean environment");
        log("Starting cleaning");
        try{
            sleepy(4.7);

            log("Environment has been cleaned successfully");
        }
        catch (Exception e){
            logE(e.getMessage());
        }

        buf = Proc.getBuf();
    }
}
