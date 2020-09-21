package com.qutu.talk.bean.dashen;

import java.util.List;

public class GoodsMiLiListBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"mili":"100.00","treaty":"http://qutu.zzmzrj.com/index/index/recharge_protocol","goods":[{"id":7,"price":10,"mili":10},{"id":8,"price":50,"mili":50},{"id":9,"price":100,"mili":100},{"id":10,"price":500,"mili":500},{"id":11,"price":1000,"mili":1000},{"id":12,"price":2000,"mili":2000}]}
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
         * mili : 100.00
         * treaty : http://qutu.zzmzrj.com/index/index/recharge_protocol
         * goods : [{"id":7,"price":10,"mili":10},{"id":8,"price":50,"mili":50},{"id":9,"price":100,"mili":100},{"id":10,"price":500,"mili":500},{"id":11,"price":1000,"mili":1000},{"id":12,"price":2000,"mili":2000}]
         */

        private String mili;
        private String treaty;
        private List<GoodsBean> goods;

        public String getMili() {
            return mili;
        }

        public void setMili(String mili) {
            this.mili = mili;
        }

        public String getTreaty() {
            return treaty;
        }

        public void setTreaty(String treaty) {
            this.treaty = treaty;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * id : 7
             * price : 10
             * mili : 10
             */

            private int id;
            private String price;
            private String mili;
            public boolean isSelect;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getMili() {
                return mili;
            }

            public void setMili(String mili) {
                this.mili = mili;
            }
        }
    }
}
