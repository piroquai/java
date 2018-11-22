package jnaex.RFWin.SSWindow;

/**
 * Created by Evgenia on 02-Jun-17.
 */

import jnaex.RFWin.ElemD;

/**also for groups in RoboForm for Business*/
public class FolderShar {

    public static ElemD mainW = new ElemD("","#32770");/**nameOfFolder - Sharing Settings*/

    public static ElemD addB = new ElemD("Add");

    public static ElemD closeB = new ElemD("Close");

    public static ElemD emailField = new ElemD("_RoboForm_Dialog_1100973_","Edit");

    //public static ElemD viewRole = new ElemD("", "Edit", "");//
    public static ElemD viewRole = new ElemD("button dropdown", "", "");


    public static ElemD paneWithRoles = new ElemD ("Read-write, show passwords", "RfAutoSavePopup3332333", "");


    /** close any active window*/
    public static ElemD close = new ElemD("Close", "", "Close");

    /** window with recipients*/
    public static ElemD recepW = new ElemD("_RoboForm_Dialog_1100973_","ATL:65299C58", "51508088");

    public static ElemD revokeB = new ElemD("Revoke");


    /**
     * Both of the above windows have the following elements
     */
    //public static ElemD pane12 = new ElemD ("Role list");

    public static ElemD pane = new ElemD ("Role list", "ATL:6AC4DC48", "72835040");

    /**
     *
     */

    public static ElemD owner = new ElemD ("Owner");
    public static ElemD regular = new ElemD ("Regular User");
    public static ElemD limited = new ElemD ("Limited User");





}
