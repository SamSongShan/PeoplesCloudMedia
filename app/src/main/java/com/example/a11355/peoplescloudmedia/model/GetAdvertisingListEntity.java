package com.example.a11355.peoplescloudmedia.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by ss on 2017/12/22 0022.
 */

public class GetAdvertisingListEntity {


    /**
     * Code : 200
     * Message : 获取成功
     * Data : [{"AdvertisingId":"d3f008f3-ec55-4e9f-a760-0acd1f5d2208","FilePath":"/Resource/WebUserImage/TN006/20171222165425606.jpg","FullHead":"发个干活","ToLink":"https://mp.weixin.qq.com/s?src=11&timestamp=1513838528&ver=587&signature=c1c19u76Smj-uBHAfOeyBptUawlVQb2OacO4aYJ6-*o5oO4KJTguIDZeE1XAvE*jjxlueWwwoIsTfVP7UvzpX-PjUynO5mPed1sjJVjWojrihCQ9c6ogJAwG53OnPNWQ&new=1","Content":"断断续续","EnabledMark":1,"CreateDate":"2017-12-22 16:55:52","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b"},{"AdvertisingId":"3bdcd0c7-5260-46fb-be3d-60235c72b805","FilePath":"/Resource/WebUserImage/TN006/20171222165159135.jpg","FullHead":"等你那你","ToLink":"https://mp.weixin.qq.com/s?src=11&timestamp=1513838528&ver=587&signature=c1c19u76Smj-uBHAfOeyBptUawlVQb2OacO4aYJ6-*o5oO4KJTguIDZeE1XAvE*jjxlueWwwoIsTfVP7UvzpX-PjUynO5mPed1sjJVjWojrihCQ9c6ogJAwG53OnPNWQ&new=1","Content":"在哪呢那你想那你先弄逆袭你那谢娜","EnabledMark":1,"CreateDate":"2017-12-22 16:52:25","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b"},{"AdvertisingId":"025a0888-b815-4ce9-9dc9-60b54db4efae","FilePath":"/Resource/WebUserImage/TN006/20171222164712559.jpg","FullHead":"鲅鱼","ToLink":"https://mp.weixin.qq.com/s?src=11&timestamp=1513838528&ver=587&signature=c1c19u76Smj-uBHAfOeyBptUawlVQb2OacO4aYJ6-*o5oO4KJTguIDZeE1XAvE*jjxlueWwwoIsTfVP7UvzpX-PjUynO5mPed1sjJVjWojrihCQ9c6ogJAwG53OnPNWQ&new=1","Content":"点击锦绣江南","EnabledMark":1,"CreateDate":"2017-12-22 16:48:29","CreateUserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b"}]
     * Number : 52
     */

    private int Code;
    private String Message;
    private int Number;
    private List<DataEntity> Data;

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

    public int getNumber() {
        return Number;
    }

    public void setNumber(int Number) {
        this.Number = Number;
    }

    public List<DataEntity> getData() {
        return Data;
    }

    public void setData(List<DataEntity> Data) {
        this.Data = Data;
    }

    public static class DataEntity implements Parcelable {
        /**
         * AdvertisingId : d3f008f3-ec55-4e9f-a760-0acd1f5d2208
         * FilePath : /Resource/WebUserImage/TN006/20171222165425606.jpg
         * FullHead : 发个干活
         * ToLink : https://mp.weixin.qq.com/s?src=11&timestamp=1513838528&ver=587&signature=c1c19u76Smj-uBHAfOeyBptUawlVQb2OacO4aYJ6-*o5oO4KJTguIDZeE1XAvE*jjxlueWwwoIsTfVP7UvzpX-PjUynO5mPed1sjJVjWojrihCQ9c6ogJAwG53OnPNWQ&new=1
         * Content : 断断续续
         * EnabledMark : 1
         * CreateDate : 2017-12-22 16:55:52
         * CreateUserId : 212bd12a-37f2-4eb4-ae84-6dd9e552434b
         */

        private String AdvertisingId;
        private String FilePath;
        private String FullHead;
        private String ToLink;
        private String Content;
        private int EnabledMark;
        private String CreateDate;
        private String CreateUserId;

        private int type;

        public DataEntity(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getAdvertisingId() {
            return AdvertisingId;
        }

        public void setAdvertisingId(String AdvertisingId) {
            this.AdvertisingId = AdvertisingId;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String FilePath) {
            this.FilePath = FilePath;
        }

        public String getFullHead() {
            return FullHead;
        }

        public void setFullHead(String FullHead) {
            this.FullHead = FullHead;
        }

        public String getToLink() {
            return ToLink;
        }

        public void setToLink(String ToLink) {
            this.ToLink = ToLink;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public int getEnabledMark() {
            return EnabledMark;
        }

        public void setEnabledMark(int EnabledMark) {
            this.EnabledMark = EnabledMark;
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.AdvertisingId);
            dest.writeString(this.FilePath);
            dest.writeString(this.FullHead);
            dest.writeString(this.ToLink);
            dest.writeString(this.Content);
            dest.writeInt(this.EnabledMark);
            dest.writeString(this.CreateDate);
            dest.writeString(this.CreateUserId);
            dest.writeInt(this.type);
        }

        protected DataEntity(Parcel in) {
            this.AdvertisingId = in.readString();
            this.FilePath = in.readString();
            this.FullHead = in.readString();
            this.ToLink = in.readString();
            this.Content = in.readString();
            this.EnabledMark = in.readInt();
            this.CreateDate = in.readString();
            this.CreateUserId = in.readString();
            this.type = in.readInt();
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
