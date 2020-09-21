package com.example.yueshu.home.contract;

import com.example.yueshu.base.BaseView;
import com.example.yueshu.bean.UsersBean;
import com.example.yueshu.net.ICollBack;

public class IMainContract {
    public interface IMainPresenter{

    }
    public interface IMainModel{
        public <T> void getData(String url, ICollBack<T> collBack);
    }

    public interface ImainView extends BaseView {
        @Override
        void showToast(String str);

        void getData(UsersBean usersBean);
    }
}
