package jnaex.RFWin.SyncWindow.RF7;

import jnaex.RFWin.ElemD;

/**
 * <p>Sync window description class (RF7)</p>
 */
public class RF7SyncD {
    /** main Sync window */
    public static ElemD syncW = new ElemD("Sync RoboForm Data Folder","#32770");
    /** Stop button - while performing sync */
    public static ElemD stopB = new ElemD("Stop","Button");
    /** 'success' text message on sync with no new data */
    public static ElemD successTNoCh = new ElemD("There have been no changes since your last synchronization",
            "");
    /** unsigned 'Close' button */
    public static ElemD closeBUn = new ElemD("Close","Button", "2");
}





