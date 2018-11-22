package jnaex.RFWin

import daima.DElement
import jnaex.RFWin.Kotlin.Enums.LCT
import jnaex.RFWin.Kotlin.Enums.TestEngine.AdditionalTestData
import jnaex.RFWin.Kotlin.Enums.TestEngine.TestState
import testLogger.KotlinTest
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import java.util.*

/**
 * Created by Autotester on 9/11/2018.
 */

fun isOk(_parent : DElement?, _preset : SP, _att : Int ) : Boolean {
    var res = true
    try{
        gL(_parent,_preset,_att)
    } catch (e : Exception){
        res = false
    }
    return res
}
fun <T> vectorToList(vector : Vector<T>) : List<T>{
    val res = mutableListOf<T>()
    res.addAll(vector)
    return res
}
fun lengthCut(s : String,maxLength : Int) : String{
    var res = s;
    if (res.length > maxLength){
        res = res.removeRange(maxLength,res.length)
    }
    return res
}
fun sendTestResultToMySQLServer(_test : KotlinTest, _data : AdditionalTestData?){
    Proc.setLogBlockPrefix("Sending result to MySQL server")
    sleepy(1.0)
//*
    val connectionProps = Properties()
    connectionProps.put("user",sqlUser)
    connectionProps.put("password", sqlPassword)

    try {
        Class.forName("com.mysql.jdbc.Driver").newInstance()
        val conn = DriverManager.getConnection(
                "jdbc:mysql://$sqlAddress:$sqlPort/",
                connectionProps)

        var stmt: Statement?

        stmt = conn!!.createStatement()
        val queryTable = when{
            (_data != null)&&(_data.browser != null) -> "testresults.rfexttest"
            else -> "testresults.results"
        }

        val queryStart = "INSERT INTO $queryTable (id, ${when{
            _data != null -> "testloginId, lastexecutiontime, wassuccessful,name, browser"
            else -> "lastexecutiontime, wassuccessful, name"
        }}) values (null,"
        val queryEnd = when(_data) {
            null -> "${when (_test.endDate) {
                null -> "null"
                else -> dateToUnixString(_test.endDate)
            }}, ${when (_test.state) {
                TestState.FAILED -> "0"
                TestState.SUCCEED -> "1"
                TestState.NOTCOMPLETED -> "null"
            }}, ${when (_test.name) {
                null -> "null"
                else -> "'${lengthCut(_test.name!!, 30)}'"
            }}"
            else -> "${when (_data.loginId) {
                null -> "null"
                else -> _data.loginId.toString()
            }}, ${when (_data.testFinishedAt) {
                null -> when (_test.endDate) {
                    null -> "null"
                    else -> dateToUnixString(_test.endDate)
                }
                else -> dateToUnixString(_data.testFinishedAt)
            }}, ${when (_test.state) {
                TestState.FAILED -> "0"
                TestState.SUCCEED -> "1"
                TestState.NOTCOMPLETED -> "null"
            }}, ${when (_data.name) {
                null -> when (_test.name) {
                    null -> "null"
                    else -> "'${lengthCut(_test.name!!, 30)}'"
                }
                else -> "'${lengthCut(_data.name, 30)}'"
            }}, ${when (_data.browser) {
                null -> "null"
                else -> "'${_data.browser.name}'"
            }});"
        }

        log("Preparing to execute the following query:")
        log(queryStart+queryEnd)
        if (stmt.execute(queryStart+queryEnd)){
            log("DB injection successful")
        }

    } catch (ex : SQLException){
        ex.printStackTrace()
    } catch (ex : Exception){
        ex.printStackTrace()
    }

   // */
}

fun dateToUnixString(_date : Date?) : String{
    return when (_date){
        null -> "null"
        else -> (_date.time/1000).toInt().toString()
    }
}

fun sleepy(sec : Double){    Proc.sleepy(sec)}
fun sleepy(sec : Int){  Proc.sleepy(sec*1.0)}
fun log(s : String){    Proc.log(s) }
fun logW(s : String){    Proc.logW(s) }
fun logE(s : String){    Proc.logE(s) }

