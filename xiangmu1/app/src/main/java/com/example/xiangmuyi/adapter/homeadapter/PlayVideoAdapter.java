package com.example.xiangmuyi.adapter.homeadapter;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.base.BaseAdapter;
import com.example.xiangmuyi.bean.homebean.HomeVideoBean;
import com.example.xiangmuyi.common.PlayVideo;
import com.example.xiangmuyi.utils.GildeUtils;
import com.example.xiangmuyi.utils.TVUtils;
import com.example.xiangmuyi.utils.UserUtils;

import java.util.HashMap;
import java.util.List;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class PlayVideoAdapter extends BaseAdapter {

    private PlayVideo video;

    public PlayVideoAdapter(List data, Context context) {
        super(data, context);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(getLayout(), parent, false);
        BaseViewHolder vh = new BaseViewHolder(view);
        return vh;
    }

    @Override
    protected int getLayout() {
        return R.layout.item_playvideo;
    }

    @Override
    protected void bindData(BaseViewHolder vh, Object data, int position) {
        HomeVideoBean.DataBean.ListBean bean = (HomeVideoBean.DataBean.ListBean) data;
        video = (PlayVideo) vh.getViewById(R.id.video);
       //第一帧
         Bitmap bitmap = getVideoThumbnail(bean.getVideoPath());
        video.thumbImageView.setImageBitmap(bitmap);
        JzvdStd.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        if (!TextUtils.isEmpty(bean.getVideoPath())){
            video.setUp(bean.getVideoPath(),bean.getTitle());
        }
        ImageView imghead = (ImageView) vh.getViewById(R.id.img_head);
        GildeUtils.loadRoundImg(context,bean.getHeadUrl(),imghead);
        TextView txtUserName = (TextView) vh.getViewById(R.id.txt_username);
        TVUtils.setText(txtUserName,bean.getNickName());
        TextView txtLive = (TextView) vh.getViewById(R.id.txt_live);
        TVUtils.setText(txtLive,bean.getLikeNumber());
        TextView txtComment = (TextView) vh.getViewById(R.id.txt_comment);
        TVUtils.setText(txtComment,bean.getCommentNumber());
        int resByLevel = UserUtils.getResByLevel(bean.getLevel());
        ImageView imgLv = (ImageView) vh.getViewById(R.id.img_level);
        imgLv.setImageResource(resByLevel);

        ImageView imgback = (ImageView) vh.getViewById(R.id.img_back);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        video.titleTextView.setGravity(Gravity.CENTER);
        video.titleTextView.setTextSize(20);
        layoutParams.setMargins(80,10,0,0);
        video.titleTextView.setLayoutParams(layoutParams);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video.CURRENT_JZVD.clearFloatScreen();
//                if (video.screen== Jzvd.SCREEN_FULLSCREEN){
//                    Jzvd.backPress();
//                }else {
//
//                }
                Jzvd.goOnPlayOnResume();
            }
        });

    }
    public void playVideo(){
        if (video!=null){
            video.startButton.performClick();
        }
    }




    public static Bitmap getVideoThumbnail(String url) {
        Bitmap bitmap = null;
        //MediaMetadataRetriever 是android中定义好的一个类，提供了统一
        //的接口，用于从输入的媒体文件中取得帧和元数据；
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据文件路径获取缩略图
            retriever.setDataSource(url, new HashMap());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }


}

