package com.example.a11355.peoplescloudmedia.custom;

/**
 * Created by 11355 on 2017/12/30.
 */

public class UpdateCardOther {

    /*
    * UserId:用户编号
Token:登录的判断标识
Action:修改信息( 标题-Title、音乐-MusicPath)
ActionValue:值
    * */

    private String  UserId;
    private String  Token;
    private String  Action;
    private String  ActionValue;

    public UpdateCardOther(String userId, String token, String action, String actionValue) {
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
