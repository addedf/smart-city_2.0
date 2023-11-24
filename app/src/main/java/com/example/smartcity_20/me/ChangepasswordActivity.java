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
import com.example.smartcity_20.config.kotlin.Tool;
import com.example.smartcity_20.me.bean.GeneralBean;
import com.example.smartcity_20.me.bean.StatusBean;

import org.json.JSONException;
import org.json.JSONObject;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ChangepasswordActivity extends AppCompatActivity {

    private ImageView ic_back;
    private EditText ed_oldpwd;
    private EditText ed_newpwd;
    private TextView submit;
    private ChangepasswordActivity context;
    private SharedPreferences login;
    private SharedPreferences.Editor edit;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword_activity);
        initview();
        img_bloak();
        submitpwd();
    }

    private void submitpwd() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ed_old = ed_oldpwd.getText().toString();
                String ed_new = ed_newpwd.getText().toString();
                //判空,并且输入的密码要和账号的密码保存一致
                if(!"".equals(ed_old.trim()) && !TextUtils.isEmpty(ed_old)
                    && !"".equals(ed_new.trim()) && !TextUtils.isEmpty(ed_new)
                    && !"".equals(pwd.trim()) && pwd.equals(ed_old)){

                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("newPassword",ed_new);
                        jsonObject.put("oldPassword",ed_old);
                        savepwd(jsonObject.toString(),ed_new);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(context, "修改失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void savepwd(String JSON,String newPWD) {
        /*OkHttpRequest.doNetRequst("prod-api/api/common/user/resetPwd",
                OkHttpRequest.PUT,
                StatusBean.class,
                new OkHttpRequest.NetRequst() {
                    @Override
                    public void ok(Object obj) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                StatusBean statusBean =(StatusBean)obj;
                                if(statusBean.getCode()==200){
                                    edit.putString("PWD",newPWD);
                                    edit.apply();
                                    Toast.makeText(context,"修改成功",Toast.LENGTH_SHORT).show();
                                    context.finish();
                                }else {
                                    Toast.makeText(context,statusBean.getMsg(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void no(String msg) {

                    }
                },JSON);*/
        Tool tool = new Tool(ChangepasswordActivity.this);
        tool.send("/prod-api/api/common/user/resetPwd",
                "PUT", RequestBody.create(JSON, MediaType.parse("application/json; charset=utf-8")), true, StatusBean.class,
                new Function1<StatusBean, Unit>() {
                    @Override
                    public Unit invoke(StatusBean statusBean) {
                        if(statusBean.getCode()==200){
                            edit.putString("PWD",newPWD);
                            edit.apply();
                            Toast.makeText(context,"修改成功",Toast.LENGTH_SHORT).show();
                            context.finish();
                        }else {
                            Toast.makeText(context,statusBean.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                        return null;
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
        ed_oldpwd = findViewById(R.id.ed_oldpwd);
        ed_newpwd = findViewById(R.id.ed_newpwd);
        submit = findViewById(R.id.submit);

        context = this;
        login = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        edit = login.edit();
        pwd = login.getString("PWD", "");
    }
}