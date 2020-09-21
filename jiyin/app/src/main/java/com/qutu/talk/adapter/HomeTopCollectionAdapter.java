package com.qutu.talk.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qutu.talk.R;
import com.qutu.talk.bean.CollectionRoomListBean;
import com.qutu.talk.bean.HotBean;

import java.util.List;

/**
 * 首页头部
 */
@ActivityScope
public class HomeTopCollectionAdapter extends BaseQuickAdapter<CollectionRoomListBean.DataBean.OnBean, BaseViewHolder> {


    public HomeTopCollectionAdapter(int layoutResId, @Nullable List<CollectionRoomListBean.DataBean.OnBean> data) {
        super(layoutResId, data);
    }

//    public HomeTopAdapter() {
//        super(R.layout.item_recom_two, new ArrayList<>());
//    }

//    public HomeTopAdapter(Context context) {
//        this.context = context;
//    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        final ViewHolder VH;
//        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(R.layout.item_recom_two, null);
//            VH = new ViewHolder(convertView);
//            convertView.setTag(VH);
//        } else {
//            VH = (ViewHolder) convertView.getTag();
//        }
//        VH.biaoQian.setText(list_adapter.get(position).getName());
//        VH.roomBiaoQian.setText(list_adapter.get(position).getRoom_name());
//        VH.name.setText("主持：" + list_adapter.get(position).getHost());
//        VH.id.setText("ID：" + list_adapter.get(position).getNumid());
//        VH.hot.setText(list_adapter.get(position).getHot());
//
//        if (!TextUtils.isEmpty(list_adapter.get(position).getRoom_cover())) {
//            ArmsUtils.obtainAppComponentFromContext(context)
//                    .imageLoader()
//                    .loadImage(context, ImageConfigImpl
//                            .builder()
//                            .url(list_adapter.get(position).getRoom_cover())
//                            .placeholder(R.mipmap.no_tu)
//                            .imageView(VH.ciHead)
//                            .errorPic(R.mipmap.no_tu)
//                            .build());
//        }
//        if (!TextUtils.isEmpty(list_adapter.get(position).getCate_img())) {
//            ArmsUtils.obtainAppComponentFromContext(context)
//                    .imageLoader()
//                    .loadImage(context, ImageConfigImpl
//                            .builder()
//                            .url(list_adapter.get(position).getRoom_cover())
//                            .placeholder(R.mipmap.no_tu)
//                            .imageView(VH.label)
//                            .errorPic(R.mipmap.no_tu)
//                            .build());
//        }
////        if (position % 2 == 1) {
////            GlideArms.with(context)
////                    .load(R.mipmap.home_tuijian_blue)
////                    .error(R.mipmap.home_tuijian_blue)
////                    .placeholder(R.mipmap.home_tuijian_blue)
////                    .centerCrop()
////                    .into(VH.imgBg);
////            VH.textDec.setTextColor(context.getResources().getColor(R.color.font_75adff));
////        } else {
////            GlideArms.with(context)
////                    .load(R.mipmap.home_tuijian_pink)
////                    .error(R.mipmap.home_tuijian_pink)
////                    .placeholder(R.mipmap.home_tuijian_pink)
////                    .centerCrop()
////                    .into(VH.imgBg);
////            VH.textDec.setTextColor(context.getResources().getColor(R.color.font_ff8e75));
////        }
////        if (!TextUtils.isEmpty(list_adapter.get(position).getRoom_type())) {
////            String room_type = list_adapter.get(position).getRoom_type();
////            switch (room_type) {
////                case "1":
////                    VH.textDec.setText("语圈");
////                    VH.textDec.setBackground(context.getResources().getDrawable(R.drawable.label_lianggezi));
////                    break;
////                case "2":
////                    VH.textDec.setText("文坛");
////                    VH.textDec.setBackground(context.getResources().getDrawable(R.drawable.label_lianggezi));
////                    break;
////                case "3":
////                    VH.textDec.setText("开黑");
////                    VH.textDec.setBackground(context.getResources().getDrawable(R.drawable.label_lianggezi));
////                    break;
////                case "4":
////                    VH.textDec.setText("娱乐");
////                    VH.textDec.setBackground(context.getResources().getDrawable(R.drawable.label_lianggezi));
////                    break;
////                case "5":
////                    VH.textDec.setText("二次元");
////                    VH.textDec.setBackground(context.getResources().getDrawable(R.drawable.label_sangezi));
////                    break;
////                case "6":
////                    VH.textDec.setText("FM");
////                    VH.textDec.setBackground(context.getResources().getDrawable(R.drawable.label_lianggezi));
////                    break;
////            }
////        }
//
//
//        return convertView;
//    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CollectionRoomListBean.DataBean.OnBean item) {
        helper.setText(R.id.room_biaoqian, item.getRoom_name())
                .setText(R.id.id, item.getHot())
                .setText(R.id.hot, item.getHot());
            helper.setText(R.id.name,"主持："+item.getNickname());
//        if (TextUtils.isEmpty(item.getHost())) {
//            helper.setText(R.id.name, "主持：暂无主持");
//        } else {
//            helper.setText(R.id.name, "主持：" + item.getHost());
//        }


        RoundedImageView headImg = helper.getView(R.id.head_img);
        if (!TextUtils.isEmpty(item.getRoom_cover())) {
            ArmsUtils.obtainAppComponentFromContext(mContext)
                    .imageLoader()
                    .loadImage(mContext, ImageConfigImpl
                            .builder()
                            .url(item.getRoom_cover())
                            .placeholder(R.mipmap.no_tu)
                            .imageView(headImg)
                            .errorPic(R.mipmap.no_tu)
                            .build());
        }
    }


//    public static class ViewHolder {
//        TextView biaoQian, roomBiaoQian, name, id, hot;
//        RoundedImageView ciHead;
//        ImageView label;
//
//        public ViewHolder(View convertView) {
//            biaoQian = convertView.findViewById(R.id.biaoqian);
//            roomBiaoQian = convertView.findViewById(R.id.room_biaoqian);
//            name = convertView.findViewById(R.id.name);
//            id = convertView.findViewById(R.id.id);
//            hot = convertView.findViewById(R.id.hot);
//            ciHead = convertView.findViewById(R.id.head_img);
//            label = convertView.findViewById(R.id.label);
//        }
//    }

}