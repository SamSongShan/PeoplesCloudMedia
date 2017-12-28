package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by 11355 on 2017/12/28.
 */

public class UpdateBusinessCardEntity {

         /*
         * 【21】用户名片基本信息修改(输入参数string Json)
UserId:用户编号
Token:登录的判断标识
HeadIcon:头像
RealName:姓名
PostName:职位
Mobile:电话
WeChat:微信
QQ:QQ
Signature:个性签名
         * */

    private String UserId;
    private String Token;
    private String HeadIcon;
    private String RealName;
    private String PostName;
    private String Mobile;
    private String WeChat;
    private String QQ;
    private String Signature;

    public UpdateBusinessCardEntity(String userId, String token, String headIcon, String realName, String postName, String mobile, String weChat, String QQ, String signature) {
        UserId = userId;
        Token = token;
        HeadIcon = headIcon;
        RealName = realName;
        PostName = postName;
        Mobile = mobile;
        WeChat = weChat;
        this.QQ = QQ;
        Signature = signature;
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

    public String getHeadIcon() {
        return HeadIcon;
    }

    public void setHeadIcon(String headIcon) {
        HeadIcon = headIcon;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getPostName() {
        return PostName;
    }

    public void setPostName(String postName) {
        PostName = postName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getWeChat() {
        return WeChat;
    }

    public void setWeChat(String weChat) {
        WeChat = weChat;
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

    public void setSignature(String signature) {
        Signature = signature;
    }
}
