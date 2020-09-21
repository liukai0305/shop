package com.example.xiangmuyi.adapter.discoveradapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.bean.discoverbean.DiscoverHotspotBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotSpotAdapter extends RecyclerView.Adapter {


    private Context context;
    private List<DiscoverHotspotBean.DataBean.ListBean> data = new ArrayList<>();

    public static final int ONE = 0;
    public static final int TWO = 1;
    public static final int THREE = 2;

    public HotSpotAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<DiscoverHotspotBean.DataBean.ListBean> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        List<DiscoverHotspotBean.DataBean.ListBean.FilePathListBean> filePathList = data.get(position).getFilePathList();
        if (filePathList.size() == 3) {
            return ONE;
        } else if (filePathList.size() == 1) {
            return TWO;
        } else {
            return THREE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ONE:
                return new OneVh(LayoutInflater.from(context).inflate(R.layout.adapter_hotspot_one, parent, false));
            case TWO:
                return new TwoVh(LayoutInflater.from(context).inflate(R.layout.adapter_hotspot_two, parent, false));
            case THREE:
                return new ThreeVh(LayoutInflater.from(context).inflate(R.layout.adapter_hotspot_three, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DiscoverHotspotBean.DataBean.ListBean listBean = data.get(position);
        List<DiscoverHotspotBean.DataBean.ListBean.FilePathListBean> list = listBean.getFilePathList();

        RoundedCorners roundedCorners = new RoundedCorners(15);
        RequestOptions requestOptions = RequestOptions.bitmapTransform(roundedCorners);

        switch (getItemViewType(position)) {
            case ONE:
                OneVh oneVh = (OneVh) holder;
                oneVh.tvTitleA.setText(listBean.getTitle());
                oneVh.tvHotspotDate.setText(listBean.getCreateTime());
                Glide.with(context).load(list.get(0).getFilePath()).apply(requestOptions).into(oneVh.imgOneA);
                Glide.with(context).load(list.get(1).getFilePath()).apply(requestOptions).into(oneVh.imgOneB);
                Glide.with(context).load(list.get(2).getFilePath()).apply(requestOptions).into(oneVh.imgOneC);
                break;
            case TWO:
                TwoVh twoVh = (TwoVh) holder;
                twoVh.tvHotspotContent2.setText(listBean.getTitle());
                twoVh.tvHotspotDate2.setText(listBean.getCreateTime());
                Glide.with(context).load(list.get(0).getFilePath()).apply(requestOptions).into(twoVh.imgTwo);
                break;
            case THREE:
                ThreeVh threeVh = (ThreeVh) holder;
                threeVh.tvHotspotContent3.setText(listBean.getTitle());
                threeVh.tvHotspotDate3.setText(listBean.getCreateTime());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class OneVh extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title_a)
        TextView tvTitleA;
        @BindView(R.id.img_one_a)
        ImageView imgOneA;
        @BindView(R.id.img_one_b)
        ImageView imgOneB;
        @BindView(R.id.img_one_c)
        ImageView imgOneC;
        @BindView(R.id.tv_hotspot_date)
        TextView tvHotspotDate;

        public OneVh(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class TwoVh extends RecyclerView.ViewHolder {
        @BindView(R.id.img_two)
        ImageView imgTwo;
        @BindView(R.id.tv_hotspot_content2)
        TextView tvHotspotContent2;
        @BindView(R.id.tv_hotspot_date2)
        TextView tvHotspotDate2;

        public TwoVh(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ThreeVh extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_hotspot_content3)
        TextView tvHotspotContent3;
        @BindView(R.id.tv_hotspot_date3)
        TextView tvHotspotDate3;

        public ThreeVh(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
