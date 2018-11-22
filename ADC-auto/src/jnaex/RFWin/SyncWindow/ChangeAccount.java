package jnaex.RFWin.SyncWindow;

import jnaex.RFWin.ElemD;

/**
 * Created by Evgenia on 27-Jun-17.
 */
public class ChangeAccount {

    public static ElemD mainW = new ElemD ("RoboForm Sync Settings", "#32770");

    /**first window*/

    public static ElemD emailField = new ElemD ("Email or User ID:", "Edit");

    public static ElemD createNewAcc = new ElemD ("Create New RoboForm account");

    public static ElemD nextB1 = new ElemD ("Next", "Button", "5005");


    /** second window*/

    public static ElemD passwordField = new ElemD ("Enter password for RoboForm Account:", "Edit");

    public static ElemD nextB2 = new ElemD ("Next", "Button", "5006");

    public static ElemD backB = new ElemD ("Back", "Button");


    /** end window*/

    public static ElemD OkB = new ElemD ("Ok", "Button", "5013");





}
