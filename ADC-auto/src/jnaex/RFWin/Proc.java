package jnaex.RFWin;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jna.platform.win32.WinDef;
import daima.DElement;
import daima.KeyboardHandler;
import daima.MouseHandler;
import jnaex.RFWin.Kotlin.Enums.TestEngine.TestProvider;
import jnaex.RFWin.SearchPresets.MSWindowsGeneral;
import jnaex.RFWin.SearchPresets.MozillaFF;
import jnaex.RFWin.User.DataTypeStates;
import jnaex.RFWin.User.Servers;
import jnaex.RFWin.User.UserProc;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import testLogger.KotlinTestLoggerKt;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;


/**
 * Process handling jueton
 */
public class Proc {
    public static TestProvider _testProvider = TestProvider.JAVA;

    protected static String getStringExcludingChar(String _s, char _c){
        int st = 0;
        int end = _s.indexOf(_c);
        char[] buf = new char[end - st];
        _s.getChars(st,end,buf,0);
        String res = new String(buf);
        if (end < _s.length() - 1) {
            int st1 = end + 1;
            int end1 = _s.length() - 1;
            char[] buf1 = new char[end1 - st1];
            _s.getChars(st1,end1,buf1,0);
            res = res + new String(buf1);
        }
        return res;
    }
    public static String getStringToTheLeftOfTheChar(String _s, char _c) throws Exception{
        if (!(_s.contains(""+_c))){
            throw new Exception("This string does not contain this char");
        }
        int st = 0;
        int end = _s.indexOf(_c);
        char[] buf = new char[end - st];
        _s.getChars(st,end,buf,0);
        return new String(buf);
    }
    public static String getStringToTheRightOfTheChar(String _s, char _c) throws Exception{
        if (!(_s.contains(""+_c))){
            throw new Exception("This string does not contain this char");
        }
        //TODO: test
        int st = _s.indexOf(_c) + 1;
        if (st >= _s.length()) {
            return _s;
        }
        int end = _s.length();
        char[] buf = new char[end - st];
        _s.getChars(st,end,buf,0);
        return new String(buf);
    }

//    /**
//     * Internal method to unify gL and gNL methods. Expandable
//     * @param _searchFlag 0 for gL, 1 for gNL
//     * @param _parent parent (as usual)
//     * @param _preset Search Preset
//     * @param _ignoredXattrList ignored attributes
//     * @param _attempts attempts (as usual)
//     * @param _index ignored for gL, can set any number
//     * @param _XattrList additional enforced search attributes declaration list
//     * @param _Xattrs additional enforced search attributes
//     * @return desired DElement
//     */
//    private static DElement getDElementForGL(int _searchFlag, DElement _parent, SP _preset, String _ignoredXattrList, int _attempts, int _index, String _XattrList, String..._Xattrs) throws Exception{
//        String totalXattrList1 = _preset.defaultSearchString;
//        if (!(_ignoredXattrList.isEmpty())) {
//            for (int i = 0; i < _ignoredXattrList.length(); i++){
//                char zzz = _ignoredXattrList.charAt(i);
//                while (totalXattrList1.contains("" + zzz)){
//                    totalXattrList1 = getStringExcludingChar(totalXattrList1,zzz);
//                }
//            }
//        }
//        if (!(_XattrList.isEmpty())) {
//            for (int i = 0; i < _XattrList.length(); i++){
//                char zzz = _XattrList.charAt(i);
//                while (totalXattrList1.contains("" + zzz)){
//                    totalXattrList1 = getStringExcludingChar(totalXattrList1,zzz);
//                }
//            }
//        }
//        Vector<String> Rattrs = new Vector<>();
//        if (!(totalXattrList1.isEmpty())){
//            for (int i = 0; i < totalXattrList1.length(); i++){
//                char zzz = totalXattrList1.charAt(i);
//                switch (zzz){
//                    case 'a': Rattrs.add(_preset.automationID); break;
//                    case 'A': Rattrs.add(_preset.automationID); break;
//                    case 'c': Rattrs.add(_preset.className); break;
//                    case 'C': Rattrs.add(_preset.className); break;
//                    case 'e': Rattrs.add(_preset.isEnabled); break;
//                    case 'h': Rattrs.add(_preset.hasKeyboardFocus); break;
//                    case 'k': Rattrs.add(_preset.isKeyboardFocusable); break;
//                    case 'l': Rattrs.add(_preset.localizedControlType); break;
//                    case 'n': Rattrs.add(_preset.name); break;
//                    case 'N': Rattrs.add(_preset.name); break;
//                    case 'o': Rattrs.add(_preset.isOffscreen); break;
//                    case 'p': Rattrs.add(_preset.isPassword); break;
//                    case 'r': Rattrs.add(_preset.runtimeID); break;
//                    case 't': Rattrs.add(_preset.controlType); break;
//                    case 'T': Rattrs.add(_preset.controlType); break;
//                    case 'U': Rattrs.add(_preset.timeout); break;
//                    default: logW("Incorrect parameter at: " + _preset.descriptionName);
//                }
//            }
//        }
//        if (!(_XattrList.isEmpty())){
//            for (String z : _Xattrs){
//                Rattrs.add(z);
//            }
//        }
//        String[] RattrsSA = new String[Rattrs.size()];
//        for (int i = 0; i < Rattrs.size(); i++){
//            RattrsSA[i] = Rattrs.get(i);
//        }
//
//        DElement res = null;
//        if (_searchFlag == 0) {
//            res = gL(_parent,_preset.descriptionName,_attempts,(totalXattrList1 + _XattrList),RattrsSA);
//        }
//        if (_searchFlag == 1) {
//            res = gNL(_parent,_preset.descriptionName,_attempts,_index,(totalXattrList1 + _XattrList),RattrsSA);
//        }
//        if (res == null){
//            logE("Invalid search type: " + _searchFlag);
//            throw new Exception("Invalid search type: " + _searchFlag);
//        }
//        return res;
//    }
    public static Vector<DElement> gVL(DElement _parent, SP _preset, int _attempts) throws Exception{
        return gVL(_parent,_preset,"",_attempts,"");
    }
    public static Vector<DElement> gVL(DElement _parent, SP _preset, String _ignoredXattrList, int _attempts, String _enforcedXattrList, String..._enforcedXattrs) throws Exception{
        SPProcessor.clear();
        SPProcessor.setParent(_parent);
        SPProcessor.setPreset(_preset);
        SPProcessor.setIgnoredXattrList(_ignoredXattrList);
        SPProcessor.setAttempts(_attempts);
        SPProcessor.setEnforcedXattrList(_enforcedXattrList);
        SPProcessor.setEnforcedXattrs(_enforcedXattrs);
        SPProcessor.processData();
        Vector<String> tmp = SPProcessor.getXattrs();
        String[] Xattrs = new String[tmp.size()];
        for (int i = 0; i < tmp.size(); i++){
            Xattrs[i] = tmp.get(i);
        }
        return gVL(SPProcessor.getParent(),SPProcessor.getDescription(),SPProcessor.getAttempts(),SPProcessor.getXattrList(),Xattrs);
    }
    public static DElement gNL(DElement _parent, SP _preset, int _attempts, int _index) throws Exception{
        return gNL(_parent,_preset,"",_attempts,_index,"");
    }
    public static DElement gNL(DElement _parent, SP _preset, String _ignoredXattrList, int _attempts, int _index, String _enforcedXattrList, String..._enforcedXattrs) throws Exception{
        SPProcessor.clear();
        SPProcessor.setParent(_parent);
        SPProcessor.setPreset(_preset);
        SPProcessor.setIgnoredXattrList(_ignoredXattrList);
        SPProcessor.setAttempts(_attempts);
        SPProcessor.setIndex(_index);
        SPProcessor.setEnforcedXattrList(_enforcedXattrList);
        SPProcessor.setEnforcedXattrs(_enforcedXattrs);
        SPProcessor.processData();
        Vector<String> tmp = SPProcessor.getXattrs();
        String[] Xattrs = new String[tmp.size()];
        for (int i = 0; i < tmp.size(); i++){
            Xattrs[i] = tmp.get(i);
        }
        //return getDElementForGL(1,_parent,_preset,_ignoredXattrList,_attempts,_index,_XattrList,_Xattrs);
        return gNL(SPProcessor.getParent(),SPProcessor.getDescription(),SPProcessor.getAttempts(),SPProcessor.getIndex(),SPProcessor.getXattrList(),Xattrs);
    }
    public static DElement gL(DElement _parent, SP..._presets) throws Exception{
        DElement res = gL(_parent,_presets[0],1);
        for (int i = 1; i < _presets.length; i++){
            res = gL(res,_presets[i],1);
        }
        return res;
    }
    public static DElement gL(DElement _parent, SP _preset, int _attempts) throws Exception{
        return gL(_parent,_preset,"",_attempts,"");
    }
    public static DElement gL(DElement _parent, SP _preset,String _ignoredXattrList, int _attempts, String _enforcedXattrList, String..._enforcedXattrs) throws Exception{
        SPProcessor.clear();
        SPProcessor.setParent(_parent);
        SPProcessor.setPreset(_preset);
        SPProcessor.setIgnoredXattrList(_ignoredXattrList);
        SPProcessor.setAttempts(_attempts);
        SPProcessor.setEnforcedXattrList(_enforcedXattrList);
        SPProcessor.setEnforcedXattrs(_enforcedXattrs);
        SPProcessor.processData();
        Vector<String> tmp = SPProcessor.getXattrs();
        String[] Xattrs = new String[tmp.size()];
        for (int i = 0; i < tmp.size(); i++){
            Xattrs[i] = tmp.get(i);
        }
        return gL(SPProcessor.getParent(),SPProcessor.getDescription(),SPProcessor.getAttempts(),SPProcessor.getXattrList(),Xattrs);

        //return getDElementForGL(0,_parent,_preset,_ignoredXattrList,_attempts,0,_XattrList,_Xattrs);

        //        String totalXattrList1 = _preset.defaultSearchString;
//        if (!(_ignoreXAttrList.isEmpty())) {
//            for (int i = 0; i < _ignoreXAttrList.length(); i++){
//                char zzz = _ignoreXAttrList.charAt(i);
//                while (totalXattrList1.contains("" + zzz)){
//                    totalXattrList1 = getStringExcludingChar(totalXattrList1,zzz);
//                }
//            }
//        }
//        if (!(_XattrList.isEmpty())) {
//            for (int i = 0; i < _XattrList.length(); i++){
//                char zzz = _XattrList.charAt(i);
//                while (totalXattrList1.contains("" + zzz)){
//                    totalXattrList1 = getStringExcludingChar(totalXattrList1,zzz);
//                }
//            }
//        }
//        Vector<String> Rattrs = new Vector<>();
//        if (!(totalXattrList1.isEmpty())){
//            for (int i = 0; i < totalXattrList1.length(); i++){
//                char zzz = totalXattrList1.charAt(i);
//                switch (zzz){
//                    case 'a': Rattrs.add(_preset.automationID); break;
//                    case 'A': Rattrs.add(_preset.automationID); break;
//                    case 'c': Rattrs.add(_preset.className); break;
//                    case 'C': Rattrs.add(_preset.className); break;
//                    case 'e': Rattrs.add(_preset.isEnabled); break;
//                    case 'h': Rattrs.add(_preset.hasKeyboardFocus); break;
//                    case 'k': Rattrs.add(_preset.isKeyboardFocusable); break;
//                    case 'l': Rattrs.add(_preset.localizedControlType); break;
//                    case 'n': Rattrs.add(_preset.name); break;
//                    case 'N': Rattrs.add(_preset.name); break;
//                    case 'o': Rattrs.add(_preset.isOffscreen); break;
//                    case 'p': Rattrs.add(_preset.isPassword); break;
//                    case 'r': Rattrs.add(_preset.runtimeID); break;
//                    case 't': Rattrs.add(_preset.controlType); break;
//                    case 'T': Rattrs.add(_preset.controlType); break;
//                    case 'U': Rattrs.add(_preset.timeout); break;
//                    default: logW("Incorrect parameter at: " + _preset.descriptionName);
//                }
//            }
//        }
//        if (!(_XattrList.isEmpty())){
//            for (String z : _Xattrs){
//                Rattrs.add(z);
//            }
//        }
//        String[] RattrsSA = new String[Rattrs.size()];
//        for (int i = 0; i < Rattrs.size(); i++){
//            RattrsSA[i] = Rattrs.get(i);
//        }
//        return gL(_parent,_preset.descriptionName,_attempts,(totalXattrList1 + _XattrList),RattrsSA);
    }


