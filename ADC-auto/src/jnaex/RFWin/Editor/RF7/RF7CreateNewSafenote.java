package jnaex.RFWin.Editor.RF7;

import jnaex.RFWin.ElemD;

/**
 * <p>Create new safenote subwindow</p>
 * <p>Main window is EditorRF7MW</p>
 */
public class RF7CreateNewSafenote {
    /**
     * Main window
     */
    public static ElemD mainW = new ElemD("Create New Safenote","#32770");
    /**
     * Safenote name edit
     */
    public static ElemD editN = new ElemD("Name:","Edit");
    /**
     * Password-protect checkbox (always ON by default)
     */
    public static ElemD cbPP = new ElemD("Password-protect","Button");
    /**
     * OK button (disabled if no text)
     */
    public static ElemD btnOK = new ElemD("OK","Button","1");
    /**
     * Cancel button
     */
    public static ElemD btnCancel = new ElemD("Cancel","Button","2");





}
