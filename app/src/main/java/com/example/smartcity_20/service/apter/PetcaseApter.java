package com.example.smartcity_20.service.apter;

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
import com.example.smartcity_20.service.bean.PetcaseBean;

import java.util.List;

public class PetcaseApter extends RecyclerView.Adapter<PetcaseApter.Myhot>{

    private Context context;
    private List<PetcaseBean.RowsDTO> list;

    public PetcaseApter(Context context, List<PetcaseBean.RowsDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PetcaseApter.Myhot onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.l_item_petcase, null);
        return new PetcaseApter.Myhot(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PetcaseApter.Myhot holder, int position) {
        try {
            if(list.get(position) ==null && list.get(position).getDoctor()==null){
                return;
            }
            if(!TextUtils.isEmpty(list.get(position).getDoctor().getAvatar())){
                Glide.with(context).load(IpandPort.URL+list.get(position).getDoctor().getAvatar()).into(holder.imageUrls);
            }
            if(!TextUtils.isEmpty(list.get(position).getDoctor().getName())){
                holder.name.setText(list.get(position).getDoctor().getName());
            }

            if(!TextUtils.isEmpty(list.get(position).getQuestion())){
                holder.question.setText(list.get(position).getQuestion());
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

        private final ImageView imageUrls;
        private final TextView name;
        private final TextView question;

        public Myhot(@NonNull View itemView) {
            super(itemView);
            imageUrls = itemView.findViewById(R.id.imageUrls);
            name = itemView.findViewById(R.id.name);
            question = itemView.findViewById(R.id.question);
        }
    }

}


