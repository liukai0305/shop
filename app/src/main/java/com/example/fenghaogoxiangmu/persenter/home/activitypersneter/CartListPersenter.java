package com.example.fenghaogoxiangmu.persenter.home.activitypersneter;


import com.example.fenghaogoxiangmu.base.BasePersenter;
import com.example.fenghaogoxiangmu.bean.shopping.CartBean;
import com.example.fenghaogoxiangmu.bean.home.activitybean.DeleteCartBean;
import com.example.fenghaogoxiangmu.common.CommonSubscriber;
import com.example.fenghaogoxiangmu.interfaces.home.activity.ICart;
import com.example.fenghaogoxiangmu.modle.HttpManager;
import com.example.fenghaogoxiangmu.utils.RxUtils;

public class CartListPersenter extends BasePersenter<ICart.ICartView> implements ICart.ICartPersenter {
    @Override
    public void getCartList() {
        addSubscribe(HttpManager.getInstance().getFkApi().getCartList()
                .compose(RxUtils.<CartBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CartBean>(mView) {
                    @Override
                    public void onNext(CartBean result) {
                        mView.getCartListReturn(result);
                    }
                }));
    }

    @Override
    public void deleteCartList(String productIds) {
        addSubscribe(HttpManager.getInstance().getFkApi().cartDelete(productIds)
                .compose(RxUtils.<DeleteCartBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<DeleteCartBean>(mView) {
                    @Override
                    public void onNext(DeleteCartBean result) {
                        mView.deleteCartListReturn(result);
                    }
                }));
    }
}
