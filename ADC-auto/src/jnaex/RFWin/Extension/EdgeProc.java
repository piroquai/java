package jnaex.RFWin.Extension;

import daima.DElement;
import daima.KeyboardHandler;
import jnaex.RFWin.LSURLP;
import jnaex.RFWin.Proc;
import jnaex.RFWin.SP;
import jnaex.RFWin.SearchPresets.MSEdge;
import jnaex.RFWin.SearchPresets.RF;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;

import java.io.*;
import java.util.Vector;


/**
 * Created by Autotester on 4/12/2018.
 */
public class EdgeProc extends Proc{
    public static DElement getEdgeWindow() throws Exception{

        return gL(null, MSEdge.edgeWindow,2);
    }
    public static void openRFPopup() throws Exception{
        DElement eW = getEdgeWindow();
        eW.setForeground();
        eW = gL(eW,MSEdge.edgeWindowSub,2);
        DElement wrk;
        try{
            wrk = gL(eW,MSEdge.browserActionL,2);
            wrk = gL(wrk,MSEdge.rfExtensionB,2);
        } catch (Exception e){
            wrk = gL(eW,MSEdge.rfExtensionB,2);
        }

        wrk.click();
        sleepy(1);
    }
    public static DElement getRFPopupWindow() throws Exception{
        DElement res = getEdgeWindow();
        res = gL(res,MSEdge.edgeWindowSub,2);
        res = gL(res,MSEdge.closeB,2);
        res = gL(res,MSEdge.rfP,2);
        return res;
    }
    public static void searchForItem(String _itemName,DElement _edgePopup) throws Exception{
        DElement sbox = gL(_edgePopup,MSEdge.searchS,2);
        sbox.click();
        KeyboardHandler.sendKeysHere(_itemName);
    }
    public static void assertSite(boolean _strict, String _site) throws Exception{
        Proc.setLogBlockPrefix("assertSite");
        DElement eW = getEdgeWindow();
        eW.setForeground();
        sleepy(0.5);
        eW = gL(eW,MSEdge.edgeWindowSub,2);
        eW = gL(eW,MSEdge.addressE,2);
        String text = eW.getEditText();
        if (_strict){
            if (text.equals(_site)){
                log("assert(strict,\"" + _site + "\") == true");
            } else {
                throw new Exception("Assert failed - the current site \"" + text + "\" is not equal to \"" + _site + "\"");
            }
        } else {
            if (text.contains(_site)){
                log("assert(non-strict,\"" + _site + "\") == true");
            } else {
                throw new Exception("Assert failed - the current site \"" + text + "\" does not contain \"" + _site + "\"");
            }
        }
    }
    public static void checkMarkers(DElement _parent, Vector<SP> _markers) throws Exception{
        log("Starting CheckMarkers procedure");
        for (SP z : _markers){
            gL(_parent, z, 2);
        }
        log("CheckMarkers has been completed without exceptions");
    }
    public static DElement getSearchResultItem(String _itemName,String _itemType) throws Exception{
        DElement res = gL(null,MSEdge.searchListP,2);
        res = gL(res,MSEdge.commonL,2);
        String startingSequence = "";
        String endingSequence = "";
        if (_itemType.equals("Login")){
            startingSequence = "Login to '";
            endingSequence = "'";
        }
        if (_itemType.equals("Bookmark")){
            startingSequence = "Go to '";
            endingSequence = "'";
        }
        if (_itemType.equals("Safenote")){
            startingSequence = "Edit '";
            endingSequence = "'";
        }
        if (_itemType.equals("SearchBing")){
            startingSequence = "Search '";
            endingSequence = "' in Bing";
        }
        if (_itemType.equals("SearchRoboForm Files")){
            startingSequence = "Search '";
            endingSequence = "' in RoboForm Files";
        }
        if (_itemType.equals("SearcheBay")){
            startingSequence = "Search '";
            endingSequence = "' in eBay";
        }
        String total = startingSequence + _itemName + endingSequence;
        if (_itemType.equals("")){
            total = "(Customize Search Box)";
        }
        return gL(res,total,2,"nl",total,"list item");
    }
    public static void performLoginFromContext(DElement _targetItem,boolean _theSame) throws Exception{
        Proc.setLogBlockPrefix("performLoginFromContext");

        _targetItem.contextClickRECTLeftTopOffscreen(30,10);
        //_targetItem.contextClick();
        DElement wrk = gL(null,MSEdge.contextM,2);

        if (_theSame) {
            wrk = gL(wrk,MSEdge.logInTheSameWindowMI,2);
        } else {
            wrk = gL(wrk,MSEdge.logInNewWindowMI,2);
        }
        wrk.click();
    }
    public static DElement getSitePane() throws Exception{
        DElement wrk = getEdgeWindow();
        wrk.setForeground();
        wrk = gL(wrk, MSEdge.edgeWindowSub,2);
//        wrk = gL(wrk, MSEdge.extensionsG,2);
//        wrk = gL(wrk, MSEdge.commonG,2);
//        wrk = gL(wrk, MSEdge.commonP,2);
        return wrk;
    }
    public static void clearCookies() throws Exception{
        Proc.setLogBlockPrefix("clearCookies");
        DElement edgeWT = getEdgeWindow();
        edgeWT.setForeground();
        DElement edgeW = gL(edgeWT,MSEdge.edgeWindowSub,2);
        DElement wrk = gL(edgeW,MSEdge.threeDotsB,2);
        wrk.click();
        sleepy(2);
        wrk = gL(edgeW,MSEdge.edgePopupW,2);
        wrk = gL(wrk,MSEdge.commonP,2);
        wrk = gL(wrk,MSEdge.settingsMI,2);
        wrk = gL(wrk,MSEdge.settingsT,2);
        wrk.click();
        sleepy(2);
        edgeW = gL(edgeWT,MSEdge.edgeWindowSub,2);
        //
        //wrk = gL(edgeW,MSEdge.settingsG,"",1,"!");
        wrk = gL(edgeW,MSEdge.settingsW,2);
//        wrk = gL(wrk,MSEdge.settingsW,2);
        wrk = gL(wrk,MSEdge.commonP,2);
        wrk = gL(wrk,MSEdge.chooseWTCB,2);
        wrk.click();
        sleepy(2);
        edgeW = gL(edgeWT,MSEdge.edgeWindowSub,2);
        //wrk = gL(edgeW,MSEdge.extensionsG,2);
        wrk = gL(edgeW,MSEdge.settingsW,2);
        //wrk = gL(wrk,MSEdge.settingsW,2);
        wrk = gL(wrk,MSEdge.commonP,2);
        wrk = gL(wrk,MSEdge.clearB,2);
        wrk.click();
        sleepy(10);
        wrk.click();
        sleepy(5);
        edgeW = gL(edgeWT,MSEdge.edgeWindowSub,2);
        wrk = gL(edgeW,MSEdge.threeDotsB,2);
        wrk.click();
    }
    public static void closeWarnings(DElement _MSEdgeMainWindow) throws Exception{
        Proc.setLogBlockPrefix("closeWarnings");

        boolean warningIsShown = false;
        try{
            DElement wtc = gL(_MSEdgeMainWindow,MSEdge.RFNotificationW,2);
            warningIsShown = true;
            wtc = gL(wtc,MSEdge.OKB,2);
            wtc.click();
            sleepy(1);
        } catch (Exception e) {
            if (!warningIsShown){
                log("Warnings did not appear");
            } else {
                //I believe if this exception is ever risen, additional window handle would be required.
                throw new Exception("Warning handle exception");
            }

        }
    }
    public static void clickMatching(DElement _RFpopup,String _item) throws Exception{
        DElement match = gL(_RFpopup,new SP(_item,"n",_item),2);
        match.click();
    }
    public static void performGoToFromContext(DElement _targetItem) throws Exception{
        Proc.setLogBlockPrefix("performGoToFromContext");
        _targetItem.contextClickRECTLeftTopOffscreen(30,10);
        //_targetItem.contextClick();
        DElement wrk = gL(null,MSEdge.contextM,2);

        wrk = gL(wrk,MSEdge.goToMI,2);
        wrk.click();
    }

