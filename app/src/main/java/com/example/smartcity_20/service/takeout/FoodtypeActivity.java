package com.example.smartcity_20.service.takeout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.Common;
import com.example.smartcity_20.config.kotlin.Tool;
import com.example.smartcity_20.service.takeout.apter.FoodlistApter;
import com.example.smartcity_20.service.takeout.bean.FoodlistBean;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class FoodtypeActivity extends AppCompatActivity {

    private String id;
    private String name;
    private FoodtypeActivity context;
    private RecyclerView shoplist;
    private ImageView ic_back;
    private Tool tool;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodtype);
        context = this;
        initview();
        shoplistmethod();
        img_bloak();
    }

    private void shoplistmethod() {
        tool.send("/prod-api/api/takeout/seller/list?themeId="+id,
                "GET",
                null,
                true,
                FoodlistBean.class,
                new Function1<FoodlistBean, Unit>() {
                    @Override
                    public Unit invoke(FoodlistBean foodlistBean) {
                        if(foodlistBean.getCode()==200){
                            shoplist.setLayoutManager(new LinearLayoutManager(context));
                            shoplist.setAdapter(new FoodlistApter(context,foodlistBean.getRows()));
                        }
                        return null;
                    }
                });
    }

    private void initview() {
        Intent intent = getIntent();
        id = intent.getStringExtra(Common.FoodtypeActivityid);
        name = intent.getStringExtra(Common.FoodtypeActivityname);

        ic_back = findViewById(R.id.ic_back);
        shoplist = findViewById(R.id.shoplist);
        title = findViewById(R.id.title);title.setText(name);

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