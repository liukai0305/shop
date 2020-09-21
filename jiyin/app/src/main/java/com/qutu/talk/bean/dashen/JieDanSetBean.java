package com.qutu.talk.bean.dashen;

import java.util.List;

public class JieDanSetBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"time":"~","date":"","pd_receive":[{"skill_id":1,"is_jspd":"1","skill_name":"王者荣耀"}]}
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
         * time : ~
         * date :
         * pd_receive : [{"skill_id":1,"is_jspd":"1","skill_name":"王者荣耀"}]
         */

        private int start;
        private int end;
        private String date;
        private List<PdReceiveBean> pd_receive;

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<PdReceiveBean> getPd_receive() {
            return pd_receive;
        }

        public void setPd_receive(List<PdReceiveBean> pd_receive) {
            this.pd_receive = pd_receive;
        }

        public static class PdReceiveBean {
            /**
             * skill_id : 1
             * is_jspd : 1
             * skill_name : 王者荣耀
             */

            private int skill_id;
            private String is_jspd;
            private String skill_name;

            public int getSkill_id() {
                return skill_id;
            }

            public void setSkill_id(int skill_id) {
                this.skill_id = skill_id;
            }

            public String getIs_jspd() {
                return is_jspd;
            }

            public void setIs_jspd(String is_jspd) {
                this.is_jspd = is_jspd;
            }

            public String getSkill_name() {
                return skill_name;
            }

            public void setSkill_name(String skill_name) {
                this.skill_name = skill_name;
            }
        }
    }
}
