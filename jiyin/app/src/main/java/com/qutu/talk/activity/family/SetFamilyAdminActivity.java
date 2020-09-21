package com.qutu.talk.activity.family;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.qutu.talk.R;
import com.qutu.talk.activity.room.AdminHomeActivity;
import com.qutu.talk.adapter.AdminFamilyUserAdapter;
import com.qutu.talk.adapter.AdminFamilyUserAdapter2;
import com.qutu.talk.adapter.AdminFamilyUserAdapter3;
import com.qutu.talk.adapter.AdminUserAdapter;
import com.qutu.talk.adapter.AdminUserAdapter2;
import com.qutu.talk.adapter.AdminUserAdapter3;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.AdminUser;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.FamilyUser;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.GetAdminResult;
import com.qutu.talk.bean.SearchAdmin;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.Constant;
import com.qutu.talk.view.ClearEditText;
import com.qutu.talk.view.MyListView;
import com.qutu.talk.view.ShapeTextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * 设置群组管理员
 */
public class SetFamilyAdminActivity extends MyBaseArmActivity {

    @BindView(R.id.toolbar_back)
    RelativeLayout toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rightTitle)
    TextView rightTitle;
    @BindView(R.id.rightConfirm)
    ShapeTextView rightConfirm;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edt_login_pass)
    ClearEditText edtLoginPass;
    @BindView(R.id.btn_ok)
    ShapeTextView btnOk;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.myList1)
    MyListView myList1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.myList2)
    MyListView myList2;

    @Inject
    CommonModel commonModel;
    @BindView(R.id.scroll)
    ScrollView scroll;
    @BindView(R.id.myList3)
    MyListView myList3;
    @BindView(R.id.card_admin)
    CardView mCardView;
//    private String uid;

    private AdminFamilyUserAdapter adminUserAdapter;
    private AdminFamilyUserAdapter2 adminUserAdapter2;
    private AdminFamilyUserAdapter3 adminUserAdapter3;

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
        return R.layout.activity_set_family_admin;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
//        uid = getIntent().getStringExtra("uid");
        adminUserAdapter = new AdminFamilyUserAdapter(this);
        myList1.setAdapter(adminUserAdapter);
        adminUserAdapter2 = new AdminFamilyUserAdapter2(this);
        myList2.setAdapter(adminUserAdapter2);
        adminUserAdapter3 = new AdminFamilyUserAdapter3(this);
        myList3.setAdapter(adminUserAdapter3);
        loadData();
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbarBack.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    private void loadData() {
        RxUtils.loading(commonModel.getAdminList(""), this)
                .subscribe(new ErrorHandleSubscriber<GetAdminResult>(mErrorHandler) {
                    @Override
                    public void onNext(GetAdminResult adminUser) {

                        List<FamilyUser> adminList = adminUser.getData().getAdmin_list();

                        List<FamilyUser> user_list = adminUser.getData().getUser_list();

                        text1.setText("管理员" + adminList.size());

                        text2.setText("家族成员" + user_list.size() + "人");

                        adminUserAdapter.getList_adapter().clear();
                        adminUserAdapter.getList_adapter().addAll(adminList);
                        adminUserAdapter.notifyDataSetChanged();

                        if(adminList == null || adminList.size()==0){
                            mCardView.setVisibility(View.GONE);
                        } else {
                            mCardView.setVisibility(View.VISIBLE);
                        }

                        adminUserAdapter2.getList_adapter().clear();
                        adminUserAdapter2.getList_adapter().addAll(user_list);
                        adminUserAdapter2.notifyDataSetChanged();

                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            startActivity(new Intent(this, AdminHomeActivity.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    @OnClick(R.id.btn_ok)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                loadSearch();
                break;
        }
    }

    /**
     * 搜索
     */
    private void loadSearch() {
        String s = edtLoginPass.getText().toString();
        if (TextUtils.isEmpty(s)) {
            scroll.setVisibility(View.VISIBLE);
            myList3.setVisibility(View.GONE);
        } else {

            RxUtils.loading(commonModel.getAdminList(s), this)
                    .subscribe(new ErrorHandleSubscriber<GetAdminResult>(mErrorHandler) {
                        @Override
                        public void onNext(GetAdminResult adminUser) {
                            if (adminUser != null && adminUser.getData() != null) {

                                List<FamilyUser> adminList = adminUser.getData().getAdmin_list();

                                List<FamilyUser> user_list = adminUser.getData().getUser_list();

                                List<FamilyUser> allList = new ArrayList<>();
                                allList.addAll(adminList);
                                allList.addAll(user_list);

                                scroll.setVisibility(View.GONE);
                                myList3.setVisibility(View.VISIBLE);
                                adminUserAdapter3.getList_adapter().clear();
                                adminUserAdapter3.getList_adapter().addAll(allList);
                                adminUserAdapter3.notifyDataSetChanged();
                            }else {
                                toast(adminUser.getMessage());
                            }
                        }
                    });
        }
    }

    /**
     * 取消管理员
     * @param userId
     */
    public void remove_admin(String userId,int type){
        RxUtils.loading(commonModel.actionCancelAdmin(userId), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        EventBus.getDefault().post(new FirstEvent(userId,Constant.QuxiaoGUANLI));
                        if(type == 2){
                            loadSearch();
                        }else {
                            loadData();
                        }
                    }
                });
    }

    /**
     * 设置管理员
     * @param userId
     */
    public void is_admin(String userId,int type){
        RxUtils.loading(commonModel.actionSetAdmin(userId), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        EventBus.getDefault().post(new FirstEvent(userId,Constant.SHEZHIGUANLI));
                        if(type == 2){
                            loadSearch();
                        }else {
                            loadData();
                        }
                    }
                });
    }
}
