package com.example.a11355.peoplescloudmedia.model;

import java.util.List;

/**
 * Created by ss on 2017/12/20 0020.
 */

public class GetUserEvaluationeListEntity {


    /**
     * Code : 200
     * Message : 获取成功
     * Data : {"EvaluationList":[{"EvaluationId":"91a4e306-787e-45f5-9415-5d9c2ea448c6","ObjectValue":"33dafa3d-ae61-49bf-8824-3df8cb89f57d","Content":"我的","HeadIcon":"/Resource/WebUserImage/BC008/20171219011158070.jpg","NickName":"我的昵称","CreateDate":"2017-12-20 14:05:40"},{"EvaluationId":"009b1371-c2e3-461f-afde-362fab8160e8","ObjectValue":"33dafa3d-ae61-49bf-8824-3df8cb89f57d","Content":"在线教育系统劳动模范和先进工作者？","HeadIcon":"/Resource/WebUserImage/BC008/20171219011158070.jpg","NickName":"我的昵称","CreateDate":"2017-12-17 00:53:18"},{"EvaluationId":"2e242440-8023-482f-8030-5ff788777f42","ObjectValue":"33dafa3d-ae61-49bf-8824-3df8cb89f57d","Content":"你們的愛","HeadIcon":"/Resource/WebUserImage/BC008/20171219011158070.jpg","NickName":"我的昵称","CreateDate":"2017-12-16 11:38:55"},{"EvaluationId":"d3c99cec-cadb-4c02-a872-f4b3e0937a31","ObjectValue":"33dafa3d-ae61-49bf-8824-3df8cb89f57d","Content":"Type of music game and I cannot is a good day game ","HeadIcon":"/Resource/WebUserImage/QE001/20171204191705106.jpg","NickName":"Lou789","CreateDate":"2017-12-09 15:30:29"}],"Total":4}
     * Number : 14
     */

    private int Code;
    private String Message;
    private DataEntity Data;
    private int Number;

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

    public DataEntity getData() {
        return Data;
    }

    public void setData(DataEntity Data) {
        this.Data = Data;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int Number) {
        this.Number = Number;
    }

    public static class DataEntity {
        /**
         * EvaluationList : [{"EvaluationId":"91a4e306-787e-45f5-9415-5d9c2ea448c6","ObjectValue":"33dafa3d-ae61-49bf-8824-3df8cb89f57d","Content":"我的","HeadIcon":"/Resource/WebUserImage/BC008/20171219011158070.jpg","NickName":"我的昵称","CreateDate":"2017-12-20 14:05:40"},{"EvaluationId":"009b1371-c2e3-461f-afde-362fab8160e8","ObjectValue":"33dafa3d-ae61-49bf-8824-3df8cb89f57d","Content":"在线教育系统劳动模范和先进工作者？","HeadIcon":"/Resource/WebUserImage/BC008/20171219011158070.jpg","NickName":"我的昵称","CreateDate":"2017-12-17 00:53:18"},{"EvaluationId":"2e242440-8023-482f-8030-5ff788777f42","ObjectValue":"33dafa3d-ae61-49bf-8824-3df8cb89f57d","Content":"你們的愛","HeadIcon":"/Resource/WebUserImage/BC008/20171219011158070.jpg","NickName":"我的昵称","CreateDate":"2017-12-16 11:38:55"},{"EvaluationId":"d3c99cec-cadb-4c02-a872-f4b3e0937a31","ObjectValue":"33dafa3d-ae61-49bf-8824-3df8cb89f57d","Content":"Type of music game and I cannot is a good day game ","HeadIcon":"/Resource/WebUserImage/QE001/20171204191705106.jpg","NickName":"Lou789","CreateDate":"2017-12-09 15:30:29"}]
         * Total : 4
         */

        private int Total;
        private List<EvaluationListEntity> EvaluationList;

        public int getTotal() {
            return Total;
        }

        public void setTotal(int Total) {
            this.Total = Total;
        }

        public List<EvaluationListEntity> getEvaluationList() {
            return EvaluationList;
        }

        public void setEvaluationList(List<EvaluationListEntity> EvaluationList) {
            this.EvaluationList = EvaluationList;
        }

        public static class EvaluationListEntity {
            /**
             * EvaluationId : 91a4e306-787e-45f5-9415-5d9c2ea448c6
             * ObjectValue : 33dafa3d-ae61-49bf-8824-3df8cb89f57d
             * Content : 我的
             * HeadIcon : /Resource/WebUserImage/BC008/20171219011158070.jpg
             * NickName : 我的昵称
             * CreateDate : 2017-12-20 14:05:40
             */

            private String EvaluationId;
            private String ObjectValue;
            private String Content;
            private String HeadIcon;
            private String NickName;
            private String CreateDate;

            private  int type;

            public EvaluationListEntity(int type) {
                this.type = type;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getEvaluationId() {
                return EvaluationId;
            }

            public void setEvaluationId(String EvaluationId) {
                this.EvaluationId = EvaluationId;
            }

            public String getObjectValue() {
                return ObjectValue;
            }

            public void setObjectValue(String ObjectValue) {
                this.ObjectValue = ObjectValue;
            }

            public String getContent() {
                return Content;
            }

            public void setContent(String Content) {
                this.Content = Content;
            }

            public String getHeadIcon() {
                return HeadIcon;
            }

            public void setHeadIcon(String HeadIcon) {
                this.HeadIcon = HeadIcon;
            }

            public String getNickName() {
                return NickName;
            }

            public void setNickName(String NickName) {
                this.NickName = NickName;
            }

            public String getCreateDate() {
                return CreateDate;
            }

            public void setCreateDate(String CreateDate) {
                this.CreateDate = CreateDate;
            }
        }
    }
}
