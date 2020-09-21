package com.qutu.talk.popup;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.qutu.talk.R;
import com.qutu.talk.activity.dashen.JieDanSetActivity;
import com.qutu.talk.adapter.dashen.JieDanRiQiAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.bean.CommentBean;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.dashen.JieDanRiQiBean;
import com.qutu.talk.bean.dashen.JieDanSetBean;
import com.qutu.talk.service.CommonModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class JieDanRiQiPopup extends Dialog {

    @BindView(R.id.iv_cancel)
    TextView ivCancel;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.tv_tit_one)
    TextView tvTitOne;
    @BindView(R.id.pai_dan_set_recyc)
    RecyclerView paiDanSetRecyc;
    private JieDanSetActivity mContext;
    private CommonModel mCommonModel;
    private RxErrorHandler mErrorHandler;

    private JieDanRiQiAdapter mAdapter;

    public JieDanRiQiPopup(@NonNull Activity context, CommonModel commonModel, RxErrorHandler exrrorHandler) {
        super(context, R.style.myChooseDialog);
        mContext = (JieDanSetActivity) context;
        mCommonModel = commonModel;
        mErrorHandler = exrrorHandler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.jie_dan_data_popup);

        ButterKnife.bind(this);

        setWidows();

        mAdapter = new JieDanRiQiAdapter();
        GridLayoutManager glm = new GridLayoutManager(mContext, 4);
        paiDanSetRecyc.setLayoutManager(glm);
        paiDanSetRecyc.setAdapter(mAdapter);

        getData();

        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (mAdapter.getData().get(position).getStatus() == 0) {
                mAdapter.getData().get(position).setStatus(1);
                mAdapter.notifyItemChanged(position);
            } else {
                mAdapter.getData().get(position).setStatus(0);
                mAdapter.notifyItemChanged(position);
            }
        });
    }

    private void setWidows() {

        Display display = getWindow().getWindowManager().getDefaultDisplay();

        WindowManager.LayoutParams lp = getWindow().getAttributes();

        lp.width = (int) (display.getWidth());
        lp.height = (int) (display.getHeight() / 3 * 1.5);

        lp.alpha = 1.0f;

        lp.gravity = Gravity.BOTTOM;

        getWindow().setAttributes(lp);

        this.setCanceledOnTouchOutside(true);
    }

    //获取数据
    private void getData() {
        RxUtils.loading(mCommonModel.getJdDateList(), mContext)
                .subscribe(new ErrorHandleSubscriber<JieDanRiQiBean>(mErrorHandler) {
                    @Override
                    public void onNext(JieDanRiQiBean jieDanRiQiBean) {
                        mAdapter.setNewData(jieDanRiQiBean.getData());
                    }
                });
    }

    @OnClick({R.id.iv_cancel, R.id.tv_finish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_cancel:
                dismiss();
                break;
            case R.id.tv_finish:
                StringBuilder stringBuilder = new StringBuilder();
                List<JieDanRiQiBean.DataBean> data = mAdapter.getData();
                for (JieDanRiQiBean.DataBean list : data) {
                    if (list.getStatus() == 1) {
                        stringBuilder.append(list.getWeeknum() + ",");
                    }
                }
                String str = stringBuilder.substring(0,stringBuilder.length() - 1);
                RxUtils.loading(mCommonModel.actionReceiptDate(str), mContext)
                        .subscribe(new ErrorHandleSubscriber<CommentBean>(mErrorHandler) {
                            @Override
                            public void onNext(CommentBean commentBean) {
                                EventBus.getDefault().post(new FirstEvent("jiedansetwancheng","jiedansetwancheng"));
                                dismiss();
                            }
                        });
                break;
        }
    }
}
