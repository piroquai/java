package jnaex.RFWin.Kotlin.Enums

/**
 * Created by Autotester on 9/11/2018.
 */
enum class OptionsControls(val text : String, val category : OptionsCategories, val lct : LCT) {
    SELECTLANGUAGE("Select language for RoboForm interface",OptionsCategories.GENERAL,LCT.COMBOBOX),
    EDITLANGUAGEFILE("Edit language file",OptionsCategories.GENERAL,LCT.BUTTON),
    NEWLANGUAGEFILE("New language file",OptionsCategories.GENERAL,LCT.BUTTON),
    SHOWBOOKMARKSANDLOGINSTOGETHER("Show Bookmarks and Logins together",OptionsCategories.GENERAL,LCT.CHECKBOX),
    SAMEBROWSERTAB("Same Browser Tab",OptionsCategories.GENERAL,LCT.RADIOBUTTON),
    NEWBROWSERTAB("New Browser Tab",OptionsCategories.GENERAL,LCT.RADIOBUTTON),
    FILLSUBMITDEFAULTMATCHINGLOGINS("'Fill & Submit' is the default action for Matching Logins",OptionsCategories.GENERAL,LCT.CHECKBOX),
    FILLSUBMITDEFAULTMYIDENTITIES("'Fill & Submit' is the &default action for My Identities",OptionsCategories.GENERAL,LCT.CHECKBOX),
    MAXITEMSMRULISTEDIT("Maximum number of items on the Most Recently Used list",OptionsCategories.GENERAL,LCT.EDIT),
    MAXITEMSMRULISTSPINNER("Maximum number of items on the Most Recently Used list",OptionsCategories.GENERAL,LCT.SPINNER),
    MAXITEMSMPLISTEDIT("Maximum number of items on the Most Popular list",OptionsCategories.GENERAL,LCT.EDIT),
    MAXITEMSMPLISTSPINNER("Maximum number of items on the Most Popular list",OptionsCategories.GENERAL,LCT.SPINNER),

    GOOGLECHROMEEXTENSION("Google Chrome extension",OptionsCategories.BROWSERS,LCT.TEXT),
    MICROSOFTEDGEEXTENSION("Microsoft Edge extension",OptionsCategories.BROWSERS,LCT.TEXT),
    MOZILLAFIREFOXADDON("Mozilla Firefox add-on",OptionsCategories.BROWSERS,LCT.TEXT),
    OPERAEXTENSION("Opera extension",OptionsCategories.BROWSERS,LCT.TEXT),
    CHROMIUMBASEDBROWSERS("Chromium-based browsers",OptionsCategories.BROWSERS,LCT.TEXT),
    FIREFOXBASEDBROWSERS("Firefox-based browsers",OptionsCategories.BROWSERS,LCT.TEXT),
    INSTALLBROWSEREXTENSION("Install",OptionsCategories.BROWSERS,LCT.TEXT), //corresponding to extensions counter
    INSTALLBROWSEREXTENSIONALTERNATIVE("Alternative",OptionsCategories.BROWSERS,LCT.TEXT),

    TOOLBAREDIT("",OptionsCategories.TOOLBAR,LCT.EDIT), //all are just edits there
    NUMBEROFMYIDENTITYBUTTONS("Number of My Identity Buttons",OptionsCategories.TOOLBAR,LCT.COMBOBOX),

    ACCOUNTDATATEXT("",OptionsCategories.ACCOUNTDATA,LCT.TEXT),
    CHANGEACCOUNT("Change Account",OptionsCategories.ACCOUNTDATA,LCT.TEXT),
    IMPORT("Import",OptionsCategories.ACCOUNTDATA,LCT.BUTTON),
    EXPORT("Export",OptionsCategories.ACCOUNTDATA,LCT.BUTTON),
    BACKUPANDRESTORE("Backup and Restore",OptionsCategories.ACCOUNTDATA,LCT.BUTTON),
    ACCOUNTDATAADVANCED("Advanced",OptionsCategories.ACCOUNTDATA,LCT.TEXT),
    IMPORTV7DATA("Import V7 data",OptionsCategories.ACCOUNTDATA,LCT.TEXT),
    RECONVERTRF7DATA("Re-convert RoboForm ver. 7 User Data",OptionsCategories.ACCOUNTDATA,LCT.TEXT),
    SELECTDATAFOLDER("Select Data Folder",OptionsCategories.ACCOUNTDATA,LCT.TEXT),
    SETDEFAULTDATALOCATION("Set Default Data Location",OptionsCategories.ACCOUNTDATA,LCT.TEXT),

    CHANGEMASTERPASSWORD("Change Master Password",OptionsCategories.SECURITY,LCT.BUTTON),
    DONTASKFORMASTERPASSWORD("Don't ask for Master Password",OptionsCategories.SECURITY,LCT.CHECKBOX),
    ENABLEFINGERPRINT("Enable fingerprint authentication on WBF reader",OptionsCategories.SECURITY,LCT.CHECKBOX),
    AUTOLOGOUTONUSERSWITCH("Auto-Logout on User Switch or Lock Workstation",OptionsCategories.SECURITY,LCT.CHECKBOX),
    AUTOLOGOUTAFTERMINEDIT("",OptionsCategories.SECURITY,LCT.EDIT), //the only one
    AUTOLOGOUTAFTERMINSPINNER("",OptionsCategories.SECURITY,LCT.SPINNER), //the only one
    RESETALLWARNINGSTOSHOW("Reset all warnings to show",OptionsCategories.SECURITY, LCT.TEXT),

