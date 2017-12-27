package com.example.a11355.peoplescloudmedia.model;

import java.util.List;

/**
 * Created by ss on 2017/12/27 0027.
 */

public class UpdateGraphicEditor {

    private String Token;
    private String UserId;

    private String GraphicEditorId;
    private String ImageUrl;
    private String IsUseMusic;
    private String MusicUrl;
    private String MusicName;
    private String Title;
    private List<EContent> MediaBlockList;

    public UpdateGraphicEditor(String token, String userId, String graphicEditorId, String imageUrl, String isUseMusic, String musicUrl, String musicName, String title, List<EContent> mediaBlockList) {
        Token = token;
        UserId = userId;
        GraphicEditorId = graphicEditorId;
        ImageUrl = imageUrl;
        IsUseMusic = isUseMusic;
        MusicUrl = musicUrl;
        MusicName = musicName;
        Title = title;
        MediaBlockList = mediaBlockList;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getGraphicEditorId() {
        return GraphicEditorId;
    }

    public void setGraphicEditorId(String graphicEditorId) {
        GraphicEditorId = graphicEditorId;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getIsUseMusic() {
        return IsUseMusic;
    }

    public void setIsUseMusic(String isUseMusic) {
        IsUseMusic = isUseMusic;
    }

    public String getMusicUrl() {
        return MusicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        MusicUrl = musicUrl;
    }

    public String getMusicName() {
        return MusicName;
    }

    public void setMusicName(String musicName) {
        MusicName = musicName;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public List<EContent> getMediaBlockList() {
        return MediaBlockList;
    }

    public void setMediaBlockList(List<EContent> mediaBlockList) {
        MediaBlockList = mediaBlockList;
    }
}
