package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by 11355 on 2017/12/3.
 */

public class GetNewsList {
     /*
     *
     * 【31】获取文章列表-发现模块(输入参数string Json)
TypeId:文章分类ID（默认传default）
NewsTitle:文章标题(用作模糊查询文章,默认传default)
PageIndex:页码
PageSize:显示条数
     *
     * */

    private String TypeId;
    private String NewsTitle;
    private String PageIndex;
    private String PageSize;

    public GetNewsList(String typeId, String newsTitle, String pageIndex, String pageSize) {
        TypeId = typeId;
        NewsTitle = newsTitle;
        PageIndex = pageIndex;
        PageSize = pageSize;
    }

    public String getTypeId() {
        return TypeId;
    }

    public void setTypeId(String typeId) {
        TypeId = typeId;
    }

    public String getNewsTitle() {
        return NewsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        NewsTitle = newsTitle;
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
