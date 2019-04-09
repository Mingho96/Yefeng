package com.example.administrator.yefeng.model.bean;

public class GameDataListBean implements Comparable<GameDataListBean>{
  private String size;
  private String name;
  private String icon;
  private float rate;
  private  String description;
  private int id;
  private String type;
  private String category;
  private String url;

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public String toString() {
        return "GameDataListBean{" +
                "size='" + size + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", rate=" + rate +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", url='" + url + '\'' +
                '}';
    }


    @Override
    public int compareTo(GameDataListBean o) {
        //根据ID降序排序
        if (this.getId()>o.getId()){
            return -1;
        }else if (this.getId()<o.getId()){
            return 1;
        }
        return 0;
    }
}
