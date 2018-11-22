package jnaex.RFWin.Extension.Kotlin

import daima.DElement
import jnaex.RFWin.Extension.EdgeProc.*
import jnaex.RFWin.Extension.EdgeTestContext
import jnaex.RFWin.Proc
import jnaex.RFWin.SP
import jnaex.RFWin.SearchPresets.MSEdge

/**
 * Created by Autotester on 10/9/2018.
 */
fun prepareBrowser(){

}
fun performLogin(_context : EdgeTestContext){
    var rfP: DElement
    try {
        rfP = getRFPopupWindow()
    } catch (e: Exception) {
        openRFPopup()
        rfP = getRFPopupWindow()
    }

    val parent = Proc.gL(null, MSEdge.edgeWindow, 2)
    searchForItem(_context.login, rfP)
    if (_context.preActionRequired1) {
        Proc.setLogBlockPrefix("performLoginEdgeKt-GoTo+Matching")
        performGoToFromContext(getSearchResultItem(_context.login, "Login"))
        Proc.sleepy(6.0)
        var wrk = getSitePane()
        for (z in _context.preActionElementToClick1) {
            wrk = Proc.gL(wrk, z, 3)
        }
        wrk.click()
        Proc.sleepy(6.0)

        if (_context.preActionRequired2) {
            wrk = getSitePane()
            for (z in _context.preActionElementToClick2) {
                wrk = Proc.gL(wrk, z, 3)
            }
            wrk.click()
            Proc.sleepy(6.0)
        }

        openRFPopup()
        rfP = getRFPopupWindow()
        clickMatching(rfP, _context.login)
        Proc.sleepy(15.0)
    } else {
        Proc.setLogBlockPrefix("performLoginEdgeKt-Login")
        performLoginFromContext(getSearchResultItem(_context.login, "Login"), true)
        Proc.sleepy(20.0)
    }
        closeWarnings(parent)
}
fun verifyPage(_context: EdgeTestContext) : Boolean{
    var successful = true
    try{
        assertSite(_context.strictURL, _context.URL)
        Proc.setLogBlockPrefix("VerificationEdgeKt")

        if (!_context.markers.isEmpty()) {
            checkMarkers(getSitePane(), _context.markers)
        }
    } catch (e : Exception){
        successful = false
    }
    return successful
}

fun checkMarkers(_parent: DElement, _markers: List<SP>) {
    Proc.log("Starting CheckMarkers procedure")
    for (z in _markers) {
        Proc.gL(_parent, z, 2)
    }
    Proc.log("CheckMarkers has been completed without exceptions")
}
