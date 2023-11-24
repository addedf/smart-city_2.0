package com.example.smartcity_20.service.pethospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.Common;
import com.example.smartcity_20.config.java.IpandPort;
import com.example.smartcity_20.service.bean.DoctorBean;
import com.google.gson.Gson;

public class HospitalinquiryActivity extends AppCompatActivity {

    private HospitalinquiryActivity context;
    private ImageView ic_back;
    private DoctorBean.RowsDTO rowsDTO;
    private EditText question;
    private TextView submit;
    private TextView practiceNo;
    private TextView jobName;
    private TextView name;
    private ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_hospitalinquiry);
        initview();
        initmethod();
        img_bloak();
    }

    private void initmethod() {
        if(rowsDTO==null){
            return;
        }

        String te_avatar = rowsDTO.getAvatar();
        if(!TextUtils.isEmpty(te_avatar)){
            Glide.with(context).load(IpandPort.URL+te_avatar).into(avatar);
        }

        String te_name = rowsDTO.getName();
        if(!TextUtils.isEmpty(te_name)){
            name.setText(te_name);
        }



        String te_jobname= rowsDTO.getJobName();
        if(!TextUtils.isEmpty(te_jobname)){
            jobName.setText(te_jobname);
        }

        String te_PracticeNo= rowsDTO.getPracticeNo();
        if(!TextUtils.isEmpty(te_PracticeNo)){
            practiceNo.setText(te_PracticeNo);
        }
    }

    private void initview() {
        Intent intent = getIntent();
        Gson gson = new Gson();
        String stringExtra = intent.getStringExtra(Common.HospitalinquiryActivityid);
        rowsDTO = gson.fromJson(stringExtra, DoctorBean.RowsDTO.class);
        ic_back = findViewById(R.id.ic_back);
        avatar = findViewById(R.id.avatar);
        name = findViewById(R.id.name);
        jobName = findViewById(R.id.jobName);
        practiceNo = findViewById(R.id.practiceNo);
        submit = findViewById(R.id.submit);
        question = findViewById(R.id.Question);
    }


    private void img_bloak() {
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });
    }
}