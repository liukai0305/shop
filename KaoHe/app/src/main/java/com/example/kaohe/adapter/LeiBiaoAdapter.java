package com.example.kaohe.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.kaohe.R;
import com.example.kaohe.bean.LieBiaoBean;
import com.example.kaohe.bean.TuiJianBean;
import com.example.kaohe.utils.DateUtils;
import com.example.kaohe.utils.SystemUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.jzvd.JzvdStd;

public class LeiBiaoAdapter extends RecyclerView.Adapter {
    private List<LieBiaoBean.DataBean.DynamicsBean> liebiaoList = new ArrayList<>();
    private List<TuiJianBean.DataBean> tuijianList = new ArrayList<>();
    private Context context;
    private boolean flag;
    private LieBiaoBean.DataBean.DynamicsBean bean;

    public LeiBiaoAdapter(Context context) {
        this.context = context;
    }

    public void addListData(List<LieBiaoBean.DataBean.DynamicsBean> liebiaoList) {
        this.liebiaoList.addAll(liebiaoList);
        notifyDataSetChanged();
    }

    public void addTuiData(List<TuiJianBean.DataBean> tuijianList) {
        this.tuijianList.addAll(tuijianList);
        notifyDataSetChanged();
    }

    int TUPIAN = 1;
    int SHIPIN = 2;
    int TUIJIAN = 3;

