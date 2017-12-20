package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by ss on 2017/12/20 0020.
 */

public class AddUserCollect {

    /*
    * Token:登录的判断标识
UserId:用户编号
CollectType:收藏类型（0-视频收藏）
ObjectValue:收藏对象ID（视频ID等）
    *
    * */

    private String Token;
    private String UserId;
    private String CollectType;
    private String ObjectValue;

    public AddUserCollect(String token, String userId, String collectType, String objectValue) {
        Token = token;
        UserId = userId;
        CollectType = collectType;
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

    public String getCollectType() {
        return CollectType;
    }

    public void setCollectType(String collectType) {
        CollectType = collectType;
    }

    public String getObjectValue() {
        return ObjectValue;
    }

    public void setObjectValue(String objectValue) {
        ObjectValue = objectValue;
    }
}