    private static final String tempDB = "tempdb.mydb";
    private static Vector<dbObj> localDB;


    private static void readDB() throws Exception{
        FileInputStream fstream = new FileInputStream(tempDB);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String testContext;
        localDB = new Vector<>();

        while ((testContext = br.readLine()) != null) {
            localDB.add(new dbObj(testContext));
        }

    }
    private static void writeDB() throws Exception{
        File file = new File(tempDB);

        FileWriter writer = new FileWriter(file, false);

        for (int i = 0; i < localDB.size(); i++) {
            writer.write(localDB.elementAt(i).getString() + "\n");
        }
        writer.close();

    }
    private static void dbInc(int _index, String _URL, boolean _goodAtt){
        boolean f = false;
        int i = 0;
        if (localDB.isEmpty()){
            localDB.add(new dbObj(_index,_URL));
        } else {
            do{
                dbObj o = localDB.get(i);
                if (o.getID() == _index) {
                    f = true;
                }
                i++;
            } while (!(f) && (i < localDB.size()));
            if (!(f)){
                localDB.add(new dbObj(_index,_URL));
            } else {
                i--;
            }
        }

        dbObj o = localDB.get(i);
        if (_goodAtt){
            o.incGoodAtt();
        } else {
            o.incBadAtt();
        }
    }

