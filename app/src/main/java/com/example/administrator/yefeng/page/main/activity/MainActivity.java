package com.example.administrator.yefeng.page.main.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.base.BaseActivity;
import com.example.administrator.yefeng.base.BasePresenter;
import com.example.administrator.yefeng.page.main.fragment.TopFragment;
import com.example.administrator.yefeng.page.main.fragment.HomePageFragment;
import com.example.administrator.yefeng.page.main.fragment.InformationFragment;
import com.example.administrator.yefeng.page.main.fragment.MineFragment;
import com.example.administrator.yefeng.util.ActivityUtil;
import com.example.administrator.yefeng.util.SharedUtils;
import com.example.administrator.yefeng.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {


    @BindView(R.id.fragment)
    FrameLayout fragment;
    @BindView(R.id.tab1_img)
    ImageView tab1Img;
    @BindView(R.id.tab1_text)
    TextView tab1Text;
    @BindView(R.id.tab2_img)
    ImageView tab2Img;
    @BindView(R.id.tab2_text)
    TextView tab2Text;
    @BindView(R.id.tab3_img)
    ImageView tab3Img;
    @BindView(R.id.tab3_text)
    TextView tab3Text;
    @BindView(R.id.home_linear)
    LinearLayout homeLinear;
    @BindView(R.id.information_linear)
    LinearLayout informationLinear;
    @BindView(R.id.mine_linear)
    LinearLayout mineLinear;
    @BindView(R.id.tab4_img)
    ImageView tab4Img;
    @BindView(R.id.tab4_text)
    TextView tab4Text;
    @BindView(R.id.other_linear)
    LinearLayout otherLinear;

    private Fragment homePageFragment;
    private Fragment informationFragment;
    private Fragment mineFragment;
    private Fragment blankFragment;

    private long exitTime = 0;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle bundle) {


        SharedUtils.getUser();
        initVisibility(0);

        if (bundle != null) {
            int mLastIndex = bundle.getInt("mLastIndex", 0);


            for (int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
                fragmentManager.popBackStack();
            }

            homePageFragment = null;
            informationFragment = null;
            mineFragment = null;
            blankFragment=null;
        }

    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    private void initVisibility(int i) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        hideFragment(ft);
        switch (i) {
            case 0:
                if (homePageFragment == null) {
                    homePageFragment = new HomePageFragment();
                    ft.add(R.id.fragment, homePageFragment);
                } else {
                    ft.show(homePageFragment);
                }
                setBottomStyle(0);
                break;
            case 1:
                if (blankFragment == null) {
                    blankFragment = new TopFragment();
                    ft.add(R.id.fragment, blankFragment);
                } else {
                    ft.show(blankFragment);
                }
                setBottomStyle(1);
                break;
            case 2:
                if (informationFragment == null) {
                    informationFragment = new InformationFragment();
                    ft.add(R.id.fragment, informationFragment);
                } else {
                    ft.show(informationFragment);
                }
                setBottomStyle(2);
                break;
            case 3:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    ft.add(R.id.fragment, mineFragment);
                } else {
                    ft.show(mineFragment);
                }
                setBottomStyle(3);
                break;
            default:
                break;
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        if (homePageFragment != null) {
            ft.hide(homePageFragment);
        }
        if (informationFragment != null) {
            ft.hide(informationFragment);
        }
        if (mineFragment != null) {
            ft.hide(mineFragment);
        }
        if (blankFragment != null) {
            ft.hide(blankFragment);
        }
    }

    private void initBottom() {
        tab1Img.setImageResource(R.mipmap.homepage);
        tab2Img.setImageResource(R.mipmap.inform);
        tab3Img.setImageResource(R.mipmap.personal);
        tab4Img.setImageResource(R.mipmap.top);
        tab1Text.setTextColor(getResources().getColor(R.color.content_light_black));
        tab2Text.setTextColor(getResources().getColor(R.color.content_light_black));
        tab3Text.setTextColor(getResources().getColor(R.color.content_light_black));
        tab4Text.setTextColor(getResources().getColor(R.color.content_light_black));
    }

    private void setBottomStyle(int setStyle) {
        switch (setStyle) {
            case 0:
                tab1Img.setImageResource(R.mipmap.homepage_2);
                tab1Text.setTextColor(getResources().getColor(R.color.navigation_blue));
                break;
            case 1:
                tab4Img.setImageResource(R.mipmap.top_blue);
                tab4Text.setTextColor(getResources().getColor(R.color.navigation_blue));
                break;
            case 2:
                tab2Img.setImageResource(R.mipmap.inform_2);
                tab2Text.setTextColor(getResources().getColor(R.color.navigation_blue));
                break;
            case 3:
                tab3Img.setImageResource(R.mipmap.personal_2);
                tab3Text.setTextColor(getResources().getColor(R.color.navigation_blue));
                break;
            default:
                break;
        }
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtil.showToast(this, "再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                ActivityUtil.finishAll();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.home_linear, R.id.information_linear, R.id.mine_linear,R.id.other_linear})
    public void onViewClicked(View view) {
        initBottom();
        switch (view.getId()) {
            case R.id.home_linear:
                initVisibility(0);
                break;
            case R.id.other_linear:
                initVisibility(1);
                break;
            case R.id.information_linear:
                initVisibility(2);
                break;
            case R.id.mine_linear:
                initVisibility(3);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
