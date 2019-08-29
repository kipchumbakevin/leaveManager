package com.example.leavemanager.networking;

import com.example.leavemanager.models.RequestsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonPlaceHolderInterface {
    @POST("android/applySent/")
    Call <List<RequestsModel>> sendapplication(@Body RequestsModel requestsModel);
}
