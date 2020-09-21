package com.example.yueshu.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yueshu.R;
import com.example.yueshu.bean.UsersBean;

import java.util.ArrayList;

public class RcyAdapter extends RecyclerView.Adapter {
    private ArrayList<UsersBean.ResultsBean> list;
    private Context context;

    public RcyAdapter(ArrayList<UsersBean.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_rcy, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UsersBean.ResultsBean resultsBean = list.get(position);
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.tv.setText(resultsBean.getType());
        Glide.with(context).load(resultsBean.getUrl()).into(holder1.iv);

    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv;
        public TextView tv;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv = (ImageView) rootView.findViewById(R.id.iv);
            this.tv = (TextView) rootView.findViewById(R.id.tv);
        }

    }
}
