package com.example.administrator.yefeng.model.rxbean;

import android.view.View;


public class ULoan {
    private String gameImg;
    private String gameType;
    private String gameName;
    private float gameRate;
    private String gameDescription;
    private String gameCategory;
    private String gameUrl;
    private String gameSize;
    private View.OnClickListener listener;


    public ULoan() {
    }


    public String getGameSize() {
        return gameSize;
    }

    public void setGameSize(String gameSize) {
        this.gameSize = gameSize;
    }


    public String getGameImg() {
        return gameImg;
    }

    public void setGameImg(String gameImg) {
        this.gameImg = gameImg;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public float getGameRate() {
        return gameRate;
    }

    public void setGameRate(float gameRate) {
        this.gameRate = gameRate;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    public String getGameCategory() {
        return gameCategory;
    }

    public void setGameCategory(String gameCategory) {
        this.gameCategory = gameCategory;
    }

    public String getGameUrl() {
        return gameUrl;
    }

    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
    }

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public ULoan(String gameImg, String gameType, String gameName, float gameRate, String gameDescription, String gameCategory, String gameUrl,String gameSize, View.OnClickListener listener) {

        this.gameImg = gameImg;
        this.gameType = gameType;
        this.gameName = gameName;
        this.gameRate = gameRate;
        this.gameDescription = gameDescription;
        this.gameCategory = gameCategory;
        this.gameUrl = gameUrl;
        this.gameSize = gameSize;
        this.listener = listener;
    }
}
