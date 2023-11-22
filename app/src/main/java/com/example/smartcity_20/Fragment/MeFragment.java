package com.example.smartcity_20.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartcity_20.R;
import com.example.smartcity_20.me.LoginActivity;

public class MeFragment extends Fragment {


    private View view;
    private TextView login;
    private TextView set_where;
    private TextView setpwd;
    private TextView orderlist;
    private TextView penosn;
    private TextView username;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_me, container, false);
        initview();
        islogin();
        return view;
    }

    private void islogin() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
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

        context = this.getContext();
    }
}