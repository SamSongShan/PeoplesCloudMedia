package com.example.a11355.peoplescloudmedia.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11355 on 2018/1/1.
 */

public class GetUserProductInfoEntity {


    /**
     * Code : 200
     * Message : 获取成功
     * Data : {"MediaBlockList":[{"HtmlToTexts":"吃v哈哈","VideoImg":"default","MediaBlockId":"0c72628d-8fd3-43ad-83f1-d48c623be2c1","FilePath":"default","Texts":"吃v哈哈","MediaType":1,"Mark":2,"SortCode":1,"CreateDate":"2018-01-01 15:50:24","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b","OtherId":"6ad58348-1f74-4dad-93f3-ba8413481d8a"},{"HtmlToTexts":"打广告","VideoImg":"default","MediaBlockId":"1eb607c4-571a-4406-abfd-bb4062fe00e7","FilePath":"/Resource/WebUserImage/TN006/20180101154816186.jpg","Texts":"打广告","MediaType":2,"Mark":2,"SortCode":1,"CreateDate":"2018-01-01 15:50:24","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b","OtherId":"6ad58348-1f74-4dad-93f3-ba8413481d8a"},{"HtmlToTexts":"哥古古怪怪GVvvvv尴尬","VideoImg":"/Resource/WebUserImage/TN006/20180101154840272.jpg","MediaBlockId":"30bfa126-e372-424a-b024-6ae9498f8b7a","FilePath":"/Resource/WebUserVideo//20180101154839785.mp4","Texts":"哥古古怪怪GVvvvv尴尬","MediaType":3,"Mark":2,"SortCode":1,"CreateDate":"2018-01-01 15:50:24","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b","OtherId":"6ad58348-1f74-4dad-93f3-ba8413481d8a"},{"HtmlToTexts":"好好","VideoImg":"default","MediaBlockId":"32588f7d-fa64-4846-b2b4-27a8f9b328d9","FilePath":"default","Texts":"<a href=\"http://www.dunian.com\">好好<\/a>","MediaType":1,"Mark":2,"SortCode":1,"CreateDate":"2018-01-01 15:56:34","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b","OtherId":"6ad58348-1f74-4dad-93f3-ba8413481d8a"},{"HtmlToTexts":"都好喝","VideoImg":"/Resource/WebUserImage/TN006/20180101155428810.jpg","MediaBlockId":"7b0badb2-c7f1-424a-b973-e116edea0d58","FilePath":"/Resource/WebUserVideo//20180101155428183.mp4","Texts":"都好喝","MediaType":3,"Mark":2,"SortCode":1,"CreateDate":"2018-01-01 15:56:34","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b","OtherId":"6ad58348-1f74-4dad-93f3-ba8413481d8a"},{"HtmlToTexts":"dhjjdh你好hh","VideoImg":"default","MediaBlockId":"80416909-6b21-4290-a9c9-4f1b706316bb","FilePath":"/Resource/WebUserImage/TN006/20180101155242320.jpg","Texts":"dhjjdh<a href=\"http://www.du.com\">你好<\/a>hh","MediaType":2,"Mark":2,"SortCode":1,"CreateDate":"2018-01-01 15:56:34","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b","OtherId":"6ad58348-1f74-4dad-93f3-ba8413481d8a"}],"AttachLinkList":[{"OtherId":"6ad58348-1f74-4dad-93f3-ba8413481d8a","AttachLinkId":"5c9d4d7a-882e-4330-9f72-6772b669fcfd","LinkName":"点多","ToLink":"www.dfg.com","LinkType":2,"SortCode":1,"CreateDate":"2018-01-01 15:56:34","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b"},{"OtherId":"6ad58348-1f74-4dad-93f3-ba8413481d8a","AttachLinkId":"5f0ae24a-e937-4e48-a0c6-24c7fea797f6","LinkName":"添加链接","ToLink":"def","LinkType":2,"SortCode":1,"CreateDate":"2018-01-01 15:50:24","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b"},{"OtherId":"6ad58348-1f74-4dad-93f3-ba8413481d8a","AttachLinkId":"8d35493e-2d15-41db-b237-bd2ea61f15a8","LinkName":"添加链接","ToLink":"def","LinkType":2,"SortCode":1,"CreateDate":"2018-01-01 15:56:34","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b"},{"OtherId":"6ad58348-1f74-4dad-93f3-ba8413481d8a","AttachLinkId":"966b0a5b-5366-4c32-9684-3545d18eacab","LinkName":"百度","ToLink":"www.baidu.com","LinkType":2,"SortCode":1,"CreateDate":"2018-01-01 15:50:24","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b"}],"UserProductInfoId":"6ad58348-1f74-4dad-93f3-ba8413481d8a","UserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b","ProductImg":null,"ProductName":"产品名称","ProductPrice":"13","Description":"秒速","Title":"洗白白","IsUseMusic":"1","MusicPath":"http://m2.music.126.net/hmZoNQaqzZALvVp0rE7faA==/0.mp3","MusicName":"作词大赛DEMO 4","CreateDate":"2017-11-25 00:19:44"}
     * Number : 29
     */

