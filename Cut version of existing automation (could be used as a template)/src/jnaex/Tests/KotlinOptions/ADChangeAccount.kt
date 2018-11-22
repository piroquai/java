package jnaex.Tests.KotlinOptions

import jnaex.RFWin.Kotlin.Enums.TestEngine.*
import jnaex.RFWin.Proc
import testLogger.ITest
import java.util.*
import jnaex.RFWin.*
import jnaex.RFWin.SearchPresets.RF
import testLogger.KotlinITest
import testLogger.KotlinTest

/**
 * Created by Autotester on 9/14/2018.
 */
class ADChangeAccount : KotlinITest{
    override fun performTest(test: KotlinTest){
        Proc.setGLP("AccountData:Change Account")
        Proc.setLogScenarioPrefix("ADCA")
        var state : TestState
        performGiven(Given.EDITOROPENED)
        try {
            log("Test begins!")
            sleepy(15)
            gL(null, RF.editorW,5)
            log("Test has been completed")
            state = TestState.SUCCEED
        } catch (e : Exception) {
            Proc.setLogStepPrefix("ERROR")
            logE(e.message.orEmpty())
            ITest.uninstallIfError(Proc.preinstallRF)
            state = TestState.FAILED
        }
        performAfter(After.CLOSEEDITOR)
        test.state = state
    }

}
