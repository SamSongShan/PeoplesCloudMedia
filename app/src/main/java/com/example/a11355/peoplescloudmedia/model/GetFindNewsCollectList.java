package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by 11355 on 2017/12/7.
 */

public class GetFindNewsCollectList {

    /*
    *
    * 【38】获取文章收藏列表-发现模块(输入参数string Json)
Token:登录的判断标识
UserId:用户编号
PageIndex:页码
PageSize:显示条数
    * */

    private String Token;
    private String UserId;
    private String PageIndex;
    private String PageSize;

    public GetFindNewsCollectList(String token, String userId, String pageIndex, String pageSize) {
        Token = token;
        UserId = userId;
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
