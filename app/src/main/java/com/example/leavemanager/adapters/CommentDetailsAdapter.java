package com.example.leavemanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leavemanager.R;
import com.example.leavemanager.models.ViewRequestsModel;
import com.example.leavemanager.utils.SharedPreferencesConfig;

import java.util.ArrayList;

public class CommentDetailsAdapter extends RecyclerView.Adapter<CommentDetailsAdapter.CommentViewHolders> {

    private final Context mContext;
    private final ArrayList<ViewRequestsModel> mRequestArrayList;
    private final LayoutInflater mLayoutInflator;

    public CommentDetailsAdapter(Context context, ArrayList<ViewRequestsModel> requestsModelArrayList){
        mContext = context;
        mRequestArrayList = requestsModelArrayList;
        mLayoutInflator = LayoutInflater.from(mContext);
    }
    @NonNull
    @Override
    public CommentDetailsAdapter.CommentViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflator.inflate(R.layout.comments_details,parent,false);
        return new CommentViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentDetailsAdapter.CommentViewHolders holder, int position) {
        ViewRequestsModel viewRequestsModel = mRequestArrayList.get(position);
        holder.comment.setText(viewRequestsModel.getComment());
        holder.username.setText(new SharedPreferencesConfig(mContext).readEmployeeUsername() +",");

    }

    @Override
    public int getItemCount() {
        return mRequestArrayList.size();
    }

    public class CommentViewHolders extends RecyclerView.ViewHolder {
        TextView comment,username,dateCommented;
        public CommentViewHolders(@NonNull View itemView) {
            super(itemView);
            comment = itemView.findViewById(R.id.comment);
            username = itemView.findViewById(R.id.username);
            dateCommented = itemView.findViewById(R.id.dateCommented);
        }
    }
}
