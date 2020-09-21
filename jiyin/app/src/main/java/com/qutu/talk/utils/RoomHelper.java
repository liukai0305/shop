package com.qutu.talk.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.mvp.IView;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.qutu.talk.R;
import com.qutu.talk.activity.room.AdminHomeActivity;
import com.qutu.talk.adapter.FragmentAdapter;
import com.qutu.talk.adapter.PagerAdapter;
import com.qutu.talk.adapter.RoomMessageAdapter;
import com.qutu.talk.app.service.RoomPlayService;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.AgreeCpResult;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.EmojiBean;
import com.qutu.talk.bean.LocalMusicInfo;
import com.qutu.talk.bean.LoginData;
import com.qutu.talk.bean.MessageBean;
import com.qutu.talk.bean.MusicYinxiao;
import com.qutu.talk.fragment.EmojiFragment;
import com.qutu.talk.fragment.YinxiaoFragment;
import com.qutu.talk.popup.RequestCPDialog;
import com.qutu.talk.service.CommonModel;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import io.agora.rtc.RtcEngine;
import io.agora.rtm.ErrorInfo;
import io.agora.rtm.ResultCallback;
import io.agora.rtm.RtmChannel;
import io.agora.rtm.RtmClient;
import io.agora.rtm.RtmMessage;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.relex.circleindicator.CircleIndicator;

public class RoomHelper {

    /**
     *
     * @param context
     * @param recyclerView
     * @param roomAdapter
     * @param systemTip 系统提示
     * @param notice 欢迎语
     */
    public static void initRecyclerViewAndTitle(Context context,RecyclerView recyclerView, RoomMessageAdapter roomAdapter,String systemTip,String notice){

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(roomAdapter);
        View headerMessage = ArmsUtils.inflate(context, R.layout.message_header);
        TextView textNameXitong = headerMessage.findViewById(R.id.textNameXitong);
        textNameXitong.setText(systemTip);
        TextView textMessage2 = headerMessage.findViewById(R.id.textMessage2);
        textMessage2.setVisibility(View.VISIBLE);
        textMessage2.setText(notice);

        roomAdapter.addHeaderView(headerMessage);


    }

    //刷新adapter
    public static void refreshAdapter(MessageBean messageBean,RecyclerView recyclerView, RoomMessageAdapter roomAdapter){
        if(recyclerView == null || roomAdapter == null){
            return;
        }
        roomAdapter.getData().add(messageBean);
        roomAdapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(roomAdapter.getData().size());
    }


