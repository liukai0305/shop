package com.qutu.talk.di;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.qutu.talk.activity.AgreementActivity;
import com.qutu.talk.activity.ChargeActivity;
import com.qutu.talk.activity.ChargeMiLiActivity;
import com.qutu.talk.activity.EditGaoActivity;
import com.qutu.talk.activity.FamilyIntroduceActivity;
import com.qutu.talk.activity.FamilyMeltingRankActivity;
import com.qutu.talk.activity.GxRecordActivity;
import com.qutu.talk.activity.HelpActivity;
import com.qutu.talk.activity.HelpRechargeActivity;
import com.qutu.talk.activity.LunchActivity;
import com.qutu.talk.activity.MainActivity;
import com.qutu.talk.activity.MoreRoomListActivity;
import com.qutu.talk.activity.MyDressActivity;
import com.qutu.talk.activity.MyFriendActivity;
import com.qutu.talk.activity.PersonalityShopActivity;
import com.qutu.talk.activity.SearchDynamicActivity;
import com.qutu.talk.activity.SearchHisActivity;
import com.qutu.talk.activity.SearchResultActivity;
import com.qutu.talk.activity.SearchRoomActivity;
import com.qutu.talk.activity.SearchUserActivity;
import com.qutu.talk.activity.SetActivity;
import com.qutu.talk.activity.WaitFamilyVerifyActivity;
import com.qutu.talk.activity.dashen.DaShenExclusiveActivity;
import com.qutu.talk.activity.dashen.DaShenList2Activity;
import com.qutu.talk.activity.dashen.DaShenListActivity;
import com.qutu.talk.activity.dashen.GodPerCenterActivity;
import com.qutu.talk.activity.dashen.JieDanSetActivity;
import com.qutu.talk.activity.dashen.MiLiIncomeActivity;
import com.qutu.talk.activity.dashen.PaiDanCenterActivity;
import com.qutu.talk.activity.dynamic.CommentDetailsActivity;
import com.qutu.talk.activity.dynamic.DynamicDetailsActivity;
import com.qutu.talk.activity.dynamic.DynamicNewsActivity;
import com.qutu.talk.activity.dynamic.HeatTopicActivity;
import com.qutu.talk.activity.dynamic.MeZanActivity;
import com.qutu.talk.activity.dynamic.SocialReleaseActivity;
import com.qutu.talk.activity.dynamic.TopicTrendsActivity;
import com.qutu.talk.activity.family.CreateFamilyModifyActivity;
import com.qutu.talk.activity.family.CreateFamilySecondActivity;
import com.qutu.talk.activity.family.FamilyApplyActivity;
import com.qutu.talk.activity.family.FamilyDetailsActivity;
import com.qutu.talk.activity.family.FamilyListActivity;
import com.qutu.talk.activity.family.FamilyUserListActivity;
import com.qutu.talk.activity.family.SetFamilyAdminActivity;
import com.qutu.talk.activity.game.AppealActivity;
import com.qutu.talk.activity.game.EvaluateOrderActivity;
import com.qutu.talk.activity.game.OrderCenterActivity;
import com.qutu.talk.activity.game.OrderDetailActivity;
import com.qutu.talk.activity.game.OrderDetailGodActivity;
import com.qutu.talk.activity.game.applyskill.ApplyGameSkillActivity;
import com.qutu.talk.activity.game.applyskill.GameApplyingActivity;
import com.qutu.talk.activity.game.applyskill.GameDetailInfoActivity;
import com.qutu.talk.activity.game.applyskill.LOLHomeActivity;
import com.qutu.talk.activity.game.applyskill.MyGameSkillActivity;
import com.qutu.talk.activity.login.BingPhoneActivity;
import com.qutu.talk.activity.login.ForgetPsActivity;
import com.qutu.talk.activity.login.LoginActivity;
import com.qutu.talk.activity.login.ModifyPsActivity;
import com.qutu.talk.activity.login.RegisterActivity;
import com.qutu.talk.activity.login.SexActivity;
import com.qutu.talk.activity.login.UploadActivity;
import com.qutu.talk.activity.message.MessageActivity;
import com.qutu.talk.activity.message.MessageOfficeActivity;
import com.qutu.talk.activity.message.MessageSetActivity;
import com.qutu.talk.activity.message.MessageSetGroupActivity;
import com.qutu.talk.activity.message.ReportActivity;
import com.qutu.talk.activity.mine.BingCancelActivity;
import com.qutu.talk.activity.mine.CashMoneyActivity;
import com.qutu.talk.activity.mine.CouponsActivity;
import com.qutu.talk.activity.mine.DiamondHelpRechargeActivity;
import com.qutu.talk.activity.mine.HelpRechargeHistoryActivity;
import com.qutu.talk.activity.mine.MiLiRecordActivity;
import com.qutu.talk.activity.mine.MoneyActivity;
import com.qutu.talk.activity.mine.MyProfitActivity;
import com.qutu.talk.activity.mine.RealNameActivity;
import com.qutu.talk.activity.mine.WebRealNameActivity;
import com.qutu.talk.activity.my.BlackListActivity;
import com.qutu.talk.activity.my.CPActivity;
import com.qutu.talk.activity.my.GradeCenterActivity;
import com.qutu.talk.activity.my.MemberCoreActivity;
import com.qutu.talk.activity.my.ModifyDataActivity;
import com.qutu.talk.activity.my.MyFollowActivity;
import com.qutu.talk.activity.my.MyPackageActivity;
import com.qutu.talk.activity.my.MyPersonalCenterActivity;
import com.qutu.talk.activity.my.MyPersonalCenterTwoActivity;
import com.qutu.talk.activity.my.ProblemHelpActivity;
import com.qutu.talk.activity.order.ChoiceCouponActivity;
import com.qutu.talk.activity.order.ConfirmOrderActivity;
import com.qutu.talk.activity.room.AdminHomeActivity;
import com.qutu.talk.activity.room.AllRoomListActivity;
import com.qutu.talk.activity.room.CollectionRoomListActivity;
import com.qutu.talk.activity.room.FamilyGxRankActivity;
import com.qutu.talk.activity.room.GxRankActivity;
import com.qutu.talk.activity.room.IncomeSumActivity;
import com.qutu.talk.activity.room.MusicActivity;
import com.qutu.talk.activity.room.MyMusciActivity;
import com.qutu.talk.activity.room.RankActivity;
import com.qutu.talk.activity.room.RankExplainActivity;
import com.qutu.talk.activity.room.RoomRank2Activity;
import com.qutu.talk.activity.room.RoomRewardActivity;
import com.qutu.talk.activity.room.RoomSettingActivity;
import com.qutu.talk.activity.room.RoomUserOnlineActivity;
import com.qutu.talk.activity.room.SetAdminActivity;
import com.qutu.talk.activity.task.GoldExchangeActivity;
import com.qutu.talk.activity.task.TaskCenterActivity;
import com.qutu.talk.fragment.BeiBaoHeadKuangFragment;
import com.qutu.talk.fragment.BeiBaoIntoSEFragment;
import com.qutu.talk.fragment.BeiBaoTalkKuangFragment;
import com.qutu.talk.fragment.BeiBaoTalkapertureFragment;
import com.qutu.talk.fragment.CPAFragment;
import com.qutu.talk.fragment.CPBFragment;
import com.qutu.talk.fragment.CPCFragment;
import com.qutu.talk.fragment.CardFragment;
import com.qutu.talk.fragment.CashHisFragment;
import com.qutu.talk.fragment.CollectionHomeFragment;
import com.qutu.talk.fragment.CommFragment;
import com.qutu.talk.fragment.DressUpFragment;
import com.qutu.talk.fragment.EmojiFragment;
import com.qutu.talk.fragment.FamilyDetailsFragment;
import com.qutu.talk.fragment.FamilyGxRankFragment;
import com.qutu.talk.fragment.FamilyListFragment;
import com.qutu.talk.fragment.FamilyMeltingRankFragment;
import com.qutu.talk.fragment.FamilyRankFragment;
import com.qutu.talk.fragment.FollowDynamicFragment;
import com.qutu.talk.fragment.GemFragment;
import com.qutu.talk.fragment.GemstoneFragment;
import com.qutu.talk.fragment.IncomeFragment;
import com.qutu.talk.fragment.IndexPeiwanFragment;
import com.qutu.talk.fragment.IndexYuyinFragment;
import com.qutu.talk.fragment.MainCenterFragment;
import com.qutu.talk.fragment.MainCommunityFragment;
import com.qutu.talk.fragment.MainFindFragment;
import com.qutu.talk.fragment.MainHomeFragment;
import com.qutu.talk.fragment.MainMessageFragment;
import com.qutu.talk.fragment.MainPeiwanPageFragment;
import com.qutu.talk.fragment.MainYulePageFragment;
import com.qutu.talk.fragment.MessageFamilyListFragment;
import com.qutu.talk.fragment.MessageFansFragment;
import com.qutu.talk.fragment.MessageFragment;
import com.qutu.talk.fragment.MessageFriendFragment;
import com.qutu.talk.fragment.MiBiAndMiLiFragment;
import com.qutu.talk.fragment.MiLiSZJiLuFragment;
import com.qutu.talk.fragment.MiLiTXJiLuFragment;
import com.qutu.talk.fragment.MyConcernFragment;
import com.qutu.talk.fragment.MyCouponFragment;
import com.qutu.talk.fragment.MyDongTaiFragment;
import com.qutu.talk.fragment.MyGiftFragment;
import com.qutu.talk.fragment.MyInformationFragment;
import com.qutu.talk.fragment.MyMusicFragment;
import com.qutu.talk.fragment.NetMuscicFragment;
import com.qutu.talk.fragment.NewestDynamicFragment;
import com.qutu.talk.fragment.OrderAcceptFragment;
import com.qutu.talk.fragment.OrderBuyFragment;
import com.qutu.talk.fragment.PresentFragment;
import com.qutu.talk.fragment.RankFragment;
import com.qutu.talk.fragment.RankJiedanFragment;
import com.qutu.talk.fragment.RecomFragment;
import com.qutu.talk.fragment.RecomHomeFragment;
import com.qutu.talk.fragment.RecomHomePageFragment;
import com.qutu.talk.fragment.RoomRankFragment;
import com.qutu.talk.fragment.SkillFragment;
import com.qutu.talk.fragment.TopicFragment;
import com.qutu.talk.fragment.YuleFragment;

