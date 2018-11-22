package jnaex.RFWin.User;

/**
 * Created by Autotester on 12/15/2017.
 */
public enum DataTypeStates {
    RF7("RF7 multifile"),
    RF7CONVERTED("RF7 converted to Onefile"),
    RF8("RF8 onefile");
    private String description;

    DataTypeStates(String _description) {
        this.description = _description;
    }

    public String getDescription(){
        return description;
    }

}
