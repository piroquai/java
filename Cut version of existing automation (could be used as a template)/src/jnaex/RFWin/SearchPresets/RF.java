package jnaex.RFWin.SearchPresets;


import jnaex.RFWin.SP;

/**
 * Created by Autotester on 3/27/2018.
 */
public class RF {
    public static SP nextB = new SP("Next button","nlo","Next","button","false");
    public static SP installB = new SP("Install button","nlo","Install","button","false");
    public static SP createNewAccB = new SP("Create New Account button","nlo","Create New Account","button","false");
    public static SP logInWithExistingAccB = new SP("Log In with Existing Account button","nlo","Log In with Existing Account","button","false");
    public static SP stopB = new SP("Stop button","nlo","Stop","button","false");
    public static SP acceptB = new SP("Accept button","nlo","Accept","button","false");
    public static SP rejectB = new SP("Reject button","nlo","Reject","button","false");
    public static SP closeB = new SP("Close button","nlo","Close","button","false");
    public static SP okBm = new SP("Ok button","nlo","Ok","button","false");
    public static SP okB = new SP("OK button","nlo","OK","button","false");
    public static SP cancelB = new SP("Cancel button","nlo","Cancel","button","false");
    public static SP logoutB = new SP("Log Out button","nl","Log Out","button");
    public static SP roboformB = new SP("RoboForm button","nl","RoboForm","split button");
    public static SP yesB = new SP("Yes button","nc","Yes","Button");
    public static SP noB = new SP("No button","nc","No","Button");
    public static SP backB = new SP("Back button","nc","Back","Button");
    public static SP changeB = new SP("Change button","nc","Change","Button");
    public static SP B = new SP("Button","co","Button","false");
    public static SP addB = new SP("Add button","n","Add");
    public static SP deleteB = new SP("Delete button","nl","Delete","button");
    public static SP saveB = new SP("Save button","nlo","Save","button","false");
    public static SP resetToDefaultsB = new SP("Reset to Defaults button","nlo","Reset to Defaults","button","false");

    public static SP editorW = new SP("Editor window","c","RfEditor");
    public static SP supremeWFast = new SP("Supreme window","ncU","RoboForm Setup","#32770","900");
    public static SP syncSetupW = new SP("Sync setup window","nc","Install RoboForm","#32770");
    /**x-Sync RoboForm Data Folder window*/
    public static SP syncRFDFW = new SP("Sync RoboForm Account window","nl","Sync RoboForm Account","window");
    public static SP syncRFDFW2 = new SP("Sync RoboForm Account window (V2)","nl","RoboForm Setup","window");
    public static SP syncSettingsW = new SP("Sync Settings Window","nl","RoboForm Sync Settings","window");
    /**RF warnings windows*/
    public static SP warningRFW = new SP("RoboForm warning window","nc","RoboForm Warning","#32770");
    public static SP browserExtensionSetupW = new SP("RF Browser Extension Setup window","nlco","RoboForm Browser Extension Setup","window","#32770","false");
    public static SP uninstallerW = new SP("RF Uninstaller window","nc","RoboForm Uninstaller","#32770");
    public static SP uninstallerMainW = new SP("RF Uninstaller main window","nc","Uninstalling RoboForm","#32770");
    public static SP errorDialogW = new SP("Error dialog window","Nc","Error","#32770");
    public static SP warningDialogW = new SP("Warning dialog window","Nc","Warning","#32770");
    public static SP warningDialogRFW = new SP("RoboForm warning dialog","nc","RoboForm Warning","#32770");
    public static SP installerW = new SP("RoboForm Installer window","N","Install RoboForm");
    public static SP confirmationInstallerW = new SP("Confirmation dialog window","nc","RoboForm Installer","#32770");
    public static SP browseForFolderW = new SP("Browse For Folder dialog window","nc","Browse For Folder","#32770");
    /**Uninstaller*/
    public static SP infoW = new SP("RoboForm Information window","nc","RoboForm Information","#32770");
    /**Options*/
    public static SP optionsW = new SP("RoboForm Options window","nl","RoboForm Options","window");
    /**Sharing*/
    public static SP sharingW = new SP("Sharing window","n","RoboForm Sharing");
    public static SP roboformQuestionDlgW = new SP("RoboForm Question dialog","nc","RoboForm Question","#32770");


    public static SP dnsChB = new SP("Do not sync my RF data checkbox","nc","Do not sync my RoboForm data","Button");
    public static SP removeAllChB = new SP("Remove all checkbox","nc","Remove all Passcards, Identities, Safenotes (RoboForm User Data) from this computer","Button");
    public static SP showAdvancedOptionsChB = new SP("Show Advanced Options checkbox","nc","Show Advanced Options","Button");
    public static SP installRFForAllUsersChB = new SP("All users checkbox","n","Install RoboForm for all Windows users");
    public static SP dnsThisMsgAgain = new SP("Do not show this message again checkbox","nl","Do not show this message again","check box");

