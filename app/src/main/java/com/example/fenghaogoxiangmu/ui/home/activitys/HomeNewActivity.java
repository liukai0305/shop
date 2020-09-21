package com.example.fenghaogoxiangmu.ui.home.activitys;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fenghaogoxiangmu.R;
import com.example.fenghaogoxiangmu.base.BaseActivity;
import com.example.fenghaogoxiangmu.bean.home.activitybean.BrandRBean;
import com.example.fenghaogoxiangmu.interfaces.home.activity.INew;
import com.example.fenghaogoxiangmu.persenter.home.activitypersneter.NewPrestenr;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeNewActivity extends BaseActivity<INew.Persenter> implements INew.View {


    @BindView(R.id.img_new_activity)
    ImageView imgNewActivity;
    @BindView(R.id.tv_New_name)
    TextView tvNewName;
    @BindView(R.id.tv_New_title)
    TextView tvNewTitle;

    @Override
    protected int getLayout() {
        return R.layout.activity_home_new;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected INew.Persenter initPersenter() {
        return new NewPrestenr();
    }

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id", 0);
        persenter.getNew(id);
    }

    @Override
    public void getNewReturn(BrandRBean result) {
        tvNewName.setText(result.getData().getBrand().getName());
        tvNewTitle.setText(result.getData().getBrand().getSimple_desc());
        Glide.with(this).load(result.getData().getBrand().getPic_url()).into(imgNewActivity);

    }
    @Override
    public void getBrandRBean(BrandRBean bean) {

    }

}