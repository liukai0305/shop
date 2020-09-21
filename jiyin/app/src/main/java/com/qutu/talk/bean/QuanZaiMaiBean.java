package com.qutu.talk.bean;

import java.util.List;

public class QuanZaiMaiBean {
    private String chang;
    private Microphone.DataBean data;

    public Microphone.DataBean getData() {
        return data;
    }

    public void setData(Microphone.DataBean data) {
        this.data = data;
    }

    public String getChang() {
        return chang;
    }

    public void setChang(String chang) {
        this.chang = chang;
    }

    public static class DataBean {
        /**
         * microphone : [{"shut_sound":1,"status":3},{"shut_sound":1,"status":3},{"headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","id":1151835,"is_sound":1,"nickname":"手机用户92813950","sex":1,"shut_sound":1,"status":2,"user_id":1151835},{"shut_sound":1,"status":3},{"shut_sound":1,"status":3},{"shut_sound":1,"status":3},{"shut_sound":1,"status":3},{"shut_sound":1,"status":3}]
         * user_id :
         */

        private String user_id;
        private List<MicrophoneBean> microphone;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public List<MicrophoneBean> getMicrophone() {
            return microphone;
        }

        public void setMicrophone(List<MicrophoneBean> microphone) {
            this.microphone = microphone;
        }

        public static class MicrophoneBean {
            public boolean isSelect = false;
            public int indexl;
            /**
             * shut_sound : 1
             * status : 3
             * headimgurl : http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg
             * id : 1151835
             * is_sound : 1
             * nickname : 手机用户92813950
             * sex : 1
             * user_id : 1151835
             */

            private int shut_sound;
            private int status;
            private String headimgurl;
            private int id;
            private int is_sound;
            private String nickname;
            private int sex;
            private String user_id = "";
            private String txk;//头像框
            private String mic_color;//麦上光圈颜色
            private String price = "";//数值
            private String is_play = "";//是否开启了数值玩法 1 开启了 0 关闭了
            private String is_master = "";//是否房主 1 是 0 不是

            public int getShut_sound() {
                return shut_sound;
            }

            public void setShut_sound(int shut_sound) {
                this.shut_sound = shut_sound;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIs_sound() {
                return is_sound;
            }

            public void setIs_sound(int is_sound) {
                this.is_sound = is_sound;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getTxk() {
                return txk;
            }

            public void setTxk(String txk) {
                this.txk = txk;
            }

            public String getMic_color() {
                return mic_color;
            }

            public void setMic_color(String mic_color) {
                this.mic_color = mic_color;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getIs_play() {
                return is_play;
            }

            public void setIs_play(String is_play) {
                this.is_play = is_play;
            }

            public String getIs_master() {
                return is_master;
            }

            public void setIs_master(String is_master) {
                this.is_master = is_master;
            }

            public int getIndexl() {
                return indexl;
            }

            public void setIndexl(int indexl) {
                this.indexl = indexl;
            }
        }
    }
}
