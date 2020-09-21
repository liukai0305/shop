package com.qutu.talk.bean;

import java.util.List;

public class ShengyouQuanbutop {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"uid":1100002,"numid":"1100002","hot":"2786.8w","room_name":"啦咯哦考虑咯莫问","room_cover":"http://tp5_test.miniyuyin.cn/upload/cover/20191105/15729402542961.png","microphone":"0,1103271,1102791,1101988,0,1100028,1102776,0,0","cate_img":"http://47.92.85.75/upload/emoji/room_cate/004.png","name":"聊天","host":""},{"uid":1100001,"numid":"1100001","hot":"751.4w","room_name":"帅气阿呆","room_cover":"http://tp5_test.miniyuyin.cn/upload/cover/20191105/15729402544249.png","microphone":"1101709,1100564,0,1100952,1100693,1100246,1100982,1100904,1100437","cate_img":"http://47.92.85.75/upload/emoji/room_cate/003.png","name":"FM","host":"语音.顾子囚"},{"uid":1100000,"numid":"1100000","hot":60,"room_name":"飛飛","room_cover":"http://tp5_test.miniyuyin.cn/upload/cover/20191105/15729402545008.png","microphone":"0,0,0,0,0,0,0,0,0","cate_img":"http://47.92.85.75/upload/emoji/room_cate/004.png","name":"聊天","host":""}]
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
         * uid : 1100002
         * numid : 1100002
         * hot : 2786.8w
         * room_name : 啦咯哦考虑咯莫问
         * room_cover : http://tp5_test.miniyuyin.cn/upload/cover/20191105/15729402542961.png
         * microphone : 0,1103271,1102791,1101988,0,1100028,1102776,0,0
         * cate_img : http://47.92.85.75/upload/emoji/room_cate/004.png
         * name : 聊天
         * host :
         *
         *
         room_name
         string
         true
         房间名称
         is_afk
         number
         true
         是否在线 1 在线 0离线
         numid
         string
         true
         房间id
         openid
         string
         true
         week_star
         number
         true
         是否是周星1是2不是
         uid
         number
         true
         房主uid
         room_cover
         string
         true
         房间封面
         nickname
         string
         true
         房主名称
         sex
         number
         true
         性别
         name
         string
         true
         分类名称
         */

        private int uid;
        private String numid;
        private String room_name;
        private String room_cover;
        private String name;
        private String openid;
        private String nickname;
        private int is_afk;
        private int week_star;
        private int sex;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getNumid() {
            return numid;
        }

        public void setNumid(String numid) {
            this.numid = numid;
        }

        public String getRoom_name() {
            return room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
        }

        public String getRoom_cover() {
            return room_cover;
        }

        public void setRoom_cover(String room_cover) {
            this.room_cover = room_cover;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getIs_afk() {
            return is_afk;
        }

        public void setIs_afk(int is_afk) {
            this.is_afk = is_afk;
        }

        public int getWeek_star() {
            return week_star;
        }

        public void setWeek_star(int week_star) {
            this.week_star = week_star;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }
    }
}
