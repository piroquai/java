package testLogger

import java.util.*
import jnaex.RFWin.Kotlin.Enums.TestEngine.TestState

/**
 * Created by Autotester on 9/21/2018.
 */
class KotlinTest(_name : String){
    val debugInfo: MutableList<String> = mutableListOf()
    var name: String? = null
    private var startDate: Date? = null
    var endDate: Date? = null
    var state: TestState = TestState.NOTCOMPLETED
    var attempts: Int = 0
    var totalTimeElapsed: Long = 0
    var lastAttemptTimeElapsed: Long = 0

    init {
        name = _name
        debugInfo.clear()
        attempts = 0
        start()
    }

    fun reset() {
        debugInfo.clear()
        attempts++
        state = TestState.NOTCOMPLETED
    }

    fun start() {
        startDate = Date()
    }

//    fun stop(_state: TestState) {
//        endDate = Date()
//        state = _state
//    }

//    fun stop() {
//        stop(TestState.SUCCEED)
//    }
    fun stop(){
        endDate = Date()
        lastAttemptTimeElapsed = getTimeElapsed()
        totalTimeElapsed += lastAttemptTimeElapsed
    }
    fun getTimeElapsed(): Long {
        if (state == TestState.NOTCOMPLETED) {
            throw Exception("The test '$name' is not finished yet.")
        }
        return (endDate!!.time - startDate!!.time) / 1000
    }

    fun writeDebugInfo(vararg _text: String) {
        for (h in _text) {
            debugInfo.add(h)
        }
    }

    fun getHTMLDebugInfo(): List<String> {
        val res = mutableListOf<String>()
        for (h in debugInfo) {
            if (h.startsWith("ERROR: ")) {
                res.add("<span class=\"redcolor\">$h</span>")
            } else {
                if (h.startsWith("WARNING: ")) {
                    res.add("<span class=\"orangecolor\">$h</span>")
                } else {
                    res.add(h)
                }
            }
        }
        return res
    }
}