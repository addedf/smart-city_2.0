package com.example.smartcity_20.service.takeout.apter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.IpandPort;
import com.example.smartcity_20.service.takeout.FoodorderActivity;
import com.example.smartcity_20.service.takeout.bean.FoodBean;
import com.example.smartcity_20.service.takeout.bean.FoodtypetakoutBean;

import java.util.List;

public class FoodlisttakeoutApter extends RecyclerView.Adapter<FoodlisttakeoutApter.Myhot>{

    private Context context;
    private List<FoodBean.DataDTO> list;

    public FoodlisttakeoutApter(Context context, List<FoodBean.DataDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FoodlisttakeoutApter.Myhot onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.l_item_foodlisttakeout, null);
        return new FoodlisttakeoutApter.Myhot(inflate);
    }

    public void account(){
        double money =  0.0;
        try {
            for (FoodBean.DataDTO dataDTO : list) {
                money=money+dataDTO.getNums()*dataDTO.getPrice();
            }
            if(context instanceof FoodorderActivity){
                FoodorderActivity foodorder =  (FoodorderActivity)context;
                foodorder.displayprice(String.valueOf(money),list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onBindViewHolder(@NonNull FoodlisttakeoutApter.Myhot holder, int position) {
        try {
            if(list.get(position) ==null){
                return;
            }
            if(!TextUtils.isEmpty(list.get(position).getImgUrl())){
                Glide.with(context).load(IpandPort.URL+list.get(position).getImgUrl()).into(holder.imgUrl);
            }

            if(!TextUtils.isEmpty(list.get(position).getDetail())){
                holder.detail.setText(list.get(position).getName());
            }

            if(list.get(position).getPrice() != null){
                holder.price.setText("价格: "+list.get(position).getPrice());
            }

            if(list.get(position).getSaleQuantity()!=null ){
                holder.saleQuantity.setText("月销量: "+String.valueOf(list.get(position).getSaleQuantity()));
            }

            if(list.get(position).getFavorRate()!=null ){
                holder.favorRate.setText("好评率: "+String.valueOf(list.get(position).getFavorRate()));
            }

            holder.addnum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int  nums = list.get(position).getNums();
                    if(nums>=0){
                        nums++;
                        list.get(position).setNums(nums);
                        holder.ed_num.setText(String.valueOf(nums));
                        account();
                    }
                }
            });

            holder.decreasenum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nums = list.get(position).getNums();
                    if(nums>0 ){
                        nums--;
                        list.get(position).setNums(nums);
                        holder.ed_num.setText(String.valueOf(nums));
                        account();
                    }else {
                        Toast.makeText(context,"数量必须大于0",Toast.LENGTH_SHORT).show();
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


        private final RelativeLayout re;
        private final ImageView imgUrl;
        private final TextView detail;
        private final TextView price;
        private final TextView saleQuantity;
        private final TextView favorRate;
        private final TextView decreasenum;
        private final TextView addnum;
        private final EditText ed_num;

        public Myhot(@NonNull View itemView) {
            super(itemView);
            re = itemView.findViewById(R.id.re);
            imgUrl = itemView.findViewById(R.id.imgUrl);
            detail = itemView.findViewById(R.id.detail);
            price = itemView.findViewById(R.id.price);
            saleQuantity = itemView.findViewById(R.id.saleQuantity);
            favorRate = itemView.findViewById(R.id.favorRate);
            decreasenum = itemView.findViewById(R.id.decreasenum);
            addnum = itemView.findViewById(R.id.addnum);
            ed_num = itemView.findViewById(R.id.ed_num);
        }
    }

}



