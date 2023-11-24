package com.example.smartcity_20;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.smartcity_20.apter.YIDaoApter;
import com.example.smartcity_20.config.java.IpandPort;

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
    private String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_y_i_dao);
        SharedPreferences isfirstlogin = getSharedPreferences("isfirstlogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = isfirstlogin.edit();
        boolean isfirst = isfirstlogin.getBoolean("isfirst", true);
        if(isfirst){
            initview();
            imglist();
            setipandport();

            set_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edit.putBoolean("isfirst",false);
                    edit.apply();
                    gethome();
                }
            });
        }else {
            gethome();
        }

    }

    private void gethome() {
        Intent intent = new Intent(YIDaoActivity.this,MainActivity.class);
        this.startActivity(intent);
    }


    private void setipandport() {
        set_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(YIDaoActivity.this);
                View inflate = LayoutInflater.from(YIDaoActivity.this).inflate(R.layout.l_item_ipandport, null);
                EditText ip = inflate.findViewById(R.id.ip);
                EditText port = inflate.findViewById(R.id.port);
                ip.setText(IpandPort.IP);
                port.setText(IpandPort.PORT);
                builder.setView(inflate);
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String ip_String = ip.getText().toString();
                        String port_String = port.getText().toString();
                        IpandPort.IP = ip_String;
                        IpandPort.PORT = port_String;
                        Toast.makeText(YIDaoActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                // 设置按钮的颜色
                Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);

                // 设置按钮的背景颜色为白色
                positiveButton.setBackgroundColor(Color.WHITE);
                negativeButton.setBackgroundColor(Color.WHITE);

                positiveButton.setTextColor(Color.BLACK); // 设置"确定"按钮的颜色
                negativeButton.setTextColor(Color.BLACK); // 设置"取消"按钮的颜色
            }
        });
    }

    private void imglist() {
        ArrayList<Integer> img_list = new ArrayList();
        img_list.add(R.drawable.img_yi);
        img_list.add(R.drawable.img_er);
        img_list.add(R.drawable.img_san);
        img_list.add(R.drawable.img_si);
        img_list.add(R.drawable.img_wu);

        ArrayList<View> yuan_list = new ArrayList();
        yuan_list.add(view1);
        yuan_list.add(view2);
        yuan_list.add(view3);
        yuan_list.add(view4);
        yuan_list.add(view5);
        vp.setAdapter(new YIDaoApter(this,img_list));
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < yuan_list.size(); i++) {
                    if(position==i){
                        yuan_list.get(i).setBackground(getDrawable(R.drawable.l_yuan_white));
                    }else {
                        yuan_list.get(i).setBackground(getDrawable(R.drawable.l_yuan_zhongse));
                    }
                }

               if(img_list.size()-1==position){
                   set_word.setVisibility(View.VISIBLE);
                   set_main.setVisibility(View.VISIBLE);
               }else {
                   set_word.setVisibility(View.GONE);
                   set_main.setVisibility(View.GONE);
               }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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