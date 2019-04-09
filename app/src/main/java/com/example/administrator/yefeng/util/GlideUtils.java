package com.example.administrator.yefeng.util;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.yefeng.app.MyApplication;

public class GlideUtils {
    public static RequestOptions optionCircleCrop=new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE).circleCrop();
    public static void loadUrl(String url, ImageView imageView, RequestOptions options){
        Glide.with(MyApplication.getContext()).load(url).apply(options).into(imageView);
    }
    public static void loadUrl(String url, ImageView imageView){
        Glide.with(MyApplication.getContext()).load(url).into(imageView);
    }
    public static void loadLocation(int url, ImageView imageView, RequestOptions options){
        Drawable drawable=MyApplication.getContext().getResources().getDrawable(url);
        BitmapDrawable bd = (BitmapDrawable) drawable;
        Bitmap bmm = bd.getBitmap();
        Glide.with(MyApplication.getContext()).load(drawable).apply(options).into(imageView);
    }
    public static void loadLocation(int url, ImageView imageView){
        Drawable drawable=MyApplication.getContext().getResources().getDrawable(url);
        BitmapDrawable bd = (BitmapDrawable) drawable;
        Bitmap bmm = bd.getBitmap();
        Glide.with(MyApplication.getContext()).load(drawable).into(imageView);
    }

}
