package com.example.administrator.yefeng.page.main.fragment;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.app.MyApplication;
import com.example.administrator.yefeng.base.BaseActivity;
import com.example.administrator.yefeng.base.BaseFragment;
import com.example.administrator.yefeng.base.BasePresenter;
import com.example.administrator.yefeng.model.LoanApi;
import com.example.administrator.yefeng.model.bean.GameDataListBean;
import com.example.administrator.yefeng.model.rxbean.ULoan;
import com.example.administrator.yefeng.page.homepage.adapter.HomeAdapter;
import com.example.administrator.yefeng.page.homepage.adapter.HomeContent;
import com.example.administrator.yefeng.page.homepage.adapter.HomeHeader;
import com.example.administrator.yefeng.page.login.activity.LoginActivity;
import com.example.administrator.yefeng.page.main.activity.MainActivity;
import com.example.administrator.yefeng.util.IntentUtil;
import com.example.administrator.yefeng.util.LoadingDialogUtil;
import com.example.administrator.yefeng.util.MyStringCallback;
import com.example.administrator.yefeng.util.ScreenUtils;
import com.example.administrator.yefeng.util.SharedUtilsT;
import com.example.administrator.yefeng.util.ToastUtil;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

public class HomePageFragment extends BaseFragment {
    @BindView(R.id.recyclerview_homepage)
    RecyclerView recyclerviewHomepage;
    @BindView(R.id.pull_layout)
    SmartRefreshLayout pullLayout;
    Unbinder unbinder;
    @BindView(R.id.fh_show_data)
    LinearLayout fhShowData;
    @BindView(R.id.fh_show_empty)
    LinearLayout fhShowEmpty;

    private HomeAdapter adapter;
    private AlertDialog dialog;
    Button btnOk, btnCancel;
    private List<ULoan> lists = new ArrayList<>();
    public static List<GameDataListBean> dataList = new ArrayList<>();


    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_homepage;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        SharedUtilsT.getUser();
        try {
            if (SharedUtilsT.token.equals("")) {
                showDialog();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        setRefreshListener();
//        LoadingDialogUtil.initDialog(getActivity(),1000);
        initData();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        adapter = new HomeAdapter(lists, MyApplication.getContext());
        recyclerviewHomepage.setLayoutManager(manager);
        recyclerviewHomepage.setAdapter(adapter);

        if (lists.size() < 0) {
            ToastUtil.showToast(MyApplication.getContext(), "服务器错误");
        }

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    private void setRefreshListener() {
        //下拉刷新
        pullLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initData();
//                recyclerviewHomepage.setAdapter(adapter);
                pullLayout.finishRefresh(1000);
            }
        });

        //加载更多数据(上拉刷新)
        pullLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pullLayout.finishLoadMoreWithNoMoreData();
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View dialogView = View.inflate(getContext(), R.layout.custom_dialog, null);
        builder.setView(dialogView)
                .setCancelable(false);
        dialog = builder.create();
        dialog.show();
        btnOk = dialogView.findViewById(R.id.btn_ok);
        btnCancel = dialogView.findViewById(R.id.btn_cancel);
        btnOk.setOnClickListener(dialogListener);
        btnCancel.setOnClickListener(dialogListener);
    }

    View.OnClickListener dialogListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_ok:
                    IntentUtil.initIntent2(LoginActivity.class);
                    dialog.dismiss();
                    break;
                case R.id.btn_cancel:
                    dialog.dismiss();
                    break;
            }
        }
    };

    private void initData() {
        lists.clear();
        dataList.clear();
        OkHttpUtils.post()
                .url(LoanApi.BASE_URL + "itemList")
                .build().execute(new MyStringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {
                if (BaseActivity.isNetworkConnected(getActivity())){
                    ToastUtil.showToast(getActivity(),"网络走丢了");
                }
                ToastUtil.showToast(MyApplication.getContext(), "请求失败");
            }

            @Override
            public void onResponse(String s, int i) {
                JSONObject jsonObject = null;
                String data = "";
                try {
                    jsonObject = new JSONObject(s);
                    data = jsonObject.getString("data");
                    JSONArray jsonArray = new JSONArray(data);
                    for (int j = 0; j < jsonArray.length(); j++) {
                        jsonObject = jsonArray.getJSONObject(j);
                        GameDataListBean bean = new Gson().fromJson(String.valueOf(jsonObject), GameDataListBean.class);
                        bean.setRate((float) jsonObject.getDouble("rating"));
                        dataList.add(bean);
                    }

                    Collections.sort(dataList);

                    if (dataList.size() > 0||lists.size()>0) {
                        bindData();
                        fhShowData.setVisibility(View.VISIBLE);
                        fhShowEmpty.setVisibility(View.GONE);
                        adapter.notifyDataSetChanged();
                        recyclerviewHomepage.setAdapter(adapter);

                    } else {
                        ToastUtil.showToast(MyApplication.getContext(), "没有获取到数据");
                        fhShowData.setVisibility(View.GONE);
                        fhShowEmpty.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void bindData() {
        SharedUtilsT.getUser();
        View.OnClickListener listener;
        for (int j = 0; j < dataList.size(); j++) {
            if (SharedUtilsT.token.equals("")) {
                listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastUtil.showToast(getContext(), "请先登录");
                    }
                };
            } else {
                listener = IntentUtil.toWeb2(dataList.get(j).getUrl());
            }

            ULoan bean = new ULoan(
                    dataList.get(j).getIcon(),
                    dataList.get(j).getType(),
                    dataList.get(j).getName(),
                    dataList.get(j).getRate(),
                    dataList.get(j).getDescription(),
                    dataList.get(j).getCategory(),
                    dataList.get(j).getUrl(),
                    dataList.get(j).getSize(),
                    listener);
            lists.add(bean);
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
