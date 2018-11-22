package jnaex.RFWin;

import daima.DElement;

import java.util.Vector;

/**
 * Created by Autotester on 4/4/2018.
 */
public class SPProcessor {
    static DElement parent;
    static String description;
    static int attempts;
    static int index;
    static String XattrList;
    static Vector<String> Xattrs;

    static String ignoredXattrList;
    static String enforcedXattrList;
    static Vector<String> enforcedXattrs;

    static SP preset;

    public static void clear(){
        parent = null;
        description = "";
        attempts = 0;
        index = 0;
        XattrList = "";
        Xattrs = new Vector<>();
        ignoredXattrList = "";
        enforcedXattrList = "";
        enforcedXattrs = new Vector<>();
        preset = null;
    }
    public static void assertOK() throws Exception{
        if (attempts == 0) { throw new Exception("SPProcessor: number of attempts are not set");}
        if (preset == null) { throw new Exception("SPProcessor: preset to process is not set");}
    }
    public static void setPreset(SP _preset){
        preset = _preset;
    }
    public static void setIgnoredXattrList(String _ignoredXattrList){
        ignoredXattrList = _ignoredXattrList;
    }
    public static void setAttempts(int _attempts){
        attempts = _attempts;
    }
    public static void setIndex(int _index){
        index = _index;
    }
    public static void setEnforcedXattrList(String _enforcedXattrList){
        enforcedXattrList = _enforcedXattrList;
    }
    public static void setEnforcedXattrs(String..._enforcedXattrs){
        enforcedXattrs.clear();
        for (String s : _enforcedXattrs){
            enforcedXattrs.add(s);
        }
    }
    public static void setParent(DElement _parent){
        parent = _parent;
    }
    public static void setDescription(String _description){
        description = _description;
    }
    public static DElement getParent(){
        return parent;
    }
    public static String getDescription(){
        return description;
    }
    public static int getAttempts(){
        return attempts;
    }
    public static int getIndex(){
        return index;
    }
    public static String getXattrList(){
        return XattrList;
    }
    public static Vector<String> getXattrs(){
        return Xattrs;
    }
    public static void processData() throws Exception{
        assertOK();
        if (description.isEmpty()){
            description = preset.descriptionName;
        }
        String totalXattrList1 = preset.defaultSearchString;
        if (!(ignoredXattrList.isEmpty())) {
            for (int i = 0; i < ignoredXattrList.length(); i++){
                char zzz = ignoredXattrList.charAt(i);
                while (totalXattrList1.contains("" + zzz)){
                    totalXattrList1 = Proc.getStringExcludingChar(totalXattrList1,zzz);
                }
            }
        }
        if (!(enforcedXattrList.isEmpty())) {
            for (int i = 0; i < enforcedXattrList.length(); i++){
                char zzz = enforcedXattrList.charAt(i);
                while (totalXattrList1.contains("" + zzz)){
                    totalXattrList1 = Proc.getStringExcludingChar(totalXattrList1,zzz);
                }
            }
        }
        Xattrs.clear();
        if (!(totalXattrList1.isEmpty())){
            for (int i = 0; i < totalXattrList1.length(); i++){
                char zzz = totalXattrList1.charAt(i);
                switch (zzz){
                    case 'a': Xattrs.add(preset.automationID); break;
                    case 'A': Xattrs.add(preset.automationID); break;
                    case 'c': Xattrs.add(preset.className); break;
                    case 'C': Xattrs.add(preset.className); break;
                    case 'e': Xattrs.add(preset.isEnabled); break;
                    case 'h': Xattrs.add(preset.hasKeyboardFocus); break;
                    case 'k': Xattrs.add(preset.isKeyboardFocusable); break;
                    case 'l': Xattrs.add(preset.localizedControlType); break;
                    case 'n': Xattrs.add(preset.name); break;
                    case 'N': Xattrs.add(preset.name); break;
                    case 'o': Xattrs.add(preset.isOffscreen); break;
                    case 'p': Xattrs.add(preset.isPassword); break;
                    case 'r': Xattrs.add(preset.runtimeID); break;
                    case 't': Xattrs.add(preset.controlType); break;
                    case 'T': Xattrs.add(preset.controlType); break;
                    case 'U': Xattrs.add(preset.timeout); break;
                    default: Proc.logW("Incorrect parameter at: " + preset.descriptionName);
                }
            }
        }
        if (!(enforcedXattrList.isEmpty())){
            for (String z : enforcedXattrs){
                Xattrs.add(z);
            }
        }
        XattrList = totalXattrList1 + enforcedXattrList;

    }

}
