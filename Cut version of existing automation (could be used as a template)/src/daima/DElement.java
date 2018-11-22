package daima;

import com.sun.glass.ui.Size;
import com.sun.jna.platform.win32.WinDef;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.*;
import mmarquee.automation.uiautomation.ToggleState;

import java.util.List;
import java.util.Vector;

/**
 * Created by Autotester on 9/27/2017.
 */
public class DElement{
    //MSDN Automation Element property identifiers   #used symbols
    private static int UIA_AutomationIdPropertyId = 30011; //VT_BSTR w/AutomationId  #a
    private static int UIA_CenterPointPropertyId = 30165; //VT_R8 CenterPoint with center X and Y point
    private static int UIA_ClassNamePropertyId = 30012; //VT_BSTR w/ClassName  #c
    private static int UIA_ClickablePointPropertyId = 30014; //VT_R8 | VT_ARRAY def. VT_EMPTY
    private static int UIA_ControlTypePropertyId = 30003; //VT_I4  #t
    private static int UIA_HasKeyboardFocusPropertyId = 30008; //VT_BOOL  #h
    private static int UIA_IsEnabledPropertyId = 30010; //VT_BOOL  #e
    private static int UIA_IsKeyboardFocusablePropertyId = 30009; //VT_BOOL  #k
    private static int UIA_IsOffscreenPropertyId = 30022; //VT_BOOL  #o
    private static int UIA_IsPasswordPropertyId = 30019; //VT_BOOL  #p
    private static int UIA_LocalizedControlTypePropertyId = 30004; //VT_BSTR  #l
    private static int UIA_NamePropertyId = 30005; //VT_BSTR  #n
    private static int UIA_NativeWindowHandlePropertyId = 30020; //VT_I4
    private static int UIA_ProcessIdPropertyId = 30002; //VT_I4
    private static int UIA_ProviderDescriptionPropertyId = 30107; //VT_BSTR
    private static int UIA_RuntimeIdPropertyId = 30000; //VT_I4 | VT_ARRAY  #r

    /*
    gimMe abbreviation legend:
        D - used first-level desktop objects (absent - works for both, but needs 'null' as parent
        Pm - parametrized (minor parameters set - just UIAutomation properties)
        N/V - if absent, returns first element; if N - number N (st. from 0); if V - whole vector
        P - parametrized (full)
     */

    private static void log(String s){
        System.out.println(additionalLogPrefix + separator + s);
    }
//    private static void logW(String s){
//        System.out.println("WARNING" + separator + additionalLogPrefix + separator + s);
//    }
    private static String additionalLogPrefix = "";
    private static String separator = "::";

    public AutomationBase element;
    public String typeAutomation;
    public DElement(AutomationBase _element){
        element = _element;
        if (_element.getClass() == AutomationEditBox.class) {
            typeAutomation = "edit";
        }
        if (_element.getClass() == AutomationWindow.class) {
            typeAutomation = "window";
        }
        if (_element.getClass() == AutomationButton.class) {
            typeAutomation = "button";
        }
        if (_element.getClass() == AutomationComboBox.class) {
            typeAutomation = "combobox";
        }
    }

    private static Vector<DElement> processFilters(Vector<DElement> l, String _attrList, String..._attrs) throws Exception{
        int n = 0;
        for (char c : _attrList.toCharArray()){
            String p = _attrs[n];
            switch (c){
                case 'a':
                    l = filterVector(l,null,null,p,null,null,
                            null,null,null,null,
                            null,null, true);
                    break;
                case 'A':
                    l = filterVector(l,null,null,p,null,null,
                            null,null,null,null,
                            null,null, false);
                    break;
                case 'c':
                    l = filterVector(l,null,p,null,null,null,
                            null,null,null,null,
                            null,null, true);
                    break;
                case 'C':
                    l = filterVector(l,null,p,null,null,null,
                            null,null,null,null,
                            null,null, false);
                    break;
                case 't':
                    l = filterVector(l,null,null,null,p,null,
                            null,null,null,null,
                            null,null, true);
                    break;
                case 'T':
                    l = filterVector(l,null,null,null,p,null,
                            null,null,null,null,
                            null,null, false);
                    break;
                case 'h':{
                    boolean pp = false;
                    if (p.toUpperCase().equals("TRUE") || p.toUpperCase().equals("T")){
                        pp = true;
                    }
                    l = filterVector(l,null,null,null,null,pp,
                            null,null,null,null,
                            null,null, true);
                }
                break;
                case 'e':{
                    boolean pp = false;
                    if (p.toUpperCase().equals("TRUE") || p.toUpperCase().equals("T")){
                        pp = true;
                    }
                    l = filterVector(l,null,null,null,null,null,
                            pp,null,null,null,
                            null,null, true);
                }
                break;
                case 'k':{
                    boolean pp = false;
                    if (p.toUpperCase().equals("TRUE") || p.toUpperCase().equals("T")){
                        pp = true;
                    }
                    l = filterVector(l,null,null,null,null,null,
                            null,pp,null,null,
                            null,null, true);
                }
                break;
                case 'o':{
                    boolean pp = false;
                    if (p.toUpperCase().equals("TRUE") || p.toUpperCase().equals("T")){
                        pp = true;
                    }
                    l = filterVector(l,null,null,null,null,null,
                            null,null,pp,null,
                            null,null, true);
                }
                break;
                case 'p':{
                    boolean pp = false;
                    if (p.toUpperCase().equals("TRUE") || p.toUpperCase().equals("T")){
                        pp = true;
                    }
                    l = filterVector(l,null,null,null,null,null,
                            null,null,null,pp,
                            null,null, true);
                }
                break;
                case 'l':
                    l = filterVector(l,null,null,null,null,null,
                            null,null,null,null,
                            p,null, true);
                    break;
                case 'n':
                    l = filterVector(l,p,null,null,null,null,
                            null,null,null,null,
                            null,null, true);
                    break;
                case 'N':
                    l = filterVector(l,p,null,null,null,null,
                            null,null,null,null,
                            null,null, false);
                    break;
                case 'r':
                    l = filterVector(l,null,null,null,null,null,
                            null,null,null,null,
                            null,p, true);
                    break;
                case 'U':
                    //do nothing - that is for timeout
                    break;
                default:
                    throw new Exception("Unknown key in process filters: " + Character.toString(c));
            }
            n++;
        }
        return l;
    }

