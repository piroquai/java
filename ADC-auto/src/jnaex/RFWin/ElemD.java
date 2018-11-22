package jnaex.RFWin;

/**
 * <p>Standard class for elements. All of the params are case-sensitive.</p>
 * * Constructor: ElemD(name,cName,autID);<BR>
 * ** name - Name of the element<BR>
 * ** cName - ClassName of the element<BR>
 * ** autId - AutomationId text of the element<BR>
 * <BR>
 * After element is created, we can get AutomationId string to use it in xpath description.<BR>
 * Usage: [element].autIdStr  --field of the String type containing "@AutomationId='[autId]'"
 */
public class ElemD {
    /**Name of the element*/
    public String name;
    /**ClassName of the element*/
    public String cName;
    /**AutomationId of the element*/
    public String autId;
    /**
     * AutomationId string to use it in xpath string<BR>
     * Usage: [element].autIdStr  --field of the String type containing "@AutomationId='[autId]'"
     */
    public String autIdStr; //automationId string for xpath

    private void makeAutIdStr(){
        this.autIdStr = "@AutomationId='" + this.autId + "'";
    }
/*    private ElemD(){ //never used
        this.name = "Default name";
        this.cName = "Default className";
        this.autId = "Default AutomationId";
        makeAutIdStr();
    }*/
    public ElemD(String _name, String _cName, String _autId){
        this.name = _name;
        this.cName = _cName;
        this.autId = _autId;
        makeAutIdStr();
    }
    public ElemD(String _name, String _cName){
        this.name = _name;
        this.cName = _cName;
        this.autId = "";
        makeAutIdStr();
    }
    public ElemD(String _name){
        this.name = _name;
        this.cName = "";
        this.autId = "";
        makeAutIdStr();
    }

    public String toString(){
        return "N:" + this.name + " CN:" + this.cName + " AutID:" + this.autId;
    }
}
