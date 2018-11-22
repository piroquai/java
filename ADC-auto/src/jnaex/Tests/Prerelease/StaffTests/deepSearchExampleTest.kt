package jnaex.Tests.Prerelease.StaffTests

import daima.deepSearch
import daima.deepSearchIndexed
import jnaex.RFWin.*
import jnaex.RFWin.Kotlin.Enums.TestEngine.*
import testLogger.ITest
import testLogger.KotlinITest
import testLogger.KotlinTest

/**
 * Created by Autotester on 10/5/2018.
 */
class deepSearchExampleTest : KotlinITest {
    override fun performTest(test: KotlinTest){
        Proc.setGLP("Sample test")
        Proc.setLogScenarioPrefix("ST")
        var state : TestState
        performGiven(Given.DONOTHING)
        try {
            log("Test begins!")
            if (deepSearchIndexed(/*gL(null,SP("Noklik","n","Taskbar"),1)*/null, SP("Nokl","N","Nokl"),3,1) == null){
                throw Exception("test is failed")
            }
            log("Test has been completed")
            state = TestState.SUCCEED
        } catch (e : Exception) {
            Proc.setLogStepPrefix("ERROR")
            logE(e.message.orEmpty())
            ITest.uninstallIfError(Proc.preinstallRF)
            state = TestState.FAILED
        }
        performAfter(After.DONOTHING)
        test.state = state
    }

}