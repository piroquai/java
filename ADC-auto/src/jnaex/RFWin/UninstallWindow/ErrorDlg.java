package jnaex.RFWin.UninstallWindow;

import jnaex.RFWin.ElemD;


/**
 * <p>Possible error dialog window if something was not deleted properly (may go after ConfirmationDlg as well as UninstallRFGD)</p>
 */
public class ErrorDlg {
    /**
     * <p>Dialog window</p>
     */
    public static ElemD mainW = new ElemD("RoboForm Uninstaller","#32770");
    /**
     * <p>Yes button</p>
     */
    public static ElemD btnOK = new ElemD("OK", "Button", "2");

}
