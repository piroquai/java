package jnaex.Run

import jnaex.RFWin.Proc
import jnaex.Tests.KotlinOptions.RFADCLogin
import jnaex.Tests.Prerelease.StaffTests.deepSearchExampleTest
import testLogger.KotlinTest
import testLogger.KotlinTestSet

/**
 * Created by Autotester on 10/5/2018.
 */
class KotlinRunStuff : KotlinRunnerMother() {
    fun main(set : KotlinTestSet){
        Proc.setLogSuitePrefix("KotlinStuff")

        val test = KotlinTest("Login RFADC")
        performTest(RFADCLogin(),test)
        set.addTest(test)
    }
}