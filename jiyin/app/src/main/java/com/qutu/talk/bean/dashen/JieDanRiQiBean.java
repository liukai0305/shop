package com.qutu.talk.bean.dashen;

import java.util.List;

public class JieDanRiQiBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"weeknum":1,"week":"周一","status":0},{"weeknum":2,"week":"周二","status":0},{"weeknum":3,"week":"周三","status":0},{"weeknum":4,"week":"周四","status":0},{"weeknum":5,"week":"周五","status":0},{"weeknum":6,"week":"周六","status":1},{"weeknum":7,"week":"周日","status":1}]
     */

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
         * weeknum : 1
         * week : 周一
         * status : 0
         */

        private int weeknum;
        private String week;
        private int status;

        public int getWeeknum() {
            return weeknum;
        }

        public void setWeeknum(int weeknum) {
            this.weeknum = weeknum;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