    public static DElement gL(DElement _parent, String _descr, int _attempts, String _XattrList, String..._Xattrs) throws Exception{
        DElement res;
        log("Looking for " + _descr);
        try{
            res = DElement.gimMeP(_parent,_descr,_attempts,_XattrList,_Xattrs);
        } catch (Exception e) {
            log(_descr + " finding failure");
            throw e;
        }
        return res;
    }
    public static Vector<DElement> gVL(DElement _parent, String _descr, int _attempts, String _XattrList, String..._Xattrs) throws Exception{
        Vector<DElement> res= new Vector<>();
        log("Looking for vector: " + _descr);
        try{
            res = DElement.gimMePV(_parent,_descr,_attempts,_XattrList,_Xattrs);
        } catch (Exception e) {
            log(_descr + " finding failure");
            throw e;
        }
        return res;
    }
    public static DElement gNL(DElement _parent, String _descr, int _attempts, int _index, String _XattrList, String..._Xattrs) throws Exception{
        DElement res;
        log("Looking for  " + _descr);
        try{
            res = DElement.gimMePN(_parent,_descr,_attempts,_index,_XattrList,_Xattrs);
        } catch (Exception e) {
            log(_descr + " finding failure");
            throw e;
        }
        return res;
    }
    /**
     * Log everything to file
     */
    private static boolean Log = true;
    /**
     * Log everything to console
     */
    private static boolean LogConsole = true;

