package jnaex.Run;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jnaex.RFWin.*;
import jnaex.RFWin.Kotlin.Enums.TestEngine.TestProvider;
import jnaex.RFWin.User.DataTypeStates;
import jnaex.RFWin.User.RFUser;
import jnaex.RFWin.User.Servers;
import jnaex.RFWin.User.UserProc;
import jnaex.Tests.Prerelease.TestEditor.PR73CreateLoginAuto;
import testLogger.KotlinTestLoggerKt;
import testLogger.KotlinTestSet;
import testLogger.TestLogger;
import testLogger.TestSet;

import java.io.File;
import java.io.FileReader;
import java.util.Vector;

public class RunAllTests {
    private static final String iniFilename = "C:\\Users\\Autotester\\Desktop\\test.ini";

    private static boolean official = true; //used for logging to determine whether we should check for rf-cmd or not
    public static boolean isOfficial() {
        return official;
    }
    private static void setIsOfficial(String arg){
        if (arg.toUpperCase().equals("T") || arg.toUpperCase().equals("TRUE")){
            official = true;
        } else {
            official = false;
        }
    }

    public static void main(String[] args) throws Exception {
        setParametersJSON();
        TestLogger.startLogging();

        KotlinTestLoggerKt.startLogging();

//        UserProc.initRFUsers(1);

        UserProc.initRFUsers();


        UserProc.createRFUsersUsingDesktopRFClient();

        /* Kt - START
       */
//*
        Proc._testProvider = TestProvider.KOTLIN;
        Proc.preinstallRF = true;
        KotlinTestSet kotlinTest = new KotlinTestSet("Kotlin Option tests");
        KotlinTestLoggerKt.addTestSet(kotlinTest);
        new KotlinRunOptionsTest().main(kotlinTest);
//*/
/*
        Proc._testProvider = TestProvider.KOTLIN;
        Proc.preinstallRF = false;

        KotlinTestSet kotlinStuffTest = new KotlinTestSet("Kotlin Stuff tests");
        KotlinTestLoggerKt.addTestSet(kotlinStuffTest);
        new KotlinRunStuff().main(kotlinStuffTest);


 //*/
        /* Kt - END
//       */

//*
       Proc._testProvider = TestProvider.JAVA;
       Proc.preinstallRF = false;
       TestSet inst = new TestSet("Installer");
       TestLogger.addTestSet(inst);
       RunInstaller.main(inst, args);

       Proc.preinstallRF = false;
       TestSet instRF7 = new TestSet("Installer (RF7)");
       TestLogger.addTestSet(instRF7);
       RunInstallerRF7.main(instRF7, args);


       Proc.preinstallRF = true;
       TestSet editor = new TestSet("Editor");
       TestLogger.addTestSet(editor);
       RunEditor.main(editor, args);

       Proc.preinstallRF = false;
       TestSet setupSettings = new TestSet("SetupSettings");
       TestLogger.addTestSet(setupSettings);
       RunSetupSettings.main(setupSettings,args);
//*/
//*
        Proc.preinstallRF = false;
        TestSet sendNShare = new TestSet("Send&Share");
        TestLogger.addTestSet(sendNShare);
        RunSS.main(sendNShare,args);
//*/
//*
        Proc.preinstallRF = false;
        TestSet renameSet = new TestSet("Rename");
        TestLogger.addTestSet(renameSet);
        RunRename.main(renameSet,args);
//*/


/*
       Proc.preinstallRF = false;
       TestSet utils = new TestSet("Utils");
       TestLogger.addTestSet(utils);
        RunStaff.main(utils,args);
//*/

        TestLogger.stopLogging();
        //TestLogger.writeLogs(); //to keep locally
        TestLogger.writeLogsHTML(); //to send on server

        KotlinTestLoggerKt.stopLogging();
        KotlinTestLoggerKt.writeLogsHTML();
        System.exit(0);
    }

