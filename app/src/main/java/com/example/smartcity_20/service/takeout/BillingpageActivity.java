package com.example.smartcity_20.service.takeout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.Common;
import com.example.smartcity_20.config.java.OkHttpRequest;
import com.example.smartcity_20.config.kotlin.Tool;
import com.example.smartcity_20.me.bean.StatusBean;
import com.example.smartcity_20.service.takeout.apter.AddeliveryaddressApter;
import com.example.smartcity_20.service.takeout.apter.CarteApter;
import com.example.smartcity_20.service.takeout.bean.FoodBean;
import com.example.smartcity_20.service.takeout.bean.RevealaddBean;
import com.example.smartcity_20.service.takeout.bean.TakeoutorderBean;
import com.example.smartcity_20.service.takeout.bean.TakeoutordernumberBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class BillingpageActivity extends AppCompatActivity {

    private BillingpageActivity context;
    private String TAG = "TAG";
    private TextView deliveryaddress;
    private TextView moneynum;
    private RecyclerView foodlist;
    private LinearLayout account;
    private List<FoodBean.DataDTO> dataDTO;
    private Tool tool;
    private final int REQUEST_CODE_DELIVERY  = 1 ;
    private AlertDialog alertDialog;
    private TextView addressDetail;
    private TextView name;
    private TextView phone;
    private RelativeLayout re;
    private TextView shopname;
    private FrameLayout fr;
    private RevealaddBean.DataDTO revealaddBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billingpage);
        context = this;
        initview();
        dataDTOmethod();
    }

    private void dataDTOmethod() {
        foodlist.setLayoutManager(new LinearLayoutManager(context));
        foodlist.setAdapter(new CarteApter(context,dataDTO));
    }

    private void initview() {
        Gson gson = new Gson();
        Intent intent = getIntent();
        String money = intent.getStringExtra(Common.money);
        String numlist = intent.getStringExtra(Common.numlist);
        int  Foodorderid = Integer.parseInt(intent.getStringExtra(Common.Foodorderid));
        String str_shopname = intent.getStringExtra(Common.shopname);
        dataDTO = gson.fromJson(numlist, new TypeToken<List<FoodBean.DataDTO>>() {}.getType());
        shopname = findViewById(R.id.shopname);
        ImageView ic_back = findViewById(R.id.ic_back);
        deliveryaddress = findViewById(R.id.deliveryaddress);
        moneynum = findViewById(R.id.moneynum);
        foodlist = findViewById(R.id.foodlist);
        account = findViewById(R.id.account);
        tool = new Tool(context);

        re = findViewById(R.id.re);
        addressDetail = findViewById(R.id.addressDetail);
        fr = findViewById(R.id.fr);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);

        moneynum.setText(money);
        shopname.setText(str_shopname);


        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });


        fr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View inflate = LayoutInflater.from(context).inflate(R.layout.l_dialog_deliveryaddress, null);
                TextView add_deliveryaddress = inflate.findViewById(R.id.add_deliveryaddress);
                RecyclerView deliveryaddresslist = inflate.findViewById(R.id.deliveryaddresslist);


                //显示收货地址
                revealaddeliveryaddress(deliveryaddresslist);


                add_deliveryaddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context,AddeliveryaddressActivity.class);
                        context.startActivityForResult(intent,REQUEST_CODE_DELIVERY);
                    }
                });
                builder.setView(inflate);
                alertDialog = builder.create();
                alertDialog.show();
            }
        });



        //提交订单
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(dataDTO!=null && !"0.0".equals(money.trim()) &&  revealaddBean!=null){

                        TakeoutorderBean takeoutorderBean = new TakeoutorderBean();
                        takeoutorderBean.setAddressDetail(revealaddBean.getAddressDetail());
                        takeoutorderBean.setLabel(revealaddBean.getLabel());
                        takeoutorderBean.setName(revealaddBean.getName());
                        takeoutorderBean.setPhone(revealaddBean.getPhone());
                        takeoutorderBean.setPhone(revealaddBean.getPhone());
                        takeoutorderBean.setAmount(money);

                        takeoutorderBean.setSellerId(dataDTO.get(Foodorderid).getId());

                        ArrayList<TakeoutorderBean.OrderItemListBean> arrayList = new ArrayList();
                        for (FoodBean.DataDTO dto : dataDTO) {
                            TakeoutorderBean.OrderItemListBean orderItemListBean = new TakeoutorderBean.OrderItemListBean();
                            //菜品
                            orderItemListBean.setProductId(dto.getId());
                            //购买数量
                            orderItemListBean.setQuantity(dto.getNums());
                            arrayList.add(orderItemListBean);
                        }
                        takeoutorderBean.setOrderItemList(arrayList);
                        Log.e(TAG,"takeoutorderBean"+takeoutorderBean.toString());
                        /*JSONObject jsonObject = new JSONObject();
                        jsonObject.put("addressDetail",revealaddBean.getAddressDetail());
                        jsonObject.put("label",revealaddBean.getLabel());
                        jsonObject.put("name",revealaddBean.getName());
                        jsonObject.put("phone",revealaddBean.getPhone());
                        jsonObject.put("amount",money);*/
                        createorder(gson.toJson(takeoutorderBean));
                        //Intent intent = new Intent(context,PaymentActivity.class);
                       //intent.putExtra(Common.money,money);
                        //context.startActivity(intent);
                    }else {
                        Toast.makeText(context, "提交失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void createorder(String JSON) {
        tool.send("prod-api/api/takeout/order/create",
                "POST",
                JSON,
                true, TakeoutordernumberBean.class,
                new Function1<TakeoutordernumberBean, Unit>() {
                    @Override
                    public Unit invoke(TakeoutordernumberBean takeoutordernumberBean) {
                        if(takeoutordernumberBean.getCode()==200){

                        }else {
                            Toast.makeText(context,takeoutordernumberBean.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                        return null;
                    }
                });
    }


    //刷新选择的收货地址
    public void displayaddress(RevealaddBean.DataDTO dataDTO){
        try {
            revealaddBean = dataDTO;
            if(revealaddBean==null){
                return;
            }

            re.setVisibility(View.VISIBLE);
            deliveryaddress.setVisibility(View.GONE);

            if(revealaddBean.getName()!=null){
                name.setText(revealaddBean.getName());
            }

            if(revealaddBean.getPhone()!=null){
                phone.setText(revealaddBean.getPhone());
            }

            if(revealaddBean.getAddressDetail() !=null){
                addressDetail.setText(String.valueOf(revealaddBean.getAddressDetail()));
            }
            alertDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void revealaddeliveryaddress(RecyclerView deliveryaddresslist) {

        new Tool(context).send("/prod-api/api/takeout/address/list",
                "GET",
                null,
                true,
                RevealaddBean.class,
                new Function1<RevealaddBean, Unit>() {
                    @Override
                    public Unit invoke(RevealaddBean reveal) {
                        if(reveal.getCode()==200){
                            deliveryaddresslist.setLayoutManager(new LinearLayoutManager(context));
                            deliveryaddresslist.setAdapter(new AddeliveryaddressApter(context,reveal.getData()));
                        }
                        return null;
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //点击收货地址的返回和保存地址成功都执行这个判断,结束掉弹窗的生命周期
        if(requestCode==REQUEST_CODE_DELIVERY && resultCode==RESULT_OK){
            alertDialog.dismiss();
        }
    }
}