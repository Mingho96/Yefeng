package com.example.administrator.yefeng.util;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;

import com.example.administrator.yefeng.app.MyApplication;

public class IntentUtil {

    public static View.OnClickListener initIntent1(final Class<?> clz){
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyApplication.getContext(),clz);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContext().startActivity(intent);
            }
        };
        return listener;
    }

    public static View.OnClickListener initIntent1(final Class<?> clz, final String str1){
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyApplication.getContext(),clz);
                intent.putExtra("str1",str1);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContext().startActivity(intent);
            }
        };
        return listener;
    }

    public static View.OnClickListener initIntent1(final Class<?> clz, final String str1,final String str2){
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyApplication.getContext(),clz);
                intent.putExtra("str1",str1);
                intent.putExtra("str2",str2);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContext().startActivity(intent);
            }
        };
        return listener;
    }

    public static View.OnClickListener initIntent1(final Class<?> clz, final String str1,final String str2,String str3){
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyApplication.getContext(),clz);
                intent.putExtra("str1",str1);
                intent.putExtra("str2",str2);
                intent.putExtra("str3",str2);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContext().startActivity(intent);
            }
        };
        return listener;
    }


    public static void initIntent2(Class<?> clz){
        Intent intent=new Intent(MyApplication.getContext(),clz);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getContext().startActivity(intent);
    }

    public static void initIntent2(Class<?> clz,String str1){
        Intent intent=new Intent(MyApplication.getContext(),clz);
        intent.putExtra("str1",str1);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getContext().startActivity(intent);
    }

    public static void initIntent2(Class<?> clz,String str1,String str2){
        Intent intent=new Intent(MyApplication.getContext(),clz);
        intent.putExtra("str1",str1);
        intent.putExtra("str2",str2);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getContext().startActivity(intent);
    }

    public static void initIntent2(Class<?> clz,String str1,String str2,String str3){
        Intent intent=new Intent(MyApplication.getContext(),clz);
        intent.putExtra("str1",str1);
        intent.putExtra("str2",str2);
        intent.putExtra("str3",str3);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getContext().startActivity(intent);
    }

    public static void initIntentPhone2(String number){
        Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getContext().startActivity(intent);
    }

    public static View.OnClickListener initIntentPhone1(final String number){
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContext().startActivity(intent);
            }
        };
        return listener;
    }

    /**
     * 获取跳至拨号界面意图
     *
     * @param phoneNumber 电话号码
     */
    public static Intent getDialIntent(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取拨打电话意图
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.CALL_PHONE"/>}</p>
     *
     * @param phoneNumber 电话号码
     */
    public static Intent getCallIntent(String phoneNumber) {
        Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phoneNumber));
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取应用详情页面intent
     *
     * @return
     */
    public static Intent getAppDetailSettingIntent() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", MyApplication.getContext().getPackageName(), null));
        } else if(Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", MyApplication.getContext()
                    .getPackageName());
        }
        return localIntent;
    }

    /**
     * 获取网络设置intent
     *
     * @return
     */
    public static Intent getNetworkSettingIntent() {
        Intent intent;
        if(Build.VERSION.SDK_INT > 10) {
            intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings", "com.android.settings" +
                    ".WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        return intent;
    }

    /**
     * 获取wifi设置intent
     *
     * @return
     */
    public static Intent getWifiSettingIntent() {
        return new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
    }

    /**
     * 打开浏览器
     * */
    public static void toWeb(String url){
        Uri uri=Uri.parse(url);
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getContext().startActivity(intent);
    }
    public static View.OnClickListener toWeb2(final String url){
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse(url);
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContext().startActivity(intent);
            }
        };
    return listener;
    }
}
