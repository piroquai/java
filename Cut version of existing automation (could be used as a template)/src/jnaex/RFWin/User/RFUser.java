package jnaex.RFWin.User;

import jnaex.RFWin.Proc;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Autotester on 12/14/2017.
 */
public class RFUser {
    private static final String dummyUserID = "DummyUserID";
    private static final String dummyName = "DummyName";

    private static String initEmailPrefix = "agolov+aut";
    private static String getInitEmailPrefix(){
        return initEmailPrefix;
    }
    public static void setInitEmailPrefix(String initEmailPrefix){
        RFUser.initEmailPrefix = initEmailPrefix;
    }

    private static String initEmailCore = "";
    private static String getInitEmailCore() {
        return initEmailCore;
    }
    public static void setInitEmailCore(String initEmailCore) {
        RFUser.initEmailCore = initEmailCore;
    }

    private static String initEmailSuffix = "@siber.com";
    private static String getInitEmailSuffix() {
        return initEmailSuffix;
    }
    public static void setInitEmailSuffix(String initEmailSuffix) {
        RFUser.initEmailSuffix = initEmailSuffix;
    }

    private String emailPrefix;
    private String emailCore;
    private String emailSuffix;

    public String getEmail(){
        return this.emailPrefix + this.emailCore + this.emailSuffix;
    }

    public void setEmail(){
        this.setEmail(RFUser.getInitEmailCore(),false);
    }
    public void setEmail(String _emailCore){
        this.setEmail(_emailCore,false);
    }
    public void setEmail(String _emailCore, boolean _forced) {
        if (_forced) {
            this.emailPrefix = "";
            this.emailSuffix = "";
        } else {
            this.emailPrefix = RFUser.getInitEmailPrefix();
            this.emailSuffix = RFUser.getInitEmailSuffix();
        }
        this.emailCore = _emailCore;
    }


    private static String initPasswordPrefix = "";
    private static String getInitPasswordPrefix() {
        return initPasswordPrefix;
    }
    public static void setInitPasswordPrefix(String initPasswordPrefix) {
        RFUser.initPasswordPrefix = initPasswordPrefix;
    }

    private static String initPasswordCore = "qwerty";
    private static String getInitPasswordCore() {
        return initPasswordCore;
    }
    public static void setInitPasswordCore(String initPasswordCore) {
        RFUser.initPasswordCore = initPasswordCore;
    }

    private static String initPasswordSuffix = "";
    private static String getInitPasswordSuffix() {
        return initPasswordSuffix;
    }
    public static void setInitPasswordSuffix(String initPasswordSuffix) {
        RFUser.initPasswordSuffix = initPasswordSuffix;
    }


    private String passwordPrefix;
    private String passwordCore;
    private String passwordSuffix;

    public String getPassword(){
        return this.passwordPrefix + this.passwordCore + this.passwordSuffix;
    }

    public void setPassword(){
        this.setPassword(RFUser.getInitPasswordCore(),false);
    }
    public void setPassword(String _passwordCore){
        this.setPassword(_passwordCore,false);
    }
    public void setPassword(String _passwordCore, boolean _forced) {
        if (_forced) {
            this.passwordPrefix = "";
            this.passwordSuffix = "";
        } else {
            this.passwordPrefix = RFUser.getInitPasswordPrefix();
            this.passwordSuffix = RFUser.getInitPasswordSuffix();
        }
        this.passwordCore = _passwordCore;
    }

    private static Servers initServer = Servers.USSTAGING;
    public static Servers getInitServer() {
        return initServer;
    }
    public static void setInitServer(Servers initServer) {
        RFUser.initServer = initServer;
    }

    private Servers server;
    public void setServer(Servers server) {
        this.server = server;
    }
    public Servers getServer(){
        return server;
    }


    private static String initUserIDPrefix = "agolovaut";
    private static String getInitUserIDPrefix() {
        return initUserIDPrefix;
    }
    public static void setInitUserIDPrefix(String initUserIDPrefix) {
        RFUser.initUserIDPrefix = initUserIDPrefix;
    }

    private static String initUserIDCore = "";
    private static String getInitUserIDCore() {
        return initUserIDCore;
    }
    public static void setInitUserIDCore(String initUserIDCore) {
        RFUser.initUserIDCore = initUserIDCore;
    }

    private static String initUserIDSuffix = "";
    private static String getInitUserIDSuffix() {
        return initUserIDSuffix;
    }
    public static void setInitUserIDSuffix(String initUserIDSuffix) {
        RFUser.initUserIDSuffix = initUserIDSuffix;
    }

    private String userIDPrefix;
    private String userIDCore;
    private String userIDSuffix;

    public String getUserID(){
        return userIDPrefix + userIDCore + userIDSuffix;
    }

    public void setUserID(){
        this.setUserID(RFUser.getInitUserIDCore(),false);
    }
    public void setUserID(String _userIDCore){
        this.setUserID(_userIDCore,false);
    }
    public void setUserID(String _userIDCore, boolean _forced) {
        if (_forced) {
            this.userIDPrefix = "";
            this.userIDSuffix = "";
        } else {
            this.userIDPrefix = RFUser.getInitUserIDPrefix();
            this.userIDSuffix = RFUser.getInitUserIDSuffix();
        }
        this.userIDCore = _userIDCore;
    }

    private static String initNamePrefix = "agolovaut";
    private static String getInitNamePrefix() {
        return initNamePrefix;
    }
    public static void setInitNamePrefix(String initNamePrefix) {
        RFUser.initNamePrefix = initNamePrefix;
    }

