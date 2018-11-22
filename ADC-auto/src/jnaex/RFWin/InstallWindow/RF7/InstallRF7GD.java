package jnaex.RFWin.InstallWindow.RF7;

import jnaex.RFWin.ElemD;

/**
 * <p>RF7 Installer greeting window description</p>
 */
public class InstallRF7GD {
    /*==PAGE ONE - Select language=*/
    /**
     * <p>Pre-main window</p>
     */
    public static ElemD supremeW = new ElemD("RoboForm Setup","#32770");
    /**
     * <p>Main window - Name field is not versioned</p>
     */
    public static ElemD mainW = new ElemD("Install RoboForm","#32770");
    /**
     * <p>Next button</p>
     */
    public static ElemD nextB = new ElemD("Next","Button","5000");
    /*==PAGE TWO - Browser Integration=*/
    /**
     * <p>Install button</p>
     */
    public static ElemD instB1 = new ElemD("Install","Button","5002");
    /*==PAGE THREE - Closing Applications=*/
    /**
     * <p>Install button</p>
     */
    public static ElemD instB2 = new ElemD("Install","Button","5005");
    /*==Possible - Initial sync setup window=*/
    //mainW - the same
    /**
     * <p>Everywhere - marker</p>
     */
    public static ElemD mrkEW = new ElemD("Everywhere","","");
    /**
     * <p>Radio buttons</p>
     */
    public static ElemD rbFam = new ElemD("","Button","");
    /**
     * <p>Next button</p>
     */
    //use already defined nextB
    /*
    Desktop section
     */
    /*==Create MP=*/
    /**
     * <p>MP - first</p>
     */
    public static ElemD editMP1 = new ElemD("_RoboForm_Dialog_1100973_","Edit");
    /**
     * <p>MP - second</p>
     */
    public static ElemD editMP2 = new ElemD("","Edit");
    /**
     * <p>Setup Desktop sync: Next button - enabled (IsEnabled = true) on successful MP enter in both fields</p>
     */
    public static ElemD nextBtnDesktop = new ElemD("Next","Button","5002");
    /*
    Everywhere section
     */
    /**
     * <p>Sync with existing radiobutton</p>
     */
    public static ElemD rbExist = new ElemD("Sync with my existing RoboForm Account","Button");
    /**
     * <p>UserID edit - if sync w/existed selected or new account selected</p>
     */
    public static ElemD editUserID = new ElemD("User ID:","Edit");
    /**
     * <p>Account password edit - if sync w/existed selected or new account (family in this case) selected</p>
     */
    public static ElemD editAccountPassword = new ElemD("","Edit");

    /**
     * <p>Setup new EW account radiobutton</p>
     */
    public static ElemD rbNew = new ElemD("Setup a new RoboForm Account","Button");
    /**
     * <p>Your name edit</p>
     */
    public static ElemD editYourName = new ElemD("Your Name:","Edit");
    /**
     * <p>Email edit</p>
     */
    public static ElemD editEmail = new ElemD("Email:","Edit");
    /**
     * <p>Next button - setup Everywhere account</p>
     */
    public static ElemD nextBtnEW = new ElemD("Next","Button","5003");
    /*==Using existing - after all was checked:=*/
    /**
     * Using existing: After setup: Possible marker - should..? text message -- does not work for 7-9-28-8
     */
    public static ElemD mrkEWEndExist = new ElemD("Should RoboForm perform these changes?","Static");
    /**
     * Using existing: After setup: Yes, perform changes button
     */
    public static ElemD yesBtnEndExist = new ElemD("Yes","Button","6");
    /*==Verify MP window for contact info=*/
    //First edit (for password) - use editAccountPassword
    /**
     * Next button - enabled on OK data (verify MP for Contact info - existing EW)
     */
    public static ElemD nextBtnEWEndExist = new ElemD("Next","Button","5006");
    /*==^^Setup is complete^^=*/
    /*==vvFor setup new accountvv:=*/
    /*==After 5003 is clicked:=*/
    /*==Create MP Desktop section=*/
    /*==Please memorize credentials section=*/
    /**
     * Next button (create new EW account - memorize credentials)
     */
    public static ElemD nextBtnEWEndNew = new ElemD("Next","Button","5001");
    /*==^^Setup is complete too :-) ^^=*/
    //if not - stop button (sync is still going):
    /**
     * Stop button - for sync
     */
    public static ElemD stopBtnSE = new ElemD("Stop","Button");
    /**
     * Back button - sync was not successful
     */
    public static ElemD backBtnSE = new ElemD("Back","Button");
    /**
     * Cancel button - sync was not successful
     */
    public static ElemD cancelBtnSE = new ElemD("Cancel","Button");
    /**
     * Next button is disabled (sync was not successful)
     */
    public static ElemD nextBtnSEd = new ElemD("Next","Button","5009");
    /**
     * Finish button - [incorrect] - "Cancel" button while sync is trying to happen.
     */
    public static ElemD finishBtnSS = new ElemD("Finish","Button");


    /**
     * <p>Title bar</p>
     */
    public static ElemD titleBar = new ElemD("","","TitleBar");
    /**
     * <p>At Title bar - Close button</p>
     */
    public static ElemD btnTBClose = new ElemD("Close");




}