    public static void closeDefaultBrowser(){
        setLogBlockPrefix("closeDefaultBrowser");
        sleepy(5);
        closeEdgeBrowser();
        closeFFBrowser();
    }
    public static void closeFFBrowser(){
        setLogBlockPrefix("closeFFBrowser");
        try{
            log("Closing possible FF windows");
            //Vector<DElement> fFWL = DElement.gimMePV(null, "FF",1,"NC","Mozilla Firefox","MozillaWindowClass");
            Vector<DElement> fFWL = gVL(null, MozillaFF.FFWLarge,1);

            for (DElement fFW : fFWL) {
                fFW.setForeground();
                sleepy(1);
                //DElement navTB = DElement.gimMeP(fFW,"Toolbar",1,"n","Navigation Toolbar");
                DElement navTB = gL(fFW,MozillaFF.FFMenu,1);

                //DElement fFB = DElement.gimMeP(navTB,"FF button",1,"n","Firefox");
                DElement fFB = gL(navTB,MozillaFF.FFB,1);

                fFB.click();
                sleepy(2);
                //DElement contG = DElement.gimMeP(fFW,"Group",1,"l","group");
                DElement contG = gL(fFW,MozillaFF.G,1);

                //DElement wrk = DElement.gimMeP(contG,"Exit btn",1,"n","Exit");
                DElement wrk = gL(contG,MozillaFF.exitB,1);

                wrk.click();
                sleepy(2);
            }
        } catch (Exception e) {
            log("Was not able to close FF or there were no FF windows");
        }
    }
    public static void closeEdgeBrowser(){
        setLogBlockPrefix("closeEdgeBrowser");
        try{
            log("Closing possible Edge windows");
            DElement edgeW = DElement.gimMeP(null,"Edge",1,"NC","Microsoft Edge","Frame");
            edgeW.setForeground();
            sleepy(0.2);

            try{
                gL(edgeW,"Edge menu",1,"NC","Microsoft Edge","TitleBar");
            } catch (Exception e){
                log("Edge is minimized");
                edgeW.showMinWindow();
            }
            DElement edgeM = gL(edgeW,"Edge menu",1,"NC","Microsoft Edge","TitleBar");
            DElement clBut = gL(edgeM,"Close button",1,"NA","Close","Close");
            clBut.click();
            sleepy(5);
            log("Edge was found and closed successfully");
        } catch (Exception e){
            log("Was not able to close Edge or there were no Edge windows");
        }
    }

