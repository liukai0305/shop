package com.qutu.talk.service;

import com.qutu.talk.bean.ActiveListBean;
import com.qutu.talk.bean.AdminUser;
import com.qutu.talk.bean.AgreeCpResult;
import com.qutu.talk.bean.AgreementBean;
import com.qutu.talk.bean.AliInfor;
import com.qutu.talk.bean.AllCommentBean;
import com.qutu.talk.bean.BXShuoMingTextBean;
import com.qutu.talk.bean.BannerBean;
import com.qutu.talk.bean.BaoXiangBean;
import com.qutu.talk.bean.BaseBean;
import com.qutu.talk.bean.BestRoomResult;
import com.qutu.talk.bean.BindBean;
import com.qutu.talk.bean.BoxExchangeBean;
import com.qutu.talk.bean.BoxOpenRecordBean;
import com.qutu.talk.bean.CPDetailsBean;
import com.qutu.talk.bean.CashHis;
import com.qutu.talk.bean.CategorRoomBean;
import com.qutu.talk.bean.ChaMoneyBean;
import com.qutu.talk.bean.CodeBean;
import com.qutu.talk.bean.CollectionRoomListBean;
import com.qutu.talk.bean.CommentBean;
import com.qutu.talk.bean.ConstellationBean;
import com.qutu.talk.bean.DengJiBean;
import com.qutu.talk.bean.DynamicDetailsBean;
import com.qutu.talk.bean.DynamicSearchBean;
import com.qutu.talk.bean.EmojiBean;
import com.qutu.talk.bean.EnterRoom;
import com.qutu.talk.bean.FollowBean;
import com.qutu.talk.bean.GetGameListResult;
import com.qutu.talk.bean.GetGameSetResult;
import com.qutu.talk.bean.GetGapResult;
import com.qutu.talk.bean.GetOnlineUserResult;
import com.qutu.talk.bean.GetSortResult;
import com.qutu.talk.bean.GifBean;
import com.qutu.talk.bean.GiftListBean;
import com.qutu.talk.bean.GoodsList;
import com.qutu.talk.bean.GuanFangLianXiBean;
import com.qutu.talk.bean.HotBean;
import com.qutu.talk.bean.IncomeBean;
import com.qutu.talk.bean.IncomeFragmentBean;
import com.qutu.talk.bean.IncomeSumBean;
import com.qutu.talk.bean.JinSheng;
import com.qutu.talk.bean.KzkBean;
import com.qutu.talk.bean.Login;
import com.qutu.talk.bean.LookCommentBean;
import com.qutu.talk.bean.MeYiDuiBean;
import com.qutu.talk.bean.MessageYoBean;
import com.qutu.talk.bean.MiLiSZJiLuBean;
import com.qutu.talk.bean.Microphone;
import com.qutu.talk.bean.MiniOfficBean;
import com.qutu.talk.bean.MoneyBean;
import com.qutu.talk.bean.MusicYinxiao;
import com.qutu.talk.bean.MyGameSkillResult;
import com.qutu.talk.bean.MyPackBean;
import com.qutu.talk.bean.MyPersonalCebterBean;
import com.qutu.talk.bean.MyPersonalCebterTwoBean;
import com.qutu.talk.bean.OffiMessageBean;
import com.qutu.talk.bean.OpenBoxBean;
import com.qutu.talk.bean.OpenTimeBean;
import com.qutu.talk.bean.OtherBean;
import com.qutu.talk.bean.OtherUser;
import com.qutu.talk.bean.PayBean;
import com.qutu.talk.bean.Rank;
import com.qutu.talk.bean.RankExplainBean;
import com.qutu.talk.bean.RecommenRoomBean;
import com.qutu.talk.bean.RecommendLabelBean;
import com.qutu.talk.bean.RecommendedDynamicBean;
import com.qutu.talk.bean.Register;
import com.qutu.talk.bean.ReportBean;
import com.qutu.talk.bean.RoomBg;
import com.qutu.talk.bean.RoomListResult;
import com.qutu.talk.bean.RoomInfoBean;
import com.qutu.talk.bean.RoomRankBean;
import com.qutu.talk.bean.RoomRewardOneBean;
import com.qutu.talk.bean.RoomType;
import com.qutu.talk.bean.RoomTypeResult;
import com.qutu.talk.bean.RoomUsersBean;
import com.qutu.talk.bean.Search;
import com.qutu.talk.bean.SearchAdmin;
import com.qutu.talk.bean.SearchHis;
import com.qutu.talk.bean.SearchLabelBean;
import com.qutu.talk.bean.SegmentListResult;
import com.qutu.talk.bean.SendGemResult;
import com.qutu.talk.bean.StartLoftBean;
import com.qutu.talk.bean.TodayRecommBean;
import com.qutu.talk.bean.TopicBean;
import com.qutu.talk.bean.TopicDynamicBean;
import com.qutu.talk.bean.UnreadBean;
import com.qutu.talk.bean.UpVideoResult;
import com.qutu.talk.bean.UserBean;
import com.qutu.talk.bean.UserFriend;
import com.qutu.talk.bean.VipBean;
import com.qutu.talk.bean.VipCenterBean;
import com.qutu.talk.bean.WaitList;
import com.qutu.talk.bean.Wxmodel;
import com.qutu.talk.bean.XuYaoMiZuanBean;
import com.qutu.talk.bean.Yinxiao;
import com.qutu.talk.bean.dashen.ConfirmOrderSkillBean;
import com.qutu.talk.bean.dashen.DaShenZhuanShuBean;
import com.qutu.talk.bean.dashen.DuanWeiBean;
import com.qutu.talk.bean.dashen.GodCenterBean;
import com.qutu.talk.bean.dashen.GoodsMiLiListBean;
import com.qutu.talk.bean.dashen.JieDanRiQiBean;
import com.qutu.talk.bean.dashen.JieDanSetBean;
import com.qutu.talk.bean.dashen.MainHomePageBean;
import com.qutu.talk.bean.dashen.MainHomePageSkillBean;
import com.qutu.talk.bean.dashen.MiLiIncomeBean;
import com.qutu.talk.bean.dashen.PaiDanCenterBean;
import com.qutu.talk.bean.dashen.ScreenPriceBean;
import com.qutu.talk.bean.*;
import com.qutu.talk.bean.family.FamilyApplyBean;
import com.qutu.talk.bean.order.JudgeBindingZFBBean;
import com.qutu.talk.bean.order.Orderbean;
import com.qutu.talk.bean.task.ExchangeListBean;
import com.qutu.talk.bean.task.JBExchangeBean;
import com.qutu.talk.bean.task.NewbieTaskBean;
import com.qutu.talk.bean.task.SignInBean;
import com.qutu.talk.bean.task.SignInDisplayBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import static com.qutu.talk.app.Api.APP_DOMAIN;


/**
 * 接口
 */
public interface CommonService {

    /**
     * 注册
     */
    @POST("registered")
    @FormUrlEncoded
    Observable<Register> register(@Field("phone") String phone,
                                  @Field("sex") String sex,
                                  @Field("birthday") String birthday,
                                  @Field("pass") String pass,
                                  @Field("nickname") String nickname,
                                  @Field("headimgurl") String headimgurl,
                                  @Field("system") String system,
                                  @Field("channel") String channel,
                                  @Field("code") String code);

    /**
     * 登录
     */
    @POST(APP_DOMAIN + "login")
    @FormUrlEncoded
    Observable<Login> login(@Field("phone") String phone,
                            @Field("pass") String pass);

    /**
     * 验证码登录
     */
    @POST(APP_DOMAIN + "Index/phlogin")
    @FormUrlEncoded
    Observable<Login> loginByVerifyCode(@Field("account") String account,
                                        @Field("code") String code);

    /**
     * 验证码
     */
    @POST(APP_DOMAIN + "verification")
    @FormUrlEncoded
    Observable<CodeBean> verification(
            @Field("phone") String phone,
            @Field("type") String type
    );

    /**
     * 验证码
     */
    @POST(APP_DOMAIN + "Index/phonelogincode")
    @FormUrlEncoded
    Observable<VerifyReturn> getVerifyCode(
            @Field("phone") String phone
    );

    /**
     * 校验验证码
     */
    @POST(APP_DOMAIN + "is_verification")
    @FormUrlEncoded
    Observable<Register> is_verification(@Field("phone") String phone, @Field("code") String rand);

    /**
     * 获取房间推荐位的房间
     */
    @POST(APP_DOMAIN + "room_recommend_room")
    @FormUrlEncoded
    Observable<RecommenRoomBean> getrecommendroom(@Field("categories") int categories,
                                                  @Field("page") int page);

    /**
     * 获取房间推荐位类别
     */
    @POST(APP_DOMAIN + "room_recommend_categories")
    Observable<CategorRoomBean> room_categories();

    /**
     * 获取热门房间
     */
    @POST(APP_DOMAIN + "is_popular")
    Observable<RecommenRoomBean> is_popular();

    /**
     * 获取密聊推荐房间
     */
    @POST(APP_DOMAIN + "secret_chat")
    Observable<RecommenRoomBean> secret_chat();

    /**
     * 微星阁
     */
    @POST(APP_DOMAIN + "star_loft")
    @FormUrlEncoded
    Observable<StartLoftBean> star_loft(@Field("sex") int sex);

    /**
     * 轮播图
     */
    @POST(APP_DOMAIN + "carousel")
    @FormUrlEncoded
    Observable<BannerBean> carousel(@Field("type") String type);

    /**
     * 创建编辑房间
     */
    @POST(APP_DOMAIN + "edit_room_three")
    @FormUrlEncoded
    Observable<Register> create_or_edit_room(
            @Field("room_pass") String room_pass,
            @Field("room_name") String room_name,
            @Field("room_class") String room_class,
            @Field("room_type") String room_type,
            @Field("room_intro") String room_intro,
            @Field("room_background") String room_background,
            @Field("uid") String uid,
            @Field("cover") String room_cover,
            @Field("free_mic") String free_mic);

