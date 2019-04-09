package com.example.administrator.yefeng.model.bean;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class GetJsonArray {
    private String str;
    public GetJsonArray(String str) {
        this.str=str;
    }

    public JsonArray getData(){
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(str).getAsJsonArray();
        return jsonArray;
    }

}
