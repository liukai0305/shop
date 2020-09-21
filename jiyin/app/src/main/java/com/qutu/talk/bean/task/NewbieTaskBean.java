package com.qutu.talk.bean.task;

import java.util.List;

public class NewbieTaskBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"id":6,"type":2,"img":"http://tp5_test.miniyuyin.cn/upload/uploads/20191128/ec762bc482d36b68c6246f72f89cd9e9.png","title":"观看直播5分钟","num":1,"jinbi":5,"enable":1,"addtime":1575515261,"fin":1,"recevice":1,"status":3},{"id":7,"type":2,"img":"http://tp5_test.miniyuyin.cn/upload/uploads/20191128/ec762bc482d36b68c6246f72f89cd9e9.png","title":"赠送他人3个礼物","num":3,"jinbi":5,"enable":1,"addtime":1575515261,"fin":3,"recevice":1,"status":3},{"id":8,"type":2,"img":"http://tp5_test.miniyuyin.cn/upload/uploads/20191128/ec762bc482d36b68c6246f72f89cd9e9.png","title":"分享1次语音房","num":1,"jinbi":5,"enable":1,"addtime":1575515261,"fin":0,"recevice":0,"status":1},{"id":9,"type":2,"img":"http://tp5_test.miniyuyin.cn/upload/uploads/20191128/ec762bc482d36b68c6246f72f89cd9e9.png","title":"观看3个直播","num":3,"jinbi":5,"enable":1,"addtime":1575515261,"fin":0,"recevice":0,"status":1},{"id":10,"type":2,"img":"http://tp5_test.miniyuyin.cn/upload/uploads/20191128/ec762bc482d36b68c6246f72f89cd9e9.png","title":"和3个人发送对话消息","num":3,"jinbi":5,"enable":1,"addtime":1575515261,"fin":0,"recevice":0,"status":1},{"id":11,"type":2,"img":"http://tp5_test.miniyuyin.cn/upload/uploads/20191128/ec762bc482d36b68c6246f72f89cd9e9.png","title":"每日充值1次","num":1,"jinbi":5,"enable":1,"addtime":1575515261,"fin":0,"recevice":0,"status":1},{"id":12,"type":2,"img":"http://tp5_test.miniyuyin.cn/upload/uploads/20191128/ec762bc482d36b68c6246f72f89cd9e9.png","title":"发布1条动态","num":1,"jinbi":5,"enable":1,"addtime":1575515261,"fin":0,"recevice":0,"status":1}]
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
         * id : 6
         * type : 2
         * img : http://tp5_test.miniyuyin.cn/upload/uploads/20191128/ec762bc482d36b68c6246f72f89cd9e9.png
         * title : 观看直播5分钟
         * num : 1
         * jinbi : 5
         * enable : 1
         * addtime : 1575515261
         * fin : 1
         * recevice : 1
         * status : 3
         */

        private String id;
        private int type;
        private String img;
        private String title;
        private int num;
        private String jinbi;
        private int enable;
        private int addtime;
        private int fin;
        private int recevice;
        private int status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getJinbi() {
            return jinbi;
        }

        public void setJinbi(String jinbi) {
            this.jinbi = jinbi;
        }

        public int getEnable() {
            return enable;
        }

        public void setEnable(int enable) {
            this.enable = enable;
        }

        public int getAddtime() {
            return addtime;
        }

        public void setAddtime(int addtime) {
            this.addtime = addtime;
        }

        public int getFin() {
            return fin;
        }

        public void setFin(int fin) {
            this.fin = fin;
        }

        public int getRecevice() {
            return recevice;
        }

        public void setRecevice(int recevice) {
            this.recevice = recevice;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
