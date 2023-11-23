package com.example.smartcity_20.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.OkHttpRequest;
import com.example.smartcity_20.home.apter.HelplistApter;
import com.example.smartcity_20.home.apter.HotnewsApter;
import com.example.smartcity_20.home.apter.NewslistApter;
import com.example.smartcity_20.home.bean.HotnewsBean;
import com.example.smartcity_20.home.apter.VpmapApter;
import com.example.smartcity_20.home.bean.HelplistBean;
import com.example.smartcity_20.home.bean.NewsTypeBean;
import com.example.smartcity_20.home.bean.VpmapBean;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    private View view;
    private SearchView sear;
    private ViewPager vp;
    private RecyclerView helplist;
    private RecyclerView newshots;
    private RecyclerView newlist;
    private TabLayout mytab;
    private Context context;
    private String TAG = "TAG";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initview();
        //判断是否登录过
        islogin();
        vpmap();
        help_list();
        hot_news();
        my_tab();
        return view;
    }

    private void islogin() {
        SharedPreferences login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String token = login.getString("TOKEN", "");
        if(!"".equals(token) && !TextUtils.isEmpty(token)){
            OkHttpRequest.TOKEN=token;
            Log.e(TAG,"Token="+token);
        }
    }

    private void my_tab() {
        OkHttpRequest.doNetRequst("prod-api/press/category/list",
                OkHttpRequest.GET,
                NewsTypeBean.class,
                new OkHttpRequest.NetRequst() {
                    @Override
                    public void ok(Object obj) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                NewsTypeBean newsTypeBean = (NewsTypeBean)obj;
                                if(newsTypeBean.getCode()==200){
                                    for (NewsTypeBean.DataDTO datum : newsTypeBean.getData()) {
                                        TabLayout.Tab tab = mytab.newTab();
                                        tab.setText(datum.getName());
                                        mytab.addTab(tab);
                                    }
                                    sendnewtype(newsTypeBean.getData().get(0).getId());
                                    sendmytab(newsTypeBean);
                                }
                            }
                        });
                    }

                    @Override
                    public void no(String msg) {

                    }
                });
    }

    private void sendmytab(NewsTypeBean newsTypeBean) {
        mytab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                try {
                    String name = tab.getText().toString();
                    int position = tab.getPosition();
                    List<NewsTypeBean.DataDTO> data = newsTypeBean.getData();
                    if(name.equals(data.get(position).getName())){
                        sendnewtype(data.get(position).getId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void sendnewtype(Integer id) {
        OkHttpRequest.doNetRequst("prod-api/press/press/list?type="+id,
                OkHttpRequest.GET,
                HotnewsBean.class,
                new OkHttpRequest.NetRequst() {
                    @Override
                    public void ok(Object obj) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                HotnewsBean hotNew = (HotnewsBean)obj;
                                if(hotNew.getCode()==200){
                                    newlist.setLayoutManager(new LinearLayoutManager(context));
                                    newlist.setAdapter(new NewslistApter(context,hotNew.getRows()));
                                }
                            }
                        });
                    }

                    @Override
                    public void no(String msg) {

                    }
                });
    }

    private void hot_news() {
        OkHttpRequest.doNetRequst("prod-api/press/press/list?hot=y",
                OkHttpRequest.GET,
                HotnewsBean.class,
                new OkHttpRequest.NetRequst() {
                    @Override
                    public void ok(Object obj) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                HotnewsBean hotNew = (HotnewsBean)obj;
                                if(hotNew.getCode()==200){
                                    newshots.setLayoutManager(new GridLayoutManager(context,2));
                                    newshots.setAdapter(new HotnewsApter(context,hotNew.getRows()));
                                }
                            }
                        });
                    }

                    @Override
                    public void no(String msg) {

                    }
                });
    }

    private void help_list() {
        OkHttpRequest.doNetRequst("prod-api/api/service/list",
                OkHttpRequest.GET,
                HelplistBean.class,
                new OkHttpRequest.NetRequst() {
                    @Override
                    public void ok(Object obj) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                HelplistBean helplistBean = (HelplistBean)obj;
                                if(helplistBean.getCode()==200){
                                    helplist.setLayoutManager(new GridLayoutManager(context,5));
                                    helplist.setAdapter(new HelplistApter(context,helplistBean.getRows()));
                                }
                            }
                        });
                    }

                    @Override
                    public void no(String msg) {

                    }
                });
    }

    private void vpmap() {
        OkHttpRequest.doNetRequst("prod-api/api/rotation/list",
                OkHttpRequest.GET,
                VpmapBean.class,
                new OkHttpRequest.NetRequst() {
                    @Override
                    public void ok(Object obj) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                VpmapBean vopmapBean = (VpmapBean)obj;
                                if(vopmapBean.getCode()==200){
                                    ArrayList<String> arrayList = new ArrayList();
                                    for (VpmapBean.RowsDTO row : vopmapBean.getRows()) {
                                        arrayList.add(row.getAdvImg());
                                    }
                                    vp.setAdapter(new VpmapApter(context,arrayList));
                                }
                            }
                        });
                    }

                    @Override
                    public void no(String msg) {

                    }
                });
    }

    private void initview() {
        sear = view.findViewById(R.id.sear);
        vp = view.findViewById(R.id.vp);
        helplist = view.findViewById(R.id.helplist);
        newshots = view.findViewById(R.id.newshots);
        newlist = view.findViewById(R.id.newlist);
        mytab = view.findViewById(R.id.mytab);

        context = HomeFragment.this.getContext();
    }
}