package com.example.smartcity_20.me;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcity_20.R;

public class LoginActivity extends AppCompatActivity {

    private LoginActivity context;
    private TextView enroll;
    private EditText ed_pwd;
    private EditText ed_username;
    private ImageView bloak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();
        img_bloak();
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
        context = LoginActivity.this;
    }
}