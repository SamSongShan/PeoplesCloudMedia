package com.example.a11355.peoplescloudmedia.model;

import java.util.List;

/**
 * Created by 11355 on 2017/12/3.
 */

public class GetNewsListEntity {


    /**
     * Code : 200
     * Message : 获取成功
     * Data : {"artiles":[{"id":465,"thumbs":[],"title":"【漫画税收】电子营业执照，是否应该贴花呢？","reads_count":139,"comments_count":0,"reposts_count":0,"avatar":"http://www.renzhu.com/tu/P/2014/10/24/11534083s.jpg"},{"id":289,"thumbs":[],"title":"广州广大微创整形：广州的夏天适合去丰胸隆胸吗String","reads_count":154,"comments_count":0,"reposts_count":0,"avatar":"http://www.renzhu.com/images/head.jpg"}],"total":2}
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
         * artiles : [{"id":465,"thumbs":[],"title":"【漫画税收】电子营业执照，是否应该贴花呢？","reads_count":139,"comments_count":0,"reposts_count":0,"avatar":"http://www.renzhu.com/tu/P/2014/10/24/11534083s.jpg"},{"id":289,"thumbs":[],"title":"广州广大微创整形：广州的夏天适合去丰胸隆胸吗String","reads_count":154,"comments_count":0,"reposts_count":0,"avatar":"http://www.renzhu.com/images/head.jpg"}]
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
             * id : 465
             * thumbs : []
             * title : 【漫画税收】电子营业执照，是否应该贴花呢？
             * reads_count : 139
             * comments_count : 0
             * reposts_count : 0
             * avatar : http://www.renzhu.com/tu/P/2014/10/24/11534083s.jpg
             */

            private int id;
            private String title;
            private int reads_count;
            private int comments_count;
            private int reposts_count;
            private String avatar;
            private List<String> thumbs;
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

            public List<String> getThumbs() {
                return thumbs;
            }

            public void setThumbs(List<String> thumbs) {
                this.thumbs = thumbs;
            }
        }
    }
}
