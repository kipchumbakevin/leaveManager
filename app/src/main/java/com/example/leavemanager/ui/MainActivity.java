package com.example.leavemanager.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.leavemanager.R;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.example.leavemanager.auth.LoginActivity;
import com.example.leavemanager.utils.SharedPreferencesConfig;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView usernameEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
//        usernameEmployee = findViewById(R.id.userEmployee);
//        usernameEmployee.setText("something");
        Home fragment = new Home();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragments,fragment,fragment.getTag()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_apply) {
           Apply fragment = new Apply();
           FragmentManager manager = getSupportFragmentManager();
           manager.beginTransaction().replace(R.id.fragments,fragment,fragment.getTag()).commit();

        } else if (id == R.id.nav_requests) {
            Intent intent = new Intent(MainActivity.this,ViewRequests.class);
            startActivity(intent);
//            Requests fragment = new Requests();
//            FragmentManager manager = getSupportFragmentManager();
//            manager.beginTransaction().replace(R.id.fragments,fragment,fragment.getTag()).commit();

        } else if (id == R.id.nav_entitlement) {
            Entitlement fragment = new Entitlement();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.fragments, fragment, fragment.getTag()).commit();

        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_logout) {
            new SharedPreferencesConfig(MainActivity.this).clear();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        }else if (id == R.id.nav_home){
            Home fragment = new Home();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.fragments,fragment,fragment.getTag()).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
