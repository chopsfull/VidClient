package com.example.chopsfull_.test_task_tereshchuk.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.chopsfull_.test_task_tereshchuk.App;
import com.example.chopsfull_.test_task_tereshchuk.R;
import com.example.chopsfull_.test_task_tereshchuk.adapters.VideoAdapter;
import com.example.chopsfull_.test_task_tereshchuk.fragments.listeners.EndlessRecyclerOnScrollListener;
import com.example.chopsfull_.test_task_tereshchuk.model.APIError;
import com.example.chopsfull_.test_task_tereshchuk.model.Video;
import com.example.chopsfull_.test_task_tereshchuk.model.Videos;
import com.example.chopsfull_.test_task_tereshchuk.utils.ErrorUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewFragment extends Fragment {

    List<Video> videoList;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    VideoAdapter videoAdapter;
    EndlessRecyclerOnScrollListener scrollListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new, container, false);
        videoList = new ArrayList<>();
        swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.swipe_new);
        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_fragment_new);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        scrollListener = new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                loadMore(current_page*10);
            }
        };
        recyclerView.setOnScrollListener(scrollListener);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshVideos();
            }
        });
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFirstPage(0);

    }

    private void setupAdapter(){
        videoAdapter = new VideoAdapter(videoList);
        recyclerView.setAdapter(videoAdapter);
    }

    private void getFirstPage(int offset){
        App.getApi().getNewVideos(String.valueOf(offset),"10").enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                if (response.isSuccessful()) {
                    videoList.addAll(response.body().getVideos());
                    scrollListener.resetState();
                    setupAdapter();
                    Log.i("TAG", call.request().url().toString());
                }else{
                    APIError error = ErrorUtils.parseError(response);
                    Toast.makeText(getActivity(), error.getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {

            }
        });
    }

    private void loadMore(int offset){
        App.getApi().getNewVideos(String.valueOf(offset),"10").enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                if (response.isSuccessful()) {
                    videoAdapter.appendItems(response.body().getVideos());
                    Log.i("TAG", call.request().url().toString());
                }else{
                    APIError error = ErrorUtils.parseError(response);
                    Toast.makeText(getActivity(), error.getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {

            }
        });
    }

    private void refreshVideos(){
        videoList = new ArrayList<>();
        getFirstPage(0);
        swipeRefreshLayout.setRefreshing(false);

    }
}
