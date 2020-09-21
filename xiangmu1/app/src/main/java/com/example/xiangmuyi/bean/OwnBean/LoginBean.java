package com.example.xiangmuyi.bean.OwnBean;

public class LoginBean {

    /**
     * err : 200
     * errmsg :
     * data : {"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Inl1biIsInBhc3N3b3JkIjoiMTIzNDU2IiwidWlkIjoiMTE0Njc4OTUtYzE2Mi00ZmQ1LWE4MDgtZmNhYmU0NDZjYjA4IiwiaWF0IjoxNTk5MDE0NjIwfQ.2RDr5KcWdq0ARqgZIkXHMtuvWSCujv3QJiM5DgizZJs","username":"yun","avater":"http://yun918.cn/study/public/uploadfiles//b7741f6e-e1fc-4ca3-99c7-ad5214b88ad4.jpg","phone":"Dgmwgwpwpp","uid":"11467895-c162-4fd5-a808-fcabe446cb08","adress":"新疆","birthday":null,"sex":1,"age":13}
     */

    private int err;
    private String errmsg;
    private DataBean data;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
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
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Inl1biIsInBhc3N3b3JkIjoiMTIzNDU2IiwidWlkIjoiMTE0Njc4OTUtYzE2Mi00ZmQ1LWE4MDgtZmNhYmU0NDZjYjA4IiwiaWF0IjoxNTk5MDE0NjIwfQ.2RDr5KcWdq0ARqgZIkXHMtuvWSCujv3QJiM5DgizZJs
         * username : yun
         * avater : http://yun918.cn/study/public/uploadfiles//b7741f6e-e1fc-4ca3-99c7-ad5214b88ad4.jpg
         * phone : Dgmwgwpwpp
         * uid : 11467895-c162-4fd5-a808-fcabe446cb08
         * adress : 新疆
         * birthday : null
         * sex : 1
         * age : 13
         */

        private String token;
        private String username;
        private String avater;
        private String phone;
        private String uid;
        private String adress;
        private Object birthday;
        private int sex;
        private int age;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAvater() {
            return avater;
        }

        public void setAvater(String avater) {
            this.avater = avater;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getAdress() {
            return adress;
        }

        public void setAdress(String adress) {
            this.adress = adress;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