    public static DElement gimMeP(DElement _parent,ElemD _elemD, String _displayName,int _attempts, String _eXattrList, String..._eXattrs) throws Exception{
        DElement reso;
        String addN;
        String addCN;
        String addAI;
        Vector<String> attP = new Vector<>();
        try{
            for (String hh : _eXattrs){
                attP.add(hh);
            }
            //passing ElemD as well
            if (_eXattrList.contains("n")){
                _eXattrList = _eXattrList.substring(0, _eXattrList.indexOf("n")) + _eXattrList.substring(_eXattrList.indexOf("n") + 1);
                _eXattrList = _eXattrList + "n";
                addN = _elemD.name;
                attP.add(addN);
            }
            if (_eXattrList.contains("c")){
                _eXattrList = _eXattrList.substring(0, _eXattrList.indexOf("c")) + _eXattrList.substring(_eXattrList.indexOf("c") + 1);
                _eXattrList = _eXattrList + "c";
                addCN = _elemD.cName;
                attP.add(addCN);
            }
            if (_eXattrList.contains("a")){
                _eXattrList = _eXattrList.substring(0, _eXattrList.indexOf("a")) + _eXattrList.substring(_eXattrList.indexOf("a") + 1);
                _eXattrList = _eXattrList + "a";
                addAI = _elemD.autId;
                attP.add(addAI);
            }
            String[] hurrt = attP.toArray(new String[attP.size()]);
            reso = DElement.gimMePN(_parent,_displayName,_attempts,0,_eXattrList,hurrt);
        } catch (Exception e){
            throw new Exception("DElement not found");
        }
        return reso;
    }

    //kill that =)
    /**
     * shield + signs using '+=' instead of '+'
     */
    static String targetVersionStr = "8-3-9-5";
    public static void setTargetVersionStr(String s){
        targetVersionStr = s;
    }
    static String targetVersionStrOfficial = "8-3-9-5";
    public static void setTargetVersionStrOfficial(String s){
        targetVersionStrOfficial = s;
    }
    static String itemTemplatesPath = "C:/Users/Autotester/Desktop/ItemTemplates/";
    public static void setItemTemplatesPath(String s){
        itemTemplatesPath = s;
    }

    /**
     * Used in editor tests - specifies whether RF should be installed/uninstalled before/after tests or not
     * If preinstallRF ==
     */
    public static Boolean preinstallRF = false;
    //public static Boolean preinstallRF = true;


    /**
     * New user generator RF8
     */
    public static int generateNewRF8User() throws Exception{
        lgP = "Generate new RF8 user";
        int res = UserProc.addNewCustomUser(Servers.USSTAGING, DataTypeStates.RF8);
        cl();
        return res;
    }


    /**
     * <p>Strict locator and logger for ElemD's</p>
     * <p>Use when this element is crucial for testing</p>
     * @param _dElement parent
     * @param _elemD ElemD
     * @param _name item name for logging
     * @param _attempts number of attempts to try to locate element; default value - 1
     * @return webElement if not null
     * @throws Exception with the relevant error string
     */
    public static DElement gimMe(DElement _dElement, ElemD _elemD, String _name, int _attempts) throws Exception{
        return gimMeP(_dElement,_elemD,_name,_attempts,"nc","o");
    }
    /**
     * <p>Strict locator and logger for ElemD's: override with single attempt</p>
     * <p>Use when this element is crucial for testing</p>
     * @param _dElement parent
     * @param _elemD ElemD
     * @param _name item name for logging
     * @return webElement if not null
     * @throws Exception with the relevant error string
     */
    public static DElement gimMe(DElement _dElement, ElemD _elemD, String _name) throws Exception {
        log("This method (gimMe based on ElemD) is deprecated, use gL instead");
        return gimMeP(_dElement,_elemD,_name,1,"nc");
    }



