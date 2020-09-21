package com.example.fenghaogoxiangmu.ui.own;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.fenghaogoxiangmu.R;
import com.example.fenghaogoxiangmu.base.BaseFragment;
import com.example.fenghaogoxiangmu.interfaces.IBasePersenter;
import com.example.fenghaogoxiangmu.ui.own.acivity.SiteActivity;
import com.example.fenghaogoxiangmu.ui.own.auth.activity.LoginActivity;
import com.example.fenghaogoxiangmu.utils.SpUtils;

import butterknife.BindView;


public class OwnFragment extends BaseFragment {


    @BindView(R.id.tv_o)
    TextView tvO;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.own_login)
    TextView ownLogin;
    @BindView(R.id.layout_login)
    LinearLayout layoutLogin;
    @BindView(R.id.layout_din)
    LinearLayout layoutDin;
    @BindView(R.id.layout_you)
    LinearLayout layoutYou;
    @BindView(R.id.layout_li)
    LinearLayout layoutLi;
    @BindView(R.id.layout_own)
    LinearLayout layoutOwn;
    @BindView(R.id.layout_zu)
    LinearLayout layoutZu;
    @BindView(R.id.layout_hui)
    LinearLayout layoutHui;
    @BindView(R.id.layout_location)
    LinearLayout layoutLocation;
    @BindView(R.id.layout_zhang)
    LinearLayout layoutZhang;
    @BindView(R.id.layout_lian)
    LinearLayout layoutLian;
    @BindView(R.id.layout_bang)
    LinearLayout layoutBang;
    @BindView(R.id.layout_yi)
    LinearLayout layoutYi;
    @BindView(R.id.ly)
    LinearLayout ly;
    @BindView(R.id.dizhi)
    ImageView dizhi;


    @Override
    protected int getLayout() {
        return R.layout.fragment_own;
    }

    @Override
    protected void initView() {
        ownLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        dizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SiteActivity.class));
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            String username = SpUtils.getInstance().getString("username");
            ownLogin.setText(username);
        }
    }


}