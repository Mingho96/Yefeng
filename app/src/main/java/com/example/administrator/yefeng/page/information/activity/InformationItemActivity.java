package com.example.administrator.yefeng.page.information.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.base.BaseActivity;
import com.example.administrator.yefeng.base.BasePresenter;
import com.example.administrator.yefeng.model.bean.InfoDataListBean;
import com.example.administrator.yefeng.page.main.fragment.InformationFragment;
import com.example.administrator.yefeng.util.GlideUtils;
import com.example.administrator.yefeng.widget.TitleView;

import java.util.ArrayList;

import butterknife.BindView;

public class InformationItemActivity extends BaseActivity {

    @BindView(R.id.title_view)
    TitleView titleView;
    @BindView(R.id.information_title)
    TextView informationTitle;
    @BindView(R.id.information_img)
    ImageView informationImg;
    @BindView(R.id.information_name)
    TextView informationName;
    @BindView(R.id.information_content)
    WebView informationContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_information_item;
    }

    @Override
    public void initView(Bundle bundle) {
        titleView.setTitleCotent("帖子详情");
        Intent intent = getIntent();
        String typeId = intent.getStringExtra("str1");

        ArrayList<InfoDataListBean> data=InformationFragment.dataList;
        informationTitle.setText(data.get(Integer.parseInt(typeId)).getTitle());
        informationName.setText(data.get(Integer.parseInt(typeId)).getSource());
        informationContent.loadDataWithBaseURL(null, data.get(Integer.parseInt(typeId)).getContent(),"text/html","UTF-8", null);
        RequestOptions options=new RequestOptions().circleCrop();
        GlideUtils.loadUrl(data.get(Integer.parseInt(typeId)).getImage(),informationImg,options);
    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
