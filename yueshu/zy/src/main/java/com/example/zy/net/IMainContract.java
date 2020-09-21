package com.example.zy.net;

import com.example.zy.base.BaseView;
import com.example.zy.bean.UsersBean;

public class IMainContract {
    public interface IFragmertPresenter{

    }
    public interface IFragmentModel{
        public <T> void getData(String url,IHttpCallBack<T> iHttpCallBack);
    }
    public interface IFragmentView extends BaseView {
        void showToast(String str);
        void setData(UsersBean usersBean);
    }
}
