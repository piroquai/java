package jnaex.RFWin;

import com.sun.jna.platform.win32.WinDef;
import daima.KeyboardHandler;
import jnaex.RFWin.SearchPresets.MozillaFF;
import jnaex.RFWin.SearchPresets.Chrome;
import jnaex.RFWin.User.RFUser;
import mmarquee.demo.MainPowerpoint;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import daima.DElement;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Vector;

/**
 * Created by  Evgenia on 02-Aug-17.
 */
public class BrowserTestBlock extends SeleniumProc {
    public static String pathFirefox = "C:/Program Files (x86)/Mozilla Firefox/";
    public static String nameFirefox = "firefox.exe";
    public static String pathChrome = "C:/Program Files (x86)/Google/Chrome/Application/";
    public static String nameChrome = "chrome.exe";

    public static String top100Common = "C:/Users/Autotester/Desktop/top100Common.txt";
    public static String top100VPN = "C:/Users/Autotester/Desktop/top100VPN.txt";

    public static void setPathFirefox(String s){
        pathFirefox = s;
    }
    public static void setNameFirefox(String s){
        nameFirefox = s;
    }
    public static void setPathChrome(String s){
        pathChrome = s;
    }
    public static void setNameChrome(String s){
        nameChrome = s;
    }

    public static void setTop100Common(String s) {
        top100Common = s;}
    public static void setTop100VPN(String s) {top100VPN = s;}

    private BrowserTestBlock(){}

    private static String logString = "Browser Test Block: ";
    public static DElement gL(DElement _parent, SP _SP, int _attempts) throws Exception{
        return Proc.gL(_parent,_SP,_attempts);
    }
    public static DElement gNL(DElement _parent, SP _SP, int _attempts, int _index) throws Exception{
        return Proc.gNL(_parent,_SP,_attempts,_index);
    }
    public static Vector<DElement> gVL(DElement _parent, SP _SP, int _attempts) throws Exception{
        return Proc.gVL(_parent,_SP,_attempts);
    }

