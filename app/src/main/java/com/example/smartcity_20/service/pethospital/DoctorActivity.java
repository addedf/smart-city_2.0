package com.example.smartcity_20.service.pethospital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.Common;
import com.example.smartcity_20.config.java.OkHttpRequest;
import com.example.smartcity_20.service.apter.DoctorApter;
import com.example.smartcity_20.service.apter.PetcaseApter;
import com.example.smartcity_20.service.bean.DoctorBean;
import com.example.smartcity_20.service.bean.PetcaseBean;

public class DoctorActivity extends AppCompatActivity {
    private ImageView ic_back;
    private DoctorActivity context;
    private RecyclerView doctorlist;
    private String id;
    private String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        context = this;
        initview();
        img_bloak();
        doctorlistmethod();
    }

    private void doctorlistmethod() {
        OkHttpRequest.doNetRequst("prod-api/api/pet-hospital/pet-doctor/list?pageNum=1&pageSize=10&typeId="+id,
                OkHttpRequest.GET,
                DoctorBean.class,
                new OkHttpRequest.NetRequst() {
                    @Override
                    public void ok(Object obj) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                DoctorBean doctor = (DoctorBean)obj;
                                if(doctor.getCode()==200){
                                    doctorlist.setAdapter(new DoctorApter(context,doctor.getRows()));
                                    doctorlist.setLayoutManager(new LinearLayoutManager(context));
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
        id = intent.getStringExtra(Common.DoctorID);
        ic_back = findViewById(R.id.ic_back);
        doctorlist = findViewById(R.id.doctorlist);
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