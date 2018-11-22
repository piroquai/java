package daima

import jnaex.RFWin.SP
import jnaex.RFWin.SPProcessor
import mmarquee.automation.UIAutomation
import mmarquee.automation.controls.AutomationBase

/**
 * Created by Autotester on 10/2/2018.
 */
fun deepSearch(_parent : DElement?,_sp : SP,_depth : Int?) : DElement?{
    return deepSearch(_parent, _sp, _depth, "","", arrayOf(""))
}
fun deepSearchIndexed(_parent: DElement?,_sp: SP,_depth: Int?,_index : Int) : DElement?{
    return deepSearchIndexed(_parent, _sp, _depth, _index,"","",arrayOf(""))
}
fun deepSearch(_parent: DElement?,_sp : SP,_depth: Int?,_excludeXAttrList : String,_includeXAttrList : String, _includeXAttrs : Array<out String>) : DElement?{
    return deepSearchIndexed(_parent,_sp,_depth,0,_excludeXAttrList,_includeXAttrList,_includeXAttrs)
}
fun deepSearchIndexed(_parent: DElement?,_sp: SP,_depth: Int?,_index: Int,_excludeXAttrList: String,_includeXAttrList: String,_includeXAttrs: Array<out String>): DElement?{
    val list : List<DElement>? = deepSearchList(_parent, _sp, _depth, _excludeXAttrList, _includeXAttrList, _includeXAttrs)

    var result : DElement? = null

    if (list != null){
        if (list.size > _index){
            result = list[_index]
            log("Returning item #${_index}")
        } else {
            log("There is no such item with index $_index")
        }
    }

    return result
}
fun deepSearchList(_parent: DElement?,_sp: SP,_depth: Int?) : List<DElement>?{
    return deepSearchList(_parent, _sp, _depth,"","",arrayOf(""))
}
fun deepSearchList(_parent: DElement?,_sp: SP,_depth: Int?,_excludeXAttrList: String,_includeXAttrList: String,_includeXAttrs : Array<out String>) : List<DElement>?{
    log("Looking for ${_sp.descriptionName}")
    val q : List<DElement>? = dSearch(_parent,_sp,0,_depth, _excludeXAttrList, _includeXAttrList, _includeXAttrs)
    if (q == null){
        log("${_sp.descriptionName} is not found")
    } else {
        log("${_sp.descriptionName} is found")
    }
    return q
}
fun dSearch(_parent: DElement?,_sp: SP,_currentDepth: Int,_depth: Int?,_excludeXAttrList: String,_includeXAttrList: String,_includeXAttrs : Array<out String>) : List<DElement>?{
    val thisStepDepth = _currentDepth + 1
    if ((_depth != null)&&(_depth < thisStepDepth)){
        return null
    }

    val z : List<DElement>? = evaluateChildren(_parent, _sp, _excludeXAttrList, _includeXAttrList, _includeXAttrs)
    if ((z) != null){
        return z
    } else {
        val z1 : List<DElement>? = when(_parent){
            null -> {
                val dt = DElement(UIAutomation.getInstance()!!.desktop)
                val abl = dt.element.getChildren(false)
                transformBtoD(abl)
            }
            else -> transformBtoD(_parent.element.getChildren(false)).toList()
        }

        if (z1 != null){
            for (h in z1){
                val q : List<DElement>? = dSearch(h,_sp, thisStepDepth,_depth, _excludeXAttrList, _includeXAttrList, _includeXAttrs)
                if (q != null){
                    return q
                }
            }
        }
    }
    return null
}

private fun evaluateChildren(_parent: DElement?,_sp : SP,_excludeXAttrList: String,_includeXAttrList: String,_includeXAttrs: Array<out String>) : List<DElement>?{
    SPProcessor.clear()
    SPProcessor.setParent(_parent)
    SPProcessor.setPreset(_sp)
    SPProcessor.setIgnoredXattrList(_excludeXAttrList)
    SPProcessor.setAttempts(1)
    SPProcessor.setEnforcedXattrList(_includeXAttrList)
    SPProcessor.setEnforcedXattrs(*_includeXAttrs)
    SPProcessor.processData()
    val tmp = SPProcessor.getXattrs()
    val xattrs : MutableList<String> = mutableListOf()
    xattrs.addAll(tmp)

    return gimMePL(_parent,SPProcessor.getDescription(),SPProcessor.getXattrList(),xattrs.toTypedArray(),true)
}

private fun log(s : String){
    println(s)
}
fun excludeCharFromString(_string : String, _char : Char, _silent : Boolean = true) : String{
    return excludeCharArrayFromString(_string, charArrayOf(_char),_silent)
}
fun excludeCharArrayFromString(_string : String, _charArray : CharArray, _silent : Boolean = true) : String{
    var resS = _string
    for (c in _charArray){
        val di = resS.indexOf(c)
        if (di > -1) {
            var begX = ""
            if (di > 0) {
                begX = resS.substring(0, di)
            }
            resS = begX + resS.substring(di + 1)
        } else {
            if (!_silent){
                throw Exception("ALERT! Char '$c' is not found in string \"$resS\"")
            }
        }
    }
    return resS
}

