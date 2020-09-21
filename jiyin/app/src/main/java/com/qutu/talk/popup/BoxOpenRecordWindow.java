package com.qutu.talk.popup;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.qutu.talk.R;
import com.qutu.talk.activity.room.AdminHomeActivity;
import com.qutu.talk.adapter.BoxOpenRecordAdapter;
import com.qutu.talk.adapter.JackpotAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.BoxOpenRecordBean;
import com.qutu.talk.bean.Jackpot;
import com.qutu.talk.bean.PullRefreshBean;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.DealRefreshHelper;
import com.qutu.talk.utils.MyUtil;

import java.util.ArrayList;
import java.util.List;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 砸蛋记录窗口
 */
public class BoxOpenRecordWindow extends PopupWindow {
    private AdminHomeActivity mContext;
    private RecyclerView recyclerView;
    private ImageView ivClose;
    private SmartRefreshLayout mSm;
    private CommonModel commonModel;
    private RxErrorHandler mRxErrorHandler;
    private BoxOpenRecordAdapter mAdapter;
    private List<BoxOpenRecordBean.DataBean> mDataList = new ArrayList<>();
    private PullRefreshBean mPullRefreshBean = new PullRefreshBean();

    public BoxOpenRecordWindow(Activity context, ViewGroup view, CommonModel commonModel, RxErrorHandler rxErrorHandler,int height) {//currentEggType 0金蛋 1.彩蛋
        super(context);
        this.mContext = (AdminHomeActivity) context;
        this.commonModel = commonModel;
        this.mRxErrorHandler = rxErrorHandler;
        View view1 = LayoutInflater.from(context).inflate(R.layout.box_open_record_window, null);
        recyclerView = view1.findViewById(R.id.recyclerview);
        ivClose = view1.findViewById(R.id.iv_close);
        ivClose.setOnClickListener((v)-> dismiss());
        mSm = view1.findViewById(R.id.sm);
        // 设置SelectPicPopupWindow的View
        this.setContentView(view1);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
//        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        //设置透明
        WindowManager.LayoutParams params = context.getWindow().getAttributes();
        params.alpha = 0.7f;
        //this.setWidth(view.getMeasuredWidth() - MyUtil.dip2px(context, 100));
//        this.setHeight(MyUtil.dip2px(context, 300));
        this.setHeight(height);
        context.getWindow().setAttributes(params);

        mAdapter = new BoxOpenRecordAdapter(R.layout.box_open_record_item, mDataList);
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(mAdapter);
        actionAwardExchange();
        mSm.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPullRefreshBean.setLoardMore(mPullRefreshBean, mSm);//加载更多时的处理
                actionAwardExchange();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPullRefreshBean.setLoardMore(mPullRefreshBean, mSm);//加载更多时的处理
                actionAwardExchange();
            }
        });
    }

    public void dismiss() {
        super.dismiss();
        MyBaseArmActivity myBaseArmActivity = mContext;
        WindowManager.LayoutParams params = myBaseArmActivity.getWindow().getAttributes();
        params.alpha = 1f;
        myBaseArmActivity.getWindow().setAttributes(params);
        GemStoneDialog gemStoneDialog = new GemStoneDialog(mContext, commonModel, mRxErrorHandler,0);
        gemStoneDialog.show();
    }

    //获取纪录
    private void actionAwardExchange() {
        RxUtils.loading(commonModel.getAwardRecordList(mPullRefreshBean.pageIndex), mContext)
                .subscribe(new ErrorHandleSubscriber<BoxOpenRecordBean>(mContext.mErrorHandler) {
                    @Override
                    public void onNext(BoxOpenRecordBean boxOpenRecordBean) {
                        List<BoxOpenRecordBean.DataBean> data = boxOpenRecordBean.getData();
                        new DealRefreshHelper<BoxOpenRecordBean.DataBean>().dealDataToUI(mSm, mAdapter, null, data, mDataList, mPullRefreshBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<BoxOpenRecordBean.DataBean>().dealDataToUI(mSm, mAdapter, null, new ArrayList<>(), mDataList, mPullRefreshBean);
                    }
                });
    }
}
