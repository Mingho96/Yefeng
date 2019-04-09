package com.example.administrator.yefeng.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.example.administrator.yefeng.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class NetImageHolder extends Holder<String> {
    private RoundedImageView mImageView;
    private Context mContext;

    public NetImageHolder(View itemView,  Context mContext) {
        super(itemView);
        this.mContext = mContext;
    }

    @Override
    protected void initView(View itemView) {
        mImageView = itemView.findViewById(R.id.item_img);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public void updateUI(String data) {
        Glide.with(mContext).load(data).into(mImageView);
    }
}
