package com.example.xiangmuyi.interfaces.discover;

import com.example.xiangmuyi.bean.discoverbean.DiscoverHotBean;
import com.example.xiangmuyi.bean.discoverbean.DiscoverHotspotBean;
import com.example.xiangmuyi.bean.discoverbean.DiscoverNavigationBean;
import com.example.xiangmuyi.bean.discoverbean.PaiHangBang.EsperienBean;
import com.example.xiangmuyi.bean.discoverbean.PaiHangBang.LocalBean;
import com.example.xiangmuyi.bean.discoverbean.PaiHangBang.QianDaoBean;
import com.example.xiangmuyi.bean.discoverbean.paozibean.PaoNearbyBean;
import com.example.xiangmuyi.bean.discoverbean.paozibean.PaoRecommendBean;
import com.example.xiangmuyi.bean.discoverbean.shetuan.SheTuanBean;
import com.example.xiangmuyi.interfaces.IBasePersenter;
import com.example.xiangmuyi.interfaces.IBaseView;
import com.example.xiangmuyi.interfaces.home.IHome;

public interface IDiscover {

    interface View extends IBaseView {

    }

    interface Persenter extends IBasePersenter<IHome.View> {

    }

    //发现热门
    interface HotView extends IBaseView {
        void getHot(DiscoverHotBean discover);
        void getNavigation(DiscoverNavigationBean discover);
        void getHospot(DiscoverHotspotBean discover);
    }

    interface HotPersenter extends IBasePersenter<HotView> {
        void getHot();
        void getNavigation();
        void getHospot(int cid);
    }

    //袍子
    interface PaoView extends IBaseView {
        void getPaoRecommend(PaoRecommendBean discover);
        void getPaoNearby(PaoNearbyBean discover);

    }

    interface PaoPersenter extends IBasePersenter<PaoView> {
        void getPaoRecommend();
        void getPaoNearby();
    }

    //旁行榜 经验
    interface EsperienView extends IBaseView {
        void getEsperien(EsperienBean discover);
        void getLocal(LocalBean discover);
        void getQian(QianDaoBean discover);

    }

    interface EsperienPersenter extends IBasePersenter<EsperienView> {
        void getEsperien();
        void getLocal();
        void getQian();

    }

    interface SheTuanView extends IBaseView{
        void getSheTuan(SheTuanBean discover);
    }
    interface SheTuanPersenter extends IBasePersenter<SheTuanView>{
        void getShetuan();
    }


}
