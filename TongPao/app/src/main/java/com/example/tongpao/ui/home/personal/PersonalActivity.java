package com.example.tongpao.ui.home.personal;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.tongpao.R;
import com.example.tongpao.adapter.PersonalMsgAdapter;
import com.example.tongpao.adapter.PersonalVpAdapter;
import com.example.tongpao.base.BaseActivity;
import com.example.tongpao.bean.PersonalMsgBean;
import com.example.tongpao.interfaces.home.IHomePersonal;
import com.example.tongpao.presenters.personal.PersonalPresenter;
import com.example.tongpao.ui.view.ObservableScrollView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class PersonalActivity extends BaseActivity<IHomePersonal.PersonalMsgPresenter> implements IHomePersonal.PersonalMsgView {

    ObservableScrollView scrollView;
    LinearLayout content;
    ImageView ivHeader;
    LinearLayout lvHeader;
    ImageView ivBack;
    Toolbar toolbar;
    View spiteLine;
    private TabLayout personal_tb;
    private ViewPager personal_vp;
   private RecyclerView personal_activity_rv;
    private PersonalMsgAdapter personalMsgAdapter;
    private List<PersonalMsgBean.DataBean> dataBeans;

    @Override
    protected IHomePersonal.PersonalMsgPresenter initPresenter() {
        return new PersonalPresenter();
    }

    @Override
    protected void initData() {
        presenter.getPersonalMsg();
        Log.d(TAG, "initData: " + presenter.toString());

    }

    private static final String TAG = "PersonalActivity";

    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        scrollView = findViewById(R.id.scrollView);
        ivBack = findViewById(R.id.iv_back);
        content = findViewById(R.id.content);
        ivHeader = findViewById(R.id.iv_header);
        lvHeader = findViewById(R.id.lv_header);
        spiteLine = findViewById(R.id.spite_line);
        personal_tb = findViewById(R.id.personal_tb);
        personal_vp = findViewById(R.id.personal_vp);
        personal_activity_rv = findViewById(R.id.personal_activity_rv);
        personal_activity_rv.setLayoutManager(new LinearLayoutManager(this));
        dataBeans = new ArrayList<>();
        personalMsgAdapter = new PersonalMsgAdapter(this, dataBeans);
        personal_activity_rv.setAdapter(personalMsgAdapter);

        //tablayout+viewpager+fragment
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new DataFragment());
        fragments.add(new ActivityFragment());
        fragments.add(new DynamicFragment());
        fragments.add(new MassFragment());
        fragments.add(new ArticleFragment());
        PersonalVpAdapter personalVpAdapter = new PersonalVpAdapter(getSupportFragmentManager(), fragments);
        personal_vp.setAdapter(personalVpAdapter);
        personal_tb.setupWithViewPager(personal_vp);
        personal_tb.getTabAt(0).setText("资料");
        personal_tb.getTabAt(1).setText("动态");
        personal_tb.getTabAt(2).setText("活动");
        personal_tb.getTabAt(3).setText("社团");		
        personal_tb.getTabAt(4).setText("文章");

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //获取dimen属性中 标题和头部图片的高度
        final float title_height = getResources().getDimension(R.dimen.title_height);
        final float head_height = getResources().getDimension(R.dimen.head_height);
        //滑动事件回调监听（一次滑动的过程一般会连续触发多次）
        scrollView.setOnScrollListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScroll(int oldy, int dy, boolean isUp) {
                float move_distance = head_height - title_height;
                //手指往上滑,距离未超过200dp
                if (!isUp && dy <= move_distance) {
                    //标题栏逐渐从透明变成不透明
                    toolbar.setBackgroundColor(ContextCompat.getColor(PersonalActivity.this, R.color.color_white));
                    //标题栏渐变
                    TitleAlphaChange(dy, move_distance);
                    //图片视差平移
                    HeaderTranslate(dy);
                    //手指往上滑,距离超过200dp
                } else if (!isUp && dy > move_distance) {
                    //设置不透明百分比为100%，防止因滑动速度过快，导致距离超过200dp,而标题栏透明度却还没变成完全不透的情况。
                    TitleAlphaChange(1, 1);
                    //这里也设置平移，是因为不设置的话，如果滑动速度过快，会导致图片没有完全隐藏。
                    HeaderTranslate(head_height);
                    ivBack.setImageResource(R.mipmap.ic_back_dark);
                    // ivShoppingCart.setImageResource(R.mipmap.ic_shopping_dark);
                    spiteLine.setVisibility(View.VISIBLE);
                    //返回顶部，但距离头部位置大于200dp
                } else if (isUp && dy > move_distance) {
                    //不做处理
                } else if (isUp && dy <= move_distance) {//返回顶部，但距离头部位置小于200dp
                    //标题栏逐渐从不透明变成透明
                    TitleAlphaChange(dy, move_distance);//标题栏渐变
                    HeaderTranslate(dy);//图片视差平移
                    ivBack.setImageResource(R.mipmap.ic_back);
                    //ivShoppingCart.setImageResource(R.mipmap.ic_shopping_cart);
                    spiteLine.setVisibility(View.GONE);
                }
            }
        });
    }

    private void HeaderTranslate(float distance) {
        lvHeader.setTranslationY(-distance);
        ivHeader.setTranslationY(distance / 2);
    }

    private void TitleAlphaChange(int dy, float mHeaderHeight_px) {//设置标题栏透明度变化
        float percent = (float) Math.abs(dy) / Math.abs(mHeaderHeight_px);
        //如果是设置背景透明度，则传入的参数是int类型，取值范围0-255
        //如果是设置控件透明度，传入的参数是float类型，取值范围0.0-1.0
        //这里我们是设置背景透明度就好，因为设置控件透明度的话，返回ICON等也会变成透明的。
        //alpha 值越小越透明
        int alpha = (int) (percent * 255);
        toolbar.getBackground().setAlpha(alpha);//设置控件背景的透明度，传入int类型的参数（范围0~255）
        ivBack.getBackground().setAlpha(255 - alpha);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_personal;
    }


    @Override
    public void getPersonalMsgReturn(PersonalMsgBean result) {
        Log.d(TAG, "getPersonalMsgReturns: "+result.toString());
        PersonalMsgBean.DataBean data = result.getData();
        Log.d(TAG, "getPersonalMsgReturn: "+data.toString());
        dataBeans.add(data);
        Log.d(TAG, "getPersonalMsgReturnss: "+dataBeans.toString());
        Glide.with(this).load(result.getData().getHeadUrl()).into(ivHeader);

    }

}