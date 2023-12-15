package com.example.smartcity_20.service.takeout.apter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity_20.R;
import com.example.smartcity_20.service.takeout.bean.FoodtypetakoutBean;

import java.util.List;

public class FoodtypetakoutApter extends RecyclerView.Adapter<FoodtypetakoutApter.Myhot>{

    private Context context;
    private List<FoodtypetakoutBean.DataDTO> list;
    private TakeoutcategoryId  takeoutcategoryId;


    public FoodtypetakoutApter(Context context, TakeoutcategoryId takeoutcategoryId) {
        this.context = context;
        this.takeoutcategoryId = takeoutcategoryId;
    }

    public void setData(List<FoodtypetakoutBean.DataDTO> list){
        this.list = list;
    }

    public interface TakeoutcategoryId  {
        void playcategoryId(Integer categoryId,int position);
    }
    @NonNull
    @Override
    public FoodtypetakoutApter.Myhot onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.l_item_foodtypetakout, null);
        return new FoodtypetakoutApter.Myhot(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodtypetakoutApter.Myhot holder, int position) {
        try {
            if(list.get(position) ==null){
                return;
            }
            if(!TextUtils.isEmpty(list.get(position).getName())){
                holder.name.setText(list.get(position).getName());
            }

            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    takeoutcategoryId.playcategoryId(list.get(position).getId(),position);
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

        private final TextView name;
        private final RelativeLayout re;

        public Myhot(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            re = itemView.findViewById(R.id.re);
        }
    }

}


