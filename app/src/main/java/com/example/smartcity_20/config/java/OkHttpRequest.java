package com.example.smartcity_20.config.java;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpRequest {
    public static String TOKEN;
    public static String TAG = "TAG";
    public static String URL = "http://124.93.196.45:10001/";
    public static String GET = "get";
    public static String POST = "post";
    public static String PUT = "put";
    public static Gson gson;
    public static OkHttpClient okHttpClient;
    public static ViewPager vp =null;
    private static Handler handler;


    public interface NetRequst {
        void ok(Object obj);

        void no(String msg);
    }


    public static void init() {
        okHttpClient = new OkHttpClient();
        gson = new Gson();
        handler = new Handler();
    }

    public static void doNetRequst(String url, String method, Class classof, NetRequst netRequst, String... json) {
        Request.Builder builder = new Request.Builder();
        if (!TextUtils.isEmpty(url)) {
            url = URL + url;
            Log.v(TAG, "url: " + url);
            builder.url(url);
        }

        if (!TextUtils.isEmpty(TOKEN)) {
            builder.addHeader("Authorization", TOKEN);
        }

        if (okHttpClient == null || gson == null || handler == null) {
            init();
        }

        if (GET.equals(method)) {
            builder.get();
        } else if (POST.equals(method) && json.length > 0) {
            builder.post(RequestBody.create(json[0], MediaType.parse("application/json; charset=utf-8")));
        } else if (PUT.equals(method) && json.length > 0) {
            builder.put(RequestBody.create(json[0], MediaType.parse("application/json; charset=utf-8")));
        }

        Request build = builder.build();
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                netRequst.no("Request failure");
                Log.v(TAG, "no: " + e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                Log.v(TAG, "json" + json);
                try {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Object obj = gson.fromJson(json, classof);
                            netRequst.ok(obj);
                        }
                    });
                } catch (Exception e) {
                    Log.v(TAG, "ok:" + e.getMessage());
                    netRequst.no("Request failure.Json parse exception");
                }
            }
        });

    }

    public static void doMapRequst(ViewPager viewPager, List<String> list, Context content) {
        try {
            vp = viewPager;
            pages pages = new pages(list, content);
            vp.setAdapter(pages);
            mHandler.sendEmptyMessageDelayed(0, 3 * 1000);
        } catch (Exception e) {
            Log.e(TAG,"doMapRequst"+e.getMessage());
        }
    }

    public static void dologin(View view, String text, String but, View.OnClickListener onClickListener){
        try {
            Snackbar.make(view,text, Snackbar.LENGTH_SHORT).setAction(but,v->{
                onClickListener.onClick(v);
            }).show();
        } catch (Exception e) {
             Log.e(TAG,"登录弹窗错误"+e.getMessage());
        }
    }

    public static class pages extends PagerAdapter {
        private List<String> list;
        private Context content;

        public pages(List<String> list, Context content) {
            this.list = list;
            this.content = content;
        }

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = null;
            try {
                imageView = new ImageView(content);
                String img = list.get(position);
                Glide.with(content).load(IpandPort.URL + img).into(imageView);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(imageView);
            } catch (Exception e) {
                Log.e(TAG, "轮播图解析错误" + e.getMessage());
            }
            return imageView;
        }
    }

    public static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            try {
                if (msg.what == 0 && vp!=null) {
                    int index = vp.getCurrentItem();
                    index = (index + 1) % 3;
                    vp.setCurrentItem(index);
                    mHandler.sendEmptyMessageDelayed(0, 3 * 1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

}
