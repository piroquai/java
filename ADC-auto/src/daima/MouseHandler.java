package daima;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import mmarquee.automation.controls.mouse.AutomationMouse;

import static com.sun.jna.platform.win32.WinUser.INPUT.INPUT_MOUSE;
import com.sun.jna.platform.win32.WinUser.INPUT;
import com.sun.jna.platform.win32.WinDef.POINT;
import com.sun.jna.platform.win32.WinDef.DWORD;

/**
 * Created by Autotester on 9/27/2017.
 */
public class MouseHandler {
    //mouse event flags value
    private static long MOUSEEVENTF_LEFTDOWN = 0x0002L;
    private static long MOUSEEVENTF_LEFTUP = 0x0004L;
    private static long MOUSEEVENTF_RIGHTDOWN = 0x0008L;
    private static long MOUSEEVENTF_RIGHTUP = 0x0010L;
    private static long MOUSEEVENTF_MIDDLEDOWN = 0x0020L;
    private static long MOUSEEVENTF_MIDDLEUP = 0x0040L;
    private static long MOUSEEVENTF_ABSOLUTE = 0x8000L;
    private static long MOUSEEVENTF_MOVE = 0x0001L;
    private static long MOUSEEVENTF_MOVE_NOCOALESCE = 0x2000L;
    private static long MOUSEEVENTF_VIRTUALDESK = 0x4000L;

    private static int delays = 150;
    public static void setDelays(int _delays){
        delays = _delays;
    }
    public static void moveMouseToMM(POINT _point) throws Exception{
        AutomationMouse mm = AutomationMouse.getInstance();
        mm.setLocation(_point);
    }
    public static void moveMouseTo(POINT _point) throws Exception{
        moveMouseTo(_point.x,_point.y);
    }

    private static WinDef.LONG getAbsoluteCoordX(int _x) throws Exception{
        return new WinDef.LONG(_x * 65536 / User32.INSTANCE.GetSystemMetrics(User32.SM_CXSCREEN));
    }
    private static WinDef.LONG getAbsoluteCoordY(int _y) throws Exception{
        return new WinDef.LONG(_y * 65536 / User32.INSTANCE.GetSystemMetrics(User32.SM_CYSCREEN));
    }
    public static void moveMouseTo(int x, int y) throws Exception{
        INPUT input = new INPUT();
        input.type = new DWORD(INPUT_MOUSE);

        input.input.setType("mi");
        input.input.mi.dx = getAbsoluteCoordX(x);
        input.input.mi.dy = getAbsoluteCoordY(y);
        input.input.mi.mouseData = new DWORD(0);
        input.input.mi.dwFlags = new DWORD(MOUSEEVENTF_MOVE | MOUSEEVENTF_VIRTUALDESK | MOUSEEVENTF_ABSOLUTE);

        input.input.mi.time = new DWORD(0);

        INPUT[] inArray = {input};

        int cbSize = input.size();
        DWORD nInputs = new DWORD(1);
        //DWORD result = User32.INSTANCE.SendInput(nInputs,inArray,cbSize);
        User32.INSTANCE.SendInput(nInputs,inArray,cbSize);
        Thread.sleep(delays);
    }


    //TODO:research to use current Mouse Pointer
    private static void moveMouseToOffset(POINT _point) throws Exception {
        moveMouseToOffset(_point.x,_point.y);
    }
    private static void moveMouseToOffset(int x, int y) throws Exception{
        INPUT input = new INPUT();
        input.type = new DWORD(INPUT_MOUSE);

        input.input.setType("mi");
        input.input.mi.dx = getAbsoluteCoordX(x);
        input.input.mi.dy = getAbsoluteCoordY(y);
        input.input.mi.mouseData = new DWORD(0);
        input.input.mi.dwFlags = new DWORD(MOUSEEVENTF_MOVE);

        input.input.mi.time = new DWORD(0);

        INPUT[] inArray = {input};

        int cbSize = input.size();
        DWORD nInputs = new DWORD(1);
        //DWORD result = User32.INSTANCE.SendInput(nInputs,inArray,cbSize);
        User32.INSTANCE.SendInput(nInputs,inArray,cbSize);
        Thread.sleep(delays);
    }
    public static void clickLeft() throws Exception{
        click(0,1, 0);
    }

