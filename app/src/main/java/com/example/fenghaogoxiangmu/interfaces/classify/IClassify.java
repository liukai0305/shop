package com.example.fenghaogoxiangmu.interfaces.classify;


import com.example.fenghaogoxiangmu.bean.classify.ClassifyBean;
import com.example.fenghaogoxiangmu.interfaces.IBasePersenter;
import com.example.fenghaogoxiangmu.interfaces.IBaseView;

public interface IClassify {
    interface View extends IBaseView {
        void getClassifyReturn(ClassifyBean result);
    }

    interface Persenter extends IBasePersenter<View> {
        void getClassify();
    }
}
