package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by 11355 on 2017/12/9.
 */

public class AddFindCollect {

      /*
      * Token:登录的判断标识
UserId:用户编号
Aid:文章ID
      *
      * */

    private String Token;
    private String UserId;
    private String Aid;

    public AddFindCollect(String token, String userId, String aid) {
        Token = token;
        UserId = userId;
        Aid = aid;
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

    public String getAid() {
        return Aid;
    }

    public void setAid(String aid) {
        Aid = aid;
    }
}
