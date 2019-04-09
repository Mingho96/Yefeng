package com.example.administrator.yefeng.page.user.activity;

import android.os.Bundle;

import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.base.BaseActivity;
import com.example.administrator.yefeng.base.BasePresenter;
import com.example.administrator.yefeng.widget.TitleView;

import butterknife.BindView;

public class AboutUsActivity extends BaseActivity {

    @BindView(R.id.title_view)
    TitleView titleView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    public void initView(Bundle bundle) {
        titleView.setTitleCotent("关于我们");
    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
