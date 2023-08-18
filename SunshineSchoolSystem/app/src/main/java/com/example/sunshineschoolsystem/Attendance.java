package com.example.sunshineschoolsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

public class Attendance extends AppCompatActivity {
    CalendarView calendarView;
    String date;
    ProgressBar progressBarAttendance;
    Button btnCheckAttendance;
    ImageView backbtn;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    TextView tvAttendanceStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        init();
        progressBarAttendance.setVisibility(View.INVISIBLE);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                 date = i2+"/"+(i1+1)+"/"+i;

            }
        });

        btnCheckAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(date==null) {
                    Toast.makeText(Attendance.this, "Please Select Date", Toast.LENGTH_SHORT).show();
                }else{
                progressBarAttendance.setVisibility(View.VISIBLE);
                db.getReference().child("Students").child(mAuth.getCurrentUser().getUid()).child("Attendance").child("August").child(date.substring(0,2)).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            progressBarAttendance.setVisibility(View.INVISIBLE);
                            tvAttendanceStatus.setText("Attendance Status: "+ dataSnapshot.getValue().toString());

                        }
                        else{
                            progressBarAttendance.setVisibility(View.INVISIBLE);
                            Toast.makeText(Attendance.this, "Make sure date is correct "+ date, Toast.LENGTH_SHORT).show();
                            tvAttendanceStatus.setText("Attendance Record Does not Exist for " + date);
                        }
                    }
                });


            }}
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void init(){
        progressBarAttendance=findViewById(R.id.progressBarAttendance);
        calendarView=findViewById(R.id.calendarView);
        btnCheckAttendance=findViewById(R.id.btnCheckAttendance);
        backbtn=findViewById(R.id.backbtn);
        mAuth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
        tvAttendanceStatus=findViewById(R.id.tvAttendanceStatus);




    }
}