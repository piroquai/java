package jnaex.Run;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;

import jnaex.RFWin.Proc;
import jnaex.RFWin.User.DataTypeStates;
import jnaex.RFWin.User.Servers;
import jnaex.RFWin.User.UserProc;
import jnaex.Tests.Prerelease.TestInstaller.*;
import testLogger.ITest;
import testLogger.Test;
import testLogger.TestSet;


/**
 * Created by Evgenia on 30-May-17.
 */
public class RunInstaller extends RunnerMother{

    public static void main(TestSet set, String[] args) throws Exception {
        /*
        Test Installer - BEGIN
         */

        Proc.setLogSuitePrefix("Install8");

        Test pr121 = new Test("PR121 Local RF8 official to Local RF8");
        performTest(new PR121RF8ULtL(), buf, pr121); //not actual //ok jna
        set.addTest(pr121);

        Test pr122 = new Test("PR122 Synced RF8 official to Synced RF8");
        performTest(new PR122RF8UStS(), buf, pr122); //ok jna
        set.addTest(pr122);

////
//        Test pr134 = new Test("PR134NotDefault");
//        performTest(new PR134NotDefault(), buf, pr134); //blocked by 9112
//        set.addTest(pr134);

        /*
        Test Installer - END
         */


/*
        statToFile(writer); //writing statistics

        writer.close();
*/
    }
}






