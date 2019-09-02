package com.example.leavemanager.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
public class DatePickerFragment extends DialogFragment {
   DatePickerDialog.OnDateSetListener onDateSetListener;
   private int year,month,day;
   public DatePickerFragment(){}

   public void setCallBack(DatePickerDialog.OnDateSetListener ondate){
       onDateSetListener = ondate;
   }
    @SuppressLint("NewApi")
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        year = args.getInt("year");
        month = args.getInt("month");
        day = args.getInt("day");


    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new DatePickerDialog(getActivity(),onDateSetListener,year,month,day);
    }
}