    /**
     * 获取房间类型
     */
    @POST(APP_DOMAIN + "room_type")
    @FormUrlEncoded
    Observable<RoomType> room_type(@Field("xx") String xx);

    /**
     * 获取房间背景图*
     */
    @POST(APP_DOMAIN + "room_background")
    @FormUrlEncoded
    Observable<RoomBg> room_background(@Field("xx") String xx);

    /**
     * 获取礼物列表*
     */
    @POST(APP_DOMAIN + "gift_list")
    @FormUrlEncoded
    Observable<GiftListBean> gift_list(@Field("user_id") String user_id);

    /**
     * 进入房间*
     */
    @POST(APP_DOMAIN + "enter_room")
    @FormUrlEncoded
    Observable<EnterRoom> enter_room(
            @Field("uid") String uid,
            @Field("room_pass") String room_pass,
            @Field("user_id") String user_id
    );

    /**
     * 获取麦的信息
     */
    @POST(APP_DOMAIN + "microphone_status")
    @FormUrlEncoded
    Observable<Microphone> microphone_status(
            @Field("uid") String uid);

    /**
     * user_id 主持或房主的用户id
     * roomid 房间id
     * b_user_id  被清除人的用户id
     * 清除魅力值接口
     */
    @GET(APP_DOMAIN + "Index/qcml")
    Observable<BaseBean> clearMl(
            @Query("user_id") String user_id,
            @Query("roomid") String roomid,
            @Query("b_user_id") String b_user_id
    );

    /**
     查询清除魅力值记录
     user_id 主持或房主的用户id
     roomid 房间uid
     查看清除魅力值记录接口
     */
    @GET(APP_DOMAIN + "Index/cxqcml")
    Observable<ClearMlHistory> getClearMlHistory(
            @Query("user_id") String user_id,
            @Query("roomid") String roomid
    );

    /**
     * 上麦
     */
    @POST(APP_DOMAIN + "up_microphone")
    @FormUrlEncoded
    Observable<UpVideoResult> up_microphone(
            @Field("uid") String uid, @Field("user_id") String user_id, @Field("position") String position, @Field("phase") String phase);

    /**
     * 下麦
     */
    @POST(APP_DOMAIN + "go_microphone")
    @FormUrlEncoded
    Observable<BaseBean> go_microphone(
            @Field("uid") String uid, @Field("user_id") String user_id);

    /**
     * 上传音乐*
     */
    @POST(APP_DOMAIN + "upload_music")
    @FormUrlEncoded
    Observable<BaseBean> upload_music(
            @Field("singer") String singer,
            @Field("music") String music,
            @Field("music_name") String music_name,
            @Field("upload_name") String upload_name);

    /**
     * 动态详情*
     */
    @POST(APP_DOMAIN + "dynamic_details")
    @FormUrlEncoded
    Observable<DynamicDetailsBean> dynamic_details(
            @Field("user_id") String user_id,
            @Field("sort") String sort,
            @Field("page") String page,
            @Field("id") String id);

    /**
     * 发布动态*
     */
    @POST(APP_DOMAIN + "send_dynamic")
    @Multipart
    Observable<BaseBean> send_dynamic(
            @Part("user_id") int user_id,
            @QueryMap Map<String, Object> op,
            @Part MultipartBody.Part audioFile,
            @Part MultipartBody.Part videoFile,
            @Part MultipartBody.Part img1File,
            @Part MultipartBody.Part img2File,
            @Part MultipartBody.Part img3File,
            @Part MultipartBody.Part img4File,
            @Part MultipartBody.Part img5File,
            @Part MultipartBody.Part img6File);

    /**
     * 发评论*
     */
    @POST(APP_DOMAIN + "dynamic_comment")
    @FormUrlEncoded
    Observable<BaseBean> dynamic_comment(
            @Field("b_dynamic_id") int b_dynamic_id,
            @Field("user_id") int user_id,
            @Field("content") String content);

    /**
     * 收藏动态*
     */
    @POST(APP_DOMAIN + "dynamic_collection")
    @FormUrlEncoded
    Observable<BaseBean> dynamic_collection(
            @Field("b_dynamic_id") int b_dynamic_id,
            @Field("user_id") int user_id);

    /**
     * 社区动态搜索*
     */
    @POST(APP_DOMAIN + "dynamic_search")
    @FormUrlEncoded
    Observable<DynamicSearchBean> dynamic_search(
            @Field("search") int search);

    /**
     * 获取推荐动态*
     */
    @POST(APP_DOMAIN + "dynamicTjList")
    @FormUrlEncoded
    Observable<RecommendedDynamicBean> recommended_dynamic(
            @Field("user_id") String userId,
            @Field("page") String page);

    /**
     * 获取最新动态*
     */
    @POST(APP_DOMAIN + "dynamicNewList")
    @FormUrlEncoded
    Observable<RecommendedDynamicBean> new_dynamic(
            @Field("user_id") String user_id,
            @Field("page") String page);

    /**
     * 获取热门话题*
     */
    @POST(APP_DOMAIN + "topic")
    @FormUrlEncoded
    Observable<TopicBean> topic(@Field("type") String type);

    /**
     * 获取话题内动态*
     */
    @POST(APP_DOMAIN + "topic_dynamic")
    @FormUrlEncoded
    Observable<TopicDynamicBean> topic_dynamic(
            @Field("tags") String tags,
            @Field("user_id") String user_id,
            @Field("page") String page,
            @Field("type") String type);

    /**
     * 赞动态*
     */
    @POST(APP_DOMAIN + "dynamics_hand")
    @FormUrlEncoded
    Observable<BaseBean> dynamic_praise(
            @Field("user_id") String user_id,
            @Field("target_id") String target_id,
            @Field("type") String type,
            @Field("hand") String hand);


    /**
     * 赞评论*
     */
    @POST(APP_DOMAIN + "comment_praise")
    @FormUrlEncoded
    Observable<BaseBean> comment_praise(
            @Field("b_dynamic_id") int b_dynamic_id,
            @Field("user_id") int user_id);


    /**
     * 获取置顶位置的房间
     */
    @POST(APP_DOMAIN + "is_top")
    @FormUrlEncoded
    Observable<RecommenRoomBean> is_top(@Field("xs") String xx);

    /**
     * 获取置顶位置的房间
     */
    @POST(APP_DOMAIN + "is_pass")
    @FormUrlEncoded
    Observable<BaseBean> is_pass(@Field("uid") String uid);

    /**
     * 锁麦
     */
    @POST(APP_DOMAIN + "shut_microphone")
    @FormUrlEncoded
    Observable<BaseBean> shut_microphone(@Field("uid") String uid, @Field("position") int position);

    /**
     * 开放麦
     */
    @POST(APP_DOMAIN + "open_microphone")
    @FormUrlEncoded
    Observable<BaseBean> open_microphone(@Field("uid") String uid,
                                         @Field("position") int position);

    /**
     * 获取其他用户信息
     */
    @POST(APP_DOMAIN + "get_other_user")
    @FormUrlEncoded
    Observable<OtherUser> get_other_user(
            @Field("uid") String uid,
            @Field("user_id") String user_id,
            @Field("my_id") String my_id);

    /**
     * 管理员闭麦 暂时无用
     */
    @POST(APP_DOMAIN + "shut_sound")
    @FormUrlEncoded
    Observable<BaseBean> shut_sound(@Field("uid") String uid, @Field("position") int position);

    /**
     * 加入禁声
     */
    @POST(APP_DOMAIN + "is_sound")
    @FormUrlEncoded
    Observable<JinSheng> is_sound(@Field("uid") String uid, @Field("user_id") String is_sound);

    /**
     * 取消禁声
     */
    @POST(APP_DOMAIN + "remove_sound")
    @FormUrlEncoded
    Observable<JinSheng> remove_sound(@Field("uid") String uid, @Field("user_id") String sound_id);

    /**
     * 加入禁声
     */
    @POST(APP_DOMAIN + "is_black")
    @FormUrlEncoded
    Observable<JinSheng> is_black(@Field("uid") String uid, @Field("user_id") String black_id);

    /**
     * 收藏
     */
    @POST(APP_DOMAIN + "room_mykeep")
    @FormUrlEncoded
    Observable<BaseBean> room_mykeep(@Field("uid") String uid, @Field("user_id") String user_id);


    /**
     * 取消收藏
     */
    @POST(APP_DOMAIN + "remove_mykeep")
    @FormUrlEncoded
    Observable<BaseBean> remove_mykeep(@Field("uid") String uid, @Field("user_id") String user_id);

    /**
     * 魅力排行榜
     */
    @POST(APP_DOMAIN + "ranking")
    @FormUrlEncoded
    Observable<Rank> leaderboard(
            @Field("class") String type,
            @Field("type") String date);

    /**
     * 排行榜
     */
    @POST(APP_DOMAIN + "jiedanbang")
    @FormUrlEncoded
    Observable<Rank> jiedanbang(
            @Field("class") String type,
            @Field("type") String date);

    /**
     * 全部家族贡献榜单
     */
    @GET(APP_DOMAIN + "qbjzgx")
    Observable<Rank> getAllFamilyGxRank(
            @Query("type") String date);

    /**
     * 热门家族榜单
     */
    @GET(APP_DOMAIN + "rmjz")
    Observable<FamilyRank> getFamilyRank(
            @Query("type") String date);

    /**
     * 我的家族贡献榜单
     */
    @GET(APP_DOMAIN + "wdjzgx")
    Observable<Rank> getFamilyGxRank(
            @Query("type") String date,
            @Query("jzid")String jzid
            );

