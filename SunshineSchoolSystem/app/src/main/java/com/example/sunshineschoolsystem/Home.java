package com.example.sunshineschoolsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {
    TextView tvDashboard,tvLogout,tvHomework,tvFees,tvGrades,tvAttendance,tvName;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    ProgressBar progressBarHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        progressBarHome.setVisibility(View.GONE);

        progressBarHome.setVisibility(View.VISIBLE);

        db.getReference().child("Students").child(mAuth.getCurrentUser().getUid()).child("name").get().addOnSuccessListener(dataSnapshot -> {
            tvName.setText(dataSnapshot.getValue().toString());
            progressBarHome.setVisibility(View.GONE);

        });
        tvLogout.setOnClickListener(view -> {
            progressBarHome.setVisibility(View.VISIBLE);

            mAuth.signOut();
            progressBarHome.setVisibility(View.GONE);

            Intent i=new Intent(Home.this,Login.class);
            startActivity(i);
            finish();

        });
        tvHomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this,Assignments.class);
                startActivity(i);
            }
        });
        tvGrades.setOnClickListener(view -> {
            Intent i = new Intent (Home.this, Grades.class);
            startActivity(i);
        });
        tvAttendance.setOnClickListener(view -> {
            Intent i= new Intent(Home.this,Attendance.class);
            startActivity(i);
        });
        tvDashboard.setOnClickListener(view -> {
            progressBarHome.setVisibility(View.VISIBLE);

            Intent i=new Intent(Home.this,Dashboard.class);
            startActivity(i);
            progressBarHome.setVisibility(View.GONE);

        });
        tvFees.setOnClickListener(view -> {
            Intent i= new Intent(Home.this,Fees.class);
            startActivity(i);
        });

    }
    private void init(){
        tvDashboard=findViewById(R.id.tvDashboard);
        tvLogout=findViewById(R.id.tvLogout);
        tvHomework=findViewById(R.id.tvHomework);
        tvFees=findViewById(R.id.tvFees);
        tvGrades=findViewById(R.id.tvGrades);
        tvAttendance=findViewById(R.id.tvAttendance);
        tvName=findViewById(R.id.tvName);
        mAuth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
        progressBarHome=findViewById(R.id.progressBarHome);
}}