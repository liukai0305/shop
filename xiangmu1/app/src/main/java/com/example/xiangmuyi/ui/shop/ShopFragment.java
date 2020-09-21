package com.example.xiangmuyi.ui.shop;

import android.webkit.WebView;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseFragment;
import com.example.xiangmuyi.interfaces.IBasePersenter;

import butterknife.BindView;


public class ShopFragment extends BaseFragment {


    @BindView(R.id.wv)
    WebView wv;

    @Override
    protected int getLayout() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initView() {
        if (wv!=null){
            wv.loadUrl("http://tongpao.whfpsoft.com:8095/");
            wv.getSettings().setJavaScriptEnabled(true);
        }

    }

    @Override
    protected IBasePersenter initPersenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
}