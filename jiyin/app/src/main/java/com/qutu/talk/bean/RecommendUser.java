package com.qutu.talk.bean;

public class RecommendUser {

    /**
     * id : 1100162
     * nickname : 倾心娱乐
     * headimgurl : http://47.92.85.75/upload//cover/20190825/15667344533678.png
     * is_follow : 0
     */

    private String id;
    private String nickname;
    private String headimgurl;
    private int is_follow;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public int getIs_follow() {
        return is_follow;
    }

    public void setIs_follow(int is_follow) {
        this.is_follow = is_follow;
    }
}
