package com.example.sunshineschoolsystem;


import static com.example.sunshineschoolsystem.AssignmentsAdapter.ALLOWED_FILE_TYPES;
import static com.example.sunshineschoolsystem.AssignmentsAdapter.PICK_FILE_REQUEST_CODE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class Assignments extends AppCompatActivity {
    RecyclerView rvAssignments;
    FirebaseAuth mAuth;
    ProgressBar progressBar4;
    ImageView btnBack3;
    ArrayList<AssignmentsModel>ass;
    AssignmentsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);
        initViews();
        progressBar4.setVisibility(View.VISIBLE);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference assRef = database.getReference("Students").child(mAuth.getCurrentUser().getUid()).child("Assignments");


        assRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot assSnapshot : dataSnapshot.getChildren()) {
                    // Extract fee details
                    String desc = assSnapshot.child("AssDescription").getValue(String.class);
                    String due = assSnapshot.child("AssDueDate").getValue(String.class);
                    String  Subject = assSnapshot.child("AssSubject").getValue(String.class);


                    // Create FeesModel instance and add to the list
                    AssignmentsModel AssignmentModel = new AssignmentsModel(Subject,desc,due);
                    ass.add(AssignmentModel);
                }

                rvAssignments.setLayoutManager(new LinearLayoutManager(Assignments.this));
                adapter=new AssignmentsAdapter(ass,Assignments.this);
                rvAssignments.setAdapter(adapter);
                progressBar4.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors if any
                Toast.makeText(Assignments.this, database.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }


        @Override
        public  void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
                Uri fileUri = data.getData();
                String fileType = getContentResolver().getType(fileUri);

                if (isValidFileType(fileType)) {
                    String description = "Assignment";
                    uploadFileToFirebase(fileUri, description);
                } else {
                    Toast.makeText(this, "Invalid file type. Only PDF and Word files are allowed.", Toast.LENGTH_SHORT).show();
                }
            }
        }

        private boolean isValidFileType(String fileType) {
            for (String allowedType : ALLOWED_FILE_TYPES) {
                if (allowedType.equals(fileType)) {
                    return true;
                }
            }
            return false;
        }


        private void uploadFileToFirebase(Uri fileUri, String description) {
            progressBar4.setVisibility(View.VISIBLE);
            String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            StorageReference storageRef = FirebaseStorage.getInstance().getReference(userID);
            StorageReference fileRef = storageRef.child(description + getFileExtension(fileUri));

            UploadTask uploadTask = fileRef.putFile(fileUri);

            uploadTask.addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    progressBar4.setVisibility(View.GONE);
                    // File uploaded successfully
                    Toast.makeText(this, "File uploaded successfully.", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar4.setVisibility(View.GONE);
                    // Handle upload failure
                    Toast.makeText(this, "File upload failed.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private String getFileExtension(Uri uri) {
            ContentResolver contentResolver = getContentResolver();
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            return "." + mime.getExtensionFromMimeType(contentResolver.getType(uri));
        }


    private void initViews(){
        rvAssignments=findViewById(R.id.rvAssignments);
        mAuth=FirebaseAuth.getInstance();
        ass=new ArrayList<>();
        btnBack3=findViewById(R.id.btnBack3);
        btnBack3.setOnClickListener(view -> onBackPressed());
        progressBar4=findViewById(R.id.progressBar4);
    }
}