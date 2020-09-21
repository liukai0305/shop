package com.qutu.talk.bean;

public class TopThreeImageBean {
    private String url;
    private boolean isNetwork; // 判断是否是网络图片
    private int index; //记录图片的位置

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isNetwork() {
        return isNetwork;
    }

    public void setNetwork(boolean network) {
        isNetwork = network;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
