package mock;

/**
 * Created by Autotester on 9/27/2017.
 */
public class Logger {
    static String infoPrf = "INFO::";
    static String warnPrf = "WARNING::";
    static String errorPrf = "ERROR::";
    String prefix;
    public static Logger getLogger(String name){
        return new Logger(name);
    }
    public void info(String text){
        System.out.println(infoPrf + prefix + text);
    }
    public void info(boolean val){
        System.out.println(infoPrf + val);
    }
    public void info(Object o){
        System.out.println(infoPrf + o.toString());
    }
    public void warn(String text){
        System.out.println(warnPrf + prefix + text);
    }
    public void warn(String text, Throwable ex){
        System.out.println(warnPrf + prefix + text);
        System.out.println("EXCEPTION: " + ex.getMessage());
    }
    public void error(String text){
        System.out.println(errorPrf + prefix + text);
    }
    private Logger(String _prefix){
        prefix = _prefix;
    }
}
