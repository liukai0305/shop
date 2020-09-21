package com.qutu.talk.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.vlayout.LayoutHelper;
import com.jess.arms.base.BaseApplication;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.base.BaseDelegateAdapter;
import com.qutu.talk.base.BaseDelegateViewHolder;
import com.qutu.talk.bean.UserAddItem;
import com.qutu.talk.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class NotFriendUserAdapter extends BaseDelegateAdapter<UserAddItem, BaseDelegateViewHolder> {

    Context mContext;

    OnChildViewClickListener mOnClickListener;

    List<UserAddItem> mDataList = new ArrayList<>();

    public void setOnClickListener(OnChildViewClickListener onClickListener){
        this.mOnClickListener = onClickListener;
    }

    public NotFriendUserAdapter(Context context, int layoutResId, @Nullable List<UserAddItem> data, LayoutHelper layoutHelper) {
        super(layoutResId, data, layoutHelper);
        this.mDataList = data;
//        super(R.layout.item_family_add_user, data);
        this.mContext = context;
    }

    @Override
    public void convert(BaseDelegateViewHolder helper, UserAddItem item) {

        helper.setText(R.id.tv_user_name, item.getNickname());

        helper.setText(R.id.tv_user_id, "ID："+item.getUser_id());

        if(item.getSex() == 1){
            helper.setImageResource(R.id.img_sex,R.mipmap.gender_boy);
        } else {
            helper.setImageResource(R.id.img_sex,R.mipmap.gender_girl);
        }

        ImageView imgCheck = helper.getView(R.id.img_select_status);

        if(item.getChecked()){
            imgCheck.setSelected(true);
        } else {
            imgCheck.setSelected(false);
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
                if(item.is_god()==0){
                    ToastUtil.showToast(BaseApplication.mApplication,"他还不是大神，不能被邀请！");
                    return;
                }
                if(item.getChecked()){
                    item.setChecked(false);
                }
                List<UserAddItem> data = getData();
                for(int i = 0;i<data.size();i++){
                    if(data.get(i).getUser_id().equals(item.getUser_id())){
                        removeItem(i);
                        if(mOnClickListener != null){
                            mOnClickListener.OnChildClick(view,item);
                        }
                        break;
                    }
                }

            }
        });


    }

    public interface OnChildViewClickListener{

        void OnChildClick(View view, UserAddItem recommendUser);

    }
}