    /**
     * 家族熔炼排行榜
     */
    @GET(APP_DOMAIN + "ronglianwdjz")
    Observable<Rank> meltingRank(
            @Query("jzid")String jzid,
            @Query("type")String type
            );

    /**
     * 设置管理员
     */
    @POST(APP_DOMAIN + "is_admin")
    @FormUrlEncoded
    Observable<BaseBean> is_admin(
            @Field("uid") String uid,
            @Field("admin_id") String admin_id);

    /**
     * 取消管理员
     */
    @POST(APP_DOMAIN + "remove_admin")
    @FormUrlEncoded
    Observable<BaseBean> remove_admin(
            @Field("uid") String uid,
            @Field("admin_id") String admin_id);

    /**
     * 退出房间
     */
    @POST(APP_DOMAIN + "quit_room")
    @FormUrlEncoded
    Observable<BaseBean> quit_room(
            @Field("uid") String uid,
            @Field("user_id") String user_id);

    /**
     * 添加关注
     */
    @POST(APP_DOMAIN + "follow")
    @FormUrlEncoded
    Observable<BaseBean> follow(
            @Field("user_id") String uid,
            @Field("followed_user_id") String followed_user_id);

    /**
     * 取消关注
     */
    @POST(APP_DOMAIN + "cancel_follow")
    @FormUrlEncoded
    Observable<BaseBean> cancel_follow(
            @Field("user_id") String uid,
            @Field("followed_user_id") String followed_user_id);

    /**
     * 游客管理员
     */
    @POST(APP_DOMAIN + "getRoomUsers")
    @FormUrlEncoded
    Observable<AdminUser> getRoomUsers(
            @Field("uid") String uid);

    /**
     * 搜索用户
     */
    @POST(APP_DOMAIN + "search_user")
    @FormUrlEncoded
    Observable<SearchAdmin> search_user(
            @Field("uid") String uid,
            @Field("keywords") String keywords);


    /**
     * 好友列表is_black
     */
    @POST(APP_DOMAIN + "friend_list")
    @FormUrlEncoded
    Observable<BaseBean> friend_list(
            @Field("user_id") String user_id);

    /**
     * 判断是否禁言
     */
    @POST(APP_DOMAIN + "not_speak_status")
    @FormUrlEncoded
    Observable<BaseBean> not_speak_status(
            @Field("uid") String uid,
            @Field("user_id") String user_id);

    /**
     * 音效
     */
    @POST(APP_DOMAIN + "now_music")
    @FormUrlEncoded
    Observable<MusicYinxiao> get_sound(@Field("id") String id, @Field("user_id") String user_id);


    /**
     * 音乐列表
     */
    @POST(APP_DOMAIN + "local_musics")
    @FormUrlEncoded
    Observable<Yinxiao> get_music(
            @Field("keywords") String keywords,
            @Field("page") String page,
            @Field("user_id") String user_id
    );

    /**
     * 表情
     */
    @POST(APP_DOMAIN + "emoji_list")
    @FormUrlEncoded
    Observable<EmojiBean> emoji_list(
            @Field("xx") String xx);

    /**
     * 动态表情
     */
    @POST(APP_DOMAIN + "get_emoji")
    @FormUrlEncoded
    Observable<GifBean> get_emoji(
            @Field("id") String id);

    /**
     * 获取关注动态
     */

    @POST(APP_DOMAIN + "dynamicFollowList")
    @FormUrlEncoded
    Observable<RecommendedDynamicBean> get_GZ_dynamic(@Field("user_id") String user_id,
                                                      @Field("page") String page);

    /**
     * 我点赞,收藏,转发,评论关注过的动态
     * type 1点赞  2收藏   3转发  4评论   5关注 6目标用户
     */
    @POST(APP_DOMAIN + "merge_dynamic")
    @FormUrlEncoded
    Observable<MeYiDuiBean> getMeYiDui(@Field("user_id") String user_id,
                                       @Field("my_id") String my_id,
                                       @Field("type") String type,
                                       @Field("page") String page);

    /**
     * 检查未读消息
     * user_id 本人id
     */
    @POST(APP_DOMAIN + "unreadMessage")
    @FormUrlEncoded
    Observable<UnreadBean> getUnreadMessage(@Field("user_id") String user_id);

    /**
     * 消息页面
     * user_id 本人ID
     */
    @POST(APP_DOMAIN + "message")
    @FormUrlEncoded
    Observable<MessageYoBean> getMessageYo(@Field("user_id") String user_id);

    /**
     * 查看评论
     * user_id 本人ID
     * hf_uid 评论人ID
     * id 评论ID
     */
    @POST(APP_DOMAIN + "lookComments")
    @FormUrlEncoded
    Observable<LookCommentBean> getLookComment(@Field("user_id") String user_id,
                                               @Field("hf_uid") String hf_uid,
                                               @Field("id") String id);

    /**
     * 查看某条动态下的所有评论
     * id 动态Id
     * user_id 本人ID
     */
    @POST(APP_DOMAIN + "allComment")
    @FormUrlEncoded
    Observable<AllCommentBean> getAllComment(@Field("id") String id,
                                             @Field("user_id") String user_id,
                                             @Field("page") String page);

    /**
     * 评论以及回复评论
     * id 动态的ID
     * pid 回复别人的评论或者评论别人的评论  如果不是就传0
     * user_id 自己的ID
     * content 评论的内容
     */
    @POST(APP_DOMAIN + "dynamic_comment")
    @FormUrlEncoded
    Observable<CommentBean> setComment(@Field("id") String id,
                                       @Field("pid") String pid,
                                       @Field("user_id") String user_id,
                                       @Field("content") String content);

    /**
     * 发送礼物
     */
    @POST(APP_DOMAIN + "gift_queue")
    @FormUrlEncoded
    Observable<SendGemResult> gift_queue(
            @Field("id") String id,
            @Field("uid") String uid,
            @Field("user_id") String user_id,
            @Field("fromUid") String fromUid,
            @Field("num") String num
    );

    /**
     * 发送礼物,全服
     */
    @POST(APP_DOMAIN + "gift_queue_six")
    @FormUrlEncoded
    Observable<SendGiftResult> gift_queue_six(
            @Field("id") String id,
            @Field("uid") String uid,
            @Field("fromUid") String fromUid,
            @Field("num") String num
    );

    /**
     * 排麦
     */
    @POST(APP_DOMAIN + "addWaid")
    @FormUrlEncoded
    Observable<WaitList> addWaid(
            @Field("uid") String uid,
            @Field("user_id") String user_id,
            @Field("type") String type
    );

    /**
     * 排麦列表
     */
    @POST(APP_DOMAIN + "waitList")
    @FormUrlEncoded
    Observable<WaitList> waitList(
            @Field("uid") String uid,
            @Field("user_id") String user_id
    );


    /**
     * 去除排麦
     */
    @POST(APP_DOMAIN + "delWait")
    @FormUrlEncoded
    Observable<BaseBean> delWait(
            @Field("user_id") String user_id
    );

    /**
     * 充值列表
     */
    @POST(APP_DOMAIN + "androidGoodsList")
    @FormUrlEncoded
    Observable<GoodsList> goodsList(
            @Field("user_id") String user_id
    );

    /**
     * 好友,关注,粉丝
     */
    @POST(APP_DOMAIN + "userFriend")
    @FormUrlEncoded
    Observable<UserFriend> userFriend(
            @Field("user_id") String user_id,
            @Field("type") String type,
            @Field("page") String page
    );

    /**
     * 用户个人信息
     */
    @POST(APP_DOMAIN + "get_user_info")
    @FormUrlEncoded
    Observable<UserBean> get_user_info(
            @Field("user_id") String user_id
    );

    /**
     * 我的家族
     */
    @GET(APP_DOMAIN + "isjrjz")
    Observable<MyFamily> getMyFamily(
            @Query("user_id") String user_id
    );

    /**
     * 检测版本
     */
    @GET(APP_DOMAIN + "bbjc")
    Observable<UpdateVersion> getVersion();

    /**
     * 购买头饰
     */
    @GET(APP_DOMAIN + "gmts")
    Observable<BaseBean> buy_ts(
            @Query("user_id") String user_id,
            @Query("id") String id
    );

    /**
     * 购买头饰
     */
    @GET(APP_DOMAIN + "gmzq")
    Observable<BaseBean> buy_zq(
            @Query("user_id") String user_id,
            @Query("id") String id
    );

    /**
     * 个性商城列表
     */
    @GET(APP_DOMAIN + "gxsc")
    Observable<PersonalityBean> getPersonalityShop();

    /**
     * 我的装扮
     */
    @GET(APP_DOMAIN + "zhuangban")
    Observable<MyDress> getMyDress(@Query("user_id") String user_id);

    /**
     * 使用装扮
     */
    @GET(APP_DOMAIN + "syzqts")
    Observable<BaseBean> useDress(@Query("type") String type,@Query("user_id") String user_id,@Query("id") String id);

    /**
     * 支付宝，微信
     */
    @POST(APP_DOMAIN + "rechargePay")//1支付宝2微信
    @FormUrlEncoded
    Observable<PayBean> rechargePay(
            @Field("user_id") String user_id,
            @Field("goods_id") String goods_id,
            @Field("type") String type
    );

    /**
     * 微信
     */
    @POST(APP_DOMAIN + "rechargePay")//1支付宝2微信
    @FormUrlEncoded
    Observable<Wxmodel> rechargeWxPay(
            @Field("user_id") String user_id,
            @Field("goods_id") String goods_id,
            @Field("type") String type
    );

