package com.example.a11355.peoplescloudmedia.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ss on 2017/12/29 0029.
 */

public class GetBusinessCardInfoEntity implements Parcelable {


    /**
     * Code : 200
     * Message : 获取成功
     * Data : {"BusinessCardInfo":"f6940273-1c5c-460f-bcec-b96a93ae5686","UserId":"212bd12a-37f2-4eb4-ae84-6dd9e552434b","HeadIcon":"/Resource/WebUserImage/TN006/20180101191510634.jpg","Title":null,"RealName":"土豆","PostName":"经理","Mobile":"13632840501","WeChat":"13636656655","QQ":"444555668666","Signature":"才不会不参加","IsUseCompany":1,"CompanyName":"龙岗区","CompanyNet":"www.baidu.com","ProvinceId":"440000","CityId":"440300","CountyId":"440307","Address":"广东省","Precision":"0.0000","Latitude":"0.0000","CreateDate":"2017-11-25 00:19:44","IsUseMusic":"1","MusicPath":"/Resource/DocumentFile/System/20180112/c353acdc-f0ff-4f60-a5c0-a436bafc9440.mp3","MusicName":null,"ProvinceName":"广东省","CityName":"深圳市","CountyName":"龙岗区"}
     * Number : 20
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

    public static class DataEntity implements Parcelable {
        /**
         * BusinessCardInfo : f6940273-1c5c-460f-bcec-b96a93ae5686
         * UserId : 212bd12a-37f2-4eb4-ae84-6dd9e552434b
         * HeadIcon : /Resource/WebUserImage/TN006/20180101191510634.jpg
         * Title : null
         * RealName : 土豆
         * PostName : 经理
         * Mobile : 13632840501
         * WeChat : 13636656655
         * QQ : 444555668666
         * Signature : 才不会不参加
         * IsUseCompany : 1
         * CompanyName : 龙岗区
         * CompanyNet : www.baidu.com
         * ProvinceId : 440000
         * CityId : 440300
         * CountyId : 440307
         * Address : 广东省
         * Precision : 0.0000
         * Latitude : 0.0000
         * CreateDate : 2017-11-25 00:19:44
         * IsUseMusic : 1
         * MusicPath : /Resource/DocumentFile/System/20180112/c353acdc-f0ff-4f60-a5c0-a436bafc9440.mp3
         * MusicName : null
         * ProvinceName : 广东省
         * CityName : 深圳市
         * CountyName : 龙岗区
         */

        private String BusinessCardInfo;
        private String UserId;
        private String HeadIcon;
        private String Title;
        private String RealName;
        private String PostName;
        private String Mobile;
        private String WeChat;
        private String QQ;
        private String Signature;
        private int IsUseCompany;
        private String CompanyName;
        private String CompanyNet;
        private String ProvinceId;
        private String CityId;
        private String CountyId;
        private String Address;
        private String Precision;
        private String Latitude;
        private String CreateDate;
        private String IsUseMusic;
        private String MusicPath;
        private String MusicName;
        private String ProvinceName;
        private String CityName;
        private String CountyName;

        public String getBusinessCardInfo() {
            return BusinessCardInfo;
        }

