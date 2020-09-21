package com.qutu.talk.bean;

import java.util.List;

public class RoomListResult {
    private int code;
    private String message;
    private List<RoomSimpleIntro> data;

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

    public List<RoomSimpleIntro> getData() {
        return data;
    }

    public void setData(List<RoomSimpleIntro> data) {
        this.data = data;
    }

}
