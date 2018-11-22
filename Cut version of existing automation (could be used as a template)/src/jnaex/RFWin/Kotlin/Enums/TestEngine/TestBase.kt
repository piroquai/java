package jnaex.RFWin.Kotlin.Enums.TestEngine

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
        }
        Given.EDITOROPENED -> {
        }
        Given.CLEANSYSTEM -> {
        }
        Given.ROBOFORMINSTALLED -> {
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
        }
        Given.EDITOROPENED -> {
        }
        Given.ROBOFORMINSTALLED -> {
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
        }
        After.UNINSTALLROBOFORM -> {
        }
        After.CLOSEROBOFORM -> {
        }
    }
    sleepy(2.0)
}