    public static DElement clickRfButtonChrome () throws Exception
    {
        try {

            DElement ssW = gL(null, Chrome.chromeWindow,2);

            DElement tmp1 = gL(ssW,Chrome.chromePart1,2);

            Vector <DElement> Vtmp2 = gVL(tmp1,Chrome.chromePart2,2);

            DElement tmp2 = Vtmp2.elementAt(1);


            Vector <DElement> Vtmp3 = gVL(tmp2,Chrome.chromePart3,2);

            DElement tmp3 = Vtmp3.elementAt(1);


            Vector <DElement> Vtmp4 = gVL(tmp3,Chrome.chromePart4,2);

            DElement tmp4 = Vtmp4.elementAt(1);


            DElement tmp5 = gL(tmp4,Chrome.chromeExt,2);


            DElement tmp6 = gL(tmp5,Chrome.chromeRFIcon,2);

            sleepy(5);

            return tmp6;

        } catch (Exception e) {
            logE("Procedure failed: " + e.getMessage());
            throw e;
        }

    }
    /*start page*/
    public static void clickOnPopUP (DElement _RFIcon, String _element, String _pwd) throws Exception
    {
        try {
            _RFIcon.click();
            DElement ssWs = gL(null, Chrome.chromeWindow,2);
            DElement popUp = gL(ssWs,Chrome.rfPopUp,2);
            DElement ssW = gL(popUp, Chrome.rfPopUp2,2);
            WinDef.RECT rect = ssW.getRECT();
            Chrome.pWidth = rect.right - rect.left;
            Chrome.pHeight = rect.bottom - rect.top;
            Chrome.x0 = rect.left;
            Chrome.y0 = rect.top;
            Robot robot = new Robot();
            switch(_element)
            {
                case "startPage" :
                    robot.mouseMove(Chrome.x0+Chrome.startPage.getKey(), Chrome.y0+Chrome.startPage.getValue());
                    sleepy (2);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    //что то про поиск элемента
                    sleepy(20);
                    break;
                case "dotsMenu" :
                    robot.mouseMove(Chrome.x0+Chrome.dotsMenu.getKey(), Chrome.y0+Chrome.dotsMenu.getValue());
                    sleepy (2);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    //поиск контестного окна
                    //закрытие попапа
                    ssWs.click();
                    sleepy (2);
                    break;
                case "search" :
                    robot.mouseMove(Chrome.x0+Chrome.search.getKey(), Chrome.y0+Chrome.search.getValue());
                    sleepy(2);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    //закрытие попапа
                    sleepy (2);
                    ssWs.click();
                    sleepy (2);
                    break;
                case "searchBtn" :
                    robot.mouseMove(Chrome.x0+Chrome.searchBtn.getKey(), Chrome.y0+Chrome.searchBtn.getValue());
                    sleepy(2);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    //закрытие попапа
                    sleepy (2);
                    ssWs.click();
                    sleepy (2);
                    break;
                case "logins" :
                    robot.mouseMove(Chrome.x0+Chrome.logins.getKey(), Chrome.y0+Chrome.logins.getValue());
                    log ("on logins");
                    sleepy(2);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    //контекстное окно
                    //закрытие попапа
                    sleepy (2);
                    ssWs.click();
                    sleepy (2);
                    break;
                case "bookmarks" :
                    robot.mouseMove(Chrome.x0+Chrome.bookmarks.getKey(), Chrome.y0+Chrome.bookmarks.getValue());
                    log ("on bookmarks");
                    sleepy(2);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    //контекстное окно
                    //закрытие попапа
                    sleepy (2);
                    ssWs.click();
                    sleepy (2);
                    break;
                case "safenotes" :
                    robot.mouseMove(Chrome.x0+Chrome.safenotes.getKey(), Chrome.y0+Chrome.safenotes.getValue());
                    log ("on safenotes");
                    sleepy(2);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    //контекстное окно
                    //закрытие попапа
                    sleepy (2);
                    ssWs.click();
                    sleepy (2);
                    break;
                case "setupFormFill" :
                    robot.mouseMove(Chrome.x0+Chrome.setupFormFill.getKey(), Chrome.y0+Chrome.setupFormFill.getValue());
                    sleepy(2);
                    /*robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    //контекстное окно
                    DElement newI = gL(null,Chrome.rfPopUp,2);*/
                    //закрытие попапа
                    sleepy (2);
                    ssWs.click();
                    sleepy (2);
                    break;
                case "save" :
                    robot.mouseMove(Chrome.x0+Chrome.save.getKey(), Chrome.y0+Chrome.save.getValue());
                    sleepy(2);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    //контекстное окно
                    sleepy (2);

                    DElement chr = gL(null,Chrome.rfPopUp,2);
                    sleepy (2);
                    DElement newLB = gL(chr,Chrome.saveLB,2);
                    sleepy (2);
                    DElement cancel = gL(newLB,Chrome.cancel,2);
                    sleepy (2);
                    cancel.click();
                    sleepy (2);
                    break;
                case "sync" :
                    robot.mouseMove(Chrome.x0+Chrome.sync.getKey(), Chrome.y0+Chrome.sync.getValue());
                    sleepy(2);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    //контекстное окно
                    sleepy (2);
                    EditorProc.handleSync();
                    sleepy (2);
                    //закрытие попапа
                    ssWs.click();
                    //_RFIcon.click();
                    sleepy (2);
                    break;
                case "generate" :
                    robot.mouseMove(Chrome.x0+Chrome.generate.getKey(), Chrome.y0+Chrome.generate.getValue());
                    sleepy(2);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    //контекстное окно
                    //закрытие попапа
                    sleepy (2);
                    ssWs.click();
                    sleepy (2);
                    break;
                case "securityCenter" :
                    robot.mouseMove(Chrome.x0+Chrome.securityCenter.getKey(), Chrome.y0+Chrome.securityCenter.getValue());
                    sleepy(2);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    //контекстное окно
                    sleepy (2);
                    EditorProc.closeRF8EditorSoft();
                    sleepy (2);
                    //закрытие попапа
                    ssWs.click();
                    sleepy (2);
                    break;
                case "logOut" :
                    robot.mouseMove(Chrome.x0+Chrome.logOut.getKey(), Chrome.y0+Chrome.logOut.getValue());
                    sleepy(2);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    sleepy (2);
                    _RFIcon.click();
                    sleepy (2);
                    DElement RMPW = gL(null,Chrome.RFMPWindow,2);
                    DElement field = gL(RMPW,Chrome.mpField,2);
                    field.click();
                    field.setEditValue(_pwd);
                    DElement loginB = gL(RMPW,Chrome.loginBtn,2);
                    loginB.click();
                    sleepy (5);
                    break;
                default :
                    break;
            }
        } catch (Exception e) {
            logE("Procedure failed: " + e.getMessage());
            throw e;
        }
    }



    private static DElement getStringedSubitem(Vector<DElement> vec, String name) throws Exception{
        Proc.setGLP(logString);
        boolean found = false;
        Vector<DElement> results = new Vector<>();
        for (DElement veci : vec){
            //DElement tmp = DElement.gimMeP(veci,"tmp",1,"n","");
            DElement tmp = Proc.gL(veci,MozillaFF.emptyNav,1);

            try {
                //tmp = DElement.gimMeP(tmp,"tmp1",1,"N",name);
                tmp = Proc.gL(tmp, new SP("tmp1","N",name),1);

                found = true;
                results.add(tmp);
            } catch (Exception e){
                //do nothing
            }
        }
        if (results.size() > 1){
            Proc.logW("There are several subitems for name '" + name + "'");
        }
        if (!found){
            throw new Exception("Subitem named '" + name + "' not found");
        } else {
            return results.get(0);
        }

    }


