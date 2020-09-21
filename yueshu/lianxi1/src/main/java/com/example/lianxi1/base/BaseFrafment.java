package com.example.lianxi1.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lianxi1.interfaces.IBasePersenter;
import com.example.lianxi1.interfaces.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFrafment<P extends IBasePersenter> extends Fragment implements IBaseView {
    Unbinder unbinder;
    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLavout(), null);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initView();
        presenter = initPresenter();
        if (presenter != null) {
            presenter.attchView(this);
            initData();
        }
    }

    protected abstract int getLavout();

    protected abstract void initView();

    protected abstract P initPresenter();

    protected abstract void initData();

}
