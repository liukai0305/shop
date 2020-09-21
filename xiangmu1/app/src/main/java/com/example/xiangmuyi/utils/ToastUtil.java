package com.example.xiangmuyi.utils;

import android.widget.Toast;

import com.example.xiangmuyi.app.MyApp;


public class ToastUtil {
    public static void showShort(String msg){
        Toast.makeText(MyApp.app,msg,Toast.LENGTH_SHORT).show();
    }
    public static void showLong(String msg){
        Toast.makeText(MyApp.app,msg,Toast.LENGTH_LONG).show();
    }
}
