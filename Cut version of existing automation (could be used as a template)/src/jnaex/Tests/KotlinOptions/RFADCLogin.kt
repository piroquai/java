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
class RFADCLogin : KotlinITest{
    override fun performTest(test: KotlinTest){
        Proc.setLogScenarioPrefix("ADCLogin")
        var state : TestState
        performGiven(Given.DONOTHING)
        try {
            log("Test begins!")
            Proc.setLogStepPrefix("I")
            loginToRFADC("agolov+fmlc-admin@siber.com","qwerty123456")
            Proc.setLogStepPrefix("II")
            if (checkSettingsWindowAvailability()){
                state = TestState.SUCCEED
            } else {
                state = TestState.FAILED
            }
            log("Test has been completed")

        } catch (e : Exception) {
            Proc.setLogStepPrefix("ERROR")
            logE(e.message.orEmpty())
//            ITest.uninstallIfError(Proc.preinstallRF)
            state = TestState.FAILED
        }
        performAfter(After.DONOTHING)
        test.state = state
    }

}
