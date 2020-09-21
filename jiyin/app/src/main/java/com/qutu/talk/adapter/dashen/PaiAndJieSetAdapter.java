package com.qutu.talk.adapter.dashen;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qutu.talk.R;
import com.qutu.talk.bean.dashen.JieDanSetBean;

import java.util.ArrayList;
import java.util.List;

public class PaiAndJieSetAdapter extends BaseQuickAdapter<JieDanSetBean.DataBean.PdReceiveBean, BaseViewHolder> {
    public PaiAndJieSetAdapter() {
        super(R.layout.pai_dan_set_recyc_item, new ArrayList<>());
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, JieDanSetBean.DataBean.PdReceiveBean item) {
        helper.addOnClickListener(R.id.ok_bn);

        helper.setText(R.id.pai_dan_set_name, item.getSkill_name());
        if (item.getIs_jspd().equals("1")) {
            helper.getView(R.id.ok_bn).setSelected(true);
        } else {
            helper.getView(R.id.ok_bn).setSelected(false);
        }
    }
}
