package jnaex.Run

import jnaex.RFWin.Extension.TestContext
import jnaex.RFWin.Kotlin.Enums.TestEngine.TestState
import jnaex.RFWin.Proc
import testLogger.KotlinITest
import testLogger.KotlinITestP
import testLogger.KotlinTest
import java.util.*

/**
 * Created by Autotester on 9/14/2018.
 */

open class KotlinRunnerMother{
    private val maxAttempts = 3
    var buf = Vector<String>()

    fun performTest(_met: KotlinITest, _watcher: KotlinTest) {
        testLogger.currentKotlinTest = _watcher
        while ((_watcher.state != TestState.SUCCEED) && (_watcher.attempts < maxAttempts)) {
            _watcher.reset()
            executeTest(_met,_watcher)
        }
    }
    private fun executeTest(_met: KotlinITest,_watcher: KotlinTest){
        _watcher.start()
        _met.performTest(_watcher)
        _watcher.stop()
    }

    fun <T : TestContext> performTestP(_met: KotlinITestP, _watcher: KotlinTest, _parameter : T){
        testLogger.currentKotlinTest = _watcher
        while ((_watcher.state != TestState.SUCCEED) && (_watcher.attempts < maxAttempts)) {
            _watcher.reset()
            executeTest(_met,_watcher,_parameter)
        }
    }
    private fun <T : TestContext> executeTest(_met: KotlinITestP,_watcher: KotlinTest,_parameter : T){
        _watcher.start()
        _met.performTest(_watcher,_parameter)
        _watcher.stop()
    }

}