    public static void installRFaddonFF() throws Exception{
        logString = "Install RF add-on to Firefox browser";
        Proc.setGLP(logString);
        Proc.setLogBlockPrefix("installRFaddonFF");
        log("Starting procedure");
        Proc.closeFFBrowser();
        Proc.setLogBlockPrefix("installRFaddonFF-II");

        log("Launching Firefox");
        sleepy(5);
        ProcessBuilder pb = new ProcessBuilder(pathFirefox + nameFirefox);
        pb.start();
        sleepy(5);

        openBrowser("firefox");
        Proc.setLogBlockPrefix("installRFaddonFF-III");
        sleepy(5);
        //DElement ff = Proc.gimMeP(null, new ElemD("", "MozillaWindowClass", ""), "", 3, "", "na");
        DElement ff = Proc.gL(null, MozillaFF.FFW,3);

        ff.setForeground();
        log("Decadance has begun");
        //DElement ffMenu = DElement.gimMeP(ff,"FF Menu",1,"n","Navigation Toolbar");
        DElement ffMenu = Proc.gL(ff,MozillaFF.FFMenu,1);

        //DElement wrk = DElement.gimMeP(ffMenu,"FF button",1,"n","Firefox");
        DElement wrk = Proc.gL(ffMenu,MozillaFF.FFB,1);

        wrk.click();
        sleepy(2);
        //ffMenu = DElement.gimMeP(ff,"Group",1,"l","group");
        ffMenu = Proc.gL(ff,MozillaFF.G,1);
        //wrk = DElement.gimMeP(ffMenu,"Add-ons",1,"n","Add-ons");
        wrk = Proc.gL(ffMenu,MozillaFF.addOns,1);
        wrk.click();
        log("Going to Get add-on section (this should be the only group)");
        //ffMenu = DElement.gimMeP(ff,"Group",1,"l","group");
        ffMenu = Proc.gL(ff,MozillaFF.G,1);

        //ffMenu = DElement.gimMePN(ffMenu,"Nav1",1,1,"n","");
        ffMenu = Proc.gNL(ffMenu,MozillaFF.emptyNav,1,1);

        //ffMenu = DElement.gimMeP(ffMenu,"Nav2",1,"n","");
        ffMenu = Proc.gL(ffMenu,MozillaFF.emptyNav,1);

        //ffMenu = DElement.gimMeP(ffMenu,"Manager window",1,"n","Add-ons Manager");
        ffMenu = Proc.gL(ffMenu,MozillaFF.managerW,1);

        //wrk = DElement.gimMeP(ffMenu,"List",1,"l","list");
        wrk = Proc.gL(ffMenu,MozillaFF.L,1);

        //wrk = DElement.gimMeP(wrk,"Get add-ons",1,"n","Get Add-ons");
        wrk = Proc.gL(wrk,MozillaFF.getAddOns,1);

        //wrk = DElement.gimMeP(wrk,"Text",1,"n","Get Add-ons");
        wrk = Proc.gL(wrk,MozillaFF.getAddOns,1);

        //wrk = DElement.gimMeP(wrk,"Edit",1,"n","Get Add-ons");
        wrk = Proc.gL(wrk,MozillaFF.getAddOns,1);

        wrk.click();
        sleepy(2);
        //wrk = DElement.gimMePN(ffMenu,"Nav3",1,2,"n","");
        wrk = Proc.gNL(ffMenu,MozillaFF.emptyNav,1,2);

        //wrk = DElement.gimMeP(wrk,"Discover Add-ons",1,"n","Discover Add-ons");
        wrk = Proc.gL(wrk,MozillaFF.discoverAddOns,1);

        //wrk = DElement.gimMeP(wrk,"Nav4",1,"n","");
        wrk = Proc.gL(wrk,MozillaFF.emptyNav,1);

        wrk.click();
        sleepy(2);
        wrk = Proc.gL(wrk,MozillaFF.emptyNav,1);

        //wrk = DElement.gimMeP(wrk,"Nav6",1,"n","");
        wrk = Proc.gL(wrk,MozillaFF.emptyNav,1);

        //wrk = DElement.gimMePN(wrk,"Nav7",1,20,"n","");
        wrk = Proc.gNL(wrk,MozillaFF.emptyNav,1,1);

        //wrk = DElement.gimMeP(wrk,"See more",1,"n","See more add-ons!");
        wrk = Proc.gL(wrk,MozillaFF.seeMoreAddOns,2);

        //wrk = DElement.gimMeP(wrk,"See more",1,"n","See more add-ons!");
        wrk = Proc.gL(wrk,MozillaFF.seeMoreAddOns,2);
        /*KeyboardHandler.sendKeysHere(true,"{END}");
        sleepy(1);
        //wrk = DElement.gimMeP(wrk,"Nav5",1,"n","");
        wrk = Proc.gL(wrk,MozillaFF.emptyNav,1);

        //wrk = DElement.gimMeP(wrk,"Nav6",1,"n","");
        wrk = Proc.gL(wrk,MozillaFF.emptyNav,1);

        //wrk = DElement.gimMePN(wrk,"Nav7",1,20,"n","");
        wrk = Proc.gNL(wrk,MozillaFF.emptyNav,1,20);

        //wrk = DElement.gimMeP(wrk,"See more",1,"n","See more add-ons!");
        wrk = Proc.gL(wrk,MozillaFF.seeMoreAddOns,2);

        //wrk = DElement.gimMeP(wrk,"See more",1,"n","See more add-ons!");
        wrk = Proc.gL(wrk,MozillaFF.seeMoreAddOns,2);*/

        wrk.click();
        sleepy(5);

        //ffMenu = DElement.gimMeP(ff,"Group",1,"l","group");
        ffMenu = Proc.gL(ff,MozillaFF.G,1);

        //DElement tmp = DElement.gimMeP(ffMenu,"Add-ons",1,"dn","Add-ons for Firefox");
        DElement tmp = Proc.gL(ffMenu,MozillaFF.addOnsForFF,1);

        //tmp = DElement.gimMeP(tmp, "NavC1",1,"n","");
        tmp = Proc.gL(tmp,MozillaFF.emptyNav,1);

        //tmp = DElement.gimMeP(tmp, "NavC1",1,"n","");
        tmp = Proc.gL(tmp,MozillaFF.emptyNav,1);

        //tmp = DElement.gimMeP(tmp, "NavC1",1,"n","");
        tmp = Proc.gL(tmp,MozillaFF.emptyNav,1);

        KeyboardHandler.sendKeysToElement(tmp,true,"{HOME}");
        sleepy(0.5);

        //wrk = DElement.gimMeP(ffMenu,"Find add-ons combobox", 1, "dn","Find add-ons");
        wrk = Proc.gL(ffMenu,MozillaFF.findAddOnsCB,1);


        KeyboardHandler.sendKeysToElementSlow(wrk,"roboform password manager");
        sleepy(1);

        KeyboardHandler.sendKeysHere(true,"{ENTER}");
        sleepy(1);

        sleepy(4);

        //tmp = DElement.gimMeP(ffMenu,"NavX1n",1,"dN","RoboForm Password Manager RoboForm");
        tmp = Proc.gL(ffMenu,MozillaFF.RFPassManRF,1);

        //tmp = DElement.gimMeP(tmp,"NavX1n",1,"dN","RoboForm Password Manager RoboForm");
        tmp = Proc.gL(tmp,MozillaFF.RFPassManRF,1);

        //wrk = DElement.gimMeP(tmp,"RF PM Edit",1,"dN","RoboForm Password Manager");
        wrk = Proc.gL(tmp,MozillaFF.RFPassManE,1);


        wrk.click();
        sleepy(5);
        //ffMenu = DElement.gimMeP(ff,"Group",1,"l","group");
        ffMenu = Proc.gL(ff,MozillaFF.G,1);


        //find correct FF tab
        //Vector<DElement> tmpV = DElement.gimMePV(ffMenu,"minV1",1,"n","");
        Vector<DElement> tmpV = Proc.gVL(ffMenu,MozillaFF.emptyNav,1);


        tmp = getStringedSubitem(tmpV,"RoboForm Password Manager");



        //tmp = DElement.gimMeP(tmp,"NavX3",1,"dn","Find add-ons");
        tmp = Proc.gL(tmp, MozillaFF.findAddOnsCB,1);

        tmp.click();

        KeyboardHandler.sendKeysToElement(tmp,true,"{TAB 3}");

        sleepy(1);
        KeyboardHandler.sendKeysHere(true,"{ENTER}");
        sleepy(5);
        //ffMenu = DElement.gimMeP(ff,"Pane",1,"n","");
        ffMenu = Proc.gL(ff,MozillaFF.emptyNav,1);

        //wrk = DElement.gimMeP(ffMenu,"Add btn",1,"n","Add");
        wrk = Proc.gL(ffMenu,MozillaFF.addB,1);

        wrk.click();
        sleepy(2);
        Proc.closeFFBrowser();
        Proc.setLogBlockPrefix("installRFaddonFF-IV");
        log("Procedure complete");
    }

