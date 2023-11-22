package com.example.smartcity_20.home.apter;

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
import com.example.smartcity_20.home.bean.HelplistBean;

import java.util.List;

public class HelplistApter extends RecyclerView.Adapter<HelplistApter.Myhot>{

    private Context context;
    private List<HelplistBean.RowsDTO> list;

    public HelplistApter(Context context, List<HelplistBean.RowsDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Myhot onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.l_item_help, null);
        return new HelplistApter.Myhot(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Myhot holder, int position) {
        try {
            if(list.get(position) ==null){
                return;
            }
            if(!TextUtils.isEmpty(list.get(position).getImgUrl())){
                Glide.with(context).load(IpandPort.URL+list.get(position).getImgUrl()).into(holder.imgUrl);
            }
            if(!TextUtils.isEmpty(list.get(position).getServiceName())){
                holder.serviceName.setText(list.get(position).getServiceName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list==null?0:10;
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
