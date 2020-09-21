package com.qutu.talk.bean;

public class UpdateVersion {


    /**
     * code : 1
     * message : 请求成功
     * data : {"id":1,"androidbanben":"1.0.0","androidhef":"http://www.baidu.com","ioshref":"http://www.baidu.com","iosbanben":null}
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
         * id : 1
         * androidbanben : 1.0.0
         * androidhef : http://www.baidu.com
         * ioshref : http://www.baidu.com
         * iosbanben : null
         */

        private int id;
        private String androidbanben;
        private String androidhef;
        private String ioshref;
        private Object iosbanben;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAndroidbanben() {
            return androidbanben;
        }

        public void setAndroidbanben(String androidbanben) {
            this.androidbanben = androidbanben;
        }

        public String getAndroidhef() {
            return androidhef;
        }

        public void setAndroidhef(String androidhef) {
            this.androidhef = androidhef;
        }

        public String getIoshref() {
            return ioshref;
        }

        public void setIoshref(String ioshref) {
            this.ioshref = ioshref;
        }

        public Object getIosbanben() {
            return iosbanben;
        }

        public void setIosbanben(Object iosbanben) {
            this.iosbanben = iosbanben;
        }
    }
}
