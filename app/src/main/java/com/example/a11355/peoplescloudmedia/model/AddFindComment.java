package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by ss on 2017/12/13 0013.
 */

public class AddFindComment {

    /*
    *
    * Token:登录的判断标识
UserId:用户编号
Aid:文章ID
Text:评论内容
    * */

    private String Token;
    private String UserId;
    private String Aid;
    private String Text;

    public AddFindComment(String token, String userId, String aid, String text) {
        Token = token;
        UserId = userId;
        Aid = aid;
        Text = text;
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

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}
