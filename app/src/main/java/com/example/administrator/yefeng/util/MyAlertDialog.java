package com.example.administrator.yefeng.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

public  class MyAlertDialog {
    public static Window window;
//    public static Activity activity;
    public static AlertDialog alertDialog;
    public static final int match= WindowManager.LayoutParams.MATCH_PARENT;
    public static final int wrapt=WindowManager.LayoutParams.WRAP_CONTENT;
    public static void initAlert(Context activity, int layout, int width, int height, int gravity){
        alertDialog=new AlertDialog.Builder(activity).create();
        alertDialog.show();
        alertDialog.setContentView(layout);
        window=alertDialog.getWindow();
        window.setLayout(width,height);
        window.setGravity(gravity);
    }

}