    private static long getDownEvent(int _mButton) throws Exception{
        switch (_mButton){
            case 0:
                return MOUSEEVENTF_LEFTDOWN;
            case 1:
                return MOUSEEVENTF_RIGHTDOWN;
            case 2:
                return MOUSEEVENTF_MIDDLEDOWN;
            default:
                throw new Exception("No such button: " + _mButton);
        }
    }
    private static long getUpEvent(int _mButton) throws Exception{
        switch (_mButton){
            case 0:
                return MOUSEEVENTF_LEFTUP;
            case 1:
                return MOUSEEVENTF_RIGHTUP;
            case 2:
                return MOUSEEVENTF_MIDDLEUP;
            default:
                throw new Exception("No such button: " + _mButton);
        }
    }
    /**
     * click _times times _mButton mouse button
     * @param _mButton 0 - left, 1 - right, 2- middle
     * @param _times number of times to click
     * @param _interval interval between clicks in milliseconds
     */
    public static void click(int _mButton,int _times, int _interval) throws Exception{
        INPUT inputD = new INPUT();
        INPUT inputU = new INPUT();

        inputD.type = new DWORD(INPUT_MOUSE);
        inputU.type = new DWORD(INPUT_MOUSE);

        inputD.input.setType("mi");
        inputU.input.setType("mi");
        inputD.input.mi.mouseData = new DWORD(0);
        inputU.input.mi.mouseData = new DWORD(0);
        inputD.input.mi.dwFlags = new DWORD(getDownEvent(_mButton));
        inputU.input.mi.dwFlags = new DWORD(getUpEvent(_mButton));
        inputD.input.mi.time = new DWORD(0);
        inputU.input.mi.time = new DWORD(0);

        INPUT[] inArrayD = {inputD};
        INPUT[] inArrayU = {inputU};
        DWORD nInput = new DWORD(1);
        int cbSizeD = inputD.size();
        int cbSizeU = inputU.size();

        for (int i = 1; i <= _times; i++) {
            User32.INSTANCE.SendInput(nInput,inArrayD,cbSizeD);
            User32.INSTANCE.SendInput(nInput,inArrayU,cbSizeU);
            Thread.sleep(_interval);
        }
        Thread.sleep(delays);
    }
    public static void clickRight() throws Exception{
        click(1,1, 0);
    }
    public static void doubleClick() throws Exception{
        click(0,2, 0);
    }
    public static void pressMouseButton(int _mButton) throws Exception{
        INPUT inputD = new INPUT();

        inputD.type = new DWORD(INPUT_MOUSE);

        inputD.input.setType("mi");
        inputD.input.mi.mouseData = new DWORD(0);
        inputD.input.mi.dwFlags = new DWORD(getDownEvent(_mButton));
        inputD.input.mi.time = new DWORD(0);

        INPUT[] inArray = {inputD};
        int cbSize = inputD.size();

        DWORD nInput = new DWORD(1);

        User32.INSTANCE.SendInput(nInput,inArray,cbSize);

        Thread.sleep(delays);
    }
    public static void releaseMouseButton(int _mButton) throws Exception{
        INPUT inputU = new INPUT();

        inputU.type = new DWORD(INPUT_MOUSE);

        inputU.input.setType("mi");
        inputU.input.mi.mouseData = new DWORD(0);
        inputU.input.mi.dwFlags = new DWORD(getUpEvent(_mButton));
        inputU.input.mi.time = new DWORD(0);

        INPUT[] inArray = {inputU};
        int cbSize = inputU.size();

        DWORD nInput = new DWORD(1);

        User32.INSTANCE.SendInput(nInput,inArray,cbSize);

        Thread.sleep(delays);
    }

