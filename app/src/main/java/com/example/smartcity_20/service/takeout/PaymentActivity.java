package com.example.smartcity_20.service.takeout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.Common;
import com.example.smartcity_20.config.kotlin.Tool;
import com.example.smartcity_20.me.bean.StatusBean;

import org.json.JSONException;
import org.json.JSONObject;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class PaymentActivity extends AppCompatActivity {

    private PaymentActivity context;
    private String TAG = "TAG";
    private String paymenttype = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        context = this;
        initview();
    }

    private void initview() {
        ImageView ic_back = findViewById(R.id.ic_back);
        TextView submit = findViewById(R.id.submit);
        TextView money = findViewById(R.id.money);
        RadioGroup ra = findViewById(R.id.ra);

        Intent intent = getIntent();
        String ordernumber = intent.getStringExtra(Common.ordernumber);
        String moneytotality = intent.getStringExtra(Common.moneytotality);
        //setResult(RESULT_CANCELED,intent);
        money.setText(moneytotality);


        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendbroadcast();
            }
        });


        ra.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                if (checkedRadioButtonId == R.id.ra1) {
                    paymenttype = "电子钱包";
                } else if (checkedRadioButtonId == R.id.ra2) {
                    paymenttype = "微信";
                } else if (checkedRadioButtonId == R.id.ra3) {
                    paymenttype = "支付宝";
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!TextUtils.isEmpty(paymenttype.trim()) && !TextUtils.isEmpty(ordernumber) && !TextUtils.isEmpty(moneytotality)) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("orderNo", ordernumber);
                        jsonObject.put("paymentType", paymenttype);
                        sendpost(jsonObject.toString());
                    } else {
                        Toast.makeText(context, "请选择支付类型", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void sendbroadcast() {
        Intent intent = new Intent("TakeOutActivity");
        context.sendBroadcast(intent);
        context.finish();
    }

    private void sendpost(String JSON) {
        new Tool(context).send("/prod-api/api/takeout/pay",
                "POST",
                JSON, true,
                StatusBean.class, new Function1<StatusBean, Unit>() {
                    @Override
                    public Unit invoke(StatusBean statusBean) {
                        if (statusBean.getCode() == 200) {
                            Toast.makeText(context, "支付成功", Toast.LENGTH_SHORT).show();
                            sendbroadcast();
                            context.finish();
                        } else {
                            Toast.makeText(context, statusBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        return null;
                    }
                });

    }

}