package com.qutu.talk.bean;

import android.text.TextUtils;

import com.qutu.talk.utils.Arith;
import com.qutu.talk.utils.TimeSwitchUtil;

import java.util.Date;

public class MicUserBean {

    /**
     * id : 1151896
     * nickname : 春天花
     * headimgurl : http://47.92.85.75/upload//avatar/20190816/15659281014158.png
     * is_mic : 0
     */

    private String id;
    private String nickname;
    private String headimgurl;
    private int is_mic;//1,在麦上，0 麦下
    private String sort = "";//排麦序号
    private String addtime = "";//排麦的时间

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

    public int getIs_mic() {
        return is_mic;
    }

    public void setIs_mic(int is_mic) {
        this.is_mic = is_mic;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getAddtime() {
        String time = "";
        if(!TextUtils.isEmpty(addtime)){
            long times = Arith.strToLong(addtime)*1000;
            Date date = new Date(times);
            time = TimeSwitchUtil.getTimeFormatText(date);
        } else {
            time = addtime;
        }
        return time;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }
}