    public static Vector<DElement> gimMePV(DElement _parent,String _displayName,int _attempts, String _XattrList, String..._Xattrs) throws Exception{
        additionalLogPrefix = "gimMeP";
        Vector<DElement> procV = new Vector<>();
        Vector<DElement> tempV = new Vector<>();
        boolean deep = false;
        boolean debug = false;
        String localChar = "!";
        if (_XattrList.contains(localChar)){
            debug = true;
        }
        if (!debug){
            log("Looking for '" + _displayName + "' with following _XattrList: " + _XattrList);
        } else {
            log("DEBUG MODE: Looking for '" + _displayName + "' with following parameters:");
            if (_parent == null) {
                log("Parent handle: Desktop");
            } else {
                log("Parent handle: " + _parent.getNativeWindowHandleHEXStr());
            }
            log("_XattrList: " + _XattrList);
            log("_XattrParam: BEGIN...");
            for (String s : _Xattrs) {
                log(s);
            }
            log("_XattrParam: END...");
        }
        if (_XattrList.contains(localChar)){
            int di = _XattrList.indexOf(localChar);
            String begX = "";
            if (di > 0){
                begX = _XattrList.substring(0,di);
            }
            _XattrList = begX + _XattrList.substring(di + 1);
        }

        localChar = "d";
        if (_XattrList.contains(localChar)){
            deep = true;
            int di = _XattrList.indexOf(localChar);
            String begX = "";
            if (di > 0){
                begX = _XattrList.substring(0,di);
            }
            _XattrList = begX + _XattrList.substring(di + 1);
        }
        boolean shouldDisappear = false;
        localChar = "D";
        if (_XattrList.contains(localChar)){
            shouldDisappear = true;
            int di = _XattrList.indexOf(localChar);
            String begX = "";
            if (di > 0){
                begX = _XattrList.substring(0,di);
            }
            _XattrList = begX + _XattrList.substring(di + 1);
        }
        int timeoutForATry = 3000; //in milliseconds
        localChar = "U";
        if (_XattrList.contains(localChar)){
            int di = _XattrList.indexOf(localChar);
            timeoutForATry = Integer.parseInt(_Xattrs[di]);
        }
        UIAutomation uia = null;
        if (_parent == null) {
            uia = UIAutomation.getInstance();
        }

        boolean allOK = false;
        int curAt = 1;
        if (shouldDisappear){
            while (!allOK && (curAt <= _attempts)){
                log("Waiting for disappear attempt #" + curAt + " of " + _attempts);
                if (curAt > 1) {
                    Thread.sleep(timeoutForATry);
                }
                try{
                    if (_parent == null) {
//                        procV = transformWtoD(uia.getDesktopWindows());
//                        List<AutomationPanel> apl = uia.getDesktopObjects();
//                        for (AutomationPanel k : apl){
//                            procV.add(new DElement(k));
//                        }
                        DElement dt = new DElement(uia.getDesktop());
                        List<AutomationBase> abl = dt.element.getChildren(false);
                        procV.addAll(transformBtoD(abl));
                    } else {
                        procV = transformBtoD(_parent.element.getChildren(deep));
                    }

                    //
                    if (debug) {
                        for (DElement d : procV){
                            d.printProperties();
                        }
                        if (procV.size() == 0){
                            log("Children array is empty");
                        }
                    }
                    //
                    processFilters(procV,_XattrList,_Xattrs);
                } catch (Exception e){
                    log(_displayName + " has disappeared - SUCCESS");
                    allOK = true;
                }
                curAt++;
            }
            if (!allOK){
                throw new Exception(_displayName + " is still displayed - FAILURE");
            }
            return procV;
        } else {
            while (!allOK && (curAt <= _attempts)){
                log("Waiting attempt #" + curAt + " of " + _attempts);
                if (curAt > 1) {
                    Thread.sleep(timeoutForATry);
                }
                try{
                    if (_parent == null) {
//                        procV = transformWtoD(uia.getDesktopWindows());
//                        List<AutomationPanel> apl = uia.getDesktopObjects();
//                        for (AutomationPanel k : apl){
//                            procV.add(new DElement(k));
//                        }
                        DElement dt = new DElement(uia.getDesktop());
                        List<AutomationBase> abl = dt.element.getChildren(false);
                        procV.addAll(transformBtoD(abl));
                    } else {
                        procV = transformBtoD(_parent.element.getChildren(deep));
                    }
                    //
                    if (debug) {
                        for (DElement d : procV){
                            d.printProperties();
                        }
                        if (procV.size() == 0){
                            log("Children array is empty");
                        }
                    }
                    //
                    tempV = processFilters(procV, _XattrList, _Xattrs);
                    log(_displayName + " has appeared - SUCCESS");
                    allOK = true;
                } catch (Exception e) {
                    //nothing here
                }
                curAt++;
            }
            if (!allOK) {
                throw new Exception(_displayName + " nas not appeared in time - FAILURE");
            }
            return tempV;
        }

    }
    public static DElement gimMeP(DElement _parent,String _displayName,int _attempts, String _XattrList, String..._Xattrs) throws Exception{
        DElement reso;
        try{
            reso = gimMePN(_parent,_displayName,_attempts,0,_XattrList,_Xattrs);
        } catch (Exception e){
            throw new Exception("DElement " + _displayName + " was not found");
        }
        return reso;
    }

