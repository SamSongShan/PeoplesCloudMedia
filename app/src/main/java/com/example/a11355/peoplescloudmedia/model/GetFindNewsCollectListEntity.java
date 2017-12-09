package com.example.a11355.peoplescloudmedia.model;

import java.util.List;

/**
 * Created by 11355 on 2017/12/9.
 */

public class GetFindNewsCollectListEntity {


    /**
     * Code : 200
     * Message : 获取成功
     * Data : {"artiles":[{"id":659,"thumb":"","title":"旅行让你遇见更美好的东西，只有你想不到的","reads_count":219,"comments_count":0,"reposts_count":0,"avatar":"http://www.renzhu.com/images/head.jpg","created_at":"2017-12-09 21:42:06","author":"wenxin"},{"id":559,"thumb":"","title":"去旅游一定要学会这一招！震惊了我！学会益终生","reads_count":194,"comments_count":0,"reposts_count":0,"avatar":"http://www.renzhu.com/images/head.jpg","created_at":"2017-12-09 21:49:04","author":"wenxin"}],"total":2}
     * Number : 38
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
         * artiles : [{"id":659,"thumb":"","title":"旅行让你遇见更美好的东西，只有你想不到的","reads_count":219,"comments_count":0,"reposts_count":0,"avatar":"http://www.renzhu.com/images/head.jpg","created_at":"2017-12-09 21:42:06","author":"wenxin"},{"id":559,"thumb":"","title":"去旅游一定要学会这一招！震惊了我！学会益终生","reads_count":194,"comments_count":0,"reposts_count":0,"avatar":"http://www.renzhu.com/images/head.jpg","created_at":"2017-12-09 21:49:04","author":"wenxin"}]
         * total : 2
         */

        private int total;
        private List<ArtilesBean> artiles;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ArtilesBean> getArtiles() {
            return artiles;
        }

        public void setArtiles(List<ArtilesBean> artiles) {
            this.artiles = artiles;
        }

        public static class ArtilesBean {


            /**
             * id : 659
             * thumb :
             * title : 旅行让你遇见更美好的东西，只有你想不到的
             * reads_count : 219
             * comments_count : 0
             * reposts_count : 0
             * avatar : http://www.renzhu.com/images/head.jpg
             * created_at : 2017-12-09 21:42:06
             * author : wenxin
             */

            private int id;
            private String thumb;
            private String title;
            private int reads_count;
            private int comments_count;
            private int reposts_count;
            private String avatar;
            private String created_at;
            private String author;

            private int type;

            public ArtilesBean(int type) {
                this.type = type;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getReads_count() {
                return reads_count;
            }

            public void setReads_count(int reads_count) {
                this.reads_count = reads_count;
            }

            public int getComments_count() {
                return comments_count;
            }

            public void setComments_count(int comments_count) {
                this.comments_count = comments_count;
            }

            public int getReposts_count() {
                return reposts_count;
            }

            public void setReposts_count(int reposts_count) {
                this.reposts_count = reposts_count;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }
        }
    }
}
