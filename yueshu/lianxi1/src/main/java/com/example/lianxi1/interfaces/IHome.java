package com.example.lianxi1.interfaces;

import com.example.lianxi1.bean.ChapterBean;
import com.example.lianxi1.bean.ResultBean;

public interface IHome {
    interface View extends IBaseView{
        void getIndexReturn(ResultBean bean);
        void getChapterReturn(ChapterBean chapterBean);
    }
    interface Presenter extends IBasePersenter<View>{
        void getIndex();

        void getChapter();
    }
}
