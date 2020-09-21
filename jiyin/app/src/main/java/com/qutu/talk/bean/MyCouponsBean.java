package com.qutu.talk.bean;

import java.util.List;

public class MyCouponsBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"id":9,"wares_id":72,"expire":"2019.12.17","addtime":"2019.12.10","name":"3金币优惠券","price":3,"get_type":"钻石兑换"},{"id":10,"wares_id":72,"expire":"2019.12.17","addtime":"2019.12.10","name":"3金币优惠券","price":3,"get_type":"钻石兑换"}]
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
         * id : 9
         * wares_id : 72
         * expire : 2019.12.17
         * addtime : 2019.12.10
         * name : 3金币优惠券
         * price : 3
         * get_type : 钻石兑换
         */

        private String id;
        private String wares_id;
        private String expire;
        private String addtime;
        private String name;
        private int price;
        private String get_type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWares_id() {
            return wares_id;
        }

        public void setWares_id(String wares_id) {
            this.wares_id = wares_id;
        }

        public String getExpire() {
            return expire;
        }

        public void setExpire(String expire) {
            this.expire = expire;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getGet_type() {
            return get_type;
        }

        public void setGet_type(String get_type) {
            this.get_type = get_type;
        }
    }
}
