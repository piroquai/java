package jnaex.RFWin.SyncWindow;

import jnaex.RFWin.ElemD;

/**
 * <p>Installer sync setup window description</p>
 */
public class InstallSetSyncD {
    /**
     * <p>Primary 'background' window - others are children</p>
     */
    public static ElemD mainW = new ElemD("RoboForm Setup","#32770");
    /**
     * <p>'Checking data' progress bar text - indicates that sync setup window exists +-</p>
     */
    public static ElemD checkingDataProgBarT = new ElemD("Checking Data","Static","1007");
    /**
     * <p>Sync setup window</p>
     */
    public static ElemD syncSetupW = new ElemD("Install RoboForm","#32770");
    /*=At that window:*/
    /*=Create new account group:*/
    /**
     * <p>'Create free account' text message - indicates that we enter new credentials (IsOffScreen)</p>
     */
    public static ElemD createFreeAccT = new ElemD("Create free account","");
    /**
     * Email field - use HasKeyboardFocus and IsOffscreen (there are several - verify on the first use)
     */
    public static ElemD emailF = new ElemD("Email:","Edit");
    /**
     * Master password field - use HasKeyboardFocus and IsOffscreen (there are several - verify on the first use)
     */
    public static ElemD masterPwdF = new ElemD("Master Password:","Edit");
    /**
     * 'Login with existing account' trigger
     */
    public static ElemD loginExistingAccT = new ElemD("Log In with Existing Account","");
    /**
     * Next button (several buttons with autId 5000-5012) - use IsOffscreen and IsEnabled
     */
    public static ElemD nextB = new ElemD("Next","Button");
    /*=Proxy handling=*/
    /**
     * <p>'Proxy Settings (Advanced)' trigger</p>
     */
    public static ElemD proxSettingsAdvancedT = new ElemD("Proxy Settings (Advanced)","");
    /**
     * <p>Use WinInet checkbox</p>
     */
    public static ElemD winInetLayerC = new ElemD("Use WinInet layer for complex proxies","Button");
    /**
     * <p>Use IE Proxy Server checkbox</p>
     */
    public static ElemD proxyIEC = new ElemD("Use Proxy Server used by Internet Explorer","Button");
    /**
     * <p>Use custom proxy checkbox</p>
     */
    public static ElemD proxyCustomC = new ElemD("Use this proxy server:","Button");


    /*=Enter credentials for a new account, continue=*/
    /**
     * 'Only you know your Master Password' text (indicator that we are ready to continue)
     */
    public static ElemD onlyYouIndicatorT = new ElemD ("Only you know your Master Password","");
    /*=EnterMP field - HasKeyboardFocus=*/
    /*=Next button - as usual=*/
    /*=After next - Your acc was created window=*/
    /**
     * "Your RoboForm account was successfully created" text message - indicator (IsOffscreen)
     */
    public static ElemD accCreatedT = new ElemD("Your RoboForm account was successfully created","");
    /*=Next button - as usual=*/
    /*=Wait a few sec after=*/
    /*=PAGE on enter existing login=*/
    /**
     * <p>Email or User ID field</p>
     * Beware: you should use ClassName here too, because there is the text with the same Name.<BR>
     * Still you should test the first time because there is a doppleganger.
     */
    public static ElemD emailOrUserIDF = new ElemD("Email or User ID:","Edit");
    /**
     * 'Create New RoboForm account' trigger
     */
    public static ElemD createNewRFAccT = new ElemD("Create New RoboForm account","");
    /*=Next button - as usual=*/
    /*=Enter pwd window=*/
    /*=Enter pwd field - only HasKeyboardFocus=*/
    /*=Next button - as usual=*/
    /*=Wait for 6 sec=*/
    /*erfuck*/
    /**
     * Enter pwd field (setup sync existing)
     */
    public static ElemD enterExPwdF = new ElemD("Enter password for RoboForm Account:","Edit");
    /**
     * Buttons to handle dialog in new instance
     */
    public static ElemD nextBNasty5 = new ElemD("Next","Button","5005");
    public static ElemD nextBNasty6 = new ElemD("Next","Button","5006");

    public static ElemD nextBNasty7 = new ElemD("Next","Button","5007");
    public static ElemD nextBNasty0 = new ElemD("Next","Button","5000");
    /**
     * Button to handle dialog in existing instance
     */
    public static ElemD nextBNasty2 = new ElemD("Next","Button","5002");
    public static ElemD nextBNasty4 = new ElemD("Next","Button","5004");
    public static ElemD nextBNasty9 = new ElemD("Next","Button","5009");

    /**
     * Button to handle dialog on setup RF8 when rf8 was already set
     */
    public static ElemD nextBNasty8 = new ElemD("Next","Button","5008");
    /**
     * Setup RF8 using RF7 creds - enter MP for Contact Info
     */
    public static ElemD editRF7DataPassword = new ElemD("Enter password that opens password-protected Login","Edit");
    /**
     * Setup RF8 using RF7 creds - I don't remember pwd checkbox
     */
    public static ElemD chBoxDontRem = new ElemD("I do not remember a password for this data","Button");
    /**
     * Setup RF8 using RF7 creds - Do not ask next time checkbox
     */
    public static ElemD chBoxDoNotAsk = new ElemD("Do not ask next time","Button");
    /**
     * Setup RF8 using RF7 creds - Next button
     */
    public static ElemD nextBtnRF7first = new ElemD("Next","Button","5010");
    /*
     * Setup RF8 using RF7 creds - Email field - emailF
     */
    /*
     * Setup RF8 using RF7 creds - Next btn - 5009 (nextBNasty9)
     */
    /*
     * Setup RF8 using RF7 creds - After conversion: Next btn - 5002 (nextBNasty2)
     */


}
