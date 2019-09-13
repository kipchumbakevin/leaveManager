package com.example.leavemanager.networking;

import com.example.leavemanager.models.LeaveComments;
import com.example.leavemanager.models.LoginModel;
import com.example.leavemanager.models.RequestsModel;
import com.example.leavemanager.models.RegisterModel;
import com.example.leavemanager.models.SubstitutesModel;
import com.example.leavemanager.models.UsersModel;
import com.example.leavemanager.models.ViewRequestsModel;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonPlaceHolderInterface {
    @FormUrlEncoded
    @POST("api/create/leave")
    Call <RequestsModel> sendapplication(
            @Field("absencetype")String absence,
            @Field("datefrom")String datefrom,
            @Field("dateto")String dateto,
            @Field("reason")String reason,
            @Field("substitute")String substitute,
            @Field("comment")String comment
    );

    @FormUrlEncoded
    @POST("api/auth/signup")
    Call<RegisterModel>register(
            @Field("domain")String domain,
            @Field("name")String name,
            @Field("username")String username,
            @Field("email")String email,
            @Field("password")String password,
            @Field("password_confirmation")String confirmPassword
    );
    @FormUrlEncoded
    @POST("api/auth/login")
    Call<UsersModel>loginUser(
            @Field("domain")String domain,
            @Field("username")String username,
            @Field("password")String password
    );
    @GET("api/leaveapproved")
    Call<List<ViewRequestsModel>> viewRequests();
    @GET("api/leaveapproved")
    Call<List<ViewRequestsModel>> viewApproved();
    @GET("api/leaverejected")
    Call<List<ViewRequestsModel>> viewRejected();
    @GET("api/leavepending")
    Call<List<ViewRequestsModel>> viewPending();
    @GET("api/substitutes")
    Call<List<SubstitutesModel>> addSubstitute();
    @GET("api/comments/{leaveid}")
    Call<LeaveComments>viewComments(@Path("leaveid")String leaveid);
}
