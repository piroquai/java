package jnaex.RFWin.Extension.Kotlin

import org.openqa.selenium.By

/**
 * Locator Context
 * This class may contain all possible locators that could be used for Selenium and other tests
 * E.g. name, className, id, xpath
 */
class LC(val id : String?, val name : String?, val className : String?, val xpath : String?) {
    val byId : By?
        get(){
            return when (this.id){
                null -> null
                else -> By.id(id)
            }
        }
    val byName : By?
        get(){
            return when (this.name){
                null -> null
                else -> By.name(name)
            }
        }
    val byClassName : By?
        get(){
            return when (this.className){
                null -> null
                else -> By.className(className)
            }
        }
    val byXpath : By?
        get(){
            return when (this.xpath){
                null -> null
                else -> By.xpath(xpath)
            }
        }
}