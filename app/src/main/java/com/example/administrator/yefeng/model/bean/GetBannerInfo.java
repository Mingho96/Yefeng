package com.example.administrator.yefeng.model.bean;

public class GetBannerInfo {

    /**
     * image : http://imga.mumayi.com/android/icon/001/24/85/90/96.png?is=d3fa5fe07f5952ab17c7be56b019ca8a
     * id : 1
     * title : 梦幻仙语
     * url : http://www.mumayi.com/android-1248590.html
     */

    private String image;
    private int id;
    private String title;
    private String url;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "GetBannerInfo{" +
                "image='" + image + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
