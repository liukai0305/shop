package com.example.xiangmuyi.ui.home.activity;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.homeadapter.PingLunAdapter;
import com.example.xiangmuyi.adapter.homeadapter.RecommendActivityAdapter1;
import com.example.xiangmuyi.base.BaseActivity;
import com.example.xiangmuyi.bean.homebean.HomeRecommendBean;
import com.example.xiangmuyi.common.Constants;
import com.example.xiangmuyi.common.CustomAvaterView;
import com.example.xiangmuyi.interfaces.IBasePersenter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class RecommendActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rcy_details)
    RecyclerView rcyDetails;
    @BindView(R.id.remark)
    RecyclerView remark;
    @BindView(R.id.cust)
    CustomAvaterView cust;
    @BindView(R.id.tv_num)
    TextView tvNum;
//    private Handler handler=new Handler(){
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//        }
//    };
    private RecommendActivityAdapter1 adapter1;

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recommend);
//    }
    private static final int MAX_HEADER_NUMBER = 5;

    @Override
    protected int getLayout() {
        return R.layout.activity_recommend;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        rcyDetails.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        ArrayList<HomeRecommendBean.DataBean.PostDetailBean> bean = (ArrayList<HomeRecommendBean.DataBean.PostDetailBean>) intent.getSerializableExtra("bean");
        adapter1 = new RecommendActivityAdapter1(bean, this);
        rcyDetails.setAdapter(adapter1);

        remark.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<HomeRecommendBean.DataBean.CommentsBean.AllCommentsBean> ping = (ArrayList<HomeRecommendBean.DataBean.CommentsBean.AllCommentsBean>) intent.getSerializableExtra("ping");
        PingLunAdapter pingLunAdapter = new PingLunAdapter(ping, this);
        remark.setAdapter(pingLunAdapter);

        cust.setMaxCount(MAX_HEADER_NUMBER);
        HomeRecommendBean.DataBean.PostDetailBean bean1 = Constants.curClickPost;

        Log.i("bean1", bean1.getContent());
        if (bean != null && bean1.getLikeDetails().size() > 0) {
            List<String> headers = new ArrayList<>();
            for (int i = 0; i < bean1.getImages().size(); i++) {
                if (i >= MAX_HEADER_NUMBER) break;
                headers.add(bean1.getLikeDetails().get(i).getHeadUrl());
            }
            cust.initDatas(headers);
            tvNum.setText(bean1.getLikeNumber() + "人赞过");
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected IBasePersenter initPersenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

//    @Override
//    public void onOptionsMenuClosed(Menu menu) {
//        getMenuInflater().inflate(R.menu.recommend,menu);
//        super.onOptionsMenuClosed(menu);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,1,1,"a");
        menu.add(1,2,1,"a");
        menu.add(1,3,1,"a");
        menu.add(1,4,1,"a");
        return super.onCreateOptionsMenu(menu);
    }
}