import dagger.Component;

//import com.qutu.talk.activity.ChargeActivity;

/**
 * 必须依赖arms包，dagger才会生效
 */
@ActivityScope
@Component(modules = CommonModule.class, dependencies = AppComponent.class)
public interface CommonComponent {
    //------Activity--------

    void inject(MainActivity activity);
    void inject(WaitFamilyVerifyActivity activity);
    void inject(FamilyIntroduceActivity activity);
    void inject(MyDressActivity activity);
    void inject(GxRankActivity activity);
    void inject(FamilyGxRankActivity activity);
    void inject(FamilyMeltingRankFragment familyMeltingRankFragment);
    void inject(FamilyListFragment familyListFragment);
    void inject(FamilyDetailsFragment familyDetailsFragment);
    void inject(FamilyRankFragment familyRankFragment);
    void inject(FamilyGxRankFragment familyGxRankFragment);
    void inject(FamilyMeltingRankActivity activity);
    void inject(GxRecordActivity activity);
    void inject(HelpRechargeActivity activity);
    void inject(PersonalityShopActivity activity);
    void inject(HelpRechargeHistoryActivity activity);
    void inject(DiamondHelpRechargeActivity activity);
    void inject(MyFriendActivity activity);

    void inject(LoginActivity activity);

    void inject(RegisterActivity activity);

