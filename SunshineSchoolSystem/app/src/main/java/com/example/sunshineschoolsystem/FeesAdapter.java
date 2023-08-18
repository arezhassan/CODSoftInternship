package com.example.sunshineschoolsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FeesAdapter extends RecyclerView.Adapter<FeesAdapter.ViewHolder> {
    private List<FeesModel> feesList;
    Context context;

    public FeesAdapter(Context context,List<FeesModel> feesList) {
        this.feesList = feesList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.singlefeecard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeesModel fee = feesList.get(position);

        holder.tvAmount.setText(fee.getAmount());
        holder.tvDue.setText(fee.getDue());
        holder.tvStatus.setText(fee.getStatus());
        holder.tvFine.setText(fee.getFine());
    }

    @Override
    public int getItemCount() {
        return feesList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAmount, tvDue, tvStatus, tvFine;

        ViewHolder(View itemView) {
            super(itemView);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvDue = itemView.findViewById(R.id.tvDue);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvFine = itemView.findViewById(R.id.tvFine);
        }
    }
}
