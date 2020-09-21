package com.qutu.talk.adapter;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.qutu.talk.R;
import com.qutu.talk.bean.GiftListBean;
import com.qutu.talk.bean.MeltingPack;

import java.util.ArrayList;

public class MeltingPackAdapter extends BaseQuickAdapter<MeltingPack.DataBean, BaseViewHolder> {
//    private Context context;
//    private RecyclerView recyclerView;
//    private List<GiftListBean.DataBean.GiftsBean> data;

    public MeltingPackAdapter() {
        super(R.layout.item_g, new ArrayList<>());
    }

    private int selectPosition=-1;

    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MeltingPack.DataBean item) {
        helper.setText(R.id.tv, item.getName());
        helper.setText(R.id.tvPrice, item.getPrice()+" x"+item.getNum()+"个");
        GlideArms.with(mContext)
                .load(item.getImg())
                .into((ImageView) helper.getView(R.id.item_img));

        if (selectPosition==helper.getLayoutPosition()){
            helper.getView(R.id.beijing).setBackgroundResource(R.mipmap.room_gift_xz);
        }else {
            helper.getView(R.id.beijing).setBackgroundResource(0);
        }
    }


}
