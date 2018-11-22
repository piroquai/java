Daima (Turkish) - "Forever"

This package is based on mmarquee UIAutomationCore.dll implementation and on JNA.

License info:
mmarquee:
Apache Version 2.0 license (I suppose for Logger that was re-introduced by mock package by me).
https://github.com/mmarquee/ui-automation

JNA:
This library is licensed under the LGPL, version 2.1 or later, and (from version 4.0 onward) the Apache Software License, version 2.0. Commercial license arrangements are negotiable.
NOTE: Oracle is not sponsoring this project, even though the package name (com.sun.jna) might imply otherwise.
https://github.com/java-native-access/jna

DElement.gimMeP more desirable way of usage:
DElement gimMeP(DElement _parent,String _displayName, int _attempts, String _XattrList, String..._Xattrs) throws Exception
Returns the first object in the list (exactly as at Inspect)
_parent - DElement of parent to search from. If null, search will begin from desktop.
_displayName - name that will be displayed in logs (for debug purposes).
_attempts - number of attempts to perform search, with specified timeout = 3 sec (by default - may be changed).
_XattrList - header for applying attributes.
_Xattrs - applying attributes texts (in order of appearance; attributes that do not require texts will be cut automatically).

Possible appliable attributes (mark "*" to the right of attribute character means that it will look into _Xattrs for the value):
! - display full properties list of the search pool (for debug purposes)
a* - search by specified AutomationId (strict)
A* - search by specified AutomationId (non-strict)
c* - search by specified ClassName (strict)
C* - search by specified ClassName (non-strict)
d - perform deep search (otherwise only the first level of children will get into the search pool)
D - searched item is expected to disappear (e.g. when we wait for something to disappear (like progress window))
e* - search by IsEnabled (toUpperCase() "TRUE" or "T" will provide IsEnabled == True; else - IsEnabled == False)
h* - search by HasKeyboardFocus (toUpperCase() "TRUE" or "T" will provide HasKeyboardFocus == True; else - HasKeyboardFocus == False)
k* - search by IsKeyboardFocusable (toUpperCase() "TRUE" or "T" will provide IsKeyboardFocusable == True; else - IsKeyboardFocusable == False)
l* - search by specified LocalizedControlType (strict)
n* - search by specified Name (strict)
N* - search by specified Name (non-strict)
o* - search by IsOffscreen (toUpperCase() "TRUE" or "T" will provide IsOffscreen == True; else - IsOffscreen == False)
p* - search by IsPassword (toUpperCase() "TRUE" or "T" will provide IsPassword == True; else - IsPassword == False)
r* - search by RuntimeId (strict)
t* - search by ControlType (strict)
T* - search by ControlType (non-strict)
U* - specify timeout (in milliseconds: 3000 = 3 seconds)

(strict) means that the value must be equal, (non-strict) means that the value must be contained. Those values are case-sensitive.

DElement gimMePN(DElement _parent,String _displayName, int _attempts, int _i, String _XattrList, String..._Xattrs) throws Exception
Returns #_i object in the list (exactly as at Inspect) [in Java concept - 0 is the first object, 1 - second etc.]
_parent - DElement of parent to search from. If null, search will begin from desktop.
_displayName - name that will be displayed in logs (for debug purposes).
_attempts - number of attempts to perform search, with specified timeout = 3 sec (by default - may be changed).
_i - order number of the object
_XattrList - header for applying attributes.
_Xattrs - applying attributes texts (in order of appearance; attributes that do not require texts will be cut automatically).

Vector<DElement> gimMePV(DElement _parent,String _displayName, int _attempts, String _XattrList, String..._Xattrs) throws Exception
Returns the whole DElement pool that matches the criteria.
_parent - DElement of parent to search from. If null, search will begin from desktop.
_displayName - name that will be displayed in logs (for debug purposes).
_attempts - number of attempts to perform search, with specified timeout = 3 sec (by default - may be changed).
_XattrList - header for applying attributes.
_Xattrs - applying attributes texts (in order of appearance; attributes that do not require texts will be cut automatically).

Examples:
DElement editor = DElement.gimMeP(null,"Editor window",1,"Nc","Editor","RfEditor"); -- searches once the window at desktop that contains "Editor" text within its Name property and whose ClassName property equals to "RfEditor".
DElement wok = DElement.gimMeP(editor,"Close button",3,"oDhc","false","t","Button"); -- will perform searches (up to three times) for an item with properties "IsOffscreen == False","HasKeyboardFocus == True", "ClassName.equals("Button")". If all the three times any of such item will be found, then exception is thrown.
Usual way to way for some window to disappear:
	DElement wind = DElement.gimMeP(null,"Some window",1,"n","Some window caption");
	//some actions that should close the window to disappear in time; after that:
	try{
	  DElement.gimMeP(null,"Some window",4,"Dn","Some window caption");
	  log("Window has disappeared successfully");
	} catch (Exception e) {
	  logE("Window did not disappear in time")
	}
