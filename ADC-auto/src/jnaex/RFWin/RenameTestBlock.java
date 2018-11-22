package jnaex.RFWin;

import daima.DElement;
import daima.KeyboardHandler;
import jnaex.RFWin.Editor.EditorD;
import jnaex.RFWin.SSWindow.FileShar;
import jnaex.RFWin.SSWindow.FolderShar;
import jnaex.RFWin.SSWindow.ReceiverWDlg;
import jnaex.RFWin.SSWindow.Send;
import jnaex.RFWin.User.RFUser;
import daima.DElement;
import org.openqa.selenium.Keys;

import static jnaex.RFWin.EditorProc.log;
import static jnaex.RFWin.Proc.*;



/**
 * Created by  Evgenia on 15-Jun-18.
 */
public class RenameTestBlock {


        public static String emailFReceiveA = "";
        public static String emailFReceiveR = "";
        public static String passwFReceive = "";
        public static String nameSendFile = "";
        public static String nameSafeNote = "";
        public static String nameSharedfolder = "";
        public static String newnameSharedfolder = "";
        public static String localnameSharedfolder = "";
        public static String logString = "Send Test Block: ";
        public static Boolean res;

        private static DElement g(DElement _parent, String _displayName, int _attempts, String _XattrList, String..._Xattrs) throws Exception{
            return DElement.gimMeP(_parent,_displayName,_attempts,_XattrList,_Xattrs);
        }

        public static void renameFile( String _nameFile, String _newname, String rename_type) throws Exception {
            lgP = "Rename File:";
            setLogBlockPrefix("renameFile");
            log("Starting procedure");
            try {
                nameSafeNote = _nameFile;
                DElement rfE = gL(null, "Editor", 1, "c", "RfEditor");
                DElement window = gL(rfE, nameSafeNote, 1, "dn", nameSafeNote);
                sleepy(6);
                window.click();//выделяем
                sleepy(6);
                //window.sendKeys(Keys.F2);//открываем ренейм
                window.click();//открываем ренейм
                sleepy(6);
                KeyboardHandler.sendKeysHere(_newname);
                //window = gL(rfE,"edit field",1,"n","");
                //window.sendKeys();
                //KeyboardHandler.sendKeysHere(Keys.ENTER);
                sleepy(6);
                window = gL(rfE, "clicking", 1, "n", "RoboForm");
                window.click();
                sleepy(10);

                } catch (Exception e) {
                    logE("Procedure failed: " + e.getMessage());
                    throw e;
                }

        }
    public static void renameFile( String _nameFile, String _newname) throws Exception
    {
        renameFile( _nameFile, _newname, "by F2");
    }
    }



