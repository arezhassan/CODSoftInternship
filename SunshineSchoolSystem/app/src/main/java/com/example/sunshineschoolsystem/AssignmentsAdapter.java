package com.example.sunshineschoolsystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AssignmentsAdapter extends RecyclerView.Adapter<AssignmentsAdapter.ViewHolder> {
    public static final int PICK_FILE_REQUEST_CODE = 123;
    public static final String[] ALLOWED_FILE_TYPES = {"application/pdf", "application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"};
    private List<AssignmentsModel> assignmentsList;
    private Context context;
    FirebaseAuth mAuth;

    public AssignmentsAdapter(List<AssignmentsModel> assignmentsList, Context context) {
        this.assignmentsList = assignmentsList;
        this.context = context;
        mAuth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleassignment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AssignmentsModel assignment = assignmentsList.get(position);

        holder.tvAssSubject.setText(assignment.getSubject());
        holder.tvAssDescription.setText(assignment.getDescription());
        holder.tvAssDueDate.setText(assignment.getDueDate());

        // Set click listener for the upload button
        holder.btnUploadAssignment.setOnClickListener(v -> {
            // Handle the upload button click here
            String description = assignmentsList.get(position).getDescription();
            openFilePicker(description);

        });
    }

    @Override
    public int getItemCount() {
        return assignmentsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAssSubject, tvAssDescription, tvAssDueDate;
        ImageView btnUploadAssignment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAssSubject = itemView.findViewById(R.id.tvAssSubject);
            tvAssDescription = itemView.findViewById(R.id.tvAssDescription);
            tvAssDueDate = itemView.findViewById(R.id.tvAssDueDate);
            btnUploadAssignment = itemView.findViewById(R.id.btnUploadAssignment);


        }
    }

    private void openFilePicker(String description) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_MIME_TYPES, ALLOWED_FILE_TYPES);
        ((Activity) context).startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
    }


    // Override onActivityResult in your Activity/Fragment
    // and integrate the rest of the logic there as shown in the previous responses
}
