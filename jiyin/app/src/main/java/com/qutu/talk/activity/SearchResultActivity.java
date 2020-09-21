package com.qutu.talk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.qutu.talk.R;
import com.qutu.talk.activity.dashen.DaShenListActivity;
import com.qutu.talk.activity.dynamic.DynamicDetailsActivity;
import com.qutu.talk.activity.my.MyPersonalCenterTwoActivity;
import com.qutu.talk.activity.room.AdminHomeActivity;
import com.qutu.talk.adapter.SearchDynamicAdapter;
import com.qutu.talk.adapter.SearchRoomAdapter;
import com.qutu.talk.adapter.SearchSkillAdapter;
import com.qutu.talk.adapter.SearchUserAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.Search;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.view.MyListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 搜索结果页
 */
public class SearchResultActivity extends MyBaseArmActivity {


    @BindView(R.id.textCancel)
    TextView textCancel;
    @BindView(R.id.llSearch)
    LinearLayout llSearch;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.myList1)
    RecyclerView myList1;
    @BindView(R.id.imgDelete)
    ImageView imgDelete;
    @BindView(R.id.ll2)
    LinearLayout ll2;
    @BindView(R.id.myList2)
    MyListView myList2;
    @BindView(R.id.ll3)
    LinearLayout ll3;
    @BindView(R.id.myList3)
    RecyclerView myList3;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.no_data)
    LinearLayout noData;
    @BindView(R.id.ll0)
    LinearLayout ll0;
    @BindView(R.id.myList0)
    RecyclerView myList0;
    @Inject
    CommonModel commonModel;

    private String name = "";

    private SearchSkillAdapter searchSkillAdapter;
    private SearchUserAdapter searchUserAdapter;
    private SearchRoomAdapter searchRoomAdapter;
    private SearchDynamicAdapter searchDynamicAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_search_result;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        name = getIntent().getStringExtra("name");

        searchSkillAdapter = new SearchSkillAdapter();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        myList0.setLayoutManager(llm);
        myList0.setAdapter(searchSkillAdapter);

        searchUserAdapter = new SearchUserAdapter();
        myList1.setLayoutManager(new LinearLayoutManager(this));
        myList1.setAdapter(searchUserAdapter);

        searchRoomAdapter = new SearchRoomAdapter(this);
        myList2.setAdapter(searchRoomAdapter);

        searchDynamicAdapter = new SearchDynamicAdapter();
        myList3.setLayoutManager(new LinearLayoutManager(this));
        myList3.setAdapter(searchDynamicAdapter);

        loadData();

        searchSkillAdapter.setOnItemClickListener((adapter, view, position) -> {
            Search.DataBean.GmskillBean gmskillBean = searchSkillAdapter.getData().get(position);
            Intent intent = new Intent(this, DaShenListActivity.class);
            intent.putExtra("id", String.valueOf(gmskillBean.getId()));
            intent.putExtra("name", gmskillBean.getName());
            ArmsUtils.startActivity(intent);
        });

        myList2.setOnItemClickListener((parent, view, position, id) -> {
            enterData(searchRoomAdapter.getList_adapter().get(position).getUid() + "",
                    "", commonModel, 1, searchRoomAdapter.getList_adapter().get(position).getHeadimgurl());
        });

        searchDynamicAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(this, DynamicDetailsActivity.class);
            intent.putExtra("id", searchDynamicAdapter.getData().get(position).getId());
            startActivity(intent);
        });
        searchUserAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(SearchResultActivity.this, MyPersonalCenterTwoActivity.class);
            if (searchUserAdapter.getData().get(position).getId() == UserManager.getUser().getUserId()) {
                intent.putExtra("sign", 0);
                intent.putExtra("id", "");
            } else {
                intent.putExtra("sign", 1);
                intent.putExtra("id", searchUserAdapter.getData().get(position).getId() + "");
            }
            ArmsUtils.startActivity(intent);
        });

        searchUserAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.btn_ok:
                    if (searchUserAdapter.getData().get(position).getId() != UserManager.getUser().getUserId()) {
                        if (searchUserAdapter.getData().get(position).getIs_follow() == 1) {
                            cancelFllow(String.valueOf(searchUserAdapter.getData().get(position).getId()), position);
                            searchUserAdapter.getData().get(position).setIs_follow(0);
                            searchUserAdapter.notifyDataSetChanged();
                        } else {
                            fllow(String.valueOf(searchUserAdapter.getData().get(position).getId()), position);
                            searchUserAdapter.getData().get(position).setIs_follow(1);
                            searchUserAdapter.notifyDataSetChanged();
                        }
                    } else {
                        toast("不能关注自己哟~");
                    }

                    break;

            }
        });
    }

    private void loadData() {
        RxUtils.loading(commonModel.merge_search(UserManager.getUser().getUserId() + "", name), this)
                .subscribe(new ErrorHandleSubscriber<Search>(mErrorHandler) {
                    @Override
                    public void onNext(Search search) {
                        List<Search.DataBean.GmskillBean> gmskill = search.getData().getGmskill();
                        List<Search.DataBean.UserBean> user = search.getData().getUser();
                        List<Search.DataBean.RoomsBean> rooms = search.getData().getRooms();
                        List<Search.DataBean.DynamicsBean> dynamics = search.getData().getDynamics();
                        if (gmskill.size() == 0) {
                            ll0.setVisibility(View.GONE);
                            myList0.setVisibility(View.GONE);
                        } else {
                            searchSkillAdapter.setNewData(gmskill);
                        }

                        if (user.size() == 0) {
                            ll1.setVisibility(View.GONE);
                            myList1.setVisibility(View.GONE);
                        } else {
                            searchUserAdapter.setNewData(user);
                        }

                        if (rooms.size() == 0) {
                            ll2.setVisibility(View.GONE);
                            myList2.setVisibility(View.GONE);
                        } else {
                            searchRoomAdapter.getList_adapter().clear();
                            searchRoomAdapter.getList_adapter().addAll(rooms);
                            searchRoomAdapter.notifyDataSetChanged();
                        }

                        if (dynamics.size() == 0) {
                            ll3.setVisibility(View.GONE);
                            myList3.setVisibility(View.GONE);
                        } else {
                            searchDynamicAdapter.setNewData(dynamics);
                        }
                        if (gmskill.size() == 0 && user.size() == 0 && rooms.size() == 0 && dynamics.size() == 0) {
                            noData.setVisibility(View.VISIBLE);
                            scrollView.setVisibility(View.GONE);
                        } else {
                            noData.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }


    @OnClick({R.id.llSearch, R.id.ll1, R.id.ll2, R.id.ll3, R.id.textCancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.llSearch:
                finish();
                break;
            case R.id.ll1:
                Intent intent = new Intent(this, SearchUserActivity.class);
                intent.putExtra("name", name);
                ArmsUtils.startActivity(intent);
                break;
            case R.id.ll2:
                Intent intent2 = new Intent(this, SearchRoomActivity.class);
                intent2.putExtra("name", name);
                ArmsUtils.startActivity(intent2);
                break;
            case R.id.ll3:
                Intent intent3 = new Intent(this, SearchDynamicActivity.class);
                intent3.putExtra("name", name);
                ArmsUtils.startActivity(intent3);
                break;
            case R.id.textCancel:
                finish();
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        LogUtils.debugInfo("====onRestart");
        if (AdminHomeActivity.isStart && AdminHomeActivity.isTop) {
            startActivity(new Intent(this, AdminHomeActivity.class));
        }
    }

    /**
     * 关注
     */
    private void fllow(String id, int pos) {
        RxUtils.loading(commonModel.follow(String.valueOf(UserManager.getUser().getUserId()), id), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean microphone) {
                        showToast("关注成功");
                    }
                });
    }


    /**
     * 取消关注
     */
    private void cancelFllow(String id, int pos) {
        RxUtils.loading(commonModel.cancel_follow(String.valueOf(UserManager.getUser().getUserId()), id), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean microphone) {
                        showToast("取消成功");
                    }
                });
    }
}
