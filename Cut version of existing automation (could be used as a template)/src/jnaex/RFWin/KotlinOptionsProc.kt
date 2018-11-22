package jnaex.RFWin

import daima.DElement
import jnaex.RFWin.Kotlin.Enums.GrandRFBContextMenu
import jnaex.RFWin.Kotlin.Enums.OptionsControls
import jnaex.RFWin.Kotlin.Enums.DialogActions
import jnaex.RFWin.Kotlin.Enums.LCT
import jnaex.RFWin.SearchPresets.RF
import jnaex.RFWin.User.UserProc

fun checkEditorAvailability() : Boolean {
    return isOk(null,RF.editorW,1)
}

fun openOptions() {
    Proc.setLogBlockPrefix("Open Options")

    if (!checkEditorAvailability()){
        log("Editor is closed, opening")
        sleepy(8.0)
    }
    val eW = gL(null,RF.editorW,1)
    gL(eW,RF.roboformB,1).click()
    clickItemFromContextMenu(GrandRFBContextMenu.OPTIONS.text)
}

fun getOptionsDE() : DElement {
    var res : DElement
    try{
        res = gL(null,RF.optionsW,2)
    } catch (e : Exception){
        openOptions()
        sleepy(2.0)
        res = gL(null,RF.optionsW,2)
    }
    return res
}

fun constructSPFromOptionsControlsEnum(oce : OptionsControls) : SP {
    return SP(oce.text, "nlo", oce.text, oce.lct.text, "false")
}
fun getActualOptionsControl(oce : OptionsControls) : DElement{
    val optW : DElement = getOptionsDE()
    var wrk = gL(optW,RF.optionsSectionsSI,2)
    gL(wrk,constructSPText(oce.category.text),1).click()
    sleepy(1.0)
    wrk = gNL(optW,RF.optionsSectionsSI,2,1)
    return gL(wrk, constructSPFromOptionsControlsEnum(oce),1)//todo: from inside custom indexed
}

/**
 * Close RF Options window using Cross button
 */
fun closeOptionsCross(){
    Proc.setLogBlockPrefix("Close Options [Cross]")
    var optDisplayed = false
    try{
        gL(null,RF.optionsW,2)
        optDisplayed = true
    } catch (e : Exception){
        log("Options are closed")
    }
    if (optDisplayed){
        val optW = gL(null,RF.optionsW,1)
        val titleBar = gL(optW,RF.titleBar,1)
        gL(titleBar,RF.closeB,1).click()
    }
}
/**
 * Save Options
 */
fun saveOptions(){
    Proc.setLogBlockPrefix("Save Options")
    val optW = gL(null,RF.optionsW,1)
    gL(optW,RF.saveB,1).click()
}
/**
 * Close RF Options using Cancel button
 */
fun closeOptionsCancel(){
    val optW = gL(null,RF.optionsW,1)
    gL(optW,RF.cancelB,1).click()
}
/**
 * Reset to Defaults
 */
fun resetOptions(){
    val optW = gL(null,RF.optionsW,1)
    gL(optW,RF.resetToDefaultsB,1).click()
    sleepy(2.0)
    val dlgW = gL(optW,RF.roboformQuestionDlgW,2)
    gL(dlgW,RF.okB,1).click()
}

/**
 *  saveNExit = true : should the Options be saved (true) or left as is (false)
 */
fun interactOption(oce : OptionsControls, value : String, saveNExit : Boolean = true){
    Proc.setLogBlockPrefix("Interact Option")
    log("Interacting the following option: ${oce.name} (${oce.lct.name})")
    val control = getActualOptionsControl(oce)
    interactDElement(control,oce.lct,value)
    if (saveNExit) {
        gL(getOptionsDE(),RF.saveB,2).click()
    }
}

fun handleOptWarning(da : DialogActions){
    Proc.setLogBlockPrefix("H.Options dialog.W")
    log("Handling Options dialog warning - performing ${da.name}")
    val ww = gL(getOptionsDE(),RF.warningDialogRFW,2)
    if (da != DialogActions.CLOSEWINDOW){
        gL(ww,when(da){
            DialogActions.YES -> RF.yesB
            DialogActions.NO -> RF.noB
            DialogActions.CANCEL -> RF.cancelB
            else -> throw Exception("handleOptWarning: Unhandled parameter: ${da.name}")
        }, 2).click()
    } else {
        //todo: make handling for closing RF window dialog
        throw Exception("handleOptWarning: Unhandled parameter: ${da.name}")
    }
}

fun handleBrowseForFolderDialog(folder : String, da : DialogActions){
    Proc.setLogBlockPrefix("H.Browse For Folder.D.")
    log("Handling Browse For Folder dialog with the following parameters:")
    log("folder: $folder, da: ${da.name}")
    val bw = gL(getOptionsDE(),RF.browseForFolderW,2)
    interactDElement(gL(bw,RF.folderE,2), LCT.EDIT,folder)
    sleepy(5)
    gL(bw,when(da){
        DialogActions.OK -> RF.okB
        DialogActions.CANCEL -> RF.cancelB
        else -> throw Exception("handleBrowseForFolderDialog: Unhandled parameter: ${da.name}")
    },2).click()
    sleepy(2)
    try{
        log("Question message for network handling")
        gL(gL(getOptionsDE(),RF.roboformQuestionDlgW,2),RF.yesB,2).click()
    } catch (e : Exception){
        log("No question has risen")
        return
    }
}

fun handleWarningAfterDataFolderChange(keepShowing: Boolean){
    val ww = gL(null,RF.WarningD,2)
    val dnsC = gL(ww,RF.dnsThisMsgAgain,2)
    if (keepShowing == dnsC.checkedState){
        dnsC.click()
        sleepy(0.5)
    }
    gL(ww,RF.okB,2).click()
}
fun makeDefaultDataFolder(){
    interactOption(OptionsControls.ACCOUNTDATAADVANCED,"",false)
    interactOption(OptionsControls.SETDEFAULTDATALOCATION,"",false)
    handleOptWarning(DialogActions.YES)
    sleepy(5)

}