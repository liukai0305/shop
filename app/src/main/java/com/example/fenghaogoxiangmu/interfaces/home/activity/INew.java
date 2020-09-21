package com.example.fenghaogoxiangmu.interfaces.home.activity;

import com.example.fenghaogoxiangmu.bean.home.activitybean.BrandRBean;
import com.example.fenghaogoxiangmu.bean.home.activitybean.GoodDetailBean;
import com.example.fenghaogoxiangmu.interfaces.IBasePersenter;
import com.example.fenghaogoxiangmu.interfaces.IBaseView;

import java.util.HashMap;

public interface INew  {
    interface View extends IBaseView {
        void getNewReturn(BrandRBean result);
        void getBrandRBean(BrandRBean bean);
    }

    interface Persenter extends IBasePersenter<INew.View> {
        void getNew(int id);
        void getBrand(HashMap<String,Integer> map);

    }
}
