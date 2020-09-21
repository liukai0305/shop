package com.qutu.talk.bean.dashen;

import java.util.List;

public class DuanWeiBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"id":1,"name":"倔强青铜","level":1},{"id":2,"name":"秩序白银","level":2},{"id":3,"name":"荣耀黄金","level":3},{"id":4,"name":"尊贵铂金","level":4},{"id":5,"name":"永恒金币","level":5},{"id":6,"name":"至尊星耀","level":6}]
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
         * id : 1
         * name : 倔强青铜
         * level : 1
         */

        private String id;
        private String name;
        private int level;
        public boolean isSelector;
        public boolean isClick;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }
}
