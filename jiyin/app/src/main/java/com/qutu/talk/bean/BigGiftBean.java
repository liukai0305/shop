package com.qutu.talk.bean;

import java.util.List;

public class BigGiftBean {

    /**
     * userInfo : [{"gift_name":"怦然心动","num":"1","img":"http://47.92.85.75/upload/gifts/sanqi/gift_prxd.png","uid":"1100001","from_name":"用户1101088","user_name":"白羊"}]
     * messageType : 20
     */

    private String messageType;
    private List<PushBean.DataBean> userInfo;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public List<PushBean.DataBean> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<PushBean.DataBean> userInfo) {
        this.userInfo = userInfo;
    }

}
