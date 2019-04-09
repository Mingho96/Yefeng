package com.example.administrator.yefeng.util;

import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

public abstract class MyStringCallback extends Callback<String> {
    @Override
    public boolean validateReponse(Response response, int id) {
        return true;
    }

    @Override
    public String parseNetworkResponse(Response response, int id) throws Exception {

        if(response.code()>=200 && response.code()<300){
            return response.body().string();
        }else{
            throw new Exception("code is:"+response.code()+"\n"+response.body().string());
        }

    }

}


