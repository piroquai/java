package jnaex.RFWin.User;

import jnaex.RFWin.*;
import testLogger.TestLogger;

import java.nio.file.*;
import java.util.Vector;

import static jnaex.RFWin.Proc.sleepy;

/**
 * Created by Autotester on 12/14/2017.
 */
public class UserProc {
    private static void log(String s){
        String logString = logPrefix + Proc.logSeparator;
        if (!(logPrefixLocal.isEmpty())) {
            logString += logPrefixLocal + Proc.logSeparator;
        }
        logString += s;
        Proc.log(logString);
    }
    private static String logPrefix = "UserProc";
    private static String logPrefixLocal = "";

    private static String tempFolderUDRFO = "C:/Users/Autotester/Desktop/UserDataTemp/";
    public static String getTempFolderUDRFO() {
        return tempFolderUDRFO;
    }
    public static void setTempFolderUDRFO(String tempFolderUDRFO) {
        UserProc.tempFolderUDRFO = tempFolderUDRFO;
    }

    private static int initRFUsersCount = 1;
    public static int getInitRFUsersCount() {
        return initRFUsersCount;
    }
    public static void setInitRFUsersCount(int initRFUsersCount) {
        UserProc.initRFUsersCount = initRFUsersCount;
    }

    private static Vector<RFUser> rfUsers;
    private static Vector<RFUser> rfUsersCustom = new Vector<>();


    public static void initRFUsers(int count){
        rfUsers = new Vector<>();
        for (int i = 0; i < count; i++){
            rfUsers.add(new RFUser());
            sleepy(2);
        }
    }
    public static void initRFUserCustom(RFUser _rfUser){
        rfUsersCustom.add(_rfUser);
    }
    public static void initRFUsers(){
        initRFUsers(initRFUsersCount);
    }
    public static int addNewCustomUser(Servers _server, DataTypeStates _dataTypeState) throws Exception{
        sleepy(2);
        Servers curS = RFUser.getInitServer();
        DataTypeStates curDTS = RFUser.getInitDataTypeState();

        RFUser.setInitServer(_server);
        RFUser.setInitDataTypeState(_dataTypeState);

        rfUsers.add(new RFUser());

        RFUser.setInitServer(curS);
        RFUser.setInitDataTypeState(curDTS);

        return rfUsers.size() - 1;
    }

    /**
     * Gets user from predefined in test.ini array
     * @param _index Index of the user is rfUsersCustom (started from 0)
     * @return RFUser to act with. These users should be set up already.
     * @throws Exception if something goes wrong
     */
    public static RFUser getUserCustom(int _index) throws Exception{
        if (_index > rfUsersCustom.size() - 1) {
            throw new Exception("There is no user with such index: " + _index + ". There are just " + rfUsersCustom.size() + " users.");
        }
        return rfUsersCustom.get(_index);
    }

    public static RFUser getUser(int _index) throws Exception{
        if (_index > rfUsers.size() - 1) {
            throw new Exception("There is no user with such index: " + _index + ". There are just " + rfUsers.size() + " users.");
        }
        return rfUsers.get(_index);
    }

    public static RFUser safeGetUser(int count) throws Exception{
        if (rfUsers.size() == 0) {
            throw new Exception("RFUsers are not initialized. Please perform initRFUsers and create those users first.");
        }
        if (count > rfUsers.size() - 1){
            Proc.logW("SAFEGET: There is no such user: " + count + ". Returning the first user.");
            count = 0;
        }
        return rfUsers.get(count);
    }

    public static RFUser getUser() throws Exception{
        if (rfUsers.size() == 0) {
            throw new Exception("RFUsers are not initialized. Please perform initRFUsers and create those users first.");
        }
        return rfUsers.get(0);
    }