    /**
     * Move to _from, click and hold left button, move to _to, release left button
     * @param _from starting POINT
     * @param _to ending POINT
     */
    public static void dragCursor(WinDef.POINT _from, WinDef.POINT _to) throws Exception{
        moveMouseTo(_from);
        pressMouseButton(0);
        Thread.sleep(delays);
        moveMouseTo(_to);
        releaseMouseButton(0);
    }
    public static void dragCursor(int _fromX, int _fromY, int _toX, int _toY) throws Exception{
        moveMouseTo(_fromX,_fromY);
        pressMouseButton(0);
        Thread.sleep(delays);
        moveMouseTo(_toX,_toY);
        Thread.sleep(delays);
        releaseMouseButton(0);
/*        INPUT inputM1 = new INPUT();
        INPUT inputD = new INPUT();
        INPUT inputM2 = new INPUT();
        INPUT inputU = new INPUT();

        inputM1.type = new DWORD(INPUT_MOUSE);
        inputD.type = new DWORD(INPUT_MOUSE);
        inputM2.type = new DWORD(INPUT_MOUSE);
        inputU.type = new DWORD(INPUT_MOUSE);

        inputM1.input.setType("mi");
        inputD.input.setType("mi");
        inputM2.input.setType("mi");
        inputU.input.setType("mi");
        inputM1.input.mi.dx = getAbsoluteCoordX(_fromX);
        inputD.input.mi.dx = getAbsoluteCoordX(_fromX);
        inputM2.input.mi.dx = getAbsoluteCoordX(_toX);
        inputU.input.mi.dx = getAbsoluteCoordX(_toX);

        inputM1.input.mi.dy = getAbsoluteCoordY(_fromY);
        inputD.input.mi.dy = getAbsoluteCoordY(_fromY);
        inputM2.input.mi.dy = getAbsoluteCoordY(_toY);
        inputU.input.mi.dy = getAbsoluteCoordY(_toY);

        inputM1.input.mi.mouseData = new DWORD(0);
        inputD.input.mi.mouseData = new DWORD(0);
        inputM2.input.mi.mouseData = new DWORD(0);
        inputU.input.mi.mouseData = new DWORD(0);

        inputM1.input.mi.dwFlags = new DWORD(MOUSEEVENTF_ABSOLUTE | MOUSEEVENTF_MOVE | MOUSEEVENTF_VIRTUALDESK);
        inputD.input.mi.dwFlags = new DWORD(MOUSEEVENTF_LEFTDOWN);
        inputM2.input.mi.dwFlags = new DWORD(MOUSEEVENTF_ABSOLUTE | MOUSEEVENTF_MOVE | MOUSEEVENTF_VIRTUALDESK);
        inputU.input.mi.dwFlags = new DWORD(MOUSEEVENTF_LEFTUP);
        //inputU.input.mi.dwFlags = new DWORD(getUpEvent(0));

        inputM1.input.mi.time = new DWORD(0);
        inputD.input.mi.time = new DWORD(0);
        inputM2.input.mi.time = new DWORD(0);
        inputU.input.mi.time = new DWORD(0);

        INPUT[] inArrayM1 = {inputM1};
        INPUT[] inArrayD = {inputD};
        INPUT[] inArrayM2 = {inputM2};
        INPUT[] inArrayU = {inputU};
//        INPUT[] inArray = {inputU};
        int cbSizeM1 = inputM1.size();
        int cbSizeD = inputD.size();
        int cbSizeM2 = inputM2.size();
        int cbSizeU = inputU.size();

        DWORD nInput = new DWORD(1);

//        User32.INSTANCE.SendInput(nInput,inArray,cbSize);
        User32.INSTANCE.SendInput(nInput,inArrayM1,cbSizeM1);
        Thread.sleep(delays);
        User32.INSTANCE.SendInput(nInput,inArrayD,cbSizeD);
        Thread.sleep(5*delays);
        User32.INSTANCE.SendInput(nInput,inArrayM2,cbSizeM2);
        Thread.sleep(10 * delays);
        User32.INSTANCE.SendInput(nInput,inArrayU,cbSizeU);
        Thread.sleep(delays);
*/
    }
}
