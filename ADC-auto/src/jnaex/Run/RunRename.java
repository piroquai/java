package jnaex.Run;

import jnaex.RFWin.Proc;
import jnaex.Tests.Prerelease.Rename_SendSharing_Files.*;
import testLogger.Test;
import testLogger.TestSet;

/**
 * Created by  Evgenia on 20-Sep-17.
 */
public class RunRename extends RunnerMother{
    public static final boolean custom = false;
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

        Proc.setLogSuitePrefix("Rename");
        /*
        Setup Settings Tests - BEGIN
         */
        Test rename1 = new Test("S rename untill R receive");
        performTest(new C11Rename_Send_Until_Receive(), buf, rename1);
        set.addTest(rename1);

        Test rename2 = new Test("S rename after R receive");
        performTest(new C12Rename_Send_After_Receive(), buf, rename2);
        set.addTest(rename2);

        Test rename3 = new Test("R rename after receive");
        performTest(new C13Rename_Send_By_Receiver(), buf, rename3);
        set.addTest(rename3);

        Test rename4 = new Test("Rename by Sender until Receiver receive");
        performTest(new C21Rename_Sharedfile_Until_Receive(), buf, rename4);
        set.addTest(rename4);

        Test rename5 = new Test("Rename by Sender after Receiver receive");
        performTest(new C22Rename_Sharedfile_After_Receive(), buf, rename5);
        set.addTest(rename5);

        Test rename6 = new Test("Rename by Receiver after receiving");
        performTest(new C23Rename_Sharedfile_By_Receiver(), buf, rename6);
        set.addTest(rename6);

        Test rename7 = new Test("Rename by Owner Sender");
        performTest(new C31Rename_by_Owner_Sender(), buf, rename7);
        set.addTest(rename7);

        Test rename8 = new Test("Rename by Owner Receiver");
        performTest(new C32Rename_by_Owner_Receiver(), buf, rename8);
        set.addTest(rename8);

        Test rename9 = new Test("Rename by Regular receiver");
        performTest(new C33Rename_by_Regular_Receiver(), buf, rename9);
        set.addTest(rename9);

        Test renameA = new Test("Rename by Limited Receiver");
        performTest(new C34Rename_by_Limited_Receiver(), buf, renameA);
        set.addTest(renameA);

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
