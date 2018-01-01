package com.example.a11355.peoplescloudmedia.model;

import java.util.List;

/**
 * Created by 11355 on 2017/12/30.
 */

public class UpdateUserProductEntity {

    /*
    *
    *【27】用户产品信息修改(输入参数string Json)
UserId:用户编号
Token:登录的判断标识
ProductImg:产品图片
ProductName:产品名称
ProductPrice:产品价格
Description:产品描述
IsUseMusic:是否使用音乐（默认传0,不使用）
MusicPath:音乐路径（默认传default）
MusicName:音频名称（默认传default）
Title:标题
MediaBlockList:媒体块实体列表(MediaBlockId媒体主键-新增传default,FilePath媒体文件路径,Texts文字,MediaType媒体类型(1-文本文字，2-图片文字，3-视频文字，4-广告), VideoImg:视频缩略图,SortCode排序码默认从1开始,isDelete是否删除(默认传0，删除传1))
AttachLinkList:附加链接实体列表(AttachLinkId附加链接主键-新增传default,LinkName链接名称,ToLink跳转链接地址,SortCode排序码默认从1开始,isDelete是否删除(默认传0，删除传1))
    *
    * */

    private String UserId;
    private String Token;
    private String ProductImg;
    private String ProductName;
    private String ProductPrice;
    private String Description;
    private String IsUseMusic;
    private String MusicPath;
    private String MusicName;
    private String Title;

    private List<EContent> MediaBlockList;
    private List<ZMTZZLink> AttachLinkList;

    public UpdateUserProductEntity(String userId, String token, String productImg, String productName, String productPrice, String description, String isUseMusic, String musicPath, String musicName, String title, List<EContent> mediaBlockList, List<ZMTZZLink> attachLinkList) {
        UserId = userId;
        Token = token;
        ProductImg = productImg;
        ProductName = productName;
        ProductPrice = productPrice;
        Description = description;
        IsUseMusic = isUseMusic;
        MusicPath = musicPath;
        MusicName = musicName;
        Title = title;
        MediaBlockList = mediaBlockList;
        AttachLinkList = attachLinkList;
    }

    public String getProductImg() {
        return ProductImg;
    }

    public void setProductImg(String productImg) {
        ProductImg = productImg;
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



    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getIsUseMusic() {
        return IsUseMusic;
    }

    public void setIsUseMusic(String isUseMusic) {
        IsUseMusic = isUseMusic;
    }

    public String getMusicPath() {
        return MusicPath;
    }

    public void setMusicPath(String musicPath) {
        MusicPath = musicPath;
    }

    public String getMusicName() {
        return MusicName;
    }

    public void setMusicName(String musicName) {
        MusicName = musicName;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public List<EContent> getMediaBlockList() {
        return MediaBlockList;
    }

    public void setMediaBlockList(List<EContent> mediaBlockList) {
        MediaBlockList = mediaBlockList;
    }

    public List<ZMTZZLink> getAttachLinkList() {
        return AttachLinkList;
    }

    public void setAttachLinkList(List<ZMTZZLink> attachLinkList) {
        AttachLinkList = attachLinkList;
    }
}
