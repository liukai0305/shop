package com.example.fenghaogoxiangmu.bean.user;

public class LoginBean {
    /**
     * errno : 0
     * errmsg :
     * data : {"code":200,"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiMmE3MjY4NDItYjFiNC00N2ZmLWI0MmMtYWMxYTA1MmQ5MjkwIiwiaWF0IjoxNjAwMzEyNzc0fQ.H9bmVDpToYuji6z0Y-g-zAk2Eeg4FcQjpNvuuvD2ts0","userInfo":{"uid":"2a726842-b1b4-47ff-b42c-ac1a052d9290","username":"liu","nickname":null,"gender":0,"avatar":"","birthday":0}}
     */

    private int errno;
    private String errmsg;
    private DataBean data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * code : 200
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiMmE3MjY4NDItYjFiNC00N2ZmLWI0MmMtYWMxYTA1MmQ5MjkwIiwiaWF0IjoxNjAwMzEyNzc0fQ.H9bmVDpToYuji6z0Y-g-zAk2Eeg4FcQjpNvuuvD2ts0
         * userInfo : {"uid":"2a726842-b1b4-47ff-b42c-ac1a052d9290","username":"liu","nickname":null,"gender":0,"avatar":"","birthday":0}
         */

        private int code;
        private String token;
        private UserInfoBean userInfo;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public static class UserInfoBean {
            /**
             * uid : 2a726842-b1b4-47ff-b42c-ac1a052d9290
             * username : liu
             * nickname : null
             * gender : 0
             * avatar :
             * birthday : 0
             */

            private String uid;
            private String username;
            private Object nickname;
            private int gender;
            private String avatar;
            private int birthday;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public Object getNickname() {
                return nickname;
            }

            public void setNickname(Object nickname) {
                this.nickname = nickname;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getBirthday() {
                return birthday;
            }

            public void setBirthday(int birthday) {
                this.birthday = birthday;
            }
        }
    }
}
