package com.example.administrator.yefeng.page.homepage.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.model.bean.GetClassify;
import com.example.administrator.yefeng.page.homepage.activity.LoanActivity;
import com.example.administrator.yefeng.util.IntentUtil;

import java.util.List;

public class GridAdapter extends BaseAdapter {
    private List<GetClassify> lists;
    private Context context;

    GridAdapter(List<GetClassify> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            convertView=View.inflate(context,R.layout.item_classify_gridview,null);
            holder=new ViewHolder();
            holder.name=convertView.findViewById(R.id.g_name);
            holder.icon=convertView.findViewById(R.id.g_icon);
            holder.linearItem=convertView.findViewById(R.id.item_grid);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder) convertView.getTag();
        }
        holder.icon.setImageResource(lists.get(position).getiImg());
        holder.name.setText(lists.get(position).getiName());
        holder.linearItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtil.initIntent2(LoanActivity.class,lists.get(position).getiName(),position+"");
            }
        });
        return convertView;
    }

    class ViewHolder{
        ImageView icon;
        TextView name;
        LinearLayout linearItem;
    }
}
