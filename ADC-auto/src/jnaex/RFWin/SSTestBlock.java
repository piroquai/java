package jnaex.RFWin;

import jnaex.RFWin.Editor.EditorD;
import jnaex.RFWin.SSWindow.FolderShar;
import jnaex.RFWin.SSWindow.ReceiverWDlg;
import jnaex.RFWin.SSWindow.Send;
import daima.DElement;
import jnaex.RFWin.SearchPresets.RF;
import jnaex.RFWin.User.RFUser;

////////////////////////////////
////////////////////////////////////

/**
 * Created by Evgenia on 08-Jun-17.
 */


public class SSTestBlock extends Proc {

    private SSTestBlock() {
    }
//    public static String emailSend = "";
//    public static String passwSend = "";
//    public static String emailOReceive = "";
//    public static String passwOReceive = "";
//    public static String emailRReceive = "";
//    public static String passwRReceive = "";
//    public static String emailLReceive = "";
//    public static String passwLReceive = "";

    public static String emailFReceiveA = "";
    public static String emailFReceiveR = "";
    public static String passwFReceive = "";

    public static String sharedFolderDefaultIdentificatior = "SFolder";

    public static String nameSendFile = "";
    public static String nameSafeNote = "";
    public static String logString = "Send Test Block: ";
    public static Boolean res;
    public static String nameSFolder = "";

    public static String nameShareFileA = "";
    public static String nameShareFileR = "";
    private static DElement g(DElement _parent, String _displayName, int _attempts, String _XattrList, String..._Xattrs) throws Exception{
        return DElement.gimMeP(_parent,_displayName,_attempts,_XattrList,_Xattrs);
    }

    public static void sendFile(RFUser _user, boolean _flag, String _nameFile) throws Exception {
        lgP = "SendFile::";

        setLogBlockPrefix("sendFile");
        log("Starting procedure");
        try {
//            DElement ssW = sibAttachDriverCN( EditorD.editorW.cName);
//            if (ssW == null) {
//                throw new Exception("Editor window was failed to connect");
//            }


            nameSafeNote = _nameFile;
            DElement rfE = gL(null,"Editor",1,"c","RfEditor");
//            DElement window = rfE.findElement(By.xpath("//*[@Name='" + nameSafeNote + "']"));
            DElement window = gL(rfE,nameSafeNote,1,"dn",nameSafeNote);
            //window.click();
            sleepy(2);


//            Actions builder = new Actions(_webDriver);
//            builder.contextClick(window).build().perform();
            window.contextClick();

//            DElement context = _webDriver.findElement(By.className("#32768"));
            //DElement context = gL(null,"Context",1,"c","#32768");
            DElement context = gL(null,RF.contextM,"na",1,"");

            //context.click();

//            Action enter = builder
//                    .moveToElement(context)
//                    .moveByOffset(0, 75)
//                    .click()
//                    .build();
//            enter.perform();
            offsetClick(context,0,75);
//            DElement sharwin = _webDriver.findElement(By.name("RoboForm Sharing"));
            DElement sharwin = gL(null,"Sharwin",2,"n","RoboForm Sharing");

            //context = gimMe(sharwin, Send.emailField, "_RoboForm_Dialog_1100973_");
            context = gL(sharwin, RF.emailSharedE, 2);
            sleepy(1);
//            sendKeysWr(context, _email);
            context.setEditValue(_user.getEmail());
            //context = gimMe(sharwin, Send.sendB, "Send");
            context = gL(sharwin, new SP ("send button", "n", "Send"), 2 );
            context.click();

            //first

            if (_flag) {
                log("Verifying is warning message exists or not, user does not exist, OK send operation");
                try {
                    //DElement sharwin2 = _webDriver.findElement(By.name("RoboForm Sharing"));
                    DElement sharwin2 = g(null,"sharwin2",1,"n","RoboForm Sharing");
                    //context = gimMe(sharwin2, ReceiverWDlg.mainW, "main war wind");
                    context = gL(sharwin2, new SP ("RoboForm Question", "n", "RoboForm Question"), 2 );

                    //context = gimMe(context, ReceiverWDlg.btnOK, "OK");
                    context = gL(context, new SP ("OK button", "n", "OK"), 2 );

                    context.click();
                    sleepy(0.5);
                    res = false;
                    //context = gimMe(sharwin, Send.closeB, "Close");
                    context = gL(sharwin, new SP ("Close button", "n", "Close"), 2 );

                    context.click();
                    log("File was sent, but user does not exist");
                } catch (Exception e) {
                    log("File was sent");
                    res = true;
                }
            } else {
                //second
                log("Verifying is warning message exists or not, user does not exist, canceling send operation");
                try {
                    //DElement sharwin2 = _webDriver.findElement(By.name("RoboForm Sharing"));
                    DElement sharwin2 = g(null,"sharwin2",1,"n","RoboForm Sharing");
                    //context = gimMe(sharwin2, ReceiverWDlg.mainW, "main war wind");
                    context = gL(sharwin2, new SP ("main war wind", "n", "RoboForm Question"), 2 );

                    //context = gimMe(context, ReceiverWDlg.btnCancel, "cancel");
                    context = gL(context, new SP ("Cancel button", "n", "Cancel"), 2 );

                    context.click();
                    sleepy(0.5);
                    res = false;
                    log("File was not sent to a not existing user");
                } catch (Exception e) {
                    log("File was sent");
                    res = true;
                }

                //third
                log("Verifying is email correct");
                try {
                    //DElement sharwin2 = _webDriver.findElement(By.name("RoboForm Sharing"));
                    DElement sharwin2 = gL(null, RF.sharingW,2);
                    //gimMe(sharwin2, ReceiverWDlg.emailIncorrect, "email was incorrect");
                    gL(sharwin2, new SP ("email was incorrect","n","Email is not correct"), 2 );
                    sleepy(0.5);
                    res = false;
                    log("Email was incorrect, file does not sent");
                } catch (Exception e) {
                    log("File was sent");
                    res = true;
                }
                sleepy(1);
                //context = gimMe(sharwin, Send.closeB, "Close");
                context = gL(sharwin, new SP ("close button","n","Close"), 2);
                context.click();
            }
        } catch (Exception e) {
            logE("Procedure failed: " + e.getMessage());
            res = true;
            throw e;
        }
    }


