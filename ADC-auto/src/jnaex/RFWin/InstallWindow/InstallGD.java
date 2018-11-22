package jnaex.RFWin.InstallWindow;

import jnaex.RFWin.ElemD;

/**
 * <p>Installer greeting window description</p>
 */
public class InstallGD {
    /*==PAGE ONE - Select language=*/
    /**
     * <p>Main window - Name field is versioned:</p>
     * USAGE: MainW.name + [version formatted like 8-3-3-3] {Space is already included}
     */
    public static ElemD mainW = new ElemD("Install RoboForm ","#32770");
    /**
     * <p>'Show Advanced Options' checkbox</p>
     */
    public static ElemD advancedComboB = new ElemD("Show Advanced Options","Button");
    /**
     * <p>Install button - there are many of them with AutomationId from 5000 to 5005</p>
     * I leave autId field blank, suggest search by IsOffscreen or HasKeyboardFocus
     */
    public static ElemD installBUn = new ElemD("Install","Button");
    /**
     * <p>Install button 5005 - could be helpful</p>
     */
    public static ElemD installB5 = new ElemD("Install","Button","5005");
    /**
     * <p>Select your language text string</p>
     */
    public static ElemD selectLangT = new ElemD("Select your language:","");
    /**
     * <p>Language selector combobox</p>
     */
    public static ElemD langSelectorComboB = new ElemD("Select your language:","ComboBox");
    /*==PAGE TWO - Closing applications=*/
    /**
     * <p>'Closing Application' heading text</p>
     */
    public static ElemD closeAppHeaderT = new ElemD("Closing Applications","");
    /**
     * Back button - there are many, use IsOffScreen property
     */
    public static ElemD backB = new ElemD("Back","Button");
    /*TODO: Button and additional controls for advanced options*/
    /**
     * Change button (designed for change program data path
     */
    public static ElemD changeB = new ElemD("Change","Button");
    /**
     * Next button with 5002 (moving forward after unchecking browsers window
     */
    public static ElemD nextBNasty2 = new ElemD("Next","Button","5002");
    /**
     * Warning dialog (appears when change button is clicked
     */
    public static ElemD wrnDlg = new ElemD("RoboForm " + "Warning","#32770");
    /**
     * Browse For Folder dialog (appears after first warning confirmation per installation)
     */
    public static ElemD brwsDlg = new ElemD("Browse For Folder","#32770");
    /**
     * Folder Edit in Browse For Folder dialog
     */
    public static ElemD folderE = new ElemD("Folder:","Edit");
    /**
     * OK button in Browse For Folder dialog
     */
    public static ElemD btnOK1 = new ElemD("OK","Button","1");
    /**
     * Install button to continue from specifying locationw window
     */
    public static ElemD btnInstall4 = new ElemD("Install","Button","5004");


    public static ElemD nextB = new ElemD("Next","Button", "5000");
    /**
     * Next button - if "Show advanced options" is ON
     */
}
