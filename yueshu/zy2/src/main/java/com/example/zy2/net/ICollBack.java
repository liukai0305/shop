package com.example.zy2.net;

import com.example.zy2.bean.CollBean;
import com.example.zy2.bean.HomeBean;

public interface ICollBack {
    void onSuccessHome(HomeBean homeBean);
    void onSuccessColl(CollBean collBean);
    void onFile(String str);

}
