package com.example.smartcity_20.service.takeout.apter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.Common;
import com.example.smartcity_20.config.java.IpandPort;
import com.example.smartcity_20.service.takeout.FoodtypeActivity;
import com.example.smartcity_20.service.takeout.bean.FoodtypeBean;
import com.example.smartcity_20.service.takeout.bean.ReviewBean;

import java.util.List;

public class ReviewlistApter extends RecyclerView.Adapter<ReviewlistApter.Myhot>{

    private Context context;
    private List<ReviewBean.RowsDTO> list;

    public ReviewlistApter(Context context, List<ReviewBean.RowsDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ReviewlistApter.Myhot onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.l_item_reviewlist, null);
        return new ReviewlistApter.Myhot(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewlistApter.Myhot holder, int position) {
        try {
            if(list.get(position) ==null){
                return;
            }
            if(!TextUtils.isEmpty(list.get(position).getAvatar())){
                Glide.with(context).load(IpandPort.URL+list.get(position).getAvatar()).into(holder.avatar);
            }
            if(!TextUtils.isEmpty(list.get(position).getUserName())){
                holder.userName.setText(list.get(position).getUserName());
            }

            if(!TextUtils.isEmpty(list.get(position).getCommentDate())){
                holder.commentDate.setText(list.get(position).getCommentDate());
            }

            if(list.get(position).getScore() !=null){
                holder.reting.setRating(list.get(position).getScore());
            }
            if(!TextUtils.isEmpty(list.get(position).getContent())){
                holder.content.setText("用户评论: "+ list.get(position).getContent());
            }

            if(!TextUtils.isEmpty(list.get(position).getReplyContent())){
                holder.replyContent.setText("店家回复: "+list.get(position).getReplyContent());
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

        private final ImageView avatar;
        private final TextView userName;
        private final RatingBar reting;
        private final TextView replyContent;
        private final TextView content;
        private final TextView commentDate;
        private final RelativeLayout re;

        public Myhot(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            userName = itemView.findViewById(R.id.userName);
            commentDate = itemView.findViewById(R.id.commentDate);
            reting = itemView.findViewById(R.id.reting);
            content = itemView.findViewById(R.id.content);
            replyContent = itemView.findViewById(R.id.replyContent);
            re = itemView.findViewById(R.id.re);
        }
    }

}