fun gimMePL(_parent: DElement?, _displayName: String, _XattrList: String, _Xattrs: Array<out String>, _beSilent : Boolean = false): List<DElement>? {
    var xattrList = _XattrList
    var procV : MutableList<DElement> = mutableListOf()
    var debug = false
    val debugMarker = '!'
    if (xattrList.contains(debugMarker)) {
        debug = true
    }
    if (!debug) {
        if (!_beSilent) log("Looking for '$_displayName' with following _XattrList: $xattrList")
    } else {
        if (!_beSilent) log("DEBUG MODE: Looking for '$_displayName' with following parameters:")
        if (_parent == null) {
            if (!_beSilent) log("Parent handle: Desktop")
        } else {
            if (!_beSilent) log("Parent handle: " + _parent.nativeWindowHandleHEXStr)
        }
        if (!_beSilent) log("_XattrList: $xattrList")
        if (!_beSilent) log("_XattrParam: BEGIN...")
        for (s in _Xattrs) {
            if (!_beSilent) log(s)
        }
        if (!_beSilent) log("_XattrParam: END...")
    }
    xattrList = excludeCharArrayFromString(xattrList, charArrayOf(debugMarker,'d','D'))

    val uia: UIAutomation? = when(_parent){
        null -> UIAutomation.getInstance()
        else -> null
    }

    try {
        if (_parent == null) {
            val dt = DElement(uia!!.desktop)
            val abl = dt.element.getChildren(false)
            procV.addAll(transformBtoD(abl))
        } else {
            procV = transformBtoD(_parent.element.getChildren(false)).toMutableList()
        }
        //
        if (debug) {
            for (d in procV) {
                d.printProperties()
            }
            if (procV.size == 0) {
                if (!_beSilent) log("Children array is empty")
            }
        }
        //
        if (!_beSilent) log("Item is found")
        return processFilters(procV, xattrList, _Xattrs)
    } catch (e: Exception) {
        //nothing here
        if (!_beSilent) log("Item is not found")
        return null
    }
}

private fun transformBtoD(l: List<AutomationBase>): List<DElement> {
    val vec : MutableList<DElement> = mutableListOf()
    for (q in l) {
        vec.add(DElement(q))
    }
    return vec
}

private fun processFilters(_l: List<DElement>, _attrList: String, _attrs: Array<out String>): List<DElement> {
    val attrMap : MutableMap<UIAFieldTypes,String> = mutableMapOf()
    for (c in _attrList.toCharArray().indices){
        val tmp = _attrList.toCharArray()[c]
        if (tmp == 'U') {
        //skip
        } else {
            attrMap.put(when (tmp) {
                'a' -> UIAFieldTypes.AutomationId
                'A' -> UIAFieldTypes.AutomationIdNonStrict
                'c' -> UIAFieldTypes.ClassName
                'C' -> UIAFieldTypes.ClassNameNonStrict
                't' -> UIAFieldTypes.ControlType
                'T' -> UIAFieldTypes.ControlTypeNonStrict
                'h' -> UIAFieldTypes.HasKeyboardFocus
                'e' -> UIAFieldTypes.IsEnabled
                'k' -> UIAFieldTypes.IsKeyboardFocusable
                'o' -> UIAFieldTypes.IsOffscreen
                'p' -> UIAFieldTypes.IsPassword
                'l' -> UIAFieldTypes.LocalizedControlType
                'n' -> UIAFieldTypes.Name
                'N' -> UIAFieldTypes.NameNonStrict
                'r' -> UIAFieldTypes.RuntimeId
                else -> throw Exception("Unexpected parameter - $tmp")
            }, _attrs[c])
        }
    }
    return filterList(_l,attrMap)
}

private fun processToBoolean(s : String) : Boolean{
    return when (s.toLowerCase()){
        "t","true","1" -> true
        else -> false
    }
}
private fun nonStrictCompare(s1 : String, s2 : String) : Boolean{
    return s1.toLowerCase().contains(s2.toLowerCase())
}
private fun filterList(_list : List<DElement>, filteringMap : Map<UIAFieldTypes,String>): List<DElement> {
    val res : MutableList<DElement> = mutableListOf()
    for (de in _list){
        var okay = true
        for (ftp in filteringMap){
            try{
                if (when(ftp.key){
                    UIAFieldTypes.AutomationId -> de.automationId != ftp.value
                    UIAFieldTypes.AutomationIdNonStrict -> !(nonStrictCompare(de.automationId,ftp.value))
                    UIAFieldTypes.ClassName -> de.className != ftp.value
                    UIAFieldTypes.ClassNameNonStrict -> !(nonStrictCompare(de.className,ftp.value))
                    UIAFieldTypes.ControlType -> de.controlType != ftp.value
                    UIAFieldTypes.ControlTypeNonStrict -> !(nonStrictCompare(de.controlType,ftp.value))
                    UIAFieldTypes.HasKeyboardFocus -> de.hasKeyboardFocus == processToBoolean(ftp.value)
                    UIAFieldTypes.IsEnabled -> de.isEnabled == processToBoolean(ftp.value)
                    UIAFieldTypes.IsKeyboardFocusable -> de.isKeyboardFocusable == processToBoolean(ftp.value)
                    UIAFieldTypes.IsOffscreen -> de.isOffscreen == processToBoolean(ftp.value)
                    UIAFieldTypes.IsPassword -> de.isPassword == processToBoolean(ftp.value)
                    UIAFieldTypes.LocalizedControlType -> de.localizedControlType != ftp.value
                    UIAFieldTypes.Name -> de.name != ftp.value
                    UIAFieldTypes.NameNonStrict -> !(nonStrictCompare(de.name,ftp.value))
                    UIAFieldTypes.RuntimeId -> de.runtimeId != ftp.value
                    }){
                    okay = false
                }
            } catch (e : Exception) {
                okay = false
            }
            if (!okay) break
        }
        if (okay) res.add(de)
    }
    if (res.isEmpty()) {
        throw Exception("No items have passed the filter")
    }
    return res
}
