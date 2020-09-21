package com.qutu.talk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.activity.dynamic.DynamicDetailsActivity;
import com.qutu.talk.activity.my.MyPersonalCenterActivity;
import com.qutu.talk.activity.my.MyPersonalCenterTwoActivity;
import com.qutu.talk.adapter.CommDynamicAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmFragment;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.CommentBean;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.LoginData;
import com.qutu.talk.bean.RecommendedDynamicBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.Unbinder;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.qutu.talk.utils.Constant.FABUCHENGGONG;

/**
 * 最新动态页面
 */
public class NewestDynamicFragment extends MyBaseArmFragment {

    @Inject
    CommonModel commonModel;
    @BindView(R.id.myList1)
    RecyclerView myList1;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;

    private LoginData user;
    private CommDynamicAdapter commDynamicAdapter;
    private int mPage = 1;
    private int sharePos;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    public static NewestDynamicFragment getInstance() {
        NewestDynamicFragment fragment = new NewestDynamicFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", "NewestDynamicFragment");
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comm, container, false);
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void visibleToLoadData() {
        super.visibleToLoadData();
        user = UserManager.getUser();
        commDynamicAdapter = new CommDynamicAdapter(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration did = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        did.setDrawable(getResources().getDrawable(R.drawable.did_gray_dp8));
        myList1.addItemDecoration(did);
        myList1.setLayoutManager(linearLayoutManager);
        myList1.setAdapter(commDynamicAdapter);

        getNewDynamic(mPage);
        setonClick();

        refreshLayout.setOnRefreshListener(refreshlayout -> {
            mPage = 1;
            getNewDynamic(mPage);
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout1 -> {
            mPage++;
            getNewDynamic(mPage);
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    //获取最新动态
    private void getNewDynamic(int paga) {
        RxUtils.loading(commonModel.new_dynamic(user.getUserId() + "", paga + ""), this)
                .subscribe(new ErrorHandleSubscriber<RecommendedDynamicBean>(mErrorHandler) {
                    @Override
                    public void onNext(RecommendedDynamicBean recommendedDynamicBean) {

                        if (paga == 1) {
                            refreshLayout.finishRefresh();
                            commDynamicAdapter.setNewData(recommendedDynamicBean.getData());
                        } else {
                            refreshLayout.finishLoadMore();
                            commDynamicAdapter.addData(recommendedDynamicBean.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                    }
                });
    }

    /**
     * 点赞
     *
     * @param userId
     * @param targetId
     * @param type
     * @param hand
     * @param pos
     * @param a        a=1 点赞 a=2 收藏
     */
    private void fbDynamic(String userId, String targetId, String type, String hand, int pos, int a) {

        RxUtils.loading(commonModel.dynamic_praise(userId, targetId, type, hand), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (a == 1) {
                            commDynamicAdapter.notifyItemChanged(pos, "like");
                        } else if (a == 2) {
                            commDynamicAdapter.notifyItemChanged(pos, "likeSC");
                        }

                    }
                });
    }

    /**
     * @param userId
     * @param targetId
     * @param type
     * @param hand
     * @param pos
     * @param a        a=1 取消点赞, a=2 取消收藏
     */
    private void cancelDynamic(String userId, String targetId, String type, String hand, int pos, int a) {
        RxUtils.loading(commonModel.dynamic_praise(userId, targetId, type, hand), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (a == 1) {
                            commDynamicAdapter.notifyItemChanged(pos, "unlike");
                        } else if (a == 2) {
                            commDynamicAdapter.notifyItemChanged(pos, "unlikeSC");
                        }
                    }
                });
    }

    //adapter的点击事件
    private void setonClick() {
        commDynamicAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(getContext(), DynamicDetailsActivity.class);
            intent.putExtra("id", commDynamicAdapter.getData().get(position).getId());
            startActivity(intent);
        });
        commDynamicAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.dianzan:
                    List<RecommendedDynamicBean.DataBean> data = commDynamicAdapter.getData();
                    RecommendedDynamicBean.DataBean dataBean = data.get(position);
                    int id = dataBean.getId();
                    int is_praise = dataBean.getIs_praise();
                    if (is_praise == 1) {
                        cancelDynamic(user.getUserId() + "", id + "", 1 + "", "del", position, 1);
                    } else {
                        fbDynamic(user.getUserId() + "", id + "", 1 + "", "add", position, 1);
                    }
                    break;
                case R.id.dy_collection:
                    List<RecommendedDynamicBean.DataBean> data1 = commDynamicAdapter.getData();
                    RecommendedDynamicBean.DataBean dataBean1 = data1.get(position);
                    int id1 = dataBean1.getId();
                    int is_collect = dataBean1.getIs_collect();
                    if (is_collect == 1) {
                        cancelDynamic(user.getUserId() + "", id1 + "", 2 + "", "del", position, 2);
                    } else {
                        fbDynamic(user.getUserId() + "", id1 + "", 2 + "", "add", position, 2);
                    }
                    break;
                case R.id.pinglun:
                    Intent intent = new Intent(getContext(), DynamicDetailsActivity.class);
                    intent.putExtra("id", commDynamicAdapter.getData().get(position).getId());
                    startActivity(intent);
                    break;
                case R.id.zhuanfa:
                    sharePos = position;
                    UMWeb web = new UMWeb("https://www.jiyinapp.cn");
                    web.setTitle("积音语音");//标题
                    web.setDescription("快来加入积音语音直播啦！");//描述
                    new ShareAction(getActivity())
                            .withMedia(web)
                            .setDisplayList( SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,
                                    SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN)
                            .setCallback(shareListener)
                            .open();
                    break;
                case R.id.dy_head_image:
                    Intent intent1 = new Intent(getContext(), MyPersonalCenterTwoActivity.class);
                    if (commDynamicAdapter.getData().get(position).getUser_id() == UserManager.getUser().getUserId()) {
                        intent1.putExtra("sign", 0);
                        intent1.putExtra("id", "");
                    } else {
                        intent1.putExtra("sign", 1);
                        intent1.putExtra("id", commDynamicAdapter.getData().get(position).getUser_id() + "");
                    }
                    ArmsUtils.startActivity(intent1);
                    break;
                case R.id.dy_lookmore_tv:
                    Intent intent2 = new Intent(getContext(), DynamicDetailsActivity.class);
                    intent2.putExtra("id", commDynamicAdapter.getData().get(position).getId());
                    startActivity(intent2);
                    break;
            }
        });
    }

    private UMShareListener shareListener = new UMShareListener() {

        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            fenxiang();
        }


        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(), "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(), "取消了", Toast.LENGTH_LONG).show();

        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0x147:
                refreshLayout.autoRefresh();
                break;

        }
    }

    private void fenxiang() {
        RxUtils.loading(commonModel.fenxiang(String.valueOf(UserManager.getUser().getUserId()), String.valueOf(commDynamicAdapter.getData().get(sharePos).getId()), "add"), this)
                .subscribe(new ErrorHandleSubscriber<CommentBean>(mErrorHandler) {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        if (commentBean.getCode() == 1) {
                            showToast(commentBean.getMessage());
                            LogUtils.debugInfo("====成功没有", sharePos + "");
//                            commDynamicAdapter.getData().get(sharePos).setForward_num(commDynamicAdapter.getData().get(sharePos).getForward_num() + 1);
                            commDynamicAdapter.notifyItemChanged(sharePos, "share");
//                            commDynamicAdapter.notifyDataSetChanged();
//                            myList1.setAdapter(commDynamicAdapter);
                        }
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();

        if ("pinglunchenggong".equals(tag)) {
            String msg = event.getMsg();
            LogUtils.debugInfo("====接收", msg);
            String[] split = msg.split(",");
            if (split.length != 2) {
                return;
            }
            if (commDynamicAdapter != null && commDynamicAdapter.getData() != null) {
                List<RecommendedDynamicBean.DataBean> data = commDynamicAdapter.getData();
                for (int a = 0; a < data.size(); a++) {
                    if (split[0].equals(String.valueOf(data.get(a).getId()))) {
                        data.get(a).setTalk_num(Integer.parseInt(split[1]));
                        commDynamicAdapter.notifyDataSetChanged();
                        break;
                    }
                }
            }
        } else if (FABUCHENGGONG.equals(tag)) {
            getNewDynamic(1);
        }
    }
}
