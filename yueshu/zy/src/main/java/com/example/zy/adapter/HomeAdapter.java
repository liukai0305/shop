package com.example.zy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import com.example.zy.R;
import com.example.zy.bean.UsersBean;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends RecyclerView.Adapter {
    private ArrayList<UsersBean.DataBean.ActiondataBean> list;
    private Context context;

    public HomeAdapter(ArrayList<UsersBean.DataBean.ActiondataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UsersBean.DataBean.ActiondataBean bean = list.get(position);
        ViewHolder holder1= (ViewHolder) holder;
        RequestOptions crop = new RequestOptions().circleCrop();
        Glide.with(context).load(bean.getCover()).apply(crop).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder1.iv_rcy_hot);
        holder1.tv_time_hot.setText(bean.getStartTime());
        holder1.tv_title_hot.setText(bean.getTitle());
        holder1.tv_location_hot.setText(bean.getLocation());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv_rcy_hot;
        public TextView tv_title_hot;
        public ImageView iv_gps_hot;
        public TextView tv_location_hot;
        public TextView tv_time_hot;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv_rcy_hot = (ImageView) rootView.findViewById(R.id.iv_rcy_hot);
            this.tv_title_hot = (TextView) rootView.findViewById(R.id.tv_title_hot);
            this.iv_gps_hot = (ImageView) rootView.findViewById(R.id.iv_gps_hot);
            this.tv_location_hot = (TextView) rootView.findViewById(R.id.tv_location_hot);
            this.tv_time_hot = (TextView) rootView.findViewById(R.id.tv_time_hot);
        }

    }
}
