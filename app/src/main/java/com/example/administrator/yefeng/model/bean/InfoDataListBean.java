package com.example.administrator.yefeng.model.bean;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InfoDataListBean implements Comparable<InfoDataListBean>{
    private String summary;
    private String image;
    private int _id;
    private String time;
    private String source;
    private String type;
    private String title;
    private String content;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int compareTo(InfoDataListBean bean) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd");
        Date time1 = null,time2 = null;
        int i=-2;
        try {
            time1=format.parse(this.getTime());
            time2=format.parse(bean.getTime());
            if (time1.before(time2)){
                i=1;
            }else if (time1.after(time2)){
                i=-1;
            }else {
                i=0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NullPointerException e2){
           e2.printStackTrace();
        }
        return i;
    }
}
