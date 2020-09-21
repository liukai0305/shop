package com.qutu.talk.bean.dashen;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class MainHomePageSkillBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"id":1,"name":"王者荣耀","image":"http://tp5_test.miniyuyin.cn/upload/game/logo/wzry.png"},{"id":2,"name":"和平精英","image":"http://tp5_test.miniyuyin.cn/upload/game/logo/hpjy.png"},{"id":3,"name":"英雄联盟","image":"http://tp5_test.miniyuyin.cn/upload/game/logo/yxlm.png"},{"id":4,"name":"绝地求生","image":"http://tp5_test.miniyuyin.cn/upload/game/logo/jdqs.png"}]
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
         * id : 1
         * name : 王者荣耀
         * image : http://tp5_test.miniyuyin.cn/upload/game/logo/wzry.png
         */

        private int id;
        private String name;
        private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.name);
            dest.writeString(this.image);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.name = in.readString();
            this.image = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
