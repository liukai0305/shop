package com.qutu.talk.activity.room;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.JsonObject;
import com.jaeger.library.StatusBarUtil;
import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;
import com.lzy.widget.CircleImageView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qutu.talk.R;
import com.qutu.talk.activity.MainActivity;
import com.qutu.talk.activity.family.FamilyDetailsActivity;
import com.qutu.talk.activity.my.MyPersonalCenterTwoActivity;
import com.qutu.talk.adapter.RoomMessageAdapter;
import com.qutu.talk.app.Api;
import com.qutu.talk.app.utils.RxUtils;
import com.qutu.talk.app.view.CircularImage;
import com.qutu.talk.base.MyBaseArmActivity;
import com.qutu.talk.base.UserManager;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.BigGiftBean;
import com.qutu.talk.bean.CommentBean;
import com.qutu.talk.bean.EnterRoom;
import com.qutu.talk.bean.FirstEvent;
import com.qutu.talk.bean.GetGapResult;
import com.qutu.talk.bean.GetSortResult;
import com.qutu.talk.bean.GifBean;
import com.qutu.talk.bean.GiftListBean;
import com.qutu.talk.bean.IsJoinFamily;
import com.qutu.talk.bean.JinSheng;
import com.qutu.talk.bean.LocalMusicInfo;
import com.qutu.talk.bean.LoginData;
import com.qutu.talk.bean.MessageBean;
import com.qutu.talk.bean.MessageEvent;
import com.qutu.talk.bean.MicUserBean;
import com.qutu.talk.bean.Microphone;
import com.qutu.talk.bean.OpenBoxBean;
import com.qutu.talk.bean.OpenTimeBean;
import com.qutu.talk.bean.OtherUser;
import com.qutu.talk.bean.PersonalityBean1;
import com.qutu.talk.bean.PushBean;
import com.qutu.talk.bean.RoomFamily;
import com.qutu.talk.bean.RoomMultipleItem;
import com.qutu.talk.bean.RoomRankBean;
import com.qutu.talk.bean.RoomUsersBean;
import com.qutu.talk.bean.SendGemResult;
import com.qutu.talk.bean.StateMessage;
import com.qutu.talk.bean.UpVideoResult;
import com.qutu.talk.bean.VipBean;
import com.qutu.talk.bean.WaitList;
import com.qutu.talk.bean.Winner;
import com.qutu.talk.di.CommonModule;
import com.qutu.talk.di.DaggerCommonComponent;
import com.qutu.talk.popup.ClearMlWindow;
import com.qutu.talk.popup.CountPKDialog;
import com.qutu.talk.popup.GemStoneDialog;
import com.qutu.talk.popup.GiftFlyDialog;
import com.qutu.talk.popup.GiftNoUserPopup;
import com.qutu.talk.popup.GiftPopw;
import com.qutu.talk.popup.KeybordWindow;
import com.qutu.talk.popup.MusicVolumeWindow;
import com.qutu.talk.popup.PaiDanDialog;
import com.qutu.talk.popup.PaimaiWindow;
import com.qutu.talk.popup.ReportWindow;
import com.qutu.talk.popup.RoomDialog;
import com.qutu.talk.popup.RoomGaoWindow;
import com.qutu.talk.popup.RoomSetWindow1;
import com.qutu.talk.popup.RoomSetWindow2;
import com.qutu.talk.popup.RoomTopWindow;
import com.qutu.talk.popup.SelectPeopleUpVideoNewDialog;
import com.qutu.talk.popup.TimePopup;
import com.qutu.talk.service.CommonModel;
import com.qutu.talk.utils.Arith;
import com.qutu.talk.utils.BaseUtils;
import com.qutu.talk.utils.Constant;
import com.qutu.talk.utils.CountTimeUtils;
import com.qutu.talk.utils.RoomHelper;
import com.qutu.talk.utils.ToastUtil;
import com.qutu.talk.view.MiniBarrageViewLayout;
import com.qutu.talk.view.RippleView;
import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.agora.rtc.Constants;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.agora.rtm.ErrorInfo;
import io.agora.rtm.ResultCallback;
import io.agora.rtm.RtmChannel;
import io.agora.rtm.RtmChannelListener;
import io.agora.rtm.RtmChannelMember;
import io.agora.rtm.RtmClient;
import io.agora.rtm.RtmClientListener;
import io.agora.rtm.RtmMessage;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.relex.circleindicator.CircleIndicator;

/**
 * 房间主人动态
 * //    messageType     1：正常的 聊天消息  2：进入房间  3： 刷新麦序列表  4 ：礼物消息  5 ：表型消息
 */
public class AdminHomeActivity extends MyBaseArmActivity {


    @BindView(R.id.room)
    RelativeLayout layoutRoot;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.textName)
    TextView textName;
    @BindView(R.id.textType)
    TextView textType;
    @BindView(R.id.textId)
    TextView textId;
    @BindView(R.id.textNum)
    TextView textNum;
    @BindView(R.id.imgRight)
    ImageView imgRight;
    @BindView(R.id.ll_bootombar)
    LinearLayout llBootombar;
    @BindView(R.id.recLayout)
    RelativeLayout recLayout;
    @BindView(R.id.imgBg)
    ImageView imgBg;
//    @BindView(R.id.textRoom)
//    TextView textRoom;
//    @BindView(R.id.text8)
//    TextView text8;


    @BindView(R.id.text1)
    TextView text1;

    @BindView(R.id.text6)
    TextView text6;

    @BindView(R.id.text7)
    TextView text7;

    @BindView(R.id.text2)
    TextView text2;

    @BindView(R.id.text5)
    TextView text5;

    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.text_last)
    TextView textLast;
    @BindView(R.id.tv_zhuchi_name)
    TextView tvZhuchiName;

    @BindView(R.id.imgVedio8)
    ImageView imgVedio8;
    @BindView(R.id.imgVedio1)
    ImageView imgVedio1;
    @BindView(R.id.imgVedio6)
    ImageView imgVedio6;


    @BindView(R.id.imgVedio7)
    ImageView imgVedio7;
    @BindView(R.id.imgVedio2)
    ImageView imgVedio2;
    @BindView(R.id.imgVedio5)
    ImageView imgVedio5;
    @BindView(R.id.imgVedio3)
    ImageView imgVedio3;
    @BindView(R.id.imgVedio4)
    ImageView imgVedio4;
    @BindView(R.id.imgVedio_last)
    ImageView imgVedioLast;

    @BindView(R.id.img_volume)
    ImageView imgVolume;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.imgShangmai)
    ImageView imgShangmai;
    @BindView(R.id.imgTing)
    CircularImage imgTing;
    @BindView(R.id.imgMusic)
    CircularImage imgMusic;
    @BindView(R.id.imgAdd)
    CircularImage imgAdd;
    @BindView(R.id.imgMessage)
    CircularImage imgMessage;
    @BindView(R.id.imgBiaoqing)
    CircularImage imgBiaoqing;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.view_need_offset)
    LinearLayout viewNeedOffset;
    @BindView(R.id.img8)
    RoundedImageView img8;
    @BindView(R.id.img1)
    RoundedImageView img1;
    @BindView(R.id.img6)
    RoundedImageView img6;
    @BindView(R.id.img7)
    RoundedImageView img7;
    @BindView(R.id.img2)
    RoundedImageView img2;
    @BindView(R.id.img5)
    RoundedImageView img5;
    @BindView(R.id.img3)
    RoundedImageView img3;
    @BindView(R.id.img4)
    RoundedImageView img4;
    @BindView(R.id.img_last)
    RoundedImageView imgLast;

    @BindView(R.id.iv_ts1)
    ImageView ivTs1;
    @BindView(R.id.iv_ts2)
    ImageView ivTs2;
    @BindView(R.id.iv_ts3)
    ImageView ivTs3;
    @BindView(R.id.iv_ts4)
    ImageView ivTs4;
    @BindView(R.id.iv_ts5)
    ImageView ivTs5;
    @BindView(R.id.iv_ts6)
    ImageView ivTs6;
    @BindView(R.id.iv_ts7)
    ImageView ivTs7;
    @BindView(R.id.iv_ts8)
    ImageView ivTs8;
    @BindView(R.id.iv_ts_last)
    ImageView ivTsLast;

    @BindView(R.id.imgBimai)
    CircularImage imgBimai;
    @BindView(R.id.textNum8)
    TextView textNum8;

    @BindView(R.id.imgPaihang)
    ImageView imgPaihang;
    @BindView(R.id.imgCollection)
    ImageView imgCollection;
    @BindView(R.id.imgPaimai)
    ImageView imgPaimai;
    @BindView(R.id.textRight)
    ImageView textRight;
    //    @BindView(R.id.textLayout)
//    TextView textLayout;
    @BindView(R.id.viewTop)
    View viewTop;
    @BindView(R.id.viewEnmojiTop)
    View viewEnmojiTop;
    @BindView(R.id.imgXunhuan)
    ImageView imgXunhuan;
    @BindView(R.id.imgLiebiao)
    ImageView imgLiebiao;
    @BindView(R.id.seekBar)
    RangeSeekBar seekBar;
    @BindView(R.id.imgFront)
    ImageView imgFront;
    @BindView(R.id.imgStop)
    ImageView imgStop;
    @BindView(R.id.imgNext)
    ImageView imgNext;
    @BindView(R.id.myGrid)
    GridView myGrid;
    @BindView(R.id.llMusic)
    LinearLayout llMusic;
    @BindView(R.id.textMusicName)
    TextView textMusicName;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    @BindView(R.id.rlEmoji)
    LinearLayout rlEmoji;
    @BindView(R.id.imgGif8)
    ImageView imgGif8;
    @BindView(R.id.imgGif1)
    ImageView imgGif1;
    @BindView(R.id.imgGif6)
    ImageView imgGif6;
    @BindView(R.id.imgGif7)
    ImageView imgGif7;
    @BindView(R.id.imgGif2)
    ImageView imgGif2;
    @BindView(R.id.imgGif5)
    ImageView imgGif5;
    @BindView(R.id.imgGif3)
    ImageView imgGif3;
    @BindView(R.id.imgGif4)
    ImageView imgGif4;
    @BindView(R.id.imgGift)
    ImageView imgGift;
    @BindView(R.id.imgGif_last)
    ImageView imgGiftLast;

    @BindView(R.id.svgImage)
    SVGAImageView svgImage;
    @BindView(R.id.imgFei)
    ImageView imgFei;//飞的动画
    //    @BindView(R.id.llLeft)
//    LinearLayout llLeft;
    @BindView(R.id.imgPopup)
    ImageView imgPopup;
    @BindView(R.id.imgFei1)
    ImageView imgFei1;
    @BindView(R.id.imgFei2)
    ImageView imgFei2;
    @BindView(R.id.imgFei3)
    ImageView imgFei3;
    @BindView(R.id.imgFei4)
    ImageView imgFei4;
    @BindView(R.id.imgFei5)
    ImageView imgFei5;
    @BindView(R.id.imgFei6)
    ImageView imgFei6;
    @BindView(R.id.imgFei7)
    ImageView imgFei7;
    @BindView(R.id.imgFei8)
    ImageView imgFei8;
    @BindView(R.id.imgFeiLast)
    ImageView imgFeiLast;
    @BindView(R.id.recyclerMusic)
    ViewPager recyclerMusic;
    @BindView(R.id.iv_jindan)
    ImageView ivJindan;
//    @BindView(R.id.room)
//    RelativeLayout room;

    @BindView(R.id.wave_1)
    RippleView mWaveView1;
    @BindView(R.id.wave_2)
    RippleView mWaveView2;
    @BindView(R.id.wave_3)
    RippleView mWaveView3;
    @BindView(R.id.wave_4)
    RippleView mWaveView4;
    @BindView(R.id.wave_5)
    RippleView mWaveView5;
    @BindView(R.id.wave_6)
    RippleView mWaveView6;
    @BindView(R.id.wave_7)
    RippleView mWaveView7;
    @BindView(R.id.wave_8)
    RippleView mWaveView8;

    @BindView(R.id.wave_last)
    RippleView mWaveViewLast;

    @BindView(R.id.img_txk_1)
    ImageView mImgTxk1;
    @BindView(R.id.img_txk_2)
    ImageView mImgTxk2;
    @BindView(R.id.img_txk_3)
    ImageView mImgTxk3;
    @BindView(R.id.img_txk_4)
    ImageView mImgTxk4;
    @BindView(R.id.img_txk_5)
    ImageView mImgTxk5;
    @BindView(R.id.img_txk_6)
    ImageView mImgTxk6;
    @BindView(R.id.img_txk_7)
    ImageView mImgTxk7;
    @BindView(R.id.img_txk_8)
    ImageView mImgTxk8;
    @BindView(R.id.img_txk_last)
    ImageView mImgTxkLast;

    @BindView(R.id.layout_vip_enter)
    RelativeLayout mLayoutVipEnter;
    @BindView(R.id.tv_vip_enter)
    TextView mTvVipEnter;
    @BindView(R.id.img_vip_enter_bg)
    ImageView mImgVipEnterBg;

    @BindView(R.id.layout_cp_tongfang)
    RelativeLayout mLayoutCpTongFang;
    @BindView(R.id.img_cp_tongfang)
    ImageView mImgCpTongFang;
    @BindView(R.id.img_cp_left)
    ImageView mImgCpLeft;
    @BindView(R.id.img_cp_right)
    ImageView mImgCpRight;
    @BindView(R.id.tv_cp_left)
    TextView mTvCpLeft;
    @BindView(R.id.tv_cp_right)
    TextView mTvCpRight;

    @BindView(R.id.layout_cp_all_in)
    RelativeLayout mLayoutCpAllIn;
    @BindView(R.id.img_cp_all_in)
    ImageView mImgCpALlIn;
    @BindView(R.id.img_cp_left_all_in)
    CircularImage mImgCpLeftAllIn;
    @BindView(R.id.img_cp_right_all_in)
    CircularImage mImgCpRightAllIn;
    @BindView(R.id.tv_cp_all_in)
    TextView mTvCpAllIn;
    @BindView(R.id.mini_bv_layout)
    MiniBarrageViewLayout mMiniBarrageViewLayout;
    @BindView(R.id.img_room_head_zhu)
    RoundedImageView mImgRoomHead;
    @BindView(R.id.tv_room_notice)
    TextView mTvRoomNotice;

    @BindView(R.id.tv_pk_1)
    TextView mTvPk1;
    @BindView(R.id.tv_pk_2)
    TextView mTvPk2;
    @BindView(R.id.tv_pk_3)
    TextView mTvPk3;
    @BindView(R.id.tv_pk_4)
    TextView mTvPk4;
    @BindView(R.id.tv_pk_5)
    TextView mTvPk5;
    @BindView(R.id.tv_pk_6)
    TextView mTvPk6;
    @BindView(R.id.tv_pk_7)
    TextView mTvPk7;
    @BindView(R.id.tv_pk_last)
    TextView mTvPkLast;
    @BindView(R.id.tv_pk_8)
    TextView mTvPk8;
    @BindView(R.id.tv_cunt)
    TextView mTvPaiMaiCount;
    @BindView(R.id.daojishi_1)
    TextView daoJiShi1;
    @BindView(R.id.daojishi_2)
    TextView daoJiShi2;
    @BindView(R.id.daojishi_3)
    TextView daoJiShi3;
    @BindView(R.id.daojishi_4)
    TextView daoJiShi4;
    @BindView(R.id.daojishi_5)
    TextView daoJiShi5;
    @BindView(R.id.daojishi_6)
    TextView daoJiShi6;
    @BindView(R.id.daojishi_7)
    TextView daoJiShi7;
    @BindView(R.id.daojishi_8)
    TextView daoJiShi8;
    @BindView(R.id.daojishi_last)
    TextView daoJiShiLast;
    @BindView(R.id.tv_paimai_user_count)
    TextView tvPaiMaiUserCount;
    @BindView(R.id.tv_shiyin_number)
    TextView tvShiyinNumber;
    @BindView(R.id.tv_paidan_timer)
    Chronometer tvPaiDanTimer;
    @BindView(R.id.img_diandan)
    ImageView imgDianDan;
    @BindView(R.id.tv_diandan_cunt)
    TextView tvDainDanCunt;
    @BindView(R.id.img_shiyin)
    ImageView imgShiYin;
    @BindView(R.id.tv_shiyin_cunt)
    TextView tvShiYinCunt;
    @BindView(R.id.img_tou_2)
    CircularImage imgTou2;
    @BindView(R.id.tou_4)
    CircularImage tou4;
    @BindView(R.id.tou_5)
    CircularImage tou5;
    @BindView(R.id.civ_family_avatar)
    CircleImageView civFamilyAvatar;
    @BindView(R.id.tv_paidui)
    TextView tvPaidui;
    @BindView(R.id.cv_party)
    CardView cvParty;
//    @BindView(R.id.layout_drawer)
//    DrawerLayout mLayoutDrawer;
//    @BindView(R.id.layout_user_online)
//    LinearLayout mLayoutDrawerMenuRight;
//    @BindView(R.id.layout_main_page)
//    LinearLayout mLayoutDrawerMainPage;


    //    private Renderer mCurrentRenderer;
//    private List<QuanZaiMaiBean.DataBean.MicrophoneBean> mQuanMaiList = new ArrayList<>();
//    private List<Microphone.DataBean.MicrophoneBean> mQuanMaiList = new ArrayList<>();
    private RtcEngine mRtcEngine;
    private RtmClient mRtmClient;
    private RtmChannel mRtmChannel;
    private RtmChannel mRtmChannel_notification;
    private String uid;//房间的uid
    private String room_pass = "";
    private List<Microphone.DataBean.MicrophoneBean> mMicrophone = new ArrayList<>();
    private Map<Integer, CountTimeUtils> mMap = new HashMap<>();
    private EnterRoom enterRoom;
    private int user_type;
    private RoomMessageAdapter roomMessageAdapter;
    List<MessageBean> listMessage = new ArrayList<>();//存消息的集合
    private static final int LEAVE_GO = 0x100;
    public boolean isEditBimai;//是否被管理员闭麦
    public static boolean isStart;//是否启动
    public static boolean isTop;//是否在顶部被启动
    public static AdminHomeActivity mContext;
    private int flag;//跳转来源，1.MainActivity
    private int musicPosition = 0;//当前音乐播放的位置
    private int randomMusic = 0;//是否是随机播放
    private Timer timer;
    private TimerTask timerTask;

    //    private EmojiFragment myFragment1;
    private int selfPosition = 0;//自己麦位的位置

    private int feiLeft;//飞礼物的位置
    private int feiTop;
    private VipBean vipBean = new VipBean();//vip信息
    @Inject
    CommonModel commonModel;

    private List<LocalMusicInfo> listLocal;

    boolean mHasCPAtRoom = false;

    String mStringGongGao = "";//公告，记住当前的公告，当公告修改后，公屏显示一下最新的公告

    GiftFlyDialog mGiftFlyDialog;

//    List<PushBean> mPushBeanList = new Vector<>();

    int mMixingPlayoutVolume = 20;//设置混音音量

    private boolean mIsMeCloseVideo = true;//是否自己闭麦了

    private boolean mIsMeAtVideo = false;//自己是否在麦上

    boolean mIsFreeMic = false;//是否自由麦位

    boolean mIsPaiDan = false;//是否派单厅

    private LoginData mLoginData;

    private GestureDetector mGestureDetector;

    public static boolean mCanReture = false;

    boolean mIsCuntDownPaiDan = false;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1://更新播放进度
                    try {
                        int audioMixingDuration = mRtcEngine.getAudioMixingDuration();
                        int audioMixingCurrentPosition = mRtcEngine.getAudioMixingCurrentPosition();
//                    LogUtils.debugInfo("====当前位置" + audioMixingCurrentPosition * 100 / audioMixingDuration);
                        if (audioMixingDuration != 0) {

                            if (audioMixingCurrentPosition * 100 / audioMixingDuration == 99) {
                                if (randomMusic == 0) {
                                    if (musicPosition == listLocal.size() - 1) {
                                        musicPosition = 0;
                                        seekBar.setProgress(0);
                                        textMusicName.setText(listLocal.get(musicPosition).name);
                                        mRtcEngine.startAudioMixing(listLocal.get(musicPosition).songUrl, false, false, 1);
                                    } else {
                                        musicPosition = musicPosition + 1;
                                        seekBar.setProgress(0);
                                        if (musicPosition < 0 || musicPosition > listLocal.size() - 1) {
                                            musicPosition = 0;
                                        }
                                        textMusicName.setText(listLocal.get(musicPosition).name);
                                        mRtcEngine.startAudioMixing(listLocal.get(musicPosition).songUrl, false, false, 1);
                                    }
                                } else {
                                    seekBar.setProgress(0);
                                    musicPosition = BaseUtils.getRandom(listLocal.size());
                                    textMusicName.setText(listLocal.get(musicPosition).name);
                                    mRtcEngine.startAudioMixing(listLocal.get(musicPosition).songUrl, false, false, 1);
                                }
                            } else {
                                seekBar.setProgress(audioMixingCurrentPosition * 100 / audioMixingDuration);
                            }
                        }else {
                            seekBar.setProgress(0);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        int progress = (int) seekBar.getLeftSeekBar().getProgress();
                        int allDuration = mRtcEngine.getAudioMixingDuration();
                        int currentDuration = allDuration * progress / 100;//拖动的时长
                        LogUtils.debugInfo("====拖动的时长" + currentDuration);
                        mRtcEngine.setAudioMixingPosition(currentDuration);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3://中奖
                    RxUtils.loading(commonModel.winners()).subscribe(new ErrorHandleSubscriber<Winner>(mErrorHandler) {

                        @Override
                        public void onNext(Winner winner) {
                            List<PushBean> list = new ArrayList<>();
                            for (Winner.DataBean datum : winner.getData()) {
                                PushBean pushBean = new PushBean();
                                pushBean.type = "award";
                                PushBean.DataBean dataBean = new PushBean.DataBean();
                                dataBean.setUid(Integer.parseInt(datum.getUser_id()));
                                dataBean.setUser_name("听闻" + datum.getJzname() + "家族的" + datum.getUsername());
                                dataBean.setBoxclass("" + datum.getPrice());
                                dataBean.setGift_name(datum.getLwname() + "x" + datum.getNum());
                                pushBean.setData(dataBean);
                                list.add(pushBean);
                                MessageBean messageBean = new MessageBean();
                                messageBean.setMessageType("13");
                                messageBean.setNickName(datum.getUsername());
                                messageBean.setMessage(datum.getLwname());
                                roomMessageAdapter.getData().add(messageBean);
                                roomMessageAdapter.notifyDataSetChanged();
                                recyclerView.scrollToPosition(roomMessageAdapter.getData().size());
                                mMiniBarrageViewLayout.setData(pushBean);
                            }

                        }
                    });
                    sendEmptyMessageDelayed(3, 30 * 1000);
                    break;
                default:
                    break;
            }

        }
    };

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent.builder()
                .appComponent(appComponent)
                .commonModule(new CommonModule())
                .build()
                .inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_admin_home;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        StatusBarUtil.setTranslucentForImageView(this, 0, viewNeedOffset);

        isStart = true;//代表启动了
        isTop = true;//在顶部
        mContext = this;
        mGestureDetector = new GestureDetector(this, new MyGestureDector());

        mLoginData = UserManager.getUser();
//        Glide.with(this).load(R.drawable.egg).into(ivJindan);
        //设置 paddingTop
        initRoomData();
        //getRoomFamily();
        loadVipData();
        loadVedioList();
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        initLive();//初始化直播
                    }
                });

//        diffuseViewRoom.start();
        imgFei.post(() -> {

            int[] location = new int[2];
            imgFei.getLocationOnScreen(location);
            feiLeft = location[0];
            feiTop = location[1];
            imgFei.setVisibility(View.GONE);
            LogUtils.debugInfo("====飞1：" + location[0] + "====飞1" + location[1]);
        });

        mTvRoomNotice.setSelected(true);//跑马灯必须设置

        loadMusic();

        findViewById(R.id.tv_clear_cp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxUtils.loading(commonModel.delete_cp(UserManager.getUser().getToken()), AdminHomeActivity.this)
                        .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                            @Override
                            public void onNext(BaseBean baseBean) {
                                toast("成功：" + baseBean.getMessage());
                            }
                        });
            }
        });
