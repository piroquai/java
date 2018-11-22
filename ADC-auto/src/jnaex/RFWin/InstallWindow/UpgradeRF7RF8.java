package jnaex.RFWin.InstallWindow;

import jnaex.RFWin.ElemD;

/**
 * RF8 installer when handling RF7 to RF8 procedure
 */
public class UpgradeRF7RF8 {
    /*==PAGE ONE - Select language=*/
    /**
     * <p>Main window - Name field is versioned:</p>
     * USAGE: MainW.name + [version formatted like 8-3-3-3] {Space is already included}
     */
    public static ElemD mainW = new ElemD("Install RoboForm ","#32770");
    /**
     * <p>Language selector combobox</p>
     */
    public static ElemD cbLS = new ElemD("Select your language:", "ComboBox");
    /**
     * <p>Next button</p>
     */
    public static ElemD nextBtn = new ElemD("Next","Button","5000");
    /*==PAGE TWO - existing data conversion warning=*/
    /**
     * <p>Install button</p>
     */
    public static ElemD installBtn = new ElemD("Install","Button","5001");

}