    public static void shareFile(RFUser _user, boolean _flag, String _nameFile) throws Exception {
        lgP = "ShareFile:";

        setLogBlockPrefix("shareFile");
        log("Starting sharing file procedure");
        try {
//            DElement ssW = sibAttachDriverCN( EditorD.editorW.cName);
//            if (ssW == null) {
//                throw new Exception("Editor window was failed to connect");
//            }

            nameSafeNote = _nameFile;
            //DElement rfE = _webDriver.findElement(By.className("RfEditor"));
//            DElement rfE = g(null,"Editor",1,"c","RfEditor");
            DElement rfE = gL(null,RF.editorW,1);

            //DElement window = rfE.findElement(By.xpath("//*[@Name='" + nameSafeNote + "']"));
            //DElement window = g(rfE,"window",1,"dn",nameSafeNote);
            DElement window = gL(rfE,new SP("Window","dn",nameSafeNote));

            //window.click();
            sleepy(2);


//            Actions builder = new Actions(_webDriver);
//            builder.contextClick(window).build().perform();
            window.contextClick();

            //DElement context = _webDriver.findElement(By.className("#32768"));
//            DElement context = g(null,"Context",1,"c","#32768");
            DElement context = gL(null,RF.contextM,"na",1,"");

            //context.click();
//            Action enter = builder
//                    .moveToElement(context)
//                    .moveByOffset(0, 65)///75
//                    .click()
//                    .build();
//            enter.perform();
            offsetClick(context,0,65);
            //DElement sharwin = _webDriver.findElement(By.name("RoboForm Sharing"));
//            DElement sharwin = g(null,"sharwin",1,"n","RoboForm Sharing");
            DElement sharwin = gL(null,RF.sharingW,1);

//            context = gimMe(sharwin, FileShar.emailField, "_RoboForm_Dialog_1100973_");
            context = gL(sharwin,RF.emailSharedE,1);

            sleepy(1);
//            sendKeysWr(context, _email);
            context.setEditValue(_user.getEmail());
//            context = gimMe(sharwin, FileShar.addB, "Add button");
            context = gL(sharwin,RF.addB,1);

            context.click();

            //first

            if (_flag) {
                log("Verifying is warning message exists or not, user does not exist, OK send operation");
                try {
                    //DElement sharwin2 = _webDriver.findElement(By.name("RoboForm Sharing"));
//                    DElement sharwin2 = g(null,"sharwin2",1,"n","RoboForm Sharing");
                    DElement sharwin2 = gL(null,RF.sharingW,1);

//                    context = gimMe(sharwin2, ReceiverWDlg.mainW, "main war wind");
                    context = gL(sharwin2,RF.roboformQuestionDlgW,1);

//                    context = gimMe(context, ReceiverWDlg.btnOK, "OK");
                    context = gL(context,RF.okB,1);

                    context.click();
                    sleepy(0.5);
                    res = false;
                    log("File was sent, but user does not exist");
                } catch (Exception e) {
                    log("File was sent");
                    res = true;
                }
                sleepy(3);
//                context = gimMe(sharwin, FileShar.closeB, "Close");
                context = gL(sharwin, RF.closeB,1);

                context.click();
            } else {
                //second
                log("Verifying is warning message exists or not, user does not exist, canceling send operation");
                try {
                    //DElement sharwin2 = _webDriver.findElement(By.name("RoboForm Sharing"));
//                    DElement sharwin2 = g(null,"sharwin2",1,"n","RoboForm Sharing");
                    DElement sharwin2 = gL(null,RF.sharingW,1);

//                    context = gimMe(sharwin2, ReceiverWDlg.mainW, "main war wind");
                    context = gL(sharwin2,RF.roboformQuestionDlgW,1);

//                    context = gimMe(context, ReceiverWDlg.btnCancel, "cancel");
                    context = gL(context,RF.cancelB,1);

                    context.click();
                    sleepy(1);
                    res = false;
                    log("File was not sent to a not existing user");
                } catch (Exception e) {
                    log("File was sent");
                    res = true;
                }

                //third
                log("Verifying is email correct");
                try {
                    //DElement sharwin2 = _webDriver.findElement(By.name("RoboForm Sharing"));
//                    DElement sharwin2 = g(null,"sharwin2",1,"n","RoboForm Sharing");
                    DElement sharwin2 = gL(null,RF.sharingW,1);

//                    gimMe(sharwin2, ReceiverWDlg.emailIncorrect, "email was incorrect");
                    gL(sharwin2,RF.emailIncorrectT,1);

                    sleepy(0.5);
                    res = false;
                    log("Email was incorrect, file does not sent");
                } catch (Exception e) {
                    log("File was sent");
                    res = true;
                }
                sleepy(1);
                //context = gimMe(sharwin, FileShar.closeB, "Close");
                context = gL(sharwin,RF.closeB,1);

                context.click();
            }
        } catch (Exception e) {
            logE("Procedure failed: " + e.getMessage());
            res = true;
            throw e;
        }
    }

