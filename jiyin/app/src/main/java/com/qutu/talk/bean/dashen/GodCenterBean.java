package com.qutu.talk.bean.dashen;

import java.util.List;

public class GodCenterBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"id":"16","image":"","headimgurl":"http://tp5_test.miniyuyin.cn/upload/avatar/20191024/15718942292819.png","nickname":"123456","isOnline":0,"num":0,"score":1.2,"jd_date":"周一,周二","areas":"","positions":"","audio":"","introduce":"444","total":2,"comments":[{"id":16,"addtime":"11-14 15:17","content":"真的很棒,跟大神献上膝盖","star":5,"user_id":1103,"headimgurl":"http://tp5_test.miniyuyin.cn/upload/avatar/20191106/15730210975043.png","nickname":"小丸子"},{"id":15,"addtime":"11-14 14:16","content":"总体不错哦","star":1,"user_id":1103,"headimgurl":"http://tp5_test.miniyuyin.cn/upload/avatar/20191106/15730210975043.png","nickname":"小丸子"}]}
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
         * id : 16
         * image :
         * headimgurl : http://tp5_test.miniyuyin.cn/upload/avatar/20191024/15718942292819.png
         * nickname : 123456
         * isOnline : 0
         * num : 0
         * score : 1.2
         * jd_date : 周一,周二
         * areas :
         * positions :
         * audio :
         * introduce : 444
         * total : 2
         * comments : [{"id":16,"addtime":"11-14 15:17","content":"真的很棒,跟大神献上膝盖","star":5,"user_id":1103,"headimgurl":"http://tp5_test.miniyuyin.cn/upload/avatar/20191106/15730210975043.png","nickname":"小丸子"},{"id":15,"addtime":"11-14 14:16","content":"总体不错哦","star":1,"user_id":1103,"headimgurl":"http://tp5_test.miniyuyin.cn/upload/avatar/20191106/15730210975043.png","nickname":"小丸子"}]
         */

        private String id;
        private String image;
        private String headimgurl;
        private String nickname;
        private int isOnline;
        private String num;
        private String score;
        private String jd_date;
        private String areas;
        private String positions;
        private String audio;
        private String introduce;
        private String total;
        private String audio_time;
        private int is_follow;
        private String god_id;
        private List<CommentsBean> comments;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

        public int getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(int isOnline) {
            this.isOnline = isOnline;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getJd_date() {
            return jd_date;
        }

        public void setJd_date(String jd_date) {
            this.jd_date = jd_date;
        }

        public String getAreas() {
            return areas;
        }

        public void setAreas(String areas) {
            this.areas = areas;
        }

        public String getPositions() {
            return positions;
        }

        public void setPositions(String positions) {
            this.positions = positions;
        }

        public String getAudio() {
            return audio;
        }

        public void setAudio(String audio) {
            this.audio = audio;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getAudio_time() {
            return audio_time;
        }

        public void setAudio_time(String audio_time) {
            this.audio_time = audio_time;
        }

        public int getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(int is_follow) {
            this.is_follow = is_follow;
        }

        public String getGod_id() {
            return god_id;
        }

        public void setGod_id(String god_id) {
            this.god_id = god_id;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public static class CommentsBean {
            /**
             * id : 16
             * addtime : 11-14 15:17
             * content : 真的很棒,跟大神献上膝盖
             * star : 5
             * user_id : 1103
             * headimgurl : http://tp5_test.miniyuyin.cn/upload/avatar/20191106/15730210975043.png
             * nickname : 小丸子
             */

            private int id;
            private String addtime;
            private String content;
            private String star;
            private int user_id;
            private String headimgurl;
            private String nickname;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
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
        }
    }
}
