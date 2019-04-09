package com.example.administrator.yefeng.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.eyes.Eyes;
import com.example.administrator.yefeng.util.ActivityUtil;
import com.example.administrator.yefeng.util.StatusBarUtil;
import com.example.administrator.yefeng.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    public T mPresenter;
    private Unbinder unbinder;
    private NetWorkReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtil.addActivity(this);
        //锁定竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        Eyes.translucentStatusBar(this,true);
        setContentView(getLayoutId());
        unbinder=ButterKnife.bind(this);
        //获取传递数据
        Intent intent = getIntent();
        //初始化布局
        initView(intent != null ? intent.getExtras() : null);
        initData();

        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetWorkReceiver();
        registerReceiver(receiver, filter);

    }

    public abstract int getLayoutId();

    public abstract void initView(Bundle bundle);

    public abstract void initData();

    public abstract T initPresenter();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        ActivityUtil.removeActivity(this);
        if (unbinder!=null){
            unbinder.unbind();
            unbinder=null;
        }
    }


    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    public  class NetWorkReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if (!isNetworkConnected(context)){
                Toast.makeText(context,"网络出现了点小问题",Toast.LENGTH_SHORT).show();
            }
        }
    }


}
