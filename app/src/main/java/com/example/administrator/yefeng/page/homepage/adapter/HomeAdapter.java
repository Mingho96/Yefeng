package com.example.administrator.yefeng.page.homepage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.model.rxbean.ULoan;
import com.example.administrator.yefeng.util.Contstant;
import com.example.administrator.yefeng.util.GlideUtils;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private HomeHeader header;
    private HomeContent content;

    private List<ULoan> TOM;
    private int headerCount = 1;
    private Context context;

    public boolean isHeaderView(int position) {
        return headerCount != 0 && position < headerCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (headerCount != 0 && position < headerCount) {
            return Contstant.ITEM_TYPE_HEADER;
        } else {
            return Contstant.ITEM_TYPE_CONTENT;
        }
    }

    public HomeAdapter(List<ULoan> TOM, Context context) {
        this.TOM = TOM;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Contstant.ITEM_TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_home_header, parent, false);
            header = new HomeHeader(view);
            return header;
        }
        if (viewType == Contstant.ITEM_TYPE_CONTENT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_home, parent, false);
            content = new HomeContent(view);
            return content;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HomeContent) {
            ULoan bean = TOM.get(position - 1);
            RequestOptions options = new RequestOptions().centerCrop();
            GlideUtils.loadUrl(bean.getGameImg(), ((HomeContent) holder).gameImg, options);
            ((HomeContent) holder).gameName.setText(bean.getGameName());
            ((HomeContent) holder).gameType.setText(bean.getGameType());
            ((HomeContent) holder).gameSize.setText(bean.getGameSize());
            ((HomeContent) holder).gameDescription.setText(bean.getGameDescription());
            ((HomeContent) holder).gameRate.setRating( bean.getGameRate());
            ((HomeContent) holder).loanLinear.setOnClickListener(bean.getListener());
        }
    }


    @Override
    public int getItemCount() {
        if (TOM != null && TOM.size() > 0) {
            return TOM.size() + 1;
        }
        return TOM.size();
    }

}
