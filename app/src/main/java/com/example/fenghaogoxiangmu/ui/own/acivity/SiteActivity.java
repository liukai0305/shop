package com.example.fenghaogoxiangmu.ui.own.acivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.fenghaogoxiangmu.R;
import com.example.fenghaogoxiangmu.base.BaseActivity;
import com.example.fenghaogoxiangmu.bean.own.SiteBean;
import com.example.fenghaogoxiangmu.interfaces.own.IOwn;
import com.example.fenghaogoxiangmu.persenter.own.SitePresenter;
import com.example.fenghaogoxiangmu.utils.SystemUtils;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SiteActivity extends BaseActivity<IOwn.ISitePresenter> implements IOwn.ISiteView {


    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_address)
    TextView etAddress;
    @BindView(R.id.et_address_detail)
    EditText etAddressDetail;
    @BindView(R.id.ck_default)
    CheckBox ckDefault;
    @BindView(R.id.txt_default)
    TextView txtDefault;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.layout)
    ConstraintLayout layout;
    private PopupWindow popupWindow;
    private TextView tvProvince;
    private TextView tvCity;
    private TextView tvArea;
    private TextView tvSubmit;
    private LoopView loopProvince;
    private LoopView loopCity;
    private LoopView loopArea;
    private Map<Integer, List<SiteBean.DataBean>> addressMap;

    private int curProvinceId;
    private int curCityId;
    private int curAreaId;
    private String provinceName;
    private String cityName;
    private String areaName;

    @Override
    protected int getLayout() {
        return R.layout.activity_site;
    }

    @Override
    protected void initView() {
        addressMap = new HashMap<>();

    }

    @Override
    protected IOwn.ISitePresenter initPersenter() {
        return new SitePresenter();
    }

    @Override
    protected void initData() {
        persenter.getSite(1);
    }

    @Override
    public void getSiteresult(SiteBean result) {
        List<SiteBean.DataBean> list = null;
        int type = 0;
        for (SiteBean.DataBean item : result.getData()) {
            int key = item.getParent_id();
            list = addressMap.get(key);
            if (list == null) {
                list = new ArrayList<>();
                addressMap.put(key, list);
            }
            boolean bool = hasList(item.getId(), list);
            if (!bool) list.add(item);
            if (type == 0) {
                type = item.getType();
            }
        }
        if (list == null) return;
        List<String> adresses = getAddressStrings(list);
        if (type == 1) {
            //刷新省的数据
            if (loopProvince != null) {
                curProvinceId = list.get(0).getId();
                tvProvince.setText(list.get(0).getName());
                loopProvince.setItems(adresses);
            }
        } else if (type == 2) {
            //刷新市的数据
            if (loopCity != null) {
                curCityId = list.get(loopCity.getSelectedItem()).getId();
                tvCity.setText(list.get(loopCity.getSelectedItem()).getName());
                persenter.getSite(curCityId);
                loopCity.setItems(adresses);
            }
        } else {
            //区
            if (loopArea != null) {
                curAreaId = list.get(loopArea.getSelectedItem()).getId();
                tvArea.setText(list.get(loopArea.getSelectedItem()).getName());
                loopArea.setItems(adresses);
            }
        }
    }

    @OnClick({R.id.img_back, R.id.et_address, R.id.ck_default, R.id.btn_cancel, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                break;
            case R.id.et_address:
                showPopupWindow();
                break;
            case R.id.ck_default:
                break;
            case R.id.btn_cancel:
                break;
            case R.id.btn_save:
                break;
        }
    }

    private void showPopupWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_pop_address, null);
        int height = SystemUtils.dp2px(this, 250);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, height);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setContentView(view);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        tvProvince = view.findViewById(R.id.txt_province);
        tvCity = view.findViewById(R.id.txt_city);
        tvArea = view.findViewById(R.id.txt_area);
        tvSubmit = view.findViewById(R.id.txt_submit);
        loopProvince = view.findViewById(R.id.loop_province);
        loopCity = view.findViewById(R.id.loop_city);
        loopArea = view.findViewById(R.id.loop_area);
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (provinceName != null && cityName != null && areaName != null)
                    etAddress.setText(provinceName + "-" + cityName + "-" + areaName);
                popupWindow.dismiss();
                popupWindow = null;
            }
        });
        popupWindow.showAtLocation(layout, Gravity.BOTTOM, 0, 0);
        //省份
        loopProvince.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                List<SiteBean.DataBean> proviceList = addressMap.get(1); //key为1固定为省的数据
                SiteBean.DataBean dataBean = proviceList.get(index);
                curProvinceId = dataBean.getId();
                persenter.getSite(curProvinceId);
                List<String> items = new ArrayList<>();
                items.add("城市");
                loopCity.setItems(items);
                tvProvince.setText(dataBean.getName());
                provinceName = dataBean.getName();
                tvCity.setText("城市");
                tvArea.setText("区县");
            }
        });
        //城市
        loopCity.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                List<SiteBean.DataBean> cityList = addressMap.get(curProvinceId); //key省份id
                if (cityList != null) {
                    SiteBean.DataBean dataBean = cityList.get(index);
                    curCityId = dataBean.getId();
                    persenter.getSite( curCityId);
                    loopArea.setItems(new ArrayList<>());
                    tvCity.setText(dataBean.getName());
                    cityName = dataBean.getName();
                    tvArea.setText("区县");
                }
            }
        });
        //区县
        loopArea.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                List<SiteBean.DataBean> areaList = addressMap.get(curCityId); //key省份id
                SiteBean.DataBean dataBean = areaList.get(index);
                curAreaId = dataBean.getId();
                tvArea.setText(dataBean.getName());
                areaName = dataBean.getName();
                tvSubmit.setEnabled(true);
            }
        });
        //初始化省份的数据
        List<SiteBean.DataBean> pList = addressMap.get(1);
        if (pList == null) return;
        List<String> adresses = getAddressStrings(pList);
        if (pList == null || adresses.size() == 0) {
            persenter.getSite(1);
        } else {
            loopProvince.setItems(adresses);
            curProvinceId = pList.get(0).getId();
            tvProvince.setText(adresses.get(0));
        }
    }

    //获取省市区名字
    private List<String> getAddressStrings(List<SiteBean.DataBean> list) {
        List<String> addresses = new ArrayList<>();
        for (SiteBean.DataBean item : list) {
            addresses.add(item.getName());
        }
        return addresses;
    }



    private boolean hasList(int id, List<SiteBean.DataBean> list){
        boolean bool = false;
        for(SiteBean.DataBean item:list){
            if(item.getId() == id){
                bool = true;
                break;
            }
        }
        return bool;
    }
}