package com.example.smartcity_20.service.takeout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.OkHttpRequest;
import com.example.smartcity_20.config.kotlin.Tool;
import com.example.smartcity_20.me.LoginActivity;
import com.example.smartcity_20.service.takeout.Fragment.HometakeoutFragment;
import com.example.smartcity_20.service.takeout.Fragment.MytakeoutFragment;
import com.example.smartcity_20.service.takeout.Fragment.OrdertakeoutFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public class TakeOutActivity extends AppCompatActivity {


    private TakeOutActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_out);
        context = this;
        if(TextUtils.isEmpty(OkHttpRequest.TOKEN)){
            Snackbar snackbar = Snackbar.make(findViewById(R.id.layou), "请先登录", Snackbar.LENGTH_LONG);
            snackbar.setAction("登录", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                }
            });
            snackbar.show();
        }
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //未登录的时候,点击登录刷新登录界面
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
                        getSupportFragmentManager().beginTransaction().replace(R.id.ll_body,ordert).commit();
                        break;
                    case R.id.mytakeout:
                        getSupportFragmentManager().beginTransaction().replace(R.id.ll_body,mainFragment).commit();
                        break;
                }
                return true;
            }
        });
    }
}