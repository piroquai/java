package jnaex.RFWin.SearchPresets;

import jnaex.RFWin.SP;

/**
 * Created by Autotester on 4/12/2018.
 */
public class MSEdge {
    public static SP edgeWindow = new SP("MS Edge window","Nl","Microsoft Edge","window");
    public static SP edgeWindowSub = new SP("MS Edge window Sub","nl","Microsoft Edge","window");
    public static SP RFNotificationW = new SP("RoboForm notification window","nl","RoboForm","window");
    public static SP edgePopupW = new SP("Edge three-dots popup window","nl","Popup","window");
    public static SP settingsW = new SP("Settings window","nl","Settings","window");

    public static SP browserActionL = new SP("BrowserActionListView","al","BrowserActionListView","list");
    public static SP commonL = new SP("List","l","list");

    public static SP rfExtensionB = new SP("RF extension popup button","dNl","RoboForm","button");
    public static SP closeB = new SP("Close button","nlo","Close","button","false");
    public static SP OKB = new SP("OK button","nl","OK","button");
    public static SP threeDotsB = new SP("Three-dots Edge button","nl","Settings and more","button");
    public static SP chooseWTCB = new SP("Choose what to clear Button","nl","Choose what to clear","button");
    public static SP clearB = new SP("Clear button","nl","Clear","button");

    public static SP rfP = new SP("RF pane","dnl","RoboForm","pane");
    /**runs separately*/
    public static SP searchListP = new SP("Search List pane","nl","Search List","pane");
    public static SP commonP = new SP("Common pane","l","pane");

    //public static SP searchS = new SP("Search box","nl","Search","search");
    public static SP searchS = new SP("Search box","l","search box");

    public static SP contextM = new SP("Context menu","nl","Context","menu");

    public static SP logInTheSameWindowMI = new SP("Log In in Same window","nl","Log In in Same window","menu item");
    public static SP logInNewWindowMI = new SP("Log In in New window","nl","Log In in New window","menu item");
    public static SP extensionsMI = new SP("Extensions Menu item","nl","Extensions","menu item");
    public static SP settingsMI = new SP("Settings Menu item","nl","Settings","menu item");
    public static SP goToMI = new SP("Go To Menu item","nl","Go To","menu item");

    public static SP addressE = new SP("Address edit box","al","addressEditBox","edit");

    /**extensions group - contains usual site content*/
    public static SP extensionsG = new SP("Extensions group","nl","Extensions","group");
    public static SP commonG = new SP("Common group","l","group");
    public static SP settingsG = new SP("Settings group","nl","Settings","group");

    public static SP extensionsT = new SP("Extensions text", "nl","Extensions","text");
    public static SP settingsT = new SP("Settings text","nl","Settings","text");
}