    /**
     * Transform Winium-style gimMeP evocations to DElement-style
     * @param _dElement parent or null (for desktop)
     * @param _elemD the same
     * @param _name the same
     * @param _attempts the same
     * @param _paramT the same (except 'x' since there is no xpath and '/' is now looks deeper)
     * @param _paramF the same
     * @return DElement
     * @throws Exception if somethis goes wrong
     */
    public static DElement gimMeP(DElement _dElement, ElemD _elemD, String _name, int _attempts, String _paramT, String _paramF) throws Exception{
//        lgP = "gimMeP";
//        log("Locating " + _name);
        log("This method (gimMeP based on ElemD) is deprecated, use gL instead");

        Vector<String> _eXattrs = new Vector<>();
        String _eXattrList = "";

        if (_paramT.contains("o")){
            _eXattrList += "o";
            _eXattrs.add("t");
        } else {
            if (_paramF.contains("o")){
                _eXattrList += "o";
                _eXattrs.add("f");
            }
        }
        if (_paramT.contains("e")){
            _eXattrList += "e";
            _eXattrs.add("t");
        } else {
            if (_paramF.contains("e")){
                _eXattrList += "e";
                _eXattrs.add("f");
            }
        }
        if (_paramT.contains("k")){
            _eXattrList += "h";
            _eXattrs.add("t");
        } else {
            if (_paramF.contains("k")){
                _eXattrList += "h";
                _eXattrs.add("f");
            }
        }
        if (_paramT.contains("d")){
            _eXattrList += "D";
        }
        if (_paramT.contains("/")){
            _eXattrList += "d";
        }
        if (!(_paramF.contains("n"))){
            _eXattrList += "n";
            _eXattrs.add(_elemD.name);
        }
        if (!(_paramF.contains("c"))){
            _eXattrList += "c";
            _eXattrs.add(_elemD.cName);
        }
        if (!(_paramF.contains("a"))){
            _eXattrList += "a";
            _eXattrs.add(_elemD.autId);
        }

        return DElement.gimMeP(_dElement,_name,_attempts,_eXattrList,_eXattrs.toArray(new String[_eXattrs.size()]));
    }
    /**
     * Override for gimMeP to be able to handle just names shorter
     */
    public static DElement gimMeP(DElement _dElement, String _nED, String _name, int _attempts, String _paramT, String _paramF) throws Exception{
        return gimMeP(_dElement, new ElemD(_nED),_name,_attempts,_paramT,_paramF + "ca");
    }



    /**
     * Transform Winium-style gimMeP evocations to DElement-style
     * @param _dElement parent or null (for desktop)
     * @param _elemD the same
     * @param _name the same
     * @param _paramT the same (except 'x' since there is no xpath and '/' is now looks deeper)
     * @param _paramF the same
     * @return DElement
     * @throws Exception if somethis goes wrong
     */
    public static List<DElement> gimMePL(DElement _dElement, ElemD _elemD, String _name, String _paramT, String _paramF) throws Exception{
//        lgP = "gimMePL";
//        log("Locating " + _name);
        log("This method (gimMePL based on ElemD) is deprecated, use gL instead");

        Vector<String> _eXattrs = new Vector<>();
        String _eXattrList = "";

        if (_paramT.contains("o")){
            _eXattrList += "o";
            _eXattrs.add("t");
        } else {
            if (_paramF.contains("o")){
                _eXattrList += "o";
                _eXattrs.add("f");
            }
        }
        if (_paramT.contains("e")){
            _eXattrList += "e";
            _eXattrs.add("t");
        } else {
            if (_paramF.contains("e")){
                _eXattrList += "e";
                _eXattrs.add("f");
            }
        }
        if (_paramT.contains("k")){
            _eXattrList += "h";
            _eXattrs.add("t");
        } else {
            if (_paramF.contains("k")){
                _eXattrList += "h";
                _eXattrs.add("f");
            }
        }
        if (_paramT.contains("d")){
            _eXattrList += "D";
        }
        if (_paramT.contains("/")){
            _eXattrList += "d";
        }
        if (!(_paramF.contains("n"))){
            _eXattrList += "n";
            _eXattrs.add(_elemD.name);
        }
        if (!(_paramF.contains("c"))){
            _eXattrList += "c";
            _eXattrs.add(_elemD.cName);
        }
        if (!(_paramF.contains("a"))){
            _eXattrList += "a";
            _eXattrs.add(_elemD.autId);
        }

        return DElement.gimMePV(_dElement,_name,1,_eXattrList,_eXattrs.toArray(new String[_eXattrs.size()]));
    }


    /**
     * Log prefix - redefine in procedures
     */
    public static String lgP = "Default log prefix";

