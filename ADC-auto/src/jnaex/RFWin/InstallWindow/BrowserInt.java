package jnaex.RFWin.InstallWindow;

import jnaex.RFWin.ElemD;

/**
 * Created by Evgenia on 02-Jun-17. Additional options during installation > Browser Integration
 */
public class BrowserInt {
    public static ElemD mainW = new ElemD("Install RoboForm ","#32770");
    /**
     * <p>'Show Advanced Options' checkbox</p>
     */
    public static ElemD IEComboB = new ElemD("Windows Internet Explorer","Button");
    public static ElemD FFComboB = new ElemD("Mozilla Firefox","Button");
    public static ElemD ChromeComboB = new ElemD("Google Chrome","Button");
    public static ElemD OperaComboB = new ElemD("Opera","Button");

    public static ElemD ApplicationsComboB = new ElemD("Fill and Save Forms in Windows Applications","Button");

    public static ElemD closeAppHeaderT = new ElemD("Closing Applications","");
    /**
     * Back button - there are many, use IsOffScreen property
     */
    public static ElemD backB = new ElemD("Back","Button");


    public static ElemD nextB = new ElemD("Next","Button", "5002");
}
