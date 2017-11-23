package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by 11355 on 2017/11/23.
 */

public class GetMobileCode {

    /*
    * Mobile:手机号码
Title:发送类别(用户注册、密码找回、微信快捷登录绑定手机)
    * */

    private String Mobile;
    private String Title;

    public GetMobileCode(String mobile, String title) {
        Mobile = mobile;
        Title = title;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
