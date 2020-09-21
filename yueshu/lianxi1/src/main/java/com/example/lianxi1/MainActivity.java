package com.example.lianxi1;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.lianxi1.base.BaseActivity;
import com.example.lianxi1.bean.ChapterBean;
import com.example.lianxi1.bean.ResultBean;
import com.example.lianxi1.interfaces.IHome;
import com.example.lianxi1.persenters.home.HomePersenter;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<IHome.Presenter> implements IHome.View {

    private static final String TAG ="MainActivity" ;
    @BindView(R.id.but)
    Button but;
    @BindView(R.id.tv)
    TextView tv;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//    }

    @OnClick(R.id.but)
    public void onViewClicked() {

    }

    @Override
    public void getIndexReturn(ResultBean bean) {
        Log.i(TAG, "getIndexReturn: "+bean.toString());
        tv.setText(bean.getData().getBanner().toString());
    }

    @Override
    public void getChapterReturn(ChapterBean chapterBean) {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected IHome.Presenter initPresenter() {
        return new HomePersenter();
    }

    @Override
    protected void initData() {
        presenter.getIndex();
    }


}
