package jnaex.RFWin.SearchPresets;

import jnaex.RFWin.SP;

/**
 * Created by Autotester on 4/12/2018.
 */
public class MozillaFF {
    public static SP FFW = new SP("Mozilla Firefox window","c","MozillaWindowClass");
    public static SP FFQuestion = new SP ("", "nc","Confirm close", "MozillaDialogClass");
    public static SP managerW = new SP("Manager window","n","Add-ons Manager");
    public static SP FFWLarge = new SP("FF","NC","Mozilla Firefox","MozillaWindowClass");

    public static SP FFMenu = new SP("FF Menu","n","Navigation Toolbar");

    public static SP addOns = new SP("Add-ons","n","Add-ons");
    public static SP getAddOns = new SP("Get Add-ons","n","Get Add-ons");
    public static SP discoverAddOns = new SP("Discover Add-ons","n","Discover Add-ons");
    public static SP seeMoreAddOns = new SP("Find more add-ons","n","Find more add-ons");
    public static SP addOnsForFF = new SP("Add-ons for FF","dn","Add-ons for Firefox");

    public static SP goToAddressLoc = new SP("Go to address smth","n","Go to the address in the Location Bar");

    public static SP RFPassManRF = new SP("NavX1n","dN","RoboForm Password Manager RoboForm");

    public static SP FFB = new SP("FF button","n","Firefox");
    public static SP addB = new SP("Add button","n","Add");
    public static SP exitB = new SP("Exit button","n","Exit");

    public static SP G = new SP("Group","l","group");

    public static SP L = new SP("List","l","list");

    public static SP emptyNav = new SP("Empty navigation element","n","");

    public static SP findAddOnsCB = new SP("Find Add-ons Combobox","dn","Find add-ons");

    public static SP RFPassManE = new SP("RF PM Edit","dN","RoboForm Password Manager");
    //public static SP searchOrEnterAddressE = new SP("Search string","n","Search or enter address");
    public static SP searchOrEnterAddressE = new SP("Search string","N"," or enter address");


}
