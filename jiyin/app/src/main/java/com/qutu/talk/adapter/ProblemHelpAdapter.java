package com.qutu.talk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qutu.talk.R;
import com.qutu.talk.bean.AgreementBean;

import java.util.ArrayList;
import java.util.List;

public class ProblemHelpAdapter extends BaseQuickAdapter<AgreementBean.DataBean, BaseViewHolder> {
    private Context mContext;

    public ProblemHelpAdapter(Context context) {
        super(R.layout.priblem_help_item, new ArrayList<>());
        this.mContext = context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, AgreementBean.DataBean item) {
        helper.setText(R.id.myhelp, item.getName());
        if (helper.getPosition() == (getData().size() - 1)) {
            helper.getView(R.id.line).setVisibility(View.GONE);
        }
    }
}
