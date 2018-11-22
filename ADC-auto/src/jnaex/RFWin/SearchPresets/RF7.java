package jnaex.RFWin.SearchPresets;

import jnaex.RFWin.SP;

/**
 * Created by Autotester on 4/4/2018.
 */
public class RF7 {
    public static SP supremeW = new SP("Supreme window","nc","RoboForm Setup","#32770");
    public static SP mainW = new SP("Main window (name is not versioned)","Nc","Install RoboForm","#32770");
    public static SP editorW = new SP("RoboForm Editor window","c","RfEditor");
    public static SP syncW = new SP("Main sync window","nc","Sync RoboForm Data Folder","#32770");
    public static SP warningRFW = new SP("Warning window - main","nc","RoboForm Warning","#32770");

    public static SP createNewSafenoteW = new SP("Create new Safenote window","nc","Create New Safenote","#32770");



    public static SP nextB = new SP("Next button","nco","Next","Button","false");
    public static SP installB = new SP("Install button","nco","Install","Button","false");
    public static SP yesB = new SP("Yes button","nco","Yes","Button","false");
    public static SP stopB = new SP("Stop button","nco","Stop","Button","false");
    public static SP backB = new SP("Back button","nco","Back","Button","false");
    public static SP cancelB = new SP("Cancel button","nco","Cancel","Button","false");
    public static SP finishB = new SP("Finish button","nco","Finish","Button","false");
    public static SP closeB = new SP("Close button","nco","Close","Button","false");
    public static SP closeTBB = new SP("Title bar - close button","n","Close");
    public static SP okB = new SP("OK button","n","OK");

    public static SP safenotesB = new SP("Safenotes button","nlo","Safenotes","button","false");
    public static SP grandRFB = new SP("Grand RoboForm button","n","RoboForm");
    public static SP turnOnB = new SP("Turn ON button","nc","Turn ON","Button");


    /**Always ON by default*/
    public static SP passwordProtectChB = new SP("Password-protect checkbox","nc","Password-protect","Button");

    public static SP radioBF = new SP("Radiobutton family","nco","","Button","false");
    public static SP syncExistingRB = new SP("Sync with existing radiobutton","nc","Sync with my existing RoboForm Account","Button");
    public static SP newAccRB = new SP("Setup new EW acc radiobutton","nc","Setup a new RoboForm Account","Button");

    public static SP MP1E = new SP("MP - first","nc","_RoboForm_Dialog_1100973_","Edit");
    public static SP MP2E = new SP("MP - second","nc","","Edit");
    /** - if sync w/existed selected or new account selected*/
    public static SP userIDE = new SP("UserID edit","nc","User ID:","Edit");
    public static SP yourNameE = new SP("Your name edit","nc","Your Name:","Edit");
    public static SP nameE = new SP("Name edit","nc","Name:","Edit");
    public static SP emailE = new SP("Email edit","nc","Email:","Edit");

    public static SP titleBar = new SP("Title bar","nca","","","TitleBar");

    public static SP errorMsg = new SP("RF Error message","n","RoboForm Error");

}
