package com.example.xiangmuyi.interfaces.users;


import com.example.xiangmuyi.bean.homebean.PersonalActivityBean;
import com.example.xiangmuyi.bean.homebean.PersonalArticleBean;
import com.example.xiangmuyi.bean.homebean.PersonalMsgBean;
import com.example.xiangmuyi.bean.homebean.PersonalPostBean;
import com.example.xiangmuyi.bean.homebean.UserInfoBean;
import com.example.xiangmuyi.interfaces.IBasePersenter;
import com.example.xiangmuyi.interfaces.IBaseView;

public interface IUsers {

    interface View extends IBaseView {

        void getPersonalReturn(UserInfoBean result);

        void getPersonalActivityReturn(PersonalActivityBean result);

        void getPersonalPostReturn(PersonalPostBean result);

        void getPersonalMsgReturn(PersonalMsgBean result);

        void getPersonalArticleReturn(PersonalArticleBean result);



    }

    interface Persenter extends IBasePersenter<View> {

        //获取个人信息
        void getPersonal();

        //个人详情动态接口
        void getPersonalActivity();

        //活动接口
        void getPersonalPost();

        //社团接口
        void getPersonalMsg();

        //文章
        void getPersonalArticle();


    }
}
