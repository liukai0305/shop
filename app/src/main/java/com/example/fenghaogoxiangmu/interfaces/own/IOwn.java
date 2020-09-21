package com.example.fenghaogoxiangmu.interfaces.own;

import com.example.fenghaogoxiangmu.bean.own.SiteBean;
import com.example.fenghaogoxiangmu.interfaces.IBasePersenter;
import com.example.fenghaogoxiangmu.interfaces.IBaseView;

public interface IOwn {
    interface ISiteView extends IBaseView {
        void getSiteresult(SiteBean addressBean);
    }
    interface ISitePresenter extends IBasePersenter<ISiteView> {
        void getSite(int id);
    }
}
