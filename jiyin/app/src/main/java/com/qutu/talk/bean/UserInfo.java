package com.qutu.talk.bean;

public class UserInfo {


    /**
     * code : 1
     * message : 请求成功
     * data : {"id":10010,"wx_openid":null,"wb_openid":null,"qq_openid":null,"headimgurl":"http://qutu.zzmzrj.com/upload/avatar/20190906/285da939e7818a42287305a329a685c1.png","nickname":"用户10010","sex":1,"birthday":"2019-09-06","province":null,"city":"中国","country":"中国","phone":"13569340562","pass":"f284aea44b2d82f7ab6c3f639805d06a","salt":"dd0","mizuan":"0.00","mibi":"0.07","r_mibi":"0.00","mili":"0.00","mlz":"0.00","jinbi":"0.00","name":null,"idno":null,"is_idcard":1,"constellation":"狮子座","is_sign":0,"scale":0,"is_leader":0,"mykeep":null,"token":"f13966a251a9d6a4cb49c9f6ba60de8e","status":1,"locktime":null,"ry_uid":"10010","ry_token":"OpFgxwbC1uBVJT5oz46KimMhaniob5wWctH021OAs7hKmU5pJ+z1FrIHoxB3W2pOw6o1n/QAcjHNGmLykYZRUA==","ali_account":null,"ali_user_id":null,"ali_avatar":null,"ali_nick_name":null,"district":null,"dress_4":null,"dress_5":null,"dress_6":null,"dress_7":null,"login_ip":"117.136.44.151","created_at":"2019-09-06 13:30:06","updated_at":"2019-10-15 18:02:48","cp_card":1,"keys_num":0,"points":0,"is_points_first":0,"system":null,"channel":"weizhi","isOnline":1,"img_1":null,"img_2":null,"img_3":null,"device_token":null}
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
         * id : 10010
         * wx_openid : null
         * wb_openid : null
         * qq_openid : null
         * headimgurl : http://qutu.zzmzrj.com/upload/avatar/20190906/285da939e7818a42287305a329a685c1.png
         * nickname : 用户10010
         * sex : 1
         * birthday : 2019-09-06
         * province : null
         * city : 中国
         * country : 中国
         * phone : 13569340562
         * pass : f284aea44b2d82f7ab6c3f639805d06a
         * salt : dd0
         * mizuan : 0.00
         * mibi : 0.07
         * r_mibi : 0.00
         * mili : 0.00
         * mlz : 0.00
         * jinbi : 0.00
         * name : null
         * idno : null
         * is_idcard : 1
         * constellation : 狮子座
         * is_sign : 0
         * scale : 0
         * is_leader : 0
         * mykeep : null
         * token : f13966a251a9d6a4cb49c9f6ba60de8e
         * status : 1
         * locktime : null
         * ry_uid : 10010
         * ry_token : OpFgxwbC1uBVJT5oz46KimMhaniob5wWctH021OAs7hKmU5pJ+z1FrIHoxB3W2pOw6o1n/QAcjHNGmLykYZRUA==
         * ali_account : null
         * ali_user_id : null
         * ali_avatar : null
         * ali_nick_name : null
         * district : null
         * dress_4 : null
         * dress_5 : null
         * dress_6 : null
         * dress_7 : null
         * login_ip : 117.136.44.151
         * created_at : 2019-09-06 13:30:06
         * updated_at : 2019-10-15 18:02:48
         * cp_card : 1
         * keys_num : 0
         * points : 0
         * is_points_first : 0
         * system : null
         * channel : weizhi
         * isOnline : 1
         * img_1 : null
         * img_2 : null
         * img_3 : null
         * device_token : null
         */

        private int id;
        private Object wx_openid;
        private Object wb_openid;
        private Object qq_openid;
        private String headimgurl;
        private String nickname;
        private int sex;
        private String birthday;
        private Object province;
        private String city;
        private String country;
        private String phone;
        private String pass;
        private String salt;
        private String mizuan;
        private String mibi;
        private String r_mibi;
        private String mili;
        private String mlz;
        private String jinbi;
        private Object name;
        private Object idno;
        private int is_idcard;
        private String constellation;
        private int is_sign;
        private int scale;
        private int is_leader;
        private Object mykeep;
        private String token;
        private int status;
        private Object locktime;
        private String ry_uid;
        private String ry_token;
        private Object ali_account;
        private Object ali_user_id;
        private Object ali_avatar;
        private Object ali_nick_name;
        private Object district;
        private Object dress_4;
        private Object dress_5;
        private Object dress_6;
        private Object dress_7;
        private String login_ip;
        private String created_at;
        private String updated_at;
        private int cp_card;
        private int keys_num;
        private int points;
        private int is_points_first;
        private Object system;
        private String channel;
        private int isOnline;
        private Object img_1;
        private Object img_2;
        private Object img_3;
        private Object device_token;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getWx_openid() {
            return wx_openid;
        }

        public void setWx_openid(Object wx_openid) {
            this.wx_openid = wx_openid;
        }

        public Object getWb_openid() {
            return wb_openid;
        }

        public void setWb_openid(Object wb_openid) {
            this.wb_openid = wb_openid;
        }

