package com.example.administrator.yefeng.page.main.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.app.MyApplication;
import com.example.administrator.yefeng.base.BaseFragment;
import com.example.administrator.yefeng.base.BasePresenter;
import com.example.administrator.yefeng.model.LoanApi;
import com.example.administrator.yefeng.model.bean.GameDataListBean;
import com.example.administrator.yefeng.model.rxbean.ULoan;
import com.example.administrator.yefeng.page.main.adapter.ListviewTopAdapter;
import com.example.administrator.yefeng.util.IntentUtil;
import com.example.administrator.yefeng.util.MyStringCallback;
import com.example.administrator.yefeng.util.SharedUtilsT;
import com.example.administrator.yefeng.util.ToastUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

public class TopFragment extends BaseFragment {
    @BindView(R.id.rg_tab)
    RadioGroup rgTab;
    @BindView(R.id.lv_game_list)
    ListView lvGameList;
    Unbinder unbinder;

    List<ULoan> loans = new ArrayList<>();
    List<GameDataListBean> beans = new ArrayList<>();
    ListviewTopAdapter adapter;
    @BindView(R.id.top_show_empty)
    LinearLayout topShowEmpty;



    public TopFragment() {
        // Required empty public constructor
    }



    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_blank;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        adapter = new ListviewTopAdapter(getContext());
        initTab();
        initData();

    }

    private void initTab() {

        RadioButton radioButton= (RadioButton) rgTab.getChildAt(0);
        radioButton.setChecked(true);

        rgTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int pos=0;
                for (int i=0;i<group.getChildCount();i++){
                    RadioButton radioButton= (RadioButton) group.getChildAt(i);
                    if (radioButton.isChecked())
                        pos=i;

                }


                switch (pos){
                    case 0:
                        sortByRate();
                        adapter.setObjects(loans);
                        lvGameList.setAdapter(adapter);
                        break;
                    case 1:
                        sortByDate();
                        bindData();
                        break;
                    case 2:
                        sortBySize();
                        bindData();
                        break;
                }
            }
        });
    }

    private void initData() {
        beans.clear();

        OkHttpUtils.post()
                .url(LoanApi.BASE_URL + "itemList")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

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
                                beans.add(bean);
                            }

                            if (beans.size() > 0) {
                                bindData();
                                lvGameList.setVisibility(View.VISIBLE);
                                topShowEmpty.setVisibility(View.GONE);

                            } else {
                                ToastUtil.showToast(MyApplication.getContext(), "没有获取到数据");
                                lvGameList.setVisibility(View.GONE);
                                topShowEmpty.setVisibility(View.VISIBLE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void bindData() {
        loans.clear();
        SharedUtilsT.getUser();
        View.OnClickListener listener;
        for (int j = 0; j < beans.size(); j++) {

            if (SharedUtilsT.token.equals("")) {
                listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastUtil.showToast(getContext(), "请先登录");
                    }
                };
            } else {
                listener = IntentUtil.toWeb2(beans.get(j).getUrl());
            }

            ULoan bean = new ULoan(
                    beans.get(j).getIcon(),
                    beans.get(j).getType(),
                    beans.get(j).getName(),
                    beans.get(j).getRate(),
                    beans.get(j).getDescription(),
                    beans.get(j).getCategory(),
                    beans.get(j).getUrl(),
                    beans.get(j).getSize(),
                    listener);
            loans.add(bean);

        }

        adapter.setObjects(loans);
        lvGameList.setAdapter(adapter);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
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

    private void sortByRate(){
        Collections.sort(loans, new Comparator<ULoan>() {
            @Override
            public int compare(ULoan o1, ULoan o2) {

                float v1=o1.getGameRate();
                float v2=o2.getGameRate();
                return Float.compare(v2, v1);

            }
        });

    }


    private void sortByDate(){
        Collections.sort(beans, new Comparator<GameDataListBean>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public int compare(GameDataListBean o1, GameDataListBean o2) {
                int v1=o1.getId();
                int v2=o2.getId();
                return Integer.compare(v2,v1);
            }
        });
    }



    private void sortBySize(){
        Collections.sort(beans, new Comparator<GameDataListBean>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public int compare(GameDataListBean o1, GameDataListBean o2) {
                String d1=o1.getSize().substring(o1.getSize().length()-2);
                String d2=o2.getSize().substring(o2.getSize().length()-2);
                float v1=Float.parseFloat(o1.getSize().substring(0,o1.getSize().length()-2));
                float v2=Float.parseFloat(o2.getSize().substring(0,o2.getSize().length()-2));
                if (d1.equals("GB")){
                    v1*=1024;
                }


                if (d2.equals("GB")){
                    v2*=1024;
                }

                return Float.compare(v2,v1);
            }
        });
    }



}
