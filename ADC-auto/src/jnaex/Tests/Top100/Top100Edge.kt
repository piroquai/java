package jnaex.Tests.Top100

import jnaex.RFWin.*
import jnaex.RFWin.Extension.EdgeTestContext
import jnaex.RFWin.Kotlin.Enums.TestEngine.*
import jnaex.RFWin.Extension.TestContext
import jnaex.RFWin.Kotlin.Enums.Browsers
import testLogger.ITest
import testLogger.KotlinITestP
import testLogger.KotlinTest
import java.util.*

/**
 * Created by Autotester on 10/9/2018.
 */
class Top100Edge: KotlinITestP {
    override fun <T: TestContext> performTest(_test: KotlinTest, _testContext: T) {
        Proc.setGLP("EdgeTop100")

        Proc.setLogScenarioPrefix("${_testContext.id}")
        var state : TestState
        performGiven(Given.DONOTHING)
        try {
            log("Start test")
            performLoginTest(Browsers.EDGE,_testContext)
            log("Test has been completed")
            state = TestState.SUCCEED
        } catch (e : Exception) {
            Proc.setLogStepPrefix("ERROR")
            logE(e.message.orEmpty())
            ITest.uninstallIfError(Proc.preinstallRF)
            state = TestState.FAILED
        }
        performAfter(After.DONOTHING)
        _test.state = state
        sendTestResultToMySQLServer(_test, AdditionalTestData(_testContext.id,(_testContext as EdgeTestContext).login, Date(),Browsers.EDGE))
    }

}
