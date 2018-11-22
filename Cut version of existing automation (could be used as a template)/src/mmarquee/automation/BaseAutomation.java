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
package mmarquee.automation;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import mmarquee.automation.uiautomation.IUIAutomationElement3Converter;
import mmarquee.automation.uiautomation.IUIAutomationElementArray;
import mmarquee.automation.uiautomation.IUIAutomationElementArrayConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark Humphreys on 08/02/2017.
 *
 * Base class to have underlying behaviour.
 */
public abstract class BaseAutomation {
    /**
     * Creates an Unknown object from the pointer.
     *
     * Allows Mockito to be used to create Unknown objects
     *
     * @param pvInstance The pointer to use
     * @return An Unknown object
     */
    public Unknown makeUnknown(Pointer pvInstance) {
        return new Unknown(pvInstance);
    }

    /**
     * Convert a raw PointerByReference to a IUIAutomationElement3
     * @param pbr The raw pointer
     * @return The IUIAutomationElement3
     * @throws AutomationException Automation library has thrown an error.
     */
    public IUIAutomationElement3 getAutomationElementFromReference(PointerByReference pbr)
            throws AutomationException {
        Unknown uElement = makeUnknown(pbr.getValue());

        WinNT.HRESULT result0 = uElement.QueryInterface(new Guid.REFIID(IUIAutomationElement3.IID), pbr);

        if (COMUtils.FAILED(result0)) {
            throw new AutomationException(result0.intValue());
        }

        return IUIAutomationElement3Converter.PointerToInterface(pbr);
    }

    /**
     * Convert a raw PointerByReference to a IUIAutomationElementArray
     * @param pbr The raw pointer
     * @return The IUIAutomationElementArray
     * @throws AutomationException Automation library has thrown an error.
     */
    public IUIAutomationElementArray getAutomationElementArrayFromReference(PointerByReference pbr)
            throws AutomationException {
        Unknown uElement = this.makeUnknown(pbr.getValue());
        PointerByReference pUnknown = new PointerByReference();

        WinNT.HRESULT result0 = uElement.QueryInterface(new Guid.REFIID(IUIAutomationElementArray.IID), pUnknown);

        if (COMUtils.FAILED(result0)) {
            throw new AutomationException(result0.intValue());
        }

        return IUIAutomationElementArrayConverter.PointerToInterface(pUnknown);
    }

    /**
     * Turns a collection (array) of automation elements, into a collection.
     *
     * @param collection The ElementArray.
     * @return The List
     * @throws AutomationException Error in the automation library
     */
    public List<AutomationElement> collectionToList(IUIAutomationElementArray collection) throws AutomationException {

        IntByReference ibr = new IntByReference();

        final int res = collection.getLength(ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        List<AutomationElement> list = new ArrayList<AutomationElement>();

        for (int count = 0; count < ibr.getValue(); count++) {

            PointerByReference pbr = new PointerByReference();

            final int res1 = collection.getElement(count, pbr);
            if (res1 != 0) {
                throw new AutomationException(res1);
            }

            Unknown uElement = new Unknown(pbr.getValue());

            WinNT.HRESULT result0 = uElement.QueryInterface(new Guid.REFIID(IUIAutomationElement3.IID), pbr);

            if (COMUtils.SUCCEEDED(result0)) {
                IUIAutomationElement3 element =
                        IUIAutomationElement3Converter.PointerToInterface(pbr);

                list.add(new AutomationElement(element));
            }
        }

        return list;
    }

    protected Pointer getPointerFromElement(IUIAutomationElement3 element) throws AutomationException {
        PointerByReference pElement = new PointerByReference();

        WinNT.HRESULT result1 = element.QueryInterface(new Guid.REFIID(IUIAutomationElement3.IID), pElement);
        if (!COMUtils.SUCCEEDED(result1)) {
            throw new AutomationException(result1.intValue());
        }

        return pElement.getValue();
    }
}
