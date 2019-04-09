package com.example.administrator.yefeng.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.administrator.yefeng.app.MyApplication;


public class SharedUtils {
    public static String id;
    public static String token;

    public static String phone;
    public static String uuname;
    public static String uunumber;
    public static String uuaddress;


    public static void createUser(String id,String token){
        SharedPreferences.Editor preferences= MyApplication.getContext().getSharedPreferences("save_current", Context.MODE_PRIVATE ).edit();
        preferences.putString("user_id",id);
        preferences.putString("token",token );
        //别忘记了提交
        preferences.apply();
    }

    public static void createUser(String id,String token,String phone,String uuname,String uunumber,String uuaddress){
        SharedPreferences.Editor preferences= MyApplication.getContext().getSharedPreferences("save_current", Context.MODE_PRIVATE ).edit();
        preferences.putString("user_id",id);
        preferences.putString("token",token );
        preferences.putString("phone",phone);
        preferences.putString("uuname",uuname);
        preferences.putString("uunumber",uunumber);
        preferences.putString("uuaddress",uuaddress);
        //别忘记了提交
        preferences.apply();
    }

    public static String getId(){
        return id;
    }
    public static String getToken(){
        return token;
    }
    public static String getPhone(){return phone;}

    public static void getUser(){
                SharedPreferences preferences=MyApplication.getContext().getSharedPreferences("save_current", Context.MODE_PRIVATE );
              id=preferences.getString("user_id","" );
               token=preferences.getString("token","" );
               phone=preferences.getString("phone","");
               uuname=preferences.getString("uuname","");
                uunumber=preferences.getString("uunumber","");
                uuaddress=preferences.getString("uuaddress","");

    }
    public void deleteUser(){

    }
}