    public static void openBrowser(String _browser) throws Exception{
        Proc.setLogBlockPrefix("openBrowser(String)");
        if (_browser.equals("firefox"))
        {
            try {
                //DElement ssW = Proc.gimMeP(null, new ElemD("", "MozillaWindowClass", ""), "", 2, "", "na");
                DElement ssW = Proc.gL(null,MozillaFF.FFW,2);

                //DElement tmp = Proc.gimMeP(ssW, new ElemD("Navigation Toolbar", "", ""), "", 2, "", "ca");
                DElement tmp = Proc.gL(ssW,MozillaFF.FFMenu,2);

                //DElement tmp2 = Proc.gimMeP(tmp, new ElemD("Search or enter address", "", ""), "", 2, "", "ca");
                DElement tmp2 = Proc.gL(tmp,MozillaFF.searchOrEnterAddressE,2);

                tmp2.sendKeys("https://mail.ru");
                sleepy(1);
                //DElement tmp3 = Proc.gimMeP(tmp2, new ElemD("Go to the address in the Location Bar", "", ""), "", 2, "", "ca");
                DElement tmp3 = Proc.gL(tmp2,MozillaFF.goToAddressLoc,2);

                tmp3.click();

                sleepy(5);

            } catch (Exception e) {
                logE("Procedure failed: " + e.getMessage());
                throw e;
            }

        }

    }
    public static void openBrowser(WebDriver _webDriver, String _browser) throws Exception
    {
        Proc.setLogBlockPrefix("openBrowser(WebDriver,String)");
        if (_browser.equals("chrome")) {
            try {
                _webDriver.navigate().to("https://mail.ru/");
                sleepy(3);
                Random rnd = new Random(System.currentTimeMillis());

                int number = rnd.nextInt(1000);
                WebElement element = _webDriver.findElement(By.name("login"));
                element.sendKeys("qwerty" + number);
                element = _webDriver.findElement(By.name("password"));
                element.sendKeys(number + "qwerty");
                /** enter to open autologin pop up*/
                element.sendKeys(new CharSequence[]{Keys.ENTER});
                sleepy(2);
            } catch (Exception e) {
                logE("Procedure failed: " + e.getMessage());
                throw e;
            }
        }

    }

