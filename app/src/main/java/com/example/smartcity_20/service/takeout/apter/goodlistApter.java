package com.example.smartcity_20.service.takeout.apter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
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

import java.util.List;

public class goodlistApter extends RecyclerView.Adapter<goodlistApter.Myhot> {

    private Context context;
    private String TAG = "TAG";
    private List< GoodsnumberBean.RowsDTO.OrderInfoDTO.OrderItemListDTO > list;

    public goodlistApter(Context context, List<GoodsnumberBean.RowsDTO.OrderInfoDTO.OrderItemListDTO>  list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public goodlistApter.Myhot onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.l_item_goodtypelist, null);
        return new goodlistApter.Myhot(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull goodlistApter.Myhot holder, int position) {
        try {
            GoodsnumberBean.RowsDTO.OrderInfoDTO.OrderItemListDTO orderItemListDTO = list.get(position);
            if ( orderItemListDTO== null) {
                return;
            }

            if (!TextUtils.isEmpty(orderItemListDTO.getProductImage())) {
                Glide.with(context).load(IpandPort.URL + orderItemListDTO.getProductImage()).into(holder.imgUrl);
            }

            if (!TextUtils.isEmpty(orderItemListDTO.getProductName())) {
                holder.name.setText(orderItemListDTO.getProductName());
            }

            holder.re.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, OrderdetailsActivity.class);
                    intent.putExtra(Common.OrderdetailsActivityid,orderItemListDTO.getOrderNo());
                    context.startActivity(intent);
                }
            });
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
        private final RelativeLayout re;


        public Myhot(@NonNull View itemView) {
            super(itemView);
            imgUrl = itemView.findViewById(R.id.imgUrl);
            name = itemView.findViewById(R.id.name);
            re = itemView.findViewById(R.id.re);
        }
    }

}