    /**
     * 获取标签
     */
    @POST(APP_DOMAIN + "get_talk_labels")
    @FormUrlEncoded
    Observable<RecommendLabelBean> getLabel(@Field("xx") String xx);

    /**
     * 搜索标签
     * keywords 关键词
     */
    @POST(APP_DOMAIN + "search_labels")
    @FormUrlEncoded
    Observable<SearchLabelBean> getSouSuoLabel(@Field("keywords") String keywords);

    /**
     * 加入黑名单
     */
    @POST(APP_DOMAIN + "pull_black")
    @FormUrlEncoded
    Observable<BaseBean> pull_black(@Field("user_id") String user_id,
                                    @Field("from_uid") String from_uid);

    /**
     * 移除黑名单
     */
    @POST(APP_DOMAIN + "cancel_black")
    @FormUrlEncoded
    Observable<BaseBean> cancel_black(@Field("user_id") String user_id,
                                      @Field("from_uid") String from_uid);

    /**
     * 黑名单列表
     */
    @POST(APP_DOMAIN + "blackList")
    @FormUrlEncoded
    Observable<UserFriend> blackList(@Field("user_id") String user_id,
                                     @Field("page") String page);

    /**
     * 举报类型
     */
    @POST(APP_DOMAIN + "report")
    @FormUrlEncoded
    Observable<ReportBean> report(@Field("xx") String xx);

    /**
     * 举报
     */
    @POST(APP_DOMAIN + "send_report")
    @FormUrlEncoded
    Observable<BaseBean> send_report(
            @Field("user_id") String user_id,
            @Field("img") String img,
            @Field("type") String type,
            @Field("target") String target,
            @Field("report_type") String report_type
    );

    /**
     * 获取官方联系方式
     */
    @POST(APP_DOMAIN + "official")
    @FormUrlEncoded
    Observable<GuanFangLianXiBean> guanfang(@Field("xx") String xx);

    /**
     * 官方消息
     */
    @POST(APP_DOMAIN + "official_message")
    @FormUrlEncoded
    Observable<OffiMessageBean> official_message(
            @Field("user_id") String user_id,
            @Field("page") int page
    );

    /**
     * 清空官方消息
     */
    @POST(APP_DOMAIN + "clear_message")
    @FormUrlEncoded
    Observable<BaseBean> clear_message(
            @Field("user_id") String user_id
    );

    /**
     * mini官方
     */
    @POST(APP_DOMAIN + "mini_official")
    @FormUrlEncoded
    Observable<MiniOfficBean> mini_official(
            @Field("user_id") String user_id
    );

    /**
     * <<<<<<< HEAD
     * 我的钱包
     */
    @POST(APP_DOMAIN + "my_store")
    @FormUrlEncoded
    Observable<MoneyBean> my_store(
            @Field("user_id") String user_id);

    /**
     * <<<<<<< HEAD
     *是否开启代充
     */
    @GET(APP_DOMAIN + "Index/isdc")
    Observable<IsOpenDc> isOpenDc(
            @Query("user_id") String user_id);

    /**
     * <<<<<<< HEAD
     * 根据id获取用户信息
     */
    @GET(APP_DOMAIN + "Index/qrid")
    Observable<UserInfo> getUserInfoById(
            @Query("id") String id);

    /**
     * <<<<<<< HEAD
     * 代充金币
     */
    @GET(APP_DOMAIN + "Index/qrdc")
    Observable<BaseBean> helpRecharge(
            @Query("b_user_id") String b_user_id, @Query("z_user_id") String z_user_id, @Query("num") String num);

    /**
     * <<<<<<< HEAD
     * 获取代充记录
     */
    @GET(APP_DOMAIN + "Index/dcjl")
    Observable<HelpRechargeHistory> getHelpRechargeHistories(
            @Query("user_id") String user_id);

    /**
     * 提现记录
     */
    @POST(APP_DOMAIN + "tixian_log")
    @FormUrlEncoded
    Observable<CashHis> tixian_log(
            @Field("user_id") String user_id,
            @Field("page") int page,
            @Field("type") int type
    );

    /**
     * 兑换记录
     */
    @POST(APP_DOMAIN + "exchange_log")
    @FormUrlEncoded
    Observable<CashHis> exchange_log(
            @Field("user_id") String user_id,
            @Field("page") String page
    );

    /**
     * 兑换米砖
     */
    @POST(APP_DOMAIN + "exchange")
    @FormUrlEncoded
    Observable<BaseBean> exchange(
            @Field("user_id") String user_id,
            @Field("money") String money
    );

    /**
     * 登录授权
     */
    @POST(APP_DOMAIN + "ali_oauth_code")
    @FormUrlEncoded
    Observable<BindBean> ali_oauth_code(
            @Field("xx") String xx
    );

    /**
     * 获取自己的个人信息以及其他人的个人信息
     * user_id 自己的ID
     * from_uid 别人的ID(看的是自己的信息的时候不传这个值）
     */
//    @POST(APP_DOMAIN + "user_home_page")
//    @FormUrlEncoded
//    Observable<MyPersonalCebterBean> getPersonalCabter(@Field("user_id") String user_id,
//                                                       @Field("from_uid") String from_uid);
    @POST(APP_DOMAIN + "user_home_page")
    @FormUrlEncoded
    Observable<MyPersonalCebterTwoBean> getPersonalCabter(@Field("user_id") String user_id,
                                                          @Field("from_uid") String from_uid);

    /**
     * <<<<<<< HEAD
     * 支付宝信息
     */
    @POST(APP_DOMAIN + "ali_oauth_token")
    @FormUrlEncoded
    Observable<AliInfor> ali_oauth_token(
            @Field("auth_code") String auth_code,
            @Field("user_id") String user_id
    );

    /**
     * 提现
     */
    @POST(APP_DOMAIN + "tixian")
    @FormUrlEncoded
    Observable<BaseBean> tixian(
            @Field("user_id") String user_id,
            @Field("money") String money,
            @Field("type") String type
    );

    /**
     * 人脸识别
     */
    @POST(APP_DOMAIN + "sfrz_start")
    @FormUrlEncoded
    Observable<PayBean> sfrz_start(
            @Field("user_id") String user_id,
            @Field("name") String name,
            @Field("idno") String idno
    );

    /**
     * 人脸查询
     */
    @POST(APP_DOMAIN + "sfrz_query")
    @FormUrlEncoded
    Observable<BaseBean> sfrz_query(
            @Field("user_id") String user_id
    );

    /*
     * 忘记密码
     * phone 手机号
     * code 验证码
     * pass 密码
     */
    @POST(APP_DOMAIN + "forget_pwd")
    @FormUrlEncoded
    Observable<CommentBean> ForGetPWD(@Field("phone") String phone,
                                      @Field("code") String code,
                                      @Field("pass") String pass);

    /**
     * 获取星座
     * birthday 生日
     */
    @POST(APP_DOMAIN + "getConstellation")
    @FormUrlEncoded
    Observable<ConstellationBean> getConstellation(@Field("birthday") String birthday);

    /**
     * 修改用户信息
     * img 头像
     * nickname 昵称
     * sex 性别
     * birthday 生日
     * constellation 星座
     * city 城市
     */
    @POST(APP_DOMAIN + "edit_user_info")
    @FormUrlEncoded
    Observable<OtherBean> setUserInfo(@Field("id") String id,
                                      @Field("img") String img,
                                      @Field("nickname") String nickname,
                                      @Field("sex") String sex,
                                      @Field("birthday") String birthday,
                                      @Field("constellation") String constellation,
                                      @Field("city") String city,
                                      @Field("img_1") String img_1,
                                      @Field("img_2") String img_2,
                                      @Field("img_3") String img_3);

    /**
     * 协议
     */
    @POST(APP_DOMAIN + "one_page")
    @FormUrlEncoded
    Observable<AgreementBean> getAgreement(@Field("type") String type);

    /**
     * 会员中心
     */
    @POST(APP_DOMAIN + "vip_center")
    @FormUrlEncoded
    Observable<VipCenterBean> getVipCenter(@Field("user_id") String user_id);

    /**
     * 等级中心
     */
    @POST(APP_DOMAIN + "level_center")
    @FormUrlEncoded
    Observable<DengJiBean> getLevelCenter(@Field("user_id") String user_id);


    /**
     * 收益
     */
    @POST(APP_DOMAIN + "user_income")
    @FormUrlEncoded
    Observable<IncomeBean> user_income(
            @Field("user_id") String user_id
    );

    /**
     * 搜索记录
     */
    @POST(APP_DOMAIN + "searhList")
    @FormUrlEncoded
    Observable<SearchHis> searhList(
            @Field("user_id") String user_id
    );

    /**
     * 清空记录
     */
    @POST(APP_DOMAIN + "cleanSarhList")
    @FormUrlEncoded
    Observable<BaseBean> cleanSarhList(
            @Field("user_id") String user_id
    );

    /**
     * 搜索
     */
    @POST(APP_DOMAIN + "merge_search")
    @FormUrlEncoded
    Observable<Search> merge_search(
            @Field("user_id") String user_id,
            @Field("keywords") String keywords
    );

    /**
     * 搜索全部
     */
    @POST(APP_DOMAIN + "search_all")
    @FormUrlEncoded
    Observable<UserFriend> search_all(
            @Field("user_id") String user_id,
            @Field("keywords") String keywords,
            @Field("type") String type,
            @Field("page") String page
    );

    /**
     * 搜索全部
     */
    @POST(APP_DOMAIN + "search_all")
    @FormUrlEncoded
    Observable<RecommenRoomBean> search_all_room(
            @Field("user_id") String user_id,
            @Field("keywords") String keywords,
            @Field("type") String type,
            @Field("page") String page
    );

