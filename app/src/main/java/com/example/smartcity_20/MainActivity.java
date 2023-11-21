package com.example.smartcity_20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.smartcity_20.Fragment.HelpFragment;
import com.example.smartcity_20.Fragment.HomeFragment;
import com.example.smartcity_20.Fragment.MeFragment;
import com.example.smartcity_20.Fragment.NewsFragment;
import com.example.smartcity_20.Fragment.ServlerFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView botna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
    }

    private void initview() {
        botna = findViewById(R.id.botna);

        HomeFragment homeFragment = new HomeFragment();
        HelpFragment helpFragment = new HelpFragment();
        MeFragment meFragment = new MeFragment();
        NewsFragment newsFragment = new NewsFragment();
        ServlerFragment servlerFragment = new ServlerFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.ll_body,homeFragment).commit();
        botna.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home :
                        getSupportFragmentManager().beginTransaction().replace(R.id.ll_body,homeFragment).commit();
                        break;
                    case R.id.help :
                        getSupportFragmentManager().beginTransaction().replace(R.id.ll_body,helpFragment).commit();
                        break;
                    case R.id.servler :
                        getSupportFragmentManager().beginTransaction().replace(R.id.ll_body,servlerFragment).commit();
                        break;
                    case R.id.news :
                        getSupportFragmentManager().beginTransaction().replace(R.id.ll_body,newsFragment).commit();
                        break;
                    case R.id.me :
                        getSupportFragmentManager().beginTransaction().replace(R.id.ll_body,meFragment).commit();
                        break;
                }
                return true;
            }
        });
    }
}