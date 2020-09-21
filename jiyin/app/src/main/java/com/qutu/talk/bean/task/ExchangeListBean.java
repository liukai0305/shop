package com.qutu.talk.bean.task;

import java.util.List;

public class ExchangeListBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"jinbi":790,"data":[{"id":1,"wares_id":71,"wares_type":0,"num":1,"jinbi":20,"enable":1,"addtime":1575856538,"type":102,"name":"宝箱钥匙x1","show_img":"http://tp5_test.miniyuyin.cn/upload/wares/yaoshi.png","expire":0},{"id":2,"wares_id":71,"wares_type":0,"num":10,"jinbi":200,"enable":1,"addtime":1575856538,"type":102,"name":"宝箱钥匙x10","show_img":"http://tp5_test.miniyuyin.cn/upload/wares/yaoshi.png","expire":0},{"id":3,"wares_id":72,"wares_type":0,"num":1,"jinbi":200,"enable":1,"addtime":1575856538,"type":9,"name":"3金币优惠券","show_img":"http://tp5_test.miniyuyin.cn/upload/wares/yhq_03.png","expire":7},{"id":4,"wares_id":68,"wares_type":0,"num":1,"jinbi":200,"enable":1,"addtime":1575856538,"type":4,"name":"小恶魔头像框 15天","show_img":"http://47.92.85.75/upload/emoji/txk/show_xemtxk.png","expire":15},{"id":5,"wares_id":69,"wares_type":0,"num":1,"jinbi":200,"enable":1,"addtime":1575856538,"type":5,"name":"小恶魔气泡框 15天","show_img":"http://47.92.85.75/upload/emoji/ltk/show/show_xem.png","expire":15}]}
     */

    private int code;
    private String message;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * jinbi : 790
         * data : [{"id":1,"wares_id":71,"wares_type":0,"num":1,"jinbi":20,"enable":1,"addtime":1575856538,"type":102,"name":"宝箱钥匙x1","show_img":"http://tp5_test.miniyuyin.cn/upload/wares/yaoshi.png","expire":0},{"id":2,"wares_id":71,"wares_type":0,"num":10,"jinbi":200,"enable":1,"addtime":1575856538,"type":102,"name":"宝箱钥匙x10","show_img":"http://tp5_test.miniyuyin.cn/upload/wares/yaoshi.png","expire":0},{"id":3,"wares_id":72,"wares_type":0,"num":1,"jinbi":200,"enable":1,"addtime":1575856538,"type":9,"name":"3金币优惠券","show_img":"http://tp5_test.miniyuyin.cn/upload/wares/yhq_03.png","expire":7},{"id":4,"wares_id":68,"wares_type":0,"num":1,"jinbi":200,"enable":1,"addtime":1575856538,"type":4,"name":"小恶魔头像框 15天","show_img":"http://47.92.85.75/upload/emoji/txk/show_xemtxk.png","expire":15},{"id":5,"wares_id":69,"wares_type":0,"num":1,"jinbi":200,"enable":1,"addtime":1575856538,"type":5,"name":"小恶魔气泡框 15天","show_img":"http://47.92.85.75/upload/emoji/ltk/show/show_xem.png","expire":15}]
         */

        private int jinbi;
        private List<DataBean> data;

        public int getJinbi() {
            return jinbi;
        }

        public void setJinbi(int jinbi) {
            this.jinbi = jinbi;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 1
             * wares_id : 71
             * wares_type : 0
             * num : 1
             * jinbi : 20
             * enable : 1
             * addtime : 1575856538
             * type : 102
             * name : 宝箱钥匙x1
             * show_img : http://tp5_test.miniyuyin.cn/upload/wares/yaoshi.png
             * expire : 0
             */

            private int id;
            private int wares_id;
            private int wares_type;
            private int num;
            private int jinbi;
            private int enable;
            private int addtime;
            private int type;
            private String name;
            private String show_img;
            private int expire;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getWares_id() {
                return wares_id;
            }

            public void setWares_id(int wares_id) {
                this.wares_id = wares_id;
            }

            public int getWares_type() {
                return wares_type;
            }

            public void setWares_type(int wares_type) {
                this.wares_type = wares_type;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getJinbi() {
                return jinbi;
            }

            public void setJinbi(int jinbi) {
                this.jinbi = jinbi;
            }

            public int getEnable() {
                return enable;
            }

            public void setEnable(int enable) {
                this.enable = enable;
            }

            public int getAddtime() {
                return addtime;
            }

            public void setAddtime(int addtime) {
                this.addtime = addtime;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShow_img() {
                return show_img;
            }

            public void setShow_img(String show_img) {
                this.show_img = show_img;
            }

            public int getExpire() {
                return expire;
            }

            public void setExpire(int expire) {
                this.expire = expire;
            }
        }
    }
}
