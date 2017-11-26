package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by 11355 on 2017/11/26.
 */

public class GetAppVersion {

    /*
    * 【8】获取APP版本(输入参数string Json)
Action:客户端（安卓、苹果）
    *
    * */

    private String  Action;

    public GetAppVersion(String action) {
        Action = action;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }
}
