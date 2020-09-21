package com.example.fenghaogoxiangmu.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.fenghaogoxiangmu.R;
import com.example.fenghaogoxiangmu.base.BaseAdapter;
import com.example.fenghaogoxiangmu.bean.home.HomeBean;
import com.example.fenghaogoxiangmu.ui.home.activitys.HomeBannerActivity;
import com.example.fenghaogoxiangmu.ui.home.activitys.HomeHotActivity;
import com.example.fenghaogoxiangmu.ui.home.activitys.HomeTopicActivity;
import com.example.fenghaogoxiangmu.utils.SystemUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

public class HomeListAdapter extends BaseMultiItemQuickAdapter<HomeBean.HomeListBean, BaseViewHolder> {
    private Context context;
    private String priceWord;
    private TopicAdapter topicAdapter;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeListAdapter(List<HomeBean.HomeListBean> data, Context context) {
        super(data);
        this.context = context;
        priceWord = context.getString(R.string.word_price_brand);
        //做UI绑定
        addItemType(HomeBean.ITEM_TYPE_BANNER, R.layout.layout_home_banner);
        addItemType(HomeBean.ITEM_TYPE_TAB, R.layout.layout_home_tab);
        addItemType(HomeBean.ITEM_TYPE_TITLETOP, R.layout.layout_title_top);
        addItemType(HomeBean.ITEM_TYPE_BRAND, R.layout.layout_home_brand);
        addItemType(HomeBean.ITEM_TYPE_TITLE, R.layout.layout_title);
        addItemType(HomeBean.ITEM_TYPE_NEW, R.layout.layout_home_newgood);
        addItemType(HomeBean.ITEM_TYPE_TITLETOP, R.layout.layout_title_top);
        addItemType(HomeBean.ITEM_TYPE_HOT, R.layout.layout_home_hop);
        addItemType(HomeBean.ITEM_TYPE_TITLETOP, R.layout.layout_title_top);
        addItemType(HomeBean.ITEM_TYPE_TOPIC, R.layout.layout_home_topiclist);
        addItemType(HomeBean.ITEM_TYPE_TITLETOP, R.layout.layout_title_top);
        addItemType(HomeBean.ITEM_TYPE_LIVING, R.layout.layout_home_living);
        addItemType(HomeBean.ITEM_TYPE_TITLETOP, R.layout.layout_title_top);
        addItemType(HomeBean.ITEM_TYPE_KITCHEN, R.layout.layout_home_kitchen);

    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.HomeListBean item) {
        switch (item.getItemType()) {
            case HomeBean.ITEM_TYPE_TITLE:
                updateTitle(helper, (String) item.data);
                break;
            case HomeBean.ITEM_TYPE_TITLETOP:
                updateTitle(helper, (String) item.data);
                break;
            case HomeBean.ITEM_TYPE_BANNER:
                updateBanner(helper, (List<HomeBean.DataBean.BannerBean>) item.data);
                break;
            case HomeBean.ITEM_TYPE_TAB:
                updateTab(helper, (List<HomeBean.DataBean.ChannelBean>) item.data);
                break;
            case HomeBean.ITEM_TYPE_BRAND:
                updateBrand(helper, (HomeBean.DataBean.BrandListBean) item.data);
                break;
            case HomeBean.ITEM_TYPE_NEW:
                updateNewGood(helper, (HomeBean.DataBean.NewGoodsListBean) item.data);
                break;
            case HomeBean.ITEM_TYPE_HOT:
                udpateHot(helper, (HomeBean.DataBean.HotGoodsListBean) item.data);
                break;
            case HomeBean.ITEM_TYPE_TOPIC:
                updateTopic(helper, (List<HomeBean.DataBean.TopicListBean>) item.data);
                break;
            case HomeBean.ITEM_TYPE_LIVING:
                updateLiving(helper, (HomeBean.DataBean.CategoryListBean.GoodsListBean) item.data);
                break;
            case HomeBean.ITEM_TYPE_KITCHEN:
                updateKitchen(helper, (HomeBean.DataBean.NewGoodsListBean) item.data);
                break;
        }
    }

    private void updateTitle(BaseViewHolder helper, String data) {
        helper.setText(R.id.txt_title, data);
    }

