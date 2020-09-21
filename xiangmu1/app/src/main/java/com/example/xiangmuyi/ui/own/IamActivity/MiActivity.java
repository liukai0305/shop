package com.example.xiangmuyi.ui.own.IamActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.homeadapter.FragmentsVpAdapter;
import com.example.xiangmuyi.base.BaseActivity;
import com.example.xiangmuyi.bean.homebean.PersonalActivityBean;
import com.example.xiangmuyi.bean.homebean.PersonalArticleBean;
import com.example.xiangmuyi.bean.homebean.PersonalMsgBean;
import com.example.xiangmuyi.bean.homebean.PersonalPostBean;
import com.example.xiangmuyi.bean.homebean.UserInfoBean;
import com.example.xiangmuyi.interfaces.ILoadData;
import com.example.xiangmuyi.interfaces.users.IUsers;
import com.example.xiangmuyi.persenter.users.PersonalPersenter;
import com.example.xiangmuyi.ui.Users.Fragments.ActiveFragment;
import com.example.xiangmuyi.ui.Users.Fragments.ArticleFragment;
import com.example.xiangmuyi.ui.Users.Fragments.InfoFragment;
import com.example.xiangmuyi.ui.Users.Fragments.MsgFragment;
import com.example.xiangmuyi.ui.Users.Fragments.PostFragment;
import com.example.xiangmuyi.utils.ImageFilterUtils;
import com.example.xiangmuyi.utils.SpUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import java.security.MessageDigest;
import java.util.ArrayList;

import butterknife.BindView;

public class MiActivity extends BaseActivity <IUsers.Persenter> implements IUsers.View, ILoadData {

    @BindView(R.id.img_head_bg)
    ImageView imgHeadBg;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_level)
    ImageView imgLevel;
    @BindView(R.id.txt_qqnum)
    TextView txtQqnum;
    @BindView(R.id.tv_call)
    TextView tvCall;
    @BindView(R.id.tv_myContact)
    TextView tvMyContact;
    @BindView(R.id.tv_contactMe)
    TextView tvContactMe;
    @BindView(R.id.tv_expScore)
    TextView tvExpScore;
    @BindView(R.id.linear)
    LinearLayout linear;
    @BindView(R.id.layout_userInfo)
    RelativeLayout layoutUserInfo;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.img_sex)
    ImageView imgSex;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing)
    CollapsingToolbarLayout collapsing;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private ArrayList<Fragment> fragments;


    @Override
    protected int getLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        return R.layout.activity_m_i;
    }

    @Override
    protected void initView() {
        collapsing.setExpandedTitleColor(Color.parseColor("#00ffffff"));//设置还没收缩时状态下字体颜色
        collapsing.setCollapsedTitleTextColor(Color.RED);//设置收缩后Toolbar上字体的
        fragments = new ArrayList<>();
        fragments.add(new InfoFragment());
        fragments.add(new ActiveFragment());
        fragments.add(PostFragment.getFragment(2, this));
        fragments.add(new MsgFragment());
        fragments.add(new ArticleFragment());
        FragmentPagerAdapter personalVpAdapter = new FragmentsVpAdapter(getSupportFragmentManager(), 0, fragments);
        viewPager.setAdapter(personalVpAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("资料");
        tabLayout.getTabAt(1).setText("动态");
        tabLayout.getTabAt(2).setText("活动");
        tabLayout.getTabAt(3).setText("社团");
        tabLayout.getTabAt(4).setText("文章");
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MiActivity.this,PersonageActivity.class));
            }
        });
    }

    @Override
    protected IUsers.Persenter initPersenter() {
        return new PersonalPersenter();
    }

    @Override
    protected void initData() {

    }

    //运行
    @Override
    protected void onResume() {
        super.onResume();
        String avatar = SpUtils.getInstance().getString("avater");
        if (!TextUtils.isEmpty(avatar)){
            //设置头像的圆角
            RequestOptions options = RequestOptions.bitmapTransform(new CircleCrop());
            Glide.with(this).load(avatar).apply(options).into(imgHead);

            Glide.with(this)
                    .load(avatar)
                    .apply(RequestOptions.bitmapTransform(new MiActivity.GlideBlurformation(this)))
                    .into(imgHeadBg);
        }
    }

    @Override
    public void loadData(int pos) {
        switch (pos) {
            case 1:
                persenter.getPersonalActivity();
                break;
            case 2:
                persenter.getPersonalPost();
                break;
        }
    }

    @Override
    public void getPersonalReturn(UserInfoBean result) {
        UserInfoBean.DataBean data = result.getData();
        //设置头像的圆角
        RequestOptions options = RequestOptions.bitmapTransform(new CircleCrop());
        Glide.with(this).load(data.getHeadUrl()).apply(options).into(imgHead);

        Glide.with(this)
                .load(data.getHeadUrl())
                .apply(RequestOptions.bitmapTransform(new GlideBlurformation(this)))
                .into(imgHeadBg);
        if (!TextUtils.isEmpty(data.getNickName())) {
            tvTitle.setText(data.getNickName());
        }
        if (!TextUtils.isEmpty(data.getSignature())) {
            txtQqnum.setText(data.getSignature());
        }
        tvMyContact.setText(data.getMyContact() + "");
        tvContactMe.setText(data.getContactMe() + "");
        tvExpScore.setText(data.getExpScore() + "");
    }

    @Override
    public void getPersonalActivityReturn(PersonalActivityBean result) {

    }

    @Override
    public void getPersonalPostReturn(PersonalPostBean result) {

    }

    @Override
    public void getPersonalMsgReturn(PersonalMsgBean result) {

    }

    @Override
    public void getPersonalArticleReturn(PersonalArticleBean result) {

    }
    public static class GlideBlurformation extends BitmapTransformation {
        private Context context;

        public GlideBlurformation(Context context) {
            this.context = context;
        }

        @Override
        protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
            return ImageFilterUtils.instance().blurBitmap(context, toTransform, 20, outWidth, outHeight);
        }

        @Override
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

        }
    }

}