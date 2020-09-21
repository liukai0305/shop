package com.example.tongpao.interfaces.home;

import com.example.tongpao.bean.PersonalActivityBean;
import com.example.tongpao.bean.PersonalArticleBean;
import com.example.tongpao.bean.PersonalMassBean;
import com.example.tongpao.bean.PersonalMsgBean;
import com.example.tongpao.bean.PersonalPostBean;
import com.example.tongpao.interfaces.IBasePresenter;
import com.example.tongpao.interfaces.IBaseView;

import java.util.List;

public interface IHomePersonal {

    //个人信息
    interface PersonalMsgView extends IBaseView {
        void getPersonalMsgReturn(PersonalMsgBean result);
    }

    interface PersonalMsgPresenter extends IBasePresenter<PersonalMsgView> {
        void getPersonalMsg();
    }

    //个人信息动态
    interface PersonalActivityView extends IBaseView{
        void getPersonalActivityReturn(PersonalActivityBean result);
    }
    interface PersonalActivityPresenter extends IBasePresenter<PersonalActivityView>{
        void getPersonalActivity();
    }

    //个人信息活动
    interface PersonalPostView extends IBaseView{
        void getPersonalPostReturn(PersonalPostBean result);
    }
    interface PersonalPostPresenter extends IBasePresenter<PersonalPostView>{
        void getPersonalPost();
    }

    //个人信息社团
    interface PersonalMassView extends IBaseView{
        void getPersonalMassReturn(PersonalMassBean result);
    }
    interface PersonalAMassPresenter extends IBasePresenter<PersonalMassView>{
        void getPersonalMass();
    }

    //个人信息文章
    interface PersonalArticleView extends IBaseView{
        void getPersonalArticleReturn(PersonalArticleBean result);
    }
    interface PersonalArticlePresenter extends IBasePresenter<PersonalArticleView>{
        void getPersonalArticle();
    }
}
