package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by ss on 2017/12/20 0020.
 */

public class GetUserEvaluationeList {

    /*
    * Token:登录的判断标识
UserId:用户编号
ObjectValue:评论对象ID（视频ID等）
PageIndex:页码
PageSize:显示条数
    *
    * */

    private String Token;
    private String UserId;
    private String ObjectValue;
    private String PageIndex;
    private String PageSize;


    public GetUserEvaluationeList(String token, String userId, String objectValue, String pageIndex, String pageSize) {
        Token = token;
        UserId = userId;
        ObjectValue = objectValue;
        PageIndex = pageIndex;
        PageSize = pageSize;
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

    public String getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(String pageIndex) {
        PageIndex = pageIndex;
    }

    public String getPageSize() {
        return PageSize;
    }

    public void setPageSize(String pageSize) {
        PageSize = pageSize;
    }
}
