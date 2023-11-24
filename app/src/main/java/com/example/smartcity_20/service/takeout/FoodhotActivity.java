package com.example.smartcity_20.service.takeout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.Common;
import com.example.smartcity_20.config.java.IpandPort;
import com.example.smartcity_20.config.kotlin.Tool;
import com.example.smartcity_20.service.takeout.apter.FoodhottyApter;
import com.example.smartcity_20.service.takeout.bean.FoodhotdetailsBean;
import com.example.smartcity_20.service.takeout.bean.FoodhottyBean;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class FoodhotActivity extends AppCompatActivity {

    private String id;
    private FoodhotActivity context;
    private Tool tool;
    private ImageView ic_back;
    private ImageView imgUrl;
    private TextView title;
    private TextView address;
    private TextView avgCost;
    private TextView distance;
    private TextView saleQuantity;
    private TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodhot);
        context = this;
        initview();
        Foodhotmenthod();
        img_bloak();
    }

    private void Foodhotmenthod() {
        tool.send("/prod-api/api/takeout/seller/"+id,
                "GET",
                null,
                true,
                FoodhotdetailsBean.class,
                new Function1<FoodhotdetailsBean, Unit>() {
                    @Override
                    public Unit invoke(FoodhotdetailsBean foodhotdetailsBean) {
                        if(foodhotdetailsBean.getCode()==200){
                            ifnullfoodhot(foodhotdetailsBean.getData());
                        }
                        return null;
                    }
                });
    }

    private void ifnullfoodhot(FoodhotdetailsBean.DataBean data) {
        try {
            if(data ==null){
                return;
            }

            if(!TextUtils.isEmpty(data.getImgUrl())){
                Glide.with(context).load(IpandPort.URL+data.getImgUrl()).into(imgUrl);
            }

            if(!TextUtils.isEmpty(data.getName())){
                title.setText(data.getName());
            }

            if(!TextUtils.isEmpty(data.getAddress())){
                address.setText(data.getAddress());
            }

            if(data.getAvgCost() !=null){
                avgCost.setText(String.valueOf(data.getAvgCost()));
            }

            if(data.getDistance() !=null){
                distance.setText(String.valueOf(data.getDistance()));
            }

            if(data.getSaleQuantity() !=null){
                saleQuantity.setText(String.valueOf(data.getSaleQuantity()));
            }

            if(data.getScore() !=null){
                score.setText(String.valueOf(data.getScore()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initview() {
        Intent intent = getIntent();
        id = intent.getStringExtra(Common.FoodhotActivity);

        ic_back = findViewById(R.id.ic_back);
        imgUrl = findViewById(R.id.imgUrl);
        title = findViewById(R.id.title);
        address = findViewById(R.id.address);
        avgCost = findViewById(R.id.avgCost);
        distance = findViewById(R.id.distance);
        saleQuantity = findViewById(R.id.saleQuantity);
        score = findViewById(R.id.score);
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