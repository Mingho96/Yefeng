package com.example.administrator.yefeng.page.user.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.base.BaseActivity;
import com.example.administrator.yefeng.base.BasePresenter;
import com.example.administrator.yefeng.page.main.activity.MainActivity;
import com.example.administrator.yefeng.page.main.activity.Splash2Activity;
import com.example.administrator.yefeng.util.One;

public class SplashActivity extends BaseActivity {

    private Handler handler=new Handler();
    private boolean isFrist=false;
    private One one=new One(this);
    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView(Bundle bundle) {

        isFrist=one.getState();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isFrist){
                    Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    one.setState();
                    Intent intent=new Intent(SplashActivity.this,Splash2Activity.class);
                    startActivity(intent);


                }

            }
        },2000);
    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
