package com.example.fenghaogoxiangmu.adapter.home.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fenghaogoxiangmu.R;
import com.example.fenghaogoxiangmu.bean.home.activitybean.GoodDetailBean;

import java.util.ArrayList;

public class ImgCommentAdapter extends RecyclerView.Adapter {
    private ArrayList<GoodDetailBean.DataBeanX.CommentBean.DataBean.PicListBean> listBeans;
    private Context c;

    public ImgCommentAdapter(ArrayList<GoodDetailBean.DataBeanX.CommentBean.DataBean.PicListBean> listBeans, Context c) {
        this.listBeans = listBeans;
        this.c = c;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.item_img_comment_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GoodDetailBean.DataBeanX.CommentBean.DataBean.PicListBean picListBean = listBeans.get(position);
        ViewHolder holder1= (ViewHolder) holder;
        Glide.with(c).load(picListBean.getPic_url()).into(holder1.iv);
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv = (ImageView) rootView.findViewById(R.id.iv);
        }

    }
}
