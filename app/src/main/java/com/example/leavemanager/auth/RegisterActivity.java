package com.example.leavemanager.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leavemanager.R;
import com.example.leavemanager.models.RegisterModel;
import com.example.leavemanager.networking.RetrofitClient;
import com.example.leavemanager.ui.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    RelativeLayout progressLyt;
    Button register;
    EditText domainName,names,emailInput,passwordSet,confirmPassword,usernameInput;
    public String employeeName;
    private Context mContext;
    TextView clickToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressLyt = findViewById(R.id.progressLoad);
        register = findViewById(R.id.register);
        domainName = findViewById(R.id.domain);
        names = findViewById(R.id.names);
        emailInput = findViewById(R.id.email);
        passwordSet =findViewById(R.id.password);
        clickToLogin = findViewById(R.id.clickToLogin);
        confirmPassword = findViewById(R.id.passwordConfirm);
        usernameInput = findViewById(R.id.username);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        clickToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void registerUser() {
        showProgress();
        String domain = domainName.getText().toString();
        String name = names.getText().toString();
        String username = usernameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordSet.getText().toString();
        String password_confirmation = confirmPassword.getText().toString();
        Call<RegisterModel> call = RetrofitClient.getInstance(RegisterActivity.this)
                .getApiConnector()
                .register(domain,name,username,email,password,password);
        call.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                hideProgress();
                if(response.code()==201){
                    domainName.getText().clear();
                    names.getText().clear();
                    usernameInput.getText().clear();
                    emailInput.getText().clear();
                    passwordSet.getText().clear();
                    confirmPassword.getText().clear();
                    Toast.makeText(RegisterActivity.this,"Registration successful",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(RegisterActivity.this,response.code()+response.message()+"",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {
                hideProgress();
                Toast.makeText(RegisterActivity.this,t.getMessage()+"",Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void hideProgress() {
        progressLyt.setVisibility(View.INVISIBLE);
    }

    private void showProgress() {
        progressLyt.setVisibility(View.VISIBLE);
    }
}
