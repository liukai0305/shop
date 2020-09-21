package com.qutu.talk.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.adapter.MyFamilyListAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmFragment;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.MyFamilyItem;
import com.qutu.talk.bean.MyFamilyResult;
import com.qutu.talk.bean.PullRefreshBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.Constant;
import com.qutu.talk.utils.DealRefreshHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.Unbinder;
import io.rong.imkit.RongIM;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 作者:sgm
 * 描述:朋友
 */
public class MessageFamilyListFragment extends MyBaseArmFragment {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.smart)
    SmartRefreshLayout refreshLayout;

    @Inject
    CommonModel commonModel;
    @BindView(R.id.no_data_image)
    ImageView noDataImage;
    @BindView(R.id.no_data_text)
    TextView noDataText;
    @BindView(R.id.no_data)
    LinearLayout mLinearLayoutNoData;
    Unbinder unbinder;

//    private int page = 1;

    PullRefreshBean mPullRefreshBean = new PullRefreshBean();

    private MyFamilyListAdapter mAdapter;

    List<MyFamilyItem> mDataList = new ArrayList<>();

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @Override
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return ArmsUtils.inflate(getActivity(), R.layout.fragment_my_family_list);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        mAdapter = new MyFamilyListAdapter(getActivity(),R.layout.item_my_family,mDataList);

        mAdapter.setOnClickListener(new MyFamilyListAdapter.OnButtonClickListener() {
            @Override
            public void itemClick(@NotNull MyFamilyItem item) {

                //群聊
//                RongIM.getInstance().startConversation(mContext, Conversation.ConversationType.GROUP,
//                        item.getFamily_id(),
//                        item.getName());
                RongIM.getInstance().startGroupChat(getActivity(), item.getFamily_id(), item.getName());
            }
        });

        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerview.setAdapter(mAdapter);



        refreshLayout.setOnRefreshListener(refreshlayout -> {

            mPullRefreshBean.setRefresh(mPullRefreshBean, refreshLayout);

            loadData();

        });

        refreshLayout.setOnLoadMoreListener(refreshlayout -> {

            mPullRefreshBean.setLoardMore(mPullRefreshBean, refreshLayout);

            loadData();

        });

        loadData();
    }

    private void loadData() {
        RxUtils.loading(commonModel.getMyFamilyInfo(), this)
                .subscribe(new ErrorHandleSubscriber<MyFamilyResult>(mErrorHandler) {
                    @Override
                    public void onNext(MyFamilyResult userFriend) {

                        if (userFriend != null && userFriend.getData() != null && !TextUtils.isEmpty(userFriend.getData().getFamily_id())) {

                            MyFamilyItem myFamilyItem = userFriend.getData();

                            List<MyFamilyItem> list = new ArrayList<>();

                            list.add(myFamilyItem);

                            new DealRefreshHelper<MyFamilyItem>().dealDataToUI(refreshLayout, mAdapter, mLinearLayoutNoData, list, mDataList, mPullRefreshBean);

                        } else {
                            new DealRefreshHelper<MyFamilyItem>().dealDataToUI(refreshLayout, mAdapter, mLinearLayoutNoData, new ArrayList<MyFamilyItem>(), mDataList, mPullRefreshBean);
                        }

                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        new DealRefreshHelper<MyFamilyItem>().dealDataToUI(refreshLayout, mAdapter, mLinearLayoutNoData, new ArrayList<MyFamilyItem>(), mDataList, mPullRefreshBean);
                    }
                });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
//        String msg = event.getMsg();
        if ( Constant.TUICHUFAMILY.equals(tag)) {

            mPullRefreshBean.setRefresh(mPullRefreshBean, refreshLayout);

            loadData();

        } else if(Constant.CREAT_FAMILY.equals(tag)){

            mPullRefreshBean.setRefresh(mPullRefreshBean, refreshLayout);

            loadData();
        }
    }
}
