package jnaex.RFWin.Extension

import jnaex.RFWin.SP

/**
 * Created by Autotester on 10/9/2018.
 */
class EdgeTestContext(override val id : Int,
                      val login : String,
                      val URL : String,
                      val strictURL : Boolean,
                      val markers : List<SP>,
                      val preActionRequired1 : Boolean,
                      val preActionElementToClick1 : List<SP>,
                      val preActionRequired2 : Boolean,
                      val preActionElementToClick2 : List<SP>
                      ) : TestContext(id) {
    constructor(_testID: Int,
                _login: String,
                _URL: String,
                _strictURL: Boolean,
                _markers: List<SP>,
                _preActionRequired1: Boolean,
                _preActionElementToClick: List<SP>)
        : this (_testID,_login, _URL, _strictURL, _markers,_preActionRequired1,_preActionElementToClick,
        false,listOf<SP>())
    constructor(_testID: Int,_login: String,_URL: String,_strictURL: Boolean,_markers: List<SP>)
        : this(_testID,_login,_URL,_strictURL,_markers,false,listOf<SP>())
}