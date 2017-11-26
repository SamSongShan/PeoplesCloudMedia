package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by 11355 on 2017/11/26.
 */

public class GetEntityUser {

    /*
    * 【4】用户编号获取用户实体信息(输入参数string Json)
Token:登录的判断标识
UserId:用户编号
    * */

    private String  Token;
    private String  UserId;

    public GetEntityUser(String token, String userId) {
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
