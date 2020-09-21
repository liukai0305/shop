package com.qutu.talk.bean;

import java.util.List;

public class TodayRecommBean {

    private int code;
    private String message;

    private List<RecommendUser> data;

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

    public List<RecommendUser> getData() {
        return data;
    }

    public void setData(List<RecommendUser> data) {
        this.data = data;
    }
}
