package com.example.smartcity_20.me;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.OkHttpRequest;
import com.example.smartcity_20.config.java.User;
import com.example.smartcity_20.me.bean.GeneralBean;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private LoginActivity context;
    private TextView enroll;
    private EditText ed_pwd;
    private EditText ed_username;
    private ImageView bloak;
    private SharedPreferences login;
    private SharedPreferences.Editor edit;
    private TextView submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();
        img_bloak();
        islogin();
    }

    private void islogin() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String username = ed_username.getText().toString();
                    String password = ed_pwd.getText().toString();
                    JSONObject jsonObject = new JSONObject();
                    if(!"".equals(username.trim()) && !"".equals(password.trim())
                            && !TextUtils.isEmpty(username) &&  !TextUtils.isEmpty(password)){
                        jsonObject.put("username",username);
                        jsonObject.put("password",password);
                        sendtoken(jsonObject.toString(),username,password);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



    }

    private void sendtoken(String JSON,String usen,String pwd) {
        OkHttpRequest.doNetRequst("prod-api/api/login",
                OkHttpRequest.POST,
                GeneralBean.class,
                new OkHttpRequest.NetRequst() {
                    @Override
                    public void ok(Object obj) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                GeneralBean generalBean =(GeneralBean)obj;
                                if(generalBean.getCode()==200){
                                    OkHttpRequest.TOKEN = generalBean.getToken();
                                    edit.putString("TOKEN",generalBean.getToken());
                                    edit.putString("USEN",usen);
                                    edit.putString("PWD",pwd);
                                    edit.apply();
                                    Toast.makeText(context,"登录成功",Toast.LENGTH_SHORT).show();
                                    context.finish();
                                }else {
                                    Toast.makeText(context,"登录失败",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void no(String msg) {

                    }
                },JSON);
    }

    private void img_bloak() {
        bloak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });
    }

    private void initview() {
        bloak = findViewById(R.id.bloak);
        ed_username = findViewById(R.id.ed_username);
        ed_pwd = findViewById(R.id.ed_pwd);
        enroll = findViewById(R.id.enroll);
        submit = findViewById(R.id.submit);
        context = LoginActivity.this;


        ed_username.setText(User.USER);
        ed_pwd.setText(User.PWD);

        login = getSharedPreferences("login", Context.MODE_PRIVATE);
        edit = login.edit();
        String pwd = login.getString("pwd", "");
        if(!"".equals(pwd.trim())){
            ed_pwd.setText(pwd);
        }
    }
}