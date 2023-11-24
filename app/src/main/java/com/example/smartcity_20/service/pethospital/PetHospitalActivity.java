package com.example.smartcity_20.service.pethospital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.OkHttpRequest;
import com.example.smartcity_20.service.apter.PetTypeApter;
import com.example.smartcity_20.service.apter.PetcaseApter;
import com.example.smartcity_20.service.bean.PetTypeBean;
import com.example.smartcity_20.service.bean.PetcaseBean;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class PetHospitalActivity extends AppCompatActivity {

    private PetHospitalActivity context;
    private ImageView ic_back;
    private RecyclerView mycaselist;
    private RecyclerView typelist;
    private TabLayout mytab;
    private RecyclerView caselist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_hospital);
        context = this;
        initview();
        img_bloak();
        typelistmethod();
        mytabmethod();
    }

    private void mytabmethod() {
        ArrayList<String> stringArrayList = new ArrayList();
        stringArrayList.add("问诊案例");
        stringArrayList.add("我的问诊");
        for (String s : stringArrayList) {
            TabLayout.Tab tab = mytab.newTab();
            tab.setText(s);
            mytab.addTab(tab);
        }
        sendcaselist();

        mytab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String type = tab.getText().toString();
                if(stringArrayList.get(0).equals(type)){
                    sendcaselist();
                }else if(stringArrayList.get(1).equals(type)){
                    sendmypetlist();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void sendmypetlist() {
        caselist.setVisibility(View.GONE);
        mycaselist.setVisibility(View.VISIBLE);

        OkHttpRequest.doNetRequst("/prod-api/api/pet-hospital/inquiry/my-list",
                OkHttpRequest.GET,
                PetcaseBean.class,
                new OkHttpRequest.NetRequst() {
                    @Override
                    public void ok(Object obj) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                PetcaseBean petcase = (PetcaseBean)obj;
                                if(petcase.getCode()==200){
                                    mycaselist.setAdapter(new PetcaseApter(context,petcase.getRows()));
                                    mycaselist.setLayoutManager(new LinearLayoutManager(context));
                                }
                            }
                        });
                    }

                    @Override
                    public void no(String msg) {

                    }
                });
    }

    private void sendcaselist() {
        caselist.setVisibility(View.VISIBLE);
        mycaselist.setVisibility(View.GONE);

        OkHttpRequest.doNetRequst("prod-api/api/pet-hospital/inquiry/list?pageNum=1&pageSize=10",
                OkHttpRequest.GET,
                PetcaseBean.class,
                new OkHttpRequest.NetRequst() {
                    @Override
                    public void ok(Object obj) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                PetcaseBean petcase = (PetcaseBean)obj;
                                if(petcase.getCode()==200){
                                    caselist.setAdapter(new PetcaseApter(context,petcase.getRows()));
                                    caselist.setLayoutManager(new LinearLayoutManager(context));
                                }
                            }
                        });
                    }

                    @Override
                    public void no(String msg) {

                    }
                });
    }

    private void typelistmethod() {
        OkHttpRequest.doNetRequst("prod-api/api/pet-hospital/pet-type/list",
                OkHttpRequest.GET,
                PetTypeBean.class,
                new OkHttpRequest.NetRequst() {
                    @Override
                    public void ok(Object obj) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                PetTypeBean petType = (PetTypeBean)obj;
                                if(petType.getCode()==200){
                                    typelist.setAdapter(new PetTypeApter(context,petType.getData()));
                                    typelist.setLayoutManager(new GridLayoutManager(context,5));
                                }
                            }
                        });
                    }

                    @Override
                    public void no(String msg) {

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

    private void initview() {
        ic_back = findViewById(R.id.ic_back);
        mycaselist = findViewById(R.id.mycaselist);
        typelist = findViewById(R.id.typelist);
        mytab = findViewById(R.id.mytab);
        caselist = findViewById(R.id.caselist);
    }
}