package com.example.tongpao.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.tongpao.R;
import com.example.tongpao.base.BaseAdapter;
import com.example.tongpao.bean.PersonalMsgBean;

import java.util.List;

public class PersonalDataAdapter extends BaseAdapter {
    public PersonalDataAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.data_item;
    }

    @Override
    protected void bindData(BaseVirwHolder baseVirwHolder, Object t_data, int position) {
        TextView data_sex = (TextView) baseVirwHolder.getViewById(R.id.data_sex);
        TextView data_birthday = (TextView) baseVirwHolder.getViewById(R.id.data_birthday);
        TextView data_city = (TextView) baseVirwHolder.getViewById(R.id.data_city);
        PersonalMsgBean.DataBean dataBean = (PersonalMsgBean.DataBean) t_data;
        //性别
        if (dataBean.getSex().equals("1")){
            data_sex.setText("性别:女");
        }else {
            data_sex.setText("性别:男");
        }
        //生日
        data_birthday.setText("生日:"+dataBean.getBirthday());
        //所在地区
        data_city.setText("所在地区:"+dataBean.getCity());
    }
}
