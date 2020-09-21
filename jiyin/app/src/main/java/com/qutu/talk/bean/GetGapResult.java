package com.qutu.talk.bean;

public class GetGapResult {


    /**
     * code : 1
     * message : 请求成功
     * data : {"gap":123751}
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
         * gap : 123751
         */

        private String gap;
        private String hot;
        private String exp;


        public String getGap() {
            return gap;
        }

        public void setGap(String gap) {
            this.gap = gap;
        }

        public String getHot() {
            return hot;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }

        public String getExp() {
            return exp;
        }

        public void setExp(String exp) {
            this.exp = exp;
        }
    }
}
