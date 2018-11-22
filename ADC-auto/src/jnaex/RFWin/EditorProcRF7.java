package jnaex.RFWin;

import daima.DElement;
import jnaex.RFWin.SearchPresets.RF7;

/**
 * Created by Autotester on 4/4/2018.
 */
public class EditorProcRF7 extends Proc{
    public static String nameSNDefault = "nameSN";

    /**
     * Create a safenote with the specified name as an indicator of syncing data RF7
     * @param uncheckPP (default - False). If it is true, "Password-protected" checkbox will be unchecked
     * @return True if creating was successful, False if the error appeared
     * @throws Exception If something bad had happened
     */
    private static Boolean createSafenoteRF7(Boolean uncheckPP) throws Exception{
        lgP = "Create Safenote (RF7)";
        Boolean res;
        log("Running Editor");
        EditorProc.runRF7Editor();
        log("Attaching to Editor window");
        sleepy(5);
        //DElement ssW = gimMeP(null,EditorRF7MW.defMainW,"Editor RF7 window",2,"","na");
        DElement ssW = gL(null, RF7.editorW,2);

        //DElement wrk = gimMe(ssW,EditorRF7MW.safenoteBtn,"Safenotes button");
        DElement wrk = gL(ssW,RF7.safenotesB,3);
        wrk.click();
        sleepy(2);
        log("Clicking Add new Safenote button");
        offsetClick(wrk,65,-5);
        sleepy(2);
        //DElement tWrk = gimMe(ssW,RF7CreateNewSafenote.mainW,"New Safenote dialog");
        DElement tWrk = gL(ssW,RF7.createNewSafenoteW,3);
        //wrk = gimMe(tWrk,RF7CreateNewSafenote.editN,"Safenote name");
        wrk = gL(tWrk,RF7.nameE,1);
        wrk.setEditValue(nameSNDefault);

        sleepy(0.3);
        if (uncheckPP){
            log("Unchecking Password-protected checkbox");
            //wrk = gimMe(tWrk,RF7CreateNewSafenote.cbPP,"Password-protected checkbox");
            wrk = gL(tWrk,RF7.passwordProtectChB,1);
            wrk.click();
            sleepy(1);
        }
        //wrk = gimMe(tWrk,RF7CreateNewSafenote.btnOK,"OK Button");
        wrk = gL(tWrk,RF7.okB,1);
        wrk.click();

        log("Verifying is warning message exists or not");
        try{
            //tWrk = gimMe(ssW,RF7CreateNewSafenote.mainW,"New Safenote dialog");
            tWrk = gL(ssW,RF7.createNewSafenoteW,1);
            //DElement ttWrk = gimMe(tWrk,RF7CreateNewSafenoteWDlg.mainW,"Safenote Warning dialog");
            DElement ttWrk = gL(tWrk,RF7.warningRFW,1);
            //wrk = gimMe(ttWrk,RF7CreateNewSafenoteWDlg.btnCancel,"Cancel button - warning dlg");
            wrk = gL(ttWrk,RF7.cancelB,1);
            wrk.click();
            sleepy(0.5);
            //wrk = gimMe(tWrk,RF7CreateNewSafenote.btnCancel,"Cancel button - create dlg");
            wrk = gL(tWrk,RF7.cancelB,1);
            wrk.click();

            res = false;
        }
        catch(Exception e){
            log("Safenote created successfully");
            res = true;
        }

        EditorProc.closeRF7EditorSoft();

        return res;
    }
    /**
     * Default override for createSafenoteRF7
     */
    private static Boolean createSafenoteRF7() throws Exception{
        return createSafenoteRF7(false);
    }

    /**
     * procedure to test safenote creation
     * @param uncheckPP (default - False). If it is True, "Password-protected" checkbox will be unchecked
     * @throws Exception some exception
     */
    public static void CreateSnCRF7(Boolean uncheckPP) throws Exception{
        Boolean toEv = createSafenoteRF7(uncheckPP);
        lgP = "CreateSnCRF7";
        setLogBlockPrefix("CreateSnCRF7");
        log("Procedure is run with uncheckPP = " + uncheckPP.toString());
        if (toEv){
            log("Safenote was created");
        }
        else
        {
            throw new Exception("Safenote WAS NOT created");
        }
    }
    /**
     * default override for  CreateSnCRF7
     */
    public static void CreateSnCRF7() throws Exception{
        CreateSnCRF7(false);
    }
    /**
     * Turn off sync for RF7
     * @throws Exception if something goes wrong
     */
    public static void syncTurnOffRF7() throws Exception{
        lgP = "Turn sync off (RF7)";
        setLogBlockPrefix("syncTurnOffRF7");
        log("Starting procedure");
        EditorProc.runRF7Editor();
        setLogBlockPrefix("syncTurnOffRF7-II");
        sleepy(2);
        log("Locating RF button");
        //DElement rfEW = gimMeP(null,EditorRF7MW.defMainW,"Editor window",2,"","na");
        DElement rfEW = gL(null,RF7.editorW,2);
        sleepy(1);
        //DElement wrk = gimMe(rfEW,EditorRF7MW.grandRFB,"Grand RoboForm button",2);
        DElement wrk = gL(rfEW,RF7.grandRFB,2);
        log("Clicking");
        wrk.click();

        log("Clicking Sync");
        sleepy(1);

        offsetClick(wrk,10,85);

        log("Clicking Turn off sync");
        sleepy(1);

        offsetClick(wrk, 210, 85+188);

        EditorProc.closeRF7EditorSoft();
        setLogBlockPrefix("syncTurnOffRF7-III");
        log("Procedure complete");
    }

}
