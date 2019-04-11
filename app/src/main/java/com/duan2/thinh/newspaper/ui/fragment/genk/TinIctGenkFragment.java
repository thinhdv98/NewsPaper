package com.duan2.thinh.newspaper.ui.fragment.genk;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.duan2.thinh.newspaper.R;

import com.duan2.thinh.newspaper.ui.fragment.genk.adapter.GenkTinIctAdapter;
import com.duan2.thinh.newspaper.ui.fragment.genk.model.Genk_TinIct_Content;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TinIctGenkFragment extends Fragment {
    private static final String TAG = "TinIctGenkFragment";
    private ArrayList<Genk_TinIct_Content> datas;
    private GenkTinIctAdapter adapter;
    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;


    public TinIctGenkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tin_ict_genk, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.genk_tinict_listview);
        swipeRefreshLayout = view.findViewById(R.id.genk_ict_refreshlayout);
        setupData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setupData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void setUpWidget(ArrayList<Genk_TinIct_Content> datas) {
        GenkTinIctAdapter adapter = new GenkTinIctAdapter(datas,getActivity());
        // Log.d(TAG, "setUpWidget: " + datas.size());
        listView.setAdapter(adapter);
    }

    @SuppressLint("StaticFieldLeak")
    private void setupData() {
        new AsyncTask<Void, Void, ArrayList<Genk_TinIct_Content>>() {
            ArrayList<Genk_TinIct_Content> datas = new ArrayList<>();

            @Override
            protected ArrayList<Genk_TinIct_Content> doInBackground(Void... voids) {
                try {
                    String title = "";
                    String link = "";
                    String des = "";
                    String img = "";
                    Document document = Jsoup.connect("http://genk.vn/tin-ict.chn").get();
                    Elements elements = document.select("ul.knsw-list").select("ul").select("li");
                    Log.d(TAG, "doInBackground: ");
                    for (Element element : elements) {
                        Element title1 = element.getElementsByTag("h4").first();
                        Element link1 = element.getElementsByTag("a").first();
                        Element des1 = element.getElementsByClass("knswli-sapo").first();
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
                        String linkd = "http://genk.vn" + link;
                        datas.add(new Genk_TinIct_Content(title,linkd,des,img));
                    }
                } catch (Exception e) {

                }
                return datas;
            }

            @Override
            protected void onPostExecute(ArrayList<Genk_TinIct_Content> data) {
                super.onPostExecute(data);
                setUpWidget(data);
            }
        }.execute();

    }
}