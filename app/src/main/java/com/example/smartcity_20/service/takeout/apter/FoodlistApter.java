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
import com.example.smartcity_20.service.takeout.FoodhotActivity;
import com.example.smartcity_20.service.takeout.bean.FoodlistBean;

import java.util.List;

public class FoodlistApter extends RecyclerView.Adapter<FoodlistApter.Myhot>{

    private Context context;
    private List<FoodlistBean.RowsBean> list;

    public FoodlistApter(Context context, List<FoodlistBean.RowsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FoodlistApter.Myhot onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.l_item_foodlist, null);
        return new FoodlistApter.Myhot(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodlistApter.Myhot holder, int position) {
        try {
            if(list.get(position) ==null){
                return;
            }
            if(!TextUtils.isEmpty(list.get(position).getImgUrl())){
                Glide.with(context).load(IpandPort.URL+list.get(position).getImgUrl()).into(holder.imgUrl);
            }
            if(!TextUtils.isEmpty(list.get(position).getName())){
                holder.name.setText(list.get(position).getName());
            }

            if(list.get(position).getScore() !=  null){
                holder.score.setText(String.valueOf(list.get(position).getScore()+"分"));
            }

            if(list.get(position).getSaleQuantity() !=null ){
                holder.saleQuantity.setText("月销量 "+String.valueOf(list.get(position).getSaleQuantity()));
            }

            if(list.get(position).getDistance() !=  null){
                holder.distance.setText("到店距离: "+String.valueOf(list.get(position).getDistance()));
            }

            if(list.get(position).getAvgCost() !=  null){
                holder.avgCost.setText("人均消费: "+String.valueOf(list.get(position).getAvgCost()));
            }

            holder.re.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FoodhotActivity.class);
                    if(list.get(position).getId() !=null){
                        intent.putExtra(Common.FoodhotActivity,String.valueOf(list.get(position).getId()));
                        context.startActivity(intent);
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

        private final ImageView imgUrl;
        private final TextView name;
        private final TextView score;
        private final TextView saleQuantity;
        private final TextView distance;
        private final TextView avgCost;
        private final RelativeLayout re;

        public Myhot(@NonNull View itemView) {
            super(itemView);
            imgUrl = itemView.findViewById(R.id.imgUrl);
            name = itemView.findViewById(R.id.name);
            score = itemView.findViewById(R.id.score);
            saleQuantity = itemView.findViewById(R.id.saleQuantity);
            distance = itemView.findViewById(R.id.distance);
            avgCost = itemView.findViewById(R.id.avgCost);
            re = itemView.findViewById(R.id.re);
        }
    }

}

