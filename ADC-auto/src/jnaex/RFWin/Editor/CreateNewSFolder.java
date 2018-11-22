package jnaex.RFWin.Editor;

import jnaex.RFWin.ElemD;

/**
 * Created by Evgenia on 05-Jul-17.
 */
public class CreateNewSFolder {

    /**
     * Main window
     */
    public static ElemD mainW = new ElemD("RoboForm - Create Shared Folder","#32770");
    /**
     * Safenote name edit
     */
    public static ElemD editN = new ElemD("Folder Name:","Edit");
    /**
     * OK button (disabled if no text)
     */
    public static ElemD btnCreate = new ElemD("Create","Button","1");
    /**
     * Cancel button
     */
    public static ElemD btnCancel = new ElemD("Cancel","Button","2");
}
