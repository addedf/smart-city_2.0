package com.example.smartcity_20.service.takeout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.smartcity_20.R;
import com.example.smartcity_20.config.kotlin.Tool;
import com.example.smartcity_20.service.takeout.Fragment.HometakeoutFragment;
import com.example.smartcity_20.service.takeout.Fragment.MytakeoutFragment;
import com.example.smartcity_20.service.takeout.Fragment.OrdertakeoutFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TakeOutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_out);
        initView();
    }

    private void initView() {
        LinearLayout ll_body = findViewById(R.id.ll_body);
        BottomNavigationView bot = findViewById(R.id.bot);

        HometakeoutFragment hometake = new HometakeoutFragment();
        MytakeoutFragment mainFragment = new MytakeoutFragment();
        OrdertakeoutFragment ordert = new OrdertakeoutFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.ll_body,hometake).commit();
        bot.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.hometakeout:
                        getSupportFragmentManager().beginTransaction().replace(R.id.ll_body,hometake).commit();
                        break;
                    case R.id.ordertakeout:
                        getSupportFragmentManager().beginTransaction().replace(R.id.ll_body,mainFragment).commit();
                        break;
                    case R.id.mytakeout:
                        getSupportFragmentManager().beginTransaction().replace(R.id.ll_body,ordert).commit();
                        break;
                }
                return true;
            }
        });
    }
}