package jnaex.RFWin.Editor;

import jnaex.RFWin.ElemD;

/**
 * <p>Main Editor window description class</p>
 */
public class EditorD {
    /** main RF window */
    public static ElemD editorW = new ElemD(" - RoboForm Editor", "RfEditor");
    /** Grand RoboForm button */
    public static ElemD grandRFB = new ElemD("RoboForm", "");
    /** Tree with the files and folders*/
    public static ElemD treeRF = new ElemD("", "ATL:6AC47338", "1327");

    /**
     * <p>Safenotes button</p>
     */
    public static ElemD safenoteBtn = new ElemD("Safenotes");
    //was unable to determine AddSafenote 'plus' button:
    //                .moveByOffset(55, 0)


    public static ElemD logoffBtn = new ElemD ("Log Out");

    public static ElemD changeAcc = new ElemD ("Change Account");


    /**
     * <p>Title bar</p>
     */
    public static ElemD titleBar = new ElemD("","","TitleBar");
    /**
     * <p>At Title bar - Close button</p>
     */
    public static ElemD btnTBClose = new ElemD("Close");

    public static ElemD createFolder = new ElemD ("Create New Folder in the Current Folder. Use folders to organize Passcards, Identities and Safenotes");

    public static ElemD createSFolder = new ElemD ("More");

    /**
     * <p>After logged off - password edit</p>
     */
    public static ElemD editLoginPwd = new ElemD("","Edit","1048");
    public static ElemD btnLogin = new ElemD("Log In","Button");
    //changeAcc - text

    public static ElemD identityBtn = new ElemD("Identities");
    //was unable to determine AddIdentity 'plus' button:
    //                .moveByOffset(55, 0)
    public static ElemD contactBtn = new ElemD("Contacts");
    //was unable to determine AddIdentity 'plus' button:
    //                .moveByOffset(55, 0)
    public static ElemD applicationsBtn = new ElemD("Applications");
    public static ElemD loginsBtn = new ElemD("Logins");
    public static ElemD bookmarksBtn = new ElemD("Bookmarks");
    public static ElemD rightPane = new ElemD("","ATL:63EC5C48","65418416");
}


