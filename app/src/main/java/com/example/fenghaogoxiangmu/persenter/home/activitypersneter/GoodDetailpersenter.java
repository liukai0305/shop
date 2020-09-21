package com.example.fenghaogoxiangmu.persenter.home.activitypersneter;

import com.example.fenghaogoxiangmu.base.BasePersenter;
import com.example.fenghaogoxiangmu.bean.home.activitybean.AddCartInfoBean;
import com.example.fenghaogoxiangmu.bean.home.activitybean.GoodDetailBean;
import com.example.fenghaogoxiangmu.common.CommonSubscriber;
import com.example.fenghaogoxiangmu.interfaces.home.activity.ICart;
import com.example.fenghaogoxiangmu.modle.HttpManager;
import com.example.fenghaogoxiangmu.utils.RxUtils;

public class GoodDetailpersenter extends BasePersenter<ICart.View> implements ICart.Persenter {

    @Override
    public void getGoodDetail(int id) {
        addSubscribe(HttpManager.getInstance().getFkApi().getGoodDetail(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<GoodDetailBean>(mView) {
                    @Override
                    public void onNext(GoodDetailBean goodDetailBean) {
                        mView.getGoodDetailReturn(goodDetailBean);
                    }
                })
        );
    }

    @Override
    public void addCart(int goodsId, int number, int productId) {
        addSubscribe(HttpManager.getInstance().getFkApi().addCart(goodsId,number,productId)
                .compose(RxUtils.<AddCartInfoBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<AddCartInfoBean>(mView) {
                    @Override
                    public void onNext(AddCartInfoBean result) {
                        mView.addCartInfoReturn(result);
                    }
                }));
    }
}
