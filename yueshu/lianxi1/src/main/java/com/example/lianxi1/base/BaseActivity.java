package com.example.lianxi1.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lianxi1.interfaces.IBasePersenter;
import com.example.lianxi1.interfaces.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends IBasePersenter> extends AppCompatActivity implements IBaseView {
    Unbinder unbinder;
    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        unbinder = ButterKnife.bind(this);
        initView();
        presenter = initPresenter();
        if (presenter!=null){
            presenter.attchView(this);
            initData();
        }
    }


    protected abstract int getLayout();

    protected abstract void initView();

    protected abstract P initPresenter();

    protected abstract void initData();


    @Override
    public void showTips(String tips) {

    }

    @Override
    public void showLoading(int visible) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder!=null){
            unbinder.unbind();
        }
        if (presenter!=null){
            presenter.detachView();
        }
    }


}
