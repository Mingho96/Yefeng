package com.example.administrator.yefeng.page.homepage.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.app.MyApplication;
import com.example.administrator.yefeng.base.BaseActivity;
import com.example.administrator.yefeng.base.BasePresenter;
import com.example.administrator.yefeng.model.LoanApi;
import com.example.administrator.yefeng.model.bean.GameDataListBean;
import com.example.administrator.yefeng.model.bean.GetJsonArray;
import com.example.administrator.yefeng.model.rxbean.ULoan;
import com.example.administrator.yefeng.page.homepage.adapter.LoanAdapter;
import com.example.administrator.yefeng.util.IntentUtil;
import com.example.administrator.yefeng.util.MyStringCallback;
import com.example.administrator.yefeng.util.SharedUtilsT;
import com.example.administrator.yefeng.util.ToastUtil;
import com.example.administrator.yefeng.widget.TitleView;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 刷新有问题，断开网络刷新会一直刷新
 */
public class LoanActivity extends BaseActivity {


    @BindView(R.id.recyclerview_loan)
    RecyclerView recyclerviewLoan;
    @BindView(R.id.title_view)
    TitleView titleView;
    @BindView(R.id.smartRefresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.show_data)
    LinearLayout showData;
    @BindView(R.id.show_empty)
    LinearLayout showEmpty;
    private LoanAdapter adapter;
    private List<ULoan> list = new ArrayList<>();
    private List<GameDataListBean> dataList = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_loan;
    }

    @Override
    public void initView(Bundle bundle) {
        String category = getIntent().getStringExtra("str1");
        final int typeId = Integer.parseInt(getIntent().getStringExtra("str2"));
        titleView.setTitleCotent(category);
        showData.setVisibility(View.GONE);
        showEmpty.setVisibility(View.GONE);

        initDo(typeId);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter = new LoanAdapter(list);
        recyclerviewLoan.setLayoutManager(manager);
        recyclerviewLoan.setAdapter(adapter);

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                list.clear();
                dataList.clear();
                initDo(typeId);
                smartRefreshLayout.finishRefresh(1000);
            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                smartRefreshLayout.finishLoadMoreWithNoMoreData();
            }
        });
    }

    private void initDo(final int typeId) {

        OkHttpUtils.post()
                .url(LoanApi.BASE_URL + "itemCategory")
                .addParams("category", "" + typeId)
                .build().execute(new MyStringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {

            }

            @Override
            public void onResponse(String s, int i) {
                JSONObject jsonObject = null;
                String result = "";
                String data = "";
                try {
                    jsonObject = new JSONObject(s);
                    result = jsonObject.getString("result");
                    if (result.equals("success"))
                    {
                        data = jsonObject.getString("data");
                        JSONArray jsonArray=new JSONArray(data);
                        for (int j = 0; j < jsonArray.length(); j++) {
                            jsonObject = jsonArray.getJSONObject(j);
                            GameDataListBean bean = new Gson().fromJson(String.valueOf(jsonObject), GameDataListBean.class);
                            bean.setRate((float) jsonObject.getDouble("rating"));
                            dataList.add(bean);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Message message = new Message();
                if (dataList.size()>0){
                    Collections.sort(dataList);
                    bindData();
                    message.what=0x1111;
                }else {
                    message.what=0x1110;
                }
                handler.sendMessage(message);
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void bindData() {
        SharedUtilsT.getUser();
        View.OnClickListener listener;
        for (int j = 0; j < dataList.size(); j++) {
            if (SharedUtilsT.token.equals("")){
                listener=new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastUtil.showToast(LoanActivity.this,"请先登录");
                    }
                };
            }else {
                listener=IntentUtil.toWeb2(dataList.get(j).getUrl());
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
            list.add(bean);
        }
    }


    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x1111:
                    showEmpty.setVisibility(View.GONE);
                    showData.setVisibility(View.VISIBLE);
                    break;
                case 0x1110:
                    showData.setVisibility(View.GONE);
                    showEmpty.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

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
