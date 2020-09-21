package com.qutu.talk.adapter.taskcenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.bean.task.SignInDisplayBean;

import java.util.ArrayList;
import java.util.List;

public class SignInAdapter extends BaseQuickAdapter<SignInDisplayBean.DataBeanX.DataBean, BaseViewHolder> {
    private Context mContext;

    public SignInAdapter(Context context) {
        super(R.layout.sign_in_item, new ArrayList<>());
        this.mContext = context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SignInDisplayBean.DataBeanX.DataBean item) {
        ImageView imageView = helper.getView(R.id.show_img);
        TextView textView = helper.getView(R.id.show_text);
        helper.setText(R.id.sign_in_time_text, item.getDay());
        if (item.getIs_sign() == 0) {
            ArmsUtils.obtainAppComponentFromContext(mContext)
                    .imageLoader()
                    .loadImage(mContext, ImageConfigImpl
                            .builder()
                            .url(item.getImg())
                            .placeholder(R.mipmap.no_tu)
                            .imageView(imageView)
                            .errorPic(R.mipmap.no_tu)
                            .build());
            helper.getView(R.id.bj).setBackgroundResource(R.drawable.hui_back_shape);
            textView.setText(item.getName());
            textView.setSelected(false);

            if ("0".equals(item.getIs_jinbi())) {
                helper.getView(R.id.bj).setBackgroundResource(R.drawable.ju_back_shape);
                textView.setSelected(true);
            }

        } else {
            imageView.setImageResource(R.mipmap.home_qd_cg);
            helper.getView(R.id.bj).setBackgroundResource(R.drawable.ju_back_shape);
            textView.setText("已签到");
            textView.setSelected(true);

        }

    }
}
