package com.example.administrator.yefeng.page.main.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.app.MyApplication;
import com.example.administrator.yefeng.base.BaseFragment;
import com.example.administrator.yefeng.base.BasePresenter;
import com.example.administrator.yefeng.model.LoanApi;
import com.example.administrator.yefeng.page.login.activity.LoginActivity;
import com.example.administrator.yefeng.page.user.activity.AboutUsActivity;
import com.example.administrator.yefeng.page.user.activity.FAQActivity;
import com.example.administrator.yefeng.page.user.activity.FeedBackActivity;
import com.example.administrator.yefeng.page.user.activity.SettingActivity;
import com.example.administrator.yefeng.page.user.activity.UserInfoActivity;
import com.example.administrator.yefeng.util.Base64Utils;
import com.example.administrator.yefeng.util.GlideUtils;
import com.example.administrator.yefeng.util.IntentUtil;
import com.example.administrator.yefeng.util.MyAlertDialog;
import com.example.administrator.yefeng.util.MyStringCallback;
import com.example.administrator.yefeng.util.SharedUtils;
import com.example.administrator.yefeng.util.SharedUtilsT;
import com.example.administrator.yefeng.util.ToastUtil;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yalantis.ucrop.UCrop;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pub.devrel.easypermissions.EasyPermissions;

import static android.app.Activity.RESULT_OK;

public class    MineFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks {
    @BindView(R.id.mine_nick_name)
    TextView mNickName;

    String nickName = "";
    @BindView(R.id.userinfo)
    RoundedImageView head;

