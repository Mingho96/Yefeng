package com.example.administrator.yefeng.page.login.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.base.BaseActivity;
import com.example.administrator.yefeng.base.BasePresenter;
import com.example.administrator.yefeng.model.LoanApi;
import com.example.administrator.yefeng.model.bean.GetUserInfoT;
import com.example.administrator.yefeng.page.main.activity.MainActivity;
import com.example.administrator.yefeng.util.IntentUtil;
import com.example.administrator.yefeng.util.SharedUtilsT;
import com.example.administrator.yefeng.util.ToastUtil;
import com.example.administrator.yefeng.widget.TitleView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.edit_user)
    EditText editUser;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.login)
    Button login;

    String user, pwd;
    @BindView(R.id.login_title)
    TitleView loginTitle;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x10:
                    String res = (String) msg.obj;
                    if ("success".equals(res)) {
                        ToastUtil.showToast(getApplicationContext(), "登录成功");
                        IntentUtil.initIntent2(MainActivity.class);
                        finish();
                    } else {
                        ToastUtil.showToast(getApplicationContext(), "用户名或密码不对");
                    }
                    break;
            }
        }
    };


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(Bundle bundle) {
        loginTitle.setTitleCotent("登录");
    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }


    @OnClick({R.id.login, R.id.tv_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                user = editUser.getText().toString();
                pwd = editPwd.getText().toString();
                if (user.equals("") || pwd.equals("")) {
                    ToastUtil.showToast(getApplicationContext(), "请输入完整信息");
                } else {
                    loginRequest();
                }

                break;
            case R.id.tv_regist:
                startActivity(new Intent(this, RegistActivity.class));
                break;
        }
    }

    private void loginRequest() {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            FormBody body = new FormBody.Builder()
                    .add("username", user)
                    .add("password", pwd).build();
            final Request request = new Request.Builder().url(LoanApi.BASE_URL + "login").post(body).build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i("error  === ", "请求错误");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.i("success  === ", "请求成功");
//                            Log.i("result ===== ", response.body().string());
//                            startActivity(new Intent(getApplicationContext(), RegistActivity.class));
                    try {
                        String s = response.body().string();
                        JSONObject jsonObject = new JSONObject(s);
                        String result = jsonObject.getString("result");
                        if (result.equals("success")) {
                            String data = jsonObject.getString("data");
                            GetUserInfoT user = new Gson().fromJson(data, GetUserInfoT.class);
                            SharedUtilsT.createUser(
                                    user.getId() + "",
                                    user.getUsername(),
                                    user.getNickname(),
                                    user.getToken(),
                                    user.getGender() + "",
                                    user.getEmail()
                            );
                        }
                        Message msg = handler.obtainMessage();
                        msg.what = 0x10;
                        msg.obj = result;
                        handler.sendMessage(msg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
