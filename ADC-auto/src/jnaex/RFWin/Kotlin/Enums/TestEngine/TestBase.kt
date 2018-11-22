package jnaex.RFWin.Kotlin.Enums.TestEngine

import jnaex.RFWin.EditorProc
import jnaex.RFWin.InstallerProc

import jnaex.RFWin.*
import jnaex.RFWin.SearchPresets.RF
import jnaex.RFWin.User.RFUser
import jnaex.RFWin.User.UserProc

/**
 * Created by Autotester on 9/14/2018.
 */
fun performGiven(_given : Given){
    when (_given){
        Given.ROBOFORMNOTLAUNCHED -> {
            try {
                EditorProc.runRF8Editor()
                EditorProc.closeRF8EditorSoft()
                EditorProc.killTBI()
            } catch (e : Exception) {
                InstallerProc.uninstallRF()
                InstallerProc.injectRFOTestServer()
                InstallerProc.basicInstall()
                TBIProc.syncSetupRF8Current(UserProc.getUser())
                EditorProc.runRF8Editor()
                EditorProc.closeRF8EditorSoft()
                EditorProc.killTBI()
            }
        }
        Given.EDITOROPENED -> {
            try{
                EditorProc.runRF8Editor()
            } catch (e : Exception){
                InstallerProc.uninstallRF()
                InstallerProc.injectRFOTestServer()
                InstallerProc.basicInstall()
                TBIProc.syncSetupRF8Current(UserProc.getUser())
                EditorProc.runRF8Editor()
            }
        }
        Given.CLEANSYSTEM -> {
            try{
                EditorProc.runRF8Editor()
                gL(null, RF.editorW,3)
                sleepy(2.0)
                InstallerProc.uninstallRF()
                InstallerProc.injectRFOTestServer()
            } catch (e : Exception){

            }
        }
        Given.ROBOFORMINSTALLED -> {
            try{
                EditorProc.runRF8Editor()
                gL(null, RF.editorW,3)
                sleepy(2)
                EditorProc.closeRF8EditorSoft()
            } catch ( e : Exception){
                InstallerProc.uninstallRF()
                InstallerProc.injectRFOTestServer()
                InstallerProc.basicInstall()
            }
        }
        Given.DONOTHING-> {
            log("Doing nothing")
        }
    }
    sleepy(5.0)
}
fun performGiven(_given : Given, _rfUser : RFUser){
    when (_given){
        Given.ROBOFORMNOTLAUNCHED -> {
            InstallerProc.uninstallRF()
            InstallerProc.injectRFOTestServer()
            InstallerProc.basicInstall()
            TBIProc.syncSetupRF8Current(_rfUser)
            EditorProc.runRF8Editor()
            EditorProc.closeRF8EditorSoft()
            EditorProc.killTBI()
        }
        Given.EDITOROPENED -> {
            InstallerProc.uninstallRF()
            InstallerProc.injectRFOTestServer()
            InstallerProc.basicInstall()
            TBIProc.syncSetupRF8Current(_rfUser)
            EditorProc.runRF8Editor()
        }
        Given.ROBOFORMINSTALLED -> {
            InstallerProc.uninstallRF()
            InstallerProc.injectRFOTestServer()
            InstallerProc.basicInstall()
            TBIProc.syncSetupRF8Current(_rfUser)

        }
        Given.DONOTHING-> {
            log("Doing nothing")
        }
        else -> {
            logW("Incorrect Given usage with RFUser parameter")
        }

    }
}
fun performAfter(_after : After){
    sleepy(5.0)
    when (_after){
        After.DONOTHING -> {
            sleepy(1)
        }
        After.CLOSEEDITOR -> {
            try{
                gL(null,RF.editorW,1)
                EditorProc.closeRF8EditorSoft()
            } catch (e : Exception){
                log("Editor is not opened")
            }

        }
        After.UNINSTALLROBOFORM -> {
            InstallerProc.uninstallRF()
        }
        After.CLOSEROBOFORM -> {
            EditorProc.closeRF8EditorSoft()
            EditorProc.killTBI()
        }
    }
    sleepy(2.0)
}
