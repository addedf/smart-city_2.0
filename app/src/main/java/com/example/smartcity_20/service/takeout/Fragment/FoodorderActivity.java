package com.example.smartcity_20.service.takeout.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.Common;
import com.example.smartcity_20.config.java.IpandPort;
import com.example.smartcity_20.config.kotlin.Tool;
import com.example.smartcity_20.service.takeout.bean.FoodlistBean;
import com.example.smartcity_20.service.takeout.bean.VptakeoutBean;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class FoodorderActivity extends AppCompatActivity {

    private FoodorderActivity context;
    private Gson gson;
    private FoodlistBean.RowsBean rowsBean;
    private ImageView ic_back;
    private TextView title;
    private ImageView imgUrl;
    private TextView name;
    private TextView score;
    private TextView saleQuantity;
    private TextView avgCost;
    private TextView distance;
    private TabLayout mytab;
    private RecyclerView foodlist;
    private RecyclerView reviewlist;
    private ImageView imgUrl2;
    private TextView score2;
    private TextView introduction;
    private TextView name2;
    private RelativeLayout re;
    private Tool tool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodorder);
        context = this;
        initview();
        img_bloak();
        informationshop();
        mytaymethod();
    }

    private void mytaymethod() {
        ArrayList<String> typelist = new ArrayList();
        typelist.add("点菜");
        typelist.add("评价");
        typelist.add("商家介绍");
        for (String name : typelist) {
            TabLayout.Tab tab = mytab.newTab();
            tab.setText(name);
            mytab.addTab(tab);
        }

        mytab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String name = tab.getText().toString();
                if(typelist.get(0).equals(name)){
                    //点菜
                    Orderdishes();
                }else  if(typelist.get(1).equals(name)){
                    //评价
                    reviewmethod();
                }else  if(typelist.get(2).equals(name)){
                    //商家介绍
                    shopmethod();
                }
            }



            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void reviewmethod() {
        re.setVisibility(View.GONE);
        foodlist.setVisibility(View.GONE);
        reviewlist.setVisibility(View.VISIBLE);

        /*if(){

        }*/
        tool.send("/prod-api/api/takeout/comment/list?",
                "GET",
                null,
                true,
                VptakeoutBean.class,
                new Function1<VptakeoutBean, Unit>() {
                    @Override
                    public Unit invoke(VptakeoutBean vptakeoutBean) {
                        if(vptakeoutBean.getCode()==200){


                        }
                        return null;
                    }
                });

    }



    private void Orderdishes() {
        re.setVisibility(View.GONE);
        foodlist.setVisibility(View.VISIBLE);
        reviewlist.setVisibility(View.GONE);

    }

    private void shopmethod() {
        try {
            re.setVisibility(View.VISIBLE);
            foodlist.setVisibility(View.GONE);
            reviewlist.setVisibility(View.GONE);


            if(rowsBean ==null){
                return;
            }

            if(!TextUtils.isEmpty(rowsBean.getImgUrl())){
                Glide.with(context).load(IpandPort.URL+rowsBean.getImgUrl()).into(imgUrl2);
            }
            if(!TextUtils.isEmpty(rowsBean.getName())){
                name2.setText(rowsBean.getName());
            }
            if(rowsBean.getScore() !=  null){
                score2.setText(String.valueOf(rowsBean.getScore()+"分"));
            }
            if(!TextUtils.isEmpty(rowsBean.getIntroduction())){
                introduction.setText("商家简介: "+rowsBean.getIntroduction());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void informationshop() {
        try {
            if(rowsBean ==null){
                return;
            }
            if(!TextUtils.isEmpty(rowsBean.getImgUrl())){
                Glide.with(context).load(IpandPort.URL+rowsBean.getImgUrl()).into(imgUrl);
            }
            if(!TextUtils.isEmpty(rowsBean.getName())){
                title.setText(rowsBean.getName());
                name.setText(rowsBean.getName());
            }

            if(rowsBean.getScore() !=  null){
                score.setText(String.valueOf(rowsBean.getScore()+"分"));
            }

            if(rowsBean.getSaleQuantity() !=null ){
                saleQuantity.setText("月销量 "+String.valueOf(rowsBean.getSaleQuantity()));
            }

            if(rowsBean.getDistance() !=  null){
                distance.setText("到店距离: "+String.valueOf(rowsBean.getDistance()));
            }

            if(rowsBean.getAvgCost() !=  null){
                avgCost.setText("人均消费: "+String.valueOf(rowsBean.getAvgCost()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initview() {
        ic_back = findViewById(R.id.ic_back);
        title = findViewById(R.id.title);
        imgUrl = findViewById(R.id.imgUrl);
        name = findViewById(R.id.name);
        score = findViewById(R.id.score);
        saleQuantity = findViewById(R.id.saleQuantity);
        avgCost = findViewById(R.id.avgCost);
        distance = findViewById(R.id.distance);
        mytab = findViewById(R.id.mytab);
        foodlist = findViewById(R.id.foodlist);
        reviewlist = findViewById(R.id.reviewlist);

        imgUrl2 = findViewById(R.id.imgUrl2);
        score2 = findViewById(R.id.score2);
        introduction = findViewById(R.id.introduction);
        name2 = findViewById(R.id.name2);
        re = findViewById(R.id.re);

        gson = new Gson();
        Intent intent = getIntent();
        String json = intent.getStringExtra(Common.FoodorderActivity);
        rowsBean = gson.fromJson(json, FoodlistBean.RowsBean.class);
        tool = new Tool(context);
    }

    private void img_bloak() {
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });
    }
}
