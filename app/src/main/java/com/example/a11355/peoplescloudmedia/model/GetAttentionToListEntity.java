package com.example.a11355.peoplescloudmedia.model;

import java.util.List;

/**
 * Created by 11355 on 2017/12/9.
 */

public class GetAttentionToListEntity {


    /**
     * Code : 200
     * Message : 获取成功
     * Data : [{"HeadIcon":"/Resource/WebUserImage/QE001/20171204191705106.jpg","NickName":"Lou789","Signature":"So are you!","UserId":"687ad7e4-3273-496a-a649-1095ee431afe"}]
     * Number : 43
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
         * HeadIcon : /Resource/WebUserImage/QE001/20171204191705106.jpg
         * NickName : Lou789
         * Signature : So are you!
         * UserId : 687ad7e4-3273-496a-a649-1095ee431afe
         */

        private String HeadIcon;
        private String NickName;
        private String Signature;
        private String UserId;

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

        public String getSignature() {
            return Signature;
        }

        public void setSignature(String Signature) {
            this.Signature = Signature;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }
    }
}
