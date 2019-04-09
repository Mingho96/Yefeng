package com.example.administrator.yefeng.page.homepage.adapter;

import android.view.View;

public class CardBean {
    private String cardimg;
    private String cardname;
    private String cardextract;
    private String cardstate;
    private View.OnClickListener listener;

    public CardBean(String cardimg, String cardname, String cardextract, String cardstate, View.OnClickListener listener) {
        this.cardimg = cardimg;
        this.cardname = cardname;
        this.cardextract = cardextract;
        this.cardstate = cardstate;
        this.listener = listener;
    }

    public String getCardimg() {
        return cardimg;
    }

    public String getCardname() {
        return cardname;
    }

    public String getCardextract() {
        return cardextract;
    }

    public String getCardstate() {
        return cardstate;
    }

    public View.OnClickListener getListener() {
        return listener;
    }
}
