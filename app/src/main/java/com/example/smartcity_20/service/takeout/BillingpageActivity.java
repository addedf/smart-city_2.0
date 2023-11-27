package com.example.smartcity_20.service.takeout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.Common;
import com.example.smartcity_20.config.kotlin.Tool;
import com.example.smartcity_20.service.takeout.apter.CarteApter;
import com.example.smartcity_20.service.takeout.bean.FoodBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class BillingpageActivity extends AppCompatActivity {

    private BillingpageActivity context;
    private String TAG = "TAG";
    private TextView deliveryaddress;
    private TextView moneynum;
    private RecyclerView foodlist;
    private LinearLayout account;
    private List<FoodBean.DataDTO> dataDTO;
    private Tool tool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billingpage);
        context = this;
        initview();
        dataDTOmethod();
    }

    private void dataDTOmethod() {
        foodlist.setLayoutManager(new LinearLayoutManager(context));
        foodlist.setAdapter(new CarteApter(context,dataDTO));
    }

    private void initview() {
        Gson gson = new Gson();
        Intent intent = getIntent();
        String money = intent.getStringExtra(Common.money);
        String numlist = intent.getStringExtra(Common.numlist);
        String shopname = intent.getStringExtra(Common.shopname);
        dataDTO = gson.fromJson(numlist, new TypeToken<List<FoodBean.DataDTO>>() {
        }.getType());
        TextView name = findViewById(R.id.name);
        ImageView ic_back = findViewById(R.id.ic_back);
        deliveryaddress = findViewById(R.id.deliveryaddress);
        moneynum = findViewById(R.id.moneynum);
        foodlist = findViewById(R.id.foodlist);
        account = findViewById(R.id.account);
        tool = new Tool(context);

        moneynum.setText(money);
        name.setText(shopname);


        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });


        deliveryaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View inflate = LayoutInflater.from(context).inflate(R.layout.l_dialog_deliveryaddress, null);
                TextView add_deliveryaddress = inflate.findViewById(R.id.add_deliveryaddress);
                RecyclerView deliveryaddresslist = inflate.findViewById(R.id.deliveryaddresslist);
                add_deliveryaddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context,AddeliveryaddressActivity.class);
                        context.startActivity(intent);
                    }
                });
                builder.setView(inflate);
                builder.create().show();
            }
        });
    }
}