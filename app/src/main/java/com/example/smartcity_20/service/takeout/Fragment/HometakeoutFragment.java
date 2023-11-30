package com.example.smartcity_20.service.takeout.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.OkHttpRequest;
import com.example.smartcity_20.config.kotlin.Tool;
import com.example.smartcity_20.me.LoginActivity;
import com.example.smartcity_20.service.takeout.apter.FoodhottyApter;
import com.example.smartcity_20.service.takeout.apter.FoodlistApter;
import com.example.smartcity_20.service.takeout.apter.FoodtypeApter;
import com.example.smartcity_20.service.takeout.bean.FoodhottyBean;
import com.example.smartcity_20.service.takeout.bean.FoodlistBean;
import com.example.smartcity_20.service.takeout.bean.FoodtypeBean;
import com.example.smartcity_20.service.takeout.bean.VptakeoutBean;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class HometakeoutFragment extends Fragment {


    private View view;
    private FragmentActivity activity;
    private Context context;
    private ImageView ic_back;
    private ViewPager2 vp;
    private RecyclerView foodtype;
    private RecyclerView foodhot;
    private RecyclerView foodlist;
    private Tool tool;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hometakeout, container, false);
        activity = getActivity();
        context = getContext();
        initview();
        img_bloak();
        vpmapmethod();
        foodtypemethod();
        foodhottymethod();
        foodlistmethod();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();


        vpmapmethod();
        foodtypemethod();
        foodhottymethod();
        foodlistmethod();

    }

    private void foodlistmethod() {
        tool.send("/prod-api/api/takeout/seller/list",
                "GET",
                null,
                true,
                FoodlistBean.class,
                new Function1<FoodlistBean, Unit>() {
                    @Override
                    public Unit invoke(FoodlistBean foodlistBean) {
                        if(foodlistBean.getCode()==200){
                            foodlist.setLayoutManager(new LinearLayoutManager(context));
                            foodlist.setAdapter(new FoodlistApter(context,foodlistBean.getRows()));
                        }
                        return null;
                    }
                });
    }

    private void foodhottymethod() {
        tool.send("/prod-api/api/takeout/seller/list?recommend=Y",
                "GET",
                null,
                true,
                FoodhottyBean.class,
                new Function1<FoodhottyBean, Unit>() {
                    @Override
                    public Unit invoke(FoodhottyBean fdhotty) {
                        if(fdhotty.getCode()==200){
                            foodhot.setLayoutManager(new GridLayoutManager(context,2));
                            foodhot.setAdapter(new FoodhottyApter(context,fdhotty.getRows()));
                        }
                        return null;
                    }
                });
    }

    private void foodtypemethod() {
        tool.send("/prod-api/api/takeout/theme/list",
                "GET",
                null,
                true,
                FoodtypeBean.class,
                new Function1<FoodtypeBean, Unit>() {
                    @Override
                    public Unit invoke(FoodtypeBean fdtype) {
                        if(fdtype.getCode()==200){
                            foodtype.setLayoutManager(new GridLayoutManager(context,fdtype.getData().size()));
                            foodtype.setAdapter(new FoodtypeApter(context,fdtype.getData()));
                        }
                        return null;
                    }
                });
    }

    private void vpmapmethod() {
        tool.send("/prod-api/api/takeout/rotation/list",
                "GET",
                null,
                true,
                VptakeoutBean.class,
                new Function1<VptakeoutBean, Unit>() {
                    @Override
                    public Unit invoke(VptakeoutBean vptakeoutBean) {
                        if(vptakeoutBean.getCode()==200){
                            ArrayList<String> arrayList = new ArrayList();
                            for (VptakeoutBean.RowsDTO row : vptakeoutBean.getRows()) {
                                arrayList.add(row.getAdvImg());
                            }
                            tool.setBanner(vp,arrayList);
                        }
                        return null;
                    }
                });
    }

    private void initview() {
        ic_back = view.findViewById(R.id.ic_back);
        vp = view.findViewById(R.id.vp);
        foodtype = view.findViewById(R.id.foodtype);
        foodhot = view.findViewById(R.id.foodhot);
        foodlist = view.findViewById(R.id.foodlist);
        tool = new Tool(context);
    }

    private void img_bloak() {
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }
}