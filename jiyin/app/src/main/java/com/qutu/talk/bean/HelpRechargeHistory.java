package com.qutu.talk.bean;

import java.util.List;

public class HelpRechargeHistory {


    /**
     * code : 1
     * message : 查询成功
     * data : [{"b_headimg":"http://qutu.zzmzrj.com/upload/avatar/20200103/15780151195089.jpg","z_user_id":"1111089","b_user_id":"1111090","num":"100.00","addtime":"1578303869"},{"b_headimg":"http://qutu.zzmzrj.com/upload/avatar/20200103/15780151195089.jpg","z_user_id":"1111089","b_user_id":"1111090","num":"100.00","addtime":"1578303886"}]
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
         * b_headimg : http://qutu.zzmzrj.com/upload/avatar/20200103/15780151195089.jpg
         * z_user_id : 1111089
         * b_user_id : 1111090
         * num : 100.00
         * addtime : 1578303869
         */

        private String b_headimg;
        private String z_user_id;
        private String b_user_id;
        private String num;
        private String addtime;

        public String getB_headimg() {
            return b_headimg;
        }

        public void setB_headimg(String b_headimg) {
            this.b_headimg = b_headimg;
        }

        public String getZ_user_id() {
            return z_user_id;
        }

        public void setZ_user_id(String z_user_id) {
            this.z_user_id = z_user_id;
        }

        public String getB_user_id() {
            return b_user_id;
        }

        public void setB_user_id(String b_user_id) {
            this.b_user_id = b_user_id;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }
    }
}
