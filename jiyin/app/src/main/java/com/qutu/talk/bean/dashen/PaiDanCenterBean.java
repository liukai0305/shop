package com.qutu.talk.bean.dashen;

import java.util.List;

public class PaiDanCenterBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"uid":1100119,"room_name":"%E9%A3%9B%E9%A3%9B","remark":"啊啊啊","time":"2019年11月15日 15:30","status":1,"skill_name":"王者荣耀","image":"http://tp5_test.miniyuyin.cn/upload/game/logo/wzry.png"},{"uid":1100119,"room_name":"%E9%A3%9B%E9%A3%9B","remark":"","time":"2019年11月15日 18:00","status":1,"skill_name":"王者荣耀","image":"http://tp5_test.miniyuyin.cn/upload/game/logo/wzry.png"},{"uid":1100119,"room_name":"%E9%A3%9B%E9%A3%9B","remark":"","time":"2019年11月15日 17:45","status":1,"skill_name":"王者荣耀","image":"http://tp5_test.miniyuyin.cn/upload/game/logo/wzry.png"}]
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
         * uid : 1100119
         * room_name : %E9%A3%9B%E9%A3%9B
         * remark : 啊啊啊
         * time : 2019年11月15日 15:30
         * status : 1
         * skill_name : 王者荣耀
         * image : http://tp5_test.miniyuyin.cn/upload/game/logo/wzry.png
         */

        private int uid;
        private String room_name;
        private String remark;
        private String time;
        private int status;
        private String skill_name;
        private String image;
        private String addtime;
        private String room_cover;

        public String getRoom_cover() {
            return room_cover;
        }

        public void setRoom_cover(String room_cover) {
            this.room_cover = room_cover;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getRoom_name() {
            return room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getSkill_name() {
            return skill_name;
        }

        public void setSkill_name(String skill_name) {
            this.skill_name = skill_name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }
    }
}