    public static void closeBrowser (String _browser) throws Exception{
        Proc.setLogBlockPrefix("closeBrowser(String)");
        String CloseFirefox = "{}[14]";//{}[1]{RoboForm}[1]{}[1]{}[1]{}[10]{}[1]{Save}[1]";
        String closeWar = "{}[4]";
        if (_browser.toLowerCase().equals("firefox"))
        {
            DElement ssW = Proc.gimMeP(null, new ElemD("", "MozillaWindowClass", ""), "Попытка закрыть мозилу", 2, "", "na");
            DElement cross = ApplicationProc.calculateParent(ssW, CloseFirefox);
            sleepy(5);
            cross.click();
            try {
                DElement warW = Proc.gimMeP(ssW, new ElemD("Confirm close", "MozillaDialogClass", ""), "попытка закрыть предупреждение мозилы, согласившись", 2, "", "na");
                //DElement btn = Proc.gimMeP(warW, new ElemD("Close tabs", "", ""), "попытка закрыть предупреждение мозилы о нескольких вкладках, согласившись", 2, "", "ca");
                DElement btn = ApplicationProc.calculateParent(warW, closeWar);
                sleepy(5);
                btn.click();
                log("Firefox was closed");
            } catch (Exception e) {
                log("Firefox was closed without warning");
            }
        }
    }
    public static void closeBrowser (WebDriver _webDriver, String _browser) throws Exception
    {
        Proc.setLogBlockPrefix("closeBrowser(WebDriver,String)");
        try {
            if (_browser.equals("chrome")) {
                _webDriver.close();
            }
        }
        catch (Exception e){
            logE("Procedure failed: " + e.getMessage());
            throw e;
        }
    }

    public static void autoCreateLogin(String _passcName, RFUser _user) throws Exception {
        logString = "Create login automatically";
        Proc.setLogBlockPrefix("autoCreateLogin");

        log("Starting procedure");
        try {

            /* auto login pop up*/
            DElement ssW = Proc.gimMeP(null, new ElemD("", "Chrome_WidgetWin_1", ""), "", 2, "", "na");
            DElement rfE = Proc.gimMeP(ssW, new ElemD("", "#32770", ""), "Extension pop up", 2, "", "na");
            try {
                Proc.gimMeP(rfE, new ElemD ("Enter your Master Password", "", ""), "Window for entering MP", 2, "", "ca");
                DElement enterMP = Proc.gimMeP(rfE, new ElemD("Enter password", "Edit", "1048"), "Window for entering MP", 2, "", "na");
                enterMP.setEditValue(_user.getPassword());
                sleepy(1);
                DElement okb = Proc.gimMeP(rfE, new ElemD("OK", "", ""), "Window for entering MP", 2, "", "ca");
                okb.click();
                sleepy(8);
            }
            catch (Exception e) {
                log("account was not log off");
            }
            DElement edit = Proc.gimMeP(rfE, new ElemD("", "Edit", ""), "Edit", 2, "", "na");
            edit.setEditValue(_passcName);
            sleepy(1);
            DElement saveB = Proc.gimMeP(rfE, new ElemD("Save", "", ""), "Edit", 2, "", "ca");
            saveB.click();
            try{
                DElement aSW = Proc.gimMeP(ssW,new ElemD("","#32770"),"AutoSave dialog",2,"e","ao");
                DElement wrk = Proc.gimMeP(aSW,"Close","Close button",1,"","ca");
                wrk.click();
            } catch (Exception e) {
                log("Window was closed before handling processed :-)");
            }
            log("Login was created");
        }
        catch (Exception e){
            logE("Procedure failed: " + e.getMessage());
            throw e;
        }
    }

