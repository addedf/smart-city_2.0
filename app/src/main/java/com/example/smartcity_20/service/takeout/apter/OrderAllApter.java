package com.example.smartcity_20.service.takeout.apter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.Common;
import com.example.smartcity_20.config.java.IpandPort;
import com.example.smartcity_20.service.takeout.FoodorderActivity;
import com.example.smartcity_20.service.takeout.PaymentActivity;
import com.example.smartcity_20.service.takeout.bean.GoodsnumberBean;
import com.google.gson.Gson;

import java.util.List;

public class OrderAllApter extends RecyclerView.Adapter<OrderAllApter.Myhot> {

    private Context context;
    private List<GoodsnumberBean.RowsDTO> list;
    private Goodsport good;
    private Gson gson = new Gson();

    public OrderAllApter(Context context, List<GoodsnumberBean.RowsDTO> list) {
        this.context = context;
        this.list = list;
    }

    public OrderAllApter(Context context, List<GoodsnumberBean.RowsDTO> list, Goodsport good) {
        this.context = context;
        this.list = list;
        this.good = good;
    }

    public void setData(List<GoodsnumberBean.RowsDTO> rows) {
        this.list =  rows;
    }

    public interface Goodsport {
        //显示菜品的列表
        public void goodlist(RecyclerView foodlist, GoodsnumberBean.RowsDTO.OrderInfoDTO orderInfo);
        //点击评价
        public void rateClick (String order);
        //点击退款
        public void refundClick (String order);
    }

    @NonNull
    @Override
    public OrderAllApter.Myhot onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.l_item_goodslist, null);
        return new OrderAllApter.Myhot(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAllApter.Myhot holder, int position) {
        try {
            GoodsnumberBean.RowsDTO.SellerInfoDTO sellerInfo = list.get(position).getSellerInfo();
            GoodsnumberBean.RowsDTO.OrderInfoDTO orderInfo = list.get(position).getOrderInfo();
            if (sellerInfo == null || orderInfo == null) {
                return;
            }

            List<GoodsnumberBean.RowsDTO.OrderInfoDTO.OrderItemListDTO> orderItemList = orderInfo.getOrderItemList();

            if (orderItemList == null) {
                return;
            }
            holder.quantity.setText("共 " + String.valueOf(orderItemList.size()) + " 件");


            if (!TextUtils.isEmpty(sellerInfo.getImgUrl())) {
                Glide.with(context).load(IpandPort.URL + sellerInfo.getImgUrl()).into(holder.imgUrl);
            }
            if (!TextUtils.isEmpty(sellerInfo.getName())) {
                holder.name.setText(sellerInfo.getName());
            }

            if (!TextUtils.isEmpty(orderInfo.getStatus())) {
                holder.status.setText(orderInfo.getStatus());
                String status = orderInfo.getStatus();
                if ("待评价".equals(status)) {
                    holder.status_evaluated.setVisibility(View.VISIBLE);
                    holder.status_payment.setVisibility(View.GONE);
                    holder.status_Onemore.setVisibility(View.GONE);

                    String orderNo = orderInfo.getOrderNo();

                    //点击出现评价弹窗
                    holder.evaluate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            good.rateClick(orderNo);
                        }
                    });

                    //点击出现退款的弹窗
                    holder.refund.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            good.refundClick(orderNo);
                        }
                    });


                } else if ("完成".equals(status)) {
                    holder.status_evaluated.setVisibility(View.GONE);
                    holder.status_payment.setVisibility(View.GONE);
                    holder.status_Onemore.setVisibility(View.VISIBLE);

                    holder.onemore.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, FoodorderActivity.class);

                            String JSON = gson.toJson(sellerInfo);
                            intent.putExtra(Common.FoodorderActivity, JSON);
                            context.startActivity(intent);
                        }
                    });
                } else if ("退款".equals(status)) {
                    holder.status_evaluated.setVisibility(View.GONE);
                    holder.status_payment.setVisibility(View.GONE);
                    holder.status_Onemore.setVisibility(View.GONE);


                } else if ("待支付".equals(status)) {
                    holder.status_evaluated.setVisibility(View.GONE);
                    holder.status_payment.setVisibility(View.VISIBLE);
                    holder.status_Onemore.setVisibility(View.GONE);

                    holder.payment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, PaymentActivity.class);
                            intent.putExtra(Common.ordernumber, orderInfo.getOrderNo());
                            intent.putExtra(Common.moneytotality, String.valueOf(orderInfo.getAmount()));
                            context.startActivity(intent);

                            //需要刷新支付状态
                        }
                    });
                }
            }

            if (orderInfo.getAmount() != null) {
                holder.amount.setText(String.valueOf(orderInfo.getAmount()));
            }

            //显示菜品的列表
            good.goodlist(holder.foodlist, orderInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class Myhot extends RecyclerView.ViewHolder {

        private final ImageView imgUrl;
        private final TextView name;
        private final TextView status;
        private final TextView amount;
        private final TextView quantity;
        private final TextView payment;
        private final TextView refund;
        private final TextView evaluate;
        private final TextView onemore;
        private final RelativeLayout re;
        private final LinearLayout status_evaluated;
        private final LinearLayout status_payment;
        private final LinearLayout status_Onemore;
        private final RecyclerView foodlist;

        public Myhot(@NonNull View itemView) {
            super(itemView);
            imgUrl = itemView.findViewById(R.id.imgUrl);
            name = itemView.findViewById(R.id.name);
            status = itemView.findViewById(R.id.status);
            amount = itemView.findViewById(R.id.amount);
            quantity = itemView.findViewById(R.id.quantity);
            foodlist = itemView.findViewById(R.id.foodlist);
            re = itemView.findViewById(R.id.re);
            status_evaluated = itemView.findViewById(R.id.status_evaluated);
            status_payment = itemView.findViewById(R.id.status_payment);
            onemore = itemView.findViewById(R.id.onemore);
            status_Onemore = itemView.findViewById(R.id.status_Onemore);
            payment = itemView.findViewById(R.id.payment);
            refund = itemView.findViewById(R.id.refund);
            evaluate = itemView.findViewById(R.id.evaluate);
        }
    }

}

