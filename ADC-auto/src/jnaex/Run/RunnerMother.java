package jnaex.Run;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import jnaex.RFWin.*;
import jnaex.Tests.Prerelease.TestEditor.*;
import testLogger.ITest;
import testLogger.Test;

/**
 * Created by Autotester on 6/21/2017.
 */
public class RunnerMother {
    private static String filename = "LOG" + "_tests.txt";
    static File file = new File(filename);//file
    static Vector<String> buf = new Vector<String>();
    static String[] args;
    static int CountTests = 2;

    /*
     * Introducing Date and time for logs - BEGIN
     */
    private static String logPath = "Log/";


    /**
     * Remove the rest - cuts what was before = and '=' symbol
     * @param s full string (A=B+c)
     * @return cut string (B+c)
     */
    private static String rTR(String s){
        int st = s.indexOf("=") + 1;
        int end = s.length();
        char[] buf = new char[end - st];
        s.getChars(st,end,buf,0);
        return new String(buf);
    }
//    static void setParameters(){
//        File iniF = new File(iniFilename);
//        try{
//            BufferedReader bR = new BufferedReader(new FileReader(iniF));
//            String s;
//            while ((s = bR.readLine())!=null){
//                if (s.contains("=")){
//                    //here we are
//                    if (s.contains("pathRF7Installer=")){
//                        CommonTestBlock.setPathRF7Installer(rTR(s));
//                    }
//                    if (s.contains("pathRF8Installer=")){
//                        CommonTestBlock.setPathRF8Installer(rTR(s));
//                    }
//                    if (s.contains("pathRFOTSInjector=")){
//                        CommonTestBlock.setPathRFOTSInjector(rTR(s));
//                    }
//                    if (s.contains("pathUninstaller=")){
//                        CommonTestBlock.setPathUninstaller(rTR(s));
//                    }
//                    if (s.contains("pathRF7Editor=")){
//                        EditorProc.setPathRF7Editor(rTR(s));
//                    }
//                    if (s.contains("pathRF8Editor=")){
//                        EditorProc.setPathRF8Editor(rTR(s));
//                    }
//                    if (s.contains("userIDSSRF7Prefix=")){
//                        Proc.setUserIDSSRF7Prefix(rTR(s));
//                    }
//                    if (s.contains("nameSSRF7Prefix=")){
//                        Proc.setNameSSRF7Prefix(rTR(s));
//                    }
//                    if (s.contains("emailSSRF7Prefix=")){
//                        Proc.setEmailSSRF7Prefix(rTR(s));
//                    }
//                    if (s.contains("existingUserRF7UserID=")){
//                        Proc.setExistingUserRF7UserID(rTR(s));
//                    }
//                    if (s.contains("existingUserRF7Password=")){
//                        Proc.setExistingUserRF7Password(rTR(s));
//                    }
//                    if (s.contains("emailSSRF8Prefix=")){
//                        Proc.setEmailSSRF8Prefix(rTR(s));
//                    }
//                    if (s.contains("existingUserRF8Email=")){
//                        Proc.setExistingUserRF8Email(rTR(s));
//                    }
//                    if (s.contains("existingUserRF8Password=")){
//                        Proc.setExistingUserRF8Password(rTR(s));
//                    }
//                    if (s.contains("emailExisting=")){
//                        Proc.setEmailExisting(rTR(s));
//                    }
//                    if (s.contains("passwordExisting=")){
//                        Proc.setPasswordExisting(rTR(s));
//                    }
//                    if (s.contains("targetVersionStr=")){
//                        Proc.setTargetVersionStr(rTR(s));
//                    }
//                    if (s.contains("targetVersionStrOfficial=")){
//                        Proc.setTargetVersionStrOfficial(rTR(s));
//                    }
//                    if (s.contains("nameRF7Installer=")){
//                        CommonTestBlock.setNameRF7Installer(rTR(s));
//                    }
//                    if (s.contains("nameRF7InstallerOfficial=")){
//                        CommonTestBlock.setNameRF7InstallerOfficial(rTR(s));
//                    }
//                    if (s.contains("nameRF8Installer=")){
//                        CommonTestBlock.setNameRF8Installer(rTR(s));
//                    }
//                    if (s.contains("nameRF8InstallerOfficial=")){
//                        CommonTestBlock.setNameRF8InstallerOfficial(rTR(s));
//                    }
//                    if (s.contains("extPath=")){
//                        PR73CreateLoginAuto.setExtPath(rTR(s));
//                    }
//                    if (s.contains("extName=")){
//                        PR73CreateLoginAuto.setExtName(rTR(s));
//                    }
//                    if (s.contains("appLTPath=")){
//                        ApplicationProc.setAppLTPath(rTR(s));
//                    }
//                    if (s.contains("appLTExec=")) {
//                        ApplicationProc.setAppLTExec(rTR(s));
//                    }
//                    if (s.contains("appLTExecWinName=")){
//                        ApplicationProc.setAppLTExecWinName(rTR(s));
//                    }
//                    if (s.contains("appLTExecWinCName=")){
//                        ApplicationProc.setAppLTExecWinCName(rTR(s));
//                    }
//                    if (s.contains("appLTBadMarkerPath=")){
//                        ApplicationProc.setAppLTBadMarkerPath(rTR(s));
//                    }
//                    if (s.contains("appLTBadMarkerName=")){
//                        ApplicationProc.setAppLTBadMarkerName(rTR(s));
//                    }
//                    if (s.contains("appLTBadHandlePath=")){
//                        ApplicationProc.setAppLTBadHandlePath(rTR(s));
//                    }
//                    if (s.contains("appLTBadHandleName=")){
//                        ApplicationProc.setAppLTBadHandleName(rTR(s));
//                    }
//                    if (s.contains("appLTGoodMarkerPath=")){
//                        ApplicationProc.setAppLTGoodMarkerPath(rTR(s));
//                    }
//                    if (s.contains("appLTGoodMarkerName=")){
//                        ApplicationProc.setAppLTGoodMarkerName(rTR(s));
//                    }
//                    if (s.contains("appLTLoginFieldPath=")){
//                        ApplicationProc.setAppLTLoginFieldPath(rTR(s));
//                    }
//                    if (s.contains("appLTLoginFieldName=")){
//                        ApplicationProc.setAppLTLoginFieldName(rTR(s));
//                    }
//                    if (s.contains("appLTPasswordFieldPath=")){
//                        ApplicationProc.setAppLTPasswordFieldPath(rTR(s));
//                    }
//                    if (s.contains("appLTPasswordFieldName=")){
//                        ApplicationProc.setAppLTPasswordFieldName(rTR(s));
//                    }
//                    if (s.contains("appLTSubmitPath=")){
//                        ApplicationProc.setAppLTSubmitPath(rTR(s));
//                    }
//                    if (s.contains("appLTSubmitName=")){
//                        ApplicationProc.setAppLTSubmitName(rTR(s));
//                    }
//                    if (s.contains("appLTBadAdditionalHandlePath=")){
//                        ApplicationProc.setAppLTBadAdditionalHandlePath(rTR(s));
//                    }
//                    if (s.contains("appLTBadAdditionalHandleName")){
//                        ApplicationProc.setAppLTBadAdditionalHandleName(rTR(s));
//                    }
//                    if (s.contains("itemTemplatesPath=")){
//                        Proc.setItemTemplatesPath(rTR(s));
//                    }
//
//                }
//            }
//        }catch (Exception e){
//            Proc.logE("Cannot read ini file: " + iniFilename);
//        }
//    }

