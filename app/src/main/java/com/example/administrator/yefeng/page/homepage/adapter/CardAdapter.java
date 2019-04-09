package com.example.administrator.yefeng.page.homepage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.util.GlideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.RemoveId> {

    private List<CardBean> TOM;

    public CardAdapter(List<CardBean> TOM) {
        this.TOM = TOM;
    }

    @Override
    public RemoveId onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_card, parent, false);
        RemoveId removeId = new RemoveId(view);
        return removeId;
    }

    @Override
    public void onBindViewHolder(RemoveId holder, int position) {
        CardBean bean=TOM.get(position);
        GlideUtils.loadUrl(bean.getCardimg(),holder.cardImg);
        holder.cardName.setText(bean.getCardname());
        holder.cardExtract.setText(bean.getCardextract());
        holder.cardState.setText(bean.getCardstate());
        holder.cardRelative.setOnClickListener(bean.getListener());
    }

    @Override
    public int getItemCount() {
        return TOM.size();
    }

    class RemoveId extends RecyclerView.ViewHolder {
        @BindView(R.id.card_img)
        ImageView cardImg;
        @BindView(R.id.card_name)
        TextView cardName;
        @BindView(R.id.card_extract)
        TextView cardExtract;
        @BindView(R.id.card_state)
        TextView cardState;
        @BindView(R.id.card_relative)
        RelativeLayout cardRelative;
        public RemoveId(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
