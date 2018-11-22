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
package mmarquee.automation.pattern;

import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.controls.AutomationDataGridCell;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import mmarquee.automation.uiautomation.IUIAutomationGridPattern;
import mmarquee.automation.uiautomation.IUIAutomationGridPatternConverter;
import mmarquee.automation.uiautomation.IUIAutomationRangeValuePattern;

/**
 * Created by Mark Humphreys on 25/02/2016.
 *
 * Wrapper for the Grid pattern
 */
public class Grid extends BasePattern {

    /**
     * Constructor for the pattern
     */
    public Grid() {
        this.IID = IUIAutomationGridPattern.IID;
    }

    private IUIAutomationGridPattern rawPattern;

    public Grid(IUIAutomationGridPattern rawPattern) {
        this.IID = IUIAutomationGridPattern.IID;
        this.rawPattern = rawPattern;
    }

    private IUIAutomationGridPattern getPattern() throws AutomationException {
        if (this.rawPattern != null) {
            return this.rawPattern;
        } else {
            PointerByReference pbr = new PointerByReference();

            WinNT.HRESULT result0 = this.getRawPatternPointer(pbr);

            if (COMUtils.SUCCEEDED(result0)) {
                return this.convertPointerToInterface(pbr);
            } else {
                throw new AutomationException(result0.intValue());
            }
        }
    }

    /**
     * Get the item associated with the given cell
     * @param x Cell X position
     * @param y Cell Y position
     * @return The item associated with the cell
     * @throws AutomationException Error thrown in automation library
     */
    protected PointerByReference getRawItem(int x, int y) throws AutomationException{
        PointerByReference pbr = new PointerByReference();

        final int res = this.getPattern().getItem(x, y, pbr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return pbr;
    }

    /**
     * Gets the element associated with the grid cell
     * @param x X position
     * @param y Y position
     * @return The Element from the grid
     * @throws AutomationException Something amiss with automation
     */
    public AutomationElement getItem(int x, int y) throws AutomationException {
        PointerByReference cell = this.getRawItem(x, y);

        Unknown uRoot = makeUnknown(cell.getValue());

        PointerByReference pbr = new PointerByReference();

        WinNT.HRESULT result0 = uRoot.QueryInterface(new Guid.REFIID(IUIAutomationElement3.IID), pbr);

        if (COMUtils.SUCCEEDED(result0)) {
            return new AutomationElement(convertPointerToElementInterface(pbr));
        } else {
            throw new AutomationException(result0.intValue());
        }
    }

    /**
     * Gets the row count
     * @return The tow count
     * @throws AutomationException Error thrown in automation library
     */
    public int rowCount() throws AutomationException {
        IntByReference ibr = new IntByReference();

        final int res = this.getPattern().getCurrentRowCount(ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return ibr.getValue();
    }

    /**
     * Gets the colomn count
     * @return The column count
     * @throws AutomationException Error thrown in automation library
     */
    public int columnCount() throws AutomationException {

        IntByReference ibr = new IntByReference();

        final int res = this.getPattern().getCurrentColumnCount(ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return ibr.getValue();
    }

    public IUIAutomationGridPattern convertPointerToInterface(PointerByReference pUnknownA) {
        return IUIAutomationGridPatternConverter.PointerToInterface(pUnknownA);
    }
}
