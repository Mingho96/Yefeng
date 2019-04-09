package com.example.administrator.yefeng.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.yefeng.page.main.activity.MainActivity;

import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    public T mPersenter;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(getFragmentLayoutId(),container,false);
        unbinder=ButterKnife.bind(this,view);
        initView(savedInstanceState);
        initView(view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder!=null){
            unbinder.unbind();
            unbinder=null;
        }
    }

    public abstract int getFragmentLayoutId();
    public abstract void initView(Bundle savedInstanceState);
    public abstract T getPresenter();
    public void initView(View view){

    }
}