    public void setForeground() throws Exception{
        WindowHandler.setForegroundWindow(this);
    }

    public static DElement gimMePN(DElement _parent, String _displayName, int _attempts, int _i, String _XattrList, String..._Xattrs) throws Exception{
        Vector<DElement> toR = gimMePV(_parent,_displayName,_attempts,_XattrList,_Xattrs);
        if (!(_XattrList.contains("D"))){
            if (toR.size() >= _i + 1) {
                return toR.get(_i);
            } else {
                throw new Exception("There are no such (" + (_i + 1) + ") windows. Total count is " + toR.size());
            }
        } else {
                return null;
        }
    }
//    public static DElement gimMePm(DElement _parent, String _attrList, String..._attrs) throws Exception{
//        DElement reso;
//        try{
//            reso = gimMePmN(_parent,0,_attrList,_attrs);
//        } catch (Exception e){
//            throw new Exception("DElement not found");
//        }
//        return reso;
//    }
//    public static DElement gimMePmN(DElement _parent, int _i, String _attrList, String..._attrs) throws Exception{
//        Vector<DElement> toR = gimMePmV(_parent,_attrList,_attrs);
//        if (toR.size() >= _i + 1) {
//            return toR.get(_i);
//        } else {
//            throw new Exception("There are no such (" + (_i + 1) + ") windows. Total count is " + toR.size());
//        }
//    }
//    public static Vector<DElement> gimMePmV(DElement _parent, String _attrList, String..._attrs) throws Exception{
//        if (_parent == null){
//            return gimMeDPmV(_attrList,_attrs);
//        } else {
//            Vector<DElement> rV = transformBtoD(_parent.element.getChildren(false));
//            return processFilters(rV, _attrList,_attrs);
//        }
//    }
//    public static DElement gimMeDPm(String _attrList, String..._attrs) throws Exception{
//        DElement reso;
//        try{
//            reso = gimMeDPmN(0,_attrList,_attrs);
//        } catch (Exception e){
//            throw new Exception("DElement not found");
//        }
//        return reso;
//    }
//    public static DElement gimMeDPmN(int _i, String _attrList, String..._attrs) throws Exception{
//        Vector<DElement> toR = gimMeDPmV(_attrList,_attrs);
//        if (toR.size() >= _i + 1) {
//            return toR.get(_i);
//        } else {
//            throw new Exception("There are no such (" + (_i + 1) + ") windows. Total count is " + toR.size());
//        }
//    }
//    public static Vector<DElement> gimMeDPmV(String _attrList, String..._attrs) throws Exception{
//        UIAutomation uia = UIAutomation.getInstance();
//        List<AutomationWindow> lr = uia.getDesktopWindows();
//        Vector<DElement> l = transformWtoD(lr);
//        return processFilters(l, _attrList,_attrs);
//    }

