package com.example.smartcity_20.service.takeout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.Common;
import com.example.smartcity_20.config.kotlin.Tool;
import com.example.smartcity_20.service.takeout.apter.OrderdetailsApter;
import com.example.smartcity_20.service.takeout.bean.GoodsnumberBean;
import com.example.smartcity_20.service.takeout.bean.OrderdetailsBean;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class OrderdetailsActivity extends AppCompatActivity {

    private OrderdetailsActivity context;
    private String id;
    private TextView name;
    private RecyclerView refoodlist;
    private TextView receiverAddress;
    private TextView receiverPhone;
    private TextView payTime;
    private TextView orderNo;
    private TextView paymentType;
    private TextView status;
    private TextView monrytotle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);
        context = this;
        initview();
        sendorderdetails();
    }

    private void sendorderdetails() {
        new Tool(context).send("/prod-api/api/takeout/order/" + id,
                "GET",
                null,
                true,
                OrderdetailsBean.class,
                new Function1<OrderdetailsBean, Unit>() {
                    @Override
                    public Unit invoke(OrderdetailsBean orderdetailsBean) {
                        if(orderdetailsBean.getCode()==200){
                            refoodlist.setLayoutManager(new LinearLayoutManager(context));
                            List<OrderdetailsBean.DataDTO.OrderInfoDTO.OrderItemListDTO> orderItemList = orderdetailsBean.getData().getOrderInfo().getOrderItemList();
                            refoodlist.setAdapter(new OrderdetailsApter(context,orderItemList));
                            information(orderdetailsBean);
                        }
                        return null;
                    }
                });
    }

    private void information(OrderdetailsBean orderdetailsBean) {
        try {
            if(orderdetailsBean.getData()==null){
                return;
            }
            OrderdetailsBean.DataDTO data = orderdetailsBean.getData();
            OrderdetailsBean.DataDTO.OrderInfoDTO orderInfo = data.getOrderInfo();
            OrderdetailsBean.DataDTO.SellerInfoDTO sellerInfo = data.getSellerInfo();
            if(orderInfo==null || sellerInfo==null){
                return;
            }
            if(!TextUtils.isEmpty(sellerInfo.getName())){
               name .setText(sellerInfo.getName());
            }

            if(!TextUtils.isEmpty(orderInfo.getReceiverAddress())){
                receiverAddress .setText(orderInfo.getReceiverAddress());
            }

            if(!TextUtils.isEmpty(orderInfo.getReceiverPhone())){
                receiverPhone .setText(orderInfo.getReceiverPhone());
            }

            if(!TextUtils.isEmpty(orderInfo.getPayTime())){
                payTime .setText(orderInfo.getPayTime());
            }

            if(!TextUtils.isEmpty(orderInfo.getPaymentType())){
                paymentType .setText(orderInfo.getPaymentType());
            }

            if(!TextUtils.isEmpty(orderInfo.getStatus())){
                status .setText(orderInfo.getStatus());
            }

            monrytotle.setText("合计: "+String.valueOf(orderInfo.getAmount())+"元");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initview() {
        ImageView ic_back = findViewById(R.id.ic_back);
        Intent intent = getIntent();
        id = intent.getStringExtra(Common.OrderdetailsActivityid);

        name = findViewById(R.id.name);
        refoodlist = findViewById(R.id.refoodlist);
        receiverAddress = findViewById(R.id.receiverAddress);
        receiverPhone = findViewById(R.id.receiverPhone);
        payTime = findViewById(R.id.payTime);
        orderNo = findViewById(R.id.orderNo);
        paymentType = findViewById(R.id.paymentType);
        status = findViewById(R.id.status);
        monrytotle = findViewById(R.id.monrytotle);


        orderNo.setText(id);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });
    }

}