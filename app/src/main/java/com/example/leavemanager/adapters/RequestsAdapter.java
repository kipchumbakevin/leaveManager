package com.example.leavemanager.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leavemanager.ui.DetailsActivity;
import com.example.leavemanager.R;
import com.example.leavemanager.models.ViewRequestsModel;
import java.util.ArrayList;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.RequestViewHolder> {
    private static final String CURRENT_POSITION_VALUE = "com.example.leavemanager.adapters,current_value";
    public static final String LEAVE_DETAILS = "com.example.leavemanager.adapters.leaveDetails";
    private final Context mContext;
    private final ArrayList<ViewRequestsModel> mRequestsArrayList;
    private final LayoutInflater mLayoutInflator;

    public static int mCurrentPosition;
    public RequestsAdapter(Context context, ArrayList<ViewRequestsModel> requestsModelArrayList){
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
    public void onBindViewHolder(@NonNull RequestViewHolder holder, final int position) {
        ViewRequestsModel viewRequestsModel = mRequestsArrayList.get(position);
        holder.confirmation.setText(viewRequestsModel.getStatus()+"");
        holder.requestLeaveType.setText(viewRequestsModel.getAbsencetype());
        holder.dateFromRequest.setText(viewRequestsModel.getDatefrom());
        holder.dateToRequest.setText(viewRequestsModel.getDateto());
        holder.leaveCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra(LEAVE_DETAILS,mRequestsArrayList.get(position));
                mContext.startActivity(intent);
            }
        });
       mCurrentPosition = position;

    }

    @Override
    public int getItemCount() {
        return mRequestsArrayList.size();
    }

    public class RequestViewHolder extends RecyclerView.ViewHolder {
        TextView requestLeaveType,dateFromRequest,dateToRequest,confirmation;
        CardView leaveCard;
        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            requestLeaveType = itemView.findViewById(R.id.requestLeaveType);
            dateFromRequest = itemView.findViewById(R.id.dateFromRequest);
            dateToRequest = itemView.findViewById(R.id.dateToRequest);
            confirmation = itemView.findViewById(R.id.confirmation);
            leaveCard=itemView.findViewById(R.id.leaveCard);

        }
    }
}
