package com.example.xiaogmulanxi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiaogmulanxi.R;
import com.example.xiaogmulanxi.bean.JavaBean;

import java.util.ArrayList;
import java.util.List;

public class RcyAdapter extends RecyclerView.Adapter {
    private List<JavaBean> list;
    private Context context;

    public RcyAdapter(List<JavaBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rcy, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        JavaBean javaBean = list.get(position);
        ViewHolder holder1= (ViewHolder) holder;
        holder1.name.setText(javaBean.getName());
        holder1.sex.setText(javaBean.getSex());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView name;
        public TextView sex;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.name = (TextView) rootView.findViewById(R.id.name);
            this.sex = (TextView) rootView.findViewById(R.id.sex);
        }

    }
}
