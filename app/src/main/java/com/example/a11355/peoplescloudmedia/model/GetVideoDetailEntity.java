package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by 11355 on 2017/12/19.
 */

public class GetVideoDetailEntity {


    /**
     * Code : 200
     * Message : 获取成功
     * Data : {"EvaluationNum":3,"VideoId":"33dafa3d-ae61-49bf-8824-3df8cb89f57d","FullHead":null,"FilePath":"/Resource/VideoImg/20171117/5ef98317-c8c1-45a9-b07d-5db85865a070.jpg","VideoPath":"/Resource/VideoImg/20171117/a496db87-8f8d-48de-95aa-1b86edc5bc99.mp4","PV":19,"CreateDate":"2017-11-17 20:22:30","HeadIcon":"default","NickName":"default","IsCollect":0}
     * Number : 12
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
         * EvaluationNum : 3
         * VideoId : 33dafa3d-ae61-49bf-8824-3df8cb89f57d
         * FullHead : null
         * FilePath : /Resource/VideoImg/20171117/5ef98317-c8c1-45a9-b07d-5db85865a070.jpg
         * VideoPath : /Resource/VideoImg/20171117/a496db87-8f8d-48de-95aa-1b86edc5bc99.mp4
         * PV : 19
         * CreateDate : 2017-11-17 20:22:30
         * HeadIcon : default
         * NickName : default
         * IsCollect : 0
         */

        private int EvaluationNum;
        private String VideoId;
        private Object FullHead;
        private String FilePath;
        private String VideoPath;
        private int PV;
        private String CreateDate;
        private String HeadIcon;
        private String NickName;
        private int IsCollect;

        public int getEvaluationNum() {
            return EvaluationNum;
        }

        public void setEvaluationNum(int EvaluationNum) {
            this.EvaluationNum = EvaluationNum;
        }

        public String getVideoId() {
            return VideoId;
        }

        public void setVideoId(String VideoId) {
            this.VideoId = VideoId;
        }

        public Object getFullHead() {
            return FullHead;
        }

        public void setFullHead(Object FullHead) {
            this.FullHead = FullHead;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String FilePath) {
            this.FilePath = FilePath;
        }

        public String getVideoPath() {
            return VideoPath;
        }

        public void setVideoPath(String VideoPath) {
            this.VideoPath = VideoPath;
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

        public String getHeadIcon() {
            return HeadIcon;
        }

        public void setHeadIcon(String HeadIcon) {
            this.HeadIcon = HeadIcon;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public int getIsCollect() {
            return IsCollect;
        }

        public void setIsCollect(int IsCollect) {
            this.IsCollect = IsCollect;
        }
    }
}
