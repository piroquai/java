package daima;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.WORD;
import com.sun.jna.platform.win32.WinUser.INPUT;
import static com.sun.jna.platform.win32.WinUser.INPUT.INPUT_KEYBOARD;
/**
 * Created by Autotester on 9/27/2017.
 */
public class KeyboardHandler {
    private static final DWORD KEYEVENTF_KEYUP = new DWORD(0x0002);
    private static final DWORD KEYEVENTF_KEYUP_NOT_PRESENT = new DWORD(0); //if keyup is not present, the it is keydown


    private static final WORD VK_CANCEL = new WORD(0x03); //Control-break processing
    private static final WORD VK_BACK = new WORD(0x08); //backspace
    private static final WORD VK_TAB = new WORD(0x09); //TAB
    private static final WORD VK_RETURN = new WORD(0x0D); //Enter
    private static final WORD VK_SHIFT = new WORD(0x10); //Shift
    private static final WORD VK_CONTROL = new WORD(0x11); //Control
    private static final WORD VK_MENU = new WORD(0x12); //Alt
    private static final WORD VK_PAUSE = new WORD(0x13); //PAUSE key
    private static final WORD VK_CAPITAL = new WORD(0x14); //CAPS LOCK
    private static final WORD VK_ESCAPE = new WORD(0x1B); //Escape
    private static final WORD VK_SPACE = new WORD(0x20); //Space
    private static final WORD VK_PRIOR = new WORD(0x21); //PageUp
    private static final WORD VK_NEXT = new WORD(0x22); //PageDown
    private static final WORD VK_END = new WORD(0x23); //End
    private static final WORD VK_HOME = new WORD(0x24);//Home
    private static final WORD VK_LEFT = new WORD(0x25);//Left arrow
    private static final WORD VK_UP = new WORD(0x26);//Up arrow
    private static final WORD VK_RIGHT = new WORD(0x27);//Right arrow
    private static final WORD VK_DOWN = new WORD(0x28);//Down arrow
    private static final WORD VK_SNAPSHOT = new WORD(0x2C);//Print screen
    private static final WORD VK_INSERT = new WORD(0x2D);//Insert
    private static final WORD VK_DELETE = new WORD(0x2E);//Delete key
    private static final WORD VK_HELP = new WORD(0x2F); //HELP key
    private static final WORD VK_MULTIPLY = new WORD(0x6A);//MULTIPLY * key
    private static final WORD VK_ADD = new WORD(0x6B);//ADD + key
    private static final WORD VK_SUBTRACT = new WORD(0x6D);//SUBTRACT - key
    private static final WORD VK_DIVIDE = new WORD(0x6F);//DIVIDE / key
    private static final WORD VK_F1 = new WORD(0x70);//F1 key
    private static final WORD VK_F2 = new WORD(0x71);//F2 key
    private static final WORD VK_F3 = new WORD(0x72);//F3 key
    private static final WORD VK_F4 = new WORD(0x73);//F4 key
    private static final WORD VK_F5 = new WORD(0x74);//F5 key
    private static final WORD VK_F6 = new WORD(0x75);//F6 key
    private static final WORD VK_F7 = new WORD(0x76);//F7 key
    private static final WORD VK_F8 = new WORD(0x77);//F8 key
    private static final WORD VK_F9 = new WORD(0x78);//F9 key
    private static final WORD VK_F10 = new WORD(0x79);//F10 key
    private static final WORD VK_F11 = new WORD(0x7A);//F11 key
    private static final WORD VK_F12 = new WORD(0x7B);//F12 key
    private static final WORD VK_F13 = new WORD(0x7C);//F13 key
    private static final WORD VK_F14 = new WORD(0x7D);//F14 key
    private static final WORD VK_F15 = new WORD(0x7E);//F15 key
    private static final WORD VK_F16 = new WORD(0x7F);//F16 key
    private static final WORD VK_NUMLOCK = new WORD(0x90); //NUM LOCK key
    private static final WORD VK_SCROLL = new WORD(0x91);//SCROLL LOCK key



