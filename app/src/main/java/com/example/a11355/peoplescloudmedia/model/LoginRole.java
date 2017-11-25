package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by 11355 on 2017/11/25.
 */

public class LoginRole {

    /*
    * LoginMark:登录方式(Mobile、WeChat)
Account：登录账户(说明：根据登录方式取对应值)
Password:登录密码(说明：快捷注册传default)
    * */

    private String LoginMark;
    private String Account;
    private String Password;

    public LoginRole(String loginMark, String account, String password) {
        LoginMark = loginMark;
        Account = account;
        Password = password;
    }

    public String getLoginMark() {
        return LoginMark;
    }

    public void setLoginMark(String loginMark) {
        LoginMark = loginMark;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
