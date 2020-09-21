package com.qutu.talk.bean;

import java.util.List;

public class ClearMlHistory {


    /**
     * code : 1
     * message : 查询成功
     * data : [{"id":1,"roomid":"1100162","user_id":"1100162","num":"257695","b_user_id":"1100162","addtime":"1579334261","headimgurl":"http://47.92.85.75/upload//cover/20190825/15667344533678.png","nickname":"倾心交友"},{"id":2,"roomid":"1100162","user_id":"1100162","num":"2","b_user_id":"1111090","addtime":"1579334320","headimgurl":"http://qutu.zzmzrj.com/upload/avatar/20200103/15780151195089.jpg","nickname":"k"}]
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
         * id : 1
         * roomid : 1100162
         * user_id : 1100162
         * num : 257695
         * b_user_id : 1100162
         * addtime : 1579334261
         * headimgurl : http://47.92.85.75/upload//cover/20190825/15667344533678.png
         * nickname : 倾心交友
         */

        private int id;
        private String roomid;
        private String user_id;
        private String num;
        private String b_user_id;
        private String addtime;
        private String headimgurl;
        private String nickname;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRoomid() {
            return roomid;
        }

        public void setRoomid(String roomid) {
            this.roomid = roomid;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getB_user_id() {
            return b_user_id;
        }

        public void setB_user_id(String b_user_id) {
            this.b_user_id = b_user_id;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
