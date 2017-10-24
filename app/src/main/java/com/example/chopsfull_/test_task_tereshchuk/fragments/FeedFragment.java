package com.example.chopsfull_.test_task_tereshchuk.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chopsfull_.test_task_tereshchuk.model.APIError;
import com.example.chopsfull_.test_task_tereshchuk.App;
import com.example.chopsfull_.test_task_tereshchuk.utils.ErrorUtils;
import com.example.chopsfull_.test_task_tereshchuk.R;
import com.example.chopsfull_.test_task_tereshchuk.model.Account;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedFragment extends Fragment {

    public static final String PREFS_NAME = "MyPrefsFile";
    private String mLogin;
    private String mPassword;
    private EditText login;
    private EditText pass;
    private String token;
    private Account mAcc;


    public void setmAcc(Account mAcc) {
        this.mAcc = mAcc;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME,0);
        boolean silent = settings.getBoolean("silentMode", false);*/

    }

  /*  @Override
    public void onStop() {
        super.onStop();
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.("silentMode", mSilentMode);
        editor.commit();
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feed, container, false);
        login =(EditText) v.findViewById(R.id.edit_login);
        pass = (EditText) v.findViewById(R.id.edit_pass);


        Button loginButton = (Button) v.findViewById(R.id.login_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });


        return v;
    }



    public void Authorization(String username,String password){
        String basicauth = "Basic "+ Base64.encodeToString(String.format("%s:%s",username,password).getBytes(), Base64.NO_WRAP);
        App.getApi().postAuth(basicauth, username, password).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()) {
                    mAcc = response.body();
                    getAuthToken(response.body());
                    Toast.makeText(getActivity(),"Welcome,"+response.body().getUser().getUsername(),Toast.LENGTH_SHORT).show();
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

    private boolean loginValidation() {
        // Reset errors.
        login.setError(null);
        pass.setError(null);
        // Store values at the time of the login attempt.
        mLogin = login.getText().toString();
        mPassword = pass.getText().toString();
        boolean cancel = false;
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(mPassword) && !validLength(mPassword)) {
            pass.setError(getString(R.string.error_invalid_password));
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(mLogin)) {
            login.setError(getString(R.string.error_field_required));
            cancel = true;
        } else if (!validLength(mLogin)) {
            login.setError(getString(R.string.error_invalid_login));
            cancel = true;
        }
        return cancel;
    }

    private boolean validLength(String line) {
        //checking length of input
        return line.length() > 4;
    }

    private void attemptLogin(){
        //processing login parameters
        boolean mCancel = this.loginValidation();
        if (!mCancel){
            Authorization(mLogin, mPassword);
        }
    }

    private void getAuthToken(Account account){
        this.token=account.getAuth().getToken();
    }
}
