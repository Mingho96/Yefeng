package com.example.administrator.yefeng.page.homepage.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.model.rxbean.ULoan;
import com.example.administrator.yefeng.util.GlideUtils;

import java.util.List;

public class LoanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private HomeContent content;
    private List<ULoan> TOM;
    private String url="";

    public LoanAdapter(List<ULoan> TOM) {
        this.TOM = TOM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_home,parent,false);
        content=new HomeContent(view);
    return content;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ULoan bean=TOM.get(position);
        RequestOptions options=new RequestOptions().centerCrop();
        GlideUtils.loadUrl(bean.getGameImg(),((HomeContent) holder).gameImg,options);
        ((HomeContent) holder).gameName.setText(bean.getGameName());
        ((HomeContent) holder).gameType.setText(bean.getGameType());
        ((HomeContent) holder).gameSize.setText(bean.getGameSize());
        ((HomeContent) holder).gameRate.setRating(bean.getGameRate());
        url=bean.getGameUrl();
        ((HomeContent) holder).loanLinear.setOnClickListener(bean.getListener());
    }

    @Override
    public int getItemCount() {
        return TOM.size();
    }

}