//        mCountTimeUtils.start();
        handler.sendEmptyMessage(3);

        queryzzz(String.valueOf(UserManager.getUser().getUserId()));
    }

    private void queryzzz(String userId) {
        RxUtils.loading(commonModel.tszq(userId), this)
                .subscribe(new ErrorHandleSubscriber<PersonalityBean1>(mErrorHandler) {
                    @Override
                    public void onNext(PersonalityBean1 baseBean) {
                        PersonalityBean1.DataBean dataBean = baseBean.data;
                        PersonalityBean1.DataBean.ZqinfoBean zqinfo = dataBean.zqinfo;
                        SVGAParser parser = new SVGAParser(mContext);
                        if (zqinfo != null) {
                            showServerSVG(parser, zqinfo.images, svgImage);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
//                        super.onError(t);
                    }
                });
    }

    /**
     * vip数据
     */
    private void loadVipData() {
        //vip徽章
        RxUtils.loading(commonModel.get_user_vip(uid + "", UserManager.getUser().getToken()), this)
                .subscribe(new ErrorHandleSubscriber<VipBean>(mErrorHandler) {
                    @Override
                    public void onNext(VipBean baseBean) {
                        vipBean = baseBean;
                        //刷新自己的公屏
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (vipBean != null && vipBean.getData() != null) {

                                    List<VipBean.DataBean.CpUsersBean> cp_user_list = vipBean.getData().getCp_users();//在房间的cp列表

                                    LoginData loginData = UserManager.getUser();

                                    if (cp_user_list != null && cp_user_list.size() > 0) {//看CP是否在房间里面

                                        for (VipBean.DataBean.CpUsersBean cpUsersBean : cp_user_list) {

                                            MessageBean newMessage = new MessageBean();
                                            newMessage.hz_img = vipBean.getData().getHz_img();
                                            newMessage.vip_tx = vipBean.getData().getVip_tx();
                                            newMessage.vip_img = vipBean.getData().getVip_img();

                                            newMessage.setNickName(loginData.getNickname());
                                            newMessage.setUser_id(loginData.getUserId() + "");
                                            newMessage.nick_color = vipBean.getData().getNick_color();

                                            newMessage.toNickName = cpUsersBean.getNickname();
                                            newMessage.toNick_color = cpUsersBean.getNick_color();
                                            newMessage.toUser_id = cpUsersBean.getId();
                                            newMessage.cp_tx = cpUsersBean.getCp_tx();//CP特效
                                            newMessage.setMessageType("10");
                                            roomMessageAdapter.getData().add(newMessage);

                                            newMessage = new MessageBean();
                                            newMessage.hz_img = vipBean.getData().getHz_img();
                                            newMessage.vip_tx = vipBean.getData().getVip_tx();
                                            newMessage.vip_img = vipBean.getData().getVip_img();

                                            newMessage.setNickName(loginData.getNickname());
                                            newMessage.setUser_id(loginData.getUserId() + "");
                                            newMessage.nick_color = vipBean.getData().getNick_color();

                                            newMessage.toNickName = cpUsersBean.getNickname();
                                            newMessage.toNick_color = cpUsersBean.getNick_color();
                                            newMessage.toUser_id = cpUsersBean.getId();
                                            newMessage.cp_tx = cpUsersBean.getCp_tx();//CP特效
                                            newMessage.setMessageType("8");
                                            roomMessageAdapter.getData().add(newMessage);
                                        }
                                        if (roomMessageAdapter != null) {
                                            roomMessageAdapter.notifyDataSetChanged();
                                            recyclerView.scrollToPosition(roomMessageAdapter.getData().size());
                                        }
                                    } else {
                                        //更新自己的公屏
                                        MessageBean messageBean = new MessageBean();
                                        messageBean.setMessageType("2");
                                        messageBean.setUser_id(String.valueOf(loginData.getUserId()));
                                        messageBean.setNickName(loginData.getNickname());
                                        messageBean.nick_color = vipBean.getData().getNick_color();
                                        listMessage.add(messageBean);
                                        if (roomMessageAdapter != null) {
                                            roomMessageAdapter.setNewData(listMessage);
                                        }
                                    }
                                    initMessage();
                                }
                            }
                        });
                    }
                });
    }

    private void getRoomFamily() {
        RxUtils.loading(commonModel.getRoomFamily(uid), this)
                .subscribe(new ErrorHandleSubscriber<RoomFamily>(mErrorHandler) {
                    @Override
                    public void onNext(RoomFamily roomFamily) {
                        if (TextUtils.isEmpty(roomFamily.getData().getFamily_id())) {

                        } else {
                            loadImage(civFamilyAvatar, roomFamily.getData().getJzimg(), R.mipmap.no_tou);
                            tvPaidui.setText("家族派对（" + roomFamily.getData().getCount() + "）");
                            cvParty.setOnClickListener(v -> {
                                RxUtils.loading(commonModel.isJoinFamily(UserManager.getUser().getUserId() + ""), AdminHomeActivity.this)
                                        .subscribe(new ErrorHandleSubscriber<IsJoinFamily>(mErrorHandler) {
                                            @Override
                                            public void onNext(IsJoinFamily isJoinFamily) {
                                                if (TextUtils.isEmpty(isJoinFamily.getData().getJzid())) {
                                                    ToastUtil.showToast(mContext, "您还没有加入家族");
                                                } else {
                                                    Intent intent = new Intent(mContext, FamilyDetailsActivity.class);
                                                    intent.putExtra("family_id", roomFamily.getData().getFamily_id());
                                                    startActivity(intent);
                                                }
                                            }
                                        });

                            });
                        }

                    }
                });
    }

    /**
     * 初始化房间数据
     */
    private void initRoomData() {
        uid = getIntent().getStringExtra("uid");
        flag = getIntent().getIntExtra("flag", 1);
        room_pass = getIntent().getStringExtra("pwd");
        LogUtils.debugInfo("sgm", "====uid:" + uid);

        RxUtils.loading(commonModel.room_ranking(uid, "1", "1"), this)
                .subscribe(new ErrorHandleSubscriber<RoomRankBean>(mErrorHandler) {
                    @Override
                    public void onNext(RoomRankBean roomRankBean) {
                        System.out.println(roomRankBean);
                        if (TextUtils.isEmpty(roomRankBean.getData().getTop().get(0).getName())) {
                            imgTou2.setImageResource(R.mipmap.no_tou);
                        } else {
                            loadImage(imgTou2, roomRankBean.getData().getTop().get(0).getImg(), R.mipmap.no_tou);
                        }

                        if (TextUtils.isEmpty(roomRankBean.getData().getTop().get(1).getName())) {
                            tou4.setImageResource(R.mipmap.no_tou);
                        } else {
                            loadImage(tou4, roomRankBean.getData().getTop().get(1).getImg(), R.mipmap.no_tou);
                        }

                        if (TextUtils.isEmpty(roomRankBean.getData().getTop().get(2).getName())) {
                            tou5.setImageResource(R.mipmap.no_tou);
                        } else {
                            loadImage(tou5, roomRankBean.getData().getTop().get(2).getImg(), R.mipmap.no_tou);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);

                    }
                });

        enterRoom = (EnterRoom) getIntent().getSerializableExtra("enterRoom");
        if (enterRoom != null) {
            if (enterRoom.getRoom_info().get(0).free_mic == null) {
                enterRoom.getRoom_info().get(0).free_mic = "0";
            }
            mIsFreeMic = enterRoom.getRoom_info().get(0).free_mic.equals("1") ? true : false;
            mIsPaiDan = enterRoom.getRoom_info().get(0).getRoom_type().equals("15") ? true : false;

            if (!mIsPaiDan) {//不是派单厅
                imgDianDan.setVisibility(View.GONE);
                imgShiYin.setVisibility(View.GONE);
                imgShangmai.setVisibility(View.VISIBLE);
                if (!mIsFreeMic) {
                    if (enterRoom.getRoom_info().get(0).sort != 0) {
                        mTvPaiMaiCount.setVisibility(View.VISIBLE);
                        mTvPaiMaiCount.setText(enterRoom.getRoom_info().get(0).sort + "");
                    }
                } else {
                    mTvPaiMaiCount.setVisibility(View.GONE);
                }

            } else {//是

                if (user_type == 1 || user_type == 2) {
                    tvShiyinNumber.setVisibility(View.VISIBLE);
                }

                tvPaiDanTimer.setVisibility(View.VISIBLE);

                //下方按钮
                imgDianDan.setVisibility(View.VISIBLE);

                imgShiYin.setVisibility(View.VISIBLE);

                imgShangmai.setVisibility(View.GONE);

                tvShiyinNumber.setText("点单" + enterRoom.getRoom_info().get(0).num + "人，试音" + enterRoom.getRoom_info().get(0).shiyin_num + "人");

                String paidanTime = enterRoom.getRoom_info().get(0).strto_time;
                if (TextUtils.isEmpty(paidanTime) || TextUtils.equals("0", paidanTime)) {
                    tvPaiDanTimer.setText("派单 00:00");
                    mIsCuntDownPaiDan = false;
//                    long time = Arith.strToLong(paidanTime);
//                    tvPaiDanTimer.setBase(SystemClock.elapsedRealtime()-time);
//                    tvPaiDanTimer.setFormat("派单:%s");
                } else {
                    long time = Arith.strToLong(paidanTime) * 1000;
                    tvPaiDanTimer.setBase(SystemClock.elapsedRealtime() - time);
                    tvPaiDanTimer.setFormat("派单 %s");
                    tvPaiDanTimer.start();
                    mIsCuntDownPaiDan = true;
                }

                if (enterRoom.getRoom_info().get(0).sort != 0) {//有点单序号
                    tvDainDanCunt.setVisibility(View.VISIBLE);
                    tvDainDanCunt.setText(enterRoom.getRoom_info().get(0).sort + "");
                } else if (!enterRoom.getRoom_info().get(0).shiyin_sort.equals("0")) {//有试音序号
                    tvShiYinCunt.setVisibility(View.VISIBLE);
                    tvShiYinCunt.setText(enterRoom.getRoom_info().get(0).shiyin_sort);
                }

            }

            textName.setText(enterRoom.getRoom_info().get(0).getRoom_name());
            textId.setText("ID:" + enterRoom.getRoom_info().get(0).getNumid());
            textType.setText(enterRoom.getRoom_info().get(0).getName());
            textNum.setText(enterRoom.getRoom_info().get(0).hot);
            loadImage(mImgRoomHead, enterRoom.getRoom_info().get(0).getRoom_cover(), R.mipmap.no_tou);
            loadImage(imgBg, enterRoom.getRoom_info().get(0).back_img, R.mipmap.room_bg);
            user_type = enterRoom.getRoom_info().get(0).getUser_type();
            if (enterRoom.getRoom_info().get(0).getIs_mykeep() == 1) {
                imgCollection.setSelected(true);
//                imgCollection.setVisibility(View.GONE);
            } else {
                imgCollection.setSelected(false);
                imgCollection.setVisibility(View.VISIBLE);
            }
//            if (enterRoom.getRoom_info().get(0).getSex() == 2) {
//                mImgRoomHead.setBorderColor(getResources().getColor(R.color.font_FD96AE));
//            } else {
//                mImgRoomHead.setBorderColor(getResources().getColor(R.color.font_89E0FB));
//            }
            LogUtils.debugInfo("sgm", "====userTupe:" + user_type);
//        LogUtils.debugInfo("sgm","====音乐小时");
            //TODO 1.房主 2.管理员 3.禁言 4.评委 5.普通用户
//            if (user_type == 1) {
//                imgShangmai.setVisibility(View.GONE);
//                imgAdd.setVisibility(View.VISIBLE);
//                imgBimai.setVisibility(View.VISIBLE);
//                imgMusic.setVisibility(View.VISIBLE);
//                imgBiaoqing.setVisibility(View.VISIBLE);
//            } else
            if (user_type == 1 || user_type == 2) {
                imgAdd.setVisibility(View.VISIBLE);
                imgBimai.setVisibility(View.GONE);
                imgMusic.setVisibility(View.GONE);
                imgBiaoqing.setVisibility(View.GONE);
            } else {
                imgAdd.setVisibility(View.GONE);
                imgBimai.setVisibility(View.GONE);
                imgMusic.setVisibility(View.GONE);
                imgBiaoqing.setVisibility(View.GONE);
            }

            imgBimai.setSelected(true);

            setRoomPaiHang(enterRoom.getRoom_info().get(0).gap, enterRoom.getRoom_info().get(0).exp, enterRoom.getRoom_info().get(0).hot);

            roomMessageAdapter = new RoomMessageAdapter(this, uid);

            String text = "欢迎来到我的房间~,希望你玩的开心~";
            if (!TextUtils.isEmpty(enterRoom.getRoom_info().get(0).getRoom_intro())) {
                text = enterRoom.getRoom_info().get(0).getRoom_intro();
                mStringGongGao = enterRoom.getRoom_info().get(0).getRoom_intro();
            }

            //初始化聊天区域列表
            RoomHelper.initRecyclerViewAndTitle(this, recyclerView, roomMessageAdapter, enterRoom.getRoom_info().get(0).getRoom_welcome(), text);

            //点击个人发言的名称
            roomMessageAdapter.setOnItemChildClickListener((adapter, view, position) -> {

                MessageBean itemBean = roomMessageAdapter.getData().get(position);

                if (itemBean == null) {
                    return;
                }

                String type = itemBean.getMessageType();

                if (TextUtils.equals("8", type) || TextUtils.equals("9", type) || TextUtils.equals("10", type) || TextUtils.equals("11", type)) {//这个几个都是一个textview设置不同点击事件
                    return;
                } else {
                    if (view.getId() == R.id.textName || view.getId() == R.id.textName2 || view.getId() == R.id.textNameGift1 || view.getId() == R.id.textName2_enter_room || view.getId() == R.id.img_user_heads) {
                        setFirstNameClick(position);
                    } else if (view.getId() == R.id.textNameGift2) {//接受礼物人的名字被点击
                        setSecondNameClick(position);
                    }
                }

            });

            RoomHelper.startKeepLiveService(this);
        } else {
            finish();
        }
    }

    private void setRoomPaiHang(String gap, String exp, String hot) {
        if (gap.equals("0")) {
            mTvRoomNotice.setText("当前" + exp + "金币，" + "是第一名");
        } else {
            mTvRoomNotice.setText("当前" + exp + "金币，" + "距离上一名还差" + gap + "金币");
        }
        if (!TextUtils.isEmpty(hot)) {
            textNum.setText(hot);
        }
    }

    //公屏中最前面的名字被点击（第二个名字为userInfo中的userid）
    public void setFirstNameClick(int position) {
        try {
            if (String.valueOf(UserManager.getUser().getUserId()).
                    equals(roomMessageAdapter.getData().get(position).getUser_id())) {
                setMyDataDialog(roomMessageAdapter.getData().get(position).getUser_id() + "");
            } else {
                if (user_type == 1 || user_type == 2) {
                    if (mMicrophone != null) {
                        String selectId = roomMessageAdapter.getData().get(position).getUser_id();
                        int m = 0;
                        for (int i = 0; i < mMicrophone.size(); i++) {
                            String userIds = mMicrophone.get(i).getUser_id();
                            if (!TextUtils.isEmpty(userIds) && TextUtils.equals(selectId, userIds)) {//是麦上用户
                                setVedioDialog(selectId);
                                break;
                            } else {
                                m++;
                            }
                        }
                        if (m == mMicrophone.size() && m != 0) {
                            setEditOtherDataDialog(selectId);
                        }
                    } else {
                        setEditOtherDataDialog(roomMessageAdapter.getData().get(position).getUser_id());
                    }
                } else {
                    setOtherDataDialog(roomMessageAdapter.getData().get(position).getUser_id());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //公屏中最前面的名字被点击（第二个名字为userInfo中的userid）
    public void setSecondNameClick(int position) {
        try {
            if (String.valueOf(UserManager.getUser().getUserId()).
                    equals(roomMessageAdapter.getData().get(position).userInfo.get(0).userId)) {
                setMyDataDialog(roomMessageAdapter.getData().get(position).userInfo.get(0).userId);
            } else {
                if (user_type == 1 || user_type == 2) {
                    if (mMicrophone != null) {
                        String selectId = roomMessageAdapter.getData().get(position).userInfo.get(0).userId;
                        int m = 0;
                        for (int i = 0; i < mMicrophone.size(); i++) {
                            String userIds = mMicrophone.get(i).getUser_id();
                            if (!TextUtils.isEmpty(userIds) && TextUtils.equals(selectId, userIds)) {//是麦上用户
                                setVedioDialog(selectId);
                                break;
                            } else {
                                m++;
                            }
                        }
                        if (m == mMicrophone.size() && m != 0) {
                            setEditOtherDataDialog(selectId);
                        }
                    } else {
                        setEditOtherDataDialog(roomMessageAdapter.getData().get(position).userInfo.get(0).userId);
                    }
                } else {
                    setOtherDataDialog(roomMessageAdapter.getData().get(position).userInfo.get(0).userId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //公屏中最前面的名字被点击（第二个名字为userInfo中的userid）
    public void setFirstNameClickNew(int position) {
        try {
            if (String.valueOf(UserManager.getUser().getUserId()).
                    equals(roomMessageAdapter.getData().get(position).toUser_id)) {
                setMyDataDialog(roomMessageAdapter.getData().get(position).toUser_id);
            } else {
                if (user_type == 1 || user_type == 2) {
                    String selectId = roomMessageAdapter.getData().get(position).toUser_id;
                    if (mMicrophone != null) {
                        int m = 0;
                        for (int i = 0; i < mMicrophone.size(); i++) {
                            String userIds = mMicrophone.get(i).getUser_id();
                            if (!TextUtils.isEmpty(userIds) && TextUtils.equals(selectId, userIds)) {//是麦上用户
                                setVedioDialog(selectId);
                                break;
                            } else {
                                m++;
                            }
                        }
                        if (m == mMicrophone.size() && m != 0) {
                            setEditOtherDataDialog(selectId);
                        }
                    } else {
                        setEditOtherDataDialog(selectId);
                    }
                } else {
                    setOtherDataDialog(roomMessageAdapter.getData().get(position).toUser_id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //公屏中最前面的名字被点击（第二个名字为toUserId）
    public void setSecondNameClickNew(int position) {
        try {
            if (String.valueOf(UserManager.getUser().getUserId()).
                    equals(roomMessageAdapter.getData().get(position).toUser_id)) {
                setMyDataDialog(roomMessageAdapter.getData().get(position).toUser_id);
            } else {
                if (user_type == 1 || user_type == 2) {
                    String selectId = roomMessageAdapter.getData().get(position).toUser_id;
                    if (mMicrophone != null) {
                        int m = 0;
                        for (int i = 0; i < mMicrophone.size(); i++) {
                            String userIds = mMicrophone.get(i).getUser_id();
                            if (!TextUtils.isEmpty(userIds) && TextUtils.equals(selectId, userIds)) {//是麦上用户
                                setVedioDialog(selectId);
                                break;
                            } else {
                                m++;
                            }
                        }
                        if (m == mMicrophone.size() && m != 0) {
                            setEditOtherDataDialog(selectId);
                        }
                    } else {
                        setEditOtherDataDialog(selectId);
                    }
                } else {
                    setOtherDataDialog(roomMessageAdapter.getData().get(position).toUser_id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 1.初始化直播间音频
     */
    private void initLive() {
        try {
            String myId = UserManager.getUser().getUserId() + "";
            mRtcEngine = RtcEngine.create(this, Api.AGORA_KEY,
//            mRtcEngine = RtcEngine.create(this, getString(R.string.agora_app_id_test),
                    new IRtcEngineEventHandler() {
                        @Override
                        public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
                            super.onJoinChannelSuccess(channel, uid, elapsed);
                            LogUtils.debugInfo("sgm", "====加入音频直播成功！");
                        }

                        @Override
                        public void onError(int err) {
                            super.onError(err);
                            LogUtils.debugInfo("sgm", "====加入失败！" + err);
                        }

                        @Override
                        public void onLeaveChannel(RtcStats stats) {
                            super.onLeaveChannel(stats);
                            LogUtils.debugInfo("sgm", "====离开！");
                        }


                        @Override
                        public void onConnectionLost() {
                            super.onConnectionLost();
                            LogUtils.debugInfo("sgm", "====网络链接丢失");
                        }

                        @Override
                        public void onAudioMixingStateChanged(int state, int errorCode) {//播放状态改版
                            super.onAudioMixingStateChanged(state, errorCode);
                            LogUtils.debugInfo("====状态" + state + " errorCode===" + errorCode);
                            switch (state) {
                                case 710://正常
                                    runOnUiThread(() -> {

                                        if (timer != null && timerTask != null) {
                                            timer.cancel();
                                            timerTask.cancel();
                                        }
                                        if (handler != null) {
                                            handler.removeCallbacksAndMessages(null);
                                        }

                                        timer = new Timer();
                                        timerTask = new TimerTask() {
                                            @Override
                                            public void run() {
                                                handler.sendEmptyMessage(1);
                                            }
                                        };
                                        timer.schedule(timerTask, 100, 200);
                                    });

                                    break;
                                case 711://暂停
                                    runOnUiThread(() -> {
                                        if (timer != null && timerTask != null) {
                                            timer.cancel();
                                            timerTask.cancel();
                                        }
                                    });

                                    break;
                                case 713://停止
                                    runOnUiThread(() -> {
                                        try {
                                            if (listLocal != null && listLocal.size() > 0) {
//                                                if (randomMusic == 0) {//顺序播放
////                                                    if (musicPosition == listLocal.size() - 1) {
//////                                                        toast("已经是最后一个了！");
////                                                    } else {
////                                                        musicPosition = musicPosition + 1;
////                                                        seekBar.setProgress(0);
////                                                        textMusicName.setText(listLocal.get(musicPosition).name);
////                                                        mRtcEngine.startAudioMixing(listLocal.get(musicPosition).songUrl, false, false, 1);
////                                                    }
////                                                } else {//随机播放
////                                                    seekBar.setProgress(0);
////                                                    musicPosition = BaseUtils.getRandom(listLocal.size());
////                                                    textMusicName.setText(listLocal.get(musicPosition).name);
////                                                    mRtcEngine.startAudioMixing(listLocal.get(musicPosition).songUrl, false, false, 1);
////                                                }
                                            }
//                                            seekBar.setProgress(0);
//                                            mRtcEngine.stopAudioMixing();
//                                            imgStop.setSelected(false);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    });
                                    break;
                            }
                        }

                        @Override
                        public void onAudioVolumeIndication(AudioVolumeInfo[] speakers, int totalVolume) {
                            super.onAudioVolumeIndication(speakers, totalVolume);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //谁在说话监听
                                    if (speakers.length > 0) {

                                        List<AudioVolumeInfo> listShuohua = new ArrayList<>();
                                        for (AudioVolumeInfo list : speakers) {
                                            if (list.uid != 0) {
                                                listShuohua.add(list);
                                            }
                                        }

                                        int size = mMicrophone.size();
                                        for (AudioVolumeInfo audioVolumeInfo : listShuohua) {
                                            for (int i = 0; i < size; i++) {

                                                if (String.valueOf(audioVolumeInfo.uid).equals(mMicrophone.get(i).getUser_id())) {
                                                    int colors = 0;
                                                    String color = mMicrophone.get(i).getMic_color();
                                                    if (!TextUtils.isEmpty(color)) {
                                                        colors = Color.parseColor(color);
                                                    }
                                                    showQuan(i, audioVolumeInfo.volume, colors);
//                                                    Log.e("shangmian_color===", color);
                                                }
//                                                else if (String.valueOf(audioVolumeInfo.uid).equals(uid)) {
//                                                    int colorOne = 0;
//                                                    String colorStr = enterRoom.getRoom_info().get(0).mic_color;
//                                                    if (!TextUtils.isEmpty(colorStr)) {
//                                                        colorOne = Color.parseColor(colorStr);
//                                                    }
//                                                    showQuan(8, audioVolumeInfo.volume, colorOne);
//                                                }
                                            }
                                        }

//                                        Log.e("====说话人数量：",speakers.length+"");
                                        //自己说话的处理
                                        for (AudioVolumeInfo list : speakers) {
//                                            Log.e("====说话人：" + list.uid, "====音量" + list.volume);
                                            if (list.uid == 0 && list.volume > 20) {//判断自己是否说话显示光圈
//                                                if (user_type == 1) {
//                                                    int colors = 0;
//                                                    if (enterRoom != null && enterRoom.getRoom_info() != null && enterRoom.getRoom_info().size() > 0) {
//                                                        String color = enterRoom.getRoom_info().get(0).mic_color;
//                                                        if (!TextUtils.isEmpty(color)) {
//                                                            colors = Color.parseColor(color);
//                                                        }
//                                                    }
//                                                    showQuan(8, list.volume, colors);
//                                                } else {
                                                for (Microphone.DataBean.MicrophoneBean listPhone : mMicrophone) {
                                                    if (TextUtils.equals(listPhone.getUser_id(),
                                                            myId)) {
                                                        int i = mMicrophone.indexOf(listPhone);

//                                                            LogUtils.debugInfo("====i：" + i);
                                                        int colors = 0;
//                                                            if (enterRoom != null && enterRoom.getRoom_info() != null && enterRoom.getRoom_info().size() > 0) {
                                                        String color = mMicrophone.get(i).getMic_color();
                                                        if (!TextUtils.isEmpty(color)) {
                                                            colors = Color.parseColor(color);
                                                        }
//                                                            }
                                                        showQuan(i, list.volume, colors);
                                                        break;
                                                    }
                                                }

//                                                }
                                            }
                                        }
                                    }
                                }
                            });

                        }


                    });
        } catch (Exception e) {
            throw new RuntimeException("NEED TO check rtc sdk init fatal error\n" + Log.getStackTraceString(e));
        }
        ////////////////////////////参考https://docs.agora.io/cn/Audio%20Broadcast/audio_profile_android?platform=Android/////////////////////////////////////////////////
        mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING);
        mRtcEngine.setAudioProfile(Constants.AUDIO_PROFILE_MUSIC_STANDARD, Constants.AUDIO_SCENARIO_SHOWROOM);
//        mRtcEngine.setAudioProfile(4, 3);
//        if (user_type == 1) {
//            mRtcEngine.setClientRole(Constants.CLIENT_ROLE_BROADCASTER);//1是主播，
//            if (enterRoom.getRoom_info().get(0).getIs_sound() == 1) {
//                mRtcEngine.enableLocalAudio(true);
//            } else {
//                mRtcEngine.enableLocalAudio(false);
//            }
//        } else {
        mRtcEngine.setClientRole(Constants.CLIENT_ROLE_AUDIENCE);//2是观众
//        }
//        mRtcEngine.setDefaultAudioRoutetoSpeakerphone(false);//直播模式下默认启用扬声器播放
//        mRtcEngine.setAudioProfile(5,1);
//        mRtcEngine.adjustPlaybackSignalVolume(400);
        mRtcEngine.joinChannel("",
                uid,
                "OpenVCall", UserManager.getUser().getUserId());
        mRtcEngine.enableAudioVolumeIndication(1000, 3);//监听远端说话
        mRtcEngine.adjustPlaybackSignalVolume(180);// 设置人声的播放信号音量,调节音量的参数值范围是 0 - 400，默认值 100 表示原始音量，即不对信号做缩放，400 表示原始音量的 4 倍
        mRtcEngine.adjustRecordingSignalVolume(100);// 设置录音信号音量,调节音量的参数值范围是 0 - 400，默认值 100 表示原始音量，即不对信号做缩放，400 表示原始音量的 4 倍
        mRtcEngine.adjustAudioMixingPlayoutVolume(mMixingPlayoutVolume);// 设置混音音量，设置本地用户听到的音乐文件音量
        mRtcEngine.adjustAudioMixingPublishVolume(mMixingPlayoutVolume);// 设置混音音量，设置远端用户听到的音乐文件音量，混音是指播放本地或者在线音乐文件，同时让频道内的其他人听到此音乐，调节混音音量的参数值范围是 0 - 100，默认值 100 表示原始文件音量，即不对信号做缩放
//        mRtcEngine.adjustAudioMixingVolume(10);//你也可以直接调用 adjustAudioMixingVolume，同时设置本地及远端用户听到的音乐文件音量
        // 获取全局的音效管理类
//        IAudioEffectManager manager = mRtcEngine.getAudioEffectManager();
// 设置音效音量为原始音量的 50%
//        double volume = 50.0;
// 设置所有音效的播放音量
//        manager.setEffectsVolume(volume);
// 设置单个音效的播放音量
// soundId 是你在调用 playEffect 时设置的音效 ID
//        manager.setVolumeOfEffect(soundId, volume);
    }


    /**
     * 显示麦位说话的光圈
     */
    private void showQuan(int position, int volume, int color) {
        if (mWaveView1 == null || mWaveView2 == null || mWaveView3 == null || mWaveView4 == null || mWaveView5 == null
                || mWaveView6 == null || mWaveView7 == null || mWaveView8 == null || mWaveViewLast == null) {
            return;
        }
//        Log.e("开始显示光圈===", volume + "");
        if (color == 0) {
            color = getResources().getColor(R.color.translant);
        }
        float radius = (float) Math.log10(Math.max(1, volume)) * QMUIDisplayHelper.dpToPx(25);
        LogUtils.debugInfo("voiceDb==" + volume + "radius ==" + radius + "半径等于=" + QMUIDisplayHelper.dpToPx(25));
        switch (position) {
            case 0:
//                loadQuan(imgQuan1);
//                mVoice1 = volume;
                mWaveView1.setColor(color);
                mWaveView1.addCircle(radius);
                break;
            case 1:
                mWaveView2.setColor(color);
                mWaveView2.addCircle(radius);
//                mVoice2 = volume;
//                    mWaveView2.setRippleColor(color);
//                    mWaveView2.startRecording();
                break;
            case 2:
                mWaveView3.setColor(color);
                mWaveView3.addCircle(radius);
//                mVoice3 = volume;
//                    mWaveView3.setRippleColor(color);
//                    mWaveView3.startRecording();
                break;
            case 3:
                mWaveView4.setColor(color);
                mWaveView4.addCircle(radius);
                break;
            case 4:
                mWaveView5.setColor(color);
                mWaveView5.addCircle(radius);
                break;
            case 5:
                mWaveView6.setColor(color);
                mWaveView6.addCircle(radius);
                break;
            case 6:
//                radius = (float) Math.log10(Math.max(1, volume)) * QMUIDisplayHelper.dpToPx(40);
                mWaveView7.setColor(color);
                mWaveView7.addCircle(radius);
                break;
            case 7:
                mWaveViewLast.setColor(color);
                mWaveViewLast.addCircle(radius);
                break;
            case 8:
                mWaveView8.setColor(color);
                mWaveView8.addCircle(radius);
                break;
        }
    }

    /**
     * 2.初始化消息
     */
    private void initMessage() {
        //1.实例化
        try {
            mRtmClient = RtmClient.createInstance(this, Api.AGORA_KEY,
                    new RtmClientListener() {
                        @Override
                        public void onConnectionStateChanged(int state, int reason) {
                            Log.d(TAG, "on connection state changed to "
                                    + state + " reason: " + reason);
                        }

                        @Override
                        public void onMessageReceived(RtmMessage rtmMessage, String peerId) {
                            //创建接收消息监听
                            String msg = rtmMessage.getText();
                            Log.d(TAG, "Receives message: " + msg
                                    + " from " + peerId);

//                            MessageBean messageBean = null;
//                            if(!TextUtils.isEmpty(msg)){
//                                try{
//                                    messageBean = BaseUtils.getMessageBean(msg);
//                                }catch (Exception e){
//                                    e.printStackTrace();
//                                }
//                            }
                            if (msg.equals(Constant.nfgk184grdgdfggunaliyuanxiamai)) {//被管理员下麦
                                runOnUiThread(() -> {
                                    mIsMeAtVideo = false;
                                    imgBimai.setSelected(true);
                                    loadVedioList();
                                    mRtcEngine.stopAudioMixing();
                                    seekBar.setProgress(0);
                                    imgStop.setSelected(false);
                                    forcedDownVedio();
                                    llMusic.setVisibility(View.GONE);
                                });
                            } else if (msg.equals(Constant.nfgk184grdgdfggunaliyuanbimai)) {//被管理员闭麦
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        loadVedioList();
                                        mRtcEngine.stopAudioMixing();
                                        seekBar.setProgress(0);
                                        imgStop.setSelected(false);
                                        mRtcEngine.enableLocalAudio(false);
                                        imgBimai.setSelected(true);
                                        isEditBimai = true;
                                    }
                                });

                            } else if (msg.equals(Constant.nfgk184grdgdfggunaliyuantichu)) {//被踢出房间
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        toast("您已经被踢出房间!");
                                        mRtcEngine.stopAudioMixing();
                                        isStart = false;
                                        finish();
                                    }
                                });
                            } else if (msg.equals(Constant.nfgk184grdgdfggdfghfhrthmkBeiJinYan)) {//被禁言
                                runOnUiThread(() -> {
                                    toast("您已经被禁言3分钟!");
                                    mRtcEngine.stopAudioMixing();
                                    seekBar.setProgress(0);
                                    imgStop.setSelected(false);
                                });

                            } else if (msg.equals(Constant.nfgk184grdgdfggdfghfhrthmkkaimai)) {//解禁麦
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        isEditBimai = false;
                                        if (!mIsMeCloseVideo) {
                                            imgBimai.setSelected(false);
                                            mRtcEngine.enableLocalAudio(true);
                                        }
                                    }
                                });

                            } else if (msg.equals(Constant.nfgk184grdgdfggdfghfhrthmkShezhiguanliyuan)) {//设置管理
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        imgAdd.setVisibility(View.VISIBLE);
//                                        imgMusic.setVisibility(View.VISIBLE);
                                        user_type = 2;
                                    }
                                });

                            } else if (msg.equals(Constant.nfgk184grdgdfggdfghfhrthmkQuxiaoiguanliyuan)) {//取消管理
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        imgAdd.setVisibility(View.GONE);
                                        imgMusic.setVisibility(View.GONE);
                                        user_type = 5;
                                    }
                                });

                            } else if (msg.equals(Constant.nfgk184grdgdfggunalibaorenshangmai)) {//收到抱自己上麦消息
                                LogUtils.debugInfo("收到抱自己上麦消息");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (mIsPaiDan) {
                                            imgDianDan.setSelected(true);
                                            imgDianDan.setVisibility(View.VISIBLE);
                                            imgShiYin.setSelected(false);
                                            imgShiYin.setVisibility(View.GONE);
                                            imgShangmai.setVisibility(View.GONE);
                                        } else {
                                            imgShangmai.setSelected(true);
                                            imgShangmai.setVisibility(View.VISIBLE);
                                        }
                                        imgMusic.setVisibility(View.VISIBLE);
                                        imgBiaoqing.setVisibility(View.VISIBLE);

                                        imgBimai.setVisibility(View.VISIBLE);
                                        imgBimai.setSelected(true);
                                        mIsMeCloseVideo = true;
                                        mTvPaiMaiCount.setVisibility(View.GONE);
//                                        mRtcEngine.setClientRole(Constants.CLIENT_ROLE_BROADCASTER);
//                                        mRtcEngine.enableLocalAudio(false);
                                    }
                                });

                            } else {//结为CP消息,第一次发送，不是第一次的话会走频道消息
//                                Log.e("点对点消息=====", msg);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        RoomHelper.withCPMsg(AdminHomeActivity.this, msg, recyclerView, roomMessageAdapter, commonModel, mErrorHandler,
                                                mRtmClient, mRtmChannel, vipBean.getData().getNick_color());

                                    }
                                });
                            }
                        }

                        @Override
                        public void onTokenExpired() {

                        }
                    });
        } catch (Exception e) {
            throw new RuntimeException("You need to check the RTM init process.");
        }
        //2登录
        mRtmClient.login(null, String.valueOf(UserManager.getUser().getUserId()), new ResultCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                LogUtils.debugInfo("====登录消息成功");
                joinChanalMessage();
            }

            @Override
            public void onFailure(ErrorInfo errorInfo) {
                LogUtils.debugInfo("====登录消息失败");
            }
        });

    }

    /**
     * 根据礼物地址播放后台礼物
     *
     * @param parser
     * @param giftUrl
     * @param svgaImageView
     */
    int times;

    public void showServerSVG(SVGAParser parser, String giftUrl, SVGAImageView svgaImageView) {
        try {
            parser.decodeFromURL(new URL(giftUrl), new SVGAParser.ParseCompletion() {
                @Override
                public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
                    if (svgaImageView != null){
                        svgaImageView.setVideoItem(svgaVideoEntity);
                        svgaImageView.setLoops(1);
                        svgaImageView.stepToFrame(1, true);
                        setSvgImgClickble();
                    }
                }

                @Override
                public void onError() {
//                    if (times == 0) {
//                        times+=1;
//                        showServerSVG(parser,giftUrl, svgaImageView);
//                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 3.创建消息频道
     */
    private void joinChanalMessage() {
        //3创建频道消息，
        try {
            mRtmChannel = mRtmClient.createChannel(uid, new RtmChannelListener() {
                @Override
                public void onMessageReceived(RtmMessage message, RtmChannelMember fromMember) {
                    //TODO  1：正常的 聊天消息  2：进入房间  3： 刷新麦序列表
                    // 4 ：礼物消息  5 ：表型消息 6：清空消息 7房间设置
//                    RxUtils.loading(commonModel.remove_mykeep(uid,
//                            String.valueOf(UserManager.getUser().getUserId())), this)
//                            .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
//                                @Override
//                                public void onNext(BaseBean baseBean) {
//                                    toast("取消收藏");
//                                    imgCollection.setSelected(false);
//                                }
//                            });
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String account = fromMember.getUserId();
                            String msg = message.getText();
                            LogUtils.debugInfo("====接收的id" + account + "接收的消息： = " + msg);
                            MessageBean messageBean = BaseUtils.getMessageBean(msg);
                            if (TextUtils.equals(messageBean.getMessageType(), "3")) {//麦序的操作
                                loadVedioList();
                            } else if (TextUtils.equals(messageBean.getMessageType(), "2")) {//进入房间
                                if (mHasCPAtRoom) {//有CP在房间，就过滤掉自己进入房间的消息
                                    return;
                                }
                                // TODO: 2020/7/14  
                                if (!TextUtils.isEmpty(messageBean.vip_tx)) {//有VIP特效
                                    playVIPTX(messageBean.vip_tx, messageBean.getNickName());
                                }
                                queryzzz(messageBean.getUser_id());
                                RoomHelper.refreshAdapter(messageBean, recyclerView, roomMessageAdapter);
//                                if (uid.equals(account)) {//房主进来
//                                    textLayout.setVisibility(View.GONE);
//                                }
                            } else if (TextUtils.equals(messageBean.getMessageType(), "1")) {//正常消息
                                roomMessageAdapter.getData().add(messageBean);
                                roomMessageAdapter.notifyDataSetChanged();
//                                recyclerView.post(new Runnable() {
//                                    @Override
//                                    public void run() {
                                recyclerView.scrollToPosition(roomMessageAdapter.getData().size());//滚动测试
//                                    }
//                                });
                            } else if (TextUtils.equals(messageBean.getMessageType(), "6")) {//管理员清空消息
                                roomMessageAdapter.getData().clear();
                                roomMessageAdapter.notifyDataSetChanged();
                            } else if (TextUtils.equals(messageBean.getMessageType(), "7")) {//房间修改设置
                                loadEnterRoom();

                                String gongGao = messageBean.getRoom_intro();

                                if (!TextUtils.equals(mStringGongGao, gongGao)) {//公告变了
                                    LogUtils.debugInfo("公告变了");
                                    mStringGongGao = gongGao;
                                    RoomHelper.refreshAdapter(messageBean, recyclerView, roomMessageAdapter);
                                } else {
                                    LogUtils.debugInfo("公告没变");
                                }

                            } else if (TextUtils.equals(messageBean.getMessageType(), "5")) {//表情消息
                                String emoji = messageBean.getEmoji();
                                int maiPosition = 0;//麦序位置
                                for (Microphone.DataBean.MicrophoneBean list : mMicrophone) {
                                    if (TextUtils.equals(list.getUser_id(), account)) {
                                        maiPosition = mMicrophone.indexOf(list);
                                    }
                                }
                                maiPosition = maiPosition + 1;
                                switch (maiPosition) {
                                    case 1:
                                        RoomHelper.loadGifShow(AdminHomeActivity.this, imgGif1, emoji);
                                        break;
                                    case 2:
                                        RoomHelper.loadGifShow(AdminHomeActivity.this, imgGif2, emoji);
                                        break;
                                    case 3:
                                        RoomHelper.loadGifShow(AdminHomeActivity.this, imgGif3, emoji);
                                        break;
                                    case 4:
                                        RoomHelper.loadGifShow(AdminHomeActivity.this, imgGif4, emoji);
                                        break;
                                    case 5:
                                        RoomHelper.loadGifShow(AdminHomeActivity.this, imgGif5, emoji);
                                        break;
                                    case 6:
                                        RoomHelper.loadGifShow(AdminHomeActivity.this, imgGif6, emoji);
                                        break;
                                    case 7:
                                        RoomHelper.loadGifShow(AdminHomeActivity.this, imgGif7, emoji);
                                        break;
                                    case 8:
                                        RoomHelper.loadGifShow(AdminHomeActivity.this, imgGiftLast, emoji);
                                        break;
                                    case 9:
                                        RoomHelper.loadGifShow(AdminHomeActivity.this, imgGif8, emoji);
                                        break;
                                }
                                if (!TextUtils.equals(messageBean.getIs_answer(), "0")) {//代表表情有结果
                                    RoomHelper.refreshAdapter(messageBean, recyclerView, roomMessageAdapter);
                                }
                            } else if (TextUtils.equals(messageBean.getMessageType(), "4")) {//礼物消息
                                List<MessageBean.Data> userInfo = messageBean.userInfo;
                                if (userInfo.size() == 1) {
                                    RoomHelper.refreshAdapter(messageBean, recyclerView, roomMessageAdapter);
                                } else {
                                    for (MessageBean.Data list : userInfo) {
                                        MessageBean newMessage = new MessageBean();
                                        newMessage.setUser_id(messageBean.getUser_id());
                                        newMessage.setNickName(messageBean.getNickName());
                                        newMessage.nick_color = messageBean.nick_color;
                                        newMessage.show_img = messageBean.show_img;
                                        newMessage.show_gif_img = messageBean.show_gif_img;
                                        newMessage.type = messageBean.type;
                                        newMessage.giftNum = messageBean.giftNum;
                                        newMessage.e_name = messageBean.e_name;
                                        newMessage.setMessageType("4");
                                        List<MessageBean.Data> dataList = new ArrayList<>();
                                        dataList.add(list);
                                        newMessage.userInfo = dataList;
                                        roomMessageAdapter.getData().add(newMessage);
                                    }
                                    roomMessageAdapter.notifyDataSetChanged();
                                    recyclerView.scrollToPosition(roomMessageAdapter.getData().size());
                                    if (userInfo.size() >= 8) {//显示全服通知
                                        PushBean pushBean = new PushBean();
                                        pushBean.setType("quanmai");
                                        PushBean.DataBean dataBean = new PushBean.DataBean();
                                        dataBean.setBoxclass(enterRoom.getRoom_info().get(0).getRoom_name());
                                        dataBean.setGift_name(messageBean.name);
                                        dataBean.setUser_name(messageBean.getNickName());
                                        dataBean.setNum(Arith.strToInt(messageBean.giftNum));
                                        pushBean.setData(dataBean);
                                        mMiniBarrageViewLayout.setData(pushBean);
                                    }
                                }
                                //播放动画特效
                                if (TextUtils.equals(messageBean.type, "2")) {//全屏
                                    try {
                                        SVGAParser parser = new SVGAParser(AdminHomeActivity.this);
                                        showServerSVG(parser, messageBean.show_gif_img, svgImage);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    //广播关闭礼物弹窗
                                    MessageEvent messageEvent = new MessageEvent();
                                    messageEvent.setStateMessage(StateMessage.CLOSE_GIFT_WINDOW);
                                    EventBus.getDefault().post(messageEvent);
                                }
//                                else if (TextUtils.equals(messageBean.type, "1")) {//头像动画
//                                    loadAniData(messageBean.userInfo, messageBean.show_img);
//                                }
                                loadAniData(messageBean.userInfo, messageBean.show_img);
                            } else if (TextUtils.equals(messageBean.getMessageType(), "11")) {//同意结为CP，在聊天区域提示XXX与XX结为守护CP；
                                RoomHelper.refreshAdapter(messageBean, recyclerView, roomMessageAdapter);

                            } else if (TextUtils.equals(messageBean.getMessageType(), "8")) {//CP同时在房间
                                RoomHelper.refreshAdapter(messageBean, recyclerView, roomMessageAdapter);
//                                if (uid.equals(account)) {//房主进来
//                                    textLayout.setVisibility(View.GONE);
//                                }
                                //播放同房特效
                                if (!TextUtils.isEmpty(messageBean.cp_tx)) {
                                    playCpTongFangTX(messageBean.cp_tx, messageBean.getNickName(), messageBean.headimgurl, messageBean.toNickName, messageBean.toheadimgurl);
                                }
                                if (!TextUtils.isEmpty(messageBean.vip_tx)) {//有VIP特效
                                    playVIPTX(messageBean.vip_tx, messageBean.getNickName());
                                }

                            } else if (TextUtils.equals(messageBean.getMessageType(), "12")) {//联手上麦
                                RoomHelper.refreshAdapter(messageBean, recyclerView, roomMessageAdapter);
                                //播放上麦特效
                                if (!TextUtils.isEmpty(messageBean.cp_xssm)) {
                                    playCpTX(messageBean.cp_xssm, messageBean.getNickName(), messageBean.headimgurl, messageBean.toNickName, messageBean.toheadimgurl);
                                }
                            } else if (TextUtils.equals(messageBean.getMessageType(), "13")) {//开宝箱
                                System.out.println("---------------------开宝箱");
                                if (!TextUtils.isEmpty(messageBean.getMessage()) || (messageBean.awardList != null && messageBean.awardList.size() > 0)) {
                                    RoomHelper.refreshAdapter(messageBean, recyclerView, roomMessageAdapter);
                                }

                            } else if (TextUtils.equals(messageBean.getMessageType(), "14")) {//数值玩法开启关闭
                                loadVedioList();
                                RoomHelper.refreshAdapter(messageBean, recyclerView, roomMessageAdapter);
                            } else if (TextUtils.equals(messageBean.getMessageType(), "15")) {//刷新房间排行
                                String gap = messageBean.getMessage();
                                String hot = messageBean.hot;
                                String exp = messageBean.exp;
                                setRoomPaiHang(gap, exp, hot);
                            } else if (TextUtils.equals(messageBean.getMessageType(), "17")) {//排麦列表发生变化
                                getMePaiMaiPosition();
                            } else if (TextUtils.equals(messageBean.getMessageType(), "18")) {//开关自由麦位
                                RoomHelper.refreshAdapter(messageBean, recyclerView, roomMessageAdapter);
                            } else if (TextUtils.equals(messageBean.getMessageType(), "19")) {//开始停止派单
                                String msgs = messageBean.getMessage();
                                if ("1".equals(msgs)) {
                                    tvPaiDanTimer.setBase(SystemClock.elapsedRealtime());
                                    tvPaiDanTimer.setFormat("派单 %s");
                                    tvPaiDanTimer.start();
                                    mIsCuntDownPaiDan = true;
                                } else {
                                    tvPaiDanTimer.stop();
                                    tvPaiDanTimer.setBase(SystemClock.elapsedRealtime());
                                    tvPaiDanTimer.setText("派单 00:00");
                                    mIsCuntDownPaiDan = false;
                                }
                            }
                        }
                    });

                }

                @Override
                public void onMemberJoined(RtmChannelMember rtmChannelMember) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LogUtils.debugInfo("====成员加入消息");
                        }
                    });

                }

                @Override
                public void onMemberLeft(RtmChannelMember rtmChannelMember) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String userId = rtmChannelMember.getUserId();
                            //只要有成员离开了，就需要判断是否其他人刷新麦序列表
                            try {
                                for (Microphone.DataBean.MicrophoneBean list : mMicrophone) {
                                    if (list != null) {
                                        if (TextUtils.equals(list.getUser_id(), userId)) {
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    loadVedioList();
                                                }
                                            }, 1000);
                                            break;
                                        }
                                    }
                                }
                            } catch (Exception e) {

                            }
                            LogUtils.debugInfo("====成员离开消息");
                        }
                    });
                }
            });
        } catch (RuntimeException e) {
            LogUtils.debugInfo("====创建消息频道失败");
        }
        //4加入
        mRtmChannel.join(new ResultCallback<Void>() {
            @Override
            public void onSuccess(Void responseInfo) {
                LogUtils.debugInfo("====加入频道消息成功");
                //进入房间的通知
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (vipBean != null && vipBean.getData() != null) {

                            List<VipBean.DataBean.CpUsersBean> cp_user_list = vipBean.getData().getCp_users();//在房间的cp列表
                            LoginData loginData = UserManager.getUser();
                            if (!TextUtils.isEmpty(vipBean.getData().getVip_tx())) {//vip进场特效
                                playVIPTX(vipBean.getData().getVip_tx(), loginData.getNickname());
                            }

                            if (cp_user_list != null && cp_user_list.size() > 0) {//看CP是否在房间里面

                                for (VipBean.DataBean.CpUsersBean cpUsersBean : cp_user_list) {
//                                                            mHasCPAtRoom = true;
                                    //先刷新自己的公屏
                                    MessageBean newMessage = new MessageBean();
                                    newMessage.hz_img = vipBean.getData().getHz_img();
                                    newMessage.vip_tx = vipBean.getData().getVip_tx();
                                    newMessage.vip_img = vipBean.getData().getVip_img();

                                    newMessage.setNickName(loginData.getNickname());
                                    newMessage.setUser_id(loginData.getUserId() + "");
                                    newMessage.nick_color = vipBean.getData().getNick_color();
                                    newMessage.headimgurl = loginData.getHeadimgurl();
//                                                            MessageBean.Data toUser = new MessageBean.Data();
                                    newMessage.toNickName = cpUsersBean.getNickname();
                                    newMessage.toNick_color = cpUsersBean.getNick_color();
                                    newMessage.toheadimgurl = cpUsersBean.getHeadimgurl();
                                    newMessage.toUser_id = cpUsersBean.getId();
                                    newMessage.cp_tx = cpUsersBean.getCp_tx();//CP特效

                                    if (!TextUtils.isEmpty(cpUsersBean.getCp_tx())) {//播放CP同房特效
                                        playCpTongFangTX(cpUsersBean.getCp_tx(), loginData.getNickname(), loginData.getHeadimgurl(), newMessage.toNickName, newMessage.toheadimgurl);
                                    }
//                                    newMessage.setMessageType("10");

//                                    roomMessageAdapter.getData().add(newMessage);

                                    //发送给CP同
                                    newMessage.setMessageType("8");
                                    String jsonStr = JSON.toJSONString(newMessage);
//                                    Log.e("进入房间通知CP", jsonStr);
                                    sendPeerMessage(cpUsersBean.getId(), jsonStr);
                                    //发送频道消息8（播放同房特效,公屏显示守护CP %@ 和 %@ 同在房间）
                                    sendChannelMessage(jsonStr);
                                }
                            } else {
                                //更新自己的公屏
                                sendEnterRoom();
                            }
                        } else {
                            sendChannelMessage(BaseUtils.getJson("2", "进入直播间",
                                    UserManager.getUser().getNickname(),
                                    String.valueOf(UserManager.getUser().getUserId()), "", ""));
                        }
                    }
                });


            }

            @Override
            public void onFailure(ErrorInfo errorInfo) {
                LogUtils.debugInfo("====加入频道消息失败");
            }

        });

        mRtmChannel_notification = mRtmClient.createChannel("123", new RtmChannelListener() {
            @Override
            public void onMessageReceived(RtmMessage message, RtmChannelMember fromMember) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String account = fromMember.getUserId();
                        String msg = message.getText();
                        LogUtils.debugInfo("第二个频道====接收的id" + account + "接收的消息： = " + msg);
                        try {
                            JSONObject jsonObject = new JSONObject(msg);
                            if (jsonObject != null) {
                                if (jsonObject.has("messageType")) {

                                    if ("20".equals(jsonObject.getString("messageType"))) {//礼物全服
                                        JSONArray userArray = jsonObject.getJSONArray("userInfo");
                                        if (userArray != null) {
                                            List<PushBean.DataBean> pushList = JSON.parseArray(userArray.toString(), PushBean.DataBean.class);
                                            if (pushList != null && pushList.size() > 0) {//有大礼物播报
                                                for (int i = 0; i < pushList.size(); i++) {
                                                    PushBean pushBean = new PushBean();
                                                    pushBean.setType("gift");
                                                    PushBean.DataBean dataBean = pushList.get(i);
                                                    pushBean.setData(dataBean);
                                                    mMiniBarrageViewLayout.setData(pushBean);
                                                }

                                            }
                                        }
                                    } else {

                                        MessageBean messageBean = BaseUtils.getMessageBean(msg);

                                        if (TextUtils.equals(messageBean.getMessageType(), "21")) {//开宝箱
                                            if (!TextUtils.isEmpty(messageBean.getMessage())) {
                                                PushBean pushBean = new PushBean();
                                                pushBean.setType("award");
                                                PushBean.DataBean dataBean = new PushBean.DataBean();
                                                dataBean.setBoxclass(messageBean.box_class);
                                                dataBean.setUser_name(messageBean.push_awards.user_name);
                                                dataBean.setGift_name(messageBean.push_awards.gift_name);
                                                pushBean.setData(dataBean);
                                                mMiniBarrageViewLayout.setData(pushBean);
                                            }
                                        }
                                    }

                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });

            }

            @Override
            public void onMemberJoined(RtmChannelMember rtmChannelMember) {

            }

            @Override
            public void onMemberLeft(RtmChannelMember rtmChannelMember) {

            }
        });
        mRtmChannel_notification.join(new ResultCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                LogUtils.debugInfo("加入第二个频道成功");
            }

            @Override
            public void onFailure(ErrorInfo errorInfo) {
                LogUtils.debugInfo("加入第二个频道失败");
            }
        });
    }

    /**
     * 播放cp同房特效
     *
     * @param cptx
     */
    private void playCpTongFangTX(String cptx, String nickName, String headUrl, String toNickName, String toHeadUrl) {

        if (mLayoutCpAllIn.getVisibility() == View.VISIBLE) {
            mLayoutCpAllIn.setVisibility(View.GONE);
        }
        mTvCpAllIn.setText("守护" + nickName + "与守护" + toNickName + "同在房间");
        loadImage(mImgCpLeftAllIn, headUrl, R.mipmap.no_tou);
        loadImage(mImgCpRightAllIn, toHeadUrl, R.mipmap.no_tou);

        Glide.with(mContext)
                .asBitmap()
                .load(cptx)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        Drawable drawable = new BitmapDrawable(resource);
//                        mLayoutVipEnter.setBackground(drawable);
                        mImgCpALlIn.setImageDrawable(drawable);
                        mLayoutCpAllIn.setVisibility(View.VISIBLE);

                        Animation animation = AnimationUtils.loadAnimation(AdminHomeActivity.this, R.anim.slide_right_in);
                        animation.setDuration(1000);
                        animation.setInterpolator(new DecelerateInterpolator());
                        mLayoutCpAllIn.startAnimation(animation);

                        mLayoutCpAllIn.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Animation animation = AnimationUtils.loadAnimation(AdminHomeActivity.this, R.anim.slide_left_out);
                                animation.setDuration(2000);
                                animation.setInterpolator(new AccelerateInterpolator());
                                if (mLayoutCpAllIn != null) {
                                    mLayoutCpAllIn.startAnimation(animation);
                                    mLayoutCpAllIn.setVisibility(View.GONE);
                                }
                            }
                        }, 2000);

                    }

                });

