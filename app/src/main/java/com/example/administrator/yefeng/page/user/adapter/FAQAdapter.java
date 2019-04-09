package com.example.administrator.yefeng.page.user.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.yefeng.R;
import com.example.administrator.yefeng.model.bean.GetAnswer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.RemoveId> {

    private List<GetAnswer> TOM;

    public FAQAdapter(List<GetAnswer> TOM) {
        this.TOM = TOM;
    }

    @NonNull
    @Override
    public RemoveId onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_question, viewGroup, false);
        RemoveId removeId = new RemoveId(view);
        return removeId;
    }

    @Override
    public void onBindViewHolder(@NonNull RemoveId removeId, int i) {
        GetAnswer bean=TOM.get(i);
        removeId.question.setText(bean.getDescription());
        removeId.answer.setText(bean.getContent());
    }

    @Override
    public int getItemCount() {
        return TOM.size();
    }

    class RemoveId extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_que)
        TextView question;
        @BindView(R.id.tv_ans)
        TextView answer;
        public RemoveId(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
