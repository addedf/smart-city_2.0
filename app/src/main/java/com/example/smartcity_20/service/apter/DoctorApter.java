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
import com.example.smartcity_20.service.bean.DoctorBean;
import com.example.smartcity_20.service.bean.PetcaseBean;
import com.example.smartcity_20.service.pethospital.HospitalinquiryActivity;
import com.google.gson.Gson;

import java.util.List;

public class DoctorApter extends RecyclerView.Adapter<DoctorApter.Myhot>{

    private Context context;
    private List<DoctorBean.RowsDTO> list;

    public DoctorApter(Context context, List<DoctorBean.RowsDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DoctorApter.Myhot onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.l_item_doctor, null);
        return new DoctorApter.Myhot(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorApter.Myhot holder, int position) {
        try {
            if(list.get(position) ==null ){
                return;
            }
            if(!TextUtils.isEmpty(list.get(position).getAvatar())){
                Glide.with(context).load(IpandPort.URL+list.get(position).getAvatar()).into(holder.imageUrls);
            }
            if(!TextUtils.isEmpty(list.get(position).getName())){
                holder.name.setText(list.get(position).getName());
            }

            if(!TextUtils.isEmpty(list.get(position).getJobName())){
                holder.jobName.setText("职称: "+list.get(position).getJobName());
            }

            if(!TextUtils.isEmpty(list.get(position).getPracticeNo())){
                holder.practiceNo.setText("执业证号: "+list.get(position).getPracticeNo());
            }


            if(!TextUtils.isEmpty(list.get(position).getGoodAt())){
                holder.goodAt.setText("擅长: "+list.get(position).getGoodAt());
            }

            if(list.get(position).getWorkingYears() != null){
                holder.workingYears.setText("从业年限: "+String.valueOf(list.get(position).getWorkingYears()+"年"));
            }

            holder.re.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, HospitalinquiryActivity.class);
                    Gson gson = new Gson();
                    String str = gson.toJson(list.get(position));
                    intent.putExtra(Common.HospitalinquiryActivityid,str);
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

        private final ImageView imageUrls;
        private final TextView name;
        private final TextView jobName;
        private final TextView practiceNo;
        private final TextView workingYears;
        private final TextView goodAt;
        private final RelativeLayout re;

        public Myhot(@NonNull View itemView) {
            super(itemView);
            imageUrls = itemView.findViewById(R.id.imageUrls);
            name = itemView.findViewById(R.id.name);
            jobName = itemView.findViewById(R.id.jobName);

            practiceNo = itemView.findViewById(R.id.practiceNo);
            workingYears = itemView.findViewById(R.id.workingYears);
            goodAt = itemView.findViewById(R.id.goodAt);
            re = itemView.findViewById(R.id.re);
        }
    }

}



