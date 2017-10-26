package com.example.chopsfull_.test_task_tereshchuk.utils;

import android.app.Activity;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.example.chopsfull_.test_task_tereshchuk.App;
import com.example.chopsfull_.test_task_tereshchuk.MainActivity;
import com.example.chopsfull_.test_task_tereshchuk.model.APIError;
import com.example.chopsfull_.test_task_tereshchuk.model.Account;
import com.example.chopsfull_.test_task_tereshchuk.utils.singletone.LoginCredentials;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class authorUtils {

    Activity activity;
    Account account = null;

    public authorUtils(Activity activity){
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }

    public void Authorization(String login, String password){
        String basicauth = "Basic "+ Base64.encodeToString(String.format("%s:%s",login,password).getBytes(), Base64.NO_WRAP);
        App.getApi().postAuth(basicauth, login,password).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()) {
                    LoginCredentials.getInstance().setToken(response.body().getAuth().getToken());
                    Toast.makeText(getActivity(),"Welcome,"+response.body().getUser().getUsername(),Toast.LENGTH_SHORT).show();
                    Log.i("TAG", response.body().getAuth().getToken());
                } else {
                    APIError error = ErrorUtils.parseError(response);
                    Toast.makeText(getActivity(), error.getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
                Log.i("TAG", call.request().url().toString());
            }
        });

    }
}
