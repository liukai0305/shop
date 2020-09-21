package com.qutu.talk.activity.my;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.qutu.talk.R;
import com.qutu.talk.adapter.ModifyImageAdapter;
import com.qutu.talk.adapter.ModifyImageTwoAdapter;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.ConstellationBean;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.JsonBean;
import com.qutu.talk.bean.LoginData;
import com.qutu.talk.bean.OtherBean;
import com.qutu.talk.bean.TopThreeImageBean;
import com.qutu.talk.bean.UserBean;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.BaseUtils;
import com.qutu.talk.utils.GetJsonDataUtil;
import com.qutu.talk.utils.PhotoWindow;
import com.qutu.talk.utils.SdcardTools;
import com.qutu.talk.utils.TextNumUtil;
import com.qutu.talk.utils.TimeUtil;
import com.qutu.talk.view.FullyGridLayoutManager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.litepal.LitePal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.qutu.talk.utils.Constant.XIUGAIGERENZILIAOCHENGGONG;

//修改资料

public class ModifyDataActivity extends MyBaseArmActivity {
    @Inject
    CommonModel commonModel;

    //    @BindView(R.id.head_image)
//    CircularImage headImage;
    @BindView(R.id.textSend)
    EditText textSend;
    @BindView(R.id.nick_num)
    TextView nickNum;
    @BindView(R.id.id)
    TextView id;
    @BindView(R.id.sex)
    ImageView sex;
    @BindView(R.id.birthday)
    TextView birthday;
    @BindView(R.id.birthday_btn)
    RelativeLayout birthdayBtn;
    @BindView(R.id.constellation)
    TextView constellation;
    @BindView(R.id.consteation_btn)
    RelativeLayout consteationBtn;
    @BindView(R.id.region)
    TextView region;
    @BindView(R.id.region_btn)
    RelativeLayout regionBtn;
    @BindView(R.id.img_recycylerview)
    RecyclerView imgRecycylerview;
    @BindView(R.id.head_recycylerview)
    RecyclerView headRecycylerview;


    private String nowDate;
    private String sexStr;

    //图片
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_SELECT_TWO = 200;
    private ArrayList<String> headImageList = new ArrayList<>();
    private ArrayList<TopThreeImageBean> selImageList = new ArrayList<>();
    private String headString, imgString1, imgString2, imgString3;
    private String headviewString;

    //地址选择器所用
    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private ArrayList<ImageItem> tempList;
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private static boolean isLoaded = false;

    private Calendar startDate, endDate, seleteDate;

    private ModifyImageAdapter mImgAdapter;
    private ModifyImageTwoAdapter mHeadAdapter;
    private int maxSelectNum = 3;//最多显示的图片数量
    private int headMaxSelectNum = 1;

    private String headStringFan;
    private List<UserBean.DataBean.ImgList> imglist;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了

                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 子线程中解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
//                    toast("把孤单当成晚餐却难以下咽，把黑夜当做温暖却难以入眠。");
                    break;
            }
        }
    };

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_modify_data;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        TextNumUtil.initTextNum(ModifyDataActivity.this, textSend, nickNum);
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);

        mImgAdapter = new ModifyImageAdapter(this, onAddPicClickListener);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        mImgAdapter.setSelectMax(maxSelectNum);
        imgRecycylerview.setLayoutManager(manager);
        imgRecycylerview.setAdapter(mImgAdapter);

        mHeadAdapter = new ModifyImageTwoAdapter(this, onAddPicClickListener2);
        FullyGridLayoutManager manager1 = new FullyGridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        mHeadAdapter.setSelectMax(headMaxSelectNum);
        headRecycylerview.setLayoutManager(manager1);
        headRecycylerview.setAdapter(mHeadAdapter);
        getPerInfo();

    }


    @OnClick({R.id.birthday_btn, R.id.head_image, R.id.region_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.birthday_btn:
                String string = birthday.getText().toString();
                String timeYmd = TimeUtil.getTimeYmd();
                String[] split1 = string.split("-");
                String[] split = timeYmd.split("-");
                seleteDate = Calendar.getInstance();
                startDate = Calendar.getInstance();
                endDate = Calendar.getInstance();
                startDate.set(1900, 0, 1);
                endDate.set(Integer.parseInt(split[0]), Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2]));
                seleteDate.set(Integer.parseInt(split1[0]), Integer.parseInt(split1[1]) - 1, Integer.parseInt(split1[2]));
                TimePickerView pvTime = new TimePickerBuilder(ModifyDataActivity.this, (date, v) -> {
                    nowDate = BaseUtils.getNowDate(date);
                    birthday.setText(nowDate);
                    getConstellation(nowDate);
                })
                        .setRangDate(startDate, endDate)
                        .setDate(seleteDate)
                        .build();
                pvTime.show();
                break;
            case R.id.head_image:
