package com.example.administrator.yefeng.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.administrator.yefeng.app.MyApplication;

public class SharedUtilsT {
    public static String uid;
    public static String uname;
    public static String nickName;
    public static String token;
    public static String ugender;
    public static String uemail;
    public static void createUser(String id,String uname,String nickName,String token,String gender,String email){
        SharedPreferences.Editor editor= MyApplication.getContext().getSharedPreferences("save_current", Context.MODE_PRIVATE ).edit();
        editor.putString("user_id",id);
        editor.putString("uname",uname );
        editor.putString("nickname",nickName);
        editor.putString("gender",gender);
        editor.putString("email",email);
        editor.putString("token",token);
        editor.apply();
    }

    public static void getUser(){
        SharedPreferences preferences= MyApplication.getContext().getSharedPreferences("save_current", Context.MODE_PRIVATE );
        uid=preferences.getString("user_id","");
        uname=preferences.getString("uname","");
        nickName=preferences.getString("nickname","");
        ugender=preferences.getString("gender","");
        uemail=preferences.getString("email","");
        token=preferences.getString("token", "");

    }
    public static  void clear(){
        SharedPreferences.Editor editor= MyApplication.getContext().getSharedPreferences("save_current", Context.MODE_PRIVATE ).edit();
        editor.clear();
        editor.apply();
    }
}
