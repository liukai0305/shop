package com.qutu.talk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qutu.talk.R;
import com.qutu.talk.base.MyBaseAdapter;
import com.qutu.talk.bean.DialogBean;

public class TimePopupAdapter extends MyBaseAdapter<DialogBean> {
    private Context context;

    public TimePopupAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder VH;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.time_popup_item, null);
            VH = new ViewHolder(convertView);
            convertView.setTag(VH);
        } else {
            VH = (ViewHolder) convertView.getTag();
        }
        VH.timePopupItem.setText(list_adapter.get(position).name);
        return convertView;
    }

    public static class ViewHolder {
        TextView timePopupItem;

        public ViewHolder(View convertView) {
            timePopupItem = convertView.findViewById(R.id.time_popu_item);
        }
    }
}
