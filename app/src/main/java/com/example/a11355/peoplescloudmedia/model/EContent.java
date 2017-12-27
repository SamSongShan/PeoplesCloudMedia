package com.example.a11355.peoplescloudmedia.model;

import java.io.Serializable;

/**
 * 内容对象
 * Created by HDL on 2017/2/17.
 */

public class EContent implements Serializable {
    /*
    * 媒体块实体列表(MediaBlockId媒体主键-新增传default,
    * FilePath媒体文件路径,
    * Texts文字,
    * MediaType媒体类型(1-文本文字，2-图片文字，3-视频文字，4-广告),
     * VideoImg:视频缩略图,
     * SortCode排序码默认从1开始,
     * isDelete是否删除(默认传0，删除传1))
    * */
    private String MediaBlockId;
    private String FilePath;
    private String Texts;
    private String MediaType;
    private String VideoImg;
    private String SortCode;
    private String isDelete;




    public EContent() {
    }

    public String getMediaBlockId() {
        return MediaBlockId;
    }

    public void setMediaBlockId(String mediaBlockId) {
        MediaBlockId = mediaBlockId;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public String getTexts() {
        return Texts;
    }

    public void setTexts(String texts) {
        Texts = texts;
    }

    public String getMediaType() {
        return MediaType;
    }

    public void setMediaType(String mediaType) {
        MediaType = mediaType;
    }

    public String getVideoImg() {
        return VideoImg;
    }

    public void setVideoImg(String videoImg) {
        VideoImg = videoImg;
    }

    public String getSortCode() {
        return SortCode;
    }

    public void setSortCode(String sortCode) {
        SortCode = sortCode;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }



}
