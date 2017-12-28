package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by 11355 on 2017/12/28.
 */

public class UpdateCardCompanyInfo {

    /*
    *     UserId:用户编号
Token:登录的判断标识
IsUseCompany:是否使用公司信息(0-不使用，1-使用)
CompanyName:公司名称
CompanyNet:公司网址
NativePlace:所在地址(ProvinceId|CityId|CountyId)
Address:详细地址（地址|经度|纬度）
    * */

    private  String UserId;
    private  String Token;
    private  String IsUseCompany;
    private  String CompanyName;
    private  String CompanyNet;
    private  String NativePlace;
    private  String Address;

    public UpdateCardCompanyInfo(String userId, String token, String isUseCompany, String companyName, String companyNet, String nativePlace, String address) {
        UserId = userId;
        Token = token;
        IsUseCompany = isUseCompany;
        CompanyName = companyName;
        CompanyNet = companyNet;
        NativePlace = nativePlace;
        Address = address;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getIsUseCompany() {
        return IsUseCompany;
    }

    public void setIsUseCompany(String isUseCompany) {
        IsUseCompany = isUseCompany;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCompanyNet() {
        return CompanyNet;
    }

    public void setCompanyNet(String companyNet) {
        CompanyNet = companyNet;
    }

    public String getNativePlace() {
        return NativePlace;
    }

    public void setNativePlace(String nativePlace) {
        NativePlace = nativePlace;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
