package com.qutu.talk.bean;

import java.util.List;

public class BestRoomResult {


    /**
     * code : 1
     * message : 请求成功
     * data : [{"uid":1100162,"room_cover":"http://47.92.85.75/upload//cover/20190825/15667344416670.png","room_name":"倾心娱乐🌟听歌交友"},{"uid":1100026,"room_cover":"http://47.92.85.75/upload//cover/20190910/15680957596052.png","room_name":"李软"},{"uid":1100025,"room_cover":"http://47.92.85.75/upload//cover/20190823/15665256853821.png","room_name":"cf"}]
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
         * uid : 1100162
         * room_cover : http://47.92.85.75/upload//cover/20190825/15667344416670.png
         * room_name : 倾心娱乐🌟听歌交友
         */

        private String uid;
        private String room_cover;
        private String room_name;
        private String hot = "";

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getRoom_cover() {
            return room_cover;
        }

        public void setRoom_cover(String room_cover) {
            this.room_cover = room_cover;
        }

        public String getRoom_name() {
            return room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
        }

        public String getHot() {
            return hot;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }
    }
}
