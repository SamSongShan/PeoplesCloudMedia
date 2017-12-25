package com.example.a11355.peoplescloudmedia.model;

import java.util.List;

/**
 * Created by ss on 2017/12/25 0025.
 */

public class GetHomeRotateListEntity {


    /**
     * Code : 200
     * Message : 获取成功
     * Data : [{"RotateImageId":"0e0a6ec4-328d-417d-8aa6-b1a8b147fff3","ImageName":"首页轮播图-1","ImageFilePath":"/Resource/VideoImg/20171219/850454e3-655f-46b7-afda-2080f428cd8a.png","ShowClient":"首页","IsExternalLink":1,"ObjectValue":"www.baidu.com","Type":null,"SortCode":1,"EnabledMark":1,"Description":null,"CreateDate":"2017-12-19 22:53:43","CreateUserId":"System","CreateUserName":"超级管理员","ModifyDate":"2017-12-19 23:19:33","ModifyUserId":"System","ModifyUserName":"超级管理员"},{"RotateImageId":"5cc0788f-5de5-4aa9-b533-f78ecf3a1b15","ImageName":"首页轮播图-2","ImageFilePath":"/Resource/VideoImg/20171219/86726c62-1488-4703-befa-282b35e87777.png","ShowClient":"首页","IsExternalLink":0,"ObjectValue":null,"Type":null,"SortCode":2,"EnabledMark":1,"Description":null,"CreateDate":"2017-12-19 23:03:36","CreateUserId":"System","CreateUserName":"超级管理员","ModifyDate":"2017-12-19 23:18:46","ModifyUserId":"System","ModifyUserName":"超级管理员"},{"RotateImageId":"6f21c087-bcca-48cf-9e41-645a84969fad","ImageName":"首页轮播图-3","ImageFilePath":"/Resource/VideoImg/20171219/269f4b3c-2c14-426a-a596-ef382ae45cb0.png","ShowClient":"首页","IsExternalLink":1,"ObjectValue":"www.kugou.com","Type":null,"SortCode":3,"EnabledMark":1,"Description":null,"CreateDate":"2017-12-19 23:20:40","CreateUserId":"System","CreateUserName":"超级管理员","ModifyDate":null,"ModifyUserId":null,"ModifyUserName":null}]
     * Number : 46
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
         * RotateImageId : 0e0a6ec4-328d-417d-8aa6-b1a8b147fff3
         * ImageName : 首页轮播图-1
         * ImageFilePath : /Resource/VideoImg/20171219/850454e3-655f-46b7-afda-2080f428cd8a.png
         * ShowClient : 首页
         * IsExternalLink : 1
         * ObjectValue : www.baidu.com
         * Type : null
         * SortCode : 1
         * EnabledMark : 1
         * Description : null
         * CreateDate : 2017-12-19 22:53:43
         * CreateUserId : System
         * CreateUserName : 超级管理员
         * ModifyDate : 2017-12-19 23:19:33
         * ModifyUserId : System
         * ModifyUserName : 超级管理员
         */

        private String RotateImageId;
        private String ImageName;
        private String ImageFilePath;
        private String ShowClient;
        private int IsExternalLink;
        private String ObjectValue;
        private Object Type;
        private int SortCode;
        private int EnabledMark;
        private Object Description;
        private String CreateDate;
        private String CreateUserId;
        private String CreateUserName;
        private String ModifyDate;
        private String ModifyUserId;
        private String ModifyUserName;

        public String getRotateImageId() {
            return RotateImageId;
        }

        public void setRotateImageId(String RotateImageId) {
            this.RotateImageId = RotateImageId;
        }

        public String getImageName() {
            return ImageName;
        }

        public void setImageName(String ImageName) {
            this.ImageName = ImageName;
        }

        public String getImageFilePath() {
            return ImageFilePath;
        }

        public void setImageFilePath(String ImageFilePath) {
            this.ImageFilePath = ImageFilePath;
        }

        public String getShowClient() {
            return ShowClient;
        }

        public void setShowClient(String ShowClient) {
            this.ShowClient = ShowClient;
        }

        public int getIsExternalLink() {
            return IsExternalLink;
        }

        public void setIsExternalLink(int IsExternalLink) {
            this.IsExternalLink = IsExternalLink;
        }

        public String getObjectValue() {
            return ObjectValue;
        }

        public void setObjectValue(String ObjectValue) {
            this.ObjectValue = ObjectValue;
        }

        public Object getType() {
            return Type;
        }

        public void setType(Object Type) {
            this.Type = Type;
        }

        public int getSortCode() {
            return SortCode;
        }

        public void setSortCode(int SortCode) {
            this.SortCode = SortCode;
        }

        public int getEnabledMark() {
            return EnabledMark;
        }

        public void setEnabledMark(int EnabledMark) {
            this.EnabledMark = EnabledMark;
        }

        public Object getDescription() {
            return Description;
        }

        public void setDescription(Object Description) {
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
