package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by 11355 on 2017/12/18.
 */

public class AddRZTArticle {

    /**
     *
     *
     *      【53】新增人众推文章（文章抓取）(输入参数string Json)
     Token:登录的判断标识
     UserId:用户编号
     SourceUrl:源链接地址
     *
     */


    private String  Token;
    private String  UserId;
    private String  SourceUrl;

    public AddRZTArticle(String token, String userId, String sourceUrl) {
        Token = token;
        UserId = userId;
        SourceUrl = sourceUrl;
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

    public String getSourceUrl() {
        return SourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        SourceUrl = sourceUrl;
    }
}
