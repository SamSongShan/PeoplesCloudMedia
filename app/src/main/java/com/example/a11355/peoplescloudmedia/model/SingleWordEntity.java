package com.example.a11355.peoplescloudmedia.model;

/**
 * 返回字段较少的实体类
 */
public class SingleWordEntity {

    private int Code;
    private String Message;
    private DataEntity Data;
    private int error;
    private String x;
    private String y;

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

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public static class DataEntity {
        public int getShopCarTotal() {
            return ShopCarTotal;
        }

        public void setShopCarTotal(int shopCarTotal) {
            ShopCarTotal = shopCarTotal;
        }

        private int ShopCarTotal;
        private String UserId;
        private String UnReadTotal;
        private String FilePath;
        private String BankCardId;

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String userId) {
            UserId = userId;
        }

        public String getUnReadTotal() {
            return UnReadTotal;
        }

        public void setUnReadTotal(String unReadTotal) {
            UnReadTotal = unReadTotal;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String filePath) {
            FilePath = filePath;
        }

        public String getBankCardId() {
            return BankCardId;
        }

        public void setBankCardId(String bankCardId) {
            BankCardId = bankCardId;
        }
    }
}
