package com.qutu.talk.bean;

import java.util.List;

public class BxRecord {


    /**
     * code : 1
     * message : 请求成功
     * data : [{"id":5078,"wares_id":11,"type":2,"addtime":"03/11 12:52","is_play":1,"num":1,"user_id":724002,"box_type":1,"price":52,"name":"打针","show_img":"http://qutu.zzmzrj.com/upload/gifts/qiqi/9b7a5b9b7fd0983a273f85a7c56b6b5b.png","nickname":"关关要上班","headimgurl":"http://qutu.zzmzrj.com/upload/avatar/20200302/15831540072994.jpg"},{"id":5075,"wares_id":11,"type":2,"addtime":"03/07 12:46","is_play":1,"num":1,"user_id":724001,"box_type":1,"price":52,"name":"打针","show_img":"http://qutu.zzmzrj.com/upload/gifts/qiqi/9b7a5b9b7fd0983a273f85a7c56b6b5b.png","nickname":"aaa","headimgurl":"http://qutu.zzmzrj.com/upload/avatar/20200302/15831390827580.jpg"},{"id":5074,"wares_id":11,"type":2,"addtime":"03/07 12:46","is_play":1,"num":1,"user_id":724001,"box_type":1,"price":52,"name":"打针","show_img":"http://qutu.zzmzrj.com/upload/gifts/qiqi/9b7a5b9b7fd0983a273f85a7c56b6b5b.png","nickname":"aaa","headimgurl":"http://qutu.zzmzrj.com/upload/avatar/20200302/15831390827580.jpg"},{"id":5073,"wares_id":11,"type":2,"addtime":"03/07 12:46","is_play":1,"num":1,"user_id":724001,"box_type":1,"price":52,"name":"打针","show_img":"http://qutu.zzmzrj.com/upload/gifts/qiqi/9b7a5b9b7fd0983a273f85a7c56b6b5b.png","nickname":"aaa","headimgurl":"http://qutu.zzmzrj.com/upload/avatar/20200302/15831390827580.jpg"},{"id":5072,"wares_id":11,"type":2,"addtime":"03/07 12:45","is_play":1,"num":1,"user_id":724001,"box_type":1,"price":52,"name":"打针","show_img":"http://qutu.zzmzrj.com/upload/gifts/qiqi/9b7a5b9b7fd0983a273f85a7c56b6b5b.png","nickname":"aaa","headimgurl":"http://qutu.zzmzrj.com/upload/avatar/20200302/15831390827580.jpg"},{"id":5071,"wares_id":11,"type":2,"addtime":"03/07 12:45","is_play":1,"num":1,"user_id":724001,"box_type":1,"price":52,"name":"打针","show_img":"http://qutu.zzmzrj.com/upload/gifts/qiqi/9b7a5b9b7fd0983a273f85a7c56b6b5b.png","nickname":"aaa","headimgurl":"http://qutu.zzmzrj.com/upload/avatar/20200302/15831390827580.jpg"},{"id":5070,"wares_id":11,"type":2,"addtime":"03/07 12:27","is_play":1,"num":1,"user_id":724001,"box_type":1,"price":52,"name":"打针","show_img":"http://qutu.zzmzrj.com/upload/gifts/qiqi/9b7a5b9b7fd0983a273f85a7c56b6b5b.png","nickname":"aaa","headimgurl":"http://qutu.zzmzrj.com/upload/avatar/20200302/15831390827580.jpg"},{"id":5069,"wares_id":11,"type":2,"addtime":"03/07 12:25","is_play":1,"num":1,"user_id":724001,"box_type":1,"price":52,"name":"打针","show_img":"http://qutu.zzmzrj.com/upload/gifts/qiqi/9b7a5b9b7fd0983a273f85a7c56b6b5b.png","nickname":"aaa","headimgurl":"http://qutu.zzmzrj.com/upload/avatar/20200302/15831390827580.jpg"},{"id":5068,"wares_id":11,"type":2,"addtime":"03/07 12:25","is_play":1,"num":1,"user_id":724001,"box_type":1,"price":52,"name":"打针","show_img":"http://qutu.zzmzrj.com/upload/gifts/qiqi/9b7a5b9b7fd0983a273f85a7c56b6b5b.png","nickname":"aaa","headimgurl":"http://qutu.zzmzrj.com/upload/avatar/20200302/15831390827580.jpg"},{"id":5067,"wares_id":11,"type":2,"addtime":"03/07 12:25","is_play":1,"num":1,"user_id":724001,"box_type":1,"price":52,"name":"打针","show_img":"http://qutu.zzmzrj.com/upload/gifts/qiqi/9b7a5b9b7fd0983a273f85a7c56b6b5b.png","nickname":"aaa","headimgurl":"http://qutu.zzmzrj.com/upload/avatar/20200302/15831390827580.jpg"}]
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
         * id : 5078
         * wares_id : 11
         * type : 2
         * addtime : 03/11 12:52
         * is_play : 1
         * num : 1
         * user_id : 724002
         * box_type : 1
         * price : 52
         * name : 打针
         * show_img : http://qutu.zzmzrj.com/upload/gifts/qiqi/9b7a5b9b7fd0983a273f85a7c56b6b5b.png
         * nickname : 关关要上班
         * headimgurl : http://qutu.zzmzrj.com/upload/avatar/20200302/15831540072994.jpg
         */

        private int id;
        private int wares_id;
        private int type;
        private String addtime;
        private int is_play;
        private int num;
        private int user_id;
        private int box_type;
        private int price;
        private String name;
        private String show_img;
        private String nickname;
        private String headimgurl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getWares_id() {
            return wares_id;
        }

        public void setWares_id(int wares_id) {
            this.wares_id = wares_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public int getIs_play() {
            return is_play;
        }

        public void setIs_play(int is_play) {
            this.is_play = is_play;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getBox_type() {
            return box_type;
        }

        public void setBox_type(int box_type) {
            this.box_type = box_type;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShow_img() {
            return show_img;
        }

        public void setShow_img(String show_img) {
            this.show_img = show_img;
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
    }
}
