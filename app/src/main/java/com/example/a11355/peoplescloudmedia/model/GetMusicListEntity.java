package com.example.a11355.peoplescloudmedia.model;

import java.util.List;

/**
 * Created by 11355 on 2017/12/26.
 */

public class GetMusicListEntity {


    /**
     * Code : 200
     * Message : 获取成功
     * Data : [{"FileLength":null,"FileSource":null,"AuditMark":null,"AuditDescription":null,"FileId":"5f481737-a21e-4df7-b9dc-a0f90f76916e","FolderId":"0","FileName":"白日梦 - 雨中漫步.mp3","FilePath":"/Resource/DocumentFile/System/20171121/a8677cc7-470a-4a94-a97c-275044f7ef4d.mp3","FileSize":"1633210","FileExtensions":".mp3","FileType":"mp3","IsShare":null,"ShareLink":null,"ShareCode":null,"ShareTime":null,"DownloadCount":null,"IsTop":null,"SortCode":null,"DeleteMark":0,"EnabledMark":1,"Description":null,"CreateDate":"2017-11-21 16:41:03","CreateUserId":"System","CreateUserName":"超级管理员","ModifyDate":"2017-11-21 16:41:03","ModifyUserId":"System","ModifyUserName":"超级管理员"},{"FileLength":null,"FileSource":null,"AuditMark":null,"AuditDescription":null,"FileId":"1ad85c2b-c3ef-499e-b8c5-623631b256a3","FolderId":"0","FileName":"可米小子-青春纪念册.mp3","FilePath":"/Resource/DocumentFile/System/20171121/fea6eb75-bb64-475b-9d1d-8b41444c1504.mp3","FileSize":"4994594","FileExtensions":".mp3","FileType":"mp3","IsShare":null,"ShareLink":null,"ShareCode":null,"ShareTime":null,"DownloadCount":null,"IsTop":null,"SortCode":null,"DeleteMark":0,"EnabledMark":1,"Description":null,"CreateDate":"2017-11-21 16:38:42","CreateUserId":"System","CreateUserName":"超级管理员","ModifyDate":"2017-11-21 16:38:42","ModifyUserId":"System","ModifyUserName":"超级管理员"}]
     * Number : 26
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

    public static class DataBean {
        /**
         * FileLength : null
         * FileSource : null
         * AuditMark : null
         * AuditDescription : null
         * FileId : 5f481737-a21e-4df7-b9dc-a0f90f76916e
         * FolderId : 0
         * FileName : 白日梦 - 雨中漫步.mp3
         * FilePath : /Resource/DocumentFile/System/20171121/a8677cc7-470a-4a94-a97c-275044f7ef4d.mp3
         * FileSize : 1633210
         * FileExtensions : .mp3
         * FileType : mp3
         * IsShare : null
         * ShareLink : null
         * ShareCode : null
         * ShareTime : null
         * DownloadCount : null
         * IsTop : null
         * SortCode : null
         * DeleteMark : 0
         * EnabledMark : 1
         * Description : null
         * CreateDate : 2017-11-21 16:41:03
         * CreateUserId : System
         * CreateUserName : 超级管理员
         * ModifyDate : 2017-11-21 16:41:03
         * ModifyUserId : System
         * ModifyUserName : 超级管理员
         */

        private String FileLength;
        private String FileSource;
        private String AuditMark;
        private String AuditDescription;
        private String FileId;
        private String FolderId;
        private String FileName;
        private String FilePath;
        private String FileSize;
        private String FileExtensions;
        private String FileType;
        private String IsShare;
        private String ShareLink;
        private String ShareCode;
        private String ShareTime;
        private String DownloadCount;
        private String IsTop;
        private String SortCode;
        private int DeleteMark;
        private int EnabledMark;
        private String Description;
        private String CreateDate;
        private String CreateUserId;
        private String CreateUserName;
        private String ModifyDate;
        private String ModifyUserId;
        private String ModifyUserName;

        private int isplay;

        public DataBean(int isplay) {
            this.isplay = isplay;
        }

        public int getIsplay() {
            return isplay;
        }

        public void setIsplay(int isplay) {
            this.isplay = isplay;
        }

        public String getFileLength() {
            return FileLength;
        }

        public void setFileLength(String FileLength) {
            this.FileLength = FileLength;
        }

        public String getFileSource() {
            return FileSource;
        }

        public void setFileSource(String FileSource) {
            this.FileSource = FileSource;
        }

        public String getAuditMark() {
            return AuditMark;
        }

        public void setAuditMark(String AuditMark) {
            this.AuditMark = AuditMark;
        }

        public String getAuditDescription() {
            return AuditDescription;
        }

        public void setAuditDescription(String AuditDescription) {
            this.AuditDescription = AuditDescription;
        }

        public String getFileId() {
            return FileId;
        }

        public void setFileId(String FileId) {
            this.FileId = FileId;
        }

        public String getFolderId() {
            return FolderId;
        }

        public void setFolderId(String FolderId) {
            this.FolderId = FolderId;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String FileName) {
            this.FileName = FileName;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String FilePath) {
            this.FilePath = FilePath;
        }

        public String getFileSize() {
            return FileSize;
        }

        public void setFileSize(String FileSize) {
            this.FileSize = FileSize;
        }

        public String getFileExtensions() {
            return FileExtensions;
        }

        public void setFileExtensions(String FileExtensions) {
            this.FileExtensions = FileExtensions;
        }

        public String getFileType() {
            return FileType;
        }

        public void setFileType(String FileType) {
            this.FileType = FileType;
        }

        public String getIsShare() {
            return IsShare;
        }

        public void setIsShare(String IsShare) {
            this.IsShare = IsShare;
        }

        public String getShareLink() {
            return ShareLink;
        }

        public void setShareLink(String ShareLink) {
            this.ShareLink = ShareLink;
        }

        public String getShareCode() {
            return ShareCode;
        }

        public void setShareCode(String ShareCode) {
            this.ShareCode = ShareCode;
        }

        public String getShareTime() {
            return ShareTime;
        }

        public void setShareTime(String ShareTime) {
            this.ShareTime = ShareTime;
        }

        public String getDownloadCount() {
            return DownloadCount;
        }

        public void setDownloadCount(String DownloadCount) {
            this.DownloadCount = DownloadCount;
        }

        public String getIsTop() {
            return IsTop;
        }

        public void setIsTop(String IsTop) {
            this.IsTop = IsTop;
        }

        public String getSortCode() {
            return SortCode;
        }

        public void setSortCode(String SortCode) {
            this.SortCode = SortCode;
        }

        public int getDeleteMark() {
            return DeleteMark;
        }

        public void setDeleteMark(int DeleteMark) {
            this.DeleteMark = DeleteMark;
        }

        public int getEnabledMark() {
            return EnabledMark;
        }

        public void setEnabledMark(int EnabledMark) {
            this.EnabledMark = EnabledMark;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
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

        public String getCreateUserName() {
            return CreateUserName;
        }

        public void setCreateUserName(String CreateUserName) {
            this.CreateUserName = CreateUserName;
        }

        public String getModifyDate() {
            return ModifyDate;
        }

        public void setModifyDate(String ModifyDate) {
            this.ModifyDate = ModifyDate;
        }

        public String getModifyUserId() {
            return ModifyUserId;
        }

        public void setModifyUserId(String ModifyUserId) {
            this.ModifyUserId = ModifyUserId;
        }

        public String getModifyUserName() {
            return ModifyUserName;
        }

        public void setModifyUserName(String ModifyUserName) {
            this.ModifyUserName = ModifyUserName;
        }
    }
}
