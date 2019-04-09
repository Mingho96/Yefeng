package com.example.administrator.yefeng.page.user.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.base.BaseActivity;
import com.example.administrator.yefeng.base.BasePresenter;
import com.example.administrator.yefeng.model.LoanApi;
import com.example.administrator.yefeng.model.bean.GetAnswer;
import com.example.administrator.yefeng.model.bean.GetJsonArray;
import com.example.administrator.yefeng.page.user.adapter.FAQAdapter;
import com.example.administrator.yefeng.util.MatelDialogUtil;
import com.example.administrator.yefeng.util.MyStringCallback;
import com.example.administrator.yefeng.widget.TitleView;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

public class FAQActivity extends BaseActivity {

    @BindView(R.id.title_view)
    TitleView titleView;
    @BindView(R.id.recyclerview_read)
    RecyclerView recyclerViewFAQ;

    private FAQAdapter adapter;
    private List<GetAnswer> dataList=new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_faq;
    }

    @Override
    public void initView(Bundle bundle) {
        titleView.setTitleCotent("常见问题");
        OkHttpUtils.post()
                .url(LoanApi.BASE_URL + "question")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        JSONObject jsonObject=null;
                        String data="";
                        String result="";
                        try {
                            jsonObject=new JSONObject(s);
                            result=jsonObject.getString("result");
                            data=jsonObject.getString("data");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(result.equals("success")){
                            GetJsonArray getJsonArray=new GetJsonArray(data);
                            for(JsonElement item:getJsonArray.getData()){
                                GetAnswer answer=new Gson().fromJson(item,GetAnswer.class);
                                dataList.add(answer);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

        LinearLayoutManager manager=new LinearLayoutManager(this);
        adapter=new FAQAdapter(dataList);
        recyclerViewFAQ.setLayoutManager(manager);
        recyclerViewFAQ.setAdapter(adapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }


}
