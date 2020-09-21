package com.example.zy2.view;

import com.example.zy2.bean.CollBean;
import com.example.zy2.bean.HomeBean;

public interface IView {
    void getHomeData(HomeBean homeBean);
    void getCollData(CollBean collBean);
    void showToast(String str);
}
