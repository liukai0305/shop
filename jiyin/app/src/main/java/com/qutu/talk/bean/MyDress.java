package com.qutu.talk.bean;

import java.util.List;

public class MyDress {

    /**
     * code : 1
     * message : 请求成功
     * data : {"zqinfo":[{"id":1,"zqid":1,"user_id":"724002","addtime":"1584525860","endtime":"1584525860","zqname":"南瓜跑车","image":"http://qutu.zzmzrj.com/upload/uploads/20200301/7a7569b3361aaf6a77621baa492fb9c3.jpg","status":1},{"id":5,"zqid":2,"user_id":"724002","addtime":"1584087176","endtime":"1584633600","zqname":"神龙瘦","image":"http://qutu.zzmzrj.com/upload/uploads/20200301/38d34c6f94e7715649a67f1db81a1671.jpg","status":0}],"tsinfo":[{"id":1,"tsid":1,"user_id":"724002","endtime":"1584525860","addtime":"1584525860","tsname":"闪电","image":"http://qutu.zzmzrj.com/upload/uploads/20200301/0d73c50724f6c80f35d59b56338ac65c.jpg","status":1},{"id":20,"tsid":2,"user_id":"724002","endtime":"1584633600","addtime":"1584069230","tsname":"男爵骑士","image":"http://qutu.zzmzrj.com/upload/uploads/20200301/1278762dec62465daab17555b5cf8e78.jpg","status":0}]}
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
        private List<ZqinfoBean> zqinfo;
        private List<TsinfoBean> tsinfo;

        public List<ZqinfoBean> getZqinfo() {
            return zqinfo;
        }

        public void setZqinfo(List<ZqinfoBean> zqinfo) {
            this.zqinfo = zqinfo;
        }

        public List<TsinfoBean> getTsinfo() {
            return tsinfo;
        }

        public void setTsinfo(List<TsinfoBean> tsinfo) {
            this.tsinfo = tsinfo;
        }

        public static class ZqinfoBean {
            /**
             * id : 1
             * zqid : 1
             * user_id : 724002
             * addtime : 1584525860
             * endtime : 1584525860
             * zqname : 南瓜跑车
             * image : http://qutu.zzmzrj.com/upload/uploads/20200301/7a7569b3361aaf6a77621baa492fb9c3.jpg
             * status : 1
             */

            private int id;
            private int zqid;
            private String user_id;
            private String addtime;
            private String endtime;
            private String zqname;
            private String image;
            private int status;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getZqid() {
                return zqid;
            }

            public void setZqid(int zqid) {
                this.zqid = zqid;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public String getZqname() {
                return zqname;
            }

            public void setZqname(String zqname) {
                this.zqname = zqname;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

        public static class TsinfoBean {
            /**
             * id : 1
             * tsid : 1
             * user_id : 724002
             * endtime : 1584525860
             * addtime : 1584525860
             * tsname : 闪电
             * image : http://qutu.zzmzrj.com/upload/uploads/20200301/0d73c50724f6c80f35d59b56338ac65c.jpg
             * status : 1
             */

            private int id;
            private int tsid;
            private String user_id;
            private String endtime;
            private String addtime;
            private String tsname;
            private String image;
            private int status;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getTsid() {
                return tsid;
            }

            public void setTsid(int tsid) {
                this.tsid = tsid;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getTsname() {
                return tsname;
            }

            public void setTsname(String tsname) {
                this.tsname = tsname;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
