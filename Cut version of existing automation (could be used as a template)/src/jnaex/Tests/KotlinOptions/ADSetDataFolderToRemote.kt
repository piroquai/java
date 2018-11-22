package jnaex.Tests.KotlinOptions

import jnaex.RFWin.*
import jnaex.RFWin.Kotlin.Enums.OptionsControls
import jnaex.RFWin.Kotlin.Enums.TestEngine.*
import jnaex.RFWin.Kotlin.Enums.DialogActions
import jnaex.RFWin.Proc.setLogStepPrefix
import jnaex.RFWin.User.UserProc
import testLogger.ITest
import testLogger.KotlinITest
import testLogger.KotlinTest

/**
 * Created by Autotester on 9/24/2018.
 */
class ADSetDataFolderToRemote : KotlinITest {
    override fun performTest(test: KotlinTest){
        Proc.setGLP("AccountData:SetDataFolderToRemote")
        Proc.setLogScenarioPrefix("ADSDFRemote")
        var succeed = false
        setLogStepPrefix("Given")
        performGiven(Given.EDITOROPENED)
        try {
            setLogStepPrefix("I")
            interactOption(OptionsControls.ACCOUNTDATAADVANCED,"",false)
            sleepy(1)
            setLogStepPrefix("II")
            interactOption(OptionsControls.SELECTDATAFOLDER,"",false)
            sleepy(1)
            setLogStepPrefix("III")
            handleOptWarning(DialogActions.YES)
            sleepy(1)
            setLogStepPrefix("IV")
            handleBrowseForFolderDialog(optADDataFolderRemote,DialogActions.OK)
            sleepy(8)
            log("Test has been completed")
            succeed = true
            setLogStepPrefix("V")
            log("Reverting to previous data location")
            handleWarningAfterDataFolderChange(true)
            sleepy(1)
            sleepy(5)
            makeDefaultDataFolder()
            setLogStepPrefix("VI")
            deleteEverythingAt(optADDataFolderRemote)
        } catch (e : Exception) {
            setLogStepPrefix("ERROR")
            logE(e.message.orEmpty())
            ITest.uninstallIfError(Proc.preinstallRF)
            deleteEverythingAt(optADDataFolderRemote)
        }
        setLogStepPrefix("After")
        performAfter(After.CLOSEEDITOR)
        test.state = when (succeed){
            true -> TestState.SUCCEED
            else -> TestState.FAILED
        }
    }

}