    public static String nameFolder = "";

    /**
     * @param _user
     * @param _flag
     * @param _nameFolder
     * @param _role       0-limited(default)(Login only) 1 - regular (Read and write) 2 - owner (manager) (Full control)
     * @throws Exception
     */
    public static void shareFolder(RFUser _user, boolean _flag, String _nameFolder, String _role) throws Exception {
        lgP = "ShareFolder:";

        setLogBlockPrefix("shareFolder");
        log("Starting sharing folder procedure");
        try {
            //DElement ssW = gimMeP(null,EditorD.editorW,"Editor window",1,"","an");
            DElement ssW = gL(null, RF.editorW,1);

            nameFolder = _nameFolder;
//            DElement tree = gimMeP(ssW, EditorD.treeRF, "RF tree with files", 2, "", "nc");
            DElement tree = gL(ssW,RF.editorTree,2);

            //DElement window = gimMeP(tree,_nameFolder,_nameFolder,2,"","ca");
            DElement window = gL(tree,new SP(_nameFolder,"n",_nameFolder),2);

            window.contextClick();
            window.contextClick();
//            DElement context = gimMeP(null, new ElemD ("Context", "#32768"),"context",2,"","na");
            DElement context = gL(null,RF.contextM,2);

            offsetClick(context,0,65);
            sleepy(4);
            //DElement sharwin = gimMeP(null, FolderShar.mainW, "FolderSharing main window", 2, "", "na");
            DElement sharwin = gL(null,RF.paneLT,2); //not truly paneLT, but the same

//            context = gimMe(sharwin, FolderShar.emailField, "email field");
            context = gL(sharwin,RF.emailSharedFE,1);

            context.sendKeys(_user.getEmail());

            if (_role.equals("Limited")) {
                /*it is default value for just created folder*/
                //DElement editRole = gimMeP(sharwin, FolderShar.viewRole, "edit Role Field", 2, "", "ca");
                DElement editRole = gL(sharwin,RF.viewRoleSharedD,2);//button dropdown
                editRole.click();
//                DElement allRoles = gimMeP(sharwin, FolderShar.paneWithRoles, "panel with roles", 2, "", "ca");
                DElement allRoles = gL(sharwin,RF.paneWithRolesSharedP,2);

//                DElement pane = gimMeP(allRoles, FolderShar.pane, "pane", 2, "", "ca");
                DElement pane = gL(allRoles,RF.paneSharedP,2);

//                DElement role = gimMeP(pane, FolderShar.limited, "regular", 2, "", "ca");
                DElement role = gL(pane,RF.limitedSharedT,2);
                role.click();
            }
            if (_role.equals("Regular")) {
//                DElement editRole = gimMeP(sharwin, FolderShar.viewRole, "edit Role Field", 2, "", "ca");
                DElement editRole = gL(sharwin,RF.viewRoleSharedD,2);

                editRole.click();
//                DElement allRoles = gimMeP(sharwin, FolderShar.paneWithRoles, "panel with roles", 2, "", "ca");
                DElement allRoles = gL(sharwin,RF.paneWithRolesSharedP,2);

                //                DElement pane = gimMeP(allRoles, FolderShar.pane, "pane", 2, "", "ca");
                DElement pane = gL(allRoles,RF.paneSharedP,2);

                //                DElement role = gimMeP(pane, FolderShar.regular, "regular", 2, "", "ca");
                DElement role = gL(pane,RF.regularSharedT,2);

                role.click();
            }
            if (_role.equals("Owner")) {
//                DElement editRole = gimMeP(sharwin, FolderShar.viewRole, "edit Role Field", 2, "", "ca");
                DElement editRole = gL(sharwin,RF.viewRoleSharedD,2);

                editRole.click();
//                DElement allRoles = gimMeP(sharwin, FolderShar.paneWithRoles, "panel with roles", 2, "", "ca");
                DElement allRoles = gL(sharwin,RF.paneWithRolesSharedP,2);

//                DElement pane = gimMeP(allRoles, FolderShar.pane, "pane", 2, "", "ca");
                DElement pane = gL(allRoles,RF.paneSharedP,2);

//                DElement role = gimMeP(pane, FolderShar.owner, "regular", 2, "", "ca");
                DElement role = gL(pane,RF.ownerSharedT,2);

                role.click();
            }

            if (_role.equals("Full control")) {

                //DElement editRole = gimMeP(sharwin, FolderShar.viewRole, "edit Role Field", 2, "", "ca");
                DElement editRole = gL(sharwin,RF.viewRoleSharedD,2);//button dropdown
                editRole.click();

//                DElement allRoles = gimMeP(sharwin, FolderShar.paneWithRoles, "panel with roles", 2, "", "ca");
                DElement allRoles = gL(sharwin,RF.panelWithPermissionsSharedP,2);

//                DElement pane = gimMeP(allRoles, FolderShar.pane, "pane", 2, "", "ca");
                DElement pane = gL(allRoles,RF.paneSharedP2,2);

//                DElement role = gimMeP(pane, FolderShar.limited, "regular", 2, "", "ca");
                DElement role = gL(pane,RF.fullControlSharedT,2);
                role.click();
            }

            if (_role.equals("Read and write")) {

                //DElement editRole = gimMeP(sharwin, FolderShar.viewRole, "edit Role Field", 2, "", "ca");
                DElement editRole = gL(sharwin,RF.viewRoleSharedD,2);//button dropdown
                editRole.click();

//                DElement allRoles = gimMeP(sharwin, FolderShar.paneWithRoles, "panel with roles", 2, "", "ca");
                DElement allRoles = gL(sharwin,RF.panelWithPermissionsSharedP,2);

//                DElement pane = gimMeP(allRoles, FolderShar.pane, "pane", 2, "", "ca");
                DElement pane = gL(allRoles,RF.paneSharedP2,2);

//                DElement role = gimMeP(pane, FolderShar.limited, "regular", 2, "", "ca");
                DElement role = gL(pane,RF.readAndWriteSharedT,2);
                role.click();
            }

            if (_role.equals("Login only")) {

                //DElement editRole = gimMeP(sharwin, FolderShar.viewRole, "edit Role Field", 2, "", "ca");
                DElement editRole = gL(sharwin,RF.viewRoleSharedD,2);//button dropdown
                editRole.click();

//                DElement allRoles = gimMeP(sharwin, FolderShar.paneWithRoles, "panel with roles", 2, "", "ca");
                DElement allRoles = gL(sharwin,RF.panelWithPermissionsSharedP,2);

//                DElement pane = gimMeP(allRoles, FolderShar.pane, "pane", 2, "", "ca");
                DElement pane = gL(allRoles,RF.paneSharedP2,2);

//                DElement role = gimMeP(pane, FolderShar.limited, "regular", 2, "", "ca");
                DElement role = gL(pane,RF.loginOnlySharedT,2);
                role.click();
            }

//          context = gimMe(sharwin, FolderShar.addB, "Add button");
            context = gL(sharwin,RF.addB,1);

            context.click();
            sleepy (2);

            //проверка корректности емеила
            //Email is not correct


            gL(sharwin,new SP ("Email is not correct warning", "Dn", "Email is not correct"),1);

            //Email must be at the least 6 characters long

            gL(sharwin,new SP ("Email must be at the least 6 characters long warning", "Dn", "Email must be at the least 6 characters long"),1);

            sleepy(5);

            //first

            /*if (_flag) {
                log("Verifying is warning message exists or not, user does not exist, OK send operation");
                try {
                    DElement sharwin2 = _webDriver.findElement(By.name("RoboForm Sharing"));
                    context = gimMe(sharwin2, ReceiverWDlg.mainW, "main war wind");
                    context = gimMe(context, ReceiverWDlg.btnOK, "OK");
                    context.click();
                    sleepy(0.5);
                    res = false;
                    log("File was sent, but user does not exist");
                } catch (Exception e) {
                    log("File was sent");
                    res = true;
                }
                sleepy(1);
                context = gimMe(sharwin, FileShar.closeB, "Close");
                context.click();
            } else {
                //second
                log("Verifying is warning message exists or not, user does not exist, canceling send operation");
                try {
                    DElement sharwin2 = _webDriver.findElement(By.name("RoboForm Sharing"));
                    context = gimMe(sharwin2, ReceiverWDlg.mainW, "main war wind");
                    context = gimMe(context, ReceiverWDlg.btnCancel, "cancel");
                    context.click();
                    sleepy(0.5);
                    res = false;
                    log("File was not sent to a not existing user");
                } catch (Exception e) {
                    log("File was sent");
                    res = true;
                }

                //third
                log("Verifying is email correct");
                try {
                    DElement sharwin2 = _webDriver.findElement(By.name("RoboForm Sharing"));
                    context = gimMe(sharwin2, ReceiverWDlg.emailIncorrect, "email was incorrect");
                    sleepy(0.5);
                    res = false;
                    log("Email was incorrect, file does not sent");
                } catch (Exception e) {
                    log("File was sent");
                    res = true;
                }
                sleepy(1);
                context = gimMe(sharwin, FileShar.closeB, "Close");
                context.click();
            }*/

//            context = gimMe(sharwin, FileShar.closeB, "Close");
            context = gL(sharwin,RF.closeB,1);

            context.click();
        } catch (Exception e) {
            logE("Procedure failed: " + e.getMessage());
            res = true;
            throw e;
        }
    }


