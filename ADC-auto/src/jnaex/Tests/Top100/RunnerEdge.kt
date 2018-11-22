package jnaex.Tests.Top100

import jnaex.RFWin.*
import jnaex.RFWin.Extension.EdgeTestContext
import jnaex.Run.KotlinRunnerMother
import testLogger.KotlinTest
import testLogger.KotlinTestSet

/**
 * Created by Autotester on 10/9/2018.
 */
class RunnerEdge : KotlinRunnerMother() {
    fun main(set : KotlinTestSet){
        Proc.setLogSuitePrefix("EdgeT100")
        val toTestList : List<EdgeTestContext> = getTestListEdge(BrowserTestBlock.top100Common)

        if (toTestList.isEmpty()) return
        val testList : MutableList<KotlinTest> = mutableListOf()
        for (toTest in toTestList){
            testList.add(KotlinTest("EdgeTop100:ID:${toTest.id}"))
            performTestP(Top100Edge(),testList.last(),toTest)
            set.addTest(testList.last())

        }

    }
}