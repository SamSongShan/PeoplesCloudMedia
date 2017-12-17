package com.example.a11355.peoplescloudmedia.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by 11355 on 2017/12/17.
 */

public class GetRZTArticleListEntity {


    /**
     * Code : 200
     * Message : 获取成功
     * Data : [{"ArticleId":"a659c161-79a8-4451-baf5-6c9cd1056b3b","Title":"观察影子20秒就能找出真人！这名警察的\u201c特异功能\u201d令人叫绝","ImageUrl":"/Resource/RZTImg/2017/11/29/201711292250451155.jpg","Source":null,"SourceUrl":null,"BodyHtml":null,"IsUseMusic":null,"MusicUrl":null,"IsShowCard":null,"GoodPV":0,"PV":0,"SharingPV":0,"CollectPV":0,"CommentPV":0,"EnabledMark":1,"CreateDate":"2017-11-29 22:51:09","CreateUserId":null}]
     * Number : 54
     */

    private int Code;
    private String Message;
    private int Number;
    private List<DataBean> Data;

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int Number) {
        this.Number = Number;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Parcelable {
        /**
         * ArticleId : a659c161-79a8-4451-baf5-6c9cd1056b3b
         * Title : 观察影子20秒就能找出真人！这名警察的“特异功能”令人叫绝
         * ImageUrl : /Resource/RZTImg/2017/11/29/201711292250451155.jpg
         * Source : null
         * SourceUrl : null
         * BodyHtml : null
         * IsUseMusic : null
         * MusicUrl : null
         * IsShowCard : null
         * GoodPV : 0
         * PV : 0
         * SharingPV : 0
         * CollectPV : 0
         * CommentPV : 0
         * EnabledMark : 1
         * CreateDate : 2017-11-29 22:51:09
         * CreateUserId : null
         */

        private String ArticleId;
        private String Title;
        private String ImageUrl;
        private String Source;
        private String SourceUrl;
        private String BodyHtml;
        private String IsUseMusic;
        private String MusicUrl;
        private String IsShowCard;
        private int GoodPV;
        private int PV;
        private int SharingPV;
        private int CollectPV;
        private int CommentPV;
        private int EnabledMark;
        private String CreateDate;
        private String CreateUserId;

        
        private int type;

        public DataBean(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getArticleId() {
            return ArticleId;
        }

        public void setArticleId(String ArticleId) {
            this.ArticleId = ArticleId;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(String ImageUrl) {
            this.ImageUrl = ImageUrl;
        }

        public String getSource() {
            return Source;
        }

        public void setSource(String Source) {
            this.Source = Source;
        }

        public String getSourceUrl() {
            return SourceUrl;
        }

        public void setSourceUrl(String SourceUrl) {
            this.SourceUrl = SourceUrl;
        }

        public String getBodyHtml() {
            return BodyHtml;
        }

        public void setBodyHtml(String BodyHtml) {
            this.BodyHtml = BodyHtml;
        }

        public String getIsUseMusic() {
            return IsUseMusic;
        }

        public void setIsUseMusic(String IsUseMusic) {
            this.IsUseMusic = IsUseMusic;
        }

        public String getMusicUrl() {
            return MusicUrl;
        }

        public void setMusicUrl(String MusicUrl) {
            this.MusicUrl = MusicUrl;
        }

        public String getIsShowCard() {
            return IsShowCard;
        }

        public void setIsShowCard(String IsShowCard) {
            this.IsShowCard = IsShowCard;
        }

        public int getGoodPV() {
            return GoodPV;
        }

        public void setGoodPV(int GoodPV) {
            this.GoodPV = GoodPV;
        }

        public int getPV() {
            return PV;
        }

        public void setPV(int PV) {
            this.PV = PV;
        }

        public int getSharingPV() {
            return SharingPV;
        }

        public void setSharingPV(int SharingPV) {
            this.SharingPV = SharingPV;
        }

        public int getCollectPV() {
            return CollectPV;
        }

        public void setCollectPV(int CollectPV) {
            this.CollectPV = CollectPV;
        }

        public int getCommentPV() {
            return CommentPV;
        }

        public void setCommentPV(int CommentPV) {
            this.CommentPV = CommentPV;
        }

        public int getEnabledMark() {
            return EnabledMark;
        }

        public void setEnabledMark(int EnabledMark) {
            this.EnabledMark = EnabledMark;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public String getCreateUserId() {
            return CreateUserId;
        }

        public void setCreateUserId(String CreateUserId) {
            this.CreateUserId = CreateUserId;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.ArticleId);
            dest.writeString(this.Title);
            dest.writeString(this.ImageUrl);
            dest.writeString(this.Source);
            dest.writeString(this.SourceUrl);
            dest.writeString(this.BodyHtml);
            dest.writeString(this.IsUseMusic);
            dest.writeString(this.MusicUrl);
            dest.writeString(this.IsShowCard);
            dest.writeInt(this.GoodPV);
            dest.writeInt(this.PV);
            dest.writeInt(this.SharingPV);
            dest.writeInt(this.CollectPV);
            dest.writeInt(this.CommentPV);
            dest.writeInt(this.EnabledMark);
            dest.writeString(this.CreateDate);
            dest.writeString(this.CreateUserId);
            dest.writeInt(this.type);
        }

        protected DataBean(Parcel in) {
            this.ArticleId = in.readString();
            this.Title = in.readString();
            this.ImageUrl = in.readString();
            this.Source = in.readString();
            this.SourceUrl = in.readString();
            this.BodyHtml = in.readString();
            this.IsUseMusic = in.readString();
            this.MusicUrl = in.readString();
            this.IsShowCard = in.readString();
            this.GoodPV = in.readInt();
            this.PV = in.readInt();
            this.SharingPV = in.readInt();
            this.CollectPV = in.readInt();
            this.CommentPV = in.readInt();
            this.EnabledMark = in.readInt();
            this.CreateDate = in.readString();
            this.CreateUserId = in.readString();
            this.type = in.readInt();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
