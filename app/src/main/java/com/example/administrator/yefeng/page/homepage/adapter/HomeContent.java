package com.example.administrator.yefeng.page.homepage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.administrator.yefeng.R;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeContent extends RecyclerView.ViewHolder {

    @BindView(R.id.game_img)
    RoundedImageView gameImg;
    @BindView(R.id.game_name)
    TextView gameName;
    @BindView(R.id.game_type)
    TextView gameType;
    @BindView(R.id.game_rate)
    RatingBar gameRate;
    @BindView(R.id.game_size)
    TextView gameSize;
    @BindView(R.id.game_description)
    TextView gameDescription;

    @BindView(R.id.loan_linear)
    LinearLayout loanLinear;

    public HomeContent(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
