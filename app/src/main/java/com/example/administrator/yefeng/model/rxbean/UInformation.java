package com.example.administrator.yefeng.model.rxbean;

import android.view.View;

public class UInformation {
    private String title;
    private String img1;
    private String username;
    private String time;
    private View.OnClickListener listener;


    public UInformation(String title, String img1, String username,String time,View.OnClickListener listener) {
        this.title = title;
        this.img1 = img1;
        this.username = username;
        this.listener=listener;
        this.time=time;
    }

    public View.OnClickListener getListnenr(){
        return listener;
    }
    public String getTitle() {
        return title;
    }

    public String getImg1() {
        return img1;
    }

    public String getUsername() {
        return username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
