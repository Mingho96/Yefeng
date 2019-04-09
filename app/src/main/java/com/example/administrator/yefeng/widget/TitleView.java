package com.example.administrator.yefeng.widget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.yefeng.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TitleView extends LinearLayout {
    @BindView(R.id.title_back)
    ImageView imageView;
    @BindView(R.id.title_content)
    TextView textView;
    @BindView(R.id.title_right)
    TextView textView2;
    @BindView(R.id.title_img)
    TextView textView3;

//    @BindView(R.id.title_edit)
//    EditText editText;

    public TitleView(Context context) {
        super(context);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_titleview,this);
        Unbinder unbinder= ButterKnife.bind(this);

    }

    public void setTitle3Img(int img,View.OnClickListener listener){
        textView3.setVisibility(VISIBLE);
        textView3.setBackgroundResource(img);
        textView3.setOnClickListener(listener);}
    public  void setTitleCotent(String content){
        textView.setText(content);
    }
    public  void setTitleCotentLeft(String content){
        textView2.setText(content);
    }
    public void setTitleLeftBackground(int resource){textView2.setBackgroundResource(resource);}
    //    public void setEditHint(String content){editText.setVisibility(VISIBLE);editText.setHint(content);textView.setVisibility(GONE);}
    public void setTitleLeftClickListener(View.OnClickListener onClickListener){
        textView2.setOnClickListener(onClickListener);
    }
    public void setTitleContentSize(float size){textView.setTextSize(size);}

    public void setTitleTextColor(int color){textView.setTextColor(this.getResources().getColor(color));}
    public void setTitleLeftTextColor(int color){textView2.setTextColor(this.getResources().getColor(color));}
    public void setBackImg(int i){imageView.setImageResource(i);}
    @OnClick({R.id.title_back,R.id.title_right})
    public void clickTitleBack(View view){

        switch (view.getId()){
            case R.id.title_back:
                ((Activity)getContext()).finish();
                break;
            case R.id.title_right:
                break;
            default:
        }
    }

}
