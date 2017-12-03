package com.example.a11355.peoplescloudmedia.model;

import java.util.List;

/**
 * Created by 11355 on 2017/12/3.
 */

public class GetNewsTypeListEntity {


    /**
     * Code : 200
     * Message : 获取成功
     * Data : [{"id":9,"pid":0,"name":"旅游","priority":103},{"id":10,"pid":0,"name":"问答","priority":1},{"id":11,"pid":0,"name":"娱乐","priority":92},{"id":12,"pid":0,"name":"社会","priority":1},{"id":13,"pid":0,"name":"视频","priority":92},{"id":14,"pid":0,"name":"热点","priority":108},{"id":15,"pid":0,"name":"推荐","priority":107},{"id":16,"pid":0,"name":"新技术","priority":101},{"id":17,"pid":0,"name":"汽车","priority":97},{"id":18,"pid":0,"name":"体育","priority":98},{"id":19,"pid":0,"name":"财经","priority":98},{"id":20,"pid":0,"name":"国际","priority":90},{"id":21,"pid":0,"name":"军事","priority":90},{"id":23,"pid":0,"name":"美容","priority":92},{"id":24,"pid":0,"name":"健康","priority":102},{"id":25,"pid":0,"name":"正能量","priority":100},{"id":26,"pid":0,"name":"特卖","priority":90},{"id":27,"pid":0,"name":"房产","priority":97},{"id":28,"pid":0,"name":"时尚","priority":92},{"id":30,"pid":0,"name":"育儿","priority":97},{"id":33,"pid":0,"name":"美食","priority":97},{"id":34,"pid":0,"name":"养生","priority":97},{"id":35,"pid":0,"name":"电影","priority":92},{"id":36,"pid":0,"name":"手机","priority":97},{"id":37,"pid":0,"name":"宠物","priority":92},{"id":39,"pid":0,"name":"家居","priority":97},{"id":40,"pid":0,"name":"教育","priority":104},{"id":41,"pid":0,"name":"三农","priority":98},{"id":42,"pid":0,"name":"孕产","priority":97},{"id":43,"pid":0,"name":"文化","priority":99},{"id":44,"pid":0,"name":"游戏","priority":92},{"id":45,"pid":0,"name":"科学","priority":99},{"id":46,"pid":0,"name":"动漫","priority":92},{"id":47,"pid":0,"name":"收藏","priority":92},{"id":48,"pid":0,"name":"精选","priority":106},{"id":51,"pid":0,"name":"辟谣","priority":1},{"id":52,"pid":0,"name":"新金融","priority":101},{"id":53,"pid":0,"name":"公益","priority":89},{"id":54,"pid":0,"name":"调查","priority":1},{"id":58,"pid":0,"name":"新能源","priority":101},{"id":59,"pid":0,"name":"新制造","priority":101},{"id":60,"pid":0,"name":"新零售","priority":101},{"id":61,"pid":28,"name":"时装","priority":0},{"id":62,"pid":28,"name":"美体","priority":0},{"id":63,"pid":28,"name":"腕表","priority":0},{"id":64,"pid":28,"name":"珠宝","priority":0},{"id":65,"pid":19,"name":"投资","priority":0},{"id":66,"pid":19,"name":"股票","priority":0},{"id":67,"pid":19,"name":"理财","priority":0},{"id":68,"pid":19,"name":"宏观经济","priority":0},{"id":69,"pid":17,"name":"新车","priority":0},{"id":70,"pid":17,"name":"SUV","priority":0},{"id":71,"pid":17,"name":"汽车导购","priority":0},{"id":72,"pid":17,"name":"用车","priority":0},{"id":73,"pid":18,"name":"NBA","priority":0},{"id":74,"pid":18,"name":" CBA","priority":0},{"id":75,"pid":18,"name":" 中超","priority":0},{"id":76,"pid":18,"name":"意甲","priority":0},{"id":77,"pid":0,"name":"科技","priority":100},{"id":78,"pid":77,"name":"互联网","priority":5},{"id":79,"pid":77,"name":"软件","priority":0},{"id":80,"pid":77,"name":"智能家居","priority":4},{"id":81,"pid":11,"name":"电影","priority":0},{"id":82,"pid":11,"name":" 电视剧","priority":0},{"id":83,"pid":11,"name":" 综艺","priority":0},{"id":84,"pid":11,"name":" 明星八卦","priority":0},{"id":85,"pid":12,"name":"环境保护","priority":0},{"id":86,"pid":12,"name":" 食品安全","priority":0},{"id":87,"pid":12,"name":"反腐倡廉","priority":0},{"id":88,"pid":21,"name":"中国军情","priority":0},{"id":89,"pid":21,"name":" 武器装备","priority":0},{"id":90,"pid":21,"name":"环球军事","priority":0},{"id":91,"pid":16,"name":"大数据","priority":0},{"id":92,"pid":16,"name":"人工智能","priority":0},{"id":93,"pid":16,"name":"商务智能","priority":0},{"id":94,"pid":77,"name":"营销","priority":0},{"id":95,"pid":0,"name":"新模式","priority":101},{"id":96,"pid":0,"name":" 更多","priority":0},{"id":97,"pid":0,"name":"艺术","priority":99},{"id":98,"pid":96,"name":"搞笑","priority":0},{"id":99,"pid":96,"name":"段子","priority":0},{"id":100,"pid":96,"name":"星座","priority":0},{"id":101,"pid":96,"name":"语录","priority":0},{"id":102,"pid":96,"name":"情感","priority":0},{"id":103,"pid":96,"name":"历史","priority":0},{"id":104,"pid":0,"name":"赛事","priority":88},{"id":105,"pid":0,"name":"新趋势","priority":101},{"id":106,"pid":0,"name":"最新","priority":109},{"id":107,"pid":0,"name":"影像","priority":101},{"id":108,"pid":0,"name":"新媒体","priority":1},{"id":109,"pid":23,"name":"减肥","priority":0},{"id":110,"pid":0,"name":"引流","priority":0},{"id":111,"pid":40,"name":"阅读","priority":0}]
     * Number : 0
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
         * id : 9
         * pid : 0
         * name : 旅游
         * priority : 103
         */

        private int id;
        private int pid;
        private String name;
        private int priority;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }
    }
}