    /**
     * 搜索全部
     */
    @POST(APP_DOMAIN + "search_all")
    @FormUrlEncoded
    Observable<RecommendedDynamicBean> search_all_dyni(
            @Field("user_id") String user_id,
            @Field("keywords") String keywords,
            @Field("type") String type,
            @Field("page") String page
    );

//////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 获取房间所有用户
     */
    @POST(APP_DOMAIN + "get_room_users")
    @FormUrlEncoded
    Observable<RoomUsersBean> getRoomUsers(@Field("uid") String user_id,
                                           @Field("user_id") String target_id);

    /**
     * 发送宝石
     */
    @POST(APP_DOMAIN + "send_baoshi")
    @FormUrlEncoded
    Observable<SendGemResult> send_baoshi(@Field("id") String id,
                                          @Field("uid") String uid,
                                          @Field("token") String token,
                                          @Field("fromUid") String fromUid,
                                          @Field("num") String num
    );

    /**
     * 发送爆音卡
     */
    @POST(APP_DOMAIN + "send_byk")
    @FormUrlEncoded
    Observable<SendGemResult> send_byk(
            @Field("uid") String uid,
            @Field("token") String token,
            @Field("fromUid") String fromUid,
            @Field("num") String num);

    /**
     * 同意，拒绝CP，
     */
    @POST(APP_DOMAIN + "handle_cp")
    @FormUrlEncoded
    Observable<AgreeCpResult> handle_cp(
            @Field("token") String token,
            @Field("user_id") String user_id,
            @Field("type") String type);

    /**
     * 删除CP，测试用，测试后会删掉此接口
     */
    @POST(APP_DOMAIN + "delete_cp")
    @FormUrlEncoded
    Observable<BaseBean> delete_cp(
            @Field("token") String token
    );

    /**
     * 首页今日推荐
     */
    @POST(APP_DOMAIN + "tj_user_three")
    Observable<TodayRecommBean> tj_user_three();

    /*
     * 首页房间分类
     */
    @POST(APP_DOMAIN + "room_type_three")
    Observable<RoomTypeResult> room_type_three();

    /*
     * 首页房间列表
     */
    @POST(APP_DOMAIN + "room_list_three")
    @FormUrlEncoded
    Observable<RoomListResult> room_list_three(@Field("class") String typeId, @Field("limit") String limit);

    /*
     * 首页房间推荐
     */
    @POST(APP_DOMAIN + "tuijian_room_three")
    @FormUrlEncoded
    Observable<RoomListResult> tuijian_room_three(@Field("limit") String limit);

    /*
     * 首页房间推荐
     */
    @POST(APP_DOMAIN + "good_room_three")
    Observable<BestRoomResult> good_room_three();

    /**
     * 轮播图
     */
    @POST(APP_DOMAIN + "active_list_three")
    Observable<BannerBean> active_list_three();

    /**
     * 新秀推荐
     */
    @POST(APP_DOMAIN + "new_user_three")
    @FormUrlEncoded
    Observable<TodayRecommBean> new_user_three(@Field("sex") String sex);

    /**
     * 开关数值玩法
     */
    @POST(APP_DOMAIN + "play_num_switch")
    @FormUrlEncoded
    Observable<BaseBean> play_num_switch(@Field("uid") String uid, @Field("type") String type);

    /**
     * 获取房间日榜距离上一名的差额
     */
    @POST(APP_DOMAIN + "check_gap")
    @FormUrlEncoded
    Observable<GetGapResult> check_gap(@Field("uid") String uid);

    /*
     * 房间在线人
     */
    @POST(APP_DOMAIN + "room_online_users")
    @FormUrlEncoded
    Observable<GetOnlineUserResult> room_online_users(@Field("uid") String uid);

    /*
     * 排麦序号
     */
    @POST(APP_DOMAIN + "waitSort")
    @FormUrlEncoded
    Observable<GetSortResult> waitSort(@Field("uid") String uid);

    /*
     * 获取技能列表
     */
    @POST(APP_DOMAIN + "selectSkillsList")
    Observable<GetGameListResult> selectSkillsList();

    /*
     * 获取段位
     */
    @POST(APP_DOMAIN + "getSkillLevelList")
    @FormUrlEncoded
    Observable<SegmentListResult> getSkillLevelList(@Field("skill_id") String skill_id);


    /**
     * 申请技能*
     */
    @POST(APP_DOMAIN + "actionApplySkill")
    @Multipart
    Observable<BaseBean> actionApplySkill(
            @QueryMap Map<String, Object> op,
            @Part MultipartBody.Part audioFile,
            @Part MultipartBody.Part imgFile
    );

    /*
     * 获取我的技能
     */
    @POST(APP_DOMAIN + "getMySkillList")
    Observable<MyGameSkillResult> getMySkillList();

    /*
     * 设置是否接单
     */
    @POST(APP_DOMAIN + "actionIsJd")
    @FormUrlEncoded
    Observable<BaseBean> actionIsJd(@Field("skill_id") String skill_id, @Field("is_open") String is_open);

    /*
     * 技能设置页面信息
     */
    @POST(APP_DOMAIN + "getSkillSetInfo")
    @FormUrlEncoded
    Observable<GetGameSetResult> getSkillSetInfo(@Field("skill_id") String skill_id);

    /*
     * 价格列表
     */
    @POST(APP_DOMAIN + "getSkillPriceList")
    @FormUrlEncoded
    Observable<GetPriceListResult> getSkillPriceList(@Field("skill_id") String skill_id);

    /*
     * 大区列表
     */
    @POST(APP_DOMAIN + "getSkillAreaList")
    @FormUrlEncoded
    Observable<GetAreaListResult> getSkillAreaList(@Field("skill_id") String skill_id);

    /*
     * 大区列表
     */
    @POST(APP_DOMAIN + "getSkillPositionList")
    @FormUrlEncoded
    Observable<GetGoodAtPositionListResult> getSkillPositionList(@Field("skill_id") String skill_id);

    /*
     * 设置价格
     */
    @POST(APP_DOMAIN + "actionSetPrice")
    @FormUrlEncoded
    Observable<BaseBean> actionSetPrice(@Field("skill_id") String skill_id, @Field("price_id") String price_id);

    /*
     * 设置接单大区
     */
    @POST(APP_DOMAIN + "actionSetArea")
    @FormUrlEncoded
    Observable<BaseBean> actionSetArea(@Field("skill_id") String skill_id, @Field("area_ids") String price_id);

    /*
     * 设置接单大区
     */
    @POST(APP_DOMAIN + "actionSetPosition")
    @FormUrlEncoded
    Observable<BaseBean> actionSetPosition(@Field("skill_id") String skill_id, @Field("position_ids") String price_id);

    /*
     * 设置接单大区
     */
    @POST(APP_DOMAIN + "getSkillInfo")
    @FormUrlEncoded
    Observable<GetSkillDetailInfoResult> getSkillInfo(@Field("skill_id") String skill_id);

    /*
     * 获取游戏列表
     */
    @POST(APP_DOMAIN + "getAllSkillsList")
    Observable<GetGameListResult> getAllSkillsList();

    /*
     * 派单
     */
    @POST(APP_DOMAIN + "actionStartPd")
    @FormUrlEncoded
    Observable<BaseBean> actionStartPd(@Field("skill_id") String skill_id, @Field("uid") String uid, @Field("service_time") String service_time, @Field("remark") String remark);

    /*
     * 派单信息
     */
    @POST(APP_DOMAIN + "getPdInfo")
    @FormUrlEncoded
    Observable<GetPaiDanInfoResult> getPdInfo(@Field("uid") String uid);

    /*
     * 截止派单
     */
    @POST(APP_DOMAIN + "actionEndPd")
    @FormUrlEncoded
    Observable<BaseBean> actionEndPd(@Field("pd_id") String pd_id);

    /*
     * 订单中心
     */
    @POST(APP_DOMAIN + "go_order_list")
    @FormUrlEncoded
    Observable<OrderListResult> go_order_list(@Field("keywords") String keywords, @Field("type") String type, @Field("page") String page);

    /*
     * 订单详情
     */
    @POST(APP_DOMAIN + "go_order_details")
    @FormUrlEncoded
    Observable<OrderDetailResult> go_order_details(@Field("gm_orderid") String go_order_details, @Field("type") String type);

    /*
     * 大神同意或者拒绝单子
     */
    @POST(APP_DOMAIN + "go_receipt")
    @FormUrlEncoded
    Observable<BaseBean> go_receipt(@Field("gm_orderid") String gm_orderid, @Field("type") String type);

    /*
     * 大神申请立即服务
     */
    @POST(APP_DOMAIN + "go_apply_ljwf")
    @FormUrlEncoded
    Observable<BaseBean> go_apply_ljwf(@Field("gm_orderid") String gm_orderid);

    /*
     * 同意或者拒绝立即服务
     */
    @POST(APP_DOMAIN + "go_ljwf_hand")
    @FormUrlEncoded
    Observable<BaseBean> go_ljwf_hand(@Field("gm_orderid") String gm_orderid, @Field("type") String type);

    /*
     * 同意或者拒绝立即服务
     */
    @POST(APP_DOMAIN + "go_finish")
    @FormUrlEncoded
    Observable<BaseBean> go_finish(@Field("gm_orderid") String gm_orderid);

    /*
     * 取消订单
     */
    @POST(APP_DOMAIN + "go_cancel")
    @FormUrlEncoded
    Observable<BaseBean> go_cancel(@Field("gm_orderid") String gm_orderid, @Field("cancel") String cancel);

    /*
     * 申请退款
     */
    @POST(APP_DOMAIN + "go_apply_refund")
    @FormUrlEncoded
    Observable<BaseBean> go_apply_refund(@Field("gm_orderid") String gm_orderid);

