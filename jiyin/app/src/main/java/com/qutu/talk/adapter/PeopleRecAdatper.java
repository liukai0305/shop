package com.qutu.talk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.qutu.talk.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PeopleRecAdatper extends DelegateAdapter.Adapter<PeopleRecAdatper.MainViewHolder> {

    private Context mContext;

    String mText;

    int intRes;

    boolean isHasMore;

    public PeopleRecAdatper(LayoutHelper layoutHelper,Context context,String text,int res,boolean hasMore) {
        this.layoutHelper = layoutHelper;
        this.mContext = context;
        this.mText = text;
        this.intRes = res;
        this.isHasMore = hasMore;
    }

    private LayoutHelper layoutHelper;


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_today_recommend_title,null));
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.tvLeftName.setText(mText);
        holder.imgLeft.setImageResource(intRes);
        if(isHasMore){
            holder.tvRightMore.setVisibility(View.VISIBLE);
        } else {
            holder.tvRightMore.setVisibility(View.GONE);
        }
        holder.tvRightMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class MainViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_left)
        ImageView imgLeft;
        @BindView(R.id.tv_left_name)
        TextView tvLeftName;
        @BindView(R.id.tv_right_more)
        TextView tvRightMore;

        public MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
