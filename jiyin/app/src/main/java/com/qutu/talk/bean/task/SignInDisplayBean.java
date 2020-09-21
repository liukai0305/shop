package com.qutu.talk.bean.task;

import java.util.List;

public class SignInDisplayBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"title":"已经连续签到1天","is_sign":1,"data":[{"name":"5钻石","img":"http://tp5_test.miniyuyin.cn/upload/wares/jinbi.png","day":"第一天","is_sign":1},{"name":"5钻石","img":"http://tp5_test.miniyuyin.cn/upload/wares/jinbi.png","day":"第二天","is_sign":1},{"name":"头像框","img":"http://47.92.85.75/upload/emoji/txk/xctxk.png","day":"第三天","is_sign":1},{"name":"5钻石","img":"http://tp5_test.miniyuyin.cn/upload/wares/jinbi.png","day":"第四天","is_sign":1},{"name":"5钻石","img":"http://tp5_test.miniyuyin.cn/upload/wares/jinbi.png","day":"第五天","is_sign":1},{"name":"5钻石","img":"http://tp5_test.miniyuyin.cn/upload/wares/jinbi.png","day":"第六天","is_sign":1},{"name":"头像框","img":"http://47.92.85.75/upload/emoji/txk/xxtxk.png","day":"第七天","is_sign":1}]}
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
         * title : 已经连续签到1天
         * is_sign : 1
         * data : [{"name":"5钻石","img":"http://tp5_test.miniyuyin.cn/upload/wares/jinbi.png","day":"第一天","is_sign":1},{"name":"5钻石","img":"http://tp5_test.miniyuyin.cn/upload/wares/jinbi.png","day":"第二天","is_sign":1},{"name":"头像框","img":"http://47.92.85.75/upload/emoji/txk/xctxk.png","day":"第三天","is_sign":1},{"name":"5钻石","img":"http://tp5_test.miniyuyin.cn/upload/wares/jinbi.png","day":"第四天","is_sign":1},{"name":"5钻石","img":"http://tp5_test.miniyuyin.cn/upload/wares/jinbi.png","day":"第五天","is_sign":1},{"name":"5钻石","img":"http://tp5_test.miniyuyin.cn/upload/wares/jinbi.png","day":"第六天","is_sign":1},{"name":"头像框","img":"http://47.92.85.75/upload/emoji/txk/xxtxk.png","day":"第七天","is_sign":1}]
         */

        private String title = "";
        private int is_sign;
        private List<DataBean> data;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getIs_sign() {
            return is_sign;
        }

        public void setIs_sign(int is_sign) {
            this.is_sign = is_sign;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * name : 5钻石
             * img : http://tp5_test.miniyuyin.cn/upload/wares/jinbi.png
             * day : 第一天
             * is_sign : 1
             */

            private String name;
            private String img;
            private String day;
            private int is_sign;
            private String is_jinbi;

            public String getIs_jinbi() {
                return is_jinbi;
            }

            public void setIs_jinbi(String is_jinbi) {
                this.is_jinbi = is_jinbi;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public int getIs_sign() {
                return is_sign;
            }

            public void setIs_sign(int is_sign) {
                this.is_sign = is_sign;
            }
        }
    }
}
