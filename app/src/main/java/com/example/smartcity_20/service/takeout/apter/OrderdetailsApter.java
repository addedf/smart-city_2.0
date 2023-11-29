package com.example.smartcity_20.service.takeout.apter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.Common;
import com.example.smartcity_20.config.java.IpandPort;
import com.example.smartcity_20.service.takeout.OrderdetailsActivity;
import com.example.smartcity_20.service.takeout.bean.GoodsnumberBean;
import com.example.smartcity_20.service.takeout.bean.OrderdetailsBean;

import java.util.List;

public class OrderdetailsApter extends RecyclerView.Adapter<OrderdetailsApter.Myhot> {

    private Context context;
    private String TAG = "TAG";
    private List<OrderdetailsBean.DataDTO.OrderInfoDTO.OrderItemListDTO> list;

    public OrderdetailsApter(Context context, List<OrderdetailsBean.DataDTO.OrderInfoDTO.OrderItemListDTO>  list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public OrderdetailsApter.Myhot onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.l_item_orderdetails, null);
        return new OrderdetailsApter.Myhot(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderdetailsApter.Myhot holder, int position) {
        try {
            OrderdetailsBean.DataDTO.OrderInfoDTO.OrderItemListDTO orderItemListDTO = list.get(position);
            if ( orderItemListDTO== null) {
                return;
            }

            if (!TextUtils.isEmpty(orderItemListDTO.getProductImage())) {
                Glide.with(context).load(IpandPort.URL + orderItemListDTO.getProductImage()).into(holder.imgUrl);
            }

            if (!TextUtils.isEmpty(orderItemListDTO.getProductName())) {
                holder.name.setText(orderItemListDTO.getProductName());
            }

            holder.nums.setText("X "+String.valueOf(orderItemListDTO.getQuantity()));
            holder.price.setText(String.valueOf(orderItemListDTO.getTotalPrice()));

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
        private final TextView price;
        private final TextView nums;



        public Myhot(@NonNull View itemView) {
            super(itemView);
            imgUrl = itemView.findViewById(R.id.imgUrl);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            nums = itemView.findViewById(R.id.nums);
        }
    }

}



