package com.example.xiangmuyi.persenter.users;


import com.example.xiangmuyi.base.BasePersenter;
import com.example.xiangmuyi.bean.homebean.PersonalActivityBean;
import com.example.xiangmuyi.bean.homebean.PersonalArticleBean;
import com.example.xiangmuyi.bean.homebean.PersonalMsgBean;
import com.example.xiangmuyi.bean.homebean.PersonalPostBean;
import com.example.xiangmuyi.bean.homebean.UserInfoBean;
import com.example.xiangmuyi.common.CommonSubscriber;
import com.example.xiangmuyi.interfaces.users.IUsers;
import com.example.xiangmuyi.model.HttpManager;
import com.example.xiangmuyi.utils.RxUtils;

public class PersonalPersenter extends BasePersenter<IUsers.View> implements IUsers.Persenter {
    /**
     * 获取个人信息
     */
    @Override
    public void getPersonal() {
        addSubscribe(HttpManager.getInstance().getTpApi().getPersonal()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<UserInfoBean>(mView) {

                    @Override
                    public void onNext(UserInfoBean userInfoBean) {
                        mView.getPersonalReturn(userInfoBean);
                    }
                }));
    }

    /**
     * 获取动态书
     */
    @Override
    public void getPersonalActivity() {
        addSubscribe(HttpManager.getInstance().getTpApi().getPersonalActivity()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<PersonalActivityBean>(mView) {

                    @Override
                    public void onNext(PersonalActivityBean personalActivityBean) {
                        mView.getPersonalActivityReturn(personalActivityBean);
                    }
                }));
    }

    /**
     * 获取活动数据
     */
    @Override
    public void getPersonalPost() {
        addSubscribe(HttpManager.getInstance().getTpApi().getPersonalPost()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<PersonalPostBean>(mView) {

                    @Override
                    public void onNext(PersonalPostBean personalPostBean) {
                        mView.getPersonalPostReturn(personalPostBean);
                    }
                }));
    }

    /**
     * 获取社团信息
     */
    @Override
    public void getPersonalMsg() {
        addSubscribe(HttpManager.getInstance().getTpApi().getPersonalMsg()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<PersonalMsgBean>(mView) {

                    @Override
                    public void onNext(PersonalMsgBean personalMsgBean) {
                        mView.getPersonalMsgReturn(personalMsgBean);
                    }
                }));
    }

    /**
     * 获取文章信息
     */
    @Override
    public void getPersonalArticle() {
        addSubscribe(HttpManager.getInstance().getTpApi().getPersonalArticle()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<PersonalArticleBean>(mView) {

                    @Override
                    public void onNext(PersonalArticleBean personalArticleBean) {
                        mView.getPersonalArticleReturn(personalArticleBean);
                    }
                }));

    }

}
