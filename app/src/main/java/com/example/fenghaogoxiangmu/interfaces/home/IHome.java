package com.example.fenghaogoxiangmu.interfaces.home;


import com.example.fenghaogoxiangmu.bean.home.HomeBean;
import com.example.fenghaogoxiangmu.interfaces.IBasePersenter;
import com.example.fenghaogoxiangmu.interfaces.IBaseView;

import java.util.List;

public interface IHome  {
    interface View extends IBaseView {
        void getHomeReturn(List<HomeBean.HomeListBean> result);
    }

    interface Persenter extends IBasePersenter<View> {
        void getHome();
    }
}
