package com.example.chopsfull_.test_task_tereshchuk.utils.singletone;

import com.example.chopsfull_.test_task_tereshchuk.model.Account;

public class LoginCredentials {
    private static final LoginCredentials ourInstance = new LoginCredentials();
    private String password=null;
    private String login=null;
    private Account mAcc=null;
    private boolean isLoggedIn=false;

    public static LoginCredentials getInstance() {
        return ourInstance;
    }

    private LoginCredentials() {
    }

    public void reset(){
        password=null;
        login=null;
        mAcc=null;
        isLoggedIn=false;
    }


    public String getToken(){
        if(mAcc!=null)
            return mAcc.getAuth().getToken();
        return "0";

    }
    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public Account getmAcc() {
        return mAcc;
    }

    public void setmAcc(Account mAcc) {
        this.mAcc = mAcc;
    }

}
