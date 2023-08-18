package com.example.sunshineschoolsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity {
    TextView tvNameCard, tvAge, tvClass, tvSection, tvReg;
    ImageView imgAvatar;
    FirebaseDatabase db;
    FirebaseAuth mAuth;
    ImageView btnBack;
    ProgressBar progressBar3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        init();
        progressBar3.setVisibility(View.VISIBLE);
        DatabaseReference studentRef = db.getReference().child("Students").child(mAuth.getCurrentUser().getUid());

        studentRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String name = dataSnapshot.child("name").getValue(String.class);
                    int age = dataSnapshot.child("age").getValue(Integer.class); // Retrieve as Integer
                    int cl = dataSnapshot.child("class").getValue(Integer.class);
                    String section = dataSnapshot.child("section").getValue(String.class);
                    int reg= dataSnapshot.child("reg").getValue(Integer.class);

                    // Now you have all the data in the variables, you can use them as needed
                    tvNameCard.setText("Name: " +name);
                    tvAge.setText(String.valueOf("Age: "+age));
                    tvClass.setText(String.valueOf("Class: " +cl));
                    tvSection.setText("Section: "+ section);
                    tvReg.setText(String.valueOf("Registration: "+ reg));
                    progressBar3.setVisibility(View.GONE);



                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors that occur
                Toast.makeText(Dashboard.this, databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });




    }
    private void init(){
        tvNameCard = findViewById(R.id.tvNameCard);
        tvAge = findViewById(R.id.tvAge);
        tvClass = findViewById(R.id.tvClass);
        tvSection = findViewById(R.id.tvSection);
        tvReg = findViewById(R.id.tvReg);
        imgAvatar = findViewById(R.id.imgAvatar);
        db = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        btnBack = findViewById(R.id.btnBack);
        progressBar3 = findViewById(R.id.progressBar3);



    }
}