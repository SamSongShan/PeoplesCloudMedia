package com.example.a11355.peoplescloudmedia.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by 11355 on 2017/12/3.
 */

public class GetNewsListEntity {


    /**
     * Code : 200
     * Message : 获取成功
     * Data : {"artiles":[{"id":465,"thumb":"","title":"【漫画税收】电子营业执照，是否应该贴花呢？","reads_count":139,"comments_count":0,"reposts_count":0,"avatar":"http://www.renzhu.com/tu/P/2014/10/24/11534083s.jpg","author":"铁山"},{"id":289,"thumb":"","title":"广州广大微创整形：广州的夏天适合去丰胸隆胸吗?","reads_count":155,"comments_count":0,"reposts_count":0,"avatar":"http://www.renzhu.com/images/head.jpg","author":"13798165787"}],"total":2}
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
         * artiles : [{"id":465,"thumb":"","title":"【漫画税收】电子营业执照，是否应该贴花呢？","reads_count":139,"comments_count":0,"reposts_count":0,"avatar":"http://www.renzhu.com/tu/P/2014/10/24/11534083s.jpg","author":"铁山"},{"id":289,"thumb":"","title":"广州广大微创整形：广州的夏天适合去丰胸隆胸吗?","reads_count":155,"comments_count":0,"reposts_count":0,"avatar":"http://www.renzhu.com/images/head.jpg","author":"13798165787"}]
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

        public static class ArtilesBean implements Parcelable {
            /**
             * id : 465
             * thumb :
             * title : 【漫画税收】电子营业执照，是否应该贴花呢？
             * reads_count : 139
             * comments_count : 0
             * reposts_count : 0
             * avatar : http://www.renzhu.com/tu/P/2014/10/24/11534083s.jpg
             * author : 铁山
             */

            private int id;
            private String thumb;
            private String title;
            private int reads_count;
            private int comments_count;
            private int reposts_count;
            private String avatar;
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

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.thumb);
                dest.writeString(this.title);
                dest.writeInt(this.reads_count);
                dest.writeInt(this.comments_count);
                dest.writeInt(this.reposts_count);
                dest.writeString(this.avatar);
                dest.writeString(this.author);
                dest.writeInt(this.type);
            }

            protected ArtilesBean(Parcel in) {
                this.id = in.readInt();
                this.thumb = in.readString();
                this.title = in.readString();
                this.reads_count = in.readInt();
                this.comments_count = in.readInt();
                this.reposts_count = in.readInt();
                this.avatar = in.readString();
                this.author = in.readString();
                this.type = in.readInt();
            }

            public static final Parcelable.Creator<ArtilesBean> CREATOR = new Parcelable.Creator<ArtilesBean>() {
                @Override
                public ArtilesBean createFromParcel(Parcel source) {
                    return new ArtilesBean(source);
                }

                @Override
                public ArtilesBean[] newArray(int size) {
                    return new ArtilesBean[size];
                }
            };
        }
    }
}
