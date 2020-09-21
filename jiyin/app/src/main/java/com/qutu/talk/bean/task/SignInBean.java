package com.qutu.talk.bean.task;

import java.util.List;

public class SignInBean {

    /**
     * code : 1
     * message : 签到成功
     * data : {"title":"已经连续签到1天","data":[{"title":"5钻石","img":"http://47.92.85.75/upload/emoji/txk/xctxk.png"}]}
     */

    private int code;
    private String message;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * title : 已经连续签到1天
         * data : [{"title":"5钻石","img":"http://47.92.85.75/upload/emoji/txk/xctxk.png"}]
         */

        private String title;
        private List<DataBean> data;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * title : 5钻石
             * img : http://47.92.85.75/upload/emoji/txk/xctxk.png
             */

            private String title;
            private String img;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
