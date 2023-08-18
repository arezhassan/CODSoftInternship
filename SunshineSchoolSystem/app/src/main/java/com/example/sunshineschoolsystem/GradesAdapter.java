package com.example.sunshineschoolsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class GradesAdapter extends RecyclerView.Adapter<GradesAdapter.ViewHolder> {
    private List<GradesModel> gradelist;
    Context context;

    public GradesAdapter(Context context,List<GradesModel> gradelist) {
        this.gradelist = gradelist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.singlegrade, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GradesModel grade = gradelist.get(position);

        holder.tvDate.setText(grade.getDate());
        holder.tvObtainedMarks.setText(grade.getObtainedMarks());
        holder.tvTotalMarks.setText(grade.getTotalMarks());
        holder.tvType.setText(grade.getType());
    }

    @Override
    public int getItemCount() {
        return gradelist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvType, tvObtainedMarks, tvTotalMarks;

        ViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvType = itemView.findViewById(R.id.tvType);
            tvObtainedMarks = itemView.findViewById(R.id.tvObtainedMarks);
            tvTotalMarks = itemView.findViewById(R.id.tvTotalMarks);
        }
    }
}
