package com.example.administrator.yefeng.page.information.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.model.rxbean.UInformation;
import com.example.administrator.yefeng.util.GlideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.RemoveId> {

    private List<UInformation> TOM;

    public InformationAdapter(List<UInformation> TOM) {
        this.TOM = TOM;
    }

    @Override
    public RemoveId onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_information, parent, false);
        RemoveId removeId=new RemoveId(view);
        return removeId;
    }

    @Override
    public void onBindViewHolder(RemoveId holder, int position) {
        UInformation bean=TOM.get(position);
        holder.informationTitle.setText(bean.getTitle());
        GlideUtils.loadUrl(bean.getImg1(),holder.informationImg1);
        holder.userName.setText(bean.getUsername());
        holder.time.setText(bean.getTime());
        holder.informationLinear.setOnClickListener(bean.getListnenr());

    }

    @Override
    public int getItemCount() {
        return TOM.size();
    }

    class RemoveId extends RecyclerView.ViewHolder {
        @BindView(R.id.information_title)
        TextView informationTitle;
        @BindView(R.id.information_img)
        ImageView informationImg1;
        @BindView(R.id.user_name)
        TextView userName;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.information_linear)
        LinearLayout informationLinear;

        public RemoveId(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
