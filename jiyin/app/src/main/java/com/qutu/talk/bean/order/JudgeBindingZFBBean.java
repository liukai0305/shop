package com.qutu.talk.bean.order;

public class JudgeBindingZFBBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"id":1103,"ali_user_id":"","ali_avatar":"","ali_nick_name":"","ali_account":""}
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
         * id : 1103
         * ali_user_id :
         * ali_avatar :
         * ali_nick_name :
         * ali_account :
         */

        private int id;
        private String ali_user_id;
        private String ali_avatar;
        private String ali_nick_name;
        private String ali_account;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAli_user_id() {
            return ali_user_id;
        }

        public void setAli_user_id(String ali_user_id) {
            this.ali_user_id = ali_user_id;
        }

        public String getAli_avatar() {
            return ali_avatar;
        }

        public void setAli_avatar(String ali_avatar) {
            this.ali_avatar = ali_avatar;
        }

        public String getAli_nick_name() {
            return ali_nick_name;
        }

        public void setAli_nick_name(String ali_nick_name) {
            this.ali_nick_name = ali_nick_name;
        }

        public String getAli_account() {
            return ali_account;
        }

        public void setAli_account(String ali_account) {
            this.ali_account = ali_account;
        }
    }
}
