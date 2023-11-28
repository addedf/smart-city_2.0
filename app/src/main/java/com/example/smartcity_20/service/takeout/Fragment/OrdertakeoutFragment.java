package com.example.smartcity_20.service.takeout.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity_20.R;
import com.example.smartcity_20.config.kotlin.Tool;
import com.example.smartcity_20.me.bean.StatusBean;
import com.example.smartcity_20.service.takeout.apter.OrderAllApter;
import com.example.smartcity_20.service.takeout.apter.goodlistApter;
import com.example.smartcity_20.service.takeout.bean.GoodsnumberBean;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;


public class OrdertakeoutFragment extends Fragment {


    private View view;
    private FragmentActivity activity;
    private Context context;
    private TabLayout mytab;
    private RecyclerView orderAll;
    private Tool tool;
    private String TAG = "TAG";
    private JSONObject jsonObject;
    private GoodsnumberBean goodsnumber;

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

    @Override
    public void onResume() {
        super.onResume();
        getindexoodertype();
    }

    public void getindexoodertype(){
        //获取tab的类型刷新订单的状态
        int selectedTabPosition = mytab.getSelectedTabPosition();
        String order = mytab.getTabAt(selectedTabPosition).getText().toString();
        orderlist(order);
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
        String url = "";
        if ("全部".equals(type)) {
            url = "/prod-api/api/takeout/order/list";
        } else {
            url = "/prod-api/api/takeout/order/list?status=" + type;
        }
        tool.send(url,
                "GET",
                null,
                true,
                GoodsnumberBean.class,
                new Function1<GoodsnumberBean, Unit>() {
                    @Override
                    public Unit invoke(GoodsnumberBean goodsnumberBean) {
                        if (goodsnumberBean.getCode() == 200) {
                            goodsnumber = goodsnumberBean;
                            orderAll.setLayoutManager(new LinearLayoutManager(context));
                            OrderAllApter orderAllApter = new OrderAllApter(context, goodsnumberBean.getRows(), new OrderAllApter.Goodsport() {

                                //显示点菜的列表
                                @Override
                                public void goodlist(RecyclerView foodlist, GoodsnumberBean.RowsDTO.OrderInfoDTO orderInfo) {
                                    foodlist.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
                                    foodlist.setAdapter(new goodlistApter(context, orderInfo.getOrderItemList()));
                                }

                                //出现评价的弹窗
                                @Override
                                public void rateClick(String order) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    View inflate = LayoutInflater.from(context).inflate(R.layout.l_dialog_rate, null);
                                    EditText ed_rete = inflate.findViewById(R.id.ed_rete);
                                    RatingBar bar = inflate.findViewById(R.id.bar);
                                    builder.setView(inflate);
                                    builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });

                                    builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            try {
                                                String str_rete = ed_rete.getText().toString();
                                                int numStars = Math.round(bar.getRating());
                                                Log.e(TAG, "numStars" + numStars);
                                                if (!TextUtils.isEmpty(str_rete.trim()) && !TextUtils.isEmpty(order) && numStars != 0) {
                                                    jsonObject.put("content", str_rete);
                                                    jsonObject.put("orderNo", order);
                                                    jsonObject.put("score", numStars);
                                                    sendretepost(jsonObject.toString(), order);
                                                } else {
                                                    Toast.makeText(activity, "内容不能为空", Toast.LENGTH_SHORT).show();
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });

                                    builder.create().show();
                                }

                                //出现退款弹窗
                                @Override
                                public void refundClick(String order) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    View inflate = LayoutInflater.from(context).inflate(R.layout.l_dialog_refund, null);
                                    EditText ed_refund = inflate.findViewById(R.id.ed_refund);
                                    builder.setView(inflate);
                                    builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });

                                    builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            try {
                                                String str_refund = ed_refund.getText().toString();

                                                if (!TextUtils.isEmpty(str_refund.trim()) && !TextUtils.isEmpty(order)) {
                                                    jsonObject.put("reason", str_refund);
                                                    jsonObject.put("orderNo", order);
                                                    sendrefundpost(jsonObject.toString(), order);
                                                } else {
                                                    Toast.makeText(activity, "内容不能为空", Toast.LENGTH_SHORT).show();
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });

                                    builder.create().show();
                                }
                            });
                            orderAll.setAdapter(orderAllApter);
                        } else {
                            Toast.makeText(context, goodsnumberBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        return null;
                    }
                });
    }

    private void sendrefundpost(String json, String order) {
        tool.send("/prod-api/api/takeout/order/refound",
                "POST",
                json,
                true,
                StatusBean.class,
                new Function1<StatusBean, Unit>() {
                    @Override
                    public Unit invoke(StatusBean statusBean) {
                        if (statusBean.getCode() == 200) {
                            //将回调的订单号和全部的订单号比较,并修改状态
                            for (GoodsnumberBean.RowsDTO row : goodsnumber.getRows()) {
                                String row_order = row.getOrderInfo().getOrderNo();
                                if (order.equals(row_order)) {
                                    row.getOrderInfo().setStatus("已退款");
                                }
                            }
                            Toast.makeText(activity, "退款成功", Toast.LENGTH_SHORT).show();
                            //刷新订单的状态
                            getindexoodertype();
                        } else {
                            Toast.makeText(activity, statusBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        return null;
                    }
                });
    }

    private void sendretepost(String json, String order) {
        tool.send("/prod-api/api/takeout/comment",
                "POST",
                json,
                true,
                StatusBean.class,
                new Function1<StatusBean, Unit>() {
                    @Override
                    public Unit invoke(StatusBean statusBean) {
                        if (statusBean.getCode() == 200) {
                            //将回调的订单号和全部的订单号比较,并修改状态
                            for (GoodsnumberBean.RowsDTO row : goodsnumber.getRows()) {
                                String row_order = row.getOrderInfo().getOrderNo();
                                if (order.equals(row_order)) {
                                    row.getOrderInfo().setStatus("已完成");
                                }
                            }
                            Toast.makeText(activity, "发表成功", Toast.LENGTH_SHORT).show();
                            //刷新订单的状态
                            getindexoodertype();
                        } else {
                            Toast.makeText(activity, statusBean.getMsg(), Toast.LENGTH_SHORT).show();
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
        jsonObject = new JSONObject();

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });

    }
}