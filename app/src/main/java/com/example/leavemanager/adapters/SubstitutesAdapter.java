package com.example.leavemanager.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.leavemanager.R;
import com.example.leavemanager.models.SubstitutesModel;
import com.example.leavemanager.ui.Substitutes;

import java.util.ArrayList;
public class SubstitutesAdapter extends RecyclerView.Adapter<SubstitutesAdapter.SubstitutesHolder> {


    private final Context mContext;
    private final ArrayList<SubstitutesModel> mSubstitutesArrayList;
    private final LayoutInflater mLayoutInflator;
    private FragmentManager fragmentManager;
    public static String theString;

    public SubstitutesAdapter(Context context, ArrayList<SubstitutesModel>substitutesArrayList){
        mContext = context;
        mSubstitutesArrayList = substitutesArrayList;
        mLayoutInflator = LayoutInflater.from(mContext);
    }
    @NonNull
    @Override
    public SubstitutesAdapter.SubstitutesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflator.inflate(R.layout.substitutes_activity,parent,false);
        return new SubstitutesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubstitutesAdapter.SubstitutesHolder holder, int position) {
        final SubstitutesModel substitutesModel = mSubstitutesArrayList.get(position);
        holder.substituteName.setText(substitutesModel.getName());
        holder.substituteEmail.setText(substitutesModel.getEmail());
        holder.subcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           theString=substitutesModel.getName();
                Toast.makeText(mContext,theString+ " selected",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mSubstitutesArrayList.size();
    }

    public class SubstitutesHolder extends RecyclerView.ViewHolder {
        TextView substituteName,substituteEmail;
        CardView subcard;
        public SubstitutesHolder(@NonNull final View itemView) {
            super(itemView);
            substituteName = itemView.findViewById(R.id.substitutename);
            substituteEmail = itemView.findViewById(R.id.substitutemail);
            subcard=itemView.findViewById(R.id.subCard);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,substituteName.getText()+ " selected",Toast.LENGTH_SHORT).show();
                }
            });


        }
    }
}
