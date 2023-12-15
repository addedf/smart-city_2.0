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
import com.example.smartcity_20.service.takeout.bean.FoodBean;
import com.example.smartcity_20.service.takeout.bean.FoodhottyBean;

import java.util.List;

public class CarteApter extends RecyclerView.Adapter<CarteApter.Myhot>{

    private Context context;
    private List<FoodBean.DataBean> list;

    public CarteApter(Context context, List<FoodBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CarteApter.Myhot onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.l_item_crte, null);
        return new CarteApter.Myhot(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CarteApter.Myhot holder, int position) {
        try {
            if(list.get(position) ==null){
                return;
            }
            if(!TextUtils.isEmpty(list.get(position).getImgUrl())){
                Glide.with(context).load(IpandPort.URL+list.get(position).getImgUrl()).into(holder.imgUrl);
            }



            if(list.get(position).getName()!=null){
                holder.name.setText(list.get(position).getName());
            }

            if(list.get(position).getPrice()!=null){
                holder.price.setText(String.valueOf(list.get(position).getPrice()));
            }

            if(list.get(position).getNums() !=0){
                holder.nums.setText(String.valueOf("x "+list.get(position).getNums()));
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
        private final TextView price;
        private final TextView nums;
        private final RelativeLayout re;

        public Myhot(@NonNull View itemView) {
            super(itemView);
            imgUrl = itemView.findViewById(R.id.imgUrl);
            name = itemView.findViewById(R.id.name);
            nums = itemView.findViewById(R.id.nums);
            price = itemView.findViewById(R.id.price);
            re = itemView.findViewById(R.id.re);
        }
    }

}

