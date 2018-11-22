package testLogger;

import java.util.Date;
import java.util.Vector;

/**
 * Created by Autotester on 10/11/2017.
 */
public class Test {
    private Vector<String> debugInfo;
    private String name;
    private Date startDate;
    private Date endDate = null;
    private boolean successful;
    private int attempts;
    public Test(String _name){
        name = _name;
        debugInfo = new Vector<>();
        attempts = 1;
        start();
    }
    public void reset(){
        debugInfo.clear();
        attempts++;
        //start();
    }
    public void start(){
        startDate = new Date();
    }
    public void stop(boolean _successful){
        endDate = new Date();
        successful = _successful;
    }
    public void stop(){
        stop(true);
    }
    public boolean isSuccessful(){
        return successful;
    }
    public boolean isFinished(){
        if (endDate != null){
            return true;
        } else {
            return false;
        }

    }
    public long getTimeElapsed() throws Exception{
        if (!isFinished()) {
            throw new Exception("The test '" + name + "' is not finished yet.");
        }
        return (endDate.getTime() - startDate.getTime()) / 1000;
    }
    public void writeDebugInfo(String..._text){
        for (String h : _text){
            debugInfo.add(h);
        }
    }
    public String getName(){
        return name;
    }
    public Vector<String> getDebugInfo(){
        return debugInfo;
    }
    public Vector<String> getHTMLDebugInfo() {
        Vector<String> res = new Vector<>();
        for (String h : debugInfo){
            if (h.startsWith("ERROR: ")){
                res.add("<span class=\"redcolor\">" + h + "</span>");
            } else {
                if (h.startsWith("WARNING: ")){
                    res.add("<span class=\"orangecolor\">" + h + "</span>");
                } else {
                    res.add(h);
                }
            }
        }
        return res;
    }
    public int getAttempts() {
        return attempts;
    }
}
