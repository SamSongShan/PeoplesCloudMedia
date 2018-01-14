package com.example.a11355.peoplescloudmedia.model;

import java.util.List;

/**
 * Created by 11355 on 2018/1/14.
 */

public class GetGraphicEditorEntityEntity {
    /**
     * Code : 200
     * Message : 获取成功
     * Data : {"GraphicEditorEntity":{"GraphicEditorId":"b47a5b7c-6f81-40e5-b99a-3f718af62c47","ImageUrl":"/Resource/WebUserImage/TN006/20171228160507789.jpg","IsUseMusic":0,"MusicUrl":null,"MusicName":null,"Title":"的好想好想","GoodPV":0,"PV":0,"SharingPV":0,"CollectPV":0,"CommentPV":0,"EnabledMark":1,"CreateDate":"2017-12-28 16:05:09","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b"},"MediaBlockEntityList":[{"HtmlToTexts":"","VideoImg":null,"MediaBlockId":"47647605-196f-49f5-b8e0-0bad746cdfdc","FilePath":null,"Texts":null,"MediaType":3,"Mark":3,"SortCode":1,"CreateDate":"2017-12-28 16:05:09","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b","OtherId":"b47a5b7c-6f81-40e5-b99a-3f718af62c47"},{"HtmlToTexts":"","VideoImg":null,"MediaBlockId":"c32f31a1-c3bf-42f6-95ae-5fa5dc9d167c","FilePath":"/Resource/WebUserImage/TN006/20171228160446447.jpg","Texts":null,"MediaType":2,"Mark":3,"SortCode":1,"CreateDate":"2017-12-28 16:05:09","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b","OtherId":"b47a5b7c-6f81-40e5-b99a-3f718af62c47"}]}
     * Number : 72
     */

    private int Code;
    private String Message;
    private DataBean Data;
    private int Number;

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

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int Number) {
        this.Number = Number;
    }

    public static class DataBean {
        /**
         * GraphicEditorEntity : {"GraphicEditorId":"b47a5b7c-6f81-40e5-b99a-3f718af62c47","ImageUrl":"/Resource/WebUserImage/TN006/20171228160507789.jpg","IsUseMusic":0,"MusicUrl":null,"MusicName":null,"Title":"的好想好想","GoodPV":0,"PV":0,"SharingPV":0,"CollectPV":0,"CommentPV":0,"EnabledMark":1,"CreateDate":"2017-12-28 16:05:09","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b"}
         * MediaBlockEntityList : [{"HtmlToTexts":"","VideoImg":null,"MediaBlockId":"47647605-196f-49f5-b8e0-0bad746cdfdc","FilePath":null,"Texts":null,"MediaType":3,"Mark":3,"SortCode":1,"CreateDate":"2017-12-28 16:05:09","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b","OtherId":"b47a5b7c-6f81-40e5-b99a-3f718af62c47"},{"HtmlToTexts":"","VideoImg":null,"MediaBlockId":"c32f31a1-c3bf-42f6-95ae-5fa5dc9d167c","FilePath":"/Resource/WebUserImage/TN006/20171228160446447.jpg","Texts":null,"MediaType":2,"Mark":3,"SortCode":1,"CreateDate":"2017-12-28 16:05:09","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b","OtherId":"b47a5b7c-6f81-40e5-b99a-3f718af62c47"}]
         */

        private GraphicEditorEntityBean GraphicEditorEntity;
        private List<MediaBlockEntityListBean> MediaBlockEntityList;

        public GraphicEditorEntityBean getGraphicEditorEntity() {
            return GraphicEditorEntity;
        }

        public void setGraphicEditorEntity(GraphicEditorEntityBean GraphicEditorEntity) {
            this.GraphicEditorEntity = GraphicEditorEntity;
        }

        public List<MediaBlockEntityListBean> getMediaBlockEntityList() {
            return MediaBlockEntityList;
        }

        public void setMediaBlockEntityList(List<MediaBlockEntityListBean> MediaBlockEntityList) {
            this.MediaBlockEntityList = MediaBlockEntityList;
        }

        public static class GraphicEditorEntityBean {
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
            private String MusicUrl;
            private String MusicName;
            private String Title;
            private int GoodPV;
            private int PV;
            private int SharingPV;
            private int CollectPV;
            private int CommentPV;
            private int EnabledMark;
            private String CreateDate;
            private String CreateUserId;

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

            public String getMusicUrl() {
                return MusicUrl;
            }

            public void setMusicUrl(String MusicUrl) {
                this.MusicUrl = MusicUrl;
            }

            public String getMusicName() {
                return MusicName;
            }

            public void setMusicName(String MusicName) {
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

        public static class MediaBlockEntityListBean {
            /**
             * HtmlToTexts :
             * VideoImg : null
             * MediaBlockId : 47647605-196f-49f5-b8e0-0bad746cdfdc
             * FilePath : null
             * Texts : null
             * MediaType : 3
             * Mark : 3
             * SortCode : 1
             * CreateDate : 2017-12-28 16:05:09
             * CreateUserId : 212bd12a-37f2-4eb4-ae84-6dd9e552434b
             * OtherId : b47a5b7c-6f81-40e5-b99a-3f718af62c47
             */

            private String HtmlToTexts;
            private String VideoImg;
            private String MediaBlockId;
            private String FilePath;
            private String Texts;
            private int MediaType;
            private int Mark;
            private int SortCode;
            private String CreateDate;
            private String CreateUserId;
            private String OtherId;

            public String getHtmlToTexts() {
                return HtmlToTexts;
            }

            public void setHtmlToTexts(String HtmlToTexts) {
                this.HtmlToTexts = HtmlToTexts;
            }

            public String getVideoImg() {
                return VideoImg;
            }

            public void setVideoImg(String VideoImg) {
                this.VideoImg = VideoImg;
            }

            public String getMediaBlockId() {
                return MediaBlockId;
            }

            public void setMediaBlockId(String MediaBlockId) {
                this.MediaBlockId = MediaBlockId;
            }

            public String getFilePath() {
                return FilePath;
            }

            public void setFilePath(String FilePath) {
                this.FilePath = FilePath;
            }

            public String getTexts() {
                return Texts;
            }

            public void setTexts(String Texts) {
                this.Texts = Texts;
            }

            public int getMediaType() {
                return MediaType;
            }

            public void setMediaType(int MediaType) {
                this.MediaType = MediaType;
            }

            public int getMark() {
                return Mark;
            }

            public void setMark(int Mark) {
                this.Mark = Mark;
            }

            public int getSortCode() {
                return SortCode;
            }

            public void setSortCode(int SortCode) {
                this.SortCode = SortCode;
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

            public String getOtherId() {
                return OtherId;
            }

            public void setOtherId(String OtherId) {
                this.OtherId = OtherId;
            }
        }
    }
}
