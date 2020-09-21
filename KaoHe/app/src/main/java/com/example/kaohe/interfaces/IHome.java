package com.example.kaohe.interfaces;


import com.example.kaohe.bean.LieBiaoBean;
import com.example.kaohe.bean.ReMenBean;
import com.example.kaohe.bean.TuiJianBean;

public interface IHome {

    interface View extends IBaseView {

    }

    interface Persenter extends IBasePersenter<View> {

    }


    interface IHomeView extends IBaseView {
        void getLieBiao(LieBiaoBean result);

        void getReMen(ReMenBean result);


        void getTuiJian(TuiJianBean result);


    }

    interface IHomePersenter extends IBasePersenter<IHomeView> {
        void getLieBiao();


        void getReMen();

        void getTuiJian();


    }
}