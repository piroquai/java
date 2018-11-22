package testLogger

import jnaex.RFWin.Kotlin.Enums.TestEngine.TestState

/**
 * Created by Autotester on 9/21/2018.
 */
class KotlinTestSet (_name : String) {
    private val testSet: MutableList<KotlinTest> = mutableListOf()
    private var name: String? = null
    private var nSuccessful: Int = 0
    private var nFailed: Int = 0
    private var total: Int = 0
    private var totalTime: Long = 0
    init {
        testSet.clear()
        name = _name
    }

    fun addTest(_test: KotlinTest) {
        testSet.add(_test)
        total++
        when (_test.state) {
            TestState.SUCCEED -> nSuccessful++
            else -> nFailed++
            }
        //totalTime += _test.getTimeElapsed()
        totalTime += _test.totalTimeElapsed
    }

    fun getNSuccessful(): Int {
        return nSuccessful
    }

    fun getNFailed(): Int {
        return nFailed
    }

    fun getTotal(): Int {
        return total
    }

    fun getTotalTime(): Long {
        return totalTime
    }

    fun getDebugInfoList(): List<String> {
        val res = mutableListOf<String>()
        if (nFailed != 0) {
            var i = 1
            var cntf = 0
            while (i <= testSet.size && cntf < nFailed) {
                val tmp = testSet[i - 1]
                if (tmp.state != TestState.SUCCEED) {
                    cntf++
                    res.add("=-Test set: $name Test: ${tmp.name} |BEGIN-=")
                    res.addAll(tmp.debugInfo)
                    res.add("=-Test set: $name Test: ${tmp.name} |END-=")
                }
                i++
            }
        }
        return res
    }

    fun getHTMLDebugInfoList(): List<String> {
        val res = mutableListOf<String>()
        if (nFailed != 0) {
            var i = 1
            var cntf = 0
            while (i <= testSet.size && cntf < nFailed) {
                val tmp = testSet[i - 1]
                if (tmp.state != TestState.SUCCEED) {
                    cntf++
                    res.add("=-Test set: $name Test: ${tmp.name} |BEGIN-=")
                    res.addAll(tmp.getHTMLDebugInfo())
                    res.add("=-Test set: $name Test: ${tmp.name} |END-=")
                }
                i++
            }
        }
        return res
    }

    fun getResults(): List<String> {
        val res = mutableListOf<String>()
        if (total == 0) {
            res.add("Test set '$name' has no tests")
        } else {
            res.add("Test set: $name")
            res.add("----------------------------------------")
            for (t in testSet) {
                val rr: String = when (t.state){
                    TestState.SUCCEED -> "++PASSED++"
                    else -> "-!-FAILED-!-"
                }

                var s = ""
                if (t.attempts > 1) s = "  Last attempt: ${t.lastAttemptTimeElapsed}"
                res.add("Name: ${t.name}   Result: $rr   Time elapsed: ${getTimeString(t.totalTimeElapsed)}" + s)
            }
            res.add("----------------------------------------")
            res.add("Total passed: $nSuccessful")
            res.add("Total failed: $nFailed")
            res.add("Total tests: $total")
            res.add("Total time elapsed: " + getTimeString(totalTime))
            res.add("----------------------------------------")
        }
        return res
    }

    fun getHTMLResults(): List<String> {
        val res = mutableListOf<String>()
        if (total == 0) {
            res.add("Test set '$name' has no tests.")
        } else {
            res.add("Test set: $name")
            res.add("----------------------------------------")
            for (t in testSet) {
                val rr: String = when (t.state){
                    TestState.SUCCEED -> "<span class=\"greencolor\">PASSED</span>"
                    else -> "<span class=\"redcolor\">FAILED</span>"
                }
                var atts = ""
                if (t.attempts > 1) {
                    atts = "   Last attempt: ${t.lastAttemptTimeElapsed}   Total attempts: ${t.attempts}"
                }
                res.add("Name: ${t.name}   Result: $rr   Time elapsed: ${getTimeString(t.totalTimeElapsed)}" + atts)
            }
            res.add("----------------------------------------")
            res.add("Total passed: $nSuccessful")
            res.add("Total failed: $nFailed")
            res.add("Total tests: $total")
            res.add("Total time elapsed: " + getTimeString(totalTime))
            res.add("----------------------------------------")
        }
        return res
    }

}
fun getTimeString(_date: Long): String {
    val h = _date / (60 * 60)
    val hA = java.lang.Long.toString(h)
    val m = _date / 60 % 60
    val m1 = java.lang.Long.toString(m / 10)
    val m2 = java.lang.Long.toString(m % 10)
    val s = _date % 60
    val s1 = java.lang.Long.toString(s / 10)
    val s2 = java.lang.Long.toString(s % 10)
    return "$hA:$m1$m2:$s1$s2"
}