    private int Code;
    private String Message;
    private DataEntity  Data;
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

    public DataEntity  getData() {
        return Data;
    }

    public void setData(DataEntity  Data) {
        this.Data = Data;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int Number) {
        this.Number = Number;
    }

    public static class DataEntity implements Parcelable {
        /**
         * MediaBlockList : [{"HtmlToTexts":"吃v哈哈","VideoImg":"default","MediaBlockId":"0c72628d-8fd3-43ad-83f1-d48c623be2c1","FilePath":"default","Texts":"吃v哈哈","MediaType":1,"Mark":2,"SortCode":1,"CreateDate":"2018-01-01 15:50:24","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b","OtherId":"6ad58348-1f74-4dad-93f3-ba8413481d8a"},{"HtmlToTexts":"打广告","VideoImg":"default","MediaBlockId":"1eb607c4-571a-4406-abfd-bb4062fe00e7","FilePath":"/Resource/WebUserImage/TN006/20180101154816186.jpg","Texts":"打广告","MediaType":2,"Mark":2,"SortCode":1,"CreateDate":"2018-01-01 15:50:24","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b","OtherId":"6ad58348-1f74-4dad-93f3-ba8413481d8a"},{"HtmlToTexts":"哥古古怪怪GVvvvv尴尬","VideoImg":"/Resource/WebUserImage/TN006/20180101154840272.jpg","MediaBlockId":"30bfa126-e372-424a-b024-6ae9498f8b7a","FilePath":"/Resource/WebUserVideo//20180101154839785.mp4","Texts":"哥古古怪怪GVvvvv尴尬","MediaType":3,"Mark":2,"SortCode":1,"CreateDate":"2018-01-01 15:50:24","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b","OtherId":"6ad58348-1f74-4dad-93f3-ba8413481d8a"},{"HtmlToTexts":"好好","VideoImg":"default","MediaBlockId":"32588f7d-fa64-4846-b2b4-27a8f9b328d9","FilePath":"default","Texts":"<a href=\"http://www.dunian.com\">好好<\/a>","MediaType":1,"Mark":2,"SortCode":1,"CreateDate":"2018-01-01 15:56:34","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b","OtherId":"6ad58348-1f74-4dad-93f3-ba8413481d8a"},{"HtmlToTexts":"都好喝","VideoImg":"/Resource/WebUserImage/TN006/20180101155428810.jpg","MediaBlockId":"7b0badb2-c7f1-424a-b973-e116edea0d58","FilePath":"/Resource/WebUserVideo//20180101155428183.mp4","Texts":"都好喝","MediaType":3,"Mark":2,"SortCode":1,"CreateDate":"2018-01-01 15:56:34","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b","OtherId":"6ad58348-1f74-4dad-93f3-ba8413481d8a"},{"HtmlToTexts":"dhjjdh你好hh","VideoImg":"default","MediaBlockId":"80416909-6b21-4290-a9c9-4f1b706316bb","FilePath":"/Resource/WebUserImage/TN006/20180101155242320.jpg","Texts":"dhjjdh<a href=\"http://www.du.com\">你好<\/a>hh","MediaType":2,"Mark":2,"SortCode":1,"CreateDate":"2018-01-01 15:56:34","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b","OtherId":"6ad58348-1f74-4dad-93f3-ba8413481d8a"}]
         * AttachLinkList : [{"OtherId":"6ad58348-1f74-4dad-93f3-ba8413481d8a","AttachLinkId":"5c9d4d7a-882e-4330-9f72-6772b669fcfd","LinkName":"点多","ToLink":"www.dfg.com","LinkType":2,"SortCode":1,"CreateDate":"2018-01-01 15:56:34","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b"},{"OtherId":"6ad58348-1f74-4dad-93f3-ba8413481d8a","AttachLinkId":"5f0ae24a-e937-4e48-a0c6-24c7fea797f6","LinkName":"添加链接","ToLink":"def","LinkType":2,"SortCode":1,"CreateDate":"2018-01-01 15:50:24","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b"},{"OtherId":"6ad58348-1f74-4dad-93f3-ba8413481d8a","AttachLinkId":"8d35493e-2d15-41db-b237-bd2ea61f15a8","LinkName":"添加链接","ToLink":"def","LinkType":2,"SortCode":1,"CreateDate":"2018-01-01 15:56:34","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b"},{"OtherId":"6ad58348-1f74-4dad-93f3-ba8413481d8a","AttachLinkId":"966b0a5b-5366-4c32-9684-3545d18eacab","LinkName":"百度","ToLink":"www.baidu.com","LinkType":2,"SortCode":1,"CreateDate":"2018-01-01 15:50:24","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b"}]
         * UserProductInfoId : 6ad58348-1f74-4dad-93f3-ba8413481d8a
         * UserId : 212bd12a-37f2-4eb4-ae84-6dd9e552434b
         * ProductImg : null
         * ProductName : 产品名称
         * ProductPrice : 13
         * Description : 秒速
         * Title : 洗白白
         * IsUseMusic : 1
         * MusicPath : http://m2.music.126.net/hmZoNQaqzZALvVp0rE7faA==/0.mp3
         * MusicName : 作词大赛DEMO 4
         * CreateDate : 2017-11-25 00:19:44
         */

