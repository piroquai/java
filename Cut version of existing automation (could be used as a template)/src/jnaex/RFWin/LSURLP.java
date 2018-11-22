package jnaex.RFWin;

import java.util.Vector;

/**
 * Created by Autotester on 4/13/2018.
 * Login - Successful URL Pair for top100 autotests
 */
public class LSURLP {
        public int testID;
        public String login;
        public String URL;
        public boolean strictURL;
        public String testResult;
        public boolean preActionRequired;
        public Vector<SP> preActionElementToClick;
        public boolean preActionRequired2;
        public Vector<SP> preActionElementToClick2;

//        public LSURLP(String _login, String _URL){
//            this(_login,_URL,true,new Vector<>(),false,null);
//        }
//        public LSURLP(String _login, String _URL, boolean _strictURL){
//            this(_login,_URL,_strictURL,new Vector<>(),false,null);
//        }
//        public LSURLP(String _login, String _URL,Vector<SP> _markers){
//            this(_login,_URL,true,_markers,false,null);
//        }
//        public LSURLP(String _login, String _URL, boolean _strictURL,Vector<SP> _markers){
//            this(_login,_URL,_strictURL,_markers,false,null);
//        }
        public LSURLP(int _testID, String _login, String _URL, boolean _strictURL,Vector<SP> _markers, boolean _preActionRequired, Vector<SP> _preActionElementToClick){
            this(_testID,_login,_URL,_strictURL,_markers,_preActionRequired,_preActionElementToClick,false,new Vector<>());
        }
        public LSURLP(int _testID, String _login, String _URL, boolean _strictURL,Vector<SP> _markers, boolean _preActionRequired, Vector<SP> _preActionElementToClick,boolean _preActionRequired2, Vector<SP> _preActionElementToClick2){
            testID = _testID;
            login = _login;
            URL = _URL;
            strictURL = _strictURL;
            testResult = "";
            markers = _markers;
            preActionRequired = _preActionRequired;
            preActionElementToClick = _preActionElementToClick;
            preActionRequired2 = _preActionRequired2;
            preActionElementToClick2 = _preActionElementToClick2;
        }

        public void setAsCorrect(){
            testResult = "Success";
        }
        public void setAsFailed(){
            testResult = "Fail";
        }
        public int getIntResult(){
            int ret = -1;
            if (testResult.equals("Success")) {ret = 0;}
            if (testResult.equals("Fail")) {ret = 1;}

            return ret;
        }

        public Vector<SP> markers;
}
