package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by 11355 on 2017/12/2.
 */

public class GetRecommendUserList {

    /*
    *
    * Token:登录的判断标识
 UserId:用户编号
 PageIndex:页码
 PageSize:显示条数
 Type:类型（0-直接粉丝，1-间接粉丝，2-粉丝总数）
    *
    * */

    private String Token;
    private String UserId;
    private String PageIndex;
    private String PageSize;
    private String Type;


    public GetRecommendUserList(String token, String userId, String pageIndex, String pageSize, String type) {
        Token = token;
        UserId = userId;
        PageIndex = pageIndex;
        PageSize = pageSize;
        Type = type;
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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
