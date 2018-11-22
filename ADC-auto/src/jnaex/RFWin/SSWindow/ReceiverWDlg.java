package jnaex.RFWin.SSWindow;

import jnaex.RFWin.ElemD;

/**
 * Created by Evgenia on 21-Jun-17.
 */
public class ReceiverWDlg {

    /**
     * <p>Editor RF8 SnS > Warning dialog on new user</p>
     */
        /**
         * Main handle
         */
        public static ElemD mainW = new ElemD("RoboForm Question","#32770");
        /**
         * OK button
         */
        public static ElemD btnOK = new ElemD("OK","Button","1");
        /**
         * Cancel button
         */
        public static ElemD btnCancel = new ElemD("Cancel","Button","2");
        /**
         * Email is not correct
         */
        public static ElemD emailIncorrect = new ElemD("Email is not correct");
}
