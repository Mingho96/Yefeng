package com.example.administrator.yefeng.page.login.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.app.MyApplication;
import com.example.administrator.yefeng.base.BaseActivity;
import com.example.administrator.yefeng.base.BasePresenter;
import com.example.administrator.yefeng.model.LoanApi;
import com.example.administrator.yefeng.util.IntentUtil;
import com.example.administrator.yefeng.util.SharedUtilsT;
import com.example.administrator.yefeng.util.ToastUtil;
import com.example.administrator.yefeng.widget.TitleView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class RegistActivity extends BaseActivity {

    @BindView(R.id.nike_name)
    EditText nikeName;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.male)
    RadioButton male;
    @BindView(R.id.female)
    RadioButton female;
    @BindView(R.id.gender)
    RadioGroup gender;
    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.re_password)
    EditText rePassword;
    @BindView(R.id.btn_reg)
    Button btnReg;
    @BindView(R.id.title_view)
    TitleView titleView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_regist;
    }

    @Override
    public void initView(Bundle bundle) {
        titleView.setTitleCotent("");
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

    @OnClick(R.id.btn_reg)
    public void onViewClicked() {
        String name = nikeName.getText().toString();//昵称
        String e = email.getText().toString();
        int gend = gender.getCheckedRadioButtonId() == R.id.male ? 1 : 0;//性别
        String pass = password.getText().toString();
        String rePass = rePassword.getText().toString();
        String uName = userName.getText().toString();//用户名
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now=new Date();
        String regTime=sdf.format(now);
        if (name.equals("") || e.equals("") || pass.equals("") || uName.equals("")) {
            Toast.makeText(getApplicationContext(), "请填写完整信息", Toast.LENGTH_SHORT).show();
        } else if (!pass.equals(rePass)) {
            Toast.makeText(getApplicationContext(), "两次密码不一致", Toast.LENGTH_SHORT).show();
        } else if (!isEmail(e)) {
            Toast.makeText(getApplicationContext(), "请输入正确的电子邮箱", Toast.LENGTH_SHORT).show();
        } else {
            OkHttpUtils.post()
                    .url(LoanApi.BASE_URL + "register")
                    .addParams("username", uName)
                    .addParams("password", pass)
                    .addParams("nickname", name)
                    .addParams("gender", String.valueOf(gend))
                    .addParams("email", e)
                    .addParams("regTime",regTime)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int i) {
                    Log.i("error=======>","注册失败");
                    e.printStackTrace();
                }

                @Override
                public void onResponse(String s, int i) {
                    Log.i("s==========>",s);
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(s);
                        String result = jsonObject.getString("result");
                        if (result.equals("success")) {
                            ToastUtil.showToast(MyApplication.getContext(), "注册成功");
                            IntentUtil.initIntent1(LoginActivity.class);
                            finish();
                        } else {
                           if (result.equals("error")){
                               ToastUtil.showToast(MyApplication.getContext(),"该用户名已被注册！");
                           }else {
                               ToastUtil.showToast(MyApplication.getContext(),"注册失败！");

                           }

                        }
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }

                }
            });


        }

    }


    protected boolean isEmail(String str) {
        String rule = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$"; //定义规则
        Pattern p = Pattern.compile(rule); //正则表达式的匹配器
        Matcher m = p.matcher(str); //进行正则匹配\
        return m.matches();
    }
}
