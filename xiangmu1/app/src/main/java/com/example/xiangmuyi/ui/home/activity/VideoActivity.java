package com.example.xiangmuyi.ui.home.activity;

import android.content.Intent;

import androidx.viewpager2.widget.ViewPager2;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.homeadapter.PlayVideoAdapter;
import com.example.xiangmuyi.base.BaseActivity;
import com.example.xiangmuyi.bean.homebean.HomeVideoBean;
import com.example.xiangmuyi.interfaces.IBasePersenter;

import java.util.List;

import butterknife.BindView;

public class VideoActivity extends BaseActivity {


    @BindView(R.id.viewPager2)
    ViewPager2 viewPager2;

    private PlayVideoAdapter playVideoAdapter;
    private List<HomeVideoBean.DataBean.ListBean> list;
    private int pos;
    private boolean isPlayResume;


    @Override
    protected int getLayout() {
        return R.layout.activity_video;
    }

    @Override
    protected void initView() {
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                playVideoAdapter.playVideo();
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        initData();
    }

    @Override
    protected IBasePersenter initPersenter() {
        return null;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent!=null&&intent.hasExtra("videos")){
            list = ((HomeVideoBean.DataBean)intent.getSerializableExtra("videos")).getList();
            pos = intent.getIntExtra("pos",0);
            playVideoAdapter=new PlayVideoAdapter(list,this);
            viewPager2.setAdapter(playVideoAdapter);
            if (pos>0){
                viewPager2.setCurrentItem(pos);
            }
        }
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO: add setContentView(...) invocation
//        ButterKnife.bind(this);
//    }

}