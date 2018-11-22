package jnaex.Run;

import jnaex.RFWin.Proc;
import jnaex.Tests.Prerelease.SendSharingTests.*;
import testLogger.Test;
import testLogger.TestSet;

/**
 * Created by Evgenia on 30-May-17.
 */
public class RunSS extends RunnerMother{

    public static void main(TestSet set, String[] args) throws Exception {
        /*
        Prepare environment
         */
        EnvironmentPreparation.prepare(args, buf);
        buf.clear();

        Proc.setLogSuitePrefix("SharSend");
        /*
        Send&Sharing Tests - BEGIN
         */

        Test pr81 = new Test("PR81 Create Shared Folder");
        performTest(new PR81CreateSharedFolder(), buf, pr81);
        set.addTest(pr81);

        Test pr82 = new Test("PR82 Share Folder MR");
        performTest(new PR82ShareFolderMR(), buf, pr82);
        set.addTest(pr82);

        Test pr83 = new Test("PR83 Share Folder RR");
        performTest(new PR83ShareFolderRR(), buf, pr83);
        set.addTest(pr83);

        Test pr84 = new Test("PR84 Share Folder LR");
        performTest(new PR84ShareFolderLR(), buf, pr84);
        set.addTest(pr84);

        Test pr85 = new Test("PR85 Accept shared folder");
        performTest(new PR85AcceptSharedFolder(), buf, pr85);
        set.addTest(pr85);

        Test pr86 = new Test("PR86 Reject shared folder");
        performTest(new PR86RejectSharedFolder(), buf, pr86);
        set.addTest(pr86);

        Test pr87 = new Test("PR87 Sharing file");
        performTest(new PR87SharingFile(), buf, pr87);
        set.addTest(pr87);

        Test pr88 = new Test("PR88 Accept shared file");
        performTest(new PR88AcceptSharedFile(), buf, pr88);
        set.addTest(pr88);

        Test pr89 = new Test("PR89 Reject shared file");
        performTest(new PR89RejectSharedFile(), buf, pr89);
        set.addTest(pr89);

        Test pr8A = new Test("PR8A Send file with sync");
        performTest(new PR8ASendFileSync(), buf, pr8A);
        set.addTest(pr8A);

        Test pr8B = new Test("PR8B Accept file with sync");
        performTest(new PR8BAcceptFileWithSync(), buf, pr8B);
        set.addTest(pr8B);

        Test pr8C = new Test("PR8C Send file without sync");
        performTest(new PR8CSendFileWithoutSync(), buf, pr8C);
        set.addTest(pr8C);

        Test pr8D = new Test("PR8D Accept file without sync");
        performTest(new PR8DAcceptFileWithoutSync(), buf, pr8D);
        set.addTest(pr8D);

        /*
        Send&Sharing Tests - END
         */
        /*
        Clean environment
         */
        //EnvironmentPreparation.clean(args, buf);
        //buf.clear();
    }
}






