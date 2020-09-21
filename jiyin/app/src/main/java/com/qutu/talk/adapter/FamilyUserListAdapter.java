package com.qutu.talk.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.vlayout.LayoutHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.activity.my.MyPersonalCenterTwoActivity;
import com.qutu.talk.base.BaseDelegateAdapter;
import com.qutu.talk.base.BaseDelegateViewHolder;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.FamilyUser;

import java.util.List;

public class FamilyUserListAdapter extends BaseQuickAdapter<FamilyUser, BaseViewHolder> {

    Context mContext;

//    OnChildViewClickListener mOnClickListener;
//
//    public void setOnClickListener(OnChildViewClickListener onClickListener){
//        this.mOnClickListener = onClickListener;
//    }

    public FamilyUserListAdapter(Context context, int layoutResId, @Nullable List<FamilyUser> data) {
        super(layoutResId, data);
//        super(R.layout.item_family_user, data);
        this.mContext = context;
    }

    @Override
    public void convert(BaseViewHolder helper, FamilyUser item) {

        helper.setText(R.id.tv_user_name, item.getNickname());

        helper.setText(R.id.tv_user_id, "ID："+item.getUser_id());

        if(item.getSex() == 1){
            helper.setImageResource(R.id.img_sex,R.mipmap.gender_boy);
        } else {
            helper.setImageResource(R.id.img_sex,R.mipmap.gender_girl);
        }

        ImageView imgCheck = helper.getView(R.id.img_select_status);

        imgCheck.setVisibility(View.VISIBLE);
        if(item.getUser_type() == 2){//族长
            imgCheck.setImageResource(R.mipmap.cy_zz);
        } else if(item.getUser_type() == 1){
            imgCheck.setImageResource(R.mipmap.cy_gly);
        } else {
            imgCheck.setVisibility(View.GONE);
        }

        ImageView imageView1 = helper.getView(R.id.img_head);

        LinearLayout itemLayout = helper.getView(R.id.layout_add_user_item);

        ArmsUtils.obtainAppComponentFromContext(mContext)
                .imageLoader()
                .loadImage(mContext, ImageConfigImpl
                        .builder()
                        .url(item.getHeadimgurl())
                        .placeholder(R.mipmap.no_tou)
                        .imageView(imageView1)
                        .errorPic(R.mipmap.no_tou)
                        .build());

        itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(mContext, MyPersonalCenterTwoActivity.class);

                if (item.getUser_id().equals(String.valueOf(UserManager.getUser().getUserId()))) {
                    intent1.putExtra("sign", 0);
                    intent1.putExtra("id", "");
                } else {
                    intent1.putExtra("sign", 1);
                    intent1.putExtra("id", item.getUser_id());
                }
                intent1.putExtra("isRoom", false);
                ArmsUtils.startActivity(intent1);
            }
        });


    }

//    public interface OnChildViewClickListener{
//
//        void OnChildClick(View view, UserAddItem recommendUser);
//
//    }
}
