package com.qutu.talk.bean;

import java.util.List;

public class Jackpot {


    /**
     * code : 1
     * message : 请求成功
     * data : {"jininfo":[{"wares_id":76,"box_type":1,"gift_name":"梦幻城堡","show_img":"http://qutu.zzmzrj.com/upload/gifts/siqi/mhcb.png","price":33440},{"wares_id":9,"box_type":1,"gift_name":"魅力头冠","show_img":"http://qutu.zzmzrj.com/upload/gifts/png/gift_mltg@2x.png","price":666}],"caiinfo":[{"wares_id":36,"box_type":2,"gift_name":"小心心","show_img":"http://qutu.zzmzrj.com/upload/gifts/sanqi/gift_xxx.png","price":10},{"wares_id":24,"box_type":2,"gift_name":"金麦克","show_img":"http://qutu.zzmzrj.com/upload/gifts/png/gift_jmk@2x.png","price":100},{"wares_id":20,"box_type":2,"gift_name":"心动","show_img":"http://qutu.zzmzrj.com//upload/gifts/png/gift_xd@2x.png","price":1},{"wares_id":41,"box_type":2,"gift_name":"跳动的心","show_img":"http://qutu.zzmzrj.com/upload/gifts/sanqi/gift_wnxd.png","price":990},{"wares_id":46,"box_type":2,"gift_name":"怦然心动","show_img":"http://qutu.zzmzrj.com/upload/gifts/sanqi/gift_prxd.png","price":5200},{"wares_id":75,"box_type":2,"gift_name":"超级火箭","show_img":"http://qutu.zzmzrj.com/upload/gifts/siqi/cjhj.png","price":9990}]}
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
             * wares_id : 76
             * box_type : 1
             * gift_name : 梦幻城堡
             * show_img : http://qutu.zzmzrj.com/upload/gifts/siqi/mhcb.png
             * price : 33440
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