        public Object getQq_openid() {
            return qq_openid;
        }

        public void setQq_openid(Object qq_openid) {
            this.qq_openid = qq_openid;
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

        public Object getProvince() {
            return province;
        }

        public void setProvince(Object province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getMizuan() {
            return mizuan;
        }

        public void setMizuan(String mizuan) {
            this.mizuan = mizuan;
        }

        public String getMibi() {
            return mibi;
        }

        public void setMibi(String mibi) {
            this.mibi = mibi;
        }

        public String getR_mibi() {
            return r_mibi;
        }

        public void setR_mibi(String r_mibi) {
            this.r_mibi = r_mibi;
        }

        public String getMili() {
            return mili;
        }

        public void setMili(String mili) {
            this.mili = mili;
        }

        public String getMlz() {
            return mlz;
        }

        public void setMlz(String mlz) {
            this.mlz = mlz;
        }

        public String getJinbi() {
            return jinbi;
        }

        public void setJinbi(String jinbi) {
            this.jinbi = jinbi;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public Object getIdno() {
            return idno;
        }

        public void setIdno(Object idno) {
            this.idno = idno;
        }

        public int getIs_idcard() {
            return is_idcard;
        }

        public void setIs_idcard(int is_idcard) {
            this.is_idcard = is_idcard;
        }

        public String getConstellation() {
            return constellation;
        }

        public void setConstellation(String constellation) {
            this.constellation = constellation;
        }

        public int getIs_sign() {
            return is_sign;
        }

        public void setIs_sign(int is_sign) {
            this.is_sign = is_sign;
        }

        public int getScale() {
            return scale;
        }

        public void setScale(int scale) {
            this.scale = scale;
        }

        public int getIs_leader() {
            return is_leader;
        }

        public void setIs_leader(int is_leader) {
            this.is_leader = is_leader;
        }

        public Object getMykeep() {
            return mykeep;
        }

        public void setMykeep(Object mykeep) {
            this.mykeep = mykeep;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getLocktime() {
            return locktime;
        }

        public void setLocktime(Object locktime) {
            this.locktime = locktime;
        }

        public String getRy_uid() {
            return ry_uid;
        }

        public void setRy_uid(String ry_uid) {
            this.ry_uid = ry_uid;
        }

        public String getRy_token() {
            return ry_token;
        }

        public void setRy_token(String ry_token) {
            this.ry_token = ry_token;
        }

        public Object getAli_account() {
            return ali_account;
        }

        public void setAli_account(Object ali_account) {
            this.ali_account = ali_account;
        }

        public Object getAli_user_id() {
            return ali_user_id;
        }

        public void setAli_user_id(Object ali_user_id) {
            this.ali_user_id = ali_user_id;
        }

        public Object getAli_avatar() {
            return ali_avatar;
        }

        public void setAli_avatar(Object ali_avatar) {
            this.ali_avatar = ali_avatar;
        }

        public Object getAli_nick_name() {
            return ali_nick_name;
        }

        public void setAli_nick_name(Object ali_nick_name) {
            this.ali_nick_name = ali_nick_name;
        }

        public Object getDistrict() {
            return district;
        }

        public void setDistrict(Object district) {
            this.district = district;
        }

        public Object getDress_4() {
            return dress_4;
        }

        public void setDress_4(Object dress_4) {
            this.dress_4 = dress_4;
        }

        public Object getDress_5() {
            return dress_5;
        }

        public void setDress_5(Object dress_5) {
            this.dress_5 = dress_5;
        }

        public Object getDress_6() {
            return dress_6;
        }

        public void setDress_6(Object dress_6) {
            this.dress_6 = dress_6;
        }

        public Object getDress_7() {
            return dress_7;
        }

        public void setDress_7(Object dress_7) {
            this.dress_7 = dress_7;
        }

        public String getLogin_ip() {
            return login_ip;
        }

        public void setLogin_ip(String login_ip) {
            this.login_ip = login_ip;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public int getCp_card() {
            return cp_card;
        }

        public void setCp_card(int cp_card) {
            this.cp_card = cp_card;
        }

        public int getKeys_num() {
            return keys_num;
        }

        public void setKeys_num(int keys_num) {
            this.keys_num = keys_num;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public int getIs_points_first() {
            return is_points_first;
        }

        public void setIs_points_first(int is_points_first) {
            this.is_points_first = is_points_first;
        }

        public Object getSystem() {
            return system;
        }

        public void setSystem(Object system) {
            this.system = system;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public int getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(int isOnline) {
            this.isOnline = isOnline;
        }

        public Object getImg_1() {
            return img_1;
        }

        public void setImg_1(Object img_1) {
            this.img_1 = img_1;
        }

        public Object getImg_2() {
            return img_2;
        }

        public void setImg_2(Object img_2) {
            this.img_2 = img_2;
        }

        public Object getImg_3() {
            return img_3;
        }

        public void setImg_3(Object img_3) {
            this.img_3 = img_3;
        }

        public Object getDevice_token() {
            return device_token;
        }

        public void setDevice_token(Object device_token) {
            this.device_token = device_token;
        }
    }
}
