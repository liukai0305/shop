package com.example.yueshu.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yueshu.R;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {
    public P mP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        initPresenter();
        if (mP != null) {
            mP.BindView(this);
        }
        initView();
        initData();

    }

    protected abstract void initPresenter();

    public abstract int getLayout();

    protected abstract void initView();

    protected abstract void initData();


}
