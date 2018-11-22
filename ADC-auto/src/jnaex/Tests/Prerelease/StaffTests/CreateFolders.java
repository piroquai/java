package jnaex.Tests.Prerelease.StaffTests;

import jnaex.RFWin.EditorProc;
import jnaex.RFWin.Proc;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import daima.DElement;
/**
 * Created by Evgenia on 28-Jun-17.
 */
public class CreateFolders {

    public static void sleepy(double s){  //in seconds
        Proc.sleepy(s);
    }
    public static void log(String s){ //normal log
        Proc.log(s);
    }
    public static void logE(String s){ //error log
        Proc.logE(s);
    }

    public static void main(String[] args , Vector<String> buf) throws  IOException, MalformedURLException {

        Proc.setBuf(buf);
        Proc.setLogScenarioPrefix("CreaFold");
        log("Create Folders");
        try {
            /**1*/
            Proc.setLogStepPrefix("I");
            EditorProc.runRF8Editor();
            sleepy(2);

            /**2*/
            Proc.setLogStepPrefix("II");
            EditorProc.createFolders(98, "testF");
            sleepy(3);

            /**3*/
            Proc.setLogStepPrefix("III");
            EditorProc.closeRF8EditorSoft();
        }
        catch (Exception ex){
            logE("Unable to start process:" + ex.getMessage());
        }
        buf = Proc.getBuf();
    }
}
