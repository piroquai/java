package jnaex.Run;

import jnaex.RFWin.Proc;
import jnaex.Tests.Prerelease.Setup_Settings_Tests.*;
import testLogger.Test;
import testLogger.TestSet;

/**
 * Created by  Evgenia on 20-Sep-17.
 */
public class RunSetupSettings extends RunnerMother{
    public static void main(TestSet set, String[] args) throws Exception {

//        setFilenameDT(); //resetting logfile name to represent current system time and date
//
//        FileWriter writer = new FileWriter(file, true);
//        String br_name = "";
//        writer.write("\n" + "TEST  " + br_name + "  " + new Date(System.currentTimeMillis()) + "\n");

        /*
        Prepare environment
         */
        EnvironmentPreparation.prepare(args, buf);
        buf.clear();

        Proc.setLogSuitePrefix("SyncSett");
        /*
        Setup Settings Tests - BEGIN
         */
        Test pr51 = new Test("5.1 Sync with the same account");
        performTest(new PR51SyncSameAccount(), buf, pr51);
        set.addTest(pr51);

        Test pr52 = new Test("5.2 Sync with the diff account");
        performTest(new PR52SyncDiffrentWithLocal(), buf, pr52);
        set.addTest(pr52);

        Test pr53 = new Test("5.3 Sync with the dif no local account");
        performTest(new PR53PSyncDiffrentWithoutLocal(), buf, pr53);
        set.addTest(pr53);

        Test pr54 = new Test("5.4 Logoff and login to the new account");
        performTest(new PR54LogoffLoginNew(), buf, pr54);
        set.addTest(pr54);

        Test pr55 = new Test("5.5 Logoff and login to the same account");
        performTest(new PR55LogoffLoginSame(), buf, pr55);
        set.addTest(pr55);

        Test pr56 = new Test("5.6 Logoff and login to the diff account");
        performTest(new PR56LogoffLoginDiff(), buf, pr56);
        set.addTest(pr56);

        /*
        Setup Settings Tests - END
         */
        /*
        Clean environment
         */
        EnvironmentPreparation.clean(args, buf);
        buf.clear();
//
//        statToFile(writer); //writing statistics
//
//        writer.close();
    }
}
