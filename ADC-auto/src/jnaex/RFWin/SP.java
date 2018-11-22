package jnaex.RFWin;

/**
 * Created by Autotester on 3/27/2018.
 * Search presets class
 */
public class SP {
    public String descriptionName;
    public String name;
    public boolean nameStrict;
    public String automationID;
    public boolean automationIDStrict;
    public String className;
    public boolean classNameStrict;
    public String isEnabled;
    public String hasKeyboardFocus;
    public String isKeyboardFocusable;
    public String localizedControlType;
    public String isOffscreen;
    public String isPassword;
    public String runtimeID;
    public String controlType;
    public boolean controlTypeStrict;
    public String timeout;
    public String defaultSearchString;

    public SP(String description, String _XAttrList, String..._XAttrs){
        descriptionName = description;
        if ((!_XAttrList.contains("n")) && (!_XAttrList.contains("N"))){
            name = "";
            nameStrict = false;
        }
        if ((!_XAttrList.contains("a")) && (!_XAttrList.contains("A"))){
            automationID = "";
            automationIDStrict = false;
        }
        if ((!_XAttrList.contains("c")) && (!_XAttrList.contains("C"))){
            className = "";
            classNameStrict = false;
        }
        if (!_XAttrList.contains("e")){
            isEnabled = "true";
        }
        if (!_XAttrList.contains("h")){
            hasKeyboardFocus = "false";
        }
        if (!_XAttrList.contains("k")){
            isKeyboardFocusable = "true";
        }
        if (!_XAttrList.contains("l")){
            localizedControlType = "";
        }
        if (!_XAttrList.contains("o")){
            isOffscreen = "false";
        }
        if (!_XAttrList.contains("p")){
            isPassword = "false";
        }
        if (!_XAttrList.contains("r")){
            runtimeID = "";
        }
        if ((!_XAttrList.contains("t")) && (!_XAttrList.contains("T"))){
            controlType = "";
            controlTypeStrict = false;
        }
        if (!_XAttrList.contains("U")){
            runtimeID = "3000";
        }
        defaultSearchString = _XAttrList;
        if (!(_XAttrList.isEmpty())) {
            int curr = -1;
            for (int i = 0; i < _XAttrList.length(); i++){
                char zzz = _XAttrList.charAt(i);
                if ((zzz != '!') && (zzz != 'd') && (zzz != 'D')){
                    curr++;
                    String cmp = _XAttrs[curr];
                    //String cmp = _XAttrs[i];
                    switch (zzz){
                        case 'n':
                            name = cmp;
                            nameStrict = true;
                            break;
                        case 'N':
                            name = cmp;
                            nameStrict = false;
                            break;
                        case 'a':
                            automationID = cmp;
                            automationIDStrict = true;
                            break;
                        case 'A':
                            automationID = cmp;
                            automationIDStrict = false;
                            break;
                        case 'c':
                            className = cmp;
                            classNameStrict = true;
                            break;
                        case 'C':
                            className = cmp;
                            classNameStrict = false;
                            break;
                        case 'e':
                            isEnabled = cmp;
                            break;
                        case 'h':
                            hasKeyboardFocus = cmp;
                            break;
                        case 'k':
                            isKeyboardFocusable = cmp;
                            break;
                        case 'l':
                            localizedControlType = cmp;
                            break;
                        case 'o':
                            isOffscreen = cmp;
                            break;
                        case 'p':
                            isPassword = cmp;
                            break;
                        case 'r':
                            runtimeID = cmp;
                            break;
                        case 't':
                            controlType = cmp;
                            controlTypeStrict = true;
                            break;
                        case 'T':
                            controlType = cmp;
                            controlTypeStrict = false;
                            break;
                        case 'U':
                            timeout = cmp;
                            break;
                        default:
                            Proc.logW("Incorrect item is created");
                            break;
                    }
                }
            }
        }



    }
}