    /**
     *
     * @param _passcName
     * @param _password
     * @param flag  1----bookmark  0-----login
     * @throws Exception
     */
    public static void manualCreateLB (String _passcName, String _password, Boolean flag, String _browser) throws Exception
    {
        logString = "Create passcard manually";
        Proc.setLogBlockPrefix("manualCreateLB");

        log("Starting procedure");
        try {

            try {
                if (_browser.equals("firefox")) {
                    DElement ssW = Proc.gL(null,"FF window",2,"c","MozillaWindowClass");

                    DElement tmp = Proc.gL(ssW,"Nav toolbar",2,"n","Navigation Toolbar");

                    DElement rfIcon = Proc.gL(tmp,"RF popup",2,"n","Click this button to show RoboForm commands");

                    rfIcon.click();
                    sleepy(4);
                }
                else if (_browser.equals("chrome")) {
                    /*ssW = gimMeP(_webDriver, new ElemD("", "Chrome_WidgetWin_1", ""), "", 2, "", "na");
                    rfE = gimMeP(ssW, new ElemD("Google Chrome", "", ""), "", 2, "", "ca");
                    List<WebElement> list1 = gimMePL(rfE, new ElemD("", "", ""), "", "", "ca");
                    WebElement null1 = list1.get(1);
                    List<WebElement> list2 = gimMePL(null1, new ElemD("", "", ""), "", "", "ca");
                    WebElement null2 = list2.get(1);
                    WebElement main = gimMeP(null2, new ElemD("main", "", ""), "", 2, "", "ca");

                    List<WebElement> list3 = main.findElements(By.xpath("./*"));
                    //gimMePL(main, new ElemD ("", "", ""), "", "", "ca" );
                    WebElement null3 = list3.get(4);
                    tmp = gimMeP(null3, new ElemD("Click this button to show RoboForm commands", "", ""), "", 2, "", "ca");
                    tmp.click();


                    List<WebElement> list4 = gimMePL(ssW, new ElemD("", "", ""), "", "", "ca");
                    WebElement null4 = list4.get(1);*/
                } else
                    {

                }
            }
            catch (Exception e)
            {
                /*if (_browser == "chrome")
                {
                    ssW = gimMeP(_webDriver, new ElemD("", "Chrome_WidgetWin_1", ""), "", 2, "", "na");
                    rfE = gimMeP(ssW, new ElemD("Google Chrome", "", ""), "", 2, "", "ca");
                    WebElement islogoff = gimMeP(rfE, new ElemD ("Enter your Master Password", "", ""), "Window for entering MP", 2, "", "ca");
                    WebElement enterMP = gimMeP(rfE, new ElemD("Enter password", "Edit", "1048"), "Window for entering MP", 2, "", "na");
                    sendKeysWr(enterMP, _password);
                    WebElement okb = gimMeP(rfE, new ElemD("OK", "", ""), "Window for entering MP", 2, "", "ca");
                    okb.click();
                    sleepy(8);

                }*/
                if (_browser.equals("firefox"))
                {
                    DElement ssW = Proc.gimMeP(null, new ElemD("", "MozillaWindowClass", ""), "", 2, "", "na");
                    DElement tmp = Proc.gimMeP(ssW, new ElemD("Navigation Toolbar", "", ""), "", 2, "", "ca");
                    DElement rfIcon = Proc.gimMeP(tmp, new ElemD("Click this button to login to RoboForm", "", ""), "", 2, "", "ca");
                    rfIcon.click();
                    sleepy(3);

                    DElement MPWind = Proc.gimMeP(null, new ElemD("RoboForm Master Password", "#32770", ""), "", 2, "", "ca");
                    DElement MPEdit = Proc.gimMeP(MPWind, new ElemD ("Master Password", "Edit", "1048"), "Window for entering MP", 2, "", "ca");
                    MPEdit.setEditValue(_password);
                    DElement okb = Proc.gimMeP(MPWind, new ElemD("Login", "Button", "1"), "Window for entering MP", 2, "", "ca");
                    okb.click();
                    sleepy(3);

                    DElement rfIcon2 = Proc.gimMeP(tmp, new ElemD("Click this button to show RoboForm commands", "", ""), "", 2, "", "ca");
                    rfIcon2.click();
                }
            }
            sleepy(2);

            DElement ssW = Proc.gL(null,"Mozilla",2,"c", "MozillaWindowClass");
            DElement popup1 = Proc.gL(ssW,"RF popup",1,"dn","RoboForm");
//            popup1 = Proc.gL(popup1,"RF subpopup item",1,"dn","RoboForm");
            DElement saveItem = Proc.gL(popup1,"Save button",1,"dn","SaveGenerate");
            saveItem = Proc.gL(saveItem,"nav item",1,"n","");
            saveItem = Proc.gL(saveItem,"emp1",1,"n","");
            saveItem = Proc.gL(saveItem,"emp2",1,"n","");
            saveItem.click();
            sleepy(5);

            DElement rfE = Proc.gimMeP(ssW, new ElemD("", "#32770", ""), "Extension pop up", 2, "", "na");
            DElement edit = Proc.gimMeP(rfE, new ElemD("", "Edit", ""), "Edit", 2, "", "na");
            edit.setEditValue(_passcName);
            sleepy(1);
            DElement saveB = Proc.gimMeP(rfE, new ElemD("Save", "", ""), "Edit", 2, "", "ca");
            saveB.click();
            try{
                DElement aSW = Proc.gimMeP(ssW,new ElemD("","#32770"),"AutoSave dialog",2,"e","ao");
                DElement wrk = Proc.gimMeP(aSW,"Close","Close button",1,"","ca");
                wrk.click();
            } catch (Exception e) {
                log("Window was closed before handling processed :-)");
            }
            log("Bookmark was created");
        }
        catch (Exception e){
            logE("Procedure failed: " + e.getMessage());
            throw e;
        }

    }

