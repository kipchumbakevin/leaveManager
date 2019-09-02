package com.example.leavemanager.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leavemanager.utils.Constants;
import com.example.leavemanager.R;
import com.example.leavemanager.utils.SharedPreferencesConfig;
import com.example.leavemanager.models.UsersModel;
import com.example.leavemanager.networking.RetrofitClient;
import com.example.leavemanager.ui.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button login;
    TextView noAccount;
    EditText putDomain,putUsername,putPassword;
    public String accessToken,employeeDomain,employeeEmail,employeeUsername;
    private SharedPreferencesConfig sharedPreferencesConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login);
        noAccount = findViewById(R.id.noAccount);
        sharedPreferencesConfig = new SharedPreferencesConfig(getApplicationContext());
        putDomain = findViewById(R.id.putDomain);
        putUsername = findViewById(R.id.putUsername);
        putPassword = findViewById(R.id.putPassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sharedPreferencesConfig.isloggedIn()){
            welcome();
        }
    }

    private void userLogin() {
        String domain = putDomain.getText().toString();

        String username = putUsername.getText().toString();
        String password = putPassword.getText().toString();

        Call<UsersModel> call = RetrofitClient.getInstance(LoginActivity.this)

                .getApiConnector()

                .loginUser(domain,username,password);

        call.enqueue(new Callback<UsersModel>() {

            @Override

            public void onResponse(Call<UsersModel> call, Response<UsersModel> response) {

                if (response.isSuccessful()){

                    Toast.makeText(LoginActivity.this,"Logged in", Toast.LENGTH_SHORT).show();

                    accessToken = response.body().getAccessToken();

                    employeeDomain = response.body().getUser().getDomain();

                    employeeEmail = response.body().getUser().getEmail();

                    employeeUsername = response.body().getUser().getUsername();

                    sharedPreferencesConfig.saveAuthenticationInformation(accessToken,employeeDomain,employeeEmail,employeeUsername, Constants.ACTIVE_CONSTANT);
                    welcome();

                }

                else{

                    Toast.makeText(LoginActivity.this,"Login not correct", Toast.LENGTH_SHORT).show();

                }

            }

            @Override

            public void onFailure(Call<UsersModel> call, Throwable t) {

                Toast.makeText(LoginActivity.this,"error", Toast.LENGTH_SHORT).show();

                Log.d("Error", "Another One");

            }

        });
    }

    private void welcome() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
