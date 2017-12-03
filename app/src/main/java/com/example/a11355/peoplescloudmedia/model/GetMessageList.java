package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by 11355 on 2017/12/3.
 */

public class GetMessageList {

    /*
    * 【10】消息列表(输入参数string Json)
PageIndex:页码
PageSize:显示条数
    * */

    private String  PageIndex;
    private String  PageSize;

    public GetMessageList(String pageIndex, String pageSize) {
        PageIndex = pageIndex;
        PageSize = pageSize;
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
