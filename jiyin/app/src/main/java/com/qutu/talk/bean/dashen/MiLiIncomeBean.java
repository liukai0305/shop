package com.qutu.talk.bean.dashen;

import java.util.List;

public class MiLiIncomeBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"totalPrice":0,"history":[{"count_date":"2019.11.17","total_price":"13.50"}]}
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
         * totalPrice : 0
         * history : [{"count_date":"2019.11.17","total_price":"13.50"}]
         */

        private String totalPrice;
        private List<HistoryBean> history;

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }

        public List<HistoryBean> getHistory() {
            return history;
        }

        public void setHistory(List<HistoryBean> history) {
            this.history = history;
        }

        public static class HistoryBean {
            /**
             * count_date : 2019.11.17
             * total_price : 13.50
             */

            private String count_date;
            private String total_price;

            public String getCount_date() {
                return count_date;
            }

            public void setCount_date(String count_date) {
                this.count_date = count_date;
            }

            public String getTotal_price() {
                return total_price;
            }

            public void setTotal_price(String total_price) {
                this.total_price = total_price;
            }
        }
    }
}
