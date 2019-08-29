package com.example.leavemanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leavemanager.R;
import com.example.leavemanager.models.DetailsModel;

import java.util.ArrayList;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder> {
    private final Context mContext;
    private final LayoutInflater mLayoutInflator;
    private final ArrayList<DetailsModel> mDetailsArrayList;

    public DetailsAdapter(Context context,ArrayList<DetailsModel> detailsModelArrayList) {
        mContext = context;
        mDetailsArrayList = detailsModelArrayList;
        mLayoutInflator = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public DetailsAdapter.DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflator.inflate(R.layout.comments_details,null);
        return new DetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsAdapter.DetailsViewHolder holder, int position) {
        DetailsModel detailsModel = mDetailsArrayList.get(position);
        holder.duration.setText(detailsModel.getDuration());
        holder.username.setText(detailsModel.getUsername());
        holder.dateToday.setText(detailsModel.getDateToday());
        holder.mcurrentPosition = position;

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DetailsViewHolder extends RecyclerView.ViewHolder {
        public int mcurrentPosition;
        TextView duration,username,dateToday;

        public DetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            duration = itemView.findViewById(R.id.duration);
            username = itemView.findViewById(R.id.username);
            dateToday = itemView.findViewById(R.id.dateToday);
        }
    }
}
