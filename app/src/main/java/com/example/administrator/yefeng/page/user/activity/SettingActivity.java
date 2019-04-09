package com.example.administrator.yefeng.page.user.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.app.MyApplication;
import com.example.administrator.yefeng.base.BaseActivity;
import com.example.administrator.yefeng.base.BasePresenter;
import com.example.administrator.yefeng.page.login.activity.LoginActivity;
import com.example.administrator.yefeng.page.login.activity.PwdResetActivity;
import com.example.administrator.yefeng.util.ActivityUtil;
import com.example.administrator.yefeng.util.IntentUtil;
import com.example.administrator.yefeng.util.MatelDialogUtil;
import com.example.administrator.yefeng.util.SharedUtilsT;
import com.example.administrator.yefeng.util.ToastUtil;
import com.example.administrator.yefeng.widget.TitleView;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {


    @BindView(R.id.title_view)
    TitleView titleView;

    private SharedPreferences sp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView(Bundle bundle) {
        titleView.setTitleCotent("设置");
    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }


    @SuppressLint("ApplySharedPref")
    @OnClick({R.id.versions, R.id.clean, R.id.exit_login,R.id.reset})
    public void onViewClicked(View view) {
        SharedUtilsT.getUser();
        switch (view.getId()) {
            case R.id.versions:
                ToastUtil.showToast(MyApplication.getContext(),"当前版本:V2.1");
                break;
            case R.id.clean:
                MatelDialogUtil.start(this,"清理中请等待...");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            MatelDialogUtil.end();
                            ToastUtil.showToast(MyApplication.getContext(),"清理完成");
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.exit_login:
                if(SharedUtilsT.token.equals("")){
                    ToastUtil.showToast(MyApplication.getContext(),"你还没登录，无须登出！");
                }else {
                   logout();
                }
                break;
            case R.id.reset:
                if(!SharedUtilsT.token.equals("")){
                    IntentUtil.initIntent2(PwdResetActivity.class);
                }else {
                    ToastUtil.showToast(SettingActivity.this,"请先登录");
                }
                break;
        }
    }

    public void logout(){
        sp = getApplicationContext().getSharedPreferences("save_current", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
        IntentUtil.initIntent2(LoginActivity.class);
        ActivityUtil.finishAll();
    }
}