    /*
     * 申请退款
     */
    @POST(APP_DOMAIN + "go_apply_refund_hand")
    @FormUrlEncoded
    Observable<BaseBean> go_apply_refund_hand(@Field("gm_orderid") String gm_orderid, @Field("type") String type);

    /*
     * 申诉
     */
    @POST(APP_DOMAIN + "go_appeal")
    @FormUrlEncoded
    Observable<BaseBean> go_appeal(@Field("gm_orderid") String gm_orderid, @Field("reason") String reason, @Field("img1") String img1, @Field("img2") String iimg2mg1, @Field("img3") String img3);

    /*
     * 申诉
     */
    @POST(APP_DOMAIN + "go_add_gm_com")
    @FormUrlEncoded
    Observable<BaseBean> go_add_gm_com(@Field("gm_orderid") String gm_orderid, @Field("content") String content, @Field("star") String star);

    /*
     * 订单唯未读数量
     */
    @POST(APP_DOMAIN + "go_unread_sum")
    @FormUrlEncoded
    Observable<UnReadOrderResult> go_unread_sum(@Field("type") String type);

    /*
     * 家族列表
     */
    @POST(APP_DOMAIN + "getFamilyList")
    @FormUrlEncoded
    Observable<FamilyListResult> getFamilyList(@Field("page") String page);

    /*
     * 房间家族
     */
    @GET(APP_DOMAIN + "jzpd")
    Observable<RoomFamily> getRoomFamily(@Query("user_id") String user_id);

    /*
     * 房间家族
     */
    @GET(APP_DOMAIN + "isjrjz")
    Observable<IsJoinFamily> isJoinFamily(@Query("user_id") String user_id);

    /*
     * 贡献记录
     */
    @GET(APP_DOMAIN + "jzgxjl")
    Observable<BxRecord> getBxRecord(@Query("type")String type,@Query("page") String page,@Query("jzid")String jzid);

    /*
     * 家族说明
     */
    @GET(APP_DOMAIN + "jzsm")
    Observable<FamilyIntroduce> getFamilyIntroduce();

    /*
     * 好运榜
     */
    @GET(APP_DOMAIN + "hyb")
    Observable<BxRecord> getLuckyRecord(@Query("page") String page,@Query("jzid")String jzid,@Query("type")String type);

    /*
     * 搜索好友列表
     */
    @POST(APP_DOMAIN + "getSearchUserList")
    @FormUrlEncoded
    Observable<AddFamilyUserResult> getSearchUserList(@Field("keywords") String keywords, @Field("page") String page);

    /*
     * 搜索好友列表
     */
    @POST(APP_DOMAIN + "searchUser")
    @FormUrlEncoded
    Observable<AddFamilyUserResult> searchUser(@Field("keywords") String keywords, @Field("page") String page);

    /*
     * 删除成员列表
     *
     */
    @POST(APP_DOMAIN + "getUserDelList")
    @FormUrlEncoded
    Observable<AddFamilyUserResult> getUserDelList(@Field("keywords") String keywords, @Field("family_id") String family_id, @Field("page") String page);

    /**
     * 创建家族*
     */
    @POST(APP_DOMAIN + "actionCreateFamily")
    @Multipart
    Observable<CreatFamilyResult> actionCreateFamily(
            @QueryMap Map<String, Object> op,
            @Part MultipartBody.Part imgFile
    );

    /*
     * 同意加入家族
     */
    @POST(APP_DOMAIN + "actionAgreeJoin")
    @FormUrlEncoded
    Observable<BaseBean> actionAgreeJoin(@Field("family_id") String family_id);

    /*
     * 拒绝加入家族
     */
    @POST(APP_DOMAIN + "actionRefuseJoin")
    @FormUrlEncoded
    Observable<BaseBean> actionRefuseJoin(@Field("family_id") String family_id);

    /*
     * 获取我的家族
     */
    @POST(APP_DOMAIN + "getMyFamilyInfo")
    Observable<MyFamilyResult> getMyFamilyInfo();

    /*
     * 获取家族详情
     */
    @POST(APP_DOMAIN + "getFamilyDetail")
    @FormUrlEncoded
    Observable<GetFamilyDetailResult> getFamilyDetail(@Field("family_id") String family_id);

    /*
     * 加入公聊
     */
    @POST("http://qutu.zzmzrj.com/rongyun/RongCloud/example/Group/jrqz.php")
    @FormUrlEncoded
    Observable<String> joinPublicGroup(@Field("user_id") String user_id);

    /*
     * 获取家族详情
     */
    @POST(APP_DOMAIN + "getFamilyMoreList")
    @FormUrlEncoded
    Observable<FamilySettingResult> getFamilyMoreList(@Field("family_id") String family_id);

    /*
     * 群成员禁言
     */
    @POST(APP_DOMAIN + "actionSetSpeak")
    @FormUrlEncoded
    Observable<BaseBean> actionSetSpeak(@Field("family_id") String family_id, @Field("speakswitch") String speakswitch);

    /*
     * 屏蔽群消息
     */
    @POST(APP_DOMAIN + "actionSetClose")
    @FormUrlEncoded
    Observable<BaseBean> actionSetClose(@Field("family_id") String family_id, @Field("closeswitch") String closeswitch);

    /*
     * 解散群
     */
    @POST(APP_DOMAIN + "actionDisFamily")
    @FormUrlEncoded
    Observable<BaseBean> actionDisFamily(@Field("family_id") String family_id);

    /*
     * 退出群
     */
    @POST(APP_DOMAIN + "actionQuitFamily")
    @FormUrlEncoded
    Observable<BaseBean> actionQuitFamily(@Field("family_id") String family_id);

    /*
     * 获取群组管理员和非管理列表
     */
    @POST(APP_DOMAIN + "getAdminList")
    @FormUrlEncoded
    Observable<GetAdminResult> getAdminList(@Field("keywords") String keywords);

    /*
     * 取消管理
     */
    @POST(APP_DOMAIN + "actionCancelAdmin")
    @FormUrlEncoded
    Observable<BaseBean> actionCancelAdmin(@Field("family_user_id") String family_user_id);

    /*
     * 设置管理
     */
    @POST(APP_DOMAIN + "actionSetAdmin")
    @FormUrlEncoded
    Observable<BaseBean> actionSetAdmin(@Field("family_user_id") String family_user_id);

    /*
     * 群组成员列表
     */
    @POST(APP_DOMAIN + "getFamilyUserList")
    @FormUrlEncoded
    Observable<GetFamilyUserListResult> getFamilyUserList(@Field("family_id") String family_user_id, @Field("page") String page);

    /*
     * 设置管理员
     */
    @GET(APP_DOMAIN + "szgly")
    Observable<BaseBean> setFamilyManager(@Query("jzid") String family_user_id, @Query("user_id") String userId);

    /*
     * 取消管理员
     */
    @GET(APP_DOMAIN + "qxgly")
    Observable<BaseBean> cancelFamilyManager(@Query("jzid") String family_user_id, @Query("user_id") String userId);

    /*
     * 添加成员
     */
    @POST(APP_DOMAIN + "actionAddUser")
    @FormUrlEncoded
    Observable<BaseBean> actionAddUser(@Field("family_id") String family_user_id, @Field("users") String users);

    /*
     * 删除成员
     */
    @POST(APP_DOMAIN + "actionDelUser")
    @FormUrlEncoded
    Observable<BaseBean> actionDelUser(@Field("family_id") String family_user_id, @Field("users") String users);

    /*
     * 群聊是否存在
     */
    @POST(APP_DOMAIN + "get_is_family")
    @FormUrlEncoded
    Observable<GetIsExitFamilyResult> get_is_family(@Field("family_id") String family_user_id);

    /*
     * 分享房间
     */
    @POST(APP_DOMAIN + "share_room")
    Observable<BaseBean> share_room();

    /*
     * 家族家少
     */
    @POST(APP_DOMAIN + "family_introduce")
    Observable<IntroResult> family_introduce();

    //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 收藏的房间列表
     */
    @POST(APP_DOMAIN + "get_mykeep")
    @FormUrlEncoded
    Observable<CollectionRoomListBean> getCollectionRoomList(@Field("user_id") String user_id);

    /**
     * 第三方注册
     */
    @POST(APP_DOMAIN + "OtherRegister")
    @FormUrlEncoded
    Observable<Login> registerOther(@Field("type") String type,
                                    @Field("openid") String openid,
                                    @Field("phone") String phone,
                                    @Field("sex") String sex,
                                    @Field("birthday") String birthday,
                                    @Field("nickname") String nickname,
                                    @Field("headimgurl") String headimgurl,
                                    @Field("pass") String pass,
                                    @Field("system") String system,
                                    @Field("channel") String channel,
                                    @Field("code") String code);

    /**
     * 三方登录
     */
    @POST(APP_DOMAIN + "OtherLogin")
    @FormUrlEncoded
    Observable<OtherBean> logOther(@Field("openid") String openid,
                                   @Field("type") String type);

    /**
     * 删除动态
     */
    @POST(APP_DOMAIN + "del_community")
    @FormUrlEncoded
    Observable<CommentBean> delCommunity(@Field("user_id") String user_id,
                                         @Field("id") String id);

    /**
     * 用户反馈
     */
    @POST(APP_DOMAIN + "feedback")
    @FormUrlEncoded
    Observable<BaseBean> feedBack(@Field("user_id") String user_id,
                                  @Field("content") String content,
                                  @Field("img") String img);

    /**
     * 社区活动
     */
    @POST(APP_DOMAIN + "activeList")
    @FormUrlEncoded
    Observable<ActiveListBean> activeList(@Field("xx") String xx);

    /**
     * 获取vip等级
     */
    @POST(APP_DOMAIN + "get_user_vip")
    @FormUrlEncoded
    Observable<VipBean> get_user_vip(@Field("uid") String user_id, @Field("token") String token);

