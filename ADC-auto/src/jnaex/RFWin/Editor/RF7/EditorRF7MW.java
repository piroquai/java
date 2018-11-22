package jnaex.RFWin.Editor.RF7;

import jnaex.RFWin.ElemD;

/**
 * <p>RF7 Editor main window</p>
 */
public class EditorRF7MW {
    //to start it use Identities application
    /**
     * <p>Default Editor window with opened contact info (class usage can be helpful</p>
     */
    public static ElemD defMainW = new ElemD("Contact Info - RoboForm Editor","RfEditor");
    /**
     * <p>Safenotes button</p>
     */
    public static ElemD safenoteBtn = new ElemD("Safenotes");
    //was unable to determine AddSafenote 'plus' button:
    //                .moveByOffset(65, -5)
    /**
     * <p>Title bar</p>
     */
    public static ElemD titleBar = new ElemD("","","TitleBar");
    /**
     * <p>At Title bar - Close button</p>
     */
    public static ElemD btnTBClose = new ElemD("Close");
    /**
     * <p>Grand RoboForm button</p>
     */
    public static ElemD grandRFB = new ElemD("RoboForm","");


}
