package jnaex.RFWin.User;

/**
 * Created by Autotester on 12/14/2017.
 */
public enum Servers {
    COMOFFICIAL("online.roboform.com"),
    USSTAGING("roboform.us:42001"),
    CUSTOM("");

    private String urlport;

    Servers(String urlport) {
        this.urlport = urlport;
    }

    public String getUrlport(){
        return urlport;
    }
}
