package com.example.smartcity_20.service.takeout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.Common;
import com.example.smartcity_20.config.java.IpandPort;
import com.example.smartcity_20.config.kotlin.Tool;
import com.example.smartcity_20.service.takeout.apter.FoodlisttakeoutApter;
import com.example.smartcity_20.service.takeout.apter.FoodtypetakoutApter;
import com.example.smartcity_20.service.takeout.apter.ReviewlistApter;
import com.example.smartcity_20.service.takeout.bean.FoodBean;
import com.example.smartcity_20.service.takeout.bean.FoodlistBean;
import com.example.smartcity_20.service.takeout.bean.FoodtypetakoutBean;
import com.example.smartcity_20.service.takeout.bean.ReviewBean;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    private TextView distance2;
    private TextView saleQuantity2;
    private RecyclerView foodtypelist;
    private LinearLayout ll_foodbody;
    private TextView money;
    private TextView account;
    private String  TAG ="TAG";
    private List<FoodBean.DataDTO> numlist;
    private String substring;

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

        //点菜
        Orderdishes();
        //菜品分类
        foodtypemothod();

        mytab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String name = tab.getText().toString();
                if(typelist.get(0).equals(name)){
                    //点菜
                    Orderdishes();
                    //菜品分类
                    foodtypemothod();
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


    public void displayprice(String moneyapter,List<FoodBean.DataDTO> list){
        substring = moneyapter.substring(0, moneyapter.indexOf(".")+2);
        Iterator<FoodBean.DataDTO> iterator = list.iterator();
        numlist = new ArrayList();
        while (iterator.hasNext()) {
            FoodBean.DataDTO next = iterator.next();
            if(next.getNums()!=0){
                numlist.add(next);
            }
        }
        money.setText(substring);
    }
    private void foodtypemothod() {
        re.setVisibility(View.GONE);
        ll_foodbody.setVisibility(View.VISIBLE);
        reviewlist.setVisibility(View.GONE);
        Integer id = rowsBean.getId();
        if(id ==null){
            return;
        }
        tool.send("/prod-api/api/takeout/category/list?sellerId="+id,
                "GET",
                null,
                true,
                FoodtypetakoutBean.class,
                new Function1<FoodtypetakoutBean, Unit>() {
                    @Override
                    public Unit invoke(FoodtypetakoutBean foodtypetakoutBean) {
                        if(foodtypetakoutBean.getCode()==200){
                            foodtypelist.setLayoutManager(new LinearLayoutManager(context));
                            foodtypelist.setAdapter(new FoodtypetakoutApter(context,foodtypetakoutBean.getData()));
                        }
                        return null;
                    }
                });
    }


    private void reviewmethod() {
        re.setVisibility(View.GONE);
        ll_foodbody.setVisibility(View.GONE);
        reviewlist.setVisibility(View.VISIBLE);
        Integer id = rowsBean.getId();
        if(id ==null){
            return;
        }
        tool.send("/prod-api/api/takeout/comment/list?sellerId="+id,
                "GET",
                null,
                true,
                ReviewBean.class,
                new Function1<ReviewBean, Unit>() {
                    @Override
                    public Unit invoke(ReviewBean review) {
                        if(review.getCode()==200){
                            reviewlist.setLayoutManager(new LinearLayoutManager(context));
                            reviewlist.setAdapter(new ReviewlistApter(context,review.getRows()));
                        }
                        return null;
                    }
                });

    }



    private void Orderdishes() {
        re.setVisibility(View.VISIBLE);
        ll_foodbody.setVisibility(View.GONE);
        reviewlist.setVisibility(View.GONE);

        //由于api接口文档获取id有问题,这里先写定参数
        tool.send("/prod-api/api/takeout/product/list?categoryId=5&sellerId=4",
                "GET",
                null,
                true,
                FoodBean.class,
                new Function1<FoodBean, Unit>() {
                    @Override
                    public Unit invoke(FoodBean foodBean) {
                        if(foodBean.getCode()==200){
                            foodlist.setLayoutManager(new LinearLayoutManager(context));
                            foodlist.setAdapter(new FoodlisttakeoutApter(context,foodBean.getData()));
                        }
                        return null;
                    }
                });
    }

    private void shopmethod() {
        try {
            re.setVisibility(View.VISIBLE);
            ll_foodbody.setVisibility(View.GONE);
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

            if(rowsBean.getSaleQuantity() !=null ){
                saleQuantity2.setText("月销量 "+String.valueOf(rowsBean.getSaleQuantity()));
            }

            if(rowsBean.getDistance() !=  null){
                distance2.setText("到店距离: "+String.valueOf(rowsBean.getDistance()));
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
        saleQuantity2 = findViewById(R.id.saleQuantity2);
        distance2 = findViewById(R.id.distance2);
        ll_foodbody = findViewById(R.id.ll_foodbody);
        foodtypelist = findViewById(R.id.foodtypelist);

        money = findViewById(R.id.money);
        account = findViewById(R.id.account);


        ll_foodbody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(context, BillingpageActivity.class);
                    if(numlist!=null &&  !TextUtils.isEmpty(substring) && !"0.0".equals(substring.trim())){
                        String numsjson = gson.toJson(numlist);
                        intent.putExtra(Common.numlist,numsjson);
                        intent.putExtra(Common.money,substring);
                        intent.putExtra(Common.shopname,rowsBean.getName());
                            context.startActivity(intent);
                    }else {
                        Toast.makeText(context,"请选择菜品",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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
