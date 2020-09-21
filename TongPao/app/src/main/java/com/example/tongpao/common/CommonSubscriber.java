package com.example.tongpao.common;

import android.text.TextUtils;

import com.example.tongpao.interfaces.IBaseView;

import io.reactivex.subscribers.ResourceSubscriber;


public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {
    private IBaseView mView;
    private String errorMsg;
    private boolean isShowSubErrorState = false;

    protected CommonSubscriber(IBaseView view){
        mView = view;
    }
    protected CommonSubscriber(IBaseView view,String msg){
        mView = view;
        errorMsg = msg;
    }
    protected CommonSubscriber(IBaseView view,boolean isError){
        mView = view;
        isShowSubErrorState = isError;
    }
    protected CommonSubscriber(IBaseView view,String msg,boolean isError){
        mView = view;
        errorMsg = msg;
        isShowSubErrorState = isError;
    }


    @Override
    public void onError(Throwable t) {
        if (mView==null)return;
        if (errorMsg!=null&& TextUtils.isEmpty(errorMsg)){
            mView.showTips(errorMsg);
        }
    }

    @Override
    public void onComplete() {

    }
}
