package com.example.smartcity_20.service.takeout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity_20.R;
import com.example.smartcity_20.config.kotlin.Tool;
import com.example.smartcity_20.me.bean.StatusBean;

import org.json.JSONException;
import org.json.JSONObject;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class AddeliveryaddressActivity extends AppCompatActivity {

    private AddeliveryaddressActivity context;
    private EditText address;
    private RadioGroup ra;
    private EditText contactperson;
    private EditText phone;
    private TextView save;
    private String tag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addeliveryaddress);
        context = this;
        initview();
    }

    private void initview() {
        ImageView ic_back = findViewById(R.id.ic_back);
        address = findViewById(R.id.address);
        ra = findViewById(R.id.ra);
        contactperson = findViewById(R.id.contactperson);
        phone = findViewById(R.id.phone);
        save = findViewById(R.id.save);


        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloakBillingpageActivity();
            }
        });


        ra.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                if (checkedRadioButtonId == R.id.ra1) {
                    tag = "家";
                } else if (checkedRadioButtonId == R.id.ra2) {
                    tag = "公司";
                } else if (checkedRadioButtonId == R.id.ra3) {
                    tag = "学校";
                } else if (checkedRadioButtonId == R.id.ra4) {
                    tag = "父母家";
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ed_address = address.getText().toString();
                String ed_contactperson = contactperson.getText().toString();
                String ed_phone = phone.getText().toString();


                if (!"".equals(tag.trim())
                        && !TextUtils.isEmpty(ed_address.trim())
                        && !TextUtils.isEmpty(ed_contactperson.trim())
                        && !TextUtils.isEmpty(ed_phone.trim())) {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("addressDetail", ed_address);
                        jsonObject.put("label", tag);
                        jsonObject.put("name", ed_contactperson);
                        jsonObject.put("phone", ed_phone);
                        sendpost(jsonObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, "地址不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendpost(String JSON) {
        new Tool(context).send("/prod-api/api/takeout/address",
                "POST",
                JSON,
                true,
                StatusBean.class,
                new Function1<StatusBean, Unit>() {
                    @Override
                    public Unit invoke(StatusBean statusBean) {
                        if(statusBean.getCode()==200){
                            Toast.makeText(context,"保存成功",Toast.LENGTH_SHORT).show();
                            bloakBillingpageActivity();
                        }else {
                            Toast.makeText(context,statusBean.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                        return null;
                    }
                });
    }


    private void bloakBillingpageActivity(){
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        context.finish();
    }

}