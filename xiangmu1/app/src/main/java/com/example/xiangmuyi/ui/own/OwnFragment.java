package com.example.xiangmuyi.ui.own;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.xiangmuyi.R;
import com.example.xiangmuyi.ui.own.IamActivity.MiActivity;
import com.example.xiangmuyi.ui.own.ownactivity.LoginActivity;
import com.example.xiangmuyi.ui.own.shezhi.ShiZhiActivity;
import com.example.xiangmuyi.utils.SpUtils;


public class OwnFragment extends Fragment {


    private ImageView mImgOwnBg;
    private ImageView mImgHead;
    private TextView mTvOwnNickName;
    private ImageView mImgOwnLv;
    private LinearLayout mLl1;
    private LinearLayout mLl2;
    private LinearLayout mLl3;
    private LinearLayout mLl4;
    private ConstraintLayout mConDesc;
    private RecyclerView mRecyclerOwn;
    private TextView mTvF1;
    private TextView mMAsso;
    private TextView mTvJo;
    private TextView mTvOwnJoin;
    private TextView mTvF2;
    private LinearLayout mMLL1;
    private TextView mTvF3;
    private LinearLayout mMLL2;
    private TextView mTvF4;
    private LinearLayout mMLL3;
    private TextView mTvF5;
    private LinearLayout mMLL4;
    private ImageView mShezhi;
    private ImageView mHuihua;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_own, container, false);
        initView(inflate);
        return inflate;

    }

    private void initView(View inflate) {
        mShezhi =inflate.findViewById(R.id.shezhi);
        mHuihua = inflate.findViewById(R.id.huihua);
        mImgOwnBg = inflate.findViewById(R.id.img_own_bg);
        mImgHead = inflate.findViewById(R.id.img_head);
        mTvOwnNickName = inflate.findViewById(R.id.tv_own_nickName);
        mImgOwnLv = inflate.findViewById(R.id.img_own_lv);
        mLl1 = inflate.findViewById(R.id.ll1);
        mLl2 = inflate.findViewById(R.id.ll2);
        mLl3 = inflate.findViewById(R.id.ll3);
        mLl4 = inflate.findViewById(R.id.ll4);
        mConDesc = inflate.findViewById(R.id.con_desc);
        mRecyclerOwn = inflate.findViewById(R.id.recycler_own);
        mTvF1 = inflate.findViewById(R.id.tv_f1);
        mMAsso = inflate.findViewById(R.id.mAsso);
        mTvJo = inflate.findViewById(R.id.tv_jo);
        mTvOwnJoin = inflate.findViewById(R.id.tv_own_join);
        mTvF2 = inflate.findViewById(R.id.tv_f2);
        mMLL1 = inflate.findViewById(R.id.mLL1);
        mTvF3 = inflate.findViewById(R.id.tv_f3);
        mMLL2 = inflate.findViewById(R.id.mLL2);
        mTvF4 = inflate.findViewById(R.id.tv_f4);
        mMLL3 = inflate.findViewById(R.id.mLL3);
        mTvF5 = inflate.findViewById(R.id.tv_f5);
        mMLL4 = inflate.findViewById(R.id.mLL4);
        mTvJo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        mTvOwnNickName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MiActivity.class));
            }
        });
        mShezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ShiZhiActivity.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        String avatar = SpUtils.getInstance().getString("avater");
        if (!TextUtils.isEmpty(avatar)){
            //设置头像的圆角
            RequestOptions options = RequestOptions.bitmapTransform(new CircleCrop());
            Glide.with(this).load(avatar).apply(options).into(mImgHead);

            Glide.with(this)
                    .load(avatar)
                    .apply(RequestOptions.bitmapTransform(new MiActivity.GlideBlurformation(getActivity())))
                    .into(mImgOwnBg);
        }
    }
}