    @Override
    public int getItemViewType(int position) {
        String videoPath1 = liebiaoList.get(position).getVideoPath();
        if(position==2){
            return TUIJIAN;
        }else if(!TextUtils.isEmpty(videoPath1)&&videoPath1.endsWith(".mp4")){
            return SHIPIN;
        }else{
            return TUPIAN;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TUPIAN) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_tupian, null);
            return new ViewHolder1(view);
        } else if (viewType == SHIPIN) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_shipin, null);
            return new ViewHolder2(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_tui, null);
            return new ViewHolder3(view);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        bean = liebiaoList.get(position);
        if (getItemViewType(position) == TUPIAN) {
            ViewHolder1 holder1 = (ViewHolder1) holder;
            if (liebiaoList.size() > 0) {
                int round = SystemUtils.px2Dp(context, 50);
                RoundedCorners roundedCorners = new RoundedCorners(round);
                RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
                Glide.with(context).load(bean.getHeadUrl()).apply(options).into(holder1.tu_img_head);

                if (!TextUtils.isEmpty(bean.getNickName())) {

                    holder1.tu_tv_username.setText(bean.getNickName());
                }

                //显示时间
                if (!TextUtils.isEmpty(bean.getCreateTime())) {
                    Long time = DateUtils.getDateToTime(bean.getCreateTime(), null);
                    String dateStr = DateUtils.getStandardDate(time);
                    holder1.tv_date.setText(dateStr);

                }
                //内容的显示 ##包裹起来数据  @符号后面的数据
                String content = bean.getContent();
                SpannableString mSpannableString = new SpannableString(content);
                //第一层切割
                String exp_1 = "#[^#]*#";
                Pattern pattern = Pattern.compile(exp_1);
                Matcher matcher = pattern.matcher(content);
                while (matcher.find()) {
                    int start = matcher.start();
                    int end = matcher.end();
                    //使用富文本修改 内容中的文字颜色
                    mSpannableString.setSpan(new ForegroundColorSpan(Color.BLUE), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                }
                String exp_2 = "@.+?\\s";
                Pattern pattern2 = Pattern.compile(exp_2);
                Matcher matcher2 = pattern2.matcher(content);
                while (matcher2.find()) {
                    int start1 = matcher2.start();
                    int end1 = matcher2.end();
                    //使用富文本修改 内容中的文字颜色
                    mSpannableString.setSpan(new ForegroundColorSpan(Color.BLUE), start1, end1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

                }
                holder1.tv_expand.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flag = false;
                        holder1.tv_des.setEllipsize(null);
                        holder1.tv_des.setSingleLine(flag);
                        holder1.tv_expand.setVisibility(View.GONE);
                        holder1.tv_putaway.setVisibility(View.VISIBLE);
                    }
                });
                holder1.tv_putaway.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flag = true;
                        holder1.tv_des.setLines(3);
                        holder1.tv_putaway.setVisibility(View.GONE);
                        holder1.tv_expand.setVisibility(View.VISIBLE);
                    }
                });


                List<LieBiaoBean.DataBean.DynamicsBean.ImagesBean> imgsList = bean.getImages();
                if (imgsList.size() == 1) {
                    //一张图片
                    holder1.image.setVisibility(View.VISIBLE);
                    holder1.tu_rcy_images.setVisibility(View.GONE);
                    Glide.with(context).load(imgsList.get(0).getFilePath()).into(holder1.image);
                } else if (imgsList.size() > 1) {
                    holder1.tu_rcy_images.setVisibility(View.VISIBLE);
                    holder1.image.setVisibility(View.GONE);
                    ImageAdapter imageAdapter = new ImageAdapter(context, imgsList);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
                    holder1.tu_rcy_images.setLayoutManager(gridLayoutManager);
                    holder1.tu_rcy_images.setAdapter(imageAdapter);
                    holder1.tu_rcy_images.setAdapter(imageAdapter);
                } else {
                    //没有图片全部消失
                    holder1.image.setVisibility(View.GONE);
                    holder1.tu_rcy_images.setVisibility(View.GONE);
                }
                holder1.tv_des.setText(mSpannableString);
                holder1.txt_follow_nums.setText(bean.getLikeNumber() + "");
                holder1.tu_txt_comment.setText(bean.getCommentNumber() + "");

            }
        } else if (getItemViewType(position) == SHIPIN) {
            ViewHolder2 holder2 = (ViewHolder2) holder;
            int round = SystemUtils.px2Dp(context, 50);
            RoundedCorners roundedCorners = new RoundedCorners(round);
            RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
            Glide.with(context).load(bean.getHeadUrl()).apply(options).into(holder2.shi_img_head);
            if (!TextUtils.isEmpty(bean.getNickName())) {
                holder2.shi_tv_username.setText(bean.getNickName());
            }

            //显示时间
            if (!TextUtils.isEmpty(bean.getCreateTime())) {
                Long time = DateUtils.getDateToTime(bean.getCreateTime(), null);
                String dateStr = DateUtils.getStandardDate(time);
                holder2.shi_tv_date.setText(dateStr);

            }
            //内容的显示 ##包裹起来数据  @符号后面的数据
            String content = bean.getContent();
            SpannableString mSpannableString = new SpannableString(content);
            //第一层切割
            String exp_1 = "#[^#]*#";
            Pattern pattern = Pattern.compile(exp_1);
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                //使用富文本修改 内容中的文字颜色
                mSpannableString.setSpan(new ForegroundColorSpan(Color.BLUE), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
            String exp_2 = "@.+?\\s";
            Pattern pattern2 = Pattern.compile(exp_2);
            Matcher matcher2 = pattern2.matcher(content);
            while (matcher2.find()) {
                int start1 = matcher2.start();
                int end1 = matcher2.end();
                //使用富文本修改 内容中的文字颜色
                mSpannableString.setSpan(new ForegroundColorSpan(Color.BLUE), start1, end1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
            holder2.shi_tv_des.setText(mSpannableString);

            holder2.jiao.setUp(bean.getVideoPath(), bean.getNickName());
            Glide.with(context).load(bean.getCover()).into(holder2.jiao.thumbImageView);

            holder2.txt_follow_nums.setText(bean.getLikeNumber() + "");
            holder2.shi_txt_comment.setText(bean.getCommentNumber() + "");
        } else {
            ViewHolder3 holder3 = (ViewHolder3) holder;
            holder3.tu_rcy_remen.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            TuiJIanAdapter tuiJIanAdapter = new TuiJIanAdapter(context, tuijianList);
            holder3.tu_rcy_remen.setAdapter(tuiJIanAdapter);

        }
    }

    private static final String TAG = "LeiBiaoAdapter";

    @Override
    public int getItemCount() {
        return tuijianList.size()>0?liebiaoList.size():liebiaoList.size()+1;
    }

    public static
    class ViewHolder1 extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView tu_img_head;
        public TextView tu_tv_username;
        public TextView tv_date;
        public ImageView tu_img_level;
        public ImageView image;

        public TextView tu_txt_follow;
        public TextView tv_des;
        public RecyclerView tu_rcy_images;
        public TextView txt_follow_nums;
        public TextView tu_txt_comment;
        public TextView tv_putaway;
        public TextView tv_expand;

        public ViewHolder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tu_img_head = (ImageView) rootView.findViewById(R.id.tu_img_head);
            this.tu_tv_username = (TextView) rootView.findViewById(R.id.tu_tv_username);
            this.tv_date = (TextView) rootView.findViewById(R.id.tv_date);
            this.tu_img_level = (ImageView) rootView.findViewById(R.id.tu_img_level);
            this.image = (ImageView) rootView.findViewById(R.id.image);

            this.tu_txt_follow = (TextView) rootView.findViewById(R.id.tu_txt_follow);
            this.tv_des = (TextView) rootView.findViewById(R.id.tv_des);
            this.tu_rcy_images = (RecyclerView) rootView.findViewById(R.id.tu_rcy_images);
            this.txt_follow_nums = (TextView) rootView.findViewById(R.id.txt_follow_nums);
            this.tu_txt_comment = (TextView) rootView.findViewById(R.id.tu_txt_comment);
            this.tv_expand = (TextView) rootView.findViewById(R.id.tv_expand);
            this.tv_putaway = (TextView) rootView.findViewById(R.id.tv_putaway);
        }

    }


    public static
    class ViewHolder2 extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView shi_img_head;
        public TextView shi_tv_username;
        public TextView shi_tv_date;
        public ImageView shi_img_level;
        public TextView shi_txt_follow;
        public TextView shi_tv_des;
        public JzvdStd jiao;
        public TextView txt_follow_nums;
        public TextView shi_txt_comment;

        public ViewHolder2(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.shi_img_head = (ImageView) rootView.findViewById(R.id.shi_img_head);
            this.shi_tv_username = (TextView) rootView.findViewById(R.id.shi_tv_username);
            this.shi_tv_date = (TextView) rootView.findViewById(R.id.shi_tv_date);
            this.shi_img_level = (ImageView) rootView.findViewById(R.id.shi_img_level);
            this.shi_txt_follow = (TextView) rootView.findViewById(R.id.shi_txt_follow);
            this.shi_tv_des = (TextView) rootView.findViewById(R.id.shi_tv_des);
            this.jiao = (JzvdStd) rootView.findViewById(R.id.jiao);
            this.txt_follow_nums = (TextView) rootView.findViewById(R.id.txt_follow_nums);
            this.shi_txt_comment = (TextView) rootView.findViewById(R.id.shi_txt_comment);
        }

    }

    public static
    class ViewHolder3 extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tui_tv_remen;
        public TextView tu_tv_huati;
        public RecyclerView tu_rcy_remen;

        public ViewHolder3(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tui_tv_remen = (TextView) rootView.findViewById(R.id.tui_tv_remen);
            this.tu_tv_huati = (TextView) rootView.findViewById(R.id.tu_tv_huati);
            this.tu_rcy_remen = (RecyclerView) rootView.findViewById(R.id.tu_rcy_remen);
        }
    }
}
