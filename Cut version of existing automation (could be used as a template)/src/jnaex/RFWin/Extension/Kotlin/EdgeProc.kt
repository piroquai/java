package jnaex.RFWin.Extension.Kotlin

import daima.DElement
import jnaex.RFWin.Extension.EdgeTestContext
import jnaex.RFWin.Proc
import jnaex.RFWin.SP

/**
 * Created by Autotester on 10/9/2018.
 */
fun prepareBrowser(){

}
fun performLogin(_context : EdgeTestContext){
}
fun verifyPage(_context: EdgeTestContext) : Boolean{
    return true
}

fun checkMarkers(_parent: DElement, _markers: List<SP>) {
    Proc.log("Starting CheckMarkers procedure")
    for (z in _markers) {
        Proc.gL(_parent, z, 2)
    }
    Proc.log("CheckMarkers has been completed without exceptions")
}
