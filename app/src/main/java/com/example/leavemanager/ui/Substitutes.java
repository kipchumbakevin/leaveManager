package com.example.leavemanager.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

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
    private static final String DIALOG_SUBSTITUTES = "Substitutes Dialog";
    public static ArrayList<SubstitutesModel> mSubstitutesArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    SubstitutesAdapter substitutesAdapter;
    int position = 0;
    private Context mContext;

    public Substitutes(Context context){
        mContext = context;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.substitutes,null);
//        if (savedInstanceState!=null){
//            position = savedInstanceState.getInt("position");
//        }
        recyclerView = view.findViewById(R.id.substitutes_recyclerView);
        substitutesAdapter = new SubstitutesAdapter(getActivity(),mSubstitutesArrayList);
        recyclerView.setAdapter(substitutesAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        viewSubstitutes();
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Select Substitute")
                .create();
    }

    private void viewSubstitutes() {
        ArrayList<SubstitutesModel> mSubstitutesArray;
        mSubstitutesArrayList.clear();
        Call<List<SubstitutesModel>> call = RetrofitClient.getInstance(getActivity())
                .getApiConnector()
                .addSubstitute();
        call.enqueue(new Callback<List<SubstitutesModel>>() {
            @Override
            public void onResponse(Call<List<SubstitutesModel>> call, Response<List<SubstitutesModel>> response) {
                if(response.code()==200){
                    mSubstitutesArrayList.addAll(response.body());
                    substitutesAdapter.notifyDataSetChanged();

                }
                else{

                }
            }

            @Override
            public void onFailure(Call<List<SubstitutesModel>> call, Throwable t) {
            }

        });
    }


    public void startDialog(FragmentManager fragmentManager){

        Substitutes dialog = new
                Substitutes(mContext);
        dialog.show(fragmentManager, DIALOG_SUBSTITUTES);

        // View view=LayoutInflater.from(mContext).inflate(R.layout.substitutes_activity);
    }
    public void endDialog(FragmentManager fragmentManager){
        Substitutes dialog = new Substitutes(mContext);
        dialog.dismiss();
    }
}
