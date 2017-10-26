package com.example.chopsfull_.test_task_tereshchuk.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.chopsfull_.test_task_tereshchuk.retrofit.App;
import com.example.chopsfull_.test_task_tereshchuk.R;
import com.example.chopsfull_.test_task_tereshchuk.utils.FragmentSetupUtils;
import com.example.chopsfull_.test_task_tereshchuk.model.APIError;
import com.example.chopsfull_.test_task_tereshchuk.model.Videos;
import com.example.chopsfull_.test_task_tereshchuk.utils.ErrorUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewFragment extends FragmentSetupUtils {

    public static Fragment getInstance(){return new NewFragment();}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.swipe_new);
        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_fragment_new);
        setupView(v);
        return v;
    }

    protected void getFirstPageOfVids(int offset){
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

    protected void getMoreVids(int offset){
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








}
