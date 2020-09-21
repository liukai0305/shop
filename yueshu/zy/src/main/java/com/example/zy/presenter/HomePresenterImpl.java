package com.example.zy.presenter;

import com.example.zy.base.BasePresenter;
import com.example.zy.bean.UsersBean;
import com.example.zy.model.HomeModelImpl;
import com.example.zy.net.IHttpCallBack;
import com.example.zy.net.IMainContract;

public class HomePresenterImpl extends BasePresenter<IMainContract.IFragmentView> implements IMainContract.IFragmertPresenter {
    private HomeModelImpl homeModel;
    @Override
    protected void initModel() {
        homeModel=new HomeModelImpl();
        addModel(homeModel);
    }
    public void getData(){
        homeModel.getData("http://cdwan.cn:7000/exam/data.json", new IHttpCallBack<UsersBean>() {
            @Override
            public void onSuccess(UsersBean usersBean) {
                mView.setData(usersBean);
            }

            @Override
            public void onFile(String str) {
                mView.showToast(str);
            }
        });
    }
}
