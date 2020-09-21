package com.example.tongpao.ui.home.personal;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tongpao.R;
import com.example.tongpao.adapter.PersonalDataAdapter;
import com.example.tongpao.base.BaseFragment;
import com.example.tongpao.bean.PersonalMsgBean;
import com.example.tongpao.interfaces.home.IHomePersonal;
import com.example.tongpao.presenters.personal.PersonalPresenter;

import java.util.ArrayList;
import java.util.List;

public class DataFragment extends BaseFragment<IHomePersonal.PersonalMsgPresenter> implements IHomePersonal.PersonalMsgView {

    private RecyclerView data_rv;
    private List<PersonalMsgBean.DataBean> dataBeans;
    private PersonalDataAdapter personalDataAdapter;

    @Override
    protected IHomePersonal.PersonalMsgPresenter initPresenter() {
        return new PersonalPresenter();
    }

    @Override
    protected void initData() {
        Presenter.getPersonalMsg();
    }

    @Override
    protected void initView() {
        data_rv = getActivity().findViewById(R.id.data_rv);
        data_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBeans = new ArrayList<>();
        personalDataAdapter = new PersonalDataAdapter(getActivity(), dataBeans);
        data_rv.setAdapter(personalDataAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_data;
    }

    @Override
    public void getPersonalMsgReturn(PersonalMsgBean result) {
        if (result.getStatus().getStatusCode()==100){
            dataBeans.add(result.getData());
            personalDataAdapter.notifyDataSetChanged();
        }

    }
}