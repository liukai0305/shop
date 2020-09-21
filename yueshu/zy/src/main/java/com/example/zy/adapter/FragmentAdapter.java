package com.example.zy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zy.R;
import com.example.zy.bean.UsersBean;

import java.util.ArrayList;

public class FragmentAdapter extends RecyclerView.Adapter {
    private ArrayList<UsersBean.DataBean.ListBean>list;
    private Context context;

    public FragmentAdapter(ArrayList<UsersBean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    int index1 = 1;
    int index2 = 2;
    int index3 = 3;


    @Override
    public int getItemViewType(int position) {
        if (position % 3==0) {
            return index1;
        } else if (position % 2==0) {
            return index2;
        } else {
            return index3;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == index1) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_rcy_redian3, parent, false);
            ViewHolder1 holder1 = new ViewHolder1(view);
            return holder1;
        } else if (viewType == index2) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_rcy_redian1, parent, false);
            ViewHolder2 holder2 = new ViewHolder2(view);
            return holder2;
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_rcy_redian2, parent, false);
            ViewHolder3 holder3 = new ViewHolder3(view);
            return holder3;
        }
    }




    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        UsersBean.DataBean.ListBean bean = list.get(position);
        if (getItemViewType(position)==index1){
            ViewHolder1 holder1= (ViewHolder1) holder;
            Glide.with(context).load(bean.getFilePathList().get(0).getFilePath()).into(holder1.iv_rcy_redian3);
            holder1.tv_title_rcy_redian3.setText(bean.getTitle());
            holder1.tv_time_rcy_redian3.setText(bean.getCreateTime());
        }else if (getItemViewType(position)==index2){
            ViewHolder2 holder2= (ViewHolder2) holder;
            for (int i = 0; i < bean.getFilePathList().size(); i++) {
                UsersBean.DataBean.ListBean.FilePathListBean filePathListBean = bean.getFilePathList().get(i);
                Glide.with(context).load(filePathListBean.getFilePath()).into(holder2.iv1_rcy_redian1);
                Glide.with(context).load(filePathListBean.getFilePath()).into(holder2.iv2_rcy_redian1);
                Glide.with(context).load(filePathListBean.getFilePath()).into(holder2.iv3_rcy_redian1);
            }
            holder2.tv_title_rcy_redian1.setText(bean.getTitle());
            holder2.tv_time.setText(bean.getCreateTime());
        }else {
            ViewHolder3 holder3= (ViewHolder3) holder;
            holder3.tv_title_rcy_redian2.setText(bean.getTitle());
            holder3.tv_time_rcy_redian2.setText(bean.getCreateTime());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static
    class ViewHolder1 extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_title_rcy_redian3;
        public ImageView iv_rcy_redian3;
        public TextView tv_time_rcy_redian3;

        public ViewHolder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_title_rcy_redian3 = (TextView) rootView.findViewById(R.id.tv_title_rcy_redian3);
            this.iv_rcy_redian3 = (ImageView) rootView.findViewById(R.id.iv_rcy_redian3);
            this.tv_time_rcy_redian3 = (TextView) rootView.findViewById(R.id.tv_time_rcy_redian3);
        }

    }

    public static
    class ViewHolder2 extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_title_rcy_redian1;
        public ImageView iv1_rcy_redian1;
        public ImageView iv2_rcy_redian1;
        public ImageView iv3_rcy_redian1;
        public TextView tv_time;

        public ViewHolder2(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_title_rcy_redian1 = (TextView) rootView.findViewById(R.id.tv_title_rcy_redian1);
            this.iv1_rcy_redian1 = (ImageView) rootView.findViewById(R.id.iv1_rcy_redian1);
            this.iv2_rcy_redian1 = (ImageView) rootView.findViewById(R.id.iv2_rcy_redian1);
            this.iv3_rcy_redian1 = (ImageView) rootView.findViewById(R.id.iv3_rcy_redian1);
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
        }

    }

    public static
    class ViewHolder3 extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_title_rcy_redian2;
        public TextView tv_time_rcy_redian2;

        public ViewHolder3(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_title_rcy_redian2 = (TextView) rootView.findViewById(R.id.tv_title_rcy_redian2);
            this.tv_time_rcy_redian2 = (TextView) rootView.findViewById(R.id.tv_time_rcy_redian2);
        }

    }

}
