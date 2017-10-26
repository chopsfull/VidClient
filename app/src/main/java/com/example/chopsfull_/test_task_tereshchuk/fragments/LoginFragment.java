package com.example.chopsfull_.test_task_tereshchuk.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.chopsfull_.test_task_tereshchuk.retrofit.App;
import com.example.chopsfull_.test_task_tereshchuk.utils.ErrorUtils;
import com.example.chopsfull_.test_task_tereshchuk.R;
import com.example.chopsfull_.test_task_tereshchuk.model.Account;
import com.example.chopsfull_.test_task_tereshchuk.utils.singletone.LoginCredentials;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    public static Fragment getInstance(){return new LoginFragment();}

    private EditText login;
    private EditText pass;
    Button loginButton;

    private Account account= null;

    private LoginCredentials credentials = LoginCredentials.getInstance();;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(loadCreds())
            Authorization(credentials.getLogin(),credentials.getPassword());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        login =(EditText) v.findViewById(R.id.edit_login);
        pass = (EditText) v.findViewById(R.id.edit_pass);
        loginButton = (Button) v.findViewById(R.id.login_btn);

        if(credentials.getLogin()!=null&&credentials.getPassword()!=null){
            login.setText(credentials.getLogin());
            pass.setText(credentials.getPassword());

        }


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });


        return v;
    }



    private void Authorization(String login,String password){
        String basicauth = "Basic "+ Base64.encodeToString(String.format("%s:%s",login,password).getBytes(), Base64.NO_WRAP);
        App.getApi().postAuth(basicauth, login,password).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()) {
                    account = response.body();
                    Toast.makeText(getActivity(),"Welcome,"+response.body().getUser().getUsername(),Toast.LENGTH_SHORT).show();
                    credentials.setLoggedIn(true);
                    credentials.setmAcc(account);
                    ((ContainerFragment)getParentFragment()).replaceFragment(FeedFragment.getInstance(),true);
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
        credentials.setLogin(login.getText().toString());
        credentials.setPassword(pass.getText().toString());
        boolean cancel = false;
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(credentials.getPassword()) && !validLength(credentials.getPassword())) {
            pass.setError(getString(R.string.error_invalid_password));
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(credentials.getLogin())) {
            login.setError(getString(R.string.error_field_required));
            cancel = true;
        } else if (!validLength(credentials.getLogin())) {
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
            Authorization(credentials.getLogin(), credentials.getPassword());
        }
    }




    private boolean loadCreds() {
       if(!credentials.isLoggedIn()) {
           SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
           String result = sharedPref.getString(getString(R.string.saved_credentials), null);
           sharedPref.edit().remove(getString(R.string.saved_credentials));
           if (result != null) {
               setCreds(result);
            if (credentials.getLogin() != null && credentials.getPassword() != null)
               return true;
            }
       }
        return false;
    }
    private void setCreds(String input){
        if(input!=null) {
            String[] result = input.split(":");
            credentials.setLogin(result[0]);
            credentials.setPassword(result[1]);
        }
    }

}
