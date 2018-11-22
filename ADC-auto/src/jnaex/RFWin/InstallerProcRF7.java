package jnaex.RFWin;

import daima.DElement;
import jnaex.RFWin.SearchPresets.RF7;
import jnaex.RFWin.SearchPresets.RF7Markers;

import java.io.IOException;

/**
 * Created by Autotester on 4/4/2018.
 */
public class InstallerProcRF7 extends Proc {

    /**
     * RF7 Installer path
     */
    private static String pathRF7Installer = "C:/Users/AutoTester/Desktop/";
    public static void setPathRF7Installer(String s){
        pathRF7Installer = s;
    }
    /**
     * RF7 Installer name
     */
    private static String nameRF7Installer = "7-9-28-8-official-RoboForm-Setup.exe";
    public static void setNameRF7Installer(String s){
        nameRF7Installer = s;
    }
    /**
     * RF7 Official Installer name
     */
    private static String nameRF7InstallerOfficial = "7-9-28-8-official-RoboForm-Setup.exe";
    public static void setNameRF7InstallerOfficial(String s){
        nameRF7InstallerOfficial = s;
    }

    /**
     * Run specified RF7 Installer
     */
    private static void runRF7Installer() throws IOException {
        setLogBlockPrefix("runRF7Installer");
        ProcessBuilder pb = new ProcessBuilder(pathRF7Installer + nameRF7Installer);
        EditorProc.shell = pb.start();
    }
    /**
     * Run official RF7 Installer
     */
    private static void runRF7InstallerOfficial() throws IOException{
        setLogBlockPrefix("runRF7InstallerOfficial");
        ProcessBuilder pb = new ProcessBuilder(pathRF7Installer + nameRF7InstallerOfficial);
        EditorProc.shell = pb.start();
    }


    /**
     * <p>Override of basicInstallRF7 with default _official = false</p>
     */
    public static void basicInstallRF7() throws Exception{
        basicInstallRF7(false);
    }

    /**
     * <p>Basic installation procedure for RF7</p>
     */
    public static void basicInstallRF7(Boolean _official) throws Exception{
        lgP = "Basic Install RF7";
        int timeoutIC = 5;
        setLogBlockPrefix("basicInstallRF7");
        log("Starting procedure");
        try{
            try{
                if (_official)
                {
                    runRF7InstallerOfficial();
                    setLogBlockPrefix("basicInstallRF7-IIa");
                    log("RF7 Official Installer has run successfully");
                }
                else{
                    runRF7Installer();
                    setLogBlockPrefix("basicInstallRF7-IIb");
                    log("RF7 Installer has run successfully");
                }
            }
            catch(Exception e){
                throw new Exception("Run RF7 Installer failed:" + e.getLocalizedMessage());
            }

            log("Waiting for Installer window to appear");
            Boolean bul;
            DElement instW;
            try{
//                instW = gL(null,"Installer window",5,"NcU",InstallRF7GD.mainW.name,InstallRF7GD.mainW.cName,"6000");
                instW = gL(null, RF7.mainW,"",5,"U","6000");
            } catch (Exception e) {
                logW("Installer window did not appear, attempting restart installer...");
                try{
                    if (_official)
                    {
                        runRF7InstallerOfficial();
                        setLogBlockPrefix("basicInstallRF7-IIIa");
                        log("RF7 Official Installer has run successfully");
                    }
                    else{
                        runRF7Installer();
                        setLogBlockPrefix("basicInstallRF7-IIIb");
                        log("RF7 Installer has run successfully");
                    }
                }
                catch(Exception e1){
                    throw new Exception("Run RF7 Installer failed:" + e.getLocalizedMessage());
                }

                log("Waiting for Installer window to appear");
                //instW = gL(null,"Installer window",5,"NcU",InstallRF7GD.mainW.name,InstallRF7GD.mainW.cName,"6000");
                instW = gL(null,RF7.mainW,"",5,"U","6000");
            }
            instW.setForeground();

            log("Attaching to Next button");
            //DElement btn = gimMeP(instW,InstallRF7GD.nextB,"Next button",1,"","o");
            DElement btn = gL(instW,RF7.nextB,1);
            btn.click();
            sleepy(2);

            log("Attaching to Install button");
            //btn = gimMeP(instW,InstallRF7GD.instB1,"Install button",1,"","o");
            btn = gL(instW,RF7.installB,1);
            btn.click();
            sleepy(4);

            log("Possible Closing application window handling");
            bul = true;

            while (bul){
                try{
                    //instW = gimMe(null,InstallRF7GD.mainW,"Installer window");
                    instW = gL(null,RF7.mainW,3);
                } catch (Exception e){
                    instW = null;
                }
                if (instW == null){
                    log("Possible Closing application window disappeared");
                    bul = false;
                }
                else
                {
                    try{
                        sleepy(5);
                        log("Installer window exists, looking for Install button");
                        DElement iBtn;
                        try{
                            //iBtn = gimMeP(instW,InstallRF7GD.instB2,"Install button",1,"","o");
                            iBtn = gL(instW,RF7.installB,1);
                        }
                        catch (Exception e){
                            log("Looking for possible Sync setup window");
                            //gimMeP(instW, InstallRF7GD.mrkEW, "Sync", 8, "d", "o");
                            gL(instW, RF7Markers.mrkEW,"",8,"D");
                            //iBtn = gimMeP(instW,InstallRF7GD.instB2,"Install button",3,"","o");
                            iBtn = gL(instW,RF7.installB,3);
                        }
                        sleepy(1);
                        log("Install button is found, clicking");
                        iBtn.click();
                        try {
                            DElement chk; //redundant null initializer removed by hint :-)
                            log("Waiting for the window to close");
                            int i = 0;
                            while ((bul)&&(i < timeoutIC)){
                                try{
                                    //instW = gimMe(null,InstallRF7GD.mainW,"Installer window");
                                    instW = gL(null,RF7.mainW,3);
                                }
                                catch(Exception e){
                                    instW = null;
                                }
                                if (instW != null){
                                    log("Looking for possible Sync setup window");
                                    try {
                                        //chk = gimMeP(instW,InstallRF7GD.mrkEW,"Marker",1,"","o");
                                        chk = gL(instW,RF7Markers.mrkEW,1);
                                    }
                                    catch (Exception e){
                                        chk = null;
                                    }
                                    if (chk == null){
                                        sleepy(4);
                                        i++;
                                    }
                                    else{
                                        bul = false;
                                    }
                                }
                                else
                                {
                                    bul = false;
                                }

                            }
                        }catch(Exception e){
                            log("Seems like this installation is after RF8, am I right? :-)");
                            bul = false;
                        }
                    } catch (Exception e) {
                        log("Installer window was hidden during this staff");
                        bul = false;
                    }

                    if (bul){
                        throw new Exception("Closing application window was not closed at time");
                    }
                }
            }
            log("Procedure complete");
        }
        catch (Exception e){
            logE("Procedure failed: " + e.getMessage());
            throw e;
        }
    }

}
