package com.example.leavemanager.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leavemanager.DetailsActivity;
import com.example.leavemanager.R;
import com.example.leavemanager.models.RequestsModel;

import java.util.ArrayList;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.RequestViewHolder> {
    private final Context mContext;
    private final ArrayList<RequestsModel> mRequestsArrayList;
    private final LayoutInflater mLayoutInflator;

    public RequestsAdapter(Context context, ArrayList<RequestsModel> requestsModelArrayList){
      mContext = context;
      mRequestsArrayList = requestsModelArrayList;
      mLayoutInflator = LayoutInflater.from(mContext);
   }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = mLayoutInflator.inflate(R.layout.requests_view,viewGroup,false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        RequestsModel requestsModel = mRequestsArrayList.get(position);
        holder.requestLeaveType.setText(requestsModel.getAbsencetype());
        holder.dateFromRequest.setText(requestsModel.getDatefrom());
        holder.dateToRequest.setText(requestsModel.getDateto());
        holder.mcurrentPosition = position;
    }

    @Override
    public int getItemCount() {
        return mRequestsArrayList.size();
    }

    public class RequestViewHolder extends RecyclerView.ViewHolder {
        public int mcurrentPosition;
        TextView requestLeaveType,dateFromRequest,dateToRequest;
        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            requestLeaveType = itemView.findViewById(R.id.requestLeaveType);
            dateFromRequest = itemView.findViewById(R.id.dateFromRequest);
            dateToRequest = itemView.findViewById(R.id.dateToRequest);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DetailsActivity.class);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
