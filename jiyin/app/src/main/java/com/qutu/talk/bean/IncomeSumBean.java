package com.qutu.talk.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class IncomeSumBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"totalPrice":75451,"todayTotalPrice":110,"historyIncomeList":[{"count_date":"2019-10-28","total_price":"73159"},{"count_date":"2019-10-29","total_price":"2180"},{"count_date":"2019-09-04","total_price":"1"},{"count_date":"2019-08-27","total_price":"1"},{"count_date":"2019-09-04","total_price":"1"},{"count_date":"2019-09-04","total_price":"1"},{"count_date":"2019-08-27","total_price":"1"}]}
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
         * totalPrice : 75451
         * todayTotalPrice : 110
         * historyIncomeList : [{"count_date":"2019-10-28","total_price":"73159"},{"count_date":"2019-10-29","total_price":"2180"},{"count_date":"2019-09-04","total_price":"1"},{"count_date":"2019-08-27","total_price":"1"},{"count_date":"2019-09-04","total_price":"1"},{"count_date":"2019-09-04","total_price":"1"},{"count_date":"2019-08-27","total_price":"1"}]
         */

        private String totalPrice;
        private String todayTotalPrice;
        private List<HistoryIncomeListBean> historyIncomeList;

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getTodayTotalPrice() {
            return todayTotalPrice;
        }

        public void setTodayTotalPrice(String todayTotalPrice) {
            this.todayTotalPrice = todayTotalPrice;
        }

        public List<HistoryIncomeListBean> getHistoryIncomeList() {
            return historyIncomeList;
        }

        public void setHistoryIncomeList(List<HistoryIncomeListBean> historyIncomeList) {
            this.historyIncomeList = historyIncomeList;
        }

        public static class HistoryIncomeListBean implements MultiItemEntity {
            /**
             * count_date : 2019-10-28
             * total_price : 73159
             */
            public static final int TOP = 1;
            public static final int BUTTOM = 2;
            private int itemType;

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

            public void setItemType(int itemType) {
                this.itemType = itemType;
            }

            @Override
            public int getItemType() {
                return itemType;
            }
        }
    }
}
