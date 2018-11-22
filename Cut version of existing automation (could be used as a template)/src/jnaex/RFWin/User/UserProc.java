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

}