    public static void roleConformationSender(String _nameFolder, String _role) throws Exception
    {
        lgP = "roleConformationSender:";

        setLogBlockPrefix("roleInformationSender");
        log("Starting roleConformationSender procedure");
        try {
            DElement ssW = gimMeP(null,EditorD.editorW,"Editor window",1,"","an");

            nameFolder = _nameFolder;
            DElement tree = gimMeP(ssW, EditorD.treeRF, "RF tree with files", 2, "", "nc");
            DElement window = gimMeP(tree,_nameFolder,"search" +  _nameFolder,2,"","ca");
            sleepy(4);
            window.click();
            sleepy(4);
//            Actions builder = new Actions(_webDriver);
//            builder.contextClick(window).build().perform();
            window.contextClick();
//            builder = new Actions(_webDriver);
//            builder.contextClick(window).build().perform();
            window.contextClick();

            DElement context = gimMeP(null,new ElemD ("Context", "#32768"),"context",2,"","na");
//            Action enter = builder
//                    .moveToElement(context)
//                    .moveByOffset(0, 65)///75
//                    .click()
//                    .build();
//            enter.perform();
            offsetClick(context,0,65);

            DElement sharwin = gimMeP(null,FolderShar.mainW, "FolderSharing main window", 2, "", "na");
            context = gimMeP(sharwin, new ElemD("Recipients", "", ""), "Pane with recipients", 2, "", "ca");
            context = gimMeP(context, new ElemD("", "Edit", ""), "Roles", 2, "", "na");
            //String role = context.getText();
            String role = context.getEditText();


            if (role.equals(_role)) {
                log("Roles are equal");
            }
            else  {
                logE("Roles are not equal");
            }

            DElement close = gimMeP(sharwin, FolderShar.closeB, "close FolderSharing main window", 2, "", "ca");
            close.click();
        } catch (Exception e) {
            logE("Procedure failed: " + e.getMessage());
            res = true;
            throw e;
        }

    }