    private static void clearTempDirectory() throws Exception{
        String d = UserProc.getTempFolderUDRFO();
        Path dir = FileSystems.getDefault().getPath(d);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)){
            for (Path file: stream){
                Proc.log("Clearing: " + file.getFileName());
                try {
                    Files.delete(file);
                } catch (DirectoryNotEmptyException dex) {
                    try (DirectoryStream<Path> st = Files.newDirectoryStream(file)){
                        for (Path nfile: st) {
                            Files.delete(nfile);
                        }
                        Files.delete(file);
                    } catch (Exception e) {
                        Proc.logW("Clearing failed: " + e.getMessage());
                    }

                }


            }
        }
    }

    private static void createTempUDSubDirectory(RFUser _user) throws Exception{
        String d = UserProc.getTempFolderUDRFO();
        Path sdir = FileSystems.getDefault().getPath(d + _user.getName());
        Files.createDirectory(sdir);
    }
    private static void checkTempDir() throws Exception{
        String d = UserProc.getTempFolderUDRFO();
        Path dir = FileSystems.getDefault().getPath(d);
        try{
            DirectoryStream<Path> stream = Files.newDirectoryStream(dir);
            stream.close();
        } catch (NoSuchFileException e1){
            log("Directory does not exist, creating");
            Files.createDirectory(dir);
        }
    }

    private static void getUserDataRFO(RFUser _user) throws Exception{
        log("Get user data.rfo file");
        String pathRFC = TestLogger.getPathRFCmd();
        String nameRFC = TestLogger.getNameRFCmd();
        String addParam = "--download-onefile https://";
        switch (_user.getServer()){
            case USSTAGING:
                addParam += "www.";
        }
        addParam += _user.getServer().getUrlport() + " ";
        addParam += _user.getEmail() + " ";
        addParam += _user.getPassword() + " ";
        addParam += UserProc.getTempFolderUDRFO() + _user.getName() + "/";

        log("Starting RF-cmd with the following command:");
        log(pathRFC + nameRFC + " " + addParam);

        Process p;
        try {
            p = Runtime.getRuntime().exec(pathRFC + nameRFC + " " + addParam);
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Incorrect execution");
        }
        log("Procedure complete");
    }
    static void setUserDataRFO(RFUser _user) throws Exception{
        log("Set user data.rfo file");
        String pathRFC = TestLogger.getPathRFCmd();
        String nameRFC = TestLogger.getNameRFCmd();
        String addParam = "--upload-onefile user https://";
        switch (_user.getServer()){
            case USSTAGING:
                addParam += "www.";
        }
        addParam += _user.getServer().getUrlport() + " ";
        addParam += _user.getEmail() + " ";
        addParam += _user.getPassword() + " ";
        addParam += UserProc.getTempFolderUDRFO() + _user.getName() + "/";

        log("Starting RF-cmd with the following command:");
        log(pathRFC + nameRFC + " " + addParam);

        Process p;
        try {
            p = Runtime.getRuntime().exec(pathRFC + nameRFC + " " + addParam);
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Incorrect execution");
        }
        log("Procedure complete");
    }

    public static void clearUser(int count) throws Exception{
        if (count > rfUsers.size() - 1) {
            throw new Exception("There is no such user: " + count + ". There are just " + rfUsers.size() + " users.");
        }
        setUserDataRFO(rfUsers.get(count));
    }

    public static void safeClearUser(int count) throws Exception{
        if (rfUsers.size() == 0) {
            throw new Exception("RFUsers are not initialized. Please perform initRFUsers and create those users first.");
        }
        if (count > rfUsers.size() - 1){
            Proc.logW("SAFEGET: There is no such user: " + count + ". Returning the first user.");
            count = 0;
        }
        setUserDataRFO(rfUsers.get(count));
    }

    public static void clearUser() throws Exception{
        if (rfUsers.size() == 0) {
            throw new Exception("RFUsers are not initialized. Please perform initRFUsers and create those users first.");
        }
        setUserDataRFO(rfUsers.get(0));
    }

    public static void createSpecificRFUserUsingDesktopRFClient(RFUser _user) throws Exception{
        logPrefixLocal = "Create specific user";
        log("Starting procedure with following parameters:");
        log("User server: " + _user.getServer().getUrlport());
        log("User data type: " + _user.getDataTypeState().getDescription());

        //TODO: handle server type - COM and CUSTOM
        switch (_user.getServer()){
            case USSTAGING:
                InstallerProc.injectRFOTestServer();
        }

        switch (_user.getDataTypeState()){
            case RF8:
            {
                sleepy(3);
//                log("SyncSetup new account");
//                TBIProc.setupSyncNew(_user,false);
                EditorProc.runRF8Editor();
                sleepy(2);
                EditorProc.createNewAccount(_user);
                sleepy(3);
                EditorProc.closeRF8EditorSoft();
                sleepy(3);
                getUserDataRFO(_user);
                break;
            }
            case RF7:
            {
                RFUser tmpU = _user;
                InstallerProcRF7.basicInstallRF7();
                sleepy(3);
                log("SyncSetup new account");
                TBIProcRF7.syncSetupRF7EWCreateNew(_user);
                sleepy(3);
                getUserDataRFO(_user);
                log("Clearing");
                InstallerProc.uninstallRF();
                sleepy(3);
                break;
            }
        }
    }
    public static void createRFUsersUsingDesktopRFClient() throws Exception{
        Proc.setGLP("Create RF Users");
        if (rfUsers.size() == 0){
            Proc.logW("RFUsers were not initialized. Initializing using default value: " + getInitRFUsersCount());
            initRFUsers(getInitRFUsersCount());
        }
        logPrefixLocal = "Create using RoboForm Desktop client";

        log("Starting procedure");
        log("Checking temporary directory");
        checkTempDir();
        log("Clearing temporary directory");
        clearTempDirectory();
        log("Creating temporary sub-directories for user data files");
        for (int i = 0; i < rfUsers.size(); i++){
            createTempUDSubDirectory(rfUsers.get(i));
        }

        log("Uninstalling RF");
        InstallerProc.uninstallRF();

        log("Initializing RF8 users - .US, if there are any");
        int ch = 0;
        for (RFUser tmp : rfUsers){
            if ((tmp.getServer() == Servers.USSTAGING)&&(tmp.getDataTypeState() == DataTypeStates.RF8)){
                ch++;
            }
        }

        int i = 0;
        if (ch > 0){
            InstallerProc.injectRFOTestServer();

            InstallerProc.basicInstall();

            for (RFUser tmp : rfUsers){
                if ((tmp.getServer() == Servers.USSTAGING)&&(tmp.getDataTypeState() == DataTypeStates.RF8)) {
                    i++;
                    log("Creating RF8 .US user number #" + i);
                    if (i == 1) {
                        sleepy(3);
                        log("SyncSetup new account");
                        TBIProc.setupSyncNew(tmp,false);
                        sleepy(3);
                        getUserDataRFO(tmp);
                    }
                    else {
                        createSpecificRFUserUsingDesktopRFClient(tmp);
                    }
                }
            }

            log("Clearing");
            InstallerProc.uninstallRF();
            sleepy(3);

        } else {
            log("There are no RF8 .US users to initialize");
        }

        log("Initializing RF7 users - .US, if there are any");

        ch = 0;
        for (RFUser tmp : rfUsers){
            if ((tmp.getServer() == Servers.USSTAGING)&&(tmp.getDataTypeState() == DataTypeStates.RF7)){
                ch++;
            }
        }

        i = 0;

        if (ch > 0){
            InstallerProc.injectRFOTestServer();

            for (RFUser tmp : rfUsers){
                if ((tmp.getServer() == Servers.USSTAGING)&&(tmp.getDataTypeState() == DataTypeStates.RF7)) {
                    i++;
                    log("Creating RF7 .US user number #" + i);
                    createSpecificRFUserUsingDesktopRFClient(tmp);
                }
            }

        } else {
            log("There are no RF7 .US users to initialize");
        }



        log("Procedure complete");
//        safeClearUser(1);
//        log("Procedure complete");


    }


    public static RFUser getRF7User() throws Exception{
        boolean found = false;
        int cnt = 0;
        for (int i = 0; i < rfUsers.size(); i++){
            if (rfUsers.get(i).getDataTypeState() == DataTypeStates.RF7){
                found = true;
                cnt = i;
                break;
            }
        }
        if (found) {
            return rfUsers.get(cnt);
        }
        else
        {
            throw new Exception("There are no active RF7 users");
        }
    }

}
