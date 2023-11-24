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
import com.example.smartcity_20.service.takeout.bean.FoodtypeBean;

import java.util.List;

public class FoodtypeApter extends RecyclerView.Adapter<FoodtypeApter.Myhot>{

    private Context context;
    private List<FoodtypeBean.DataDTO> list;

    public FoodtypeApter(Context context, List<FoodtypeBean.DataDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FoodtypeApter.Myhot onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.l_item_help, null);
        return new FoodtypeApter.Myhot(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodtypeApter.Myhot holder, int position) {
        try {
            if(list.get(position) ==null){
                return;
            }
            if(!TextUtils.isEmpty(list.get(position).getImgUrl())){
                Glide.with(context).load(IpandPort.URL+list.get(position).getImgUrl()).into(holder.imgUrl);
            }
            if(!TextUtils.isEmpty(list.get(position).getThemeName())){
                holder.serviceName.setText(list.get(position).getThemeName());
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
        private final TextView serviceName;

        public Myhot(@NonNull View itemView) {
            super(itemView);
            imgUrl = itemView.findViewById(R.id.imgUrl);
            serviceName = itemView.findViewById(R.id.serviceName);
        }
    }

}

