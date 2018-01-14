package com.example.a11355.peoplescloudmedia.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * 内容对象
 * Created by HDL on 2017/2/17.
 */

public class EContent implements Serializable, Parcelable {
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

    public EContent(GetUserProductInfoEntity.DataEntity.MediaBlockListEntity data) {


        MediaBlockId=data.getMediaBlockId();
        FilePath =data.getFilePath();
        Texts =data.getTexts()  ;
        MediaType =data.getMediaType()+"";
        VideoImg =data.getVideoImg();
        SortCode=data.getSortCode()+"";
        isDelete ="0" ;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.MediaBlockId);
        dest.writeString(this.FilePath);
        dest.writeString(this.Texts);
        dest.writeString(this.MediaType);
        dest.writeString(this.VideoImg);
        dest.writeString(this.SortCode);
        dest.writeString(this.isDelete);
    }

    protected EContent(Parcel in) {
        this.MediaBlockId = in.readString();
        this.FilePath = in.readString();
        this.Texts = in.readString();
        this.MediaType = in.readString();
        this.VideoImg = in.readString();
        this.SortCode = in.readString();
        this.isDelete = in.readString();
    }

    public static final Parcelable.Creator<EContent> CREATOR = new Parcelable.Creator<EContent>() {
        @Override
        public EContent createFromParcel(Parcel source) {
            return new EContent(source);
        }

        @Override
        public EContent[] newArray(int size) {
            return new EContent[size];
        }
    };
}
