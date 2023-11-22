package com.example.smartcity_20.home.apter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.smartcity_20.config.java.IpandPort;

import java.util.List;

public class VpmapApter extends PagerAdapter {
    private Context context;
    private String TAG ="TAG";
    private List<String> list;

    public VpmapApter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(View)object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = null;
        try {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(IpandPort.URL+list.get(position)).into(imageView);
            Log.e(TAG,"VpmapApter="+IpandPort.URL+list.get(position));
            container.addView(imageView);
        } catch (Exception e) {
            Log.e(TAG,"VpmapApter="+e.getMessage());
        }
        return imageView;
    }

}

