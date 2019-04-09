package com.example.administrator.yefeng.page.login.activity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.app.MyApplication;
import com.example.administrator.yefeng.base.BaseActivity;
import com.example.administrator.yefeng.base.BasePresenter;
import com.example.administrator.yefeng.model.LoanApi;
import com.example.administrator.yefeng.util.SharedUtilsT;
import com.example.administrator.yefeng.util.ToastUtil;
import com.example.administrator.yefeng.widget.TitleView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class PwdResetActivity extends BaseActivity {


    @BindView(R.id.title_view)
    TitleView titleView;
    @BindView(R.id.cur_password)
    EditText curPassword;
    @BindView(R.id.new_password)
    EditText newPassword;
    @BindView(R.id.re_password)
    EditText rePassword;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pwd_reset;
    }

    @Override
    public void initView(Bundle bundle) {
        titleView.setTitleCotent("修改密码");
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

    @OnClick(R.id.btn_reset)
    public void onViewClicked() {
        SharedUtilsT.getUser();
        if (!SharedUtilsT.token.equals("")){
            passWordReset();
        }else {
            ToastUtil.showToast(MyApplication.getContext(),"你还没有登录");
        }

    }

    private void passWordReset() {
        String curPass = curPassword.getText().toString();
        String newPass = newPassword.getText().toString();
        String rePass = rePassword.getText().toString();
        SharedUtilsT.getUser();
        String name = SharedUtilsT.uname;//用户名
        if (name.equals("")|| curPass.equals("") || newPass.equals("")) {
            ToastUtil.showToast(getApplication(),"请填写完整信息");
        }else if(!newPass.equals(rePass)){
            ToastUtil.showToast(getApplication(),"两次输入的密码不一致");
        } else {
            OkHttpUtils.post()
                    .url(LoanApi.BASE_URL + "updatePwd")
                    .addParams("username", name)
                    .addParams("password", curPass)
                    .addParams("newPwd", newPass)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int i) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(String s, int i) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(s);
                        String result = jsonObject.getString("result");
                        if (result.equals("success")) {
                            ToastUtil.showToast(MyApplication.getContext(), "修改成功");
                            finish();
                        } else {
                            ToastUtil.showToast(MyApplication.getContext(), "原密码不正确");
                        }
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }

                }
            });
        }
    }

}
