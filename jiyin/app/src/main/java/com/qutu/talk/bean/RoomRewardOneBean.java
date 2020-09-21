package com.qutu.talk.bean;

import java.util.List;

public class RoomRewardOneBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"id":17473,"giftName":"怦然心动","giftNum":1,"giftPrice":"5200.00","user_id":1103,"fromUid":1102907,"created_at":"2019-10-28 18:57:43","user_name":"小丸子","from_name":"若析"},{"id":17472,"giftName":"怦然心动","giftNum":1,"giftPrice":"5200.00","user_id":1103,"fromUid":1102907,"created_at":"2019-10-28 18:54:02","user_name":"小丸子","from_name":"若析"},{"id":17465,"giftName":"补给包","giftNum":1,"giftPrice":"150.00","user_id":1103,"fromUid":1100025,"created_at":"2019-10-28 14:43:58","user_name":"小丸子","from_name":"hello"},{"id":17464,"giftName":"月神降临","giftNum":1,"giftPrice":"52000.00","user_id":1103,"fromUid":1102627,"created_at":"2019-10-28 14:43:02","user_name":"小丸子","from_name":"싱 야붕"},{"id":17463,"giftName":"小心心","giftNum":1,"giftPrice":"10.00","user_id":1103,"fromUid":1100025,"created_at":"2019-10-28 14:42:40","user_name":"小丸子","from_name":"hello"},{"id":17462,"giftName":"守护天使","giftNum":1,"giftPrice":"5200.00","user_id":1103,"fromUid":1100025,"created_at":"2019-10-28 14:42:31","user_name":"小丸子","from_name":"hello"},{"id":17461,"giftName":"守护之冠","giftNum":1,"giftPrice":"5200.00","user_id":1103,"fromUid":1100025,"created_at":"2019-10-28 14:42:22","user_name":"小丸子","from_name":"hello"},{"id":17460,"giftName":"守护之心","giftNum":1,"giftPrice":"99.00","user_id":1103,"fromUid":1100025,"created_at":"2019-10-28 14:42:11","user_name":"小丸子","from_name":"hello"},{"id":17459,"giftName":"小星星","giftNum":1,"giftPrice":"1.00","user_id":1103,"fromUid":1100025,"created_at":"2019-10-28 14:42:06","user_name":"小丸子","from_name":"hello"},{"id":17444,"giftName":"守护之心","giftNum":1,"giftPrice":"99.00","user_id":1103,"fromUid":1100025,"created_at":"2019-10-28 14:36:45","user_name":"小丸子","from_name":"hello"}]
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
         * id : 17473
         * giftName : 怦然心动
         * giftNum : 1
         * giftPrice : 5200.00
         * user_id : 1103
         * fromUid : 1102907
         * created_at : 2019-10-28 18:57:43
         * user_name : 小丸子
         * from_name : 若析
         */

        private int id;
        private String giftName;
        private int giftNum;
        private String giftPrice;
        private int user_id;
        private int fromUid;
        private String created_at;
        private String user_name;
        private String from_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getFromUid() {
            return fromUid;
        }

        public void setFromUid(int fromUid) {
            this.fromUid = fromUid;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getFrom_name() {
            return from_name;
        }

        public void setFrom_name(String from_name) {
            this.from_name = from_name;
        }
    }
}
