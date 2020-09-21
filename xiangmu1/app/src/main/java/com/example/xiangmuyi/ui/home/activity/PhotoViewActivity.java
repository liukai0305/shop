package com.example.xiangmuyi.ui.home.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.homeadapter.MyImageAdapter;
import com.example.xiangmuyi.bean.homebean.HomeRecommendBean;
import com.example.xiangmuyi.common.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoViewActivity extends AppCompatActivity {

    public static final String TAG = PhotoViewActivity.class.getSimpleName();
    @BindView(R.id.tv_zi)
    TextView tvZi;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;

    private int index;
    private MyImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        ButterKnife.bind(this);
        initData();

    }

    private void initData() {
        index = Constants.INDEX;
        List<HomeRecommendBean.DataBean.PostDetailBean.ImagesBean> bean =Constants.IMG_DATA;
        adapter = new MyImageAdapter(bean, this);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(index, false);
        tvZi.setText(index + 1 + "/" + bean.size());
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                index = position;
                tvZi.setText(index + 1 + "/" + bean.size());
            }
        });
    }


}
