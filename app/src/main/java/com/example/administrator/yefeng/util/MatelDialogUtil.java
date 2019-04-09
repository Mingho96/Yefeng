package com.example.administrator.yefeng.util;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

public class MatelDialogUtil {
    private static MaterialDialog dialog;

    public static void start(Context context){
        if (dialog==null){
            dialog=new MaterialDialog.Builder(context)
                    .progress(true,0)
                    .progressIndeterminateStyle(false)
                    .build();
        }
        dialog.show();
    }
    public static void start(Context context,String content){
        if (dialog==null){
            dialog=new MaterialDialog.Builder(context)
                    .content(content)
                    .progress(true,0)
                    .progressIndeterminateStyle(false)
                    .build();
        }
        dialog.show();
    }

    public static void end(){
        if (dialog!=null){
            dialog.dismiss();
        }
        dialog=null;
    }
}
