package com.qutu.talk.bean;

public class RankExplainBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"value":"<p><b>什么是金锐榜？<\/b><\/p><p>金锐榜是根据用户赠送礼物金币对应的金锐值排列的榜单；<\/p><p><br><\/p><p><b>什么是星锐榜？<\/b><\/p><p>星锐榜是根据用户收到礼物金币对应的金锐值排列的榜单；<\/p><p><br><\/p><p><b>什么是房间金锐榜？<\/b><\/p><p>房间金锐榜是每个房间内用户的赠送礼物金币对应的金锐值排列的榜单；<\/p><p><br><\/p><p><b>什么是房间星锐榜？<\/b><\/p><p>房间星锐榜是每个房间内用户收到的礼物金币对应的金锐值排列的榜单；<\/p><p><br><\/p><p><b>什么是房间榜？<\/b><\/p><p>房间榜是所有房间的排名，按照房间内赠送礼物计算，礼物金币越高排名越靠前；<\/p>"}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * value : <p><b>什么是金锐榜？</b></p><p>金锐榜是根据用户赠送礼物金币对应的金锐值排列的榜单；</p><p><br></p><p><b>什么是星锐榜？</b></p><p>星锐榜是根据用户收到礼物金币对应的金锐值排列的榜单；</p><p><br></p><p><b>什么是房间金锐榜？</b></p><p>房间金锐榜是每个房间内用户的赠送礼物金币对应的金锐值排列的榜单；</p><p><br></p><p><b>什么是房间星锐榜？</b></p><p>房间星锐榜是每个房间内用户收到的礼物金币对应的金锐值排列的榜单；</p><p><br></p><p><b>什么是房间榜？</b></p><p>房间榜是所有房间的排名，按照房间内赠送礼物计算，礼物金币越高排名越靠前；</p>
         */

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
