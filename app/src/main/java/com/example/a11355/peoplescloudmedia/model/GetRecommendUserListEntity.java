package com.example.a11355.peoplescloudmedia.model;

import java.util.List;

/**
 * Created by 11355 on 2017/12/2.
 */

public class GetRecommendUserListEntity {


    /**
     * Code : 200
     * Message : 获取成功
     * Data : {"Count":3,"UserList":[{"Mobile":"15994250959","CreateDate":"2017-11-23 18:06:14"},{"Mobile":"15994250959","CreateDate":"2017-11-23 18:06:10"},{"Mobile":"18071419172","CreateDate":"2017-11-23 17:55:35"}]}
     * Number : 0
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
         * Count : 3
         * UserList : [{"Mobile":"15994250959","CreateDate":"2017-11-23 18:06:14"},{"Mobile":"15994250959","CreateDate":"2017-11-23 18:06:10"},{"Mobile":"18071419172","CreateDate":"2017-11-23 17:55:35"}]
         */

        private int Count;
        private List<UserListBean> UserList;

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

        public int getCount() {
            return Count;
        }

        public void setCount(int Count) {
            this.Count = Count;
        }

        public List<UserListBean> getUserList() {
            return UserList;
        }

        public void setUserList(List<UserListBean> UserList) {
            this.UserList = UserList;
        }

        public static class UserListBean {
            /**
             * Mobile : 15994250959
             * CreateDate : 2017-11-23 18:06:14
             */

            private String Mobile;
            private String CreateDate;
            private int type;

            public UserListBean(int type) {
                this.type = type;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
            public String getMobile() {
                return Mobile;
            }

            public void setMobile(String Mobile) {
                this.Mobile = Mobile;
            }

            public String getCreateDate() {
                return CreateDate;
            }

            public void setCreateDate(String CreateDate) {
                this.CreateDate = CreateDate;
            }
        }
    }
}
