package com.example.zy.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.zy.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    public P mP;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initPresenter();
        if (mP != null) {
            mP.BindView(this);
        }

        initView();
        initData();
        return inflate;

    }

    protected abstract int getLayout();

    protected abstract void initPresenter();

    protected abstract void initView();

    protected abstract void initData();

}