    /**
     * Global Log prefix (redefine in classes)
     */
    private static String logGPr = "Global log prefix";
    public static String logSeparator = ": ";
    private static String logDeprecatedMarker = "[deprecated]";
    private static String logSuitePrefix = "";
    private static String logScenarioPrefix = "";
    private static String logStepPrefix = "";
    private static String logBlockPrefix = "";
    public static void setLogSuitePrefix(String logString){
        int recommendedMaxLength = 8;
        if (logString.length() > recommendedMaxLength) {
            System.out.println("setLogSuitePrefix - exceeded recommended max length = " + recommendedMaxLength);
        }
        logSuitePrefix = logString;
        logScenarioPrefix = "";
        logStepPrefix = "";
        logBlockPrefix = "";
    }
    public static void setLogScenarioPrefix(String logString){
        int recommendedMaxLength = 8;
        if (logString.length() > recommendedMaxLength) {
            System.out.println("setLogScenarioPrefix - exceeded recommended max length = " + recommendedMaxLength);
        }
        logScenarioPrefix = logString;
        logStepPrefix = "";
        logBlockPrefix = "";
    }
    public static void setLogStepPrefix(String logString){
        int recommendedMaxLength = 5;
        if (logString.length() > recommendedMaxLength) {
            System.out.println("setLogStepPrefix - exceeded recommended max length = " + recommendedMaxLength);
        }
        logStepPrefix = logString;
        logBlockPrefix = "";
    }
    public static void setLogBlockPrefix(String logString){
//        int recommendedMaxLength = 5;
//        if (logString.length() > recommendedMaxLength) {
//            System.out.println("setLogBlockPrefix - exceeded recommended max length = " + recommendedMaxLength);
//        }
        logBlockPrefix = logString;
    }
    private static String makeLogString(String s){
        String res = "";
//        if (!(logGPr.isEmpty())){
//            res = logGPr + logSeparator;
//        }
//        if (!(lgP.isEmpty())){
//            res += lgP + logSeparator;
//        }
        if (!(logSuitePrefix.isEmpty())){
            res += logSuitePrefix + logSeparator;
        }
        if (!(logScenarioPrefix.isEmpty())){
            res += logScenarioPrefix + logSeparator;
        }
        if (!(logStepPrefix.isEmpty())){
            res += logStepPrefix + logSeparator;
        }
        if (!(logBlockPrefix.isEmpty())){
            res += logBlockPrefix + logSeparator;
        }
        res += s;
        return res;
    }
    public static void log(String s){ //normal log
        wrLog(makeLogString(s));
    }
    public static void log(String s1, String s2) {
        wrLog(s1 + logSeparator + makeLogString(s2));
    }
    public static void logE(String s){ //error log
        wrLog("ERROR" + logSeparator + makeLogString(s));
    }
    public static void logE(String s1, String s2) {
        wrLog("ERROR" + logSeparator + s1 + logSeparator + makeLogString(s2));
    }
    public static void logW(String s){ //warning log
        wrLog("WARNING" + logSeparator + makeLogString(s));
    }
    public static void logW(String s1, String s2){
        wrLog("WARNING" + logSeparator + s1 + logSeparator + makeLogString(s2));
    }
    private static void wrLog(String s){ // writes log
        if (Log){
            if (_testProvider == TestProvider.JAVA){
                buf.add(s);
            }
            if (_testProvider == TestProvider.KOTLIN){
                KotlinTestLoggerKt.logToKotlinTest(s);
            }
        }
        if (LogConsole){
            System.out.println(s);
        }
    }

    /**
     * Set combobox value and add the value if there is no such value
     * @param _editorW editor DElement that contains target combobox
     * @param _cb target combobox DElement
     * @param _t text string to set
     * @param _dic display item count
     * @param _nv order number of the element if there are the same items; 0 is the first (JCS - Java conventional style)
     */
    public static void setCBVAdd(DElement _editorW,DElement _cb, String _t, int _dic, int _nv) throws Exception{
        try{
            _cb.setRFComboboxValue(_t,_dic,_nv);
        } catch (Exception e) {
            log("Current combobox does not has this value: " + _t);
            log("Looking for possible adding items");
            try{
                _cb.setRFComboboxValue("(Unlisted)",_dic,0);
            } catch (Exception e1){
                _cb.setRFComboboxValue("(Unl)",_dic,0);
            }
            log("Combobox new value dialog should be emitted. Checking");
            sleepy(4);
            DElement dialW = DElement.gimMeP(_editorW,"RF dialog",1,"nc","RoboForm","#32770");
            log("Dialog window has been found, processing");
            DElement namE = DElement.gimMeP(dialW,"Enter new value edit",1,"Nc","new value here","Edit");
            namE.setEditValue(_t);
            sleepy(1);
            log("Name was entered, looking for OK button");
            DElement okB = DElement.gimMeP(dialW,"OK button",1,"nce","OK","Button","t");
            okB.click();
            sleepy(1);
            log("Possible error handling:");
            boolean wasErrors = false;
            try{
                DElement wrkW = DElement.gimMeP(dialW,"RF Error dlg",1,"nc","RoboForm Error","#32770");
                wasErrors = true;
                DElement wrk = DElement.gimMeP(wrkW,"OK button",1,"nc","OK","Button");
                wrk.click();
                DElement cancelB = DElement.gimMeP(dialW,"Cancel button",1,"nc","Cancel","Button");
                cancelB.click();
            } catch (Exception err) {
                if (wasErrors) {
                    throw new Exception("Error while handling error windows");
                } else {
                    log("Error window did not appear");
                }
            }


        }
    }
    /**
     * Set global log prefix
     * @param s log prefix
     */
    public static void setGLP(String s){
        logGPr = s;
    }

