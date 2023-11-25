package com.example.smartcity_20.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.Common;
import com.example.smartcity_20.config.java.OkHttpRequest;
import com.example.smartcity_20.home.apter.VpmapApter;
import com.example.smartcity_20.home.bean.VpmapBean;
import com.example.smartcity_20.me.ChangepasswordActivity;
import com.example.smartcity_20.me.FeedbackActivity;
import com.example.smartcity_20.me.LoginActivity;
import com.example.smartcity_20.me.OrderActivity;
import com.example.smartcity_20.me.PersonalinformationActivity;
import com.example.smartcity_20.me.bean.QuerypersonalBean;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MeFragment extends Fragment {


    private View view;
    private TextView login;
    private TextView set_where;
    private TextView setpwd;
    private TextView orderlist;
    private TextView penosn;
    private TextView username;
    private Context context;
    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor edit;
    private Gson gson;
    private QuerypersonalBean queryperson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_me, container, false);
        initview();
        querypersonal();
        return view;
    }

    private void intentorder() {

    }

    private void feedback() {

    }

    private void editpwd() {

    }

    private void Personalinformation() {

    }

    @Override
    public void onResume() {
        super.onResume();
        ispersonToken();
    }

    private void ispersonToken() {
        if (OkHttpRequest.TOKEN != null) {
            querypersonal();
        } else {
            login.setText("登录");
        }
    }

    private void querypersonal() {
        OkHttpRequest.doNetRequst("prod-api/api/common/user/getInfo",
                OkHttpRequest.GET,
                QuerypersonalBean.class,
                new OkHttpRequest.NetRequst() {
                    @Override
                    public void ok(Object obj) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                queryperson = (QuerypersonalBean) obj;
                                if (queryperson.getCode() == 200) {
                                    username.setText(queryperson.getUser().getNickName());
                                    login.setText("退出登录");
                                } else {
                                    username.setText("未登录");
                                    login.setText("登录");
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
        username = view.findViewById(R.id.username);
        penosn = view.findViewById(R.id.penosn);
        orderlist = view.findViewById(R.id.orderlist);
        setpwd = view.findViewById(R.id.setpwd);
        set_where = view.findViewById(R.id.set_where);
        login = view.findViewById(R.id.login);
        gson = new Gson();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginstatus = login.getText().toString();
                if ("登录".equals(loginstatus)) {
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                } else if ("退出登录".equals(loginstatus)) {
                    OkHttpRequest.TOKEN = "";
                    edit.putString("TOKEN", "");
                    edit.apply();
                    login.setText("登录");
                    querypersonal();
                }

            }
        });

        penosn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PersonalinformationActivity.class);
                if (!"".equals(OkHttpRequest.TOKEN) && !TextUtils.isEmpty(OkHttpRequest.TOKEN)) {
                    String JSON = gson.toJson(queryperson);
                    intent.putExtra(Common.QuerypersonalBean, JSON);
                }
                context.startActivity(intent);
            }
        });

        setpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChangepasswordActivity.class);
                context.startActivity(intent);
            }
        });

        set_where.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FeedbackActivity.class);
                context.startActivity(intent);
            }
        });

        orderlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderActivity.class);
                context.startActivity(intent);
            }
        });

        context = this.getContext();
        sharedpreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        edit = sharedpreferences.edit();
    }
}