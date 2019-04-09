package com.example.administrator.yefeng.page.main.adapter;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.model.rxbean.ULoan;
import com.example.administrator.yefeng.util.GlideUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import android.widget.TextView;
import android.widget.RatingBar;

public class ListviewTopAdapter extends BaseAdapter {

    private List<ULoan> objects = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ListviewTopAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }


    public void setObjects(List<ULoan> objects) {
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public ULoan getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listview_top, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    @SuppressLint("SetTextI18n")
    private void initializeViews(ULoan object, ViewHolder holder, int pos) {
        //TODO implement
        holder.gameTopDescription.setText(object.getGameDescription());
        holder.gameTopName.setText(object.getGameName());
        holder.gameTopType.setText(object.getGameType());
        holder.gameTopSize.setText(object.getGameSize());
        holder.gameTopRate.setRating(object.getGameRate());
        holder.goLoan.setOnClickListener(object.getListener());
        holder.loanLinear.setOnClickListener(object.getListener());
        holder.gameTop.setText((pos+1)+"");
        switch (pos){
            case 0:
                holder.gameTop.setTextSize(32);
                holder.gameTop.setTextColor(Color.RED);break;
            case 1:
                holder.gameTop.setTextSize(24);
                holder.gameTop.setTextColor(Color.parseColor("#dd2222"));
                break;
            case 2:
                holder.gameTop.setTextSize(20);
                holder.gameTop.setTextColor(Color.parseColor("#aa5555"));
                break;
            default:
                holder.gameTop.setTextSize(16);
                holder.gameTop.setTextColor(Color.DKGRAY);
                break;
        }
    }

    protected class ViewHolder {
        private LinearLayout loanLinear;
        private TextView gameTopName;
        private RatingBar gameTopRate;
        private TextView gameTopType;
        private TextView gameTopSize;
        private TextView gameTopDescription;
        private TextView goLoan;
        private TextView gameTop;

        public ViewHolder(View view) {
            loanLinear = (LinearLayout) view.findViewById(R.id.loan_linear);
            gameTopName = (TextView) view.findViewById(R.id.game_top_name);
            gameTopRate = (RatingBar) view.findViewById(R.id.game_top_rate);
            gameTop = (TextView) view.findViewById(R.id.tv_top);
            gameTopType = (TextView) view.findViewById(R.id.game_top_type);
            gameTopSize = (TextView) view.findViewById(R.id.game_top_size);
            gameTopDescription = (TextView) view.findViewById(R.id.game_top_description);
            goLoan = (TextView) view.findViewById(R.id.go_top_loan);
        }
    }
}
