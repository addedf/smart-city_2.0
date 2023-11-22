package com.example.smartcity_20.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.Common;
import com.example.smartcity_20.config.java.IpandPort;
import com.example.smartcity_20.config.java.OkHttpRequest;
import com.example.smartcity_20.home.bean.NewXuanQingBean;

public class NewXuanQingActivity extends AppCompatActivity {

    private ImageView bloak;
    private TextView title;
    private TextView content;
    private ImageView cover;
    private String id;
    private String TAG = "TAG";
    private NewXuanQingActivity newXuanQingActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_xuan_qing);
        initview();
        img_bloak();
        sendidnew();
    }

    private void img_bloak() {
       bloak.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               newXuanQingActivity.finish();
           }
       });
    }

    private void sendidnew() {
        if(TextUtils.isEmpty(id)){
            return;
        }
        OkHttpRequest.doNetRequst("prod-api/press/press/" + id,
                OkHttpRequest.GET,
                NewXuanQingBean.class,
                new OkHttpRequest.NetRequst() {
                    @Override
                    public void ok(Object obj) {
                        NewXuanQingActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                NewXuanQingBean newBean = (NewXuanQingBean)obj;
                                if(newBean.getCode()==200){
                                    try {
                                        NewXuanQingBean.DataDTO data = newBean.getData();
                                        title.setText(data.getTitle());
                                        String contents = Html.fromHtml(data.getContent()).toString();
                                        content.setText(contents);
                                        Glide.with(newXuanQingActivity).load(IpandPort.URL+data.getCover()).into(cover);
                                    } catch (Exception e) {
                                       Log.e(TAG,e.getMessage());
                                    }
                                }
                            }
                        });
                    }

                    @Override
                    public void no(String msg) {

                    }
                });
    }

    private void initview() {
        Intent intent = getIntent();
        id = intent.getStringExtra(Common.NEWXUANQINGACTIVITY);
        bloak = findViewById(R.id.bloak);
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        cover = findViewById(R.id.cover);

        newXuanQingActivity = NewXuanQingActivity.this;
    }
}