    public static void roleConformationReceiver(String _nameFolder, String _role) throws Exception
    {
        lgP = "roleConformationReceiver:";

        setLogBlockPrefix("roleInformationReceiver");
        log("Starting roleConformationReceiver procedure");
        try {
            DElement ssW = gimMeP(null,EditorD.editorW,"Editor window",1,"","an");

            nameFolder = _nameFolder;
            DElement tree = gimMeP(ssW, EditorD.treeRF, "RF tree with files", 2, "", "nc");
            DElement window = gimMeP(tree,_nameFolder,"search" +  _nameFolder,2,"","ca");
            sleepy(4);
            window.click();
            sleepy(4);
            window.contextClick();
//            builder = new Actions(_webDriver);
//            builder.contextClick(window).build().perform();
            window.contextClick();

            DElement context = gimMeP(null, new ElemD ("Context", "#32768"),"context",2,"","na");
//            Action enter = builder
//                    .moveToElement(context)
//                    .moveByOffset(0, 65)///75
//                    .click()
//                    .build();
//            enter.perform();
            offsetClick(context,0,65);
            DElement sharwin = gimMeP(null,FolderShar.mainW, "FolderSharing main window", 2, "", "na");
            String textRole = "";
            if (_role == "Owner")
                textRole = "You can share and edit folder.";
            else if (_role == "Regular")
                textRole = "You can edit folder.";
            else if (_role == "Limited")
                textRole = "You can only use logins in folder.";

            gimMeP(sharwin, new ElemD(textRole, "", ""), "Recipients role in folder", 2, "", "ca");
            log ("Roles are equal");

        } catch (Exception e) {
            logE ("Roles are not equal");
            logE("Procedure failed: " + e.getMessage());
            res = true;
            throw e;
        }

    }

