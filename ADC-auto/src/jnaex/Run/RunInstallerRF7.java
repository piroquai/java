package jnaex.Run;

import jnaex.RFWin.Proc;
import jnaex.RFWin.User.DataTypeStates;
import jnaex.RFWin.User.Servers;
import jnaex.RFWin.User.UserProc;
import jnaex.Tests.Prerelease.TestInstaller.*;
import testLogger.Test;
import testLogger.TestSet;


/**
 * Created by Evgenia on 30-May-17.
 */
public class RunInstallerRF7 extends RunnerMother{

    public static void main(TestSet set, String[] args) throws Exception {
        /*
        Test Installer - BEGIN
         */
        Proc.setLogSuitePrefix("Install7");

        //TODO: rework and test; user handling should be reviewed
        for (int i = 0; i < 5; i++){
            int j = UserProc.addNewCustomUser(Servers.USSTAGING, DataTypeStates.RF7);
            //UserProc.createSpecificRFUserUsingDesktopRFClient(rfUsers.get(j));

        }
        Test pr111 = new Test("PR111 Local RF7 Desktop to Local RF8");
        performTest(new PR111RF7RF8LtLD(), buf, pr111);
        set.addTest(pr111);

        Test pr112 = new Test("PR112 Local RF7 No-MP to Local RF8");
        performTest(new PR112RF7RF8LtLNoMP(), buf, pr112); //ok jna
        set.addTest(pr112);

        Test pr113 = new Test("PR113 Local RF7 to Local RF8 (Server data Exists)");
        performTest(new PR113RF7RF8LtLSrvExs(), buf, pr113); //ok jna
        set.addTest(pr113);

        Test pr114 = new Test("PR114 Local RF7 to Synced RF8");
        performTest(new PR114RF7RF8LtS(), buf, pr114); //ok jna
        set.addTest(pr114);

        Test pr115 = new Test("PR115 Synced RF7 to Synced RF8");
        performTest(new PR115RF7RF8StS(), buf, pr115); //ok jna
        set.addTest(pr115);


        Test pr132 = new Test("PR132 setup RF8 with non-converted RF7 account");
        performTest(new PR132NotConverted(), buf, pr132); //ok jna
        set.addTest(pr132);

        Test pr141 = new Test("PR141 Downgrade from RF8 to RF7");
        performTest(new PR141Downgrade(), buf, pr141); //ok jna
        set.addTest(pr141);



        /*
        Test Installer - END
         */


/*
        statToFile(writer); //writing statistics

        writer.close();
*/
    }
}






