package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by 11355 on 2018/1/14.
 */

public class GetGraphicEditorEntity {

    /*
    * GraphicEditorId:图文编辑实体主键
Token:登录的判断标识
UserId:用户编号
    *
    * */

   private String GraphicEditorId;
    private String Token;
    private String UserId;

    public GetGraphicEditorEntity(String graphicEditorId, String token, String userId) {
        GraphicEditorId = graphicEditorId;
        Token = token;
        UserId = userId;
    }

    public String getGraphicEditorId() {
        return GraphicEditorId;
    }

    public void setGraphicEditorId(String graphicEditorId) {
        GraphicEditorId = graphicEditorId;
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
}