    /**
     * Clear global log prefix
     */
    public static void clearGLP(){
        logGPr = "";
    }

    private static Vector <String> buf = new Vector<String>();

    /**
     * Clear log prefix string
     */
    private static void cl(){
        lgP = "";
    }
    public static void sleepy(double s){  //in seconds
        lgP = "Sleeping";
        double multiplierFastMachine = 0.9;
        try {
            log("Waiting for " + s * multiplierFastMachine + " seconds.");
            Thread.sleep(Math.round(s * multiplierFastMachine * 1000));                 //milliseconds
        } catch(InterruptedException ex) {
            logE("Interrupted exception appeared");
            Thread.currentThread().interrupt();
        }
        cl();
    }


    /* OBSOLETE
     * Strict attach to visible ElemD (deprecated, transformed to DElement)
     * @param _dElement parent SearchContext
     * @param _elemD ElemD object
     * @return WebElement which was found or null
     */
/*    public static DElement sibStrictAttachV(DElement _dElement, ElemD _elemD){
        lgP = "sibStrictAttachV" + logDeprecatedMarker;
        logW("This method is deprecated. Please use gimMeP instead: gimMeP(_searchContext,_elemD,..,1,\"\",\"o\").");
        log("Launched with the following parameters: " + _dElement.toString() + " " + _elemD.toString());
        DElement res = null;
        try{
            try{
               res = gimMeP(_dElement,_elemD,_elemD.name + " as " + _elemD.cName,1,"","o");
            }catch (Exception e){
                throw new Exception("Element was not found" + e.getMessage());
            }
            log("Element found");
        }catch (Exception e)
        {
            log("Exception caught: " + e.getMessage());
        }
        if (res != null) {
            log("Returning following element: " + res.toString());
        }
        else{
            log("Returning NULL");
        }
        cl();
        return res;
    }
*/
/*    public static List<DElement> sibGetStrictItemListV(DElement _dElement, ElemD _elemD){
        lgP = "sibGetStrictItemListV" + logDeprecatedMarker;
        logW("This method is deprecated. Please use gimMePL instead: gimMePL(_webElement,_elemD,..,\"\",\"o\").");
        log("Launched with the following parameters: " + _dElement.toString() + " " + _elemD.toString());
        List<DElement> res = null;
        try{
            String xpathStr = "*[(@Name='" + _elemD.name + "') and (@ClassName='" +
                    _elemD.cName + "') and (@AutomationId='" + _elemD.autId + "') and (@IsOffscreen='False')]";
            try{
                res = gimMePL(_dElement,_elemD,_elemD.name + " as " + _elemD.cName,"","o");
            }
            catch(Exception e){
                throw new Exception("Element group was not found by this xpath:" + xpathStr);
            }
            log("Element found");
        }
        catch (Exception e){
            log("Exception caught: " + e.getMessage());
        }
        if (res != null){
            log("Returning following element: " + res.toString());
        }
        else{
            log("Returning NULL");
        }
        cl();
        return res;
    }
*/
/*
    public static DElement sibAttachToCN(DElement _dElement, String _className){
        lgP = "sibAttachToCN" + logDeprecatedMarker;
        logW("This method is deprecated. Please use gimMeP instead: gimMeP(_webElement,new ElemD(\"\",s),..,1,\"\",\"na\").");
        log("Launched with the following parameters: " + _dElement.toString() + " " + _className);
        try{
            DElement res = DElement.gimMeP(_dElement,_className,1,"c",_className);
            log( _className + " window found");
            cl();
            return res;
        }catch(Exception ex){
            log(_className + " :: " + ex.getMessage());
            cl();
            return null;
        }
    }
    */
/*
    public static DElement sibAttachToN(DElement _dElement, String _name){
        lgP = "sibAttachToN" + logDeprecatedMarker;
        logW("This method is deprecated. Please use gimMeP instead: gimMeP(_webElement,s,..,1,\"\",\"ca\").");
        log("Launched with the following parameters: " + _dElement.toString() + " " + _name);
        try{
            DElement res = DElement.gimMeP(_dElement,_name,1,"n",_name);
            log(_name + " window found");
            cl();
            return res;
        }catch(Exception ex){
            log(_name + " :: " + ex.getMessage());
            cl();
            return null;
        }
    }
    */
    static boolean checkExistenceN(DElement _dElement, String _name){
        lgP = "checkExistenceN";
        log("Launched with the following parameters: " + _dElement.toString() + " " + _name);
        boolean res = true;
        try{
            DElement.gimMeP(_dElement,_name,1,"n",_name);
            log("element named '" + _name + "' exists.");
        }catch (Exception e){
            log("element named '" + _name + "' does not exist.");
            res = false;
        }
        cl();
        return res;
    }
    /*
    public static DElement sibAttachDriverCN(String _className){
        lgP = "sibAttachDriverCN" + logDeprecatedMarker;

        logW("This method is deprecated. Please use gimMeP instead: gimMeP(_searchContext,new ElemD(\"\",s),..,1,\"\",\"na\").");

        log("Launched with the following parameters: " + _className);

        try{
            DElement res = DElement.gimMeP(null,_className,1,"c",_className);
            log("Chupakabra has been attached to " + _className);
            cl();
            return res;
        }catch(Exception ex){
            log(_className + " window not found or its Name field is empty");
            cl();
            return null;
        }

    }
    */
    /*
    public static DElement sibAttachDriverN(String _name){
        lgP = "sibAttachDriverN" + logDeprecatedMarker;
        logW("This method is deprecated. Please use gimMeP instead: gimMeP(_searchContext,s,..,1,\"\",\"ca\").");
        log("Launched with the following parameters: " + _name);

        try{
            log("Looking for element named " + _name);
            DElement res = DElement.gimMeP(null,_name,1,"n",_name);
            log("Rakobax has been attached to " + _name);
            cl();
            return res;
        }catch(Exception ex){
            log(_name + " window not found or its Name field is empty");
            cl();
            return null;
        }

    }
    */
    public static void setBuf(Vector<String> _buff){
        buf = _buff;
    }
    public static Vector<String> getBuf(){
        Proc.clearGLP();
        return buf;
    }

