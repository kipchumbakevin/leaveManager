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
import com.example.leavemanager.ob_box.ObjectBox;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.objectbox.Box;


public class Apply extends Fragment{
    TextView dateFrom,dateTo,absenceType;
    private Box<RequestsModel>mProductBox;
    Button apply;
    EditText reason,comment;
    private RequestsModel model;

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
                addRequest();
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
                showDatePicker();
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

    private void addRequest() {
        String confirmation = absenceType.getText().toString();
        String absencetype = absenceType.getText().toString();
        String datefrom = dateFrom.getText().toString();
        String dateto = dateTo.getText().toString();
        model = new RequestsModel(confirmation,absencetype,datefrom,dateto);
        mProductBox.put(this.model);
    }

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        Calendar calendar = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year",calendar.get(Calendar.YEAR));
        args.putInt("month",calendar.get(Calendar.MONTH));
        args.putInt("day",calendar.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        date.setCallBack(ondate);
        date.show(getFragmentManager(),"Date picker");
    }
    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateFrom.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year));
                dateTo.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year));
            }
    };

}
