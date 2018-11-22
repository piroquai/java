package jnaex.RFWin.SearchPresets;

import javafx.util.Pair;
import jnaex.RFWin.SP;

/**
 * Created by  Evgenia on 02-Jul-18.
 */
public class Chrome {
    //связка, ведущая от окна браузера до иконки РФ с поп-апом

    public static SP chromeWindow = new SP("chrome browser window","N","Google Chrome");
    public static SP chromePart1 = new SP("chrome browser part1","n","Google Chrome");
    public static SP chromePart2 = new SP("chrome browser part2","n","");
    public static SP chromePart3 = new SP("chrome browser part3","n","");
    public static SP chromePart4 = new SP("chrome browser part4","n","");
    public static SP chromeExt = new SP("chrome extension bar","n","Extensions");
    public static SP chromeRFIcon = new SP("chrome Rf Icon","n","Click this button to show RoboForm commands");

    public static SP rfPopUp = new SP("chrome Rf pop up","C","Chrome_WidgetWin_1");
    public static SP rfPopUp2 = new SP("chrome Rf pop up(основной)","N","Chrome Legacy Window");

    //команды основного pop-up
    //фиксированная ширина окна 377
    public static int pWidth;
    //высота от 425 (пустой аккаунт)
    public static int pHeight;
    public static int x0 = 0;
    public static int y0 = 0;
    //all elements (13)
    public static String[] popUpElements =
            {"startPage",
            "dotsMenu",
            "search",
            "searchBtn",
            "logins",
            "bookmarks",
            "safenotes",
            "setupFormFill",
            "save",
            "sync",
            "generate",
            "securityCenter",
            "logOut"};
    //относительные значения
    //startpage 23 25
    public static Pair <Integer, Integer> startPage = new Pair<> (23, 25);
    //3dots menu 353 25
    public static Pair <Integer, Integer> dotsMenu = new Pair<> (353, 25);
    //search 28 60
    public static Pair <Integer, Integer> search = new Pair<> (28, 60);
    //search button 350 57
    public static Pair <Integer, Integer> searchBtn = new Pair<> (350, 57);

    //logins [0..377] 99
    public static Pair <Integer, Integer> logins = new Pair<> (100, 99);
    //bookmarks [0..377] 135
    public static Pair <Integer, Integer> bookmarks = new Pair<> (100, 135);
    //safenotes [0..377] 173
    public static Pair <Integer, Integer> safenotes = new Pair<> (100, 173);
    //
    //setupff [0..377] 211
    public static Pair <Integer, Integer> setupFormFill = new Pair<> (100, 211);
    public static SP newI = new SP("setup new identity from extension","N","Chrome Legacy Window");
    //
    //save 59 264
    public static Pair <Integer, Integer> save = new Pair<> (59, 264);
    public static SP saveLB = new SP("autosave window","C","#32770");
    public static SP cancel = new SP("cancel button","N","Cancel");
    //sync 56 304
    public static Pair <Integer, Integer> sync = new Pair<> (56, 304);
    //generate 245 259
    public static Pair <Integer, Integer> generate = new Pair<> (245, 259);
    //security center 275 300
    public static Pair <Integer, Integer> securityCenter = new Pair<> (275, 300);
    //log out 60 331
    public static Pair <Integer, Integer> logOut = new Pair<> (60, 331);
    public static SP RFMPWindow = new SP ("roboform master password window", "C", "#32770");
    public static SP mpField = new SP ("master password field in MP windoe", "N", "Master Password");
    public static SP loginBtn = new SP ("log in button in MP window", "N", "Log In");
}
