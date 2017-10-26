package com.example.chopsfull_.test_task_tereshchuk.utils;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.chopsfull_.test_task_tereshchuk.adapters.VideoAdapter;
import com.example.chopsfull_.test_task_tereshchuk.model.Video;
import com.example.chopsfull_.test_task_tereshchuk.utils.listeners.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;
import java.util.List;

public abstract class FragmentSetupUtils extends Fragment {

    protected List<Video> videoList;
    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected VideoAdapter videoAdapter;
    protected EndlessRecyclerOnScrollListener scrollListener;
    protected SwipeRefreshLayout.OnRefreshListener refreshListener;



    protected void setupAdapter(){
        videoAdapter = new VideoAdapter(videoList);
        recyclerView.setAdapter(videoAdapter);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFirstPageOfVids(0);
    }

    protected abstract void getFirstPageOfVids(int offset);
    protected abstract void getMoreVids(int offset);

    protected void setupView(View v){
        videoList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        scrollListener = new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                getMoreVids(current_page*10);
            }
        };
        recyclerView.setOnScrollListener(scrollListener);
        swipeRefreshLayout.setOnRefreshListener(refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshVideos();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setupAdapter();
    }

    protected void refreshVideos(){
        videoList = new ArrayList<>();
        getFirstPageOfVids(0);
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onStart() {
        super.onStart();
        if(videoList.size()==0)
            refreshListener.onRefresh();
    }
}
