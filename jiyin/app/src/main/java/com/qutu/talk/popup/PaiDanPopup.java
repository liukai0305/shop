package com.qutu.talk.popup;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.qutu.talk.R;
import com.qutu.talk.activity.dashen.JieDanSetActivity;
import com.qutu.talk.adapter.dashen.PaiAndJieSetAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.bean.CommentBean;
import com.qutu.talk.bean.dashen.JieDanSetBean;
import com.qutu.talk.service.CommonModel;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class PaiDanPopup extends Dialog {

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

    private PaiAndJieSetAdapter mAdapter;

    private List<JieDanSetBean.DataBean.PdReceiveBean> mPd_receiveList = new ArrayList<>();

    private int skillId;
    private String isJspd;

    public PaiDanPopup(@NonNull Activity context, List<JieDanSetBean.DataBean.PdReceiveBean> pd_receive, CommonModel commonModel, RxErrorHandler exrrorHandler) {
        super(context, R.style.myChooseDialog);
        mContext = (JieDanSetActivity) context;
        mPd_receiveList = pd_receive;
        mCommonModel = commonModel;
        mErrorHandler = exrrorHandler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pai_dan_set_popup);

        ButterKnife.bind(this);

        setWidows();
        getData();
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
        if (mPd_receiveList != null && mPd_receiveList.size() != 0) {
            mAdapter = new PaiAndJieSetAdapter();
            LinearLayoutManager llm = new LinearLayoutManager(mContext);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            paiDanSetRecyc.setLayoutManager(llm);
            paiDanSetRecyc.setAdapter(mAdapter);

            mAdapter.setNewData(mPd_receiveList);
        }

        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            List<JieDanSetBean.DataBean.PdReceiveBean> data = mAdapter.getData();
            JieDanSetBean.DataBean.PdReceiveBean pdReceiveBean = data.get(position);
            skillId = pdReceiveBean.getSkill_id();
            if (pdReceiveBean.getIs_jspd().equals("0")) {
                isJspd = "1";
            } else {
                isJspd = "0";
            }
            setData(isJspd, position);
        });
    }

    //设置数据
    private void setData(String isJspd, int position) {
        RxUtils.loading(mCommonModel.actionReceiptPd(skillId, isJspd), mContext)
                .subscribe(new ErrorHandleSubscriber<CommentBean>(mErrorHandler) {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        JieDanSetBean.DataBean.PdReceiveBean pdReceiveBean = mAdapter.getData().get(position);
                        if (pdReceiveBean.getIs_jspd().equals("0")) {
                            pdReceiveBean.setIs_jspd("1");
                        } else {
                            pdReceiveBean.setIs_jspd("0");
                        }
                        mAdapter.notifyDataSetChanged();
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
                dismiss();
                break;
        }
    }
}
