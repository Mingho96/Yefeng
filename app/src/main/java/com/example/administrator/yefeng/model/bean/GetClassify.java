package com.example.administrator.yefeng.model.bean;

public class GetClassify {
    private int iImg;
    private String iName;

    public GetClassify(int img, String name) {
        this.iImg = img;
        this.iName = name;
    }

    public int getiImg() {
        return iImg;
    }

    public void setiImg(int iImg) {
        this.iImg = iImg;
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }
}