//        try {
//            SVGAParser parser = new SVGAParser(AdminHomeActivity.this);
//            showServerSVG(parser, cptx, svgImage);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    /**
     * 播放cp携手上麦特效
     *
     * @param cptx
     */
    private void playCpTX(String cptx, String nickName, String headUrl, String toNickName, String toHeadUrl) {

        if (mLayoutCpTongFang.getVisibility() == View.VISIBLE) {
            mLayoutCpTongFang.setVisibility(View.GONE);
        }
        mTvCpLeft.setText(nickName);
        mTvCpRight.setText(toNickName);
        loadImage(mImgCpLeft, headUrl, R.mipmap.no_tou);
        loadImage(mImgCpRight, toHeadUrl, R.mipmap.no_tou);

        Glide.with(mContext)
                .asBitmap()
                .load(cptx)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        Drawable drawable = new BitmapDrawable(resource);
//                        mLayoutVipEnter.setBackground(drawable);
                        mImgCpTongFang.setImageDrawable(drawable);
                        mLayoutCpTongFang.setVisibility(View.VISIBLE);
                        Animation animation = AnimationUtils.loadAnimation(AdminHomeActivity.this, R.anim.alpha_in);
                        mLayoutCpTongFang.startAnimation(animation);
                        mLayoutCpTongFang.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Animation animation = AnimationUtils.loadAnimation(AdminHomeActivity.this, R.anim.alpha_out);
                                if (mLayoutCpTongFang != null) {
                                    mLayoutCpTongFang.startAnimation(animation);
                                    mLayoutCpTongFang.setVisibility(View.GONE);
                                }
                            }
                        }, 2000);

                    }

                });

