package com.example.administrator.yefeng.page.main.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.base.BaseActivity;
import com.example.administrator.yefeng.base.BasePresenter;
import com.example.administrator.yefeng.util.SharedUtilsT;
import com.example.administrator.yefeng.util.ToastUtil;

import butterknife.BindView;

public class WebActivity extends BaseActivity {

    @BindView(R.id.web_view)
    WebView webView;

    public AlertDialog dialog;
    public Button btnOk,btnCancel;


    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void initView(Bundle bundle) {
        SharedUtilsT.getUser();
        loadUrl();
    }

    public void  loadUrl(){

        String url=getIntent().getStringExtra("str1");
        webView.loadUrl(url);
        WebSettings settings=webView.getSettings();
//       允许加载脚本
        settings.setJavaScriptEnabled(true);
//        允许缩放
        settings.setSupportZoom(true);
//        自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
//      应用内打开网页
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                ToastUtil.showToast(getApplicationContext(),"网页走丢了！！");
                super.onReceivedError(view, request, error);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