    public void showMinWindow() throws Exception{
        WindowHandler.showMinWindow(this);
    }

    private static Vector<DElement> filterVector(Vector<DElement> _list, String _name, String _className,
                                                 String _automationID, String _controlType, Boolean _hasKeyboardFocus,
                                                 Boolean _isEnabled, Boolean _isKeyboardFocusable, Boolean _isOffscreen,
                                                 Boolean _isPassword, String _localizedControlType, String _runtimeId,
                                                 boolean _strict)
                                                    throws Exception{
        Vector<DElement> res = new Vector<>();
        for (DElement q : _list){
            boolean getinto = true;
            if (!(_name == null)){
                try{
                    if (_strict){
                        if (!(q.getName().equals(_name))){
                            getinto = false;
                        }
                    } else {
                        if (!(q.getName().contains(_name))){
                            getinto = false;
                        }
                    }
                } catch (Exception e){
//                    logW("Failed to get Name property value");
                    getinto = false;
                }
            }
            if (getinto){
                if (!(_className == null)){
                    try{
                        if (_strict){
                            if (!(q.getClassName().equals(_className))){
                                getinto = false;
                            }
                        } else {
                            if (!(q.getClassName().contains(_className))){
                                getinto = false;
                            }
                        }
                    } catch (Exception e){
//                        logW("Failed to get ClassName property value");
                        getinto = false;
                    }
                }
            }
            if (getinto){
                if (!(_automationID == null)){
                    try{
                        if (_strict){
                            if (!(q.getAutomationId().equals(_automationID))){
                                getinto = false;
                            }
                        } else {
                            if (!(q.getAutomationId().contains(_automationID))){
                                getinto = false;
                            }
                        }
                    } catch (Exception e){
//                        logW("Failed to get AutomationId property value");
                        getinto = false;
                    }
                }
            }
            if (getinto){
                if (!(_controlType == null)){
                    try{
                        if (_strict){
                            if (!(q.getControlType().equals(_controlType))){
                                getinto = false;
                            }
                        } else {
                            if (!(q.getControlType().contains(_controlType))){
                                getinto = false;
                            }
                        }
                    } catch (Exception e){
//                        logW("Failed to get ControlType property value");
                        getinto = false;
                    }
                }
            }
            if (getinto){
                if (!(_hasKeyboardFocus == null)){
                    try{
                        if (!(q.getHasKeyboardFocus() == _hasKeyboardFocus)){
                            getinto = false;
                        }
                    } catch (Exception e){
//                        logW("Failed to get HasKeyboardFocus property value");
                        getinto = false;
                    }
                }
            }
            if (getinto){
                if (!(_isEnabled == null)){
                    try{
                        if (!(q.getIsEnabled() == _isEnabled)){
                            getinto = false;
                        }
                    } catch (Exception e){
//                        logW("Failed to get IsEnabled property value");
                        getinto = false;
                    }
                }
            }
            if (getinto){
                if (!(_isOffscreen == null)){
                    try{
                        if (!(q.getIsOffscreen() == _isOffscreen)){
                            getinto = false;
                        }
                    } catch (Exception e){
//                        logW("Failed to get IsOffscreen property value");
                        getinto = false;
                    }
                }
            }
            if (getinto){
                if (!(_isKeyboardFocusable == null)){
                    try{
                        if (!(q.getIsKeyboardFocusable() == _isKeyboardFocusable)){
                            getinto = false;
                        }
                    } catch (Exception e){
//                        logW("Failed to get IsKeyboardFocusable property value");
                        getinto = false;
                    }
                }
            }
            if (getinto){
                if (!(_isPassword == null)){
                    try{
                        if (!(q.getIsPassword() == _isPassword)){
                            getinto = false;
                        }
                    } catch (Exception e){
//                        logW("Failed to get IsPassword property value");
                        getinto = false;
                    }
                }
            }
            if (getinto){
                if (!(_localizedControlType == null)){
                    try{
                        if (!(q.getLocalizedControlType().equals(_localizedControlType))){
                            getinto = false;
                        }
                    } catch (Exception e){
//                        logW("Failed to get LocalizedControlType property value");
                        getinto = false;
                    }
                }
            }
            if (getinto){
                if (!(_runtimeId == null)){
                    try{
                        if (!(q.getRuntimeId().equals(_runtimeId))){
                            getinto = false;
                        }
                    } catch (Exception e){
//                        logW("Failed to get RuntimeId property value");
                        getinto = false;
                    }
                }
            }

            if (getinto){
                res.add(q);
            }
        }
        if (res.isEmpty()) {
            throw new Exception("No items have passed the filter");
        }
        return res;

    }
    private static Vector<DElement> transformWtoD(List<AutomationWindow> l){
        Vector<DElement> vec = new Vector<>();
        for (AutomationWindow q : l){
            vec.add(new DElement(q));
        }
        return vec;
    }
    private static Vector<DElement> transformBtoD(List<AutomationBase> l){
        Vector<DElement> vec = new Vector<>();
        for (AutomationBase q : l){
            vec.add(new DElement(q));
        }
        return vec;
    }
//    public static Vector<DElement> gimMeDV(String _name, String _className) throws Exception{
//        UIAutomation uia = UIAutomation.getInstance();
//        List<AutomationWindow> lr = uia.getDesktopWindows();
//        Vector<DElement> l = transformWtoD(lr);
//        Vector<DElement> res;
//        try {
//            res = filterVector(l,_name,_className, null, null,null,
//                    null,null,null,null,null,
//                    null, true);
//        } catch (Exception e){
//            throw new Exception("Filter failed");
//        }
//        if (res.size() == 0){
//            throw new Exception("No such elements");
//        }
//        return res;
//    }
//    /**
//     * Returns first window from desktop (by name, classname)
//     * name, classname can be null or empty
//     */
//    public static DElement gimMeD(String _name, String _className) throws Exception{
//        DElement reso;
//        try{
//            reso = gimMeDN(_name,_className,0);
//        } catch (Exception e){
//            throw new Exception("Window not found");
//        }
//        return reso;
//    }

