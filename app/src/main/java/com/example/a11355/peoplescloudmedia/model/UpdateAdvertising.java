package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by ss on 2017/12/22 0022.
 */

public class UpdateAdvertising {

    /*
    * Token:登录的判断标识
UserId:用户编号
AdvertisingId:广告主键（默认传default）
FilePath:缩略图
FullHead:标题
ToLink:跳转链接
Content:内容
    *
    * */

    private String Token;
    private String UserId;
    private String AdvertisingId;
    private String FilePath;
    private String FullHead;
    private String ToLink;
    private String Content;

    public UpdateAdvertising(String token, String userId, String advertisingId, String filePath, String fullHead, String toLink, String content) {
        Token = token;
        UserId = userId;
        AdvertisingId = advertisingId;
        FilePath = filePath;
        FullHead = fullHead;
        ToLink = toLink;
        Content = content;
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

    public String getAdvertisingId() {
        return AdvertisingId;
    }

    public void setAdvertisingId(String advertisingId) {
        AdvertisingId = advertisingId;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public String getFullHead() {
        return FullHead;
    }

    public void setFullHead(String fullHead) {
        FullHead = fullHead;
    }

    public String getToLink() {
        return ToLink;
    }

    public void setToLink(String toLink) {
        ToLink = toLink;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