    private static void parseCharSequences(CharSequence...charSequences) throws Exception{
        for (CharSequence seq : charSequences) {
            while (seq.length() > 0) {
                int cutterPos = 0; //all the substring to the left of this position is cut (inclusive)
                char yohh = seq.charAt(0);
                switch (yohh){
                    case '{':
                        int posl = seq.toString().indexOf('}');
                        if (posl == -1){
                            throw new Exception("Incorrect char sequence: '}' is absent");
                        }
                        doCommandInFBraces(seq.toString().substring(1,posl));
                        cutterPos = posl;
                        break;
                    case '+': //shift
                        {
                            char h = seq.charAt(1);
                            if (getLetterCode(h) == 0){
                                if (h == '('){
                                    CharSequence ss =seq.subSequence(2,seq.length());
                                    int nd = ss.toString().indexOf(')');
                                    if (nd != -1){
                                        boolean good = true;
                                        for (int i = 0; i < nd; i++){
                                            if (getLetterCode(ss.charAt(i)) == 0) {
                                                good = false;
                                                break;
                                            }
                                        }
                                        cutterPos = nd + 2; //+( should be also included
                                        if (good){
                                            keyDown(VK_SHIFT);
                                            for (int i = 0; i < nd; i++){
                                                keyPress(new WORD(getLetterCode(ss.charAt(i))));
                                            }
                                            keyUp(VK_SHIFT);
                                        } else {
                                            throw new Exception("Latter sequence is incorrect: " + seq.toString());
                                        }
                                    } else {
                                        throw new Exception("Latter sequence is incorrect: " + seq.toString());
                                    }
                                }

//                                throw new Exception("Cannot enter such key: " + Character.toString(h));
                            }
                            else {
                                keyDown(VK_SHIFT);
                                keyPress(new WORD(getLetterCode(h)));
                                keyUp(VK_SHIFT);
                                cutterPos = 1;
                            }
                        }
                        break;
                    case '^': //control
                    {
                        char h = seq.charAt(1);
                        if (getLetterCode(h) == 0){
                            if (h == '('){
                                CharSequence ss =seq.subSequence(2,seq.length());
                                int nd = ss.toString().indexOf(')');
                                if (nd != -1){
                                    boolean good = true;
                                    for (int i = 0; i < nd; i++){
                                        if (getLetterCode(ss.charAt(i)) == 0) {
                                            good = false;
                                            break;
                                        }
                                    }
                                    cutterPos = nd + 2; //+( should be also included
                                    if (good){
                                        keyDown(VK_CONTROL);
                                        for (int i = 0; i < nd; i++){
                                            keyPress(new WORD(getLetterCode(ss.charAt(i))));
                                        }
                                        keyUp(VK_CONTROL);
                                    } else {
                                        throw new Exception("Latter sequence is incorrect: " + seq.toString());
                                    }
                                } else {
                                    throw new Exception("Latter sequence is incorrect: " + seq.toString());
                                }
                            }

//                                throw new Exception("Cannot enter such key: " + Character.toString(h));
                        }
                        else {
                            keyDown(VK_CONTROL);
                            keyPress(new WORD(getLetterCode(h)));
                            keyUp(VK_CONTROL);
                            cutterPos = 1;
                        }
                    }
                    break;
                    case '%': //alt
                    {
                        char h = seq.charAt(1);
                        if (getLetterCode(h) == 0){
                            if (h == '('){
                                CharSequence ss =seq.subSequence(2,seq.length());
                                int nd = ss.toString().indexOf(')');
                                if (nd != -1){
                                    boolean good = true;
                                    for (int i = 0; i < nd; i++){
                                        if (getLetterCode(ss.charAt(i)) == 0) {
                                            good = false;
                                            break;
                                        }
                                    }
                                    cutterPos = nd + 2; //+( should be also included
                                    if (good){
                                        keyDown(VK_MENU);
                                        for (int i = 0; i < nd; i++){
                                            keyPress(new WORD(getLetterCode(ss.charAt(i))));
                                        }
                                        keyUp(VK_MENU);
                                    } else {
                                        throw new Exception("Latter sequence is incorrect: " + seq.toString());
                                    }
                                } else {
                                    throw new Exception("Latter sequence is incorrect: " + seq.toString());
                                }
                            }

//                                throw new Exception("Cannot enter such key: " + Character.toString(h));
                        }
                        else {
                            keyDown(VK_MENU);
                            keyPress(new WORD(getLetterCode(h)));
                            keyUp(VK_MENU);
                            cutterPos = 1;
                        }
                    }
                    break;
                    default:
                        if (getLetterCode(yohh) == 0){
                            throw new Exception("Cannot enter such key: " + Character.toString(yohh));
                        }
                        keyPress(new WORD(getLetterCode(yohh)));
                }
                seq = cut(seq,cutterPos);
            }
        }
    }
    private static int getLetterCode(char _c){
        int res = 0;
        if ((_c >= '0') && (_c <= '9')){
            res = _c;
        }
        if ((_c >= 'A') && (_c <= 'Z')) {
            res = _c;
        }
        if ((_c >= 'a') && (_c <= 'z')) {
            res = Character.toUpperCase(_c);
        }
        if ((_c == ';') || (_c == ':')) {
            res = 0xBA;
        }
        if ((_c == '=')){
            res = 0xBB;
        }
        if ((_c == ',')){
            res = 0xBC;
        }
        if ((_c == '-')){
            res = 0xBD;
        }
        if ((_c == '.')){
            res = 0xBE;
        }
        if ((_c == '/')){
            res = 0xBF;
        }
        if ((_c == '`')){
            res = 0xC0;
        }
        if ((_c == '[')){
            res = 0xDB;
        }
        if ((_c == '\\')){
            res = 0xDC;
        }
        if ((_c == ']')){
            res = 0xDD;
        }
        if ((_c == '\'')){
            res = 0xDE;
        }
        if ((_c == '~')){
            res = VK_RETURN.intValue(); //for MSDN - connectivity
        }
        if ((_c == '\n') || (_c == '\r')) {
            res = VK_RETURN.intValue();
        }
        if ((_c == ' ')){
            res = VK_SPACE.intValue();
        }

        return res;
    }
    private static CharSequence cut(CharSequence _seq, int _cutterPos){
        return _seq.subSequence(_cutterPos + 1,_seq.length());
    }
    private static WORD getCommandKey(String _commandKey) throws Exception{
        _commandKey = _commandKey.toUpperCase();
        if (_commandKey.equals("BACKSPACE") || _commandKey.equals("BS") || _commandKey.equals("BKSP")){
            return VK_BACK;
        }
        if (_commandKey.equals("BREAK")) {
            return VK_PAUSE;
            //return VK_CANCEL; //for ctrl+break command
        }
        if (_commandKey.equals("CAPSLOCK")) {
            return VK_CAPITAL;
        }
        if (_commandKey.equals("DELETE") || _commandKey.equals("DEL")){
            return VK_DELETE;
        }
        if (_commandKey.equals("DOWN")){
            return VK_DOWN;
        }
        if (_commandKey.equals("END")){
            return VK_END;
        }
        if (_commandKey.equals("ENTER")){
            return VK_RETURN;
        }
        if (_commandKey.equals("ESC")){
            return VK_ESCAPE;
        }
        if (_commandKey.equals("HELP")){
            return VK_HELP;
        }
        if (_commandKey.equals("HOME")){
            return VK_HOME;
        }
        if (_commandKey.equals("INSERT") || _commandKey.equals("INS")){
            return VK_INSERT;
        }
        if (_commandKey.equals("LEFT")){
            return VK_LEFT;
        }
        if (_commandKey.equals("NUMLOCK")){
            return VK_NUMLOCK;
        }
        if (_commandKey.equals("PGDN") || _commandKey.equals("PAGEDOWN")){ //more than MSDN :-)
            return VK_NEXT;
        }
        if (_commandKey.equals("PGUP") || _commandKey.equals("PAGEUP")){ //more than MSDN :-)
            return VK_PRIOR;
        }
        if (_commandKey.equals("SPACE")){ //more than MSDN :-)
            return VK_SPACE;
        }
        if (_commandKey.equals("PRTSC")){
            return VK_SNAPSHOT;
        }
        if (_commandKey.equals("RIGHT")){
            return VK_RIGHT;
        }
        if (_commandKey.equals("SCROLLLOCK")){
            return VK_SCROLL;
        }
        if (_commandKey.equals("TAB")){
            return VK_TAB;
        }
        if (_commandKey.equals("UP")){
            return VK_UP;
        }
        if (_commandKey.equals("F1")){
            return VK_F1;
        }
        if (_commandKey.equals("F2")){
            return VK_F2;
        }
        if (_commandKey.equals("F3")){
            return VK_F3;
        }
        if (_commandKey.equals("F4")){
            return VK_F4;
        }
        if (_commandKey.equals("F5")){
            return VK_F5;
        }
        if (_commandKey.equals("F6")){
            return VK_F6;
        }
        if (_commandKey.equals("F7")){
            return VK_F7;
        }
        if (_commandKey.equals("F8")){
            return VK_F8;
        }
        if (_commandKey.equals("F9")){
            return VK_F9;
        }
        if (_commandKey.equals("F10")){
            return VK_F10;
        }
        if (_commandKey.equals("F11")){
            return VK_F11;
        }
        if (_commandKey.equals("F12")){
            return VK_F12;
        }
        if (_commandKey.equals("F13")){
            return VK_F13;
        }
        if (_commandKey.equals("F14")){
            return VK_F14;
        }
        if (_commandKey.equals("F15")){
            return VK_F15;
        }
        if (_commandKey.equals("F16")){
            return VK_F16;
        }
        if (_commandKey.equals("ADD")){
            return VK_ADD;
        }
        if (_commandKey.equals("SUBTRACT")){
            return VK_SUBTRACT;
        }
        if (_commandKey.equals("MULTIPLY")){
            return VK_MULTIPLY;
        }
        if (_commandKey.equals("DIVIDE")){
            return VK_DIVIDE;
        }
        if (_commandKey.length() == 1 && getLetterCode(_commandKey.charAt(0)) != 0){
            return new WORD(getLetterCode(_commandKey.charAt(0)));
        }

        throw new Exception("Command " + _commandKey + " was not identified");
    }
    private static void doCommandInFBraces(String _command) throws Exception{
        int cnt = 1;
        int ind = _command.indexOf(' ');
        if (ind != -1) {
            cnt = Integer.parseInt(_command.substring(ind + 1));
            _command = _command.substring(0,ind);
        }
        for (int i = 1; i <=cnt; i++){
            keyPress(getCommandKey(_command));
        }
    }
    private static void keyDown(WORD _btnCode) throws Exception{
        INPUT input = new INPUT();
        input.type = new DWORD(INPUT_KEYBOARD);

        input.input.setType("ki");

        input.input.ki.wScan = new WORD(0);
        input.input.ki.time = new DWORD(0);

        input.input.ki.wVk = _btnCode;
        input.input.ki.dwFlags = KEYEVENTF_KEYUP_NOT_PRESENT;

        User32.INSTANCE.SendInput(new DWORD(1), (INPUT[]) input.toArray(1), input.size());
    }
    private static void keyUp(WORD _btnCode) throws Exception {
        INPUT input = new INPUT();
        input.type = new DWORD(INPUT_KEYBOARD);

        input.input.setType("ki");

        input.input.ki.wScan = new WORD(0);
        input.input.ki.time = new DWORD(0);

        input.input.ki.wVk = _btnCode;
        input.input.ki.dwFlags = KEYEVENTF_KEYUP;

        User32.INSTANCE.SendInput(new DWORD(1), (INPUT[]) input.toArray(1), input.size());
    }
    private static void keyPress(WORD _btnCode) throws Exception{
        keyDown(_btnCode);
        keyUp(_btnCode);
    }

