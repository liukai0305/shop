package com.qutu.talk.bean;

import java.util.List;

public class PersonalityBean {


    /**
     * code : 1
     * message : 请求成功
     * data : {"ts":[{"id":1,"price":"99.00","name":"闪电","image":"http://qutu.zzmzrj.com/upload/uploads/20200301/0d73c50724f6c80f35d59b56338ac65c.jpg","isgm":0},{"id":2,"price":"9999.00","name":"男爵骑士","image":"http://qutu.zzmzrj.com/upload/uploads/20200301/1278762dec62465daab17555b5cf8e78.jpg","isgm":0}],"zq":[{"id":1,"name":"南瓜跑车","price":"999.00","image":"http://qutu.zzmzrj.com/upload/uploads/20200301/7a7569b3361aaf6a77621baa492fb9c3.jpg","images":"http://qutu.zzmzrj.com/upload/uploads/20200301/7a7569b3361aaf6a77621baa492fb9c3.jpg","isgm":0},{"id":2,"name":"神龙瘦","price":"987.00","image":"http://qutu.zzmzrj.com/upload/uploads/20200301/38d34c6f94e7715649a67f1db81a1671.jpg","images":"http://qutu.zzmzrj.com/upload/uploads/20200301/38d34c6f94e7715649a67f1db81a1671.jpg","isgm":0}],"headimgurl":""}
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
        /**
         * ts : [{"id":1,"price":"99.00","name":"闪电","image":"http://qutu.zzmzrj.com/upload/uploads/20200301/0d73c50724f6c80f35d59b56338ac65c.jpg","isgm":0},{"id":2,"price":"9999.00","name":"男爵骑士","image":"http://qutu.zzmzrj.com/upload/uploads/20200301/1278762dec62465daab17555b5cf8e78.jpg","isgm":0}]
         * zq : [{"id":1,"name":"南瓜跑车","price":"999.00","image":"http://qutu.zzmzrj.com/upload/uploads/20200301/7a7569b3361aaf6a77621baa492fb9c3.jpg","images":"http://qutu.zzmzrj.com/upload/uploads/20200301/7a7569b3361aaf6a77621baa492fb9c3.jpg","isgm":0},{"id":2,"name":"神龙瘦","price":"987.00","image":"http://qutu.zzmzrj.com/upload/uploads/20200301/38d34c6f94e7715649a67f1db81a1671.jpg","images":"http://qutu.zzmzrj.com/upload/uploads/20200301/38d34c6f94e7715649a67f1db81a1671.jpg","isgm":0}]
         * headimgurl :
         */

        private String headimgurl;
        private List<TsBean> ts;
        private List<ZqBean> zq;

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public List<TsBean> getTs() {
            return ts;
        }

        public void setTs(List<TsBean> ts) {
            this.ts = ts;
        }

        public List<ZqBean> getZq() {
            return zq;
        }

        public void setZq(List<ZqBean> zq) {
            this.zq = zq;
        }

        public static class TsBean {
            /**
             * id : 1
             * price : 99.00
             * name : 闪电
             * image : http://qutu.zzmzrj.com/upload/uploads/20200301/0d73c50724f6c80f35d59b56338ac65c.jpg
             * isgm : 0
             */

            private int id;
            private String price;
            private String name;
            private String image;
            private int isgm;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getIsgm() {
                return isgm;
            }

            public void setIsgm(int isgm) {
                this.isgm = isgm;
            }
        }

        public static class ZqBean {
            /**
             * id : 1
             * name : 南瓜跑车
             * price : 999.00
             * image : http://qutu.zzmzrj.com/upload/uploads/20200301/7a7569b3361aaf6a77621baa492fb9c3.jpg
             * images : http://qutu.zzmzrj.com/upload/uploads/20200301/7a7569b3361aaf6a77621baa492fb9c3.jpg
             * isgm : 0
             */

            private int id;
            private String name;
            private String price;
            private String image;
            private String images;
            private int isgm;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public int getIsgm() {
                return isgm;
            }

            public void setIsgm(int isgm) {
                this.isgm = isgm;
            }
        }
    }
}