    private static String initNameCore = "";
    private static String getInitNameCore() {
        return initNameCore;
    }
    public static void setInitNameCore(String initNameCore) {
        RFUser.initNameCore = initNameCore;
    }

    private static String initNameSuffix ="Name";
    private static String getInitNameSuffix() {
        return initNameSuffix;
    }
    public static void setInitNameSuffix(String initNameSuffix) {
        RFUser.initNameSuffix = initNameSuffix;
    }

    private String namePrefix;
    private String nameCore;
    private String nameSuffix;

    public String getName(){
        return this.namePrefix + this.nameCore + this.nameSuffix;
    }

    public void setName(){
        this.setName(RFUser.getInitNameCore(),false);
    }
    public void setName(String _nameCore){
        this.setName(_nameCore,false);
    }
    public void setName(String _nameCore, boolean _forced) {
        if (_forced) {
            this.namePrefix = "";
            this.nameSuffix = "";
        } else {
            this.namePrefix = RFUser.getInitNamePrefix();
            this.nameSuffix = RFUser.getInitNameSuffix();
        }
        this.nameCore = _nameCore;
    }

    public void clearData() throws Exception{
        UserProc.setUserDataRFO(this);
    }


    private static String initDescription = "";
    public static String getInitDescription() {
        return initDescription;
    }
    public static void setInitDescription(String initDescription) {
        RFUser.initDescription = initDescription;
    }

    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    private static DataTypeStates initDataTypeState = DataTypeStates.RF8;
    public static DataTypeStates getInitDataTypeState() {
        return initDataTypeState;
    }
    public static void setInitDataTypeState(DataTypeStates initDataTypeStates) {
        RFUser.initDataTypeState = initDataTypeStates;
    }

    private DataTypeStates dataTypeState;
    public void setDataTypeState(DataTypeStates dataTypeState) {
        this.dataTypeState = dataTypeState;
    }
    public DataTypeStates getDataTypeState(){
        return dataTypeState;
    }

    public void convertTo(DataTypeStates _dataTypeState){
        this.setDataTypeState(_dataTypeState);
    }

    public RFUser(){
        Proc.lgP = "Generate new RF user";
        Date d = new Date();
        SimpleDateFormat fd = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
        this.setEmail(fd.format(d));
        this.setPassword();
        this.setName(fd.format(d));
        this.setUserID(fd.format(d));
        this.setServer(RFUser.getInitServer());
        this.setDataTypeState(RFUser.getInitDataTypeState());
        this.setDescription(RFUser.getInitDescription() + "Created by default constructor");
        Proc.log("New user has been generated with the following credentials:");
        Proc.log("Email: " + this.getEmail());
        Proc.log("Password: " + this.getPassword());
        Proc.log("UserID: " + this.getUserID());
        Proc.log("Name: " + this.getName());
        Proc.log("Server: " + this.getServer().getUrlport());
        Proc.log("Data type: " + this.getDataTypeState().getDescription());
        Proc.log("Description: " + this.getDescription());
    }
    public RFUser(String _email, String _password){
        Proc.lgP = "Generate new RF user (email, pwd)";
        this.setEmail(_email,true);
        this.setPassword(_password,true);
        this.setName("", true);
        this.setUserID("", true);
        this.setServer(RFUser.getInitServer());
        this.setDataTypeState(RFUser.getInitDataTypeState());
        this.setDescription(RFUser.getInitDescription() + "Created using Email and Password");
        Proc.log("New user has been generated with the following credentials:");
        Proc.log("Email: " + this.getEmail());
        Proc.log("Password: " + this.getPassword());
        Proc.log("UserID: " + this.getUserID());
        Proc.log("Name: " + this.getName());
        Proc.log("Server: " + this.getServer().getUrlport());
        Proc.log("Data type: " + this.getDataTypeState().getDescription());
        Proc.log("Description: " + this.getDescription());
    }
    public RFUser(String _email, String _password, String _name, String _userID, Servers _server, DataTypeStates _dataTypeState, String _description) throws Exception{
        Proc.lgP = "Generate new RF user (all attrs)";
        if (_email.isEmpty() || _password.isEmpty()) {
            throw new Exception("Email or password is empty");
        }
        this.setEmail(_email,true);
        this.setPassword(_password,true);
        if (_name.isEmpty()){
            this.setName(dummyName, true);
        } else {
            this.setName(_name, true);
        }
        if (_userID.isEmpty()){
            this.setUserID(dummyUserID, true);
        } else {
            this.setUserID(_userID, true);
        }
        if (_server == null){
            this.setServer(RFUser.getInitServer());
        } else {
            this.setServer(_server);
        }
        if (_dataTypeState == null){
            this.setDataTypeState(RFUser.getInitDataTypeState());
        } else {
            this.setDataTypeState(_dataTypeState);
        }
        if (_description.isEmpty()){
            this.setDescription("Created using all parameters");
        } else {
            this.setDescription(_description);
        }
        Proc.log("New user has been generated with the following credentials:");
        Proc.log("Email: " + this.getEmail());
        Proc.log("Password: " + this.getPassword());
        Proc.log("UserID: " + this.getUserID());
        Proc.log("Name: " + this.getName());
        Proc.log("Server: " + this.getServer().getUrlport());
        Proc.log("Data type: " + this.getDataTypeState().getDescription());
        Proc.log("Description: " + this.getDescription());
    }
}
