package com.example.smartcity_20.config.java;

import android.text.TextUtils;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttp {
    private static String TOKEN ;
    private static String TAG = "TAG" ;
    private static String URL ="http://124.93.196.45:10001/";
    private static String GET = "get" ;
    private static String POST = "post" ;
    private static String PUT = "put" ;
    private static Gson gson;
    private static OkHttp okHttp;

    public  static void init(){
        okHttp = new OkHttp();
        gson = new Gson();
    }

    public  static void doNetRequst(String url ,String method,Class classof,NetRequst netRequst,String... json){
        Request.Builder builder = new Request.Builder();
        if(!TextUtils.isEmpty(url)){
            url = URL + url;
        }

        if(!TextUtils.isEmpty(TOKEN)){
            //builder.addHeader("")
        }

        if(okHttp==null ||gson==null ){
            init();
        }

        if(GET.equals(method)){
            builder.get();
        }else if(POST.equals(method) && json.length>0){
            builder.post(RequestBody.create(json[0], MediaType.parse("application/json,charset=utf-8")));
        }else if(PUT.equals(method) && json.length>0){
            builder.put(RequestBody.create(json[0], MediaType.parse("application/json,charset=utf-8")));
        }

       // builder.
    }


    interface NetRequst{
        void ok(Object obj);
        void no(String msg);
    }
}
