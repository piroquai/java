package jnaex.RFWin

import daima.DElement
import daima.KeyboardHandler
import jnaex.RFWin.SearchPresets.*

fun loginToRFADC(_email : String, _password : String){
    Proc.setLogBlockPrefix("LoginToRFADC")
    val mainWindow = Proc.gL(null, mainW,2)
    mainWindow.setForeground()
    sleepy(1)
    val emailF = Proc.gNL(mainWindow, editE,2,0)
    emailF.click()
    KeyboardHandler.sendKeysHere(_email)
    val passwordF = Proc.gNL(mainWindow, editE,2,1)
    passwordF.click()
    KeyboardHandler.sendKeysHere(_password)
    val loginButton = Proc.gL(mainWindow,loginB,2)
    loginButton.click()
    sleepy(10)
}
fun checkSettingsWindowAvailability(): Boolean {
    Proc.setLogBlockPrefix("checkSWA")
    var result = false
    try{
        for (window in Proc.gVL(null, mainW,1)){
            if (isRFADCWindowSettings(window)){
                result = true
                break
            }
        }
    }catch (e: Exception){result = false}
    return result
}
fun isRFADCWindowSettings(_rfadcW : DElement): Boolean{
    Proc.setLogBlockPrefix("isRFADCWS")
    try{
        val custom2 = Proc.gNL(_rfadcW, customC,2,1)
        Proc.gL(custom2, stepsT,2)
        return true
    }catch(e : Exception){
        return false
    }
}
