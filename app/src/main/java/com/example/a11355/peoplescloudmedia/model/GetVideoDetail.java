package com.example.a11355.peoplescloudmedia.model;

/**
 * Created by 11355 on 2017/12/19.
 */

public class GetVideoDetail {

    /*
    * VideoId:视频主键
UserId:用户编号(默认传default)
    *
    * */

    private String VideoId;
    private String UserId;

    public GetVideoDetail(String videoId, String userId) {
        VideoId = videoId;
        UserId = userId;
    }

    public String getVideoId() {
        return VideoId;
    }

    public void setVideoId(String videoId) {
        VideoId = videoId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
