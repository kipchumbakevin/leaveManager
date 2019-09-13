package com.example.leavemanager.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.leavemanager.R;
import com.example.leavemanager.utils.SharedPreferencesConfig;

public class Home extends Fragment {
    TextView employeeUsername,employeeDomain;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_home, container, false);
       employeeDomain = view.findViewById(R.id.employeeDomain);
       employeeUsername = view.findViewById(R.id.employee_username);
       employeeDomain.setText(new SharedPreferencesConfig(getActivity()).readEmployeerDomain());
       employeeUsername.setText(new SharedPreferencesConfig(getActivity()).readEmployeeUsername());
       return view;
    }

}