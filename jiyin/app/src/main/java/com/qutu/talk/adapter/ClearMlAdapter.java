package com.qutu.talk.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.lzy.widget.CircleImageView;
import com.qutu.talk.R;
import com.qutu.talk.bean.BoxOpenRecordBean;
import com.qutu.talk.bean.Microphone;

import java.util.List;

public class ClearMlAdapter extends BaseQuickAdapter<Microphone.DataBean.MicrophoneBean, BaseViewHolder> {

    public interface OnClearClickListener{
        public void onClick(Microphone.DataBean.MicrophoneBean microphoneBean);
    }

    private OnClearClickListener onClearClickListener;

    public void setOnClearClickListener(OnClearClickListener onClearClickListener) {
        this.onClearClickListener = onClearClickListener;
    }

    public ClearMlAdapter(int layoutResId, @Nullable List<Microphone.DataBean.MicrophoneBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Microphone.DataBean.MicrophoneBean item) {
        helper.setText(R.id.tv_nickname,item.getNickname());
        helper.setText(R.id.tv_ml,"魅力值："+item.getPrice());
        GlideArms.with(helper.itemView.getContext())
                .load(item.getHeadimgurl())
                .placeholder(R.mipmap.no_tu)
                .error(R.mipmap.no_tu)
                .into((CircleImageView) helper.getView(R.id.civ_avatar));
        if(onClearClickListener!=null){
            helper.getView(R.id.cv_clear).setOnClickListener(v -> onClearClickListener.onClick(item));
        }
    }
}
