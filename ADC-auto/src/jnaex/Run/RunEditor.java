package jnaex.Run;

import jnaex.RFWin.Proc;
import jnaex.Tests.Prerelease.TestEditor.*;
import jnaex.Tests.Prerelease.TestInstaller.PR111RF7RF8LtLD;
import testLogger.Test;
import testLogger.TestSet;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Autotester on 7/27/2017.
 */
public class RunEditor extends RunnerMother{
    public static void main(TestSet set, String[] args) throws Exception {
        /*
        Prepare environment
         */
        EnvironmentPreparation.prepare(args, buf);
//        bufToFile(buf,writer, true);
        buf.clear();
        /*
        Test Editor - BEGIN
         */
        Proc.setLogSuitePrefix("Editor");

        Test pr71 = new Test("PR71 Logoff and Login");
        performTest(new PR71LogoffLogin(), buf, pr71);
        set.addTest(pr71);


        Test pr72 = new Test("PR72 Change Account After Logoff");
        performTest(new PR72ChangeAccAfterLogoff(), buf, pr72); //ok jna
        set.addTest(pr72);

        //Create login:
        Test pr73 = new Test("PR73 Create Login [Chrome browser]");
        performTest(new PR73CreateLoginAuto(), buf, pr73); //ok jna
        set.addTest(pr73);

        Test pr74 = new Test("PR74 Create Bookmark [FF browser]");
        performTest(new PR74CreateBookmark(), buf, pr74); //ok jna
        set.addTest(pr74);

        Test pr75 = new Test("PR75 Create Application");
        performTest(new PR75CreateApplication(), buf, pr75); //ok jna
        set.addTest(pr75);

        Test pr76 = new Test("PR76 Create Identity");
        performTest(new PR76CreateIdentity(), buf, pr76); //ok jna
        set.addTest(pr76);

        Test pr77 = new Test("PR77 Create Contact");
        performTest(new PR77CreateContact(), buf, pr77); //ok jna
        set.addTest(pr77);

        Test pr78 = new Test("PR78 Create Safenote");
        performTest(new PR78CreateSafenote(), buf, pr78); //ok jna
        set.addTest(pr78);

        Test pr7c = new Test("PR7C Edit Identity");
        performTest(new PR7CEditIdentity(), buf, pr7c); //ok jna
        set.addTest(pr7c);

        Test pr7d = new Test("PR7D Edit Contact");
        performTest(new PR7DEditContact(), buf, pr7d); //ok jna
        set.addTest(pr7d);

        Test pr7e = new Test("PR7E Edit Safenote");
        performTest(new PR7EEditSafenote(), buf, pr7e); //ok jna
        set.addTest(pr7e);

        /*
        Test Editor - END
         */
        /*
        Clean environment
         */
//        EnvironmentPreparation.clean(args, buf);
//        bufToFile(buf,writer, true);

//        statToFile(writer); //writing statistics
//
//        writer.close();


    }

}
