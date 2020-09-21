package com.qutu.talk.adapter.dashen;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.bean.RecommendedDynamicBean;
import com.qutu.talk.bean.dashen.MainHomePageBean;
import com.qutu.talk.utils.MediaManager;

import java.util.List;

public class RecomHomePageAdapter extends BaseQuickAdapter<MainHomePageBean.DataBean, BaseViewHolder> {

    public LoadingDailog dialog;

    private CountDownTimer mSingleCountDownTimer;

    boolean mHasFinishTimer = true;

    Handler mHandler = new Handler(Looper.getMainLooper());
    private Context mContext;


    public RecomHomePageAdapter(int layoutResId, @Nullable List<MainHomePageBean.DataBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(mContext)
                .setMessage("加载中...")
                .setCancelable(true)
                .setCancelOutside(true);
        dialog = loadBuilder.create();
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MainHomePageBean.DataBean item) {

        ArmsUtils.obtainAppComponentFromContext(mContext)
                .imageLoader()
                .loadImage(mContext, ImageConfigImpl
                        .builder()
                        .url(item.getImg_1())
                        .placeholder(R.mipmap.no_tou)
                        .imageView(helper.getView(R.id.head_img_recom_home_page))
                        .errorPic(R.mipmap.no_tou)
                        .build());

        helper.setText(R.id.price_home_page, item.getSkill_name())
                .setText(R.id.name_home_page, item.getNickname());

        if (item.getIsOnline() == 0) {
            helper.getView(R.id.zx_img_recom_home_page).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.zx_img_recom_home_page).setVisibility(View.VISIBLE);
        }

        if (!item.isPlay()) {
            helper.setText(R.id.time_recom_home_page, item.getAudio_time() + "''");
            helper.getView(R.id.gif_jing).setVisibility(View.VISIBLE);
            helper.getView(R.id.gif_jing_gif).setVisibility(View.GONE);
        } else {
            helper.setText(R.id.time_recom_home_page, item.getCurrentTime() + "''");
            helper.getView(R.id.gif_jing).setVisibility(View.GONE);
            helper.getView(R.id.gif_jing_gif).setVisibility(View.VISIBLE);
        }

        /*ImageView imageView = helper.getView(R.id.label_home_page);
        if (item.getSkill_id() == 1) {
            imageView.setImageResource(R.mipmap.home_label_wzry);
        } else if (item.getSkill_id() == 2) {
            imageView.setImageResource(R.mipmap.home_label_hpjy);
        } else if (item.getSkill_id() == 3) {
            imageView.setImageResource(R.mipmap.home_label_lol);
        } else {
            imageView.setImageResource(R.mipmap.home_label_pubg);
        }*/

