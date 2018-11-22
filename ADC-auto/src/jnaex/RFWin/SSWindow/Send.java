package jnaex.RFWin.SSWindow;

import jnaex.RFWin.ElemD;

/**
 * Created by Evgenia on 02-Jun-17.
 */
public class Send {
    public static ElemD mainW = new ElemD("RoboForm Sharing","#32770");

    public static ElemD sendB = new ElemD("Send");

    public static ElemD closeB = new ElemD("Close");

    public static ElemD emailField = new ElemD("_RoboForm_Dialog_1100973_","Edit");

    /**if user does not exists or uses old version*/

    public static ElemD questionW = new ElemD("RoboForm Question","#32770");

    public static ElemD okB = new ElemD("OK", "Button", "1");

    public static ElemD cancelB = new ElemD("Cancel", "Button","2");

    /** close any active window*/
    public static ElemD close = new ElemD("Close", "", "Close");

    public static ElemD emailNotCorrect = new ElemD ("Email is not correct");




}