    public static void performTop100Test(Vector<LSURLP> _testData) throws Exception{
        //.us agolov+testtop100@siber.com qwerty123456
        lgP = "PerformTop100Test";
        Proc.setLogBlockPrefix("performTop100Test");
        //clearCookies();

        //load db
        try{
            readDB();
        } catch (Exception e){
            localDB = new Vector<>();
        }

        int totalres = 0;
        for (LSURLP test : _testData){
            try{
                DElement rfP;
                try{
                    rfP = getRFPopupWindow();
                } catch (Exception e){
                    openRFPopup();
                    rfP = getRFPopupWindow();
                }
                DElement parent = gL(null,MSEdge.edgeWindow,2);
                searchForItem(test.login,rfP);
                if (test.preActionRequired){
                    performGoToFromContext(getSearchResultItem(test.login,"Login"));
                    Proc.setLogBlockPrefix("performTop100Test-Cycle-C");
                    sleepy(6);
                    DElement wrk = getSitePane();
                    for (SP z : test.preActionElementToClick){
                        wrk = gL(wrk,z,3);
                    }
                    wrk.click();
                    sleepy(6);

                    if (test.preActionRequired2){
                        wrk = getSitePane();
                        for (SP z : test.preActionElementToClick2){
                            wrk = gL(wrk,z,3);
                        }
                        wrk.click();
                        sleepy(6);
                    }

                    openRFPopup();
                    rfP = getRFPopupWindow();
                    clickMatching(rfP,test.login);
                    sleepy(15);
                } else {
                    performLoginFromContext(getSearchResultItem(test.login,"Login"),true);
                    Proc.setLogBlockPrefix("performTop100Test-Cycle-B");
                    sleepy(20);
                }
                closeWarnings(parent);
                assertSite(test.strictURL,test.URL);
                Proc.setLogBlockPrefix("performTop100Test-Cycle-A");

                if (!(test.markers.isEmpty())){
                    checkMarkers(getSitePane(),test.markers);
                }
                test.setAsCorrect();
                dbInc(test.testID,test.URL,true);
            } catch (Exception e){
                logE(e.getMessage());
                test.setAsFailed();
                dbInc(test.testID,test.URL,false);
            }
            log("Test of Login " + test.login + ": " + test.testResult);
            if (test.getIntResult() != -1){
                totalres += test.getIntResult();
            }
            sleepy(1);
            openRFPopup();
            sleepy(2);
            openRFPopup();
        }
        writeDB();
        if (totalres == 0){
            log("All tests were successful");
        } else {
            logE(totalres + " of " + _testData.size() + " tests are failed");
            logE("Full list of tests:");

            for (LSURLP item : _testData){
                if (item.getIntResult() == 0) {
                    log(item.login + " [" + item.URL + "] - " + item.testResult);
                }
            }
            logE("================================================");

            for (LSURLP item : _testData){
                if (item.getIntResult() != 0) {
                    logE(item.login + " [" + item.URL + "] - " + item.testResult);
                }
            }
        }
    }
}
class dbObj{
    private int id;
    public int getID(){
        return id;
    }
    private int goodatt;
    public int goodatt(){
        return goodatt;
    }
    public void incGoodAtt(){
        goodatt++;
    }
    private int badatt;
    public int getBadAtt(){
        return badatt;
    }
    public void incBadAtt(){
        badatt++;
    }
    private String URL;
    public String getURL(){
        return URL;
    }
    private static final char logseparator = ';';
    dbObj(String str) throws Exception{
        String tmpps = str;
        id = Integer.parseInt(Proc.getStringToTheLeftOfTheChar(tmpps,logseparator));
        tmpps = Proc.getStringToTheRightOfTheChar(tmpps,logseparator);
        goodatt = Integer.parseInt(Proc.getStringToTheLeftOfTheChar(tmpps,logseparator));
        tmpps = Proc.getStringToTheRightOfTheChar(tmpps,logseparator);
        badatt = Integer.parseInt(Proc.getStringToTheLeftOfTheChar(tmpps,logseparator));
        URL = Proc.getStringToTheRightOfTheChar(tmpps,logseparator);
    }
    dbObj(int newID, String newURL){
        id = newID;
        goodatt = 0;
        badatt = 0;
        URL = newURL;
    }
    public String getString(){
        return Integer.toString(id) + logseparator + Integer.toString(goodatt) + logseparator +
                Integer.toString(badatt) + logseparator + URL;
    }

}