    public static SP MPE = new SP("MP edit","nlo","Master Password:","edit","false");
    public static SP emailUserIDE = new SP("Email of UserID edit","nlo","Email or User ID:","edit","false");
    public static SP changeAccE = new SP("Change Account edit","nlo","Change Account","edit","false");
    /**x-SetupSyncWindow>editMPField*/
    public static SP syncSetupEnterMPE = new SP("Enter MP edit","nca","Enter Your Master Password","Edit", "1048");
    public static SP emailE = new SP("Email edit","nc","Email:","Edit");
    /**x-SetupSyncWindow>editNoMPCreateMP1*/
    public static SP chooseMPE = new SP("Choose a MP edit","nc","Choose a Master Password","Edit");
    /**x-SetupSyncWindow>editNoMPCreateMP2*/
    public static SP retypeMPE = new SP("Choose a MP edit","nc","Retype Your Master Password","Edit");
    public static SP folderE = new SP("Folder edit","nc","Folder:","Edit");
    public static SP editMultifileDataPassword = new SP("MP to open specific item edit","nc","Enter password that opens password-protected Login","Edit");
    /**shared screen*/
    public static SP emailSharedE = new SP("Email field","nc","_RoboForm_Dialog_1100973_","Edit");
    public static SP emailSharedFE = new SP("Email field","nc","Enter Email Address...","Edit");


    public static SP viewRoleSharedD = new SP("edit Role Field","n","button dropdown");
//    public static SP paneWithRolesP = new SP("panel with roles","nc","Read-write, show passwords", "RfAutoSavePopup3332333");
    //2 варианта
    public static SP paneWithRolesSharedP = new SP("panel with roles","n","Read-write, show passwords");
    public static SP panelWithPermissionsSharedP = new SP("panel with permissions","n","Read and Write, Non-Owner");
//    public static SP paneSharedP = new SP ("pane","nca","Role list", "ATL:6AC4DC48", "72835040");
    //2 варианта
    public static SP paneSharedP = new SP ("pane","n","Role list");
    public static SP paneSharedP2 = new SP ("pane2","n","Role list");

    //2 варианта
    public static SP ownerSharedT = new SP ("Owner","n","Owner");
    public static SP regularSharedT = new SP ("Regular User","n","Regular User");
    public static SP limitedSharedT = new SP ("Limited User","n","Limited User");
    public static SP fullControlSharedT = new SP ("Full control","n","Full control");
    public static SP readAndWriteSharedT = new SP ("Read and write","n","Read and write");
    public static SP loginOnlySharedT = new SP ("Login only","n","Login only");
    //send actions

    public static SP emailIncorrectT = new SP("email was incorrect","n","Email is not correct");

    public static SP editorTree = new SP("RF tree with files","a","1327");

    public static SP titleBar = new SP("Title bar","l","title bar");


    public static SP contextM = new SP("Context menu","nlc","Context","menu","#32768");
    public static SP syncContextM = new SP("Sync context menu","nlc","Sync","menu","#32768");

    public static SP closeAppHeaderT = new SP("'Closing Application' heading text","nc","Closing Applications","");
    public static SP selectLangT = new SP("Select your language text","nc","Select your language:","");
    /**Options*/
    public static SP optionsAccountDataSectionT = new SP("Account and Data Options section","Nl","Account","text");
    public static SP changeAccountT = new SP("Change Account Options text","nl","Change Account","text");
    /**Lower toolbar*/
    public static SP saveT = new SP("Save text (button)","n","Save");
    public static SP paneLT = new SP("Pane","c","#32770");

    public static SP langSelectorComboB = new SP("Language selector combobox","nc","Select your language:","ComboBox");

    /**Options*/
    public static SP optionsSectionsSI = new SP("Options' Sections Structure Item","lo","custom","false");

    /**Dummy tree item*/
    public static SP TI = new SP("Dummy tree item","l","tree item");

    /**TBI Actions*/
    public static SP ContextM = new SP("Context menu","c","#32768");
    public static SP WarningD = new SP("Warning dialog","nc","RoboForm", "#32770");
    public static SP OkBtn = new SP("OK button ","nc","OK", "Button");

    /**Appl Actions*/
    public static SP autoSave = new SP("Autosave dialog","c","#32770");
    public static SP nameF = new SP("Name field","c","Edit");



}
