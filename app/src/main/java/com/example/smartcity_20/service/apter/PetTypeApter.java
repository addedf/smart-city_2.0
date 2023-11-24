package com.example.smartcity_20.service.apter;

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
import com.example.smartcity_20.home.apter.HelplistApter;
import com.example.smartcity_20.home.bean.HelplistBean;
import com.example.smartcity_20.service.bean.PetTypeBean;
import com.example.smartcity_20.service.pethospital.DoctorActivity;

import java.util.List;

public class PetTypeApter extends RecyclerView.Adapter<PetTypeApter.Myhot>{

    private Context context;
    private List<PetTypeBean.DataDTO> list;

    public PetTypeApter(Context context, List<PetTypeBean.DataDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PetTypeApter.Myhot onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.l_item_help, null);
        return new PetTypeApter.Myhot(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PetTypeApter.Myhot holder, int position) {
        try {
            if(list.get(position) ==null){
                return;
            }
            if(!TextUtils.isEmpty(list.get(position).getImgUrl())){
                Glide.with(context).load(IpandPort.URL+list.get(position).getImgUrl()).into(holder.imgUrl);
            }
            if(!TextUtils.isEmpty(list.get(position).getName())){
                holder.serviceName.setText(list.get(position).getName());
            }

            holder.re.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DoctorActivity.class);
                    intent.putExtra(Common.DoctorID,String.valueOf(list.get(position).getId()));
                    context.startActivity(intent);
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
        private final TextView serviceName;
        private final RelativeLayout re;

        public Myhot(@NonNull View itemView) {
            super(itemView);
            imgUrl = itemView.findViewById(R.id.imgUrl);
            serviceName = itemView.findViewById(R.id.serviceName);
            re = itemView.findViewById(R.id.re);
        }
    }

}

