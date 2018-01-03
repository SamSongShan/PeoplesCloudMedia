package com.example.a11355.peoplescloudmedia.model;

import java.util.List;

/**
 * Created by ss on 2018/1/3 0003.
 */

public class GetGraphicEditorListEntity {


    /**
     * Code : 200
     * Message : 获取成功
     * Data : [{"GraphicEditorId":"b47a5b7c-6f81-40e5-b99a-3f718af62c47","ImageUrl":"/Resource/WebUserImage/TN006/20171228160507789.jpg","IsUseMusic":0,"MusicUrl":null,"MusicName":null,"Title":"的好想好想","GoodPV":0,"PV":0,"SharingPV":0,"CollectPV":0,"CommentPV":0,"EnabledMark":1,"CreateDate":"2017-12-28 16:05:09","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b"},{"GraphicEditorId":"61b0c515-bb20-4f53-86a3-94b961f537ac","ImageUrl":"/Resource/WebUserImage/TN006/20171228105957156.jpg","IsUseMusic":0,"MusicUrl":"/Resource/DocumentFile/System/20171121/fea6eb75-bb64-475b-9d1d-8b41444c1504.mp3","MusicName":"可米小子-青春纪念册.mp3","Title":"嘿嘿嘿","GoodPV":0,"PV":0,"SharingPV":0,"CollectPV":0,"CommentPV":0,"EnabledMark":1,"CreateDate":"2017-12-28 11:00:55","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b"},{"GraphicEditorId":"10fa149f-43d9-4c0f-a061-3faea14c199f","ImageUrl":"/Resource/WebUserImage/TN006/20171228105957156.jpg","IsUseMusic":0,"MusicUrl":"/Resource/DocumentFile/System/20171121/fea6eb75-bb64-475b-9d1d-8b41444c1504.mp3","MusicName":"可米小子-青春纪念册.mp3","Title":"嘿嘿嘿","GoodPV":0,"PV":0,"SharingPV":0,"CollectPV":0,"CommentPV":0,"EnabledMark":1,"CreateDate":"2017-12-28 11:00:53","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b"},{"GraphicEditorId":"c6d1777d-cf77-4261-af84-b584e025bd14","ImageUrl":"/Resource/WebUserImage/TN006/20171228105957156.jpg","IsUseMusic":0,"MusicUrl":"/Resource/DocumentFile/System/20171121/fea6eb75-bb64-475b-9d1d-8b41444c1504.mp3","MusicName":"可米小子-青春纪念册.mp3","Title":"嘿嘿嘿","GoodPV":0,"PV":0,"SharingPV":0,"CollectPV":0,"CommentPV":0,"EnabledMark":1,"CreateDate":"2017-12-28 11:00:51","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b"}]
     * Number : 71
     */

    private int Code;
    private String Message;
    private int Number;
    private List<DataEntity> Data;

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

    public List<DataEntity> getData() {
        return Data;
    }

    public void setData(List<DataEntity> Data) {
        this.Data = Data;
    }

    public static class DataEntity {
        /**
         * GraphicEditorId : b47a5b7c-6f81-40e5-b99a-3f718af62c47
         * ImageUrl : /Resource/WebUserImage/TN006/20171228160507789.jpg
         * IsUseMusic : 0
         * MusicUrl : null
         * MusicName : null
         * Title : 的好想好想
         * GoodPV : 0
         * PV : 0
         * SharingPV : 0
         * CollectPV : 0
         * CommentPV : 0
         * EnabledMark : 1
         * CreateDate : 2017-12-28 16:05:09
         * CreateUserId : 212bd12a-37f2-4eb4-ae84-6dd9e552434b
         */

        private String GraphicEditorId;
        private String ImageUrl;
        private int IsUseMusic;
        private Object MusicUrl;
        private Object MusicName;
        private String Title;
        private int GoodPV;
        private int PV;
        private int SharingPV;
        private int CollectPV;
        private int CommentPV;
        private int EnabledMark;
        private String CreateDate;
        private String CreateUserId;

        private int type;

        public DataEntity(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getGraphicEditorId() {
            return GraphicEditorId;
        }

        public void setGraphicEditorId(String GraphicEditorId) {
            this.GraphicEditorId = GraphicEditorId;
        }

        public String getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(String ImageUrl) {
            this.ImageUrl = ImageUrl;
        }

        public int getIsUseMusic() {
            return IsUseMusic;
        }

        public void setIsUseMusic(int IsUseMusic) {
            this.IsUseMusic = IsUseMusic;
        }

        public Object getMusicUrl() {
            return MusicUrl;
        }

        public void setMusicUrl(Object MusicUrl) {
            this.MusicUrl = MusicUrl;
        }

        public Object getMusicName() {
            return MusicName;
        }

        public void setMusicName(Object MusicName) {
            this.MusicName = MusicName;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
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
    }
}
