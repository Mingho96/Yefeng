package com.example.administrator.yefeng.page.user.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.app.MyApplication;
import com.example.administrator.yefeng.base.BaseActivity;
import com.example.administrator.yefeng.base.BasePresenter;
import com.example.administrator.yefeng.model.LoanApi;
import com.example.administrator.yefeng.util.MyStringCallback;
import com.example.administrator.yefeng.util.SharedUtilsT;
import com.example.administrator.yefeng.util.ToastUtil;
import com.example.administrator.yefeng.widget.TitleView;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class FeedBackActivity extends BaseActivity {

    @BindView(R.id.title_view)
    TitleView titleView;
    @BindView(R.id.feed_content)
    EditText feedContent;
    @BindView(R.id.ps)
    TextView ps;

    private static final int MAX_LENGTH = 100;
    int leftSize = 0;
    String cont="";

    @Override
    public int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    public void initView(Bundle bundle) {
        titleView.setTitleCotent("意见反馈");
        titleView.setBackgroundColor(Color.rgb(30, 144, 255));
        ps.setText(getResources().getString(R.string.feed_content_ps, MAX_LENGTH - leftSize));
        feedContent.setEms(MAX_LENGTH);

        feedContent.addTextChangedListener(new CustomWatcher());
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

    @OnClick(R.id.submit)
    public void onViewClicked() {
        SharedUtilsT.getUser();
        if(cont.equals("")){
            ToastUtil.showToast(MyApplication.getContext(),"你还没有输入反馈内容！");
        }
        else if(SharedUtilsT.uname.equals("")){
            ToastUtil.showToast(MyApplication.getContext(),"请先登录！");
        }else {
           submitFeedBack();
        }
    }

    public void submitFeedBack(){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String t=format.format(new Date());
        SharedUtilsT.getUser();
        OkHttpUtils.post()
                .url(LoanApi.BASE_URL+"addFeedback")
                .addParams("content",cont)
                .addParams("people",SharedUtilsT.uname)
                .addParams("times",t)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        ToastUtil.showToast(getBaseContext(),"发送失败");
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        JSONObject jsonObject=null;
                        String result="";
                        try {
                            jsonObject =new JSONObject(s);
                            result=jsonObject.getString("result");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(result.equals("success")){
                            ToastUtil.showToast(getBaseContext(),"反馈成功");
                            finish();
                        }else {
                            ToastUtil.showToast(getBaseContext(),"反馈失败");
                        }
                    }
                });
    }

    public class CustomWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            cont = feedContent.getText().toString();
            leftSize = cont.length();
            ps.setText(getResources().getString(R.string.feed_content_ps, (MAX_LENGTH - leftSize)));
            if (leftSize > MAX_LENGTH) {
                feedContent.setText(cont.subSequence(0, MAX_LENGTH));
                ps.setText(getResources().getString(R.string.feed_content_ps, 0));
            }
        }
    }
}