//        try {
//            SVGAParser parser = new SVGAParser(AdminHomeActivity.this);
//            showServerSVG(parser, cptx, svgImage);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    /**
     * 播放VIP进场特效
     *
     * @param txStr
     */
    private void playVIPTX(String txStr, String userName) {
        if (mLayoutVipEnter.getVisibility() == View.VISIBLE) {
            mLayoutVipEnter.setVisibility(View.GONE);
        }
        mTvVipEnter.setText(userName + "进入房间");
        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        mTvVipEnter.measure(spec, spec);
        int measuredWidthTicketNum = mTvVipEnter.getMeasuredWidth();
        LogUtils.debugInfo("TextView宽度=======", measuredWidthTicketNum + "00000");
        Glide.with(mContext)
                .asBitmap()
                .load(txStr)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        Drawable drawable = new BitmapDrawable(resource);
//                        mLayoutVipEnter.setBackground(drawable);
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mImgVipEnterBg.getLayoutParams();
                        params.width = measuredWidthTicketNum;
                        mImgVipEnterBg.setLayoutParams(params);

                        mImgVipEnterBg.setImageDrawable(drawable);
                        mLayoutVipEnter.setVisibility(View.VISIBLE);

                        Animation animation = AnimationUtils.loadAnimation(AdminHomeActivity.this, R.anim.alpha_in);
                        mLayoutVipEnter.startAnimation(animation);

                        mLayoutVipEnter.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Animation animation = AnimationUtils.loadAnimation(AdminHomeActivity.this, R.anim.alpha_out);
                                if (mLayoutVipEnter != null) {
                                    mLayoutVipEnter.startAnimation(animation);
                                    mLayoutVipEnter.setVisibility(View.GONE);
                                }
                            }
                        }, 2000);

                    }

                });

    }

    private void sendEnterRoom() {
        LoginData loginData = UserManager.getUser();
        sendChannelMessage(BaseUtils.getJson("2", "进入直播间", loginData.getNickname(), loginData.getUserId() + "", vipBean.getData()));
//        sendChannelMessage(BaseUtils.getJson("2", "进入直播间",
//                UserManager.getUser().getNickname(),
//                String.valueOf(UserManager.getUser().getUserId()),
//                vipBean.getData().getVip_img(), vipBean.getData().getHz_img(), vipBean.getData().getNick_color()));
    }


    /**
     * 设置播放礼物期间不能点击页面上的其它按钮
     */
    private void setSvgImgClickble() {
        svgImage.setClickable(true);
        svgImage.setCallback(new SVGACallback() {
            @Override
            public void onPause() {

            }

            @Override
            public void onFinished() {
                if (svgImage != null){
                    svgImage.setClickable(false);
                }
            }

            @Override
            public void onRepeat() {

            }

            @Override
            public void onStep(int i, double v) {

            }
        });
    }

    /**
     * 麦序列表
     */
    public void loadVedioList() {
        RxUtils.loading(commonModel.microphone_status(uid), this)
                .subscribe(new ErrorHandleSubscriber<Microphone>(mErrorHandler) {
                    @Override
                    public void onNext(Microphone enterRoom) {
                        List<Microphone.DataBean.MicrophoneBean> microphone = enterRoom.getData().getMicrophone();
                        mMicrophone = microphone;
                        List<Integer> listKong = new ArrayList<>();
                        for (Microphone.DataBean.MicrophoneBean list : microphone) {//判断是否满位
                            int status = list.getStatus();
                            if (status == 1) {
                                listKong.add(status);
                            }
                        }

                        /*boolean meAtMic = false;
                        //判断是否自己在麦上
                        for (Microphone.DataBean.MicrophoneBean items : microphone) {
                            if (items.getUser_id()
                                    .equals(String.valueOf(mLoginData.getUserId()))) {
                                meAtMic = true;
                                break;
                            }
                        }*/

                        for (Integer key : mMap.keySet()) {
                            CountTimeUtils countTimeUtils = mMap.get(key);
                            countTimeUtils.cancel();
                        }

                        setKazuo(img1, text1, mImgTxk1, 0, microphone, imgVedio1, mTvPk1, daoJiShi1, ivTs1);
                        setKazuo(img2, text2, mImgTxk2, 1, microphone, imgVedio2, mTvPk2, daoJiShi2, ivTs2);
                        setKazuo(img3, text3, mImgTxk3, 2, microphone, imgVedio3, mTvPk3, daoJiShi3, ivTs3);
                        setKazuo(img4, text4, mImgTxk4, 3, microphone, imgVedio4, mTvPk4, daoJiShi4, ivTs4);
                        setKazuo(img5, text5, mImgTxk5, 4, microphone, imgVedio5, mTvPk5, daoJiShi5, ivTs5);
                        setKazuo(img6, text6, mImgTxk6, 5, microphone, imgVedio6, mTvPk6, daoJiShi6, ivTs6);
                        setKazuo(img7, text7, mImgTxk7, 6, microphone, imgVedio7, mTvPk7, daoJiShi7, ivTs7);
                        setKazuo(img8, tvZhuchiName, mImgTxk8, 8, microphone, imgVedio8, mTvPk8, daoJiShi8, ivTs8);
                        setKazuo(imgLast, textLast, mImgTxkLast, 7, microphone, imgVedioLast, mTvPkLast, daoJiShiLast, ivTsLast);

                        String userId = enterRoom.getData().getUser_id();
                        if (!TextUtils.isEmpty(userId) && TextUtils.equals(userId, String.valueOf(mLoginData.getUserId()))) {//自己是房主

                            for (int i = 0; i < mMicrophone.size(); i++) {
                                if (mMicrophone.get(i).getStatus() == 1) {
                                    if (mIsFreeMic) {//自由麦上麦
                                        upDownVedio(i);
                                        break;
                                    }
                                }
                            }
                        }

                        getMePaiMaiPosition();

                        //排麦逻辑
//                        if(!mIsPaiDan){//不是派单厅
//                            if (!meAtMic) {//如果自己不在麦位上
//                                if (listKong.size() == 0) {//证明麦上位置满了，显示排麦
//                                    if (mIsFreeMic) {
//                                        imgPaimai.setVisibility(View.VISIBLE);
//                                        imgShangmai.setVisibility(View.GONE);
//                                    } else {
//                                        imgPaimai.setVisibility(View.GONE);
//                                        imgShangmai.setVisibility(View.VISIBLE);
//                                    }
//                                } else {
//                                    imgPaimai.setVisibility(View.GONE);
//                                    imgShangmai.setVisibility(View.VISIBLE);
//                                }
//                            }
//                        }

//                        if(!mIsPaiDan){
//                            if (!mIsFreeMic) {
//                            getMePaiMaiPosition();
//                            }
//                        } else {
//                            getMePaiMaiPosition();
//                        }

                    }
                });
    }


    /**
     * 设置卡座的各个状态
     */
    private void setKazuo(RoundedImageView imageView, TextView textView, ImageView imgTxk, int position,
                          List<Microphone.DataBean.MicrophoneBean> microphone, ImageView imgVedio,
                          TextView tvPk, TextView daojishi, ImageView ivTs) {

        int status = microphone.get(position).getStatus();
        //右下角开麦，闭麦的状态
        int is_sound = microphone.get(position).getIs_sound();
        switch (status) {
            case 1://无人

                if (position != 8) {//8是主持位置
                    if (position == 7) {
                        imageView.setImageResource(R.mipmap.icon_boos_laoban);
                        textView.setText("老板位");
                    } else {
                        loadImage(imageView, "", R.mipmap.room_kazuo_kong);
                        textView.setText((position + 1) + "号麦");
                    }
                } else {
                    loadImage(imageView, "", R.mipmap.room_kazuo_kong);
                    textView.setText("主持位");
                }
//                LogUtils.debugInfo("位置==="+position);
                imgVedio.setVisibility(View.GONE);
                imgTxk.setVisibility(View.GONE);//头像框
                tvPk.setVisibility(View.GONE);//数值PK

                CountTimeUtils countTimeUtils = mMap.get(position);
                if (countTimeUtils != null) {
                    countTimeUtils.cancel();
                    daojishi.setVisibility(View.GONE);
                }

//                daojishi.setVisibility(View.GONE);
//                if (mCountTimeUtils!=null){
//                    mCountTimeUtils.cancel();
//                }
//                hideQuan(position);
                break;
            case 2://麦序有人
                if (!TextUtils.isEmpty(microphone.get(position).getTxk())) {
                    loadImage(ivTs, microphone.get(position).getTxk(), 0);
                }
                loadImage(imageView, microphone.get(position).getHeadimgurl(), R.mipmap.room_kazuo_kong);
                if (microphone.get(position).getIs_play().equals("1")) {//开启了数值PK
                    tvPk.setVisibility(View.VISIBLE);//数值PK
                    tvPk.setText(microphone.get(position).getPrice());
                } else {
                    tvPk.setVisibility(View.GONE);//数值PK
                }
//                if (!TextUtils.isEmpty(microphone.get(position).getTxk())) {//头像框
//                    imgTxk.setVisibility(View.VISIBLE);
//                    loadImage(imgTxk, microphone.get(position).getTxk(), 0);
//                } else {
//                    imgTxk.setVisibility(View.GONE);
//                }
                imgTxk.setVisibility(View.GONE);
                if (position != 8) {//8是主持位置
                    textView.setText(microphone.get(position).getNickname());
                } else {
                    textView.setText("接待：" + microphone.get(position).getNickname());
                }
                imgVedio.setVisibility(View.GONE);
                if (is_sound == 1) {
                    imgVedio.setVisibility(View.GONE);
                    imgVedio.setSelected(true);
                } else {
                    imgVedio.setVisibility(View.VISIBLE);
                    imgVedio.setSelected(false);
                }
                //判断是否自己在麦上
                if (microphone.get(position).getUser_id()
                        .equals(String.valueOf(UserManager.getUser().getUserId()))) {
                    if (is_sound == 1) {
                        isEditBimai = false;
                    } else {
                        isEditBimai = true;
                    }
//                    LogUtils.debugInfo("====userid相同");
                    if (!mIsMeAtVideo) {//表示自己之前不在麦位上，上麦了
                        mIsMeAtVideo = true;
                        selfPosition = position;//自己的麦位位置
                        if (!mIsPaiDan) {
                            imgShangmai.setSelected(true);
                            imgShangmai.setVisibility(View.VISIBLE);
                        }
                        imgPaimai.setVisibility(View.GONE);
                        imgBimai.setVisibility(View.VISIBLE);
                        imgMusic.setVisibility(View.VISIBLE);
                        imgBiaoqing.setVisibility(View.VISIBLE);
                        mRtcEngine.setClientRole(Constants.CLIENT_ROLE_BROADCASTER);

                        if (!isEditBimai) {
                            if (imgBimai.isSelected()) {
                                mRtcEngine.enableLocalAudio(false);
//                Log.e("麦关闭====", "开启555");
//                                imgBimai.setSelected(true);
                            }
//                            else {
////                                mRtcEngine.enableLocalAudio(true);
////                                imgBimai.setSelected(false);
////                            }
                        } else {
                            mRtcEngine.enableLocalAudio(false);
                            toast("已经被管理员闭麦！");
                        }
                    }
                }

                if ("0".equals(microphone.get(position).getRemainTime())) {
                    daojishi.setVisibility(View.GONE);
                } else {
                    daojishi.setVisibility(View.VISIBLE);
                    CountTimeUtils mCountTimeUtils = new CountTimeUtils(Long.parseLong(microphone.get(position).getRemainTime())) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            daojishi.setText(millisUntilFinished + "s");
                        }

                        @Override
                        public void onFinish() {
                            daojishi.setVisibility(View.GONE);
                        }
                    };
                    mCountTimeUtils.start();
                    mMap.put(position, mCountTimeUtils);
                }
                break;
            case 3://被锁
                loadImage(imageView, "", R.mipmap.room_kazuo_suo);
                tvPk.setVisibility(View.GONE);//数值PK
                imgTxk.setVisibility(View.GONE);//头像框
                if (position != 8) {//8是主持位置
                    if (position == 7) {
                        textView.setText("老板位");
                    } else {
                        textView.setText((position + 1) + "号麦");
                    }
                } else {
                    textView.setText("主持位");
                }
                imgVedio.setVisibility(View.GONE);
                break;
            default:
        }

        int sex = microphone.get(position).getSex();
        switch (sex) {
            case 1:
//                imageView.setBorderColor(getResources().getColor(R.color.font_89E0FB));
                imageView.setBorderColor(getResources().getColor(R.color.translant));
                tvPk.setSelected(true);
                break;
            case 2:
//                imageView.setBorderColor(getResources().getColor(R.color.font_FD96AE));
                imageView.setBorderColor(getResources().getColor(R.color.translant));
                tvPk.setSelected(false);
                break;
            default:
                imageView.setBorderColor(getResources().getColor(R.color.translant));
//                textNumber.setBackground(getResources().getDrawable(R.mipmap.room_xuhao_weizhi));
        }
    }

    @Override
    public void finish() {
        super.finish();
        LogUtils.debugInfo("====onDestroy销毁了直播间");
        selfPosition = -1;
        isStart = false;
        mIsMeAtVideo = false;
        try {
            if (mRtcEngine != null) {
                mRtcEngine.stopAudioMixing();
                mRtcEngine.setClientRole(Constants.CLIENT_ROLE_AUDIENCE);//2是观众
            }
            //调用下麦的接口
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            if (timerTask != null) {
                timerTask.cancel();
                timerTask = null;
            }
//            if (mCountTimeUtils!=null){
//                mCountTimeUtils.cancel();
//            }

            layoutRoom();

//            if (mRtmChannel != null) {
//                mRtmChannel.leave(new ResultCallback<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
////                        layoutRoom();
//                    }
//
//                    @Override
//                    public void onFailure(ErrorInfo errorInfo) {
//
//                    }
//                });
//                mRtmChannel.release();
//                mRtmChannel = null;
//            }

            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
            //告诉首页隐藏悬浮窗
            if (enterRoom != null) {

                EventBus.getDefault().post(new FirstEvent(enterRoom.getRoom_info().get(0).getRoom_cover()
                        , Constant.XUANFUYINCANG));
            }
            RoomHelper.stopkeepLiveService(AdminHomeActivity.this);//停止service
//            if (mCountTimeUtils != null) {
//                mCountTimeUtils.cancel();
//            }
            tvPaiDanTimer.stop();
            mIsCuntDownPaiDan = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 自己排麦
     */
    private void mePaiMai(String type) {

        RxUtils.loading(commonModel.addWaid(uid, String.valueOf(mLoginData.getUserId()), type))
                .subscribe(new ErrorHandleSubscriber<WaitList>(mErrorHandler) {
                    @Override
                    public void onNext(WaitList giftListBean) {

//                        toast("排麦成功");

                        getMePaiMaiPosition();

                        PaimaiWindow paimaiWindow = new PaimaiWindow(AdminHomeActivity.this, uid,
                                commonModel, type, mIsPaiDan);
                        paimaiWindow.showAtLocation(imgMessage, Gravity.BOTTOM, 0, 0);
                        //发送广播，通知其他人刷新排麦
                        sendChannelMessage(BaseUtils.getJson("17", "", "", ""));
                    }
                });
    }

    /**
     * 自己排麦的序号
     */
    public void getMePaiMaiPosition() {
        RxUtils.loading(commonModel.waitSort(uid))
                .subscribe(new ErrorHandleSubscriber<GetSortResult>(mErrorHandler) {
                    @Override
                    public void onNext(GetSortResult giftListBean) {

                        if (giftListBean != null && giftListBean.getData() != null) {
                            String sort = giftListBean.getData().getSort();
                            String num = giftListBean.getData().getNum();
                            String shiyinNum = giftListBean.getData().getShiyin_num();
                            String shiyinSort = giftListBean.getData().getShiyin_sort();

                            boolean meAtMic = false;
                            //判断是否自己在麦上
                            for (Microphone.DataBean.MicrophoneBean items : mMicrophone) {

                                if (items.getUser_id()
                                        .equals(String.valueOf(mLoginData.getUserId()))) {
                                    meAtMic = true;
                                    break;
                                }
                            }

                            if (!mIsPaiDan) {//不是派单厅
                                tvShiyinNumber.setVisibility(View.GONE);
                                tvPaiDanTimer.setVisibility(View.GONE);
                                tvPaiDanTimer.stop();
                                mIsCuntDownPaiDan = false;
                                imgDianDan.setVisibility(View.GONE);
                                imgShiYin.setVisibility(View.GONE);
                                imgShangmai.setVisibility(View.VISIBLE);
                                tvShiYinCunt.setVisibility(View.GONE);
                                tvDainDanCunt.setVisibility(View.GONE);
                                if (meAtMic) {
                                    imgShangmai.setSelected(true);
                                } else {
                                    imgShangmai.setSelected(false);
                                }

                                if (!TextUtils.isEmpty(sort) && !TextUtils.equals(sort, "0")) {
                                    mTvPaiMaiCount.setVisibility(View.VISIBLE);
                                    mTvPaiMaiCount.setText(sort);
                                } else {
                                    mTvPaiMaiCount.setVisibility(View.GONE);
                                }
                            } else {
                                tvPaiMaiUserCount.setVisibility(View.GONE);
                                mTvPaiMaiCount.setVisibility(View.GONE);
                                imgPaimai.setVisibility(View.GONE);

                                if (tvPaiDanTimer.getVisibility() == View.GONE) {
                                    tvPaiDanTimer.setVisibility(View.VISIBLE);
                                    tvPaiDanTimer.setText("派单 00:00");
                                    mIsCuntDownPaiDan = false;
                                }

                                imgDianDan.setVisibility(View.VISIBLE);
                                imgShiYin.setVisibility(View.VISIBLE);
                                imgShangmai.setVisibility(View.GONE);

                                if (meAtMic) {
                                    imgDianDan.setSelected(true);
                                    imgShiYin.setVisibility(View.GONE);
                                } else {
                                    imgDianDan.setSelected(false);
                                }

                                if (!TextUtils.isEmpty(sort) && !TextUtils.equals(sort, "0")) {
                                    tvDainDanCunt.setVisibility(View.VISIBLE);
                                    tvDainDanCunt.setText(sort);
                                } else {
                                    tvDainDanCunt.setVisibility(View.GONE);
                                }

                                if (!TextUtils.isEmpty(shiyinSort) && !TextUtils.equals(shiyinSort, "0")) {
                                    tvShiYinCunt.setVisibility(View.VISIBLE);
                                    tvShiYinCunt.setText(shiyinSort);
                                } else {
                                    tvShiYinCunt.setVisibility(View.GONE);
                                }

                            }

                            if (user_type == 1 || user_type == 2) {

                                if (!mIsPaiDan) {

                                    if (!mIsFreeMic) {
                                        if (!TextUtils.isEmpty(num) && !num.equals("0")) {
                                            tvPaiMaiUserCount.setVisibility(View.VISIBLE);
                                            tvPaiMaiUserCount.setText("当前" + num + "人排麦");
                                        } else {
                                            tvPaiMaiUserCount.setVisibility(View.GONE);
                                        }
                                    } else {
                                        tvPaiMaiUserCount.setVisibility(View.GONE);
                                    }
                                } else {
                                    tvShiyinNumber.setVisibility(View.VISIBLE);
                                    tvShiyinNumber.setText("点单" + num + "人，试音" + shiyinNum + "人");
                                }

                            } else {
                                tvShiyinNumber.setVisibility(View.GONE);
                            }

                            //排麦逻辑
                            if (!mIsPaiDan) {//不是派单厅
                                if (!meAtMic) {//如果自己不在麦位上
                                    List<Integer> listKong = new ArrayList<>();
                                    for (Microphone.DataBean.MicrophoneBean list : mMicrophone) {//判断是否满位
                                        int status = list.getStatus();
                                        if (status == 1) {
                                            listKong.add(status);
                                        }
                                    }
                                    if (listKong.size() == 0) {//证明麦上位置满了，显示排麦
                                        if (mIsFreeMic) {
                                            imgPaimai.setVisibility(View.VISIBLE);
                                            imgShangmai.setVisibility(View.GONE);
                                        } else {
                                            imgPaimai.setVisibility(View.GONE);
                                            imgShangmai.setVisibility(View.VISIBLE);
                                            imgShangmai.setSelected(false);
                                        }
                                    } else {
                                        imgPaimai.setVisibility(View.GONE);
                                        imgShangmai.setVisibility(View.VISIBLE);
                                        imgShangmai.setSelected(false);
                                    }
                                }
                            }
                        }

                    }
                });
    }


    @OnClick({R.id.img1, R.id.img6, R.id.img7, R.id.img2, R.id.img8,
            R.id.img5, R.id.img3, R.id.img4, R.id.img_last,
            R.id.imgBack, R.id.imgRight,
            R.id.imgShangmai, R.id.imgTing, R.id.imgBimai, R.id.imgPaimai,
            R.id.imgMusic, R.id.imgAdd, R.id.imgGift, R.id.viewEnmojiTop,
            R.id.viewTop, R.id.imgBiaoqing, R.id.imgMessage,
            R.id.imgCollection, R.id.rl_rank, R.id.textRight, R.id.iv_jindan, R.id.img_volume, R.id.tv_room_notice, R.id.img_diandan, R.id.img_shiyin, R.id.tv_paidan_timer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_paidan_timer://派单
                if (user_type == 1 || user_type == 2) {
                    if (enterRoom != null && enterRoom.getRoom_info() != null && enterRoom.getRoom_info().size() > 0) {
                        PaiDanDialog paiDanDialog = new PaiDanDialog(this, enterRoom.getRoom_info().get(0).getRoom_name(), uid, mIsCuntDownPaiDan);
                        paiDanDialog.setInfo(commonModel, mErrorHandler);
                        paiDanDialog.show();
                    }
                }
                break;
            case R.id.imgPaimai://排麦
                mePaiMai("1");
                break;
            case R.id.img_diandan://点单
                if (imgDianDan.isSelected()) {
                    goDownVedio(String.valueOf(mLoginData.getUserId()));
                } else {
                    if (tvShiYinCunt.getVisibility() == View.VISIBLE) {
                        toast("正在排麦中,请勿重复排麦");
                        return;
                    }
                    mePaiMai("1");
                }
                break;
            case R.id.img_shiyin://试音
                if (imgShiYin.isSelected()) {
                    Log.e("试音", "下麦");
                    goDownVedio(String.valueOf(mLoginData.getUserId()));
                } else {
                    if (enterRoom != null && enterRoom.getRoom_info() != null && enterRoom.getRoom_info().get(0).is_god.equals("0")) {
                        toast("你还不是大神");
                        return;
                    }
                    if (tvDainDanCunt.getVisibility() == View.VISIBLE) {
                        toast("正在排麦中,请勿重复排麦");
                        return;
                    }
                    mePaiMai("2");
                }
                break;
            case R.id.imgGift://礼物
                RxUtils.loading(commonModel.gift_list(String.valueOf(UserManager.getUser().getUserId())))
                        .subscribe(new ErrorHandleSubscriber<GiftListBean>(mErrorHandler) {
                            @Override
                            public void onNext(GiftListBean giftListBean) {
                                if (mMicrophone != null) {
                                    Microphone.DataBean.MicrophoneBean microphoneBean =
                                            new Microphone.DataBean.MicrophoneBean();
                                    microphoneBean.setUser_id(String.valueOf(enterRoom.getRoom_info().get(0).getUid()));
                                    microphoneBean.setHeadimgurl(enterRoom.getRoom_info().get(0).getHeadimgurl());
                                    microphoneBean.setSex(enterRoom.getRoom_info().get(0).getSex());
                                    microphoneBean.setNickname(enterRoom.getRoom_info().get(0).getNickname());

//                                    QuanZaiMaiBean.DataBean.MicrophoneBean microphoneBean = new QuanZaiMaiBean.DataBean.MicrophoneBean();
//                                    microphoneBean.setUser_id(String.valueOf(enterRoom.getRoom_info().get(0).getUid()));
//                                    microphoneBean.setHeadimgurl(enterRoom.getRoom_info().get(0).getHeadimgurl());
//                                    microphoneBean.setSex(enterRoom.getRoom_info().get(0).getSex());
//                                    microphoneBean.setNickname(enterRoom.getRoom_info().get(0).getNickname());

//                                    GiftWindow giftWindow = new GiftWindow(AdminHomeActivity.this,
//                                            mQuanMaiList, commonModel, giftListBean, microphoneBean, imgPopup);
//                                    giftWindow.showAtLocation(imgMessage, Gravity.BOTTOM, 0, 0);

                                    int size = mMicrophone.size();

                                    List<Microphone.DataBean.MicrophoneBean> micList = Arrays.asList(new Microphone.DataBean.MicrophoneBean[size]);

                                    Collections.copy(micList, mMicrophone);

                                    for (int i = 0; i < size; i++) {
                                        micList.get(i).setIndexl(i + 1);
                                    }

                                    List<Microphone.DataBean.MicrophoneBean> micNewList = new ArrayList<>();

                                    String meId = String.valueOf(UserManager.getUser().getUserId());
                                    Microphone.DataBean.MicrophoneBean bean;
                                    for (int a = 0; a < micList.size() - 1; a++) {
                                        bean = micList.get(a);
                                        if (!TextUtils.isEmpty(bean.getUser_id())) {
                                            if (!bean.getUser_id().equals(meId)) {
                                                micNewList.add(bean);
                                            }
                                        }
                                    }
                                    Microphone.DataBean.MicrophoneBean microphoneBean1 = micList.get(micList.size() - 1);
                                    if (!TextUtils.isEmpty(microphoneBean1.getUser_id())) {
                                        microphoneBean1.setIs_zhuchi(true);
                                        micNewList.add(0, microphoneBean1);
                                    }

                                    GiftPopw giftWindow = new GiftPopw(AdminHomeActivity.this,
                                            micNewList, commonModel, giftListBean, microphoneBean, imgPopup);
                                    giftWindow.showAtLocation(imgMessage, Gravity.BOTTOM, 0, 0);

                                }
                            }
                        });

                break;
            case R.id.imgBack:
                if (flag == 1) {
//                    EventBus.getDefault().post(new FirstEvent(enterRoom.getRoom_info().get(0).getRoom_cover()
//                            , Constant.FANHUIZHUYE));
                    EventBus.getDefault().post(new FirstEvent("指定发送",
                            Constant.FANHUIZHUYE, enterRoom));
                    moveTaskToBack(true);
                    overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
                    isTop = false;
                }
                break;
            case R.id.imgBiaoqing://表情
                RoomHelper.loadEmoji(commonModel, this, mErrorHandler, rlEmoji, viewPager, indicator, getSupportFragmentManager());
                break;
//            case R.id.imgRoom://厅主头像
//                setRoomHeader();
//                break;
            case R.id.viewTop://隐藏音乐
                llMusic.setVisibility(View.GONE);
                break;
            case R.id.viewEnmojiTop://隐藏表情
                rlEmoji.setVisibility(View.GONE);
                break;
            case R.id.imgRight://房间公告
                RoomGaoWindow roomGaoWindow = new RoomGaoWindow(this);

                View viewNotice = findViewById(R.id.imgRight);

                roomGaoWindow.showAsDropDown(viewNotice, 0, 10);

                if (TextUtils.isEmpty(enterRoom.getRoom_info().get(0).getRoom_intro())) {
                    roomGaoWindow.getTextDec().setText("暂无公告");
                } else {
                    roomGaoWindow.getTextDec().setText(enterRoom.getRoom_info().get(0).getRoom_intro());
                }
                break;
            case R.id.img1:
                if (mMicrophone.size() > 0) {
                    if (user_type == 1 || user_type == 2) {
                        setEditMaiwei(0);
                    } else {
                        upDownVedio(0);
                    }
                }
                break;
            case R.id.img6:
                if (mMicrophone.size() > 0) {
                    if (user_type == 1 || user_type == 2) {
                        setEditMaiwei(5);
                    } else {
                        upDownVedio(5);
                    }
                }
                break;
            case R.id.img7:
                if (mMicrophone.size() > 0) {
                    if (user_type == 1 || user_type == 2) {
                        setEditMaiwei(6);
                    } else {
                        upDownVedio(6);
                    }
                }
                break;
            case R.id.img8:
                if (mMicrophone.size() > 0) {
                    if (user_type == 1 || user_type == 2) {
                        setEditMaiwei(8);
                    } else {
                        upDownVedio(8);
                    }
                }
                break;
            case R.id.img_last:
                if (mMicrophone.size() > 0) {
                    if (user_type == 1 || user_type == 2) {
                        setEditMaiwei(7);
                    } else {
                        upDownVedio(7);
                    }
                }
                break;
            case R.id.img2:
                if (mMicrophone.size() > 0) {
                    if (user_type == 1 || user_type == 2) {
                        setEditMaiwei(1);
                    } else {
                        upDownVedio(1);
                    }
                }
                break;
            case R.id.img5:
                if (mMicrophone.size() > 0) {
                    if (user_type == 1 || user_type == 2) {
                        setEditMaiwei(4);
                    } else {
                        upDownVedio(4);
                    }
                }
                break;
            case R.id.img3:
                if (mMicrophone.size() > 0) {
                    if (user_type == 1 || user_type == 2) {
                        setEditMaiwei(2);
                    } else {
                        upDownVedio(2);
                    }
                }
                break;
            case R.id.img4:
                if (mMicrophone.size() > 0) {
                    if (user_type == 1 || user_type == 2) {
                        setEditMaiwei(3);
                    } else {
                        upDownVedio(3);
                    }
                }
                break;

            case R.id.imgShangmai://上麦
                if (imgShangmai.isSelected()) {
                    Log.e("上麦", "下麦");
                    goDownVedio(String.valueOf(mLoginData.getUserId()));
                } else {//上麦
                    if (mIsFreeMic) {//自由麦位
                        for (int i = 0; i < mMicrophone.size(); i++) {
                            if (mMicrophone.get(i).getStatus() == 1) {
                                upDownVedio(i);
                                return;
                            }
                        }
                    } else {//开始排麦
                        mePaiMai("1");
                    }
                }
                break;
            case R.id.imgTing://开关声音
                //TODO enableLocalAudio	开关本地音频采集  muteAllRemoteAudioStreams--停止/恢复接收所有音频流
                if (imgTing.isSelected()) {
                    mRtcEngine.muteAllRemoteAudioStreams(false);
                    imgTing.setSelected(false);
                } else {
                    mRtcEngine.muteAllRemoteAudioStreams(true);
                    imgTing.setSelected(true);
                }
                break;
            case R.id.imgBimai://闭麦
                if (!isEditBimai) {
                    if (imgBimai.isSelected()) {
                        mRtcEngine.enableLocalAudio(true);
//                        Log.e("麦开启====", "开启");
                        imgBimai.setSelected(false);
                        mIsMeCloseVideo = false;
                    } else {
                        mRtcEngine.enableLocalAudio(false);
//                        Log.e("麦关闭====", "关闭");
                        imgBimai.setSelected(true);
                        mIsMeCloseVideo = true;//自己闭麦了

                        MessageBean messageBean = new MessageBean();
                        messageBean.setMessageType("16");
                        messageBean.setUser_id(String.valueOf(mLoginData.getUserId()));
                        LogUtils.debugInfo("====发送关麦消息：" + JSON.toJSONString(messageBean));
                        sendChannelMessage(JSON.toJSONString(messageBean));
                    }
                } else {
                    toast("已经被管理员闭麦！");
                }
                break;
            case R.id.imgMusic:
                llMusic.setVisibility(View.VISIBLE);
//                loadMusic();
                break;
            case R.id.imgAdd:
                if (user_type == 1) {
                    RoomSetWindow1 roomSetWindow1 = new RoomSetWindow1(AdminHomeActivity.this);
                    roomSetWindow1.showAtLocation(imgMessage, Gravity.BOTTOM, 0, 0);
                    roomSetWindow1.setOnDismissListener(() -> {
                        WindowManager.LayoutParams params = getWindow().getAttributes();
                        params.alpha = 1f;
                        getWindow().setAttributes(params);
                    });
                    roomSetWindow1.getMyGrid().setOnItemClickListener((parent, view1, position, id) -> {
                        roomSetWindow1.dismiss();
                        switch (position) {
                            case 0://设置
                                if (enterRoom == null) {
                                    return;
                                }
                                Intent intent2 = new Intent(AdminHomeActivity.this, RoomSettingActivity.class);
                                intent2.putExtra("isHome", uid);
                                intent2.putExtra("enterRoom", enterRoom);
                                ArmsUtils.startActivity(intent2);
                                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                                mCanReture = true;
                                break;
                            case 5: // 管理员
                                Intent intent = new Intent(AdminHomeActivity.this, SetAdminActivity.class);
                                intent.putExtra("uid", uid);
                                ArmsUtils.startActivity(intent);
                                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                                mCanReture = true;
                                break;
                            case 1: //清空消息
                                String json = BaseUtils.getJson("6", "",
                                        UserManager.getUser().getNickname(),
                                        String.valueOf(UserManager.getUser().getUserId()));
                                sendChannelMessage(json);
                                roomMessageAdapter.getData().clear();
                                roomMessageAdapter.notifyDataSetChanged();
                                recyclerView.scrollToPosition(roomMessageAdapter.getData().size());
                                break;
                            case 2://数值PK
                                if (enterRoom == null || enterRoom.getRoom_info() == null || enterRoom.getRoom_info().size() == 0) {
                                    return;
                                }
                                CountPKDialog countPKDialog = new CountPKDialog(AdminHomeActivity.this);
                                countPKDialog.setInfo(commonModel, uid, mErrorHandler, enterRoom.getRoom_info().get(0).play_num);
                                countPKDialog.show();
                                break;
                            case 3: //厅主打赏统计
                                Intent intent1 = new Intent(mContext, RoomRewardActivity.class);
                                intent1.putExtra("uid", uid);
                                startActivity(intent1);
                                mCanReture = true;
                                break;
                            case 4: //收入统计
                                Intent intent3 = new Intent(mContext, IncomeSumActivity.class);
                                intent3.putExtra("uid", uid);
                                startActivity(intent3);
                                mCanReture = true;
                                break;
                        }
                    });
                } else if (user_type == 2) {
                    RoomSetWindow2 roomSetWindow2 = new RoomSetWindow2(AdminHomeActivity.this);
                    roomSetWindow2.showAtLocation(imgMessage, Gravity.BOTTOM, 0, 0);
                    roomSetWindow2.setOnDismissListener(() -> {
                        WindowManager.LayoutParams params = getWindow().getAttributes();
                        params.alpha = 1f;
                        getWindow().setAttributes(params);
                    });
                    roomSetWindow2.getMyGrid().setOnItemClickListener((parent, view1, position, id) -> {
                        roomSetWindow2.dismiss();
                        switch (position) {
                            case 0:
                                if (enterRoom == null) {
                                    return;
                                }
                                Intent intent2 = new Intent(AdminHomeActivity.this, RoomSettingActivity.class);
                                intent2.putExtra("isHome", uid);
                                intent2.putExtra("enterRoom", enterRoom);
                                ArmsUtils.startActivity(intent2);
                                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                                mCanReture = true;
                                break;
                            case 1:
                                String json = BaseUtils.getJson("6", "",
                                        UserManager.getUser().getNickname(),
                                        String.valueOf(UserManager.getUser().getUserId()));
                                sendChannelMessage(json);
                                roomMessageAdapter.getData().clear();
                                roomMessageAdapter.notifyDataSetChanged();
                                break;
                            case 2://数值PK
                                if (enterRoom == null || enterRoom.getRoom_info() == null || enterRoom.getRoom_info().size() == 0) {
                                    return;
                                }
                                CountPKDialog countPKDialog = new CountPKDialog(AdminHomeActivity.this);
                                countPKDialog.setInfo(commonModel, uid, mErrorHandler, enterRoom.getRoom_info().get(0).play_num);
                                countPKDialog.show();
                                break;
                            case 3: //管理员打赏统计
                                Intent intent = new Intent(mContext, RoomRewardActivity.class);
                                intent.putExtra("uid", uid);
                                startActivity(intent);
                                mCanReture = true;
                                break;
                        }
                    });
                }
                break;
            case R.id.imgMessage://点击发消息
                sendUserData();
                break;
            case R.id.rl_rank://排行
                Intent intent = new Intent(AdminHomeActivity.this, RoomRankActivity.class);
                intent.putExtra("id", enterRoom.getRoom_info().get(0).getUid());
                intent.putExtra("image", enterRoom.getRoom_info().get(0).back_img);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                mCanReture = true;
                break;
            case R.id.imgCollection://收藏
                if (imgCollection.isSelected()) {
                    RxUtils.loading(commonModel.remove_mykeep(uid,
                            String.valueOf(UserManager.getUser().getUserId())), this)
                            .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                                @Override
                                public void onNext(BaseBean baseBean) {
                                    toast("取消收藏");
                                    imgCollection.setSelected(false);
                                }
                            });
                } else {
                    RxUtils.loading(commonModel.room_mykeep(uid,
                            String.valueOf(UserManager.getUser().getUserId())), this)
                            .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                                @Override
                                public void onNext(BaseBean baseBean) {
                                    toast("收藏成功");
                                    imgCollection.setSelected(true);
                                    Animation animation = AnimationUtils.loadAnimation(AdminHomeActivity.this, R.anim.alpha_out);
                                    imgCollection.startAnimation(animation);
                                    animation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
//                                            imgCollection.setVisibility(View.GONE);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });

                                }
                            });
                }
                break;
            case R.id.textRight://更多
                RoomTopWindow roomTopWindow = new RoomTopWindow(this, user_type);
                roomTopWindow.showAsDropDown(textRight);
                roomTopWindow.getLlClose().setOnClickListener(v -> {
                    roomTopWindow.dismiss();
                    mRtcEngine.stopAudioMixing();
                    isStart = false;
                    finish();
                });
                roomTopWindow.getLlJubao().setOnClickListener(v -> {//举报
                    roomTopWindow.dismiss();
                    ReportWindow reportWindow = new ReportWindow(AdminHomeActivity.this);
                    reportWindow.showAtLocation(imgMessage, Gravity.BOTTOM, 0, 0);
                });
                roomTopWindow.getLlClear().setOnClickListener(v -> {//清除魅力值
                    roomTopWindow.dismiss();
                    ClearMlWindow clearMlWindow = new ClearMlWindow(mContext, (ViewGroup) getWindow().getDecorView(), commonModel, mErrorHandler, uid);
                    clearMlWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
                });
                roomTopWindow.getLlShare().setOnClickListener(v -> {
                    roomTopWindow.dismiss();
                    //UMWeb web = new UMWeb("http://fir.zzmzrj.com/tvc2");
                    UMWeb web = new UMWeb("https://www.jiyinapp.cn");
                    web.setTitle("积音语音");//标题
                    web.setDescription("连麦哄睡，甜蜜积音，有趣的小姐姐小哥哥都在这里呀(✿◡‿◡)");//描述
                    //分享的标题，房间名称，图片，房间图片
                    if (enterRoom != null && enterRoom.getRoom_info() != null && enterRoom.getRoom_info().size() > 0) {
                        web.setTitle(enterRoom.getRoom_info().get(0).getRoom_name());//标题
                        web.setThumb(new UMImage(this, enterRoom.getRoom_info().get(0).getRoom_cover()));
                    }

                    ShareBoardConfig config = new ShareBoardConfig();//新建ShareBoardConfig
                    config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_BOTTOM);//设置位置
//                    config.setMenuItemBackgroundShape(getResources().getColor(R.drawable.shape_home_round));
                    config.setCancelButtonVisibility(true);
                    config.setTitleText("分享至");
                    config.setTitleTextColor(getResources().getColor(R.color.font_333333));
                    config.setMenuItemTextColor(getResources().getColor(R.color.font_333333));
                    config.setIndicatorVisibility(false);
                    config.setCancelButtonVisibility(false);
                    config.setShareboardBackgroundColor(getResources().getColor(R.color.white));

                    new ShareAction(AdminHomeActivity.this)
                            .withMedia(web)
                            .setDisplayList(SHARE_MEDIA.WEIXIN,
                                    SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                            .setCallback(RoomHelper.getUMShareListener(this, commonModel, AdminHomeActivity.this, mErrorHandler))
                            .open(config);
                });
                break;
            case R.id.iv_jindan:
                GemStoneDialog gemStoneDialog = new GemStoneDialog(AdminHomeActivity.this, commonModel, mErrorHandler, 0);
                gemStoneDialog.show();
