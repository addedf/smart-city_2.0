package com.example.smartcity_20.config.java;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpRequest {
    public static String TOKEN ;
    public static String TAG = "TAG" ;
    public static String URL ="http://124.93.196.45:10001/";
    public static String GET = "get" ;
    public static String POST = "post" ;
    public static String PUT = "put" ;
    public static Gson gson;
    public static OkHttpClient okHttpClient;

    public  static void init(){
        okHttpClient = new OkHttpClient();
        gson = new Gson();
    }

    public  static void doNetRequst(String url ,String method,Class classof,NetRequst netRequst,String... json){
        Request.Builder builder = new Request.Builder();
        if(!TextUtils.isEmpty(url)){
            url = URL + url;
            Log.v(TAG,"url: "+url);
            builder.url(url);
        }

        if(!TextUtils.isEmpty(TOKEN)){
            builder.addHeader("Authorization",TOKEN);
        }

        if(okHttpClient==null ||gson==null ){
            init();
        }

        if(GET.equals(method)){
            builder.get();
        }else if(POST.equals(method) && json.length>0){
            builder.post(RequestBody.create(json[0], MediaType.parse("application/json; charset=utf-8")));
        }else if(PUT.equals(method) && json.length>0){
            builder.put(RequestBody.create(json[0], MediaType.parse("application/json; charset=utf-8")));
        }

        Request build = builder.build();
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                netRequst.no("Request failure");
                Log.v(TAG,"no: "+e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                Log.v(TAG,"json"+json);

                try {
                    Object obj = gson.fromJson(json, classof);
                    netRequst.ok(obj);
                } catch (Exception e) {
                    Log.v(TAG,"ok:"+e.getMessage());
                    netRequst.no("Request failure.Json parse exception");
                }
            }
        });

    }


    public interface NetRequst{
        void ok(Object obj);
        void no(String msg);
    }
}
