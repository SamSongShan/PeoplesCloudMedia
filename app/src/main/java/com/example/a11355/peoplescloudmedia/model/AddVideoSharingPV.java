package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by ss on 2017/12/20 0020.
 */

public class AddVideoSharingPV {

    /*
    * 【18】添加视频分享量(输入参数string Json)
VideoId:视频主键
    * */

    private String VideoId;


    public AddVideoSharingPV(String videoId) {
        VideoId = videoId;
    }

    public String getVideoId() {
        return VideoId;
    }

    public void setVideoId(String videoId) {
        VideoId = videoId;
    }
}
