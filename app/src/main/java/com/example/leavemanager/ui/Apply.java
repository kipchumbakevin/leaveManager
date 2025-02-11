package com.example.leavemanager.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leavemanager.R;
import com.example.leavemanager.models.EmptyModel;
import com.example.leavemanager.models.RequestsModel;
import com.example.leavemanager.networking.RetrofitClient;
import com.example.leavemanager.ob_box.ObjectBox;
import java.util.ArrayList;
import java.util.Calendar;
import io.objectbox.Box;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Apply extends Fragment{
    TextView dateFrom,dateTo,absenceType,subSelected;
    private Box<EmptyModel>mProductBox;
    private static final String DIALOG_SUBSTITUTES = "Substitutes Dialog";
    private static final int APPLY_REQUEST_CODE = 8789;
    Button apply;
    EditText reasonR,commentC;
    RelativeLayout progressLyt;
    private RequestsModel model;
    private ArrayList<RequestsModel> mRequestsList = new ArrayList<>();
    private Context mContext;
    ImageView addSubstitute;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_apply, container, false);
        mProductBox = ObjectBox.get().boxFor(EmptyModel.class);
        dateFrom = view.findViewById(R.id.dateFrom);
        dateTo = view.findViewById(R.id.dateTo);
        progressLyt = view.findViewById(R.id.progressLoad);
        reasonR = view.findViewById(R.id.reason);
        commentC = view.findViewById(R.id.comment);
        subSelected = view.findViewById(R.id.substituteSelected);
        absenceType = view.findViewById(R.id.absenceType);
        addSubstitute = view.findViewById(R.id.addSubstitute);
        apply = view.findViewById(R.id.apply);
        addSubstitute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startDialog();
            }
        });
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendapplication();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
        dateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });
        absenceType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
               final View mView = getLayoutInflater().inflate(R.layout.absence_type,null);
               final TextView annualLeave = mView.findViewById(R.id.annualLeave);
               final TextView remoteWork = mView.findViewById(R.id.remoteWork);
               final TextView sickLeave = mView.findViewById(R.id.sickLeave);
                alertDialog.setView(mView);
                final AlertDialog absence = alertDialog.create();
                absence.show();

                annualLeave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        absenceType.setText(annualLeave.getText().toString());
                        absenceType.setTextColor(getResources().getColor(R.color.colorBlack));
                        absence.cancel();
                    }
                });
                remoteWork.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        absenceType.setText(remoteWork.getText().toString());
                        absenceType.setTextColor(getResources().getColor(R.color.colorBlack));
                        absence.cancel();
                    }
                });
                sickLeave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        absenceType.setText(sickLeave.getText().toString());
                        absenceType.setTextColor(getResources().getColor(R.color.colorBlack));
                        absence.cancel();
                    }
                });
                }


        });

    }

    private void showDatePicker() {
            DatePickerFragment date = new DatePickerFragment();
            Calendar calendar = Calendar.getInstance();
            Bundle args = new Bundle();
            args.putInt("year", calendar.get(Calendar.YEAR));
            args.putInt("month", calendar.get(Calendar.MONTH));
            args.putInt("day", calendar.get(Calendar.DAY_OF_MONTH));
            date.setArguments(args);
            date.setCallBack(ondate);
            date.show(getFragmentManager(), "Date picker");

    }
    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateFrom.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year));
            }
    };
    private void showDate() {
        DatePickerFragment date = new DatePickerFragment();
        Calendar calendar = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calendar.get(Calendar.YEAR));
        args.putInt("month", calendar.get(Calendar.MONTH));
        args.putInt("day", calendar.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        date.setCallBack(ondateSet);
        date.show(getFragmentManager(), "Date picker");

    }
    DatePickerDialog.OnDateSetListener ondateSet = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dateTo.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year));
        }
    };


    private void sendapplication(){
        showProgress();
        final String absencetype = absenceType.getText().toString();
        String datefrom = dateFrom.getText().toString();
        String dateto = dateTo.getText().toString();
        String reason = reasonR.getText().toString();
        String substitute = subSelected.getText().toString();
        String comment = commentC.getText().toString();
        Call<RequestsModel> call = RetrofitClient.getInstance(getActivity())
                .getApiConnector()
                .sendapplication(absencetype,datefrom,dateto,reason,substitute,comment);
        call.enqueue(new Callback<RequestsModel>() {
            @Override
            public void onResponse(Call<RequestsModel> call, Response<RequestsModel> response) {
                hideProgress();
                if(response.code()==201){
                    reasonR.getText().clear();
                    commentC.getText().clear();
                    Toast.makeText(getActivity(),absencetype + " applied successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(),response+response.message(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RequestsModel> call, Throwable t) {
                hideProgress();
                Toast.makeText(getActivity(),"Connection failed",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void startDialog(){
        Substitutes dialog = new
                Substitutes(mContext);

        dialog.setTargetFragment(Apply.this,APPLY_REQUEST_CODE);
        dialog.show(getFragmentManager(), DIALOG_SUBSTITUTES);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode!= Activity.RESULT_OK){
            return;
        }
        if(requestCode==APPLY_REQUEST_CODE){
            String selectedSub=data.getStringExtra(Substitutes.SUBBSTITUTE_SELECTED);
            subSelected.setVisibility(View.VISIBLE);
            subSelected.setText(selectedSub);

        }
    }
    private void hideProgress() {
        progressLyt.setVisibility(View.INVISIBLE);
    }

    private void showProgress() {
        progressLyt.setVisibility(View.VISIBLE);
    }
}
