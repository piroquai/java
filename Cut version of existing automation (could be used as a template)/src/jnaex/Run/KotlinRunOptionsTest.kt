package jnaex.Run

import jnaex.RFWin.*
import jnaex.Tests.KotlinOptions.*
import testLogger.KotlinTest
import testLogger.KotlinTestSet
import testLogger.Test
import testLogger.TestSet

/**
 * Created by Autotester on 9/14/2018.
 */
class KotlinRunOptionsTest : KotlinRunnerMother() {
    fun main(set : KotlinTestSet){
        Proc.setLogSuitePrefix("KotlinOptions")

        executeWinCommand(pathfilenameCreateFolders)
        sleepy(2)

        val adSDFLocal = KotlinTest("KtOpt: A&D: Select Data Folder: to Local")
        performTest(ADSetDataFolderToLocal(),adSDFLocal)
        set.addTest(adSDFLocal)

        val adSDFMapped = KotlinTest("KtOpt: A&D: Select Data Folder: to Mapped drive")
        performTest(ADSetDataFolderToMapped(),adSDFMapped)
        set.addTest(adSDFMapped)

        val adSDFRemote = KotlinTest("KtOpt: A&D: Select Data Folder: to Remote")
        performTest(ADSetDataFolderToRemote(),adSDFRemote)
        set.addTest(adSDFRemote)

//        val adBaR = KotlinTest("KtOpt : Backup and Restore")
//        performTest(ADBackupAndRestore(), adBaR)
//        set.addTest(adBaR)
//
//        val adCA = KotlinTest("KtOpt : Change Account")
//        performTest(ADChangeAccount(), adCA)
//        set.addTest(adCA)

    }
}