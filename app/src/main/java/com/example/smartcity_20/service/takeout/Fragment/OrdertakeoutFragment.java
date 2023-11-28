package com.example.smartcity_20.service.takeout.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.smartcity_20.R;
import com.example.smartcity_20.config.kotlin.Tool;
import com.example.smartcity_20.service.takeout.apter.OrderAllApter;
import com.example.smartcity_20.service.takeout.apter.goodlistApter;
import com.example.smartcity_20.service.takeout.bean.GoodsnumberBean;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;


public class OrdertakeoutFragment extends Fragment {


    private View view;
    private FragmentActivity activity;
    private Context context;
    private TabLayout mytab;
    private RecyclerView orderAll;
    private RecyclerView orderpaid;
    private RecyclerView orderevaluated;
    private RecyclerView orderrefund;
    private Tool tool;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ordertakeout, container, false);
        activity = getActivity();
        context = getContext();
        initview();
        orderlisttype();

        return view;
    }

    private void orderlisttype() {
        ArrayList<String> types = new ArrayList<>();
        types.add("全部");
        types.add("待支付");
        types.add("待评价");
        types.add("退款");

        for (String type : types) {
            TabLayout.Tab tab = mytab.newTab();
            tab.setText(type);
            mytab.addTab(tab);
        }

        orderlist("全部");

        mytab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                orderlist(tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void orderlist(String type) {
        String url  = "";
        if("全部".equals(type)){
            url = "/prod-api/api/takeout/order/list";
        }else{
            url = "/prod-api/api/takeout/order/list?status="+type;
        }
        tool.send(url,
                "GET",
                null,
                true,
                GoodsnumberBean.class,
                new Function1<GoodsnumberBean, Unit>() {
                    @Override
                    public Unit invoke(GoodsnumberBean goodsnumberBean) {
                        if(goodsnumberBean.getCode()==200){
                            orderAll.setLayoutManager(new LinearLayoutManager(context));
                            OrderAllApter orderAllApter = new OrderAllApter(context, goodsnumberBean.getRows(), new OrderAllApter.Goodsport() {

                                //显示点菜的列表
                                @Override
                                public void goodlist(RecyclerView foodlist, GoodsnumberBean.RowsDTO.OrderInfoDTO orderInfo) {
                                    foodlist.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
                                    foodlist.setAdapter(new goodlistApter(context,orderInfo.getOrderItemList()));
                                }

                                //出现评价的弹窗
                                @Override
                                public void rateClick(String order) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    View inflate = LayoutInflater.from(context).inflate(R.layout.l_dialog_rate, null);
                                    builder.setView(inflate);
                                    builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });

                                    builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });

                                    builder.create().show();
                                }

                                //出现退款弹窗
                                @Override
                                public void refundClick(String order) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    View inflate = LayoutInflater.from(context).inflate(R.layout.l_dialog_refund, null);
                                    builder.setView(inflate);
                                    builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });

                                    builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });

                                    builder.create().show();
                                }
                            });
                            orderAll.setAdapter(orderAllApter);
                        }else {
                            Toast.makeText(context,goodsnumberBean.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                        return null;
                    }
                });
    }



    private void initview() {
        ImageView ic_back = view.findViewById(R.id.ic_back);
        mytab = view.findViewById(R.id.mytab);
        orderAll = view.findViewById(R.id.orderAll);
        tool = new Tool(context);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });

    }
}