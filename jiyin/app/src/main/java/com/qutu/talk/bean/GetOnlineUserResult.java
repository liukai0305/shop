package com.qutu.talk.bean;

import java.util.List;

public class GetOnlineUserResult {


    /**
     * code : 1
     * message : 请求成功
     * data : {"total":2,"users":[{"id":1103,"nickname":"小丸子","headimgurl":"http://47.92.85.75/upload//cover/20190817/15660351526494.png","sort":0,"title":"房主","is_mic":1,"vip_img":"http://47.92.85.75/upload//emoji/vip_ico/vip/vip_6.png"},{"id":1102616,"nickname":"A.渡己","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/EFFSbRb3BgGRWoZKbUhTV7S1Vk6OhsefibsE1H6LooueNpQrYxgHqkWXicI7ykQUp22ZU4c1Xtm1rbuiaAcwGgF1g/132","sort":1,"title":"管理员","is_mic":1,"vip_img":""}]}
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
         * total : 2
         * users : [{"id":1103,"nickname":"小丸子","headimgurl":"http://47.92.85.75/upload//cover/20190817/15660351526494.png","sort":0,"title":"房主","is_mic":1,"vip_img":"http://47.92.85.75/upload//emoji/vip_ico/vip/vip_6.png"},{"id":1102616,"nickname":"A.渡己","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/EFFSbRb3BgGRWoZKbUhTV7S1Vk6OhsefibsE1H6LooueNpQrYxgHqkWXicI7ykQUp22ZU4c1Xtm1rbuiaAcwGgF1g/132","sort":1,"title":"管理员","is_mic":1,"vip_img":""}]
         */

        private String total;
        private List<UsersBean> users;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<UsersBean> getUsers() {
            return users;
        }

        public void setUsers(List<UsersBean> users) {
            this.users = users;
        }

        public static class UsersBean {
            /**
             * id : 1103
             * nickname : 小丸子
             * headimgurl : http://47.92.85.75/upload//cover/20190817/15660351526494.png
             * sort : 0
             * title : 房主
             * is_mic : 1
             * vip_img : http://47.92.85.75/upload//emoji/vip_ico/vip/vip_6.png
             */

            private String id;
            private String nickname;
            private String headimgurl;
            private int sort;
            private String title;
            private String is_mic;
            private String vip_img;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIs_mic() {
                return is_mic;
            }

            public void setIs_mic(String is_mic) {
                this.is_mic = is_mic;
            }

            public String getVip_img() {
                return vip_img;
            }

            public void setVip_img(String vip_img) {
                this.vip_img = vip_img;
            }
        }
    }
}