    /**
     * 分享动态
     */
    @POST(APP_DOMAIN + "share_dynamic")
    @FormUrlEncoded
    Observable<CommentBean> fenxiang(@Field("user_id") String user_id,
                                     @Field("target_id") String target_id,
                                     @Field("hand") String hand);

    /**
     * 踢出房间
     */
    @POST(APP_DOMAIN + "out_room")
    @FormUrlEncoded
    Observable<BaseBean> out_room(@Field("uid") String uid,
                                  @Field("user_id") String user_id);

    /**
     * 接触阿里绑定
     */
    @POST(APP_DOMAIN + "UntyingAli")
    @FormUrlEncoded
    Observable<BaseBean> UntyingAli(
            @Field("user_id") String user_id);

    /**
     * 房间信息
     */
    @POST(APP_DOMAIN + "getRoomInfo")
    @FormUrlEncoded
    Observable<EnterRoom> getRoomInfo(
            @Field("uid") String uid);

    /**
     * 兑换查询
     */
    @POST(APP_DOMAIN + "exchange_check")
    @FormUrlEncoded
    Observable<ChaMoneyBean> getMoney(@Field("money") String money);

    /**
     * 是否关注
     */
    @POST(APP_DOMAIN + "is_follow")
    @FormUrlEncoded
    Observable<FollowBean> is_follow(@Field("user_id") String user_id, @Field("from_uid") String from_uid);

    /**
     * 我的背包
     */
    @POST(APP_DOMAIN + "my_pack")
    @FormUrlEncoded
    Observable<MyPackBean> my_pack(@Field("type") String type);

    /**
     * 装扮
     */
    @POST(APP_DOMAIN + "dress_up")
    @FormUrlEncoded
    Observable<CommentBean> dress_up(@Field("id") String id,
                                     @Field("type") String type);

    /**
     * CP详情
     * id 本cp的id
     */
    @POST(APP_DOMAIN + "cp_desc")
    @FormUrlEncoded
    Observable<CPDetailsBean> cp_desc(@Field("id") String id);

    /**
     * 解除CP
     * id CP的id
     */
    @POST(APP_DOMAIN + "remove_cp")
    @FormUrlEncoded
    Observable<CommentBean> remove_cp(@Field("id") String id);

    /**
     * 宝箱信息
     */
    @POST(APP_DOMAIN + "getBoxInfo")
    @FormUrlEncoded
    Observable<BaoXiangBean> getBoxInfo(@Field("xx") String xx);

    /**
     * 宝箱信息
     */
    @GET(APP_DOMAIN + "nldnum")
    Observable<NldBean> getNld(@Query("user_id") String user_id);

    /**
     * 熔炼背包
     */
    @GET(APP_DOMAIN + "lwrlpack")
    Observable<MeltingPack> getMeltingPack(@Query("user_id") String userId);

    /**
     * 熔炼礼物
     */
    @GET(APP_DOMAIN + "lwrl")
    Observable<BaseBean> meltingGift(@Query("user_id") String userId,@Query("id")String id);


    /**
     * 开奖（开启宝箱）
     */
    @POST(APP_DOMAIN + "getAwardList")
    @FormUrlEncoded
    Observable<OpenBoxBean> getAwardList(@Field("keysNum") int keysNum);

    /**
     * 普通贡献
     */
    @GET(APP_DOMAIN + "getAwardListptgx")
    Observable<OpenBoxBean> getCommonGx(@Query("keysNum") int keysNum,@Query("jzid")String jzid,@Query("banben")String banben);

    /**
     * 快速贡献
     */
    @GET(APP_DOMAIN + "getAwardListtwokxgx")
    Observable<OpenBoxBean> getQuickGx(@Query("keysNum") int keysNum,@Query("jzid")String jzid,@Query("banben")String banben);

    /**
     * 开奖（开启彩蛋）
     */
    @POST(APP_DOMAIN + "getAwardListtwo")
    @FormUrlEncoded
    Observable<OpenBoxBean> getAwardListColor(@Field("keysNum") int keysNum);

    /**
     * 购买钥匙
     */
    @POST(APP_DOMAIN + "actionBuyKeys")
    @FormUrlEncoded
    Observable<CommentBean> actionBuyKeys(@Field("keysNum") String keysNum);

    /**
     * 查看购买的钥匙需要多少金币
     */
    @POST(APP_DOMAIN + "getMizuanNum")
    @FormUrlEncoded
    Observable<XuYaoMiZuanBean> getMizuanNum(@Field("keysNum") String keysNum);

    /**
     * 获取积分可兑换的礼物
     */
    @POST(APP_DOMAIN + "getAwardWaresList")
    @FormUrlEncoded
    Observable<BoxExchangeBean> getAwardWaresList(@Field("xx") String xx);

    /**
     * 积分兑换礼物
     */
    @POST(APP_DOMAIN + "actionAwardExchange")
    @FormUrlEncoded
    Observable<CommentBean> actionAwardExchange(@Field("waresId") String waresId);

    /**
     * 获取中奖纪录
     */
    @POST(APP_DOMAIN + "getAwardRecordList")
    @FormUrlEncoded
    Observable<BoxOpenRecordBean> getAwardRecordList(@Field("page") int page);

    /**
     * 获取奖池
     */
    @GET(APP_DOMAIN + "getJiangchi")
    Observable<Jackpot> getJiangchi();

    /**
     * 获取奖池
     */
    @GET(APP_DOMAIN + "jzgetJiangchi")
    Observable<GxJackpot> getGxJiangchi(@Query("jzid") String jzid);
    /**
     * 宝箱文字说明
     */
    @POST(APP_DOMAIN + "getRewardInfo")
    @FormUrlEncoded
    Observable<BXShuoMingTextBean> getRewardInfo(@Field("xx") String xx);

    /**
     * 宝箱文字说明
     */
    @POST(APP_DOMAIN + "jzgetRewardInfo")
    Observable<GXShuoMingTextBean> getGXShuoMing();

    /**
     * 熔炼说明
     */
    @GET(APP_DOMAIN + "rlgz")
    Observable<GXShuoMingTextBean> getMeltingShuoMing();

    /**
     * 开启cp位
     */
    @POST(APP_DOMAIN + "open_cp_card")
    @FormUrlEncoded
    Observable<CommentBean> openCPCard(@Field("xx") String xx);

    /**
     * 兑换金币卡
     */
    @POST(APP_DOMAIN + "exchange_mizuan_card")
    @FormUrlEncoded
    Observable<CommentBean> exchangeMizuanCard(@Field("id") String id);

    /**
     * 房间内排行榜
     * 1贡献榜2房间榜 class
     * 1日榜2周榜3月榜 type
     */
    @POST(APP_DOMAIN + "room_ranking")
    @FormUrlEncoded
    Observable<RoomRankBean> room_ranking(@Field("uid") String uid,
                                          @Field("class") String mclass,
                                          @Field("type") String type);

    /**
     * 获取房间信息
     * uid 房间ID
     */
    @POST(APP_DOMAIN + "getRoomInfo")
    @FormUrlEncoded
    Observable<RoomInfoBean> getRoomInfoThree(@Field("uid") String uid);

    /**
     * 删除评论
     * id 评论ID
     */
    @POST(APP_DOMAIN + "del_comments_three")
    @FormUrlEncoded
    Observable<CommentBean> del_comments_three(@Field("id") String id);

    /**
     * 榜单说明
     */
    @POST(APP_DOMAIN + "get_shuoming")
    @FormUrlEncoded
    Observable<RankExplainBean> get_shuoming(@Field("name") String name);

    /**
     * 打赏记录
     */
    @POST(APP_DOMAIN + "room_gifts_log")
    @FormUrlEncoded
    Observable<RoomRewardOneBean> room_gifts_log(@Field("uid") String uid, @Field("page") int page);

    /**
     * 收入统计
     */
    @POST(APP_DOMAIN + "roomIncomeCount")
    @FormUrlEncoded
    Observable<IncomeSumBean> roomIncomeCount(@Field("page") int page, @Field("uid") String uid);

    /**
     * 礼物收入记录
     */
    @POST(APP_DOMAIN + "getUserGiftList")
    @FormUrlEncoded
    Observable<IncomeFragmentBean> getUserGiftList(@Field("page") int page);

    /**
     * 打赏礼物记录
     */
    @POST(APP_DOMAIN + "getUserSendGiftList")
    @FormUrlEncoded
    Observable<IncomeFragmentBean> getUserSendGiftList(@Field("page") int page);

    /**
     * 开启计时
     */
    @POST(APP_DOMAIN + "openTime")
    @FormUrlEncoded
    Observable<OpenTimeBean> openTime(@Field("uid") String uid, @Field("time") int time, @Field("muid") String muid);

    /**
     * 关闭计时
     */
    @POST(APP_DOMAIN + "closeTime")
    @FormUrlEncoded
    Observable<CommentBean> closeTime(@Field("uid") String uid, @Field("muid") String muid);

    /**
     * 首页热门房间
     */
    @POST(APP_DOMAIN + "hot_room_three")
    @FormUrlEncoded
    Observable<HotBean> hot_room_three(@Field("page") int page);

    /**
     * 根据房间类型来显示房间
     */
    @POST(APP_DOMAIN + "room_list_by_cate")
    @FormUrlEncoded
    Observable<HotBean> room_list_by_cate(@Field("class") String mClass,
                                          @Field("page") int page);

    /**
     * 检测扩展卡数量
     */
    @POST(APP_DOMAIN + "check_kzk")
    Observable<KzkBean> check_kzk();

    /**
     * 订单记录
     */
    @POST(APP_DOMAIN + "go_order_log")
    @FormUrlEncoded
    Observable<MiLiSZJiLuBean> go_order_log(@Field("page") int page,
                                            @Field("type") int type);