    public static void shareFileConfirmation(String _nameFile, String _Email) throws Exception
    {
        lgP = "shareFileConfirmation:";

        setLogBlockPrefix("shareFileConfirmation");
        log("Starting shareFileConfirmation procedure");
        try {
            DElement ssW = gimMeP(null,EditorD.editorW,"Editor window",1,"","an");

            nameFolder = _nameFile;
            DElement tree = gimMeP(ssW, EditorD.treeRF, "RF tree with files", 2, "", "nc");
            DElement window = gimMeP(tree,_nameFile,"search _nameFolder",1,"","ca");

//            Actions builder = new Actions(_webDriver);
//            builder.contextClick(window).build().perform();
            window.contextClick();
//            builder = new Actions(_webDriver);
//            builder.contextClick(window).build().perform();
            window.contextClick();

            DElement context = gimMeP(null, new ElemD ("Context", "#32768"),"context",2,"","na");
//            Action enter = builder
//                    .moveToElement(context)
//                    .moveByOffset(0, 65)///75
//                    .click()
//                    .build();
//            enter.perform();
            offsetClick(context,0,65);
            DElement sharwin = gimMeP(null,FolderShar.mainW, "FolderSharing main window", 2, "", "na");
            context = gimMeP(sharwin, new ElemD("Recipients", "", ""), "Pane with recipients", 2, "", "ca");
            gimMeP(context, new ElemD(_Email, "", ""), "Receivers emeil", 2, "", "ca");

            DElement close = gimMeP(sharwin, FolderShar.closeB, "close FolderSharing main window", 2, "", "ca");
            close.click();
            log("File was share succsessfully");
        } catch (Exception e) {
            logE("Procedure failed: " + e.getMessage());
            res = true;
            throw e;
        }

    }
}

