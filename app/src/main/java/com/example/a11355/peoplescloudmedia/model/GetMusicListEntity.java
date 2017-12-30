package com.example.a11355.peoplescloudmedia.model;

import java.util.List;

/**
 * Created by 11355 on 2017/12/26.
 */

public class GetMusicListEntity {


    /**
     * Code : 200
     * Message : 获取成功
     * Data : [{"name":"超级飞侠","artists":"群星","album":"热门华语241","picUrl":"http://p1.music.126.net/kyLChMWjs_fDjMnnk2RJhw==/3428277256617884.jpg","audio":"http://m2.music.126.net/hmZoNQaqzZALvVp0rE7faA==/0.mp3"},{"name":"西安西安","artists":"群星","album":"我为西安唱首歌","picUrl":"http://p1.music.126.net/A8HgOIfDxp17rxKp2A0w_Q==/109951163093824637.jpg","audio":"http://m2.music.126.net/hmZoNQaqzZALvVp0rE7faA==/0.mp3"},{"name":"追光者","artists":"岑宁儿","album":"夏至未至 影视原声带","picUrl":"http://p1.music.126.net/ZZAQGWl9mR7g5xCyWWH3Pw==/19149094509535913.jpg","audio":"http://m2.music.126.net/hmZoNQaqzZALvVp0rE7faA==/0.mp3"},{"name":"全民颈椎舞之摇起来","artists":"群星","album":"全民脊椎舞","picUrl":"http://p1.music.126.net/b5VaVkYfB2p2W9DRHq42YQ==/109951163095208877.jpg","audio":"http://m2.music.126.net/hmZoNQaqzZALvVp0rE7faA==/0.mp3"},{"name":"为梦鼓掌","artists":"群星","album":"为梦鼓掌","picUrl":"http://p1.music.126.net/AWwgpg6n8OImcl0UmWuqyw==/109951163095136669.jpg","audio":"http://m2.music.126.net/hmZoNQaqzZALvVp0rE7faA==/0.mp3"},{"name":"我所有的思念","artists":"大壮","album":"MOMO音乐计划","picUrl":"http://p1.music.126.net/_OUFA3dr6FkiswwVMTlHDA==/18852226369889909.jpg","audio":"http://m2.music.126.net/hmZoNQaqzZALvVp0rE7faA==/0.mp3"},{"name":"醉赤壁","artists":"林俊杰","album":"JJ陆","picUrl":"http://p1.music.126.net/s6zFxvXe5kOxub4_x4rZhQ==/109951163052847567.jpg","audio":"http://m2.music.126.net/qNDziQZOoUM-PjGGjILrJQ==/7973658326183112.mp3"},{"name":"我多喜欢你，你会知道","artists":"王俊琪","album":"致我们单纯的小美好 电视原声带","picUrl":"http://p1.music.126.net/Z--3NoFqowsVF3L4a7yE_A==/109951163063300063.jpg","audio":"http://m2.music.126.net/hmZoNQaqzZALvVp0rE7faA==/0.mp3"},{"name":"Real Friends (Leowi & NGO Remix)","artists":"Camila Cabello","album":"Real Friends (Leowi & NGO Remix)","picUrl":"http://p1.music.126.net/tcGjbWU7l00NgT0SDVPi0Q==/109951163094320928.jpg","audio":"http://m2.music.126.net/hmZoNQaqzZALvVp0rE7faA==/0.mp3"},{"name":"男孩","artists":"高誉容","album":"我想和你唱 第二季 第7期","picUrl":"http://p1.music.126.net/QFXbRb-OOk-ThxtDyg8fjQ==/18618030395080088.jpg","audio":"http://m2.music.126.net/hmZoNQaqzZALvVp0rE7faA==/0.mp3"},{"name":"In The Garden (Reunion Album Version)","artists":"Various Artists","album":"Reunion","picUrl":"http://p1.music.126.net/D7dY6hTnIX-w9oB1hBxcZg==/901599534798888.jpg","audio":"http://m2.music.126.net/xraevg655DPoeyGqphI4kw==/1221557418464405.mp3"},{"name":"Hold To God's Unchanging Hand (Reunion Album Version)","artists":"Various Artists","album":"Reunion","picUrl":"http://p1.music.126.net/D7dY6hTnIX-w9oB1hBxcZg==/901599534798888.jpg","audio":"http://m2.music.126.net/kPi9kiAXD5N7KBWWqX0Syw==/1161084278936940.mp3"},{"name":"凉凉","artists":"唐倩","album":"我想和你唱 第二季 第4期","picUrl":"http://p1.music.126.net/9FhJLS1BrJdXAUJ2qjyJSg==/19212866183896782.jpg","audio":"http://m2.music.126.net/3bfpqfEfQ-mwLtOS9o5bag==/18898405858352638.mp3"},{"name":"So Far Away (Brandon Langella Remix)","artists":"David Guetta","album":"So Far Away (Brandon Langella Remix)","picUrl":"http://p1.music.126.net/CmYy6miVyOSx7GKC8BSZEw==/109951163093890237.jpg","audio":"http://m2.music.126.net/hmZoNQaqzZALvVp0rE7faA==/0.mp3"}]
     * Number : 26
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

    public static class DataBean {
        /**
         * name : 超级飞侠
         * artists : 群星
         * album : 热门华语241
         * picUrl : http://p1.music.126.net/kyLChMWjs_fDjMnnk2RJhw==/3428277256617884.jpg
         * audio : http://m2.music.126.net/hmZoNQaqzZALvVp0rE7faA==/0.mp3
         */

        private String name;
        private String artists;
        private String album;
        private String picUrl;
        private String audio;

        private int Isplay;

        public int getIsplay() {
            return Isplay;
        }

        public void setIsplay(int isplay) {
            Isplay = isplay;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getArtists() {
            return artists;
        }

        public void setArtists(String artists) {
            this.artists = artists;
        }

        public String getAlbum() {
            return album;
        }

        public void setAlbum(String album) {
            this.album = album;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getAudio() {
            return audio;
        }

        public void setAudio(String audio) {
            this.audio = audio;
        }
    }
}
