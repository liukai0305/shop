package com.qutu.talk.bean;

import java.util.List;

public class GxJackpot {


    /**
     * code : 1
     * message : 请求成功
     * data : {"jininfo":[{"wares_id":11,"box_type":1,"gift_name":"打针","show_img":"http://qutu.zzmzrj.com/upload/gifts/qiqi/9b7a5b9b7fd0983a273f85a7c56b6b5b.png","price":52},{"wares_id":12,"box_type":1,"gift_name":"天使之心","show_img":"http://qutu.zzmzrj.com/upload/gifts/qiqi/fddc99afe9712d90ecd70daab2d012a6.png","price":100}],"caiinfo":[{"wares_id":10,"box_type":2,"gift_name":"寻你","show_img":"http://qutu.zzmzrj.com/upload/gifts/qiqi/0836d68aa98b165e735eea2cfb6da045.png","price":10},{"wares_id":7,"box_type":2,"gift_name":"鲸","show_img":"http://qutu.zzmzrj.com/upload/gifts/qiqi/d593b2e7d93fe8de79920435369ed392.png","price":33440},{"wares_id":6,"box_type":2,"gift_name":"平安夜","show_img":"http://qutu.zzmzrj.com/upload/gifts/qiqi/5c132f030c092b5eaa97f7539697c0c1.png","price":10000}]}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<Info> jininfo;
        private List<Info> caiinfo;

        public List<Info> getJininfo() {
            return jininfo;
        }

        public void setJininfo(List<Info> jininfo) {
            this.jininfo = jininfo;
        }

        public List<Info> getCaiinfo() {
            return caiinfo;
        }

        public void setCaiinfo(List<Info> caiinfo) {
            this.caiinfo = caiinfo;
        }

        public static class Info {
            /**
             * wares_id : 11
             * box_type : 1
             * gift_name : 打针
             * show_img : http://qutu.zzmzrj.com/upload/gifts/qiqi/9b7a5b9b7fd0983a273f85a7c56b6b5b.png
             * price : 52
             */

            private int wares_id;
            private int box_type;
            private String gift_name;
            private String show_img;
            private int price;

            public int getWares_id() {
                return wares_id;
            }

            public void setWares_id(int wares_id) {
                this.wares_id = wares_id;
            }

            public int getBox_type() {
                return box_type;
            }

            public void setBox_type(int box_type) {
                this.box_type = box_type;
            }

            public String getGift_name() {
                return gift_name;
            }

            public void setGift_name(String gift_name) {
                this.gift_name = gift_name;
            }

            public String getShow_img() {
                return show_img;
            }

            public void setShow_img(String show_img) {
                this.show_img = show_img;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }
        }
    }
}
