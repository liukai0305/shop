package com.example.xiangmuyi.ui.own.IamActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.app.GlideEngine;
import com.example.xiangmuyi.base.BaseActivity;
import com.example.xiangmuyi.bean.OwnBean.UserUpdateBean;
import com.example.xiangmuyi.interfaces.own.IOwn;
import com.example.xiangmuyi.model.HttpManager;
import com.example.xiangmuyi.model.api.UploadApi;
import com.example.xiangmuyi.persenter.own.UpDataPersenter;
import com.example.xiangmuyi.utils.GildeUtils;
import com.example.xiangmuyi.utils.SpUtils;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.soundcloud.android.crop.Crop;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonageActivity extends BaseActivity<IOwn.UpDataPersenter> implements IOwn.UpDataView {
    @BindView(R.id.to)
    TextView to;
    @BindView(R.id.zhao)
    ImageView zhao;
    @BindView(R.id.img_head_into)
    ImageView imgHeadInto;
    @BindView(R.id.ni)
    TextView ni;
    @BindView(R.id.name)
    TextView tvName;
    @BindView(R.id.img_head_into1)
    ImageView imgHeadInto1;
    @BindView(R.id.xing)
    TextView xing;
    @BindView(R.id.sex)
    TextView tvSex;
    @BindView(R.id.img_head_into2)
    ImageView imgHeadInto2;
    @BindView(R.id.chu)
    TextView chu;
    @BindView(R.id.ri)
    TextView ri;
    @BindView(R.id.img_head_into3)
    ImageView imgHeadInto3;
    @BindView(R.id.zuo)
    TextView zuo;
    @BindView(R.id.mimi)
    TextView mimi;
    @BindView(R.id.img_head_into5)
    ImageView imgHeadInto5;
    @BindView(R.id.suo)
    TextView suo;
    @BindView(R.id.didian)
    TextView didian;
    @BindView(R.id.img_head_into6)
    ImageView imgHeadInto6;
    @BindView(R.id.ge)
    TextView ge;
    @BindView(R.id.qianming)
    TextView qianming;
    @BindView(R.id.img_head_into7)
    ImageView imgHeadInto7;
    @BindView(R.id.tooblar)
    Toolbar tooblar;
    @BindView(R.id.dainhua)
    TextView dainhua;
    @BindView(R.id.hao)
    TextView hao;
    @BindView(R.id.img_head_into9)
    ImageView imgHeadInto9;
    private CityPicker mCP;
    private String url;
    private String name;
    private String adress;
    private String phone;
    private String sex;

    @Override
    protected int getLayout() {
        return R.layout.activity_personage;
    }

    protected void initView() {
        String avater = SpUtils.getInstance().getString("avater");
        GildeUtils.loadImg(this, avater, zhao);
        if(SpUtils.getInstance().getString("nickname") != null && !TextUtils.isEmpty(SpUtils.getInstance().getString("nickname"))){
            tvName.setText(SpUtils.getInstance().getString("nickname"));
        }else if (!TextUtils.isEmpty(SpUtils.getInstance().getString("username"))){
            tvName.setText(SpUtils.getInstance().getString("username"));
        }
        if (SpUtils.getInstance().getInt("sex") == 0){
            tvSex.setText("男");
        }else if (SpUtils.getInstance().getInt("sex") == 1){
            tvSex.setText("女");
        }else {
            tvSex.setText("请选择性别");
        }
        if (!TextUtils.isEmpty(SpUtils.getInstance().getString("birthday"))){
//            ri.setText(DateUtil.getDateToString(Long.parseLong(dataBean.getBirthday()+"000"),"yyyy-MM-dd"));
        }else {
            ri.setText("请选择生日");
        }
        if (!TextUtils.isEmpty(SpUtils.getInstance().getString("adress"))){
            didian.setText(SpUtils.getInstance().getString("adress"));
        }else {
            didian.setText("请选择地区");
        }
        if (!TextUtils.isEmpty(SpUtils.getInstance().getString("phone"))){
            hao.setText(SpUtils.getInstance().getString("phone"));
        }else {
            hao.setText("请输入手机号");
        }
    }

    @Override
    protected IOwn.UpDataPersenter initPersenter() {
        return new UpDataPersenter();
    }

    @Override
    protected void initData() {

    }

    private static final String TAG = "PersonageActivity";

    @Override
    public void updateUserInfoReturn(UserUpdateBean result) {
        Log.d(TAG, "updateUserInfoReturn: " + result.getErr());
        if (result.getErr() == 200) {
            if (result.getData().getType() == 1) {
                if (url != null) {
                    SpUtils.getInstance().setValue("avater", url);
                }
            }
            if (result.getData().getType() == 8) {
                if (adress != null) {
                    SpUtils.getInstance().setValue("adress", adress);
                }
            }

            if (result.getData().getType() == 4) {
                if (sex != null) {
                    if (sex != null){
                        if (sex.equals("男")){
                            SpUtils.getInstance().setValue("sex", 0);
                        }else {
                            SpUtils.getInstance().setValue("sex", 1);
                        }
                    }
                }
            }
            if (result.getData().getType() ==3) {
                if (name != null) {
                    SpUtils.getInstance().setValue("nickname", name);
                }
            }
            if (result.getData().getType() ==2) {
                if (phone != null) {
                    SpUtils.getInstance().setValue("phone", phone);
                }
            }
        }
    }

    public void mYunCityPicher() {
        mCP = new CityPicker.Builder(PersonageActivity.this)
                .textSize(20)
                //地址选择
                .title("地址选择")
                .backgroundPop(0xa0000000)
                //文字的颜色
                .titleBackgroundColor("#0CB6CA")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("xx省")
                .city("xx市")
                .district("xx区")
                //滑轮文字的颜色
                .textColor(Color.parseColor("#000000"))
                //省滑轮是否循环显示
                .provinceCyclic(true)
                //市滑轮是否循环显示
                .cityCyclic(false)
                //地区（县）滑轮是否循环显示
                .districtCyclic(false)
                //滑轮显示的item个数
                .visibleItemsCount(7)
                //滑轮item间距
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();

        //监听
        mCP.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省
                String province = citySelected[0];
                //市
                String city = citySelected[1];
                //区。县。（两级联动，必须返回空）
                String district = citySelected[2];
                //邮证编码
                String code = citySelected[3];
                adress = province + city + district;
                didian.setText(adress);
                Map<String, String> map = new HashMap<>();
                map.put("adress", adress);
                persenter.updateUserInfo(map);
            }

            @Override
            public void onCancel() {

            }
        });
    }

    Calendar calendar = Calendar.getInstance(Locale.CHINA);

    /**
     * 日期选择
     *
     * @param activity
     * @param themeResId
     * @param tv
     * @param calendar
     */
    public static void showDatePickerDialog(Activity activity, int themeResId, final TextView tv, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity, themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                String birth = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                tv.setText(birth);
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @OnClick({R.id.tooblar, R.id.zhao, R.id.didian, R.id.ri, R.id.name, R.id.sex,R.id.hao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tooblar:
                finish();
                break;
            case R.id.zhao:
                openPhoto();
                break;
            case R.id.didian:
                mYunCityPicher();
                mCP.show();
                break;
            case R.id.ri:
                showDatePickerDialog(this, 2, ri, calendar);
                break;
            case R.id.name:
                EditText etName = new EditText(this);
                new AlertDialog.Builder(this)
                        .setView(etName)
                        .setTitle("输入新的昵称")
                        .setNegativeButton("取消",null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                name = etName.getText().toString();
                                tvName.setText(name);
                                Map<String,String> map = new HashMap<>();
                                map.put("nickname", name);
                                persenter.updateUserInfo(map);
                            }
                        }).show();

                break;
            case R.id.sex:
                new AlertDialog.Builder(this)
                        .setTitle("选择性别")
                        .setNegativeButton("男", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                sex = "男";
                                tvSex.setText(sex);
                                Map<String,String> map = new HashMap<>();
                                map.put("sex", sex);
                                persenter.updateUserInfo(map);
                            }
                        })
                        .setPositiveButton("女", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                sex = "女";
                                tvSex.setText(sex);
                                Map<String,String> map = new HashMap<>();
                                map.put("sex", sex);
                                persenter.updateUserInfo(map);
                            }
                        }).show();
                break;
            case R.id.hao:
                EditText etPhone = new EditText(this);
                new AlertDialog.Builder(this)
                        .setView(etPhone)
                        .setTitle("输入新的手机号")
                        .setNegativeButton("取消",null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                phone = etPhone.getText().toString();
                                hao.setText(phone);
                                Map<String,String> map = new HashMap<>();
                                map.put("phone", phone);
                                persenter.updateUserInfo(map);
                            }
                        }).show();
                break;
        }
    }

    private void openPhoto() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .maxSelectNum(1)
                .imageSpanCount(4)
                .selectionMode(PictureConfig.MULTIPLE)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    /**
     * 获取相机相册图片
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PictureConfig.CHOOSE_REQUEST:
                // onResult Callback
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                if (selectList.size() == 0) return;
                //获取本地图片的选择地址，上传到服务器
                //头像的压缩和二次采样
                Uri inputUri = Uri.fromFile(new File(selectList.get(0).getPath()));
                Uri outUri = Uri.fromFile(new File(getCacheDir(), "crop_" + selectList.get(0).getFileName()));
                Crop.of(inputUri, outUri).asSquare().start(this);
                break;
            case Crop.REQUEST_CROP:
                if (resultCode == RESULT_OK) {
                    //获取剪切以后的图片
                    Uri uri = (Uri) data.getExtras().get("output");
                    try {
                        uploadImage(uri.toString());
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    zhao.setImageURI(uri);
                }
            default:
                break;
        }
    }

    /**
     * 缺少图片上传的功能
     *
     * @param path
     */
    private void uploadImage(String path) throws URISyntaxException {
        String img_format = "image/jpg";
        String key = "lk";
        //sd卡图片文件
        File file = new File(new URI(path));
        if (file.exists()) {
            //创建一个RequestBody 封装文件格式以及文件内容
            RequestBody requestFile = MultipartBody.create(MediaType.parse(img_format), file);
            //创建一个MultipartBody.Part 封装的文件数据（文件流） file参数是给后台接口读取文件用，file.getName() 保存到后台的文件名字
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
            //设置对应的key application/json; charset=utf-8
            RequestBody key_file = RequestBody.create(MediaType.parse("multipart/form-data"), key);
            //通过requestbody传值到后台接口
            //RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),key);
            //创建retrofit
            UploadApi uploadApi = HttpManager.getInstance().getUploadApi();
            Call<ResponseBody> call = uploadApi.uploadFile(key_file, part);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String result = response.body().string();
                        if (!TextUtils.isEmpty(result)) {
                            JSONObject json = new JSONObject(result);
                            int code = json.getInt("code");
                            if (code == 200) {
                                JSONObject dataJson = new JSONObject(json.getString("data"));
                                url = dataJson.getString("url");
                                Map<String, String> map = new HashMap<>();
                                map.put("avater", url);
                                //更新服务器上的头像
                                persenter.updateUserInfo(map);
                            }
                        }
                        Log.i("onResponse", response.body().string());
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        Log.i("e", "解析上传结果json失败");
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.i("onFailure", t.getMessage());
                }
            });
        } else {
            Toast.makeText(this, "找不到本地文件", Toast.LENGTH_SHORT).show();
        }
    }
}