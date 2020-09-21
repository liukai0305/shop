package com.qutu.talk.activity.room;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.immersionbar.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.utils.LogUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.qutu.talk.R;
import com.qutu.talk.adapter.RoomCategoryAdapter;
import com.qutu.talk.adapter.RoomCategoryAdapterOne;
import com.qutu.talk.adapter.RoomCategoryAdapterTwo;
import com.qutu.talk.adapter.RoomImgyAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.EnterRoom;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.Register;
import com.qutu.talk.bean.RoomInfoBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.BaseUtils;
import com.qutu.talk.utils.Constant;
import com.qutu.talk.utils.MyUtil;
import com.qutu.talk.utils.PhotoWindow;
import com.qutu.talk.utils.PwdEditText;
import com.qutu.talk.utils.SdcardTools;
import com.qutu.talk.utils.TextNumUtil;
import com.qutu.talk.view.ShapeTextView;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.qutu.talk.activity.my.ModifyDataActivity.REQUEST_CODE_SELECT;

/**
 * 房间设置
 */
public class RoomSettingActivity extends MyBaseArmActivity {

    @Inject
    CommonModel commonModel;

    @BindView(R.id.top_btn)
    RelativeLayout topBtn;
    @BindView(R.id.room_set_back)
    ImageView roomSetBack;
    @BindView(R.id.toolbar_back)
    RelativeLayout toolbarBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rightConfirm)
    ShapeTextView rightConfirm;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.room_set_head)
    QMUIRadiusImageView roomSetHead;
    @BindView(R.id.room_set_pw)
    PwdEditText roomSetPw;
    @BindView(R.id.room_set_name_num)
    TextView roomSetNameNum;
    @BindView(R.id.room_set_rv_one)
    RecyclerView roomSetRvOne;
    @BindView(R.id.room_set_rv_two)
    RecyclerView roomSetRvTwo;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.room_set_gg_edit)
    EditText roomSetGgEdit;
    @BindView(R.id.room_set_gg_num)
    TextView roomSetGgNum;
    @BindView(R.id.room_set_name)
    EditText roomSetName;
    @BindView(R.id.room_setting_scrollview)
    NestedScrollView roomScrollView;
    @BindView(R.id.mView)
    LinearLayout mView;
    @BindView(R.id.mRootView)
    RelativeLayout rootView;
    @BindView(R.id.imgTop)
    ImageView imagTop;

    private RoomImgyAdapter roomImgyAdapter;
    //    private RoomCategoryAdapterOne mRoomCategoryAdapterOne;
    private RoomCategoryAdapterTwo mRoomCategoryAdapterTwo;
    private RoomInfoBean.DataBean mDataBean;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private ArrayList<ImageItem> selImageList = new ArrayList<>();
    private String strGao = "";
    private String imgUrl = "";
    private String imgBackUrl = "";
    //    ;
    private String isHome;
    private String pass = "";

    //输入框初始值
    private int num = 0;
    //输入框最大值
    public int mMaxNum = 300;
    //
    EnterRoom mEnterRoom;

    private View myLayout;
    private int statusBarHeight;
