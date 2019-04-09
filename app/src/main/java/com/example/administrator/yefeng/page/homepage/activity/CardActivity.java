package com.example.administrator.yefeng.page.homepage.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.app.MyApplication;
import com.example.administrator.yefeng.base.BaseActivity;
import com.example.administrator.yefeng.base.BasePresenter;
import com.example.administrator.yefeng.model.LoanApi;
import com.example.administrator.yefeng.model.bean.CardImage;
import com.example.administrator.yefeng.model.bean.GetAddState;
import com.example.administrator.yefeng.page.homepage.adapter.CardAdapter;
import com.example.administrator.yefeng.page.homepage.adapter.CardBean;
import com.example.administrator.yefeng.util.IntentUtil;
import com.example.administrator.yefeng.util.MyStringCallback;
import com.example.administrator.yefeng.util.SharedUtils;
import com.example.administrator.yefeng.util.ToastUtil;
import com.example.administrator.yefeng.widget.TitleView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

public class CardActivity extends BaseActivity {

    @BindView(R.id.recyclerview_card_list)
    RecyclerView recyclerView;
    @BindView(R.id.title_view)
    TitleView titleView;

    private String cardId=null;
    private String url=null;
    private CardAdapter adapter;
    private List<CardBean> lists=new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_card;
    }

    @Override
    public void initView(Bundle bundle) {
        titleView.setTitleCotent("信用卡");
        initDa();
        LinearLayoutManager manager=new LinearLayoutManager(this);
        adapter=new CardAdapter(lists);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {

    }

    private void initDa(){
        OkHttpUtils.post()
                .url(LoanApi.BASE_URL+"card/cardList.do")
                .build().execute(new MyStringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {

            }

            @Override
            public void onResponse(String s, int i) {
                CardImage user=new Gson().fromJson(s,CardImage.class);
                if (user.getCode().equals("1")){
                    for (int j=0;j<user.getData().getDataList().size();j++){
                      cardId=user.getData().getDataList().get(j).getCardId()+"";
                      url=user.getData().getDataList().get(j).getUrl();
                        CardBean bean=new CardBean(
                                user.getData().getDataList().get(j).getLogo(),
                                user.getData().getDataList().get(j).getName(),
                                user.getData().getDataList().get(j).getSlogan(),
                                user.getData().getDataList().get(j).getSlogan2(),
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        OkHttpUtils.post()
                                                .url(LoanApi.BASE_URL+"card/cardinsert.do")
                                                .addParams("cardId",cardId)
                                                .addParams("userId",SharedUtils.id)
                                                .addParams("platform","2")
                                                .addParams("channel","无")
                                                .build()
                                                .execute(new MyStringCallback() {
                                                    @Override
                                                    public void onError(Call call, Exception e, int i) {

                                                    }

                                                    @Override
                                                    public void onResponse(String s, int i) {
                                                        GetAddState user=new Gson().fromJson(s,GetAddState.class);
                                                        if (user.getCode().equals("1")){
                                                            IntentUtil.toWeb(url);
                                                        }else if (user.getCode().equals("2")){
                                                            IntentUtil.toWeb(url);
                                                        }else {
                                                            ToastUtil.showToast(MyApplication.getContext(),user.getMessage());
                                                        }
                                                    }
                                                });

                                    }
                                });
                        lists.add(bean);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
