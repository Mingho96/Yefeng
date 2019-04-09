package com.example.administrator.yefeng.page.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.base.BaseActivity;
import com.example.administrator.yefeng.base.BasePresenter;
import com.example.administrator.yefeng.util.IntentUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGABannerUtil;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;

public class Splash2Activity extends BaseActivity {
//    @BindView(R.id.guide_banner)
//    Banner guideBanner;

    @BindView(R.id.bga_banner)
    BGABanner bgaBanner;


   private List<Integer> imgList;
   private Integer[] imgIds=new Integer[]{R.drawable.guide_01,R.drawable.guide_02};

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash2;
    }

    @Override
    public void initView(Bundle bundle) {
        imgList=Arrays.asList(imgIds);

        bgaBanner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View itemView, @Nullable Object model, int position) {

            }
        });
        DisplayMetrics dm = new DisplayMetrics();
        int height = dm.heightPixels;
        int width = dm.widthPixels;
        BGALocalImageSize size = new BGALocalImageSize(width, height, width, height);
        List<View> view = new ArrayList<>();
        view.add(BGABannerUtil.getItemImageView(Splash2Activity.this, R.drawable.guide_01, size, ImageView.ScaleType.FIT_XY));
        view.add(BGABannerUtil.getItemImageView(Splash2Activity.this, R.drawable.guide_02, size, ImageView.ScaleType.FIT_XY));
        bgaBanner.setData(view);
        bgaBanner.setDelegate(new BGABanner.Delegate() {
            @Override
            public void onBannerItemClick(BGABanner banner, View itemView, @Nullable Object model, int position) {
                if (position == 1) {
                    IntentUtil.initIntent2(MainActivity.class);
                }
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
