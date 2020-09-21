package com.qutu.talk.bean;

import java.util.List;

public class RoomInfoBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"id":14,"numid":"1103","uid":1103,"room_status":"1","room_name":"萌萌吒😈😈","room_cover":"http://tp5_test.miniyuyin.cn/upload/cover/20190926/15694874111657.png","room_intro":"欢迎来到我的房间玩~","room_pass":"","room_class":2,"room_type":1,"room_welcome":"请大家遵守房间绿色聊天公告,开心聊天,不要违规哦~","roomAdmin":null,"roomVisitor":"","roomSpeak":"","roomSound":null,"roomBlack":"1100386#1568283947,1101416#1568708074,1100449#1568708078,1101196#1568708119,1100537#1568708123,1100546#1568708126,1100859#1568708129,1100999#1568708131","week_star":2,"ranking":0,"is_popular":2,"secret_chat":2,"is_top":2,"sort":0,"room_background":13,"microphone":"0,0,0,0,0,0,0,0","super_uid":2,"is_afk":0,"hot":1000,"updated_at":"2019-09-27 14:48:21","created_at":"2019-08-16 15:25:56","roomJudge":null,"is_prohibit_sound":"0,0,0,0,0,0,0,0","openid":null,"commission_proportion":null,"freshTime":null,"start_hour":null,"end_hour":null,"back_img":"http://47.92.85.75/upload/background/0031.png","rooms_cate":[{"id":1,"pid":0,"name":"娱乐","is_check":0,"children":[{"id":4,"pid":1,"name":"交友","is_check":0},{"id":5,"pid":1,"name":"男神","is_check":0},{"id":6,"pid":1,"name":"女神","is_check":0},{"id":7,"pid":1,"name":"唱歌","is_check":0},{"id":8,"pid":1,"name":"电台","is_check":0},{"id":9,"pid":1,"name":"开黑","is_check":0},{"id":10,"pid":1,"name":"脱口秀","is_check":0}]},{"id":2,"pid":0,"name":"语圈","is_check":1,"children":[{"id":11,"pid":2,"name":"英语","is_check":0},{"id":12,"pid":2,"name":"韩语","is_check":0},{"id":13,"pid":2,"name":"法语","is_check":0},{"id":14,"pid":2,"name":"方言","is_check":0},{"id":15,"pid":2,"name":"日语","is_check":0}]},{"id":3,"pid":0,"name":"文坛","is_check":0,"children":[{"id":16,"pid":3,"name":"原创","is_check":0},{"id":17,"pid":3,"name":"读文","is_check":0},{"id":18,"pid":3,"name":"故事","is_check":0},{"id":19,"pid":3,"name":"剧本","is_check":0},{"id":20,"pid":3,"name":"历史","is_check":0}]}],"backgrounds":[{"id":11,"img":"http://47.92.85.75/upload/background/001.png","enable":1,"updated_at":"2019-08-16 14:14:17","created_at":"2019-06-10 14:01:16","is_check":0},{"id":12,"img":"http://47.92.85.75/upload/background/0021.png","enable":1,"updated_at":"2019-08-16 17:45:03","created_at":"2019-06-10 14:01:16","is_check":0},{"id":13,"img":"http://47.92.85.75/upload/background/0031.png","enable":1,"updated_at":"2019-08-16 17:45:17","created_at":"2019-06-10 14:01:16","is_check":1}]}]
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
         * id : 14
         * numid : 1103
         * uid : 1103
         * room_status : 1
         * room_name : 萌萌吒😈😈
         * room_cover : http://tp5_test.miniyuyin.cn/upload/cover/20190926/15694874111657.png
         * room_intro : 欢迎来到我的房间玩~
         * room_pass :
         * room_class : 2
         * room_type : 1
         * room_welcome : 请大家遵守房间绿色聊天公告,开心聊天,不要违规哦~
         * roomAdmin : null
         * roomVisitor :
         * roomSpeak :
         * roomSound : null
         * roomBlack : 1100386#1568283947,1101416#1568708074,1100449#1568708078,1101196#1568708119,1100537#1568708123,1100546#1568708126,1100859#1568708129,1100999#1568708131
         * week_star : 2
         * ranking : 0
         * is_popular : 2
         * secret_chat : 2
         * is_top : 2
         * sort : 0
         * room_background : 13
         * microphone : 0,0,0,0,0,0,0,0
         * super_uid : 2
         * is_afk : 0
         * hot : 1000
         * updated_at : 2019-09-27 14:48:21
         * created_at : 2019-08-16 15:25:56
         * roomJudge : null
         * is_prohibit_sound : 0,0,0,0,0,0,0,0
         * openid : null
         * commission_proportion : null
         * freshTime : null
         * start_hour : null
         * end_hour : null
         * back_img : http://47.92.85.75/upload/background/0031.png
         * rooms_cate : [{"id":1,"pid":0,"name":"娱乐","is_check":0,"children":[{"id":4,"pid":1,"name":"交友","is_check":0},{"id":5,"pid":1,"name":"男神","is_check":0},{"id":6,"pid":1,"name":"女神","is_check":0},{"id":7,"pid":1,"name":"唱歌","is_check":0},{"id":8,"pid":1,"name":"电台","is_check":0},{"id":9,"pid":1,"name":"开黑","is_check":0},{"id":10,"pid":1,"name":"脱口秀","is_check":0}]},{"id":2,"pid":0,"name":"语圈","is_check":1,"children":[{"id":11,"pid":2,"name":"英语","is_check":0},{"id":12,"pid":2,"name":"韩语","is_check":0},{"id":13,"pid":2,"name":"法语","is_check":0},{"id":14,"pid":2,"name":"方言","is_check":0},{"id":15,"pid":2,"name":"日语","is_check":0}]},{"id":3,"pid":0,"name":"文坛","is_check":0,"children":[{"id":16,"pid":3,"name":"原创","is_check":0},{"id":17,"pid":3,"name":"读文","is_check":0},{"id":18,"pid":3,"name":"故事","is_check":0},{"id":19,"pid":3,"name":"剧本","is_check":0},{"id":20,"pid":3,"name":"历史","is_check":0}]}]
         * backgrounds : [{"id":11,"img":"http://47.92.85.75/upload/background/001.png","enable":1,"updated_at":"2019-08-16 14:14:17","created_at":"2019-06-10 14:01:16","is_check":0},{"id":12,"img":"http://47.92.85.75/upload/background/0021.png","enable":1,"updated_at":"2019-08-16 17:45:03","created_at":"2019-06-10 14:01:16","is_check":0},{"id":13,"img":"http://47.92.85.75/upload/background/0031.png","enable":1,"updated_at":"2019-08-16 17:45:17","created_at":"2019-06-10 14:01:16","is_check":1}]
         */

        private int id;
        private String numid;
        private int uid;
        private String room_status;
        private String room_name;
        private String room_cover;
        private String room_intro;
        private String room_pass;
        private String room_class;
        private String room_type;
        private String room_welcome;
        private Object roomAdmin;
        private String roomVisitor;
        private String roomSpeak;
        private Object roomSound;
        private String roomBlack;
        private int week_star;
        private int ranking;
        private int is_popular;
        private int secret_chat;
        private int is_top;
        private int sort;
        private int room_background;
        private String microphone;
        private int super_uid;
        private int is_afk;
        private int hot;
        private String updated_at;
        private String created_at;
        private Object roomJudge;
        private String is_prohibit_sound;
        private Object openid;
        private Object commission_proportion;
        private Object freshTime;
        private Object start_hour;
        private Object end_hour;
        private String back_img;
        private String free_mic;

        public String getFree_mic() {
            return free_mic;
        }

        public void setFree_mic(String free_mic) {
            this.free_mic = free_mic;
        }

        private List<RoomsCateBean> rooms_cate;
        private List<BackgroundsBean> backgrounds;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNumid() {
            return numid;
        }

        public void setNumid(String numid) {
            this.numid = numid;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getRoom_status() {
            return room_status;
        }

        public void setRoom_status(String room_status) {
            this.room_status = room_status;
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

        public String getRoom_intro() {
            return room_intro;
        }

        public void setRoom_intro(String room_intro) {
            this.room_intro = room_intro;
        }

        public String getRoom_pass() {
            return room_pass;
        }

        public void setRoom_pass(String room_pass) {
            this.room_pass = room_pass;
        }

        public String getRoom_class() {
            return room_class;
        }

        public void setRoom_class(String room_class) {
            this.room_class = room_class;
        }

        public String getRoom_type() {
            return room_type;
        }

        public void setRoom_type(String room_type) {
            this.room_type = room_type;
        }

        public String getRoom_welcome() {
            return room_welcome;
        }

        public void setRoom_welcome(String room_welcome) {
            this.room_welcome = room_welcome;
        }

        public Object getRoomAdmin() {
            return roomAdmin;
        }

        public void setRoomAdmin(Object roomAdmin) {
            this.roomAdmin = roomAdmin;
        }

        public String getRoomVisitor() {
            return roomVisitor;
        }

        public void setRoomVisitor(String roomVisitor) {
            this.roomVisitor = roomVisitor;
        }

        public String getRoomSpeak() {
            return roomSpeak;
        }

        public void setRoomSpeak(String roomSpeak) {
            this.roomSpeak = roomSpeak;
        }

        public Object getRoomSound() {
            return roomSound;
        }

        public void setRoomSound(Object roomSound) {
            this.roomSound = roomSound;
        }

        public String getRoomBlack() {
            return roomBlack;
        }

        public void setRoomBlack(String roomBlack) {
            this.roomBlack = roomBlack;
        }

        public int getWeek_star() {
            return week_star;
        }

        public void setWeek_star(int week_star) {
            this.week_star = week_star;
        }

        public int getRanking() {
            return ranking;
        }

        public void setRanking(int ranking) {
            this.ranking = ranking;
        }

        public int getIs_popular() {
            return is_popular;
        }

        public void setIs_popular(int is_popular) {
            this.is_popular = is_popular;
        }

        public int getSecret_chat() {
            return secret_chat;
        }

        public void setSecret_chat(int secret_chat) {
            this.secret_chat = secret_chat;
        }

        public int getIs_top() {
            return is_top;
        }

        public void setIs_top(int is_top) {
            this.is_top = is_top;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getRoom_background() {
            return room_background;
        }

        public void setRoom_background(int room_background) {
            this.room_background = room_background;
        }

        public String getMicrophone() {
            return microphone;
        }

        public void setMicrophone(String microphone) {
            this.microphone = microphone;
        }

        public int getSuper_uid() {
            return super_uid;
        }

        public void setSuper_uid(int super_uid) {
            this.super_uid = super_uid;
        }

        public int getIs_afk() {
            return is_afk;
        }

        public void setIs_afk(int is_afk) {
            this.is_afk = is_afk;
        }

        public int getHot() {
            return hot;
        }

        public void setHot(int hot) {
            this.hot = hot;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public Object getRoomJudge() {
            return roomJudge;
        }

        public void setRoomJudge(Object roomJudge) {
            this.roomJudge = roomJudge;
        }

        public String getIs_prohibit_sound() {
            return is_prohibit_sound;
        }

        public void setIs_prohibit_sound(String is_prohibit_sound) {
            this.is_prohibit_sound = is_prohibit_sound;
        }

        public Object getOpenid() {
            return openid;
        }

        public void setOpenid(Object openid) {
            this.openid = openid;
        }

        public Object getCommission_proportion() {
            return commission_proportion;
        }

        public void setCommission_proportion(Object commission_proportion) {
            this.commission_proportion = commission_proportion;
        }

        public Object getFreshTime() {
            return freshTime;
        }

        public void setFreshTime(Object freshTime) {
            this.freshTime = freshTime;
        }

        public Object getStart_hour() {
            return start_hour;
        }

        public void setStart_hour(Object start_hour) {
            this.start_hour = start_hour;
        }

        public Object getEnd_hour() {
            return end_hour;
        }

        public void setEnd_hour(Object end_hour) {
            this.end_hour = end_hour;
        }

        public String getBack_img() {
            return back_img;
        }

        public void setBack_img(String back_img) {
            this.back_img = back_img;
        }

        public List<RoomsCateBean> getRooms_cate() {
            return rooms_cate;
        }

        public void setRooms_cate(List<RoomsCateBean> rooms_cate) {
            this.rooms_cate = rooms_cate;
        }

        public List<BackgroundsBean> getBackgrounds() {
            return backgrounds;
        }

        public void setBackgrounds(List<BackgroundsBean> backgrounds) {
            this.backgrounds = backgrounds;
        }

        public static class RoomsCateBean {
            /**
             * id : 1
             * pid : 0
             * name : 娱乐
             * is_check : 0
             * children : [{"id":4,"pid":1,"name":"交友","is_check":0},{"id":5,"pid":1,"name":"男神","is_check":0},{"id":6,"pid":1,"name":"女神","is_check":0},{"id":7,"pid":1,"name":"唱歌","is_check":0},{"id":8,"pid":1,"name":"电台","is_check":0},{"id":9,"pid":1,"name":"开黑","is_check":0},{"id":10,"pid":1,"name":"脱口秀","is_check":0}]
             */

            private int id;
            private int pid;
            private String name;
            private int is_check;
            private List<ChildrenBean> children;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getIs_check() {
                return is_check;
            }

            public void setIs_check(int is_check) {
                this.is_check = is_check;
            }

            public List<ChildrenBean> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBean> children) {
                this.children = children;
            }

            public static class ChildrenBean {
                /**
                 * id : 4
                 * pid : 1
                 * name : 交友
                 * is_check : 0
                 */

                private int id;
                private int pid;
                private String name;
                private int is_check;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getIs_check() {
                    return is_check;
                }

                public void setIs_check(int is_check) {
                    this.is_check = is_check;
                }
            }
        }

        public static class BackgroundsBean {
            /**
             * id : 11
             * img : http://47.92.85.75/upload/background/001.png
             * enable : 1
             * updated_at : 2019-08-16 14:14:17
             * created_at : 2019-06-10 14:01:16
             * is_check : 0
             */

            private int id;
            private String img;
            private int enable;
            private String updated_at;
            private String created_at;
            private int is_check;
            public boolean isSlect;

            public boolean isSlect() {
                return isSlect;
            }

            public void setSlect(boolean slect) {
                isSlect = slect;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getEnable() {
                return enable;
            }

            public void setEnable(int enable) {
                this.enable = enable;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public int getIs_check() {
                return is_check;
            }

            public void setIs_check(int is_check) {
                this.is_check = is_check;
            }
        }
    }
}
