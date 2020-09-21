package com.qutu.talk.activity.login;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.utils.LogUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.qutu.talk.R;
import com.qutu.talk.app.Api;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.Login;
import com.qutu.talk.bean.Register;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.BaseUtils;
import com.qutu.talk.utils.PhotoWindow;
import com.qutu.talk.utils.SdcardTools;
import com.qutu.talk.view.ShapeTextView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.qutu.talk.utils.Constant.REGISTER;

/**
 * 完善资料
 */
public class UploadActivity extends MyBaseArmActivity {


    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.edt_login_name)
    EditText edtLoginName;
    @BindView(R.id.edt_code)
    TextView edtCode;
    @BindView(R.id.rl_data)
    RelativeLayout rlData;
    @BindView(R.id.btn_ok)
    ShapeTextView btnOk;


    private String sex;
    String phone;
    String password;
    String nowDate;
    String type;
    String tag;
    String uid;
    String coad;
    String nickName;

    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private ArrayList<ImageItem> selImageList = new ArrayList<>();
    @Inject
    CommonModel commonModel;

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
        return R.layout.activity_upload;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        sex = getIntent().getStringExtra("sex");
        phone = getIntent().getStringExtra("phone");
        password = getIntent().getStringExtra("password");
        type = getIntent().getStringExtra("type");
        uid = getIntent().getStringExtra("uid");
        tag = getIntent().getStringExtra("tag");
        coad = getIntent().getStringExtra("coad");
    }

    @OnClick({R.id.img, R.id.rl_data, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img:
                showPop();
                hideKeyboard(img);
                break;
            case R.id.rl_data:
                hideKeyboard(img);
                //时间选择器
                TimePickerView pvTime = new TimePickerBuilder(UploadActivity.this, (date, v) -> {
                    nowDate = BaseUtils.getNowDate(date);
                    edtCode.setText(nowDate);
                }).build();
                pvTime.show();
                break;
            case R.id.btn_ok:
                nickName = edtLoginName.getText().toString().replace(" ", "").trim();
                if (TextUtils.isEmpty(nowDate)) {
                    showToast("请选择出生日期！");
                } else if (selImageList.size() == 0) {
                    showToast("请选择头像！");
                } else if (TextUtils.isEmpty(nickName)) {
                    showToast("请填写昵称！");
                } else if (nickName.length() > 10) {
                    showToast("用户名最多只有10位");
                } else if(nickName.length() < 2){
                    showToast("用户名不能少于2位");
                } else {

                    LogUtils.debugInfo("sgm:" + BaseUtils.file2Base64(selImageList.get(0).path));
                    showDialogLoding();
                    RxUtils.loading(commonModel.register(phone, sex, nowDate, password, nickName,
                            "data:image/png;base64," + BaseUtils.file2Base64(selImageList.get(0).path), "android", Api.CHANNEL,coad), this)
                            .subscribe(new ErrorHandleSubscriber<Register>(mErrorHandler) {
                                @Override
                                public void onNext(Register codeBean) {
                                    disDialogLoding();
                                    if (codeBean.getCode() == 1) {
                                        showMessage(codeBean.getMessage());
                                        EventBus.getDefault().post(new FirstEvent("指定发送", REGISTER));
                                        finish();
                                    } else {
                                        showMessage(codeBean.getMessage());
                                    }
                                }

                                @Override
                                public void onError(Throwable t) {
                                    super.onError(t);
                                    disDialogLoding();
                                }
                            });
                }
                break;
        }
    }


    private void showPop() {
        PhotoWindow photoWindow = new PhotoWindow(this);
        photoWindow.showAtLocation(img, Gravity.BOTTOM, 0, 0);
        photoWindow.getTake_photo().setOnClickListener(v -> {
            photoWindow.dismiss();
            if (SdcardTools.sdcard()) {
                RxPermissions rxPermissions = new RxPermissions(this);
                rxPermissions
                        .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(granted -> {
                            if (granted) { // Always true pre-M
                                //相机
                                Intent intent = new Intent(UploadActivity.this, ImageGridActivity.class);
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
                            Intent intent = new Intent(UploadActivity.this, ImageGridActivity.class);
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
                    GlideArms.with(UploadActivity.this)
                            .load(selImageList.get(0).path)
                            .placeholder(R.drawable.ic_default_image)
                            .error(R.drawable.ic_default_image)
                            .circleCrop()
                            .into(img);
                }
            }
        }
    }


}
