package com.example.administrator.yefeng.model;

import com.example.administrator.yefeng.base.BaseResponse;
import com.example.administrator.yefeng.model.rxbean.UEverydayRecommend;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoanApi {
    String BASE_URL="http://123.206.47.135:8080/Games/";
//    String BASE_URL="http://192.168.43.8:8080/Games/";
//    String BASE_URL="http://192.168.0.117:8083/v1/";

    @POST("product/recommend.do")
    @FormUrlEncoded
    Observable<BaseResponse<UEverydayRecommend>> recommend(@Field("recommend") String recommend);




    class Factory{
        private static LoanApi loanApi;

        public static LoanApi create(){
            if (loanApi==null){
                OkHttpClient.Builder builder=new OkHttpClient.Builder();
                builder.readTimeout(10, TimeUnit.SECONDS);
                builder.writeTimeout(10, TimeUnit.SECONDS);
                builder.connectTimeout(5, TimeUnit.SECONDS);

                Retrofit retrofit=new Retrofit.Builder()
                        .client(builder.build())
                        .baseUrl(BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()))
                        .build();
                loanApi=retrofit.create(LoanApi.class);
            }
            return loanApi;
        }
    }
}
