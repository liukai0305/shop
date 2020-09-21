package com.example.lianxi1.persenters.home;

import com.example.lianxi1.base.BasePersenter;
import com.example.lianxi1.bean.ChapterBean;
import com.example.lianxi1.bean.ResultBean;
import com.example.lianxi1.common.CommonSubscriber;
import com.example.lianxi1.interfaces.IHome;
import com.example.lianxi1.model.HttpManager;
import com.example.utils.RxUtils;

import io.reactivex.disposables.Disposable;

public class HomePersenter extends BasePersenter<IHome.View> implements IHome.Presenter {
    @Override
    public void getIndex() {
        Disposable with = HttpManager.getInstance().getYunApi()
                .getIndex()
                .compose(RxUtils.<ResultBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<ResultBean>(mView) {
                    @Override
                    public void onNext(ResultBean bean) {
                        mView.getIndexReturn(bean);
                    }
                });
        addSubscribe(with);

    }

    @Override
    public void getChapter() {
        //获取网络数据的接口调用
        Disposable disposable = HttpManager.getInstance().getYunApi()
                .getChapters().compose(RxUtils.<ChapterBean>rxScheduler())  //线程切换
                //请求接口返回的封装抽取失败和完成的方法
                 .subscribeWith(new CommonSubscriber<ChapterBean>(mView) {
                    @Override
                    public void onNext(ChapterBean resultBean) {
                        mView.getChapterReturn(resultBean);
                    }
                });
        addSubscribe(disposable);
    }

}
