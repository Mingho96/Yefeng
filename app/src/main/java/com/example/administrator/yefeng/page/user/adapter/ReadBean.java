package com.example.administrator.yefeng.page.user.adapter;

import android.view.View;

public class ReadBean {
    private String readimg;
    private String readtitle;
    private String readtime;
    private View.OnClickListener listener;

    public ReadBean(String readimg, String readtitle, String readtime, View.OnClickListener listener) {
        this.readimg = readimg;
        this.readtitle = readtitle;
        this.readtime = readtime;
        this.listener=listener;
    }

    public String getReadimg() {
        return readimg;
    }

    public void setReadimg(String readimg) {
        this.readimg = readimg;
    }

    public String getReadtitle() {
        return readtitle;
    }

    public void setReadtitle(String readtitle) {
        this.readtitle = readtitle;
    }

    public String getReadtime() {
        return readtime;
    }

    public void setReadtime(String readtime) {
        this.readtime = readtime;
    }

    public View.OnClickListener getListener() {
        return listener;
    }
}
