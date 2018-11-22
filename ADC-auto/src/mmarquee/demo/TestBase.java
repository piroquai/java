package mmarquee.demo;

import mmarquee.automation.controls.AutomationBase;
import mock.Logger;

/**
 * Created by Mark Humphreys on 19/05/2016.
 */
public class TestBase {

    protected Logger logger = Logger.getLogger(AutomationBase.class.getName());

    protected void rest() {
        try {
            Thread.sleep(1500);
        } catch (Exception ex) {
            logger.info("Interrupted");
        }
    }
}
