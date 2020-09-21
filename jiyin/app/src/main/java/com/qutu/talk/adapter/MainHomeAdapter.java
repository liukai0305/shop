package com.qutu.talk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.bean.HomeMultipleItem;
import com.qutu.talk.bean.RecommendUser;
import com.qutu.talk.bean.RoomSimpleIntro;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import java.util.List;

public class MainHomeAdapter extends BaseMultiItemQuickAdapter<HomeMultipleItem, BaseViewHolder> {

    Context mContext;

    int mSrceenWidth;

    int mThreeChildWidth;

    int mTwoChildWidth;

    int mStartPosition;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MainHomeAdapter(Context context, List<HomeMultipleItem> data) {
        super(data);
        this.mContext = context;
        mSrceenWidth = QMUIDisplayHelper.getScreenWidth(mContext);
        mThreeChildWidth = (mSrceenWidth - QMUIDisplayHelper.dpToPx(40)) / 3;
        mTwoChildWidth = (mSrceenWidth - QMUIDisplayHelper.dpToPx(35)) / 2;
        addItemType(HomeMultipleItem.title_today_recommend, R.layout.item_today_recommend_title);//个人推荐title
        addItemType(HomeMultipleItem.list_today_recommend, R.layout.item_people_head);//个人推荐数据
        addItemType(HomeMultipleItem.title_hot_recommend, R.layout.item_today_recommend_title);//热门推荐、娱乐、语圈、文坛title
        addItemType(HomeMultipleItem.list_hot_recommend, R.layout.item_home_room);//热门推荐、娱乐、语圈、文坛数据
        addItemType(HomeMultipleItem.title_union_recommend, R.layout.item_today_recommend_title);//最佳工会title
        addItemType(HomeMultipleItem.list_union_recommend, R.layout.item_best_union);//最佳工会数据
        addItemType(HomeMultipleItem.center_banner, R.layout.item_home_center_banner);//center_banner
        addItemType(HomeMultipleItem.title_new_people, R.layout.item_today_recommend_title);//新人推荐title
        addItemType(HomeMultipleItem.text_last_string, R.layout.item_home_bottom_string);//我也是有底线的
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HomeMultipleItem item) {

        switch (helper.getItemViewType()) {
            case HomeMultipleItem.title_today_recommend://个人推荐title
                helper.setText(R.id.tv_left_name, "个人推荐");
                helper.setImageResource(R.id.img_left, R.mipmap.icon_title_recommend);
                helper.setGone(R.id.tv_right_more, false);
                break;
            case HomeMultipleItem.list_today_recommend://个人推荐数据

                RecommendUser recommendUser = (RecommendUser) item.getEntity();

                ConstraintLayout rootLayout = helper.getView(R.id.layout_item_root);
                ViewGroup.LayoutParams rootParams = rootLayout.getLayoutParams();
                rootParams.width = mThreeChildWidth;
                rootLayout.setLayoutParams(rootParams);

                ImageView imageView = helper.getView(R.id.img_people_head);

                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                params.width = mThreeChildWidth;
                params.height = mThreeChildWidth;
                imageView.setLayoutParams(params);

                ArmsUtils.obtainAppComponentFromContext(mContext)
                        .imageLoader()
                        .loadImage(mContext, ImageConfigImpl
                                .builder()
                                .url(recommendUser.getHeadimgurl())
                                .placeholder(R.mipmap.no_tou)
                                .imageView(helper.getView(R.id.img_people_head))
                                .errorPic(R.mipmap.no_tou)
                                .build());

                if (recommendUser.getIs_follow() == 0) {
                    helper.setText(R.id.tv_to_attention, "关注");
                } else {
                    helper.setText(R.id.tv_to_attention, "已关注");
                }

                helper.setText(R.id.tv_people_name, recommendUser.getNickname());

                helper.addOnClickListener(R.id.tv_to_attention);

                break;
            case HomeMultipleItem.title_hot_recommend://热门推荐、娱乐、语圈、文坛title
                String title = (String) item.getEntity();
                helper.setText(R.id.tv_left_name, title);
                helper.setImageResource(R.id.img_left, R.mipmap.icon_title_recommend);
                helper.setGone(R.id.tv_right_more, true);
                break;
            case HomeMultipleItem.list_hot_recommend://热门推荐、娱乐、语圈、文坛数据

                mTwoChildWidth = QMUIDisplayHelper.dpToPx(30);

                RoomSimpleIntro roomSimpleIntro = (RoomSimpleIntro) item.getEntity();

                ConstraintLayout rootLayout1 = helper.getView(R.id.csl_item_root);

                ViewGroup.LayoutParams rootParams1 = rootLayout1.getLayoutParams();

                rootParams1.width = mTwoChildWidth;

                ImageView imageView1 = helper.getView(R.id.img_people_head);

                ViewGroup.LayoutParams params1 = imageView1.getLayoutParams();

                params1.width = mTwoChildWidth;

                int position = helper.getAdapterPosition();

                int prePosition = position - 1;


                if (getData().get(prePosition).getItemType() == HomeMultipleItem.title_hot_recommend) {//前一个类型是title，说明是这个分类中的第一个

                    mStartPosition = position;//记住开始的位置

                    rootParams1.height = mTwoChildWidth;

                } else {
                    if((position-mStartPosition)%2 == 0){
                        LogUtils.debugInfo("长度","短的====");
                        rootParams1.height = mTwoChildWidth;
                        params1.height = mTwoChildWidth;
                    } else {
                        LogUtils.debugInfo("长度","长的====");
                        rootParams1.height = mTwoChildWidth+QMUIDisplayHelper.dpToPx(30);
                        params1.height = mTwoChildWidth+QMUIDisplayHelper.dpToPx(30);
                    }

                }

                rootLayout1.setLayoutParams(rootParams1);

                imageView1.setLayoutParams(params1);

                ArmsUtils.obtainAppComponentFromContext(mContext)
                        .imageLoader()
                        .loadImage(mContext, ImageConfigImpl
                                .builder()
                                .url(roomSimpleIntro.getRoom_cover())
                                .placeholder(R.mipmap.no_tou)
                                .imageView(helper.getView(R.id.img_people_head))
                                .errorPic(R.mipmap.no_tou)
                                .build());

                helper.setText(R.id.tv_home_room_name, roomSimpleIntro.getRoom_name());

                helper.setText(R.id.tv_hot_count, roomSimpleIntro.getHot());

                break;
            case HomeMultipleItem.title_union_recommend://最佳工会title
                break;
            case HomeMultipleItem.list_union_recommend://最佳工会数据
                break;
            case HomeMultipleItem.center_banner://center_banner
                break;
            case HomeMultipleItem.title_new_people://新人推荐title
                break;
            case HomeMultipleItem.text_last_string://我也是有底线的
                break;
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();

        if (manager instanceof GridLayoutManager) {

            final GridLayoutManager gridManager = ((GridLayoutManager) manager);


            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
//                    LogUtils.debugInfo("类型=====" + type);
                    switch (type) {
                        case HomeMultipleItem.title_today_recommend://个人推荐title
                            return 6;
                        case HomeMultipleItem.list_today_recommend://个人推荐数据
                            return 2;
                        case HomeMultipleItem.title_hot_recommend://热门推荐、娱乐、语圈、文坛title
                            return 6;
                        case HomeMultipleItem.list_hot_recommend://热门推荐、娱乐、语圈、文坛数据
                            return 3;
                        case HomeMultipleItem.title_union_recommend://最佳工会title
                            return 6;
                        case HomeMultipleItem.list_union_recommend://最佳工会数据
                            return 2;
                        case HomeMultipleItem.center_banner://center_banner
                            return 6;
                        case HomeMultipleItem.title_new_people://新人推荐title
                            return 6;
                        case HomeMultipleItem.text_last_string://我也是有底线的
                            return 6;
                        default:
                            return 6;
                    }
                }
            });
        }
    }
}
