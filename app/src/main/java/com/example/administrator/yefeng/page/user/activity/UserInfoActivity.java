package com.example.administrator.yefeng.page.user.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.app.MyApplication;
import com.example.administrator.yefeng.base.BaseActivity;
import com.example.administrator.yefeng.base.BasePresenter;
import com.example.administrator.yefeng.model.LoanApi;
import com.example.administrator.yefeng.model.bean.GetUserInfoT;
import com.example.administrator.yefeng.page.main.activity.MainActivity;
import com.example.administrator.yefeng.util.ActivityUtil;
import com.example.administrator.yefeng.util.IntentUtil;
import com.example.administrator.yefeng.util.MyStringCallback;
import com.example.administrator.yefeng.util.SharedUtilsT;
import com.example.administrator.yefeng.util.ToastUtil;
import com.example.administrator.yefeng.widget.TitleView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class UserInfoActivity extends BaseActivity {

    @BindView(R.id.submit_userinfo)
    Button button;
    @BindView(R.id.title_view)
    TitleView titleView;
    @BindView(R.id.user_email)
    EditText userEmail;
    @BindView(R.id.email_relative)
    RelativeLayout emailRelative;
    @BindView(R.id.malee)
    RadioButton male;
    @BindView(R.id.femalee)
    RadioButton female;
    @BindView(R.id.genderr)
    RadioGroup gender;
    @BindView(R.id.gender_relative)
    RelativeLayout genderRelative;
    @BindView(R.id.nickname)
    EditText nickname;
    @BindView(R.id.nickname_relative)
    RelativeLayout nicknameRelative;

    private String nickName, email, gen;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    public void initView(Bundle bundle) {
        titleView.setTitleCotent("修改/查看个人信息");
        SharedUtilsT.getUser();
        nickname.setText(SharedUtilsT.nickName);
        userEmail.setText(SharedUtilsT.uemail);
        int g = 1;

         try {
             g = Integer.parseInt(SharedUtilsT.ugender);
         } catch (Exception e) {
             e.printStackTrace();
         }
        if (g == 1) {
            male.setChecked(true);
        } else {
            female.setChecked(true);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @OnClick(R.id.submit_userinfo)
    public void onViewClicked(View view) {
        nickName =nickname.getText().toString();
        email =userEmail.getText().toString();
        gen=gender.getCheckedRadioButtonId()==R.id.male?"1":"0";
        Log.i("token",SharedUtilsT.token);
        try {

            OkHttpUtils.post()
                    .url(LoanApi.BASE_URL + "update")
                    .addParams("id", SharedUtilsT.uid)
                    .addParams("nickname", nickName)
                    .addParams("email", email)
                    .addParams("gender", gen)
                    .addParams("token", SharedUtilsT.token)
                    .build()
                    .execute(new MyStringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int i) {
                            ToastUtil.showToast(MyApplication.getContext(),"请求失败");
                        }

                        @Override
                        public void onResponse(String s, int i) {
                            Log.i("response======",s);
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(s);
                                String result = jsonObject.getString("result");
                                String data = jsonObject.getString("data");
//                                GetUserInfoT user=new Gson().fromJson(data,GetUserInfoT.class);
                                GetUserInfoT gui=new Gson().fromJson(data,GetUserInfoT.class);
                                if (result.equals("success")) {
                                    ToastUtil.showToast(MyApplication.getContext(), "更改成功！");
                                    SharedUtilsT.createUser(
                                            gui.getId()+"",
                                            gui.getUsername(),
                                            gui.getNickname(),
                                            SharedUtilsT.token,
                                            gui.getGender()+"",
                                            gui.getEmail()
                                    );
                                    IntentUtil.initIntent2(MainActivity.class);
                                    ActivityUtil.finishAll();
                                }else{
                                    ToastUtil.showToast(MyApplication.getContext(), "更改失败！");

                                }
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
