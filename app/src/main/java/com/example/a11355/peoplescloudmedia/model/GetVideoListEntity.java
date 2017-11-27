package com.example.a11355.peoplescloudmedia.model;

import java.util.List;

/**
 * Created by 11355 on 2017/11/27.
 */

public class GetVideoListEntity {


    /**
     * Code : 200
     * Message : 获取成功
     * Data : [{"VideoId":"a3fd141f-f318-42c6-b1f8-e04bada2ad89","FullHead":"ADSA","FilePath":"/Resource/VideoImg/20171120/e1ba60ab-ba9c-4561-8587-386154b73a01.jpg","PV":0,"CreateDate":"2017-11-20 10:50:22","EnabledMark":1,"UserHeadImg":"default"},{"VideoId":"cb39622c-7c6c-4f6c-8970-4f6e1c61b649","FullHead":"海洋世界","FilePath":"/Resource/VideoImg/20171117/3277429e-3662-4c67-a9f2-5d1c9bfd9df7.jpg","PV":0,"CreateDate":"2017-11-17 20:49:40","EnabledMark":1,"UserHeadImg":"default"},{"VideoId":"33dafa3d-ae61-49bf-8824-3df8cb89f57d","FullHead":"测试视频","FilePath":"/Resource/VideoImg/20171117/5ef98317-c8c1-45a9-b07d-5db85865a070.jpg","PV":0,"CreateDate":"2017-11-17 20:22:30","EnabledMark":1,"UserHeadImg":"default"}]
     * Number : 0
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
         * VideoId : a3fd141f-f318-42c6-b1f8-e04bada2ad89
         * FullHead : ADSA
         * FilePath : /Resource/VideoImg/20171120/e1ba60ab-ba9c-4561-8587-386154b73a01.jpg
         * PV : 0
         * CreateDate : 2017-11-20 10:50:22
         * EnabledMark : 1
         * UserHeadImg : default
         */

        private String VideoId;
        private String FullHead;
        private String FilePath;
        private int PV;
        private String CreateDate;
        private int EnabledMark;
        private String UserHeadImg;

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

        public String getVideoId() {
            return VideoId;
        }

        public void setVideoId(String VideoId) {
            this.VideoId = VideoId;
        }

        public String getFullHead() {
            return FullHead;
        }

        public void setFullHead(String FullHead) {
            this.FullHead = FullHead;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String FilePath) {
            this.FilePath = FilePath;
        }

        public int getPV() {
            return PV;
        }

        public void setPV(int PV) {
            this.PV = PV;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public int getEnabledMark() {
            return EnabledMark;
        }

        public void setEnabledMark(int EnabledMark) {
            this.EnabledMark = EnabledMark;
        }

        public String getUserHeadImg() {
            return UserHeadImg;
        }

        public void setUserHeadImg(String UserHeadImg) {
            this.UserHeadImg = UserHeadImg;
        }
    }
}
