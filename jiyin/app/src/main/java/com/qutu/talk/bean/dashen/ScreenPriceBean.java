package com.qutu.talk.bean.dashen;

import java.util.List;

public class ScreenPriceBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"id":1,"price":5,"unit":"局","orders":0,"level":1,"checked":1},{"id":2,"price":10,"unit":"局","orders":20,"level":1,"checked":0},{"id":3,"price":15,"unit":"局","orders":50,"level":1,"checked":0},{"id":4,"price":20,"unit":"局","orders":200,"level":1,"checked":0},{"id":5,"price":25,"unit":"局","orders":200,"level":1,"checked":0}]
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
         * price : 5
         * unit : 局
         * orders : 0
         * level : 1
         * checked : 1
         */



        private String id;
        private String price;
        private String unit;
        private int orders;
        private int level;
        private int checked;
        public boolean isSelector;
        public boolean isClick;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getOrders() {
            return orders;
        }

        public void setOrders(int orders) {
            this.orders = orders;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getChecked() {
            return checked;
        }

        public void setChecked(int checked) {
            this.checked = checked;
        }
    }
}