        private String UserProductInfoId;
        private String UserId;
        private String ProductImg;
        private String ProductName;
        private String ProductPrice;
        private String Description;
        private String Title;
        private String IsUseMusic;
        private String MusicPath;
        private String MusicName;
        private String CreateDate;
        private List<MediaBlockListEntity > MediaBlockList;
        private List<AttachLinkListEntity > AttachLinkList;

        public String getUserProductInfoId() {
            return UserProductInfoId;
        }

        public void setUserProductInfoId(String UserProductInfoId) {
            this.UserProductInfoId = UserProductInfoId;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getProductImg() {
            return ProductImg;
        }

        public void setProductImg(String ProductImg) {
            this.ProductImg = ProductImg;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public String getProductPrice() {
            return ProductPrice;
        }

        public void setProductPrice(String ProductPrice) {
            this.ProductPrice = ProductPrice;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getIsUseMusic() {
            return IsUseMusic;
        }

        public void setIsUseMusic(String IsUseMusic) {
            this.IsUseMusic = IsUseMusic;
        }

        public String getMusicPath() {
            return MusicPath;
        }

        public void setMusicPath(String MusicPath) {
            this.MusicPath = MusicPath;
        }

        public String getMusicName() {
            return MusicName;
        }

        public void setMusicName(String MusicName) {
            this.MusicName = MusicName;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public List<MediaBlockListEntity > getMediaBlockList() {
            return MediaBlockList;
        }

        public void setMediaBlockList(List<MediaBlockListEntity > MediaBlockList) {
            this.MediaBlockList = MediaBlockList;
        }

        public List<AttachLinkListEntity > getAttachLinkList() {
            return AttachLinkList;
        }

        public void setAttachLinkList(List<AttachLinkListEntity > AttachLinkList) {
            this.AttachLinkList = AttachLinkList;
        }

        public static class MediaBlockListEntity  {
            /**
             * HtmlToTexts : 吃v哈哈
             * VideoImg : default
             * MediaBlockId : 0c72628d-8fd3-43ad-83f1-d48c623be2c1
             * FilePath : default
             * Texts : 吃v哈哈
             * MediaType : 1
             * Mark : 2
             * SortCode : 1
             * CreateDate : 2018-01-01 15:50:24
             * CreateUserId : 212bd12a-37f2-4eb4-ae84-6dd9e552434b
             * OtherId : 6ad58348-1f74-4dad-93f3-ba8413481d8a
             */

            private String HtmlToTexts;
            private String VideoImg;
            private String MediaBlockId;
            private String FilePath;
            private String Texts;
            private int MediaType;
            private int Mark;
            private int SortCode;
            private String CreateDate;
            private String CreateUserId;
            private String OtherId;

            public String getHtmlToTexts() {
                return HtmlToTexts;
            }

            public void setHtmlToTexts(String HtmlToTexts) {
                this.HtmlToTexts = HtmlToTexts;
            }

            public String getVideoImg() {
                return VideoImg;
            }

            public void setVideoImg(String VideoImg) {
                this.VideoImg = VideoImg;
            }

            public String getMediaBlockId() {
                return MediaBlockId;
            }

            public void setMediaBlockId(String MediaBlockId) {
                this.MediaBlockId = MediaBlockId;
            }

            public String getFilePath() {
                return FilePath;
            }

            public void setFilePath(String FilePath) {
                this.FilePath = FilePath;
            }

            public String getTexts() {
                return Texts;
            }

            public void setTexts(String Texts) {
                this.Texts = Texts;
            }

            public int getMediaType() {
                return MediaType;
            }

            public void setMediaType(int MediaType) {
                this.MediaType = MediaType;
            }

            public int getMark() {
                return Mark;
            }

            public void setMark(int Mark) {
                this.Mark = Mark;
            }

            public int getSortCode() {
                return SortCode;
            }

            public void setSortCode(int SortCode) {
                this.SortCode = SortCode;
            }

            public String getCreateDate() {
                return CreateDate;
            }

            public void setCreateDate(String CreateDate) {
                this.CreateDate = CreateDate;
            }

            public String getCreateUserId() {
                return CreateUserId;
            }

            public void setCreateUserId(String CreateUserId) {
                this.CreateUserId = CreateUserId;
            }

            public String getOtherId() {
                return OtherId;
            }

            public void setOtherId(String OtherId) {
                this.OtherId = OtherId;
            }
        }

        public static class AttachLinkListEntity  {
            /**
             * OtherId : 6ad58348-1f74-4dad-93f3-ba8413481d8a
             * AttachLinkId : 5c9d4d7a-882e-4330-9f72-6772b669fcfd
             * LinkName : 点多
             * ToLink : www.dfg.com
             * LinkType : 2
             * SortCode : 1
             * CreateDate : 2018-01-01 15:56:34
             * CreateUserId : 212bd12a-37f2-4eb4-ae84-6dd9e552434b
             */

            private String OtherId;
            private String AttachLinkId;
            private String LinkName;
            private String ToLink;
            private int LinkType;
            private int SortCode;
            private String CreateDate;
            private String CreateUserId;

            public String getOtherId() {
                return OtherId;
            }

            public void setOtherId(String OtherId) {
                this.OtherId = OtherId;
            }

            public String getAttachLinkId() {
                return AttachLinkId;
            }

            public void setAttachLinkId(String AttachLinkId) {
                this.AttachLinkId = AttachLinkId;
            }

            public String getLinkName() {
                return LinkName;
            }

            public void setLinkName(String LinkName) {
                this.LinkName = LinkName;
            }

            public String getToLink() {
                return ToLink;
            }

            public void setToLink(String ToLink) {
                this.ToLink = ToLink;
            }

            public int getLinkType() {
                return LinkType;
            }

            public void setLinkType(int LinkType) {
                this.LinkType = LinkType;
            }

            public int getSortCode() {
                return SortCode;
            }

            public void setSortCode(int SortCode) {
                this.SortCode = SortCode;
            }

            public String getCreateDate() {
                return CreateDate;
            }

            public void setCreateDate(String CreateDate) {
                this.CreateDate = CreateDate;
            }

            public String getCreateUserId() {
                return CreateUserId;
            }

            public void setCreateUserId(String CreateUserId) {
                this.CreateUserId = CreateUserId;
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.UserProductInfoId);
            dest.writeString(this.UserId);
            dest.writeString(this.ProductImg);
            dest.writeString(this.ProductName);
            dest.writeString(this.ProductPrice);
            dest.writeString(this.Description);
            dest.writeString(this.Title);
            dest.writeString(this.IsUseMusic);
            dest.writeString(this.MusicPath);
            dest.writeString(this.MusicName);
            dest.writeString(this.CreateDate);
            dest.writeList(this.MediaBlockList);
            dest.writeList(this.AttachLinkList);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.UserProductInfoId = in.readString();
            this.UserId = in.readString();
            this.ProductImg = in.readString();
            this.ProductName = in.readString();
            this.ProductPrice = in.readString();
            this.Description = in.readString();
            this.Title = in.readString();
            this.IsUseMusic = in.readString();
            this.MusicPath = in.readString();
            this.MusicName = in.readString();
            this.CreateDate = in.readString();
            this.MediaBlockList = new ArrayList<MediaBlockListEntity>();
            in.readList(this.MediaBlockList, MediaBlockListEntity.class.getClassLoader());
            this.AttachLinkList = new ArrayList<AttachLinkListEntity>();
            in.readList(this.AttachLinkList, AttachLinkListEntity.class.getClassLoader());
        }

        public static final Parcelable.Creator<DataEntity> CREATOR = new Parcelable.Creator<DataEntity>() {
            @Override
            public DataEntity createFromParcel(Parcel source) {
                return new DataEntity(source);
            }

            @Override
            public DataEntity[] newArray(int size) {
                return new DataEntity[size];
            }
        };
    }
}
