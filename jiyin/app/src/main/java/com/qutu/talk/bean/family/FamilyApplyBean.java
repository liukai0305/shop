package com.qutu.talk.bean.family;

import java.util.List;

public class FamilyApplyBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"family_user_id":118,"user_id":1100119,"addtime":"2019-12-12 16:55","headimgurl":"http://tp5_test.miniyuyin.cn/upload/avatar/20191114/15737146739438.png","nickname":"王老头","msg":"#g同意加入"},{"family_user_id":111,"user_id":1100042,"addtime":"2019-12-12 11:42","headimgurl":"http://47.92.85.75/upload//avatar/20190822/15664721051092.png","nickname":"向佳军的佳佳","msg":"#g同意加入"},{"family_user_id":66,"user_id":1100025,"addtime":"2019-12-10 13:09","headimgurl":"http://tp5_test.miniyuyin.cn/upload/avatar/20191129/15749950331313.png","nickname":"888888","msg":"用户1100041同意加入"},{"family_user_id":65,"user_id":1100114,"addtime":"2019-12-10 13:09","headimgurl":"http://tp5_test.miniyuyin.cn/upload/avatar/20191105/15729483342761.png","nickname":"chufang","msg":"用户1100041同意加入"},{"family_user_id":64,"user_id":1100109,"addtime":"2019-12-10 13:09","headimgurl":"http://47.92.85.75/upload//cover/20190823/15665385657569.png","nickname":"cjc","msg":"用户1100041同意加入"},{"family_user_id":93,"user_id":1100045,"addtime":"2019-12-08 16:10","headimgurl":"http://47.92.85.75/upload//cover/20190910/15680858152414.png","nickname":"阿哲","msg":"已过期"}]
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
         * family_user_id : 118
         * user_id : 1100119
         * addtime : 2019-12-12 16:55
         * headimgurl : http://tp5_test.miniyuyin.cn/upload/avatar/20191114/15737146739438.png
         * nickname : 王老头
         * msg : #g同意加入
         */

        private String family_user_id;
        private String user_id;
        private String addtime;
        private String headimgurl;
        private String nickname;
        private String msg;

        public String getFamily_user_id() {
            return family_user_id;
        }

        public void setFamily_user_id(String family_user_id) {
            this.family_user_id = family_user_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
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

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
