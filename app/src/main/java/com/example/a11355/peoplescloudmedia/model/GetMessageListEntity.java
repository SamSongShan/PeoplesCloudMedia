package com.example.a11355.peoplescloudmedia.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by 11355 on 2017/12/3.
 */

public class GetMessageListEntity {


    /**
     * Code : 200
     * Message : 获取成功
     * Data : [{"MessageId":"1e7c8e24-7308-441a-a710-4db44a3dd57f","FullHead":"给你的一封信","BriefHead":"人众云媒团队","FilePath":"/Resource/VideoImg/20171119/36b38867-b5c3-4834-ac71-b7a589e5761a.jpg","IsJpush":0,"Content":"&lt;p&gt;HI&lt;/p&gt;&lt;p&gt;感谢每一次使用人众云媒的你，从今天起我们会发生很多故事。&lt;/p&gt;&lt;p&gt;人众云媒程序猿&lt;/p&gt;&lt;p&gt;人众云媒某设计师&lt;/p&gt;&lt;p&gt;人众云媒团队想把最好的给你&lt;/p&gt;","PV":0,"ReleaseTime":"2017-11-19 17:05:00","SortCode":1,"EnabledMark":1,"CreateDate":"2017-11-19 17:10:44","CreateUserId":"System","CreateUserName":"超级管理员","ModifyDate":null,"ModifyUserId":null,"ModifyUserName":null},{"MessageId":"e9269dc7-573e-4fe5-8c32-66ecf8b9cb1a","FullHead":"人众云媒APP上线通知","BriefHead":"上线通知啦！！","FilePath":"/Resource/VideoImg/20171119/c281969b-663c-41bb-90d4-308716b85a15.jpg","IsJpush":0,"Content":"&lt;p&gt;随着大众传播媒介的不断发展,除了广播、电视、报纸等传统媒体外,网站、微博、微信等新媒体经过短短十几年时间其社会影响力以惊人的速度不断增大,在大众传播中越来越占有举足轻重的地位。&lt;/p&gt;&lt;p&gt;而品牌推广是企业壮大发展必不可少的环节,更离不开对大众传播手段的运用。其中,新媒体在品牌推广中起着不可忽视的作用。1新媒体的定义新媒体这一名词是相较于传统媒体而言的,它随着互联网的发展,逐渐派生出多种多样的形式,并以其独有的特质迅速吸引了社会大众的注意力,成为人们日常生活中与外界联系的不可或缺的纽带。&lt;/p&gt;","PV":0,"ReleaseTime":"2017-11-19 16:39:00","SortCode":1,"EnabledMark":1,"CreateDate":"2017-11-19 16:40:02","CreateUserId":"System","CreateUserName":"超级管理员","ModifyDate":"2017-11-19 16:40:15","ModifyUserId":"System","ModifyUserName":"超级管理员"}]
     * Number : 0
     */

    private int Code;
    private String Message;
    private int Number;
    private List<DataBean> Data;

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

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Parcelable {
        /**
         * MessageId : 1e7c8e24-7308-441a-a710-4db44a3dd57f
         * FullHead : 给你的一封信
         * BriefHead : 人众云媒团队
         * FilePath : /Resource/VideoImg/20171119/36b38867-b5c3-4834-ac71-b7a589e5761a.jpg
         * IsJpush : 0
         * Content : &lt;p&gt;HI&lt;/p&gt;&lt;p&gt;感谢每一次使用人众云媒的你，从今天起我们会发生很多故事。&lt;/p&gt;&lt;p&gt;人众云媒程序猿&lt;/p&gt;&lt;p&gt;人众云媒某设计师&lt;/p&gt;&lt;p&gt;人众云媒团队想把最好的给你&lt;/p&gt;
         * PV : 0
         * ReleaseTime : 2017-11-19 17:05:00
         * SortCode : 1
         * EnabledMark : 1
         * CreateDate : 2017-11-19 17:10:44
         * CreateUserId : System
         * CreateUserName : 超级管理员
         * ModifyDate : null
         * ModifyUserId : null
         * ModifyUserName : null
         */

        private String MessageId;
        private String FullHead;
        private String BriefHead;
        private String FilePath;
        private int IsJpush;
        private String Content;
        private int PV;
        private String ReleaseTime;
        private int SortCode;
        private int EnabledMark;
        private String CreateDate;
        private String CreateUserId;
        private String CreateUserName;
        private String ModifyDate;
        private String ModifyUserId;
        private String ModifyUserName;

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

        public String getMessageId() {
            return MessageId;
        }

        public void setMessageId(String MessageId) {
            this.MessageId = MessageId;
        }

        public String getFullHead() {
            return FullHead;
        }

        public void setFullHead(String FullHead) {
            this.FullHead = FullHead;
        }

        public String getBriefHead() {
            return BriefHead;
        }

        public void setBriefHead(String BriefHead) {
            this.BriefHead = BriefHead;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String FilePath) {
            this.FilePath = FilePath;
        }

        public int getIsJpush() {
            return IsJpush;
        }

        public void setIsJpush(int IsJpush) {
            this.IsJpush = IsJpush;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public int getPV() {
            return PV;
        }

        public void setPV(int PV) {
            this.PV = PV;
        }

        public String getReleaseTime() {
            return ReleaseTime;
        }

        public void setReleaseTime(String ReleaseTime) {
            this.ReleaseTime = ReleaseTime;
        }

        public int getSortCode() {
            return SortCode;
        }

        public void setSortCode(int SortCode) {
            this.SortCode = SortCode;
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

        public String getCreateUserName() {
            return CreateUserName;
        }

        public void setCreateUserName(String CreateUserName) {
            this.CreateUserName = CreateUserName;
        }

        public String getModifyDate() {
            return ModifyDate;
        }

        public void setModifyDate(String ModifyDate) {
            this.ModifyDate = ModifyDate;
        }

        public String getModifyUserId() {
            return ModifyUserId;
        }

        public void setModifyUserId(String ModifyUserId) {
            this.ModifyUserId = ModifyUserId;
        }

        public String getModifyUserName() {
            return ModifyUserName;
        }

        public void setModifyUserName(String ModifyUserName) {
            this.ModifyUserName = ModifyUserName;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.MessageId);
            dest.writeString(this.FullHead);
            dest.writeString(this.BriefHead);
            dest.writeString(this.FilePath);
            dest.writeInt(this.IsJpush);
            dest.writeString(this.Content);
            dest.writeInt(this.PV);
            dest.writeString(this.ReleaseTime);
            dest.writeInt(this.SortCode);
            dest.writeInt(this.EnabledMark);
            dest.writeString(this.CreateDate);
            dest.writeString(this.CreateUserId);
            dest.writeString(this.CreateUserName);
            dest.writeString(this.ModifyDate);
            dest.writeString(this.ModifyUserId);
            dest.writeString(this.ModifyUserName);
            dest.writeInt(this.type);
        }

        protected DataBean(Parcel in) {
            this.MessageId = in.readString();
            this.FullHead = in.readString();
            this.BriefHead = in.readString();
            this.FilePath = in.readString();
            this.IsJpush = in.readInt();
            this.Content = in.readString();
            this.PV = in.readInt();
            this.ReleaseTime = in.readString();
            this.SortCode = in.readInt();
            this.EnabledMark = in.readInt();
            this.CreateDate = in.readString();
            this.CreateUserId = in.readString();
            this.CreateUserName = in.readString();
            this.ModifyDate = in.readString();
            this.ModifyUserId = in.readString();
            this.ModifyUserName = in.readString();
            this.type = in.readInt();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
