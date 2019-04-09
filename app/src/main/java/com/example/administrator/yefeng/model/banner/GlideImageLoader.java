package com.example.administrator.yefeng.model.banner;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {
     @Override
     public void displayImage(Context context, Object path, ImageView imageView) {
         Glide.with(context).load(path).into(imageView);
         Uri uri = Uri.parse((String) path); imageView.setImageURI(uri);
    }

    @Override
    public ImageView createImageView(Context context) {
        return super.createImageView(context);
    }
}
