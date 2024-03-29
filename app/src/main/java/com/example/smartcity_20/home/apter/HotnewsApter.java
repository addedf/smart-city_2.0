package com.example.smartcity_20.home.apter;

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
import com.example.smartcity_20.home.NewXuanQingActivity;
import com.example.smartcity_20.home.bean.HelplistBean;
import com.example.smartcity_20.home.bean.HotnewsBean;

import java.util.List;

public class HotnewsApter extends RecyclerView.Adapter<HotnewsApter.Myhot>{

    private Context context;
    private List<HotnewsBean.RowsDTO> list;

    public HotnewsApter(Context context, List<HotnewsBean.RowsDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HotnewsApter.Myhot onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.l_item_hotnews, null);
        return new HotnewsApter.Myhot(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HotnewsApter.Myhot holder, int position) {
        try {
            if(list.get(position) ==null){
                return;
            }
            if(!TextUtils.isEmpty(list.get(position).getCover())){
                Glide.with(context).load(IpandPort.URL+list.get(position).getCover()).into(holder.cover);
            }
            if(!TextUtils.isEmpty(list.get(position).getTitle())){
                holder.title.setText(list.get(position).getTitle());
            }

            //跳转到
            holder.re.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewXuanQingActivity.class);
                    intent.putExtra(Common.NEWXUANQINGACTIVITY,String.valueOf(list.get(position).getId()));
                    context.startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list==null?0:2;
    }

    public class Myhot extends RecyclerView.ViewHolder{

        private final ImageView cover;
        private final TextView title;
        private final RelativeLayout re;

        public Myhot(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.cover);
            title = itemView.findViewById(R.id.title);
            re = itemView.findViewById(R.id.re);
        }
    }

}

