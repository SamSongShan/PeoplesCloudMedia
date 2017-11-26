package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by 11355 on 2017/11/26.
 */

public class UpdateUserEntity {
    /*
    *
    * 【6】用户基本信息修改(输入参数string Json)
UserId:用户编号
Token:登录的判断标识
Action:修改信息( 昵称-NickName、头像-HeadIcon、性别-Gender，个性签名-Signature,手机号-Mobile)
ActionValue:值
    *
    * */

    private String UserId;
    private String Token;
    private String Action;
    private String ActionValue;

    public UpdateUserEntity(String userId, String token, String action, String actionValue) {
        UserId = userId;
        Token = token;
        Action = action;
        ActionValue = actionValue;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public String getActionValue() {
        return ActionValue;
    }

    public void setActionValue(String actionValue) {
        ActionValue = actionValue;
    }
}
