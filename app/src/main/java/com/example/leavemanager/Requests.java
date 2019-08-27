package com.example.leavemanager;

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

import com.example.leavemanager.adapters.RequestsAdapter;
import com.example.leavemanager.models.RequestsModel;
import com.example.leavemanager.models.RequestsModel_;
import com.example.leavemanager.ob_box.ObjectBox;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.query.Query;


public class Requests extends Fragment {
    public static ArrayList<RequestsModel> mRequestsArrayList = new ArrayList<>();
    RecyclerView mRecyclerView;
    private Box<RequestsModel> mProductBox;
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
       mRecyclerView = view.findViewById(R.id.requests_recyclerView);
       mRequestsAdapter = new RequestsAdapter(getActivity(),mRequestsArrayList);
       mRecyclerView.setAdapter(mRequestsAdapter);
       mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        initObjectBox();
       return view;
    }

    private void initObjectBox() {
        mProductBox = ObjectBox.get().boxFor(RequestsModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateProducts();
    }

    private void updateProducts() {
        mRequestsArrayList.clear();
        mProductQuery = mProductBox.query().order(RequestsModel_.__ID_PROPERTY).build();
        List<RequestsModel>requestsModels = mProductQuery.find();
        mRequestsArrayList.addAll(requestsModels);
        mRequestsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position",position);
    }
}