//    private ViewTreeObserver.OnGlobalLayoutListener listener;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//    }

    @Override
    public void onResume() {
        super.onResume();
//        if (!TextUtils.isEmpty(isHome)) {
            toolbarBack.setOnClickListener(v -> {
                startActivity(new Intent(this, AdminHomeActivity.class));
                finish();
            });
//        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, AdminHomeActivity.class));
        finish();
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_room_setting;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        ImmersionBar.with(this)
                .reset()
                .titleBar(topBtn)
                .keyboardEnable(true)
                .init();

        isHome = getIntent().getStringExtra("isHome");
        myLayout = getWindow().getDecorView();
        mEnterRoom = (EnterRoom) getIntent().getSerializableExtra("enterRoom");

        roomSetPw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

        roomImgyAdapter = new RoomImgyAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(roomImgyAdapter);

//        mRoomCategoryAdapterOne = new RoomCategoryAdapterOne();
//        GridLayoutManager gm = new GridLayoutManager(this, 5);
//        roomSetRvOne.setLayoutManager(gm);
//        roomSetRvOne.setAdapter(mRoomCategoryAdapterOne);

        mRoomCategoryAdapterTwo = new RoomCategoryAdapterTwo();
        GridLayoutManager gm1 = new GridLayoutManager(this, 4);
        roomSetRvTwo.setLayoutManager(gm1);
        roomSetRvTwo.setAdapter(mRoomCategoryAdapterTwo);

        loadData();
        setAdapterClick();

        getEditTextStr();

//        listener = new ViewTreeObserver.OnGlobalLayoutListener() {
//
//            @Override
//            public void onGlobalLayout() {
//
//                Rect rect = new Rect();
//                mView.getWindowVisibleDisplayFrame(rect);
//                int height = myLayout.getRootView().getHeight();
//                int heightDiff = height - (rect.bottom - rect.top);
//                if (heightDiff > 100) {
//                    // 如果超过100个像素，它可能是一个键盘。获取状态栏的高度
//                    statusBarHeight = 0;
//                }
//                try {
//                    Class<?> c = Class.forName("com.android.internal.R$dimen");
//                    Object obj = c.newInstance();
//                    Field field = c.getField("status_bar_height");
//                    int x = Integer.parseInt(field.get(obj).toString());
//                    statusBarHeight = getResources().getDimensionPixelSize(x);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                int realKeyboardHeight = heightDiff - statusBarHeight; // 键盘的高度
//
//                int screeHeight = mView.getRootView().getHeight();
//                int mainInvisibleHeight = mView.getRootView().getHeight() - rect.bottom;
//                if (mainInvisibleHeight > screeHeight / 4) {
//                    if (isNavigationBarShow()) {
//                        mView.scrollTo(0, realKeyboardHeight - MyUtil.getVirtualBarHeigh(RoomSettingActivity.this));
//                    } else {
//                        mView.scrollTo(0, realKeyboardHeight);
//                    }
//                } else {
//                    mView.scrollTo(0, 0);
//                }
//
//            }
//        };

    }


    private void loadData() {

        RxUtils.loading(commonModel.getRoomInfoThree(isHome), this)
                .subscribe(new ErrorHandleSubscriber<RoomInfoBean>(mErrorHandler) {
                    @Override
                    public void onNext(RoomInfoBean roomInfoBean) {
                        mDataBean = roomInfoBean.getData().get(0);
//                        List<RoomInfoBean.DataBean.RoomsCateBean> rooms_cate = dataBean.getRooms_cate();
                        //背景
                        if (!TextUtils.isEmpty(mDataBean.getBack_img())) {
                            loadImage(roomSetBack, mDataBean.getBack_img(), 0);
                        }
                        if (mDataBean.getFree_mic().equals("0")) {
                            imagTop.setSelected(false);
                        } else {
                            imagTop.setSelected(true);
                        }
                        //小的图片
                        if (!TextUtils.isEmpty(mDataBean.getRoom_cover())) {
                            loadImage(roomSetHead, mDataBean.getRoom_cover(), R.mipmap.fabu_shangchuan);
                        } else {
                            roomSetHead.setImageResource(R.mipmap.fabu_shangchuan);
                        }
                        //房间名称
                        if (!TextUtils.isEmpty(mDataBean.getRoom_name())) {
                            roomSetName.setText(mDataBean.getRoom_name());
                        }
                        //房间密码
                        if (!TextUtils.isEmpty(mDataBean.getRoom_pass())) {
                            roomSetPw.setText(mDataBean.getRoom_pass());
                        }
                        //房间公告
                        if (!TextUtils.isEmpty(mDataBean.getRoom_intro())) {
                            roomSetGgEdit.setText(mDataBean.getRoom_intro());
                        }
                        //背景
                        String back_img = mDataBean.getBack_img();
                        List<RoomInfoBean.DataBean.BackgroundsBean> backgrounds = mDataBean.getBackgrounds();
                        if (backgrounds.size() != 0) {
                            for (RoomInfoBean.DataBean.BackgroundsBean list : backgrounds) {
                                if (TextUtils.equals(String.valueOf(list.getImg()), back_img)) {
                                    list.setIs_check(1);
                                } else {
                                    list.setIs_check(0);
                                }
                            }
                            roomImgyAdapter.setNewData(mDataBean.getBackgrounds());
                        }

                        //房间类型第一种选择默认
//                        if (!TextUtils.isEmpty(mDataBean.getRoom_class())) {
                        String room_class = mDataBean.getRoom_class();
                        List<RoomInfoBean.DataBean.RoomsCateBean> rooms_cate = mDataBean.getRooms_cate();
//                            if (rooms_cate.size() != 0) {
//                                for (RoomInfoBean.DataBean.RoomsCateBean list : rooms_cate) {
//                                    if (TextUtils.equals(String.valueOf(list.getId()), room_class)) {
//                                        list.setIs_check(1);
//                                    } else {
//                                        list.setIs_check(0);
//                                    }
//                                }
//                                mRoomCategoryAdapterOne.setNewData(mDataBean.getRooms_cate());
//                            }
//
//                            //房间类型第二种选择默认
                        String room_type = mDataBean.getRoom_type();
                        for (int a = 0; a < rooms_cate.size(); a++) {
                            List<RoomInfoBean.DataBean.RoomsCateBean.ChildrenBean> children = rooms_cate.get(a).getChildren();
                            for (RoomInfoBean.DataBean.RoomsCateBean.ChildrenBean list : children) {
                                if (TextUtils.equals(String.valueOf(list.getId()), room_type)) {
                                    list.setIs_check(1);
                                } else {
                                    list.setIs_check(0);
                                }
                            }
                            mRoomCategoryAdapterTwo.addData(rooms_cate.get(a).getChildren());
                        }
//                        }
                    }
                });
    }

    //适配器的点击事件
    private void setAdapterClick() {
        //背景的适配器点击事件
        roomImgyAdapter.setOnItemClickListener((adapter, view, position) -> {
            List<RoomInfoBean.DataBean.BackgroundsBean> data = roomImgyAdapter.getData();
            for (RoomInfoBean.DataBean.BackgroundsBean list : data) {
                list.setIs_check(0);
            }
            data.get(position).setIs_check(1);
            roomImgyAdapter.notifyDataSetChanged();
        });

        //房间类型一级选择点击事件
//        mRoomCategoryAdapterOne.setOnItemClickListener((adapter, view, position) -> {
//            String room_type = mDataBean.getRoom_type();
//            List<RoomInfoBean.DataBean.RoomsCateBean> data = mRoomCategoryAdapterOne.getData();
//            for (RoomInfoBean.DataBean.RoomsCateBean list : data) {
//                list.setIs_check(0);
//            }
//            data.get(position).setIs_check(1);
//            mRoomCategoryAdapterOne.notifyDataSetChanged();
//
//            if (position == 0) {
//                List<RoomInfoBean.DataBean.RoomsCateBean.ChildrenBean> children = data.get(0).getChildren();
//                for (RoomInfoBean.DataBean.RoomsCateBean.ChildrenBean list : children) {
//                    if (TextUtils.equals(String.valueOf(list.getId()), room_type)) {
//                        list.setIs_check(1);
//                    } else {
//                        list.setIs_check(0);
//                    }
//                }
//                mRoomCategoryAdapterTwo.setNewData(data.get(0).getChildren());
//            } else if (position == 1) {
//                List<RoomInfoBean.DataBean.RoomsCateBean.ChildrenBean> children = data.get(1).getChildren();
//                for (RoomInfoBean.DataBean.RoomsCateBean.ChildrenBean list : children) {
//                    if (TextUtils.equals(String.valueOf(list.getId()), room_type)) {
//                        list.setIs_check(1);
//                    } else {
//                        list.setIs_check(0);
//                    }
//                }
//                mRoomCategoryAdapterTwo.setNewData(data.get(1).getChildren());
//            } else if (position == 2) {
//                List<RoomInfoBean.DataBean.RoomsCateBean.ChildrenBean> children = data.get(2).getChildren();
//                for (RoomInfoBean.DataBean.RoomsCateBean.ChildrenBean list : children) {
//                    if (TextUtils.equals(String.valueOf(list.getId()), room_type)) {
//                        list.setIs_check(1);
//                    } else {
//                        list.setIs_check(0);
//                    }
//                }
//                mRoomCategoryAdapterTwo.setNewData(data.get(2).getChildren());
//            }
//        });

        //房间类型二级选择
        mRoomCategoryAdapterTwo.setOnItemClickListener((adapter, view, position) -> {
            List<RoomInfoBean.DataBean.RoomsCateBean.ChildrenBean> data = mRoomCategoryAdapterTwo.getData();
            for (RoomInfoBean.DataBean.RoomsCateBean.ChildrenBean list : data) {
                list.setIs_check(0);
            }
            data.get(position).setIs_check(1);
            mRoomCategoryAdapterTwo.notifyDataSetChanged();
        });
    }

    private void getEditTextStr() {
        TextNumUtil.initTextNum(RoomSettingActivity.this, roomSetName, roomSetNameNum);

        //实时记录公告字数
        roomSetGgEdit.addTextChangedListener(new TextWatcher() {
            //记录输入的字数
            private CharSequence wordNum;
            private int selectionStart;
            private int selectionEnd;
            //记录空格跟换行的次数；
            private int kh = 0;
            String regex = "\\n";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//实时记录输入的字数
//                Pattern pattern = Pattern.compile(regex);
//                Matcher matcher = pattern.matcher(s);
//                if (s.equals("") || matcher.matches()) {
//                    kh++;
//                }
//                LogUtils.debugInfo("====数量数量",kh+"");
                wordNum = s;
            }

            @Override
            public void afterTextChanged(Editable s) {
//                s.toString().trim().replace(" ","");
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(s.toString());
                String strAfter = m.replaceAll("");
//                s.toString().trim().replaceAll(" ","").length()
                int number = num + strAfter.length();
                //TextView显示剩余字数
                roomSetGgNum.setText(number + "");
                selectionStart = roomSetGgEdit.getSelectionStart();
                selectionEnd = roomSetGgEdit.getSelectionEnd();
                //判断大于最大值
//                int length = wordNum.length();
//                int i = length - kh;
                if (wordNum.length() > mMaxNum) {
                    //删除多余输入的字（不会显示出来）
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionEnd;
                    roomSetGgEdit.setText(s);
                    roomSetGgEdit.setSelection(tempSelection);//设置光标在最后
//                    roomSetGgNum.setText("300");
                    //吐司最多输入300字
                    toast("最多输入300字");
                }
            }
        });

        roomSetPw.setOnTextChangeListener(new PwdEditText.OnTextChangeListener() {
            @Override
            public void onTextChange(String pwd) {
                if (pwd.length() == roomSetPw.getTextLength()) {
                    pass = pwd;
                } else if (pwd.length() > 0 && pwd.length() < roomSetPw.getTextLength()) {
//                    toast("房间密码是四位哟");
                    pass = pwd;
                    return;
                } else {
                    pass = "";
                }
            }
        });

