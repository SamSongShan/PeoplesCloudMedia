package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by ss on 2017/12/20 0020.
 */

public class CancelUserCollect {

    /*
    *
    * 【17】取消用户收藏(输入参数string Json)
Token:登录的判断标识
UserId:用户编号
ObjectValue:收藏对象ID（视频ID等）
    * */

    private String Token;
    private String UserId;
    private String ObjectValue;

    public CancelUserCollect(String token, String userId, String objectValue) {
        Token = token;
        UserId = userId;
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

    public String getObjectValue() {
        return ObjectValue;
    }

    public void setObjectValue(String objectValue) {
        ObjectValue = objectValue;
    }
}