    /**
     * <p>Setting filename and reassigning file due to the current system date</p>
     */
    static void setFilenameDT(){
    //    setParametersJSON();
        Date d = new Date();
        SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd-hh-mm");

        filename = "LOG_" + fd.format(d) + "_tests.txt";
        file = new File(logPath + filename);
    }
    /*
     * Introducing Date and time for logs - END
     */


    public static void sleepy(double s){  //in seconds
        Proc.sleepy(s);
    }

    private static Process shell;


    /*
     * Running statistics - BEGIN
     */
    /**
     * Successful tests counter
     */
    private static int succT = 0;
    /**
     * Failed tests counter
     */
    private static int failT = 0;
    /**
     * Failed tests vector
     */
    private static Vector<String> failedTests = new Vector<String>();
    /**
     * Successful tests vector [future]
     */
    private static Vector<String> successfulTests = new Vector<String>();;

    /**
     * Method that determines whether the test is successful or not by analyzing the last string
     * @param marker the last test string
     * @return True if the test is successful
     */
    private static boolean isTSuccessful(String marker){
        boolean result = true;
        //'ERROR' + logSeparator (': ' was in the beginning)
        if (marker.indexOf("ERROR" + Proc.logSeparator) == 0){
            result = false;
        }
        return result;
    }

    /**
     * Writing final statistics to the file
     * @param writer
     * @throws IOException
     */
    static void statToFile(FileWriter writer) throws IOException{
        writer.write("<<Test running statistics>>\n");
        writer.write("Successful: " + succT + " Failed: " + failT + "\n");
        if (failT > 0){
            writer.write("Failed tests list:\n");
            for (int i = 0; i < failedTests.size(); i++){
                writer.write(failedTests.elementAt(i) + "\n");
            }
        }
        writer.write("End of the test running statistics\n");
    }

    /*
     * Running statistics - END
     */

    static void bufToFile(Vector<String> _buf, FileWriter _fileWriter) throws IOException{
        bufToFile(_buf,_fileWriter,false);
    }

    /**
     * Analyzing run statistics, starting driver, writing buffer to file, clean buffer
     * @param _buf buffer
     * @param _fileWriter  FileWriter assigned to the log file
     * @param _skipStat if true, current test run will not be analyzed for statistics (default = false)
     * @throws IOException if something bad happens
     */
    static void bufToFile(Vector<String> _buf, FileWriter _fileWriter, Boolean _skipStat) throws IOException {
        {
            // statistics - BEGIN
            if (!_skipStat){
                if (isTSuccessful(buf.elementAt(buf.size() - 1))){
                    succT++;
                    successfulTests.add(buf.elementAt(0));
                    buf.add("<<Test completed successfully>>");
                }
                else
                {
                    failT++;
                    failedTests.add((buf.elementAt(0)));
                    buf.add(">>Test failed<<");
                }
            }
            //statistics - END

            for (int i = 0; i < buf.size(); ++i) {
                _fileWriter.write(buf.elementAt(i) + "\n");
            }

            buf.clear();
        }
    }
    static void bufToTest(Vector<String> _buf, Test _test){
        for (String st : _buf){
            _test.writeDebugInfo(st);
        }
        if (isTSuccessful(buf.elementAt(buf.size() - 1))){
            _test.stop();
        } else {
            _test.stop(false);
        }
        buf.clear();
    }
    public static void performTest(ITest _met, Vector<String> _buf, Test _watcher){
        int att = 1;
        _met.performTest(buf);
        bufToTest(buf,_watcher);
        while (!(_watcher.isSuccessful()) && (att < 3)) {
            _watcher.reset();
            buf.clear();
            _met.performTest(buf);
            bufToTest(buf,_watcher);
            att++;
        }
    }

}
