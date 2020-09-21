package com.qutu.talk.bean;

import java.util.List;

public class PersonalityBean1 {


    /**
     * code : 1
     * message : 请求成功
     * data : {"tsinfo":[],"zqinfo":{"id":6,"name":"跑车","price":"50.00","image":"http://jiyinapp.cn/upload/uploads/20200711/95c3466b62f08ef4aef427f8a4a5ff98.png","images":"http://jiyinapp.cn/upload/emoji/2b9126b653b8b38b00bcdaa29bdfcdd3.svga","num":0}}
     */

    public int code;
    public String message;
    public DataBean data;

    public static class DataBean {
        /**
         * tsinfo : []
         * zqinfo : {"id":6,"name":"跑车","price":"50.00","image":"http://jiyinapp.cn/upload/uploads/20200711/95c3466b62f08ef4aef427f8a4a5ff98.png","images":"http://jiyinapp.cn/upload/emoji/2b9126b653b8b38b00bcdaa29bdfcdd3.svga","num":0}
         */

        public ZqinfoBean zqinfo;
//        public List<?> tsinfo;

        public static class ZqinfoBean {
            /**
             * id : 6
             * name : 跑车
             * price : 50.00
             * image : http://jiyinapp.cn/upload/uploads/20200711/95c3466b62f08ef4aef427f8a4a5ff98.png
             * images : http://jiyinapp.cn/upload/emoji/2b9126b653b8b38b00bcdaa29bdfcdd3.svga
             * num : 0
             */

            public int id;
            public String name;
            public String price;
            public String image;
            public String images;
            public int num;
        }
    }
}
