package jnaex.RFWin.SyncWindow;

import jnaex.RFWin.ElemD;

/**
 * <p>Sync window description class</p>
 */
public class SyncD {
    /** main Sync window */
    public static ElemD syncW = new ElemD("Sync RoboForm Data Folder","#32770");/**window after manual sync*/
    public static ElemD syncWS = new ElemD("RoboForm Sync Settings","#32770");/** window after changing/creating account*/

    /** Stop button - while performing sync */
    public static ElemD stopB = new ElemD("Stop","Button");
    /** 'success' text message on sync with no new data */
    public static ElemD successTNoCh = new ElemD("There have been no changes since your last synchronization",
            "");
    public static ElemD nextBtn = new ElemD ("Next", "Button", "5004");

    public static ElemD selectD = new ElemD ("Select a different RoboForm account", "", "");
    /** unsigned 'Close' button */
    public static ElemD closeBUn = new ElemD("Close","Button", "2");/** end of syncW*/

    public static ElemD OkB = new ElemD("Ok","Button", "5013");/** end of syncWS*/


    public static ElemD acceptB = new ElemD("Accept", "Button");

    public static ElemD rejectB = new ElemD("Reject", "Button");

    public static ElemD cancelB = new ElemD("Cancel","Button");




    /** Field with checkboxes*/
    public static ElemD dataFieldW = new ElemD("", "ATL:6AC49C48", "104495248");

    public static ElemD dataFieldWS = new ElemD("", "ATL:6AC46C48", "103980392");


}
