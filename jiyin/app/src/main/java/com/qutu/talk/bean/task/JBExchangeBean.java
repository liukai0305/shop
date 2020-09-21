package com.qutu.talk.bean.task;

import java.util.List;

public class JBExchangeBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"time":"2019.12.10","data":[{"id":12,"user_id":1103,"wares_id":72,"title":"兑换3金币优惠券 7天","jinbi":200,"logtime":"2019.12.10","addtime":1575947438},{"id":11,"user_id":1103,"wares_id":72,"title":"兑换3金币优惠券 7天","jinbi":200,"logtime":"2019.12.10","addtime":1575947407}]},{"time":"2019.12.09","data":[{"id":8,"user_id":1103,"wares_id":71,"title":"兑换宝箱钥匙x10","jinbi":200,"logtime":"2019.12.09","addtime":1575875171},{"id":7,"user_id":1103,"wares_id":71,"title":"兑换宝箱钥匙x10","jinbi":200,"logtime":"2019.12.09","addtime":1575871588},{"id":6,"user_id":1103,"wares_id":71,"title":"兑换宝箱钥匙x1","jinbi":20,"logtime":"2019.12.09","addtime":1575869202},{"id":5,"user_id":1103,"wares_id":71,"title":"兑换宝箱钥匙x10","jinbi":200,"logtime":"2019.12.09","addtime":1575869197},{"id":4,"user_id":1103,"wares_id":72,"title":"兑换3金币优惠券 7天","jinbi":200,"logtime":"2019.12.09","addtime":1575869182},{"id":3,"user_id":1103,"wares_id":68,"title":"兑换小恶魔头像框 15天","jinbi":200,"logtime":"2019.12.09","addtime":1575869170}]},{"time":"2019.12.08","data":[{"id":2,"user_id":1103,"wares_id":69,"title":"兑换小恶魔气泡框 15天","jinbi":200,"logtime":"2019.12.08","addtime":1575869161}]},{"time":"2019.12.07","data":[{"id":1,"user_id":1103,"wares_id":69,"title":"兑换小恶魔气泡框x1","jinbi":200,"logtime":"2019.12.07","addtime":1575869121}]}]
     */

    private int code;
    private String message;
    private List<DataBeanX> data;

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

    public List<DataBeanX> getData() {
        return data;
    }

    public void setData(List<DataBeanX> data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * time : 2019.12.10
         * data : [{"id":12,"user_id":1103,"wares_id":72,"title":"兑换3金币优惠券 7天","jinbi":200,"logtime":"2019.12.10","addtime":1575947438},{"id":11,"user_id":1103,"wares_id":72,"title":"兑换3金币优惠券 7天","jinbi":200,"logtime":"2019.12.10","addtime":1575947407}]
         */

        private String time;
        private List<DataBean> data;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 12
             * user_id : 1103
             * wares_id : 72
             * title : 兑换3金币优惠券 7天
             * jinbi : 200
             * logtime : 2019.12.10
             * addtime : 1575947438
             */

            private int id;
            private int user_id;
            private int wares_id;
            private String title;
            private String jinbi;
            private String logtime;
            private int addtime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getWares_id() {
                return wares_id;
            }

            public void setWares_id(int wares_id) {
                this.wares_id = wares_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getJinbi() {
                return jinbi;
            }

            public void setJinbi(String jinbi) {
                this.jinbi = jinbi;
            }

            public String getLogtime() {
                return logtime;
            }

            public void setLogtime(String logtime) {
                this.logtime = logtime;
            }

            public int getAddtime() {
                return addtime;
            }

            public void setAddtime(int addtime) {
                this.addtime = addtime;
            }
        }
    }
}
