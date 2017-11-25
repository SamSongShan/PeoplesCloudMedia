package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by 11355 on 2017/11/25.
 */

public class LoginRoleEntity {


    /**
     * Code : 200
     * Message : 登录成功
     * Data : {"UserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b","Token":"DBD9C4D01D17D71E3669E0904B159BEB53415545A63EAB82CDC321316657DF7C919A576457B7C0A0"}
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
         * Token : DBD9C4D01D17D71E3669E0904B159BEB53415545A63EAB82CDC321316657DF7C919A576457B7C0A0
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
