package com.example.leavemanager.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.leavemanager.R;

import me.itangqi.waveloadingview.WaveLoadingView;

public class Entitlement extends Fragment {
    ImageView dropdown,dropUp;
    LinearLayoutCompat droppedDown;
    WaveLoadingView waveLoadingView;
    int progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_entitlement, container, false);
       dropdown = view.findViewById(R.id.dropDown);
       dropUp = view.findViewById(R.id.dropUp);
       waveLoadingView = view.findViewById(R.id.waveLoadingView);
       droppedDown = view.findViewById(R.id.layoutDropped);
       //progress = view.findViewById(R.id.progress);
        waveLoadingView.setCenterTitle("16 days");
        waveLoadingView.setProgressValue(progress);
        waveLoadingView.setProgressValue(100);

       dropdown.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               droppedDown.setVisibility(View.VISIBLE);
               dropdown.setVisibility(View.GONE);
               dropUp.setVisibility(View.VISIBLE);

           }
       });
       dropUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               droppedDown.setVisibility(View.GONE);
               dropUp.setVisibility(View.GONE);
               dropdown.setVisibility(View.VISIBLE);
           }
       });
       return view;
    }


}
