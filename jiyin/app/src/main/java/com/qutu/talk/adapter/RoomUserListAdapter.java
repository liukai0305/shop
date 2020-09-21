package com.qutu.talk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.qutu.talk.R;
import com.qutu.talk.bean.RoomMultipleItem;

import java.util.List;

public class RoomUserListAdapter extends BaseMultiItemQuickAdapter<RoomMultipleItem, BaseViewHolder> {

    private int mMicUpUser, mMicUpUserLine, mMicDownUser,mMicPaiUserCount,mMicShiYin;

    Context mContext;

    List<RoomMultipleItem> mDataList;

    boolean mIsPaiDan = false;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public RoomUserListAdapter(Context context, List<RoomMultipleItem> data,boolean isPaiDan) {
        super(data);
        mContext = context;
        mDataList = data;
        this.mIsPaiDan = isPaiDan;
        //必须绑定type和layout的关系
        addItemType(RoomMultipleItem.TITLE_MIC_UP, R.layout.item_room_user_mic_title);
        addItemType(RoomMultipleItem.MIC_UP, R.layout.item_room_item);
        addItemType(RoomMultipleItem.TITLE_MIC_DOWN, R.layout.item_room_user_mic_title);
        addItemType(RoomMultipleItem.MIC_DOWN, R.layout.item_room_item);
        addItemType(RoomMultipleItem.TITLE_MIC_PAI, R.layout.item_room_user_mic_title);
        addItemType(RoomMultipleItem.MIC_PAI, R.layout.item_room_item_new);
        addItemType(RoomMultipleItem.TITLE_MIC_SHIYIN, R.layout.item_room_user_mic_title);
        addItemType(RoomMultipleItem.MIC_SHIYIN, R.layout.item_room_item_new);
    }

    public void setUserCount(int micUpUser, int micUpUserLine, int micDownUser,int micShiYin) {
        this.mMicUpUser = micUpUser;
        this.mMicUpUserLine = micUpUserLine;
        this.mMicDownUser = micDownUser;
        this.mMicShiYin = micShiYin;
    }

    public void setPaiUserCount(int micPaiUserCount){
        this.mMicPaiUserCount = micPaiUserCount;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RoomMultipleItem item) {

        switch (helper.getItemViewType()) {
            case RoomMultipleItem.TITLE_MIC_UP:
                helper.setText(R.id.tv_mul_title, "麦上用户" + mMicUpUserLine + "/" + mMicUpUser);
                break;
            case RoomMultipleItem.MIC_UP:

                ImageView imageView = helper.getView(R.id.img_head);

                GlideArms.with(mContext)
                        .load(item.getData().getHeadimgurl())
                        .placeholder(R.mipmap.no_tou)
                        .error(R.mipmap.no_tou)
                        .circleCrop()
                        .into(imageView);

                helper.setText(R.id.shape_tv_mic, "下麦");

                helper.setText(R.id.tv_user_name, item.getData().getNickname());

                helper.setText(R.id.tv_user_id, "ID:  "+item.getData().getId());

                break;
            case RoomMultipleItem.TITLE_MIC_PAI:
                if(mIsPaiDan){
                    helper.setText(R.id.tv_mul_title, "点单人员" + mMicPaiUserCount + "人");
                } else {
                    helper.setText(R.id.tv_mul_title, "排麦人员" + mMicPaiUserCount + "人");
                }
                break;
            case RoomMultipleItem.MIC_PAI:

                ImageView imageView2 = helper.getView(R.id.img_head);

                GlideArms.with(mContext)
                        .load(item.getData().getHeadimgurl())
                        .placeholder(R.mipmap.no_tou)
                        .error(R.mipmap.no_tou)
                        .circleCrop()
                        .into(imageView2);

                //排名
                TextView tvCount = helper.getView(R.id.tv_cunt);
                tvCount.setText(item.getData().getSort());
                tvCount.setVisibility(View.VISIBLE);

                helper.setText(R.id.tv_time, item.getData().getAddtime());

                helper.setText(R.id.tv_user_name, item.getData().getNickname());

                helper.setText(R.id.tv_user_id, "ID:  "+item.getData().getId());

                helper.setText(R.id.shape_tv_mic, "上麦");

                break;
            case RoomMultipleItem.TITLE_MIC_SHIYIN://试音
                helper.setText(R.id.tv_mul_title, "试音人员" + mMicShiYin+"人");
                break;
            case RoomMultipleItem.MIC_SHIYIN://试音
                ImageView imageView3 = helper.getView(R.id.img_head);

                GlideArms.with(mContext)
                        .load(item.getData().getHeadimgurl())
                        .placeholder(R.mipmap.no_tou)
                        .error(R.mipmap.no_tou)
                        .circleCrop()
                        .into(imageView3);

                //排名
                TextView tvCount1 = helper.getView(R.id.tv_cunt);
                tvCount1.setText(item.getData().getSort());
                tvCount1.setVisibility(View.VISIBLE);

                helper.setText(R.id.tv_time, item.getData().getAddtime());

                helper.setText(R.id.tv_user_name, item.getData().getNickname());

                helper.setText(R.id.tv_user_id, "ID:  "+item.getData().getId());

                helper.setText(R.id.shape_tv_mic, "上麦");
                break;
            case RoomMultipleItem.TITLE_MIC_DOWN:
                helper.setText(R.id.tv_mul_title, "麦下人员" + mMicDownUser+"人");
                break;
            case RoomMultipleItem.MIC_DOWN:

                ImageView imageView1 = helper.getView(R.id.img_head);

                GlideArms.with(mContext)
                        .load(item.getData().getHeadimgurl())
                        .placeholder(R.mipmap.no_tou)
                        .error(R.mipmap.no_tou)
                        .circleCrop()
                        .into(imageView1);

                helper.setText(R.id.tv_user_name, item.getData().getNickname());

                helper.setText(R.id.tv_user_id, "ID:  "+item.getData().getId());

                helper.setText(R.id.shape_tv_mic, "上麦");

                break;
        }

        helper.addOnClickListener(R.id.shape_tv_mic);
    }
}