    /**
     * Returns i- window from desktop
     * @param _i starts from 0 (as conventional for Java)
     */
    public static DElement gimMeDN(String _name, String _className, int _i) throws Exception{
        UIAutomation uia = UIAutomation.getInstance();
        List<AutomationWindow> lr = uia.getDesktopWindows();
        Vector<DElement> l = transformWtoD(lr);
        Vector<DElement> res = filterVector(l,_name,_className, null, null,null,
                null,null,null,null,null,
                null, true);
        if (res.size() >= _i + 1){
            return res.get(_i);
        } else {
            throw new Exception("There are no such (" + (_i + 1) + ") windows. Total count is " + res.size());
        }
    }

    public static DElement gimMe(DElement _parent, String _name, String _className) throws Exception{
        DElement reso;
        try{
            reso = gimMeN(_parent,_name,_className,0);
        } catch (Exception e){
            throw new Exception("DElement not found");
        }
        return reso;
    }
//    public static Vector<DElement> gimMeV(DElement _parent, String _name, String _className) throws Exception{
//        if (_parent == null){
//            return gimMeDV(_name,_className);
//        } else {
//            List<AutomationBase> lr = _parent.element.getChildren(false);
//            Vector<DElement> res;
//            Vector<DElement> l = transformBtoD(lr);
//            try {
//                res = filterVector(l, _name, _className, null, null,null,
//                        null,null,null,null,null,
//                        null, true);
//            } catch (Exception e){
//                throw new Exception("Filtering failed");
//            }
//            if (res.size() == 0){
//                throw new Exception("No such elements");
//            }
//            return res;
//        }
//    }
    public static DElement gimMeN(DElement _parent, String _name, String _className, int _i) throws Exception{
        if (_parent == null){
            return gimMeDN(_name,_className,_i);
        } else {
            List<AutomationBase> lr = _parent.element.getChildren(false);
            Vector<DElement> l = transformBtoD(lr);
            Vector<DElement> res = filterVector(l,_name,_className, null, null,null,
                    null,null,null,null,null,
                    null, true);
            if (res.size() >= _i + 1){
                return res.get(_i);
            } else {
                throw new Exception("There are no such (" + (_i + 1) + ") elements. Total count is " + res.size());
            }
        }
    }

