package com.duan2.thinh.newspaper.ui.fragment.soha;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.duan2.thinh.newspaper.R;

import com.duan2.thinh.newspaper.ui.fragment.soha.adapter.SohaInfoAdapter;
import com.duan2.thinh.newspaper.ui.fragment.soha.model.Soha_Info_Content;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SohaINFOFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;

    ListView list;
    ArrayList<Soha_Info_Content> datas;
    SohaInfoAdapter adapter;

    public SohaINFOFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_soha_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = view.findViewById(R.id.soha_info_listview);
        swipeRefreshLayout = view.findViewById(R.id.soha_info_refresh);
        setupData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setupData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void setUpWidget(ArrayList<Soha_Info_Content> datas) {
        SohaInfoAdapter adapter = new SohaInfoAdapter(datas, getActivity());
        list.setAdapter(adapter);
    }

    @SuppressLint("StaticFieldLeak")
    private void setupData() {
        new AsyncTask<Void, Void, ArrayList<Soha_Info_Content>>() {
            ArrayList<Soha_Info_Content> datas = new ArrayList<>();

            @Override
            protected ArrayList<Soha_Info_Content> doInBackground(Void... voids) {
                try {
                    String title = "";
                    String link = "";
                    String des = "";
                    String img = "";
                    Document document = Jsoup.connect("http://m.soha.vn/infographic.htm").userAgent("Mozilla/5.0 (Linux; Android 7.0; PLUS Build/NRD90M) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.98 Mobile Safari/537.36").get();
                    Elements elements = document.select("ul.list-news").select("li");
                    for (Element element : elements) {
                        Element title1 = element.getElementsByTag("h3").first();
                        Element link1 = element.getElementsByTag("a").first();
                        Element des1 = element.getElementsByClass("sapo").first();
                        Element img1 = element.getElementsByTag("img").first();
                        if (title1 != null) {
                            title = title1.text();
                        }
                        if (des1 != null) {
                            des = des1.text();
                        }
                        if (img1 != null) {
                            img = img1.attr("src");
                        }
                        if (link1 != null) {
                            link = link1.attr("href");
                        }
                        String linkd = "http://m.soha.vn" + link;
                        datas.add(new Soha_Info_Content(title, linkd, des, img));
                    }
                } catch (Exception e) {

                }
                return datas;
            }

            @Override
            protected void onPostExecute(ArrayList<Soha_Info_Content> data) {
                super.onPostExecute(data);
                setUpWidget(data);
            }
        }.execute();

    }
}

