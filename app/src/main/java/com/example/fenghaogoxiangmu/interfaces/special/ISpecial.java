package com.example.fenghaogoxiangmu.interfaces.special;


import com.example.fenghaogoxiangmu.bean.special.SpecialBean;
import com.example.fenghaogoxiangmu.interfaces.IBasePersenter;
import com.example.fenghaogoxiangmu.interfaces.IBaseView;

public interface ISpecial {

    interface SpecialView extends IBaseView {
        void getSpecialReturn(SpecialBean result);
    }

    interface Persenter extends IBasePersenter<SpecialView> {
        void getSpecia();
    }
}