    /**
     * 展示表情git效果
     */
    public static void loadGifShow(MyBaseArmActivity baseArmActivity,ImageView imgGif, String url) {
        imgGif.setVisibility(View.VISIBLE);
        baseArmActivity.loadOneTimeGif(baseArmActivity, imgGif, url, new MyBaseArmActivity.GifListener() {
            @Override
            public void gifPlayComplete() {
                imgGif.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 获取表情
     */
    public static void loadEmoji(CommonModel commonModel, IView iView, RxErrorHandler mErrorHandler, LinearLayout rlEmoji, ViewPager viewPager, CircleIndicator indicator, FragmentManager manager) {

        RxUtils.loading(commonModel.emoji_list(""), iView)
                .subscribe(new ErrorHandleSubscriber<EmojiBean>(mErrorHandler) {
                    @Override
                    public void onNext(EmojiBean emojiBean) {

                        List<EmojiBean.DataBean> emojiList = emojiBean.getData();

                        if (emojiBean != null && emojiList != null && emojiList.size() > 0) {

                            rlEmoji.setVisibility(View.VISIBLE);

                            List<Fragment> listFragment = new ArrayList<>();

                            int pageSize = 10;//每页10个

                            int size = emojiList.size();

                            int tem = size % pageSize;

                            int page = size / pageSize;

                            int pageIndex = page;

                            if (page == 0) {//小于1页
                                pageIndex = 1;
                            } else {//大于1页

                                if (tem != 0) {//不能被10出尽

                                    pageIndex = pageIndex + 1;

                                }

                            }
                            //添加fragment
                            EmojiFragment emojiFragment;
                            for (int i = 0; i < pageIndex; i++) {

                                int length = i * pageSize + pageSize;
                                //将总list拆分成页
                                List<EmojiBean.DataBean> childList = new ArrayList<>();
                                for (int j = i * pageSize; j < (length > size ? size : length); j++) {
                                    childList.add(emojiList.get(j));
                                }
                                emojiFragment = EmojiFragment.getInstance(childList);
                                listFragment.add(emojiFragment);
                            }

                            PagerAdapter pagerAdapter = new PagerAdapter(manager, listFragment);
                            viewPager.setAdapter(pagerAdapter);
                            indicator.setViewPager(viewPager);

                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
//                        toast("请重新请求数据");
                    }
                });

//        myFragment1 = EmojiFragment.getInstance(0);
//        EmojiFragment myFragment2 = EmojiFragment.getInstance(1);
//        list.add(myFragment1);
//        list.add(myFragment2);
//        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), list);
//        viewPager.setAdapter(pagerAdapter);
//        indicator.setViewPager(viewPager);
    }

    /**
     * 获取本地音乐
     * @return
     */
    public static List<LocalMusicInfo> getLocalMusic() {

        List<LocalMusicInfo> list = null;
        try {
            list = LitePal.findAll(LocalMusicInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list == null) {
            list = new ArrayList<>();
        }

        return list;
    }

    /**
     * 加载音效
     */
    public static void loadYinXiao(CommonModel commonModel, IView iView, RxErrorHandler mErrorHandler, ViewPager viewPager, RtcEngine rtcEngine, FragmentManager manager){
        RxUtils.loading(commonModel.get_sound("", UserManager.getUser().getUserId() + ""), iView)
                .subscribe(new ErrorHandleSubscriber<MusicYinxiao>(mErrorHandler) {
                    @Override
                    public void onNext(MusicYinxiao yinxiao) {
//                        yinxiaoAdapter = new YinxiaoAdapter(AdminHomeActivity.this, mRtcEngine);
//                        myGrid.setAdapter(yinxiaoAdapter);
//                        yinxiaoAdapter.getList_adapter().clear();
//                        yinxiaoAdapter.getList_adapter().addAll(yinxiao.getData().getYinxiao());
//                        yinxiaoAdapter.notifyDataSetChanged();
                        List<Fragment> mFragments = new ArrayList<>();
                        List<MusicYinxiao.DataBean.YinxiaoBean> yinxiaoList = yinxiao.getData().getYinxiao();
                        if (yinxiaoList.size() > 6) {
                            YinxiaoFragment yinxiaoFragment1 = YinxiaoFragment.getInstance(0, yinxiao);
                            YinxiaoFragment yinxiaoFragment2 = YinxiaoFragment.getInstance(1, yinxiao);
                            mFragments.add(yinxiaoFragment1);
                            mFragments.add(yinxiaoFragment2);
                            yinxiaoFragment1.setRt(rtcEngine);
                            yinxiaoFragment2.setRt(rtcEngine);
                        } else {
                            YinxiaoFragment yinxiaoFragment1 = YinxiaoFragment.getInstance(0, yinxiao);
                            mFragments.add(yinxiaoFragment1);
                            yinxiaoFragment1.setRt(rtcEngine);
                        }
                        FragmentAdapter adapter = new FragmentAdapter(manager, mFragments);
                        viewPager.setAdapter(adapter);
                    }
                });
    }

    /**
     * 设置飞用户头像
     */
    public static void setImageFei(Context context,RelativeLayout layoutRoot,ImageView imgVedio, ImageView imgVisiFei, String imgUrl, int feiLeft,int feiTop,boolean isSeven) {

        RelativeLayout.LayoutParams paramsImgFei = (RelativeLayout.LayoutParams) imgVisiFei.getLayoutParams();
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(paramsImgFei);
        layoutRoot.addView(imageView);

        GlideArms.with(context)
                .load(imgUrl)
                .placeholder(R.mipmap.room_kazuo_kong)
                .error(R.mipmap.room_kazuo_kong)
                .circleCrop()
                .into(imageView);

        int[] location = new int[2];
        imgVedio.getLocationOnScreen(location);
        if (isSeven) {
            startAnimotion(location[0] - feiLeft + 90, location[1] - feiTop + 175, imageView);
        } else {
            startAnimotion(location[0] - feiLeft + 70, location[1] - feiTop + 150, imageView);
        }
    }

    /**
     * 飞的动画
     */
    public static void startAnimotion(int width, int height, ImageView imgVisiFei) {
        imgVisiFei.setVisibility(View.VISIBLE);

        final TranslateAnimation translateAnimation = new TranslateAnimation(0,
                width, 0, height);
        translateAnimation.setDuration(2000);
        translateAnimation.setFillAfter(true);
        translateAnimation.setFillBefore(false);
        translateAnimation.setRepeatMode(ScaleAnimation.RESTART);
        translateAnimation.setRepeatCount(0);

        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imgVisiFei.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (imgVisiFei != null) {
                            imgVisiFei.clearAnimation();
                            translateAnimation.cancel();
                            imgVisiFei.setVisibility(View.GONE);
                            RelativeLayout layoutParent = (RelativeLayout) imgVisiFei.getParent();
                            layoutParent.removeView(imgVisiFei);
                        }
                    }
                }, 1000);//延时1秒后隐藏

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //开始动画
        imgVisiFei.startAnimation(translateAnimation);
        translateAnimation.start();
    }

    /**
     * 第一次结为CP消息
     * @param baseArmActivity
     * @param msg
     * @param recyclerView
     * @param roomMessageAdapter
     * @param commonModel
     * @param mErrorHandler
     * @param mRtmClient
     * @param mRtmChannel
     * @param nickColors
     */
    public static void withCPMsg(MyBaseArmActivity baseArmActivity,String msg,RecyclerView recyclerView,RoomMessageAdapter roomMessageAdapter,
                                 CommonModel commonModel, RxErrorHandler mErrorHandler,RtmClient mRtmClient, RtmChannel mRtmChannel,String nickColors){
        try {
            JSONObject object = new JSONObject(msg);

            if (object != null) {

                String msgType = object.getString("messageType");

                if (!TextUtils.isEmpty(msgType)) {

                    if (TextUtils.equals("2", msgType)) {//,第一次收到结为CP消息，不是第一次的话会走频道消息

                        String nickName = object.getString("nickName");

                        String user_id = object.getString("user_id");

                        String headimgurlss = object.getString("headimgurl");

                        String nick_color = object.getString("nick_color");

                        LoginData localUser = UserManager.getUser();

                        RequestCPDialog requestCPDialog = new RequestCPDialog(baseArmActivity, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                switch (view.getId()) {
                                    case R.id.tv_left://拒绝，发送点对点消息
                                        RxUtils.loading(commonModel.handle_cp(localUser.getToken(), localUser.getUserId() + "",
                                                "2"), baseArmActivity)
                                                .subscribe(new ErrorHandleSubscriber<AgreeCpResult>(mErrorHandler) {
                                                    @Override
                                                    public void onNext(AgreeCpResult agreeCpResult) {

//                                                                                        if(agreeCpResult != null && agreeCpResult.getData() != null){

                                                        baseArmActivity.toast("很遗憾，结为守护CP失败");

                                                        String myUserName = localUser.getNickname();

                                                        String messageType = "1";

                                                        String cpType = "2";

                                                        JsonObject rootObj = new JsonObject();

                                                        rootObj.addProperty("nickName", myUserName);

                                                        rootObj.addProperty("messageType", messageType);

                                                        rootObj.addProperty("cpType", cpType);

                                                        String str = rootObj.toString();

//                                                                                        Log.e("发送拒绝CP消息", str);

                                                        sendPeerMessage(user_id, str,mRtmClient);

//                                                                                        }

                                                    }
                                                });

                                        break;
                                    case R.id.tv_right://同意,发送点对点消息，频道消息

                                        RxUtils.loading(commonModel.handle_cp(localUser.getToken(), localUser.getUserId() + "",
                                                "1"), baseArmActivity)
                                                .subscribe(new ErrorHandleSubscriber<AgreeCpResult>(mErrorHandler) {
                                                    @Override
                                                    public void onNext(AgreeCpResult agreeCpResult) {

                                                        if (agreeCpResult != null && agreeCpResult.getData() != null) {

                                                            baseArmActivity.toast("哇哦，你与" + nickName + "结为守护CP啦");

                                                            String myUserNames = localUser.getNickname();

                                                            String messageTypes = "1";

                                                            String cpTypes = "1";

                                                            JsonObject rootObjs = new JsonObject();

                                                            rootObjs.addProperty("nickName", myUserNames);

                                                            rootObjs.addProperty("messageType", messageTypes);

                                                            rootObjs.addProperty("cpType", cpTypes);

                                                            String strs = rootObjs.toString();

                                                            //发送点对点消息，通知对方结为CP了
                                                            sendPeerMessage(user_id, strs,mRtmClient);

                                                            MessageBean messageBean = new MessageBean();
                                                            messageBean.setMessageType("11");
                                                            messageBean.setNickName(myUserNames);
                                                            messageBean.nick_color = nickColors;//CP颜色
                                                            messageBean.setUser_id(localUser.getUserId() + "");
                                                            messageBean.headimgurl = localUser.getHeadimgurl();

                                                            messageBean.toUser_id = user_id + "";
                                                            messageBean.toNickName = nickName;
                                                            messageBean.toNick_color = nick_color;//CP颜色
                                                            messageBean.toheadimgurl = headimgurlss;

                                                            String jsons = JSON.toJSONString(messageBean);

                                                            //更新自己的公屏
                                                            RoomHelper.refreshAdapter(messageBean,recyclerView,roomMessageAdapter);

                                                            //发送结为CP的频道消息
                                                            sendChannelMessage(jsons,mRtmClient,mRtmChannel);

                                                        }

                                                    }
                                                });
                                        break;
                                }

                            }
                        }, user_id, nickName, headimgurlss);

                        requestCPDialog.show();

                    } else if (TextUtils.equals("1", msgType)) {//对方结为CP的回应

                        String cType = object.getString("cpType");

                        if (TextUtils.equals("2", cType)) {//拒绝了CP
                            baseArmActivity.toast("很遗憾，结为守护CP失败");
                        } else {
                            String nickName = object.getString("nickName");
                            baseArmActivity.toast("哇哦，你与" + nickName + "结为守护CP啦");
                        }
                    } else if (TextUtils.equals("8", msgType)) {//收到了CP进入房间的消息

                        MessageBean newMessage = BaseUtils.getMessageBean(msg);

                        if (newMessage != null) {
                            newMessage.setMessageType("9");

                            refreshAdapter(newMessage,recyclerView,roomMessageAdapter);
                        }

                    }

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * 发送频道消息
     */
    public static void sendChannelMessage(String msg, RtmClient mRtmClient, RtmChannel mRtmChannel) {
        try {
            RtmMessage message = mRtmClient.createMessage();
            message.setText(msg);
            mRtmChannel.sendMessage(message, new ResultCallback<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    LogUtils.debugInfo("====发送消息成功");
                }

                @Override
                public void onFailure(ErrorInfo errorInfo) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 点对点消息
     * 传userid
     */
    public static void sendPeerMessage(String dst, String message, RtmClient mRtmClient) {
        RtmMessage msg = mRtmClient.createMessage();
        msg.setText(message);

        mRtmClient.sendMessageToPeer(dst, msg, new ResultCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                LogUtils.debugInfo("====发送点对点消息成功");
            }

            @Override
            public void onFailure(ErrorInfo errorInfo) {
                final int errorCode = errorInfo.getErrorCode();
                Log.d("send msg", "Fails to send the message to the peer, errorCode = "
                        + errorCode);
            }
        });
    }

    /**
     * @param context
     * @return
     */
    public static UMShareListener getUMShareListener(Context context,CommonModel commonModel, IView iView, RxErrorHandler mErrorHandler){

        UMShareListener shareListener = new UMShareListener() {

            @Override
            public void onStart(SHARE_MEDIA platform) {

            }

            @Override
            public void onResult(SHARE_MEDIA platform) {

//                Toast.makeText(context, "成功了", Toast.LENGTH_LONG).show();

                RxUtils.loading(commonModel.share_room(), iView)
                        .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                            @Override
                            public void onNext(BaseBean yinxiao) {

                            }
                        });
            }


            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                Toast.makeText(context, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Toast.makeText(context, "取消了", Toast.LENGTH_LONG).show();

            }
        };

        return shareListener;

    }

    /**
     * 保活
     */
    public static void startKeepLiveService(Context context) {

        stopkeepLiveService(context);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //android8.0以上通过startForegroundService启动service
            context.startForegroundService(new Intent(context, RoomPlayService.class));
        } else {
            context.startService(new Intent(context, RoomPlayService.class));
        }
    }

    /**
     * 停止保活
     */
    public static void stopkeepLiveService(Context context) {
        boolean isStartService = MyUtil.isServiceExisted(context, "com.qutu.talk.app.service.RoomPlayService");
        if (isStartService) {
            Intent stopIntent = new Intent(context, RoomPlayService.class);
            context.stopService(stopIntent);
        }
    }
}