    public static void sendKeysToElement(DElement _element, CharSequence...charSequences) throws Exception{
        sendKeysToElement(_element,false,charSequences);
    }
    public static void sendKeysToElement(DElement _element, boolean _raw,CharSequence...charSequences ) throws Exception{
        MouseHandler.moveMouseTo(_element.getClickablePoint());
        MouseHandler.clickLeft();
        sendKeysHere(_raw,charSequences);
    }
    public static void sendKeysToElementSlow(DElement _element, /*boolean _raw, */CharSequence...charSequences) throws Exception{
        MouseHandler.moveMouseTo(_element.getClickablePoint());
        MouseHandler.clickLeft();
        sendKeysHereSlow(charSequences);
    }
    public static void sendKeysHere(CharSequence...charSequences) throws Exception{
        sendKeysHere(false, charSequences);
    }
    public static void sendKeysHere(boolean _raw, CharSequence...charSequences) throws Exception{
        if (_raw){
            parseCharSequences(charSequences);
        } else {
            for (CharSequence seq : charSequences){
                for (int i = 0; i < seq.length(); i++){
                    parseCharSequences(transformCharToMSDN(seq.charAt(i)));
                    //DEBUG
                    //System.out.print(transformCharToMSDN(seq.charAt(i)));
                    //
                }
                //DEBUG
                //System.out.println();
                //
            }
        }
    }
    public static void sendKeysHereSlow(/*boolean _raw, */CharSequence...charSequences) throws Exception{
//        if (_raw){
//            parseCharSequences(charSequences);
//        } else {
            for (CharSequence seq : charSequences){
                for (int i = 0; i < seq.length(); i++){
                    Thread.sleep(50);
                    parseCharSequences(transformCharToMSDN(seq.charAt(i)));
                    //DEBUG
                    //System.out.print(transformCharToMSDN(seq.charAt(i)));
                    //
                }
                //DEBUG
                //System.out.println();
                //
            }
//        }
    }
    private static String transformCharToMSDN(char _c) throws Exception{
        String res = null;
        if ((_c >= '0') && (_c <= '9')){
            res = Character.toString(_c);
        }
        if ((_c >= 'A') && (_c <= 'Z')) {
            res = "+" + Character.toString(_c);
        }
        if ((_c >= 'a') && (_c <= 'z')) {
            res = Character.toString(_c);
        }
        if (_c == '\n') {
            res = "{ENTER}";
        }
        if (_c == '\r') {
            res = "";
        }
        if ((_c == ';')) {
            res = ";";
        }
        if (_c == ':') {
            res = "+;";
        }
        if ((_c == '=')){
            res = "=";
        }
        if ((_c == '+')){
            res = "+=";
        }
        if ((_c == ',')){
            res = ",";
        }
        if (_c == '<'){
            res = "+,";
        }
        if ((_c == '-')){
            res = "-";
        }
        if (_c == '_'){
            res = "+-";
        }
        if ((_c == '.')){
            res = ".";
        }
        if (_c == '>'){
            res = "+.";
        }
        if ((_c == '/')){
            res = "/";
        }
        if (_c == '?'){
            res = "+/";
        }
        if ((_c == '`')){
            res = "`";
        }
        if (_c == '~'){
            res = "+`";
        }
        if ((_c == '[')){
            res = "[";
        }
        if (_c == '{'){
            res = "+[";
        }
        if ((_c == '\\')){
            res = "\\";
        }
        if (_c == '|'){
            res = "+\\";
        }
        if ((_c == ']')){
            res = "]";
        }
        if (_c == '}'){
            res = "+]";
        }
        if ((_c == '\'')){
            res = "\'";
        }
        if (_c == '\"'){
            res = "+\'";
        }
        if (_c == '!'){
            res = "+1";
        }
        if (_c == '@'){
            res = "+2";
        }
        if (_c == '#'){
            res = "+3";
        }
        if (_c == '$'){
            res = "+4";
        }
        if (_c == '%'){
            res = "+5";
        }
        if (_c == '^'){
            res = "+6";
        }
        if (_c == '&'){
            res = "+7";
        }
        if (_c == '*'){
            res = "+8";
        }
        if (_c == '('){
            res = "+9";
        }
        if (_c == ')'){
            res = "+0";
        }
        if (_c == ' '){
            res = "{SPACE}";
        }
        if (res == null){
            throw new Exception("Char is empty");
        }
        return res;
    }

////                WinUser.INPUT input = new WinUser.INPUT();
////
////                input.type = new WinDef.DWORD(WinUser.INPUT.INPUT_KEYBOARD);
////                input.input.setType("ki"); // because setting INPUT_INPUT_KEYBOARD is not enough https://groups.google.com/d/msg/jna-users/NDBGwC1VZbU/cjYCQ1CjBwAJ
////                input.input.ki.wScan = new WinDef.WORD(0);
////                input.input.ki.time = new WinDef.DWORD(0);
////                input.input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR(0);
////
////                //Press 'a'
////                input.input.ki.wVk = new WinDef.WORD('A'); //0x41
////                input.input.ki.dwFlags = new WinDef.DWORD(0); //keydown
////
////                User32old.instance.SendInput(new WinDef.DWORD(1), (WinUser.INPUT[]) input.toArray(1), input.size());
////
////                //Release 'a'
////                input.input.ki.wVk = new WinDef.WORD('A'); //0x41
////                input.input.ki.dwFlags = new WinDef.DWORD(2); //keyup
////
////                User32old.instance.SendInput(new WinDef.DWORD(1), (WinUser.INPUT[]) input.toArray(1), input.size());
}
