package com.example.xiangmuyi.adapter.homeadapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.bean.homebean.HomeTopicDiscussedBean;

import java.util.ArrayList;

public class HomeTopicAdapter extends RecyclerView.Adapter {
    private ArrayList<HomeTopicDiscussedBean.DataBean> list;
    private Context context;

    public HomeTopicAdapter(ArrayList<HomeTopicDiscussedBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_hottopic, parent, false);
        return new ViewHolder(inflate);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomeTopicDiscussedBean.DataBean dataBean = list.get(position);
        ViewHolder holder1= (ViewHolder) holder;
        RoundedCorners roundedCorners = new RoundedCorners(10);
        RequestOptions override = RequestOptions.bitmapTransform(roundedCorners).override(100, 100);
        Glide.with(context).load(dataBean.getImageUrl()).apply(override).into(holder1.mImg);
        holder1.mTitle.setText("#"+dataBean.getName()+"#");
        holder1.number.setText(dataBean.getAttentionNum() + "人参与");
        if(position>=0||position<=2){
            holder1.mImg.setVisibility(View.VISIBLE);
        }
        if (position % 3 == 0) {
            holder1.mCard.setCardBackgroundColor(Color.argb(1f, 255, 197, 197));
        } else if (position % 3 == 1) {
            holder1.mCard.setCardBackgroundColor(Color.argb(1f, 188, 255, 155));
        } else {
            holder1.mCard.setCardBackgroundColor(Color.argb(1f, 122, 120, 255));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView mImg;
        public TextView mTitle;
        public TextView number;
        public CardView mCard;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.mImg = (ImageView) rootView.findViewById(R.id.mImg);
            this.mTitle = (TextView) rootView.findViewById(R.id.mTitle);
            this.number = (TextView) rootView.findViewById(R.id.number);
            this.mCard = (CardView) rootView.findViewById(R.id.mCard);
        }

    }
}
