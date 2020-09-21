package com.qutu.talk.bean;

import java.util.List;

public class MeltingPack {


    /**
     * code : 1
     * message : 获取成功
     * data : [{"id":18833,"num":1,"target_id":11,"get_type":3,"name":"打针","price":52,"img":"http://qutu.zzmzrj.com/upload/gifts/qiqi/9b7a5b9b7fd0983a273f85a7c56b6b5b.png","show_img":"http://qutu.zzmzrj.com/upload/gifts/qiqi/9b7a5b9b7fd0983a273f85a7c56b6b5b.png","show_img2":"","type":1,"wares_type":2},{"id":18834,"num":1,"target_id":6,"get_type":3,"name":"平安夜","price":10000,"img":"http://qutu.zzmzrj.com/upload/gifts/qiqi/5c132f030c092b5eaa97f7539697c0c1.png","show_img":"http://qutu.zzmzrj.com/upload/gifts/qiqi/5c132f030c092b5eaa97f7539697c0c1.png","show_img2":"/upload/gifts/qiqi/47599816572540793309ffca4132bff3.svga","type":2,"wares_type":2}]
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
         * id : 18833
         * num : 1
         * target_id : 11
         * get_type : 3
         * name : 打针
         * price : 52
         * img : http://qutu.zzmzrj.com/upload/gifts/qiqi/9b7a5b9b7fd0983a273f85a7c56b6b5b.png
         * show_img : http://qutu.zzmzrj.com/upload/gifts/qiqi/9b7a5b9b7fd0983a273f85a7c56b6b5b.png
         * show_img2 :
         * type : 1
         * wares_type : 2
         */

        private int id;
        private int num;
        private int target_id;
        private int get_type;
        private String name;
        private int price;
        private String img;
        private String show_img;
        private String show_img2;
        private int type;
        private int wares_type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getTarget_id() {
            return target_id;
        }

        public void setTarget_id(int target_id) {
            this.target_id = target_id;
        }

        public int getGet_type() {
            return get_type;
        }

        public void setGet_type(int get_type) {
            this.get_type = get_type;
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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getShow_img() {
            return show_img;
        }

        public void setShow_img(String show_img) {
            this.show_img = show_img;
        }

        public String getShow_img2() {
            return show_img2;
        }

        public void setShow_img2(String show_img2) {
            this.show_img2 = show_img2;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getWares_type() {
            return wares_type;
        }

        public void setWares_type(int wares_type) {
            this.wares_type = wares_type;
        }
    }
}
