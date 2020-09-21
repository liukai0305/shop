package com.qutu.talk.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qutu.talk.R;
import com.qutu.talk.activity.FamilyIntroduceActivity;
import com.qutu.talk.activity.WaitFamilyVerifyActivity;
import com.qutu.talk.activity.family.FamilyListActivity;
import com.qutu.talk.activity.family.FamilyUserListActivity;
import com.qutu.talk.activity.room.FamilyGxRankActivity;
import com.qutu.talk.adapter.FamilyPartyAdapter;
import com.qutu.talk.adapter.FamilyPersonAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.MyBaseArmFragment;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.CommentBean;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.GetFamilyDetailResult;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.popup.FamilyMeltingDialog;
import com.qutu.talk.popup.FamilyTopWindow;
import com.qutu.talk.popup.FamilyUpgradeDialog;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.Constant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.qutu.talk.utils.Constant.FAMILYXIUGAI;

public class FamilyDetailsFragment extends MyBaseArmFragment {

    @Inject
    CommonModel commonModel;
    Unbinder unbinder;
    @BindView(R.id.toolbar_iv_back)
    ImageView toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.img_bar_right)
    ImageView imgBarRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_family_head)
    QMUIRadiusImageView imgFamilyHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_ranking)
    TextView tvRanking;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_exp_rank)
    TextView tvExpRank;
    @BindView(R.id.iv_upgrade)
    ImageView ivUpgrade;
    @BindView(R.id.tv_upgrade)
    TextView tvUpgrade;
    @BindView(R.id.iv_gift_melting)
    ImageView ivGiftMelting;
    @BindView(R.id.tv_gift_melting)
    TextView tvGiftMelting;
    @BindView(R.id.ll_family_user_list)
    LinearLayout llFamilyUserList;
    @BindView(R.id.recycler_person)
    RecyclerView recyclerPerson;
    @BindView(R.id.tv_notice)
    TextView tvNotice;
    @BindView(R.id.recycler_party)
    RecyclerView recyclerParty;
    @BindView(R.id.cv_apply)
    CardView cvApply;
    private String family_id;
    private FamilyPersonAdapter familyPersonAdapter;
    private FamilyPartyAdapter familyPartyAdapter;

    @Override
    public View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = null;
        if (mRootView == null) {
            mRootView = ArmsUtils.inflate(getActivity(), R.layout.fragment_family_details);
            unbinder = ButterKnife.bind(this, mRootView);
        }
        return mRootView;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        showDialogLoding();
        family_id = getArguments().getString("family_id");
        if(getArguments().getBoolean("show_back",false)){
            toolbarBack.setVisibility(View.VISIBLE);
            toolbarBack.setOnClickListener(v -> getActivity().finish());
        }else{
            toolbarBack.setVisibility(View.GONE);
        }

        initAdapter();
        getFamilyDetailsData();
    }

    private void initAdapter(){
        familyPersonAdapter=new FamilyPersonAdapter(R.layout.item_family_person,null);
        recyclerPerson.setLayoutManager(new GridLayoutManager(getActivity(), 5, GridLayoutManager.VERTICAL, false));
        recyclerPerson.setAdapter(familyPersonAdapter);

        familyPartyAdapter=new FamilyPartyAdapter(R.layout.item_family_party);
        recyclerParty.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerParty.setAdapter(familyPartyAdapter);
        familyPartyAdapter.setOnItemClickListener((adapter, view, position) -> {
            enterData(String.valueOf(familyPartyAdapter.getData().get(position).getUid()), "", commonModel, 1, familyPartyAdapter.getData().get(position).getRoom_cover());
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    private GetFamilyDetailResult mBean;

    //获取家族详情的数据
    private void getFamilyDetailsData() {
        RxUtils.loading(commonModel.getFamilyDetail(family_id), this)
                .subscribe(new ErrorHandleSubscriber<GetFamilyDetailResult>(mErrorHandler) {
                    @Override
                    public void onNext(GetFamilyDetailResult getFamilyDetailResult) {
                        if(getFamilyDetailResult.getData().is_success()==0){
                            ArmsUtils.startActivity(WaitFamilyVerifyActivity.class);
                            getActivity().finish();
                        }
                        mBean = getFamilyDetailResult;
                        setFamilyData(getFamilyDetailResult);
                    }
                });
    }


    //设置家族数据
    private void setFamilyData(GetFamilyDetailResult getFamilyDetailResult) {

        //家族的头像
        ArmsUtils.obtainAppComponentFromContext(getActivity())
                .imageLoader()
                .loadImage(getActivity(), ImageConfigImpl
                        .builder()
                        .url(getFamilyDetailResult.getData().getImage())
                        .placeholder(R.mipmap.no_tou)
                        .imageView(imgFamilyHead)
                        .errorPic(R.mipmap.no_tou)
                        .build());
        tvName.setText(getFamilyDetailResult.getData().getName());
        tvId.setText("家族ID："+getFamilyDetailResult.getData().getFamily_id());
        tvExpRank.setText(
                String.format("月经验：%d 月排名：%d", getFamilyDetailResult.getData().getExp(), getFamilyDetailResult.getData().getPaiming())
        );
        tvNotice.setText(getFamilyDetailResult.getData().getNotice());
        if(getFamilyDetailResult.getData().is_show()==0){
            cvApply.setVisibility(View.GONE);
        }else{
            cvApply.setVisibility(View.VISIBLE);
        }
        if(!TextUtils.isEmpty(getFamilyDetailResult.getData().getJzid())&&
                getFamilyDetailResult.getData().getJzid().equals(getFamilyDetailResult.getData().getFamily_id())){
            tvUpgrade.setVisibility(View.VISIBLE);
            tvRanking.setVisibility(View.VISIBLE);
            tvGiftMelting.setVisibility(View.VISIBLE);
            ivUpgrade.setVisibility(View.VISIBLE);
            ivGiftMelting.setVisibility(View.VISIBLE);
        }else{
            tvUpgrade.setVisibility(View.GONE);
            tvRanking.setVisibility(View.GONE);
            tvGiftMelting.setVisibility(View.GONE);
            ivUpgrade.setVisibility(View.GONE);
            ivGiftMelting.setVisibility(View.GONE);
        }
        familyPersonAdapter.setNewData(getFamilyDetailResult.getData().getFamily_user_list());
        familyPartyAdapter.setNewData(getFamilyDetailResult.getData().getFamily_pdinfo());
        disDialogLoding();
    }

    @OnClick({R.id.img_bar_right,R.id.cv_apply,R.id.tv_upgrade,R.id.tv_gift_melting,R.id.tv_ranking,R.id.ll_family_user_list})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_bar_right:
                if(mBean==null){
                    return;
                }
                FamilyTopWindow familyTopWindow = new FamilyTopWindow(getActivity(), !TextUtils.isEmpty(mBean.getData().getJzid())&&
                        mBean.getData().getJzid().equals(mBean.getData().getFamily_id()));
                familyTopWindow.showAsDropDown(imgBarRight);
                familyTopWindow.getLlExit().setOnClickListener(v -> {
                    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                    builder.setMessage("退出家族后，你所在家族背包的能量豆和礼物会被清空");
                    builder.setPositiveButton("确定", (dialog1, which) -> {
                        RxUtils.loading(commonModel.actionQuitFamily(family_id))
                                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                                    @Override
                                    public void onNext(BaseBean baseBean) {
                                        if(baseBean.getCode() == 1){
                                            dialog1.dismiss();
                                            familyTopWindow.dismiss();
                                            EventBus.getDefault().post(new FirstEvent("", Constant.TUICHUFAMILY));
                                            //跳转到家族列表
                                            Intent intent = new Intent(getActivity(), FamilyListActivity.class);
                                            intent.putExtra("is_family_show",1);
                                            //intent.putExtra("is_god",1);
                                            startActivity(intent);
                                            //退出本页面
                                            getActivity().finish();
                                        }
                                    }
                                });
                    });
                    builder.setNegativeButton("取消",(dialog1, which) -> {
                        dialog1.dismiss();
                        familyTopWindow.dismiss();
                    });
                    builder.show();

                });
                familyTopWindow.getLlIntroduce().setOnClickListener(v -> {
                    ArmsUtils.startActivity(FamilyIntroduceActivity.class);
                });
                break;
            case R.id.cv_apply:
                actionApplyFamily();
                break;
            case R.id.tv_upgrade:
                FamilyUpgradeDialog familyUpgradeDialog = new FamilyUpgradeDialog((MyBaseArmActivity) getActivity(), commonModel, mErrorHandler, 0,Integer.parseInt(family_id));
                familyUpgradeDialog.show();
                break;
            case R.id.tv_gift_melting:
                FamilyMeltingDialog familyMeltingDialog = new FamilyMeltingDialog(getActivity(), commonModel, mErrorHandler, Integer.parseInt(family_id));
                familyMeltingDialog.show();
                break;
            case R.id.tv_ranking:
                Intent intent=new Intent(getActivity(), FamilyGxRankActivity.class);
                intent.putExtra("familyId",Integer.parseInt(family_id));
                ArmsUtils.startActivity(intent);
                break;
            case R.id.ll_family_user_list:
                if(mBean == null){
                    return;
                }

                Intent intent1 = new Intent(getActivity(), FamilyUserListActivity.class);
                intent1.putExtra("family_id",family_id);
                intent1.putExtra("family_name",mBean.getData().getName());
                intent1.putExtra("user_type",Integer.parseInt(mBean.getData().getUser_type()));
                startActivity(intent1);
                break;
        }
    }

    //申请加入家族
    private void actionApplyFamily() {
        RxUtils.loading(commonModel.actionApplyFamily(family_id), this)
                .subscribe(new ErrorHandleSubscriber<CommentBean>(mErrorHandler) {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        if (commentBean.getCode() == 1) {
                            showToast("申请成功,请等待审核");
                            getFamilyDetailsData();
                        }
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if (FAMILYXIUGAI.equals(tag)) {
            getFamilyDetailsData();
        }
    }

}
