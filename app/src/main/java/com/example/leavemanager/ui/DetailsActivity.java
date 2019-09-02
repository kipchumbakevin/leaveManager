package com.example.leavemanager.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leavemanager.R;
import com.example.leavemanager.adapters.CommentDetailsAdapter;
import com.example.leavemanager.models.ViewRequestsModel;
import com.example.leavemanager.networking.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {
    public static ArrayList<ViewRequestsModel> mRequestsArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    ImageView goBack;
    int position = 78;
    CommentDetailsAdapter commentDetailsAdapter;
    TextView employeeName,applicationStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState!=null){
            position = savedInstanceState.getInt("position");
        }
        setContentView(R.layout.activity_details);
        goBack = findViewById(R.id.goBack);
        employeeName = findViewById(R.id.employeeName);
        applicationStatus = findViewById(R.id.applicationStatus);
        recyclerView = findViewById(R.id.requests_recyclerView_details);
        commentDetailsAdapter = new CommentDetailsAdapter(DetailsActivity.this,mRequestsArrayList);
        recyclerView.setAdapter(commentDetailsAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(DetailsActivity.this,1));

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        viewRequests();

    }
    public void viewRequests(){
        ArrayList<ViewRequestsModel> mRequestsArray;
        mRequestsArrayList.clear();
        Call<List<ViewRequestsModel>> call = RetrofitClient.getInstance(DetailsActivity.this)
                .getApiConnector()
                .viewRequests();
        call.enqueue(new Callback<List<ViewRequestsModel>>() {
            @Override
            public void onResponse(Call<List<ViewRequestsModel>> call, Response<List<ViewRequestsModel>> response) {
                if(response.code()==200){
                    mRequestsArrayList.addAll(response.body());
                    commentDetailsAdapter.notifyDataSetChanged();

                }
                else{

                }
            }

            @Override
            public void onFailure(Call<List<ViewRequestsModel>> call, Throwable t) {
            }

        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(DetailsActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position",position);
    }
}
