package com.example.administrator.yefeng.util;


import android.content.Context;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/10/1.
 */

public class ScreenUtils {
    /**
     * 获取屏幕尺寸
     */
    public static WindowManager setWindowManger(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        return wm;

    }

    /**
     * 得到 屏幕高度
     *
     * @return
     */
    public static int CallPhoneHeight(Context context) {

        return setWindowManger(context).getDefaultDisplay().getHeight();
    }

    /**
     * 得到 屏幕宽度
     *
     * @return
     */
    public static int CallPhoneWidth(Context context) {
        return setWindowManger(context).getDefaultDisplay().getWidth();


    }

}
