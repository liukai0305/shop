package com.qutu.talk.bean.dashen;

import java.util.List;

public class MainHomePageBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"headimgurl":"http://tp5_test.miniyuyin.cn/upload/avatar/20191021/15716509366195.jpg","audio":"http://tp5_test.miniyuyin.cn/upload/audio/20191007/c3a85ef6ed29d3d7ec6b6635af472a1a.mp3","img_1":"","user_id":1100002,"nickname":"呼噜娃12","skill_id":1,"isOnline":0,"num":1,"score":"5.0","price":5,"unit":"局","skill_name":"王者荣耀","level_name":"倔强青铜"},{"headimgurl":"http://tp5_test.miniyuyin.cn/upload/avatar/20191024/15718942292819.png","audio":"","img_1":"http://tp5_test.miniyuyin.cn/upload/avatar/20191024/15718942292819.png","user_id":1100025,"nickname":"123456","skill_id":1,"isOnline":0,"num":0,"score":"5.0","price":5,"unit":"局","skill_name":"王者荣耀","level_name":"倔强青铜"}]
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
         * headimgurl : http://tp5_test.miniyuyin.cn/upload/avatar/20191021/15716509366195.jpg
         * audio : http://tp5_test.miniyuyin.cn/upload/audio/20191007/c3a85ef6ed29d3d7ec6b6635af472a1a.mp3
         * img_1 :
         * user_id : 1100002
         * nickname : 呼噜娃12
         * skill_id : 1
         * isOnline : 0
         * num : 1
         * score : 5.0
         * price : 5
         * unit : 局
         * skill_name : 王者荣耀
         * level_name : 倔强青铜
         */
        private String id;
        private String headimgurl;
        private String audio;
        private String img_1;
        private int user_id;
        private String nickname;
        private int skill_id;
        private int isOnline;
        private int num;
        private String score;
        private int price;
        private String unit;
        private String skill_name;
        private String level_name;
        private String audio_time;
        private boolean play;
        private String currentTime;//当前播放倒计时时间

        public boolean isPlay() {
            return play;
        }

        public void setPlay(boolean play) {
            this.play = play;
        }

        public String getCurrentTime() {
            return currentTime;
        }

        public void setCurrentTime(String currentTime) {
            this.currentTime = currentTime;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getAudio() {
            return audio;
        }

        public void setAudio(String audio) {
            this.audio = audio;
        }

        public String getImg_1() {
            return img_1;
        }

        public void setImg_1(String img_1) {
            this.img_1 = img_1;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getSkill_id() {
            return skill_id;
        }

        public void setSkill_id(int skill_id) {
            this.skill_id = skill_id;
        }

        public int getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(int isOnline) {
            this.isOnline = isOnline;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getSkill_name() {
            return skill_name;
        }

        public void setSkill_name(String skill_name) {
            this.skill_name = skill_name;
        }

        public String getLevel_name() {
            return level_name;
        }

        public void setLevel_name(String level_name) {
            this.level_name = level_name;
        }

        public String getAudio_time() {
            return audio_time;
        }

        public void setAudio_time(String audio_time) {
            this.audio_time = audio_time;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
