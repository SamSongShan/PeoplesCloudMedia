package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by ss on 2017/12/13 0013.
 */

public class GetNewsCommentList {

    /*
    *
    * Aid:文章ID
PageIndex:页码
PageSize:显示条数
    *
    * */

    private String Aid;
    private String PageIndex;
    private String PageSize;

    public GetNewsCommentList(String aid, String pageIndex, String pageSize) {
        Aid = aid;
        PageIndex = pageIndex;
        PageSize = pageSize;
    }

    public String getAid() {
        return Aid;
    }

    public void setAid(String aid) {
        Aid = aid;
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
