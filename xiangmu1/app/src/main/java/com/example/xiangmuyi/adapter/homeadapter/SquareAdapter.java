package com.example.xiangmuyi.adapter.homeadapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.bean.homebean.HomeSquareBean;
import com.example.xiangmuyi.common.Constants;
import com.example.xiangmuyi.ui.home.activity.PhotoViewActivity;
import com.example.xiangmuyi.ui.home.activity.SqiareViewActivity;
import com.example.xiangmuyi.utils.DateUtils;

import java.util.List;

import cn.jzvd.JzvdStd;

public class SquareAdapter extends BaseAdapter<HomeSquareBean.DataBean.DynamicsBean > {



    public SquareAdapter(List<HomeSquareBean.DataBean.DynamicsBean> data, Context context) {
        super(data, context);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_guangchang;
    }

    @Override
    protected void bindData(BaseViewHolder vh, HomeSquareBean.DataBean.DynamicsBean dynamicsBean, int position) {
        ImageView  img_head = (ImageView) vh.getViewById(R.id.img_head);
        TextView tvUserName = (TextView) vh.getViewById(R.id.tv_username);
        TextView tvDate = (TextView) vh.getViewById(R.id.tv_date);
        TextView txtFollow = (TextView) vh.getViewById(R.id.txt_follow);
        TextView tv_des = (TextView) vh.getViewById(R.id.tv_des);
        RecyclerView rclImages = (RecyclerView) vh.getViewById(R.id.rcl_images);
        ImageView imgSquare = (ImageView) vh.getViewById(R.id.img_square);
        ProgressBar progress = (ProgressBar) vh.getViewById(R.id.progress);
        JzvdStd jzVideo =(JzvdStd) vh.getViewById(R.id.jz_video);
        TextView txtFollowNums = (TextView) vh.getViewById(R.id.txt_follow_nums);
        TextView txtComment = (TextView) vh.getViewById(R.id.txt_comment);

        Log.d(TAG, "bindData: "+dynamicsBean.getImages().size());
        if (!TextUtils.isEmpty(dynamicsBean.getHeadUrl()) && (dynamicsBean.getHeadUrl().contains(".jpg")
                || dynamicsBean.getHeadUrl().contains(".jpeg")
                || dynamicsBean.getHeadUrl().contains(".png"))) {
            Glide.with(context).load(dynamicsBean.getHeadUrl()).apply(RequestOptions.circleCropTransform()).into(img_head);
        }
        if (!TextUtils.isEmpty(dynamicsBean.getNickName())) {
            tvUserName.setText(dynamicsBean.getNickName());
        }
        //显示时间
        if (!TextUtils.isEmpty(dynamicsBean.getCreateTime())) {
            Long time = DateUtils.getDateToTime(dynamicsBean.getCreateTime(), null);
            String dateStr = DateUtils.getStandardDate(time);
            tvDate.setText(dateStr);
        }
        //内容的显示 ##包裹起来数据  @符号后面的数据
        String content = dynamicsBean.getContent();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = manager.getDefaultDisplay().getWidth();
        //tv_des.setText(width);
        tv_des.setText(content);

        //判断描述下方是图片还是音视频文件及图片数量
        String videoPath = dynamicsBean.getVideoPath();
        if (!TextUtils.isEmpty(videoPath) && !TextUtils.isEmpty(dynamicsBean.getCover())) {
           jzVideo.setVisibility(View.VISIBLE);
            rclImages.setVisibility(View.GONE);
            imgSquare.setVisibility(View.GONE);
            progress.setVisibility(View.GONE);
            jzVideo.setUp(videoPath,  dynamicsBean.getContent());
          Glide.with(context).load(dynamicsBean.getCover()).into(jzVideo.thumbImageView);
        }
        List<HomeSquareBean.DataBean.DynamicsBean.ImagesBean> images = dynamicsBean.getImages();
        //集合内元素数量为1时  再判断是一张图片还是音频
        if (images.size() == 1) {
            rclImages.setVisibility(View.GONE);
            jzVideo.setVisibility(View.GONE);

            String filePath = images.get(0).getFilePath();
            //如果路径结尾为.mp3则为音频
            if (filePath.contains(".mp3")) {
                imgSquare.setVisibility(View.GONE);

            }
            if (filePath.contains(".jpg") || filePath.contains(".jpeg") || filePath.contains(".png")) {
                imgSquare.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
                Glide.with(context).load(filePath).into(imgSquare);
                imgSquare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, SqiareViewActivity.class);
                        intent.putExtra("squerimg", filePath);
                        intent.putExtra("sqposi", 0);
                        context.startActivity(intent);
                    }
                });
            }
        }
        if (images.size() > 1) {
            jzVideo.setVisibility(View.GONE);
            rclImages.setVisibility(View.VISIBLE);
            imgSquare.setVisibility(View.GONE);
            progress.setVisibility(View.GONE);
            rclImages.setLayoutManager(new GridLayoutManager(context, 3) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
//            SquareImageAdapter squareImageAdapter = new SquareImageAdapter(context);
//            squareImageAdapter.setData(images);
//            rclImages.setAdapter(squareImageAdapter);
//            squareImageAdapter.setiOnClick(new IOnClick() {
//                @Override
//                public void onClick(int position, Object o, View view) {
//                    Intent intent = new Intent(context, OtherActivity.class);
//                    intent.putExtra("squareposi", position);
//                    String s = new Gson().toJson(dataBean);
//                    intent.putExtra("squareData", s);
//                    context.startActivity(intent);
//                }
//            });
            SquareImageAdapter imageAdapter = new SquareImageAdapter(images, context);
            rclImages.setAdapter(imageAdapter);
            imageAdapter.setOnClickItem1(new SquareImageAdapter.OnClickItem() {
                @Override
                public void onClick(int pos, List<HomeSquareBean.DataBean.DynamicsBean.ImagesBean> bean) {
                    if (Constants.Square != null && Constants.Square.size() > 0) {

                        Constants.Square.clear();
                    }
                    Constants.Square.addAll(bean);
                    Constants.square = pos;

                    Intent intent = new Intent(context, PhotoViewActivity.class);
                    context.startActivity(intent);
                }
            });

            txtFollowNums.setText(String.valueOf(dynamicsBean.getLikeNumber()));
            txtComment.setText(String.valueOf(dynamicsBean.getCommentNumber()));
        }
    }

    private static final String TAG = "SquareAdapter";

}