    /**
     * 大神专属
     */
    @POST(APP_DOMAIN + "getGodInfo")
    Observable<DaShenZhuanShuBean> getGodInfo();

    /**
     * 游戏订单收入统计
     */
    @POST(APP_DOMAIN + "GmOrderIncomeCount")
    Observable<MiLiIncomeBean> gmOrderIncomeCount();

    /**
     * 派单中心
     */
    @POST(APP_DOMAIN + "getReceiptCenterList")
    @FormUrlEncoded
    Observable<PaiDanCenterBean> getReceiptCenterList(@Field("page") int page);

    /**
     * 接单设置
     */
    @POST(APP_DOMAIN + "getReceiptList")
    Observable<JieDanSetBean> getReceiptList();

    /**
     * 派单接受设置
     */
    @POST(APP_DOMAIN + "actionReceiptPd")
    @FormUrlEncoded
    Observable<CommentBean> actionReceiptPd(@Field("skill_id") int skill_id,
                                            @Field("status") String status);

    /**
     * 设置接单时间
     */
    @POST(APP_DOMAIN + "actionReceiptTime")
    @FormUrlEncoded
    Observable<CommentBean> actionReceiptTime(@Field("start") int start,
                                              @Field("end") int end);

    /**
     * 选择接单日期页面
     */
    @POST(APP_DOMAIN + "getJdDateList")
    Observable<JieDanRiQiBean> getJdDateList();

    /**
     * 设置接单日期
     */
    @POST(APP_DOMAIN + "actionReceiptDate")
    @FormUrlEncoded
    Observable<CommentBean> actionReceiptDate(@Field("days") String days);

    /**
     * 金币充值列表
     */
    @POST(APP_DOMAIN + "androidMiliList")
    Observable<GoodsMiLiListBean> androidMiliList();

    /**
     * 金币充值
     * 支付宝
     */
    @POST(APP_DOMAIN + "miliRecharge")//1支付宝2微信
    @FormUrlEncoded
    Observable<PayBean> miliRecharge(
            @Field("goods_id") String goods_id,
            @Field("type") String type
    );

    /**
     * 金币充值
     * 微信
     */
    @POST(APP_DOMAIN + "miliRecharge")//1支付宝2微信
    @FormUrlEncoded
    Observable<Wxmodel> miliWXRecharge(
            @Field("goods_id") String goods_id,
            @Field("type") String type
    );

    /**
     * 技能分类
     */
    @POST(APP_DOMAIN + "getAllSkillsList")
    Observable<MainHomePageSkillBean> getAllSkillsLists();

    /**
     * 技能大神列表
     */
    @POST(APP_DOMAIN + "getSkillGodList")
    @FormUrlEncoded
    Observable<MainHomePageBean> getSkillGodList(@Field("is_recom") String is_recom,
                                                 @Field("sex") String sex,
                                                 @Field("level_id") String level_id,
                                                 @Field("price_id") String price_id,
                                                 @Field("skill_id") String skill_id,
                                                 @Field("page") int page);

    /*
     * 获取段位
     */
    @POST(APP_DOMAIN + "getSkillLevelList")
    @FormUrlEncoded
    Observable<DuanWeiBean> getSkillLevelListJava(@Field("skill_id") String skill_id);

    /**
     * 价格列表
     */
    @POST(APP_DOMAIN + "getSkillPriceList")
    @FormUrlEncoded
    Observable<ScreenPriceBean> getSkillPriceListJava(@Field("skill_id") String skill_id);

    /**
     * 确认订单页面技能选择
     */
    @POST(APP_DOMAIN + "orderUserSkillList")
    @FormUrlEncoded
    Observable<ConfirmOrderSkillBean> orderUserSkillList(@Field("user_id") String user_id);

    /**
     * 游戏下单
     */
    @POST(APP_DOMAIN + "go_add_order")
    @FormUrlEncoded
    Observable<Orderbean> go_add_order(@Field("skill_apply_id") String skill_apply_id,
                                       @Field("start_time") String start_time,
                                       @Field("num") String num,
                                       @Field("remarks") String remarks,
                                       @Field("coupon_id") String coupon_id);

    /**
     * 支付游戏订单
     */
    @POST(APP_DOMAIN + "go_pay_game")
    @FormUrlEncoded
    Observable<CommentBean> go_pay_game_yu_e(@Field("gm_orderid") String gm_orderid,
                                             @Field("type") String type);

    /**
     * 支付游戏订单
     */
    @POST(APP_DOMAIN + "go_pay_game")
    @FormUrlEncoded
    Observable<PayBean> go_pay_game_zfb(@Field("gm_orderid") String gm_orderid,
                                        @Field("type") String type);

    /**
     * 支付游戏订单
     */
    @POST(APP_DOMAIN + "go_pay_game")
    @FormUrlEncoded
    Observable<Wxmodel> go_pay_game_wx(@Field("gm_orderid") String gm_orderid,
                                       @Field("type") String type);

    /**
     * 大神技能信息
     */
    @POST(APP_DOMAIN + "getGodSkillInfo")
    @FormUrlEncoded
    Observable<GodCenterBean> getGodSkillInfo(@Field("skill_apply_id") String skill_apply_id,
                                              @Field("page") int page);

    /**
     * 绑定device_token
     */
    @POST(APP_DOMAIN + "go_binding_device")
    @FormUrlEncoded
    Observable<BaseBean> go_binding_device(@Field("device_token") String device_token,
                                           @Field("token") String token);

    /********************************************************************老王**********************************************************/
    /**
     * 新手任务
     */
    @POST(APP_DOMAIN + "new_task")
    Observable<NewbieTaskBean> new_task();

    /**
     * 日常任务
     */
    @POST(APP_DOMAIN + "daily_task")
    Observable<NewbieTaskBean> daily_task();

    /**
     * 领取任务奖励
     */
    @POST(APP_DOMAIN + "recevice_task")
    @FormUrlEncoded
    Observable<CommentBean> recevice_task(@Field("task_id") String task_id);

    /**
     * 签到展示
     */
    @POST(APP_DOMAIN + "show_sign")
    Observable<SignInDisplayBean> show_sign();

    /**
     * 签到
     */
    @POST(APP_DOMAIN + "sign")
    Observable<SignInBean> sign();

    /**
     * 钻石兑换记录
     */
    @POST(APP_DOMAIN + "jb_exchange_log")
    @FormUrlEncoded
    Observable<JBExchangeBean> jb_exchange_log(@Field("page") int page);

    /**
     * 兑换列表
     */
    @POST(APP_DOMAIN + "jb_exchange_list")
    Observable<ExchangeListBean> jb_exchange_list();

    /**
     * 钻石兑换
     */
    @POST(APP_DOMAIN + "jb_exchange")
    @FormUrlEncoded
    Observable<CommentBean> jb_exchange(@Field("id") String id);

    /**
     * 今日是否已打开此应用
     */
    @POST(APP_DOMAIN + "is_open_today")
    Observable<SignInDisplayBean> is_open_today();

    /**
     * 我的优惠券
     */
    @POST(APP_DOMAIN + "my_coupon")
    @FormUrlEncoded
    Observable<MyCouponsBean> my_coupon(@Field("type") String type,
                                        @Field("page") int page);

    /**
     * 我的优惠券
     */
    @POST(APP_DOMAIN + "my_coupon")
    @FormUrlEncoded
    Observable<ChoiceCouponBean> my_coupontwo(@Field("type") String type,
                                              @Field("page") int page);

    /**
     * 判断是否族长与管理员
     */
    @POST(APP_DOMAIN + "getUserType")
    Observable<UserTypeBean> getUserType();

    /**
     * 家族申请
     */
    @POST(APP_DOMAIN + "getFamilyApllyList")
    @FormUrlEncoded
    Observable<FamilyApplyBean> getFamilyApllyList(@Field("page") int page);

    /**
     * 家族申请--同意
     */
    @POST(APP_DOMAIN + "actionAgreeFamily")
    @FormUrlEncoded
    Observable<CommentBean> actionAgreeFamily(@Field("family_user_id") String family_user_id);

    /**
     * 家族申请--拒绝
     */
    @POST(APP_DOMAIN + "actionRefuseFamily")
    @FormUrlEncoded
    Observable<CommentBean> actionRefuseFamily(@Field("family_user_id") String family_user_id);

    /**
     * 申请加入家族
     */
    @POST(APP_DOMAIN + "actionApplyFamily")
    @FormUrlEncoded
    Observable<CommentBean> actionApplyFamily(@Field("family_id") String family_id);

    /**
     * 家族编辑保存
     */
    @POST(APP_DOMAIN + "actionEditFamily")
    @Multipart
    Observable<CommentBean> actionEditFamily(@QueryMap Map<String, Object> op,
                                             @Part MultipartBody.Part imgFile);

    /**
     * 中奖信息
     */
    @POST(APP_DOMAIN + "demo/qfbb")
    Observable<Winner> winners();
    /**
     * 声优
     */
    @POST(APP_DOMAIN + "shengyou")
    Observable<Shengyou> shengyou();
    /**
     * 首页全部语音
     */
    @POST(APP_DOMAIN + "quanbutop")
    Observable<HotBean> quanbutop();
//    Observable<ShengyouQuanbutop> quanbutop();

    /**
     * 首页-接单榜
     * @param type 1日榜 2周榜
     * @return
     */
    @GET(APP_DOMAIN + "jiedanbang")
    Observable<Rank> jiedanbang(
            @Query("type") String type);
    /**
     * 查询坐骑
     * @return
     */
    @GET(APP_DOMAIN + "tszq")
    Observable<PersonalityBean1> tszq(@Query("user_id") String user_id);
}