    private static String getValue(JsonObject _o, String _property){
        if (_o.has(_property)){
            return _o.get(_property).getAsString();
        }
        else
        {
            return null;
        }
    }
    private static Vector<String> getValueArray(JsonObject _o, String _property){
        if (_o.has(_property)){
            JsonArray ar = _o.get(_property).getAsJsonArray();
            Vector<String> tempest = new Vector<>();
            for (JsonElement el : ar){
                tempest.add(el.getAsString());
            }
            return tempest;
        }
        else
        {
            return new Vector<>();
        }
    }



    public static void setParametersJSON() throws Exception{
        File iniF = new File(iniFilename);
        try
        {
            JsonParser parser = new JsonParser();
            JsonObject o = (JsonObject) parser.parse(new FileReader(iniF));

            String s = getValue(o, "appLTCloseButtonPath");
            if (s != null) {
                ApplicationProc.setAppLTCloseButtonPath(s);
            }

            s = getValue(o,"KtPathfilenameCreateFolders");
            if (s != null) {
                KotlinVariablesKt.setPathfilenameCreateFolders(s);
            }

                s = getValue(o, "pathFileDeleteBatchFile");
            if (s != null) {
                KotlinVariablesKt.setPathFileDeleteBatchFile(s);
            }

            s = getValue(o, "nameFileDeleteBatchFile");
            if (s != null) {
                KotlinVariablesKt.setNameFileDeleteBatchFile(s);
            }

            s = getValue(o, "KtOptADDataFolderLocal");
            if (s != null) {
                KotlinVariablesKt.setOptADDataFolderLocal(s);
            }

            s = getValue(o, "KtOptADDataFolderMapped");
            if (s != null) {
                KotlinVariablesKt.setOptADDataFolderMapped(s);
            }

            s = getValue(o, "KtOptADDataFolderRemote");
            if (s != null) {
                KotlinVariablesKt.setOptADDataFolderRemote(s);
            }

            s = getValue(o, "top100Common");
            if (s != null) {
                BrowserTestBlock.setTop100Common(s);
            }

            s = getValue(o, "top100VPN");
            if (s != null) {
                BrowserTestBlock.setTop100VPN(s);
            }

            s = getValue(o, "nameUninstaller");
            if (s != null) {
                InstallerProc.setNameUninstaller(s);
            }

            s = getValue(o, "initDataTypeState");
            if (s != null) {
                if (s.equals("RF7")){
                    RFUser.setInitDataTypeState(DataTypeStates.RF7);
                } else {
                    if (s.equals("RF7CONVERTED")){
                        RFUser.setInitDataTypeState(DataTypeStates.RF7CONVERTED);
                    } else {
                        if (s.equals("RF8")){
                            RFUser.setInitDataTypeState(DataTypeStates.RF8);
                        } else {
                            throw new Exception("Unable to determine correct DataTypeState");
                        }
                    }
                }
            }

            s = getValue(o, "customAccountsCount");
            if (s != null) {
                int ccount = Integer.parseInt(s);
                for (int i = 0; i < ccount; i++){
                    JsonObject f = o.get("CUSTOM_ACCOUNT_" + i).getAsJsonObject();
                    String email;
                    String password;
                    String name;
                    String userID;
                    Servers server;
                    DataTypeStates dataTypeState;
                    String description;

                    if (f.has("Email")){
                        email = getValue(f,"Email");
                    } else {
                        throw new Exception("CUSTOM_ACCOUNT_" + i + " does not have Email");
                    }

                    if (f.has("Password")){
                        password = getValue(f,"Password");
                    } else {
                        throw new Exception("CUSTOM_ACCOUNT_" + i + " does not have Password");
                    }

                    if (f.has("Name")){
                        name = getValue(f, "Name");
                    } else {
                        name = "";
                    }

                    if (f.has("UserID")){
                        userID = getValue(f, "UserID");
                    } else {
                        userID = "";
                    }

                    if (f.has("Server")){
                        String t = getValue(f, "Server");
                        if (t.equals("USSTAGING")){
                            server = Servers.USSTAGING;
                        } else {
                            if (t.equals("COMOFFICIAL")){
                                server = Servers.COMOFFICIAL;
                            } else
                            {
                                server = Servers.CUSTOM;
                            }
                        }
                    } else {
                        server = null;
                    }

                    if (f.has("DataTypeState")) {
                        String t = getValue(f, "DataTypeState");
                        if (t.equals("RF7")){
                            dataTypeState = DataTypeStates.RF7;
                        } else {
                            if (t.equals("RF7CONVERTED")){
                                dataTypeState = DataTypeStates.RF7CONVERTED;
                            } else {
                                if (t.equals("RF8")){
                                    dataTypeState = DataTypeStates.RF8;
                                } else {
                                    throw new Exception("Unable to determine correct DataTypeState");
                                }
                            }
                        }
                    } else {
                        dataTypeState = null;
                    }

                    if (f.has("Description")) {
                        description = getValue(f, "Description");
                    } else {
                        description = "";
                    }

                    UserProc.initRFUserCustom(new RFUser(email, password, name, userID, server, dataTypeState, description));
                }
            }

            s = getValue(o,"initEmailPrefix");
            if (s != null) {
                RFUser.setInitEmailPrefix(s);
            }
            s = getValue(o,"initEmailCore");
            if (s != null) {
                RFUser.setInitEmailCore(s);
            }
            s = getValue(o,"initEmailSuffix");
            if (s != null) {
                RFUser.setInitEmailSuffix(s);
            }
            s = getValue(o,"initPasswordPrefix");
            if (s != null) {
                RFUser.setInitPasswordPrefix(s);
            }
            s = getValue(o,"initPasswordCore");
            if (s != null) {
                RFUser.setInitPasswordCore(s);
            }
            s = getValue(o,"initPasswordSuffix");
            if (s != null) {
                RFUser.setInitPasswordSuffix(s);
            }
            s = getValue(o,"initServer");
            if (s != null) {
                if (s.equals("USSTAGING")){
                    RFUser.setInitServer(Servers.USSTAGING);
                } else {
                    if (s.equals("COMOFFICIAL")){
                        RFUser.setInitServer(Servers.COMOFFICIAL);
                    } else
                    {
                        RFUser.setInitServer(Servers.CUSTOM);
                    }
                }
            }
            s = getValue(o,"initUserIDPrefix");
            if (s != null) {
                RFUser.setInitUserIDPrefix(s);
            }
            s = getValue(o,"initUserIDCore");
            if (s != null) {
                RFUser.setInitUserIDCore(s);
            }
            s = getValue(o,"initUserIDSuffix");
            if (s != null) {
                RFUser.setInitUserIDSuffix(s);
            }
            s = getValue(o,"initNamePrefix");
            if (s != null) {
                RFUser.setInitNamePrefix(s);
            }
            s = getValue(o,"initNameCore");
            if (s != null) {
                RFUser.setInitNameCore(s);
            }
            s = getValue(o,"initNameSuffix");
            if (s != null) {
                RFUser.setInitNameSuffix(s);
            }

            s = getValue(o, "initRFUsersCount");
            if (s != null) {
                UserProc.setInitRFUsersCount(Integer.parseInt(s));
            }
            s = getValue(o, "tempFolderUDRFO");
            if (s != null) {
                UserProc.setTempFolderUDRFO(s);
            }

            s = getValue(o,"pathFirefox");
            if (s != null) {
                BrowserTestBlock.setPathFirefox(s);
            }
            s = getValue(o,"nameFirefox");
            if (s != null) {
                BrowserTestBlock.setNameFirefox(s);
            }
            s = getValue(o,"pathChrome");
            if (s != null) {
                BrowserTestBlock.setPathChrome(s);
            }
            s = getValue(o,"nameChrome");
            if (s != null) {
                BrowserTestBlock.setNameChrome(s);
            }

            s = getValue(o, "isOfficial");
            if (s != null) {
                setIsOfficial(s);
            }

            s = getValue(o, "pathRFCmd");
            if (s != null) {
                TestLogger.setPathRFCmd(s);
            }

            s = getValue(o, "nameRFCmd");
            if (s != null) {
                TestLogger.setNameRFCmd(s);
            }

            s = getValue(o, "reportEmailFromName");
            if (s != null) {
                TestLogger.setReportEmailFromName(s);
                KotlinTestLoggerKt.setReportEmailFromName(s);
            }

            s = getValue(o, "reportEmailFromPassword");
            if (s != null) {
                TestLogger.setReportEmailFromPassword(s);
                KotlinTestLoggerKt.setReportEmailFromPassword(s);
            }

            s = getValue(o, "reportEmailSMTPServer");
            if (s != null) {
                TestLogger.setReportEmailSMTPServer(s);
                KotlinTestLoggerKt.setReportEmailSMTPServer(s);
            }

            s = getValue(o, "reportEmailSMTPPort");
            if (s != null) {
                TestLogger.setReportEmailSMTPPort(s);
                KotlinTestLoggerKt.setReportEmailSMTPPort(s);
            }

            Vector<String> sa = getValueArray(o, "reportEmailList");
            if (!(sa.isEmpty())) {
                TestLogger.setEmailList(sa);
                KotlinTestLoggerKt.setEmailList(sa);
            }

            s = getValue(o,"pathRF7Installer");
            if (s != null) {
                InstallerProcRF7.setPathRF7Installer(s);
            }

            s = getValue(o,"pathRF8Installer");
            if (s != null) {
                InstallerProc.setPathRF8Installer(s);
            }

            s = getValue(o,"pathRFOTSInjector");
            if (s != null) {
                InstallerProc.setPathRFOTSInjector(s);
            }
            s = getValue(o,"pathUninstaller");
            if (s != null) {
                InstallerProc.setPathUninstaller(s);
            }
            s = getValue(o,"pathRF7Editor");
            if (s != null) {
                EditorProc.setPathRF7Editor(s);
            }
            s = getValue(o,"pathRF8Editor");
            if (s != null) {
                EditorProc.setPathRF8Editor(s);
            }
//            s = getValue(o,"userIDSSRF7Prefix");
//            if (s != null) {
//                Proc.setUserIDSSRF7Prefix(s);
//            }
//            s = getValue(o,"nameSSRF7Prefix");
//            if (s != null) {
//                Proc.setNameSSRF7Prefix(s);
//            }
//            s = getValue(o,"emailSSRF7Prefix");
//            if (s != null) {
//                Proc.setEmailSSRF7Prefix(s);
//            }
//            s = getValue(o,"existingUserRF7UserID");
//            if (s != null) {
//                Proc.setExistingUserRF7UserID(s);
//            }
//            s = getValue(o,"existingUserRF7Password");
//            if (s != null) {
//                Proc.setExistingUserRF7Password(s);
//            }
//            s = getValue(o,"emailSSRF8Prefix");
//            if (s != null) {
//                Proc.setEmailSSRF8Prefix(s);
//            }
//            s = getValue(o,"existingUserRF8Email");
//            if (s != null) {
//                Proc.setExistingUserRF8Email(s);
//            }
//            s = getValue(o,"existingUserRF8Password");
//            if (s != null) {
//                Proc.setExistingUserRF8Password(s);
//            }
//            s = getValue(o,"emailExisting");
//            if (s != null) {
//                Proc.setEmailExisting(s);
//            }
//            s = getValue(o,"passwordExisting");
//            if (s != null) {
//                Proc.setPasswordExisting(s);
//            }
            s = getValue(o,"targetVersionStr");
            if (s != null) {
                Proc.setTargetVersionStr(s);
            }
            s = getValue(o,"targetVersionStrOfficial");
            if (s != null) {
                Proc.setTargetVersionStrOfficial(s);
            }
            s = getValue(o,"nameRF7Installer");
            if (s != null) {
                InstallerProcRF7.setNameRF7Installer(s);
            }
            s = getValue(o,"nameRF7InstallerOfficial");
            if (s != null) {
                InstallerProcRF7.setNameRF7InstallerOfficial(s);
            }
            s = getValue(o,"nameRF8Installer");
            if (s != null) {
                InstallerProc.setNameRF8Installer(s);
            }
            s = getValue(o,"nameRF8InstallerOfficial");
            if (s != null) {
                InstallerProc.setNameRF8InstallerOfficial(s);
            }
            s = getValue(o,"extPath");
            if (s != null) {
                PR73CreateLoginAuto.setExtPath(s);
            }
            s = getValue(o,"extName");
            if (s != null) {
                PR73CreateLoginAuto.setExtName(s);
            }
            s = getValue(o,"appLTPath");
            if (s != null) {
                ApplicationProc.setAppLTPath(s);
            }
            s = getValue(o,"appLTExec");
            if (s != null) {
                ApplicationProc.setAppLTExec(s);
            }
            s = getValue(o,"appLTExecWinName");
            if (s != null) {
                ApplicationProc.setAppLTExecWinName(s);
            }
            s = getValue(o,"appLTExecWinCName");
            if (s != null) {
                ApplicationProc.setAppLTExecWinCName(s);
            }
            s = getValue(o,"appLTBadMarkerPath");
            if (s != null) {
                ApplicationProc.setAppLTBadMarkerPath(s);
            }
            s = getValue(o,"appLTBadMarkerName");
            if (s != null) {
                ApplicationProc.setAppLTBadMarkerName(s);
            }
            s = getValue(o,"appLTBadHandlePath");
            if (s != null) {
                ApplicationProc.setAppLTBadHandlePath(s);
            }
            s = getValue(o,"appLTBadHandleName");
            if (s != null) {
                ApplicationProc.setAppLTBadHandleName(s);
            }
            s = getValue(o,"appLTGoodMarkerPath");
            if (s != null) {
                ApplicationProc.setAppLTGoodMarkerPath(s);
            }
            s = getValue(o,"appLTGoodMarkerName");
            if (s != null) {
                ApplicationProc.setAppLTGoodMarkerName(s);
            }
            s = getValue(o,"appLTLoginFieldPath");
            if (s != null) {
                ApplicationProc.setAppLTLoginFieldPath(s);
            }
            s = getValue(o,"appLTLoginFieldName");
            if (s != null) {
                ApplicationProc.setAppLTLoginFieldName(s);
            }
            s = getValue(o,"appLTPasswordFieldPath");
            if (s != null) {
                ApplicationProc.setAppLTPasswordFieldPath(s);
            }
            s = getValue(o,"appLTPasswordFieldName");
            if (s != null) {
                ApplicationProc.setAppLTPasswordFieldName(s);
            }
            s = getValue(o,"appLTSubmitPath");
            if (s != null) {
                ApplicationProc.setAppLTSubmitPath(s);
            }
            s = getValue(o,"appLTSubmitName");
            if (s != null) {
                ApplicationProc.setAppLTSubmitName(s);
            }
            s = getValue(o,"appLTBadAdditionalHandlePath");
            if (s != null) {
                ApplicationProc.setAppLTBadAdditionalHandlePath(s);
            }
            s = getValue(o,"appLTBadAdditionalHandleName");
            if (s != null) {
                ApplicationProc.setAppLTBadAdditionalHandleName(s);
            }
            s = getValue(o,"itemTemplatesPath");
            if (s != null) {
                Proc.setItemTemplatesPath(s);
            }

        } catch (Exception e) {
            Proc.logW("JSON parsing exception: " + e.getMessage());
        }
    }

}