    //property getters
    public String getTypeAutomation() {
        return typeAutomation;
    }
    public String getAutomationId() throws Exception{
        return element.getElement().getPropertyValue(UIA_AutomationIdPropertyId).toString();
    }
    public String getCenterPoint() throws Exception{
        return element.getElement().getPropertyValue(UIA_CenterPointPropertyId).toString();
    }
    public String getClassName() throws Exception{
        return element.getElement().getPropertyValue(UIA_ClassNamePropertyId).toString();
    }
    public WinDef.POINT getClickablePoint() throws Exception {
        return element.getClickablePoint();
    }
    public String getClickablePointDE() throws Exception{
        System.out.println("Using DE method: working is not guaranteed");
        return element.getElement().getPropertyValue(UIA_ClickablePointPropertyId).toString();
    }
    public String getControlType() throws Exception{
        return element.getElement().getPropertyValue(UIA_ControlTypePropertyId).toString();
    }
    public boolean getHasKeyboardFocus() throws Exception{
        String res = element.getElement().getPropertyValue(UIA_HasKeyboardFocusPropertyId).toString();
        if (res.equals("0")){
            return false;
        }
        if (res.equals("-1")){
            return true;
        }
        throw new Exception("Property value not found: " + res);
    }
    public boolean getIsEnabled() throws Exception {
        String res = element.getElement().getPropertyValue(UIA_IsEnabledPropertyId).toString();
        if (res.equals("0")){
            return false;
        }
        if (res.equals("-1")){
            return true;
        }
        throw new Exception("Property value not found: " + res);
    }
    public boolean getIsKeyboardFocusable() throws Exception {
        String res = element.getElement().getPropertyValue(UIA_IsKeyboardFocusablePropertyId).toString();
        if (res.equals("0")){
            return false;
        }
        if (res.equals("-1")){
            return true;
        }
        throw new Exception("Property value not found: " + res);
    }
    public boolean getIsOffscreen() throws Exception {
        String res = element.getElement().getPropertyValue(UIA_IsOffscreenPropertyId).toString();
        if (res.equals("0")){
            return false;
        }
        if (res.equals("-1")){
            return true;
        }
        throw new Exception("Property value not found: " + res);
    }
    public boolean getIsPassword() throws Exception {
        String res = element.getElement().getPropertyValue(UIA_IsPasswordPropertyId).toString();
        if (res.equals("0")){
            return false;
        }
        if (res.equals("-1")){
            return true;
        }
        throw new Exception("Property value not found: " + res);
    }
    public String getLocalizedControlType() throws Exception {
        return element.getElement().getPropertyValue(UIA_LocalizedControlTypePropertyId).toString();
    }
    public String getName() throws Exception {
        return element.getElement().getPropertyValue(UIA_NamePropertyId).toString();
    }
    public String getNativeWindowHandleStr() throws Exception {
        return element.getElement().getPropertyValue(UIA_NativeWindowHandlePropertyId).toString();
    }
    public String getNativeWindowHandleHEXStr() throws Exception {
        return element.getNativeWindowHandle().toString();
    }
    public WinDef.HWND getNativeWindowHandle() throws Exception {
        return element.getNativeWindowHandle();
    }
    public String getProcessId() throws Exception{
        return element.getElement().getPropertyValue(UIA_ProcessIdPropertyId).toString();
    }
    public String getProviderDescription() throws Exception{
        return element.getElement().getPropertyValue(UIA_ProviderDescriptionPropertyId).toString();
    }
    public String getRuntimeId() throws Exception{
        return element.getElement().getPropertyValue(UIA_RuntimeIdPropertyId).toString();
    }

