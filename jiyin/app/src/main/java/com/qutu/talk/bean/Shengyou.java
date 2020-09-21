package com.qutu.talk.bean;

import java.util.List;

public class Shengyou {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"username":"zz","lwname":"么么哒","user_id":"12218","num":1}]
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
        public int id;
        public String nickname;
        public String headimgurl;
    }
}
