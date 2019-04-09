package com.example.administrator.yefeng.page.main.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.app.MyApplication;
import com.example.administrator.yefeng.base.BaseActivity;
import com.example.administrator.yefeng.base.BaseFragment;
import com.example.administrator.yefeng.base.BasePresenter;
import com.example.administrator.yefeng.base.BaseView;
import com.example.administrator.yefeng.model.LoanApi;
import com.example.administrator.yefeng.model.bean.GetJsonArray;
import com.example.administrator.yefeng.model.bean.InfoDataListBean;
import com.example.administrator.yefeng.model.rxbean.UInformation;
import com.example.administrator.yefeng.page.information.activity.InformationItemActivity;
import com.example.administrator.yefeng.page.information.adapter.InformationAdapter;
import com.example.administrator.yefeng.page.main.activity.MainActivity;
import com.example.administrator.yefeng.util.Contstant;
import com.example.administrator.yefeng.util.IntentUtil;
import com.example.administrator.yefeng.util.LoadingDialogUtil;
import com.example.administrator.yefeng.util.MyStringCallback;
import com.example.administrator.yefeng.util.ScreenUtils;
import com.example.administrator.yefeng.util.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

public class InformationFragment extends BaseFragment {


    @BindView(R.id.text_hot)
    TextView textHot;
    @BindView(R.id.fragment_hot)
    LinearLayout fragmentHot;
    @BindView(R.id.text_new)
    TextView textNew;
    @BindView(R.id.fragment_new)
    LinearLayout fragmentNew;
    @BindView(R.id.recyclerview_information)
    RecyclerView recyclerviewInformation;
    @BindView(R.id.fi_show_empty)
    LinearLayout fiShowEmpty;
    Unbinder unbinder;

    private List<UInformation> lists = new ArrayList<>();
    public static ArrayList<InfoDataListBean> dataList = new ArrayList<>();
    private InformationAdapter adapter;


    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_information;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setBanner(0);
        initList(0);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        adapter = new InformationAdapter(lists);
        recyclerviewInformation.setLayoutManager(manager);
        recyclerviewInformation.setAdapter(adapter);
    }

    private void initList(final int showWho) {
        getDataRequest(showWho);
    }

    private void getDataRequest(int showWho) {
        dataList.clear();
        lists.clear();
        String conn = "";
        if (showWho == 0) {
            conn += "recommend";
        } else {
            conn += "advisory";
        }
        OkHttpUtils.post()
                .url(LoanApi.BASE_URL + conn)
                .addParams("type", "" + conn)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        ToastUtil.showToast(MyApplication.getContext(), "请求失败");
                    }

                    @Override
                    public void onResponse(String s, int i) {

                        JSONObject jsonObject = null;
                        String data = "";
                        try {
                            jsonObject = new JSONObject(s);
                            data = jsonObject.getString("data");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        GetJsonArray getJsonArray;
                        try {
                            getJsonArray = new GetJsonArray(data);
                            for (JsonElement user : getJsonArray.getData()) {
                                InfoDataListBean bean = new Gson().fromJson(user, InfoDataListBean.class);
                                dataList.add(bean);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Collections.sort(dataList);

                        if (dataList.size() > 0) {
                            for (int j = 0; j < dataList.size(); j++) {
                                UInformation infor = new UInformation(
                                        dataList.get(j).getTitle(),
                                        dataList.get(j).getImage(),
                                        dataList.get(j).getSource(),
                                        dataList.get(j).getTime(),
                                        IntentUtil.initIntent1(InformationItemActivity.class, j + "")
                                );
                                lists.add(infor);
                            }
                            adapter.notifyDataSetChanged();
                            BaseView.showContent(Contstant.DATE_LAYOUT,recyclerviewInformation,fiShowEmpty);
                        } else {
                            ToastUtil.showToast(MyApplication.getContext(), "没有获取到数据");
                            BaseView.showContent(Contstant.REFRESH_LAYOUT,recyclerviewInformation,fiShowEmpty);

                        }

                    }
                });
    }


    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    @OnClick({R.id.fragment_hot, R.id.fragment_new})
    public void onViewClicked(View view) {
        initBanner();
        lists.clear();
        switch (view.getId()) {
            case R.id.fragment_hot:
                setBanner(0);
                initList(0);
                break;
            case R.id.fragment_new:
                setBanner(1);
                initList(1);
                break;
        }
    }

    //设置banner条
    private void setBanner(int i) {
        switch (i) {
            case 0:
                textHot.setTextSize(14);
                textHot.setTextColor(Color.WHITE);
                break;
            case 1:
                textNew.setTextSize(14);
                textNew.setTextColor(Color.WHITE);
                break;
        }
    }

    //初始化banner条颜色
    private void initBanner() {
        textHot.setTextSize(12);
        textNew.setTextSize(12);
        textHot.setTextColor(Color.parseColor("#ffeeeeee"));
        textNew.setTextColor(Color.parseColor("#ffeeeeee"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initList(0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