    Unbinder unbinder;
    private File cameraSavePath;//拍照照片路径
    private Uri uri;
    private String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        SharedUtilsT.getUser();
        nickName = "登录";
        try {
            if (!SharedUtilsT.nickName.equals("")) {
                nickName = SharedUtilsT.nickName;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mNickName.setText(nickName);
        cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
        head.setScaleType(ImageView.ScaleType.FIT_XY);
        setHead();
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    @OnClick({R.id.question, R.id.feedback, R.id.about, R.id.my_setting, R.id.userinfo, R.id.mine_nick_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.question:
                IntentUtil.initIntent2(FAQActivity.class);
                break;
            case R.id.feedback:
                IntentUtil.initIntent2(FeedBackActivity.class);
                break;
            case R.id.about:
                IntentUtil.initIntent2(AboutUsActivity.class);
                break;
            case R.id.my_setting:
                IntentUtil.initIntent2(SettingActivity.class);
                break;
            case R.id.userinfo:
                if(SharedUtilsT.token.equals(""))
                {
                    ToastUtil.showToast(getActivity(),"请先登录");
                }else {
                    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                    builder.setItems(new String[]{"立即拍照", "从相册选"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getPermission();
                            switch (which){
                                case 0:
                                    goCamera();break;
                                case 1:
                                    goPhotoAlbum();break;
                            }
                            dialog.dismiss();
                        }
                    });
                    MyAlertDialog.alertDialog =builder.create();
                    MyAlertDialog.alertDialog.show();
                 }
                break;
            case R.id.mine_nick_name:
                if (nickName.equals("登录")) {
                    IntentUtil.initIntent2(LoginActivity.class);
                } else {
                    IntentUtil.initIntent2(UserInfoActivity.class);
                }
                break;
            default:
                break;
        }
    }


    //获取权限
    private void getPermission() {
        if (EasyPermissions.hasPermissions(MyApplication.getContext(), permissions)) {
            //已经打开权限
            ToastUtil.showToast(MyApplication.getContext(), "已经申请相关权限");
        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(this, "需要获取您的相册、照相使用权限", 1, permissions);
        }
    }


    //激活相册操作
    private void goPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 2);
    }

    //激活相机操作
    private void goCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(MyApplication.getContext(), "com.example.administrator.yefeng.fileprovider", cameraSavePath);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(cameraSavePath);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, 1);
    }

    //UCrop框架裁剪
    private  void startCrop(Uri uri){
        UCrop.Options options = new UCrop.Options();
        //裁剪后图片保存在文件夹中
        @SuppressLint({"NewApi", "LocalSuppress"}) File cropImage = new File(Objects.requireNonNull(getActivity()).getCacheDir(), System.currentTimeMillis()+".jpg");
        Uri destinationUri = Uri.fromFile(cropImage);
        UCrop uCrop = UCrop.of(uri, destinationUri);//第一个参数是裁剪前的uri,第二个参数是裁剪后的uri
        uCrop.withAspectRatio(1,1);//设置裁剪框的宽高比例
        options.setCropFrameStrokeWidth(10);//设置裁剪框的宽度
        options.setMaxScaleMultiplier(4);//设置最大缩放比例
        options.setStatusBarColor(Color.parseColor("#1e9fff"));
        options.setToolbarColor(Color.parseColor("#1e9fff"));
        uCrop.withOptions(options);
        uCrop.start(getActivity(),this,UCrop.REQUEST_CROP);
    }

    /**
     *
     * @param cropError 处理剪切失败的返回值
     */
    private void handleCropError(Throwable cropError) {
        deleteTempPhotoFile();
        if (cropError != null) {
            Toast.makeText(getActivity(), cropError.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), "无法剪切选择图片", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteTempPhotoFile() {
        File tempFile = new File(String.valueOf(uri));
        if (tempFile.exists() && tempFile.isFile()) {
            tempFile.deleteOnExit();
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        ToastUtil.showToast(MyApplication.getContext(), "请同意相关权限，否则功能无法使用");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            //相机
            case 1:
                if(resultCode==RESULT_OK){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        startCrop(Uri.fromFile(cameraSavePath));
                    } else {
                        startCrop(uri);
                    }
                }

                break;
            //相册
            case 2:
                if (resultCode==RESULT_OK){
                    startCrop(data.getData());
                }else {
                    ToastUtil.showToast(getActivity(),"你取消了操作");
                }
                break;
            //裁剪
            case UCrop.REQUEST_CROP:
               if(resultCode==RESULT_OK){
                   Uri resultUri=UCrop.getOutput(data);
                   uploadImageRequest(resultUri);
               }else {
                   ToastUtil.showToast(getActivity(),"你取消了操作");
               }
            //裁剪出错
            case UCrop.RESULT_ERROR:
                if(resultCode==RESULT_OK){
                    final Throwable cropError = UCrop.getError(data);
                    handleCropError(cropError);
                }
                break;


        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     *  上传图片请求
     * @param uri 图片路径
     */
    private void uploadImageRequest(Uri uri){
        SharedUtilsT.getUser();
        Bitmap bitmap=null;
        try {
            bitmap=MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),uri);
        } catch (IOException e) {
            e.printStackTrace();
        }


        OkHttpUtils.post()
                .url(LoanApi.BASE_URL+"updateHead")
                .addParams("id",SharedUtilsT.uid)
                .addParams("image",Base64Utils.bitmapToBase64(bitmap))
                .build().execute(new MyStringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(String s, int i) {
                JSONObject object;
                try {
                    object=new JSONObject(s);
                    String img=object.getString("image");
                    GlideUtils.loadUrl(img,head);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

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


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            setHead();
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x10:
                    if(isRealImage(String.valueOf(msg.obj))){
                        GlideUtils.loadUrl((String) msg.obj, head);
                    }else{
                        head.setImageResource(R.mipmap.unknown);
                    }
                    break;
            }
        }
    };

    private boolean isRealImage(String s) {
        boolean isImage=false;
        if (s.endsWith(".jpg")||s.endsWith(".png")||s.endsWith(".jpeg")||s.endsWith(".bmp")||s.endsWith(".gif")){
            isImage=true;
        }
        return isImage;
    }

    private void setHead(){
        getHead();
    }

    private void getHead() {
        SharedUtilsT.getUser();
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils.post()
                        .url(LoanApi.BASE_URL+"getHead")
                        .addParams("id",SharedUtilsT.uid)
                        .build()
                        .execute(new MyStringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int i) {

                            }

                            @Override
                            public void onResponse(String s, int i) {
                                try {
                                    JSONObject object=new JSONObject(s);
                                    Message msg = handler.obtainMessage();
                                    msg.what = 0x10;
                                    msg.obj = object.getString("image");
                                    handler.sendMessage(msg);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        }).start();
    }
}
