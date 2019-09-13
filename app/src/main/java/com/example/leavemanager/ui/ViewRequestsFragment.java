package com.example.leavemanager.ui;


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
import com.example.leavemanager.models.ViewRequestsModel;
import com.example.leavemanager.networking.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewRequestsFragment extends Fragment {
    private  static final String REQUEST_TYPE = "com.example.leavemanager.ui.REQUEST_TYPE" ;
    public static ArrayList<ViewRequestsModel> mRequestsArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    RequestsAdapter requestsAdapter;
    RelativeLayout progressLyt;
    int position = 78;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_view_requests, container, false);
        if (savedInstanceState!=null){
            position = savedInstanceState.getInt("position");
        }
        progressLyt = view.findViewById(R.id.progressLoad);
        recyclerView = view.findViewById(R.id.requests_recyclerView);
        requestsAdapter = new RequestsAdapter(getActivity(),mRequestsArrayList);
        recyclerView.setAdapter(requestsAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));

       int requestType=(int)getArguments().get(REQUEST_TYPE);
        if(requestType==0){
            viewApproved();
        }else if (requestType==1){
            viewPending();
        }else if (requestType==2){
            viewRejected();
        }
       return view;

    }

    private void viewRejected() {
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
                    requestsAdapter.notifyDataSetChanged();

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

    private void viewPending() {
        showProgress();
        ArrayList<ViewRequestsModel> mRequestsArray;
        mRequestsArrayList.clear();
        Call<List<ViewRequestsModel>> call = RetrofitClient.getInstance(getActivity())
                .getApiConnector()
                .viewPending();
        call.enqueue(new Callback<List<ViewRequestsModel>>() {
            @Override
            public void onResponse(Call<List<ViewRequestsModel>> call, Response<List<ViewRequestsModel>> response) {
                hideProgress();
                if(response.code()==200){
                    mRequestsArrayList.addAll(response.body());
                    requestsAdapter.notifyDataSetChanged();

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

    public static ViewRequestsFragment newInstance(int position){
ViewRequestsFragment vrf=new ViewRequestsFragment();
Bundle bundle=new Bundle();
bundle.putInt(REQUEST_TYPE,position);
        vrf.setArguments(bundle);
        return vrf;
    }

    public void viewApproved(){
        showProgress();
        ArrayList<ViewRequestsModel> mRequestsArray;
        mRequestsArrayList.clear();
        Call<List<ViewRequestsModel>> call = RetrofitClient.getInstance(getActivity())
                .getApiConnector()
                .viewApproved();
        call.enqueue(new Callback<List<ViewRequestsModel>>() {
            @Override
            public void onResponse(Call<List<ViewRequestsModel>> call, Response<List<ViewRequestsModel>> response) {
                hideProgress();
                if(response.code()==200){
                    mRequestsArrayList.addAll(response.body());
                    requestsAdapter.notifyDataSetChanged();

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
