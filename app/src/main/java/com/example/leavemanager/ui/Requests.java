package com.example.leavemanager.ui;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.leavemanager.R;
import com.example.leavemanager.adapters.RequestsAdapter;
import com.example.leavemanager.models.RequestsModel;
import com.example.leavemanager.models.ViewRequestsModel;
import com.example.leavemanager.networking.RetrofitClient;
import com.example.leavemanager.ob_box.ObjectBox;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.query.Query;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Requests extends Fragment {
    public static ArrayList<ViewRequestsModel> mRequestsArrayList = new ArrayList<>();
    RecyclerView mRecyclerView;
    RelativeLayout progressLyt;
    RequestsAdapter mRequestsAdapter;
    int position = 78;
    private Query <RequestsModel>mProductQuery;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_requests, container, false);
       if (savedInstanceState!=null){
           position = savedInstanceState.getInt("position");
       }
       progressLyt = view.findViewById(R.id.progressLoad);
       mRecyclerView = view.findViewById(R.id.requests_recyclerView);
       mRequestsAdapter = new RequestsAdapter(getActivity(),mRequestsArrayList);
       mRecyclerView.setAdapter(mRequestsAdapter);
       mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        //initObjectBox();
        viewRequests();
       return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    public void viewRequests(){
        showProgress();
        ArrayList<ViewRequestsModel> mRequestsArray;
        mRequestsArrayList.clear();
        Call<List<ViewRequestsModel>> call = RetrofitClient.getInstance(getActivity())
                .getApiConnector()
                .viewRejected();
        call.enqueue(new Callback<List<ViewRequestsModel>>() {
            @Override
            public void onResponse(Call<List<ViewRequestsModel>> call, Response<List<ViewRequestsModel>> response) {
                hideProgress();
                if(response.code()==200){
                    mRequestsArrayList.addAll(response.body());
                    mRequestsAdapter.notifyDataSetChanged();

                }
                else{

                }
            }

            @Override
            public void onFailure(Call<List<ViewRequestsModel>> call, Throwable t) {
                hideProgress();
                Toast.makeText(getActivity(),"Connection failed",Toast.LENGTH_LONG).show();
            }

        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position",position);
    }
    private void hideProgress() {
        progressLyt.setVisibility(View.INVISIBLE);
    }

    private void showProgress() {
        progressLyt.setVisibility(View.VISIBLE);
    }
}
