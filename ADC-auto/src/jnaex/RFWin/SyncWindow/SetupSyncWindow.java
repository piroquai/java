package jnaex.RFWin.SyncWindow;

import jnaex.RFWin.ElemD;

/**
 * Created by Evgenia on 02-Jun-17.
 */
public class SetupSyncWindow {


    /**
     * I forgot MP combobox
     */
    public static ElemD cbForgotMP = new ElemD("I forgot my Master Password","Button");


    public static ElemD closeAppHeaderT = new ElemD("Closing Applications","");

    /**
     * Next button
     */
    public static ElemD nextB = new ElemD("Next","Button", "5008");
//    /**
  //   * Back button - there are many, use IsOffScreen property
    // */

   /*==if RF had been installed already=*/
    /**
     * Enter Master Password field
     */
    public static ElemD editMPField= new ElemD("Enter Your Master Password","Edit", "1048");
    /*==Page 2 - RF EW account upgrade warning=*/
    /**
     * Do not sync my RF data (by default checked combobox on upgrading through Desktop account or with sync OFF)
     */
    public static ElemD chbDNS = new ElemD("Do not sync my RoboForm data","Button");
    /**
     * Next button on DNS warning (account upgrade)
     */
    public static ElemD nextBtnDNSW = new ElemD("Next","Button","5012");
    /*==Simple create account page=*/
    /**
     * Supreme sync setup window on installation
     */
    public static ElemD suprW = new ElemD("RoboForm Setup","#32770");
    /**
     * Sync setup main window handle (RF7 to RF8 upgrade)
     */
    public static ElemD mainWUpg = new ElemD("Install RoboForm","#32770");
    /**
     * RF7 desktop to RF8: create online account - email field
     */
    public static ElemD editEmailCrFr78 = new ElemD("Email:","Edit");
    /**
     * RF7 desktop to RF8: create online account - Next button
     */
    public static ElemD nextBtnCrFr78 = new ElemD("Next","Button","5007");
    /**
     * RF7 desktop to RF8: create online account - login with existing link text
     */
    public static ElemD lnkLogExCrFr78 = new ElemD("Login with existing account","");
    /*==Confirm MP window =*/
    /**
     * RF7 Desktop to RF8: create online account - Confirm MP edit
     */
    public static ElemD editConfirmMPCrFr78 = new ElemD("Master Password:","Edit");
    /**
     * RF7 Desktop to RF8: create online account - Confirm MP edit - Back button //no use now
     */
    public static ElemD btnBackCMPCrFr78 = new ElemD("Back","Button");
    /**
     * RF7 Desktop to RF8: create online account - Confirm MP edit - Next button - enabled on filled MP
     */
    public static ElemD btnNextCMPCrFr78 = new ElemD("Next","Button","5000");
    /*==RF account was successfully created=*/
    /**
     * RF7 Desktop to RF8: create online account - Acc created - Next button
     */
    public static ElemD btnNextACCrFr78 = new ElemD("Next","Button","5002");
    //syncing - and then that's all
    /**
     * No MP - Next button on the first screen
     */
    public static ElemD btnNextNoMP78_1 = new ElemD("Next","Button","5013");
    /**
     * No MP - Next button on the second screen (IsEnabled = false until passwords are entered
     */
    public static ElemD btnNextNoMP78_2 = new ElemD("Next","Button","5003");
    /**
     * No MP - Enter a MP - first edit
     */
    public static ElemD editNoMPCreateMP1 = new ElemD("Choose a Master Password","Edit");
    /**
     * No MP - Enter a MP = second edit
     */
    public static ElemD editNoMPCreateMP2 = new ElemD("Retype Your Master Password","Edit");




}