    void inject(ForgetPsActivity activity);

    void inject(SexActivity activity);

    void inject(UploadActivity activity);

    void inject(RoomSettingActivity activity);

    void inject(AdminHomeActivity activity);

    void inject(RoomUserOnlineActivity activity);

    void inject(ApplyGameSkillActivity applyGameSkillActivity);

    void inject(MyGameSkillActivity myGameSkillActivity);

    void inject(LOLHomeActivity lolHomeActivity);

    void inject(GameDetailInfoActivity gameDetailInfoActivity);

    void inject(GameApplyingActivity gameApplyingActivity);

    void inject(OrderCenterActivity orderCenterActivity);

    void inject(OrderBuyFragment orderBuyFragment);

    void inject(OrderAcceptFragment orderAcceptFragment);

    void inject(OrderDetailActivity orderDetailActivity);

    void inject(OrderDetailGodActivity orderDetailGodActivity);

    void inject(AppealActivity appealActivity);

    void inject(EvaluateOrderActivity evaluateOrderActivity);

    void inject(FamilyListActivity familyListActivity);

    void inject(CreateFamilySecondActivity createFamilySecondActivity);

    void inject(MessageFamilyListFragment messageFamilyListFragment);

    void inject(MessageSetGroupActivity messageSetGroupActivity);