//        //键盘弹起，布局上移
//        roomSetGgEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
////                roomSetPw.clearFocus();
////                roomSetName.clearFocus();
//                if (hasFocus) {
//                    mView.getViewTreeObserver().addOnGlobalLayoutListener(listener);
//                } else {
//                    mView.scrollTo(0, 0);
//                    mView.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
//                }
//            }
//        });
    }

    /**
     * 重新获取数据给房间
     */
    private void loadRoomData() {
//        RxUtils.loading(commonModel.getRoomInfo(isHome), this)
//                .subscribe(new ErrorHandleSubscriber<EnterRoom>(mErrorHandler) {
//                    @Override
//                    public void onNext(EnterRoom enterRoom) {
                        toast("修改成功！");
                        EventBus.getDefault().post(new FirstEvent("指定发送", Constant.FANGJIANSHEZHI));
                        if (!TextUtils.isEmpty(isHome)) {
                            startActivity(new Intent(RoomSettingActivity.this, AdminHomeActivity.class));
                            finish();
                        } else {
                            finish();
                        }
//                    }
//                });
    }


    private void showPop() {
        PhotoWindow photoWindow = new PhotoWindow(this);
        photoWindow.showAtLocation(roomSetHead, Gravity.BOTTOM, 0, 0);
        photoWindow.getTake_photo().setOnClickListener(v -> {
            photoWindow.dismiss();
            if (SdcardTools.sdcard()) {
                RxPermissions rxPermissions = new RxPermissions(this);
                rxPermissions
                        .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(granted -> {
                            if (granted) { // Always true pre-M
                                //相机
                                Intent intent = new Intent(RoomSettingActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                            }
                        });
            } else {
                Toast.makeText(this, "sd卡不可用", Toast.LENGTH_SHORT).show();
            }
        });
        photoWindow.getChose_pic().setOnClickListener(v -> {
            photoWindow.dismiss();
            RxPermissions rxPermissions = new RxPermissions(this);
            rxPermissions
                    .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(granted -> {
                        if (granted) { // Always true pre-M
                            // 跳转到相册
                            ImagePicker.getInstance().setSelectLimit(1);
                            ImagePicker.getInstance().setMultiMode(false);
                            Intent intent = new Intent(RoomSettingActivity.this, ImageGridActivity.class);
                            //显示选中的图片
                            startActivityForResult(intent, REQUEST_CODE_SELECT);
                        }
                    });
        });
        photoWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.alpha = 1f;
            getWindow().setAttributes(params);
        });
    }

    @OnClick({R.id.rightConfirm, R.id.room_set_head, R.id.imgTop,R.id.room_set_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.room_set_back:
                startActivity(new Intent(RoomSettingActivity.this, AdminHomeActivity.class));
                finish();
                break;
            case R.id.rightConfirm:
                String name = roomSetName.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    toast("房间名字不能为空");
                    return;
                }
                strGao = roomSetGgEdit.getText().toString().trim();
                if (pass.length() != 4 && pass.length() != 0) {
                    toast("房间密码必须是四位哟！");
                    return;
                }

                String roomClass = "";
                String roomType = "";
                String room_background = "";
//                String roomBiao = "";

//                List<RoomInfoBean.DataBean.RoomsCateBean> data = mRoomCategoryAdapterOne.getData();
//                if (data == null || data.size() == 0) {
//                    toast("请退出重新获取数据");
//                    return;
//                }
//                for (RoomInfoBean.DataBean.RoomsCateBean list : data) {
//                    if (list.getIs_check() == 1) {
//                        roomClass = String.valueOf(list.getId());
//                        LogUtils.debugInfo("====roomClass", roomClass);
//                    }
//                }

                List<RoomInfoBean.DataBean.RoomsCateBean.ChildrenBean> data1 = mRoomCategoryAdapterTwo.getData();
                if (data1 == null || data1.size() == 0) {
                    toast("请退出重新获取数据");
                    return;
                }
                for (RoomInfoBean.DataBean.RoomsCateBean.ChildrenBean list : data1) {
                    if (list.getIs_check() == 1) {
                        roomClass = String.valueOf(list.getPid());
                        roomType = String.valueOf(list.getId());
//                        roomBiao = String.valueOf(list.getPid());
                        LogUtils.debugInfo("====roomType", roomType);
                    }
                }

                List<RoomInfoBean.DataBean.BackgroundsBean> data2 = roomImgyAdapter.getData();
                if (data2 == null || data2.size() == 0) {
                    toast("请退出重新获取数据");
                    return;
                }
                for (RoomInfoBean.DataBean.BackgroundsBean list : data2) {
                    if (list.getIs_check() == 1) {
                        room_background = String.valueOf(list.getId());
                        imgBackUrl = list.getImg();
                    }
                }
                if (selImageList.size() > 0) {
                    imgUrl = "data:image/png;base64," + BaseUtils.file2Base64(selImageList.get(0).path);
                } else if (TextUtils.isEmpty(imgUrl)) {
                    imgUrl = "";
                }

//                if (!roomClass.equals(roomBiao)) {
//                    toast("请选择标签");
//                    return;
//                }

                if (TextUtils.isEmpty(name)) {
                    name = "";
                } else if (TextUtils.isEmpty(roomClass)) {
                    roomClass = "";
                } else if (TextUtils.isEmpty(roomType)) {
                    roomType = "";
                } else if (TextUtils.isEmpty(room_background)) {
//                    showToast("请选择背景！");
                    room_background = "";
                } else if (TextUtils.isEmpty(strGao)) {
//                    showToast("请填写公告！");
                    strGao = "";
                }


                showDialogLoding();
                RxUtils.loading(commonModel.create_or_edit_room(pass, name, roomClass, roomType,
                        strGao, room_background,
                        isHome, imgUrl, mDataBean.getFree_mic()), this)
                        .subscribe(new ErrorHandleSubscriber<Register>(mErrorHandler) {
                            @Override
                            public void onNext(Register roomBg) {
                                loadRoomData();
                                disDialogLoding();
//                                loadData();
//                                loadImage(roomSetBack, imgBackUrl, 0);
                            }

                            @Override
                            public void onError(Throwable t) {
                                super.onError(t);
                                disDialogLoding();
                            }
                        });

                break;
            case R.id.room_set_head:
                showPop();
                hideKeyboard(roomSetHead);
                break;
            case R.id.imgTop:
                if (mDataBean.getFree_mic().equals("0")) {
                    mDataBean.setFree_mic("1");
                    imagTop.setSelected(true);
                } else {
                    mDataBean.setFree_mic("0");
                    imagTop.setSelected(false);
                }
                break;
        }
    }


    /*-----------------------图片选择回调------------------------------*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        ArrayList<ImageItem> tempList;
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (requestCode == REQUEST_CODE_SELECT) {
                tempList = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (tempList == null) {
                    return;
                }
                selImageList.clear();
                selImageList.addAll(tempList);
                if (selImageList.size() > 0) {
                    GlideArms.with(RoomSettingActivity.this)
                            .load(selImageList.get(0).path)
                            .placeholder(R.drawable.ic_default_image)
                            .error(R.drawable.ic_default_image)
                            .circleCrop()
                            .into(roomSetHead);
                }
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!TextUtils.isEmpty(isHome)) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                startActivity(new Intent(this, AdminHomeActivity.class));
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public boolean isNavigationBarShow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            Point realSize = new Point();
            display.getSize(size);
            display.getRealSize(realSize);
            boolean result = realSize.y != size.y;
            return realSize.y != size.y;
        } else {
            boolean menu = ViewConfiguration.get(this).hasPermanentMenuKey();
            boolean back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            if (menu || back) {
                return false;
            } else {
                return true;
            }
        }
    }

}
