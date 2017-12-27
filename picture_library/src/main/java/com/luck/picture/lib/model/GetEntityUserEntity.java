package com.luck.picture.lib.model;

/**
 * Created by 11355 on 2017/11/26.
 */

public class GetEntityUserEntity {


    /**
     * Code : 200
     * Message : 获取成功
     * Data : {"FocusNum":0,"FansNum":0,"CollectNum":0,"Signature":null,"UserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b","EnCode":"TN006","RoleType":"0","NickName":"13632840502","HeadIcon":null,"Gender":null,"Mobile":"13632840502","Description":null,"EnabledMark":1}
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
         * FocusNum : 0
         * FansNum : 0
         * CollectNum : 0
         * Signature : null
         * UserId : 212bd12a-37f2-4eb4-ae84-6dd9e552434b
         * EnCode : TN006
         * RoleType : 0
         * NickName : 13632840502
         * HeadIcon : null
         * Gender : null
         * Mobile : 13632840502
         * Description : null
         * EnabledMark : 1
         */

        private int FocusNum;
        private int FansNum;
        private int CollectNum;
        private String Signature;
        private String UserId;
        private String EnCode;
        private String RoleType;
        private String NickName;
        private String HeadIcon;
        private String Gender;
        private String Mobile;
        private String Description;
        private int EnabledMark;

        public int getFocusNum() {
            return FocusNum;
        }

        public void setFocusNum(int FocusNum) {
            this.FocusNum = FocusNum;
        }

        public int getFansNum() {
            return FansNum;
        }

        public void setFansNum(int FansNum) {
            this.FansNum = FansNum;
        }

        public int getCollectNum() {
            return CollectNum;
        }

        public void setCollectNum(int CollectNum) {
            this.CollectNum = CollectNum;
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

        public String getEnCode() {
            return EnCode;
        }

        public void setEnCode(String EnCode) {
            this.EnCode = EnCode;
        }

        public String getRoleType() {
            return RoleType;
        }

        public void setRoleType(String RoleType) {
            this.RoleType = RoleType;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public String getHeadIcon() {
            return HeadIcon;
        }

        public void setHeadIcon(String HeadIcon) {
            this.HeadIcon = HeadIcon;
        }

        public String getGender() {
            return Gender;
        }

        public void setGender(String Gender) {
            this.Gender = Gender;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public int getEnabledMark() {
            return EnabledMark;
        }

        public void setEnabledMark(int EnabledMark) {
            this.EnabledMark = EnabledMark;
        }
    }
}
