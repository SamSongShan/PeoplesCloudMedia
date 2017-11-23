package com.example.a11355.peoplescloudmedia.model;

/**
 * 手机验证码 实体类
 */
public class MobileCodeEntity {

    /**
     * Code : 200
     * Message : 发送成功
     * Data : {"Mobile":"1857567","Code":"8566"}
     */

    private int Code;
    private String Message;
    /**
     * Mobile : 1857567
     * Code : 8566
     */

    private DataEntity Data;

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

    public DataEntity getData() {
        return Data;
    }

    public void setData(DataEntity Data) {
        this.Data = Data;
    }

    public static class DataEntity {
        private String Mobile;
        private String Code;

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public String getCode() {
            return Code;
        }

        public void setCode(String Code) {
            this.Code = Code;
        }
    }
}