        public void setBusinessCardInfo(String BusinessCardInfo) {
            this.BusinessCardInfo = BusinessCardInfo;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getHeadIcon() {
            return HeadIcon;
        }

        public void setHeadIcon(String HeadIcon) {
            this.HeadIcon = HeadIcon;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getRealName() {
            return RealName;
        }

        public void setRealName(String RealName) {
            this.RealName = RealName;
        }

        public String getPostName() {
            return PostName;
        }

        public void setPostName(String PostName) {
            this.PostName = PostName;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public String getWeChat() {
            return WeChat;
        }

        public void setWeChat(String WeChat) {
            this.WeChat = WeChat;
        }

        public String getQQ() {
            return QQ;
        }

        public void setQQ(String QQ) {
            this.QQ = QQ;
        }

        public String getSignature() {
            return Signature;
        }

        public void setSignature(String Signature) {
            this.Signature = Signature;
        }

        public int getIsUseCompany() {
            return IsUseCompany;
        }

        public void setIsUseCompany(int IsUseCompany) {
            this.IsUseCompany = IsUseCompany;
        }

        public String getCompanyName() {
            return CompanyName;
        }

        public void setCompanyName(String CompanyName) {
            this.CompanyName = CompanyName;
        }

        public String getCompanyNet() {
            return CompanyNet;
        }

        public void setCompanyNet(String CompanyNet) {
            this.CompanyNet = CompanyNet;
        }

        public String getProvinceId() {
            return ProvinceId;
        }

        public void setProvinceId(String ProvinceId) {
            this.ProvinceId = ProvinceId;
        }

        public String getCityId() {
            return CityId;
        }

        public void setCityId(String CityId) {
            this.CityId = CityId;
        }

        public String getCountyId() {
            return CountyId;
        }

        public void setCountyId(String CountyId) {
            this.CountyId = CountyId;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getPrecision() {
            return Precision;
        }

        public void setPrecision(String Precision) {
            this.Precision = Precision;
        }

        public String getLatitude() {
            return Latitude;
        }

        public void setLatitude(String Latitude) {
            this.Latitude = Latitude;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
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

        public String getProvinceName() {
            return ProvinceName;
        }

        public void setProvinceName(String ProvinceName) {
            this.ProvinceName = ProvinceName;
        }

        public String getCityName() {
            return CityName;
        }

        public void setCityName(String CityName) {
            this.CityName = CityName;
        }

        public String getCountyName() {
            return CountyName;
        }

        public void setCountyName(String CountyName) {
            this.CountyName = CountyName;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.BusinessCardInfo);
            dest.writeString(this.UserId);
            dest.writeString(this.HeadIcon);
            dest.writeString(this.Title);
            dest.writeString(this.RealName);
            dest.writeString(this.PostName);
            dest.writeString(this.Mobile);
            dest.writeString(this.WeChat);
            dest.writeString(this.QQ);
            dest.writeString(this.Signature);
            dest.writeInt(this.IsUseCompany);
            dest.writeString(this.CompanyName);
            dest.writeString(this.CompanyNet);
            dest.writeString(this.ProvinceId);
            dest.writeString(this.CityId);
            dest.writeString(this.CountyId);
            dest.writeString(this.Address);
            dest.writeString(this.Precision);
            dest.writeString(this.Latitude);
            dest.writeString(this.CreateDate);
            dest.writeString(this.IsUseMusic);
            dest.writeString(this.MusicPath);
            dest.writeString(this.MusicName);
            dest.writeString(this.ProvinceName);
            dest.writeString(this.CityName);
            dest.writeString(this.CountyName);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.BusinessCardInfo = in.readString();
            this.UserId = in.readString();
            this.HeadIcon = in.readString();
            this.Title = in.readString();
            this.RealName = in.readString();
            this.PostName = in.readString();
            this.Mobile = in.readString();
            this.WeChat = in.readString();
            this.QQ = in.readString();
            this.Signature = in.readString();
            this.IsUseCompany = in.readInt();
            this.CompanyName = in.readString();
            this.CompanyNet = in.readString();
            this.ProvinceId = in.readString();
            this.CityId = in.readString();
            this.CountyId = in.readString();
            this.Address = in.readString();
            this.Precision = in.readString();
            this.Latitude = in.readString();
            this.CreateDate = in.readString();
            this.IsUseMusic = in.readString();
            this.MusicPath = in.readString();
            this.MusicName = in.readString();
            this.ProvinceName = in.readString();
            this.CityName = in.readString();
            this.CountyName = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Code);
        dest.writeString(this.Message);
        dest.writeParcelable(this.Data, flags);
        dest.writeInt(this.Number);
    }

    public GetBusinessCardInfoEntity() {
    }

    protected GetBusinessCardInfoEntity(Parcel in) {
        this.Code = in.readInt();
        this.Message = in.readString();
        this.Data = in.readParcelable(DataEntity.class.getClassLoader());
        this.Number = in.readInt();
    }

    public static final Parcelable.Creator<GetBusinessCardInfoEntity> CREATOR = new Parcelable.Creator<GetBusinessCardInfoEntity>() {
        @Override
        public GetBusinessCardInfoEntity createFromParcel(Parcel source) {
            return new GetBusinessCardInfoEntity(source);
        }

        @Override
        public GetBusinessCardInfoEntity[] newArray(int size) {
            return new GetBusinessCardInfoEntity[size];
        }
    };
}
