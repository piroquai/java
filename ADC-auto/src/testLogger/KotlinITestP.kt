package testLogger

import jnaex.RFWin.Extension.TestContext

/**
 * Created by Autotester on 10/9/2018.
 */
interface KotlinITestP {
    fun <T : TestContext> performTest(_test: KotlinTest, _testContext: T)
}