package jnaex.Run;

import jnaex.RFWin.Proc;
import testLogger.Test;
import testLogger.TestSet;

/**
 * Created by Evgenia on 28-Jun-17.
 */
public class RunStaff extends RunnerMother {

    public static void main(TestSet set, String[] args) throws Exception {

//        setFilenameDT(); //resetting logfile name to represent current system time and date
//
//        FileWriter writer = new FileWriter(file, true);
//        String br_name = "";
//        writer.write("\n" + "TEST  " + br_name + "  " + new Date(System.currentTimeMillis()) + "\n");

        //EnvironmentPreparation.prepare(args, buf);
        //buf.clear();

        Proc.setLogSuitePrefix("Staff");

        //        Test pr81 = new Test("CreateTonsOfSharedFiles");
//        performTest(new CreateTonsOfSharedFiles(), buf, pr81);
//        set.addTest(pr81);

//        Test amazon = new Test("AmazonWorkspacesTest");
//        performTest(new AmazonWorkspacesTest(), buf, amazon);
//        set.addTest(amazon);

//        Test ipblock = new Test("IP blocking by accessing from different incorrect credentials");
//        performTest(new ServerBadauthTestUI(), buf, ipblock);
//        set.addTest(ipblock);

//        Test ipblock1 = new Test("IP blocking by password resets");
//        performTest(new ServerErasePwdTestUI(), buf, ipblock1);
//        set.addTest(ipblock1);

//        Test edgeTop100 = new Test("Edge top-100 sites login");
//        performTest(new EdgeTop100(),buf,edgeTop100);
//        set.addTest(edgeTop100);

        //Test clearAcc = new Test("Clear current account");
       //performTest(new CleanCurrentRFAccount(),buf,clearAcc);
        //set.addTest(clearAcc);


//        EnvironmentPreparation.clean(args, buf);
//        buf.clear();


        /*
        CF - BEGIN
         */
//        Test clb = new Test("CreateLB");
//        CreateLB.main(args, buf);
//        bufToTest(buf,clb);
//        set.addTest(clb);

////        bufToFile(buf,writer);

        /*
        CF Tests - END
         */


//        statToFile(writer); //writing statistics
//
//        writer.close();
    }
}
