/*
 * Copyright 2016-17 inpwtepydjuf@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * Created by Mark Humphreys on 02/03/2016.
 *
 * Specialist pane that represents the NUIPane (part of the MS ribbon controls)
 */
public class AutomationNUIPane extends AutomationPanel {
	
	public final static String CLASS_NAME = "NUIPane";
	
    /**
     * Construct the AutomationNUIPane
     * @param element The element
     * @throws AutomationException Automation error
     * @throws PatternNotFoundException Could not find pattern
     */
    public AutomationNUIPane(AutomationElement element) throws AutomationException, PatternNotFoundException {
        super(element);
        assertClassName(CLASS_NAME);
    }

    /**
     * Construct the AutomationNUIPane
     * @param element The element
     * @param container ItemContainer pattern
     * @param instance Automation instance
     * @throws AutomationException Automation error
     * @throws PatternNotFoundException Could not find pattern
     */
    AutomationNUIPane(AutomationElement element, ItemContainer container, UIAutomation instance) throws AutomationException, PatternNotFoundException {
        super(element, container, instance);
        assertClassName(CLASS_NAME);
    }

    /**
     * Construct the AutomationNUIPane
     * @param element The element
     * @param container ItemContainer pattern
     * @throws AutomationException Automation error
     * @throws PatternNotFoundException Could not find pattern
     */
    AutomationNUIPane(AutomationElement element, ItemContainer container) throws AutomationException, PatternNotFoundException {
        super(element, container);
        assertClassName(CLASS_NAME);
    }

    /**
     * Get the AutomationNetUIHWND associated with the given index
     * @param index The index
     * @return The AutomationNetUIHWND
     * @throws AutomationException Automation error
     * @throws PatternNotFoundException Pattern not found
     */
    public AutomationNetUIHWND getNetUIHWND(int index) throws PatternNotFoundException, AutomationException {
        return new AutomationNetUIHWND(this.getElementByControlType(index, ControlType.Pane, AutomationNetUIHWND.CLASS_NAME));
    }
}