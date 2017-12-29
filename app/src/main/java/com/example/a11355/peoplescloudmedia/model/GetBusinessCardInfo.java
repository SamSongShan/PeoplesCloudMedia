package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by ss on 2017/12/29 0029.
 */

public class GetBusinessCardInfo {

    /*
    * Token:登录的判断标识
UserId:用户编号
    * */

    protected String Token;
    protected String UserId;

    public GetBusinessCardInfo(String token, String userId) {
        Token = token;
        UserId = userId;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
