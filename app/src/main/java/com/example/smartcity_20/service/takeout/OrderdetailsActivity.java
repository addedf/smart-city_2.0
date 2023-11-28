package com.example.smartcity_20.service.takeout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.Common;
import com.example.smartcity_20.config.kotlin.Tool;
import com.example.smartcity_20.service.takeout.bean.GoodsnumberBean;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class OrderdetailsActivity extends AppCompatActivity {

    private OrderdetailsActivity context;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);
        context = this;
        initview();
        sendorderdetails();
    }

    private void sendorderdetails() {
       /* new Tool(context).send("/prod-api/api/takeout/order/" + id,
                "GET",
                null,
                true,
                GoodsnumberBean.class,
                new Function1<GoodsnumberBean, Unit>() {
                    @Override
                    public Unit invoke(GoodsnumberBean goodsnumberBean) {
                        if(goodsnumberBean.getCode()==200){


                        }
                        return null;
                    }
                });*/
    }

    private void initview() {
        ImageView ic_back = findViewById(R.id.ic_back);
        Intent intent = getIntent();
        id = intent.getStringExtra(Common.OrderdetailsActivityid);

        TextView name = findViewById(R.id.name);
        RecyclerView refoodlist = findViewById(R.id.refoodlist);
        TextView receiverAddress = findViewById(R.id.receiverAddress);
        TextView receiverPhone = findViewById(R.id.receiverPhone);
        TextView payTime = findViewById(R.id.payTime);
        TextView orderNo = findViewById(R.id.orderNo);
        TextView paymentType = findViewById(R.id.paymentType);
        TextView status = findViewById(R.id.status);


        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });
    }

}