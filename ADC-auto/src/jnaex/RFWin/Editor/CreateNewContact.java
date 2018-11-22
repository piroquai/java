package jnaex.RFWin.Editor;

import jnaex.RFWin.ElemD;

public class CreateNewContact {
    /**
     * Main window
     */
    public static ElemD mainW = new ElemD("","#32770");
    /**
     * Safenote name edit
     */
    public static ElemD editN = new ElemD("Name:","Edit");
    /**
     * OK button (disabled if no text)
     */
    public static ElemD btnOK = new ElemD("OK","Button","1");
    /**
     * Cancel button
     */
    public static ElemD btnCancel = new ElemD("Cancel","Button","2");
}