    private void p(String s){
        System.out.print(s);
    }
    private void pe(String s){
        System.out.println(s);
    }
    public void printPropertiesMM(){
        pe("___________________________");
        pe("Gained property list:");
        try{
            p("AutomationId: "); pe(element.getElement().getAutomationId());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("ClassName: "); pe(element.getClassName());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("ClickablePoint: "); pe("POINT: x=" + element.getClickablePoint().x + " y=" + element.getClickablePoint().y);
            p("ClickablePoint(toString): ");pe(element.getClickablePoint().toString());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("ControlType: "); pe(Integer.toString(element.getElement().getControlType()));
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("IsEnabled: ");
            if (element.isEnabled()) {
                pe("True");
            } else {
                pe("False");
            }
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("IsPassword: ");
            if (element.getElement().isPassword()) {
                pe("True");
            } else {
                pe("False");
            }
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("LocalizedControlType: "); pe(element.getElement().localizedControlType());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("Name: "); pe(element.getName());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("NativeWindowHandle: "); pe(element.getNativeWindowHandle().toString());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("ProcessId: "); pe(element.getProcessId().toString());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("ProviderDescription: "); pe(element.getProviderDescription());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("RuntimeId array: ");
            for (int i : element.getRuntimeId()){
                pe(Integer.toString(i));
            }
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        pe("==============================");
    }
    public void printPropertiesDE(){
        pe("___________________________");
        pe("Full known property list:");
        p("Type automation (internal): "); pe(getTypeAutomation());
        try{
            p("AutomationId: "); pe(getAutomationId());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("CenterPoint: "); pe(getCenterPoint());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("ClassName: "); pe(getClassName());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("ClickablePoint: "); pe(getClickablePointDE());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("ControlType: "); pe(getControlType());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("HasKeyboardFocus: ");
            if (getHasKeyboardFocus()) {
                pe("True");
            } else {
                pe("False");
            }
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("IsEnabled: ");
            if (getIsEnabled()) {
                pe("True");
            } else {
                pe("False");
            }
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("IsKeyboardFocusable: ");
            if (getIsKeyboardFocusable()) {
                pe("True");
            } else {
                pe("False");
            }
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("IsOffscreen: ");
            if (getIsOffscreen()) {
                pe("True");
            } else {
                pe("False");
            }
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("IsPassword: ");
            if (getIsPassword()) {
                pe("True");
            } else {
                pe("False");
            }
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("LocalizedControlType: "); pe(getLocalizedControlType());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("Name: "); pe(getName());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("NativeWindowHandle: "); pe(getNativeWindowHandleStr());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("ProcessId: "); pe(getProcessId());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("ProviderDescription: "); pe(getProviderDescription());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("RuntimeId: "); pe(getRuntimeId());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        pe("==============================");
    }
    public void printProperties(){
        pe("___________________________");
        pe("Full known property list:");
        p("Type automation (internal): "); pe(getTypeAutomation());
        try{
            p("AutomationId: "); pe(getAutomationId());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("CenterPoint: "); pe(getCenterPoint());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("ClassName: "); pe(getClassName());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("ClickablePoint: "); pe("POINT: x=" + getClickablePoint().x + " y=" + getClickablePoint().y);
            p("ClickablePoint(toString): ");pe(getClickablePoint().toString());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("ControlType: "); pe(getControlType());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("HasKeyboardFocus: ");
            if (getHasKeyboardFocus()) {
                pe("True");
            } else {
                pe("False");
            }
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("IsEnabled: ");
            if (getIsEnabled()) {
                pe("True");
            } else {
                pe("False");
            }
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("IsKeyboardFocusable: ");
            if (getIsKeyboardFocusable()) {
                pe("True");
            } else {
                pe("False");
            }
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("IsOffscreen: ");
            if (getIsOffscreen()) {
                pe("True");
            } else {
                pe("False");
            }
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("IsPassword: ");
            if (getIsPassword()) {
                pe("True");
            } else {
                pe("False");
            }
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("LocalizedControlType: "); pe(getLocalizedControlType());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("Name: "); pe(getName());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("NativeWindowHandle: "); pe(getNativeWindowHandleStr());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("NativeWindowHandleHEXStr: "); pe(getNativeWindowHandleHEXStr());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }


        try{
            p("ProcessId: "); pe(getProcessId());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("ProviderDescription: "); pe(getProviderDescription());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        try{
            p("RuntimeId: "); pe(getRuntimeId());
        } catch (Exception e) {
            pe("ERROR:" + e.getMessage());
        }
        pe("==============================");

    }

    public void sendKeysMM(CharSequence...charSequences) throws Exception{
        System.out.println("Quite the same as setValue");
        if (typeAutomation.equals("edit")){
            AutomationEditBox eb = (AutomationEditBox) element;
            eb.setValue(charSequences.toString());
        }
    }
    public void sendKeys(CharSequence...charSequences) throws Exception{
        KeyboardHandler.sendKeysToElement(this,false,charSequences);
    }
    public void sendKeysRaw(CharSequence...charSequences) throws Exception{
        KeyboardHandler.sendKeysToElement(this,true,charSequences);
    }
    public String getEditText() throws Exception{
        AutomationEditBox juy = (AutomationEditBox) element;
        try{
            return juy.getText();
        } catch (Exception e) {
            return juy.getValue();
        }

    }
    public void setValue(String _value) throws Exception{
        if (typeAutomation.equals("edit")){
            setEditValue(_value);
        }
    }
    public void setEditValue (String _value) throws Exception{
        setFocus();
        click();
        KeyboardHandler.sendKeysHere(true,"^a{DEL}");
        Thread.sleep(100);
        KeyboardHandler.sendKeysHere(_value);
    }
    public Size getWindowSize() throws Exception{
        WinDef.RECT ber = WindowHandler.getWindowRECT(this);
        return new Size(ber.right - ber.left, ber.bottom - ber.top);
    }

    /**
     * Deal with standard RF combobox. For other comboboxes another implementation could be required
     * @param _value value to select
     * @param _dispItemCount a number of items that are displayed simultaneously
     * @param _numVal if there are several items with the same name, specify the order number of it.
     *                if there is only one (or you want just to select first), specify 0 here (Java conventional).
     */
    public void setRFComboboxValue(String _value, int _dispItemCount, int _numVal) throws Exception{
        setFocus();
        DElement cbLst;
        try{
            cbLst = gimMe(this,getName(),"ComboLBox");
        } catch (Exception e){
            try{
                cbLst = gimMe(this,"","ComboLBox");
            } catch (Exception e1) {
                //opening combobox
                DElement opBut = gimMe(this,"Open",null);
                opBut.click();
                Thread.sleep(100);
                try{
                    cbLst = gimMe(this,getName(),"ComboLBox");
                } catch (Exception e2){
                    try{
                        cbLst = gimMe(this,"","ComboLBox");
                    } catch (Exception e3) {
                        throw new Exception("Opening combobox failed");
                    }
                }
            }
        }
        int nItem = 0;
        int totCnt = 0;
        int cntItem = 0;
        for (AutomationBase j : cbLst.element.getChildren(false)){
            totCnt++;
            if (nItem == 0){
                if (j.getName().equals(_value)){
                    if (_numVal == cntItem){
                        nItem = totCnt;
                    } else {
                        cntItem++;
                    }
                }
            }
        }
        if (nItem == 0) {
            try{
                DElement clBut = gimMe(this,"Close",null);
                clBut.click();
                Thread.sleep(100);
            } catch (Exception e){

            }
            String exText = "The item '" + _value + "' is not present in combobox";
            if (_numVal > 1) {
                exText = "There is no " + _numVal + "- item '" + _value + "' in combobox";
            }
            throw new Exception(exText);
        }
        if (totCnt > _dispItemCount){
            //going up
            KeyboardHandler.sendKeysHere(true,"{HOME}");
            Thread.sleep(1000);
            //scrolling down
            int nScrolls = nItem - _dispItemCount - 1;
            if (nScrolls > 0) {
                boolean scBarFound = false;
                for (AutomationBase el : cbLst.element.getChildren(false)){
                    if (el.getName().equals("Vertical")){
                        if (el.getElement().localizedControlType().equals("scroll bar")){
                            //if (el.getElement().getPropertyValue(UIA_LocalizedControlTypePropertyId).equals("scroll bar")){
                            scBarFound = true;
                            try{
                                DElement dnBtn = gimMe(new DElement(el),"Line down",null);
                                for (int i = 1; i <= nScrolls; i++){
                                    dnBtn.click();
                                    Thread.sleep(50);
                                }
                            } catch (Exception e){
                                try{
                                    DElement clBut = gimMe(this,"Close",null);
                                    clBut.click();
                                    Thread.sleep(100);
                                } catch (Exception exx){

                                }
                                throw new Exception("Scrolling failed: " + e.getMessage());
                            }

                            break;
                        }
                    }
                }
                if (!scBarFound){
                    try{
                        DElement clBut = gimMe(this,"Close",null);
                        clBut.click();
                        Thread.sleep(100);
                    } catch (Exception e){

                    }
                    throw new Exception("Vertical scroll bar not found");
                }
            }
        }
        DElement reso = gimMeN(cbLst,_value,null,_numVal);
        reso.click();
    }

    /**
     * for checkboxes
     */
    public boolean getCheckedState() throws Exception {
        AutomationCheckBox j = new AutomationCheckBox(this.element.getElement());
        if (j.getToggleState() == ToggleState.On){
            return true;
        } else {
            return false;
        }
    }
    public void clickMM() throws Exception{
        if (typeAutomation.equals("button")){
            AutomationButton butt = (AutomationButton) element;
            butt.click();
        }
    }
    public void click() throws Exception{
        //this.setFocus();
        log("Clicking");
        Thread.sleep(10);
        WinDef.POINT jj = this.getClickablePoint();
        if (jj.x == 0 && jj.y == 0){
            clickRECTCenter();
        } else {
            MouseHandler.moveMouseTo(jj);
            Thread.sleep(10);
            MouseHandler.clickLeft();
        }
        Thread.sleep(10);
    }
    public void clickRECTCenter() throws Exception{
        WinDef.RECT herc = this.element.getBoundingRectangle();
        int x = herc.left + (herc.right - herc.left) / 2;
        int y = herc.top + (herc.bottom - herc.top) / 2;
        WinDef.POINT jj = new WinDef.POINT(x,y);
        MouseHandler.moveMouseTo(jj);
        MouseHandler.clickLeft();
    }
    public WinDef.RECT getRECT() throws Exception{
        return this.element.getBoundingRectangle();
    }
    public void doubleClick() throws Exception{
        MouseHandler.moveMouseTo(getClickablePoint());
        MouseHandler.doubleClick();
    }
    public void contextClick() throws Exception{
        MouseHandler.moveMouseTo(getClickablePoint());
        MouseHandler.clickRight();
    }
    public void clickLeftOffscreen(int _x, int _y) throws Exception{
        int xx = getClickablePoint().x + _x;
        int yy = getClickablePoint().y + _y;
        MouseHandler.moveMouseTo(xx,yy);
        MouseHandler.clickLeft();
    }
    public void contextClickOffscreen(int _x, int _y) throws Exception{
        int xx = getClickablePoint().x + _x;
        int yy = getClickablePoint().y + _y;
        MouseHandler.moveMouseTo(xx,yy);
        MouseHandler.clickRight();
    }
    public void contextClickRECTLeftTopOffscreen(int _x, int _y) throws Exception{
        WinDef.RECT herc = this.element.getBoundingRectangle();
        int x = herc.left + _x;
        int y = herc.top + _y;
        WinDef.POINT jj = new WinDef.POINT(x,y);
        MouseHandler.moveMouseTo(jj);
        MouseHandler.clickRight();
    }
    public void clickRECTLeftTopOffscreen(int _x, int _y) throws Exception{
        WinDef.RECT herc = this.element.getBoundingRectangle();
        int x = herc.left + _x;
        int y = herc.top + _y;
        WinDef.POINT jj = new WinDef.POINT(x,y);
        MouseHandler.moveMouseTo(jj);
        MouseHandler.clickLeft();
    }

    public void setFocus() throws Exception{
        element.getElement().setFocus();
    }
}
