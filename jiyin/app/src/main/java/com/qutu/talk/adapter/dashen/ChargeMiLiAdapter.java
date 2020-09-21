package com.qutu.talk.adapter.dashen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qutu.talk.R;
import com.qutu.talk.adapter.ChargeAdapter;
import com.qutu.talk.base.MyBaseAdapter;
import com.qutu.talk.bean.GoodsList;
import com.qutu.talk.bean.dashen.GoodsMiLiListBean;

public class ChargeMiLiAdapter extends MyBaseAdapter<GoodsMiLiListBean.DataBean.GoodsBean> {
    private Context context;
    public ChargeMiLiAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder VH;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_chager_mili, null);
            VH = new ViewHolder(convertView);
            convertView.setTag(VH);
        } else {
            VH = (ViewHolder) convertView.getTag();
        }
        VH.textNum1.setText(list_adapter.get(position).getMili());
        VH.textNum3.setText(list_adapter.get(position).getPrice() + "元");

        if(list_adapter.get(position).isSelect){
            VH.rlBg.setSelected(true);
            VH.textNum1.setSelected(true);
            VH.textNum3.setSelected(true);
            VH.textJia.setSelected(true);
        }else {
            VH.rlBg.setSelected(false);
            VH.textNum1.setSelected(false);
            VH.textNum3.setSelected(false);
            VH.textJia.setSelected(false);
        }
            VH.textJia.setText("金币");

        return convertView;
    }

    public static class ViewHolder {
        RelativeLayout rlBg;
        TextView textNum1,textNum3,textJia;

        public ViewHolder(View convertView) {
            rlBg = (RelativeLayout) convertView.findViewById(R.id.rlBg);
            textNum1 = (TextView) convertView.findViewById(R.id.textNum1);
            textNum3 = (TextView) convertView.findViewById(R.id.textNum3);
            textJia = (TextView) convertView.findViewById(R.id.textJia);
        }
    }
}
