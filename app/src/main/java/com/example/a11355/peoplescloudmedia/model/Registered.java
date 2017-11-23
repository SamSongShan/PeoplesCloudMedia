package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by 11355 on 2017/11/24.
 */

public class Registered {

            /*
            * 【1】用户注册(输入参数string Json)
RegisterMark:注册方式(Mobile、WeChat)
RegisterType:注册类型(Web、Android、Ios、Pc)
Account：登录账户(说明：根据注册方式取对应值)
Password:登录密码(说明：快捷注册为default)
OpenId:第三方快捷返回值
FullName:企业全称(个人传default)
ContactName:联系人姓名(个人传default)
RecommendMobile:推荐人手机号(默认传default)
RoleType:用户类型(0-个人，1-企业)
            * */
            private String RegisterMark;
    private String RegisterType;
    private String Account;
    private String Password;
    private String OpenId;
    private String FullName;
    private String ContactName;
    private String RecommendMobile;
    private String RoleType;

    public Registered(String registerMark, String registerType, String account, String password, String openId, String fullName, String contactName, String recommendMobile, String roleType) {
        RegisterMark = registerMark;
        RegisterType = registerType;
        Account = account;
        Password = password;
        OpenId = openId;
        FullName = fullName;
        ContactName = contactName;
        RecommendMobile = recommendMobile;
        RoleType = roleType;
    }

    public String getRegisterMark() {
        return RegisterMark;
    }

    public void setRegisterMark(String registerMark) {
        RegisterMark = registerMark;
    }

    public String getRegisterType() {
        return RegisterType;
    }

    public void setRegisterType(String registerType) {
        RegisterType = registerType;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getOpenId() {
        return OpenId;
    }

    public void setOpenId(String openId) {
        OpenId = openId;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public String getRecommendMobile() {
        return RecommendMobile;
    }

    public void setRecommendMobile(String recommendMobile) {
        RecommendMobile = recommendMobile;
    }

    public String getRoleType() {
        return RoleType;
    }

    public void setRoleType(String roleType) {
        RoleType = roleType;
    }
}
