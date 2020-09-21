package com.example.xiangmuyi.ui.Users.Activitys;

import android.content.Intent;

import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseActivity;

import com.example.xiangmuyi.bean.homebean.PersonalPostBean;
import com.example.xiangmuyi.common.Constants;
import com.example.xiangmuyi.interfaces.home.IHome;
import com.example.xiangmuyi.utils.ObservableScrollView;

import java.util.List;

import butterknife.BindView;


public class HuoXiangQiangActivity extends BaseActivity {


    @BindView(R.id.dynamic_activity_finsh)
    TextView dynamicActivityFinsh;
    @BindView(R.id.dynamic_activity_title)
    TextView dynamicActivityTitle;
    @BindView(R.id.dynamic_activity_tags)
    TextView dynamicActivityTags;
    @BindView(R.id.dynamic_activity_attention)
    TextView dynamicActivityAttention;
    @BindView(R.id.dynamic_activity_apply)
    TextView dynamicActivityApply;
    @BindView(R.id.dynamic_activity_residue)
    TextView dynamicActivityResidue;
    @BindView(R.id.dynamic_activity_free)
    TextView dynamicActivityFree;
    @BindView(R.id.dynamic_activity_iv_time)
    ImageView dynamicActivityIvTime;
    @BindView(R.id.dynamic_activity_tv_time)
    TextView dynamicActivityTvTime;
    @BindView(R.id.dynamic_activity_tv_by)
    TextView dynamicActivityTvBy;
    @BindView(R.id.dynamic_activity_iv_place)
    ImageView dynamicActivityIvPlace;
    @BindView(R.id.dynamic_activity_tv_place)
    TextView dynamicActivityTvPlace;
    @BindView(R.id.dynamic_activity_logo)
    ImageView dynamicActivityLogo;
    @BindView(R.id.dynamic_activity_unit)
    TextView dynamicActivityUnit;
    @BindView(R.id.dynamic_activity_principal)
    TextView dynamicActivityPrincipal;
    @BindView(R.id.dynamic_activity_webview)
    WebView dynamicActivityWebview;
    @BindView(R.id.discussed_activity_tv_texts)
    TextView discussedActivityTvTexts;
    @BindView(R.id.dynamic_scrolls)
    ObservableScrollView dynamicScrolls;
    @BindView(R.id.dynamic_contexts)
    LinearLayout dynamicContexts;
    @BindView(R.id.dynamic_iv_headers)
    ImageView dynamicIvHeaders;
    @BindView(R.id.dynamic_lv_headers)
    LinearLayout dynamicLvHeaders;
    @BindView(R.id.dynamic_activity_iv_backs)
    ImageView dynamicActivityIvBacks;
    @BindView(R.id.dynamic_activity_tv_text)
    TextView dynamicActivityTvText;
    @BindView(R.id.dynamic_activity_toolbars)
    Toolbar dynamicActivityToolbars;
    @BindView(R.id.dynamic_activity_spite_lines)
    View dynamicActivitySpiteLines;
    private int pos;
    private List<PersonalPostBean.DataBean.ActivitysBean> activitysBean;

