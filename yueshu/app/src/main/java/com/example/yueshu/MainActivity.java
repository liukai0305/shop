package com.example.yueshu;


import android.widget.Toast;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.yueshu.adapter.RcyAdapter;
import com.example.yueshu.base.BaseActivity;
import com.example.yueshu.bean.UsersBean;
import com.example.yueshu.home.MainPresenterImpl;
import com.example.yueshu.home.contract.IMainContract;
import java.util.ArrayList;

public class MainActivity extends BaseActivity<MainPresenterImpl> implements IMainContract.ImainView {


    private ArrayList<UsersBean.ResultsBean> list;
    private RecyclerView rcy;
    private RcyAdapter rcyAdapter;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        initView();
//
//
//    }

    @Override
    protected void initPresenter() {
        mP=new MainPresenterImpl();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        rcy = (RecyclerView) findViewById(R.id.rcy);
        rcy.setLayoutManager(new LinearLayoutManager(this));
        rcy.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        list = new ArrayList<>();
        rcyAdapter = new RcyAdapter(list, this);
        rcy.setAdapter(rcyAdapter);

    }

    @Override
    protected void initData() {
        mP.getData();
    }



    @Override
    public void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getData(UsersBean usersBean) {
        list.addAll(usersBean.getResults());
        rcyAdapter.notifyDataSetChanged();
    }


}