package com.example.lianxi1.common;

import android.text.TextUtils;

import com.example.lianxi1.interfaces.IBaseView;

import io.reactivex.subscribers.ResourceSubscriber;

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {

    private IBaseView iBaseView;
    private String errorMsg;
    private boolean isShowErrorState = false;

    public CommonSubscriber(IBaseView iBaseView, boolean isShowErrorState) {
        this.iBaseView = iBaseView;
        this.isShowErrorState = isShowErrorState;
    }


    public CommonSubscriber(String errorMsg, boolean isShowErrorState) {
        this.errorMsg = errorMsg;
        this.isShowErrorState = isShowErrorState;
    }

    public CommonSubscriber(IBaseView iBaseView) {
        this.iBaseView = iBaseView;
    }

    public CommonSubscriber(IBaseView iBaseView, String errorMsg, boolean isShowErrorState) {
        this.iBaseView = iBaseView;
        this.errorMsg = errorMsg;
        this.isShowErrorState = isShowErrorState;
    }

    @Override
    public void onError(Throwable t) {
        if (iBaseView==null)return;
        if (errorMsg!=null&& TextUtils.isEmpty(errorMsg)){
            iBaseView.showTips(errorMsg);
        }
    }

    @Override
    public void onComplete() {

    }
}
