package com.example.sunshineschoolsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fees extends AppCompatActivity {
    ArrayList<FeesModel> Fees;
    RecyclerView rvFees;
    FeesAdapter adapter;
    FirebaseDatabase db;
    FirebaseAuth mAuth;
    ImageView feesback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees);
        init();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference feesRef = database.getReference("Students").child(mAuth.getCurrentUser().getUid()).child("Fees");


        feesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot feesSnapshot : dataSnapshot.getChildren()) {
                    // Extract fee details
                    int amount = feesSnapshot.child("amount").getValue(Integer.class);
                    String due = feesSnapshot.child("due").getValue(String.class);
                    int fine = feesSnapshot.child("fine").getValue(Integer.class);
                    String status = feesSnapshot.child("status").getValue(String.class);

                    // Create FeesModel instance and add to the list
                    FeesModel feesModel = new FeesModel(String.valueOf(amount), due, String.valueOf(fine), status);
                    Fees.add(feesModel);
                }

                rvFees.setLayoutManager(new LinearLayoutManager(Fees.this));
                adapter=new FeesAdapter(Fees.this,Fees);
                rvFees.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors if any
                Toast.makeText(Fees.this, database.toString(), Toast.LENGTH_SHORT).show();
            }
        });














    }



            private void init(){
        rvFees=findViewById(R.id.rvFees);
        Fees=new ArrayList<>();
        feesback=findViewById(R.id.feesback);
        feesback.setOnClickListener(view -> onBackPressed());
        db=FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();



    }
}