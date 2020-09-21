/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qutu.talk.service;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.qutu.talk.bean.*;
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
import com.qutu.talk.bean.family.FamilyApplyBean;
import com.qutu.talk.bean.order.JudgeBindingZFBBean;
import com.qutu.talk.bean.order.Orderbean;
import com.qutu.talk.bean.task.ExchangeListBean;
import com.qutu.talk.bean.task.JBExchangeBean;
import com.qutu.talk.bean.task.NewbieTaskBean;
import com.qutu.talk.bean.task.SignInBean;
import com.qutu.talk.bean.task.SignInDisplayBean;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;

/**
 * ================================================
 * 展示 Model 的用法
 *
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki#2.4.3">Model wiki 官方文档</a>
 * Created by JessYan on 09/04/2016 10:56
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
@ActivityScope
public class CommonModel extends com.jess.arms.mvp.BaseModel {
    public static final int USERS_PER_PAGE = 10;

    @Inject
    public CommonModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    public Observable<Register> register(String phone,
                                         @Field("sex") String sex,
                                         @Field("birthday") String birthday,
                                         @Field("pass") String pass,
                                         String nickname,
                                         String headimgurl,
                                         String system,
                                         String channel,
                                         @Field("code") String code) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .register(phone, sex, birthday, pass, nickname, headimgurl, system, channel, code);
    }


    public Observable<CodeBean> verification(String phone, String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .verification(phone, type);
    }

    public Observable<VerifyReturn> getVerifyCode(String phone) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .getVerifyCode(phone);
    }

    public Observable<Login> login(String phone, String pass) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .login(phone, pass);
    }

    public Observable<Login> loginByVerifyCode(String phone, String code) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .loginByVerifyCode(phone, code);
    }

    public Observable<Register> is_verification(String phone, String code) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .is_verification(phone, code);
    }

    public Observable<RecommenRoomBean> getrecommendroom(int categories, int page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .getrecommendroom(categories, page);
    }

    public Observable<CategorRoomBean> room_categories() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .room_categories();
    }

    public Observable<RecommenRoomBean> is_popular() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .is_popular();
    }

    public Observable<RecommenRoomBean> secret_chat() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .secret_chat();
    }

    public Observable<StartLoftBean> star_loft(int sex) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .star_loft(sex);
    }

    public Observable<BannerBean> carousel(String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .carousel(type);
    }

    public Observable<Register> create_or_edit_room(
            @Field("room_pass") String room_pass,
            @Field("room_name") String room_name,
            @Field("room_class") String room_class,
            @Field("room_type") String room_type,
            @Field("room_intro") String room_intro,
            String room_background,
            String uid,
            String room_covere,
            String free_mic) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .create_or_edit_room(room_pass, room_name, room_class, room_type,
                        room_intro, room_background, uid, room_covere, free_mic);
    }

    public Observable<RoomType> room_type(String xx) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .room_type(xx);
    }

    public Observable<RoomBg> room_background(String xx) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .room_background(xx);
    }

    public Observable<GiftListBean> gift_list(String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .gift_list(user_id);
    }

    public Observable<Microphone> microphone_status(@Field("uid") String uid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .microphone_status(uid);
    }

    /**
     user_id 主持或房主的用户id
     roomid 房间id
     b_user_id  被清除人的用户id
     清除魅力值接口
     */
    public Observable<BaseBean> clearMl(String user_id,String roomid,String b_user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .clearMl(user_id,roomid,b_user_id);
    }

    /**
     查询清除魅力值记录
     user_id 主持或房主的用户id
     roomid 房间uid
     查看清除魅力值记录接口
     */
    public Observable<ClearMlHistory> getClearMlHistory(String user_id,String roomid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .getClearMlHistory(user_id,roomid);
    }

    public Observable<UpVideoResult> up_microphone(@Field("uid") String uid,
                                                   @Field("user_id") String user_id,
                                                   @Field("position") String position, String phase) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .up_microphone(uid, user_id, position, phase);
    }


    public Observable<BaseBean> go_microphone(@Field("uid") String uid,
                                              @Field("user_id") String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .go_microphone(uid, user_id);
    }

    public Observable<EnterRoom> enter_room(@Field("uid") String uid,
                                            @Field("room_pass") String room_pass,
                                            @Field("user_id") String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .enter_room(uid, room_pass, user_id);
    }

    public Observable<BaseBean> upload_music(@Field("singer") String singer,
                                             @Field("music") String music,
                                             @Field("music_name") String music_name,
                                             @Field("upload_name") String upload_name) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .upload_music(singer, music, music_name, upload_name);
    }

    public Observable<DynamicDetailsBean> dynamic_details(@Field("user_id") String user_id,
                                                          @Field("sort") String sort,
                                                          @Field("page") String page,
                                                          @Field("id") String id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .dynamic_details(user_id, sort, page, id);
    }

    public Observable<BaseBean> send_dynamic(@Part("user_id") int user_id,
                                             @QueryMap Map<String, Object> op,
                                             @Part MultipartBody.Part audioFile,
                                             @Part MultipartBody.Part videoFile,
                                             @Part MultipartBody.Part img1File,
                                             @Part MultipartBody.Part img2File,
                                             @Part MultipartBody.Part img3File,
                                             @Part MultipartBody.Part img4File,
                                             @Part MultipartBody.Part img5File,
                                             @Part MultipartBody.Part img6File) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .send_dynamic(user_id, op, audioFile, videoFile, img1File, img2File, img3File, img4File, img5File, img6File);
    }

    public Observable<BaseBean> dynamic_comment(@Field("b_dynamic_id") int b_dynamic_id,
                                                @Field("user_id") int user_id,
                                                @Field("content") String content) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .dynamic_comment(b_dynamic_id, user_id, content);
    }

    public Observable<BaseBean> dynamic_collection(@Field("b_dynamic_id") int b_dynamic_id,
                                                   @Field("user_id") int user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .dynamic_collection(b_dynamic_id, user_id);
    }

    public Observable<DynamicSearchBean> dynamic_search(@Field("search") int search) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .dynamic_search(search);
    }

    public Observable<RecommendedDynamicBean> recommended_dynamic(@Field("user_id") String userId,
                                                                  @Field("page") String page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .recommended_dynamic(userId, page);
    }

    public Observable<RecommendedDynamicBean> new_dynamic(@Field("user_id") String user_id,
                                                          @Field("page") String page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .new_dynamic(user_id, page);
    }

    public Observable<TopicBean> topic(@Field("type") String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .topic(type);
    }

    public Observable<TopicDynamicBean> topic_dynamic(@Field("tags") String tags,
                                                      @Field("user_id") String user_id,
                                                      @Field("page") String page,
                                                      @Field("type") String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .topic_dynamic(tags, user_id, page, type);
    }


    public Observable<BaseBean> dynamic_praise(@Field("user_id") String user_id,
                                               @Field("target_id") String target_id,
                                               @Field("type") String type,
                                               @Field("hand") String hand) {

        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .dynamic_praise(user_id, target_id, type, hand);
    }

    public Observable<BaseBean> comment_praise(@Field("b_dynamic_id") int b_dynamic_id,
                                               @Field("user_id") int user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .comment_praise(b_dynamic_id, user_id);
    }

    public Observable<RecommenRoomBean> is_top(String xx) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .is_top(xx);
    }

    public Observable<BaseBean> is_pass(String uid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .is_pass(uid);
    }

    public Observable<BaseBean> shut_microphone(String uid, int position) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .shut_microphone(uid, position);
    }

    public Observable<BaseBean> open_microphone(String uid, int position) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .open_microphone(uid, position);
    }

    public Observable<OtherUser> get_other_user(@Field("uid") String uid,
                                                @Field("user_id") String user_id,
                                                @Field("my_id") String my_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .get_other_user(uid, user_id, my_id);
    }


    public Observable<BaseBean> shut_sound(String uid, int position) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .shut_sound(uid, position);
    }

    public Observable<JinSheng> is_sound(String uid, String is_sound) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .is_sound(uid, is_sound);
    }


    public Observable<JinSheng> remove_sound(String uid, String is_sound) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .remove_sound(uid, is_sound);
    }

    public Observable<JinSheng> is_black(String uid, String is_sound) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .is_black(uid, is_sound);
    }

    public Observable<BaseBean> room_mykeep(String uid, String is_sound) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .room_mykeep(uid, is_sound);
    }

    public Observable<BaseBean> remove_mykeep(String uid, String is_sound) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .remove_mykeep(uid, is_sound);
    }

    public Observable<Rank> leaderboard(@Field("class") String date,
                                        @Field("type") String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .leaderboard(date, type);
    }

    public Observable<Rank> jiedanbang(@Field("class") String date,
                                        @Field("type") String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .jiedanbang(date,type);
    }

    public Observable<Rank> getAllFamilyGxRank(String date) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .getAllFamilyGxRank(date);
    }

    public Observable<FamilyRank> getFamilyRank(String date) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .getFamilyRank(date);
    }

    public Observable<Rank> getFamilyGxRank(String date,String jzid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .getFamilyGxRank(date,jzid);
    }

    public Observable<Rank> meltingRank(String jzid,String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .meltingRank(jzid,type);
    }

    public Observable<BaseBean> is_admin(String uid, String is_sound) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .is_admin(uid, is_sound);
    }

    public Observable<BaseBean> remove_admin(String uid, String is_sound) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .remove_admin(uid, is_sound);
    }

    public Observable<BaseBean> quit_room(@Field("uid") String uid,
                                          @Field("followed_user_id") String followed_user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .quit_room(uid, followed_user_id);
    }

    public Observable<BaseBean> follow(@Field("uid") String uid,
                                       @Field("followed_user_id") String followed_user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .follow(uid, followed_user_id);
    }

    public Observable<BaseBean> cancel_follow(@Field("uid") String uid,
                                              @Field("followed_user_id") String followed_user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .cancel_follow(uid, followed_user_id);
    }

    public Observable<AdminUser> getRoomUsers(@Field("uid") String uid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .getRoomUsers(uid);
    }

    public Observable<SearchAdmin> search_user(String uid,
                                               String followed_user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .search_user(uid, followed_user_id);
    }

    public Observable<BaseBean> friend_list(String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .friend_list(user_id);
    }

    public Observable<BaseBean> not_speak_status(@Field("uid") String uid,
                                                 @Field("followed_user_id") String followed_user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .not_speak_status(uid, followed_user_id);
    }

    public Observable<MusicYinxiao> get_sound(String xx, String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .get_sound(xx, user_id);
    }

    public Observable<Yinxiao> get_music(String singer,
                                         String music_name, String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .get_music(singer, music_name, user_id);
    }

    public Observable<EmojiBean> emoji_list(String xx) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .emoji_list(xx);
    }

    public Observable<GifBean> get_emoji(String id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .get_emoji(id);
    }

    public Observable<SendGemResult> gift_queue(@Field("id") String id,
                                                @Field("uid") String uid,
                                                @Field("user_id") String user_id,
                                                @Field("fromUid") String fromUid,
                                                @Field("num") String num) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .gift_queue(id, uid, user_id, fromUid, num);
    }

    public Observable<SendGiftResult> gift_queue_six(@Field("id") String id,
                                                     @Field("uid") String uid,
                                                     @Field("fromUid") String fromUid,
                                                     @Field("num") String num) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .gift_queue_six(id, uid, fromUid, num);
    }

    public Observable<WaitList> addWaid(String uid,
                                        String user_id,
                                        String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .addWaid(uid, user_id, type);
    }

    public Observable<WaitList> waitList(String uid,
                                         String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .waitList(uid, user_id);
    }

    public Observable<BaseBean> delWait(String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .delWait(user_id);
    }

    public Observable<GoodsList> goodsList(String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .goodsList(user_id);
    }

    public Observable<RecommendedDynamicBean> get_GZ_dynamic(@Field("user_id") String user_id,
                                                             @Field("page") String page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).get_GZ_dynamic(user_id, page);
    }

    public Observable<MeYiDuiBean> getMeYiDui(@Field("user_id") String user_id,
                                              @Field("my_id") String my_id,
                                              @Field("type") String type,
                                              @Field("page") String page) {

        return mRepositoryManager.obtainRetrofitService(CommonService.class).getMeYiDui(user_id, my_id, type, page);
    }

    public Observable<UnreadBean> getUnreadMessage(@Field("user_id") String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getUnreadMessage(user_id);
    }

    public Observable<MessageYoBean> getMessageYo(@Field("user_id") String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getMessageYo(user_id);
    }

    public Observable<LookCommentBean> getLookComment(@Field("user_id") String user_id,
                                                      @Field("hf_uid") String hf_uid,
                                                      @Field("id") String id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getLookComment(user_id, hf_uid, id);
    }

    public Observable<AllCommentBean> getAlComment(@Field("id") String id,
                                                   @Field("user_id") String user_id,
                                                   @Field("page") String page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getAllComment(id, user_id, page);
    }

    public Observable<CommentBean> setComment(@Field("id") String id,
                                              @Field("pid") String pid,
                                              @Field("user_id") String user_id,
                                              @Field("content") String content) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).setComment(id, pid, user_id, content);
    }

    public Observable<UserFriend> userFriend(@Field("user_id") String user_id,
                                             @Field("type") String type,
                                             @Field("page") String page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                userFriend(user_id, type, page);
    }

    public Observable<UserBean> get_user_info(@Field("user_id") String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).get_user_info(user_id);
    }

    public Observable<MyFamily> getMyFamily(String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getMyFamily(user_id);
    }

    public Observable<UpdateVersion> getVersion() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getVersion();
    }

    public Observable<BaseBean> buy_ts(String user_id,String id){
        return mRepositoryManager.obtainRetrofitService(CommonService.class).buy_ts(user_id,id);
    }

    public Observable<BaseBean> buy_zq(String user_id,String id){
        return mRepositoryManager.obtainRetrofitService(CommonService.class).buy_zq(user_id,id);
    }

    public Observable<PersonalityBean> getPersonalityShop() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getPersonalityShop();
    }

    public Observable<MyDress> getMyDress(String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getMyDress(user_id);
    }

    public Observable<BaseBean> useDress(String type,String user_id,String id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).useDress(type,user_id,id);
    }


    public Observable<PayBean> rechargePay(@Field("user_id") String user_id,
                                           @Field("goods_id") String goods_id,
                                           @Field("type") String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                rechargePay(user_id, goods_id, type);
    }

    public Observable<Wxmodel> rechargeWxPay(@Field("user_id") String user_id,
                                             @Field("goods_id") String goods_id,
                                             @Field("type") String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                rechargeWxPay(user_id, goods_id, type);
    }

    public Observable<RecommendLabelBean> getLabel(@Field("xx") String xx) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getLabel(xx);
    }

    public Observable<SearchLabelBean> getSouSuoLabel(@Field("keywords") String keywords) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getSouSuoLabel(keywords);

    }

    public Observable<BaseBean> pull_black(@Field("user_id") String user_id,
                                           @Field("from_uid") String from_uid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                pull_black(user_id, from_uid);
    }

    public Observable<BaseBean> cancel_black(@Field("user_id") String user_id,
                                             @Field("from_uid") String from_uid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                cancel_black(user_id, from_uid);
    }

    public Observable<UserFriend> blackList(@Field("user_id") String user_id,
                                            @Field("page") String page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                blackList(user_id, page);
    }

    public Observable<ReportBean> report(@Field("xx") String xx) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                report(xx);
    }

    public Observable<BaseBean> send_report(@Field("user_id") String user_id,
                                            @Field("img") String img,
                                            @Field("type") String type,
                                            @Field("target") String target,
                                            @Field("report_type") String report_type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                send_report(user_id, img, type, target, report_type);
    }

    public Observable<OffiMessageBean> official_message(@Field("user_id") String user_id,int page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                official_message(user_id,page);
    }

    public Observable<BaseBean> clear_message(@Field("user_id") String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                clear_message(user_id);
    }

    public Observable<MiniOfficBean> mini_official(@Field("user_id") String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                mini_official(user_id);
    }


    public Observable<MoneyBean> my_store(@Field("user_id") String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                my_store(user_id);
    }

    //是否开启代充
    public Observable<IsOpenDc> isOpenDc(String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                isOpenDc(user_id);
    }

    public Observable<UserInfo> getUserInfoById(@Field("id") String id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getUserInfoById(id);
    }

    public Observable<BaseBean> helpRecharge(String b_user_id,String z_user_id,String num) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                helpRecharge(b_user_id,z_user_id,num);
    }

    public Observable<HelpRechargeHistory> getHelpRechargeHistories(@Field("user_id") String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getHelpRechargeHistories(user_id);
    }

    public Observable<CashHis> tixian_log(@Field("user_id") String user_id, @Field("page") int page,
                                          @Field("type") int type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                tixian_log(user_id, page, type);
    }

    public Observable<CashHis> exchange_log(@Field("user_id") String user_id, String page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                exchange_log(user_id, page);
    }

    public Observable<BaseBean> exchange(@Field("user_id") String user_id, String money) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                exchange(user_id, money);
    }

    public Observable<BindBean> ali_oauth_code(String xx) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                ali_oauth_code(xx);
    }

    public Observable<MyPersonalCebterTwoBean> getPersonalCabter(@Field("user_id") String user_id,
                                                                 @Field("from_uid") String from_uid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getPersonalCabter(user_id, from_uid);
    }

    public Observable<AliInfor> ali_oauth_token(String xx, String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                ali_oauth_token(xx, user_id);
    }

    public Observable<BaseBean> tixian(@Field("user_id") String user_id, String money, String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                tixian(user_id, money, type);
    }

    public Observable<PayBean> sfrz_start(@Field("user_id") String user_id,
                                          @Field("name") String name,
                                          @Field("idno") String idno) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                sfrz_start(user_id, name, idno);
    }

    public Observable<BaseBean> sfrz_query(@Field("user_id") String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                sfrz_query(user_id);
    }

    public Observable<CommentBean> ForGetPWD(@Field("phone") String phone,
                                             @Field("code") String code,
                                             @Field("pass") String pass) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).ForGetPWD(phone, code, pass);
    }

    public Observable<ConstellationBean> getConstellation(@Field("birthday") String birthday) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getConstellation(birthday);
    }

    public Observable<OtherBean> setUserInfo(@Field("id") String id,
                                             @Field("img") String img,
                                             @Field("nickname") String nickname,
                                             @Field("sex") String sex,
                                             @Field("birthday") String birthday,
                                             @Field("constellation") String constellation,
                                             @Field("city") String city,
                                             @Field("img_1") String img_1,
                                             @Field("img_2") String img_2,
                                             @Field("img_3") String img_3) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).setUserInfo(id, img, nickname, sex,
                birthday, constellation, city, img_1, img_2, img_3);
    }

    public Observable<AgreementBean> getAgreement(@Field("type") String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getAgreement(type);
    }

    public Observable<VipCenterBean> getVipCenter(@Field("user_id") String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getVipCenter(user_id);
    }

    public Observable<DengJiBean> getLevelCenter(@Field("user_id") String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getLevelCenter(user_id);
    }


    public Observable<IncomeBean> user_income(@Field("user_id") String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                user_income(user_id);
    }

    public Observable<SearchHis> searhList(@Field("user_id") String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                searhList(user_id);
    }

    public Observable<BaseBean> cleanSarhList(@Field("user_id") String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                cleanSarhList(user_id);
    }

    public Observable<Search> merge_search(@Field("user_id") String user_id, String keywords) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                merge_search(user_id, keywords);
    }

    public Observable<UserFriend> search_all(@Field("user_id") String user_id,
                                             @Field("keywords") String keywords,
                                             @Field("type") String type,
                                             @Field("page") String page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                search_all(user_id, keywords, type, page);
    }

    public Observable<RecommenRoomBean> search_all_room(@Field("user_id") String user_id,
                                                        @Field("keywords") String keywords,
                                                        @Field("type") String type,
                                                        @Field("page") String page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                search_all_room(user_id, keywords, type, page);
    }

    public Observable<RecommendedDynamicBean> search_all_dyni(@Field("user_id") String user_id,
                                                              @Field("keywords") String keywords,
                                                              @Field("type") String type,
                                                              @Field("page") String page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                search_all_dyni(user_id, keywords, type, page);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    public Observable<RoomUsersBean> getRoomUsers(String uid,
                                                  String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getRoomUsers(uid, user_id);
    }

    public Observable<SendGemResult> send_baoshi(String id,
                                                 String uid,
                                                 String token,
                                                 String fromUid,
                                                 String num) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                send_baoshi(id, uid, token, fromUid, num);
    }

    public Observable<SendGemResult> send_byk(
            String uid,
            String token,
            String fromUid,
            String num) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                send_byk(uid, token, fromUid, num);
    }

    public Observable<AgreeCpResult> handle_cp(
            String token,
            String user_id,
            String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                handle_cp(token, user_id, type);
    }

    public Observable<BaseBean> delete_cp(String token) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                delete_cp(token);
    }

    public Observable<TodayRecommBean> tj_user_three() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                tj_user_three();
    }

    public Observable<RoomTypeResult> room_type_three() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                room_type_three();
    }

    public Observable<RoomListResult> room_list_three(String typeId, String limit) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                room_list_three(typeId, limit);
    }

    public Observable<RoomListResult> tuijian_room_three(String limit) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                tuijian_room_three(limit);
    }

    public Observable<BestRoomResult> good_room_three() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                good_room_three();
    }

    public Observable<BannerBean> active_list_three() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                active_list_three();
    }

    public Observable<TodayRecommBean> new_user_three(String sex) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                new_user_three(sex);
    }

    public Observable<BaseBean> play_num_switch(String uid, String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                play_num_switch(uid, type);
    }

    public Observable<GetGapResult> check_gap(String uid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                check_gap(uid);
    }

    public Observable<GetOnlineUserResult> room_online_users(String uid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                room_online_users(uid);
    }

    public Observable<GetSortResult> waitSort(String uid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                waitSort(uid);
    }

    public Observable<GetGameListResult> selectSkillsList() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                selectSkillsList();
    }

    public Observable<GetGameListResult> getAllSkillsList() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getAllSkillsList();
    }

    public Observable<BaseBean> actionStartPd(String skillid, String uid, String serviceTime, String remark) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                actionStartPd(skillid, uid, serviceTime, remark);
    }

    public Observable<GetPaiDanInfoResult> getPdInfo(String uid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getPdInfo(uid);
    }

    public Observable<BaseBean> actionEndPd(String pd_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                actionEndPd(pd_id);
    }

    public Observable<SegmentListResult> getSkillLevelList(String skillId) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getSkillLevelList(skillId);
    }

    public Observable<BaseBean> actionApplySkill(
            @QueryMap Map<String, Object> op,
            @Part MultipartBody.Part audioFile, @Part MultipartBody.Part imgFile) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .actionApplySkill(op, audioFile, imgFile);
    }

    public Observable<MyGameSkillResult> getMySkillList() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getMySkillList();
    }

    public Observable<BaseBean> actionIsJd(String skill_id, String is_open) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                actionIsJd(skill_id, is_open);
    }

    public Observable<GetGameSetResult> getSkillSetInfo(String skill_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getSkillSetInfo(skill_id);
    }

    public Observable<GetPriceListResult> getSkillPriceList(String skill_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getSkillPriceList(skill_id);
    }

    public Observable<GetAreaListResult> getSkillAreaList(String skill_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getSkillAreaList(skill_id);
    }

    public Observable<GetGoodAtPositionListResult> getSkillPositionList(String skill_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getSkillPositionList(skill_id);
    }

    public Observable<BaseBean> actionSetPrice(String skill_id, String priceId) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                actionSetPrice(skill_id, priceId);
    }

    public Observable<BaseBean> actionSetArea(String skill_id, String priceId) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                actionSetArea(skill_id, priceId);
    }

    public Observable<BaseBean> actionSetPosition(String skill_id, String priceId) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                actionSetPosition(skill_id, priceId);
    }

    public Observable<GetSkillDetailInfoResult> getSkillInfo(String skill_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getSkillInfo(skill_id);
    }

    public Observable<OrderListResult> go_order_list(String keywords, String type, String page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                go_order_list(keywords, type, page);
    }

    public Observable<OrderDetailResult> go_order_details(String keywords, String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                go_order_details(keywords, type);
    }

    public Observable<BaseBean> go_receipt(String gm_orderid, String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                go_receipt(gm_orderid, type);
    }

    public Observable<BaseBean> go_apply_ljwf(String gm_orderid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                go_apply_ljwf(gm_orderid);
    }

    public Observable<BaseBean> go_ljwf_hand(String gm_orderid, String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                go_ljwf_hand(gm_orderid, type);
    }

    public Observable<BaseBean> go_finish(String gm_orderid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                go_finish(gm_orderid);
    }

    public Observable<BaseBean> go_cancel(String gm_orderid, String reason) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                go_cancel(gm_orderid, reason);
    }

    public Observable<BaseBean> go_apply_refund(String gm_orderid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                go_apply_refund(gm_orderid);
    }

    public Observable<BaseBean> go_apply_refund_hand(String gm_orderid, String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                go_apply_refund_hand(gm_orderid, type);
    }

    public Observable<BaseBean> go_appeal(String gm_orderid, String reason, String img1, String img2, String img3) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                go_appeal(gm_orderid, reason, img1, img2, img3);
    }

    public Observable<BaseBean> go_add_gm_com(String gm_orderid, String content, String star) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                go_add_gm_com(gm_orderid, content, star);
    }

    public Observable<UnReadOrderResult> go_unread_sum(String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                go_unread_sum(type);
    }

    public Observable<FamilyListResult> getFamilyList(String page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getFamilyList(page);
    }

    public Observable<RoomFamily> getRoomFamily(String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getRoomFamily(user_id);
    }

    public Observable<IsJoinFamily> isJoinFamily(String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                isJoinFamily(user_id);
    }

    public Observable<BxRecord> getBxRecord(String type,String page,String jzid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getBxRecord(type,page,jzid);
    }

    public Observable<FamilyIntroduce> getFamilyIntroduce() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getFamilyIntroduce();
    }

    public Observable<BxRecord> getLuckyRecord(String page,String jzid,String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getLuckyRecord(page,jzid,type);
    }

    public Observable<AddFamilyUserResult> getSearchUserList(String keywords, String page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getSearchUserList(keywords, page);
    }

    public Observable<AddFamilyUserResult> searchUser(String keywords, String page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                searchUser(keywords, page);
    }

    public Observable<AddFamilyUserResult> getUserDelList(String keywords,String family_id, String page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getUserDelList(keywords,family_id,page);
    }

    public Observable<CreatFamilyResult> actionCreateFamily(
            @QueryMap Map<String, Object> op,
            @Part MultipartBody.Part imgFile) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .actionCreateFamily(op, imgFile);
    }

    public Observable<BaseBean> actionAgreeJoin(String family_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                actionAgreeJoin(family_id);
    }

    public Observable<BaseBean> actionRefuseJoin(String family_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                actionRefuseJoin(family_id);
    }

    public Observable<MyFamilyResult> getMyFamilyInfo() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getMyFamilyInfo();
    }

    public Observable<GetFamilyDetailResult> getFamilyDetail(String family_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getFamilyDetail(family_id);
    }

    public Observable<String> joinPublicGroup(String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                joinPublicGroup(user_id);
    }

    public Observable<FamilySettingResult> getFamilyMoreList(String family_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getFamilyMoreList(family_id);
    }

    public Observable<BaseBean> actionSetSpeak(String family_id, String speakswitch) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                actionSetSpeak(family_id, speakswitch);
    }

    public Observable<BaseBean> actionSetClose(String family_id, String closeswitch) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                actionSetClose(family_id, closeswitch);
    }

    public Observable<BaseBean> actionDisFamily(String family_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                actionDisFamily(family_id);
    }

    public Observable<BaseBean> actionQuitFamily(String family_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                actionQuitFamily(family_id);
    }

    public Observable<GetAdminResult> getAdminList(String family_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getAdminList(family_id);
    }

    public Observable<BaseBean> actionCancelAdmin(String family_user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                actionCancelAdmin(family_user_id);
    }

    public Observable<BaseBean> actionSetAdmin(String family_user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                actionSetAdmin(family_user_id);
    }

    public Observable<GetFamilyUserListResult> getFamilyUserList(String family_user_id, String page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                getFamilyUserList(family_user_id, page);
    }

    public Observable<BaseBean> setFamilyManager(String familyId, String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                setFamilyManager(familyId, user_id);
    }

    public Observable<BaseBean> cancelFamilyManager(String familyId, String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                cancelFamilyManager(familyId, user_id);
    }

    public Observable<BaseBean> actionAddUser(String family_user_id, String users) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                actionAddUser(family_user_id, users);
    }

    public Observable<BaseBean> actionDelUser(String family_user_id, String users) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                actionDelUser(family_user_id, users);
    }
    public Observable<GetIsExitFamilyResult> get_is_family(String family_user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                get_is_family(family_user_id);
    }

    public Observable<BaseBean> share_room() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                share_room();
    }

    public Observable<IntroResult> family_introduce() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).
                family_introduce();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    public Observable<CollectionRoomListBean> getCollectionRoomList(@Field("user_id") String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getCollectionRoomList(user_id);
    }

    public Observable<Login> registerOther(@Field("type") String type,
                                           @Field("openid") String openid,
                                           @Field("phone") String phone,
                                           @Field("sex") String sex,
                                           @Field("birthday") String birthday,
                                           @Field("nickname") String nickname,
                                           @Field("headimgurl") String headimgurl,
                                           @Field("pass") String pass,
                                           @Field("system") String system,
                                           @Field("channel") String channel,
                                           @Field("code") String code) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).registerOther(type, openid, phone, sex, birthday, nickname, headimgurl, pass, system, channel, code);
    }

    public Observable<OtherBean> logOther(@Field("openid") String openid,
                                          @Field("type") String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).logOther(openid, type);
    }

    public Observable<CommentBean> delCommunity(String user_id, String id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).delCommunity(user_id, id);
    }

    public Observable<BaseBean> feedBack(@Field("user_id") String user_id,
                                         @Field("content") String content,
                                         @Field("img") String img) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).feedBack(user_id, content, img);
    }

    public Observable<ActiveListBean> activeList(@Field("xx") String xx) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).activeList(xx);
    }

    public Observable<VipBean> get_user_vip(String uId, String token) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).get_user_vip(uId, token);
    }

    public Observable<CommentBean> fenxiang(@Field("user_id") String user_id,
                                            @Field("target_id") String target_id,
                                            @Field("hand") String hand) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).fenxiang(user_id, target_id, hand);
    }

    public Observable<BaseBean> out_room(@Field("uid") String uid,
                                         @Field("user_id") String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).out_room(uid, user_id);
    }

    public Observable<GuanFangLianXiBean> guanfang(@Field("xx") String xx) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).guanfang(xx);
    }

    public Observable<BaseBean> UntyingAli(@Field("xx") String xx) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).UntyingAli(xx);
    }

    public Observable<EnterRoom> getRoomInfo(@Field("xx") String xx) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getRoomInfo(xx);
    }

    public Observable<ChaMoneyBean> getMoney(@Field("money") String money) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getMoney(money);
    }

    public Observable<FollowBean> is_follow(@Field("user_id") String user_id,
                                            @Field("from_uid") String from_uid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).is_follow(user_id, from_uid);
    }

    public Observable<MyPackBean> my_pack(@Field("type") String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).my_pack(type);
    }

    public Observable<CommentBean> dress_up(@Field("id") String id,
                                            @Field("type") String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).dress_up(id, type);
    }

    public Observable<CPDetailsBean> cp_desc(@Field("id") String id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).cp_desc(id);
    }

    public Observable<CommentBean> remove_cp(@Field("id") String id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).remove_cp(id);
    }

    public Observable<BaoXiangBean> getBoxInfo(@Field("xx") String xx) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getBoxInfo(xx);
    }

    public Observable<NldBean> getNld(String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getNld(user_id);
    }

    public Observable<MeltingPack> getMeltingPack(String userId) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getMeltingPack(userId);
    }

    public Observable<BaseBean> meltingGift(String userId,String id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).meltingGift(userId,id);
    }

    public Observable<OpenBoxBean> getAwardList(@Field("keysNum") int keysNum) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getAwardList(keysNum);
    }

    public Observable<OpenBoxBean> getCommonGx(int keysNum,String jzid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getCommonGx(keysNum,jzid,"1");
    }

    public Observable<OpenBoxBean> getQuickGx(int keysNum,String jzid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getQuickGx(keysNum,jzid,"1");
    }

    public Observable<OpenBoxBean> getAwardListColor(@Field("keysNum") int keysNum) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getAwardListColor(keysNum);
    }

    public Observable<CommentBean> actionBuyKeys(@Field("keysNum") String keysNum) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).actionBuyKeys(keysNum);
    }

    public Observable<XuYaoMiZuanBean> getMizuanNum(@Field("keysNum") String keysNum) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getMizuanNum(keysNum);
    }

    public Observable<BoxExchangeBean> getAwardWaresList(@Field("xx") String xx) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getAwardWaresList(xx);
    }

    public Observable<CommentBean> actionAwardExchange(@Field("waresId") String waresId) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).actionAwardExchange(waresId);
    }

    public Observable<BoxOpenRecordBean> getAwardRecordList(@Field("page") int page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getAwardRecordList(page);
    }

    public Observable<Jackpot> getJiangchi() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getJiangchi();
    }

    public Observable<GxJackpot> getGxJiangchi(int jzid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getGxJiangchi(jzid+"");
    }

    public Observable<BXShuoMingTextBean> getRewardInfo(@Field("xx") String xx) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getRewardInfo(xx);
    }

    public Observable<GXShuoMingTextBean> getGXShuoMing() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getGXShuoMing();
    }

    public Observable<GXShuoMingTextBean> getMeltingShuoMing() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getMeltingShuoMing();
    }

    public Observable<CommentBean> openCPCard(@Field("xx") String xx) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).openCPCard(xx);
    }

    public Observable<CommentBean> exchangeMizuanCard(@Field("id") String id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).exchangeMizuanCard(id);
    }

    public Observable<RoomRankBean> room_ranking(@Field("uid") String uid,
                                                 @Field("class") String mclass,
                                                 @Field("type") String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).room_ranking(uid, mclass, type);
    }

    public Observable<RoomInfoBean> getRoomInfoThree(@Field("uid") String uid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getRoomInfoThree(uid);
    }

    public Observable<CommentBean> del_comments_three(@Field("id") String id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).del_comments_three(id);
    }

    public Observable<RankExplainBean> get_shuoming(@Field("name") String name) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).get_shuoming(name);
    }

    public Observable<RoomRewardOneBean> room_gifts_log(String uid, int page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).room_gifts_log(uid, page);
    }

    public Observable<IncomeSumBean> roomIncomeCount(int page, String uid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).roomIncomeCount(page, uid);
    }

    public Observable<IncomeFragmentBean> getUserGiftList(int page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getUserGiftList(page);
    }

    public Observable<IncomeFragmentBean> getUserSendGiftList(int page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getUserSendGiftList(page);
    }

    public Observable<OpenTimeBean> openTime(@Field("uid") String uid, @Field("time") int time, @Field("muid") String muid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).openTime(uid, time, muid);
    }

    public Observable<CommentBean> colseTime(@Field("uid") String uid, @Field("muid") String muid) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).closeTime(uid, muid);
    }

    public Observable<HotBean> hot_room_three(int page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).hot_room_three(page);
    }

    public Observable<HotBean> room_list_by_cate(String mClass, int page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).room_list_by_cate(mClass, page);
    }

    public Observable<KzkBean> check_kzk() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).check_kzk();
    }

    public Observable<MiLiSZJiLuBean> go_order_log(int page,
                                                   int type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).go_order_log(page, type);
    }

    public Observable<DaShenZhuanShuBean> getGodInfo() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getGodInfo();
    }

    public Observable<MiLiIncomeBean> GmOrderIncomeCount() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).gmOrderIncomeCount();
    }

    public Observable<PaiDanCenterBean> getReceiptCenterList(int page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getReceiptCenterList(page);
    }

    public Observable<JieDanSetBean> getReceiptList() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getReceiptList();
    }

    public Observable<CommentBean> actionReceiptPd(@Field("skill_id") int skill_id,
                                                   @Field("status") String status) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).actionReceiptPd(skill_id, status);
    }

    public Observable<CommentBean> actionReceiptTime(@Field("start") int start,
                                                     @Field("end") int end) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).actionReceiptTime(start, end);
    }

    public Observable<JieDanRiQiBean> getJdDateList() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getJdDateList();
    }

    public Observable<CommentBean> actionReceiptDate(String days) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).actionReceiptDate(days);
    }

    public Observable<PayBean> miliRecharge(@Field("goods_id") String goods_id,
                                            @Field("type") String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).miliRecharge(goods_id, type);
    }

    public Observable<Wxmodel> miliWXRecharge(@Field("goods_id") String goods_id,
                                              @Field("type") String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).miliWXRecharge(goods_id, type);
    }

    public Observable<GoodsMiLiListBean> androidMiliList() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).androidMiliList();
    }

    public Observable<MainHomePageSkillBean> getAllSkillsLists() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getAllSkillsLists();
    }
    public Observable<Shengyou> shengyou() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).shengyou();
    }

    public Observable<MainHomePageBean> getSkillGodList(@Field("is_recom") String is_recom,
                                                        @Field("sex") String sex,
                                                        @Field("level_id") String level_id,
                                                        @Field("price_id") String price_id,
                                                        @Field("skill_id") String skill_id,
                                                        @Field("page") int page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getSkillGodList(is_recom, sex, level_id, price_id, skill_id, page);
    }

    public Observable<DuanWeiBean> getSkillLevelListJava(@Field("skill_id") String skill_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getSkillLevelListJava(skill_id);
    }

    public Observable<ScreenPriceBean> getSkillPriceListJava(@Field("skill_id") String skill_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getSkillPriceListJava(skill_id);
    }

    public Observable<ConfirmOrderSkillBean> orderUserSkillList(String user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).orderUserSkillList(user_id);
    }

    public Observable<Orderbean> go_add_order(String skill_apply_id,
                                              String start_time,
                                              String num,
                                              String remarks,
                                              String coupon_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).go_add_order(skill_apply_id, start_time, num, remarks, coupon_id);
    }


    public Observable<CommentBean> go_pay_game_yu_e(String gm_orderid,
                                                    String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).go_pay_game_yu_e(gm_orderid, type);
    }

    public Observable<PayBean> go_pay_game_zfb(String gm_orderid,
                                               String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).go_pay_game_zfb(gm_orderid, type);
    }

    public Observable<Wxmodel> go_pay_game_wx(String gm_orderid,
                                              String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).go_pay_game_wx(gm_orderid, type);
    }

    public Observable<GodCenterBean> getGodSkillInfo(String skill_apply_id,
                                                     int page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getGodSkillInfo(skill_apply_id, page);
    }

    public Observable<BaseBean> go_binding_device(String device_token,
                                                  String token) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).go_binding_device(device_token, token);
    }

    public Observable<NewbieTaskBean> new_task() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).new_task();
    }

    public Observable<NewbieTaskBean> daily_task() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).daily_task();
    }

    public Observable<CommentBean> recevice_task(String task_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).recevice_task(task_id);
    }

    public Observable<SignInDisplayBean> show_sign() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).show_sign();
    }

    public Observable<SignInBean> sign() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).sign();
    }

    public Observable<JBExchangeBean> jb_exchange_log(int page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).jb_exchange_log(page);
    }

    public Observable<ExchangeListBean> jb_exchange_list() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).jb_exchange_list();
    }

    public Observable<CommentBean> jb_exchange(String id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).jb_exchange(id);
    }

    public Observable<SignInDisplayBean> is_open_today() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).is_open_today();
    }

    public Observable<MyCouponsBean> my_coupon(String type,
                                               int page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).my_coupon(type, page);
    }

    public Observable<ChoiceCouponBean> my_coupontwo(String type,
                                                     int page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).my_coupontwo(type, page);
    }

    public Observable<UserTypeBean> getUserType() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getUserType();
    }

    public Observable<FamilyApplyBean> getFamilyApllyList(int page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getFamilyApllyList(page);
    }

    public Observable<CommentBean> actionAgreeFamily(String family_user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).actionAgreeFamily(family_user_id);
    }

    public Observable<CommentBean> actionRefuseFamily(String family_user_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).actionRefuseFamily(family_user_id);
    }

    public Observable<CommentBean> actionApplyFamily(String family_id) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).actionApplyFamily(family_id);
    }

    public Observable<CommentBean> actionEditFamily(@QueryMap Map<String, Object> op,
                                                    @Part MultipartBody.Part imgFile) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).actionEditFamily(op, imgFile);
    }

    public Observable<Winner> winners() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).winners();
    }
    public Observable<HotBean> quanbutop() {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).quanbutop();
    }
    public Observable<Rank> jiedanbang(String type) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).jiedanbang(type);
    }
    public Observable<PersonalityBean1> tszq(String user_id){
        return mRepositoryManager.obtainRetrofitService(CommonService.class).tszq(user_id);
    }
}
