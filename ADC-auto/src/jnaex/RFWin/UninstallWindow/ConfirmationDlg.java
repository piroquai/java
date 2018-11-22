package jnaex.RFWin.UninstallWindow;

import jnaex.RFWin.ElemD;


/**
 * <p>Possible confirmation dialog if you have some data to delete (may go after UninstallRFGD)</p>
 */
public class ConfirmationDlg {
    /*==PAGE TWO - Possible confirmation window=*/
    /**
     * <p>Dialog window</p>
     */
    public static ElemD mainW = new ElemD("RoboForm Installer","#32770");
    /**
     * <p>Yes button</p>
     */
    public static ElemD btnYes = new ElemD("Yes", "Button", "6");

    /*
    are not used yet:
     */
    /**
     * No button
     */
    public static ElemD btnNo = new ElemD("No","Button","7");
    /**
     * Cancel button
     */
    public static ElemD btnCancel = new ElemD("Cancel","Button","2");


}
