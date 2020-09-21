package com.qutu.talk.bean;

import java.util.List;

public class IncomeFragmentBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"nickname":"白羊","giftName":"来瓶香槟","giftNum":1,"giftPrice":"69.30","created_at":"2019.10.30 14:30"},{"nickname":"白羊","giftName":"梦幻城堡","giftNum":188,"giftPrice":"440070.40","created_at":"2019.10.30 14:30"},{"nickname":"白羊","giftName":"么么哒","giftNum":1,"giftPrice":"0.07","created_at":"2019.10.21 09:39"},{"nickname":"夏天","giftName":"么么哒","giftNum":1,"giftPrice":"0.07","created_at":"2019.10.21 09:38"},{"nickname":"夏天","giftName":"么么哒","giftNum":1,"giftPrice":"0.07","created_at":"2019.10.21 09:38"},{"nickname":"夏天","giftName":"么么哒","giftNum":1,"giftPrice":"0.07","created_at":"2019.10.21 09:38"},{"nickname":"夏天","giftName":"么么哒","giftNum":1,"giftPrice":"0.07","created_at":"2019.10.21 09:38"},{"nickname":"夏天","giftName":"么么哒","giftNum":1,"giftPrice":"0.07","created_at":"2019.10.21 09:38"},{"nickname":"夏天","giftName":"么么哒","giftNum":1,"giftPrice":"0.07","created_at":"2019.10.21 09:38"},{"nickname":"小手冰凉","giftName":"泡泡枪","giftNum":1,"giftPrice":"9.10","created_at":"2019.10.17 15:50"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * nickname : 白羊
         * giftName : 来瓶香槟
         * giftNum : 1
         * giftPrice : 69.30
         * created_at : 2019.10.30 14:30
         */

        private String nickname;
        private String giftName;
        private int giftNum;
        private String giftPrice;
        private String created_at;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getGiftName() {
            return giftName;
        }

        public void setGiftName(String giftName) {
            this.giftName = giftName;
        }

        public int getGiftNum() {
            return giftNum;
        }

        public void setGiftNum(int giftNum) {
            this.giftNum = giftNum;
        }

        public String getGiftPrice() {
            return giftPrice;
        }

        public void setGiftPrice(String giftPrice) {
            this.giftPrice = giftPrice;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