//                MessageDialog.show(AdminHomeActivity.this,"","1、开宝箱需要优先购买钥匙，钥匙10金币/ 把;\n2、可以单次开锁宝箱，也可以10次、100 次;\n3、宝箱分为两种普通宝箱和守护宝箱，守护 宝箱每天定时定点开启;\n4、宝箱中的神秘礼物定期会更新，赠送和收 到神秘礼物的双方若已经开通守护点数，则会 提升两人的守护值;\n5、宝箱中开到的礼物会自动加入背包列表， 可以随时使用;\n6、可以在中奖记录中查看最近一周的开奖记 录;\n7、如有问题，请截图保存并与积音官方客服 联系;",null);
//                CustomDialog.show(AdminHomeActivity.this, R.layout.box_rule_popu, new CustomDialog.OnBindView() {
//                    @Override
//                    public void onBind(CustomDialog dialog, View v) {
//
//                    }
//                });

//                if (a == 0) {
//                    BoxTitleWindow boxTitleWindow = new BoxTitleWindow(AdminHomeActivity.this);
//                    boxTitleWindow.showAtLocation(room, Gravity.CENTER, 0, 0);
//                    a = 1;
//                } else if (a == 1) {
//                    BoxFirstOpenWindow boxFirstOpenWindow = new BoxFirstOpenWindow(AdminHomeActivity.this);
//                    boxFirstOpenWindow.showAtLocation(room, Gravity.CENTER, 0, 0);
//                    boxFirstOpenWindow.getOkBtn().setOnClickListener(v -> {
//                        boxFirstOpenWindow.dismiss();
//                    });
//                    a = 2;
//                } else if (a == 2) {
//                    CustomDialog.show(AdminHomeActivity.this, R.layout.box_open_record_window, new CustomDialog.OnBindView() {
//                        @Override
//                        public void onBind(CustomDialog dialog, View v) {
//
//                        }
//                    });
//                    a = 0;
//                }

                break;
            case R.id.img_volume://点击音量

                MusicVolumeWindow musicAddWindow = new MusicVolumeWindow(this);

                musicAddWindow.getMenuView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

                int measuredHeight = musicAddWindow.getMenuView().getMeasuredHeight();

                int measuredWidth = musicAddWindow.getMenuView().getMeasuredWidth();

                int[] location = new int[2];

                imgVolume.getLocationOnScreen(location);

                musicAddWindow.showAtLocation(imgVolume, Gravity.NO_GRAVITY, location[0] + imgVolume.getWidth() / 2 - measuredWidth / 2, location[1] - measuredHeight - QMUIDisplayHelper.dpToPx(6));

                musicAddWindow.setProgress(mMixingPlayoutVolume);

                musicAddWindow.setOnRangeChangedListener(new OnRangeChangedListener() {
                    boolean isSetOne = false;

                    @Override
                    public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                        LogUtils.debugInfo("left====" + leftValue + "right===" + rightValue);
                        mMixingPlayoutVolume = (int) (leftValue * 0.7);
                        mRtcEngine.adjustAudioMixingPlayoutVolume(mMixingPlayoutVolume);// 设置混音音量，设置本地用户听到的音乐文件音量
                        mRtcEngine.adjustAudioMixingPublishVolume(mMixingPlayoutVolume);// 设置混音音量，设置远端用户听到的音乐文件音量，
//                        mRtcEngine.adjustPlaybackSignalVolume(180);// 设置人声的播放信号音量,调节音量的参数值范围是 0 - 400，默认值 100 表示原始音量，即不对信号做缩放，400 表示原始音量的 4 倍
                        if (leftValue == 0) {
                            imgVolume.setImageResource(R.mipmap.icon_volume_zero);
                            isSetOne = false;
                        } else {
                            if (!isSetOne) {
                                isSetOne = true;
                                imgVolume.setImageResource(R.mipmap.icon_volume_one);
                            }
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

                    }

                    @Override
                    public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

                    }
                });
                break;
            case R.id.tv_room_notice:
//                LogUtils.debugInfo("====第一次获取房间ID",enterRoom.getRoom_info().get(0).getUid()+);
                Intent intent1 = new Intent(AdminHomeActivity.this, RoomRank2Activity.class);
                intent1.putExtra("uid", String.valueOf(enterRoom.getRoom_info().get(0).getUid()));
                intent1.putExtra("url", enterRoom.getRoom_info().get(0).back_img);
                intent1.putExtra("type", 3);
                startActivity(intent1);
                mCanReture = true;
                break;
            default:
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mCanReture = false;
    }

    /**
     * 聊天发消息
     */
    private void sendUserData() {
        RxUtils.loading(commonModel.not_speak_status(uid, String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean microphone) {
                        KeybordWindow payWindow = new KeybordWindow(AdminHomeActivity.this);
                        payWindow.showAtLocation(imgMessage, Gravity.BOTTOM, 0, 0);
                        payWindow.setOnDismissListener(() -> {
                            WindowManager.LayoutParams params = getWindow().getAttributes();
                            params.alpha = 1f;
                            getWindow().setAttributes(params);
                        });
                        payWindow.getBtn_ok().setOnClickListener(v -> {
                            String str = payWindow.getEditMessage().getText().toString();
                            if (!TextUtils.isEmpty(str)) {
                                String json = "";
                                LoginData loginData = UserManager.getUser();
                                MessageBean msg = new MessageBean();
                                msg.setMessageType("1");
                                msg.setMessage(str);
                                msg.setNickName(loginData.getNickname());
                                msg.headimgurl = loginData.getHeadimgurl();
                                msg.setUser_id(String.valueOf(loginData.getUserId()));

                                if (vipBean != null && vipBean.getData() != null) {
                                    VipBean.DataBean dataBean = vipBean.getData();

                                    msg.nick_color = dataBean.getNick_color();

                                    msg.ltk = dataBean.getLtk();
                                    msg.ltk_left = dataBean.getLtk_left();
                                    msg.ltk_right = dataBean.getLtk_right();

                                    if (TextUtils.isEmpty(vipBean.getData().getVip_img())) {
                                        msg.vip_img = "";
                                    } else {
                                        msg.vip_img = vipBean.getData().getVip_img();
                                    }
                                    if (TextUtils.isEmpty(vipBean.getData().getHz_img())) {
                                        msg.hz_img = "";
                                    } else {
                                        msg.hz_img = vipBean.getData().getHz_img();
                                    }
                                    json = JSON.toJSONString(msg);
//                                    json = BaseUtils.getJson("1", str,
//                                            UserManager.getUser().getNickname(),
//                                            String.valueOf(UserManager.getUser().getUserId()),
//                                            vipBean.getData().getVip_img(), vipBean.getData().getHz_img(), vipBean.getData().getNick_color(), dataBean);
                                }
//                                else {
//                                    json = BaseUtils.getJson("1", str,
//                                            UserManager.getUser().getNickname(),
//                                            String.valueOf(UserManager.getUser().getUserId()),
//                                            "", "", "");
//                                }
                                sendChannelMessage(json);
                                roomMessageAdapter.getData().add(BaseUtils.getMessageBean(json));
                                roomMessageAdapter.notifyDataSetChanged();
//                                recyclerView.post(new Runnable() {
//                                    @Override
//                                    public void run() {
                                recyclerView.scrollToPosition(roomMessageAdapter.getData().size());//滚动测试
//                                    }
//                                });
                                payWindow.dismiss();
                            } else {
                                showToast("请输入内容！");
                            }
                        });
                    }
                });
    }

    /**
     * 管理员点击麦位的操作
     */
    private void setEditMaiwei(int position) {
        switch (mMicrophone.get(position).getStatus()) {
            case 1://可以上麦
                MaterialDialog dialog = null;
                if (user_type == 2 || user_type == 1) {
                    dialog = new MaterialDialog.Builder(this)
                            .customView(R.layout.dialog_room_admin1, true)
                            .show();
                } else {
                    dialog = new MaterialDialog.Builder(this)
                            .customView(R.layout.dialog_room_admin4, true)
                            .show();
                }
                TextView textBaoren = (TextView) dialog.findViewById(R.id.textBaoren);
                TextView textSuomai = (TextView) dialog.findViewById(R.id.textSuomai);
                TextView textCancel = (TextView) dialog.findViewById(R.id.textCancel);
                MaterialDialog finalDialog = dialog;
                textCancel.setOnClickListener(v -> {
                    finalDialog.dismiss();
                });
                textSuomai.setOnClickListener(v -> {
                    RxUtils.loading(commonModel.shut_microphone(uid, position), this)
                            .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                                @Override
                                public void onNext(BaseBean microphone) {
                                    finalDialog.dismiss();
                                    sendChannelMessage(BaseUtils.getJson("3", "", "", ""));
                                    loadVedioList();
                                }
                            });
                });
                textBaoren.setOnClickListener(v -> {//操作用户上下麦
                    finalDialog.dismiss();
//                    String.valueOf(UserManager.getUser().getUserId()
                    showDialogLoding();
                    RxUtils.loading(commonModel.getRoomUsers(uid,
                            ""), this)
                            .subscribe(new ErrorHandleSubscriber<RoomUsersBean>(mErrorHandler) {
                                @Override
                                public void onNext(RoomUsersBean roomUsersBean) {
                                    disDialogLoding();
                                    if (roomUsersBean != null) {

                                        RoomUsersBean.DataBean dataBean = roomUsersBean.getData();

                                        if (dataBean != null) {

                                            List<RoomMultipleItem> roomMultipleItemList = new ArrayList<>();

                                            List<MicUserBean> micUserBeanList = dataBean.getMic_user();//麦上

                                            List<MicUserBean> roomUserBeanList = dataBean.getRoom_user();//麦下

                                            List<MicUserBean> paiMaiBeanList = dataBean.getPaimai();//排麦

                                            List<MicUserBean> shiyinBeanList = dataBean.getShiyin();//试音

                                            List<MicUserBean> seaUserBeanList = dataBean.getSea_user();//搜索

                                            if (micUserBeanList == null) {
                                                micUserBeanList = new ArrayList<>();
                                            }
                                            if (roomUserBeanList == null) {
                                                roomUserBeanList = new ArrayList<>();
                                            }

                                            if (shiyinBeanList == null) {
                                                shiyinBeanList = new ArrayList<>();
                                            }

                                            if (paiMaiBeanList == null) {
                                                paiMaiBeanList = new ArrayList<>();
                                            }

                                            int micUpUsersLine = micUserBeanList.size();//麦上用户在线数目

                                            int micUpUsers = 9;//麦上用户数目

                                            int micDownUsers = roomUserBeanList.size();

                                            int micPaiCounts = paiMaiBeanList.size();

                                            int micShiYinCounts = shiyinBeanList.size();

                                            //麦上title
//                                            RoomMultipleItem roomMultipleItem = new RoomMultipleItem(RoomMultipleItem.TITLE_MIC_UP, new MicUserBean());
                                            //排麦title
                                            RoomMultipleItem roomMultipleItem = new RoomMultipleItem(RoomMultipleItem.TITLE_MIC_PAI, new MicUserBean());

                                            roomMultipleItemList.add(roomMultipleItem);

                                            //排麦
                                            MicUserBean micUserBean;

                                            for (int i = 0; i < paiMaiBeanList.size(); i++) {

                                                micUserBean = paiMaiBeanList.get(i);

                                                roomMultipleItem = new RoomMultipleItem(RoomMultipleItem.MIC_PAI, micUserBean);

                                                roomMultipleItemList.add(roomMultipleItem);

                                            }

                                            if (mIsPaiDan) {

                                                //试音的title
                                                roomMultipleItem = new RoomMultipleItem(RoomMultipleItem.TITLE_MIC_SHIYIN, new MicUserBean());

                                                roomMultipleItemList.add(roomMultipleItem);

                                                //试音
                                                for (int i = 0; i < shiyinBeanList.size(); i++) {

                                                    micUserBean = shiyinBeanList.get(i);

                                                    roomMultipleItem = new RoomMultipleItem(RoomMultipleItem.MIC_SHIYIN, micUserBean);

                                                    roomMultipleItemList.add(roomMultipleItem);

                                                }

                                            }


                                            //麦下的title
                                            roomMultipleItem = new RoomMultipleItem(RoomMultipleItem.TITLE_MIC_DOWN, new MicUserBean());

                                            roomMultipleItemList.add(roomMultipleItem);

                                            //麦下
                                            for (int i = 0; i < roomUserBeanList.size(); i++) {

                                                micUserBean = roomUserBeanList.get(i);

                                                roomMultipleItem = new RoomMultipleItem(RoomMultipleItem.MIC_DOWN, micUserBean);

                                                roomMultipleItemList.add(roomMultipleItem);

                                            }

                                            SelectPeopleUpVideoNewDialog selectPeopleUpVideoDialog = new SelectPeopleUpVideoNewDialog(AdminHomeActivity.this, position, new SelectPeopleUpVideoNewDialog.OnOperationMicListener() {
                                                @Override
                                                public void toUpMic(int position, String userId) {//管理员操作上麦
                                                    upEditVedio(position, userId);
                                                }

                                                @Override
                                                public void toDownMic(String userId) {//管理员操作下麦
                                                    editXiamai(userId);
                                                }
                                            }, mIsPaiDan);

                                            selectPeopleUpVideoDialog.setInfo(commonModel, uid, mErrorHandler);

                                            selectPeopleUpVideoDialog.show();

                                            selectPeopleUpVideoDialog.setUserCount(roomMultipleItemList, micUpUsers, micUpUsersLine, micDownUsers, micPaiCounts, micShiYinCounts);

                                        }
                                    }
                                }

                                @Override
                                public void onError(Throwable t) {
                                    super.onError(t);
                                    disDialogLoding();
                                }
                            });
//                    new MaterialDialog.Builder(this)
//                            .title("抱麦")
//                            .content("输入用户id")
//                            .inputType(InputType.TYPE_CLASS_TEXT)
//                            .input("输入用户id", null, new MaterialDialog.InputCallback() {
//                                @Override
//                                public void onInput(MaterialDialog dialog, CharSequence input) {
//                                    String trim = input.toString().trim();
//                                    if (!TextUtils.isEmpty(trim)) {
//                                        upEditVedio(position, trim);
//                                    } else {
//                                        showToast("请输入用户id");
//                                    }
//                                }
//                            })
//                            .positiveText("确定")
//                            .show();
                });
                textCancel.setOnClickListener(v -> {
                    RxUtils.loading(commonModel.up_microphone(uid,
                            String.valueOf(UserManager.getUser().getUserId()), position + "", "4"), this)
                            .subscribe(new ErrorHandleSubscriber<UpVideoResult>(mErrorHandler) {
                                @Override
                                public void onNext(UpVideoResult microphone) {
                                    finalDialog.dismiss();
                                    if (!mIsPaiDan) {
                                        imgShangmai.setSelected(true);
                                    } else {
                                        imgDianDan.setVisibility(View.VISIBLE);
                                        imgDianDan.setSelected(true);
                                        imgShiYin.setVisibility(View.GONE);
                                    }
                                    imgBimai.setVisibility(View.VISIBLE);
                                    imgMusic.setVisibility(View.VISIBLE);
                                    imgBiaoqing.setVisibility(View.VISIBLE);
                                    sendChannelMessage(BaseUtils.getJson("3", "", "", ""));
                                    mRtcEngine.setClientRole(Constants.CLIENT_ROLE_BROADCASTER);
                                    imgBimai.setSelected(true);
                                    mRtcEngine.enableLocalAudio(false);
                                    sendCPAtVideo(microphone);
                                    loadVedioList();
                                }
                            });
                });
                break;
            case 2://麦上有人
                if ((UserManager.getUser().getUserId() + "").
                        equals(mMicrophone.get(position).getUser_id())) {//点击自己
                    setMyDataDialog(mMicrophone.get(position).getUser_id());
                    return;
                } else if (uid.equals(mMicrophone.get(position).getUser_id())) {//厅主
                    setRoomHeader();
                    return;
                }
                setVedioDialog(position);
                break;
            case 3:
                new MaterialDialog.Builder(this)
                        .title("开放当前麦位")
                        .content("")
                        .onPositive((dialog1, which) -> {
                            RxUtils.loading(commonModel.open_microphone(uid, position), this)
                                    .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                                        @Override
                                        public void onNext(BaseBean microphone) {
                                            dialog1.dismiss();
                                            sendChannelMessage(BaseUtils.getJson("3", "", "", ""));
                                            loadVedioList();
                                        }
                                    });
                        })
                        .positiveText("确认")
                        .negativeText("取消")
                        .show();
                break;
            default:
        }
    }

    /**
     * cp联手上麦
     */
    private void sendCPAtVideo(UpVideoResult upVideoResult) {

        if (upVideoResult.getData() != null && upVideoResult.getData().getUser() != null && upVideoResult.getData().getCp() != null && upVideoResult.getData().getCp().size() > 0) {//有CP

            List<UpVideoResult.DataBean.CpBean> cpList = upVideoResult.getData().getCp();

            UpVideoResult.DataBean.UserBean userBean = upVideoResult.getData().getUser();

            for (UpVideoResult.DataBean.CpBean itemBean : cpList) {

                MessageBean newMessage = new MessageBean();

                newMessage.setNickName(userBean.getNickname());
                newMessage.setUser_id(userBean.getId() + "");
                newMessage.nick_color = userBean.getNick_color();
                newMessage.headimgurl = userBean.getHeadimgurl();

                newMessage.toNickName = itemBean.getNickname();
                newMessage.toNick_color = itemBean.getNick_color();
                newMessage.toheadimgurl = itemBean.getHeadimgurl();
                newMessage.toUser_id = itemBean.getId();
                newMessage.cp_xssm = itemBean.getCp_xssm();//CP特效

                newMessage.setMessageType("12");

                //刷新本地列表
                roomMessageAdapter.getData().add(newMessage);
                roomMessageAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(roomMessageAdapter.getData().size());

                if (!TextUtils.isEmpty(itemBean.getCp_xssm())) {//播放CP特效
                    //播放上麦特效
                    playCpTX(itemBean.getCp_xssm(), userBean.getNickname(), userBean.getHeadimgurl(), itemBean.getNickname(), itemBean.getHeadimgurl());
                }

                String strs = JSON.toJSONString(newMessage);
                //发送频道消息
                sendChannelMessage(strs);

            }


        }

    }


    /**
     * 自己上麦操作
     */
    private void upDownVedio(int position) {
        switch (mMicrophone.get(position).getStatus()) {
            case 1://可以上麦
                if (!mIsFreeMic) {
                    toast("当前为非自由麦位");
                    return;
                }
                String me_id = mLoginData.getUserId() + "";
                RxUtils.loading(commonModel.up_microphone(uid,
                        me_id, position + "", "4"), this)
                        .subscribe(new ErrorHandleSubscriber<UpVideoResult>(mErrorHandler) {
                            @Override
                            public void onNext(UpVideoResult microphone) {
                                if (!mIsPaiDan) {
                                    imgShangmai.setSelected(true);
                                } else {
                                    imgDianDan.setVisibility(View.VISIBLE);
                                    imgDianDan.setSelected(true);
                                    imgShiYin.setVisibility(View.GONE);
                                }
                                imgBimai.setVisibility(View.VISIBLE);
                                imgMusic.setVisibility(View.VISIBLE);
                                imgBiaoqing.setVisibility(View.VISIBLE);
                                sendChannelMessage(BaseUtils.getJson("3", "", "", ""));
                                imgBimai.setSelected(true);
                                mIsMeCloseVideo = true;
//                                mRtcEngine.setClientRole(Constants.CLIENT_ROLE_BROADCASTER);
//                                mRtcEngine.enableLocalAudio(false);
                                mTvPaiMaiCount.setVisibility(View.GONE);
                                sendCPAtVideo(microphone);
//                                uploadLoadVedioList(me_id);
                                loadVedioList();
                            }
                        });
                break;
            case 2:
                if (mMicrophone.get(position).getUser_id().equals(UserManager.getUser().getUserId() + "")) {
                    setMyDataDialog(mMicrophone.get(position).getUser_id());
                } else {
                    setOtherDataDialog(mMicrophone.get(position).getUser_id());
                }
                break;
            case 3:
                showToast("该麦序已锁定！");
                break;
            default:
        }
    }

    /**
     * 自己下麦的操作
     */
    private void goDownVedio(String user_id) {
        RxUtils.loading(commonModel.go_microphone(uid, user_id), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean microphone) {
                        if (!mIsPaiDan) {
                            imgShangmai.setSelected(false);
                        } else {
                            imgDianDan.setVisibility(View.VISIBLE);
                            imgDianDan.setSelected(false);
                            imgShiYin.setVisibility(View.VISIBLE);
                            imgShiYin.setSelected(false);
                        }
                        imgBimai.setVisibility(View.GONE);
                        imgMusic.setVisibility(View.GONE);
                        imgBiaoqing.setVisibility(View.GONE);
                        sendChannelMessage(BaseUtils.getJson("3", "", "", ""));
                        mRtcEngine.setClientRole(Constants.CLIENT_ROLE_AUDIENCE);
                        mRtcEngine.stopAudioMixing();
                        seekBar.setProgress(0);
                        imgStop.setSelected(false);
                        isEditBimai = false;
                        imgBimai.setSelected(false);
                        mIsMeAtVideo = false;
                        loadVedioList();
                    }
                });
    }

    /**
     * 被迫下麦
     */
    private void forcedDownVedio() {
        if (!mIsPaiDan) {
            imgShangmai.setSelected(false);
        } else {
            imgDianDan.setVisibility(View.VISIBLE);
            imgDianDan.setSelected(false);
            imgShiYin.setVisibility(View.VISIBLE);
            imgShiYin.setSelected(false);
        }
        imgBimai.setVisibility(View.GONE);
        imgMusic.setVisibility(View.GONE);
        imgBiaoqing.setVisibility(View.GONE);
        mRtcEngine.setClientRole(Constants.CLIENT_ROLE_AUDIENCE);
//        sendChannelMessage(BaseUtils.getJson("3", "", "", ""));
//        sendChannelMessage(BaseUtils.getJson("3", "", "", ""));
//        mRtcEngine.setClientRole(Constants.CLIENT_ROLE_AUDIENCE);
//        mRtcEngine.stopAudioMixing();
    }

    /**
     * 退出房间的下麦操作
     *
     * @param
     */
    private void goDownVedioUnBind(String user_id) {
        RxUtils.loading(commonModel.go_microphone(uid, user_id))
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean microphone) {
                        sendChannelMessage(BaseUtils.getJson("3", "", "", ""));
                    }
                });
    }

    /**
     * 管理员操作用户上麦
     */
    private void editShangmai(String user_id) {
        for (int i = 0; i < mMicrophone.size(); i++) {
            if (mMicrophone.get(i).getStatus() == 1) {
                RxUtils.loading(commonModel.up_microphone(uid,
                        user_id, i + "", "4"), this)
                        .subscribe(new ErrorHandleSubscriber<UpVideoResult>(mErrorHandler) {
                            @Override
                            public void onNext(UpVideoResult microphone) {
//                                imgShangmai.setSelected(true);
//                                imgBimai.setVisibility(View.VISIBLE);
//                                imgMusic.setVisibility(View.VISIBLE);
//                                imgBiaoqing.setVisibility(View.VISIBLE);
                                sendChannelMessage(BaseUtils.getJson("3", "", "", ""));
                                sendPeerMessage(user_id, Constant.nfgk184grdgdfggunalibaorenshangmai);
//                                mRtcEngine.setClientRole(Constants.CLIENT_ROLE_BROADCASTER);
                                sendCPAtVideo(microphone);
                                loadVedioList();
                            }
                        });
                return;
            }
        }
    }

    /**
     * 管理员操作用户下麦
     */
    private void editXiamai(String user_id) {
        showDialogLoding();
        RxUtils.loading(commonModel.go_microphone(uid, user_id), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean microphone) {
                        disDialogLoding();
                        sendChannelMessage(BaseUtils.getJson("3", "", "", ""));
                        sendPeerMessage(user_id, Constant.nfgk184grdgdfggunaliyuanxiamai);
                        mRtcEngine.stopAudioMixing();
                        seekBar.setProgress(0);
                        imgStop.setSelected(false);
//                        isEditBimai = false;//恢复重置
                        loadVedioList();
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        disDialogLoding();
                    }
                });
    }

    /**
     * 自己点自己头像的弹窗,包括消息列表点击自己名字
     */
    private void setMyDataDialog(String user_id) {
        if (TextUtils.isEmpty(user_id)) {
            user_id = String.valueOf(UserManager.getUser().getUserId());
        }
        RxUtils.loading(commonModel.get_other_user(uid,
                user_id, String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<OtherUser>(mErrorHandler) {
                    @Override
                    public void onNext(OtherUser otherUser) {
                        RoomDialog roomDialog = new RoomDialog(AdminHomeActivity.this,
                                R.layout.dialog_room_admin7);
                        View dialog2 = roomDialog.getmMenuView();
                        roomDialog.showAtLocation(img1, Gravity.CENTER, 0, 0);
//                        MaterialDialog dialog2 = new MaterialDialog.Builder(AdminHomeActivity.this)
//                                .customView(R.layout.dialog_room_admin7, true)
//                                .show();
                        CircularImage img1 = (CircularImage) dialog2.findViewById(R.id.img1);
                        ImageView img2 = (ImageView) dialog2.findViewById(R.id.img2);
                        TextView textName = (TextView) dialog2.findViewById(R.id.textName);
                        TextView textId = (TextView) dialog2.findViewById(R.id.textId);
                        TextView timeText = dialog2.findViewById(R.id.timetext);
//                        if (user_type == 1 || user_type == 2) {
//                            timeText.setVisibility(View.VISIBLE);
//                        } else {
//                            timeText.setVisibility(View.GONE);
//                        }
                        dialog2.findViewById(R.id.iv_liang).setVisibility(otherUser.getData().get(0).getIslh() == 1 ? View.VISIBLE : View.GONE);
                        timeText.setVisibility((user_type == 1 || user_type == 2) ? View.VISIBLE : View.GONE);
                        loadImage(img1, otherUser.getData().get(0).getHeadimgurl(), R.mipmap.touxiang_ziliao_girl);
                        img2.setSelected(otherUser.getData().get(0).getSex() == 1);
                        textName.setText(otherUser.getData().get(0).getNickname());
                        textId.setText("ID:" + otherUser.getData().get(0).getId());
                        TextView textFenNumber = (TextView) dialog2.findViewById(R.id.textFenNumber);
                        textFenNumber.setText(otherUser.getData().get(0).getAge() + "");
                        dialog2.findViewById(R.id.imgVip).setVisibility(TextUtils.isEmpty(otherUser.getData().get(0).vip_img) ? View.GONE : View.VISIBLE);
                        dialog2.findViewById(R.id.imgJinrui).setVisibility(TextUtils.isEmpty(otherUser.getData().get(0).gold_img) ? View.GONE : View.VISIBLE);
                        dialog2.findViewById(R.id.imgXingrui).setVisibility(TextUtils.isEmpty(otherUser.getData().get(0).star_img) ? View.GONE : View.VISIBLE);
                        loadImage(dialog2.findViewById(R.id.imgVip), otherUser.getData().get(0).vip_img, R.mipmap.huizhang);
                        loadImage(dialog2.findViewById(R.id.imgJinrui), otherUser.getData().get(0).gold_img, R.mipmap.huizhang);
                        loadImage(dialog2.findViewById(R.id.imgXingrui), otherUser.getData().get(0).star_img, R.mipmap.huizhang);
                        dialog2.findViewById(R.id.imgHome).setOnClickListener(v -> {
                            roomDialog.dismiss();
                            Intent intent1 = new Intent(AdminHomeActivity.this, MyPersonalCenterTwoActivity.class);
                            intent1.putExtra("sign", 0);
                            intent1.putExtra("id", "");
                            intent1.putExtra("isRoom", true);
                            ArmsUtils.startActivity(intent1);
                            overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                            mCanReture = true;
                        });

                        if (otherUser.getData().get(0).getIs_time() == 0) {
                            timeText.setText("计时");
                        } else {
                            timeText.setText("关闭");
                        }

                        timeText.setOnClickListener(v -> {
                            if (otherUser.getData().get(0).getIs_time() == 0) {
                                roomDialog.dismiss();
                                zhanshiPopup(otherUser);
                            } else {
                                roomDialog.dismiss();
                                closeTime(String.valueOf(otherUser.getData().get(0).getId()));
                            }
                        });
                    }
                });
    }

    /**
     * 点击厅主头像
     */
    private void setRoomHeader() {
        if (user_type == 1) {
            RoomDialog roomDialog = new RoomDialog(AdminHomeActivity.this,
                    R.layout.dialog_room_admin7);
            View dialog2 = roomDialog.getmMenuView();
            roomDialog.showAtLocation(img1, Gravity.CENTER, 0, 0);

//            MaterialDialog dialog2 = new MaterialDialog.Builder(AdminHomeActivity.this)
//                    .customView(R.layout.dialog_room_admin7, true)
//                    .show();
            CircularImage img1 = (CircularImage) dialog2.findViewById(R.id.img1);
            ImageView img2 = (ImageView) dialog2.findViewById(R.id.img2);
            TextView textName = (TextView) dialog2.findViewById(R.id.textName);
            TextView textId = (TextView) dialog2.findViewById(R.id.textId);
            TextView timeText = dialog2.findViewById(R.id.timetext);
            timeText.setVisibility((user_type == 1 || user_type == 2) ? View.VISIBLE : View.GONE);
            loadImage(img1, enterRoom.getRoom_info().get(0).getHeadimgurl(), R.mipmap.touxiang_ziliao_girl);
            img2.setSelected(enterRoom.getRoom_info().get(0).getSex() == 1);
            textName.setText(enterRoom.getRoom_info().get(0).getNickname());
            textId.setText("ID:" + UserManager.getUser().getUserId());
            dialog2.findViewById(R.id.imgHome).setOnClickListener(v -> {
                roomDialog.dismiss();
                Intent intent1 = new Intent(AdminHomeActivity.this, MyPersonalCenterTwoActivity.class);
                intent1.putExtra("sign", 0);
                intent1.putExtra("id", "");
                intent1.putExtra("isRoom", true);
                ArmsUtils.startActivity(intent1);
                overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                mCanReture = true;
            });
            RxUtils.loading(commonModel.get_other_user(uid, uid,
                    String.valueOf(UserManager.getUser().getUserId())), this)
                    .subscribe(new ErrorHandleSubscriber<OtherUser>(mErrorHandler) {
                        @Override
                        public void onNext(OtherUser otherUser) {
                            TextView textFenNumber = (TextView) dialog2.findViewById(R.id.textFenNumber);
                            textFenNumber.setText(otherUser.getData().get(0).getAge() + "");
                            dialog2.findViewById(R.id.iv_liang).setVisibility(otherUser.getData().get(0).getIslh() == 1 ? View.VISIBLE : View.GONE);
                            dialog2.findViewById(R.id.imgVip).setVisibility(TextUtils.isEmpty(otherUser.getData().get(0).vip_img) ? View.GONE : View.VISIBLE);
                            dialog2.findViewById(R.id.imgJinrui).setVisibility(TextUtils.isEmpty(otherUser.getData().get(0).gold_img) ? View.GONE : View.VISIBLE);
                            dialog2.findViewById(R.id.imgXingrui).setVisibility(TextUtils.isEmpty(otherUser.getData().get(0).star_img) ? View.GONE : View.VISIBLE);
                            loadImage(dialog2.findViewById(R.id.imgVip), otherUser.getData().get(0).vip_img, R.mipmap.huizhang);
                            loadImage(dialog2.findViewById(R.id.imgJinrui), otherUser.getData().get(0).gold_img, R.mipmap.huizhang);
                            loadImage(dialog2.findViewById(R.id.imgXingrui), otherUser.getData().get(0).star_img, R.mipmap.huizhang);

                            if (otherUser.getData().get(0).getIs_time() == 0) {
                                timeText.setText("计时");
                            } else {
                                timeText.setText("关闭");
                            }

                            timeText.setOnClickListener(v -> {
                                if (otherUser.getData().get(0).getIs_time() == 0) {
                                    roomDialog.dismiss();
                                    zhanshiPopup(otherUser);
                                } else {
                                    roomDialog.dismiss();
                                    closeTime(String.valueOf(otherUser.getData().get(0).getId()));
                                }
                            });
                        }
                    });
        } else {
            setOtherDataDialog(uid);
        }
    }

    /**
     * 普通用户点击普通用户的弹窗显示
     */
    private void setOtherDataDialog(String userId) {
        RxUtils.loading(commonModel.get_other_user(uid, userId,
                String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<OtherUser>(mErrorHandler) {
                    @Override
                    public void onNext(OtherUser otherUser) {
                        loadSongLi(otherUser);
                    }
                });
    }
    private void setOtherDataDialogs(String userId) {
        RxUtils.loading(commonModel.get_other_user(uid, userId,
                String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<OtherUser>(mErrorHandler) {
                    @Override
                    public void onNext(OtherUser otherUser) {
                        loadSongLis(otherUser);
                    }
                });
    }
    /**
     * 管理员点击发言用户的弹窗显示
     */
    private void setEditOtherDataDialog(String userId) {
        RxUtils.loading(commonModel.get_other_user(uid, userId,
                String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<OtherUser>(mErrorHandler) {
                    @Override
                    public void onNext(OtherUser otherUser) {
                        RoomDialog roomDialog = new RoomDialog(AdminHomeActivity.this,
                                R.layout.dialog_room_admin5);
                        View dialog2 = roomDialog.getmMenuView();
                        roomDialog.showAtLocation(img1, Gravity.CENTER, 0, 0);
//                        MaterialDialog dialog2 = new MaterialDialog.Builder(AdminHomeActivity.this)
//                                .customView(R.layout.dialog_room_admin5, true)
//                                .show();
                        CircularImage img1 = (CircularImage) dialog2.findViewById(R.id.img1);
                        ImageView img2 = (ImageView) dialog2.findViewById(R.id.img2);
                        TextView textName = (TextView) dialog2.findViewById(R.id.textName);
                        TextView textId = (TextView) dialog2.findViewById(R.id.textId);
                        TextView textDialogGuanzhu = (TextView) dialog2.findViewById(R.id.textDialogGuanzhu);

                        TextView timeText = dialog2.findViewById(R.id.timetext);
                        dialog2.findViewById(R.id.iv_liang).setVisibility(otherUser.getData().get(0).getIslh() == 1 ? View.VISIBLE : View.GONE);
                        loadImage(img1, otherUser.getData().get(0).getHeadimgurl(), R.mipmap.touxiang_ziliao_girl);
                        img2.setSelected(otherUser.getData().get(0).getSex() == 1);
                        textName.setText(otherUser.getData().get(0).getNickname());
                        textId.setText("ID:" + otherUser.getData().get(0).getId());
                        textDialogGuanzhu.setText(otherUser.getData().get(0).is_follows == 1 ? "已关注" : "关注");
                        TextView textFenNumber = (TextView) dialog2.findViewById(R.id.textFenNumber);
                        TextView textDialogLiwus = (TextView) dialog2.findViewById(R.id.textDialogLiwu);
                        textDialogLiwus.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                roomDialog.dismiss();
                                setOtherDataDialogs(uid);
                            }
                        });
                        textFenNumber.setText(otherUser.getData().get(0).getAge() + "");
                        dialog2.findViewById(R.id.imgVip).setVisibility(TextUtils.isEmpty(otherUser.getData().get(0).vip_img) ? View.GONE : View.VISIBLE);
                        dialog2.findViewById(R.id.imgJinrui).setVisibility(TextUtils.isEmpty(otherUser.getData().get(0).gold_img) ? View.GONE : View.VISIBLE);
                        dialog2.findViewById(R.id.imgXingrui).setVisibility(TextUtils.isEmpty(otherUser.getData().get(0).star_img) ? View.GONE : View.VISIBLE);
                        loadImage(dialog2.findViewById(R.id.imgVip), otherUser.getData().get(0).vip_img, R.mipmap.huizhang);
                        loadImage(dialog2.findViewById(R.id.imgJinrui), otherUser.getData().get(0).gold_img, R.mipmap.huizhang);
                        loadImage(dialog2.findViewById(R.id.imgXingrui), otherUser.getData().get(0).star_img, R.mipmap.huizhang);
                        dialog2.findViewById(R.id.textDialogXiamai).setOnClickListener(v -> {
                            editShangmai(String.valueOf(otherUser.getData().get(0).getId()));
                            roomDialog.dismiss();
                        });
                        dialog2.findViewById(R.id.textDialogJinyan).setOnClickListener(v -> {
                            editJinyan(String.valueOf(otherUser.getData().get(0).getId()));
                            roomDialog.dismiss();
                        });
                        dialog2.findViewById(R.id.textDialogTichu).setOnClickListener(v -> {
                            new MaterialDialog.Builder(AdminHomeActivity.this)
                                    .title("确定要把Ta踢出房间么？")
                                    .content("")
                                    .onPositive((dialog1, which) -> {
                                        editTichu(otherUser.getData().get(0).getId() + "");
                                        roomDialog.dismiss();
                                    })
                                    .positiveText("确认")
                                    .negativeText("取消")
                                    .show();
                        });
                        dialog2.findViewById(R.id.textDialogSongli).setOnClickListener(v -> {
                            roomDialog.dismiss();
                            loadSongLi(otherUser);
                        });
                        dialog2.findViewById(R.id.imgJubao).setOnClickListener(v -> {
                            roomDialog.dismiss();
                            ReportWindow reportWindow = new ReportWindow(AdminHomeActivity.this);
                            reportWindow.showAtLocation(imgMessage, Gravity.BOTTOM, 0, 0);
                        });
                        dialog2.findViewById(R.id.imgHome).setOnClickListener(v -> {
                            roomDialog.dismiss();
                            Intent intent1 = new Intent(AdminHomeActivity.this, MyPersonalCenterTwoActivity.class);
                            if (otherUser.getData().get(0).getId() == UserManager.getUser().getUserId()) {
                                intent1.putExtra("sign", 0);
                                intent1.putExtra("id", "");
                                intent1.putExtra("isRoom", true);
                            } else {
                                intent1.putExtra("sign", 1);
                                intent1.putExtra("id", otherUser.getData().get(0).getId() + "");
                                intent1.putExtra("isRoom", true);
                            }
                            ArmsUtils.startActivity(intent1);
                            overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                            mCanReture = true;
                        });
                        textDialogGuanzhu.setOnClickListener(v -> {
                            String text = textDialogGuanzhu.getText().toString();
                            if (TextUtils.equals("关注", text)) {
                                fllow(String.valueOf(otherUser.getData().get(0).getId()), textDialogGuanzhu);
                                roomDialog.dismiss();
                                toast("关注成功");
                            } else {
                                cancelFllow(String.valueOf(otherUser.getData().get(0).getId()), textDialogGuanzhu);
                                roomDialog.dismiss();
                                toast("取消关注成功");
                            }
                        });

                        if (otherUser.getData().get(0).getIs_time() == 0) {
                            timeText.setText("计时");
                        } else {
                            timeText.setText("关闭");
                        }

                        timeText.setOnClickListener(v -> {
                            if (otherUser.getData().get(0).getIs_time() == 0) {
                                roomDialog.dismiss();
                                zhanshiPopup(otherUser);
                            } else {
                                roomDialog.dismiss();
                                closeTime(String.valueOf(otherUser.getData().get(0).getId()));
                            }
                        });
                    }
                });
    }

    /**
     * 管理员点击麦上用户弹窗
     */
    private void setVedioDialog(String userId) {
        RxUtils.loading(commonModel.get_other_user(uid,
                userId, String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<OtherUser>(mErrorHandler) {
                    @Override
                    public void onNext(OtherUser otherUser) {
                        RoomDialog roomDialog = new RoomDialog(AdminHomeActivity.this,
                                R.layout.dialog_room_admin2);
                        View dialog2 = roomDialog.getmMenuView();
                        roomDialog.showAtLocation(img1, Gravity.CENTER, 0, 0);
//                        MaterialDialog dialog2 = new MaterialDialog.Builder(AdminHomeActivity.this)
//                                .customView(R.layout.dialog_room_admin2, true)
//                                .show();
                        CircularImage img1 = (CircularImage) dialog2.findViewById(R.id.img1);
                        ImageView img2 = (ImageView) dialog2.findViewById(R.id.img2);
                        TextView textName = (TextView) dialog2.findViewById(R.id.textName);
                        TextView textDialogGuanzhu = (TextView) dialog2.findViewById(R.id.textDialogGuanzhu);
                        TextView textId = (TextView) dialog2.findViewById(R.id.textId);
                        TextView textDialogBimai = (TextView) dialog2.findViewById(R.id.textDialogBimai);
                        TextView timeText = dialog2.findViewById(R.id.timetext);
                        loadImage(img1, otherUser.getData().get(0).getHeadimgurl(), R.mipmap.touxiang_ziliao_girl);
                        img2.setSelected(otherUser.getData().get(0).getSex() == 1);
                        textName.setText(otherUser.getData().get(0).getNickname());
                        textId.setText("ID:" + otherUser.getData().get(0).getId());
                        textDialogBimai.setText(otherUser.getData().get(0).getIs_sound() == 1 ? "闭麦" : "开麦");
                        //TODO 关注的状态
                        textDialogGuanzhu.setText(otherUser.getData().get(0).is_follows == 1 ? "已关注" : "关注");
                        TextView textFenNumber = (TextView) dialog2.findViewById(R.id.textFenNumber);
                        textFenNumber.setText(otherUser.getData().get(0).getAge() + "");
                        dialog2.findViewById(R.id.iv_liang).setVisibility(otherUser.getData().get(0).getIslh() == 1 ? View.VISIBLE : View.GONE);
                        dialog2.findViewById(R.id.imgVip).setVisibility(TextUtils.isEmpty(otherUser.getData().get(0).vip_img) ? View.GONE : View.VISIBLE);
                        dialog2.findViewById(R.id.imgJinrui).setVisibility(TextUtils.isEmpty(otherUser.getData().get(0).gold_img) ? View.GONE : View.VISIBLE);
                        dialog2.findViewById(R.id.imgXingrui).setVisibility(TextUtils.isEmpty(otherUser.getData().get(0).star_img) ? View.GONE : View.VISIBLE);
                        loadImage(dialog2.findViewById(R.id.imgVip), otherUser.getData().get(0).vip_img, R.mipmap.huizhang);
                        loadImage(dialog2.findViewById(R.id.imgJinrui), otherUser.getData().get(0).gold_img, R.mipmap.huizhang);
                        loadImage(dialog2.findViewById(R.id.imgXingrui), otherUser.getData().get(0).star_img, R.mipmap.huizhang);
                        dialog2.findViewById(R.id.textDialogXiamai).setOnClickListener(v -> {
                            editXiamai(String.valueOf(otherUser.getData().get(0).getId()));
                            roomDialog.dismiss();
                        });
                        textDialogBimai.setOnClickListener(v -> {
                            if (TextUtils.equals(textDialogBimai.getText(), "闭麦")) {
                                editBimai(String.valueOf(otherUser.getData().get(0).getId()));
                            } else {
                                editKaimai(String.valueOf(otherUser.getData().get(0).getId()));
                            }
                            roomDialog.dismiss();
                        });
                        dialog2.findViewById(R.id.textDialogJinyan).setOnClickListener(v -> {
                            editJinyan(String.valueOf(otherUser.getData().get(0).getId()));
                            roomDialog.dismiss();
                        });
                        dialog2.findViewById(R.id.textDialogTichu).setOnClickListener(v -> {
                            new MaterialDialog.Builder(AdminHomeActivity.this)
                                    .title("确定要把Ta踢出房间么？")
                                    .content("")
                                    .onPositive((dialog1, which) -> {
                                        editTichu(otherUser.getData().get(0).getId() + "");
                                        roomDialog.dismiss();
                                    })
                                    .positiveText("确认")
                                    .negativeText("取消")
                                    .show();
                        });
                        textDialogGuanzhu.setOnClickListener(v -> {
                            String text = textDialogGuanzhu.getText().toString();
                            if (TextUtils.equals("关注", text)) {
                                fllow(String.valueOf(otherUser.getData().get(0).getId()), textDialogGuanzhu);
                                roomDialog.dismiss();
                                toast("关注成功");
                            } else {
                                cancelFllow(String.valueOf(otherUser.getData().get(0).getId()), textDialogGuanzhu);
                                roomDialog.dismiss();
                                toast("取消关注成功");
                            }
                        });
                        dialog2.findViewById(R.id.textDialogSongli).setOnClickListener(v -> {
                            roomDialog.dismiss();
                            loadSongLi(otherUser);
                        });
                        dialog2.findViewById(R.id.imgJubao).setOnClickListener(v -> {
                            roomDialog.dismiss();
                            ReportWindow reportWindow = new ReportWindow(AdminHomeActivity.this);
                            reportWindow.showAtLocation(imgMessage, Gravity.BOTTOM, 0, 0);
                        });
                        dialog2.findViewById(R.id.imgHome).setOnClickListener(v -> {
                            roomDialog.dismiss();
                            Intent intent1 = new Intent(AdminHomeActivity.this, MyPersonalCenterTwoActivity.class);
                            if (otherUser.getData().get(0).getId() == UserManager.getUser().getUserId()) {
                                intent1.putExtra("sign", 0);
                                intent1.putExtra("id", "");
                                intent1.putExtra("isRoom", true);
                            } else {
                                intent1.putExtra("sign", 1);
                                intent1.putExtra("id", otherUser.getData().get(0).getId() + "");
                                intent1.putExtra("isRoom", true);
                            }
                            ArmsUtils.startActivity(intent1);
                            overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                            mCanReture = true;
                        });

                        if (otherUser.getData().get(0).getIs_time() == 0) {
                            timeText.setText("计时");
                        } else {
                            timeText.setText("关闭");
                        }

                        timeText.setOnClickListener(v -> {
                            if (otherUser.getData().get(0).getIs_time() == 0) {
                                roomDialog.dismiss();
                                zhanshiPopup(otherUser);
                            } else {
                                roomDialog.dismiss();
                                closeTime(String.valueOf(otherUser.getData().get(0).getId()));
                            }
                        });
                    }
                });
    }

    /**
     * 管理员点击麦上用户弹窗
     */
    private void setVedioDialog(int position) {
        RxUtils.loading(commonModel.get_other_user(uid,
                mMicrophone.get(position).getUser_id(), String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<OtherUser>(mErrorHandler) {
                    @Override
                    public void onNext(OtherUser otherUser) {
                        RoomDialog roomDialog = new RoomDialog(AdminHomeActivity.this,
                                R.layout.dialog_room_admin2);
                        View dialog2 = roomDialog.getmMenuView();
                        roomDialog.showAtLocation(img1, Gravity.CENTER, 0, 0);
//                        MaterialDialog dialog2 = new MaterialDialog.Builder(AdminHomeActivity.this)
//                                .customView(R.layout.dialog_room_admin2, true)
//                                .show();
                        CircularImage img1 = (CircularImage) dialog2.findViewById(R.id.img1);
                        ImageView img2 = (ImageView) dialog2.findViewById(R.id.img2);
                        TextView textName = (TextView) dialog2.findViewById(R.id.textName);
                        TextView textDialogGuanzhu = (TextView) dialog2.findViewById(R.id.textDialogGuanzhu);
                        TextView textId = (TextView) dialog2.findViewById(R.id.textId);
                        TextView textDialogBimai = (TextView) dialog2.findViewById(R.id.textDialogBimai);
                        TextView timeText = dialog2.findViewById(R.id.timetext);
                        loadImage(img1, otherUser.getData().get(0).getHeadimgurl(), R.mipmap.touxiang_ziliao_girl);
                        img2.setSelected(otherUser.getData().get(0).getSex() == 1);
                        textName.setText(otherUser.getData().get(0).getNickname());
                        textId.setText("ID:" + otherUser.getData().get(0).getId());
                        textDialogBimai.setText(otherUser.getData().get(0).getIs_sound() == 1 ? "闭麦" : "开麦");
                        //TODO 关注的状态
                        textDialogGuanzhu.setText(otherUser.getData().get(0).is_follows == 1 ? "已关注" : "关注");
                        TextView textFenNumber = (TextView) dialog2.findViewById(R.id.textFenNumber);
                        textFenNumber.setText(otherUser.getData().get(0).getAge() + "");
                        dialog2.findViewById(R.id.iv_liang).setVisibility(otherUser.getData().get(0).getIslh() == 1 ? View.VISIBLE : View.GONE);
                        dialog2.findViewById(R.id.imgVip).setVisibility(TextUtils.isEmpty(otherUser.getData().get(0).vip_img) ? View.GONE : View.VISIBLE);
                        dialog2.findViewById(R.id.imgJinrui).setVisibility(TextUtils.isEmpty(otherUser.getData().get(0).gold_img) ? View.GONE : View.VISIBLE);
                        dialog2.findViewById(R.id.imgXingrui).setVisibility(TextUtils.isEmpty(otherUser.getData().get(0).star_img) ? View.GONE : View.VISIBLE);
                        loadImage(dialog2.findViewById(R.id.imgVip), otherUser.getData().get(0).vip_img, R.mipmap.huizhang);
                        loadImage(dialog2.findViewById(R.id.imgJinrui), otherUser.getData().get(0).gold_img, R.mipmap.huizhang);
                        loadImage(dialog2.findViewById(R.id.imgXingrui), otherUser.getData().get(0).star_img, R.mipmap.huizhang);
                        dialog2.findViewById(R.id.textDialogXiamai).setOnClickListener(v -> {
                            editXiamai(String.valueOf(otherUser.getData().get(0).getId()));
                            roomDialog.dismiss();
                        });
                        textDialogBimai.setOnClickListener(v -> {
                            if (TextUtils.equals(textDialogBimai.getText(), "闭麦")) {
                                editBimai(String.valueOf(otherUser.getData().get(0).getId()));
                            } else {
                                editKaimai(String.valueOf(otherUser.getData().get(0).getId()));
                            }
                            roomDialog.dismiss();
                        });
                        dialog2.findViewById(R.id.textDialogJinyan).setOnClickListener(v -> {
                            editJinyan(String.valueOf(otherUser.getData().get(0).getId()));
                            roomDialog.dismiss();
                        });
                        dialog2.findViewById(R.id.textDialogTichu).setOnClickListener(v -> {
                            new MaterialDialog.Builder(AdminHomeActivity.this)
                                    .title("确定要把Ta踢出房间么？")
                                    .content("")
                                    .onPositive((dialog1, which) -> {
                                        editTichu(otherUser.getData().get(0).getId() + "");
                                        roomDialog.dismiss();
                                    })
                                    .positiveText("确认")
                                    .negativeText("取消")
                                    .show();
                        });
                        textDialogGuanzhu.setOnClickListener(v -> {
                            String text = textDialogGuanzhu.getText().toString();
                            if (TextUtils.equals("关注", text)) {
                                fllow(String.valueOf(otherUser.getData().get(0).getId()), textDialogGuanzhu);
                                roomDialog.dismiss();
                                toast("关注成功");
                            } else {
                                cancelFllow(String.valueOf(otherUser.getData().get(0).getId()), textDialogGuanzhu);
                                roomDialog.dismiss();
                                toast("取消关注成功");
                            }
                        });
                        dialog2.findViewById(R.id.textDialogSongli).setOnClickListener(v -> {
                            roomDialog.dismiss();
                            loadSongLi(otherUser);
                        });
                        dialog2.findViewById(R.id.imgJubao).setOnClickListener(v -> {
                            roomDialog.dismiss();
                            ReportWindow reportWindow = new ReportWindow(AdminHomeActivity.this);
                            reportWindow.showAtLocation(imgMessage, Gravity.BOTTOM, 0, 0);
                        });
                        dialog2.findViewById(R.id.imgHome).setOnClickListener(v -> {
                            roomDialog.dismiss();
                            Intent intent1 = new Intent(AdminHomeActivity.this, MyPersonalCenterTwoActivity.class);
                            if (otherUser.getData().get(0).getId() == UserManager.getUser().getUserId()) {
                                intent1.putExtra("sign", 0);
                                intent1.putExtra("id", "");
                                intent1.putExtra("isRoom", true);
                            } else {
                                intent1.putExtra("sign", 1);
                                intent1.putExtra("id", otherUser.getData().get(0).getId() + "");
                                intent1.putExtra("isRoom", true);
                            }
                            ArmsUtils.startActivity(intent1);
                            overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                            mCanReture = true;
                        });
                        if (otherUser.getData().get(0).getIs_time() == 0) {
                            timeText.setText("计时");
                        } else {
                            timeText.setText("关闭");
                        }

                        timeText.setOnClickListener(v -> {
                            if (otherUser.getData().get(0).getIs_time() == 0) {
                                roomDialog.dismiss();
                                zhanshiPopup(otherUser);
                            } else {
                                roomDialog.dismiss();
                                closeTime(String.valueOf(otherUser.getData().get(0).getId()));
                            }
                        });
                    }
                });
    }

    /**
     * 点击按钮送礼
     */
    private void loadSongLi(OtherUser otherUser) {
        RxUtils.loading(commonModel.gift_list(String.valueOf(UserManager.getUser().getUserId())))
                .subscribe(new ErrorHandleSubscriber<GiftListBean>(mErrorHandler) {
                    @Override
                    public void onNext(GiftListBean giftListBean) {
                        Microphone.DataBean.MicrophoneBean microphoneBean =
                                new Microphone.DataBean.MicrophoneBean();
                        microphoneBean.setUser_id(String.valueOf(enterRoom.getRoom_info().get(0).getUid()));
                        microphoneBean.setHeadimgurl(enterRoom.getRoom_info().get(0).getHeadimgurl());
                        microphoneBean.setSex(enterRoom.getRoom_info().get(0).getSex());
                        microphoneBean.setNickname(enterRoom.getRoom_info().get(0).getNickname());
//                        GiftNoUserWindow giftWindow = new GiftNoUserWindow(AdminHomeActivity.this,
//                                fromUserId, nickName, commonModel,
//                                giftListBean, microphoneBean, imgPopup, headerUrl);
//                        giftWindow.showAtLocation(imgMessage, Gravity.BOTTOM, 0, 0);

//                        GiftNoUserPopup giftNoUserPopup = new GiftNoUserPopup(
//                                AdminHomeActivity.this,
//                                otherUser,
//                                commonModel,
//                                giftListBean,
//                                microphoneBean,
//                                imgPopup,
//                                () -> {
                                    RoomDialog roomDialog = new RoomDialog(AdminHomeActivity.this,
                                            R.layout.dialog_room_admin6);
                                    View dialog2 = roomDialog.getmMenuView();
                                    roomDialog.showAtLocation(img1, Gravity.CENTER, 0, 0);
//                        MaterialDialog dialog2 = new MaterialDialog.Builder(AdminHomeActivity.this)
//                                .customView(R.layout.dialog_room_admin6, true)
//                                .show();
                                    CircularImage img1 = (CircularImage) dialog2.findViewById(R.id.img1);
                                    ImageView img2 = (ImageView) dialog2.findViewById(R.id.img2);
                                    TextView textName = (TextView) dialog2.findViewById(R.id.textName);
                                    TextView textId = (TextView) dialog2.findViewById(R.id.textId);
                                    TextView textDialogGuanzhu = (TextView) dialog2.findViewById(R.id.textDialogGuanzhu);
                                    TextView timeText = dialog2.findViewById(R.id.timetext);
                                    TextView textDialogLiwus = (TextView) dialog2.findViewById(R.id.textDialogLiwu);
                                    textDialogLiwus.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            roomDialog.dismiss();
                                            setOtherDataDialogs(uid);
                                        }
                                    });
                                    dialog2.findViewById(R.id.iv_liang).setVisibility(otherUser.getData().get(0).getIslh() == 1 ? View.VISIBLE : View.GONE);
                                    timeText.setVisibility((user_type == 1 || user_type == 2) ? View.VISIBLE : View.GONE);
                                    loadImage(img1, otherUser.getData().get(0).getHeadimgurl(), R.mipmap.touxiang_ziliao_girl);
                                    img2.setSelected(otherUser.getData().get(0).getSex() == 1);
                                    textName.setText(otherUser.getData().get(0).getNickname());
                                    textId.setText("ID:" + otherUser.getData().get(0).getId());
                                    textDialogGuanzhu.setText(otherUser.getData().get(0).is_follows == 1 ? "已关注" : "关注");
                                    TextView textFenNumber = (TextView) dialog2.findViewById(R.id.textFenNumber);
                                    textFenNumber.setText(otherUser.getData().get(0).getAge() + "");
                                    dialog2.findViewById(R.id.imgVip).setVisibility(TextUtils.isEmpty(otherUser.getData().get(0).vip_img) ? View.GONE : View.VISIBLE);
                                    dialog2.findViewById(R.id.imgJinrui).setVisibility(TextUtils.isEmpty(otherUser.getData().get(0).gold_img) ? View.GONE : View.VISIBLE);
                                    dialog2.findViewById(R.id.imgXingrui).setVisibility(TextUtils.isEmpty(otherUser.getData().get(0).star_img) ? View.GONE : View.VISIBLE);
                                    loadImage(dialog2.findViewById(R.id.imgVip), otherUser.getData().get(0).vip_img, R.mipmap.huizhang);
                                    loadImage(dialog2.findViewById(R.id.imgJinrui), otherUser.getData().get(0).gold_img, R.mipmap.huizhang);
                                    loadImage(dialog2.findViewById(R.id.imgXingrui), otherUser.getData().get(0).star_img, R.mipmap.huizhang);
                                    dialog2.findViewById(R.id.textDialogSongli).setOnClickListener(v -> {
                                        roomDialog.dismiss();
                                        Intent intent1 = new Intent(AdminHomeActivity.this, MyPersonalCenterTwoActivity.class);
                                        if (otherUser.getData().get(0).getId() == UserManager.getUser().getUserId()) {
                                            intent1.putExtra("sign", 0);
                                            intent1.putExtra("id", "");
                                            intent1.putExtra("isRoom", true);
                                        } else {
                                            intent1.putExtra("sign", 1);
                                            intent1.putExtra("id", otherUser.getData().get(0).getId() + "");
                                            intent1.putExtra("isRoom", true);
                                        }
                                        ArmsUtils.startActivity(intent1);
                                        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                                        mCanReture = true;

                                    });
                                    dialog2.findViewById(R.id.imgJubao).setOnClickListener(v -> {
                                        roomDialog.dismiss();
                                        ReportWindow reportWindow = new ReportWindow(AdminHomeActivity.this);
                                        reportWindow.showAtLocation(imgMessage, Gravity.BOTTOM, 0, 0);
                                    });
                                    dialog2.findViewById(R.id.imgHome).setOnClickListener(v -> {
                                        roomDialog.dismiss();
                                        Intent intent1 = new Intent(AdminHomeActivity.this, MyPersonalCenterTwoActivity.class);
                                        if (otherUser.getData().get(0).getId() == UserManager.getUser().getUserId()) {
                                            intent1.putExtra("sign", 0);
                                            intent1.putExtra("id", "");
                                            intent1.putExtra("isRoom", true);
                                        } else {
                                            intent1.putExtra("sign", 1);
                                            intent1.putExtra("id", otherUser.getData().get(0).getId() + "");
                                            intent1.putExtra("isRoom", true);
                                        }
                                        ArmsUtils.startActivity(intent1);
                                        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                                        mCanReture = true;
                                    });
                                    textDialogGuanzhu.setOnClickListener(v -> {
                                        String text = textDialogGuanzhu.getText().toString();
                                        if (TextUtils.equals("关注", text)) {
                                            fllow(String.valueOf(otherUser.getData().get(0).getId()), textDialogGuanzhu);
                                            roomDialog.dismiss();
                                            toast("关注成功");
                                        } else {
                                            cancelFllow(String.valueOf(otherUser.getData().get(0).getId()), textDialogGuanzhu);
                                            roomDialog.dismiss();
                                            toast("取消关注成功");
                                        }
                                    });

                                    if (otherUser.getData().get(0).getIs_time() == 0) {
                                        timeText.setText("计时");
                                    } else {
                                        timeText.setText("关闭");
                                    }

                                    timeText.setOnClickListener(v -> {
                                        if (otherUser.getData().get(0).getIs_time() == 0) {
                                            roomDialog.dismiss();
                                            zhanshiPopup(otherUser);
                                        } else {
                                            roomDialog.dismiss();
                                            closeTime(String.valueOf(otherUser.getData().get(0).getId()));
                                        }
                                    });
//                                }
//                        );
//                        giftNoUserPopup.showAtLocation(imgMessage, Gravity.BOTTOM, 0, 0);
                    }
                });
    }

    private void loadSongLis(OtherUser otherUser) {
        RxUtils.loading(commonModel.gift_list(String.valueOf(UserManager.getUser().getUserId())))
                .subscribe(new ErrorHandleSubscriber<GiftListBean>(mErrorHandler) {
                    @Override
                    public void onNext(GiftListBean giftListBean) {
                        Microphone.DataBean.MicrophoneBean microphoneBean =
                                new Microphone.DataBean.MicrophoneBean();
                        microphoneBean.setUser_id(String.valueOf(enterRoom.getRoom_info().get(0).getUid()));
                        microphoneBean.setHeadimgurl(enterRoom.getRoom_info().get(0).getHeadimgurl());
                        microphoneBean.setSex(enterRoom.getRoom_info().get(0).getSex());
                        microphoneBean.setNickname(enterRoom.getRoom_info().get(0).getNickname());
//                        GiftNoUserWindow giftWindow = new GiftNoUserWindow(AdminHomeActivity.this,
//                                fromUserId, nickName, commonModel,
//                                giftListBean, microphoneBean, imgPopup, headerUrl);
//                        giftWindow.showAtLocation(imgMessage, Gravity.BOTTOM, 0, 0);

                        GiftNoUserPopup giftNoUserPopup = new GiftNoUserPopup(
                                AdminHomeActivity.this,
                                otherUser,
                                commonModel,
                                giftListBean,
                                microphoneBean,
                                imgPopup,
                                () -> {
                        RoomDialog roomDialog = new RoomDialog(AdminHomeActivity.this,
                                R.layout.dialog_room_admin6);
                        View dialog2 = roomDialog.getmMenuView();
                        roomDialog.showAtLocation(img1, Gravity.CENTER, 0, 0);
//                        MaterialDialog dialog2 = new MaterialDialog.Builder(AdminHomeActivity.this)
//                                .customView(R.layout.dialog_room_admin6, true)
//                                .show();
                        CircularImage img1 = (CircularImage) dialog2.findViewById(R.id.img1);
                        ImageView img2 = (ImageView) dialog2.findViewById(R.id.img2);
                        TextView textName = (TextView) dialog2.findViewById(R.id.textName);
                        TextView textId = (TextView) dialog2.findViewById(R.id.textId);
                        TextView textDialogGuanzhu = (TextView) dialog2.findViewById(R.id.textDialogGuanzhu);
                        TextView timeText = dialog2.findViewById(R.id.timetext);
                        dialog2.findViewById(R.id.iv_liang).setVisibility(otherUser.getData().get(0).getIslh() == 1 ? View.VISIBLE : View.GONE);
                        timeText.setVisibility((user_type == 1 || user_type == 2) ? View.VISIBLE : View.GONE);
                        loadImage(img1, otherUser.getData().get(0).getHeadimgurl(), R.mipmap.touxiang_ziliao_girl);
                        img2.setSelected(otherUser.getData().get(0).getSex() == 1);
                        textName.setText(otherUser.getData().get(0).getNickname());
                        textId.setText("ID:" + otherUser.getData().get(0).getId());
                        textDialogGuanzhu.setText(otherUser.getData().get(0).is_follows == 1 ? "已关注" : "关注");
                                    TextView textDialogLiwus = (TextView) dialog2.findViewById(R.id.textDialogLiwu);
                                    textDialogLiwus.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            roomDialog.dismiss();
                                            setOtherDataDialogs(uid);
                                        }
                                    });
                        TextView textFenNumber = (TextView) dialog2.findViewById(R.id.textFenNumber);
                        textFenNumber.setText(otherUser.getData().get(0).getAge() + "");
                        dialog2.findViewById(R.id.imgVip).setVisibility(TextUtils.isEmpty(otherUser.getData().get(0).vip_img) ? View.GONE : View.VISIBLE);
                        dialog2.findViewById(R.id.imgJinrui).setVisibility(TextUtils.isEmpty(otherUser.getData().get(0).gold_img) ? View.GONE : View.VISIBLE);
                        dialog2.findViewById(R.id.imgXingrui).setVisibility(TextUtils.isEmpty(otherUser.getData().get(0).star_img) ? View.GONE : View.VISIBLE);
                        loadImage(dialog2.findViewById(R.id.imgVip), otherUser.getData().get(0).vip_img, R.mipmap.huizhang);
                        loadImage(dialog2.findViewById(R.id.imgJinrui), otherUser.getData().get(0).gold_img, R.mipmap.huizhang);
                        loadImage(dialog2.findViewById(R.id.imgXingrui), otherUser.getData().get(0).star_img, R.mipmap.huizhang);
                        dialog2.findViewById(R.id.textDialogSongli).setOnClickListener(v -> {
                            roomDialog.dismiss();
                            Intent intent1 = new Intent(AdminHomeActivity.this, MyPersonalCenterTwoActivity.class);
                            if (otherUser.getData().get(0).getId() == UserManager.getUser().getUserId()) {
                                intent1.putExtra("sign", 0);
                                intent1.putExtra("id", "");
                                intent1.putExtra("isRoom", true);
                            } else {
                                intent1.putExtra("sign", 1);
                                intent1.putExtra("id", otherUser.getData().get(0).getId() + "");
                                intent1.putExtra("isRoom", true);
                            }
                            ArmsUtils.startActivity(intent1);
                            overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                            mCanReture = true;

                        });
                        dialog2.findViewById(R.id.imgJubao).setOnClickListener(v -> {
                            roomDialog.dismiss();
                            ReportWindow reportWindow = new ReportWindow(AdminHomeActivity.this);
                            reportWindow.showAtLocation(imgMessage, Gravity.BOTTOM, 0, 0);
                        });
                        dialog2.findViewById(R.id.imgHome).setOnClickListener(v -> {
                            roomDialog.dismiss();
                            Intent intent1 = new Intent(AdminHomeActivity.this, MyPersonalCenterTwoActivity.class);
                            if (otherUser.getData().get(0).getId() == UserManager.getUser().getUserId()) {
                                intent1.putExtra("sign", 0);
                                intent1.putExtra("id", "");
                                intent1.putExtra("isRoom", true);
                            } else {
                                intent1.putExtra("sign", 1);
                                intent1.putExtra("id", otherUser.getData().get(0).getId() + "");
                                intent1.putExtra("isRoom", true);
                            }
                            ArmsUtils.startActivity(intent1);
                            overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                            mCanReture = true;
                        });
                        textDialogGuanzhu.setOnClickListener(v -> {
                            String text = textDialogGuanzhu.getText().toString();
                            if (TextUtils.equals("关注", text)) {
                                fllow(String.valueOf(otherUser.getData().get(0).getId()), textDialogGuanzhu);
                                roomDialog.dismiss();
                                toast("关注成功");
                            } else {
                                cancelFllow(String.valueOf(otherUser.getData().get(0).getId()), textDialogGuanzhu);
                                roomDialog.dismiss();
                                toast("取消关注成功");
                            }
                        });

                        if (otherUser.getData().get(0).getIs_time() == 0) {
                            timeText.setText("计时");
                        } else {
                            timeText.setText("关闭");
                        }

                        timeText.setOnClickListener(v -> {
                            if (otherUser.getData().get(0).getIs_time() == 0) {
                                roomDialog.dismiss();
                                zhanshiPopup(otherUser);
                            } else {
                                roomDialog.dismiss();
                                closeTime(String.valueOf(otherUser.getData().get(0).getId()));
                            }
                        });
                                }
                        );
                        giftNoUserPopup.showAtLocation(imgMessage, Gravity.BOTTOM, 0, 0);
                    }
                });
    }
    /**
     * 管理员踢出房间 --- 走下麦接口，踢出
     */
    private void editTichu(String user_id) {
        RxUtils.loading(commonModel.out_room(uid, user_id), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean microphone) {
                        sendChannelMessage(BaseUtils.getJson("3", "", "", ""));
                        sendPeerMessage(user_id, Constant.nfgk184grdgdfggunaliyuantichu);
                        loadVedioList();
                    }
                });
    }

    /**
     * 管理员禁言
     */
    private void editJinyan(String user_id) {
        RxUtils.loading(commonModel.is_black(uid, user_id), this)
                .subscribe(new ErrorHandleSubscriber<JinSheng>(mErrorHandler) {
                    @Override
                    public void onNext(JinSheng microphone) {
                        sendChannelMessage(BaseUtils.getJson("3", "", "", ""));
                        sendPeerMessage(user_id, Constant.nfgk184grdgdfggdfghfhrthmkBeiJinYan);
                        loadVedioList();
                    }
                });
    }

    /**
     * 管理员闭麦麦上用户
     */
    private void editBimai(String user_id) {
        RxUtils.loading(commonModel.is_sound(uid, user_id), this)
                .subscribe(new ErrorHandleSubscriber<JinSheng>(mErrorHandler) {
                    @Override
                    public void onNext(JinSheng microphone) {
                        sendChannelMessage(BaseUtils.getJson("3", "", "", ""));
                        sendPeerMessage(user_id, Constant.nfgk184grdgdfggunaliyuanbimai);
                        loadVedioList();
                    }
                });
    }

    /**
     * 管理员解禁麦麦上用户
     */
    private void editKaimai(String user_id) {
        RxUtils.loading(commonModel.remove_sound(uid, user_id), this)
                .subscribe(new ErrorHandleSubscriber<JinSheng>(mErrorHandler) {
                    @Override
                    public void onNext(JinSheng microphone) {
                        sendChannelMessage(BaseUtils.getJson("3", "", "", ""));
                        sendPeerMessage(user_id, Constant.nfgk184grdgdfggdfghfhrthmkkaimai);
                        loadVedioList();
                    }
                });
    }

    /**
     * 管理员抱人上麦
     */
    private void upEditVedio(int position, String id) {
        showDialogLoding();
        RxUtils.loading(commonModel.up_microphone(uid, id, position + "", "4"), this)
                .subscribe(new ErrorHandleSubscriber<UpVideoResult>(mErrorHandler) {
                    @Override
                    public void onNext(UpVideoResult microphone) {
                        disDialogLoding();
                        sendChannelMessage(BaseUtils.getJson("3", "", "", ""));
                        sendPeerMessage(id, Constant.nfgk184grdgdfggunalibaorenshangmai);
                        sendCPAtVideo(microphone);
                        loadVedioList();
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        disDialogLoding();
                    }
                });
    }

    /**
     * 个人离开房间，只个人走
     */
    private void layoutRoom() {//
        RxUtils.loading(commonModel.quit_room(uid, String.valueOf(UserManager.getUser().getUserId())))
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean microphone) {
                        sendChannelMessage(BaseUtils.getJson("3", "", "", ""));
                        if (mRtmChannel != null) {
                            mRtcEngine.stopAudioMixing();
                            mRtcEngine.leaveChannel();
                            mRtcEngine = null;
                        }
                        if (mRtmChannel != null) {
                            mRtmChannel.leave(new ResultCallback<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
//                        layoutRoom();
                                }

                                @Override
                                public void onFailure(ErrorInfo errorInfo) {

                                }
                            });
                            mRtmChannel.release();
                            mRtmChannel = null;
                        }
                        if (mRtmChannel_notification != null) {
                            mRtmChannel_notification.leave(new ResultCallback<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
//                        layoutRoom();
                                }

                                @Override
                                public void onFailure(ErrorInfo errorInfo) {

                                }
                            });
                            mRtmChannel_notification.release();
                            mRtmChannel_notification = null;
                        }

                        if (mRtmClient != null) {
                            mRtmClient.logout(null);
                            mRtmClient.release();
                        }
                    }
                });
    }


    /**
     * 关注
     */
    private void fllow(String id, TextView textView) {
        RxUtils.loading(commonModel.follow(String.valueOf(UserManager.getUser().getUserId()), id), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean microphone) {
                        textView.setText("已关注");
                        EventBus.getDefault().post(new FirstEvent("指定发送", Constant.SHUAXINGUANZHU));
                    }
                });
    }


    /**
     * 取消关注
     */
    private void cancelFllow(String id, TextView textView) {
        RxUtils.loading(commonModel.cancel_follow(String.valueOf(UserManager.getUser().getUserId()), id), this)
                .subscribe(new ErrorHandleSubscriber<BaseBean>(mErrorHandler) {
                    @Override
                    public void onNext(BaseBean microphone) {
                        textView.setText("关注");
                        EventBus.getDefault().post(new FirstEvent("指定发送", Constant.SHUAXINGUANZHU));
                    }
                });
    }

    /**
     * 发送频道消息
     */
    public void sendChannelMessage_2(String msg) {
        try {
            RtmMessage message = mRtmClient.createMessage();
            message.setText(msg);
            mRtmChannel_notification.sendMessage(message, new ResultCallback<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    LogUtils.debugInfo("第二个频道===发送消息成功");
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
     * 发送频道消息
     */
    public void sendChannelMessage(String msg) {
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
    public void sendPeerMessage(String dst, String message) {
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
                Log.d(TAG, "Fails to send the message to the peer, errorCode = "
                        + errorCode);
            }
        });
    }


    /**
     * 加载音乐
     */
    private void loadMusic() {
        //加载音效
        RoomHelper.loadYinXiao(commonModel, this, mErrorHandler, recyclerMusic, mRtcEngine, getSupportFragmentManager());
//        llMusic.setVisibility(View.VISIBLE);
        try {
            listLocal = RoomHelper.getLocalMusic();
            textMusicName.setText(listLocal.get(musicPosition).name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        imgLiebiao.setOnClickListener(v -> {
            llMusic.setVisibility(View.GONE);
            ArmsUtils.startActivity(MusicActivity.class);
            overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
            mCanReture = true;
        });
        imgXunhuan.setOnClickListener(v -> {//循环
            if (imgXunhuan.isSelected()) {
                imgXunhuan.setSelected(false);
                randomMusic = 0;//0代表顺序播放
                toast("当前是顺序播放");
            } else {
                imgXunhuan.setSelected(true);
                randomMusic = 1;
                toast("当前是随机播放");
            }
        });
        imgFront.setOnClickListener(v -> {//上一个
            if (listLocal.size() > 0) {
                if (randomMusic == 0) {
                    if (musicPosition == 0) {
                        toast("已经是第一个了！");
                    } else {
                        seekBar.setProgress(0);
                        imgStop.setSelected(true);
                        musicPosition = musicPosition - 1;

                        if (musicPosition < 0 || musicPosition > listLocal.size() - 1) {
                            musicPosition = 0;
                        }
                        textMusicName.setText(listLocal.get(musicPosition).name);
                        mRtcEngine.startAudioMixing(listLocal.get(musicPosition).songUrl, false, false, 1);
                    }
                } else {
                    seekBar.setProgress(0);
                    imgStop.setSelected(true);
                    musicPosition = BaseUtils.getRandom(listLocal.size());
                    if (musicPosition < 0 || musicPosition > listLocal.size() - 1) {
                        musicPosition = 0;
                    }
                    textMusicName.setText(listLocal.get(musicPosition).name);
                    mRtcEngine.startAudioMixing(listLocal.get(musicPosition).songUrl, false, false, 1);
                }
            } else {
                toast("请去音乐库添加至我的音乐！");
            }
        });
        imgStop.setOnClickListener(v -> {//暂停
            if (imgStop.isSelected()) {
                imgStop.setSelected(false);
                mRtcEngine.pauseAudioMixing();
//                mRtcEngine.getAudioEffectManager().stopAllEffects();
            } else {
                if (listLocal.size() > 0) {
                    int audioMixingCurrentPosition = mRtcEngine.getAudioMixingCurrentPosition();
                    if (audioMixingCurrentPosition != 0) {
                        mRtcEngine.resumeAudioMixing();
                    } else {
                        mRtcEngine.startAudioMixing(listLocal.get(musicPosition).songUrl, false, false, 1);
                    }
                    imgStop.setSelected(true);
                } else {
                    toast("请去音乐库添加至我的音乐！");
                }
            }
        });
        imgNext.setOnClickListener(v -> {//下一个
            if (listLocal.size() > 0) {
                if (randomMusic == 0) {
                    if (musicPosition == listLocal.size() - 1) {
                        toast("已经是最后一个了！");
                    } else {
                        imgStop.setSelected(true);
                        musicPosition = musicPosition + 1;
                        seekBar.setProgress(0);
                        if (musicPosition < 0 || musicPosition > listLocal.size() - 1) {
                            musicPosition = 0;
                        }
                        textMusicName.setText(listLocal.get(musicPosition).name);
                        mRtcEngine.startAudioMixing(listLocal.get(musicPosition).songUrl, false, false, 1);
                    }
                } else {
                    imgStop.setSelected(true);
                    seekBar.setProgress(0);
                    musicPosition = BaseUtils.getRandom(listLocal.size());
                    textMusicName.setText(listLocal.get(musicPosition).name);
                    mRtcEngine.startAudioMixing(listLocal.get(musicPosition).songUrl, false, false, 1);
                }
            } else {
                toast("请去音乐库添加至我的音乐！");
            }
        });

        seekBar.setOnRangeChangedListener(new OnRangeChangedListener() {

            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
//                view.setProgress(leftValue);
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {
                runOnUiThread(() -> {
                    if (timer != null && timerTask != null) {
                        timer.cancel();
                        timerTask.cancel();
                    }
                    if (handler != null) {
                        handler.removeCallbacksAndMessages(null);
                    }
                });
            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {
                LogUtils.debugInfo("拖动结束======");
                runOnUiThread(() -> {
                    timer = new Timer();
                    timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            handler.sendEmptyMessage(1);
                        }
                    };
                    timer.schedule(timerTask, 100, 200);
                });

                handler.sendEmptyMessage(2);//拖动进度条结束
            }
        });
    }

    /**
     * 主页悬浮窗关闭yinpin
     */
    public void stopTing(boolean isStop) {
        if (isStop) {
            mRtcEngine.muteAllRemoteAudioStreams(false);
            imgTing.setSelected(false);
        } else {
            mRtcEngine.muteAllRemoteAudioStreams(true);
            imgTing.setSelected(true);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //flag 1.mainactivity 2.
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (flag == 1) {
                EventBus.getDefault().post(new FirstEvent("指定发送",
                        Constant.FANHUIZHUYE, enterRoom));
                ArmsUtils.startActivity(MainActivity.class);
                moveTaskToBack(true);
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
                llMusic.setVisibility(View.GONE);
                rlEmoji.setVisibility(View.GONE);
                isTop = false;
            } else {
                EventBus.getDefault().post(new FirstEvent("指定发送",
                        Constant.FANHUIZHUYE, enterRoom));
                moveTaskToBack(true);
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
                llMusic.setVisibility(View.GONE);
                rlEmoji.setVisibility(View.GONE);
                isTop = false;
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 外界获取，做对比
     */
    public String getUid() {
        return uid;
    }


    /**
     * 设置编辑房间成功的回调
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(FirstEvent event) {
        String tag = event.getTag();
        if (Constant.FANGJIANSHEZHI.equals(tag)) {//发广播，刷新
//            loadEnterRoom();

            RxUtils.loading(commonModel.enter_room(uid, room_pass,
                    String.valueOf(UserManager.getUser().getUserId())), this)
                    .subscribe(new ErrorHandleSubscriber<EnterRoom>(mErrorHandler) {
                        @Override
                        public void onNext(EnterRoom menterRoom) {
                            enterRoom = menterRoom;
                            textName.setText(enterRoom.getRoom_info().get(0).getRoom_name());
                            textId.setText("ID:" + enterRoom.getRoom_info().get(0).getNumid());
                            textType.setText(enterRoom.getRoom_info().get(0).getName());
                            textNum.setText(String.valueOf(enterRoom.getRoom_info().get(0).hot));
                            loadImage(mImgRoomHead, enterRoom.getRoom_info().get(0).getRoom_cover(), R.mipmap.no_tou);
                            loadImage(imgBg, enterRoom.getRoom_info().get(0).back_img, R.mipmap.room_bg);

                            String currentGongGao = enterRoom.getRoom_info().get(0).getRoom_intro();

                            if (!TextUtils.equals(mStringGongGao, currentGongGao)) {//公告变了
                                mStringGongGao = currentGongGao;
                                //刷新公屏显示公告
                                MessageBean msg = new MessageBean();
                                msg.setMessageType("7");
                                msg.setMessage("");
                                msg.setNickName("");
                                msg.setUser_id("");
                                msg.setRoom_name(enterRoom.getRoom_info().get(0).getRoom_name());
                                msg.setRoom_type(enterRoom.getRoom_info().get(0).getName());
                                msg.setRoom_background(enterRoom.getRoom_info().get(0).back_img);
                                msg.setRoom_intro(enterRoom.getRoom_info().get(0).getRoom_intro());
                                roomMessageAdapter.getData().add(msg);
                                roomMessageAdapter.notifyDataSetChanged();
                                recyclerView.scrollToPosition(roomMessageAdapter.getData().size());
                            }

                            //发送广播通知用户设置
                            sendChannelMessage(BaseUtils.getJson("7", "",
                                    "", "", enterRoom.getRoom_info().get(0).getRoom_name(),
                                    enterRoom.getRoom_info().get(0).getName(),
                                    enterRoom.getRoom_info().get(0).back_img,
                                    enterRoom.getRoom_info().get(0).getRoom_intro()));


                            boolean freeMic = menterRoom.getRoom_info().get(0).free_mic.equals("1") ? true : false;
                            boolean isPaiDan = enterRoom.getRoom_info().get(0).getRoom_type().equals("15") ? true : false;
                            boolean isLoadVedioList = false;
                            if ((mIsFreeMic && !freeMic) || (!mIsFreeMic && freeMic)) {//说明修改了麦位开启或者关闭
                                isLoadVedioList = true;
                                mIsPaiDan = isPaiDan;
                                MessageBean msg = new MessageBean();
                                msg.setMessageType("18");
                                msg.setNickName(mLoginData.getNickname());
                                msg.setUser_id(mLoginData.getUserId() + "");
                                msg.nick_color = vipBean.getData().getNick_color();
                                if (freeMic) {
                                    msg.setMessage("打开");
                                } else {
                                    msg.setMessage("关闭");
                                }
                                mIsFreeMic = freeMic;
                                RoomHelper.refreshAdapter(msg, recyclerView, roomMessageAdapter);
                                String str = JSON.toJSONString(msg);
                                //发送广播通知
                                sendChannelMessage(str);
                                loadVedioList();
                            }
                            if (!isLoadVedioList && ((mIsPaiDan && !isPaiDan) || (!mIsPaiDan && isPaiDan))) {
                                mIsPaiDan = isPaiDan;
                                loadVedioList();
                            }

                        }
                    });


        } else if (Constant.SHEZHIGUANLI.equals(tag)) {//设置管理
            String userId = event.getMsg();
            sendPeerMessage(userId, Constant.nfgk184grdgdfggdfghfhrthmkShezhiguanliyuan);
        } else if (Constant.QuxiaoGUANLI.equals(tag)) {//取消管理
            String userId = event.getMsg();
            sendPeerMessage(userId, Constant.nfgk184grdgdfggdfghfhrthmkQuxiaoiguanliyuan);
        } else if (Constant.YINYUEZANTING.equals(tag)) {//暂停音乐
            String msg = event.getMsg();
            musicPosition = Integer.valueOf(msg);
            imgStop.setSelected(false);
            mRtcEngine.pauseAudioMixing();
        } else if (Constant.YINYUESHUAXIN.equals(tag)) {//添加音乐
            LogUtils.debugInfo("音乐列表改变===添加了====");
            listLocal = RoomHelper.getLocalMusic();
        } else if (Constant.YINYUEBOFANG.equals(tag)) {//播放音乐
            String msg = event.getMsg();
//            musicPosition = Integer.valueOf(msg);
            try {
                listLocal = LitePal.findAll(LocalMusicInfo.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (listLocal.size() > 0) {
                int audioMixingCurrentPosition = mRtcEngine.getAudioMixingCurrentPosition();
                if (audioMixingCurrentPosition != 0) {
                    if (musicPosition == Integer.valueOf(msg)) {
                        mRtcEngine.resumeAudioMixing();
                    } else {
                        musicPosition = Integer.valueOf(msg);
                        if (musicPosition < 0 || musicPosition > listLocal.size() - 1) {
                            musicPosition = 0;
                        }
                        textMusicName.setText(listLocal.get(musicPosition).name);
                        mRtcEngine.startAudioMixing(listLocal.get(musicPosition).songUrl, false, false, 1);
                    }
                } else {
                    textMusicName.setText(listLocal.get(musicPosition).name);
                    mRtcEngine.startAudioMixing(listLocal.get(musicPosition).songUrl, false, false, 1);
                }
                imgStop.setSelected(true);
            } else {
                toast("请去音乐库添加至我的音乐！");
            }
        } else if (Constant.DIANJIBIAOQING.equals(tag)) {//点击表情
            String msg = event.getMsg();
            rlEmoji.setVisibility(View.GONE);
            loadGifEmoji(msg);
        } else if (Constant.FASONGMAIXULIWU.equals(tag)) {//发送礼物

            MessageBean messageBean = event.getMessageBean();
            messageBean.nick_color = vipBean.getData().getNick_color();
            LogUtils.debugInfo("====发送礼物消息：" + JSON.toJSONString(messageBean));
            sendChannelMessage(JSON.toJSONString(messageBean));
            if (enterRoom != null && enterRoom.getRoom_info() != null && enterRoom.getRoom_info().size() > 0 && enterRoom.getRoom_info().get(0).play_num.equals("1")) {//开启数值PK情况下要刷新麦序，
                sendChannelMessage(BaseUtils.getJson("3", "", "", ""));
                loadVedioList();
            }
            check_gap();//刷新房间排名
            FirstEvent firstEvent = new FirstEvent("send_gift", "send_gift");//通知个人中心刷新数据
            EventBus.getDefault().post(firstEvent);


            List<MessageBean.Data> receiveUserList = messageBean.userInfo;
            if (receiveUserList.size() == 1) {
                LogUtils.debugInfo("====mingcheng:" + receiveUserList.get(0).nickname);
                messageBean.nick_color = vipBean.getData().getNick_color();
                RoomHelper.refreshAdapter(messageBean, recyclerView, roomMessageAdapter);
            } else {
                LoginData loginData = UserManager.getUser();
                String userId = loginData.getUserId() + "";
                String userNick = loginData.getNickname();
                String nickColor = vipBean.getData().getNick_color();
                for (MessageBean.Data itemUser : receiveUserList) {
                    MessageBean newMessage = new MessageBean();
                    newMessage.setUser_id(userId);
                    newMessage.setNickName(userNick);
                    newMessage.nick_color = nickColor;
                    newMessage.show_img = messageBean.show_img;
                    newMessage.show_gif_img = messageBean.show_gif_img;
                    newMessage.type = messageBean.type;
                    newMessage.giftNum = messageBean.giftNum;
                    newMessage.e_name = messageBean.e_name;
                    newMessage.setMessageType("4");
                    List<MessageBean.Data> dataList = new ArrayList<>();
                    dataList.add(itemUser);
                    newMessage.userInfo = dataList;
                    roomMessageAdapter.getData().add(newMessage);
                }
                roomMessageAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(roomMessageAdapter.getData().size());
                if (receiveUserList.size() >= 8) {//显示全服通知
                    PushBean pushBean = new PushBean();
                    pushBean.setType("quanmai");
                    PushBean.DataBean dataBean = new PushBean.DataBean();
                    dataBean.setBoxclass(enterRoom.getRoom_info().get(0).getRoom_name());
                    dataBean.setGift_name(messageBean.name);
                    dataBean.setUser_name(messageBean.getNickName());
                    dataBean.setNum(Integer.parseInt(messageBean.giftNum));
                    pushBean.setData(dataBean);
                    mMiniBarrageViewLayout.setData(pushBean);
                }

            }

            List<PushBean.DataBean> pushList = messageBean.pushUser;
            if (pushList != null && pushList.size() > 0) {//有大礼物播报


                BigGiftBean bigGiftBean = new BigGiftBean();
                bigGiftBean.setMessageType("20");
                bigGiftBean.setUserInfo(pushList);

//                JsonObject rootObj = new JsonObject();
//
//                rootObj.addProperty("messageType", "20");
//
//                rootObj.addProperty("userInfo", JSON.toJSONString(pushList));

                for (int i = 0; i < pushList.size(); i++) {
                    PushBean pushBean = new PushBean();
                    pushBean.setType("gift");
                    PushBean.DataBean dataBean = pushList.get(i);
                    pushBean.setData(dataBean);
                    mMiniBarrageViewLayout.setData(pushBean);
                }

                sendChannelMessage_2(JSON.toJSONString(bigGiftBean));

            }

            if (TextUtils.equals(messageBean.type, "2")) {//全屏
                SVGAParser parser = new SVGAParser(this);
                showServerSVG(parser, messageBean.show_gif_img, svgImage);
                //确保礼物弹窗关闭
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setStateMessage(StateMessage.CLOSE_GIFT_WINDOW);
                EventBus.getDefault().post(messageEvent);
            }
//            else if (TextUtils.equals(messageBean.type, "1")) {//头像动画
//                setFlyAnimate(messageBean);
////                loadAniData(messageBean.userInfo, messageBean.show_img);
//            }
            setFlyAnimate(messageBean);
        } else if (Constant.XIUGAIGERENZILIAOCHENGGONG.equals(tag)) {//修改个人资料刷新名称信息
            if (user_type == 1) {
                loadEnterRoom();
            } else {
                sendChannelMessage(BaseUtils.getJson("3", "", "", ""));
                loadVedioList();
            }
        } else if (Constant.TUISONG.equals(tag)) {//推送消息，显示布局
//            PushBean pushBean = event.getPushBean();
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    if (!mIsPushRuning) {
//                        mMiniBarrageViewLayout.setData(pushBean);
//                    }
//                }
//            });

        } else if (Constant.KBXTUISONG.equals(tag)) { //开宝箱推送消息，显示布局
//            PushBean pushBean = event.getPushBean();
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    if (!mIsPushRuning) {
//                        mMiniBarrageViewLayout.setData(pushBean);
//                    }
//                }
//            });

        } else if (Constant.COUNT_PK.equals(tag)) { //数值PK开关
            RxUtils.loading(commonModel.enter_room(uid, room_pass,
                    String.valueOf(UserManager.getUser().getUserId())), this)
                    .subscribe(new ErrorHandleSubscriber<EnterRoom>(mErrorHandler) {
                        @Override
                        public void onNext(EnterRoom menterRoom) {
                            enterRoom = menterRoom;

                            if (enterRoom == null || enterRoom.getRoom_info() == null || enterRoom.getRoom_info().size() == 0) {
                                return;
                            }

                            MessageBean messageBean = new MessageBean();
                            messageBean.setMessageType("14");
                            if (enterRoom.getRoom_info().get(0).play_num.equals("1")) {//开启了
                                messageBean.setMessage("打开");
                            } else {
                                messageBean.setMessage("关闭");
                            }
                            LoginData loginData = UserManager.getUser();
                            String nickColor = vipBean.getData().getNick_color();
                            String nickName = loginData.getNickname();
                            String userId = String.valueOf(loginData.getUserId());
                            messageBean.setNickName(nickName);
                            messageBean.setUser_id(userId);
                            messageBean.nick_color = nickColor;
                            String str = JSON.toJSONString(messageBean);
                            //发送广播通知
                            sendChannelMessage(str);

                            loadVedioList();
                            RoomHelper.refreshAdapter(messageBean, recyclerView, roomMessageAdapter);

                        }
                    });

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(MessageEvent event) {

        StateMessage stateMessage = event.getStateMessage();

        if (stateMessage.getState() == StateMessage.SEND_GEMSTONE.getState()) {//发送宝石

            Object[] objects = (Object[]) event.getObject();

            MessageBean messageBean = (MessageBean) objects[0];

            SendGemResult sendGemResult = (SendGemResult) objects[1];

            //根据返回的数据，判断是有不是第一次 送宝石的，只有第一次送宝石对方才会显示结为CP的对话框

            List<SendGemResult.DataBean> sendDataList = sendGemResult.getData();

            for (SendGemResult.DataBean dataItem : sendDataList) {

                if (dataItem.getIs_first() == 1) {//第一次发送

                    JsonObject rootObj = new JsonObject();

                    LoginData loginData = UserManager.getUser();

                    rootObj.addProperty("nickName", loginData.getNickname());

                    rootObj.addProperty("user_id", loginData.getUserId());

                    rootObj.addProperty("nick_color", vipBean.getData().getNick_color());

                    rootObj.addProperty("messageType", "2");

                    rootObj.addProperty("headimgurl", loginData.getHeadimgurl());

                    String str = rootObj.toString();

//                    Log.e("第一次发送宝石==", str);

                    sendPeerMessage(dataItem.getUserId(), str);

                } else {//不是第一次发送宝石，就发送频道消息

                    messageBean.nick_color = vipBean.getData().getNick_color();

                    sendChannelMessage(JSON.toJSONString(messageBean));

                }

            }

            LogUtils.debugInfo("====发送宝石消息：" + JSON.toJSONString(messageBean));
//            sendChannelMessage(JSON.toJSONString(messageBean));

            FirstEvent firstEvent = new FirstEvent("send_gift", "send_gift");//通知个人中心刷新数据
            EventBus.getDefault().post(firstEvent);

            if (enterRoom != null && enterRoom.getRoom_info() != null && enterRoom.getRoom_info().size() > 0 && enterRoom.getRoom_info().get(0).play_num.equals("1")) {//开启数值PK情况下要刷新麦序，
                sendChannelMessage(BaseUtils.getJson("3", "", "", ""));
                loadVedioList();
            }

            List<MessageBean.Data> userInfo = messageBean.userInfo;
            if (userInfo.size() == 1) {
                LogUtils.debugInfo("====单个人:" + userInfo.get(0).nickname);
                messageBean.nick_color = vipBean.getData().getNick_color();
                RoomHelper.refreshAdapter(messageBean, recyclerView, roomMessageAdapter);
            } else {
                LoginData loginData = UserManager.getUser();
                String nickColor = vipBean.getData().getNick_color();
                String nickName = loginData.getNickname();
                String userId = String.valueOf(loginData.getUserId());
                for (MessageBean.Data list : userInfo) {
                    MessageBean newMessage = new MessageBean();
                    newMessage.setUser_id(userId);
                    newMessage.setNickName(nickName);
                    newMessage.nick_color = nickColor;
                    newMessage.show_img = messageBean.show_img;
                    newMessage.show_gif_img = messageBean.show_gif_img;
                    newMessage.type = messageBean.type;
                    newMessage.giftNum = messageBean.giftNum;
                    newMessage.e_name = messageBean.e_name;
                    newMessage.setMessageType("4");
                    List<MessageBean.Data> dataList = new ArrayList<>();
                    dataList.add(list);
                    newMessage.userInfo = dataList;
                    roomMessageAdapter.getData().add(newMessage);
                }
                roomMessageAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(roomMessageAdapter.getData().size());
            }
            if (TextUtils.equals(messageBean.type, "2")) {//全屏
                SVGAParser parser = new SVGAParser(this);
                showServerSVG(parser, messageBean.show_gif_img, svgImage);
            }
//            else if (TextUtils.equals(messageBean.type, "1")) {//头像动画
////                setFlyAnimate(messageBean);
//                loadAniData(messageBean.userInfo, messageBean.show_img);
//            }
            loadAniData(messageBean.userInfo, messageBean.show_img);
        } else if (stateMessage.getState() == StateMessage.PEOPLE_OPEN_GEMSTONE.getState()) {//开宝箱的消息，类型13
            MessageBean messageBean = (MessageBean) event.getObject();
            if (!TextUtils.isEmpty(messageBean.getMessage()) || (messageBean.awardList != null && messageBean.awardList.size() > 0)) {
                if (messageBean.awardList != null && messageBean.awardList.size() > 0) {
                    List<OpenBoxBean.DataBean.AwardListBean> awardListBeans = messageBean.awardList;
                    for (int i = 0; i < awardListBeans.size(); i++) {

                        double price = Arith.strToDouble(awardListBeans.get(i).getPrice());
                        if (price >= 20) {
                            MessageBean messageBean1 = messageBean;
                            List<OpenBoxBean.DataBean.AwardListBean> list = new ArrayList<>();
                            list.add(awardListBeans.get(i));
                            messageBean1.awardList = list;
//                            messageBean1.setMessage("");
                            RoomHelper.refreshAdapter(JSON.parseObject(JSON.toJSONString(messageBean1), MessageBean.class), recyclerView, roomMessageAdapter);
                            //发送频道消息
                            sendChannelMessage(JSON.toJSONString(messageBean1));
                            if (messageBean1.push_awards != null && !TextUtils.isEmpty(messageBean1.push_awards.gift_name)) {//全服通知
                                messageBean1.push_awards.gift_name = awardListBeans.get(i).getName() + "x" + awardListBeans.get(i).getNum();
                                MessageBean msgNew = new MessageBean();
                                msgNew.box_class = messageBean1.box_class;
                                msgNew.setNickName(messageBean1.getNickName());
                                msgNew.setMessage(messageBean1.getMessage());
                                msgNew.push_awards = messageBean1.push_awards;
                                msgNew.setMessageType("21");
                                sendChannelMessage_2(JSON.toJSONString(msgNew));
                                //自己的通知
                                PushBean pushBean = new PushBean();
                                pushBean.setType("award");
                                PushBean.DataBean dataBean = new PushBean.DataBean();
                                dataBean.setBoxclass(messageBean1.box_class);
                                dataBean.setUser_name(messageBean1.push_awards.user_name);
                                dataBean.setGift_name(messageBean1.push_awards.gift_name);
                                pushBean.setData(dataBean);
                                mMiniBarrageViewLayout.setData(pushBean);
                                LogUtils.debugInfo("开宝箱type====" + messageBean1.getMessageType());
                            }
                        }
                    }
                } else {
                    RoomHelper.refreshAdapter(messageBean, recyclerView, roomMessageAdapter);
                    //发送频道消息
                    sendChannelMessage(JSON.toJSONString(messageBean));
                    if (messageBean.push_awards != null && !TextUtils.isEmpty(messageBean.push_awards.gift_name)) {//全服通知
                        MessageBean msgNew = new MessageBean();
                        msgNew.box_class = messageBean.box_class;
                        msgNew.setNickName(messageBean.getNickName());
                        msgNew.setMessage(messageBean.getMessage());
                        msgNew.push_awards = messageBean.push_awards;
                        msgNew.setMessageType("21");
                        sendChannelMessage_2(JSON.toJSONString(msgNew));
                        //自己的通知
                        PushBean pushBean = new PushBean();
                        pushBean.setType("award");
                        PushBean.DataBean dataBean = new PushBean.DataBean();
                        dataBean.setBoxclass(messageBean.box_class);
                        dataBean.setUser_name(messageBean.push_awards.user_name);
                        dataBean.setGift_name(messageBean.push_awards.gift_name);
                        pushBean.setData(dataBean);
                        mMiniBarrageViewLayout.setData(pushBean);
                        LogUtils.debugInfo("开宝箱type====" + messageBean.getMessageType());
                    }
                }

            }

        } else if (stateMessage.getState() == StateMessage.MUSIC_CHANGE.getState()) {//音乐列表改变
            LogUtils.debugInfo("音乐列表改变了=======");
            listLocal = RoomHelper.getLocalMusic();
        } else if (stateMessage.getState() == StateMessage.END_CALL.getState()) {//挂断电话
//            Log.e("收到电话状态改变了====", "开启");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isMeCloseMai();
                }
            }, 700);

        } else if (stateMessage.getState() == StateMessage.START_PAIDAN.getState()) {//开始派单

            MessageBean messageBean = new MessageBean();
            messageBean.setMessageType("19");
            messageBean.setMessage("1");

            //开始计时
            tvPaiDanTimer.setBase(SystemClock.elapsedRealtime());
            tvPaiDanTimer.setFormat("派单 %s");
            tvPaiDanTimer.start();
            mIsCuntDownPaiDan = true;

            sendChannelMessage(JSON.toJSONString(messageBean));

        } else if (stateMessage.getState() == StateMessage.END_PAIDAN.getState()) {//结束派单
            MessageBean messageBean = new MessageBean();
            messageBean.setMessageType("19");
            messageBean.setMessage("0");

            //停止计时
            tvPaiDanTimer.setBase(SystemClock.elapsedRealtime());
            tvPaiDanTimer.stop();
            tvPaiDanTimer.setText("派单 00:00");
            mIsCuntDownPaiDan = false;

            sendChannelMessage(JSON.toJSONString(messageBean));
        }

    }

//    boolean mIsPushRuning = true;
//
//    CountTimeUtils mCountTimeUtils = new CountTimeUtils(10 * 6 * 10) {
//        @Override
//        public void onTick(long millisUntilFinished) {
//
//        }
//
//        @Override
//        public void onFinish() {
//            mIsPushRuning = false;
//        }
//    };

    private void isMeCloseMai() {
        if (!isEditBimai) {
            if (imgBimai.isSelected()) {
                mRtcEngine.enableLocalAudio(false);
//                Log.e("麦关闭====", "开启555");
                imgBimai.setSelected(true);
            } else {
                mRtcEngine.enableLocalAudio(true);
//                Log.e("麦开启====", "开启");
                imgBimai.setSelected(false);
            }
        } else {
            toast("已经被管理员闭麦！");
        }
    }

    /**
     * 发送礼物飞的动画
     */
    private void setFlyAnimate(MessageBean messageBean) {

        int[] location = messageBean.location;
        if (mGiftFlyDialog == null) {
            mGiftFlyDialog = new GiftFlyDialog(this, R.layout.pop_gift_fly, feiLeft, feiTop, location);
        }
        if (!mGiftFlyDialog.isShowing()) {
            mGiftFlyDialog.showAsDropDown(imgMessage);
        }
        mGiftFlyDialog.setStartLocation(location);

        List<MessageBean.Data> userInfo = messageBean.userInfo;

        String imgUrl = messageBean.show_img;

        for (MessageBean.Data list : userInfo) {
            for (Microphone.DataBean.MicrophoneBean listPhone : mMicrophone) {
                if (TextUtils.equals(list.userId, listPhone.getUser_id())) {
                    LogUtils.debugInfo("====sgm礼物位置：" + list.userId);
                    int i = mMicrophone.indexOf(listPhone);
                    switch (i) {
                        case 0:
                            mGiftFlyDialog.startImageFly(img1, imgUrl);
                            break;
                        case 1:
                            mGiftFlyDialog.startImageFly(img2, imgUrl);
                            break;
                        case 2:
                            mGiftFlyDialog.startImageFly(img3, imgUrl);
                            break;
                        case 3:
                            mGiftFlyDialog.startImageFly(img4, imgUrl);
                            break;
                        case 4:
                            mGiftFlyDialog.startImageFly(img5, imgUrl);
                            break;
                        case 5:
                            mGiftFlyDialog.startImageFly(img6, imgUrl);
                            break;
                        case 6:
                            mGiftFlyDialog.startImageFly(img7, imgUrl);
                            break;
                        case 7:
                            mGiftFlyDialog.startImageFly(imgLast, imgUrl);
                            break;
                        case 8:
                            mGiftFlyDialog.startImageFly(img8, imgUrl, true);
                            break;
                    }
                }
            }
        }

    }

    /**
     * 计算飞的距离
     */
    private void loadAniData(List<MessageBean.Data> userInfo, String imgUrl) {
        for (MessageBean.Data list : userInfo) {
            for (Microphone.DataBean.MicrophoneBean listPhone : mMicrophone) {
                if (TextUtils.equals(list.userId, listPhone.getUser_id())) {
                    LogUtils.debugInfo("====sgm礼物位置：" + list.userId);
                    int i = mMicrophone.indexOf(listPhone);
                    switch (i) {
                        case 0:
                            RoomHelper.setImageFei(this, layoutRoot, img1, imgFei1, imgUrl, feiLeft, feiTop, false);
                            break;
                        case 1:
                            RoomHelper.setImageFei(this, layoutRoot, img2, imgFei2, imgUrl, feiLeft, feiTop, false);
                            break;
                        case 2:
                            RoomHelper.setImageFei(this, layoutRoot, img3, imgFei3, imgUrl, feiLeft, feiTop, false);
                            break;
                        case 3:
                            RoomHelper.setImageFei(this, layoutRoot, img4, imgFei4, imgUrl, feiLeft, feiTop, false);
                            break;
                        case 4:
                            RoomHelper.setImageFei(this, layoutRoot, img5, imgFei5, imgUrl, feiLeft, feiTop, false);
                            break;
                        case 5:
                            RoomHelper.setImageFei(this, layoutRoot, img6, imgFei6, imgUrl, feiLeft, feiTop, false);
                            break;
                        case 6:
                            RoomHelper.setImageFei(this, layoutRoot, img7, imgFei7, imgUrl, feiLeft, feiTop, false);
                            break;
                        case 7:
                            RoomHelper.setImageFei(this, layoutRoot, imgLast, imgFeiLast, imgUrl, feiLeft, feiTop, false);
                            break;
                        case 8:
                            RoomHelper.setImageFei(this, layoutRoot, img8, imgFei8, imgUrl, feiLeft, feiTop, true);
                            break;
                    }
                }
            }
        }
    }

    /**
     * 动态表情
     */
    private void loadGifEmoji(String id) {
        RxUtils.loading(commonModel.get_emoji(id), this)
                .subscribe(new ErrorHandleSubscriber<GifBean>(mErrorHandler) {
                    @Override
                    public void onNext(GifBean gifBean) {
                        String emoji = gifBean.getData().get(0).getEmoji();
                        LoginData loginData = UserManager.getUser();
                        if (vipBean != null && vipBean.getData() != null) {
                            sendChannelMessage(BaseUtils.getJson("5",
                                    "使用道具结果为：" + gifBean.getData().get(0).getName(),
                                    loginData.getNickname(), loginData.getUserId() + "",
                                    gifBean.getData().get(0).getIs_answer(),
                                    gifBean.getData().get(0).getEmoji(), gifBean.getData().get(0).getT_length(), loginData.getHeadimgurl(),
                                    vipBean.getData().getVip_img(), vipBean.getData().getHz_img(), vipBean.getData().getNick_color()));
                        } else {
                            sendChannelMessage(BaseUtils.getJson("5",
                                    "使用道具结果为：" + gifBean.getData().get(0).getName(),
                                    loginData.getNickname(), loginData.getUserId() + "",
                                    gifBean.getData().get(0).getIs_answer(),
                                    gifBean.getData().get(0).getEmoji(), gifBean.getData().get(0).getT_length(), loginData.getHeadimgurl(),
                                    "", "", ""));
                        }
                        int maiPosition = 0;//麦序位置
                        for (Microphone.DataBean.MicrophoneBean list : mMicrophone) {
                            if (TextUtils.equals(list.getUser_id(),
                                    String.valueOf(loginData.getUserId()))) {
                                maiPosition = mMicrophone.indexOf(list);
                            }
                        }
                        maiPosition = maiPosition + 1;
                        switch (maiPosition) {
                            case 1:
                                RoomHelper.loadGifShow(AdminHomeActivity.this, imgGif1, emoji);
                                break;
                            case 2:
                                RoomHelper.loadGifShow(AdminHomeActivity.this, imgGif2, emoji);
                                break;
                            case 3:
                                RoomHelper.loadGifShow(AdminHomeActivity.this, imgGif3, emoji);
                                break;
                            case 4:
                                RoomHelper.loadGifShow(AdminHomeActivity.this, imgGif4, emoji);
                                break;
                            case 5:
                                RoomHelper.loadGifShow(AdminHomeActivity.this, imgGif5, emoji);
                                break;
                            case 6:
                                RoomHelper.loadGifShow(AdminHomeActivity.this, imgGif6, emoji);
                                break;
                            case 7:
                                RoomHelper.loadGifShow(AdminHomeActivity.this, imgGif7, emoji);
                                break;
                            case 8:
                                RoomHelper.loadGifShow(AdminHomeActivity.this, imgGiftLast, emoji);
                                break;
                            case 9:
                                RoomHelper.loadGifShow(AdminHomeActivity.this, imgGif8, emoji);
                                break;
                        }

                        if (!TextUtils.equals(gifBean.getData().get(0).getIs_answer(), "0")) {//代表表情有结果
                            String json = "";
                            if (vipBean != null && vipBean.getData() != null) {
                                json = BaseUtils.getJson("5", "使用道具结果为：" + gifBean.getData().get(0).getName(),
                                        loginData.getNickname(), loginData.getUserId() + "",
                                        gifBean.getData().get(0).getIs_answer(),
                                        gifBean.getData().get(0).getEmoji(),
                                        gifBean.getData().get(0).getT_length(), loginData.getHeadimgurl(),
                                        vipBean.getData().getVip_img(), vipBean.getData().getHz_img(), vipBean.getData().getNick_color());
                            } else {
                                json = BaseUtils.getJson("5", "使用道具结果为：" + gifBean.getData().get(0).getName(),
                                        loginData.getNickname(), loginData.getUserId() + "",
                                        gifBean.getData().get(0).getIs_answer(),
                                        gifBean.getData().get(0).getEmoji(),
                                        gifBean.getData().get(0).getT_length(), loginData.getHeadimgurl(),
                                        "", "", "");
                            }
                            RoomHelper.refreshAdapter(BaseUtils.getMessageBean(json), recyclerView, roomMessageAdapter);
                        }
                    }
                });
    }

    /**
     * 获取日榜差额
     */
    private void check_gap() {
        RxUtils.loading(commonModel.check_gap(uid), this)
                .subscribe(new ErrorHandleSubscriber<GetGapResult>(mErrorHandler) {
                    @Override
                    public void onNext(GetGapResult gapResult) {
                        if (gapResult != null && gapResult.getData() != null) {
                            String gap = gapResult.getData().getGap();
                            String hot = gapResult.getData().getHot();
                            String exp = gapResult.getData().getExp();
                            if (!TextUtils.isEmpty(gap)) {
                                MessageBean messageBean = new MessageBean();
                                messageBean.setMessageType("15");
                                messageBean.setMessage(gap);
                                messageBean.exp = exp;
                                messageBean.hot = hot;
                                //发送频道消息
                                sendChannelMessage(JSON.toJSONString(messageBean));

                                setRoomPaiHang(gap, exp, hot);
                            }
                        }
                    }
                });
    }

    /**
     * 广播用户,厅主修改房间设置
     */
    private void loadEnterRoom() {
        RxUtils.loading(commonModel.enter_room(uid, room_pass,
                String.valueOf(UserManager.getUser().getUserId())), this)
                .subscribe(new ErrorHandleSubscriber<EnterRoom>(mErrorHandler) {
                    @Override
                    public void onNext(EnterRoom menterRoom) {
                        enterRoom = menterRoom;
                        boolean freeMic = menterRoom.getRoom_info().get(0).free_mic.equals("1") ? true : false;
                        boolean isPaiDan = enterRoom.getRoom_info().get(0).getRoom_type().equals("15") ? true : false;
                        boolean isLoadVedioList = false;
                        if ((mIsFreeMic && !freeMic) || (!mIsFreeMic && freeMic)) {//说明修改了麦位开启或者关闭
                            if (freeMic) {
                                mIsPaiDan = isPaiDan;
                                mIsFreeMic = freeMic;
                                isLoadVedioList = true;
                                loadVedioList();
                            }
                        }
                        if (!isLoadVedioList && ((mIsPaiDan && !isPaiDan) || (!mIsPaiDan && isPaiDan))) {
                            mIsPaiDan = isPaiDan;
                            loadVedioList();
                        }
                        mIsPaiDan = isPaiDan;
                        mIsFreeMic = freeMic;
                        textName.setText(enterRoom.getRoom_info().get(0).getRoom_name());
                        textId.setText("ID:" + enterRoom.getRoom_info().get(0).getNumid());
                        textType.setText(enterRoom.getRoom_info().get(0).getName());
                        textNum.setText(String.valueOf(enterRoom.getRoom_info().get(0).hot));
                        loadImage(mImgRoomHead, enterRoom.getRoom_info().get(0).getRoom_cover(), R.mipmap.no_tou);
                        loadImage(imgBg, enterRoom.getRoom_info().get(0).back_img, R.mipmap.room_bg);
                    }
                });
    }


    class MyGestureDector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //e1:手指按下的移动事件.
            float e1X = e1.getRawX();
            float e1Y = e1.getRawY();
            // e2 : 手指移动的动作事件.
            float e2X = e2.getRawX();
            float e2Y = e2.getRawY();
            if (Math.abs(e2X - e1X) < 50) {
                LogUtils.debugInfo("左右滑动小于50px");
                return super.onFling(e1, e2, velocityX, velocityY);
            } else if (Math.abs(e2Y - e1Y) > 200) {
                LogUtils.debugInfo("手势上下滑动");
                return super.onFling(e1, e2, velocityX, velocityY);
            } else if (Math.abs(e2X - e1X) > 50) {
                // 判断是否左滑
                if ((e2X - e1X) > 0) {
                    LogUtils.debugInfo("右滑");
                    return super.onFling(e1, e2, velocityX, velocityY);

                    // 判断是否右滑
                } else {
//                    if (e1X > (width - 220)) {
                    LogUtils.debugInfo("左滑");
                    if (rlEmoji.getVisibility() == View.VISIBLE || llMusic.getVisibility() == View.VISIBLE) {
                        return true;
                    }
                    if (enterRoom == null || enterRoom.getRoom_info() == null || enterRoom.getRoom_info().size() == 0) {
                        return true;
                    }

                    String bg = enterRoom.getRoom_info().get(0).back_img;
                    if (TextUtils.isEmpty(bg)) {
                        bg = "";
                    }
                    Intent intent = new Intent(AdminHomeActivity.this, RoomUserOnlineActivity.class);
                    intent.putExtra("room_id", uid);
                    intent.putExtra("room_bg", bg);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                    mCanReture = true;
//                    }

                }
                return true;
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mGestureDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    //显示倒计时的Popop
    private void zhanshiPopup(OtherUser otherUser) {
        TimePopup timePopup = new TimePopup(mContext);
        timePopup.showAtLocation(img1, Gravity.BOTTOM, 0, 0);
        timePopup.getMyListView().setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    timePopup.dismiss();
                    setCountDown(1 * 60, String.valueOf(otherUser.getData().get(0).getId()));
                    break;
                case 1:
                    timePopup.dismiss();
                    setCountDown(2 * 60, String.valueOf(otherUser.getData().get(0).getId()));
                    break;
                case 2:
                    timePopup.dismiss();
                    setCountDown(3 * 60, String.valueOf(otherUser.getData().get(0).getId()));
                    break;
                case 3:
                    timePopup.dismiss();
                    setCountDown(4 * 60, String.valueOf(otherUser.getData().get(0).getId()));
                    break;
                case 4:
                    timePopup.dismiss();
                    setCountDown(5 * 60, String.valueOf(otherUser.getData().get(0).getId()));
                    break;
                case 5:
                    timePopup.dismiss();
                    setCountDown(6 * 60, String.valueOf(otherUser.getData().get(0).getId()));
                    break;
                case 6:
                    timePopup.dismiss();
                    setCountDown(7 * 60, String.valueOf(otherUser.getData().get(0).getId()));
                    break;
                case 7:
                    timePopup.dismiss();
                    setCountDown(8 * 60, String.valueOf(otherUser.getData().get(0).getId()));
                    break;
                case 8:
                    timePopup.dismiss();
                    setCountDown(9 * 60, String.valueOf(otherUser.getData().get(0).getId()));
                    break;
                case 9:
                    timePopup.dismiss();
                    setCountDown(10 * 60, String.valueOf(otherUser.getData().get(0).getId()));
                    break;
            }
        });
    }

    //开启倒计时
    private void setCountDown(int time, String maiWeiId) {
        RxUtils.loading(commonModel.openTime(uid, time, maiWeiId), this)
                .subscribe(new ErrorHandleSubscriber<OpenTimeBean>(mErrorHandler) {
                    @Override
                    public void onNext(OpenTimeBean openTimeBean) {
                        sendChannelMessage(BaseUtils.getJson("3", "", "", ""));
                        loadVedioList();
                    }
                });
    }

    //关闭倒计时
    private void closeTime(String maiWeiId) {
        RxUtils.loading(commonModel.colseTime(uid, maiWeiId), this)
                .subscribe(new ErrorHandleSubscriber<CommentBean>(mErrorHandler) {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        sendChannelMessage(BaseUtils.getJson("3", "", "", ""));
                        loadVedioList();
                    }
                });
    }
}
