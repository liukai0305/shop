package com.example.xiangmuyi.utils;


import com.example.xiangmuyi.R;

public class UserUtils {

    public static int getResByLevel(int lv){
        switch (lv){
            case 1:
                return R.mipmap.lv1;
            case 2:
                return R.mipmap.lv2;
            case 3:
                return R.mipmap.lv3;
            case 4:
                return R.mipmap.lv4;
            case 5:
                return R.mipmap.lv5;
            case 6:
                return R.mipmap.lv6;
            case 7:
                return R.mipmap.lv7;
            case 8:
                return R.mipmap.lv8;
            case 9:
                return R.mipmap.lv9;
            case 10:
                return R.mipmap.lv10;
        }
        return 0;
    }

}
