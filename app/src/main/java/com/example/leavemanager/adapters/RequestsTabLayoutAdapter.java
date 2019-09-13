package com.example.leavemanager.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.leavemanager.ui.ViewRequestsFragment;



public class RequestsTabLayoutAdapter extends FragmentStatePagerAdapter {

    public RequestsTabLayoutAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {


          return       ViewRequestsFragment.newInstance(position);

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "Approved";
            case 1:
                return "Pending";
            case 2:
                return "Rejected";
            default:
                return null;
        }
    }
}
