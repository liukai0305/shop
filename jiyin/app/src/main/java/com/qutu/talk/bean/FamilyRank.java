package com.qutu.talk.bean;

import java.util.List;

public class FamilyRank {


    /**
     * code : 1
     * message : 请求成功
     * data : {"top":[{"exp":210,"jzid":"504","jzname":"504家族","img":"http://qutu.zzmzrj.com/upload/avatar/20200302/15831576335961.jpg"},{"exp":"","jzid":"","jzname":"","img":""},{"exp":"","jzid":"","jzname":"","img":""}],"other":[]}
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
        private List<Item> top;
        private List<Item> other;

        public List<Item> getTop() {
            return top;
        }

        public void setTop(List<Item> top) {
            this.top = top;
        }

        public List<Item> getOther() {
            return other;
        }

        public void setOther(List<Item> other) {
            this.other = other;
        }

        public static class Item {
            /**
             * exp : 210
             * jzid : 504
             * jzname : 504家族
             * img : http://qutu.zzmzrj.com/upload/avatar/20200302/15831576335961.jpg
             */

            private String exp;
            private String jzid;
            private String jzname;
            private String img;
            private boolean isBg;

            public boolean isBg() {
                return isBg;
            }

            public void setBg(boolean bg) {
                isBg = bg;
            }

            public String getExp() {
                return exp;
            }

            public void setExp(String exp) {
                this.exp = exp;
            }

            public String getJzid() {
                return jzid;
            }

            public void setJzid(String jzid) {
                this.jzid = jzid;
            }

            public String getJzname() {
                return jzname;
            }

            public void setJzname(String jzname) {
                this.jzname = jzname;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
