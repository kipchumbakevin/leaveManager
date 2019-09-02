package com.example.leavemanager.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leavemanager.R;
import com.example.leavemanager.models.RegisterModel;
import com.example.leavemanager.networking.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    Button register;
    EditText domainName,names,emailInput,passwordSet,confirmPassword,usernameInput;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = findViewById(R.id.register);
        domainName = findViewById(R.id.domain);
        names = findViewById(R.id.names);
        emailInput = findViewById(R.id.email);
        passwordSet =findViewById(R.id.password);
        confirmPassword = findViewById(R.id.passwordConfirm);
        usernameInput = findViewById(R.id.username);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String domain = domainName.getText().toString();
        String name = names.getText().toString();
        String username = usernameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordSet.getText().toString();
        final String password_confirmation = confirmPassword.getText().toString();
        Call<List<RegisterModel>> call = RetrofitClient.getInstance(RegisterActivity.this)
                .getApiConnector()
                .register(domain,name,username,email,password,password);
        call.enqueue(new Callback<List<RegisterModel>>() {
            @Override
            public void onResponse(Call<List<RegisterModel>> call, Response<List<RegisterModel>> response) {
                if(response.code()==201){
                    domainName.getText().clear();
                    names.getText().clear();
                    usernameInput.getText().clear();
                    emailInput.getText().clear();
                    passwordSet.getText().clear();
                    confirmPassword.getText().clear();
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();                }
                else{

                }
                Toast.makeText(RegisterActivity.this,response.code(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<RegisterModel>> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}
