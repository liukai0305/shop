package com.qutu.talk.bean.dashen;

public class DaShenZhuanShuBean {


    /**
     * code : 1
     * message : 请求成功
     * data : {"mili":"100","is_newpd":1,"num":1}
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
         * mili : 100
         * is_newpd : 1
         * num : 1
         */

        private String mili;
        private int is_newpd;
        private String num;

        public String getMili() {
            return mili;
        }

        public void setMili(String mili) {
            this.mili = mili;
        }

        public int getIs_newpd() {
            return is_newpd;
        }

        public void setIs_newpd(int is_newpd) {
            this.is_newpd = is_newpd;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