    @Override
    protected int getLayout() {
        return R.layout.activity_huo_xiang_qiang;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        pos = intent.getIntExtra("pos", 0);
        activitysBean = Constants.activitysBean;
        Glide.with(this).load(activitysBean.get(pos).getCover()).into(dynamicIvHeaders);
        dynamicActivityTitle.setText(activitysBean.get(pos).getTitle());
        dynamicActivityTags.setText(activitysBean.get(pos).getTags());
        dynamicActivityApply.setText("已报名"+activitysBean.get(pos).getApplyUserCount()+"人");
        dynamicActivityResidue.setText("剩余名额："+(activitysBean.get(pos).getMaxUserNumber()-activitysBean.get(pos).getApplyUserCount()));
        dynamicActivityTvTime.setText("报名时间："+activitysBean.get(pos).getStartTime()+"-"+activitysBean.get(pos).getEndTime());
        dynamicActivityTvBy.setText("报名截止"+activitysBean.get(pos).getApplyCutOffTime());
        dynamicActivityPrincipal.setText("活动地点："+activitysBean.get(pos).getLocation());
        dynamicActivityWebview.loadDataWithBaseURL(null,activitysBean.get(pos).getContent(),"text/html","utf-8",null);
        Spanned spanned = Html.fromHtml(activitysBean.get(pos).getContent(), 1);
        String s = String.valueOf(spanned);
        discussedActivityTvTexts.setText(s);
        dynamicActivityToolbars.setTitle("");
        setSupportActionBar(dynamicActivityToolbars);
        //获取dimen属性中 标题和头部图片的高度
        final float title_height = getResources().getDimension(R.dimen.title_height);
        final float head_height = getResources().getDimension(R.dimen.head_height);
        //滑动事件回调监听（一次滑动的过程一般会连续触发多次）
        dynamicScrolls.setOnScrollListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScroll(int oldy, int dy, boolean isUp) {
                float move_distance = head_height - title_height;
                //手指往上滑,距离未超过200dp
                if (!isUp && dy <= move_distance) {

                    //标题栏逐渐从透明变成不透明
                    dynamicActivityToolbars.setBackgroundColor(ContextCompat.getColor(HuoXiangQiangActivity.this, R.color.color_white));
                    //标题栏渐变
                    TitleAlphaChanges(dy, move_distance);
                    //图片视差平移
                    HeaderTranslates(dy);
                    //手指往上滑,距离超过200dp
                } else if (!isUp && dy > move_distance) {
                    //设置不透明百分比为100%，防止因滑动速度过快，导致距离超过200dp,而标题栏透明度却还没变成完全不透的情况。
                    TitleAlphaChanges(1, 1);
                    //这里也设置平移，是因为不设置的话，如果滑动速度过快，会导致图片没有完全隐藏。
                    HeaderTranslates(head_height);
                    dynamicActivityIvBacks.setImageResource(R.mipmap.ic_back_dark);
                    dynamicActivitySpiteLines.setVisibility(View.VISIBLE);
                    dynamicActivityTvText.setVisibility(View.VISIBLE);
                    //返回顶部，但距离头部位置大于200dp
                } else if (isUp && dy > move_distance) {
                    //不做处理
                } else if (isUp && dy <= move_distance) {//返回顶部，但距离头部位置小于200dp
                    //标题栏逐渐从不透明变成透明
                    TitleAlphaChanges(dy, move_distance);//标题栏渐变
                    HeaderTranslates(dy);//图片视差平移
                    dynamicActivityIvBacks.setImageResource(R.mipmap.ic_back);
                    dynamicActivitySpiteLines.setVisibility(View.GONE);
                    dynamicActivityTvText.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected IHome.RecommendPersenter initPersenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

            private void HeaderTranslates(float distance) {
                dynamicLvHeaders.setTranslationY(-distance);
                dynamicLvHeaders.setTranslationY(distance / 2);
            }

            private void TitleAlphaChanges(int dy, float mHeaderHeight_px) {//设置标题栏透明度变化
                float percent = (float) Math.abs(dy) / Math.abs(mHeaderHeight_px);
                //如果是设置背景透明度，则传入的参数是int类型，取值范围0-255
                //如果是设置控件透明度，传入的参数是float类型，取值范围0.0-1.0
                //这里我们是设置背景透明度就好，因为设置控件透明度的话，返回ICON等也会变成透明的。
                //alpha 值越小越透明
                int alpha = (int) (percent * 255);
                dynamicActivityToolbars.getBackground().setAlpha(alpha);//设置控件背景的透明度，传入int类型的参数（范围0~255）
                dynamicActivityIvBacks.getBackground().setAlpha(255 - alpha);
                dynamicActivityIvBacks.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
            }


}