fun gL(_parent: DElement?, _preset: SP, _att: Int) : DElement {    return Proc.gL(_parent,_preset,_att) }
fun gNL(_parent: DElement?, _preset: SP, _att: Int, _index: Int) : DElement { return Proc.gNL(_parent,_preset,_att,_index)}
fun clickItemFromContextMenu(s : String) { Proc.clickItemFromContextMenu(s)}

fun constructSPText(s : String) : SP{
    return constructSPType(s,LCT.TEXT)
}
fun constructSPType(s : String, lct : LCT) : SP{
    return SP("AKt:$s","nlo",s,lct.text,"false")
}

/**
 * "action" value helper (depends on LCT):
 *    COMBOBOX("combo box"): String combobox value - will set CB to that value if appropriate
 *      format: "nitems;index;text"
 *          where: nitems - number of combobox items that are displayed simultaneously
 *                 index - if several items with the same name - an index of that item (Java-conventional); 0 for single item
 *      should work for RF combobox; for other - a new implementation could be required
 *    BUTTON("button"): any value here provides a button click
 *    CHECKBOX("check box"): true, t, check, checked (case-unified) will make checkbox checked; any other value - unchecked
 *    RADIOBUTTON("radio button"): any value here provides a radiobutton click
 *    TEXT("text"): any value here provides a click on the text
 *    EDIT("edit"): String value here - edit will be filled with the value using ctrl+a
 *    SPINNER("spinner"): Int in String value here. + or - will specify direction (up or down), an integer after - number of clicks
 *    CUSTOM("custom"): any value here provides a click on the item
 *    PROGRESSBAR("progress bar"): any value here provides a click on the progressbar
 *    WINDOW("window"): any value here provides a click on the window
 *    LIST("list"): any value here provides a click on the list
 */
fun interactDElement(elem : DElement,lct : LCT, action : String){
    when (lct){
        LCT.COMBOBOX -> {
            if (!action.contains(';')) throw Exception("Invalid action parameter 1 for combobox. See KotlinProc.kt > interactDElement")
            val num : Int = Proc.getStringToTheLeftOfTheChar(action,';').toInt()
            var str = Proc.getStringToTheRightOfTheChar(action,';')
            if (!str.contains(';')) throw Exception("Invalid action parameter 2 for combobox. See KotlinProc.kt > interactDElement")
            val index : Int = Proc.getStringToTheLeftOfTheChar(str,';').toInt()
            str = Proc.getStringToTheRightOfTheChar(str,';')
            elem.setRFComboboxValue(str,num,index)
        }
        LCT.CHECKBOX -> {
            val actState = elem.checkedState
            val needState = when (action.toLowerCase()){
                "true","t","check","checked" -> true
                else -> false
            }
            if (actState != needState) {
                elem.click()
                sleepy(1.0)
                //checking
                if (needState != elem.checkedState) throw Exception("Checkbox change failure")
            }
        }
        LCT.EDIT -> {
            elem.setEditValue(action)
        }
        LCT.SPINNER -> {
            val up = when (action[0]){
                '+' -> true
                '-' -> false
                else -> throw Exception("Invalid action parameter for spinner. See KotlinProc.kt > interactDElement")
            }
            val num  = action.substring(1).toInt()
            val offset = when (up){
                true -> -5
                else -> 5
            }
            for (i in 1..num){
                elem.clickLeftOffscreen(0,offset)
                sleepy(0.5)
            }
        }
        else -> {
            elem.click()
        }
    }

}

fun deleteEverythingAt(location : String){
//    executeWinCommand("del /Q $location\\*.*")
    /* Batch file contents:
    if "%1"=="" (
            exit /B 0
    )
    del /Q %1\*.*
    */
    log("Deleting everything at $location")
    executeWinCommand("$pathFileDeleteBatchFile$nameFileDeleteBatchFile $location")
}
fun executeWinCommand(command : String){
    val p: Process
    try {
        p = Runtime.getRuntime().exec(command)
        p.waitFor()
    } catch (e: Exception) {
        e.printStackTrace()
        throw Exception("Incorrect execution")
    }
}