package com.qutu.talk.bean.dashen;

import java.util.List;

public class ScreenSexBean {
    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * price : 5
         * unit : 局
         * orders : 0
         * level : 1
         * checked : 1
         */

        private String id;
        private String sex;
        public boolean isSelector;
        public boolean isClick;

        public DataBean(String id, String sex, boolean isSelector, boolean isClick) {
            this.id = id;
            this.sex = sex;
            this.isSelector = isSelector;
            this.isClick = isClick;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
