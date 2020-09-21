package com.qutu.talk.bean;

public class RoomFamily {


    /**
     * code : 1
     * message : 请求成功
     * data : {"jzimg":"http://qutu.zzmzrj.com/upload/avatar/20200302/15831576335961.jpg","count":1,"family_id":504}
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
         * jzimg : http://qutu.zzmzrj.com/upload/avatar/20200302/15831576335961.jpg
         * count : 1
         * family_id : 504
         */

        private String jzimg;
        private String count;
        private String family_id;

        public String getJzimg() {
            return jzimg;
        }

        public void setJzimg(String jzimg) {
            this.jzimg = jzimg;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getFamily_id() {
            return family_id;
        }

        public void setFamily_id(String family_id) {
            this.family_id = family_id;
        }
    }
}