        helper.getView(R.id.bofang_and_zanting).setOnClickListener(v -> {
            if (!item.isPlay()) {
                if (dialog != null) {
                    dialog.show();
                }

                //停止其它正在播放的录音
                if (mSingleCountDownTimer != null && !mHasFinishTimer) {
                    mSingleCountDownTimer.cancel();
                    MediaManager.pause();
                    List<MainHomePageBean.DataBean> list = getData();
                    int size = list.size();
                    int position = 0;
                    for (int i = 0; i < size; i++) {
                        MainHomePageBean.DataBean dataBean = list.get(i);
                        if (dataBean.isPlay()) {
                            position = i;
                            dataBean.setPlay(false);
                            break;
                        }
                    }
                    LogUtils.debugInfo("==正在倒计时，要停止它");
                    notifyItemChanged(position, "text_stop_timer");
//                        notifyItemChanged(position);
                }
                mHasFinishTimer = false;
                MediaManager.playSoundAsync(item.getAudio(), null, new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        item.setPlay(true);
                        helper.setImageResource(R.id.bofang, R.mipmap.yy_zt);
                        helper.getView(R.id.gif_jing_gif).setVisibility(View.VISIBLE);
                        helper.getView(R.id.gif_jing).setVisibility(View.GONE);
                        mp.start();
//                            CountDownTimer countDownTimer = mCountDownCounters.get(helper.getView(R.id.dy_voice_time).hashCode());
//                            if (countDownTimer != null) {
//                                //将复用的倒计时清除
//                                countDownTimer.cancel();
//                            }
                        int t = Integer.parseInt(item.getAudio_time().trim());
                        long time = (long) t * 1000;
                        mSingleCountDownTimer = new CountDownTimer(time, 1000) {
                            @Override
                            public void onTick(long l) {

                                int times = (int) (l / 1000);

                                item.setCurrentTime(times + "");

//                                    helper.setText(R.id.dy_voice_time, (int) (l / 1000) + "s");
                                LogUtils.debugInfo("====倒计时", (l / 1000) + "s");

//                                    notifyDataSetChanged();
                                int position = helper.getPosition();

                                notifyItemChanged(position, "text_timer");

                            }

                            @Override
                            public void onFinish() {//倒计时结束了
                                helper.setText(R.id.time_recom_home_page, item.getAudio_time() + "''");
                                helper.setImageResource(R.id.bofang, R.mipmap.yy_bf);
                                helper.getView(R.id.gif_jing).setVisibility(View.VISIBLE);
                                helper.getView(R.id.gif_jing_gif).setVisibility(View.GONE);
                                MediaManager.pause();
                                MediaManager.release();
                                item.setPlay(false);
                                int position = helper.getPosition();
                                notifyItemChanged(position, "text_timer");
                                mHasFinishTimer = true;
                            }
                        }.start();
                    }
                });
            } else {
                item.setPlay(false);
                helper.setImageResource(R.id.bofang, R.mipmap.yy_bf);
                helper.setText(R.id.time_recom_home_page, item.getAudio_time() + "''");
                helper.getView(R.id.gif_jing_gif).setVisibility(View.GONE);
                helper.getView(R.id.gif_jing).setVisibility(View.VISIBLE);
                MediaManager.pause();
                MediaManager.release();
            }
        });
    }

    @Override
    protected void convertPayloads(@NonNull BaseViewHolder helper, MainHomePageBean.DataBean item, @NonNull List<Object> payloads) {
        super.convertPayloads(helper, item, payloads);
        if (payloads.isEmpty()) {
            convert(helper, item);
        } else {
            String payload = (String) payloads.get(0);
            if ("text_timer".equals(payload)) {

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        String time = item.getCurrentTime();
                        if (item.isPlay()) {
                            helper.setText(R.id.time_recom_home_page, item.getCurrentTime() + "''");
//                            helper.setText(R.id.dy_share, forward_num + 1+"");
                            LogUtils.debugInfo("====倒计时更新======", time + "");
                        } else {
                            helper.setText(R.id.time_recom_home_page, item.getAudio_time() + "''");
                        }
                        LogUtils.debugInfo("====时间======", time + "");
                    }
                });

            } else if ("text_stop_timer".equals(payload)) {

                LogUtils.debugInfo("====停止了哈哈哈======");

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        String time = item.getCurrentTime();
                        if (item.isPlay()) {
                            helper.setText(R.id.time_recom_home_page, item.getCurrentTime() + "''");
                            LogUtils.debugInfo("==停止==倒计时更新======", time + "");
                        } else {
                            helper.setText(R.id.time_recom_home_page, item.getAudio_time() + "''");
                            helper.setImageResource(R.id.bofang, R.mipmap.yy_bf);
                            helper.getView(R.id.gif_jing_gif).setVisibility(View.GONE);
                            helper.getView(R.id.gif_jing).setVisibility(View.VISIBLE);
                            LogUtils.debugInfo("==停止==停止倒计时更新======", item.getAudio_time() + "");
                        }
                        LogUtils.debugInfo("==停止==时间======", time + "");
                    }
                });

            }
        }
    }
}
