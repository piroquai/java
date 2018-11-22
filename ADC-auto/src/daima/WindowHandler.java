package daima;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import mmarquee.automation.controls.AutomationWindow;

/**
 * Created by Autotester on 9/29/2017.
 */
public class WindowHandler {
    public static final int WPF_RESTORETOMAXIMIZED = 0x002;
    public static final int SW_SHOW = 5;
    public static void changeWindowPosition(DElement _elementWindow, int x, int y, int width, int height) throws Exception{
        HWND handle = _elementWindow.element.getNativeWindowHandle();
        User32.INSTANCE.SetWindowPos(handle,null,x,y,width,height,0);
    }
    public static WinDef.RECT getWindowRECT(DElement _elementWindow) throws Exception{
        HWND handle = _elementWindow.element.getNativeWindowHandle();
        WinDef.RECT juect = new WinDef.RECT();
        User32.INSTANCE.GetWindowRect(handle,juect);
        return juect;
    }
    public static void setForegroundWindow(DElement _elementWindow) throws Exception{
        HWND handle = _elementWindow.element.getNativeWindowHandle();
        User32.INSTANCE.SetForegroundWindow(handle);
    }
    public static void showMinWindow(DElement _elementWindow) throws Exception{
        WinUser.WINDOWPLACEMENT WP = new WinUser.WINDOWPLACEMENT();
        WP.showCmd = 5;
        WP.length = WP.size();
        setWindowPlacement(_elementWindow,WP);
    }
    private static void setWindowPlacement(DElement _elementWindow, WinUser.WINDOWPLACEMENT _WP) throws Exception{
        HWND handle = _elementWindow.element.getNativeWindowHandle();
        User32.INSTANCE.SetWindowPlacement(handle,_WP);
    }
    public static void destroyWindow(DElement _elementWindow) throws Exception{
        HWND handle = _elementWindow.element.getNativeWindowHandle();
        User32.INSTANCE.DestroyWindow(handle);
    }

}
