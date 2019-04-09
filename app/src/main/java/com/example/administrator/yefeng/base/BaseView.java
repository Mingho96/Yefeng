package com.example.administrator.yefeng.base;

import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.yefeng.util.Contstant;

public class BaseView {
    public static  void showContent(int who, View v1,View v2){
        if (who==Contstant.REFRESH_LAYOUT){
            v1.setVisibility(View.GONE);
            if (v2.getVisibility()==View.GONE)
                v2.setVisibility(View.VISIBLE);
        }else {
            v1.setVisibility(View.VISIBLE);
            v2.setVisibility(View.GONE);
        }
    }
}