    void inject(SetFamilyAdminActivity setFamilyAdminActivity);

    void inject(FamilyUserListActivity setFamilyAdminActivity);

    void inject(MiLiRecordActivity miLiRecordActivity);

//    void inject(AdminHome257Activity activity);

    void inject(SetActivity activity);

    void inject(EditGaoActivity activity);

    void inject(RankActivity activity);

    void inject(SocialReleaseActivity socialReleaseActivity);

    void inject(SetAdminActivity activity);

    void inject(MusicActivity activity);

    void inject(MyMusciActivity activity);

    void inject(HeatTopicActivity activity);

    void inject(TopicTrendsActivity topicTrendsActivity);

    void inject(DynamicDetailsActivity dynamicDetailsActivity);

    void inject(ChargeActivity dynamicDetailsActivity);

    void inject(DynamicNewsActivity dynamicNewsActivity);

    void inject(MeZanActivity meZanActivity);

    void inject(CommentDetailsActivity commentDetailsActivity);

    void inject(MessageActivity messageActivity);

    void inject(MessageSetActivity messageActivity);

    void inject(ReportActivity messageActivity);

    void inject(MessageOfficeActivity messageActivity);

    void inject(MoneyActivity messageActivity);

    void inject(MyPersonalCenterActivity myPersonalCenterActivity);

    void inject(CashMoneyActivity myPersonalCenterActivity);

    void inject(RealNameActivity myPersonalCenterActivity);

    void inject(WebRealNameActivity myPersonalCenterActivity);

    void inject(ModifyDataActivity modifyDataActivity);

    void inject(ModifyPsActivity modifyPsActivity);

    void inject(BlackListActivity blackListActivity);

    void inject(AgreementActivity agreementActivity);

    void inject(MyProfitActivity agreementActivity);

    void inject(MyFollowActivity myFollowActivity);

    void inject(MemberCoreActivity memberCoreActivity);

    void inject(GradeCenterActivity gradeCenterActivity);

    void inject(SearchHisActivity gradeCenterActivity);

    void inject(SearchResultActivity gradeCenterActivity);

    void inject(SearchUserActivity gradeCenterActivity);

    void inject(SearchRoomActivity gradeCenterActivity);

    void inject(SearchDynamicActivity gradeCenterActivity);

    void inject(BingCancelActivity bingCancelActivity);

    void inject(CollectionRoomListActivity collectionRoomListActivity);

    void inject(BingPhoneActivity bingPhoneActivity);

    void inject(HelpActivity helpActivity);

    void inject(MyPackageActivity myPackageActivity);

    void inject(CPActivity cpActivity);

    void inject(RoomRank2Activity roomRank2Activity);

    void inject(RankExplainActivity roomRank2Activity);

    void inject(RoomRewardActivity roomRewardActivity);

    void inject(IncomeSumActivity incomeSumActivity);

    void inject(MoreRoomListActivity moreRoomListActivity);

    void inject(MyPersonalCenterTwoActivity myPersonalCenterTwoActivity);

    void inject(MiLiIncomeActivity miLiIncomeActivity);

    void inject(DaShenExclusiveActivity daShenExclusiveActivity);

