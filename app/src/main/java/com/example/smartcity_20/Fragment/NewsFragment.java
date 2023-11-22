package com.example.smartcity_20.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartcity_20.R;
import com.example.smartcity_20.config.java.OkHttpRequest;
import com.example.smartcity_20.home.apter.NewslistApter;
import com.example.smartcity_20.home.apter.VpmapApter;
import com.example.smartcity_20.home.bean.HotnewsBean;
import com.example.smartcity_20.home.bean.NewsTypeBean;
import com.example.smartcity_20.home.bean.VpmapBean;
import com.example.smartcity_20.news.apter.NewslisttabApter;
import com.example.smartcity_20.news.bean.NewsTypetabBean;
import com.example.smartcity_20.news.bean.NewsmapBean;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment {


    private View view;
    private RecyclerView newlist;
    private TabLayout mytab;
    private ViewPager vp;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        initview();
        vpmap();
        my_tab();
        return view;
    }

    private void vpmap() {
        OkHttpRequest.doNetRequst("prod-api/api/park/press/press/list",
                OkHttpRequest.GET,
                NewsmapBean.class,
                new OkHttpRequest.NetRequst() {
                    @Override
                    public void ok(Object obj) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                NewsmapBean newsmap = (NewsmapBean)obj;
                                if(newsmap.getCode()==200){
                                    List<NewsmapBean.RowsDTO> rows = newsmap.getRows();
                                    List<String> arrayList = new ArrayList();
                                    for (int i = 0; i < 3; i++) {
                                        arrayList.add(rows.get(i).getCover());
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

    private void my_tab() {
        OkHttpRequest.doNetRequst("prod-api/api/park/press/category/list",
                OkHttpRequest.GET,
                NewsTypetabBean.class,
                new OkHttpRequest.NetRequst() {
                    @Override
                    public void ok(Object obj) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                NewsTypetabBean newsTypetab = (NewsTypetabBean)obj;
                                if(newsTypetab.getCode()==200){
                                    for (NewsTypetabBean.DataDTO datum : newsTypetab.getData()) {
                                        TabLayout.Tab tab = mytab.newTab();
                                        tab.setText(datum.getName());
                                        mytab.addTab(tab);
                                    }
                                    sendnewtype(newsTypetab.getData().get(0).getId());
                                    sendmytab(newsTypetab);
                                }
                            }
                        });
                    }

                    @Override
                    public void no(String msg) {

                    }
                });
    }

    private void sendmytab(NewsTypetabBean newsTypeBean) {
        mytab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                try {
                    String name = tab.getText().toString();
                    int position = tab.getPosition();
                    List<NewsTypetabBean.DataDTO> data = newsTypeBean.getData();
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
        OkHttpRequest.doNetRequst("prod-api/api/park/press/press/list?type="+id,
                OkHttpRequest.GET,
                NewsmapBean.class,
                new OkHttpRequest.NetRequst() {
                    @Override
                    public void ok(Object obj) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                NewsmapBean newsmap = (NewsmapBean)obj;
                                if(newsmap.getCode()==200){
                                    newlist.setLayoutManager(new LinearLayoutManager(context));
                                    newlist.setAdapter(new NewslisttabApter(context,newsmap.getRows()));
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
        newlist = view.findViewById(R.id.newlist);
        mytab = view.findViewById(R.id.mytab);
        vp = view.findViewById(R.id.vp);
        context = getContext();
    }
}