package jnaex.Run
import jnaex.RFWin.Kotlin.Enums.TestEngine.TestProvider
import jnaex.RFWin.Proc
import jnaex.Tests.Top100.RunnerEdge
import testLogger.*

/**
 * Created by Autotester on 10/9/2018.
 */
fun main(args : Array<String>){
    RunAllTests.setParametersJSON()
    startLogging()

    Proc._testProvider = TestProvider.KOTLIN

    val edgeTop100Test = KotlinTestSet("Edge Top-100 test (Kt)")
    addTestSet(edgeTop100Test)
    RunnerEdge().main(edgeTop100Test)

    stopLogging()
    writeLogsHTML()
    System.exit(0)
}