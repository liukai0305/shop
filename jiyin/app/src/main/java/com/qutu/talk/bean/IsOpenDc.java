package com.qutu.talk.bean;

public class IsOpenDc {


    /**
     * code : 1
     * message : 请求成功
     * data : {"iskq":"1"}
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
         * iskq : 1
         */

        private int iskq;

        public int getIskq() {
            return iskq;
        }

        public void setIskq(int iskq) {
            this.iskq = iskq;
        }
    }
}
