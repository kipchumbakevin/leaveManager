package com.example.leavemanager.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.leavemanager.R;
import com.example.leavemanager.auth.LoginActivity;

public class SharedPreferencesConfig {
    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPreferencesConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.SHARED_PREFERENCES),Context.MODE_PRIVATE);
    }

    public void saveAuthenticationInformation(String acessToken, String domain, String email,String username, String status){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.EMPLOYEE_TOKEN),acessToken);
        editor.putString(context.getResources().getString(R.string.EMPLOYEE_DOMAIN),domain);
        editor.putString(context.getResources().getString(R.string.EMPLOYEE_EMAIL),email);
        editor.putString(context.getResources().getString(R.string.EMPLOYEE_STATUS),status);
        editor.putString(context.getResources().getString(R.string.EMPLOYEE_USERNAME),username);

        editor.commit();

    }

    public String readEmployeerDomain(){
        String domain;
        domain = sharedPreferences.getString(context.getResources().getString(R.string.EMPLOYEE_DOMAIN),"");
        return  domain;
    }

    public String readEmployeeEmail(){
        String email;
        email = sharedPreferences.getString(context.getResources().getString(R.string.EMPLOYEE_EMAIL),"");
        return  email;
    }
    public String readEmployeeUsername(){
        String username;
        username = sharedPreferences.getString(context.getResources().getString(R.string.EMPLOYEE_USERNAME),"");
        return  username;
    }

    public String readEmployeeAccessToken(){
        String acessToken;
        acessToken = sharedPreferences.getString(context.getResources().getString(R.string.EMPLOYEE_TOKEN),"");
        return acessToken;
    }

    public String readEmployeeStatus(){
        String status;
        status = sharedPreferences.getString(context.getResources().getString(R.string.EMPLOYEE_STATUS),"");
        return status;
    }
    public  boolean isloggedIn(){
        return readEmployeeStatus().equals(Constants.ACTIVE_CONSTANT);
    }

    public void clear() {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.commit();
        context.startActivity(new Intent(context, LoginActivity.class));
    }
}
