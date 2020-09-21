package com.qutu.talk.bean;

import java.io.Serializable;
import java.util.List;

public class RoomTypeResult implements Serializable {


    /**
     * code : 1
     * message : 请求成功
     * data : [{"id":1,"pid":0,"name":"娱乐","children":[{"id":4,"pid":1,"name":"交友"},{"id":5,"pid":1,"name":"男神"},{"id":6,"pid":1,"name":"女神"},{"id":7,"pid":1,"name":"唱歌"},{"id":8,"pid":1,"name":"电台"},{"id":9,"pid":1,"name":"开黑"},{"id":10,"pid":1,"name":"脱口秀"}]},{"id":2,"pid":0,"name":"语圈","children":[{"id":11,"pid":2,"name":"英语"},{"id":12,"pid":2,"name":"韩语"},{"id":13,"pid":2,"name":"法语"},{"id":14,"pid":2,"name":"方言"},{"id":15,"pid":2,"name":"日语"}]},{"id":3,"pid":0,"name":"文坛","children":[{"id":16,"pid":3,"name":"原创"},{"id":17,"pid":3,"name":"读文"},{"id":18,"pid":3,"name":"故事"},{"id":19,"pid":3,"name":"剧本"},{"id":20,"pid":3,"name":"历史"}]}]
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

    public static class DataBean implements Serializable {
        /**
         * id : 1
         * pid : 0
         * name : 娱乐
         * children : [{"id":4,"pid":1,"name":"交友"},{"id":5,"pid":1,"name":"男神"},{"id":6,"pid":1,"name":"女神"},{"id":7,"pid":1,"name":"唱歌"},{"id":8,"pid":1,"name":"电台"},{"id":9,"pid":1,"name":"开黑"},{"id":10,"pid":1,"name":"脱口秀"}]
         */

        private String id;
        private String pid;
        private String name;
        private List<ChildrenBean> children;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean implements Serializable{
            /**
             * id : 4
             * pid : 1
             * name : 交友
             */

            private String id;
            private String pid;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
