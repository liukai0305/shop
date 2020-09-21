package com.example.tongpao.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tongpao.interfaces.IBasePresenter;
import com.example.tongpao.interfaces.IBaseView;

public abstract class BaseFragment<P extends IBasePresenter> extends Fragment implements IBaseView {
    protected  P Presenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayout(), null);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        Presenter = initPresenter();
        if (Presenter!=null){
            Presenter.attachView(this);
            initData();
        }

    }

    protected abstract P initPresenter();


    protected abstract void initData();

    protected abstract void initView();


    protected abstract int getLayout();


    @Override
    public void showTips(String tips) {

    }

    @Override
    public void showLoading(int visible) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Presenter!=null){
            Presenter.detachView();
        }
    }
}
