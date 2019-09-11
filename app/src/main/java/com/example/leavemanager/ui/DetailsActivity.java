package com.example.leavemanager.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leavemanager.R;
import com.example.leavemanager.adapters.CommentDetailsAdapter;
import com.example.leavemanager.adapters.RequestsAdapter;
import com.example.leavemanager.models.Comment;
import com.example.leavemanager.models.Leave;
import com.example.leavemanager.models.LeaveComments;
import com.example.leavemanager.models.ViewRequestsModel;
import com.example.leavemanager.networking.RetrofitClient;
import com.example.leavemanager.utils.SharedPreferencesConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {
    public  ArrayList<Comment> mRequestsArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    ImageView goBack;
    int position = 78;
    RelativeLayout progressLyt;
    CommentDetailsAdapter commentDetailsAdapter;
    TextView employeeName,applicationStatus,datefrom,dateto,period,leavetype;
    ViewRequestsModel mViewRequestModel;

    @Override
    protected void onResume() {
        super.onResume();
        mViewRequestModel=getIntent().getParcelableExtra(RequestsAdapter.LEAVE_DETAILS);
        datefrom.setText(mViewRequestModel.getDatefrom()+" to,");
        dateto.setText(mViewRequestModel.getDateto());
        period.setText("substitute");
        leavetype.setText(mViewRequestModel.getAbsencetype());
        applicationStatus.setText(mViewRequestModel.getStatus());
        viewRequests();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState!=null){
            position = savedInstanceState.getInt("position");
        }
        setContentView(R.layout.activity_details);
        goBack = findViewById(R.id.goBack);
        datefrom = findViewById(R.id.datefrom);
        dateto = findViewById(R.id.dateto);
        period = findViewById(R.id.period);
        leavetype = findViewById(R.id.leavetype);
        employeeName = findViewById(R.id.employeeName);
        progressLyt = findViewById(R.id.progressLoad);
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
        employeeName.setText(new SharedPreferencesConfig(DetailsActivity.this).readEmployeerName());


    }
    public void viewRequests(){
        showProgress();
        mRequestsArrayList.clear();

        Call<LeaveComments> call = RetrofitClient.getInstance(DetailsActivity.this)
                .getApiConnector()
                .viewComments(mViewRequestModel.getId().toString());
        call.enqueue(new Callback<LeaveComments>() {
            @Override
            public void onResponse(Call<LeaveComments> call, Response<LeaveComments> response) {
                hideProgress();
                if(response.code()==200){

                    assert response.body() != null;
                    mRequestsArrayList.addAll(response.body().getComments());
                    commentDetailsAdapter.notifyDataSetChanged();

                }
                else{

                }
            }

            @Override
            public void onFailure(Call<LeaveComments> call, Throwable t) {
                hideProgress();
                Toast.makeText(DetailsActivity.this,"Connection failed", Toast.LENGTH_LONG).show();

            }

        });

    }
    private void hideProgress() {
        progressLyt.setVisibility(View.INVISIBLE);
    }

    private void showProgress() {
        progressLyt.setVisibility(View.VISIBLE);
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
