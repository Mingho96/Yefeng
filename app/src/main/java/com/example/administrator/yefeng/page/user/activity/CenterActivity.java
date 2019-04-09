package com.example.administrator.yefeng.page.user.activity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.base.BaseActivity;
import com.example.administrator.yefeng.base.BasePresenter;
import com.example.administrator.yefeng.util.IntentUtil;
import com.example.administrator.yefeng.widget.TitleView;

import butterknife.BindView;
import butterknife.OnClick;

public class CenterActivity extends BaseActivity {

    @BindView(R.id.title_view)
    TitleView titleView;
    @BindView(R.id.go_help)
    LinearLayout goHelp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_center;
    }

    @Override
    public void initView(Bundle bundle) {
        titleView.setTitleCotent("客服中心");
    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }


    @OnClick(R.id.go_help)
    public void onViewClicked() {
        IntentUtil.initIntent2(AboutUsActivity.class);
    }
}