    public static String getItemFileProperty(String _fileName, String _property){
        File iniF = new File(itemTemplatesPath + _fileName);
        String ress = "";
        try
        {
            JsonParser parser = new JsonParser();
            JsonObject o = (JsonObject) parser.parse(new FileReader(iniF));
            if (o.has(_property)) {
                ress = o.get(_property).getAsString();
            }

        } catch (Exception e) {
            logW("JSON parsing exception: " + e.getMessage());
        }
        return ress;
    }

    public static void doubleClick(DElement _dElement) throws Exception{
        log("Double-clicking on " + _dElement.getNativeWindowHandleHEXStr());
        _dElement.doubleClick();
    }
    public static void contextClick(DElement _dElement) throws Exception{
        log("Context-clicking on " + _dElement.getNativeWindowHandleHEXStr());
        _dElement.contextClick();
    }
    public static void offsetClick(DElement _dElement, int _offsetX, int _offsetY) throws Exception{
        log("Clicking with offset " + _offsetX + ":" + _offsetY + " on " + _dElement.getNativeWindowHandleHEXStr());

//        int xP = _dElement.getClickablePoint().x + _offsetX;
//        int yP = _dElement.getClickablePoint().y + _offsetY;
        WinDef.RECT rc =_dElement.element.getBoundingRectangle();
        int xP = rc.left + (rc.right - rc.left) / 2 + _offsetX;
        int yP = rc.top + (rc.bottom - rc.top) / 2 + _offsetY;
        MouseHandler.moveMouseTo(xP,yP);
        Thread.sleep(250);
        MouseHandler.clickLeft();

    }
    public static void offsetClick(DElement _dElement, int _offsetX) throws Exception{
        offsetClick(_dElement,_offsetX,0);
    }
    public static void offsetClick(DElement _dElement, int _offsetX, int _offsetY, String _keys) throws Exception{
        log("Clicking with offset " + _offsetX + ":" + _offsetY + " on " + _dElement.getNativeWindowHandleHEXStr());
        log("After that entering key sequence: " + _keys);
        offsetClick(_dElement,_offsetX,_offsetY);
        KeyboardHandler.sendKeysHere(_keys);
    }
    public static void offsetClick(DElement _dElement, int _offsetX, String _keys) throws Exception{
        offsetClick(_dElement,_offsetX,0,_keys);
    }
    public static void clickItemFromContextMenu(String _itemName) throws Exception{
        //DElement cont = gimMeP(null,new ElemD("Context","#32768"),"Context menu",2,"","na");
        DElement cont = gL(null, MSWindowsGeneral.contextW,2);
        //DElement tmp = gimMeP(cont,_itemName,_itemName,1,"","ca");
        DElement tmp = gL(cont,_itemName,2,"n",_itemName);
        tmp.click();
    }

//    private static String getStringBeforeChar(String _s, char _c){
//        int st = 0;
//        int end = _s.indexOf(_c);
//        char[] buf = new char[end - st];
//        _s.getChars(st,end,buf,0);
//        return new String(buf);
//    }
//    private static String cutStringAfterChar(String _s, char _c){
//        int st = _s.indexOf(_c) + 1;
//        int end = _s.length();
//        char[] buf = new char[end - st];
//        _s.getChars(st,end,buf,0);
//        return new String(buf);
//    }

    protected Proc()    {
    }
}
