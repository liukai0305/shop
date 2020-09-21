package com.example.xiangmuyi.ui.home.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.homeadapter.PhotoImageAdapter;
import com.example.xiangmuyi.base.BaseActivity;
import com.example.xiangmuyi.bean.homebean.HomePhtotBean;
import com.example.xiangmuyi.common.CustomAvaterView;
import com.example.xiangmuyi.interfaces.IBasePersenter;

import java.util.List;

import butterknife.BindView;

public class PhotoActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_tou)
    ImageView ivTou;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content1)
    TextView tvContent1;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.coustomAvaterBox)
    CustomAvaterView coustomAvaterBox;
    @BindView(R.id.tv_praise)
    TextView tvPraise;
    @BindView(R.id.vw)
    View vw;
    @BindView(R.id.tv_comment1)
    TextView tvComment1;
    @BindView(R.id.rv_comment)
    RecyclerView rvComment;
    private int pos;
    private List<HomePhtotBean.DataBean.DynamicsBean.ImagesBean> images;
    private PhotoImageAdapter adapter;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_photo);
//    }

    @Override
    protected int getLayout() {
        return R.layout.activity_photo;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        List<HomePhtotBean.DataBean.DynamicsBean> photo = (List<HomePhtotBean.DataBean.DynamicsBean>) intent.getSerializableExtra("photo");
        pos = intent.getIntExtra("pos", 0);
        HomePhtotBean.DataBean.DynamicsBean bean = photo.get(pos);
        Glide.with(this).load(bean.getHeadUrl()).into(ivTou);
        tvName.setText(bean.getNickName());
        tvTime.setText(bean.getCreateTime());
        tvContent1.setText(bean.getContent());
        rv.setLayoutManager(new LinearLayoutManager(this));
        images = bean.getImages();
        adapter = new PhotoImageAdapter(images, this);
        rv.setAdapter(adapter);


    }

    @Override
    protected IBasePersenter initPersenter() {
        return null;
    }

    @Override
    protected void initData() {

    }


}