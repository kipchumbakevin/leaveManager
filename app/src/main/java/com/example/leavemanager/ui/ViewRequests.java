package com.example.leavemanager.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.example.leavemanager.R;
import com.example.leavemanager.adapters.RequestsTabLayoutAdapter;
import com.google.android.material.tabs.TabLayout;

public class ViewRequests extends AppCompatActivity {
    ImageView goBack;
    TabLayout tabLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_requests);
        goBack = findViewById(R.id.goBackHome);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewRequests.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        tabLayout = findViewById(R.id.cart_tab);
        tabLayout.setTabsFromPagerAdapter(new RequestsTabLayoutAdapter(getSupportFragmentManager()));
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getTabContent(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        int tabPosition = tabLayout.getSelectedTabPosition() + 1;
        getTabContent(tabPosition);

    }

    public void getTabContent(int tabIndex){
        ViewRequestsFragment tabContentFragment = ViewRequestsFragment.newInstance(tabIndex);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.fragment, tabContentFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}
