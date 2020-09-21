package com.example.zy2.presenter;

import com.example.zy2.bean.CollBean;
import com.example.zy2.bean.HomeBean;
import com.example.zy2.model.FragmentsModelImp;
import com.example.zy2.model.IModel;
import com.example.zy2.net.ICollBack;
import com.example.zy2.view.IView;

public class FragmentspPresenterImp implements IPresenter, ICollBack {
    private IView view;
    private IModel model;

    public FragmentspPresenterImp(IView view) {
        this.view = view;
        model=new FragmentsModelImp();
    }

    @Override
    public void onSuccessHome(HomeBean homeBean) {
        view.getHomeData(homeBean);
    }

    @Override
    public void onSuccessColl(CollBean collBean) {
        view.getCollData(collBean);
    }

    @Override
    public void onFile(String str) {
        view.showToast(str);
    }

    @Override
    public void setHomeData() {
        model.setHomeData(this);
    }

    @Override
    public void setCollData() {
        model.setCollData(this);
    }
}
