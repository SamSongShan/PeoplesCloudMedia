package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by 11355 on 2017/11/25.
 */

public class RegisteredEntity {


    /**
     * Code : 200
     * Message : 注册成功
     * Data : {"UserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b","Token":"900D8E4BFE8BAF098729480B6044F5155199FD70808FC82A5E2A0EE857BE54CDF1D0525C35ABD775"}
     */

    private int Code;
    private String Message;
    private DataBean Data;

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

    public static class DataBean {
        /**
         * UserId : 212bd12a-37f2-4eb4-ae84-6dd9e552434b
         * Token : 900D8E4BFE8BAF098729480B6044F5155199FD70808FC82A5E2A0EE857BE54CDF1D0525C35ABD775
         */

        private String UserId;
        private String Token;

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getToken() {
            return Token;
        }

        public void setToken(String Token) {
            this.Token = Token;
        }
    }
}
