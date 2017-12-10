package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by 11355 on 2017/12/10.
 */

public class GetFindOutDetail {

    /*
    *
    * 【39】获取文章收藏列表-发现模块(输入参数string Json)
Uid:文章ID
Token:登录的判断标识
UserId:用户编号
    * */


    private String Uid;
    private String Token;
    private String UserId;

    public GetFindOutDetail(String uid, String token, String userId) {
        Uid = uid;
        Token = token;
        UserId = userId;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
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
