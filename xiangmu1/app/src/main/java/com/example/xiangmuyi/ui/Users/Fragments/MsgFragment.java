package com.example.xiangmuyi.ui.Users.Fragments;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiangmuyi.R;
import com.example.xiangmuyi.adapter.homeadapter.SheiTuanAdapter;
import com.example.xiangmuyi.base.BaseFragment;
import com.example.xiangmuyi.bean.homebean.PersonalActivityBean;
import com.example.xiangmuyi.bean.homebean.PersonalArticleBean;
import com.example.xiangmuyi.bean.homebean.PersonalMsgBean;
import com.example.xiangmuyi.bean.homebean.PersonalPostBean;
import com.example.xiangmuyi.bean.homebean.UserInfoBean;
import com.example.xiangmuyi.interfaces.users.IUsers;
import com.example.xiangmuyi.persenter.users.PersonalPersenter;

import java.util.ArrayList;

import butterknife.BindView;


public class MsgFragment extends BaseFragment<IUsers.Persenter> implements IUsers.View {

    @BindView(R.id.rcy)
    RecyclerView rcy;
    private ArrayList<PersonalMsgBean.DataBean> list;
    private SheiTuanAdapter sheiTuanAdapter;



    @Override
    protected int getLayout() {
        return R.layout.fragment_msg;
    }

    @Override
    protected void initView() {
        rcy.setLayoutManager(new LinearLayoutManager(getActivity()));
        list= new ArrayList<>();
        sheiTuanAdapter=new SheiTuanAdapter(list,getActivity());
        rcy.setAdapter(sheiTuanAdapter);
    }

    @Override
    protected IUsers.Persenter initPersenter() {
        return new PersonalPersenter();
    }

    @Override
    protected void initData() {
        persenter.getPersonalMsg();
    }


    @Override
    public void getPersonalReturn(UserInfoBean result) {

    }

    @Override
    public void getPersonalActivityReturn(PersonalActivityBean result) {

    }

    @Override
    public void getPersonalPostReturn(PersonalPostBean result) {

    }

    @Override
    public void getPersonalMsgReturn(PersonalMsgBean result) {
        list.addAll(result.getData());
        sheiTuanAdapter.notifyDataSetChanged();
    }

    @Override
    public void getPersonalArticleReturn(PersonalArticleBean result) {

    }
}