    void inject(PaiDanCenterActivity paiDanCenterActivity);

    void inject(JieDanSetActivity jieDanSetActivity);

    void inject(ChargeMiLiActivity chargeMiLiActivity);

    void inject(DaShenListActivity daShenListActivity);

    void inject(ConfirmOrderActivity confirmOrderActivity);

    void inject(GodPerCenterActivity godPerCenterActivity);

    void inject(ProblemHelpActivity problemHelpActivity);

    void inject(TaskCenterActivity taskCenterActivity);

    void inject(GoldExchangeActivity goldExchangeActivity);

    void inject(CouponsActivity couponsActivity);

    void inject(ChoiceCouponActivity choiceCouponActivity);

    void inject(FamilyApplyActivity familyApplyActivity);

    void inject(FamilyDetailsActivity familyDetailsActivity);

    void inject(CreateFamilyModifyActivity createFamilyModifyActivity);


    //------Fragment--------

    void inject(MainCommunityFragment mainHomeFragment);

    void inject(MainMessageFragment mainHomeFragment);

    void inject(MainFindFragment mainHomeFragment);

    void inject(MainCenterFragment mainHomeFragment);

    void inject(RecomFragment recomFragment);

    void inject(RankFragment recomFragment);

    void inject(CommFragment commFragment);

    void inject(MyMusicFragment commFragment);

    void inject(NetMuscicFragment commFragment);

    void inject(TopicFragment topicFragment);

    void inject(EmojiFragment topicFragment);

    void inject(NewestDynamicFragment newestDynamicFragment);

    void inject(FollowDynamicFragment followDynamicFragment);

    void inject(MessageFragment followDynamicFragment);

    void inject(MessageFansFragment followDynamicFragment);

    void inject(MessageFriendFragment followDynamicFragment);

    void inject(CashHisFragment followDynamicFragment);

    void inject(MyGiftFragment myGiftFragment);

    void inject(MyDongTaiFragment myDongTaiFragment);

    void inject(MyConcernFragment myConcernFragment);

    void inject(GemstoneFragment mGemstoneFragment);

    void inject(DressUpFragment mDressUpFragment);

    void inject(GemFragment gemFragment);

    void inject(PresentFragment presentFragment);

    void inject(CardFragment cardFragment);

    void inject(BeiBaoHeadKuangFragment beiBaoHeadKuangFragment);

    void inject(BeiBaoTalkKuangFragment beiBaoTalkKuangFragment);

    void inject(BeiBaoIntoSEFragment beiBaoIntoSEFragment);

    void inject(BeiBaoTalkapertureFragment beiBaoTalkapertureFragment);

    void inject(CPAFragment cpaFragment);

    void inject(CPBFragment cpbFragment);

    void inject(CPCFragment cpcFragment);

    void inject(RecomHomeFragment recomFragment);

    void inject(RoomRankFragment roomRankFragment);

    void inject(MainHomeFragment roomRankFragment);

    void inject(IncomeFragment incomeFragment);

    void inject(MyInformationFragment myInformationFragment);

    void inject(SkillFragment skillFragment);

    void inject(MiBiAndMiLiFragment miBiAndMiLiFragment);

    void inject(MiLiSZJiLuFragment miLiSZJiLuFragment);

    void inject(MiLiTXJiLuFragment miLiTXJiLuFragment);

    void inject(RecomHomePageFragment recomHomePageFragment);

    void inject(MyCouponFragment myCouponFragment);

    void inject(CollectionHomeFragment collectionHomeFragment);

    void inject(MainPeiwanPageFragment mainHomePageFragment);

    void inject(MainYulePageFragment mainYulePageFragment);

    void inject(YuleFragment yuleFragment);

    void inject(AllRoomListActivity allRoomListActivity);

    void inject(DaShenList2Activity daShenList2Activity);

    void inject(RankJiedanFragment rankJiedanFragment);

    void inject(IndexYuyinFragment indexYuyinFragment);

    void inject(IndexPeiwanFragment indexPeiwanFragment);

    void inject(LunchActivity lunchActivity);
}
