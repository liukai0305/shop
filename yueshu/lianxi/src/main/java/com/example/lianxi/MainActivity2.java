package com.example.lianxi;

import android.view.View;
import android.widget.TextView;

import com.example.lianxi.base.BaseActivity;

import butterknife.BindView;

public class MainActivity2 extends BaseActivity {

    @BindView(R.id.tv)
    TextView tv;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
//        ButterKnife.bind(this);
//    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
    tv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    });
    }

    @Override
    protected int gatLayou() {
        return R.layout.activity_main2;
    }
}