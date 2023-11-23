package com.example.smartcity_20.me;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.OkHttpRequest;
import com.example.smartcity_20.me.bean.StatusBean;

import org.json.JSONException;
import org.json.JSONObject;

public class FeedbackActivity extends AppCompatActivity {

    private ImageView ic_back;
    private FeedbackActivity context;
    private TextView submit;
    private EditText ed_feed;
    private EditText ed_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        context = this;
        initview();
        img_bloak();
        submitfeed();
    }

    private void submitfeed() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = ed_title.getText().toString();
                String feed = ed_feed.getText().toString();

                if(!"".equals(title.trim()) && !TextUtils.isEmpty(title)
                        && !"".equals(feed.trim()) && !TextUtils.isEmpty(feed)){

                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("content",feed);
                        jsonObject.put("title",title);
                        savefeed(jsonObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(context, "提交的内容不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void savefeed(String JSON) {
        OkHttpRequest.doNetRequst("prod-api/api/common/feedback",
                OkHttpRequest.POST,
                StatusBean.class,
                new OkHttpRequest.NetRequst() {
                    @Override
                    public void ok(Object obj) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                StatusBean statusBean =(StatusBean)obj;
                                if(statusBean.getCode()==200){
                                    Toast.makeText(context,"提交成功",Toast.LENGTH_SHORT).show();
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
                },JSON);
    }

    private void initview() {
        ic_back = findViewById(R.id.ic_back);
        ed_title = findViewById(R.id.ed_title);
        ed_feed = findViewById(R.id.ed_feed);
        submit = findViewById(R.id.submit);
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