package mmarquee.demo;

import mmarquee.automation.EventID;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.AutomationApplication;
import mmarquee.automation.controls.AutomationButton;
import mmarquee.automation.controls.AutomationWindow;
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.uiautomation.TreeScope;

/**
 * Created by Mark Humphreys on 04/02/2017.
 */
public class DemoEventHandler extends TestBase {
    public DemoEventHandler() {

    }

    public void run() {
        UIAutomation automation = UIAutomation.getInstance();

        AutomationApplication application = null;

        try {
            application = automation.launchOrAttach("apps\\Project1.exe");
        } catch (Throwable ex) {
            logger.warn("Failed to find application", ex);
        }

        try {
            // Wait for the process to start
            application.waitForInputIdle(5000);
        } catch (Throwable ex) {
            logger.error("Failed to wait properly");
        }

        try {
            AutomationWindow window = automation.getDesktopWindow("Form1");
            String name = window.getName();
            logger.info(name);

            AutomationButton button = window.getButton("OK");
/*
            automation.addAutomationEventHandler(
                    EventID.Invoke_Invoked,
                    new TreeScope(TreeScope.Element),
                    button.getElement(),
                    new AutomationEventHandler());
                    */
        } catch (Throwable ex) {
            logger.error("Failed to get window properly");
        }
    }
}