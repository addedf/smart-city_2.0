package com.example.smartcity_20.service.pethospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.Common;
import com.example.smartcity_20.config.java.IpandPort;
import com.example.smartcity_20.service.bean.PetcaseBean;
import com.google.gson.Gson;

public class ReadditiondoctorActivity extends AppCompatActivity {

    private ReadditiondoctorActivity context;
    private String json;
    private ImageView ic_back;
    private ImageView avatar;
    private TextView name;
    private TextView jobName;
    private TextView practiceNo;
    private TextView question;
    private TextView doctorwhy;
    private TextView submit;
    private Gson gson;
    private PetcaseBean.RowsDTO rowsDTO;
    private PetcaseBean.RowsDTO.DoctorDTO doctor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readditiondoctor);
        context = this;
        initview();
        initmethod();
        img_bloak();
        submitmethod();
    }

    private void submitmethod() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(context, HospitalinquiryActivity.class);
                    String json = gson.toJson(doctor);
                    intent.putExtra(Common.HospitalinquiryActivityid,json);
                    context.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void img_bloak() {
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });
    }

    private void initmethod() {
        if(rowsDTO==null || doctor==null){
            return;
        }

        String te_avatar = doctor.getAvatar();
        if(!TextUtils.isEmpty(te_avatar)){
            Glide.with(context).load(IpandPort.URL+te_avatar).into(avatar);
        }

        String te_name = doctor.getName();
        if(!TextUtils.isEmpty(te_name)){
            name.setText(te_name);
        }



        String te_jobname= doctor.getJobName();
        if(!TextUtils.isEmpty(te_jobname)){
            jobName.setText(te_jobname);
        }

        String te_PracticeNo= doctor.getPracticeNo();
        if(!TextUtils.isEmpty(te_PracticeNo)){
            practiceNo.setText(te_PracticeNo);
        }

        String te_question= rowsDTO.getQuestion();
        if(!TextUtils.isEmpty(te_question)){
            question.setText(te_question);
        }
    }


    private void initview() {
        Intent intent = getIntent();
        json = intent.getStringExtra(Common.ReadditiondoctorActivityID);
        gson = new Gson();
        rowsDTO = gson.fromJson(json, PetcaseBean.RowsDTO.class);
        doctor = rowsDTO.getDoctor();

        ic_back = findViewById(R.id.ic_back);
        avatar = findViewById(R.id.avatar);
        name = findViewById(R.id.name);
        jobName = findViewById(R.id.jobName);
        practiceNo = findViewById(R.id.practiceNo);
        question = findViewById(R.id.question);
        doctorwhy = findViewById(R.id.doctorwhy);
        submit = findViewById(R.id.submit);
    }
}