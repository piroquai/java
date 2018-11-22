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
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * Created by Mark Humphreys on 03/03/2016.
 *
 * Wrapper for the SplitButton element.
 */
public class AutomationSplitButton extends AutomationButton {
    /**
     * Construct the AutomationSplitButton
     * @param element The element
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Failed to find expected pattern
     */
    public AutomationSplitButton(AutomationElement element) throws PatternNotFoundException, AutomationException {
        super(element);
    }

    /**
     * Constructor for the AutomationButton
     * @param element The underlying automation element
     * @param pattern The pattern
     * @param instance Automation instance
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationSplitButton(AutomationElement element, Invoke pattern, UIAutomation instance)
            throws PatternNotFoundException, AutomationException {
        super(element, pattern, instance);
    }
}
