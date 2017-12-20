package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by ss on 2017/12/20 0020.
 */

public class AddUserEvaluation {

    /*
    *
    * 【13】添加用户评论(输入参数string Json)
Token:登录的判断标识
UserId:用户编号
Content:评论内容
Type:评论类型（0-视频）
ObjectValue:评论对象ID（视频ID等）
    *
    * */


    private String Token;
    private String UserId;
    private String Content;
    private String Type;
    private String ObjectValue;

    public AddUserEvaluation(String token, String userId, String content, String type, String objectValue) {
        Token = token;
        UserId = userId;
        Content = content;
        Type = type;
        ObjectValue = objectValue;
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

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getObjectValue() {
        return ObjectValue;
    }

    public void setObjectValue(String objectValue) {
        ObjectValue = objectValue;
    }
}