//                showPop();
                break;
            case R.id.region_btn:
                //条件选择器
                if (isLoaded) {
                    showPickerView();
                }
                break;
        }
    }

    // 弹出选择器
    private void showPickerView() {

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
//                String opt1tx = options1Items.size() > 0 ?
//                        options1Items.get(options1).getPickerViewText() : "";
//
                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";

//                String opt3tx = options2Items.size() > 0
//                        && options3Items.get(options1).size() > 0
//                        && options3Items.get(options1).get(options2).size() > 0 ?
//                        options3Items.get(options1).get(options2).get(options3) : "";

                region.setText(opt2tx);
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    //解析数据
    private void initJsonData() {

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String cityName = jsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                /*if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }*/
                city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(cityList);

            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }

    //Gson 解析
    public ArrayList<JsonBean> parseData(String result) {
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    //图片的弹窗
    private void showPop() {
        PhotoWindow photoWindow = new PhotoWindow(this);
        photoWindow.showAtLocation(sex, Gravity.BOTTOM, 0, 0);
        photoWindow.getTake_photo().setOnClickListener(v -> {
            photoWindow.dismiss();
            if (SdcardTools.sdcard()) {
                RxPermissions rxPermissions = new RxPermissions(this);
                rxPermissions
                        .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(granted -> {
                            if (granted) { // Always true pre-M
                                //相机
                                Intent intent = new Intent(ModifyDataActivity.this, ImageGridActivity.class);
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
                            Intent intent = new Intent(ModifyDataActivity.this, ImageGridActivity.class);
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

        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (requestCode == REQUEST_CODE_SELECT) {
                //添加图片返回
                tempList = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);

                if (tempList == null) {
                    return;
                }
                int insex = 0;
                for (int a = 0; a < tempList.size(); a++) {
                    insex = selImageList.size();
                    TopThreeImageBean ttib = new TopThreeImageBean();
                    ttib.setIndex(insex);
                    ttib.setNetwork(false);
                    ttib.setUrl(tempList.get(a).path);
                    selImageList.add(ttib);
                }

                mImgAdapter.setList(selImageList);
                mImgAdapter.notifyDataSetChanged();
                imgRecycylerview.setVisibility(View.VISIBLE);
            } else if (requestCode == REQUEST_CODE_SELECT_TWO) {
                List<ImageItem> headList = (List<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (headList == null) {
                    return;
                }
                for (int a = 0; a < headList.size(); a++) {
                    headImageList.add(headList.get(a).path);
                }
                mHeadAdapter.setList(headImageList);
                mHeadAdapter.notifyDataSetChanged();
                headRecycylerview.setVisibility(View.VISIBLE);
            }

        }
    }

    //获取个人信息
    private void getPerInfo() {
        RxUtils.loading(commonModel.get_user_info(UserManager.getUser().getUserId() + ""), this)
                .subscribe(new ErrorHandleSubscriber<UserBean>(mErrorHandler) {
                    @Override
                    public void onNext(UserBean userBean) {
                        imglist = userBean.getData().getImglist();
                        //头像
                        headStringFan = userBean.getData().getHeadimgurl();
                        if (!TextUtils.isEmpty(userBean.getData().getHeadimgurl())) {
                            headImageList.add(userBean.getData().getHeadimgurl());
                            mHeadAdapter.setList(headImageList);
                            mHeadAdapter.notifyDataSetChanged();
                        }

                        //昵称
                        textSend.setText(userBean.getData().getNickname());
                        //ID号
                        id.setText(userBean.getData().getId() + "");
                        //性别
                        sexStr = String.valueOf(userBean.getData().getSex());
                        if (userBean.getData().getSex() == 1) {
                            sex.setImageResource(R.mipmap.gender_boy);
                        } else {
                            sex.setImageResource(R.mipmap.gender_girl);
                        }
                        //出生日期
                        birthday.setText(userBean.getData().getBirthday());
                        //星座
                        constellation.setText(userBean.getData().getConstellation());
                        //地区
                        region.setText(userBean.getData().getCity());

                        //顶部除头像外的3张图片
                        if (userBean.getData().getImglist() != null) {
                            for (int a = 0; a < userBean.getData().getImglist().size(); a++) {
                                TopThreeImageBean ttib = new TopThreeImageBean();
                                ttib.setUrl(userBean.getData().getImglist().get(a).getImg());
                                ttib.setNetwork(true);
                                ttib.setIndex(a);
                                selImageList.add(ttib);
                            }
                            mImgAdapter.setList(selImageList);
                            mImgAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    //获取星座
    private void getConstellation(String birthday) {
        RxUtils.loading(commonModel.getConstellation(birthday), this)
                .subscribe(new ErrorHandleSubscriber<ConstellationBean>(mErrorHandler) {
                    @Override
                    public void onNext(ConstellationBean constellationBean) {
                        constellation.setText(constellationBean.getData().getConstellation());
                    }
                });
    }

    //修改信息
    private void setUserInfo(String img, String nickname, String sex, String birthday, String constellation, String city, String img1, String img2, String img3) {
        showDialogLoding();
        RxUtils.loading(commonModel.setUserInfo(String.valueOf(UserManager.getUser().getUserId()), img, nickname, sex, birthday, constellation, city, img1, img2, img3), this)
                .subscribe(new ErrorHandleSubscriber<OtherBean>(mErrorHandler) {
                    @Override
                    public void onNext(OtherBean commentBean) {

                        disDialogLoding();

                        toast("修改成功");

                        if (commentBean.getCode() == 1) {
                            String token = UserManager.getUser().getToken();
                            //用户信息存入数据库
                            LoginData loginData = new LoginData();
                            loginData.setHeadimgurl(commentBean.getData().getHeadimgurl());
                            loginData.setNickname(commentBean.getData().getNickname());
                            loginData.setUserId(commentBean.getData().getId());
                            loginData.setPhone(commentBean.getData().getPhone());
                            loginData.setRy_token(commentBean.getData().getRy_token());
                            loginData.setToken(token);
//                                    if(TextUtils.isEmpty(userInfo.getData().getProvince())){
//                                        loginData.setPr(userInfo.getData().getProvince());
//                                    }
//                                    if(TextUtils.isEmpty(userInfo.getData().getCountry())){
//                                        loginData.set(userInfo.getData().getCountry());
//                                    }
                            LitePal.deleteAll(LoginData.class);
                            loginData.save();//litepal数据库，不能随便改LoginData数据
                            UserManager.initData();//存储完，初始化
                            finish();
                            EventBus.getDefault().post(new FirstEvent("修改成功", XIUGAIGERENZILIAOCHENGGONG));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        disDialogLoding();
                        LogUtils.debugInfo("====修改个人信息错误", t.getMessage());
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle("修改资料", true);
        setToolbarRightConfirmText(" 保存", v -> {
            if (TextUtils.isEmpty(textSend.getText())) {
                ArmsUtils.snackbarText("昵称为空");
                return;
            } else if (TextUtils.isEmpty(birthday.getText())) {
                ArmsUtils.snackbarText("生日为空");
                return;
            } else if (TextUtils.isEmpty(constellation.getText())) {
                ArmsUtils.snackbarText("未知星座");
                return;
            } else if (TextUtils.isEmpty(region.getText())) {
                ArmsUtils.snackbarText("你没有住的地方吗");
            } else if (textSend.getText().toString().trim().length() < 2) {
                ArmsUtils.snackbarText("昵称最少为2位");
                return;
            } else {
                if (mHeadAdapter.getList().size() == 0) {
                    headString = "";
                } else {
                    if (headImageList.get(0).equals(headStringFan)) {
                        headString = "";
                    } else {
                        headString = "data:image/png;base64," + BaseUtils.file2Base64(headImageList.get(0));
                    }
                }

                if (mImgAdapter.getList().size() == 0) {
                    imgString1 = "";
                    imgString2 = "";
                    imgString3 = "";
                } else {
                    if (imglist != null) {
                        for (int a = 0; a < mImgAdapter.getList().size(); a++) {
                            if (mImgAdapter.getList().get(a).getIndex() == 0) {
                                if (mImgAdapter.getList().get(a).isNetwork()) {
                                    imgString1 = mImgAdapter.getList().get(a).getUrl();
                                } else {
                                    imgString1 = "data:image/png;base64," + BaseUtils.file2Base64(mImgAdapter.getList().get(a).getUrl());
                                }
                            } else if (mImgAdapter.getList().get(a).getIndex() == 1) {
                                if (mImgAdapter.getList().get(a).isNetwork()) {
                                    imgString2 = mImgAdapter.getList().get(a).getUrl();
                                } else {
                                    imgString2 = "data:image/png;base64," + BaseUtils.file2Base64(mImgAdapter.getList().get(a).getUrl());
                                }
                            } else if (mImgAdapter.getList().get(a).getIndex() == 2) {
                                if (mImgAdapter.getList().get(a).isNetwork()) {
                                    imgString3 = mImgAdapter.getList().get(a).getUrl();
                                } else {
                                    imgString3 = "data:image/png;base64," + BaseUtils.file2Base64(mImgAdapter.getList().get(a).getUrl());
                                }
                            }
                        }
                    } else {
                        for (int a = 0; a < mImgAdapter.getList().size(); a++) {
                            if (mImgAdapter.getList().get(a).getIndex() == 0) {
                                imgString1 = "data:image/png;base64," + BaseUtils.file2Base64(mImgAdapter.getList().get(a).getUrl());
                            } else if (mImgAdapter.getList().get(a).getIndex() == 1) {
                                imgString2 = "data:image/png;base64," + BaseUtils.file2Base64(mImgAdapter.getList().get(a).getUrl());
                            } else if (mImgAdapter.getList().get(a).getIndex() == 2) {
                                imgString3 = "data:image/png;base64," + BaseUtils.file2Base64(mImgAdapter.getList().get(a).getUrl());
                            }
                        }
                    }
                }
                setUserInfo(headString, textSend.getText().toString().replace(" ", ""), sexStr, birthday.getText().toString().replace(" ", ""), constellation.getText().toString().replace(" ", ""), region.getText().toString().replace(" ", ""), imgString1, imgString2, imgString3);
            }
        });
    }

    private ModifyImageAdapter.onAddPicClickListener onAddPicClickListener = new ModifyImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            showPop();
        }
    };

    private ModifyImageTwoAdapter.onAddPicClickListener onAddPicClickListener2 = new ModifyImageTwoAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            PhotoWindow photoWindow = new PhotoWindow(ModifyDataActivity.this);
            photoWindow.showAtLocation(sex, Gravity.BOTTOM, 0, 0);
            photoWindow.getTake_photo().setOnClickListener(v -> {
                photoWindow.dismiss();
                if (SdcardTools.sdcard()) {
                    RxPermissions rxPermissions = new RxPermissions(ModifyDataActivity.this);
                    rxPermissions
                            .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .subscribe(granted -> {
                                if (granted) { // Always true pre-M
                                    //相机
                                    Intent intent = new Intent(ModifyDataActivity.this, ImageGridActivity.class);
                                    intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                    startActivityForResult(intent, REQUEST_CODE_SELECT_TWO);
                                }
                            });
                } else {
                    Toast.makeText(ModifyDataActivity.this, "sd卡不可用", Toast.LENGTH_SHORT).show();
                }
            });
            photoWindow.getChose_pic().setOnClickListener(v -> {
                photoWindow.dismiss();
                RxPermissions rxPermissions = new RxPermissions(ModifyDataActivity.this);
                rxPermissions
                        .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(granted -> {
                            if (granted) { // Always true pre-M
                                // 跳转到相册
                                ImagePicker.getInstance().setSelectLimit(1);
                                ImagePicker.getInstance().setMultiMode(false);
                                Intent intent = new Intent(ModifyDataActivity.this, ImageGridActivity.class);
                                //显示选中的图片
                                startActivityForResult(intent, REQUEST_CODE_SELECT_TWO);
                            }
                        });
            });
            photoWindow.setOnDismissListener(() -> {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
            });
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}
