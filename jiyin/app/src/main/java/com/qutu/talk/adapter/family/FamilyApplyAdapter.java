package com.qutu.talk.adapter.family;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.bean.family.FamilyApplyBean;

import java.util.List;

public class FamilyApplyAdapter extends BaseQuickAdapter<FamilyApplyBean.DataBean, BaseViewHolder> {
    private Context mContext;

    public FamilyApplyAdapter(int layoutResId, @Nullable List<FamilyApplyBean.DataBean> data, Context context) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, FamilyApplyBean.DataBean item) {
        helper.setText(R.id.fa_time, item.getAddtime())
                .setText(R.id.fa_name, item.getNickname())
                .setText(R.id.fa_shenqing, "请求加入家族")
                .setText(R.id.yicaozuo, item.getMsg())
                .addOnClickListener(R.id.jujue)
                .addOnClickListener(R.id.tongyi);

        ArmsUtils.obtainAppComponentFromContext(mContext)
                .imageLoader()
                .loadImage(mContext, ImageConfigImpl
                        .builder()
                        .url(item.getHeadimgurl())
                        .placeholder(R.mipmap.no_tou)
                        .imageView(helper.getView(R.id.fa_headimg))
                        .errorPic(R.mipmap.no_tou)
                        .build());

        LinearLayout ll = helper.getView(R.id.anniu_linea);
        TextView textView = helper.getView(R.id.yicaozuo);
        if (TextUtils.isEmpty(item.getMsg())) {
            ll.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.VISIBLE);
            ll.setVisibility(View.GONE);
        }

    }
}
