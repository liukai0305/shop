package com.qutu.talk.bean;

public class MyFamily {


    /**
     * code : 1
     * message : 请求成功
     * data : {"jzid":504}
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
         * jzid : 504
         */

        private String jzid;

        public String getJzid() {
            return jzid;
        }

        public void setJzid(String jzid) {
            this.jzid = jzid;
        }
    }
}
