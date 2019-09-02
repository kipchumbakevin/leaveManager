package com.example.leavemanager.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leavemanager.ui.DetailsActivity;
import com.example.leavemanager.R;
import com.example.leavemanager.models.ViewRequestsModel;
import java.util.ArrayList;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.RequestViewHolder> {
    private static final String CURRENT_POSITION_VALUE = "com.example.leavemanager.adapters,current_value";
    private final Context mContext;
    private final ArrayList<ViewRequestsModel> mRequestsArrayList;
    private final LayoutInflater mLayoutInflator;
    private int mCurrentPosition;

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

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        String approve = "Approved";
        int colorgreen = R.color.colorGreen;
        ViewRequestsModel viewRequestsModel = mRequestsArrayList.get(position);
        holder.confirmation.setText(viewRequestsModel.getStatus()+"");
        if (holder.confirmation.getText()== approve){
            holder.confirmation.setTextColor(colorgreen);
        }
        holder.requestLeaveType.setText(viewRequestsModel.getAbsencetype());
        holder.dateFromRequest.setText(viewRequestsModel.getDatefrom());
        holder.dateToRequest.setText(viewRequestsModel.getDateto());
        holder.mcurrentPosition = position;
    }

    @Override
    public int getItemCount() {
        return mRequestsArrayList.size();
    }

    public class RequestViewHolder extends RecyclerView.ViewHolder {
        public int mcurrentPosition;
        TextView requestLeaveType,dateFromRequest,dateToRequest,confirmation;
        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            requestLeaveType = itemView.findViewById(R.id.requestLeaveType);
            dateFromRequest = itemView.findViewById(R.id.dateFromRequest);
            dateToRequest = itemView.findViewById(R.id.dateToRequest);
            confirmation = itemView.findViewById(R.id.confirmation);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DetailsActivity.class);
                    intent.putExtra(CURRENT_POSITION_VALUE,mCurrentPosition);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
