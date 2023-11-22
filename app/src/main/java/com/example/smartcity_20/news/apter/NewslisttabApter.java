package com.example.smartcity_20.news.apter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
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
import com.example.smartcity_20.home.apter.NewslistApter;
import com.example.smartcity_20.home.bean.HotnewsBean;
import com.example.smartcity_20.news.bean.NewsTypetabBean;
import com.example.smartcity_20.news.bean.NewsmapBean;

import java.util.List;

public class NewslisttabApter extends RecyclerView.Adapter<NewslisttabApter.Myhot>{

    private Context context;
    private List<NewsmapBean.RowsDTO> list;

    public NewslisttabApter(Context context, List<NewsmapBean.RowsDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NewslisttabApter.Myhot onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.l_item_news, null);
        return new NewslisttabApter.Myhot(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull NewslisttabApter.Myhot holder, int position) {
        try {
            if(list.get(position) ==null){
                return;
            }
            if(!TextUtils.isEmpty(list.get(position).getCover())){
                Glide.with(context).load(IpandPort.URL+list.get(position).getCover()).into(holder.imgUrl);
            }
            if(!TextUtils.isEmpty(list.get(position).getTitle())){
                holder.title.setText(list.get(position).getTitle());
            }
            if(!TextUtils.isEmpty(list.get(position).getContent())){
                String html = Html.fromHtml(list.get(position).getContent()).toString();
                holder.content.setText(html);
            }
            if(list.get(position).getLikeNum() !=null){
                holder.likeNum.setText("点赞: "+String.valueOf(list.get(position).getLikeNum()));
            }
            if(!TextUtils.isEmpty(list.get(position).getCreateTime())){
                holder.createTime.setText(list.get(position).getCreateTime());
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
        return list==null?0:list.size();
    }

    public class Myhot extends RecyclerView.ViewHolder{

        private final ImageView imgUrl;
        private final TextView title;
        private final TextView content;
        private final TextView likeNum;
        private final TextView createTime;
        private final RelativeLayout re;

        public Myhot(@NonNull View itemView) {
            super(itemView);
            imgUrl = itemView.findViewById(R.id.imgUrl);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            likeNum = itemView.findViewById(R.id.likeNum);
            createTime = itemView.findViewById(R.id.createTime);
            re = itemView.findViewById(R.id.re);
        }
    }

}