    //轮播图
    private void updateBanner(BaseViewHolder helper, List<HomeBean.DataBean.BannerBean> data) {
        Banner banner = helper.getView(R.id.banner);
        banner.setImages(data);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.DataBean.BannerBean bean = (HomeBean.DataBean.BannerBean) path;
                Glide.with(context).load(bean.getImage_url()).into(imageView);
            }
        }).start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(context, HomeBannerActivity.class);
                context.startActivity(intent);
            }
        });
    }

    //tab
    private void updateTab(BaseViewHolder helper, List<HomeBean.DataBean.ChannelBean> data) {
        LinearLayout layoutChannels = helper.getView(R.id.layout_tab);
        //只让当前的布局内容添加一次 only one
        if (layoutChannels.getChildCount() == 0) {
            for (HomeBean.DataBean.ChannelBean item : data) {
                TextView tab = new TextView(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                int size = SystemUtils.dp2px(context, 14);
                tab.setTextSize(size);
                tab.setGravity(Gravity.CENTER);
                tab.setText(item.getName());
                params.leftMargin = 10;
                params.rightMargin = 10;
                tab.setLayoutParams(params);
                //   int i = Integer.parseInt(item.getUrl());
//                Drawable icon = context.getDrawable(i);
//                tab.setCompoundDrawables(null,icon,null,null);
                layoutChannels.addView(tab);

            }

        }
    }

    //封装品牌制造商直供的列表数据
    private void updateBrand(BaseViewHolder helper, HomeBean.DataBean.BrandListBean data) {
        if (!TextUtils.isEmpty(data.getNew_pic_url())) {
            Glide.with(context).load(data.getNew_pic_url()).into((ImageView) helper.getView(R.id.img_brand));
        }
        helper.setText(R.id.txt_brand_name, data.getName());
        String price = priceWord.replace("$", String.valueOf(data.getFloor_price()));
        helper.setText(R.id.txt_brand_price, price);

    }

    //新品首发标题
    private void updateNewGood(BaseViewHolder helper, HomeBean.DataBean.NewGoodsListBean data) {
        if (!TextUtils.isEmpty(data.getList_pic_url())) {
            Glide.with(context).load(data.getList_pic_url()).into((ImageView) helper.getView(R.id.img_newgood));
        }
        helper.setText(R.id.txt_newgood_name, data.getName());
        String price = priceWord.replace("$", String.valueOf(data.getRetail_price()));
        helper.setText(R.id.txt_newgood_price, price);

    }

    //人气推荐
    private void udpateHot(BaseViewHolder helper, HomeBean.DataBean.HotGoodsListBean data) {
        if (!TextUtils.isEmpty(data.getList_pic_url())) {
            Glide.with(context).load(data.getList_pic_url()).into((ImageView) helper.getView(R.id.img_hot));
        }
        helper.setText(R.id.txt_hot_name, data.getName());
        helper.setText(R.id.txt_hot_title, data.getGoods_brief());
        String price = priceWord.replace("$", String.valueOf(data.getRetail_price()));
        helper.setText(R.id.txt_hot_price, price);

    }

    //专题精选
    private void updateTopic(BaseViewHolder helper, List<HomeBean.DataBean.TopicListBean> data) {
        RecyclerView rcy = helper.getView(R.id.recyclerviewTopic);
        if (topicAdapter == null) {
            topicAdapter = new TopicAdapter(data, context);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rcy.setLayoutManager(linearLayoutManager);
            rcy.setAdapter(topicAdapter);
        } else if (rcy.getAdapter() == null) {
            rcy.setAdapter(topicAdapter);
        }
        topicAdapter.setOnItemClickLis(new BaseAdapter.OnItemClickLisf() {
            @Override
            public void click(int position) {
                HomeBean.DataBean.TopicListBean topicListBean = data.get(position);
                Intent intent = new Intent(context, HomeTopicActivity.class);
                intent.putExtra("id", topicListBean.getId());
                context.startActivity(intent);
            }
        });

    }

    //居家推荐
    private void updateLiving(BaseViewHolder helper, HomeBean.DataBean.CategoryListBean.GoodsListBean data) {
        if (!TextUtils.isEmpty(data.getList_pic_url())) {
            Glide.with(context).load(data.getList_pic_url()).into((ImageView) helper.getView(R.id.img_living_tu));
        }
        helper.setText(R.id.tv_living_trait, data.getName());
        String price = priceWord.replace("$", String.valueOf(data.getRetail_price()));
        helper.setText(R.id.tv_living_price, price);

    }

    //餐厨推荐
    private void updateKitchen(BaseViewHolder helper, HomeBean.DataBean.NewGoodsListBean data) {
        if (!TextUtils.isEmpty(data.getList_pic_url())) {
            Glide.with(context).load(data.getList_pic_url()).into((ImageView) helper.getView(R.id.img_kitchen_tu));
        }
        helper.setText(R.id.tv_kitchen_trait, data.getName());
        String price = priceWord.replace("$", String.valueOf(data.getRetail_price()));
        helper.setText(R.id.tv_kitchen_price, price);

    }
}
