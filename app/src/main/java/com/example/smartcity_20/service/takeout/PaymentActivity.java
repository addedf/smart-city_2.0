package com.example.smartcity_20.service.takeout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.Common;

public class PaymentActivity extends AppCompatActivity {

    private PaymentActivity context;
    private String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        context = this;
        initview();
    }

    private void initview() {
        ImageView ic_back = findViewById(R.id.ic_back);
        Intent intent = getIntent();
        String momey = intent.getStringExtra(Common.money);
        Log.d(TAG,"momey"+momey);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });
    }

}