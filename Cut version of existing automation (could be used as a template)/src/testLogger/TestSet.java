package testLogger;

import java.util.Vector;

/**
 * Created by Autotester on 10/11/2017.
 */
public class TestSet {
    private Vector<Test> testSet;
    private String name;
    private int nSuccessful;
    private int nFailed;
    private int total;
    private long totalTime;
    public TestSet(String _name){
        testSet = new Vector<>();
        name = _name;
    }
    public void addTest(Test _test) throws Exception{
        testSet.add(_test);
        total++;
        if (_test.isSuccessful()){
            nSuccessful++;
        } else {
            nFailed++;
        }
        totalTime += _test.getTimeElapsed();
    }
    public int getNSuccessful(){
        return nSuccessful;
    }
    public int getNFailed() {
        return nFailed;
    }
    public int getTotal(){
        return total;
    }
    public long getTotalTime(){
        return totalTime;
    }
    public Vector<String> getDebugInfoVector(){
        Vector<String> res = new Vector<>();
        if (nFailed != 0){
            int i = 1;
            int cntf = 0;
            while ((i <= testSet.size()) && (cntf < nFailed)) {
                Test tmp = testSet.get(i - 1);
                if (!(tmp.isSuccessful())) {
                    cntf++;
                    res.add("=-Test set: " + name + " Test: " + tmp.getName() + " |BEGIN-=");
                    res.addAll(tmp.getDebugInfo());
                    res.add("=-Test set: " + name + " Test: " + tmp.getName() + " |END-=");
                }
                i++;
            }
        }
        return res;
    }
    public Vector<String> getHTMLDebugInfoVector(){
        Vector<String> res = new Vector<>();
        if (nFailed != 0){
            int i = 1;
            int cntf = 0;
            while ((i <= testSet.size()) && (cntf < nFailed)) {
                Test tmp = testSet.get(i - 1);
                if (!(tmp.isSuccessful())) {
                    cntf++;
                    res.add("=-Test set: " + name + " Test: " + tmp.getName() + " |BEGIN-=");
                    res.addAll(tmp.getHTMLDebugInfo());
                    res.add("=-Test set: " + name + " Test: " + tmp.getName() + " |END-=");
                }
                i++;
            }
        }
        return res;
    }
    public Vector<String> getResults() throws Exception{
        Vector<String> res = new Vector<>();
        if (total == 0) {
            res.add("Test set '" + name + "' has no tests");
        } else {
            res.add("Test set: " + name);
            res.add("----------------------------------------");
            for (Test t : testSet) {
                String rr;
                if (t.isSuccessful()) {
                    rr = "++PASSED++";
                } else {
                    rr = "-!-FAILED-!-";
                }
                res.add("Name: " + t.getName() + "   Result: " + rr + "   Time elapsed: " + getTimeString(t.getTimeElapsed()));
            }
            res.add("----------------------------------------");
            res.add("Total passed: " + nSuccessful);
            res.add("Total failed: " + nFailed);
            res.add("Total tests: " + total);
            res.add("Total time elapsed: " + getTimeString(totalTime));
            res.add("----------------------------------------");
        }
        return res;
    }
    public Vector<String> getHTMLResults() throws Exception{
        Vector<String> res = new Vector<>();
        if (total == 0) {
            res.add("Test set '" + name + "' has no tests.");
        } else {
            res.add("Test set: " + name);
            res.add("----------------------------------------");
            for (Test t : testSet) {
                String rr;
                if (t.isSuccessful()) {
                    rr = "<span class=\"greencolor\">PASSED</span>";
                } else {
                    rr = "<span class=\"redcolor\">FAILED</span>";
                }
                String atts = "";
                if (t.getAttempts() > 1) {
                    atts = "   Total attempts: " + Integer.toString(t.getAttempts());
                }
                res.add("Name: " + t.getName() + "   Result: " + rr + "   Time elapsed: " + getTimeString(t.getTimeElapsed()) + atts);
            }
            res.add("----------------------------------------");
            res.add("Total passed: " + nSuccessful);
            res.add("Total failed: " + nFailed);
            res.add("Total tests: " + total);
            res.add("Total time elapsed: " + getTimeString(totalTime));
            res.add("----------------------------------------");
        }
        return res;
    }
    public static String getTimeString(long _date){
        long h = (_date) / (60 * 60);
        String hA = Long.toString(h);
        long m = ((_date) / 60) % 60;
        String m1 = Long.toString(m / 10);
        String m2 = Long.toString(m % 10);
        long s = (_date) % 60;
        String s1 = Long.toString(s / 10);
        String s2 = Long.toString(s % 10);
        return hA + ":" + m1 + m2 + ":" + s1 + s2;
    }
}
