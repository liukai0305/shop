package com.qutu.talk.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ChoiceCouponBean {
    /**
     * code : 1
     * message : 请求成功
     * data : [{"id":9,"wares_id":72,"expire":"2019.12.17","addtime":"2019.12.10","name":"3金币优惠券","price":3,"get_type":"钻石兑换"},{"id":10,"wares_id":72,"expire":"2019.12.17","addtime":"2019.12.10","name":"3金币优惠券","price":3,"get_type":"钻石兑换"}]
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

    public static class DataBean implements Parcelable {
        /**
         * id : 9
         * wares_id : 72
         * expire : 2019.12.17
         * addtime : 2019.12.10
         * name : 3金币优惠券
         * price : 3
         * get_type : 钻石兑换
         */

        private String id;
        private String wares_id;
        private String expire;
        private String addtime;
        private String name;
        private int price;
        private String get_type;
        private boolean isCheck;

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.wares_id = in.readString();
            this.expire = in.readString();
            this.addtime = in.readString();
            this.name = in.readString();
            this.price = in.readInt();
            this.get_type = in.readString();
            this.isCheck = in.readByte() != 0;
        }

        public static final Creator<ChoiceCouponBean.DataBean> CREATOR = new Creator<ChoiceCouponBean.DataBean>() {
            @Override
            public ChoiceCouponBean.DataBean createFromParcel(Parcel in) {
                return new ChoiceCouponBean.DataBean(in);
            }

            @Override
            public ChoiceCouponBean.DataBean[] newArray(int size) {
                return new ChoiceCouponBean.DataBean[size];
            }
        };

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWares_id() {
            return wares_id;
        }

        public void setWares_id(String wares_id) {
            this.wares_id = wares_id;
        }

        public String getExpire() {
            return expire;
        }

        public void setExpire(String expire) {
            this.expire = expire;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
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

        public String getGet_type() {
            return get_type;
        }

        public void setGet_type(String get_type) {
            this.get_type = get_type;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.wares_id);
            dest.writeString(this.expire);
            dest.writeString(this.addtime);
            dest.writeString(this.name);
            dest.writeInt(this.price);
            dest.writeString(this.get_type);
            dest.writeByte((byte) (this.isCheck ? 1 : 0));
        }
    }
}
