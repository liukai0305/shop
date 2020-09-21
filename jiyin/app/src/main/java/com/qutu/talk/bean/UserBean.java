package com.qutu.talk.bean;

import java.util.List;

/**
 * 作者:sgm
 * 描述:
 */
public class UserBean {


    /**
     * code : 1
     * message : 请求成功!
     * data : {"id":1151842,"headimgurl":"http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg","nickname":"哈哈","sex":1,"birthday":"1992-05-07","constellation":"金牛座","city":"月球","mizuan":"79905419.00","is_idcard":1,"ry_uid":"test1151842","vip_level":8,"follows_num":13,"fans_num":13}
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
         * id : 1151842
         * headimgurl : http://47.92.85.75/upload//avatar/20190613/25220_155229_4875.jpg
         * nickname : 哈哈
         * sex : 1
         * birthday : 1992-05-07
         * constellation : 金牛座
         * city : 月球
         * mizuan : 79905419.00
         * is_idcard : 1
         * ry_uid : test1151842
         * vip_level : 8
         * follows_num : 13
         * fans_num : 13
         */

        private int id;
        private String headimgurl;
        private String nickname;
        private int sex;
        private String birthday;
        private String constellation;
        private String city;
        private String mizuan;
        private int is_idcard;
        private String ry_uid;
        private int vip_level;
        private int follows_num;
        private int fans_num;
        private String is_god;//是否大神，0 否 1 是
        private int is_newpd; //大神专属是否显示红点 0
        private int is_neworder; //订单是否显示红点 0=不显示 1=显示
        private int is_newpack;//我的背包是否显示红点 0=不显示 1=显示
        private int is_family_show ;// 创建家族按钮是否显示 0=不显示 1=显示
        private String family_id ;//

        public String getFamily_id() {
            return family_id;
        }

        public void setFamily_id(String family_id) {
            this.family_id = family_id;
        }

        public int getIs_newpack() {
            return is_newpack;
        }

        public void setIs_newpack(int is_newpack) {
            this.is_newpack = is_newpack;
        }

        public int getIs_neworder() {
            return is_neworder;
        }

        public void setIs_neworder(int is_neworder) {
            this.is_neworder = is_neworder;
        }

        public int getIs_newpd() {
            return is_newpd;
        }

        public void setIs_newpd(int is_newpd) {
            this.is_newpd = is_newpd;
        }

        private List<ImgList> imglist;

        public String getIs_god() {
            return is_god;
        }

        public void setIs_god(String is_god) {
            this.is_god = is_god;
        }

        public List<ImgList> getImglist() {
            return imglist;
        }

        public void setImglist(List<ImgList> imglist) {
            this.imglist = imglist;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getConstellation() {
            return constellation;
        }

        public void setConstellation(String constellation) {
            this.constellation = constellation;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getMizuan() {
            return mizuan;
        }

        public void setMizuan(String mizuan) {
            this.mizuan = mizuan;
        }

        public int getIs_idcard() {
            return is_idcard;
        }

        public void setIs_idcard(int is_idcard) {
            this.is_idcard = is_idcard;
        }

        public String getRy_uid() {
            return ry_uid;
        }

        public void setRy_uid(String ry_uid) {
            this.ry_uid = ry_uid;
        }

        public int getVip_level() {
            return vip_level;
        }

        public void setVip_level(int vip_level) {
            this.vip_level = vip_level;
        }

        public int getFollows_num() {
            return follows_num;
        }

        public void setFollows_num(int follows_num) {
            this.follows_num = follows_num;
        }

        public int getFans_num() {
            return fans_num;
        }

        public void setFans_num(int fans_num) {
            this.fans_num = fans_num;
        }

        public int getIs_family_show() {
            return is_family_show;
        }

        public void setIs_family_show(int is_family_show) {
            this.is_family_show = is_family_show;
        }

        public static class ImgList {
            private String img;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
