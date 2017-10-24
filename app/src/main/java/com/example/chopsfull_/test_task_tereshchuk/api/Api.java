package com.example.chopsfull_.test_task_tereshchuk.api;

import com.example.chopsfull_.test_task_tereshchuk.model.Account;
import com.example.chopsfull_.test_task_tereshchuk.model.Videos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    @FormUrlEncoded
    @POST("auth/create")
    Call<Account> postAuth(@Header("Authorization") String authorization, @Field("username") String userName, @Field("password") String userPassword);

    @GET("videos/following")
    Call<Videos> getFeedVideos(@Header("AccessToken") String token, @Query("offset") String offset);

    @GET("videos/featured")
    Call<Videos> getFeaturedVideos(@Query("offset") String offset,@Query("limit") String limit);

    @GET("videos/new")
    Call<Videos> getNewVideos(@Query("offset") String offset,@Query("limit") String limit);
}
