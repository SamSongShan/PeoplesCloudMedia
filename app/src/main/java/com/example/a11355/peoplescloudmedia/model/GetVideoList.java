package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by 11355 on 2017/11/27.
 */

public class GetVideoList {

    /*
    * 【11】小视频列表(输入参数string Json)
PageIndex:页码
PageSize:显示条数
    *
    * */

    private String PageIndex;
    private String PageSize;
    private String UserId;

    



    public GetVideoList(String userId,String pageIndex, String pageSize) {
        PageIndex = pageIndex;
        PageSize = pageSize;
        UserId=userId;
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
