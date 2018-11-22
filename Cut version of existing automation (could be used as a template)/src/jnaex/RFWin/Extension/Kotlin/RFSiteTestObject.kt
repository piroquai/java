package jnaex.RFWin.Extension.Kotlin

/**
 * Test object to test certain Site from.
 * Supposed to be passed with or as test context.
 * A piece of shared code as a first attempt to unify testing paradigm throughout all platforms and browsers
 */
class RFSiteTestObject(val id : Int,
                       val itemName : String,
                       val strict : Boolean, val url : String, val preActions : Array<LC>,
                       val todelete : Boolean) {
}