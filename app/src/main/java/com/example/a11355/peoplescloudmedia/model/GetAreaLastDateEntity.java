package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by ss on 2017/11/22 0022.
 */

public class GetAreaLastDateEntity {


    /**
     * Number : 3
     * Code : 200
     * Message : 获取成功
     * Data : {"LastDate":"2017/11/22 13:47:31"}
     */

    private int Number;
    private int Code;
    private String Message;
    private DataEntity Data;

    public int getNumber() {
        return Number;
    }

    public void setNumber(int Number) {
        this.Number = Number;
    }

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
        /**
         * LastDate : 2017/11/22 13:47:31
         */

        private String LastDate;

        public String getLastDate() {
            return LastDate;
        }

        public void setLastDate(String LastDate) {
            this.LastDate = LastDate;
        }
    }
}
