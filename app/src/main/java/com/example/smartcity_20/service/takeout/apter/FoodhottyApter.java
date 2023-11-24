package com.example.smartcity_20.service.takeout.apter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.IpandPort;
import com.example.smartcity_20.home.apter.HelplistApter;
import com.example.smartcity_20.home.bean.HelplistBean;
import com.example.smartcity_20.service.takeout.bean.FoodhottyBean;

import java.util.List;

public class FoodhottyApter extends RecyclerView.Adapter<FoodhottyApter.Myhot>{

    private Context context;
    private List<FoodhottyBean.RowsDTO> list;

    public FoodhottyApter(Context context, List<FoodhottyBean.RowsDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FoodhottyApter.Myhot onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.l_item_foodhotty, null);
        return new FoodhottyApter.Myhot(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodhottyApter.Myhot holder, int position) {
        try {
            if(list.get(position) ==null){
                return;
            }
            if(!TextUtils.isEmpty(list.get(position).getImgUrl())){
                Glide.with(context).load(IpandPort.URL+list.get(position).getImgUrl()).into(holder.imgUrl);
            }
            if(list.get(position).getSaleNum3hour()!=null){
                holder.saleNum3hour.setText("近三小时下单:"+String.valueOf(list.get(position).getSaleNum3hour()));
            }

            if(list.get(position).getName()!=null){
                holder.name.setText(list.get(position).getName());
            }


            if(list.get(position).getScore() !=null){
                holder.score.setText(String.valueOf(list.get(position).getScore())+"分");
            }
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
        private final TextView saleNum3hour;
        private final TextView score;

        public Myhot(@NonNull View itemView) {
            super(itemView);
            imgUrl = itemView.findViewById(R.id.imgUrl);
            name = itemView.findViewById(R.id.name);
            saleNum3hour = itemView.findViewById(R.id.saleNum3hour);
            score = itemView.findViewById(R.id.score);
        }
    }

}
