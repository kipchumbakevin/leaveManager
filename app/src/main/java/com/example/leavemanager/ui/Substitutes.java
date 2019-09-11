package com.example.leavemanager.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leavemanager.R;
import com.example.leavemanager.adapters.SubstitutesAdapter;
import com.example.leavemanager.models.SubstitutesModel;
import com.example.leavemanager.networking.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Substitutes extends DialogFragment {

    public static final String SUBBSTITUTE_SELECTED = "com.example.leavemanager.ui.selectedsubstitute";
    public static ArrayList<SubstitutesModel> mSubstitutesArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    SubstitutesAdapter substitutesAdapter;
    RelativeLayout progressLyt;
    int position = 0;
    private Context mContext;

    public Substitutes(Context context){
        mContext = context;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.substitutes,null);
//        if (savedInstanceState!=null){
//            position = savedInstanceState.getInt("position");
//        }
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                sendResult(SubstitutesAdapter.theString,Activity.RESULT_OK);
            }
        };
        progressLyt = view.findViewById(R.id.progressLoad);
        recyclerView = view.findViewById(R.id.substitutes_recyclerView);
        substitutesAdapter = new SubstitutesAdapter(getActivity(),mSubstitutesArrayList);
        recyclerView.setAdapter(substitutesAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        viewSubstitutes();
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Select Substitute")
                .setPositiveButton(R.string.Ok,listener)
                .create();
    }

    private void viewSubstitutes() {
        showProgress();
        ArrayList<SubstitutesModel> mSubstitutesArray;
        mSubstitutesArrayList.clear();
        Call<List<SubstitutesModel>> call = RetrofitClient.getInstance(getActivity())
                .getApiConnector()
                .addSubstitute();
        call.enqueue(new Callback<List<SubstitutesModel>>() {
            @Override
            public void onResponse(Call<List<SubstitutesModel>> call, Response<List<SubstitutesModel>> response) {
                hideProgress();
                if(response.code()==200){
                    mSubstitutesArrayList.addAll(response.body());
                    substitutesAdapter.notifyDataSetChanged();

                }
                else{

                }
            }

            @Override
            public void onFailure(Call<List<SubstitutesModel>> call, Throwable t) {
                hideProgress();
            }

        });
    }
    private void hideProgress() {
        progressLyt.setVisibility(View.INVISIBLE);
    }

    private void showProgress() {
        progressLyt.setVisibility(View.VISIBLE);
    }


   public void sendResult(String selectedSubstitute,int resultCode){
        if(getTargetFragment()==null){
            return;}
            Intent intent =new Intent();
            intent.putExtra(SUBBSTITUTE_SELECTED,selectedSubstitute);
            getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);
   }
    public void endDialog(){
        Substitutes dialog = new Substitutes(mContext);
        dialog.dismiss();
    }


}
