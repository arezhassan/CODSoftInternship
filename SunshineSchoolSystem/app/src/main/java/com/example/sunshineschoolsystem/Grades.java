package com.example.sunshineschoolsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.RecoverySystem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class Grades extends AppCompatActivity {
    RecyclerView rvGrades;
    ArrayList<GradesModel> grades;
    ImageView btnBack2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
        init();
        grades.add(new GradesModel("16/08/2023","100","83","Quiz 1"));
        grades.add(new GradesModel("16/08/2023","100","100","HomeWork"));
        grades.add(new GradesModel("16/08/2023","100","82","Quiz 2"));
        grades.add(new GradesModel("16/08/2023","100","98","Quiz 3"));
        grades.add(new GradesModel("16/08/2023","100","53","Quiz 4"));


        GradesAdapter adapter= new GradesAdapter(this,grades);
        rvGrades.setLayoutManager(new LinearLayoutManager(this));
        rvGrades.setAdapter(adapter);
        btnBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private void init(){
        rvGrades=findViewById(R.id.rvGrades);
        grades=new ArrayList<GradesModel>();
        btnBack2=findViewById(R.id.btnBack2);

    }
}