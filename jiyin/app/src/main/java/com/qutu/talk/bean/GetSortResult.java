package com.qutu.talk.bean;

public class GetSortResult {


    /**
     * code : 1
     * message : 请求成功
     * data : {"sort":0}
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
         * sort : 0
         */

        private String sort;
        private String num;
        private String shiyin_sort;//试音的顺序
        private String shiyin_num;//试音数量

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getShiyin_sort() {
            return shiyin_sort;
        }

        public void setShiyin_sort(String shiyin_sort) {
            this.shiyin_sort = shiyin_sort;
        }

        public String getShiyin_num() {
            return shiyin_num;
        }

        public void setShiyin_num(String shiyin_num) {
            this.shiyin_num = shiyin_num;
        }
    }
}
