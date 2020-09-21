package com.example.xiangmuyi.persenter.own;

import com.example.xiangmuyi.base.BasePersenter;
import com.example.xiangmuyi.bean.OwnBean.UserUpdateBean;
import com.example.xiangmuyi.common.CommonSubscriber;
import com.example.xiangmuyi.interfaces.own.IOwn;
import com.example.xiangmuyi.model.HttpManager;
import com.example.xiangmuyi.utils.RxUtils;

import java.util.Map;

public class UpDataPersenter extends BasePersenter<IOwn.UpDataView> implements IOwn.UpDataPersenter {

    @Override
    public void updateUserInfo(Map<String, String> map) {
        addSubscribe(HttpManager.getInstance().getUserApi().updateUserInfo(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<UserUpdateBean>(mView) {
                    @Override
                    public void onNext(UserUpdateBean userUpdateBean) {
                        mView.updateUserInfoReturn(userUpdateBean);
                    }
                })
        );
    }
}
