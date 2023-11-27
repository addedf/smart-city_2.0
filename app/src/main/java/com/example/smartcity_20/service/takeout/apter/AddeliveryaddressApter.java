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
import com.example.smartcity_20.service.takeout.BillingpageActivity;
import com.example.smartcity_20.service.takeout.FoodhotActivity;
import com.example.smartcity_20.service.takeout.bean.FoodBean;
import com.example.smartcity_20.service.takeout.bean.RevealaddBean;

import java.util.List;

public class AddeliveryaddressApter extends RecyclerView.Adapter<AddeliveryaddressApter.Myhot>{

    private Context context;
    private List<RevealaddBean.DataDTO> list;

    public AddeliveryaddressApter(Context context, List<RevealaddBean.DataDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AddeliveryaddressApter.Myhot onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.l_item_addeliveryaddress, null);
        return new AddeliveryaddressApter.Myhot(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull AddeliveryaddressApter.Myhot holder, int position) {
        try {
            if(list.get(position) ==null){
                return;
            }

            if(list.get(position).getName()!=null){
                holder.name.setText(list.get(position).getName());
            }

            if(list.get(position).getPhone()!=null){
                holder.phone.setText(String.valueOf(list.get(position).getPhone()));
            }

            if(list.get(position).getAddressDetail() !=null){
                holder.addressDetail.setText(String.valueOf("x "+list.get(position).getAddressDetail()));
            }

            holder.re.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(context instanceof BillingpageActivity){
                        BillingpageActivity billingpage = (BillingpageActivity)context;
                        billingpage.displayaddress(list.get(position));
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class Myhot extends RecyclerView.ViewHolder{


        private final TextView addressDetail;
        private final TextView name;
        private final TextView phone;
        private final RelativeLayout re;


        public Myhot(@NonNull View itemView) {
            super(itemView);
            phone = itemView.findViewById(R.id.phone);
            name = itemView.findViewById(R.id.name);
            addressDetail = itemView.findViewById(R.id.addressDetail);
            re = itemView.findViewById(R.id.re);

        }
    }

}

