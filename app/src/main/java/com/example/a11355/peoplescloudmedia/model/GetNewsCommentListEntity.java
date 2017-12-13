package com.example.a11355.peoplescloudmedia.model;

import java.util.List;

/**
 * Created by ss on 2017/12/13 0013.
 */

public class GetNewsCommentListEntity {


    /**
     * Code : 200
     * Message : 获取成功
     * Data : {"comments":[{"avatar":"http://www.renzhu.com/images/head.jpg","name":"13632840502","created_at":"2017-12-13 13:27:50","text":"发的休息休息"},{"avatar":"http://www.renzhu.com/images/head.jpg","name":"13632840502","created_at":"2017-12-13 13:27:48","text":"发的休息休息"},{"avatar":"http://www.renzhu.com/images/head.jpg","name":"13632840502","created_at":"2017-12-13 13:27:33","text":"发的休息休息"},{"avatar":"http://www.renzhu.com/images/head.jpg","name":"13632840502","created_at":"2017-12-13 13:27:33","text":"发的休息休息"}],"total":4}
     * Number : 32
     */

    private int Code;
    private String Message;
    private DataEntity Data;
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

    public DataEntity getData() {
        return Data;
    }

    public void setData(DataEntity Data) {
        this.Data = Data;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int Number) {
        this.Number = Number;
    }

    public static class DataEntity {
        /**
         * comments : [{"avatar":"http://www.renzhu.com/images/head.jpg","name":"13632840502","created_at":"2017-12-13 13:27:50","text":"发的休息休息"},{"avatar":"http://www.renzhu.com/images/head.jpg","name":"13632840502","created_at":"2017-12-13 13:27:48","text":"发的休息休息"},{"avatar":"http://www.renzhu.com/images/head.jpg","name":"13632840502","created_at":"2017-12-13 13:27:33","text":"发的休息休息"},{"avatar":"http://www.renzhu.com/images/head.jpg","name":"13632840502","created_at":"2017-12-13 13:27:33","text":"发的休息休息"}]
         * total : 4
         */

        private int total;
        private List<CommentsEntity> comments;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<CommentsEntity> getComments() {
            return comments;
        }

        public void setComments(List<CommentsEntity> comments) {
            this.comments = comments;
        }

        public static class CommentsEntity {
            /**
             * avatar : http://www.renzhu.com/images/head.jpg
             * name : 13632840502
             * created_at : 2017-12-13 13:27:50
             * text : 发的休息休息
             */

            private String avatar;
            private String name;
            private String created_at;
            private String text;

            private int type;

            public CommentsEntity(int type) {
                this.type = type;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }
    }
}
