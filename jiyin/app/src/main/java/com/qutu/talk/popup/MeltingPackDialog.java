package com.qutu.talk.popup;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.jess.arms.utils.LogUtils;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qutu.talk.R;
import com.qutu.talk.adapter.MeltingPackAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.MeltingPack;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


public class MeltingPackDialog extends Dialog {

    @BindView(R.id.ll_root)
    LinearLayout llRoot;
    @BindView(R.id.layout_baoxiao)
    LinearLayout layoutBaoxiao;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.cv_melting)
    CardView cvMelting;

    private MyBaseArmActivity mContext;
    private CommonModel commonModel;
    private RxErrorHandler mErrorHandler;

    private int familyId = 0;
    private int mPosition;

    public MeltingPackDialog(@NonNull Activity context, CommonModel commonModel, RxErrorHandler errorHandler, int familyId) {
        super(context, R.style.myChooseDialog);
        mContext = (MyBaseArmActivity) context;
        this.commonModel = commonModel;
        this.mErrorHandler = errorHandler;
        this.familyId = familyId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_melting_pack);
        ButterKnife.bind(this);
        llRoot.setOnClickListener(v -> {

        });
        layoutBaoxiao.setOnClickListener(v -> {
            dismiss();
        });
        setWidows();
        initRecyclerView();
        getList();

    }

    private MeltingPackAdapter meltingPackAdapter;
    private GridLayoutManager mGlm;

    private void initRecyclerView() {
        meltingPackAdapter = new MeltingPackAdapter();
        mGlm = new GridLayoutManager(mContext, 4);
        recycler.setLayoutManager(mGlm);
        recycler.setAdapter(meltingPackAdapter);
        meltingPackAdapter.setOnItemClickListener((adapter, view, position) -> {
            mPosition = position;
            meltingPackAdapter.setSelectPosition(position);
        });
    }

    private void setWidows() {

        Display display = getWindow().getWindowManager().getDefaultDisplay();

        WindowManager.LayoutParams lp = getWindow().getAttributes();

        lp.width = (int) (display.getWidth() - QMUIDisplayHelper.dp2px(mContext, 72));

        lp.alpha = 1.0f;

        lp.gravity = Gravity.BOTTOM;

//        getWindow().setAttributes(lp);

        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        this.setCanceledOnTouchOutside(true);

    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @OnClick({
            R.id.cv_melting
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cv_melting:
                if(meltingPackAdapter.getSelectPosition()==-1){
                    return;
                }
                melting(meltingPackAdapter.getData().get(meltingPackAdapter.getSelectPosition()).getId()+"");
                break;
        }
    }

    private void getList(){
        RxUtils.loading(commonModel.getMeltingPack(String.valueOf(UserManager.getUser().getUserId())), mContext)
                .subscribe(new ErrorHandleSubscriber<MeltingPack>(mErrorHandler) {
                    @Override
                    public void onNext(MeltingPack meltingPack) {
                        if (meltingPack.getCode() == 1) {
                            meltingPackAdapter.setNewData(meltingPack.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        LogUtils.debugInfo("====数据请求错误", t.getMessage());
                    }
                });
    }

    private void melting(String id){
        RxUtils.loading(commonModel.meltingGift(String.valueOf(UserManager.getUser().getUserId()),id), mContext)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (baseBean.getCode() == 1) {
                            ToastUtil.showToast(mContext,"熔炼成功");
                            getList();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        LogUtils.debugInfo("====数据请求错误", t.getMessage());
                    }
                });
    }

}
