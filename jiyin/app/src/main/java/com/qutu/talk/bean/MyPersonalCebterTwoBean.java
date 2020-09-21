package com.qutu.talk.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MyPersonalCebterTwoBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"userInfo":{"id":1100119,"headimgurl":"http://47.92.85.75/upload//avatar/20190823/15665504727568.png","nickname":"王老头","sex":1,"birthday":"2017-11-26","constellation":"射手座","city":"中国","ry_uid":"1100119","age":1,"fabu":6,"follows_num":3,"fans_num":1,"star_level":30,"gold_level":7,"vip_level":7,"hz_level":4,"is_follow":1,"star_img":"http://47.92.85.75/upload/emoji/vip_ico/ml/ml_30.png","gold_img":"http://47.92.85.75/upload/emoji/vip_ico/cf/cf_07.png","vip_img":"http://47.92.85.75/upload//emoji/vip_ico/vip/vip_7.png"},"glory":[{"type":4,"level":4,"name":"徽章4级","img":"http://47.92.85.75/upload/emoji/vip_ico/hz/huizhang_4.png"}],"roomInfo":{},"gifts":[{"giftId":1,"giftName":"么么哒","show_img":"gifts/siqi/xxx.png","sum":"11","img":"http://47.92.85.75/upload/gifts/siqi/xxx.png"},{"giftId":36,"giftName":"小心心","show_img":"gifts/sanqi/gift_xxx.png","sum":"2","img":"http://47.92.85.75/upload/gifts/sanqi/gift_xxx.png"},{"giftId":55,"giftName":"鸦声一片","show_img":"gifts/siqi/ysyp.png","sum":"1","img":"http://47.92.85.75/upload/gifts/siqi/ysyp.png"},{"giftId":54,"giftName":"女神口红","show_img":"gifts/siqi/nskh.png","sum":"5","img":"http://47.92.85.75/upload/gifts/siqi/nskh.png"},{"giftId":37,"giftName":"爱的抱抱","show_img":"gifts/sanqi/gift_adbb.png","sum":"1","img":"http://47.92.85.75/upload/gifts/sanqi/gift_adbb.png"},{"giftId":39,"giftName":"泡泡枪","show_img":"gifts/sanqi/gift_ppq.png","sum":"1","img":"http://47.92.85.75/upload/gifts/sanqi/gift_ppq.png"},{"giftId":70,"giftName":"来瓶香槟","show_img":"gifts/siqi/lpxb.png","sum":"1","img":"http://47.92.85.75/upload/gifts/siqi/lpxb.png"},{"giftId":76,"giftName":"梦幻城堡","show_img":"gifts/siqi/mhcb.png","sum":"188","img":"http://47.92.85.75/upload/gifts/siqi/mhcb.png"}],"cplist":[{"cp_type":2,"days":"暂无CP"},{"cp_type":2,"days":"暂无CP"},{"cp_type":2,"days":"暂无CP"}],"imglist":[]}
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
         * userInfo : {"id":1100119,"headimgurl":"http://47.92.85.75/upload//avatar/20190823/15665504727568.png","nickname":"王老头","sex":1,"birthday":"2017-11-26","constellation":"射手座","city":"中国","ry_uid":"1100119","age":1,"fabu":6,"follows_num":3,"fans_num":1,"star_level":30,"gold_level":7,"vip_level":7,"hz_level":4,"is_follow":1,"star_img":"http://47.92.85.75/upload/emoji/vip_ico/ml/ml_30.png","gold_img":"http://47.92.85.75/upload/emoji/vip_ico/cf/cf_07.png","vip_img":"http://47.92.85.75/upload//emoji/vip_ico/vip/vip_7.png"}
         * glory : [{"type":4,"level":4,"name":"徽章4级","img":"http://47.92.85.75/upload/emoji/vip_ico/hz/huizhang_4.png"}]
         * roomInfo : {}
         * gifts : [{"giftId":1,"giftName":"么么哒","show_img":"gifts/siqi/xxx.png","sum":"11","img":"http://47.92.85.75/upload/gifts/siqi/xxx.png"},{"giftId":36,"giftName":"小心心","show_img":"gifts/sanqi/gift_xxx.png","sum":"2","img":"http://47.92.85.75/upload/gifts/sanqi/gift_xxx.png"},{"giftId":55,"giftName":"鸦声一片","show_img":"gifts/siqi/ysyp.png","sum":"1","img":"http://47.92.85.75/upload/gifts/siqi/ysyp.png"},{"giftId":54,"giftName":"女神口红","show_img":"gifts/siqi/nskh.png","sum":"5","img":"http://47.92.85.75/upload/gifts/siqi/nskh.png"},{"giftId":37,"giftName":"爱的抱抱","show_img":"gifts/sanqi/gift_adbb.png","sum":"1","img":"http://47.92.85.75/upload/gifts/sanqi/gift_adbb.png"},{"giftId":39,"giftName":"泡泡枪","show_img":"gifts/sanqi/gift_ppq.png","sum":"1","img":"http://47.92.85.75/upload/gifts/sanqi/gift_ppq.png"},{"giftId":70,"giftName":"来瓶香槟","show_img":"gifts/siqi/lpxb.png","sum":"1","img":"http://47.92.85.75/upload/gifts/siqi/lpxb.png"},{"giftId":76,"giftName":"梦幻城堡","show_img":"gifts/siqi/mhcb.png","sum":"188","img":"http://47.92.85.75/upload/gifts/siqi/mhcb.png"}]
         * cplist : [{"cp_type":2,"days":"暂无CP"},{"cp_type":2,"days":"暂无CP"},{"cp_type":2,"days":"暂无CP"}]
         * imglist : []
         */

        private UserInfoBean userInfo;
        private RoomInfoBean roomInfo;
        private List<GloryBean> glory;
        private List<GiftsBean> gifts;
        private List<CplistBean> cplist;
        private List<ImgList> imglist;
        private List<SkilllistBean> skilllist;

        public List<SkilllistBean> getSkilllist() {
            return skilllist;
        }

        public void setSkilllist(List<SkilllistBean> skilllist) {
            this.skilllist = skilllist;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public RoomInfoBean getRoomInfo() {
            return roomInfo;
        }

        public void setRoomInfo(RoomInfoBean roomInfo) {
            this.roomInfo = roomInfo;
        }

        public List<GloryBean> getGlory() {
            return glory;
        }

        public void setGlory(List<GloryBean> glory) {
            this.glory = glory;
        }

        public List<GiftsBean> getGifts() {
            return gifts;
        }

        public void setGifts(List<GiftsBean> gifts) {
            this.gifts = gifts;
        }

        public List<CplistBean> getCplist() {
            return cplist;
        }

        public void setCplist(List<CplistBean> cplist) {
            this.cplist = cplist;
        }

        public List<ImgList> getImglist() {
            return imglist;
        }

        public void setImglist(List<ImgList> imglist) {
            this.imglist = imglist;
        }

        public static class UserInfoBean implements Parcelable {
            /**
             * id : 1100119
             * headimgurl : http://47.92.85.75/upload//avatar/20190823/15665504727568.png
             * nickname : 王老头
             * sex : 1
             * birthday : 2017-11-26
             * constellation : 射手座
             * city : 中国
             * ry_uid : 1100119
             * age : 1
             * fabu : 6
             * follows_num : 3
             * fans_num : 1
             * star_level : 30
             * gold_level : 7
             * vip_level : 7
             * hz_level : 4
             * is_follow : 1
             * star_img : http://47.92.85.75/upload/emoji/vip_ico/ml/ml_30.png
             * gold_img : http://47.92.85.75/upload/emoji/vip_ico/cf/cf_07.png
             * vip_img : http://47.92.85.75/upload//emoji/vip_ico/vip/vip_7.png
             */

            private String star_img;
            private String gold_img;
            private String vip_img;
            private int id;
            private String headimgurl;
            private String nickname;
            private int sex;
            private String birthday;
            private String constellation;
            private String city;
            private String ry_uid;
            private String age;
            private int fabu;
            private int follows_num;
            private int fans_num;
            private int star_level;
            private int gold_level;
            private int vip_level;
            private int hz_level;
            private int is_follow;

            protected UserInfoBean(Parcel in) {
                this.star_img = in.readString();
                this.gold_img = in.readString();
                this.vip_img = in.readString();
                this.id = in.readInt();
                this.headimgurl = in.readString();
                this.nickname = in.readString();
                this.sex = in.readInt();
                this.birthday = in.readString();
                this.constellation = in.readString();
                this.city = in.readString();
                this.ry_uid = in.readString();
                this.age = in.readString();
                this.fabu = in.readInt();
                this.follows_num = in.readInt();
                this.fans_num = in.readInt();
                this.star_level = in.readInt();
                this.gold_level = in.readInt();
                this.vip_level = in.readInt();
                this.hz_level = in.readInt();
                this.is_follow = in.readInt();
            }

            public static final Creator<MyPersonalCebterTwoBean.DataBean.UserInfoBean> CREATOR = new Creator<MyPersonalCebterTwoBean.DataBean.UserInfoBean>() {
                @Override
                public MyPersonalCebterTwoBean.DataBean.UserInfoBean createFromParcel(Parcel in) {
                    return new MyPersonalCebterTwoBean.DataBean.UserInfoBean(in);
                }

                @Override
                public MyPersonalCebterTwoBean.DataBean.UserInfoBean[] newArray(int size) {
                    return new MyPersonalCebterTwoBean.DataBean.UserInfoBean[size];
                }
            };

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

            public String getRy_uid() {
                return ry_uid;
            }

            public void setRy_uid(String ry_uid) {
                this.ry_uid = ry_uid;
            }

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public int getFabu() {
                return fabu;
            }

            public void setFabu(int fabu) {
                this.fabu = fabu;
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

            public int getStar_level() {
                return star_level;
            }

            public void setStar_level(int star_level) {
                this.star_level = star_level;
            }

            public int getGold_level() {
                return gold_level;
            }

            public void setGold_level(int gold_level) {
                this.gold_level = gold_level;
            }

            public int getVip_level() {
                return vip_level;
            }

            public void setVip_level(int vip_level) {
                this.vip_level = vip_level;
            }

            public int getHz_level() {
                return hz_level;
            }

            public void setHz_level(int hz_level) {
                this.hz_level = hz_level;
            }

            public int getIs_follow() {
                return is_follow;
            }

            public void setIs_follow(int is_follow) {
                this.is_follow = is_follow;
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

            public String getStar_img() {
                return star_img;
            }

            public void setStar_img(String star_img) {
                this.star_img = star_img;
            }

            public String getGold_img() {
                return gold_img;
            }

            public void setGold_img(String gold_img) {
                this.gold_img = gold_img;
            }

            public String getVip_img() {
                return vip_img;
            }

            public void setVip_img(String vip_img) {
                this.vip_img = vip_img;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.star_img);
                dest.writeString(this.gold_img);
                dest.writeString(this.vip_img);
                dest.writeInt(this.id);
                dest.writeString(this.headimgurl);
                dest.writeString(this.nickname);
                dest.writeInt(this.sex);
                dest.writeString(this.birthday);
                dest.writeString(this.constellation);
                dest.writeString(this.city);
                dest.writeString(this.ry_uid);
                dest.writeString(this.age);
                dest.writeInt(this.fabu);
                dest.writeInt(this.follows_num);
                dest.writeInt(this.fans_num);
                dest.writeInt(this.star_level);
                dest.writeInt(this.gold_level);
                dest.writeInt(this.vip_level);
                dest.writeInt(this.hz_level);
                dest.writeInt(this.is_follow);
            }
        }

        public static class RoomInfoBean {
            /**
             * uid : 113114
             * room_name : 我男神李现
             * hot : 9999
             * room_cover : http://47.92.85.75/upload/cover/cover/6.jpg
             */

            private int uid;
            private String room_name;
            private String hot;
            private String room_cover;

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

            public String getHot() {
                return hot;
            }

            public void setHot(String hot) {
                this.hot = hot;
            }

            public String getRoom_cover() {
                return room_cover;
            }

            public void setRoom_cover(String room_cover) {
                this.room_cover = room_cover;
            }
        }

        public static class GloryBean implements Parcelable {
            /**
             * type : 4
             * level : 4
             * name : 徽章4级
             * img : http://47.92.85.75/upload/emoji/vip_ico/hz/huizhang_4.png
             */

            private int type;
            private int level;
            private String name;
            private String img;

            protected GloryBean(Parcel in) {
                this.type = in.readInt();
                this.level = in.readInt();
                this.name = in.readString();
                this.img = in.readString();
            }

            public static final Creator<MyPersonalCebterTwoBean.DataBean.GloryBean> CREATOR = new Creator<MyPersonalCebterTwoBean.DataBean.GloryBean>() {
                @Override
                public MyPersonalCebterTwoBean.DataBean.GloryBean createFromParcel(Parcel in) {
                    return new MyPersonalCebterTwoBean.DataBean.GloryBean(in);
                }

                @Override
                public MyPersonalCebterTwoBean.DataBean.GloryBean[] newArray(int size) {
                    return new MyPersonalCebterTwoBean.DataBean.GloryBean[size];
                }
            };

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.type);
                dest.writeInt(this.level);
                dest.writeString(this.name);
                dest.writeString(this.img);
            }
        }

        public static class GiftsBean implements Parcelable {
            /**
             * giftId : 1
             * giftName : 么么哒
             * show_img : gifts/siqi/xxx.png
             * sum : 11
             * img : http://47.92.85.75/upload/gifts/siqi/xxx.png
             */

            private int giftId;
            private String giftName;
            private String show_img;
            private String sum;
            private String img;

            public int getGiftId() {
                return giftId;
            }

            public void setGiftId(int giftId) {
                this.giftId = giftId;
            }

            public String getGiftName() {
                return giftName;
            }

            public void setGiftName(String giftName) {
                this.giftName = giftName;
            }

            public String getShow_img() {
                return show_img;
            }

            public void setShow_img(String show_img) {
                this.show_img = show_img;
            }

            public String getSum() {
                return sum;
            }

            public void setSum(String sum) {
                this.sum = sum;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            protected GiftsBean(Parcel in) {
                this.giftId = in.readInt();
                this.giftName = in.readString();
                this.img = in.readString();
                this.sum = in.readString();
            }

            public static final Parcelable.Creator<MyPersonalCebterBean.DataBean.GiftsBean> CREATOR = new Parcelable.Creator<MyPersonalCebterBean.DataBean.GiftsBean>() {
                @Override
                public MyPersonalCebterBean.DataBean.GiftsBean createFromParcel(Parcel in) {
                    return new MyPersonalCebterBean.DataBean.GiftsBean(in);
                }

                @Override
                public MyPersonalCebterBean.DataBean.GiftsBean[] newArray(int size) {
                    return new MyPersonalCebterBean.DataBean.GiftsBean[size];
                }
            };

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.giftId);
                dest.writeString(this.giftName);
                dest.writeString(this.img);
                dest.writeString(this.sum);
            }
        }

        public static class CplistBean implements Parcelable {
            /**
             * cp_type : 2
             * days : 暂无CP
             */

            private int cp_type;
            private String days;
            private int id;
            private int wares_id;
            private int user_id;
            private int fromUid;
            private int agreetime;
            private int cp_level;
            private String user_nick;
            private String user_head;
            private String from_nick;
            private String from_head;
            private String bs_img;

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

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getFromUid() {
                return fromUid;
            }

            public void setFromUid(int fromUid) {
                this.fromUid = fromUid;
            }

            public int getAgreetime() {
                return agreetime;
            }

            public void setAgreetime(int agreetime) {
                this.agreetime = agreetime;
            }

            public int getCp_level() {
                return cp_level;
            }

            public void setCp_level(int cp_level) {
                this.cp_level = cp_level;
            }

            public String getUser_nick() {
                return user_nick;
            }

            public void setUser_nick(String user_nick) {
                this.user_nick = user_nick;
            }

            public String getUser_head() {
                return user_head;
            }

            public void setUser_head(String user_head) {
                this.user_head = user_head;
            }

            public String getFrom_nick() {
                return from_nick;
            }

            public void setFrom_nick(String from_nick) {
                this.from_nick = from_nick;
            }

            public String getFrom_head() {
                return from_head;
            }

            public void setFrom_head(String from_head) {
                this.from_head = from_head;
            }

            public String getBs_img() {
                return bs_img;
            }

            public void setBs_img(String bs_img) {
                this.bs_img = bs_img;
            }

            public int getCp_type() {
                return cp_type;
            }

            public void setCp_type(int cp_type) {
                this.cp_type = cp_type;
            }

            public String getDays() {
                return days;
            }

            public void setDays(String days) {
                this.days = days;
            }

            protected CplistBean(Parcel in) {
                this.id = in.readInt();
                this.wares_id = in.readInt();
                this.user_id = in.readInt();
                this.fromUid = in.readInt();
                this.agreetime = in.readInt();
                this.cp_level = in.readInt();
                this.user_nick = in.readString();
                this.user_head = in.readString();
                this.from_nick = in.readString();
                this.from_head = in.readString();
                this.bs_img = in.readString();
                this.days = in.readString();
                this.cp_type = in.readInt();
            }

            public static final Parcelable.Creator<MyPersonalCebterBean.DataBean.CplistBean> CREATOR = new Parcelable.Creator<MyPersonalCebterBean.DataBean.CplistBean>() {
                @Override
                public MyPersonalCebterBean.DataBean.CplistBean createFromParcel(Parcel in) {
                    return new MyPersonalCebterBean.DataBean.CplistBean(in);
                }

                @Override
                public MyPersonalCebterBean.DataBean.CplistBean[] newArray(int size) {
                    return new MyPersonalCebterBean.DataBean.CplistBean[size];
                }
            };

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeInt(this.wares_id);
                dest.writeInt(this.user_id);
                dest.writeInt(this.fromUid);
                dest.writeInt(this.agreetime);
                dest.writeInt(this.cp_level);
                dest.writeString(this.user_nick);
                dest.writeString(this.user_head);
                dest.writeString(this.from_nick);
                dest.writeString(this.from_head);
                dest.writeString(this.bs_img);
                dest.writeString(this.days);
                dest.writeInt(this.cp_type);
            }
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

        public static class SkilllistBean implements Parcelable {

//                "headimgurl":"http://tp5_test.miniyuyin.cn/upload/avatar/20191021/15716509366195.jpg",
//                "audio":"http://tp5_test.miniyuyin.cn/upload/audio/20191007/c3a85ef6ed29d3d7ec6b6635af472a1a.mp3",
//                "img_1":"",
//                "user_id":1100002,
//                "nickname":"呼噜娃12",
//                "skill_id":1,
//                "isOnline":0,
//                "num":1,
//                "score":"5.0",
//                 "price":5,
//                 "unit":"局",
//                 "skill_name":"王者荣耀",
//                 "level_name":"倔强青铜"

            private String id;
            private String headimgurl;
            private String audio;
            private String img_1;
            private int user_id;
            private String nickname;
            private String skill_id;
            private int isOnline;
            private int num;
            private String score;
            private String price;
            private String unit;
            private String skill_name;
            private String level_name;
            private String img_2;

            protected SkilllistBean(Parcel in) {
                this.headimgurl = in.readString();
                this.audio = in.readString();
                this.img_1 = in.readString();
                this.user_id = in.readInt();
                this.nickname = in.readString();
                this.skill_id = in.readString();
                this.isOnline = in.readInt();
                this.num = in.readInt();
                this.score = in.readString();
                this.price = in.readString();
                this.unit = in.readString();
                this.skill_name = in.readString();
                this.level_name = in.readString();
                this.img_2 = in.readString();
                this.id = in.readString();
            }

            public static final Creator<MyPersonalCebterTwoBean.DataBean.SkilllistBean> CREATOR = new Creator<MyPersonalCebterTwoBean.DataBean.SkilllistBean>() {
                @Override
                public MyPersonalCebterTwoBean.DataBean.SkilllistBean createFromParcel(Parcel in) {
                    return new MyPersonalCebterTwoBean.DataBean.SkilllistBean(in);
                }

                @Override
                public MyPersonalCebterTwoBean.DataBean.SkilllistBean[] newArray(int size) {
                    return new MyPersonalCebterTwoBean.DataBean.SkilllistBean[size];
                }
            };

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getSkill_id() {
                return skill_id;
            }

            public void setSkill_id(String skill_id) {
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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
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

            public String getImg_2() {
                return img_2;
            }

            public void setImg_2(String img_2) {
                this.img_2 = img_2;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.headimgurl);
                dest.writeString(this.audio);
                dest.writeString(this.img_1);
                dest.writeInt(this.user_id);
                dest.writeString(this.nickname);
                dest.writeString(this.skill_id);
                dest.writeInt(this.isOnline);
                dest.writeInt(this.num);
                dest.writeString(this.score);
                dest.writeString(this.price);
                dest.writeString(this.unit);
                dest.writeString(this.skill_name);
                dest.writeString(this.level_name);
                dest.writeString(this.img_2);
                dest.writeString(this.id);
            }
        }
    }
}
