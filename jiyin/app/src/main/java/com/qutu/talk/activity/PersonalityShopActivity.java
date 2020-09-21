package com.qutu.talk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.qutu.talk.R;
import com.qutu.talk.adapter.PersonalityTsAdapter;
import com.qutu.talk.adapter.PersonalityZqAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.app.view.CircularImage;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.PersonalityBean;
import com.qutu.talk.bean.UserBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.ToastUtil;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class PersonalityShopActivity extends MyBaseArmActivity {

    @Inject
    CommonModel commonModel;
    @BindView(R.id.top_image_bj)
    ImageView topImageBj;
    @BindView(R.id.iv_head)
    CircularImage ivHead;
    @BindView(R.id.tv_tab_0)
    TextView tvTab0;
    @BindView(R.id.tv_indicator_0)
    TextView tvIndicator0;
    @BindView(R.id.ll_tab_0)
    LinearLayout llTab0;
    @BindView(R.id.tv_tab_1)
    TextView tvTab1;
    @BindView(R.id.tv_indicator_1)
    TextView tvIndicator1;
    @BindView(R.id.ll_tab_1)
    LinearLayout llTab1;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.cv_buy)
    CardView cvBuy;
    @BindView(R.id.tv_buy)
    TextView tvBuy;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_my_dress)
    TextView tvMyDress;
    @BindView(R.id.svga)
    SVGAImageView svgaImageView;
    private int selectTab = 0;// 0.头饰 1.坐骑

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_personality_shop;
    }

    private PersonalityTsAdapter personalityTsAdapter = new PersonalityTsAdapter();
    private PersonalityZqAdapter personalityZqAdapter = new PersonalityZqAdapter();

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        svgaImageView.setCallback(new SVGACallback() {
            @Override
            public void onPause() {

            }

            @Override
            public void onFinished() {
                if (svgaImageView!= null){
                    svgaImageView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onRepeat() {

            }

            @Override
            public void onStep(int i, double v) {

            }
        });
        personalityTsAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            personalityTsAdapter.setSelectPosition(position);
            tvTotalMoney.setText(personalityTsAdapter.getData().get(position).getPrice());
            if (personalityTsAdapter.getData().get(position).getIsgm() == 0) {
                tvBuy.setText("立即购买");
                cvBuy.setOnClickListener(v -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("确定购买");
                    builder.setPositiveButton("确定", (dialog, which) -> {
                        buyTs();
                    });
                    builder.setNegativeButton("取消", (dialog1, which) -> {

                    });
                    builder.show();
                });
            } else {
                tvBuy.setText("已购买");
                cvBuy.setOnClickListener(v -> {
                });
            }
        });
        personalityZqAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            PersonalityBean.DataBean.ZqBean zqBean = personalityZqAdapter.getData().get(position);
            if (view.getId() == R.id.tv_test) {
                SVGAParser parser = new SVGAParser(mContext);
                URL url = null;
                try {
                    url = new URL(zqBean.getImages());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                if (url != null) {

                    parser.decodeFromURL(url, new SVGAParser.ParseCompletion() {
                        @Override
                        public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
                            if (svgaImageView != null){
                                svgaImageView.setVideoItem(svgaVideoEntity);
                                svgaImageView.setVisibility(View.VISIBLE);
                                svgaImageView.setLoops(1);
                                svgaImageView.stepToFrame(1, true);
                            }
                        }

                        @Override
                        public void onError() {

                        }
                    });
                }

            } else {

                personalityZqAdapter.setSelectPosition(position);
                tvTotalMoney.setText(zqBean.getPrice());
                if (zqBean.getIsgm() == 0) {
                    tvBuy.setText("立即购买");
                    cvBuy.setOnClickListener(v -> {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage("确定购买");
                        builder.setPositiveButton("确定", (dialog, which) -> {
                            buyZq();
                        });
                        builder.setNegativeButton("取消", (dialog1, which) -> {

                        });
                        builder.show();
                    });
                } else {
                    tvBuy.setText("已购买");
                    cvBuy.setOnClickListener(v -> {
                    });
                }
            }
        });
        recyclerView.setLayoutManager(
                new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(personalityTsAdapter);
        loadUserData();
        loadList();
    }

    private void initTab() {
        tvTab0.setTextColor(0xff000000);
        tvTab1.setTextColor(0xff000000);
        tvIndicator0.setVisibility(View.INVISIBLE);
        tvIndicator1.setVisibility(View.INVISIBLE);
        personalityTsAdapter.setSelectPosition(-1);
        personalityZqAdapter.setSelectPosition(-1);
        tvTotalMoney.setText("0");
        cvBuy.setOnClickListener(v -> {
        });
        tvBuy.setText("立即购买");
    }

    @OnClick({R.id.ll_tab_0, R.id.ll_tab_1, R.id.cv_buy, R.id.iv_back, R.id.tv_my_dress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_tab_0:
                initTab();
                tvTab0.setTextColor(0xffee0092);
                tvIndicator0.setVisibility(View.VISIBLE);
                recyclerView.setAdapter(personalityTsAdapter);
                selectTab = 0;
                break;
            case R.id.ll_tab_1:
                initTab();
                tvTab1.setTextColor(0xffee0092);
                tvIndicator1.setVisibility(View.VISIBLE);
                recyclerView.setAdapter(personalityZqAdapter);
                selectTab = 1;
                break;
            case R.id.cv_buy:
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_my_dress:
                Intent intent = new Intent(this, MyDressActivity.class);
                ArmsUtils.startActivity(intent);
                break;
        }
    }

    /**
     * 购买头饰
     */
    private void buyTs() {
        RxUtils.loading(commonModel.buy_ts(
                String.valueOf(UserManager.getUser().getUserId()),
                String.valueOf(personalityTsAdapter.getData().get(personalityTsAdapter.getSelectPosition()).getId())
                ),
                this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        ToastUtil.showToast(PersonalityShopActivity.this, "购买成功");
                        loadList();
                    }
                });
    }

    /**
     * 购买坐骑
     */
    private void buyZq() {
        RxUtils.loading(commonModel.buy_zq(
                String.valueOf(UserManager.getUser().getUserId()),
                String.valueOf(personalityZqAdapter.getData().get(personalityZqAdapter.getSelectPosition()).getId())
                ),
                this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        ToastUtil.showToast(PersonalityShopActivity.this, "购买成功");
                        loadList();
                    }
                });
    }

    /**
     * 用户信息
     */
    private void loadUserData() {
        RxUtils.loading(commonModel.get_user_info(String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<UserBean>(mErrorHandler) {
                    @Override
                    public void onNext(UserBean userBean) {
                        loadImage(ivHead, userBean.getData().getHeadimgurl(),
                                R.mipmap.no_tou);
                        loadImage(topImageBj, userBean.getData().getHeadimgurl(),
                                R.mipmap.no_tu);
                    }
                });
    }

    /**
     * 商品列表
     */
    private void loadList() {
        RxUtils.loading(commonModel.getPersonalityShop(), this)
                .subscribe(new ErrorHandleSubscriber<PersonalityBean>(mErrorHandler) {
                    @Override
                    public void onNext(PersonalityBean personalityBean) {
                        personalityTsAdapter.setNewData(personalityBean.getData().getTs());
                        personalityZqAdapter.setNewData(personalityBean.getData().getZq());
                    }
                });
    }
}
