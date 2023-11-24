package com.example.smartcity_20.me;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.Common;
import com.example.smartcity_20.config.java.OkHttpRequest;
import com.example.smartcity_20.me.bean.QuerypersonalBean;
import com.example.smartcity_20.me.bean.StatusBean;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class PersonalinformationActivity extends AppCompatActivity {

    private PersonalinformationActivity context;
    private EditText email;
    private EditText phonenumber;
    private RadioGroup sex;
    private EditText nikename;
    private ImageView back;
    private QuerypersonalBean queryperson;
    private Gson gson;
    private RadioButton nv;
    private RadioButton nan;
    private TextView save;
    String edit_sex = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinformation);
        initview();
        img_bloak();
        querypersonlinformation();
        editqueryperson();
    }

    private void editqueryperson() {

        sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                if(checkedRadioButtonId==R.id.nan){
                    edit_sex ="0";
                }else if(checkedRadioButtonId==R.id.nv){
                    edit_sex ="1";
                }
            }
        });


      save.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String edit_name = nikename.getText().toString();
              String edit_email = email.getText().toString();
              String edit_phonenumber = phonenumber.getText().toString();

              if(!"".equals(edit_name.trim()) && !TextUtils.isEmpty(edit_name) &&
                      !"".equals(edit_email.trim()) && !TextUtils.isEmpty(edit_email) &&
                      !"".equals(edit_phonenumber.trim()) && !TextUtils.isEmpty(edit_phonenumber) &&
                      !"".equals(edit_sex.trim()) && !TextUtils.isEmpty(edit_sex)){
                  JSONObject jsonObject = new JSONObject();
                  try {
                      jsonObject.put("email",edit_email);
                      jsonObject.put("nickName",edit_name);
                      jsonObject.put("phonenumber",edit_phonenumber);
                      jsonObject.put("sex",edit_sex);
                      sendpenson(jsonObject.toString());
                  } catch (JSONException e) {
                      e.printStackTrace();
                  }

              }else {
                  Toast.makeText(context,"内容不能为空",Toast.LENGTH_SHORT).show();
              }
          }
      });
    }

    private void sendpenson(String JSON) {
        OkHttpRequest.doNetRequst("prod-api/api/common/user",
                OkHttpRequest.PUT,
                StatusBean.class,
                new OkHttpRequest.NetRequst() {
                    @Override
                    public void ok(Object obj) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                StatusBean status  = (StatusBean)obj;
                                if(status.getCode()==200){
                                    Toast.makeText(context,"修改成功",Toast.LENGTH_SHORT).show();
                                    context.finish();
                                }else {
                                    Toast.makeText(context, status.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void no(String msg) {

                    }
                },JSON);
    }

    private void querypersonlinformation() {
        try {
            QuerypersonalBean.UserDTO user = queryperson.getUser();
            if(user==null){
                return;
            }

            String nickName = user.getNickName();
            if(!TextUtils.isEmpty(nickName)){
                nikename.setText(nickName);
            }

            String sex = user.getSex();
            if(!TextUtils.isEmpty(sex)){
                if("0".equals(sex)){
                    nan.setChecked(true);
                    edit_sex = "0";
                }else if("1".equals(sex)){
                    nv.setChecked(true);
                    edit_sex = "1";
                }
            }

            String emaill = user.getEmail();
            if(!TextUtils.isEmpty(emaill)){
                email.setText(emaill);
            }

            String userPhonenumber = user.getPhonenumber();
            if(!TextUtils.isEmpty(userPhonenumber)){
                phonenumber.setText(userPhonenumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void img_bloak() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });
    }

    private void initview() {
        Intent intent = getIntent();
        String JSON = intent.getStringExtra(Common.QuerypersonalBean);
        gson = new Gson();
        queryperson = gson.fromJson(JSON, QuerypersonalBean.class);
        back = findViewById(R.id.back);
        sex = findViewById(R.id.sex);
        nikename = findViewById(R.id.nikename);
        save = findViewById(R.id.save);
        nan = findViewById(R.id.nan);
        nv = findViewById(R.id.nv);
        phonenumber = findViewById(R.id.phonenumber);
        email = findViewById(R.id.email);
        context = PersonalinformationActivity.this;

    }
}