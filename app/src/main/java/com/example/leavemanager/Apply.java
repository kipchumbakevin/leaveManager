package com.example.leavemanager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leavemanager.models.RequestsModel;
import com.example.leavemanager.networking.RetrofitClient;
import com.example.leavemanager.ob_box.ObjectBox;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.objectbox.Box;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Apply extends Fragment{
    TextView dateFrom,dateTo,absenceType;
    private Box<RequestsModel>mProductBox;
    Button apply;
    EditText reason,comment;
    private RequestsModel model;
    private ArrayList<RequestsModel> mRequestsList = new ArrayList<>();
    private Context mContext;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_apply, container, false);
        mProductBox = ObjectBox.get().boxFor(RequestsModel.class);
        dateFrom = view.findViewById(R.id.dateFrom);
        dateTo = view.findViewById(R.id.dateTo);
        reason = view.findViewById(R.id.reason);
        comment = view.findViewById(R.id.comment);
        absenceType = view.findViewById(R.id.absenceType);
        apply = view.findViewById(R.id.apply);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendapplication();
                reason.getText().clear();
                comment.getText().clear();
                Toast.makeText(getActivity(), absenceType.getText().toString() + " applied", Toast.LENGTH_SHORT).show();
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

//    private void addRequest() {
//        String confirmation = absenceType.getText().toString();
//        String absencetype = absenceType.getText().toString();
//        String datefrom = dateFrom.getText().toString();
//        String dateto = dateTo.getText().toString();
//        model = new RequestsModel(confirmation,absencetype,datefrom,dateto);
//        mProductBox.put(this.model);
//    }
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

        String absenceT = absenceType.getText().toString();
        String dateF = dateFrom.getText().toString();
        String dateT = dateTo.getText().toString();
        String reasonR = reason.getText().toString();
        String commentC = comment.getText().toString();
        RequestsModel requestsModel = new RequestsModel("Confirmed",absenceT, dateF,dateT,reasonR,commentC);
        Call<List<RequestsModel>> call = RetrofitClient.getInstance(mContext)
                .getApiConnector()
                .sendapplication(requestsModel);
        call.enqueue(new Callback<List<RequestsModel>>() {
            @Override
            public void onResponse(Call<List<RequestsModel>> call, Response<List<RequestsModel>> response) {
                if(response.code()==200){
                    Toast.makeText(mContext,"Sent", Toast.LENGTH_SHORT).show();
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<List<RequestsModel>> call, Throwable t) {
            }
        });
    }
}
