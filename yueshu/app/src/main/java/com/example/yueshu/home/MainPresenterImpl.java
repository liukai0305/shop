package com.example.yueshu.home;

import com.example.yueshu.base.BasePresenter;
import com.example.yueshu.bean.UsersBean;
import com.example.yueshu.home.contract.IMainContract;
import com.example.yueshu.net.ICollBack;

public class MainPresenterImpl extends BasePresenter<IMainContract.ImainView> implements IMainContract.IMainPresenter{
    private MainModelImpl mainModel;
    @Override
    protected void initModel() {
        mainModel=new MainModelImpl();
//        medols.add(mainModel);
        addModel(mainModel);
    }
    public void getData(){
        mainModel.getData("https://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/3", new ICollBack<UsersBean>() {
            @Override
            public void onSuccess(UsersBean usersBean) {
               mView.getData(usersBean);
            }

            @Override
            public void onFile(String str) {
                mView.showToast(str);
            }
        });
    }



}