    AUTOFILLON("AutoFill On",OptionsCategories.AUTOFILL,LCT.CHECKBOX),
    SHOWLOGINSANDIDENTITIES("Show Logins ans Identities",OptionsCategories.AUTOFILL,LCT.RADIOBUTTON),
    SHOWLOGINSONLY("Show Logins only",OptionsCategories.AUTOFILL,LCT.RADIOBUTTON),
    SHOWIDENTITIESONLY("Show Identities only",OptionsCategories.AUTOFILL,LCT.RADIOBUTTON),
    AUTOFILLFROMLOGINSONLY("AutoFill from Logins only on pages with Passwords",OptionsCategories.AUTOFILL,LCT.CHECKBOX),
    PAGEFIELDSREQUIREDEDIT("",OptionsCategories.AUTOFILL,LCT.EDIT), //the only one
    PAGEFIELDSREQUIREDSPINNER("",OptionsCategories.AUTOFILL,LCT.SPINNER), //the only one
    ALWAYSPROMPTBEFOREFILL("Always Prompt before Fill",OptionsCategories.AUTOFILL,LCT.CHECKBOX),
    AUTOFILLWINDOWSBASICAUTH("AutoFill Windows Basic Authentication dialogs in browsers",OptionsCategories.AUTOFILL,LCT.CHECKBOX),

    OFFERTOSAVEFROMHTML("Offer to Save Logins from web (HTML) forms",OptionsCategories.AUTOSAVE,LCT.CHECKBOX),
    OFFERTOSAVEFROMBASICAUTH("Offer to Save Logins from Windows dialogs in browsers (Basic Auth",OptionsCategories.AUTOSAVE,LCT.CHECKBOX),
    ALTCLICKFORCESAUTOSAVE("ALT+click of Login button forces AutoSave",OptionsCategories.AUTOSAVE,LCT.CHECKBOX),
    SHIFTENTERFORCESAUTOSASVE("SHIFT+ENTER in text field forces AutoSave",OptionsCategories.AUTOSAVE,LCT.CHECKBOX),

    CONTEXTMENUSELECTALL("Select All",OptionsCategories.CONTEXTMENU,LCT.TEXT), //two of them
    CONTEXTMENUREMOVEALL("Remove All",OptionsCategories.CONTEXTMENU,LCT.TEXT), //two of them
    CONTEXTMENUCHECKBOX("",OptionsCategories.CONTEXTMENU,LCT.CHECKBOX), // miriads of them, ladder-type, inside empty custom

    SEARCHCHECKBOX("",OptionsCategories.SEARCH,LCT.CHECKBOX), //one per search engine, inside empty custom
    SEARCHMOVEUP("Move Up",OptionsCategories.SEARCH,LCT.BUTTON),
    SEARCHMOVEDOWN("Move Down",OptionsCategories.SEARCH,LCT.BUTTON),
    SEARCHREMOVE("Remove",OptionsCategories.SEARCH,LCT.BUTTON),
    SEARCHEDIT("Edit",OptionsCategories.SEARCH,LCT.BUTTON),
    SEARCHCOMBOBOX("",OptionsCategories.SEARCH,LCT.COMBOBOX), //the only one
    SAVESEARCHHISTORY("Save Search History",OptionsCategories.SEARCH,LCT.CHECKBOX),
    SEARCHCLEAR("Clear",OptionsCategories.SEARCH,LCT.BUTTON),
    MAXNUMBERSEARCHHISTORYEDIT("",OptionsCategories.SEARCH,LCT.EDIT), // the only one
    MAXNUMBERSEARCHHISTORYSPINNER("",OptionsCategories.SEARCH,LCT.SPINNER), //the only one

    ENABLEROBOFORMSHORTCUTSINBROWSER("Enable RoboForm Shortcuts in Browser",OptionsCategories.KEYBOARD,LCT.CHECKBOX),
    KEYBOARDSHORTCUTITEM1("List of RoboForm shortcuts in browser. You can turn on/off each of them separately",OptionsCategories.KEYBOARD,LCT.WINDOW),//visible shortcuts :-) inside custom
    KEYBOARDSHORTCUTITEM2("",OptionsCategories.KEYBOARD,LCT.WINDOW), // non-visible shortcuts, inside custom
    CTRL("Ctrl",OptionsCategories.KEYBOARD,LCT.CHECKBOX),
    SHIFT("Shift",OptionsCategories.KEYBOARD,LCT.CHECKBOX),
    ALT("Alt",OptionsCategories.KEYBOARD,LCT.CHECKBOX),
    USERIGHTALT("Use Right ALT for Shortcuts too",OptionsCategories.KEYBOARD,LCT.CHECKBOX),

    EQUIVALENTDOMAINS("",OptionsCategories.DOMAINS,LCT.EDIT), //the only one
    WARNABOUTSUBMITTINGNOTMATCH("Warn about submitting a Login with a domain what doesn't match the page",OptionsCategories.DOMAINS,LCT.CHECKBOX),

    ATTACHTOWINDOWSAPPLICATIONS("Attach to Windows application",OptionsCategories.APPLICATIONS,LCT.CHECKBOX),
    APPLICATIONSLIST("",OptionsCategories.APPLICATIONS,LCT.LIST), //the only one
    APPLICATIONSADD("Add",OptionsCategories.APPLICATIONS,LCT.BUTTON),
    APPLICATIONSREMOVE("Remove",OptionsCategories.APPLICATIONS,LCT.BUTTON)

}