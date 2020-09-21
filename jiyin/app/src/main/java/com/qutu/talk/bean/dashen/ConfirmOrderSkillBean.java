package com.qutu.talk.bean.dashen;

import java.util.List;

public class ConfirmOrderSkillBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"skill_apply_id":1,"name":"王者荣耀","price":10,"unit":"局"},{"skill_apply_id":2,"name":"和平精英","price":10,"unit":"半小时"},{"skill_apply_id":3,"name":"英雄联盟","price":10,"unit":"局"},{"skill_apply_id":4,"name":"绝地求生","price":25,"unit":"时"}]
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
         * skill_apply_id : 1
         * name : 王者荣耀
         * price : 10
         * unit : 局
         */

        private int skill_apply_id;
        private String name;
        private int price;
        private String unit;
        private String skill_id;

        public int getSkill_apply_id() {
            return skill_apply_id;
        }

        public void setSkill_apply_id(int skill_apply_id) {
            this.skill_apply_id = skill_apply_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getSkill_id() {
            return skill_id;
        }

        public void setSkill_id(String skill_id) {
            this.skill_id = skill_id;
        }
    }
}
