package com.qutu.talk.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.bean.RoomBg;
import com.qutu.talk.bean.RoomInfoBean;
import com.qutu.talk.bean.RoomType;

import java.util.ArrayList;

/**
 * 设置房间图片
 */
@ActivityScope
public class RoomImgyAdapter extends BaseQuickAdapter<RoomInfoBean.DataBean.BackgroundsBean, BaseViewHolder> {


    public RoomImgyAdapter() {
        super(R.layout.item_room_img, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomInfoBean.DataBean.BackgroundsBean item) {
        ImageView imageView = helper.getView(R.id.img);
        ArmsUtils.obtainAppComponentFromContext(mContext)
                .imageLoader()
                .loadImage(mContext, ImageConfigImpl
                        .builder()
                        .url(item.getImg())
                        .placeholder(R.mipmap.default_home)
                        .imageView(imageView)
                        .errorPic(R.mipmap.default_home)
                        .build());
       if(item.getIs_check()==1){
           helper.setVisible(R.id.imgSelect,true);
       }else {
           helper.setVisible(R.id.imgSelect,false);
       }


//        helper.setText(R.id.txt1, item.getId() + "   " + item.getContact())
//                .setOnClickListener(R.id.txt3, v -> ArmsUtils.snackbarText("投票成功"))
//                .setText(R.id.txt2, item.getTotal() + "票");

//        ((TextView) helper.getView(R.id.tv_oldPrice)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }
}