    private static char nonStrictNameMarker = '<';
    private static char operatorBraces = '>';
    private static char strictURLMarker = '!';
    private static char nameOpenMarker = '{';
    private static char nameCloseMarker = '}';
    private static char ltnameOpenMarker = '[';
    private static char ltnameCloseMarker = ']';
    private static char preActionRequiredMarker = '1';
    private static char preTwoActionsRequiredMarker = '2';
    private static char deepSearchMarker = '~';
    private static char separator = ';';
    /**
     * Parsing testData file to fill up the vector of LSURLP objects.
     * syntax of testData which is processing is as follows:
     * %Login_name;%Successful_URL;%Marker_SP1;%Marker_SP2;...;Marker_SPN
     * where ; is separator (relies on the expression above) and
     * where %Marker_SPx can contain sequences:
     * - {%name}
     * - [%localized_type_name]
     * - ~ - perform deep search
     * conditions above can be mixed but please write them once since extra occurrences would be ignored
     * example:
     * yahoo;http://yahoo.com;{Enter UserID};~{RoboForm}[window]
     *
     * new:
     * ;<name>;<successfulURL>;{name strict}[ltname]~notDeep<name not strict
     * ;;URL: ! - non-strict, 1 - need additional action (the first ;{}[] after the URL)
     * ;;2 - need two additional actions > - nested SPs
     * ;; e.g. dummy;2https://dummy.org/;{Log In}[link];>{Log In}[list item];{Log In}[link]>;{Here I am}[text]
     * ;; clear cookies and restart browser (Edge) after each test
     *
     * new:
     * ;<id_int_unique>;<name>;<successfulURL>;{name strict}[ltname]~notDeep<name not strict
     * ;;URL: ! - non-strict, 1 - need additional action (the first ;{}[] after the URL)
     * ;;2 - need two additional actions > - nested SPs
     *
     * @param _filename testData file
     * @return vector of LSURLP object (so will be prepared for test)
     * @throws Exception if something goes wrong
     */
    public static Vector<LSURLP> getTestList(String _filename) throws Exception{
        Proc.lgP = "getTestList";
        FileInputStream fstream = new FileInputStream(_filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String testContext;
        Vector<LSURLP> res = new Vector<>();

        while ((testContext = br.readLine()) != null) {
            //ignore unicode character is present
            if (testContext.charAt(0) == '\uFEFF'){
                testContext = Proc.getStringToTheRightOfTheChar(testContext,'\uFEFF');
            }
            if (testContext.charAt(0) != separator) {
                int testID = Integer.parseInt(Proc.getStringToTheLeftOfTheChar(testContext,separator));
                testContext = Proc.getStringToTheRightOfTheChar(testContext,separator);
                String res1 = Proc.getStringToTheLeftOfTheChar(testContext,separator);
                String res2;
                boolean strictURL = true;
                Vector<SP> resM = new Vector<>();
                String wrk = Proc.getStringToTheRightOfTheChar(testContext,separator);
//                boolean hasMarkers = false;
                boolean preActionRequired = false;
                Vector<SP> preActionItemToClick = new Vector<>();
                boolean preActionRequired2 = false;
                Vector<SP> preActionItemToClick2 = new Vector<>();

//                boolean hasPreActions = false;

                if (wrk.charAt(0) == strictURLMarker){
                    strictURL = false;
                    wrk = Proc.getStringToTheRightOfTheChar(wrk,strictURLMarker);
                }
                if (wrk.charAt(0) == preActionRequiredMarker){
                    preActionRequired = true;
                    wrk = Proc.getStringToTheRightOfTheChar(wrk,preActionRequiredMarker);
                }
                if (wrk.charAt(0) == preTwoActionsRequiredMarker){
                    preActionRequired = true;
                    preActionRequired2 = true;
                    wrk = Proc.getStringToTheRightOfTheChar(wrk,preTwoActionsRequiredMarker);
                }
                if (wrk.contains("" + separator)){
                    res2 = Proc.getStringToTheLeftOfTheChar(wrk,separator);
                    wrk = Proc.getStringToTheRightOfTheChar(wrk,separator);
                } else {
                    res2 = wrk;
                }
                //split actions
                if (preActionRequired || preActionRequired2){
                    if (wrk.charAt(0) == operatorBraces){
                        String tempStr = Proc.getStringToTheLeftOfTheChar(Proc.getStringToTheRightOfTheChar(wrk,operatorBraces),operatorBraces);
                        if (wrk.contains("" + separator)){
                            wrk = Proc.getStringToTheRightOfTheChar(Proc.getStringToTheRightOfTheChar(Proc.getStringToTheRightOfTheChar(wrk,operatorBraces),operatorBraces),separator);
                        } else {
                            wrk = "";
                        }
                        while (tempStr.contains(""+separator)){
                            preActionItemToClick.add(getSP(Proc.getStringToTheLeftOfTheChar(tempStr,separator)));
                            tempStr = Proc.getStringToTheRightOfTheChar(tempStr,separator);
                        }
                        preActionItemToClick.add(getSP(tempStr));
                    } else {
                        if (wrk.contains("" + separator)) {
                            preActionItemToClick.add(getSP(Proc.getStringToTheLeftOfTheChar(wrk,separator)));
                            wrk = Proc.getStringToTheRightOfTheChar(wrk,separator);
                        } else {
                            preActionItemToClick.add(getSP(wrk));
                        }
                    }
                    if (preActionRequired2){
                        if (wrk.charAt(0) == operatorBraces){
                            String tempStr = Proc.getStringToTheLeftOfTheChar(Proc.getStringToTheRightOfTheChar(wrk,operatorBraces),operatorBraces);
                            if (wrk.contains("" + separator)){
                                wrk = Proc.getStringToTheRightOfTheChar(Proc.getStringToTheRightOfTheChar(Proc.getStringToTheRightOfTheChar(wrk,operatorBraces),operatorBraces),separator);
                            } else {
                                wrk = "";
                            }
                            while (tempStr.contains(""+separator)){
                                preActionItemToClick2.add(getSP(Proc.getStringToTheLeftOfTheChar(tempStr,separator)));
                                tempStr = Proc.getStringToTheRightOfTheChar(tempStr,separator);
                            }
                            preActionItemToClick2.add(getSP(tempStr));
                        } else {
                            if (wrk.contains("" + separator)) {
                                preActionItemToClick2.add(getSP(Proc.getStringToTheLeftOfTheChar(wrk,separator)));
                                wrk = Proc.getStringToTheRightOfTheChar(wrk,separator);
                            } else {
                                preActionItemToClick2.add(getSP(wrk));
                            }
                        }
                    }
                }

                if (wrk.contains(""+separator)){
//                    hasMarkers = true;
//                    res2 = Proc.getStringToTheLeftOfTheChar(wrk,separator);

//!!//                    wrk = Proc.getStringToTheRightOfTheChar(wrk,separator);
                    do{
                        String tmp = wrk;
                        if (tmp.contains(""+separator)){
                            tmp = Proc.getStringToTheLeftOfTheChar(tmp,separator);
                        }
//                        String name = "";
//                        String ltname = "";
//                        boolean hasName = false;
//                        boolean nameStrict = true;
//                        boolean hasLTName = false;
//                        boolean isDeep = true;
//                        if (tmp.contains(""+nameOpenMarker)){
//                            hasName = true;
//                            name = Proc.getStringToTheLeftOfTheChar(Proc.getStringToTheRightOfTheChar(tmp,nameOpenMarker),nameCloseMarker);
//                        }
//                        if (tmp.contains(""+ltnameOpenMarker)){
//                            hasLTName = true;
//                            ltname = Proc.getStringToTheLeftOfTheChar(Proc.getStringToTheRightOfTheChar(tmp,ltnameOpenMarker),ltnameCloseMarker);
//                        }
//                        if (tmp.contains(""+deepSearchMarker)){
//                            isDeep = false;
//                        }
//                        if (tmp.contains(""+nonStrictNameMarker)){
//                            nameStrict = false;
//                        }
//                        String totarg = "";
//                        if (isDeep) {
//                            totarg += "d";
//                        }
//                        if (hasName){
//                            if (nameStrict){
//                                totarg += "n";
//                            } else {
//                                totarg += "N";
//                            }
//
//                        }
//                        if (hasLTName){
//                            totarg += "l";
//                        }
//                        if (!hasName){
//                            if (!hasLTName){
//                                throw new Exception("String parsing error: " + wrk);
//                            } else {
//                                resM.add(new SP("Marker LTN:" + ltname,totarg,ltname));
//                            }
//                        } else {
//                            if (!hasLTName) {
//                                resM.add(new SP("Marker N:" + name,totarg,name));
//                            } else {
//                                resM.add(new SP("Marker N:" + name + " LTN:" + ltname,totarg,name,ltname));
//                            }
//                        }
                        resM.add(getSP(tmp));
                        wrk = Proc.getStringToTheRightOfTheChar(wrk,separator);
                    }
                    while (wrk.contains(""+separator));

                    resM.add(getSP(wrk));
                }
//                if (res2.charAt(0) == strictURLMarker){
//                    res2 = Proc.getStringToTheRightOfTheChar(res2,strictURLMarker);
//                    strictURL = false;
//                }
//                if (res2.charAt(0) == preActionRequiredMarker){
//                    res2 = Proc.getStringToTheRightOfTheChar(res2,preActionRequiredMarker);
//
//
//                    preActionRequired = true;
//                    preActionItemToClick = resM.elementAt(0);
//                    resM.remove(0);
////                    if (resM.isEmpty()){
////                        hasMarkers = false;
////                    }
//                }
                //preparing
                if (resM.isEmpty()) { resM = new Vector<>();}
                res.add(new LSURLP(testID,res1,res2,strictURL,resM,preActionRequired,preActionItemToClick,preActionRequired2,preActionItemToClick2));
//                if (hasMarkers) {
//                    res.add(new LSURLP(res1,res2,strictURL,resM));
//                } else {
//                    res.add(new LSURLP(res1,res2,strictURL));
//                }
            }
        }
        return res;
    }
    public static SP getSP(String s) throws Exception{
        String name = "";
        String ltname = "";
        boolean hasName = false;
        boolean nameStrict = true;
        boolean hasLTName = false;
        boolean isDeep = true;
        SP res = null;
        if (s.contains(""+nameOpenMarker)){
            hasName = true;
            name = Proc.getStringToTheLeftOfTheChar(Proc.getStringToTheRightOfTheChar(s,nameOpenMarker),nameCloseMarker);
        }
        if (s.contains(""+ltnameOpenMarker)){
            hasLTName = true;
            ltname = Proc.getStringToTheLeftOfTheChar(Proc.getStringToTheRightOfTheChar(s,ltnameOpenMarker),ltnameCloseMarker);
        }
        if (s.contains(""+deepSearchMarker)){
            isDeep = false;
        }
        if (s.contains(""+nonStrictNameMarker)){
            nameStrict = false;
        }
        String totarg = "";
        if (isDeep) {
            totarg += "d";
        }
        if (hasName){
            if (nameStrict){
                totarg += "n";
            } else {
                totarg += "N";
            }

        }
        if (hasLTName){
            totarg += "l";
        }
        if (!hasName){
            if (!hasLTName){
                throw new Exception("String parsing error: " + s);
            } else {

                res = new SP("Marker LTN:" + ltname,totarg,ltname);
            }
        } else {
            if (!hasLTName) {
                res = new SP("Marker N:" + name,totarg,name);
            } else {
                res = new SP("Marker N:" + name + " LTN:" + ltname,totarg,name,ltname);
            }
        }
        return res;

    }
}


