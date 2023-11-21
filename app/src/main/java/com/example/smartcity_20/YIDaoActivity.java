package com.example.smartcity_20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class YIDaoActivity extends AppCompatActivity {

    private TextView set_word;
    private TextView set_main;
    private ViewPager vp;
    private View view1;
    private View view2;
    private View view3;
    private View view5;
    private View view4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_y_i_dao);
        initview();
        imglist();
    }

    private void imglist() {
        ArrayList<Integer> img_list = new ArrayList();
        img_list.add(R.drawable.img_yi);
        img_list.add(R.drawable.img_er);
        img_list.add(R.drawable.img_san);
        img_list.add(R.drawable.img_si);
        img_list.add(R.drawable.img_wu);

        //vp.setAdapter();
    }

    private void initview() {
        set_word = findViewById(R.id.set_word);
        set_main = findViewById(R.id.set_main);
        vp = findViewById(R.id.vp);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        view5 = findViewById(R.id.view5);
        view4 = findViewById(R.id.view4);


    }
}